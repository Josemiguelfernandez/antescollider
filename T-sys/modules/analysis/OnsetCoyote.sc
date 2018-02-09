OnsetCoyote : Tmodule2 {

	*def
	{


	^SynthDef(\onsetCoyote, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, trig = 0, amp = 0, trackFall = 0.2, slowLag = 0.2, fastLag = 0.01, fastMul = 0.5, thresh = 0.05, minDur = 0.1, id = 1000, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, onsets;


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in);

			// chain = FFT(LocalBuf(fftsize), sigin);
			onsets = Coyote.kr(sig, trackFall, slowLag, fastLag, fastMul, thresh, minDur);

			SendReply.kr(onsets, '/onsetdetect', 1, id);

/*			sig = PinkNoise.ar(Decay.kr(onsets, 0.2));
			Out.ar(0, sig);*/

/*			sig = WhiteNoise.ar(EnvGen.kr(Env.perc(0.001, 0.1, 0.2), onsets));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar*/
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "onsetCoyote",
			type: \fx,
			main: \thresh,
			sliders: [
				// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
				\thresh -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.05, \thresh),
				\trackFall -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.2, \trackFall),
				\slowLag -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.2, \slowLag),
				\fastLag -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.01, \fastLag),
				\fastMul -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.5, \fastMul),
				\minDur -> ControlSpec(0.0, 5.0, \lin, 0.01, 0.1, \minDur),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -120, \dB)
			],

		))

	}

}
