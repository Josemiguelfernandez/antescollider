HOA_Encode4_out : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Encode4_out, {|in, out = 0, outbus, outgain = 0, dist = 3, amp = 0, matrix_ramp = 0.01, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, contr = 1, x = 0, y = 0, z = 0, xoffset = 0, yoffset = 0, zoffset = 0,  dopon = 0, dopamnt = 0, el = 0, gbfbus, globTBus, llev = 0, glev = 0, sp = 0, df = 0, spk_radius = 2.5, pitch = 0, roll = 0, yaw = 0|
			var sig, encoderMatrix, decoderMatrix, foa, b2a, a2b, ambSigFoa;
			var position, dis, azim, rd, dopplershift, globallev = 3, intens, locallev, junto, omni, spread, diffuse;
			var hoa_encoder;
			// var ambSigRef = Ref(0);

			x = x+xoffset;
			y = y+yoffset;
			z = z+zoffset;
			x = Lag.kr(x, 0.1);
			y = Lag.kr(y, 0.1);
			z = Lag.kr(z, 0.1);
			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;

			// contr = Lag.kr(contr, 0.1);

			dis = 1 - position.rho;
			// azim = position.theta;
			// azim.poll;
			// el = position.phi;
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

			hoa_encoder = HOAEncoder.ar(4, sig, position.theta, position.phi, 0, 1,position.rho, spk_radius); // amp in dB
			// hoa_encoder = HOAConvert.ar(4, HOAEncoder.ar(4, sig, position.theta, position.phi, 0, 1, position.rho, spk_radius), \ACN_N3D, \ACN_SN3D); // amp in dB

			hoa_encoder = hoa_encoder*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14);


			// Out.ar(gbfbus, (hoa_encoder*globallev) + (hoa_encoder*locallev));
			Out.ar(globTBus, hoa_encoder*amp.dbamp.lag);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Encode4_out",
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

