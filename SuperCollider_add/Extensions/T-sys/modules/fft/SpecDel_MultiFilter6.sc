SpecDel_MultiFilter6 : Tmodule2 {
	// : TEffectModule
	*def
	{
		^SynthDef(\SpecDel_MultiFilter6, {|in = 0, out = 0, amp = 0, gate = 1, free = 1, matrix_ramp = 0.01, xFade = 1, fftsize = 1024, maxdel = 5, dels_buf, fb_buf, hop=0.25|

			var sig, offset, envgate, envpause, cross, chain, dels, fb, filt1, filt2, filt3, filt4, filt5, filt6, gain, n=6;

			// 3 segundos

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

			gain = \gain.kr(([0.0] ! n).flat);

			sig = In.ar(in, 1);

			chain = FFT(LocalBuf(fftsize, 1), sig, hop);
			chain = PV_BinDelay(chain, maxdel, dels_buf, fb_buf, hop);
			filt1 = PV_BinFilter(chain, 40, 512);
			filt2 = PV_BinFilter(chain, 20, 40);
			filt3 = PV_BinFilter(chain, 10, 20);
			filt4 = PV_BinFilter(chain, 7, 10);
			filt5 = PV_BinFilter(chain, 3, 7);
			filt6 = PV_BinFilter(chain, 0, 3);

			sig =  [IFFT(filt1), IFFT(filt2), IFFT(filt3), IFFT(filt4), IFFT(filt5), IFFT(filt6)]*gain.dbamp.lag;

			sig = sig * envgate * envpause * amp.dbamp.lag;
			XOut.ar(out, cross, sig); //salida directa a un bus

		}).load;
	}

	// SpecDel.def

	*metadata
	{
		^(metadata: (
			synthdefname: "SpecDel_MultiFilter6",
			type: \fx,
			main: \amp,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
			],
		))

	}

}

