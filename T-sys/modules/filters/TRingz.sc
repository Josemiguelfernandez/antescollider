TRingz : Tmodule2 {

	*def
	{
		^SynthDef(\tRingz, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freq = 440, decaytime = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);
			sig = Ringz.ar(sig, freq, decaytime);
			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig*amp.dbamp.lag); //salida directa a un bus
				// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tRingz",
			type: \fx,
			main: \freq,
			sliders: [
				\freq -> \freq.asSpec,
				\decaytime -> ControlSpec(0.0001, 20.0, \lin, 0.001, 1,\decaytime),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}