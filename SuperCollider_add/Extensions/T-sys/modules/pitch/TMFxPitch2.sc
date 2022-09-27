TMFxPitch2 : Tmodule2 {

	*def
	{


	^SynthDef(\TMFxPitch2, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, pitch = 1.0, amp = 0, winsize = 0.1, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = PitchShift.ar(sig, winsize, pitch.lag)*amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TMFxPitch2",
			type: \fx,
			main: \pitch,
			sliders: [
				\pitch -> ControlSpec(0.01, 4,\lin,0.001,1,\pitch),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}