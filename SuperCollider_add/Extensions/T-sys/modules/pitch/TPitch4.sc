TPitch4 : Tmodule2 {

	*def
	{


		^SynthDef(\TPitch4, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, pitch1 = 1.0, pitch2 = 1.0, pitch3 = 1.0, pitch4 = 1.0, amp = -12, winsize = 0.1, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, sig1, sig2, sig3, sig4, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig1 = PitchShift.ar(sig, winsize, pitch1.lag);
			sig2 = PitchShift.ar(sig, winsize, pitch2.lag);
			sig3 = PitchShift.ar(sig, winsize, pitch3.lag);
			sig4 = PitchShift.ar(sig, winsize, pitch4.lag);
			sig = (sig1+sig2+sig3+sig4) * envgate * envpause;

			XOut.ar(out, cross, sig* amp.dbamp.lag); //salida directa a un bus
			// Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TPitch4",
			type: \fx,
			main: \pitch1,
			sliders: [
				\pitch1 -> ControlSpec(0.01, 4,\lin,0.001,1,\pitch),
				\pitch2 -> ControlSpec(0.01, 4,\lin,0.001,1,\pitch),
				\pitch3 -> ControlSpec(0.01, 4,\lin,0.001,1,\pitch),
				\pitch4 -> ControlSpec(0.01, 4,\lin,0.001,1,\pitch),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -12, \dB)
			],
		))

	}

}



