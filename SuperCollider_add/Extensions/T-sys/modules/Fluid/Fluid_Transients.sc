Fluid_Transients : Tmodule2 {

	*def
	{
		^SynthDef(\Fluid_Transients, {|in = 0, out = 0, xFade = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1,order = 20, blockSize = 256, padSize = 128, skew = 0.0, threshFwd = 2.0, threshBack = 1.1, windowSize = 14, clumpLength = 25|

			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);
			sig = FluidTransients.ar(sig, order:order, blockSize:blockSize, padSize:padSize, skew:skew, threshFwd:threshFwd, threshBack:threshBack, windowSize:windowSize, clumpLength:clumpLength);

			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_Transients",
			type: \fx,
			main: \modfreq,
			sliders: [
				\modfreq -> ControlSpec(-2400, 2400, \lin, 0, 0, \Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

