Tweets_fo_0137 : Tmodule2 {

	*def
	{


	^SynthDef(\Tweets_fo_0137, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, p1 = 8, p2 = 9, p3 = 50, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			a=LFSaw;
			b=a.ar(1/64)*p1+p2;

			sig = Splay.ar({|i|a.ar(round(a.ar(i+1/32/b,i/40)+1**2*2e3+p3,p3),0,a.ar(i/16/b,i/48).min(0))}!64);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Tweets_fo_0137",
			type: \gen,
			main: \p1,
			sliders: [
				\p1 -> ControlSpec(0.1, 1000, \lin, 0, 8,\p1),
				\p2 -> ControlSpec(1, 1000, \lin, 0, 9,\p2),
				\p3 -> ControlSpec(1, 1000, \lin, 0, 50,\p3),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


