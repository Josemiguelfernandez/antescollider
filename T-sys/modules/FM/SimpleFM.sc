SimpleFM : Tmodule2 {


	*def
	{
	^SynthDef(\simplefm, {|out, outbus, outgain = 0, carrfreq = 440, modfreq = 1, modindex = 0, amp=0, matrix_ramp = 0.01, gate = 1, free = 1|
		var sig, envgate, envpause, conversion;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			conversion = 2pi/(Server.default.sampleRate);

			sig = SinOsc.ar(carrfreq, ( (modfreq*modindex)*conversion*SinOsc.ar(modfreq)),0.25);
			//x = Balance2.ar(x[0], x[1], bal);
			//x = Pan2.ar(x, -1);
			sig = sig * envgate * envpause;
			Out.ar(out, sig * amp.dbamp); //salida directa a un bus
			//Out.ar(outbus, sig * outgain); //salida a un bus auxiliar

			}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "simplefm",
			type: \gen,
			main: \carrfreq,
			sliders:  [
				\carrfreq -> ControlSpec(20, 5000, 'exponential', 10, 440, \Hz),
				\modfreq -> ControlSpec(1, 5000, 'exponential', 1, 1, \modfq),
				\modindex -> ControlSpec(0.0, 100, 'linear', 0.01, 0.0,\modidx),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB) // db spec acts weird, so use self made one
			]
		))

	}

}

