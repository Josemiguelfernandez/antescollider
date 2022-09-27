HOA_Grain2_xyz4 : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Grain2_xyz4, {|amp = 0, x = 0, y = 0, z = 0, globTBus, buf, envbuf, pos = 0, loop = 0, t_trig = 0, rate = 1, env_dur = 1|
			var sig, position, hoa_encoder, env;

			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;

			env = PlayBuf.ar(1, envbuf, (SampleRate.ir/BufFrames.kr(envbuf)).reciprocal*env_dur.reciprocal, t_trig, doneAction: 2); //BufRateScale.kr(envbuf) *
			sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate, t_trig, SampleRate.ir*pos, loop: loop);

			hoa_encoder = HOAEncoder.ar(4, sig*env, position.theta, position.phi, 0, 1,position.rho); // amp in dB

			Out.ar(globTBus, hoa_encoder*amp.dbamp);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Grain2_xyz4",
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

