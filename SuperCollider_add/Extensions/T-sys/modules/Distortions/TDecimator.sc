TDecimator : Tmodule2 {

	*def
	{


		^SynthDef(\TDecimator, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, rate = 48000, bits = 24, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) ;
			sig = Decimator.ar(sig, rate, bits)*amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TDecimator",
			type: \fx,
			main: \rate,
			sliders: [
				\rate -> ControlSpec(0, 48000, \lin, 1, 48000, \rate),
				\bits -> ControlSpec(0, 32, \lin, 1, 24, \bits),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}



