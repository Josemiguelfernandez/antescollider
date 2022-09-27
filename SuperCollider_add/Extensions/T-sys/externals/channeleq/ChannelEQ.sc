// requires wslib & TabbedView quarks
// wslib 2009, revised Nathan Ho 2014
// adapted for Tsys dec 2015

ChannelEQ {

    var <target, <numChannels, <server, <bus, outsynth;
    var <synth, synthdef;

    var <>frdb;
    var <gui;

    *new { |numChannels, server, bus, target|
        var eq;

        eq = super.new.route(numChannels, server, bus, target)
            .init
            .initGUI
            .play;
        eq.gui.stopOnClose = true;
        ^eq;
    }

    *newBare { |frdb|
        ^super.new.init(frdb);
    }

    route { |argNumChannels, argServer, argBus, argTarget|
        // Hack to avoid sclang from yelling at us for using the class name itself
        var mixerChannel = \MixerChannel.asClass;

		"target_target ="++argTarget.postln;
		"toto_eq".postln;
		server = Server.default;
		outsynth = argTarget;
		numChannels = argNumChannels;
		bus = argBus.asBus;

/*        if (argTarget.notNil
            and: mixerChannel.notNil
            and: { argTarget.isKindOf(mixerChannel) }) {
            // MixerChannel in use
			// target = argTarget;
			outsynth = argTarget;
			// server = target.server;
			// numChannels = target.inChannels;
        } {
            if (argBus.isNil) {
                // If no bus is provided, default to zero
                bus = 0;
                // Server
                server = argServer ? Server.default;
                // Number of channels set to 2
                numChannels = argNumChannels ? 2;
            } {
                // If a bus is provided, use it
                bus = argBus.asBus;
                // and its server
                server = argServer ? bus.server;
                // and its number of channels (unless overridden)
                numChannels = argNumChannels ? if (argBus.isNumber) { 2 } { bus.numChannels };
            };
        };*/
    }

    init { |argFrdb|
        frdb = argFrdb ? [[100,0,1], [250,0,1], [1000,0,1], [3500,0,1], [6000,0,1]];

        synthdef = SynthDef("param_beq", { |out = 0, gate = 1, fadeTime = 0.05, doneAction = 2|
            var frdb, input, env;
            env = EnvGen.kr(Env.asr(fadeTime, 1, fadeTime), gate, doneAction: doneAction);
            input = In.ar(out, numChannels);
            input = this.ar(input);
            XOut.ar(out, env, input);
        }).load;
    }

    initGUI {
        gui = ChannelEQGUI(this);
    }

	visible {
		gui.window.visible_(true);
	}


    unlinkGUI {
		// gui = nil;
		gui.window.visible_(false);
    }

    play {
        server.waitForBoot {
            if (target.notNil) {
                // using MixerChannel
				// synth = target.playfx(\param_beq, [\eq_controls, this.toControl]);
            } {
				"outsynth es".postln;
				outsynth.postln;
				"outsynth es".postln;
                // using raw Bus
				// synth = Synth.before(outsynth, \param_beq, [\out, bus, \eq_controls, this.toControl]);
				synth = Synth(\param_beq, [\out, bus, \eq_controls, this.toControl], outsynth, '\addBefore');
            };
            NodeWatcher.register(synth);
        };
    }

    stop {
        if (synth.notNil and: { synth.isPlaying }) {
            synth.set(\gate, 0);
        }
    }

    ar { |input|
        var frdb;
        frdb = this.fromControl(Control.names([\eq_controls]).kr(0!15));

        input = BLowShelf.ar(input, *frdb[0][[0,2,1]].lag(0.1));
        input = BPeakEQ.ar(input, *frdb[1][[0,2,1]].lag(0.1));
        input = BPeakEQ.ar(input, *frdb[2][[0,2,1]].lag(0.1));
        input = BPeakEQ.ar(input, *frdb[3][[0,2,1]].lag(0.1));
        input = BHiShelf.ar(input, *frdb[4][[0,2,1]].lag(0.1));
        input = RemoveBadValues.ar(input);

        ^input;
    }

    fromControl { |controls|
        ^controls.clump(3).collect({ |item|
            [(item[0] + 1000.cpsmidi).midicps, item[1], 10**item[2]]
        });
    }

    toControl {
        ^frdb.collect({ |item, i|
            [item[0].cpsmidi - 1000.cpsmidi, item[1], item[2].log10]
        }).flat;
    }

    sendCurrent {
        if (synth.notNil and: { synth.isPlaying }) {
            synth.setn(\eq_controls, this.toControl);
        }
    }

    doOnServerTree {
        if (synth.isPlaying) { { this.play; }.defer(0.05); };
    }

}

