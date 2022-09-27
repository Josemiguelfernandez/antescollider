//Tsys José Miguel Fernández 2013


TrackConstEnv2 {
	var <>outsynth, auxguindexincr, auxbusguindex, <>presetdico, presetpath, presetitems, buffer, bus, outbus, <>group, <>addsynth, count, preset;

	*new {|gbus, channel, env, dur, amp, mix_group ... args|
		^super.newCopyArgs(gbus, channel, env, dur, amp, mix_group, *args).initTestargs(gbus, channel, env, dur, amp, mix_group, *args);
	}

	initTestargs {|gbus, channel, env, dur, amp, mix_group ... args|

		// args.postln;


		// "etapa1".postln;
		/*		amp = 1;
		fadein = 1;
		channel = 1;*/
		preset = preset.asSymbol;

		count = 0;

		group = Group.new(mix_group);

		bus = Bus.audio(group.server, 1);

		outbus = gbus.index+channel; //define output channel

		// "etapa2".postln;

		outsynth = AudioOutEnv.synthenv2(group, bus, env, outbus, amp, dur);
		// dbspec = ControlSpec(0.ampdb, 1.4126.ampdb, \db, 0, 0, \dB);

		// presetdico = Dictionary.new;
		// "etapa3".postln;
		addsynth = Dictionary.new;
		// "etapa4".postln;
		// args[0].postln;
		// args.postln;

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
				this.addmodulepreset(prst, channel); //module, xfade, sliders, multislider, otros
			});
			outsynth.onFree {bus.free}; // libera el bus!!
		} {
			// "etapa5".postln;
			if (args.size != 0) { // si no hay argumentos no envia por ejemplo init de track vacio
				// "etapa6".postln;
				// bus.postln;
				outsynth.onFree {bus.free}; // libera el bus!!
				// "etapa61".postln;
				if (args.indicesOfEqual('#->').notNil) {
					// "etapa62".postln;
					args.clumps(args.indicesOfEqual('#->').differentiate++50).collect({|elem| //separa listas a partir de \to el ++50 agrega 50 items al final para no corta por clumps
						// "etapa7".postln;
						elem.removeAll('#->');
						// elem.postln;
						// "etapa8".postln;
						this.addmodule(elem, channel); //module, params o preset...
					})
				} {
					// "etapa9".postln;
					this.addmodule(args, channel); //module, params o preset...
				}
			}
		}

	}

	addmodule {|elem| //module, xfade, sliders, multislider, otros

		var module, xfade, par, sliders, multi, otros, keymodule, preset;
		var temparams, tempmultiparams, amppar, ampparspec, mainpar, mainparspec;
		var path, path_def, json_path, json_file;

		module = elem[0];
		par = elem.drop(1);
		/*	xfade = prst[1];
		par = prst[2];
		multi = prst[3];
		otros = prst[4];*/

		// module.postln;
		// par.postln;

		// "etapa4".postln;

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
			// preset = Object.readArchive("/Applications/SuperCollider3.6.6/fxPresets/"++module);
			/*preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++module);
			preset = preset[par[1]];
			keymodule = (""++count.asStringToBase(width: 2)++"_"++module).asSymbol;
			sliders = preset.keep(temparams.size);
			multi = preset.drop(temparams.size);
			/*			"preset_preset_preset_preset_preset_preset_preset_".postln;
			sliders.postln;
			multi.postln;
			"preset_preset_preset_preset_preset_preset_preset_".postln;*/

			addsynth.add(keymodule -> module.asClass.synthoutpar(outsynth, [[temparams,sliders].flop.flat++[tempmultiparams, multi].flop.flatten(1), [\buf, buffer], [\in, bus, \out, bus]].flatten(1)));*/

			path = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/").standardizePath();
			path_def = path++module++".json";
			json_path = File(path_def.standardizePath,"r");
			json_file = json_path.readAllString;
			json_path.close;
			preset = json_file.jsonToDict; //convert json to dictionary
			preset = preset[module][par[1]]; //reucpera preset_name contenido
			// preset = preset[par[1]];

			keymodule = (""++count.asStringToBase(width: 2)++"_"++module).asSymbol;
			/*			sliders = preset.keep(temparams.size);
			multi = preset.drop(temparams.size);*/


			addsynth.add(keymodule -> module.asClass.synthoutpar(outsynth, [preset.asKeyValuePairs, [\buf, buffer], [\in, bus, \out, bus], par.drop(2)].flatten(1)));


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


}



