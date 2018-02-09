SpecDel : Tmodule2 {

	classvar n = 1024; //numero de componentes
	classvar maxdel = 3;
	// : TEffectModule
	*def
	{
		^SynthDef(\specDel, {|in = 0, ingain = 0, out = -1, outbus = -2, transp = 1.0, amp = 0, gate = 1, free = 1, matrix_ramp = 0.01, xFade = 1, dels_buf=92 fb_buf=93|

			var sig, offset, envgate, envpause, cross, chain, fftsize, dels, fb;
			fftsize = 2048;
			// maxdel = 3; // 3 segundos

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

/*			dels = \dels.kr(Array.fill(n, { 0 }));
			fb = \fb.kr(Array.fill(n, { 0 }));*/

/*			dels_buf = LocalBuf(fftsize/2).set(dels*maxdel);
			fb_buf = LocalBuf(fftsize/2).set(fb);*/

			sig = In.ar(in, 1); // + (In.ar(inbus, 1) * ingain);

			chain = FFT(LocalBuf(fftsize, 1).clear, sig, 0.25);
			chain = PV_BinDelay(chain, maxdel, dels_buf, fb_buf, 0.25);
			sig = IFFT(chain);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain); //salida a un bus auxiliar


		}).load;
	}

	// SpecDel.def

	*metadata
	{
		^(metadata: (
			synthdefname: "specDel",
			type: \fx,
			main: \amp,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
			],
			multisliderbuf: [
				\dels -> [ControlSpec(0, maxdel, \lin, 0.001, 0, \dels), n, 92],
				\fb -> [ControlSpec(0, 1, \lin, 0.001, 0, \fb), n, 93]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\dels -> [\multislidersbuf, \dels,{ rand(1.0) }, n],
				\fb -> [\multislidersbuf, \fb, { rand(1.0) }, n],

			]
		))

	}

}



/*a = Synth(\adict_20_8)
a.set(\amps, [0, 1,0,0,0,0,0,0,0,1,0,0,0,0,0,0,0,1,0,0])
a.set(\gate, 0)*/