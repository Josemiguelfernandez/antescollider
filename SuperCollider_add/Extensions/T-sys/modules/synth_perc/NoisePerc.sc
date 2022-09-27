NoisePerc : Tmodule2 {

	// classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{
		^SynthDef(\NoisePerc, {|out = -1, amp = 0, freq=110, noise_att = 0.001, noise_release = 0.1, noise_amp = -6, gate = 1, free = 1, matrix_ramp = 0.01, t_trig = 0|
			var noisenv, sig, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			noisenv = EnvGen.kr(Env.perc(noise_att, noise_release, noise_amp.dbamp.lag), t_trig);
			sig = PinkNoise.ar(10)*noisenv;


			Out.ar(out, sig*amp.dbamp.lag);
		}).load;
	}

	// Perc_add_20.def


	*metadata
	{
		^(metadata: (
			synthdefname: "NoisePerc",
			type: \gen,
			main: \freq,
			sliders: [
				\noise_att -> ControlSpec(0, 0.5, \lin, 0.001, 0.001, \attack),
				\noise_release -> ControlSpec(0.01, 20, \lin, 0.01, 0.1, \decay),
				\noise_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \dB),
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



