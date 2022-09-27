Multi5BandPass : Tmodule2 {

	classvar n = 5; //numero de componentes
	*def
	{
		^SynthDef(\Multi5BandPass, {|out, in, amp = 0, gate = 1, free = 1, matrix_ramp = 0.01, xFade = 1|

			var freqs, amps, q, sig, envgate, envpause, cross;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

			freqs = \freqs.kr([ 400, 750, 2400, 2600, 2900 ]);
			amps = \amps.kr([ 1, 0.28183829312645, 0.089125093813375, 0.1, 0.01 ]);
			q = \q.kr([ 0.1, 0.10666666666667, 0.041666666666667,
				0.046153846153846, 0.041379310344828 ]);

			sig = In.ar(in, 1);

			sig = Mix.new(BBandPass.ar(sig, freqs, q) * amps);

			sig = sig * envgate * envpause;

			// Out.ar(out, sig*amp.dbamp.lag(1));
			XOut.ar(out, cross, sig*amp.dbamp.lag(1)); //salida directa a un bus

		}).load;
	}

	// TAddic_20_8.def
	*metadata
	{
		^(metadata: (
			synthdefname: "Multi5BandPass",
			type: \fx,
			main: \amp,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
				\q -> [ControlSpec(0.0001, 2, \lin, 0, 0.3, \pos), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\osc -> [\osc, n],
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 1.0) }, n],
				\q -> [\multisliders, \pos, { rrand(0.0, 12.0) }, n],
			]
		))

	}

}

/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/