MotorMixEnvir {
	var mixer;
	var <envir;
	var syncFunctions;

	*new{
		^super.new.init;
	}

	init{
		envir = ();
		(["fader", "encoder"] +++ "." +.x (1..8)).do(_.remove($ )).do({arg key;
			envir.put(key.asSymbol, 0.0);
		});
		(["lhs", "rhs", "touch", "mute", "solo", "select", "burn", "multi", "burn_sw", "multi_sw"] +++ "." +.x (1..8)).do(_.remove($ )).do({arg key;
			envir.put(key.asSymbol, 0);
		});
		envir.put("lcdString", String.new(40).fill(Char.space));
		envir.put("segmentString", String.new(2).fill(Char.space));
	}

	put{arg key, val;
		envir.put(key, val);
		this.changed(key, val);
	}

	at{arg key;
		^envir.at(key);
	}

	keys{
		^envir.keys;
	}

	refresh{
		envir.keysValuesDo({arg key, val;
			this.changed(key, val);
		});
		//a deferred lcd update hack, since MMix don't like to fast updates
		fork{
			0.4.wait;
			this.changed('lcdString', envir[\lcdString]);
			0.1.wait;
			this.changed('segmentString', envir[\segmentString]);
		};
	}
}