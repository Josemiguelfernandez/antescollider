FftConformalMap : Tmodule2 {

	*def
	{


	^SynthDef(\fftConformalMap, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, areal = 0, aimag = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, chain, fftsize;
			fftsize = 2048;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			chain = FFT(LocalBuf(fftsize, 1).clear, sig);
			chain = PV_ConformalMap(chain, areal, aimag);
			sig = IFFT(chain);


			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "fftConformalMap",
			type: \fx,
			main: \areal,
			sliders: [
				\areal -> ControlSpec(0.01,2.0,\exp, 0.001,0,\areal),
				\aimag -> ControlSpec(0.01,10.0,\exp, 0.001,0,\aimag),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			]
		))

	}

}

