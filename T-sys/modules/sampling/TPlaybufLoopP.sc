TPlaybufLoopP : Tmodule2 {


	*def
	{
		^SynthDef(\TPlaybufLoopP, { |out, outbus, outgain = -120, buf, rev = 1, envbuf = -1,dur = 3000, amp = 0, matrix_ramp = 0.01, free = 1,

			shift = 0, dir = 1, loop = 1, lag = 0.1, gate = 1, off = 0, dirP = 1,
				eq = 0.5,fT = 0.05, trF = 20, trJ = 0.1, speed = 1, prb = 1, lpp = 130
			|

			var sig, envgate, envpause;
			var rate, pos, timescale;
			var trig, trigdiv, env, offset;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);


			dirP = dir;
				rate = 2.pow(Lag.kr(shift, lag)/12) * BufRateScale.kr(buf) * dir;

				//trF  = Geiger1.kr(1, 0.25, jit * trF, trF);
				trF = trF.midicps;
				trF = trF * Geiger1.kr(0.25*trF, 0.5, 10, trJ, 1);
				trig = CoinGate.ar(prb, Impulse.ar(trF.lag(lag)));
				trigdiv = PulseDivider.ar(trig, 2, [0,1]); // divide triggers over 2 channels
				//env = Lag.ar(ToggleFF.ar(trig), fT * trF.reciprocal); // one signal to contol which channel is heard
				env = LPF.ar(ToggleFF.ar(trig), (trF * 2.pow(fT/12)).min(16000).lag(lag));
				offset = Phasor.ar(0, dirP * speed * BufRateScale.kr(buf), off*BufFrames.kr(buf), BufFrames.kr(buf), 0);
				sig = PlayBuf.ar(1,buf,rate, trigdiv, offset, 1, 0); //1 = loop!
				sig = SelectX.ar( env, sig);

				sig = P2Shelf.ar(sig, eq.lag(lag));
				sig = BLowPass4.ar(sig, (lpp-shift).min(132).midicps.lag);



			sig = sig * envgate * envpause * amp.dbamp.lag;

			Out.ar(out, sig);
			Out.ar(outbus, sig*outgain.dbamp.lag);
		}).load;


	}









	*metadata
	{
	 ^(metadata: (
			synthdefname: "TPlaybufLoopP",
			type: \gen,
			main: \amp,
			sliders: [
				// \pos -> ControlSpec(0, 1, \lin, 0.001, 0, \pos),
				//\rate -> ControlSpec(-5.0, 5.0,\lin, 0.001,1.0,\rate),
				\amp   -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\speed -> ControlSpec(0.01, 32, 'exp', 0.01, 1, \rate),
				\eq    -> ControlSpec(0, 1, 'lin', 0, 0.5, \amt),
				\shift -> ControlSpec(-24, 24, 'lin', 0.01, 0, \amt),
				\trF -> ControlSpec(-60, 100, 'lin',1, default:20),

			],
			soundfileview: [
				\playbufview -> "soundview1"
			],
			buttons: [
				\play -> [[["Play", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \gate, 1, \gate, 0],
				\dir -> [[["Frwrd", Color.black, Color.green(0.7)], ["Rev", Color.white, Color.red(0.7)]], \dir, 1, \dir, -1],




				/*\loop -> [[["Loop", Color.black, Color.green(0.7)], ["Loop", Color.white, Color.red(0.7)]], \loop, 1, \loop, 0],
				\rev -> [[["norm", Color.black, Color.rand], ["rever", Color.white, Color.rand]], \rev, -1, \rev, 1],*/
			],


		))

	}

}


