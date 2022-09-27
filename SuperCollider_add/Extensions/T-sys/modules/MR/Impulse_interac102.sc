Impulse_interac102 : Tmodule2 {

	classvar n = 10; //numero de componentes
	*def
	{

		^SynthDef(\Impulse_interac102, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, x = 0, y = 0, amp = 0,  matrix_ramp = 0.01, freq = 10, pitch = 1, width = 0.1, gate = 1, free = 1|

			var sig, envgate, envpause, cross, exc, pluse, freqs, amps, rings;

			freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.fill(n, { 0.2 + 0.7.rand }));
			rings = \rings.kr(Array.fill(n, { 0.05 + 0.2.rand }));

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;
			freqs = freqs*pitch;

			pluse = LFPulse.kr(freq,0,width,0.002);
			exc = LPZ1.ar(GrayNoise.ar([pluse,pluse]));
			sig = DynKlank.ar(`[freqs,
				amps,
				rings], exc).abs;
			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus

			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	// Impulse_interac102.def

	*metadata
	{
		^(metadata: (
			synthdefname: "Impulse_interac102",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(0.0, 200.0, \lin, 0.1, 10,\freq),
				\pitch -> ControlSpec(0, 20, \lin, 0.01, 1,\pitch),
				\width -> ControlSpec(0, 1, \lin, 0.001, 0.1,\width),
				\x -> ControlSpec(0, 1, \lin, 0.001, 0,\x),
				\y -> ControlSpec(0, 1, \lin, 0.001, 0,\y),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
				\rings -> [ControlSpec(0, 0.25, \lin, 0.001, 0.1, \rings), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\osc -> [\osc, n],
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\rings -> [\multisliders, \rings,{ rrand(0.0, 0.25) }, n]
			]
			)
		)
		}

	}


	/*a = Synth(\impulse_interac1,[\freqs, Array.rand(4, 200, 1600)])

	a.setn(\freqs, 4, *[200, 300, 444, 555])

	b = Array.fill(4, {rrand(100, 1000)});

	s.sendMsg("/n_setn", 1000, '\freqs', 10, *b);  // do this in your OSC responder

	s.sendMsg("/s_new", "impulse_interac1", 1800);

	a = Array.fill(4, {rrand(100, 1000)});

	s.sendMsg("/n_setn", 1800, '\freqs', 4, *a);  // do this in your OSC responder*/


	/*(
	{
	var exc, pluse;
	pluse = LFPulse.kr(8,0,0.1,0.002);
	exc = LPZ1.ar(GrayNoise.ar([amp,amp]));
	Klank.ar(`[FloatArray.fill(4, { rrand(80.0,400.0) }),
	nil,
	FloatArray[1, 1, 1, 1]], exc).abs;
	}.play;
	)*/