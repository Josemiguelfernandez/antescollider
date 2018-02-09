TAnalog_blipfml : Tmodule2 {

	classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\TAnalog_blipfml, {|out = -1, outbus = -2, topfreq = 18000, ffreqloMul = 2.5, ffreqhiMul = 16.3, atk = 0.02, dcy = 0.026, ffreqRateCtr = 1, ffreqRateMul = 0.5, modRate = 1, panCtr = 0, panWidth = 1, detune = 1.006, env, vsens = 0, amp = 0, gate = 1, free = 1, matrix_ramp = 0.01|

			// var freqs, amps, ringtimes, sig, pos, conca, len, lista, distarray, offset, envgate, envpause;
			var sig, freqs, amps, signal, pan, ffreqRate, ffreq, panRate, conca, len, lista, envgate, envpause, ampint;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//offset = Line.kr(0, -0.02, 60);
			freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.postln.midicps}));
			amps = \amps.kr(Array.fill(n, { 0.02 + 0.07.rand }));
			signal = Mix.fill(n, { |i|
				sig = Blip.ar(freqs[i] * [1, detune], (topfreq / freqs[i] ).trunc, amps[i]).sum;
				ampint = (Latch.kr(gate, gate) - 1) * vsens + 1;
				ffreqRate = LFNoise1.kr(modRate, ffreqRateMul, ffreqRateCtr);
				ffreq = LFDNoise1.kr(ffreqRate).linexp(-1.0, 1.0, *clip(freqs[i]  * [ffreqloMul, ffreqhiMul], 20, 18000));
				panRate = LFNoise1.kr(modRate, ffreqRateMul, ffreqRateCtr);
				pan = LFDNoise1.kr(ffreqRate, panWidth, panCtr).clip(-1.0, 1.0);
				sig = Formlet.ar(sig, ffreq, atk, dcy);
				sig = Limiter.ar(sig) * EnvGen.kr(Env.adsr(0.2, 0.6, 0.7, 0.4), gate, doneAction: 2);
				sig = PanAz.ar(8, sig, pan, ampint, 2);
			});

			signal = signal * envgate * envpause;
			Out.ar(out, signal*amp.dbamp.lag(1));
			// Out.ar(outbus, signal*amp.dbamp.lag(1))

		}).load;
	}
/*TAnalog_blipfml.def*/

	*metadata
	{
		^(metadata: (
			synthdefname: "TAnalog_blipfml",
			type: \gen,
			main: \ffreqloMul,
			sliders: [
				\topfreq -> ControlSpec(20, 18000, \exp, 1, 18000, \topfreq),
				\ffreqloMul -> ControlSpec(0.9, 25, \exp, 0.1, 2.5, \ffreqloMul),
				\ffreqhiMul -> ControlSpec(0.9, 25, \exp, 0.1, 16.3, \ffreqhiMul),
				\atk -> ControlSpec(0.01, 2.0, \exp, 0.001, 0.02, \atk),
				\dcy -> ControlSpec(0.01, 2.0, \exp, 0.001, 0.026, \dcy),
				\ffreqRateCtr -> ControlSpec(0.1, 10, \exp, 0.01, 1, \ffreqRateCtr),
				\ffreqRateMul -> ControlSpec(0, 1, \lin, 0.01, 0.5, \ffreqRateMul),
				\modRate -> ControlSpec(0.1, 10, \exp, 0.1, 1, \modRate),
				\panCtr -> ControlSpec(-1, 1, \lin, 0.1, 0, \panCtr),
				\panWidth -> ControlSpec(0, 1, \lin, 0.01, 1 , \panWidth),
				\detune -> ControlSpec(-2, 2, \lin, 0.001, 1.006, \detune),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \amp)
			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)]
			]
		))

	}

}

