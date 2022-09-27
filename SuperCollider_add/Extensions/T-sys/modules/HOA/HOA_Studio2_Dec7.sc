HOA_Studio2_Dec7 : Tmodule2  {

	*def
	{
		^SynthDef(\HOA_Studio2_Dec7, {|in, out, gatee = 1, amp = 0, lag = 0.2, matrix_ramp = 0.05,  in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1, xFade = 1, buf = 172, azi = 0, ele = 1, spr = 0, freeroom = 0.5, freedamp = 0.5, freemul = 1, pitch = 0, roll = 0, yaw = 0|
			var sig, envgate, envpause, cross, convsig, b2a, a2b;

			sig = In.ar(in, 64);
			sig = sig * amp.dbamp.lag(1);

			// sig = HOATransRotateXYZ.ar(6, sig, pitch, roll, yaw);

			sig = HOADecStudio2.ar(7, sig, 0);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			Out.ar(out, sig*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14)); //salida directa a un bus

		}).load;

	}

	*metadata
	{
		/*		var buffnum;
		buffnum = ~wave_buff[\vbap].bufnum; // VBAP Speaker conf.*/
		^(metadata: (
			synthdefname: "HOA_Studio2_Dec7",
			type: \fx,
			main: \azi,
			sliders:[
				\freeroom -> ControlSpec(0.0, 1.0,\lin, 0.001,0.5,\freeroom),
				\freedamp -> ControlSpec(0, 1,\lin, 0.001,0.5,\freedamp),
				\freemul -> ControlSpec(0, 1,\lin, 0.001, 1.0,\freemul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			]
		))

	}
}

