TRingMod4 : Tmodule2 {

	*def
	{
		^SynthDef(\tRingMod4, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, modfreq = 1, lpfilt = 10000,  hpfilt = 50, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, modulator;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			modulator = SinOsc.ar(modfreq,
				[0,0.5pi]						// offset phase ofone osc by 90 degrees
			);
			sig  = ring4(sig, modulator);

			sig		= LPF.ar(sig, lpfilt);
			sig		= HPF.ar(sig, hpfilt);

			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tRingMod4",
			type: \fx,
			main: \modfreq,
			sliders: [
				\modfreq -> ControlSpec(10, 4000, \exp, 0.1, 100, \Hz),
				\ampmod -> ControlSpec(0, 10, \lin, 0, 1, \ampmod),
				\lpfilt -> ControlSpec(30, 20000, \exp, 0.1, 10000, \lpfilt),
				\hpfilt -> ControlSpec(30, 20000, \exp, 0.1, 50, \hpfilt),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}
