TOSWrap8 : Tmodule2 {

	*def
	{
		^SynthDef(\tOswrap8, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1,
			lo = -50, hi = -3, drv = 0, lag = 0.1, lpp = 120, hpp = 25
			|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8) + (In.ar(inbus, 8) * ingain);

			drv = Lag.kr(drv.dbamp, lag);

			sig = sig * drv;

			sig = OSWrap4.ar(sig, lo.dbamp.lag(lag), hi.dbamp.lag(lag));
			sig = BLowPass.ar(sig, lpp.midicps.lag(lag));
			sig	= HPF.ar(sig, Lag.kr(hpp.midicps, lag));
	//sig = sig * drv.reciprocal.sqrt;


			sig = sig * envgate * envpause * amp.dbamp.lag;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: \tOswrap8,
			type: \fx,
			main: \lo,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\lo -> ControlSpec(-90, 0, \db, 0, -90, \dB),
				\hi -> ControlSpec(-90, 0, \db, 0, 0, \dB),
				\lpp -> ControlSpec(40, 131, \lin, 0, 100, \midi),
				\drv -> ControlSpec(-50, 50, \lin, 1, -12),
				\lag -> ControlSpec(0.01, 1, \exp, 0, 0.6)
			],
		))
	}
}

