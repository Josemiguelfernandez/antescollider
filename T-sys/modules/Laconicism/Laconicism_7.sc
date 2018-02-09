Laconicism_7 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_7, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, iFreq = 0.25, bliFMul = 1, fbMul = 1, delMul = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, f, k;

			f=LocalIn.ar(2).tanh;
			k=Latch.kr(f[0].abs,Impulse.kr(iFreq));
			LocalOut.ar(f+CombC.ar(Blip.ar([4,6]*bliFMul,100*k+50,0.9),1,k*0.3*delMul,50*f*fbMul));


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(f);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_7",
			type: \gen,
			main: \iFreq,
			sliders: [
				\iFreq -> ControlSpec(0.1, 100, \exp, 0, 0.25,\iFreq),
				\bilFMul -> ControlSpec(0.1, 100, \exp, 0, 1,\bilFMul),
				\fbMul -> ControlSpec(0.1, 500, \lin, 0, 100,\fbMul),
				\delMul -> ControlSpec(0.1, 1, \lin, 0, 1,\delMul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


