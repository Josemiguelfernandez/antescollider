TMFxShift2 : Tmodule2 {

	*def
	{
		^SynthDef(\TMFxShift2, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, modfreq = 0.0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = FreqShift.ar(sig, modfreq.lag)* amp.dbamp.lag;
			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TMFxShift2",
			type: \fx,
			main: \modfreq,
			sliders: [
				\modfreq -> ControlSpec(-2400, 2400, \lin, 0, 0, \Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

