TGVerb : Tmodule2 {

	*def
	{



		^SynthDef(\TGVerb, {|in = 0, inbus, ingain = 0, out = 0, outbus, inlev = -12, amp = 0, outgain = -120, mixi = 0.25, roomsize = 10, revtime = 3, damping = 0.5, inputbw = 0.5, spread = 15, drylevel = 0, earlylevel = -3, taillevel = -6, maxroomsize = 300, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = GVerb.ar(sig*inlev.dbamp, roomsize, revtime, damping, inputbw, spread, drylevel.dbamp, earlylevel.dbamp, taillevel.dbamp, roomsize)*amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TGVerb",
			type: \fx,
			main: \roomsize,
			sliders: [
				\inlev -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -12, \inlev),
				\roomsize -> ControlSpec(0.1, 300,\lin, 0.1,10,\roomsize),
				\revtime -> ControlSpec(0.1, 300,\lin, 0.1,3,\revtime),
				\damping -> ControlSpec(0, 1,\lin, 0.001,0.5,\damping),
				\inputbw -> ControlSpec(0, 1,\lin, 0.001,0.5,\inputbw),
				\spread -> ControlSpec(0, 100,\lin, 0.1,15,\spread),
				\drylevel -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \drylevel),
				\earlylevel -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -3, \earlylevel),
				\taillevel -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \taillevel),
				\maxroomsize -> ControlSpec(0, 500,\lin, 1,300,\maxroomsize),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


