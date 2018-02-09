Perc_add_30_res : Tmodule2 {

	classvar n = 30; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\Perc_add_30_res, {|out = -1, transp = 1.0, shift = 0, distor = 0, amp = 0, d = 5.5, releaseTime = 0.15, lag = 0.2, pos = 0, gate = 1, free = 1, matrix_ramp = 0.01, releaseFactor = 1, noise_att = 0.001, noise_release = 0.1, noise_amp = -6, t_trig = 0|
			var freqs, amps, res, envs, signal, distarray;
			var init = 0.005, init2 = 0.05, peaklevel = 0.2, curva1 = -4, curva2 = -6, curva3 = 3, imp, noisenv;

			// imp = Impulse.kr(10); //update meter 10 por seg
			freqs =  \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.with(Array.fill(n, { 0.02 + 0.07.rand })));
			res = \reson.kr(Array.with(Array.fill(n, { 0.02 + 0.07.rand })));
			distarray = Array.series(n, 1);

			noisenv = EnvGen.kr(Env.perc(noise_att, noise_release, noise_amp.dbamp.lag), t_trig);
			noisenv = PinkNoise.ar(10)*noisenv;
			//n = freqs.size;
			// envs = Array.fill(n, { Env.perc(0.002 + 0.005.rand, init2 + releaseTime.rand, peaklevel, [curva1, curva2, curva3].choose)});
			signal = Mix.fill(n*6,  { |i|
				var ampc;
				ampc = AmpCompA.kr(freqs.wrapAt(i),);

				SinOsc.ar(Lag.kr((freqs.wrapAt(i)*transp) + (Rand(-2.0, 2.0) * d  + shift) * (distarray.wrapAt(i) ** distor), (0.5 + 2.0.rand)*lag), 0, Lag.kr(amps.wrapAt(i)) * EnvGen.kr(Env.perc(0.002 + 0.005.rand, init2 + res.wrapAt(i)*releaseFactor, peaklevel, [curva1, curva2, curva3].choose), t_trig)).softclip * ampc;
			});
			// SendReply.kr(imp, '/levelis/superPercAddMulti3', [RunningSum.ar(signal.squared, 4410), Peak.ar(signal, Delay1.ar(imp)).lag(0, 3)].flop.flat);
			signal = signal+noisenv;
			// DetectSilence.ar(signal, doneAction:2);
			Out.ar(out, signal*amp.dbamp.lag);
		}).load;
	}

	// Perc_add_30_res.def



	*metadata
	{
		^(metadata: (
			synthdefname: "Perc_add_30_res",
			type: \gen,
			main: \transp,
			sliders: [
				\transp -> ControlSpec(0.001, 5, \lin, 0.001, 1, \transp),
				\d -> ControlSpec(0, 200, \lin, 0.1, 0, \desv),
				\shift -> ControlSpec(-1200, 1200, \lin, 0, 0, \shift),
				\distor -> ControlSpec(-2, 2, \lin, 0.001, 0, \distor),
				\releaseFactor -> ControlSpec(0, 10, \lin, 0.01, 1, \releaseFactor),
				\noise_att -> ControlSpec(0, 2, \lin, 0.001, 0.001, \noise_att),
				\noise_release -> ControlSpec(0, 2, \lin, 0.001, 0.1, \noise_rel),
				\noise_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \dB),
				\lag -> ControlSpec(0.001, 1, \lin, 0.001, 1, \lag),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
				\reson -> [ControlSpec(0, 10, \lin, 0.01, 0.02), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				// \osc -> [\osc, n],
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\reson -> [\multisliders, \reson,{ rrand(0.0, 10.0) }, n],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)],
				\trig -> [\t_trig]
			]
		))

	}

}

/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/