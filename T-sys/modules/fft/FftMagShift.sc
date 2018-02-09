FftMagShift : Tmodule2 {

	*def
	{


	^SynthDef(\fftMagShift, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, stretch = 1, shift = 0, interp = 0, amp = 0, winsize = 0.1, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, chain, fftsize;
			fftsize = 2048;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			chain = FFT(LocalBuf(fftsize, 1).clear, sig);
			chain = PV_MagShift(chain, stretch, shift);
			sig = IFFT(chain).dup;


			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "fftMagShift",
			type: \fx,
			main: \stretch,
			sliders: [
				\stretch -> ControlSpec(0.001, 8,\exp,0.001,0,\stretch),
				\shift -> ControlSpec(-128, 128,\lin,0.001,0.2,\shift),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			/*buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\trig -> [\t_trig]]*/
		))

	}

}

