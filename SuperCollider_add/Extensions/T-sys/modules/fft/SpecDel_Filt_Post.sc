SpecDel_Filt_Post : Tmodule2 {
	// : TEffectModule
	*def
	{
		^SynthDef(\SpecDel_Filt_Post, {|in = 0, out = 0, amp = 0, gate = 1, free = 1, matrix_ramp = 0.01, xFade = 1, fftsize = 512, maxdel = 5, dels_buf, fb_buf, filt_buf, hop=0.25|

			var sig, offset, envgate, envpause, cross, chain, dels, fb, filt1, filt2, filt3, filt4, filt5, filt6, gain, n=6;

			// 3 segundos

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);

			chain = FFT(LocalBuf(fftsize, 1), sig, hop);

			chain = PV_BinDelay(chain, maxdel, dels_buf, fb_buf, hop);
			chain = PV_MagMul(chain, filt_buf); // post delays
			// filt_buf.poll;
			sig = IFFT(chain);

			sig = sig * envgate * envpause * amp.dbamp.lag;
			XOut.ar(out, cross, sig); //salida directa a un bus

		}).load;
	}

	// SpecDel.def

	*metadata
	{
		^(metadata: (
			synthdefname: "SpecDel_Filt_Post",
			type: \fx,
			main: \amp,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
			],
		))

	}

}

