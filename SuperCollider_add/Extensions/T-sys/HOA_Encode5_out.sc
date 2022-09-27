HOA_Encode5_out : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Encode5_out, {|in, out = 0, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, x = 0, y = 0, z = 0, dopon = 0, dopamnt = 0, el = 0, gbfbus, globTBus, plane_spherical = 1, spk_radius = 2.5, lo_freq = 50, lo_freq_gain = -40.0, compensation = 1|
			var sig;
			var position, distance, rd;
			var hoa_encoder;
			var min_sphere = 0.8, dis2;
			// var ambSigRef = Ref(0);


			x = Lag.kr(x, 0.1);
			y = Lag.kr(y, 0.1);
			z = Lag.kr(z, 0.1);
			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;

			// contr = Lag.kr(contr, 0.1);
			distance = position.rho;
			// dis = 1 - distance;
			// azim = position.theta;
			// azim.poll;
			// position.theta.poll;
			// el = position.phi;
			// dis = Select.kr(dis < 0, [dis, 0]);
			// dis = Select.kr(dis > 1, [dis, 1]);

			sig = In.ar(in, 1);
			/*sig = sig * amp.dbamp.lag(1);*/

			// sig.poll;
			// sig = LPF.ar(sig, (dis) * 18000 + 2000); // attenuate high freq with distance
			// Doppler
			// rd = (1 - dis) * 340;
			rd = distance * 340;
			rd = Lag.kr(rd, 1.0);
			// dopplershift= DelayC.ar(sig, 0.2, rd/1640.0 * dopon * dopamnt);
			sig = Select.ar(dopon, [sig, DelayC.ar(sig, 0.2, rd/1640.0 * dopamnt)]);
			// sig = dopplershift;

			/*			// Global reverberation & intensity
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
			*/
			// junto = sig;


			/*			omni = FoaEncode.ar(junto, foaEncoderOmni);
			spread = FoaEncode.ar(junto, ~foaEncoderSpread);
			diffuse = FoaEncode.ar(junto, ~foaEncoderDiffuse);
			junto = Select.ar(df, [omni, diffuse]);
			junto = Select.ar(sp, [junto, spread]);*/

			// foa = FoaEncode.ar(sig, encoderMatrix);
			// foa = FoaTransform.ar(foa, 'push', angle, (azim+45) * (pi/180)); // +45 para que 0° sea L-R

			// foa = FoaTransform.ar(junto, 'push', pi/2*contr, azim, el, intens);

			/*			ambSigSoa = [ambSigRef[0].value, ambSigRef[1].value, ambSigRef[2].value, ambSigRef[3].value,
			ambSigRef[4].value, ambSigRef[5].value, ambSigRef[6].value, ambSigRef[7].value,
			ambSigRef[8].value];*/

			// dis = (1 - dis)*max_radius; // * 5.0;
			// dis2 = dis;
			// dis2 = (1 - dis)*max_radius; // * 5.0;

			// dis = Select.kr(dis < 0.001, [dis, 0.001]);
			distance = Select.kr(distance < 0.001, [distance, 0.001]);
			 dis2 = distance;
			// dis2 = Select.kr(dis < 0.001, [dis1, 0.001]);
			// dis2.poll;
			// dis2 = Select.kr(dis2 > min_sphere, [dis2, 0]);
			// dis2 = Select.kr(dis2 < min_sphere, [dis2, dis2.linlin(min_sphere, 0.0, 0.0, -60.0)]);
			dis2 = dis2.linlin(0.0, min_sphere, lo_freq_gain, 0.0);
			// dis2.poll;
			// -0.2.linlin(0.0,0.5, -60.0, 0.0)
			/*			foa = HPF.ar(foa, 20);
			foa = FoaTransform.ar(foa, 'proximity', dis);*/
			// foa = foa * amp.dbamp.lag(1);
			// sig = LPF.ar(sig, (1-distance) * 18000 + 2000); // attenuate high freq with distance
			sig = Select.ar(compensation, [sig, BLowShelf.ar(sig, lo_freq, 1.0, dis2.lag(0.1))]);
			// sig = BLowShelf.ar(sig, lo_freq, 1.0, dis2.lag(0.1));

			hoa_encoder = HOAEncoder.ar(5, sig, position.theta, position.phi, amp, plane_spherical, distance, spk_radius);

			hoa_encoder = hoa_encoder*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14);


			// Out.ar(gbfbus, (hoa_encoder*globallev) + (hoa_encoder*locallev));
			Out.ar(globTBus, hoa_encoder);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Encode5_out",
			type: \fx,
			main: \x,
			sliders: [
				\x -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\x),
				\y -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\y),
				\z -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\z),
				\dopon -> ControlSpec(0, 1,\lin,1, 0,\dopon),
				\dopamnt -> ControlSpec(0.0, 1.0,\lin,0.001, 0,\dopamnt),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],

		))
	}
}

