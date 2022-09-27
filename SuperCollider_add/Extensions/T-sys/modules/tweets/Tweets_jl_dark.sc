Tweets_jl_dark : Tmodule2 {

	*def
	{


	^SynthDef(\tweets_jl_dark, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, f1 = 10, f2 = 99, p1 = 0.2, p2 = 70, p3 = 99,p4 = 60, p5 = 0.1, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b, c;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			a=HPF.ar(PinkNoise.ar(5e-3),f1); //*Line.kr(0,1,9);


			sig = GVerb.ar(({|i| Ringz.ar(a*LFNoise1.kr(0.05+p5.rand),f2*i+p4,p1)}!99).sum,p2,p3).tanh;
			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tweets_jl_dark",
			type: \gen,
			main: \f1,
			sliders: [
				\f1 -> ControlSpec(1, 1000, \lin, 0.1, 10,\f1),
				\f2 -> ControlSpec(1.0, 1000, \lin, 0.1, 55,\f2),
				\p1 -> ControlSpec(0.0001, 100, \lin, 0.1, 0.2,\p1),
				\p2 -> ControlSpec(1.0, 1000, \lin, 0.1, 70,\p2),
				\p3 -> ControlSpec(1.0, 100, \lin, 0.1, 99,\p3),
				\p4 -> ControlSpec(1.0, 100, \lin, 0.1, 60,\p4),
				\p5 -> ControlSpec(0.001, 100, \lin, 0.1, 0.1,\p5),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}




/*play{
	a=HPF.ar(ar(PinkNoise,5e-3),10)*Line.kr(0,1,9);
	ar(GVerb,({|i|ar(Ringz,a*LFNoise1.kr(0.05+0.1.rand),55*i+60,0.2)}!99).sum,70,99).tanh}
({|i|ar(Ringz,a*LFNoise1.kr(0.05+0.1.rand),55*i+60,0.2)}!99)*/