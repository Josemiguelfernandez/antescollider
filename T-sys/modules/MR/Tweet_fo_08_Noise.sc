Tweet_fo_08_Noise : Tmodule2 {

	*def
	{


	^SynthDef(\tweet_fo_08_Noise, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, lfo = 0.5, rangeA1 = 1.8, rangeA2 = 1.98, rangeB1 = 5e-4, rangeB2 = 1e-3, decaytime = 0.0012, trandlo = 200, trandhi = 2e3, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, x;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			x = LFNoise1.ar(lfo!2);

			sig = Formlet.ar(Crackle.ar(x.range(rangeA1,rangeA2)),TExpRand.ar(trandlo,trandhi,x).lag(2), x.range(5e-4,1e-3), decaytime);

			sig = Mix.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tweet_fo_08_Noise",
			type: \gen,
			main: \lfo,
			sliders: [
				\lfo -> ControlSpec(0, 200, \lin, 0.001, 0.5, \lfo),
				\rangeA1 -> ControlSpec(0.001, 10, \lin, 0.01, 1.8,\rA1),
				\rangeA2 -> ControlSpec(0.001, 10, \lin, 0.01, 1.98,\rA2),
				\rangeB1 -> ControlSpec(0.001, 0.5, \lin, 0.0001, 5e-4,\rB1),
				\rangeB2 -> ControlSpec(0.001, 0.5, \lin, 0.0001, 1e-3,\rB2),
				\trandlo -> ControlSpec(0, 5000, \lin, 1, 200,\trandlo),
				\trandhi -> ControlSpec(0, 5000, \lin, 1, 2000,\trandhi),
				\decaytime -> ControlSpec(0.001, 1, \lin, 0.0001, 0.0012,\decaytime),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


/*//--tweet0008
play{
	x=LFNoise1.ar(0.5!2);
	Formlet.ar(Crackle.ar(x.range(1.8,1.98)),TExpRand.ar(200,2e3,x).lag(2),x.range(5e-4,1e-3),0.0012)}  //#SuperCollider*/