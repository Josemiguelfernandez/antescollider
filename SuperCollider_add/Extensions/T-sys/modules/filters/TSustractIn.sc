TSustractIn : Tmodule2 {

	classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{

		^SynthDef(\sustractin, {|in = 0, inbus, ingain = 0, out = -1, outbus = -2, outgain = -120, transp = 1.0, amp = -12, d = 0, shift = 0, distor = 0, lfnoise_amp = 0.001, lfnoise_ring = 0.001, lag = 1, gate = 1, free = 1, matrix_ramp = 0.01, width = 2, orientation = 0, lag_spat = 0.2, xFade = 1|
			var freqs, amps, rings, sig, pos, conca, len, lista, distarray, envgate, envpause, cross, input;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			distarray = Array.series(n, 1);
			freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.fill(n, { 0.2 + 0.7.rand }));
			rings = \rings.kr(Array.fill(n, { 0.05 + 0.2.rand }));
			pos = \pos.kr((Array.series(20, 0)*(2/20)));

			cross = xFade * envgate * envpause;

			input = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			sig = Mix.fill(n*3, { |i|

				var ampc;
				ampc = AmpCompA.kr(freqs.wrapAt(i));

				PanAz.ar(8, Ringz.ar(Ringz.ar(input*0.001,((((Lag.kr(freqs.wrapAt(i)*transp*transp, lag)) + (Rand(-2.0, 2.0) * d))  + shift) * (distarray.wrapAt(i) ** distor)), Lag.kr(rings.wrapAt(i), lag), Lag.kr(amps.wrapAt(i), lag)),((((Lag.kr(freqs.wrapAt(i)*transp*transp, lag)) + (Rand(-2.0, 2.0) * d))  + shift) * (distarray.wrapAt(i) ** distor)), Lag.kr(rings.wrapAt(i), lag), Lag.kr(amps.wrapAt(i), lag)), Lag.kr(pos.wrapAt(i), lag_spat), 1, width, orientation) * ampc;
			});

			sig = sig * envgate * envpause* 0.25;


			XOut.ar(out, cross, sig*amp.dbamp.lag); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}


	// TSustractIn.def

	*metadata
	{
		^(metadata: (
			synthdefname: "sustractin",
			type: \fx,
			main: \transp,
			sliders: [
				\transp -> ControlSpec(0.001, 5, \lin, 0.001, 1, \transp),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -12, \dB),
				\d -> ControlSpec(0, 200, \lin, 0.1, 0, \desv),
				\shift -> ControlSpec(-1200, 1200, \lin, 0, 0, \shift),
				\distor -> ControlSpec(-2, 2, \lin, 0.001, 0, \distor),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lag),
				\width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\lag_spat -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag_spat)
			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
				\rings -> [ControlSpec(0, 0.25, \lin, 0.001, 0.1, \rings), n],
				\pos -> [ControlSpec(0.0001, 2, \lin, 0, 0.3, \vol), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\rings -> [\multisliders, \rings,{ rrand(0.0, 0.25) }, n],
				\pos -> [\multisliders, \pos, { rrand(0.0, 2.0) }, n],
				\pos_rt -> [\multisliders, \pos, Array.series(n, 0)*(2/n)],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)]
			]
		))

	}

}

/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/