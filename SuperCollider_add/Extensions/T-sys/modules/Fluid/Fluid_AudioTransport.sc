Fluid_AudioTransport : Tmodule2 {

	*def
	{
		^SynthDef(\Fluid_AudioTransport, {|in = 0, out = 0, xFade = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1, buf = 0, interpolation = 0.0, windowSize = 1024, hopSize = -1, fftSize = -1, maxFFTSize = -1|

			var sig, envgate, envpause, cross, play_buf;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);
			play_buf = PlayBuf.ar(1,buf,loop: 1);
			sig = FluidAudioTransport.ar(sig, play_buf, interpolation, windowSize, hopSize, fftSize, maxFFTSize);

			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_AudioTransport",
			type: \fx,
			main: \modfreq,
			sliders: [
				\modfreq -> ControlSpec(-2400, 2400, \lin, 0, 0, \Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

