TPlayBufEnvTrig2F : Tmodule2 {


	*def
	{
		^SynthDef(\TPlayBufEnvTrig2F, { |out = 0, buf, loop = 1, rate=1, envbuf, env_dur, impulse_rate = 1, freqHPF = 440, thresh = 0.5, slopeBelow = 1.0, slopeAbove = 1.0, clampTime = 0.01, relaxTime = 0.1, coef = 0.995|

			var in, sig, env, trig, comp, phasor, sr;
			sr = SampleRate.ir;

			trig = Impulse.kr(impulse_rate);
			phasor = Phasor.ar(trig, impulse_rate/sr, 0, 1);
			env = BufRd.ar(1, envbuf, phasor*BufFrames.kr(envbuf));

			// env = PlayBuf.ar(1, envbuf, ((SampleRate.ir/BufFrames.kr(envbuf)).reciprocal*env_dur.reciprocal)*rate, trig, loop: loop); //BufRateScale.kr(envbuf) *
			in = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate, trig, 0, loop: loop);
			// sig = HPF.ar(in, freqHPF.lag);
/*			comp	= Compander.ar(sig, sig, thresh, slopeBelow, slopeAbove, clampTime, relaxTime);*/
			// sig	= LeakDC.ar(sig, coef);

			Out.ar(out, in*env);
		}).load;
	}


	*metadata
	{
		^(metadata: (
			synthdefname: "TPlayBufEnvTrig2F",
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
// Synth(\TPlayBufEnvTrig2F)

// 2.reciprocal
