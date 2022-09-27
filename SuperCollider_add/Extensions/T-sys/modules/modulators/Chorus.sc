Chorus : Tmodule2 {

	*def
	{
		^SynthDef(\Chorus, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, flangefreq = 0.1, ampmod = 0.005,lpfilt = 10000,  hpfilt = 50, fdback = 0.1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, wet, count, snd;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);
			count = 3;
			wet = LeakDC.ar(LocalIn.ar(1)).tanh * 0.95; // turn up the feedback past 1 for some WILD sounds
			wet = wet + sig;
			wet = 2.collect { |i|
				DelayC.ar(wet, 0.03, LFNoise2.ar(0.1, ((0..count - 1) + (i * 0.5)) / count * 2pi).linlin(-1, 1, 5e-3, 20e-3)).sum / sqrt(count);
			};
			LocalOut.ar(wet.sum);
			snd = (sig + wet) / sqrt(2);
			snd = snd * 0.5;
			snd = snd * envgate * envpause;
			XOut.ar(out, cross, snd); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Chorus",
			type: \fx,
			main: \flangefreq,
			sliders: [
				\flangefreq -> ControlSpec(0.001, 10, \exp, 0.001, 0.1, \Hz),
				\ampmod -> ControlSpec(0.001, 0.015, \lin, 0.001, 0.005, \ampmod),
				\fdback -> ControlSpec(0, 1, \lin, 0.01, 0.1, \fdback),
				\lpfilt -> ControlSpec(30, 20000, \exp, 0.1, 10000, \lpfilt),
				\hpfilt -> ControlSpec(30, 20000, \exp, 0.1, 50, \hpfilt),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

