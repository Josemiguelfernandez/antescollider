STM_specCombN : Tmodule2 {

	*def
	{


		^SynthDef(\stm_specCombN, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = -120, xFade = 1, trig = 0, amp = 0,
			//normLev = 1, normDur = 0.01, // normalizer
			partials = 8, ratio = 2, strength = 0.1, // specEnhancer
			comb_teeth = 16, comb_phase = 0, comb_width = 0.5, // comb
			scr_trig, scr_wipe = 0, scr_width = 0.2, // scrambler
			limLev = 1, limDur = 0.01, buf,
			matrix_ramp = 1, gate = 1, free = 1|
			var sig, envgate, envpause, cross, norm, harmGen, rComb, scramble, fftSize;
			fftSize = 1024; //1024;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			// sig = Normalizer.ar(sig, normLev, normDur); // a normalizer to make sure there's always a healthy input
			sig =  FFT(LocalBuf(fftSize), sig); // fft analysis
			// sig =  FFT(buf, sig);
			sig = PV_SpectralEnhance(sig, partials, ratio, strength);
			sig = PV_RectComb(sig, comb_teeth, comb_phase, comb_width);
			sig = PV_BinScramble(sig, scr_wipe, scr_width, scr_trig);
			sig = IFFT(sig);
			// sig = PitchShift.ar(sig, wSize, pShift, pDisp, tDisp);
			sig = LeakDC.ar(Limiter.ar(sig, limLev, limDur));


			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "stm_specCombN",
			type: \fx,
			main: \amp,
			sliders: [
				\partials -> ControlSpec(1, 200,\lin,1, 8,\partials),
				\ratio -> ControlSpec(0.98, 2,\lin,0.001, 1,\ratio),
				\strength -> ControlSpec(0.01, 2,\lin,0.001, 0.1,\strength),
				\comb_teeth -> ControlSpec(0.01, 200,\lin,0.001, 16,\comb_teeth),
				\comb_phase -> ControlSpec(0, 2,\lin,0.001, 0,\comb_phase),
				\comb_width -> ControlSpec(0.01, 2,\lin,0.001, 0.5,\comb_width),
				\scr_trig -> ControlSpec(0, 1,\lin,1, 0,\scr_trig),
				\scr_wipe -> ControlSpec(0.01, 2,\lin,0.001, 0,\scr_wipe),
				\scr_width -> ControlSpec(0.01, 2,\lin,0.001, 0.2,\scr_width),
				\limLev -> ControlSpec(0.01, 2,\lin,0.001, 1,\limLev),
				\limDur -> ControlSpec(0.01, 2,\lin,0.001, 0.01,\limDur),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			]
		))

	}

}

// Synth(\stm_specCombN, [\buf, 44])

/*(
// an FFT FX chain
SynthDef(\stm_specCombN, {|outbus = 0, inbus = 8, inAmp = 0.5,
	normLev = 1, normDur = 0.01, // normalizer
	partials = 8, ratio = 2, strength = 0.1, // specEnhancer
	comb_teeth = 16, comb_phase = 0, comb_width = 0.5, // comb
	scr_trig, scr_wipe = 0, scr_width = 0.2, // scrambler
	limLev = 1, limDur = 0.01
	|
	var sig, norm, harmGen, rComb, scramble;
	var fftSize = 2048; //needs to be hardcoded when loading the synth. Take your pick considering time-delay vs lowest frequency...
	sig = In.ar(inbus, 1); // mono input
	// sig = Normalizer.ar(sig, normLev, normDur); // a normalizer to make sure there's always a healthy input
	sig =  FFT(LocalBuf(fftSize), sig); // fft analysis
	sig = PV_SpectralEnhance(sig, partials, ratio, strength);
	sig = PV_RectComb(sig, comb_teeth, comb_phase, comb_width);
	sig = PV_BinScramble(sig, scr_wipe, scr_width, scr_trig);
	sig = IFFT(sig);
	// sig = PitchShift.ar(sig, wSize, pShift, pDisp, tDisp);
	sig = LeakDC.ar(Limiter.ar(sig, limLev, limDur));
	sig = sig.dup; // duplicating ONLY for test purposes
	// sig = FreeVerb.ar(sig); // a little reverb can be useful - but maybe you have that in another synth
	Out.ar(outbus, sig);
}).add
)*/