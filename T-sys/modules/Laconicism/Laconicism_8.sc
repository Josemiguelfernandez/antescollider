Laconicism_8 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_8, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, iFreq = 8, nFreq = 2, sawFMul = 1, dTime = 0.1, delHi = 0.01, fbMul = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, t;

			t=Impulse.ar(iFreq)*LFNoise1.ar(nFreq);


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(CombL.ar(Saw.ar([3,4]*sawFMul,Decay.ar(t,dTime)).tanh,1,TRand.ar(0,delHi,t).round(15e-4),TRand.ar(-30 * fbMul,30 * fbMul,t)));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_8",
			type: \gen,
			main: \iFreq,
			sliders: [
				\iFreq -> ControlSpec(0.1, 64, \exp, 0, 8,\iFreq),
				\nFreq -> ControlSpec(0.1, 64, \exp, 0, 2,\nFreq),
				\sawFMul -> ControlSpec(0.1, 200, \exp, 0, 1,\sawFMul),
				\dTime -> ControlSpec(0.01, 3, \exp, 0, 0.1,\dTime),
				\delHi -> ControlSpec(0.001, 0.9, \exp, 0, 0.01,\delHi),
				\fbMul -> ControlSpec(0.1, 5, \exp, 0, 1,\fbMul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


