HOA_Encode3 : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Encode3, {|in, amp = 0, x = 0, y = 0, z = 0, xoffset = 0, yoffset = 0, zoffset = 0, globTBus, buf, envbuf, pos = 0, loop = 0, t_trig = 0, rate = 1|
			var sig, position, hoa_encoder;

			// offset for all mix_group
			x = x+xoffset;
			y = y+yoffset;
			z = z+zoffset;

			x = Lag.kr(x, 0.1);
			y = Lag.kr(y, 0.1);
			z = Lag.kr(z, 0.1);

			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;

			sig = In.ar(in, 1);

			hoa_encoder = HOAEncoder.ar(3, sig, position.theta, position.phi, 0, 1,position.rho); // amp in dB ne marche pas

			Out.ar(globTBus, hoa_encoder*amp.dbamp.lag);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Encode3",
			type: \gen,
			main: \amp,
			sliders: [
				\x -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\x),
				\y -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\y),
				\z -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\z),
/*				\dopon -> ControlSpec(0, 1,\lin,1, 0,\dopon),
				\dopamnt -> ControlSpec(0.0, 1.0,\lin,0.001, 0,\dopamnt),*/
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],

		))
	}
}

