Laconicism_11 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_11, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, spd = 8, freq = 55, randHi = 0.4, fbck = 2, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross;


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(AllpassL.ar(SinOsc.ar(freq).tanh,1,TExpRand.ar(2e-4, randHi,Impulse.ar(spd)).round([2e-3,4e-3]),fbck));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_11",
			type: \gen,
			main: \spd,
			sliders: [
				\spd -> ControlSpec(0.1, 64, \exp, 0, 8,\spd),
				\freq -> ControlSpec(10, 150, \exp, 0, 55,\freq),
				\randHi -> ControlSpec(0.01, 1, \lin, 0, 0.4,\randHi),
				\fbck -> ControlSpec(0.01, 20, \exp, 0, 2,\qDiv),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

