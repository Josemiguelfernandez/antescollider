TMFxRev : Tmodule2 {

	*def
	{



		^SynthDef(\TMFxRev, {|in = 0, inbus, ingain = 0, out = 0, outbus, amp = 0, outgain = -120, mixi = 0.25, room = 0.9, damp = 0.5, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = FreeVerb.ar(sig, mixi, room, damp)*amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TMFxRev",
			type: \fx,
			main: \room,
			sliders: [
				\room -> ControlSpec(0, 1,\lin, 0.001,0.9,\bins),
				\damp -> ControlSpec(0, 1,\lin, 0.001,0.5,\bins),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}




