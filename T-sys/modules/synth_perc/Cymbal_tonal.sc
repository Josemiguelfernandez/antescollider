
Cymbal_tonal : Tmodule2 {

	// classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\Cymbal_tonal, {|out = -1, outbus = -2, amp = 0, t_trig = 0, sing_switch = 0, freq = 1327.36, amp_intern = 0.5, decayscale = 1, freq_fac = 1, lag = 1, envdur = 5, decay = 6, i_doneAction = 0, gate = 1, free = 1, matrix_ramp = 0.005, width = 2, orientation = 0, lag_spat = 0.2|

			var sig, input, first, freqscale, mallet, sing, envgate, envpause, adsr, freqs, freqsDetune, ringTimes, amps, lFreqs, rFreqs, z;

			adsr = EnvGen.kr(Env.adsr(0.001, envdur, 1, decay, 1, -4), t_trig);

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);


			z =[ `([ [ 1327.36, 1171.43, 1141.97, 1429.71 ], nil, [ 1.21146, 1.9158, 1.42798, 1.9487 ] ]),
				`([ [ 1569.68, 1565.56, 1219.64, 1310.97 ], nil, [ 1.75512, 1.30389, 1.87559, 1.80242 ] ]) ];

/*			lFreqs = 	freqs;
			rFreqs = 	lFreqs + freqsDetune;*/
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

			sig = DynKlank.ar(						// extended to two channels:
				[ 	z /*,
					// left: original resonator
					`[ rFreqs, amps, ringTimes ]*/
			], input *0.5, freqscale, 0, decayscale); // , * freqscale.reciprocal.pow(0.5)
			//				Decay.ar(Impulsar.ar(trig), decay),

			// DetectSilence.ar(sig, doneAction: i_doneAction);


			// Env.adsr(0.001, 5, 1, 3, 1, -4).test(2).plot;



			sig = 0.25 * sig * envgate * envpause * adsr;

			DetectSilence.ar(sig, doneAction: i_doneAction);


			Out.ar(out, sig*amp.dbamp.lag(1));
			// Out.ar(outbus, sig*amp.dbamp.lag(1))

		}).load;
	}

	// TAddic_20_8.def
	*metadata
	{
		^(metadata: (
			synthdefname: "Cymbal_tonal",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 10000, \exp, 0.1, 180.629, \freq),
				\sing_switch -> ControlSpec(0, 4, \lin, 0.01, 0, \sing_switch),
				\decayscale -> ControlSpec(0.01, 10, \lin, 0.01, 1, \decayscale),
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

		))

	}

}









