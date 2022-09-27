TRecBuff : Tmodule2 {


	*def
	{
		^SynthDef(\recbuff, { |in = 0, buf, rec_dur = 1|

			var sig, input, impulso, posDevia, pos, trigRate2, envgate, envpause, env;
			// = trigRate + WhiteNoise.kr(trigRateDev);
			// envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			// envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			sig = SoundIn.ar(in, 1);

			env = EnvGen.ar(Env.linen(0.01, rec_dur, 0.01), 1,  doneAction: 2); // gate 1 env 5 ms //AGREGE donaction en 2021

			RecordBuf.ar(sig, buf.clear, doneAction: 2, loop: 0); //, trigger: buf_trig
			//Out.ar(1, sig);

		}).load;
	}


	*metadata
	{
	 ^(metadata: (
			synthdefname: "recbuff",
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



