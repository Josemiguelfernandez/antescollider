TPlayEigenMikeEnc3 : Tmodule2 {


	*def
	{
		^SynthDef(\TPlayEigenMikeEnc3, { |globTBus, buf, rev = 1, envbuf = -1, rate = 1.0, pos = 0, loop = 0, amp = 0, t_trig = 0, matrix_ramp = 0.01, gate = 1, free = 1, gatee = 1, in_ramp = 0.01, out_ramp = 0.01|

			var sig, envgate, envpause, hoa_encoder;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			sig = PlayBuf.ar(32, buf, BufRateScale.kr(buf) * rate * rev, t_trig, BufFrames.kr(buf) * pos, loop: loop, doneAction:2); //este libera synth para performance


			sig = sig * envgate * envpause * amp.dbamp.lag;

			hoa_encoder = HOAEncEigenMike.ar(3, sig);

			hoa_encoder = hoa_encoder*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14);


			// Out.ar(gbfbus, (hoa_encoder*globallev) + (hoa_encoder*locallev));
			Out.ar(globTBus, hoa_encoder);
			// Out.ar(outbus, sig*outgain.dbamp.lag)
		}).load;


	}


	*metadata
	{
	 ^(metadata: (
			synthdefname: "TPlayEigenMikeEnc3",
			type: \gen,
			main: \rate,
			sliders: [
				\pos -> ControlSpec(0, 1, \lin, 0.001, 0, \pos),
				\rate -> ControlSpec(0.0, 5.0,\lin, 0.001,1.0,\rate),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			],
			soundfileview: [
				\playbufview -> "soundview1"
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				\play -> [[["Play", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \t_trig, 1, \t_trig, 0],
				\loop -> [[["Loop", Color.black, Color.green(0.7)], ["Loop", Color.white, Color.red(0.7)]], \loop, 1, \loop, 0],
				\rev -> [[["norm", Color.black, Color.rand], ["rever", Color.white, Color.rand]], \rev, -1, \rev, 1],
			],


		))

	}

}

// HOAEncEigenMike.loadRadialFilters;

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
