HOA_Encode_Ste4_out : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Encode_Ste4_out, {|in, out = 0, amp = 0, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, x = 0, y = 0, z = 0, x1 = 0, y1 = 0, z1 = 0, xoffset = 0, yoffset = 0, zoffset = 0,  dopon = 0, dopamnt = 0, globTBus, spk_radius = 2.5, amp_enc0 = 0, amp_enc1 = 0|
			var sig;
			var position1, position2, dis1, dis2, azim1, azim2, rd1, rd2, dopplershift1, dopplershift2;
			var hoa_encoder1, hoa_encoder2, hoa_encoder_result;
			// var ambSigRef = Ref(0);

			sig = In.ar(in, 2);

			x = x+xoffset;
			y = y+yoffset;
			z = z+zoffset;
/*			x = Lag.kr(x, 0.1);
			y = Lag.kr(y, 0.1);
			z = Lag.kr(z, 0.1);*/
			position1 = Cartesian(y, x.neg, z);
			position1 = position1.asSpherical;

			dis1 = 1 - position1.rho;
			dis1 = Select.kr(dis1 < 0, [dis1, 0]);
			dis1 = Select.kr(dis1 > 1, [dis1, 1]);

			sig[0] = LPF.ar(sig[0], (dis1) * 18000 + 2000); // attenuate high freq with distance
			// Doppler
			rd1 = (1 - dis1) * 340;
			rd1 = Lag.kr(rd1, 1.0);
			dopplershift1 = DelayC.ar(sig[0], 0.2, rd1/1640.0 * dopon * dopamnt);
			// dopplershift1.poll;
			// sig.poll;

			x1 = x1+xoffset;
			y1 = y1+yoffset;
			z1 = z1+zoffset;
/*			x1 = Lag.kr(x1, 0.1);
			y1 = Lag.kr(y1, 0.1);
			z1 = Lag.kr(z1, 0.1);*/
			position2 = Cartesian(y1, x1.neg, z1);
			position2 = position2.asSpherical;

			dis2 = 1 - position2.rho;
			dis2 = Select.kr(dis2 < 0, [dis2, 0]);
			dis2 = Select.kr(dis2 > 1, [dis2, 1]);

			sig[1] = LPF.ar(sig[1], (dis2) * 18000 + 2000); // attenuate high freq with distance
			// Doppler
			rd2 = (1 - dis2) * 340;
			rd2 = Lag.kr(rd2, 1.0);
			dopplershift2 = DelayC.ar(sig[1], 0.2, rd2/1640.0 * dopon * dopamnt);
			// dopplershift2.poll;
			// sig = dopplershift;

			hoa_encoder1 = HOAEncoder.ar(4, dopplershift1, position1.theta, position1.phi, 0, 1,position1.rho, spk_radius); // amp in dB
			// hoa_encoder1.poll;
			hoa_encoder2 = HOAEncoder.ar(4, dopplershift2, position2.theta, position2.phi, 0, 1,position2.rho, spk_radius); // amp in dB

			hoa_encoder_result = (hoa_encoder1*amp_enc0.dbamp.lag) + (hoa_encoder2*amp_enc1.dbamp.lag); // local amp
			hoa_encoder_result = hoa_encoder_result*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14);

			Out.ar(globTBus, hoa_encoder_result*amp.dbamp.lag);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Encode_Ste4_out",
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

