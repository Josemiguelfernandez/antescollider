Addic_Synth3 : Tmodule2 {

	classvar n = 30; //numero de componentes
	classvar bandas = 15;

	*def
	{
		^SynthDef(\adic_synth3, {|out = -1, outbus = -2,transp = 1.0, amp = 0, d1 = 5, d2 = 5, d3 = 5, d4 = 5, d5 = 5, shift = 0, distor = 0, lag = 1, lagGen = 1, amp0 = 0, amp1 = 0, amp2 = 0, amp3 = 0, amp4 = 0, amp5 = 0, amp6 = 0, amp7 = 0, amp8 = 0, amp9 = 0, amp10 = 0, amp11 = 0, amp12 = 0, amp13 = 0, amp14 = 0, amp15 = 0, gate = 1, free = 1, matrix_ramp = 0.01, width = 2, orientation = 0, lag_spat = 0.2|

			var freqs, amps, pos, amps2, sig, fqs, conca, len, lista, distarray, offset, envgate, envpause, freqs_clump, elementos, desv;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//offset = Line.kr(0, -0.02, 60);
			elementos = n/bandas;
			distarray = Array.series(n, 1);
			/*			freqs = \freqs.kr( [49.907581, 99.2, 156.117996, 262.744202, 340.753235, 419.81662, 522.859253, 602.681885, 681.22052, 759.881714, 838.429199, 916.473938, 943.13501, 1021.06, 1099.651733, 1177.955444, 1282.507446, 1360.8, 1439.156738, 1517.888916, 1595.893433, 1621.914429, 1672.90918, 1699.648926, 1778.450562, 1856.845703, 1935.331299, 2039.495117, 2117.734863, 2196.139648]);*/
			freqs = \freqs.kr( [49.907581, 99.2, 156.117996, 262.744202, 340.753235, 419.81662, 522.859253, 602.681885, 681.22052, 759.881714, 838.429199, 916.473938, 943.13501, 1021.06, 1099.651733, 1177.955444, 1282.507446, 1360.8, 1439.156738, 1517.888916, 1595.893433, 1621.914429, 1672.90918, 1699.648926, 1778.450562, 1856.845703, 1935.331299, 2039.495117, 2117.734863, 2196.139648]);

			amps = \amps.kr([0.992126, 0.992126, 0.88189, 0.401575, 0.637795, 0.787402, 0.84252, 0.88189, 0.944882, 0.992126, 0.992126, 0.992126, 0.992126, 0.992126, 0.992126, 0.992126, 0.992126, 0.992126, 0.874016, 0.850394, 0.566929, 0.503937, 0.362205, 0.362205, 0.992126, 0.834646, 0.401575, 0.401575, 0.84252, 0.527559]);

			pos = \pos.kr((Array.series(bandas, 0)*(2/bandas)));

			amps2 =  [amp0, amp1, amp2, amp3, amp4, amp5, amp6, amp7, amp8, amp9, amp10, amp11, amp12, amp13, amp14, amp15];
			// desv = [[0, 20, \lin].asSpec.map(d1), [0, 20, \lin].asSpec.map(d2), [0, 20, \lin].asSpec.map(d3), [0, 20, \lin].asSpec.map(d4), [0, 20, \lin].asSpec.map(d5)];
			desv = [d1, d2, d3, d4, d5];

			freqs_clump = freqs.clump(elementos);
			freqs_clump.postln;

			sig = freqs_clump.collect({|frequ, a|
				var fsize = frequ.size;

				Mix.fill(fsize*3, {|i|
					PanAz.ar(8, SinOsc.ar(((((Lag.kr(frequ.wrapAt(i), (0.5 + 2.0.rand)*lag)*transp) + (Rand(-2.0, 2.0) * desv[a]))  + [0, 0, \lin].asSpec.map(shift)) * (distarray.wrapAt((i%fsize)+(a*3)) ** distor)) , 0, Lag.kr(amps.wrapAt((i%fsize)) * Lag.kr(amps2[a], lagGen), ((0.5 + 2.0.rand)*lag))), Lag.kr(pos[a], lag_spat), 1, width, orientation);
					// pos[a].postln;
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

	// Addic_Synth3.def
	*metadata
	{
		^(metadata: (
			synthdefname: "adic_synth3",
			type: \gen,
			main: \transp,
			sliders: [
				\transp -> ControlSpec(0.001, 5, \lin, 0.001, 1, \transp),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\d1 -> ControlSpec(0, 1, \lin, 0.1, 0, \desv1),
				\d2 -> ControlSpec(0, 1, \lin, 0.1, 0, \desv2),
				\d3 -> ControlSpec(0, 1, \lin, 0.1, 0, \desv3),
				\d4 -> ControlSpec(0, 1, \lin, 0.1, 0, \desv4),
				\d5 -> ControlSpec(0, 1, \lin, 0.1, 0, \desv5),
				\shift -> ControlSpec(-1200, 1200, \lin, 0, 0, \shift),
				\distor -> ControlSpec(-2, 2, \lin, 0.001, 0, \distor),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lag),
				\lagGen -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lagGen),
				\amp0 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp0),
				\amp1 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp1),
				\amp2 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp2),
				\amp3 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp3),
				\amp4 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp4),
				\amp5 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp5),
				\amp6 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp6),
				\amp7 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp7),
				\amp8 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp8),
				\amp9 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp9),
				\amp10 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp10),
				\amp11 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp11),
				\amp12 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp12),
				\amp13 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp13),
				\amp14 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp14),
				\amp15 -> ControlSpec(0, 1, \lin, 0.1, 0, \amp15),
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

