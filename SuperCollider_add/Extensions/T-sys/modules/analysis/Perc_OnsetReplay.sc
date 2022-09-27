Perc_OnsetReplay : Tmodule2 {

	*def
	{


		^SynthDef(\Perc_OnsetReplay, {|in = 0, xFade = 1, trig = 0, amp = 0, winsize = 0.1, thresh = 0.05, relaxtime = 100, floor = 0.01, mingap = 12, id = 10, matrix_ramp = 0.01, gate = 1, free = 1, xfreq = 400, lflim = 0.5, hflim = 2, lfgain = 1.0, hfgain = 2.0, binRange, loBin, hiBin, loFreq = 200, hiFreq = 5000|
			var sig, envgate, envpause, cross, chain, fftsize, onsets;
			fftsize = 512;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

// binRange = s.sampleRate / fftsize;
            binRange = 48000 / fftsize;
			loBin = (loFreq / binRange).round;
			hiBin = (hiFreq / binRange).round;

			sig = In.ar(in);

			//compressor
			// sig =    DualBandLim.ar(sig, xfreq, lflim, hflim, lfgain, hfgain);

			chain = FFT(LocalBuf(fftsize), sig);
			chain = PV_BinRange(chain, loBin, hiBin);
			onsets = Onsets.kr(chain, thresh, \complex, relaxtime, floor, mingap);  //\mkl \rcomplex
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
			synthdefname: "Perc_OnsetReplay",
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

