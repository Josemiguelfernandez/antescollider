VOsc_wt_20_mod_mono : Tmodule2 {

	classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\VOsc_wt_20_mod_mono, {|out = -1, outbus = -2, transp = 1.0, amp = 0, d = 0, shift = 0, distor = 0, lfnoise_amp = 0, lag = 1, ampmod = 0, gate = 1, free = 1, matrix_ramp = 0.01, width = 2, orientation = 0, lag_spat = 0.2, buf, numBufs=2, detune=0.2, detu_freq = 0.1, lfo_buf_freq = 0.5|

			var freqs, amps, pos, sig, fqs, conca, len, lista, distarray, offset, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//offset = Line.kr(0, -0.02, 60);
			distarray = Array.series(20, 1);
			freqs = \freqs.kr(Array.fill(20, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.fill(20, { 0.02 + 0.07.rand }));
			pos = \pos.kr((Array.series(20, 0)*(2/20)));

			// detuneCtrl = LFNoise1.kr(0.1).bipolar(detune).midiratio;

			/*freqs.poll;
			amps.poll;*/
			sig = Mix.fill(20*3, { |i|
				var ampc;
				ampc = AmpCompA.kr(freqs.wrapAt(i));

				VOsc.ar(buf + LFNoise1.kr(lfo_buf_freq).range(0, numBufs-1), Lag.kr((freqs.wrapAt(i)*transp) + (Rand(-2.0, 2.0) * d  + shift) * (distarray.wrapAt(i) ** distor), (0.5 + 2.0.rand)*lag) * LFNoise1.kr(detu_freq).bipolar(detune).midiratio, {Rand(0,2pi)}, Lag.kr(amps.wrapAt(i) , ((0.5 + 2.0.rand)*lag)) * SinOsc.ar(ampmod, 0, 0.5, 1) * max(0, LFDNoise3.kr(lfnoise_amp,1.0)));

/*				SinOsc.ar(Lag.kr((freqs.wrapAt(i)*transp) + (Rand(-2.0, 2.0) * d  + shift) * (distarray.wrapAt(i) ** distor), (0.5 + 2.0.rand)*lag) , 0, Lag.kr(amps.wrapAt(i) , ((0.5 + 2.0.rand)*lag)) * SinOsc.ar(ampmod, 0, 0.5, 1) * max(0, LFDNoise3.kr(lfnoise_amp,1.0))) * ampc;*/
		 	});

			//* max(0, LFDNoise3.kr(lfnoise_amp,1.0)) lo saqué

			fqs = (freqs*transp+shift) * (distarray ** distor);
			conca = [fqs, amps].flop;
			len = conca.size*2;
			lista = conca.reshape(len);
			SendReply.kr(Impulse.kr(30), '/mi-list', Lag.kr(lista, lag));

			sig = 0.25 * sig * envgate * envpause;


			Out.ar(out, sig*amp.dbamp.lag(1));
			// Out.ar(outbus, sig*amp.dbamp.lag(1))

		}).load;
	}

	// VOsc_wt_20_mod_mono.def
	*metadata
	{
	 ^(metadata: (
			synthdefname: "VOsc_wt_20_mod_mono",
			type: \gen,
			main: \transp,
			sliders: [
				\transp -> ControlSpec(0.001, 5, \lin, 0.001, 1, \transp),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\d -> ControlSpec(0, 200, \lin, 0.1, 0, \desv),
				\buf -> ControlSpec(0,  100, \lin, 1, 0, \buf),
				\lfo_buf_freq -> ControlSpec(0, 100, \lin, 0.001, 0, \lfo_buf_freq),
				\numBufs  -> ControlSpec(0,  100, \lin, 1, 0, \numBufs),
				\shift -> ControlSpec(-1200, 1200, \lin, 0, 0, \shift),
				\distor -> ControlSpec(-2, 2, \lin, 0.001, 0, \distor),
				\detune -> ControlSpec(0, 100, \lin, 0.001, 0, \detune),
				\detu_freq -> ControlSpec(0, 100, \lin, 0.001, 0, \detu_freq),
				\lfnoise_amp -> ControlSpec(0, 20, \lin, 0.001, 0, \lfamp),
				\ampmod -> ControlSpec(0, 20, \lin, 0.001, 0.001, \ampmod),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lag),
				\width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\lag_spat -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag_spat)
			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
				// \pos -> [ControlSpec(0.0001, 2, \lin, 0, 0.3, \vol), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\osc -> [\osc, n],
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				// \pos -> [\multisliders, \pos, { rrand(0.0, 2.0) }, n],
				// \pos_rt -> [\multisliders, \pos, Array.series(n, 0)*(2/n)],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)]
			]
		))

	}

}

/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/