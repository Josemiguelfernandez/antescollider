SpecFilt : Tmodule2 {

	classvar n = 2048; //numero de componentes fftsize/2
	classvar filt_buf_num = 52;
	// : TEffectModule
	*def
	{
		^SynthDef(\SpecFilt, {|in = 0, ingain = 0, out = -1, outbus = -2, transp = 1.0, amp = 0, gate = 1, free = 1, matrix_ramp = 0.01, xFade = 1, filt_buf=52, fftsize = 1024|

			var sig, offset, envgate, envpause, cross, chain;
			// fftsize = 2048;
			// maxdel = 3; // 3 segundos

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1); // + (In.ar(inbus, 1) * ingain);

			chain = FFT(LocalBuf(fftsize, 1).clear, sig, 0.25);
			chain = PV_MagMul(chain, filt_buf);
			sig = IFFT(chain);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain); //salida a un bus auxiliar


		}).load;
	}

	// SpecFilt.def


	*metadata
	{
		^(metadata: (
			synthdefname: "SpecFilt",
			type: \fx,
			main: \amp,
			sliders: [
				\filt_buf -> ControlSpec(filt_buf_num, 100, \lin, 1, filt_buf_num, \filt_buf),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
			],
			multisliderbuf: [
				\filt -> [ControlSpec(0, 1, \lin, 0.001, 0, \filt), n/2, filt_buf_num, \mid_buf] //Spec, size, buf_number, if \mid_buf write half buff for fft_buf like fft filter
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\filt -> [\multislidersbuf, \filt,{ rand(1.0) }, n/2]

			]
		))

	}

}



/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/