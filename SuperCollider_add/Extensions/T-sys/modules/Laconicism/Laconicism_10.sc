Laconicism_10 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_10, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, expon = 6, qDiv = 3000, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, t, a;

			t=[0,0,0,1,5,7,10,12,12,12]+30;
			a=Duty.kr(1/8,0,Dxrand(t+24++t++t,inf));

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar((BHiPass.ar(LFNoise1.ar(8)**expon,[a,a+7].midicps,a/qDiv,67-a)).tanh);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_10",
			type: \gen,
			main: \expon,
			sliders: [
				\expon -> ControlSpec(1, 64, \exp, 0, 6,\expon),
				\qDiv -> ControlSpec(10, 10000, \lin, 0, 3000,\qDiv),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

