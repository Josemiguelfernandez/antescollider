Laconicism_13 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_13, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, spd = 16, lTime = 0.1, lo = 1500, hi = 2000, sExp = 2, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, t, p, b;

			t=[0,3,5,7,10,12]+40;
			p=Duty.ar(1/4,0,Drand((t+12++t).midicps,inf));

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(Blip.ar([b=TRand.ar(lo,hi,Impulse.ar(spd)).lag(lTime),b+p],1).mean!2**sExp);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_13",
			type: \gen,
			main: \spd,
			sliders: [
				\spd -> ControlSpec(0.5, 64, \lin, 0, 16,\spd),
				\lTime -> ControlSpec(0.01, 10, \exp, 0, 0.1,\lTime),
				\lo -> ControlSpec(100, 2000, \exp, 0, 1500,\lo),
				\hi -> ControlSpec(500, 4000, \exp, 0, 2000,\hi),
				\sExp -> ControlSpec(1, 6, \exp, 0, 2,\sExp),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


		