Fluid_Sines_Noise : Tmodule2 {

	*def
	{
		^SynthDef(\Fluid_Sines_Noise, {|in = 0, out = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1, bandwidth = 76, detectionThreshold = -96, birthLowThreshold = -24, birthHighThreshold = -60, minTrackLen = 15, trackingMethod = 0, trackMagRange = 15, trackFreqRange = 50, trackProb = 0.5, windowSize = 1024, hopSize = -1, fftSize = -1, maxFFTSize = -1|

			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			// cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);
			sig = FluidSines.ar(sig, bandwidth:bandwidth, detectionThreshold:detectionThreshold, birthLowThreshold:birthLowThreshold, birthHighThreshold:birthHighThreshold, minTrackLen:minTrackLen, trackingMethod:trackingMethod, trackMagRange:trackMagRange, trackFreqRange:trackFreqRange, trackProb:trackProb, windowSize:windowSize, hopSize:hopSize, fftSize:fftSize);
			sig = sig * envgate * envpause;
			ReplaceOut.ar(out, sig[1])
			// XOut.ar(out, cross, sig); //salida directa a un bus

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_Sines_Noise",
			type: \fx,
			main: \modfreq,
			sliders: [
				\modfreq -> ControlSpec(-2400, 2400, \lin, 0, 0, \Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

