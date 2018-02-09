TGreyhole : Tmodule2 {

	*def
	{



		^SynthDef(\tGreyhole, {|in = 0, inbus, ingain = 0, out = 0, outbus, amp = 0, outgain = -120, dtime = 0.1, damp = 0.1, size = 1, diff = 0.707, fb = 0.1, modDepth = 0.01, modFreq = 2, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = Greyhole.ar(sig, dtime, damp, size, diff, fb, modDepth, modFreq) *amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tGreyhole",
			type: \fx,
			main: \dtime,
			sliders: [
				\dtime -> ControlSpec(0, 10,\lin, 0.01,0.1,\dtime),
				\damp -> ControlSpec(0, 1,\lin, 0.01,0.1,\damp),
				\size -> ControlSpec(0.5, 3,\lin, 0.001,1,\size),
				\diff -> ControlSpec(0, 1,\lin, 0.001,0.707,\diff),
				\fb -> ControlSpec(0, 1,\lin, 0.01,0.1,\fb),
				\modDepth -> ControlSpec(0, 50,\lin, 0.001,0.01,\modDepth),
				\modFreq -> ControlSpec(0, 10,\lin, 0.001,2,\modFreq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

