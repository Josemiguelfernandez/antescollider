TVbapKubusZig : Tmodule2  {


	*def
	{


		^SynthDef(\TVbapKubusZig, {|in, out = 0, outbus, outgain = -120, gatee = 1, amp = 0, lag = 0.2, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1, buf = 175, azig = 0.5, elemod = 3, spr = 0|
			var sig, envgate, envpause, cross;

			sig = In.ar(in, 1);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			// sig = PanAz.ar(8, sig, pos.lag(lag), 1, width, orientation);
			// sig =  VBAP.ar(16, sig, buf, azi.lag(lag), ele.lag(lag), spr);
			sig = VBAP.ar(43, sig, buf, LFSaw.kr(azig, 0).range(-180, 180) * -1, SinOsc.kr(elemod, 0).range(0, 90), spr);

			sig = sig * envgate * envpause;
			sig = sig * amp.dbamp.lag(1);

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar

		}).load;

	}

	*metadata
	{
		var buffnum;
		buffnum = ~wave_buff[\vbapkubus].bufnum; // VBAP Speaker conf.
		^(metadata: (
			synthdefname: "TVbapKubusZig",
			type: \fx,
			main: \azig,
			sliders:[
				\azig -> ControlSpec(0, 30, \lin, 0.001, 0.5, \azig),
				\elemod -> ControlSpec(0, 30, \lin, 0.001, 3, \ele),
				\spr -> ControlSpec(0, 100, \lin, 0.1, 0, \spr),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag),
				\buf -> ControlSpec(0, 1000, \lin, 1, buffnum, \buf),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			]
		))

	}
}
