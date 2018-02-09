TPlaybufP : Tmodule2 {


	*def
	{
		^SynthDef(\TPlaybufP, { |out, outbus, outgain = -120, buf, rev = 1, envbuf = -1, amp = 0, t_trig = 1, matrix_ramp = 0.01, gate = 1, free = 1,

			shft = 0, dir = 1, loop = 1, lag = 0.1, off = 0, dirP = 1,
				eq = 0.5,fT = 0.05, trF = 20, trJ = 0.1, spd = 1, prb = 1, lpp = 130

			|

			var trigRate2, envgate, envpause;

			var sig, rate, lFactor, pos, timescale;
				var trig, trigdiv, env, offset;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			//sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate * rev, t_trig, BufFrames.kr(buf) * pos, loop: loop, doneAction:1);



				rate = 2.pow(Lag.kr(shft, lag)/12) * BufRateScale.kr(buf) * dir;

				//trF  = Geiger1.kr(1, 0.25, jit * trF, trF);
				trF = trF.midicps;
				trF = trF * Geiger1.kr(0.25*trF, 0.5, 10, trJ, 1);
				trig = CoinGate.ar(prb, Impulse.ar(trF.lag(lag)));
				trigdiv = PulseDivider.ar(trig, 2, [0,1]); // divide triggers over 2 channels
				//env = Lag.ar(ToggleFF.ar(trig), fT * trF.reciprocal); // one signal to contol which channel is heard
				env = LPF.ar(ToggleFF.ar(trig), (trF * 2.pow(fT/12)).min(16000).lag(lag));
				offset = Phasor.ar(t_trig, dirP * spd * BufRateScale.kr(buf), off*BufFrames.kr(buf), BufFrames.kr(buf), 0);
				sig = PlayBuf.ar(1,buf,rate, trigdiv, offset, loop, 1);
				sig = SelectX.ar( env, sig);

				sig = P2Shelf.ar(sig, eq.lag(lag));
				sig = BLowPass4.ar(sig, (lpp-shft).min(132).midicps.lag);

				//pos			= Phasor.ar(1, rate, off*BufFrames.kr(bufnum),  BufFrames.kr(bufnum), 0);
				//lFactor	= BufFrames.kr(buf).reciprocal;
				//SendTrig.kr(Impulse.kr(10), 0, offset * lFactor);





			sig = sig * envgate * envpause * amp.dbamp.lag;

			Out.ar(out, sig*amp.dbamp);
			Out.ar(outbus, sig*outgain.dbamp.lag)
		}).load;


	}


	*metadata
	{
	 ^(metadata: (
			synthdefname: "TPlaybufP",
			type: \gen,
			main: \spd,
			sliders: [


				\lag  -> ControlSpec(0.01, 1, \exp, 0, 0.1, \s),
				"eq"  -> ControlSpec(0, 1, \lin, 0, 0.5, \bal),
				"shft"-> ControlSpec(-24, 24, \lin, 0, 0, \semitones),
				\spd -> ControlSpec(0.01, 32, 'exp', default:1),
				"trF" -> ControlSpec(-60, 100, 'lin',1, default:20),
				"fT"  -> ControlSpec(-72, 48, 'lin',1, default:0),
				"off"  -> ControlSpec(0,1, 'lin', default:0),
				"trJ"  -> ControlSpec(0,1, 2, default:0.1),

				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			],
			soundfileview: [
				\playbufview -> "soundview1"
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				\play -> [[["Play", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \gate, 1, \gate, 0],
				\loop -> [[["Loop", Color.black, Color.green(0.7)], ["Loop", Color.white, Color.red(0.7)]], \loop, 1, \loop, 0],

				\dir -> [[["normal", Color.black, Color.rand], ["rev", Color.white, Color.rand]], \dir, 0, \dir, 1],
				\dirP -> [[["normal-P", Color.black, Color.rand], ["rev-P", Color.white, Color.rand]], \dirP, 0, \dirP, 1],
			],


		))

	}

}



/*// Server.default.options.maxSynthDefs = 2048
// note: not *that* columbia, the first one
b = Buffer.read(s, Platform.resourceDir +/+ "sounds/a11wlk01.wav"); // remember to free the buffer later.

SynthDef(\help_PlayBuf, {| out = 0, bufnum = 0, t_trig = 1, loop = 0 |
    Out.ar(out,
        PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum), t_trig, BufFrames.kr(bufnum) * 0, loop: loop)
    )
}).load;

a = Synth(\play_buf1, [\out, 0, \buf, b])
a = Synth(\help_PlayBuf, [\out, 0, \bufnum, b])
a.set(\t_trig, 1, \loop, 1 )
a.set(\loop, 0)*/
