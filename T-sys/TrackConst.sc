//Tsys José Miguel Fernández 2013


TrackConst {
	var <>outsynth, auxguindexincr, auxbusguindex, <>presetdico, presetpath, presetitems, buffer, bus, <>group, <>addsynth, count, preset;

	*new {|channel, fadein, amp ... args|
		^super.newCopyArgs(channel, fadein, amp, *args).initTestargs(channel, fadein, amp, *args);
	}

	initTestargs {|channel, fadein, amp ... args|

		// args.postln;



		/*		amp = 1;
		fadein = 1;
		channel = 1;*/
		/*preset = preset.asSymbol;*/

		count = 0;

		group = Group.new;
		bus = Bus.audio(Server.default, 1);

		outsynth = AudioOut.synth2(group, bus, bus.index, fadein, channel, amp);
		// dbspec = ControlSpec(0.ampdb, 1.4126.ampdb, \db, 0, 0, \dB);

		// presetdico = Dictionary.new;
		addsynth = Dictionary.new;

		// presetpath = "./fxPresets/Tracks/*".pathMatch; //.basename
		// presetpath.collect({|path|
		// presetdico.put(path.basename.asSymbol, Object.readArchive(path))
		// });
		if (args[0] == 'trackpreset') { //para preset de track
			presetdico = Dictionary.new;
			preset = args[1].asSymbol;

			presetpath = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/Tracks/*").pathMatch;
			presetpath.collect({|path|
				// path.postln;
				presetdico.put(path.basename.asSymbol, Object.readArchive(path))
			});
			// presetdico.postln;
			presetdico[preset].collect({|prst|
				// prst.postln;
				this.addmodulepreset(prst); //module, xfade, sliders, multislider, otros
			});
			outsynth.onFree {bus.free}; // libera el bus!!
		} {
			outsynth.onFree {bus.free}; // libera el bus!!
			if (args.indicesOfEqual('#->').notNil) {
				args.clumps(args.indicesOfEqual('#->').differentiate++50).collect({|elem| //separa listas a partir de \to el ++50 agrega 50 items al final para no corta por clumps
					elem.removeAll('#->');
					// elem.postln;
					this.addmodule(elem); //module, params o preset...
				})
			} {

				this.addmodule(args); //module, params o preset...
			}
		}

	}


	addmodule {|elem| //module, xfade, sliders, multislider, otros

		var module, xfade, par, multi, otros, keymodule, preset;
		var temparams, amppar, ampparspec, mainpar, mainparspec;

		module = elem[0];
		par = elem.drop(1);
		/*	xfade = prst[1];
		par = prst[2];
		multi = prst[3];
		otros = prst[4];*/

		// module.postln;
		// par.postln;



		temparams = module.asClass.paramslider.collect({|x| x.key});


		// tempmultiparams = module.asClass.parammultislider.collect({|x| x.key});

		if (otros.notNil) {
			if (otros.key == 'buf') {
				buffer = Buffer.read(Server.default, otros.value);
			};
			// others = otros;
		};

		//bus.postln;
		if (par[0] == '#preset') {
			// preset = Object.readArchive("/Applications/SuperCollider3.6.6/fxPresets/"++module);
			preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++module);
			preset = preset[par[1]];
			keymodule = (""++count.asStringToBase(width: 2)++"_"++module).asSymbol;
			// preset.postln;
			addsynth.add(keymodule -> module.asClass.synthoutpar(outsynth, [[temparams,preset].flop.flat, [\buf, buffer], [\in, bus, \out, bus]].flatten(1)));
			addsynth[keymodule].onFree {addsynth.removeAt(keymodule)}; // elimina el synth del diccionario
		}
		{
			/*			if (addsynth.includesKey(module.asSymbol)) {keymodule = (module++"_"++count).asSymbol} //si hay dos o mas modulos repetidos
			{keymodule = module.asSymbol};*/
			keymodule = (""++count.asStringToBase(width: 2)++"_"++module).asSymbol;
			addsynth.add(keymodule -> module.asClass.synthoutpar(outsynth, par++[\in, bus, \out, bus]));
			addsynth[keymodule].onFree {addsynth.removeAt(keymodule)}; // elimina el synth del diccionario
		};
		// module.asClass.synthoutpar(outsynth, par);
		// outsynth.postln;
		count = count + 1;


	}

	addmodulepreset {|prst| //module, xfade, sliders, multislider, otros

		var module, xfade, par, multi, otros, typesynth, keymodule;
		var temparams, tempmultiparams, amppar, ampparspec, mainpar, mainparspec;

		module = prst[0];
		xfade = prst[1];
		par = prst[2];
		multi = prst[3];
		otros = prst[4];



		typesynth = module.asClass.synthtype;

		temparams = module.asClass.paramslider.collect({|x| x.key});

		tempmultiparams = module.asClass.parammultislider.collect({|x| x.key});

		if (otros.notNil) {
			if (otros.key == 'buf') {
				buffer = Buffer.read(Server.default, otros.value);
			};
			// others = otros;
		};

		//bus.postln;
		keymodule = (""++count.asStringToBase(width: 2)++"_"++module).asSymbol;
		// addsynth = module.asClass.synthoutpar(outsynth, [[temparams,par].flop.flat++[tempmultiparams, multi].flop.flatten(1), [\xFade, xfade], [\buf, buffer], [\in, bus, \out, bus]].flatten(1));
		addsynth.add(keymodule -> module.asClass.synthoutpar(outsynth, [[temparams,par].flop.flat++[tempmultiparams, multi].flop.flatten(1), [\xFade, xfade], [\buf, buffer], [\in, bus, \out, bus]].flatten(1)));
		addsynth[keymodule].onFree {addsynth.removeAt(keymodule)}; // elimina el synth del diccionario


	}

	insertafter {|module, moduleafterpos ... params|
		var keymodule;

		/*		if (addsynth.includesKey(module.asSymbol)) {keymodule = (module++"_"++count).asSymbol} //si hay dos o mas modulos repetidos
		{keymodule = module.asSymbol};*/
		keymodule = (""++count.asStringToBase(width: 2)++"_"++module).asSymbol;
		addsynth.add(keymodule -> module.asClass.synthafternw(addsynth[moduleafterpos], bus, params++[\in, bus, \out, bus]));
		addsynth[keymodule].onFree {addsynth.removeAt(keymodule)}; // elimina el synth del diccionario

	}

	gui {
		// addsynth.postln;

		var track, fonction, result, g;
		track = TTracks.new("track", nil, group, bus, outsynth);

		addsynth.order.collect({|x|
			track.addmodfromsynth(x.asString.drop(3).asSymbol, addsynth[x.asSymbol]);
		});

		// synchronous as default values are known to language

		/*fonction = { |synth|
			var x = (), d = SynthDescLib.global[synth.defName.asSymbol];
			d.notNil.if { d.controls.do { |c| x.put(c.name, c.defaultValue) } };
			x
		};

		g = { |synth|
			var x = (), d = SynthDescLib.global[synth.defName.asSymbol];
			d.notNil.if { d.controls.do { |c|  synth.get(c.name,  { |y| x.put(c.name, y) }) } };
			x
		};

		addsynth.postln;
		addsynth.order.collect({|x|
			track.addmodule(x.asString.drop(3).asSymbol);
			addsynth[x].postln;
			result = fonction.(addsynth[x]);
			result.values;
			result = g.(addsynth[x]);
			result
		});*/

	}
}



