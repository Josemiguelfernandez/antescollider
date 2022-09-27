var dbspec, faderwin, server, lemur_port, fade_val1, fade_val2, fade_val3, fade_val4, fade_val5, fade_val6, fade_val7, fade_val8, fade_val9, fade_val10, fade_val11, fade_val12, fade_val13, fade_val14, dBLow = -80, lemur_in, servers;

// server = server = Server.default;
dbspec = ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB);
lemur_port = 8005; //9876;

// lemur_in = NetAddr("169.254.42.234", 8000); // adress to send from sclang to Lemur
lemur_in = NetAddr("192.168.1.101", 8000); // adress to send from sclang to Lemur

/*~toto = NetAddr("169.254.199.185", 8000);
~toto.sendMsg("/Range/x", 0, 0.6)*/
servers = Array.fill(~scservers.size, 0);
~scservers.order.collect({|ser, i|
	servers[i] = ~scservers[ser];
});
// ~scservers[\local].volume(-20);
lemur_in.postln;
servers[0].postln;
///////////////LEMUR

OSCdef(\lemur_fade1, {|msg|
	{~slider1.valueAction_(msg[1])}.defer;
	// ("lemur "++msg[1]).postln
}, '/Fader1/x', nil, 8005).fix;

OSCdef(\lemur_fade2, {|msg|
	{~slider2.valueAction_(msg[1])}.defer;
}, '/Fader2/x', nil, lemur_port).fix;

OSCdef(\lemur_fade3, {|msg|
	{~slider3.valueAction_(msg[1])}.defer;
}, '/Fader3/x', nil, lemur_port).fix;

OSCdef(\lemur_fade4, {|msg|
	{~slider4.valueAction_(msg[1])}.defer;
}, '/Fader4/x', nil, lemur_port).fix;

OSCdef(\lemur_fade5, {|msg|
	{~slider5.valueAction_(msg[1])}.defer;
}, '/Fader5/x', nil, lemur_port).fix;

OSCdef(\lemur_fade6, {|msg|
	{~slider6.valueAction_(msg[1])}.defer;
}, '/Fader6/x', nil, lemur_port).fix;

OSCdef(\lemur_fade7, {|msg|
	{~slider7.valueAction_(msg[1])}.defer;
}, '/Fader7/x', nil, lemur_port).fix;

OSCdef(\lemur_fade8, {|msg|
	{~slider8.valueAction_(msg[1])}.defer;
}, '/Fader8/x', nil, lemur_port).fix;

OSCdef(\lemur_fade9, {|msg|
	{~slider9.valueAction_(msg[1])}.defer;
}, '/Fader9/x', nil, lemur_port).fix;

OSCdef(\lemur_fade10, {|msg|
	{~slider10.valueAction_(msg[1])}.defer;
}, '/Fader10/x', nil, lemur_port).fix;

OSCdef(\lemur_fade11, {|msg|
	{~slider11.valueAction_(msg[1])}.defer;
}, '/Fader11/x', nil, lemur_port).fix;

OSCdef(\lemur_fade12, {|msg|
	{~slider12.valueAction_(msg[1])}.defer;
}, '/Fader12/x', nil, lemur_port).fix;


OSCdef(\lemur_fade13, {|msg|
	{~slider13.valueAction_(msg[1])}.defer;
}, '/Fader13/x', nil, lemur_port).fix;

OSCdef(\lemur_fade14, {|msg|
	{~slider14.valueAction_(msg[1])}.defer;
}, '/Fader14/x', nil, lemur_port).fix;



faderwin =Window("Levels", Rect(128, 64, 560, 360)).background_(Color(0, 0.1, 0.1, 0.3)).layout_( GridLayout.columns(
	[StaticText().stringColor_(Color.white).string_("local"),~slider1 = Slider().background_(Color.green).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		servers[0].volume = dbspec.map(sld.value);
		~textoutputlev1.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev1 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade1 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("server1"),~slider2 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		servers[1].volume = dbspec.map(sld.value);
		~textoutputlev2.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev2 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade2 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("server2"),~slider3 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		servers[2].volume = dbspec.map(sld.value);
		~textoutputlev3.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev3 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade3 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("server3"),~slider4 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		servers[3].volume = dbspec.map(sld.value);
		~textoutputlev4.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev4 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade4 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("server4"),~slider5 = Slider().background_(Color.blue).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		servers[4].volume = dbspec.map(sld.value);
		~textoutputlev5.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev5 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade5 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("server5"),~slider6 = Slider().background_(Color.blue).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		servers[5].volume = dbspec.map(sld.value);
		~textoutputlev6.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev6 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade6 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("server6"),~slider7 = Slider().background_(Color.blue).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		servers[6].volume = dbspec.map(sld.value);
		~textoutputlev7.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev7 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade7 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("server7"),~slider8 = Slider().background_(Color.blue).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		servers[7].volume = dbspec.map(sld.value);
		~textoutputlev8.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev8 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade8 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("server8"),~slider9 = Slider().background_(Color.blue).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		servers[8].volume = dbspec.map(sld.value);
		~textoutputlev9.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev9 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade9 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],



)).front;
faderwin.onClose_({OSCdef(\homotopy).disable;});

