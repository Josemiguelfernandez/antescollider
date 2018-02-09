Compressor_8 : Tmodule2 {

	*def
	{



		^SynthDef(\compressor_8, {|in = 0, inbus, ingain = 0, out = 0, outbus, inlev = 0, amp = 0, outgain = -120, thresh = 1, slopeBelow = 1, slopeAbove = 1, clampTime = 0.01, relaxTime = 0.1, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8) + (In.ar(inbus, 8) * ingain);

			sig = sig*inlev.dbamp;

			sig = Compander.ar(sig, sig, thresh, slopeBelow, slopeAbove, clampTime, relaxTime);

			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "compressor_8",
			type: \fx,
			main: \thresh,
			sliders: [
				\inlev -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \inlev),
				\thresh -> ControlSpec(0, 2,\lin, 0.001, 1,\thresh),
				\slopeBelow -> ControlSpec(0, 1,\lin, 0.001, 1,\slopeBelow),
				\slopeAbove -> ControlSpec(0, 1,\lin, 0.001, 1,\slopeAbove),
				\clampTime -> ControlSpec(0, 1,\lin, 0.0001, 0.01,\clampTime),
				\relaxTime -> ControlSpec(0, 1,\lin, 0.001, 0.1,\relaxTime),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


