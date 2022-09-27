HOA_Grain5 : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Grain5, {|in, out = 0, outbus, outgain = 0, dist = 3, amp = 0, matrix_ramp = 0.01, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, contr = 1, mx = 0, my = 0, mz = 0, dopon = 0, dopamnt = 0, el = 0, gbfbus, globTBus, llev = 0, glev = 0, sp = 0, df = 0, buf, envbuf, pos = 0, loop = 0, t_trig = 0, rate = 1, env_rate = 1, dis = 1, azim = 20|
			var sig, encoderMatrix, decoderMatrix, foa, b2a, a2b, ambSigFoa;
			var fonte, rd, dopplershift, globallev = 3, intens, locallev, junto, omni, spread, diffuse;
			var hoa_encoder, env;

/*			mx = Lag.kr(mx, 0.1);
			my = Lag.kr(my, 0.1);
			mz = Lag.kr(mz, 0.1);
			contr = Lag.kr(contr, 0.1);
			fonte = Cartesian.new;
			fonte.set(mx, my, mz);
			dis = 1 - fonte.rho;
			azim = fonte.theta;
			// azim.poll;
			el = fonte.phi;
			dis = Select.kr(dis < 0, [dis, 0]);
			dis = Select.kr(dis > 1, [dis, 1]);

			sig = In.ar(in, 1);
			/*sig = sig * amp.dbamp.lag(1);*/

			// sig.poll;
			sig = LPF.ar(sig, (dis) * 18000 + 2000); // attenuate high freq with distance
			// Doppler
			rd = (1 - dis) * 340;
			rd = Lag.kr(rd, 1.0);
			dopplershift= DelayC.ar(sig, 0.2, rd/1640.0 * dopon * dopamnt);
			sig = dopplershift;

			// Global reverberation & intensity
			globallev = 1 / (1 - dis).sqrt;
			intens = globallev - 1;
			intens = Select.kr(intens > 4, [intens, 4]);
			intens = Select.kr(intens < 0, [intens, 0]);
			intens = intens / 4;

			globallev = globallev - 1.0; // lower tail of curve to zero
			globallev = Select.kr(globallev > 1, [globallev, 1]);
			globallev = Select.kr(globallev < 0, [globallev, 0]);
			globallev = globallev * Lag.kr(glev, 0.1);

			// Local reverberation
			locallev = 1 - dis;
			locallev = locallev  * Lag.kr(llev, 0.1);



			dis = (1 - dis) * 5.0;
			dis = Select.kr(dis < 0.001, [dis, 0.001]);*/
			env = PlayBuf.ar(1, envbuf, env_rate, t_trig, doneAction: 2); //BufRateScale.kr(envbuf) *
			// BufRateScale.kr(envbuf).poll;
			// envbuf.poll;

			sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate, t_trig, BufFrames.kr(buf) * pos, loop: loop);



/*			foa = HPF.ar(foa, 20);
			foa = FoaTransform.ar(foa, 'proximity', dis);*/
			// foa = foa * amp.dbamp.lag(1);
			hoa_encoder = HOAEncoder.ar(5, sig*env, azim, el, amp, 1, dis); // amp in dB

			// hoa_encoder = hoa_encoder*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14);


			// Out.ar(gbfbus, (hoa_encoder*globallev) + (hoa_encoder*locallev));
			Out.ar(globTBus, hoa_encoder);

			// Out.ar(0, sig*env);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Grain5",
			type: \gen,
			main: \amp,
			sliders: [
				\mx -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\mx),
				\my -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\my),
				\mz -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\mz),
				\dopon -> ControlSpec(0, 1,\lin,1, 0,\dopon),
				\dopamnt -> ControlSpec(0.0, 1.0,\lin,0.001, 0,\dopamnt),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],

		))
	}
}

