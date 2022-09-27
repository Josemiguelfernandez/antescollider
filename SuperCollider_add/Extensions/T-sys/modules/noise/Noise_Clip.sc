Noise_Clip : Tmodule2 {

	*def
	{


	^SynthDef(\Noise_Clip, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, speed = 5, round = 10, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, k;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = ClipNoise.ar(0.25);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "noise_Clip",
			type: \gen,
			main: \amp,
			sliders: [
/*				\speed -> ControlSpec(0.1, 10000, \exp, 0, 5,\speed),
				\round -> ControlSpec(1, 10, \lin, 0, 10,\round),*/
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

// Noise