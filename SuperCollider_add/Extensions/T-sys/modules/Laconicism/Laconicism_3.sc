Laconicism_3 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_3, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, speed = 4, bigger = 0, decay = 3, ratio = 2, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, q, t;

			q=[0,3,5,7,10];
			t=Impulse.kr(speed)*LFNoise0.kr>bigger;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(PitchShift.ar(Saw.ar(Demand.kr(t,0,Drand((q+12++q+33).midicps,inf)),Decay.kr(t,decay)),7,ratio)!2);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_3",
			type: \gen,
			main: \speed,
			sliders: [
				\speed -> ControlSpec(0.1, 32, \exp, 0, 4,\speed),
				\bigger -> ControlSpec(-1, 1, \lin, 0, 0,\bigger),
				\decay -> ControlSpec(0.01, 10, \lin, 0, 3,\decay),
				\ratio -> ControlSpec(0.1, 4, \exp, 0, 2,\ratio),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

