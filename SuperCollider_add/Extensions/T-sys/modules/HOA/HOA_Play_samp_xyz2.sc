HOA_Play_samp_xyz2 : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Play_samp_xyz2, {|amp = 0, x = 0, y = 0, z = 0, globTBus, buf, envbuf, pos = 0, loop = 0, t_trig = 0, rate = 1, rev = 1|
			var sig, position, hoa_encoder, env;

			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;

			sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate * rev, t_trig, BufFrames.kr(buf) * pos, loop: loop, doneAction:2); //este libera synth para performance
			hoa_encoder = HOAEncoder.ar(2, sig, position.theta, position.phi, amp, 1, position.rho); // amp in dB

			Out.ar(globTBus, hoa_encoder);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Play_samp_xyz2",
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

