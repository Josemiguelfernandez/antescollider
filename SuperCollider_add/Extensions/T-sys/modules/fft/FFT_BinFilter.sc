FFT_BinFilter : Tmodule2 {
	// : TEffectModule
	*def
	{
		^SynthDef(\FFT_BinFilter, {|in = 0, out = 0, amp = 0, gate = 1, free = 1, matrix_ramp = 0.01, xFade = 1, fftsize = 1024, start, end, hop=0.25|

			var sig, offset, envgate, envpause, cross, chain, dels, fb;

			 // 3 segundos

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);

			chain = FFT(LocalBuf(fftsize, 1), sig, hop);
			chain = PV_BinFilter(chain, start, end);
			sig = IFFT(chain);

			sig = sig * envgate * envpause * amp.dbamp.lag;
			XOut.ar(out, cross, sig); //salida directa a un bus

		}).load;
	}

	// SpecDel.def

	*metadata
	{
		^(metadata: (
			synthdefname: "FFT_BinFilter",
			type: \fx,
			main: \amp,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
			],
		))

	}

}
