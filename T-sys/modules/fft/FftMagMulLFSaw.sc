FftMagMulLFSaw : Tmodule2 {

	*def
	{


		^SynthDef(\fftMagMulLFSaw, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, stretch = 1, shift = 0, interp = 0, amp = 0, winsize = 0.1, matrix_ramp = 0.01, gate = 1, free = 1, lfsaw_freq = 100, lfsaw_amp = 0.2|
			var sig, envgate, envpause, cross, chain, fftsize, inA, inB, chainA, chainB;
			fftsize = 2048;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			inA = LFSaw.ar(lfsaw_freq, 0, lfsaw_amp);

			inB = In.ar(in, 1);// + (In.ar(inbus, 1) * ingain);

			chainA = FFT(LocalBuf(fftsize), inA);
			chainB = FFT(LocalBuf(fftsize), inB);

			sig = PV_MagMul(chainA, chainB); // writes into bufferA

			sig = 0.1 * IFFT(sig);


			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "fftMagMulLFSaw",
			type: \fx,
			main: \lfsaw_freq,
			sliders: [
				\lfsaw_freq -> ControlSpec(0.001, 5000,\exp,0.001, 100,\lfsaw_freq),
				\lfsaw_amp -> ControlSpec(0, 1,\lin,0.001,0.2,\lfsaw_amp),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			/*buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
			\trig -> [\t_trig]]*/
		))

	}

}

/*(
{ var inA, chainA, inB, chainB, chain;
	inA = LFSaw.ar(100, 0, 0.2);
	inB = PlayBuf.ar(1, d, BufRateScale.kr(d), loop: 1);
	chainA = FFT(LocalBuf(2048), inA);
	chainB = FFT(LocalBuf(2048), inB);
	chain = PV_MagMul(chainA, chainB); // writes into bufferA
	0.1 * IFFT(chain);
}.play;
)*/
