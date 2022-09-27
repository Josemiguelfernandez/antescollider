Vbap_14 : Tmodule2  {

	*def
	{

		^SynthDef(\Vbap_14, {|in, out = 0, outbus, outgain = -120, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, azi = 0, ele = 0, spr = 0, width = 60, vbapBuf, lag = 0, matrix_ramp = 0, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			azi = azi.circleRamp;
			sig = In.ar(in, 1);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = VBAP.ar(14, sig, vbapBuf, azi, ele, spr);

			sig = sig * envgate * envpause;
			sig = sig * amp.dbamp.lag(1);

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Vbap_14",
			type: \fx,
			main: \azi,
			sliders:[
				\azi -> ControlSpec(-180, 180, \lin, 0.001, 0, \azi),
				\width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\ele -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag),
				\vbapBuf -> ControlSpec(0, 1000, \lin, 1, 0, \vbapBuf),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			]
		))
	}
}
