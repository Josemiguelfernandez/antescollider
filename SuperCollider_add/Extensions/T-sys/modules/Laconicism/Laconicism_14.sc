Laconicism_14 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_14, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, iFreq = 8, sLag = 0.3, nFreq = 8, rangeHi = 0.2, fbMul = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, i;

			i=Impulse.ar(iFreq).lag(sLag)!2;
			10.do{i=LeakDC.ar(AllpassC.ar(i,1,LFNoise0.ar(nFreq).range(1e-5,rangeHi),-0.15 * fbMul,LFNoise0.ar(8).range(1,3))).tanh};

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(i);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_14",
			type: \gen,
			main: \iFreq,
			sliders: [
				\iFreq -> ControlSpec(0.1, 64, \exp, 0, 8,\iFreq),
				\sLag -> ControlSpec(0, 2, \lin, 0, 0.3,\sLag),
				\nFreq -> ControlSpec(1, 64, \exp, 0, 8,\nFreq),
				\rangeHi -> ControlSpec(0.01, 0.9, \exp, 0, 0.2,\rangeHi),
				\fbMul -> ControlSpec(0.1, 20, \exp, 0, 1,\fbMul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


