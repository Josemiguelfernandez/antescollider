PDhomontDl : Tmodule2 { //PDhomontDl Constatin Popp

	*def
	{


	^SynthDef(\PDdhomontDl, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, th = 0.05, md = 1,tbus = 100, lag = 1, jitF = 1, dl = 0.01, dlJ = 0.25, hpp = 20, lpp = 135, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, chain, index, trig;


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			trig 	= Coyote.kr( sig, thresh:th, minDur:md );

			dl  = dl * Rand(0.25,1);//Array.rand(1, 0.25, 1.0);
			dl  = Ramp.ar(dl +
				LFBrownNoise0.ar(jitF, dlJ / 4, 0.01, dlJ),
				jitF.reciprocal
			);
			sig = DelayL.ar( sig, 4, dl.abs);

			index 	=  Lag.kr(TRand.kr(0, sig.size, trig), lag);

			sig		= SelectX.ar(index, sig);
			sig		= HPF.ar(sig, Lag.kr(hpp.midicps, lag));
			sig		= BLowPass.ar(sig, Lag.kr(lpp.midicps, lag));


			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "PDdhomontDl",
			type: \fx,
			main: \jitF,
			sliders: [
				\lag -> ControlSpec(0,10, \lin, 0.001, 0.05, \lag), //interpolation time
				\jitF -> ControlSpec(0.01, 20,\exp, 0.001,1,\jitF), //jitter frequency
				\md -> ControlSpec(0.001,3,\exp, 0.001,1,\md), //minimum trigger duration
				\dlJ -> ControlSpec(0, 4,\lin, 0.001,0.24,\dlJ), //delay jitter in secs
				\hpp -> ControlSpec(7, 135,\lin, 0.001,15,\hpp), //high pass filter (midi notes)
				\lpp -> ControlSpec(7, 135,\lin, 0.001,130,\lpp), //low pass filter (midi notes)
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			]
		))

	}

}

