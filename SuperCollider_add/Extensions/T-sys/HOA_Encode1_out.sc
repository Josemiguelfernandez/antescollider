HOA_Encode1_out : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Encode1_out, {|in, out = 0, outbus, outgain = 0, dist = 3, amp = 0, matrix_ramp = 0.01, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, contr = 1, x = 0, y = 0, z = 0, dopon = 0, dopamnt = 0, el = 0, gbfbus, globTBus, llev = 0, glev = 0, sp = 0, df = 0, spk_radius = 2.5|
			var sig, encoderMatrix, decoderMatrix, foa, b2a, a2b, ambSigFoa;
			var position, dis, azim, rd, dopplershift, globallev = 3, intens, locallev, junto, omni, spread, diffuse;
			var hoa_encoder;
			// var ambSigRef = Ref(0);



			// encoderMatrix = FoaEncoderMatrix.newOmni; //FoaEncoderMatrix.newOmni; //FoaEncoderKernel.newSpread;
			// foaEncoderOmni = FoaEncoderMatrix.newOmni;
			// ~foaEncoderSpread = FoaEncoderKernel.newSpread (subjectID: 6, kernelSize: 2048);
			// ~foaEncoderDiffuse = FoaEncoderKernel.newDiffuse (subjectID: 3, kernelSize: 2048);

			x = Lag.kr(x, 0.1);
			y = Lag.kr(y, 0.1);
			z = Lag.kr(z, 0.1);
			position = Cartesian(y, x.neg, z);

			contr = Lag.kr(contr, 0.1);

			dis = 1 - position.rho;
			azim = position.theta;
			// azim.poll;
			el = position.phi;
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

			dis = (1 - dis) * 5.0;
			dis = Select.kr(dis < 0.001, [dis, 0.001]);

/*			foa = HPF.ar(foa, 20);
			foa = FoaTransform.ar(foa, 'proximity', dis);*/
			// foa = foa * amp.dbamp.lag(1);
			hoa_encoder = HOAEncoder.ar(1, sig, position.theta, position.phi, amp, 1, dis, spk_radius);

			hoa_encoder = hoa_encoder*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14);


			// Out.ar(gbfbus, (hoa_encoder*globallev) + (hoa_encoder*locallev));
			Out.ar(globTBus, hoa_encoder);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Encode1_out",
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

