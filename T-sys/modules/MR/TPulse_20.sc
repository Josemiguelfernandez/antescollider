TPulse_20 : Tmodule2 {

	classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\tPulse_20, {|out = -1, outbus = -2, transp = 1.0, amp = 0, d = 0, shift = 0, distor = 0, lfnoise_amp = 0, lag = 1, ampmod = 0, amplag = 1, gate = 1, free = 1, freq_filt = 400, matrix_ramp = 0.01, width = 2, orientation = 0, lag_spat = 0.2|

			var freqs, amps, widthp, fq_filt, sig, pos, conca, len, lista, distarray, offset, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//offset = Line.kr(0, -0.02, 60);
			distarray = Array.series(n, 1);
			freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.fill(n, { 0.02 + 0.07.rand }));
			pos = \pos.kr((Array.series(n, 0)*(2/n)));
			fq_filt = \fq_filt.kr(Array.fill(n, 400));
			widthp = \widthp.kr(Array.fill(n, 0.5));

			// sig = Pulse.ar(freq + [0,1], width, 0.1);

			sig = Mix.fill(n, { |i|
					HPF.ar(PanAz.ar(8,Pulse.ar(Lag.kr((freqs.wrapAt(i)*transp) + (Rand(-2.0, 2.0) * d  + shift) * (distarray.wrapAt(i) ** distor), (0.5 + 2.0.rand)*lag), widthp.wrapAt(i), Lag.kr(amps.wrapAt(i), lag) * SinOsc.ar(ampmod, 0, 0.5, 1)), Lag.kr(pos.wrapAt(i), lag), 1, width, orientation), Lag.kr(fq_filt.wrapAt(i), lag)) * 0.7;
						});

			sig = 0.25 * sig * envgate * envpause;


			Out.ar(out, sig*amp.dbamp.lag(1));
			// Out.ar(outbus, sig*amp.dbamp.lag(1))

		}).load;
	}
	// TPulse_20.def
	*metadata
	{
	 ^(metadata: (
			synthdefname: "tPulse_20",
			type: \gen,
			main: \transp,
			sliders: [
				\transp -> ControlSpec(0.001, 5, \lin, 0.001, 1, \transp),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\d -> ControlSpec(0, 200, \lin, 0.1, 0, \desv),
				\shift -> ControlSpec(-1200, 1200, \lin, 0, 0, \shift),
				\distor -> ControlSpec(-2, 0.3, \lin, 0.001, 0, \distor),
				\ampmod -> ControlSpec(0, 50, \lin, 0.001, 0, \ampmod),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lag),
				\width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\lag_spat -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag_spat)
			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
				\pos -> [ControlSpec(0.0001, 2, \lin, 0, 0.3, \vol), n],
				\widthp -> [ControlSpec(0, 1, \lin, 0.001, 0.5, \widthp), n],
				\fq_filt -> [ControlSpec(20, 10000, \exp, 0.01, 400, \fq_filt), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\pos -> [\multisliders, \pos, { rrand(0.0, 2.0) }, n],
				\pos_rt -> [\multisliders, \pos, Array.series(n, 0)*(2/n)],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)],
				\widthp -> [\multisliders, \widthp,{ rrand(0.0, 1.0) }, n],
			]
		))

	}

}

/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/
// TAddic_15_8.def