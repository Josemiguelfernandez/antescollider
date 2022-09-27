AmpMod_ste : Tmodule2 {

	*def
	{
		^SynthDef(\AmpMod_ste, {|in, out,  xFade = 1,  ampmod = 1,  amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in,  2); // + (In.ar(inbus, 1) * ingain);
			// sig = sig * SinOsc.ar(modfreq, 0, ampmod)* amp.dbamp.lag;
			sig = sig * SinOsc.ar(ampmod, 0, 0.5, 1);
		// sig = sig * SinOsc.ar(ampmod, 0, 0.5, 1) * max(0, LFDNoise3.kr(lfnoise_amp,1.0));

			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig*amp.dbamp.lag;); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "AmpMod_ste",
			type: \fx,
			main: \ampmod,
			sliders: [
				\ampmod -> ControlSpec(0.0, 100, \lin, 0.1, 1, \Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

