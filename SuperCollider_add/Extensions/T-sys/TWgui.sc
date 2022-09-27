//Tsys José Miguel Fernández 2013


TWgui {
	var <>outsynth, auxguindexincr, auxbusguindex, <>presetdico, presetpath, presetitems, buffer, bus, <>group, <>addsynth, count;

	*new {|preset, fadein, fadeout, channel, amp|
		^super.newCopyArgs(preset).initTWgui(preset, fadein, fadeout, channel, amp);
	}

	initTWgui {|preset, fadein, fadeout, channel, amp|

		preset = preset.asSymbol;

		count = 1;

		group = Group.new;
		bus = Bus.audio(Server.default, 1);

		outsynth = AudioOut.synth(group, bus, bus.index, fadein, fadeout, channel, amp);
		// dbspec = ControlSpec(0.ampdb, 1.4126.ampdb, \db, 0, 0, \dB);

		presetdico = Dictionary.new;
		addsynth = Dictionary.new;

		presetpath = "./fxPresets/Tracks/*".pathMatch; //.basename
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
		if (addsynth.includesKey(module.asSymbol)) {keymodule = (module++"_"++count).asSymbol} //si hay dos o mas modulos repetidos
			{keymodule = module.asSymbol};
		addsynth.add(keymodule -> module.asClass.synthoutpar(outsynth, [[temparams,par].flop.flat++[tempmultiparams, multi].flop.flatten(1), [\xFade, xfade], [\buf, buffer], [\in, bus, \out, bus]].flatten(1)));

		count = count + 1;


	}
}



