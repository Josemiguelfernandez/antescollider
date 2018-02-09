Laconicism_4 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_4, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, duty = 0.6, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross;


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(Mix(HPF.ar(MidEQ.ar(Limiter.ar(GVerb.ar(HPF.ar(Pulse.ar([[0.1,0.11],[0.12,0.13]],duty,5e-3),99),[[1,5/4],[1.5,2]],99)),9e3,0.9,9),200)));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_4",
			type: \gen,
			main: \duty,
			sliders: [
				\duty -> ControlSpec(0.01, 0.99, \lin, 0, 0.6,\duty),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}
