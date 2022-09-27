HOA_GrainIn_Interp_del_xyz7 : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_GrainIn_Interp_del_xyz7, {|in, amp = 0, x = 0, y = 0, z = 0, xoffset = 0, yoffset = 0, zoffset = 0, globTBus, buf, envbuf0=0, envbuf1=0, pos = 0, loop = 0, t_trig = 0, rate = 1, env_dur = 1, env_interp = -1, del = 0, max_del_time = 3, wind_steep = 0.0005|
			var sig, position, hoa_encoder, env, localbuf, sr, tapPhase, phasor1, del_ctr, tap1, tap2, fenetre1, fenetre2, del_dyn1, del_dyn2;

			x = x+xoffset;
			y = y+yoffset;
			z = z+zoffset;

			x = Lag.kr(x, 0.1);
			y = Lag.kr(y, 0.1);
			z = Lag.kr(z, 0.1);

			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;

			sr = SampleRate.ir;

			// sig

			// sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate, t_trig, BufFrames.kr(buf) * pos, loop: loop);

			sig = SoundIn.ar(in); // read directly from audio hardware
			// env
			env = PlayBuf.ar(1, [envbuf0, envbuf1], (sr/BufFrames.kr(envbuf0)).reciprocal*env_dur.reciprocal, t_trig); // 2 env correlated
			env = LinXFade2.ar(env[0], env[1], env_interp); // Interpol from -1 to 1
			sig = sig*env;

			// del
			localbuf = LocalBuf((max_del_time * sr).ceil, 1).clear;
			tapPhase = DelTapWr.ar(localbuf, sig);
			phasor1 = Phasor.ar(0, wind_steep, 0, 2pi, 0);
			del_ctr = K2A.ar(del);
			fenetre1 = sin(phasor1*0.5);
			fenetre2 = sin(phasor1+pi*0.5).abs;
			del_dyn1 = Latch.ar(del_ctr, fenetre1-0.0001);
			del_dyn2 = Latch.ar(del_ctr, fenetre2-0.0001);
			tap1 = DelTapRd.ar(localbuf, tapPhase, del_dyn1, 2) * fenetre1;
			tap2 = DelTapRd.ar(localbuf, tapPhase, del_dyn2, 2) * fenetre2;
			sig =  (tap1+tap2);

			Line.ar(start: 0.0, end: 1.0, dur: env_dur+del, doneAction: 2); // free grain after delay
			// hoa_encoder = HOAEncoder.ar(5, sig*env, position.theta, position.phi, 0, 1, position.rho); // amp in dB
			hoa_encoder = AmbisonicEncoder.ar(7, sig, 0,position.rho, position.theta*(180/pi), position.phi*(180/pi)); // amp in dB0,

			Out.ar(globTBus, hoa_encoder*amp.dbamp.lag);

		}).load

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_GrainIn_Interp_del_xyz7",
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

