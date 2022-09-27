HOA_Grain_Interp_xyz7 : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Grain_Interp_xyz7, {|amp = 0, x = 0, y = 0, z = 0, xoffset = 0, yoffset = 0, zoffset = 0, globTBus, buf, envbuf0=0, envbuf1=0, pos = 0, loop = 0, t_trig = 0, rate = 1, env_dur = 1, env_interp = -1|
			var sig, position, hoa_encoder, env, phase;

			x = x+xoffset;
			y = y+yoffset;
			z = z+zoffset;

			x = Lag.kr(x, 0.1);
			y = Lag.kr(y, 0.1);
			z = Lag.kr(z, 0.1);

			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;

			// env = PlayBuf.ar(1, envbuf, (SampleRate.ir/BufFrames.kr(envbuf)).reciprocal*env_dur.reciprocal, t_trig, doneAction: 2);
			// phase = Phasor.ar(0, ((SampleRate.ir/BufFrames.kr(envbuf0)).reciprocal*1env_dur.reciprocal)*rate, 0, BufFrames.ir(envbuf0));

			env = PlayBuf.ar(1, [envbuf0, envbuf1], (SampleRate.ir/BufFrames.kr(envbuf0)).reciprocal*env_dur.reciprocal, t_trig, doneAction: 2); // 2 env correlated
			// env = BufRd.ar(1,  [envbuf0, envbuf1], phase); // 2 env correlated

			env = LinXFade2.ar(env[0], env[1], env_interp); // Interpol from -1 to 1

			sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate, t_trig, BufFrames.kr(buf) * pos, loop: loop);

			// hoa_encoder = HOAEncoder.ar(5, sig*env, position.theta, position.phi, 0, 1, position.rho); // amp in dB
			hoa_encoder = AmbisonicEncoder.ar(7, sig*env, 0,position.rho, position.theta*(180/pi), position.phi*(180/pi)); // amp in dB0,

			Out.ar(globTBus, hoa_encoder*amp.dbamp.lag);

		}).load

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Grain_Interp_xyz7",
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

