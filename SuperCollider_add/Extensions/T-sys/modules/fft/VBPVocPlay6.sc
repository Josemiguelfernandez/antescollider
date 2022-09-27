VBPVocPlay6 : Tmodule2 {


	*def
	{
		^SynthDef(\VBPVocPlay6, { |out = 0, amp = 0, buf, num_chnls = 1, pos = 0|
			var sig;

			sig = VBPVoc.ar(6, buf, pos);

			Out.ar(out, sig* amp.dbamp.lag);
		}).load;
	}

    // chain = PV_PlayBuf(bufnum, recBuf, MouseX.kr(-1, 1), 50, 1);

	*metadata
	{
	 ^(metadata: (
			synthdefname: "VBPVocPlay6",
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



