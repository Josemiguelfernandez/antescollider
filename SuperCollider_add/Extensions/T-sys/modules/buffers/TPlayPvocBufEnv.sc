TPlayPvocBufEnv : Tmodule2 {


	*def
	{
		^SynthDef(\TPlayPvocBufEnv, { |out = 0, buf, loop = 1, fftSize=2048, window=1, doneAction = 2, rate=1, offset = 0, envbuf, env_dur|
			var env, chain, bufnum;

			bufnum = LocalBuf.new(fftSize);

			env = PlayBuf.ar(1, envbuf, ((SampleRate.ir/BufFrames.kr(envbuf)).reciprocal*env_dur.reciprocal)*rate, 1, loop: loop); //BufRateScale.kr(envbuf) *
			chain = PV_PlayBuf(bufnum, buf, rate, offset, loop);
			chain = IFFT(chain, window)*env;

			Out.ar(out, chain);
		}).load;
	}

    // chain = PV_PlayBuf(bufnum, recBuf, MouseX.kr(-1, 1), 50, 1);

	*metadata
	{
	 ^(metadata: (
			synthdefname: "TPlayPvocBufEnv",
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
