FM_band_mes91 : Tmodule2 {

	classvar n = 10; //numero de componentes
	classvar bandas = 5;

	*def
	{
		^SynthDef(\fm_bandas_mes91, {|out = -1, outbus = -2,transp = 1.0, d = 0, amp = 0, shift = 0, distor = 0, lag = 1, lfnoise_amp = 0.001, index1 = 0.1, index2 = 0.1, index3 = 0.1, index4 = 0.1, index5 = 0.1, gate = 1, free = 1, matrix_ramp = 0.01, width = 2, orientation = 0, lag_spat = 0.2|

			var freqs, amps, mod, pos, amps2, sig, fqs, conca, len, lista, distarray, offset, envgate, envpause, freqs_clump, elementos, desv, index, spat;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//offset = Line.kr(0, -0.02, 60);
			elementos = n/bandas;
			mod = Array.geom(n, 0.123, 1.13).stutter(4);
			distarray = Array.series(n, 1);
			/*			freqs = \freqs.kr( [49.907581, 99.2, 156.117996, 262.744202, 340.753235, 419.81662, 522.859253, 602.681885, 681.22052, 759.881714, 838.429199, 916.473938, 943.13501, 1021.06, 1099.651733, 1177.955444, 1282.507446, 1360.8, 1439.156738, 1517.888916, 1595.893433, 1621.914429, 1672.90918, 1699.648926, 1778.450562, 1856.845703, 1935.331299, 2039.495117, 2117.734863, 2196.139648]);*/
			freqs = [110.0, 116.540947, 184.997208, 207.652344, 239.911697, 311.126984, 523.251099, 466.163788, 493.883301, 538.361816];
			amps = [0.021014, 0.036714, 0.032305, 1.738727, 0.199656, 0.02438, 0.012296, 0.004999, 0.007963, 0.168083];

			index = [[0, 20, \lin].asSpec.map(index1), [0, 20, \lin].asSpec.map(index2), [0, 20, \lin].asSpec.map(index3), [0, 20, \lin].asSpec.map(index4), [0, 20, \lin].asSpec.map(index5)];

			spat = [[0.001, 2.0, \lin].asSpec.map(index1), [0.001, 2.0, \lin].asSpec.map(index2), [0.001, 2.0, \lin].asSpec.map(index3), [0.001, 2.0, \lin].asSpec.map(index4), [0.001, 2.0, \lin].asSpec.map(index5)];


			freqs_clump = freqs.clump(elementos);
			// freqs_clump.postln;

			sig = freqs_clump.collect({|frequ, a|
				var fsize= frequ.size;
				Mix.fill(fsize*3, {|i|

					PanAz.ar(7, PMOsc.ar(((((frequ.foldAt(i)*transp) + (Rand(-2.0, 2.0) * d))  + shift) * (distarray.foldAt(i) ** distor)) , frequ.foldAt(i)*(mod[i%fsize]+1), index[a], 0, amps.wrapAt(i%fsize) * max(0, LFDNoise3.kr(lfnoise_amp,1.0))),  LFSaw.kr(spat[a%5]));
			})});

			// sig.poll;
			sig = sig*amp.dbamp.lag(0.1) * envgate * envpause;
			// Out.ar(out, sig)

			Out.ar(out, Splay.ar(FreeVerb.ar(sig, 0.25, 0.9, 0.5, 1.0)))

			/*			sig.collect { |sign, i|
			// i.postln;
			Out.ar(out, FreeVerb2.ar(sig[0], sig[1], 0.25, 0.9, 0.5, 1.0) * Lag.kr(amps2[i], lagGen)) // * EnvGen.ar(Env.asr(7, 1, 15), gate, doneAction: 2))
			}*/

		}).load;
	}

	// FM_band_mes91.def
	*metadata
	{
		^(metadata: (
			synthdefname: "fm_bandas_mes91",
			type: \gen,
			main: \transp,
			sliders: [
				\transp -> ControlSpec(0.001, 5, \lin, 0.001, 1, \transp),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\index1 -> ControlSpec(0, 1, \lin, 0.1, 0, \idx1),
				\index2 -> ControlSpec(0, 1, \lin, 0.1, 0, \idx2),
				\index3 -> ControlSpec(0, 1, \lin, 0.1, 0, \idx3),
				\index4 -> ControlSpec(0, 1, \lin, 0.1, 0, \idx4),
				\index5 -> ControlSpec(0, 1, \lin, 0.1, 0, \idx5),
				\lfnoise_amp -> ControlSpec(0, 20, \lin, 0.001, 0, \lfamp),
				\shift -> ControlSpec(-1200, 1200, \lin, 0, 0, \shift),
				\distor -> ControlSpec(-2, 2, \lin, 0.001, 0, \distor),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lag),
				\lagGen -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lagGen),
				\width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\lag_spat -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag_spat)
			],
			multisliders: [
				/*			\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],*/
				\pos -> [ControlSpec(0.0001, 2, \lin, 0, 0.3, \vol), bandas]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				// \osc -> [\osc, n],
				/*			\freqs -> [\multisliders, \freqs, {
				var freq_min = 40, freq_max = 75; //en midi quart de ton
				[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],*/
				// \amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\pos -> [\multisliders, \pos, { rrand(0.0, 2.0) }, bandas],
				/*			\pos_rt -> [\multisliders, \pos, Array.series(n, 0)*(2/n)],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)]*/
			]
		))

	}

}

