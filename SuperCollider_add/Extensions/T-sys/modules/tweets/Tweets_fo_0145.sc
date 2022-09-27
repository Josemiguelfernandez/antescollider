Tweets_fo_0145 : Tmodule2 {

	*def
	{


	^SynthDef(\tweets_fo_0145, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, p1 = 3.5, p2 = 9, p3 = 99, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b, c, l;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			a=LFTri;
			l=LocalBuf(b=600,9).clear;
			BufWr.ar(a.ar(c=(3..11)*p1),l,a.ar(p2/c,c/p3)*b);

			sig = Splay.ar(PlayBuf.ar(9,l,loop:1)/2);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tweets_fo_0145",
			type: \gen,
			main: \p1,
			sliders: [
				\p1 -> ControlSpec(0.1, 100, \lin, 0.1, 3.5,\p1),
				\p2 -> ControlSpec(1.0, 100, \lin, 0.1, 9,\p2),
				\p3 -> ControlSpec(0.1, 100, \lin, 0.1, 99,\p3),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}







/*play{
	a=LFTri;
	l=LocalBuf(b=600,9).clear;
	BufWr.ar(a.ar(c=(3..11)*3.5),l,a.ar(9/c,c/99)*b);
	Splay.ar(PlayBuf.ar(9,l,loop:1)/2)}// #SuperCollider*/
