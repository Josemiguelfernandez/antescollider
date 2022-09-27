Tube_add_26 : Tmodule2 {

	classvar n = 26; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\Tube_add_26, {|out = -1, transp = 1.0, amp = 0, d = 10, shift = 0, distor = 0, lag = 1, inclino, inclinolag = 0.5, gate = 1, free = 1, matrix_ramp = 0.01|
			var freqs, amps, ringtimes, sig, pos, conca, len, lista, envgate, envpause, distarray, f;
			f =  [80, 800, \exp].asSpec.map(inclino.lag(inclinolag));
			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			distarray = Array.series(n, 1);
			freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.with(Array.fill(n, { 0.02 + 0.07.rand })));
			sig = Mix.fill(n*3, { |i|
				CombN.ar(SinOsc.ar(((((Lag.kr(freqs.wrapAt(i), (0.5 + 2.0.rand)*lag)*transp) + (Rand(-2.0, 2.0) * d))  + shift) * (distarray.wrapAt(i) ** distor)) , 0, Lag.kr(amps.wrapAt(i), ((0.5 + 2.0.rand)*lag))),1/f,1/f,0.5)
			});
			sig = NTube.ar(sig,`[0.97,1.0,1.0,1.0,1.0,0.97], `[0.5,0.5,0.2,-0.4],`([0.01,0.02,0.01,0.005,0.05]*inclino.lag(0.2))*0.1).dup;

			sig = sig * envgate * envpause * 0.25;


			Out.ar(out, sig*amp.dbamp.lag(1));
			// Out.ar(outbus, sig*amp.dbamp.lag(1))

		}).load;
	}

	// Tube_add_26.def
	*metadata
	{
		^(metadata: (
			synthdefname: "Tube_add_26",
			type: \gen,
			main: \transp,
			sliders: [
				\transp -> ControlSpec(0.001, 5, \lin, 0.001, 1, \transp),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\d -> ControlSpec(0, 200, \lin, 0.1, 0, \desv),
				\shift -> ControlSpec(-1200, 1200, \lin, 0, 0, \shift),
				\distor -> ControlSpec(-2, 2, \lin, 0.001, 0, \distor),
				\inclino -> ControlSpec(0, 1, \lin, 0.01, 0, \inclino),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lag)
			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\osc -> [\osc, n],
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)]
			]
		))

	}

}

/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/