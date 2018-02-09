//Tsys José Miguel Fernández 2013


TTrackspreset {
	var <>presetdico, presetpath;

	*new {
		^super.new.initLoadPreset();
	}


	initLoadPreset {

		presetdico = Dictionary.new;

		presetpath = "./fxPresets/Tracks/*".pathMatch; //.basename
		presetpath.collect({|path|
			presetdico.put(path.basename.asSymbol, Object.readArchive(path))
		});

		}

	names {

		^presetdico.keys.asArray;
	}

		len {

		^presetdico.keys.size;
	}
}

// a = TTrackspreset.new.presetdico
