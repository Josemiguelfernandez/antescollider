TNCabinet : Tmodule2 {

	*def
	{
		^SynthDef(\tnCabinet, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1,
			hpp = 19.36,drv = 0,
			lpp = 59.21, tlt = -12, lag = 0.1, res = 0.01, lmt = 10000
			|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			drv = Lag.kr(drv.dbamp, lag);
	sig = sig * drv;
	sig = BHiPass.ar(sig,hpp.midicps.lag(lag), 1-res);
	sig = BLowPass4.ar(sig, lmt.lag(lag), 1);
	tlt = tlt.lag(lag);
	sig = BLowShelf.ar(sig, 1200, 1, tlt);
	sig = HyperSat4.ar(sig, (tlt * 0.5).dbamp);
	sig = BLowShelf.ar(sig, 1200, 1, tlt.neg);
	lpp = lpp.midicps.lag(lag);
	sig = BLowPass.ar(sig, lpp, 1-res);
	sig = BLowPass.ar(sig, lpp * 1.33, 1-res);
	sig = sig * drv.reciprocal.sqrt;


			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: \tnCabinet,
			type: \fx,
			main: \drv,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\lmt -> ControlSpec(2000, 16000, \exp, 1, 3500, \Hz),
				\res -> ControlSpec(0.01, 0.99, \exp, 0, 0.1, \amnt),
				\lag -> ControlSpec(0.01, 1, \exp, 0, 0.1, \s),
				\lpp -> ControlSpec(59.21, 131, \lin, 0, 100, \midi),
				\hpp -> ControlSpec(19.36, 99.073, \lin, 0, 50, \midi),
				\drv -> ControlSpec(-12, 50, \lin, 1, 0),
				\tlt -> ControlSpec(-50, 50, 'lin', 0.25, -12, \dB)
			],
		))
	}
}