ChannelEQGUI {
    classvar <>prefsFile;
    var channelEQ;
    var <>window;

    var uvw, font;
    var frpresets;
    var bypassButton;
    var selected;
    var tvw, tvwViews;
    var puMenu, puButtons, puFileButtons;
    var <>stopOnClose = false;

    *initClass {
        prefsFile = Platform.userConfigDir +/+ "eq-prefs.dat";
    }

    *new { |channelEQ|
        if (\TabbedView.asClass.notNil) {
            ^super.new.init(channelEQ);
        } { "ChannelEQGUI requires the TabbedView Quark".error };
    }

    tvwRefresh {
        channelEQ.frdb.do({ |item, i|
            item.do({ |subitem, ii|
                tvwViews[i][ii].value = subitem;
            });
        });
    }

    puMenuCheck {
        var index;
        index = frpresets.clump(2).detectIndex({ |item| item[1] == channelEQ.frdb });
        if (index.notNil) {
            puMenu.value = index;
            puButtons[0].enabled_(false);
            if (frpresets[index * 2].asString[..1] == "x_") {
                puButtons[1].enabled_(false);
            } {
                puButtons[1].enabled_(true);
            };
        } {
            puMenu.value = (frpresets.size/2) + 1;
            puButtons[1].enabled_(false);
            puButtons[0].enabled_(true);
        };
    }

    puMenuCreateItems {
        var items;
        items = [];
        frpresets.pairsDo({ |key, value|
            if (key.asString[..1] == "x_") {
                items = items.add(key.asString[2..]);
            } {
                items = items.add(key.asString);
            };
        });
        items = items ++ ["-", "(custom)"];
        puMenu.items = items;
    }

    save {
        File.use(prefsFile, "w", { |f|
            f.write((current: channelEQ.frdb, presets: frpresets).asCompileString);
        });
    }

    revert {
        var contents;
        if (File.exists(prefsFile)) {
            File.use(prefsFile, "r", { |f|
                contents = f.readAllString.interpret;
                //contents.postln;
                channelEQ.frdb = contents[\current];
                frpresets = contents[\presets];
                channelEQ.sendCurrent;
                this.puMenuCreateItems;
                this.puMenuCheck;
                uvw.refresh;
                this.tvwRefresh;
            });
        };
    }

    onSelect {
        var idx = puMenu.value;
        idx.switch(
            puMenu.items.size - 1, { },
            puMenu.items.size - 2, { puMenu.value = puMenu.items.size - 1; },
            {
                channelEQ.frdb = frpresets[(puMenu.value * 2) + 1].deepCopy;
                //frdb.postln;
                channelEQ.sendCurrent;
                uvw.refresh;
                this.tvwRefresh;
                if (frpresets[puMenu.value * 2].asString[..1] == "x_") {
                    puButtons[0].enabled_(false);
                    puButtons[1].enabled_(false);
                } {
                    puButtons[0].enabled_(false);
                    puButtons[1].enabled_(true);
                };
            }
        );
    }

    addPreset {
        var testPreset, addPreset, replacePreset;

        testPreset = { |name = "user"|
            var index, xnames, clpresets;
            name = name.asSymbol;
            index = frpresets.clump(2)
                .detectIndex({ |item| item[0] == name.asSymbol });
            xnames = frpresets.clump(2)
                .select({ |item| item[0].asString[..1] == "x_" })
                .collect({ |item| item[0].asString[2..].asSymbol });
            if (index.isNil) {
                if (xnames.includes(name).not) {
                    addPreset.value(name);
                } {
                    SCAlert("EQ preset '%' cannot be overwritten.\nPlease choose a different name"
                            .format(name), ["ok"]);
                };
            } {
                SCAlert("EQ preset '%' already exists.\nDo you want to overwrite it?"
                        .format(name), ["cancel","ok"],
                        [{}, { replacePreset.value(name, index) }]);
            };
        };

        addPreset = { |name = "user"|
            frpresets = frpresets ++ [name.asSymbol, channelEQ.frdb.deepCopy];
            this.puMenuCreateItems;
            this.puMenuCheck;
        };

        replacePreset = { |name = "x_default", index = 0|
            frpresets[index * 2] = name.asSymbol;
            frpresets[(index * 2)+1] = channelEQ.frdb.deepCopy;
            this.puMenuCreateItems;
            this.puMenuCheck;
        };

        SCRequestString( "user", "Enter a short name for the new preset",
            { |str| testPreset.value(str); });
    }

    deletePreset {
         SCAlert("Are you sure you want to\ndelete preset '%'"
                    .format(puMenu.items[puMenu.value]), ["cancel","ok"],
                    [{}, {
                    frpresets.removeAt( puMenu.value * 2);
                    frpresets.removeAt( puMenu.value * 2);
                    this.puMenuCreateItems;
                    this.puMenuCheck;
                }]);
    }

    mouseDownAction { |vw, x, y, mod|
        var bounds;
        var pt;
        var min = 20, max = 22050, range = 24;

        bounds = vw.bounds.moveTo(0, 0);
        //pt = (x@y) - (bounds.leftTop);
        pt = (x@y);

        selected =  channelEQ.frdb.detectIndex({ |array|
            ((array[0].explin(min, max, 0, bounds.width))@(array[1].linlin(range.neg, range, bounds.height, 0, \none)))
                .dist(pt) <= 5;
        }) ? -1;

        if (selected != -1) { tvw.focus(selected) };
        vw.refresh;
    }

    mouseMoveAction { |vw, x, y, mod|
        var bounds;
        var pt;
        var min = 20, max = 22050, range = 24;
        var frdb = channelEQ.frdb;

        bounds = vw.bounds.moveTo(0,0);
        //pt = (x@y) - (bounds.leftTop);
        pt = (x@y);

        if (selected != -1) {
            case { ModKey(mod).alt }
                {
                if ( ModKey(mod).shift)
                    {
                frdb[selected] = frdb[selected][[0,1]]
                    ++ [y.linexp(bounds.height, 0, 0.1, 10, \none).nearestInList(
                        if ([0,4].includes(selected))
                            {[0.6,1,2.5,5,10]}
                            {[0.1,0.25,0.5,1,2.5,5,10]}

                        )];
                    }
                    {
                frdb[selected] = frdb[selected][[0,1]]
                    ++ [y.linexp(bounds.height, 0, 0.1, 10, \none).clip(
                             if ([0,4].includes(selected)) { 0.6 } {0.1},
                                10).round(0.01)];
                    };
                tvwViews[selected][2].value = frdb[selected][2];
                     }
                { ModKey(mod).shift }
                {
            frdb[selected] = [
                pt.x.linexp(0, bounds.width, min, max)
                    .nearestInList([25,50,75,100,250,500,750,1000,2500,5000,7500,10000]),
                pt.y.linlin(0, bounds.height, range, range.neg, \none)
                    .clip2(range).round(6),
                frdb[selected][2]
                ];
            tvwViews[selected][0].value = frdb[selected][0];
            tvwViews[selected][1].value = frdb[selected][1];
                }
                { true }
                {
            frdb[selected] = [
                pt.x.linexp(0, bounds.width, min, max).clip(20,20000).round(1),
                pt.y.linlin(0, bounds.height, range, range.neg, \none).clip2(range)
                    .round(0.25),
                frdb[selected][2]
                ];
            tvwViews[selected][0].value = frdb[selected][0];
            tvwViews[selected][1].value = frdb[selected][1];
                };
            channelEQ.sendCurrent;
            vw.refresh;
            this.puMenuCheck;
        };

    }

    drawFunc { |vw|
        var freqs, svals, values, bounds, zeroline;
        var freq = 1200, rq = 0.5, db = 12;
        var min = 20, max = 22050, range = 24;
        var vlines = [100,1000,10000];
        var dimvlines = [25,50,75, 250,500,750, 2500,5000,7500];
        var hlines = [-18,-12,-6,6,12,18];
        var pt, strOffset = 11;
        var sr = channelEQ.server.sampleRate;
        var frdb = channelEQ.frdb;

        if (GUI.id === 'swing') { strOffset = 14 };

        bounds = vw.bounds.moveTo(0,0);

        #freq,db,rq = frdb[0] ? [freq, db, rq];

        freqs = ({|i| i } ! (bounds.width+1));
        freqs = freqs.linexp(0, bounds.width, min, max);

        values = [
            BLowShelf.magResponse(freqs, sr, frdb[0][0], frdb[0][2], frdb[0][1]),
            BPeakEQ.magResponse(freqs, sr, frdb[1][0], frdb[1][2], frdb[1][1]),
            BPeakEQ.magResponse(freqs, sr, frdb[2][0], frdb[2][2], frdb[2][1]),
            BPeakEQ.magResponse(freqs, sr, frdb[3][0], frdb[3][2], frdb[3][1]),
            BHiShelf.magResponse(freqs, sr, frdb[4][0], frdb[4][2], frdb[4][1])
        ].ampdb.max(-200).min(200);

        zeroline = 0.linlin(range.neg,range, bounds.height, 0, \none);

        svals = values.sum.linlin(range.neg,range, bounds.height, 0, \none);
        values = values.linlin(range.neg,range, bounds.height, 0, \none);

        vlines = vlines.explin(min, max, 0, bounds.width);
        dimvlines = dimvlines.explin(min, max, 0, bounds.width);

        pt = frdb.collect { |array|
            (array[0].explin(min, max, 0, bounds.width))
            @
            (array[1].linlin(range.neg,range,bounds.height,0,\none));
        };

        Pen.color_(Color.white.alpha_(0.25));
        Pen.roundedRect(bounds, [6,6,0,0]).fill;

        Pen.color = Color.gray(0.2).alpha_(0.5);
        //Pen.strokeRect(bounds.insetBy(-1,-1));

        //Pen.addRect(bounds).clip;
        Pen.roundedRect(bounds.insetBy(0,0), [6,6,0,0]).clip;

        Pen.color = Color.gray(0.2).alpha_(0.125);

        hlines.do({ |hline,i|
            hline = hline.linlin(range.neg,range, bounds.height, 0, \none);
            Pen.line(0@hline, bounds.width@hline)
            });
        dimvlines.do({ |vline,i|
            Pen.line(vline@0, vline@bounds.height);
            });
        Pen.stroke;

        Pen.color = Color.gray(0.2).alpha_(0.5);
        vlines.do({ |vline,i|
            Pen.line(vline@0, vline@bounds.height);
            });
        Pen.line(0@zeroline, bounds.width@zeroline).stroke;

        /*
        Pen.color = Color.white.alpha_(0.5);
        Pen.fillRect(Rect(33, 0, 206, 14));
        */

        Pen.font = font;

        Pen.color = Color.gray(0.2).alpha_(0.5);
        hlines.do({ |hline|
            Pen.stringAtPoint(hline.asString ++ "dB",
                3@(hline.linlin(range.neg,range, bounds.height, 0, \none)
                    - strOffset));
            });
        vlines.do({ |vline,i|
            Pen.stringAtPoint(["100Hz", "1KHz", "10KHz"][i],
                (vline+2)@(bounds.height - (strOffset + 1)));
            });

        //Pen.roundedRect(bounds.insetBy(0.5,0.5), [5,5,0,0]).stroke;

        /*
        if (selected != -1)
            { Pen.stringAtPoint(
                ["low shelf: %hz, %dB, rs=%",
                  "peak 1: %hz, %dB, rq=%",
                  "peak 2: %hz, %dB, rq=%",
                  "peak 3: %hz, %dB, rq=%",
                  "hi shelf: %hz, %dB, rs=%"
                ][selected].format(
                    frdb[selected][0],
                    frdb[selected][1],
                    frdb[selected][2]
                    ),
                35@1);
             }
             { Pen.stringAtPoint("shift: snap, alt: rq", 35@1); };
        */

        values.do({ |svals,i|
            var color;
            color = Color.hsv(
                i.linlin(0,values.size,0,1),
                0.75, 0.5).alpha_(if (selected == i) { 0.75 } { 0.25 });
            Pen.color = color;
            Pen.moveTo(0@(svals[0]));
            svals[1..].do({ |val, i|
                Pen.lineTo((i+1)@val);
                });
            Pen.lineTo(bounds.width@(bounds.height/2));
            Pen.lineTo(0@(bounds.height/2));
            Pen.lineTo(0@(svals[0]));
            Pen.fill;

            Pen.addArc(pt[i], 5, 0, 2pi);

            Pen.color = color.alpha_(0.75);
            Pen.stroke;

            });

        Pen.color = Color.blue(0.5);
        Pen.moveTo(0@(svals[0]));
        svals[1..].do({ |val, i|
            Pen.lineTo((i + 1)@val);
            });
        Pen.stroke;

        Pen.extrudedRect(bounds, [6,6,0,0], 1, inverse: true);

    }

    init { |argChannelEQ|
        channelEQ = argChannelEQ;

        window = Window.new(
            if (channelEQ.target.isNil) {
                "ChannelEQ on bus % (% channels)".format(channelEQ.bus, channelEQ.numChannels)
            } {
                "ChannelEQ on '%' (% channels)".format(channelEQ.target.name, channelEQ.numChannels)
            },
            Rect(385,0,310,200), true

        ).front;

        window.view.decorator = FlowLayout(window.view.bounds, 10@10, 4@0);

        uvw = UserView(window,
            window.view.bounds.insetBy(10,10)
                .height_(window.view.bounds.height - 80)
            ).resize_(5);

        font = Font(Font.defaultSansFace, 10);

        // uvw.relativeOrigin = false;

        uvw.focusColor = Color.clear;

        frpresets = [// x_ = cannot delete or modify
            'x_flat', [[100, 0, 1], [250, 0, 1], [1000, 0, 1], [3500, 0, 1],
                [6000, 0, 1]],
            'x_loudness', [[78.0, 7.5, 0.65], [250, 0, 1], [890.0, -9.5, 3.55],
                [2800.0, 3.5, 1.54], [7400.0, 7.0, 1.0]],
            'x_telephone', [[600.0, -22.0, 0.7], [250, 0, 1], [1200.0, -2.0, 0.5],
                [1800.0, 1.0, 0.5], [4000.0, -22.0, 0.7]]
        ];

        //frdb = frpresets[1].deepCopy;

        selected = -1;

        tvw = TabbedView(window,
            window.view.bounds.insetBy(10,10).height_(35).top_(200),
            ["low shelf", "peak 1", "peak 2", "peak 3", "high shelf"],
            { |i| Color.hsv(i.linlin(0,5,0,1), 0.75, 0.5).alpha_(0.25); }!5)
                .font_(font)
                .resize_(8)
                .tabPosition_(\bottom);

        tvw.focusActions = { |i| { selected = i; uvw.refresh;  }; }!5;

        tvwViews = [];

        window.view.decorator.shift(0,8);

        tvw.views.do({ |view, i|
            var vw_array = [];

            view.decorator = FlowLayout(view.bounds.moveTo(0,0));

            StaticText(view, 35@14).font_(font).align_(\right).string_("freq:");
            vw_array = vw_array.add(
                RoundNumberBox(view, 40@14).font_(font).value_(channelEQ.frdb[i][0])
                    .clipLo_(20).clipHi_(22000)
                    .action_({ |vw|
                        channelEQ.frdb[i][0] = vw.value;
                        channelEQ.sendCurrent;
                        uvw.refresh;
                        this.puMenuCheck;
                        }) );

            StaticText(view, 25@14).font_(font).align_(\right).string_("db:");
            vw_array = vw_array.add(
                RoundNumberBox(view, 40@14).font_(font).value_(channelEQ.frdb[i][1])
                    .clipLo_(-36).clipHi_(36)
                    .action_({ |vw|
                        channelEQ.frdb[i][1] = vw.value;
                        channelEQ.sendCurrent;
                        uvw.refresh;
                        this.puMenuCheck;
                        }) );

            StaticText(view, 25@14).font_(font).align_(\right)
                .string_((0: "rs:", 4:"rs:")[i] ? "rq" );
            vw_array = vw_array.add(
                RoundNumberBox(view, 40@14).font_(font).value_(channelEQ.frdb[i][2])
                    .step_(0.1).clipLo_(if ([0,4].includes(i)) { 0.6 } {0.01}).clipHi_(10)
                    .action_({ |vw|
                        channelEQ.frdb[i][2] = vw.value;
                        channelEQ.sendCurrent;
                        uvw.refresh;
                        this.puMenuCheck;
                        })
                        );

            tvwViews = tvwViews.add(vw_array);

        });

        puMenu = PopUpMenu.new(window, 100@16)
            .font_(font).canFocus_(false)
            .resize_(7);

        if (GUI.id == \swing) {
            puMenu.bounds = puMenu.bounds.insetBy(-3,-3).moveBy(0, 1)
        };

        //this.puMenuCheck;
        puButtons = [
            RoundButton.new(window, 16@16)
                .border_(1)
                .states_([['+']])
                .resize_(7)
                ,
            RoundButton.new(window, 16@16)
                .border_(1)
                .resize_(7)
                .states_([['-']]),
            ];

        puFileButtons = [
            RoundButton.new(window, 55@16)
                .extrude_(true).border_(1).font_(font)
                .states_([["save", Color.black, Color.red(0.75).alpha_(0.25)]])
                .resize_(7),
            RoundButton.new(window,  55@16)
                .extrude_(true).border_(1).font_(font)
                .states_([["revert", Color.black, Color.green(0.75).alpha_(0.25)]])
                .resize_(7)
        ];

        puFileButtons[0].action_ { this.save; };
        puFileButtons[1].action_ { this.revert; };

        this.puMenuCreateItems;

        puMenu.action_ { this.onSelect; };

        puButtons[0].action_ { this.addPreset; };
        puButtons[1].action_ { this.deletePreset; };

        this.puMenuCheck;

        uvw.mouseDownAction_ { |vw, x, y, mod| this.mouseDownAction(vw, x, y, mod); };
        uvw.mouseMoveAction_ { |vw, x, y, mod| this.mouseMoveAction(vw, x, y, mod); };

        uvw.drawFunc = { |vw| this.drawFunc(vw); };

        puFileButtons[1].action.value; // revert

        bypassButton = RoundButton.new(window, 17@17)
                .extrude_(true).border_(1) //.font_(font)
                .states_([
                    ['power', Color.gray(0.2), Color.white(0.75).alpha_(0.25)],
                    ['power', Color.red(0.8), Color.white(0.75).alpha_(0.25)]])
                .value_(1)
                .action_({ |bt| switch(bt.value,
                    1, { channelEQ.play },
                    0, { channelEQ.stop });
                    })
                .resize_(7);

        window.refresh;

        //uvw.refreshInRect(uvw.bounds.insetBy(-2,-2));
		window.userCanClose_(false);
/*        window.onClose_ {
			// if (stopOnClose) { channelEQ.stop; };
			channelEQ.unlinkGUI;
			// channelEQ.window.visible_(false);
			// channelEQ = nil;
        };*/

    }
	close {
		window.close;

	}


}