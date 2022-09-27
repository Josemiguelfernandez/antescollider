TDesintegrator8 : Tmodule2 {

	*def
	{


	^SynthDef(\tDesintegrator8, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, probability = 0.5, multiplier = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8) + (In.ar(inbus, 8) * ingain);
			sig = Disintegrator.ar(sig, probability, multiplier)*amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tDesintegrator8",
			type: \fx,
			main: \probability,
			sliders: [
				\probability -> ControlSpec(0, 4, \lin, 0.001, 0.5, \prob),
				\multiplier -> ControlSpec(-1, 5, \lin, 0.001, 1, \mult),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}



