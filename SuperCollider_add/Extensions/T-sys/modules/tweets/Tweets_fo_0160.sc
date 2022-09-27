Tweets_fo_0160 : Tmodule2 {

	*def
	{


	^SynthDef(\tweets_fo_0160, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, f1 = 50, f2 = 99, p1 = 0.4, p2 = 3, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b, c;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			a=SinOscFB;
			c=a.ar([f1,f2],p1);
			RecordBuf.ar(InFeedback.ar(0,2)+c/p2,b=LocalBuf(8e4,2).clear);

			sig = BufRd.ar(2,b,a.ar(c)*6e4);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tweets_fo_0160",
			type: \gen,
			main: \f1,
			sliders: [
				\f1 -> ControlSpec(1, 1000, \lin, 0.1, 50,\f1),
				\f2 -> ControlSpec(1.0, 1000, \lin, 0.1, 99,\f2),
				\p1 -> ControlSpec(0.1, 100, \lin, 0.1, 0.4,\p1),
				\p2 -> ControlSpec(1.0, 100, \lin, 0.1, 3,\p2),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}






// play{
// 	a=SinOscFB;
// 	c=a.ar([50,99],0.4);
// 	RecordBuf.ar(InFeedback.ar(0,2)+c/3,b=LocalBuf(8e4,2).clear);
// BufRd.ar(2,b,a.ar(c)*6e4)}// #SuperCollider



