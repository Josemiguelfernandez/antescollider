Vowel_dyn_o_alto : Tmodule2 {

	// classvar thresh = 0.7, result, vow, freqs; //numero de componentes

	*def
	{
		^SynthDef(\Vowel_dyn_o_alto,  {|out = -1, amp = 0, gate = 1, free = 1, matrix_ramp = 0.01, imp_freq = 1, freqscale = 1, freqoffset = 0, decayscale = 1|

			var sig, freqs, thresh = 0.7, result, vow, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			// calcule freqs+amp en fonction de vowel
			vow = Vowel(\o, \alto); //Vowel(\e, \bass); //Vowel(\a, \tenor) ;
			freqs = (40..10000).collect { |x| x + 0.5.rand2 };
			freqs.do{ |freq|
				var amp = vow.ampAt(freq);
				if(amp > thresh) {
					result = result.add([freq, amp])
				}
			};
			result = result.flop ++ 0.2;

			sig =  DynKlank.ar(`result, Impulse.ar(imp_freq), freqscale, freqoffset, decayscale) * 0.001;
			sig = sig * envgate * envpause;

			Out.ar(out, sig*amp.dbamp.lag);
		}).load;
	}
/*	a = Synth(\Vowel_dyn_e_bass) //, [\imp_freq, MouseX.kr(1, 1000, 1)])
	a.set(\freqscale, 0.2)
	a.set(\imp_freq, 10)
	a.set(\freqoffset, 200)
	a.set(\decayscale, 2)
	a.free*/
	// AnalogVowel_mono.def

	*metadata
	{
		^(metadata: (
			synthdefname: "Vowel_dyn_o_alto",
			type: \gen,
			main: \imp_freq,
			sliders: [
				\imp_freq-> ControlSpec(0.1, 10, \exp, 0.1, 1, \imp_freq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
/*			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				// \osc -> [\osc, n],
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)],
				\trig -> [\t_trig]
			]*/
		))

	}

}

/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/