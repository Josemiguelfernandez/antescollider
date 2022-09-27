TPlayBufEnvTrigX : Tmodule2 {


	*def
	{
		^SynthDef(\TPlayBufEnvTrigX, { |out = 0, buf, loop = 1, fftSize=2048, window=1, doneAction = 2, rate=1, offset = 0, envbuf, env_dur, impulse_rate = 1|
			var sig, env, trig;

			 trig = Impulse.kr(impulse_rate);

			// env = PlayBuf.ar(1, envbuf, ((SampleRate.ir/BufFrames.kr(envbuf)).reciprocal*env_dur.reciprocal)*rate, trig, loop: loop); //BufRateScale.kr(envbuf) *
			env = XPlayBuf.ar(1, envbuf, ((SampleRate.ir/BufFrames.kr(envbuf)).reciprocal*env_dur.reciprocal)*rate, trig, loop: loop);

			// sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate, trig, 0, loop: loop);
			sig = XPlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate, trig, 0, loop: loop);
			sig = SafetyLimiter.ar(sig);

			Out.ar(out, sig*env);
		}).load;
	}


	*metadata
	{
		^(metadata: (
			synthdefname: "TPlayBufEnvTrigX",
			type: \gen,
			main: \trigRate,
			sliders: [
				\amp -> ControlSpec(0.0001, 2, \exp, 0, 0.3, \amp), // db spec acts weird, so use self made one
				\ampDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\ampDev),
				\interpol -> ControlSpec(1, 4, \lin, 1, 4, \interpol),
			],
			soundfileview: [
				\granenview -> "soundview1"
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				\env -> [\menu, \env, { ~envs[\envitems.item].plot(minval: 0, maxval: 1)}],
				\trig -> [\t_trig]
			],
			menu: [
				\envitems -> [\envbuf, [-1,0,1, 2, 3, 4, 5, 6, 7]],

			]

		))

	}

}

// TGran_ext_in.def


// 2.reciprocal
