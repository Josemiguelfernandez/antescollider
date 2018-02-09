OnsetJensen : Tmodule2 {

	*def
	{


	^SynthDef(\onsetJensen, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, trig = 0, amp = 0, propsc = 0.25, prophfe = 0.25, prophfc = 0.25, propsf = 0.25, thresh = 1, waittime = 0.04, relaxtime = 1, id = 1000, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, sigin, envgate, envpause, cross, chain, fftsize, onsets;
			fftsize = 2048;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sigin = In.ar(in);

			chain = FFT(LocalBuf(fftsize), sigin);
			onsets = PV_JensenAndersen.ar(chain, propsc, prophfe, prophfc, propsf, thresh, waittime);  //\mkl \rcomplex
			// onsets.poll;
			onsets = A2K.kr(onsets);
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
			synthdefname: "onsetJensen",
			type: \fx,
			main: \thresh,
			sliders: [
				// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
				\thresh -> ControlSpec(0.0, 1.0, \lin, 0.01, 1, \thresh),
				\waittime -> ControlSpec(0.01, 5.0, \lin, 0.01, 0.04, \waittime),
				\propsc -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.25, \propsc),
				\prophfe -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.25, \prophfe),
				\propsf -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.25, \propsf),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),

			],

		))

	}

}

// (buffer, propsc: 0.25, prophfe: 0.25, prophfc: 0.25, propsf: 0.25, threshold: 1, waittime: 0.04)

// a = Synth(\fftOnset, [\in, 60,\out, 0])

// a.free
/*(
SynthDef(\fftod, { var source1, detect;
        source1 = AudioIn.ar(1);
        detect = PV_JensenAndersen.ar(FFT(LocalBuf(2048), source1),
            threshold:MouseX.kr(0.1,1.0));
        Out.ar(0, SinOsc.ar([440,445], 0, Decay.ar(0.1*detect, 0.1)));
    }).play(s);
)*/