TFreeVerb8 : Tmodule2 {

	*def
	{



		^SynthDef(\TFreeVerb8, {|in = 0, inbus, ingain = 0, out = 0, outbus, inlev = -12, amp = 0, outgain = -120, roomsize = 0.15, mix = 0.33, damp = 0.5, inputbw = 0.5, spread = 15, drylevel = 0, earlylevel = -3, taillevel = -6, maxroomsize = 300, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8) + (In.ar(inbus, 8) * ingain);
			sig = FreeVerb.ar(sig*inlev.dbamp, mix, roomsize, damp)*amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TFreeVerb8",
			type: \fx,
			main: \roomsize,
			sliders: [
				\inlev -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -12, \inlev),
				\mix -> ControlSpec(0, 1,\lin, 0.001,0.33, \mix),
				\roomsize -> ControlSpec(0.0, 1.0,\lin, 0.001,0.15,\roomsize),
				\damp -> ControlSpec(0, 1,\lin, 0.001,0.5,\damp),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


