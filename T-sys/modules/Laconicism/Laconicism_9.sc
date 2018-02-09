Laconicism_9 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_9, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, sLag = 0.1, ranFreqMul = 1, fMul = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, n, f, l;

			n=LFDNoise0.ar(_);
			f=[60,61] * fMul;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(tanh(BBandPass.ar(max(max(n.(4 * ranFreqMul),l=n.(6 * ranFreqMul)),SinOsc.ar(f*ceil(l*9).lag(sLag))*0.7),f,n.(1 * ranFreqMul).abs/2)*700*l.lag(1)));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_9",
			type: \gen,
			main: \sLag,
			sliders: [
				\sLag -> ControlSpec(0.001, 100, \exp, 0, 0.1,\sLag),
				\ranFreqMul -> ControlSpec(0.1, 100, \exp, 0, 1,\ranFreqMul),
				\fMul -> ControlSpec(0.1, 20, \exp, 0, 1,\fMul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

