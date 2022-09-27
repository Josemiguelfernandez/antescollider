FftFreezeOnset_mono_ext : Tmodule2 {

	*def
	{


	^SynthDef(\FftFreezeOnset_mono_ext, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = -120, xFade = 1, t_trig = 0, amp = 0, winsize = 0.1, buf = 73, width = 2, orientation = 0.5, lag = 0.2, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, chain, onsets, fftsize, buffer, pos;
			/*fftsize = 2048;
			buffer= LocalBuf(2**11).clear;*/

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1); // + (In.ar(inbus, 1) * ingain);

			chain = FFT(buf, sig);

/*			chain2 = FFT(0, sig);
			onsets = Onsets.kr(chain2, thresh, \rcomplex);*/

			onsets = Trig1.kr(t_trig, 2048/48000 * 0.5 + 0.002);

			chain = PV_Freeze(chain, DelayN.kr(abs(onsets - 1), 0.05, 0.05));
			sig = IFFT(chain);

/*			pos = TRand.kr(0, 2, onsets);

			sig = PanAz.ar(8, sig, pos.lag(lag), 1, width, orientation);*/

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "FftFreezeOnset_mono_ext",
			type: \fx,
			main: \amp,
			sliders: [
				// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
				// \thresh -> ControlSpec(0, 1, \lin, 0.001, 0.5, \thresh),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\buf -> ControlSpec(1, 200, \lin, 1, 73, \buf)
			],
			// buttons: [ \play -> [[["Freeze", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \trig, 1, \trig, 0]
			//]
		))

	}

}
/*~mm_trial = {
	var sig, onsets, chain1, chain2;
	sig = SoundIn.ar(26, 1);
	//Onset chain
	chain1 = FFT(LocalBuf(512, 1), sig);
	//Pitch chain
	chain2 = FFT(LocalBuf(2048, 1), sig);
	//Detect the stuff, make the trigger
	onsets = Onsets.kr(chain1, 0.4, \rcomplex);
	// SendTrig.kr(onsets, 69, 0.42);
	onsets = Trig1.kr(onsets, 2048/48000 * 0.5 + 0.002);
	//Freeze
	chain2 = PV_MagFreeze(chain2, DelayN.kr(abs(onsets - 1), 0.05, 0.05));
	Out.ar(0, IFFT(chain2).dup * 0.5);
}.play;*/