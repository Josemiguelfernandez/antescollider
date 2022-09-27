TScrambler : Tmodule2 {

	*def
	{


		^SynthDef(\TScrambler, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = -120, xFade = 1, buf = 1, amp = 0, trate =  60, rate = 1, rateDev = 0, tratedev = 0, pos = 0, panDev = 0, dur = 0.1, matrix_ramp = 0.01, startPos = 0.1, gate = 1, free = 1, t_trig = 0, centerpos = 0, bufenv = -1, maxdel = 2, length = 0.05, noise_freq = 13|
			var sig, envgate, envpause, cross; //dur,

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1); // + (In.ar(inbus, 8) * ingain);

			// sig = Stutter.ar(sig, t_trig, length, rate, maxdel);
			sig = DelayC.ar(sig, maxdel, LFNoise0.ar(noise_freq).range(0.0, maxdel));
			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			//Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TScrambler",
			type: \fx,
			main: \amp,
			sliders: [
				\maxdel -> ControlSpec(0, 10,\lin,0.1,2,\maxdel),
				\noise_freq -> ControlSpec(0, 100,\lin,0.01,13,\del),
				// \rateDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\rateDev),
				// \centerpos -> ControlSpec(0, 1, \lin,0.001, 0.1,\centerpos),
				// \centerDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\centerDev),
				// \dur -> ControlSpec(0.001, 1.0,\lin,0.001,0.001,\dur),
				// \durDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\durDev),
				// \pos -> ControlSpec(-1, 1, \lin, 0.001, 0, \pos),
				// \panDev -> ControlSpec(0, 1.0, \lin,0.001, 0,\panDev),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			/*buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\trig -> [\t_trig]
			]*/
		))

	}

}

// 1.bufnum