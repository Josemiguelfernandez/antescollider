FMBell : Tmodule2 {

	// classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\FMBell, {|out = -1, amp = 0, freq=110, attack = 0.02, decay=8.0, gate = 1, free = 1, matrix_ramp = 0.01, t_trig = 0|
			var mod1, mod2, sig, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			mod2 = EnvGen.kr(Env.perc(attack, decay/3)) * SinOsc.ar(freq * 11.47);
			mod1 = EnvGen.kr(Env.perc(attack, decay * 2/3)) * SinOsc.ar(freq * 9.86, mod2 * 1.5);
			sig = EnvGen.kr(Env.perc(attack, decay), t_trig) * SinOsc.ar(freq * [0.99, 1.01], mod1 * 1.5);


			Out.ar(out, sig*amp.dbamp.lag);
		}).load;
	}

	// Perc_add_20.def


	*metadata
	{
		^(metadata: (
			synthdefname: "FMBell",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 10000, \exp, 0.01, 110, \Hz),
				\attack -> ControlSpec(0, 0.5, \lin, 0.001, 0.02, \attack),
				\decay -> ControlSpec(0.01, 20, \lin, 0.01, 8, \decay),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]

				\trig -> [\t_trig]
			]

		))

	}

}

/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/