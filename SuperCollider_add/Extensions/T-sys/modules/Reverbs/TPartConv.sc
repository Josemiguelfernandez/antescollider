TPartConv : Tmodule2 {


	*def
	{
		^SynthDef(\TPartConv, {|in = 0, inbus, in_amp = 0, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1, convbuf = -1|

			var sig, envgate, envpause, cross, fftsize, irspectrum, bufsize;

			fftsize = 2048;
			// bufsize= PartConv.calcBufSize(fftsize, convbuf);
			// irspectrum = Buffer.alloc(Server.default, bufsize, 1);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = (In.ar(in, 1) * in_amp.dbamp.lag) + (In.ar(inbus, 1) * ingain);
			sig = PartConv.ar(sig, fftsize, convbuf)* amp.dbamp.lag;
			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;


	}



	*metadata
	{
		^(metadata: (
			synthdefname: "TPartConv",
			type: \conv,
			main: \in_amp,
			sliders: [
				\in_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			soundfileviewconv: [
				\conview -> "soundview"
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				\env -> [\menu, \env, { ~envs[\envitems.item].plot(minval: 0, maxval: 1)}]
			],
			menu: [
				\envitems -> [\envbuf, [-1,0,1, 2, 3, 4, 5, 6, 7]],

			]

		))

	}

}

// Synth(\partConv_1)