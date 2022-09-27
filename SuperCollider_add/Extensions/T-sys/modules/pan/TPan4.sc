TPan4 : Tmodule2  {


	*def
	{


	^SynthDef(\TPan4, {|in, out = 0, outbus, outgain = -120, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, updateFreq = 10, xpos = 0, ypos = 0, lag = 0.1, matrix_ramp = 0, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			sig = In.ar(in, 1);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = Pan4.ar(sig, xpos.lag(lag), ypos.lag(lag));

			sig = sig * envgate * envpause;
			sig = sig * amp.dbamp.lag(1);

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TPan4",
			type: \fx,
			main: \pos,
			sliders:[
				\xpos -> ControlSpec(-1, 1, \lin, 0.001, 0, \xpos),
				\ypos -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \ypos),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			]
		))

	}
}

