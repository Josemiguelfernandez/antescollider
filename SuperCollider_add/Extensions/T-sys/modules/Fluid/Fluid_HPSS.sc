Fluid_HPSS : Tmodule2 {

	*def
	{
		^SynthDef(\Fluid_HPSS, {|in = 0, out = 0, xFade = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1, harmFilterSize = 17, percFilterSize = 31, maskingMode = 0, harmThreshFreq1 = 0.1, harmThreshAmp1 = 0, harmThreshFreq2 = 0.5, harmThreshAmp2 = 0, percThreshFreq1 = 0.1, percThreshAmp1 = 0, percThreshFreq2 = 0.5, percThreshAmp2 = 0, windowSize = 1024, hopSize = -1, fftSize = -1|

			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);
			sig = FluidHPSS.ar(sig, maskingMode:maskingMode, harmThreshFreq1:harmThreshFreq1, harmThreshAmp1:harmThreshAmp1, harmThreshFreq2:harmThreshFreq2, harmThreshAmp2:harmThreshAmp2, percThreshFreq1:percThreshFreq1, percThreshAmp1:percThreshAmp1, percThreshFreq2:percThreshFreq2, percThreshAmp2:percThreshAmp2, windowSize:windowSize, hopSize:hopSize, fftSize:fftSize);

/*						sig = FluidHPSS.ar(sig, harmFilterSize:harmFilterSize, percFilterSize:percFilterSize, maskingMode:maskingMode, harmThreshFreq1:harmThreshFreq1, harmThreshAmp1:harmThreshAmp1, harmThreshFreq2:harmThreshFreq2, harmThreshAmp2:harmThreshAmp2, percThreshFreq1:percThreshFreq1, percThreshAmp1:percThreshAmp1, percThreshFreq2:percThreshFreq2, percThreshAmp2:percThreshAmp2, windowSize:windowSize, hopSize:hopSize, fftSize:fftSize);*/

			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_HPSS",
			type: \fx,
			main: \modfreq,
			sliders: [
				\modfreq -> ControlSpec(-2400, 2400, \lin, 0, 0, \Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

