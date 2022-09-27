Tweets_fo_0149 : Tmodule2 {

	*def
	{


	^SynthDef(\tweets_fo_0149, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, p1 = 99, p2 = 4, p3 = 1.2, p4 = 9, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, f, e, l;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			f=LFTri.ar(_);
			e=f*4e3+4e3*f.(p3).abs;
			BufWr.ar(f.([3,4]),l=LocalBuf(8e3,2).clear,e.(1/9));

			sig = COsc.ar(l,p1.lag,f.(1/[7,8]))/p2;

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tweets_fo_0149",
			type: \gen,
			main: \p1,
			sliders: [
				\p1 -> ControlSpec(1, 1000, \lin, 0.1, 99,\p1),
				\p2 -> ControlSpec(1.0, 100, \lin, 0.1, 4,\p2),
				\p3 -> ControlSpec(0.1, 100, \lin, 0.1, 1.2,\p3),
				\p4 -> ControlSpec(1.0, 100, \lin, 0.1, 9,\p4),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}






/*play{
	f=LFTri.ar(_);
	e=f*4e3+4e3*f.(1.2).abs;
	BufWr.ar(f.([3,4]),l=LocalBuf(8e3,2).clear,e.(1/9));
	COsc.ar(l,99,f.(1/[7,8]))/4}// #SuperCollider*/


