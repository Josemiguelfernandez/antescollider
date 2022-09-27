Perc_klank_20 : Tmodule2 {

	classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\perc_klank_20, {|out = -1, transp = 1.0, shift = 0, distor = 0, amp = 0, d = 5.5, releaseTime = 0.15, lag = 0.2, gate = 1, free = 1, t_trig = 0, amp_fac = 1, matrix_ramp = 0.01, numharm1 = 2, numharm2 = 3, blipmul =  1.5, fold2 = 0.2, dykla_mul = 12, deltime = 0.003, decaytime = 0.5, combmul = 50, delt_1 = 0.005, delt_2 = 0.005, dectApass = 3|
			var freqs, amps, rings, envs, sig, distarray;
			var init = 0.005, init2 = 0.05, peaklevel = 0.2, curva1 = -4, curva2 = -6, curva3 = 3, imp;

			distarray = Array.series(n, 1);
			freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.fill(n, { 0.2 + 0.7.rand }));
			rings = \rings.kr(Array.fill(n, { 0.05 + 0.2.rand }));
			// pos = \pos.kr((Array.series(20, 0)*(2/20)));

			sig = Blip.ar(t_trig.squared, [numharm1,numharm2], blipmul);

			sig = DynKlank.ar(`[freqs,amps*amp_fac,rings], sig).fold2(fold2).cubed * dykla_mul;

			sig = Mix.fill(3, { CombL.ar(sig, 0.2, deltime, decaytime, combmul) });

			sig = sig.distort * 0.5;
			sig = Mix.fill(6, {AllpassN.ar(sig, 0.2, [delt_1, delt_2], dectApass) }); //delay decaytime

			sig = LeakDC.ar(sig);

			Out.ar(sig, sig*amp.dbamp.lag);
		}).load;
	}

	// Perc_klank_20.def

	*metadata
	{
		^(metadata: (
			synthdefname: "perc_klank_20",
			type: \gen,
			main: \numharm1,
			sliders: [
				\numharm1 -> ControlSpec(1, 20, \lin, 1, 2, \numharm1),
				\numharm2 -> ControlSpec(1, 20, \lin, 1, 3, \numharm2),
				\blipmul -> ControlSpec(0.001, 5, \lin, 0.001, 1.5, \blipmul),
				\fold2 -> ControlSpec(0.01, 1, \lin, 0.01, 0.2, \fold2),
				\dykla_mul -> ControlSpec(0.1, 20, \lin, 0.1, 12, \dykla_mul),
				\deltime -> ControlSpec(0.0001, 0.2, \lin, 0.0001, 0.003, \deltime),
				\decaytime -> ControlSpec(0.01, 10, \lin, 0.01, 0.5, \decaytime),
				\combmul -> ControlSpec(0.1, 100, \lin, 0.1, 50, \combmul),
				\delt_1 -> ControlSpec(0.0001, 0.2, \lin, 0.0001, 0.003, \delt_1),
				\delt_2 -> ControlSpec(0.0001, 0.2, \lin, 0.0001, 0.005, \delt_2),
				\dectApass -> ControlSpec(0.01, 20, \lin, 0.01, 3, \dectApass),
	/*			\distor -> ControlSpec(-2, 2, \lin, 0.001, 0, \distor),
				\releaseTime -> ControlSpec(0, 10, \lin, 0.01, 0.15, \releaseTime),
				// \lag -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lag)*/
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
				\rings -> [ControlSpec(0, 0.25, \lin, 0.001, 0.1, \rings), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				// \osc -> [\osc, n],
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)],
				\rings -> [\multisliders, \rings,{ rrand(0.0, 0.25) }, n],
				\trig -> [\t_trig]
			]
		))

	}

}

/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/