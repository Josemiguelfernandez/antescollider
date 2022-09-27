HOA_Grain_Buf_Interpol_xyz3 : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Grain_Buf_Interpol_xyz3, {|amp = 0, x = 0, y = 0, z = 0, xoffset = 0, yoffset = 0, zoffset = 0, globTBus, buf, envbuf0, envbuf1, ifac, pos = 0, loop = 0, t_trig = 0, rate = 1, env_dur = 1, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, position, hoa_encoder, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			// offset for all mix_group
			x = x+xoffset;
			y = y+yoffset;
			z = z+zoffset;

			x = Lag.kr(x, 0.1);
			y = Lag.kr(y, 0.1);
			z = Lag.kr(z, 0.1);

			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;

			sig = BufGrainI.ar(t_trig, env_dur, buf, rate, pos, envbuf0, envbuf1, ifac);


			hoa_encoder = HOAEncoder.ar(3, sig, position.theta, position.phi, 0, 1,position.rho); // amp in dB ne marche pas

			Out.ar(globTBus, hoa_encoder*amp.dbamp.lag);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Grain_Buf_Interpol_xyz3",
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

