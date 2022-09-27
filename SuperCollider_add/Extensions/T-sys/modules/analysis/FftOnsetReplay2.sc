FftOnsetReplay2 : Tmodule2 {

	*def
	{


	^SynthDef(\fftOnsetReplay2, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, trig = 0, amp = 0, winsize = 0.1, thresh = 1, relaxtime = 1, floor = 0.05, id = 10, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, sigin, envgate, envpause, cross, chain, fftsize, onsets;
			fftsize = 1024;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sigin = In.ar(in);

			chain = FFT(LocalBuf(fftsize), sigin);
			onsets = Onsets.kr(chain, thresh, \rcomplex, relaxtime, floor);  //\mkl \rcomplex
			// onsets.poll;
			SendReply.kr(onsets, '/onsetdetect', 1, id);

/*			sig = WhiteNoise.ar(EnvGen.kr(Env.perc(0.001, 0.1, 0.2), onsets));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar*/
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "fftOnsetReplay2",
			type: \fx,
			main: \thresh,
			sliders: [
				// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
				\thresh -> ControlSpec(0.0, 1.0, \lin, 0.01, 1, \thresh),
				\relaxtime -> ControlSpec(0.1, 5.0, \lin, 0.01, 0.5, \rlxtime),
				// \id -> ControlSpec(0, 1000, \lin, 1, 10, \rlxtime),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),

			],

		))

	}

}


// a = Synth(\fftOnset, [\in, 60,\out, 0])

// a.free

