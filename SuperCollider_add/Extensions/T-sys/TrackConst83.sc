//Tsys José Miguel Fernández 2013-


TrackConst83 {
	var <>outsynth, auxguindexincr, auxbusguindex, <>presetdico, presetpath, presetitems, buffer, <bus, <>group, <>addsynth, count, preset, <>module, <>temparams, <>tempmultiparams;

	*new {|channel, fadein, amp, server ... args|
		^super.newCopyArgs(channel, fadein, amp, server, *args).initTestargs(channel, fadein, amp, server, *args);
	}

	initTestargs {|channel, fadein, amp, server ... args|

		// args.postln;



		/*		amp = 1;
		fadein = 1;
		channel = 1;*/
		preset = preset.asSymbol;

		count = 0;

		group = Group.new(server);
		bus = Bus.audio(server, 8);

		outsynth = AudioOut8.synth2(group, bus, bus.index, fadein, channel, amp);
		// dbspec = ControlSpec(0.ampdb, 1.4126.ampdb, \db, 0, 0, \dB);

		// presetdico = Dictionary.new;
		addsynth = Dictionary.new;

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
			if (args.size != 0) { // si no hay argumentos no envia por ejemplo init de track vacio
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

	}

	addmulti {|args| // add modules avec chaine #->
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

	addmodule {|elem| //module, xfade, sliders, multislider, otros

		var xfade, par, sliders, multi, otros, keymodule, preset;
		var amppar, ampparspec, mainpar, mainparspec;
		var path, path_def, json_path, json_file;

		module = elem[0];
		par = elem.drop(1);
		/*	xfade = prst[1];
		par = prst[2];
		multi = prst[3];
		otros = prst[4];*/

		// module.postln;
		// par.postln;

		// [1,2,3,4,5].drop(2)

		temparams = module.asClass.paramslider.collect({|x| x.key});
		tempmultiparams = module.asClass.parammultislider.collect({|x| x.key});

		// tempmultiparams = module.asClass.parammultislider.collect({|x| x.key});

		if (otros.notNil) {
			if (otros.key == 'buf') {
				buffer = Buffer.read(Server.default, otros.value);
			};
			// others = otros;
		};

		//bus.postln;
		if (par[0] == '#preset') { //recupera preset de modulo (sliders, multisliders)
			/*preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++module);
			preset = preset[par[1]];
			keymodule = (""++count.asStringToBase(width: 2)++"_"++module).asSymbol;
			sliders = preset.keep(temparams.size);
			multi = preset.drop(temparams.size);
			addsynth.add(keymodule -> module.asClass.synthoutpar(outsynth, [[temparams,sliders].flop.flat++[tempmultiparams, multi].flop.flatten(1), [\buf, buffer], [\in, bus, \out, bus], par.drop(2)].flatten(1)));*/

			path = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/").standardizePath();
			path_def = path++module++".json";
			json_path = File(path_def.standardizePath,"r");
			json_file = json_path.readAllString;
			json_path.close;
			preset = json_file.jsonToDict; //convert json to dictionary
			preset = preset[module][par[1]]; //reucpera preset_name contenido
			// preset = preset[par[1]];

			keymodule = (""++count.asStringToBase(width: 2)++"_"++module).asSymbol;
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

		var xfade, par, multi, otros, typesynth, keymodule;
		var amppar, ampparspec, mainpar, mainparspec;

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

		count = count + 1;
	}

	insertafter {|module, moduleafterpos ... params|
		var keymodule;

		/*		if (addsynth.includesKey(module.asSymbol)) {keymodule = (module++"_"++count).asSymbol} //si hay dos o mas modulos repetidos
		{keymodule = module.asSymbol};*/
		keymodule = (""++count.asStringToBase(width: 2)++"_"++module).asSymbol;
		addsynth.add(keymodule -> module.asClass.synthafternw(addsynth[moduleafterpos], bus, params++[\in, bus, \out, bus]));
		addsynth[keymodule].onFree {addsynth.removeAt(keymodule)}; // elimina el synth del diccionario

		// count = count + 1;
	}

	gui {|track_name| //crea gui a a partir de TrackCons nogui
		// addsynth.postln;

		var track, fonction, result, g;
		track = TTracks8.new(track_name.asString, nil, group, bus, outsynth);

		addsynth.order.collect({|x|
			track.addmodfromsynth(x.asString.drop(3).asSymbol, addsynth[x.asSymbol]); //recupera module_name y Synth
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
