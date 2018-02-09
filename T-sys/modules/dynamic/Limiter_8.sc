Limiter_8 : Tmodule2 {

	*def
	{



		^SynthDef(\limiter_8, {|in = 0, inbus, ingain = 0, out = 0, outbus, inlev = 0, amp = 0, outgain = -120, level = 1, dur = 0.01, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8) + (In.ar(inbus, 8) * ingain);
			sig = Limiter.ar(sig*inlev.dbamp, level, dur);

			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "limiter_8",
			type: \fx,
			main: \level,
			sliders: [
				\inlev -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \inlev),
				\level -> ControlSpec(0, 2,\lin, 0.001,1,\level),
				\dur -> ControlSpec(0, 1,\lin, 0.001,0.01,\dur),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


