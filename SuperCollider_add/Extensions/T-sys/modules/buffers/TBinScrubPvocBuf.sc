TBinScrubPvocBuf : Tmodule2 {


	*def
	{
		^SynthDef(\TBinScrubPvocBuf, { |out = 0, buf, loop=0, fftSize=2048, window=1, doneAction=2, pos=0, binStart=0, binSkip=1, numBins=1, clear=0|
			var sig, env, chain, bufnum;

			bufnum = LocalBuf.new(fftSize);
			// env = EnvGen.ar(Env.linen(0.01, rec_dur-0.02, 0.01), 1,  doneAction: doneAction); // gate 1 env 5 ms //AGREGE donaction en 2021
			// chain = PV_PlayBuf(bufnum, buf, rate, offset, 1, loop);
			// chain = PV_BufRd(bufnum, buf, pos);
			chain = PV_BinBufRd(bufnum, buf, pos, binStart, binSkip, numBins, clear);
			Out.ar(out, IFFT(chain, window));
		}).load;
	}

	// chain = PV_PlayBuf(bufnum, recBuf, MouseX.kr(-1, 1), 50, 1);

	*metadata
	{
		^(metadata: (
			synthdefname: "TBinScrubPvocBuf",
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



