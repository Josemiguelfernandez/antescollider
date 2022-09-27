Env_thresh : Tmodule2 {

	*def
	{


	^SynthDef(\env_thresh, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, trig = 0, amp = 0, lo = 0.2, hi = 0.5, filter_time = 0.01 , id = 10, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, sigin, envgate, envpause, cross, chain, fftsize, onsets;
			fftsize = 512;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sigin = In.ar(in);

			sig = Amplitude.kr(sigin);

			// chain = FFT(LocalBuf(fftsize), sigin);
			// onsets = Onsets.kr(chain, thresh, \power, relaxtime);  //\mkl \rcomplex
			// onsets = Onsets.kr(chain, thresh, \power, relaxtime, floor, mingap, medianspan, whtype, rawodf);
			// onsets.poll;
			onsets = Schmidt.kr(sig, lo, hi);
			// onsets = InRange.kr(sig, 0.8, 1);
			onsets = Trig1.kr(onsets, filter_time); // temporizador, antirebond, filtro temporal

			SendReply.kr(onsets, '/rangedetect', 1, id);

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
			synthdefname: "env_thresh",
			type: \fx,
			main: \thresh,
			sliders: [
				// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
				\thresh -> ControlSpec(0.0, 1.0, \lin, 0.01, 1, \thresh),
				\lo -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.2, \lo),
				\hi -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.5, \hi),
				\filter_time -> ControlSpec(0, 2, \lin, 0.01, 0.1, \filter_time),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),

			],

		))

	}

}

// 1.reciprocal
// a = Synth(\fftOnset, [\in, 60,\out, 0])

// a.free

