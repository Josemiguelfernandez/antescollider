TPComb : Tmodule2 {

	*def
	{
		^SynthDef(\tpcomb, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1,
			p = 24, dec = 3, inv = 1, drv = 0, lmt = 4000, lag = 0.1, hpp = 25,lpp = 120, res = 0.1|
			var sig, envgate, envpause, cross, org;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			lmt = lmt.lag;
			drv	= drv.dbamp.lag;
			sig	= sig * drv;
			org = sig;

			sig		= LPF.ar(org, lmt);

			sig     = PolSat.ar(sig);
			sig 	= CombC.ar(sig, 1, p.midicps.reciprocal,inv * dec, mul:0.5);
			sig     = HyperSat.ar(sig);
			sig		= sig * drv.max(1).reciprocal.sqrt;
			sig     = sig + HPF.ar(org, lmt.lag); //put original unaffected components back
			sig     = SoftClipper4.ar(sig);
			//sig 	= BMoog.ar(sig, lpp.midicps.lag(lag), res, 0, mul:0.25);
			sig		= LeakDC.ar(sig);
			sig		= HPF.ar(sig, hpp.lag(lag).midicps);
			sig     = Limiter.ar(sig, 0.8);
			sig     = BLowPass4.ar(sig, lpp.midicps.lag(lag), res);



			sig = sig * envgate * envpause * amp.dbamp.lag;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}



	*metadata
	{
		^(metadata: (
			synthdefname: "tpcomb",
			type: \fx,
			main: \p,
			sliders: [

				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\p -> ControlSpec(0, 100, \lin, 0.1, 24, \Midi),
				\dec -> ControlSpec(0.01, 10, 'exp', 0, 3, \s),
				\drv -> ControlSpec(-12, 12, 'lin', 0.1, 0, \dB),
				\lmt -> ControlSpec(1000, 15000, 'exp', 1, 4000, \Hz),
				\lpp -> ControlSpec(20, 130, 'lin', 1, 120, \Midi),
				\lag -> ControlSpec(0.01, 10, 'exp', 0.01, 0.1, \s),
				\hpp -> ControlSpec(18, 120, 'lin', 0.1, 25, \Midi),
				\res -> ControlSpec(0.01, 0.99, 'exp', 0.01, 0.1, \amt)
			],
		))
	}
}

