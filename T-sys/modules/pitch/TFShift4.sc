TFShift4 : Tmodule2 {

	*def
	{
		^SynthDef(\TFShift4, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, modfreq1 = 0.0, modfreq2 = 0.0, modfreq3 = 0.0 , modfreq4 = 0.0, amp = -12, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, sig1, sig2, sig3, sig4, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig1 = FreqShift.ar(sig, modfreq1.lag)* amp.dbamp.lag;
			sig2 = FreqShift.ar(sig, modfreq2.lag)* amp.dbamp.lag;
			sig3 = FreqShift.ar(sig, modfreq3.lag)* amp.dbamp.lag;
			sig4 = FreqShift.ar(sig, modfreq4.lag)* amp.dbamp.lag;
			sig = (sig1+sig2+sig3+sig4) * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TFShift4",
			type: \fx,
			main: \modfreq1,
			sliders: [
				\modfreq1 -> ControlSpec(-2400, 2400, \lin, 0, 0, \Hz),
				\modfreq2 -> ControlSpec(-2400, 2400, \lin, 0, 0, \Hz),
				\modfreq3 -> ControlSpec(-2400, 2400, \lin, 0, 0, \Hz),
				\modfreq4 -> ControlSpec(-2400, 2400, \lin, 0, 0, \Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -12, \dB)
			],
		))
	}
}

