Laconicism_6 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_6, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freqMul = 1, noiseFreq = 128, noise2Freq = 1, fbMul = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a;

			a=BPF.ar(Saw.ar([40,40.001] * freqMul),LFNoise0.kr(noiseFreq)+1*4e3+146,LFNoise1.kr(noise2Freq)+1*5e-2+0.01).tanh;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(CombC.ar(a,9,a.abs.lag(2)*9,a.abs.lag(1)*100*fbMul));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_6",
			type: \gen,
			main: \freqMul,
			sliders: [
				\freqMul -> ControlSpec(1, 10, \exp, 0, 1,\freqMul),
				\noiseFreq -> ControlSpec(1, 256, \lin, 0, 128,\noiseFreq),
				\noise2Freq -> ControlSpec(1, 10, \lin, 0, 1,\noise2Freq),
				\fbMul -> ControlSpec(0.1, 200, \exp, 0, 1,\fbMul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


