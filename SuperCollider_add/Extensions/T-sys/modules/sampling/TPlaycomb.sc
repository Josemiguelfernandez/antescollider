TPlaycomb : Tmodule2 { // Constatin Popp


	*def
	{
		^SynthDef(\TPlaycomb, { |out, outbus, outgain = -120, buf, fb = 1, lpf = 2000, dl = 0.1, lag = 0.1, p = 60, eq = 0.5, shft = 0, pos = 0, dir = 1, loop = 0, amp = 0, t_trig = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, trigRate2, envgate, envpause, loc, rt;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);


			loc = LocalIn.ar(1);
			rt  = 2.pow(shft.lag(lag)/12) * BufRateScale.kr(buf) * dir;
			sig = PlayBuf.ar(1, buf, rt, t_trig,pos.min(1).max(0) * BufFrames.kr(buf), loop, doneAction:1);
			sig = P2Shelf.ar(sig, eq.lag(lag));
			sig = LeakDC.ar((sig + (loc * fb.lag(lag))));
			LocalOut.ar(DelayC.ar(Lag.ar(sig.tanh, lpf.reciprocal.lag(lag)), 1, K2A1.ar(p.midicps.reciprocal, lag)));
			sig = Limiter.ar(sig);


			sig = sig * envgate * envpause * amp.dbamp.lag;

			Out.ar(out, sig*amp.dbamp);
			Out.ar(outbus, sig*outgain.dbamp.lag)
		}).load;


	}


	*metadata
	{
		^(metadata: (
			synthdefname: "TPlaycomb",
			type: \gen,
			main: \amp,
			sliders: [
				\shft -> ControlSpec(-36,36, \lin, 0.001, 0, \shft),
				\eq -> ControlSpec(0,1, \lin, 0.001, 0.5, \eq), //"brightness. 0 = dark. 1 = bright"
				\lag -> ControlSpec(0,10, \lin, 0.001, 0.05, \lag), //interpolation time
				\p -> ControlSpec(20,130, \lin, 0.1, 60, \p), //midi-pitch
				\lpf -> ControlSpec(50,10000, \exp, 0.1, 1000, \lpf), //feedback lowpass filter
				\fb -> ControlSpec(0,1, \lin, 0.1, 1, \fb), //feedback
				\pos -> ControlSpec(0, 1, \lin, 0.001, 0, \pos),
				//\rate -> ControlSpec(0.0, 5.0,\lin, 0.001,1.0,\rate),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			],
			soundfileview: [
				\playbufview -> "soundview1"
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				\play -> [[["Play", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \t_trig, 1, \t_trig, 0],
				\loop -> [[["Loop", Color.black, Color.green(0.7)], ["Loop", Color.white, Color.red(0.7)]], \loop, 1, \loop, 0],
				\dir -> [[["norm", Color.black, Color.rand], ["rever", Color.white, Color.rand]], \dir, -1, \dir, 1],
			],


		))

	}

}