///FM mes 91

// var n = 20; //numero de componentes
// var bandas = 5;
// var elementos = n/bandas;
//
//
// SynthDef('fm_bandas_mes91', {|transp = 1.0, amp = 0.02, d = 0, shift = 0, distor = 0, lfnoise_amp = 0.001, index1 = 0.1, index2 = 0.1, index3 = 0.1, index4 = 0.1, index5 = 0.1, gate = 1|
// 	var freqs, amps, ringtimes, signal, pos, conca, len, lista, distarray, mod, index, freqs_clump, spat;
// 	mod = Array.geom(n, 0.123, 1.13).stutter(4);
// 	distarray = Array.series(n, 1);
// 	freqs = [110.0, 116.540947, 184.997208, 207.652344, 239.911697, 311.126984, 523.251099, 466.163788, 493.883301, 538.361816];
// 	amps = [0.021014, 0.036714, 0.032305, 1.738727, 0.199656, 0.02438, 0.012296, 0.004999, 0.007963, 0.168083];
// 	freqs_clump = freqs.clump(elementos);
//
// 	index = [[0, 20, \lin].asSpec.map(index1), [0, 20, \lin].asSpec.map(index2), [0, 20, \lin].asSpec.map(index3), [0, 20, \lin].asSpec.map(index4), [0, 20, \lin].asSpec.map(index5)];
//
// 	spat = [[0.001, 2.0, \lin].asSpec.map(index1), [0.001, 2.0, \lin].asSpec.map(index2), [0.001, 2.0, \lin].asSpec.map(index3), [0.001, 2.0, \lin].asSpec.map(index4), [0.001, 2.0, \lin].asSpec.map(index5)];
//
// 	signal = freqs_clump.collect({|frequ, a|
// 		var fsize= frequ.size;
// 		Mix.fill(fsize*3, {|i|
//
// 			PanAz.ar(7, PMOsc.ar(((((frequ.foldAt(i)*transp) + (Rand(-2.0, 2.0) * d))  + shift) * (distarray.foldAt(i) ** distor)) , frequ.foldAt(i)*(mod[i%fsize]+1), index[a%5], 0, amps.wrapAt(i%fsize) * max(0, LFDNoise3.kr(lfnoise_amp,1.0))),  LFSaw.kr(spat[a%5]));
// 	})});
//
// 	    Out.ar(0, signal* Lag.kr(amp) * EnvGen.ar(Env.asr(2, 1, 2), gate, doneAction: 2));
// }).add;
