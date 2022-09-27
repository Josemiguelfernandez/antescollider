DWGPluck : Tmodule2 {

	// classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\DWGPluck, {|out = -1, outbus = -2, amp = 0, t_trig = 0, freq = 440, release = 0.1, i_doneAction = 0, amp2 = 0.1, c3 = 20, pos = 0.14, c1 = 1, releaseTime = 1, gate = 1, free = 1, matrix_ramp = 0.005, width = 2, orientation = 0, lag_spat = 0.2|

			var sig, input, first, freqscale, mallet, sing, envgate, envpause, adsr, env, inp;

			// adsr = EnvGen.kr(Env.adsr(0.001, envdur, 1, decay, 1, -4), t_trig);

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			env = Env.new([0,1, 1, 0],[0.001,0.006, 0.0005],[5,-5, -8]);

			inp = amp2 * LFClipNoise.ar(2000) * EnvGen.ar(env, t_trig);
			sig = DWGPlucked.ar(freq, amp2, gate, pos, c1, c3, inp, release);

			sig = sig * envgate * envpause;

			// DetectSilence.ar(sig, doneAction: i_doneAction);


			Out.ar(out, sig*amp.dbamp.lag(1));
			// Out.ar(outbus, sig*amp.dbamp.lag(1))

		}).load;
	}



	// TAddic_20_8.def
	*metadata
	{
		^(metadata: (
			synthdefname: "DWGPluck",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 10000, \exp, 0.1, 300, \freq),
				\pos -> ControlSpec(0.001, 1, \lin, 0.001, 0.14, \pos),
				\c1 -> ControlSpec(0.001, 20, \lin, 0.001, 1, \c1),
				\c3 -> ControlSpec(0.1, 100, \lin, 0.1, 20, \c3),
				\release -> ControlSpec(0.001, 10, \lin, 0.1, 0.1, \release),
				\amp2 -> ControlSpec(0.001, 1, \lin, 0.1, 0.1, \amp2),
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

	//Define pluck synthdef
/*(
SynthDef(\help_dwgplucked, { |out=0, freq=440, amp=0.5, gate=1, c3=20, pan=0|
    var env = Env.new([0,1, 1, 0],[0.001,0.006, 0.0005],[5,-5, -8]);
    var inp = amp * LFClipNoise.ar(2000) * EnvGen.ar(env,gate);
    var son = DWGPlucked.ar(freq, amp, gate,0.1,1,c3,inp);
	//     DetectSilence.ar(son, 0.001, doneAction:2);
    Out.ar(out, Pan2.ar(son * 0.1, pan));
}).add;
)*/
