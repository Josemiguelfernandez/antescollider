//Tsys José Miguel Fernández 2013


TWguiEnv {
	var <>outsynth, auxguindexincr, auxbusguindex, <>presetdico, presetpath, presetitems, buffer, bus, <>group, <>addsynth;

	*new {|preset, env, channel, amp, dur|
		^super.newCopyArgs(preset).initTWgui(preset, env, channel, amp, dur);
	}

	initTWgui {|preset, env, channel, amp, dur|

		preset = preset.asSymbol;


		group = Group.new;
		bus = Bus.audio(Server.default, 1);

		outsynth = AudioOutEnv.synthenv(group, bus, bus.index, env, channel, amp, dur);
		// dbspec = ControlSpec(0.ampdb, 1.4126.ampdb, \db, 0, 0, \dB);

		presetdico = Dictionary.new;

		//presetpath = "./fxPresets/Tracks/*".pathMatch; //.basename ANTIGUO
		presetpath = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/Tracks/*").pathMatch;
		presetpath.collect({|path|
			presetdico.put(path.basename.asSymbol, Object.readArchive(path))
		});
		presetdico[preset].collect({|prst|
			//prst.postln;
			this.addmodule(prst); //module, xfade, sliders, multislider, otros
		});
		outsynth.onFree {bus.free}; // libera el bus!!

	}

	addmodule {|prst| //module, xfade, sliders, multislider, otros

		var module, xfade, par, multi, otros, typesynth;
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
		addsynth = module.asClass.synthoutpar(outsynth, [[temparams,par].flop.flat++[tempmultiparams, multi].flop.flatten(1), [\xFade, xfade], [\buf, buffer], [\in, bus, \out, bus]].flatten(1));



	}
}



