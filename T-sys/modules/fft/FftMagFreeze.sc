FftMagFreeze : Tmodule2 {

	*def
	{


	^SynthDef(\fftMagFreeze, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, trig = 0, amp = 0, winsize = 0.1, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, chain, fftsize;
			fftsize = 2048;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			chain = FFT(LocalBuf(fftsize, 1).clear, sig);
			chain = PV_MagFreeze(chain, trig);
			sig = IFFT(chain);


			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "fftMagFreeze",
			type: \fx,
			main: \amp,
			sliders: [
				// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			buttons: [ \play -> [[["Freeze", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \trig, 1, \trig, 0]
			]
		))

	}

}

/*a = Synth(\fftMagFreeze, [\in, 60, \t_trig, 1], 1000, 'addToTail')

a.free*/