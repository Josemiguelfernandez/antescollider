TRecPvocBuf : Tmodule2 {


	*def
	{
		^SynthDef(\TRecPvocBuf, { |in = 0, buf, rec_dur = 1, loop = 0, fftSize=2048, hop= 0.25 window=1, doneAction = 2, fade_in = 0.01,  fade_out = 0.01, gate = 1, in_gain = 0|
			var sig, env, chain, bufnum;
			env = EnvGen.ar(Env.linen(fade_in, rec_dur-(fade_in+fade_out), fade_out), gate,  doneAction: doneAction); // gate 1 env 5 ms //AGREGE donaction en 2021
			sig = In.ar(in*in_gain.dbamp.lag, 1);
			// sig.poll;
			bufnum = LocalBuf.new(fftSize, 1);
			chain = FFT(bufnum, sig, hop, window); // sig*env
			chain = PV_RecordBuf(chain, buf, 0, 1, loop, hop, window);

		}).load;
	}


	*metadata
	{
	 ^(metadata: (
			synthdefname: "TRecPvocBuf",
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



