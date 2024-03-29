TAmpMod : Tmodule2 {

	*def
	{
		^SynthDef(\TAmpMod, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, modfreq = 0, lfnoise_amp = 0, ampmod = 1,lpfilt = 10000,  hpfilt = 50, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1); // + (In.ar(inbus, 1) * ingain);
			// sig = sig * SinOsc.ar(modfreq, 0, ampmod)* amp.dbamp.lag;
			sig = sig * SinOsc.ar(ampmod, 0, 0.5, 1) * max(0, LFDNoise3.kr(lfnoise_amp,1.0));

			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig*amp.dbamp.lag;); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TAmpMod",
			type: \fx,
			main: \ampmod,
			sliders: [
				\ampmod -> ControlSpec(0.0, 100, \lin, 0.1, 1, \Hz),
				\lfnoise_amp -> ControlSpec(0, 100, \lin, 0, 1, \lfnoise_amp),
				// \lpfilt -> ControlSpec(30, 20000, \exp, 0.1, 10000, \lpfilt),
				// \hpfilt -> ControlSpec(30, 20000, \exp, 0.1, 50, \hpfilt),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

