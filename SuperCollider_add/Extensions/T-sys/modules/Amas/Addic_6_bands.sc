Addic_6_bands : Tmodule2 {

	classvar n = 20; //numero de componentes
	classvar bandas = 15;

	*def
	{
		^SynthDef(\adictivo6_bandas, {|out = -1, outbus = -2,transp = 1.0, amp = 0, d1 = 5, d2 = 5, d3 = 5, d4 = 5, d5 = 5, shift = 0, distor = 0, lag = 1, lagGen = 1, amp0 = 0, amp1 = 0, amp2 = 0, amp3 = 0, amp4 = 0, amp5 = 0, amp6 = 0, amp7 = 0, amp8 = 0, amp9 = 0, amp10 = 0, amp11 = 0, amp12 = 0, amp13 = 0, amp14 = 0, amp15 = 0, gate = 1, free = 1, matrix_ramp = 0.01, width = 2, orientation = 0, lag_spat = 0.2|

			var freqs, amps, amps2, pos, sig, fqs, conca, len, lista, distarray, offset, envgate, envpause, freqs_clump, elementos, desv;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//offset = Line.kr(0, -0.02, 60);
			elementos = n/bandas;
			distarray = Array.series(n, 1);
			freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.with(Array.fill(n, { 1.0 })));
			amps2 =  [amp0, amp1, amp2, amp3, amp4, amp5, amp6, amp7, amp8, amp9, amp10, amp11, amp12, amp13, amp14, amp15];
			desv = [[5, 30, \lin].asSpec.map(d1), [7, 30, \lin].asSpec.map(d2), [8, 30, \lin].asSpec.map(d3), [9, 30, \lin].asSpec.map(d4), [10, 30, \lin].asSpec.map(d5)];
			freqs_clump = freqs.clump(elementos);



			sig = freqs_clump.collect({|frequ, a|
				var fsize = frequ.size;

				Mix.fill(fsize*3, {|i|
					Pan2.ar(SinOsc.ar(((((frequ.foldAt(i) *transp) + (Rand(-2.0, 2.0) * desv[a%5]))  + shift) * (distarray.wrapAt((i%fsize)+(a*3)) ** distor)) , 0, Lag.kr(amps.wrapAt((i%fsize)+(a*3)), ((0.5 + 2.0.rand)*lag))), 1.0.rand2);
			})});



			sig = sig*amp.dbamp.lag(0.1) * envgate * envpause;

			sig.collect { |sign, i|
				// i.postln;
				Out.ar(out, sign * Lag.kr(amps2[i], lagGen)) // * EnvGen.ar(Env.asr(7, 1, 15), gate, doneAction: 2))
			}

		}).load;
	}

// Addic_6_bands.def
	*metadata
	{
	 ^(metadata: (
			synthdefname: "adictivo6_bandas",
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
			/*multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
				\pos -> [ControlSpec(0.0001, 2, \lin, 0, 0.3, \vol), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
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

/*	var n = 30; //numero de componentes
	var bandas = 15;
	var elementos = n/bandas;

	SynthDef('adictivo6_bandas', {|transp = 1.0, amp = 0, d1 = 5, d2 = 5, d3 = 5, d4 = 5, d5 = 5, shift = 0, distor = 0, lag = 1, lagGen = 1, amp0 = 1, amp1 = 1, amp2 = 1, amp3 = 1, amp4 = 1, amp5 = 1, amp6 = 1, amp7 = 1, amp8 = 1, amp9 = 1, amp10 = 1, amp11 = 1, amp12 = 1, amp13 = 1, amp14 = 1, amp15 = 1, gate = 1|
		var freqs, amps, amps2, ringtimes, signal, pos, conca, len, lista, distarray, freqs_clump, desv;
		distarray = Array.series(n, 1);
		freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
		amps = \amps.kr(Array.with(Array.fill(n, { 0.0 })));
		amps2 =  [amp0, amp1, amp2, amp3, amp4, amp5, amp6, amp7, amp8, amp9, amp10, amp11, amp12, amp13, amp14, amp15];
		desv = [[5, 30, \lin].asSpec.map(d1), [7, 30, \lin].asSpec.map(d2), [8, 30, \lin].asSpec.map(d3), [9, 30, \lin].asSpec.map(d4), [10, 30, \lin].asSpec.map(d5)];
		freqs_clump = freqs.clump(elementos);

		signal = freqs_clump.collect({|frequ, a|
			var fsize= frequ.size;
			//frequ.postln;
			//fsize*2.postln;
			Mix.fill(fsize*3, {|i|
				//((i%fsize)+(a*3)).postln; //formula para recuperar las amps del array de amps
				//SinOsc.ar(frequ.wrapAt(i), 0, amps.wrapAt((i%fsize)+(a*3)))
				//frequ.foldAt(i).poll;

				Pan2.ar(SinOsc.ar(((((frequ.foldAt(i) *transp) + (Rand(-2.0, 2.0) * desv[a%5]))  + shift) * (distarray.wrapAt((i%fsize)+(a*3)) ** distor)) , 0, Lag.kr(amps.wrapAt((i%fsize)+(a*3))*amp, ((0.5 + 2.0.rand)*lag))), 1.0.rand2);
		})});

		//signal1.poll;

		signal.collect { |sig, i|
			i.postln;
			Out.ar(0, sig * Lag.kr(amps2[i], lagGen) * EnvGen.ar(Env.asr(7, 1, 15), gate, doneAction: 2))
		   					}

		//Out.ar(0, signal1*amps2);
	}).load; //use .load instead for large arrays to avoid /d_recv failed*/
	