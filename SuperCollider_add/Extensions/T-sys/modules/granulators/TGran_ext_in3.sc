TGran_ext_in3 : Tmodule2 {


	*def
	{
		^SynthDef(\granEnv_ext_in3, { |in = 0, inbus, ingain = 0, outgain = 0 out = 0, outbus = -2, t_trig = 0, trigRate = 1, trigRateDev = 0, buf, envbuf = -1, buf_trig = 1, rate = 1.0, rateDev = 0, startPos= 0.5, centerDev = 0, dur = 0.2, durDev = 0, pan = 0, panDev = 0, amp= -10, ampDev = 0, interpol = 2, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, input, impulso, posDevia, pos, trigRate2, envgate, envpause;
			// = trigRate + WhiteNoise.kr(trigRateDev);
			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			//sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			// trigRate2 = Impulse.ar(trigRate + WhiteNoise.kr(trigRateDev));

			amp = amp.dbamp;
			// posDevia = WhiteNoise.kr(centerDev);
			// pos = posDevia + startPos; //(startPos / BufSamples.kr(buf));
			startPos = startPos + WhiteNoise.kr(centerDev);
			rate = rate + WhiteNoise.kr(rateDev);
			dur = dur + WhiteNoise.kr(durDev).abs;

			// pan = pan + WhiteNoise.kr(panDev); //ver clip
			amp = amp + WhiteNoise.kr(ampDev);
			// RecordBuf.ar(sig, buf.clear, loop: 0, trigger: buf_trig);
			// sig = GrainBuf.ar(6, t_trig, (dur / BufSampleRate.kr(buf)), buf, rate, pos, interpol, pan , envbuf);
			sig = GrainBuf.ar(8, t_trig, dur, buf, rate, startPos, interpol, pan ,envbuf);

			sig = sig * envgate * envpause;

			Out.ar(out, sig*amp.lag(1));
			// Out.ar(outbus, sig*amp.lag(1))
		}).load;
	}


	*metadata
	{
	 ^(metadata: (
			synthdefname: "granEnv_ext_in3",
			type: \gen,
			main: \trigRate,
			sliders: [
					\trigRate -> ControlSpec(0.1, 500, \lin, 0.1, 20, \trigRate),
					\trigRateDev -> ControlSpec(0, 1000.0, \lin,0, 0.001,\trigRateDev),
					\startPos -> ControlSpec(0, 1, \lin,0.001, 0.1,\startPos),
					\centerDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\centerDev),
					\dur -> ControlSpec(0.001, 1.0,\lin,0.001,0.001,\dur),
					\durDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\durDev),
					\rate -> ControlSpec(-10.0, 10.0,\lin, 0.001,1.0,\rate),
					\rateDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\rateDev),
					\pan -> ControlSpec(-1, 1, \lin, 0, 0, \pan),
					\panDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\panDev),
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



