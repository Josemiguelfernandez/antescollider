Tweets_fo_0143_mono : Tmodule2 {

	*def
	{


	^SynthDef(\Tweets_fo_0143_mono, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, p1 = 8, p2 = 19.2, p3 = 0.111, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b, l;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			l=LocalBuf(b=1e4,2);
			{|i|BufWr.ar(a=LFTri.ar(i+1*[p1,p2]),l,a/[i+1]*b)}!3;

			sig = LPF.ar(PlayBuf.ar(2,l,p3,1,0,1).clip2,b);
			sig = Mix.ar(sig);
			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Tweets_fo_0143_mono",
			type: \gen,
			main: \p1,
			sliders: [
				\p1 -> ControlSpec(0.1, 100, \lin, 0, 8,\p1),
				\p2 -> ControlSpec(1.0, 100.0, \lin, 0.01, 19.2,\p2),
				\p3 -> ControlSpec(0.0001, 10, \lin, 0.001, 0.111,\p3),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}





/*	l=LocalBuf(b=1e4,2);
	{|i|BufWr.ar(a=LFTri.ar(i+1*[8,19.2]),l,a/[i+1]*b)}!3;
	LPF.ar(PlayBuf.ar(2,l,1/9,1,0,1).clip2,b)/2// #SuperCollider*/
