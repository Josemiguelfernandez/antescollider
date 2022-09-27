Pluck_trem : Tmodule2 {

	*def
	{


		^SynthDef(\pluck_trem, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, decay = 1, y = 0 , t_trig = 1, freq = 440, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, pitch, triggerSpacing, panSpacing;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = Pluck.ar(WhiteNoise.ar(0.1), t_trig, freq.reciprocal, freq.reciprocal, decay, coef:y);
			sig = sig * envgate * envpause * amp.dbamp.lag;
			DetectSilence.ar(sig, doneAction:14);
			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "pluck_trem",
			type: \gen,
			main: \x,
			sliders: [
				\x -> ControlSpec(0, 1, \lin, 0.001, 0,\x),
				\y -> ControlSpec(0, 1, \lin, 0.001, 0,\y),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}



/*(
    {Pluck.ar(WhiteNoise.ar(0.1), Impulse.kr(2), 440.reciprocal, 440.reciprocal, 10,
	        coef:MouseX.kr(-0.999, 0.999))
    }.play(s)
)*/

// Synth(\pluck_trem)