PlayHybrid : Tmodule2 {


	*def
	{
		^SynthDef(\PlayHybrid, { |out = 0, buf, envbuf,  fftRecordBuffer, pos,  loop = 0, fftSize=4096, window=1, doneAction = 2, rate = 1, windowSize = 0.11,  overlaps = 8, windowRandRatio = 0.1, fftScale = 1.0,  interp = 2, amp=0, matrix_ramp = 0.01, gate = 1, free = 1, warp_amp = 0, fftStretch_amp = 0|
			var sig, envgate, envpause, warp, fftStretch, chain;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			warp = Warp1.ar(
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
			fftStretch = 0.0;
			chain = PV_BufRd(LocalBuf.new(fftSize), fftRecordBuffer, pos);
			fftStretch = IFFT.ar(chain) * fftScale;

			sig = ((warp* warp_amp.dbamp.lag) + (fftStretch* fftStretch_amp.dbamp.lag)) * envgate * envpause * amp.dbamp.lag;

			Out.ar(out, sig);
		}).load;
	}

	// chain = PV_PlayBuf(bufnum, recBuf, MouseX.kr(-1, 1), 50, 1);

	*metadata
	{
		^(metadata: (
			synthdefname: "PlayHybrid",
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



