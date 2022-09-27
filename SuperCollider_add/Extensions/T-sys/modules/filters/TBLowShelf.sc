TBLowShelf : Tmodule2 {

	*def
	{
		^SynthDef(\TBLowShelf, {|in = 0, out = 0, xFade = 1, freq = 440, rs = 1, db = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);
			sig = BLowShelf.ar(sig, freq.lag, rs, db);
			sig = sig * envgate * envpause;
			sig = RemoveBadValues.ar(sig);

			XOut.ar(out, cross, sig* amp.dbamp.lag); //salida directa a un bus

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TBLowShelf",
			type: \fx,
			main: \freq,
			sliders: [
				\freq -> \freq.asSpec,
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

