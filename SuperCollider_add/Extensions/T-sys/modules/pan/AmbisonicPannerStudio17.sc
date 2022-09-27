AmbiPannerStudio1 : Tmodule2  {

	*def
	{

		^SynthDef(\AmbiPannerStudio1, {|in, out = 0, outbus, outgain = -120, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, rad = 1, azi = 0, ele = 0, spr = 0, width = 60, vbapBuf, dopon = 0, dopamnt = 0, lag = 0, matrix_ramp = 0, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross, minRadRoot, radRoot, radio;

			// azi = azi.circleRamp;
			minRadRoot = 0.076923076923077; // from Mosca spatializer
			radio = rad.linlin(0, 10, 0, 1);
			radRoot = radio.sqrt.clip(minRadRoot, 1);

			sig = In.ar(in, 1);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;
/*			sig = LPF.ar(sig, (1 -  radio)* 18000 + 2000);
			sig = Select.ar(dopon, [sig, DelayC.ar(sig, 0.2, radio/1640.0 * dopamnt)]);*/
			// sig = VBAP.ar(4, sig*(1 - radRoot), vbapBuf, azi, ele, spr);

			sig = AmbisonicPannerStudio17.ar(sig, 0, rad, azi, ele);  // order 7

			sig = sig * envgate * envpause;
			sig = sig * amp.dbamp.lag(1);

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "AmbiPannerStudio1",
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
/*~minRadRoot = 0.076923076923077; // from Mosca spatializer
 ~rad = 9.linlin(0, 10, 0, 1);
~radRoot = ~rad.sqrt.clip(~minRadRoot, 1);
(1 - ~radRoot)*/

// minRadRoot = 0.076923076923077; // from Mosca spatializer
// ~rad = 0.9;
// ~radRoot = ~rad.sqrt.clip(~minRadRoot, 1);
//
// (1 / ~radRoot - 1)
//
// (1 -  ~rad)* 18000 + 2000
//
// distFilter = { | input, intens | // attenuate high freq with intens
// 	LPF.ar(input, (1 - intens) * 18000 + 2000);
// };
