TSmoothDecimator : Tmodule2 {

	*def
	{


		^SynthDef(\TSmoothDecimator, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, rate = 48000, smoothing = 0.5, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = SmoothDecimator.ar(sig, rate, smoothing)*amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TSmoothDecimator",
			type: \fx,
			main: \rate,
			sliders: [
				\rate -> ControlSpec(0, 48000, \lin, 1, 48000, \rate),
				\smoothing -> ControlSpec(0, 5.0, \lin, 0.001, 0.5, \smoothing),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}



