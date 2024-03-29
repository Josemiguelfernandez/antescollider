Loudness_Replay : Tmodule2 {

	*def
	{


	^SynthDef(\Loudness_Replay, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, trig = 0, amp = 0, smask=0.25, tmask=1, id = 10, matrix_ramp = 0.01, gate = 1, free = 1, impulse_freq = 20|
			var sig, sigin, envgate, envpause, cross, chain, fftsize, loudness;
			fftsize = 1024; // for sampling rates 44100 and 48000
			//fftsize = 2048;  // for sampling rates 88200 and 96000

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sigin = In.ar(in);

			chain = FFT(LocalBuf(fftsize), sigin);
			// onsets = Onsets.kr(chain, thresh, \power, relaxtime);  //\mkl \rcomplex
			loudness = Loudness.kr(chain, smask, tmask);
			// onsets.poll;

			SendReply.kr(Impulse.kr(impulse_freq), '/loudness', loudness, id);

			///// good for test /////

/*			sig = WhiteNoise.ar(EnvGen.kr(Env.perc(0.001, 0.1, 0.2), onsets));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar*/
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Loudness_Replay",
			type: \fx,
			main: \thresh,
			sliders: [
				// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
				\smask -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.25, \smask),
				\tmask -> ControlSpec(0.0, 1.0, \lin, 0.01, 1, \tmask),
				// \id -> ControlSpec(0, 1000, \lin, 1, 10, \rlxtime),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),

			],

		))

	}

}

// 1.reciprocal
// a = Synth(\fftOnset, [\in, 60,\out, 0])

// a.free

