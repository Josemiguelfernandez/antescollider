Tweets_fo_0146 : Tmodule2 {

	*def
	{


	^SynthDef(\Tweets_fo_0146, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, p1 = 45, p2 = 9, p3 = 1, p4 = 9, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b, c, l;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			a=LFTri;
			l=LocalBuf(c=99,20).clear;
			RecordBuf.ar(a.ar(c=(1..20)),l);

			sig = GVerb.ar(HPF.ar(IndexL.ar(l,a.ar(c/p1)).sum,p2)/p4,p3);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Tweets_fo_0146",
			type: \gen,
			main: \p1,
			sliders: [
				\p1 -> ControlSpec(1, 100, \lin, 0.1, 45,\p1),
				\p2 -> ControlSpec(1.0, 100, \lin, 0.1, 9,\p2),
				\p3 -> ControlSpec(0.1, 100, \lin, 0.1, 1,\p3),
				\p4 -> ControlSpec(1.0, 100, \lin, 0.1, 9,\p4),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}






/*play{
	a=LFTri;
	l=LocalBuf(c=99,20).clear;
	RecordBuf.ar(a.ar(c=(1..20)),l);
	GVerb.ar(HPF.ar(IndexL.ar(l,a.ar(c/45)).sum,9)/9,1)}// #SuperCollider*/

