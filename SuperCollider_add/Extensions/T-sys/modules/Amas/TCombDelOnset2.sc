TCombDelOnset2 : Tmodule2 {

	*def
	{


	^SynthDef(\combDelOnset2, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, amp = 0, startpos = 0, endpos = 2, decaytime = 0.5, thresh = 0.03, fastMul = 0.6,  matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, chain, bufsize, trig, trigrand, pos, dur, onsets, del;
			bufsize = 48000;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);


			onsets= Coyote.kr(sig,fastMul:fastMul, thresh: thresh); // Onser detector, probar otros!
			trig = K2A.ar(onsets);
			trigrand = TRand.ar(0, 1,trig);
			del = TRand.ar(0.05, 0.3, trig);
			// dur = del*5;

			//pos = (Sweep.ar(trig, 1/dur)).linlin(0, 1, startpos, endpos, \minmax);
			pos = TRand.ar(0.0, 2.0, trig);
			sig = PanAz.ar(7, BufCombC.ar(LocalBuf(bufsize, 1), Decay.ar(trigrand*0.001, decaytime, sig), del, 2), pos);


			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "combDelOnset2",
			type: \fx,
			main: \decaytime,
			sliders: [
				// \del -> ControlSpec(0.001, 1,\exp, 0.001,0.3,\del),
				\startpos -> ControlSpec(0, 1,\lin, 0.001,0,\startpos),
				\endpos -> ControlSpec(0, 1,\lin, 0.001,2,\endpos),
				\decaytime -> ControlSpec(0.001, 5,\exp, 0.001,0.5,\decaytime),
				\thresh -> ControlSpec(0, 1,\lin, 0.001,0.03,\thresh),
				\fastMul -> ControlSpec(0, 1,\lin, 0.001,0.6,\fastMul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			]

		))

	}

}

