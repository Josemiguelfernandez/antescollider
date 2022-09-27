TPlaybufLoop : Tmodule2 {


	*def
	{
		^SynthDef(\TPlaybufLoop, { |out, outbus, outgain = -120, buf, rev = 1, envbuf = -1, rate = 1.0, pos = 0, startPos = 0, dur = 3000, loop = 0, amp = 0, matrix_ramp = 0.01, gate = 0, free = 1, ipol=2, loopRel=0|

			var sig, trigRate2, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			// sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate * rev, t_trig, BufFrames.kr(buf) * pos, loop: loop, doneAction:1);
			sig = LoopBuf.ar(1, buf, BufRateScale.kr(buf) * rate, gate+loopRel, BufFrames.kr(buf) * startPos, startPos, startPos+dur, ipol);
			// synth.set(\startPos, sel[0], \endPos, sel[0]+sel[1]);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			Out.ar(out, sig*amp.dbamp);
			Out.ar(outbus, sig*outgain.dbamp.lag)
		}).load;


	}


	*metadata
	{
	 ^(metadata: (
			synthdefname: "TPlaybufLoop",
			type: \gen,
			main: \rate,
			sliders: [
				// \pos -> ControlSpec(0, 1, \lin, 0.001, 0, \pos),
				\rate -> ControlSpec(-5.0, 5.0,\lin, 0.001,1.0,\rate),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			],
			soundfileview: [
				\playbufview -> "soundview1"
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				\play -> [[["Play", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \gate, 1, \gate, 0],
				/*\loop -> [[["Loop", Color.black, Color.green(0.7)], ["Loop", Color.white, Color.red(0.7)]], \loop, 1, \loop, 0],
				\rev -> [[["norm", Color.black, Color.rand], ["rever", Color.white, Color.rand]], \rev, -1, \rev, 1],*/
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
