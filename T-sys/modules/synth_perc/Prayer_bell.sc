Prayer_bell : Tmodule2 {

	// classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\Prayer_bell, {|out = -1, outbus = -2, amp = 0, t_trig = 0, sing_switch = 0, freq = 2434, amp_intern = 0.5, decayscale = 1, freq_fac = 1, lag = 1, i_doneAction = 0, gate = 1, free = 1, matrix_ramp = 0.005, width = 2, orientation = 0, lag_spat = 0.2|

			var sig, input, first, freqscale, mallet, sing, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);


			freqscale = freq / 2434;
			freqscale = Lag3.kr(freqscale, lag);
			decayscale = Lag3.kr(decayscale, lag);

			mallet = LPF.ar(Trig.ar(t_trig, SampleDur.ir), 10000 * freqscale);
			sing = LPF.ar(
				LPF.ar(
					{
						PinkNoise.ar * Integrator.kr(sing_switch * 0.001, 0.999).linexp(0, 1, 0.01, 1) * amp_intern
					},
					2434 * freqscale
				) + Dust.ar(0.1), 10000 * freqscale
			) * LFNoise1.kr(0.5).range(-45, -30).dbamp;
			input = mallet + (sing_switch.clip(0, 1) * sing);

			sig = DynKlank.ar(`[
				[
					(first = LFNoise1.kr(0.5).range(2424, 2444)) + Line.kr(20, 0, 0.5),
					first + LFNoise1.kr(0.5).range(1,3),
					LFNoise1.kr(1.5).range(5435, 5440) - Line.kr(35, 0, 1),
					LFNoise1.kr(1.5).range(5480, 5485) - Line.kr(10, 0, 0.5),
					LFNoise1.kr(2).range(8435, 8445) + Line.kr(15, 0, 0.05),
					LFNoise1.kr(2).range(8665, 8670),
					LFNoise1.kr(2).range(8704, 8709),
					LFNoise1.kr(2).range(8807, 8817),
					LFNoise1.kr(2).range(9570, 9607),
					LFNoise1.kr(2).range(10567, 10572) - Line.kr(20, 0, 0.05),
					LFNoise1.kr(2).range(10627, 10636) + Line.kr(35, 0, 0.05),
					LFNoise1.kr(2).range(14689, 14697) - Line.kr(10, 0, 0.05)
				] * freq_fac,
				[
					LFNoise1.kr(1).range(-10, -5).dbamp,
					LFNoise1.kr(1).range(-20, -10).dbamp,
					LFNoise1.kr(1).range(-12, -6).dbamp,
					LFNoise1.kr(1).range(-12, -6).dbamp,
					-20.dbamp,
					-20.dbamp,
					-20.dbamp,
					-25.dbamp,
					-10.dbamp,
					-20.dbamp,
					-20.dbamp,
					-25.dbamp
				],
				[
					20 * freqscale.pow(0.2),
					20 * freqscale.pow(0.2),
					5,
					5,
					0.6,
					0.5,
					0.3,
					0.25,
					0.4,
					0.5,
					0.4,
					0.6
				] * freqscale.reciprocal.pow(0.5)
			], input, freqscale, 0, decayscale);
			DetectSilence.ar(sig, doneAction: i_doneAction);

			sig = 0.25 * sig * envgate * envpause;


			Out.ar(out, sig*amp.dbamp.lag(1));
			// Out.ar(outbus, sig*amp.dbamp.lag(1))

		}).load;
	}

	// TAddic_20_8.def
	*metadata
	{
		^(metadata: (
			synthdefname: "Prayer_bell",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 10000, \exp, 0.1, 2434, \freq),
				\sing_switch -> ControlSpec(0, 4, \lin, 0.01, 0, \sing_switch),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lag),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),

/*				\shift -> ControlSpec(-1200, 1200, \lin, 0, 0, \shift),
				\distor -> ControlSpec(-2, 2, \lin, 0.001, 0, \distor),
				\lfnoise_amp -> ControlSpec(0, 20, \lin, 0.001, 0.001, \lfamp),
				\width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\lag_spat -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag_spat)*/
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


