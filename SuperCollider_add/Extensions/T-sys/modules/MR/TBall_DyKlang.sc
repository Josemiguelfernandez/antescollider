TBall_DyKlang : Tmodule2 {

	classvar n = 10; //numero de componentes
	*def
	{

		^SynthDef(\TBall_DyKlang, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, gravity = 20, damp = 0.001, fric = 0.001, amp = 0,  matrix_ramp = 0.01, freq = 1, pitch = 1, width = 0.1, gate = 1, free = 1|

			var sig, envgate, envpause, cross, exc, pluse, freqs, amps, rings, impuls;

			freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.fill(n, { 0.2 + 0.7.rand }));
			rings = \rings.kr(Array.fill(n, { 0.05 + 0.2.rand }));

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;
			freqs = freqs*pitch;

			impuls = Impulse.ar(freq);

/*			g = MouseX.kr(0.01, 100, 1);
			d = MouseY.kr(0.00001, 0.2);*/

			exc = TBall.ar(impuls * 10, gravity, damp, fric);

			// z = Mix(RHPF.ar(Ringz.ar(f, 451 * Array.fill(4, { |i| (i+1) + 0.3.rand2 }) * 2, 0.1)),800,0.1);

			/*Splay.ar(z) * 0.8*/

/*			pluse = LFPulse.kr(freq,0,width,0.002);
			exc = LPZ1.ar(GrayNoise.ar([pluse,pluse]));*/
			//probar RHPF
			sig = DynKlank.ar(`[freqs,
				amps,
				rings], exc).abs;
			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus

			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	// TBall_DyKlang.def

	*metadata
	{
		^(metadata: (
			synthdefname: "TBall_DyKlang",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(0.0, 200.0, \lin, 0.1, 10,\freq),
				\pitch -> ControlSpec(0, 20, \lin, 0.01, 1,\pitch),
				\gravity -> ControlSpec(0.01, 100, \exp, 0.001, 20, \gravity),
				\damp -> ControlSpec(0.00001, 0.2, \lin, 0.0001, 0.001,\damp),
				\fric -> ControlSpec(0.00001, 0.2, \lin, 0.0001, 0.001,\fric),
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


/*(
{
	var sc, g, d, z, lfo, rate, in;
	g = MouseX.kr(0.01, 100, 1);
	d = MouseY.kr(0.00001, 0.2);
	sc = #[451, 495.5, 595, 676, 734.5]; //azande harp tuning by B. Guinahui
	lfo = LFNoise1.kr(1, 0.005, 1);
	rate = 2.4;
	rate = rate * sc.size.reciprocal;

	in = Impulse.ar(1);
	/*        in = Ringz.ar(in,
	Array.fill(4, { |i| (i+1) + 0.1.rand2 }) / 2
	* Decay.ar(in,0.02,rand(0.5,1), lfo)                        * u,
	Array.exprand(4, 0.2, 1).sort
	);*/
	// in = Mix(in);
	f = TBall.ar(in * 10, g, d, 0.001);

	z = Mix(RHPF.ar(Ringz.ar(f, 451 * Array.fill(4, { |i| (i+1) + 0.3.rand2 }) * 2, 0.1)),800,0.1);

	Splay.ar(z) * 0.8
}.play;
)*/