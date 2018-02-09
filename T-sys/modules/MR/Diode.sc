Diode : Tmodule2 {

	*def
	{


	^SynthDef(\diode, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, car_fq1 = 200, car_fq2 = 200, modfreq = 100, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, x;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = DiodeRingMod.ar(SinOsc.ar(car_fq1 * ([1, 1.1, 1.2] * SinOsc.ar(car_fq2).range(1, 2))), SinOsc.ar(modfreq * [0.75, 1, 0.5])).sum * 0.2;

/*			sig = Mix.ar(sig);*/

			sig = sig * envgate * envpause * amp.dbamp.lag;

			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "diode",
			type: \gen,
			main: \car_fq1,
			sliders: [
				\car_fq1 -> ControlSpec(20, 20000, \exp, 0.1, 200, \Hz),
				\car_fq2 -> ControlSpec(20, 20000, \exp, 0.1, 200, \Hz),
				\modfreq -> ControlSpec(20, 20000, \exp, 0.1, 100, \Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}




// {DiodeRingMod.ar(SinOsc.ar(200 * ([1, 1.1, 1.2] * SinOsc.ar(200).range(1, 2))), SinOsc.ar(100 * [0.75, 1, 0.5])).sum * 0.2 }.play
