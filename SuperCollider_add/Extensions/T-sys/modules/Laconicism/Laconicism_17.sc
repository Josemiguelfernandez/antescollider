Laconicism_17 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_17, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, sFreq = 8, rel = 0.3, rQ = 0.1, cutMul = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, x;


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(LeakDC.ar(BRF.ar(Saw.ar(sFreq,Decay2.kr(x=Duty.kr(1/sFreq,0,Drand([0,Drand((0.4,0.5..1))],inf)),0.01,rel))**1.5,x*20+([45.1,45] * cutMul),rQ)).tanh);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_17",
			type: \gen,
			main: \sFreq,
			sliders: [
				\sFreq -> ControlSpec(0.1, 10, \lin, 0, 2,\sFreq),
				\rel -> ControlSpec(1, 10, \exp, 0, 1,\rel),
				\rQ -> ControlSpec(0.1, 2, \lin, 0, 1,\rQ),
				\cutMul -> ControlSpec(0.1, 2, \lin, 0, 1,\cutMul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