// var n = 10; //numero de componentes
// var bandas = 5;
// var elementos = n/bandas;
//
// SynthDef('adictivo_synthInit', {|transp = 1.0, amp = 0.2, d1 = 5, d2 = 5, d3 = 5, d4 = 5, d5 = 5, shift = 0, distor = 0, lag = 1, lagGen = 1, amp0 = 1, amp1 = 1, amp2 = 1, amp3 = 1, amp4 = 1, amp5 = 1, amp6 = 1, amp7 = 1, amp8 = 1, amp9 = 1, amp10 = 1, amp11 = 1, amp12 = 1, amp13 = 1, amp14 = 1, amp15 = 1, gate = 1|
// 	var freqs, amps, amps2, ringtimes, signal, pos, conca, len, lista, distarray, freqs_clump, desv;
// 	distarray = Array.series(n, 1);
// 	freqs = \freqs.kr( [339.286377, 678.572754, 761.67218, 1108.730591, 1437.845703, 1760.0, 2093.004395, 2875.690918, 3227.854248, 3322.437744]);
// 	amps = \amps.kr([0.168083, 0.035469, 0.000493, 0.016453, 0.023544, 0.156598, 1.431317, 0.071514, 0.024159, 0.001197] *0.1);
// 	amps2 =  [amp0, amp1, amp2, amp3, amp4, amp5, amp6, amp7, amp8, amp9, amp10, amp11, amp12, amp13, amp14, amp15];
// 	desv = [[0, 200, \lin].asSpec.map(d1), [0, 200, \lin].asSpec.map(d2), [0, 200, \lin].asSpec.map(d3), [0, 200, \lin].asSpec.map(d4), [0, 200, \lin].asSpec.map(d5)];
// 	freqs_clump = freqs.clump(elementos);
//
// 	signal = freqs_clump.collect({|frequ, a|
// 		var fsize= frequ.size;
// 		//frequ.postln;
// 		//fsize*2.postln;
// 		Mix.fill(fsize*3, {|i|
//
// 			Pan2.ar(SinOsc.ar(((((Lag.kr(frequ.foldAt(i), (0.5 + 2.0.rand)*lag)*transp) + (Rand(-2.0, 2.0) * desv[a%5]))  + [0, 0, \lin].asSpec.map(shift)) * (distarray.wrapAt((i%fsize)+(a*3)) ** distor)) , 0, Lag.kr(amps.wrapAt((i%fsize)+(a*3))*amp, ((0.5 + 2.0.rand)*lag))), 1.0.rand2);
// 	})});
//
//
// 	signal.collect { |sig, i|
// 		i.postln;
// 		Out.ar(0, FreeVerb2.ar(sig[0], sig[1], 0.25, 0.9, 0.5, 1.0) * Lag.kr(amps2[i], lagGen) * EnvGen.ar(Env.asr(2, 1, 2), gate, doneAction: 2))
// 	   					}
//
//
// }).load; //use .load instead for large arrays to avoid /d_recv failed
