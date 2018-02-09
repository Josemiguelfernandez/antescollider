TJPverb8 : Tmodule2 {

	*def
	{



		^SynthDef(\TJPverb8, {|in = 0, inbus, ingain = 0, out = 0, outbus, amp = 0, outgain = -120, t60 = 1, damp = 0, size = 1, earlyDiff = 0.707, modDepth = 0.1, modFreq = 2, low = 1, mid = 1, high = 1, lowcut = 500, highcut = 2000, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8) + (In.ar(inbus, 8) * ingain);
			sig = JPverb.ar(sig, t60, damp, size, earlyDiff, modDepth, modFreq, low, mid, high, lowcut, highcut) *amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TJPverb8",
			type: \fx,
			main: \t60,
			sliders: [
				\t60 -> ControlSpec(0.1, 60,\exp, 0.01,1,\t60),
				\damp -> ControlSpec(0, 1,\lin, 0.01,0,\damp),
				\size -> ControlSpec(0.5, 3,\lin, 0.001,1,\size),
				\earlydiff -> ControlSpec(0, 1,\lin, 0.001,0.707,\earlydiff),
				\modDepth -> ControlSpec(0, 50,\lin, 0.001,0.1,\modDepth),
				\modFreq -> ControlSpec(0, 10,\lin, 0.001,2,\modFreq),
				\low -> ControlSpec(0, 1,\lin, 0.01,1,\low),
				\mid -> ControlSpec(0, 1,\lin, 0.01,1,\mid),
				\high -> ControlSpec(0, 1,\lin, 0.01,1,\high),
				\lowcut -> ControlSpec(100, 6000,\exp, 0.01,500,\lowcut),
				\highcut -> ControlSpec(1000, 10000,\exp, 0.01,2000,\highcut),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


