Prayer_bell_Iana : Tmodule2 {

	// classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\Prayer_bell_Iana, {|out = 0, outbus = -2, amp = 0, t_trig = 0, sing_switch = 1, freqscale = 1, amp_intern = 0.5, decayscale = 1, lag = 1, i_doneAction = 0, gate = 1, free = 1, matrix_ramp = 0.005, width = 2, orientation = 0, lag_spat = 0.2|

			var sig, input, first, mallet, sing, envgate, envpause, freqs, amps, rings, pos, freq1;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			freqs = \freqs.kr(Array.fill(20, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.fill(20, { 0.02 + 0.07.rand }));
			rings = \rings.kr(Array.fill(20, { 0.05 + 0.2.rand }));
			pos = \pos.kr((Array.series(20, 0)*(2/20)));

			freq1 = freqs[0];
/*			freqscale = freq / 2434;*/
			freqscale = Lag3.kr(freqscale, lag);
			// freqscale = 1;
			decayscale = Lag3.kr(decayscale, lag);


			mallet = LPF.ar(Trig.ar(t_trig, SampleDur.ir), 10000 * freqscale);
			sing = LPF.ar(
				LPF.ar(
					{
						PinkNoise.ar * Integrator.kr(sing_switch * 0.001, 0.999).linexp(0, 1, 0.01, 1) * amp_intern
					},
					freq1 * freqscale
				) + Dust.ar(0.1), 10000 * freqscale
			) * LFNoise1.kr(0.5).range(-45, -30).dbamp;
			input = mallet +  (sing_switch.clip(0, 1) * sing); //+ mallet +

			sig = DynKlank.ar(`[freqs, amps, rings], input, freqscale, 0, decayscale);
			// sig = DynKlank.ar(`[freqs, nil, rings * freqscale.reciprocal.pow(0.5)], Impulse.ar(2, 0, 0.1));
			// DetectSilence.ar(sig, doneAction: i_doneAction);

			sig =  sig * envgate * envpause; //0.25 *


			Out.ar(out, sig*amp.dbamp.lag(1));
			// Out.ar(outbus, sig*amp.dbamp.lag(1))

		}).load;
	}

	// TAddic_20_8.def
	*metadata
	{
		^(metadata: (
			synthdefname: "Prayer_bell_Iana",
			type: \gen,
			main: \freqscale,
			sliders: [
				\freqscale -> ControlSpec(0.001, 2, \lin, 0.001, 1, \freqscale),
				\sing_switch -> ControlSpec(0, 4, \lin, 0.01, 0, \sing_switch),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lag),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
			],
			buttons: [
				\trig -> [\t_trig]
			]
			/*			multisliders: [
			\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
			\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
			\pos -> [ControlSpec(0.0001, 2, \lin, 0, 0.3, \vol), n]
			],*/
			/*			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
			\osc -> [\osc, n],
			\freqs -> [\multisliders, \freqs, {
			var freq_min = 40, freq_max = 75; //en midi quart de ton
			[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
			\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
			\pos -> [\multisliders, \pos, { rrand(0.0, 2.0) }, n],
			\pos_rt -> [\multisliders, \pos, Array.series(n, 0)*(2/n)],
			\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)]
			]*/
		))

	}

}


