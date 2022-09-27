Warp1Play : Tmodule2 {


	*def
	{
		^SynthDef(\Warp1Play, { |out = 0, buf, pos, rate = 1, windowSize = 0.1, envbuf,  overlaps = 8, windowRandRatio = 0.1, interp = 2, amp=0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause;

			// env = EnvGen.ar(Env.linen(0.01, rec_dur-0.02, 0.01), 1,  doneAction: doneAction); // gate 1 env 5 ms //AGREGE donaction en 2021
			// chain = PV_PlayBuf(bufnum, buf, rate, offset, 1, loop);
			// chain = PV_BinPlayBuf(bufnum, buf, rate, offset, binStart, binSkip, numBins, loop, clear);
				envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			sig = Warp1.ar(
				numChannels: 1,
				bufnum: buf,
				pointer: pos,
				freqScale: rate,
				windowSize: windowSize,
				envbufnum: envbuf,
				overlaps: overlaps,
				windowRandRatio: windowRandRatio,
				interp:interp
			);
			sig = sig * envgate * envpause * amp.dbamp.lag;

			Out.ar(out, sig);
		}).load;
	}

	// chain = PV_PlayBuf(bufnum, recBuf, MouseX.kr(-1, 1), 50, 1);

	*metadata
	{
		^(metadata: (
			synthdefname: "Warp1Play",
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



