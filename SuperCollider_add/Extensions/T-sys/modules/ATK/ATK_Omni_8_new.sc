ATK_Omni_8_new : Tmodule2 {

	*def
	{
		^SynthDef(\ATK_Omni_8_new, {|in, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, angle = 1.5, dist = 3, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1, contr = 1, mx = 0, my = 0, mz = 0, dopon = 0, dopamnt = 0, el = 0, aFormatFoa, aFormatBusOutFoa, aFormatBusInFoa, ambSigFoaProcessed, gbfbus, globTBus, llev = 0,glev = 0, aFormatSoa, sp = 0, df = 0|
			var sig, envgate, envpause, cross, encoderMatrix, decoderMatrix, foa, b2a, a2b, ambSigFoa;
			var fonte, dis, azim, rd, dopplershift, globallev = 3, intens, locallev, junto, omni, spread, diffuse;
			var foaEncoderOmni, foaEncoderSpread, foaEncoderDiffuse;
			// var ambSigRef = Ref(0);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			b2a = FoaDecoderMatrix.newBtoA;
			a2b = FoaEncoderMatrix.newAtoB;
			// encoderMatrix = FoaEncoderMatrix.newOmni; //FoaEncoderMatrix.newOmni; //FoaEncoderKernel.newSpread;
			foaEncoderOmni = FoaEncoderMatrix.newOmni;
			// ~foaEncoderSpread = FoaEncoderKernel.newSpread (subjectID: 6, kernelSize: 2048);
			// ~foaEncoderDiffuse = FoaEncoderKernel.newDiffuse (subjectID: 3, kernelSize: 2048);

			mx = Lag.kr(mx, 0.1);
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
			junto = sig;

			/*			prepareAmbSigFunc.value(ambSigRef, junto, azim, el, intens: intens, dis: dis);

			prepareAmbSigFunc = { |ambSigRef, junto, azim, el, intens, dis|*/
			// ambSigRef.value = FMHEncode0.ar(junto, azim, el, intens);

			omni = FoaEncode.ar(junto, foaEncoderOmni);
			spread = FoaEncode.ar(junto, ~foaEncoderSpread);
			diffuse = FoaEncode.ar(junto, ~foaEncoderDiffuse);
			junto = Select.ar(df, [omni, diffuse]);
			junto = Select.ar(sp, [junto, spread]);

			// foa = FoaEncode.ar(sig, encoderMatrix);
			// foa = FoaTransform.ar(foa, 'push', angle, (azim+45) * (pi/180)); // +45 para que 0° sea L-R
			foa = FoaTransform.ar(junto, 'push', pi/2*contr, azim, el, intens);

			/*			ambSigSoa = [ambSigRef[0].value, ambSigRef[1].value, ambSigRef[2].value, ambSigRef[3].value,
			ambSigRef[4].value, ambSigRef[5].value, ambSigRef[6].value, ambSigRef[7].value,
			ambSigRef[8].value];*/

			dis = (1 - dis) * 5.0;
			dis = Select.kr(dis < 0.001, [dis, 0.001]);

			foa = HPF.ar(foa, 20);
			foa = FoaTransform.ar(foa, 'proximity', dis);
			// foa.poll;
			// foa.poll;
			// decoderMatrix = FoaDecoderMatrix.newPanto(8, k: 'dual');
			// decoderMatrix = FoaDecoderKernel.newCIPIC(21);
			// decoderMatrix = FoaDecoderKernel.newSpherical;
			// decoderMatrix = FoaDecoderKernel.newUHJ;
			// decoderMatrix = ~decoder;

			// sig = FoaDecode.ar(foa, decoderMatrix);

			// aFormatFoa = FoaDecode.ar(foa, b2a);

			// Out.ar(aFormatBusOutFoa, aFormatFoa);

			// aFormatSoa = AtkMatrixMix.ar(ambSigSoa, soa_a12_decoder_matrix);


			// sig .poll;
			// XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar

			// aFormatFoa = InFeedback.ar(aFormatBusInFoa, 4);

			// ambSigFoaProcessed  = FoaEncode.ar(aFormatFoa, a2b);
			// ambSigFoaProcessed  = FoaEncode.ar(aFormatFoa, a2b);
			// ambSigSoaProcessed = AtkMatrixMix.ar(aFormatSoa, soa_a12_encoder_matrix);


			// ambSigFoa = ambSigFoaProcessed;
			/*ambSigFoa.poll;*/
			// foa.poll(label: \foa); //close rev

			Out.ar(gbfbus, (foa*globallev) + (foa*locallev));
			Out.ar(globTBus, foa);

			/*reverbOutFunc = { |soaBus, gbfbus, ambsinal, ambsinal1O, globallev, locallev |
			Out.ar(gbfbus, (ambsinal1O*globallev) + (ambsinal1O*locallev));};
			espacAmbOutFunc = { |ambsinal, ambsinal1O, dec|
			Out.ar(this.globTBus, ambsinal1O);
			*/
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "ATK_Omni_8_new",
			type: \fx,
			main: \mx,
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

// convert to A-format and send to a-format out busses
// aFormatFoa = FoaDecode.ar(ambSigFoa, b2a);
// //SendTrig.kr(Impulse.kr(1), 0, aFormatBusOutFoa); // debug
// Out.ar(aFormatBusOutFoa, aFormatFoa);
// aFormatSoa = AtkMatrixMix.ar(ambSigSoa, soa_a12_decoder_matrix);
// Out.ar(aFormatBusOutSoa, aFormatSoa);
//
// // flag switchable selector of a-format signal (from insert or not)
// aFormatFoa = Select.ar(insertFlag, [aFormatFoa, InFeedback.ar(aFormatBusInFoa, 4)]);
// aFormatSoa = Select.ar(insertFlag, [aFormatSoa, InFeedback.ar(aFormatBusInSoa, 12)]);
//
// // convert back to b-format
// ambSigFoaProcessed  = FoaEncode.ar(aFormatFoa, a2b);
// ambSigSoaProcessed = AtkMatrixMix.ar(aFormatSoa, soa_a12_encoder_matrix);
//
// //SendTrig.kr(Impulse.kr(0.5), 0, ambSigFoaProcessed); // debug
// // not sure if the b2a/a2b process degrades signal. Just in case it does:
// ambSigFoa = Select.ar(insertFlag, [ambSigFoa, ambSigFoaProcessed]);
// ambSigSoa = Select.ar(insertFlag, [ambSigSoa, ambSigSoaProcessed]);
//
//
// reverbOutFunc.value(soaBus, gbfbus, ambSigSoa, ambSigFoa, globallev, locallev);
// espacAmbOutFunc.value(ambSigSoa, ambSigFoa, dec);
// }).load;
//
//
// reverbOutFunc = { |soaBus, gbfbus, ambsinal, ambsinal1O, globallev, locallev |
// Out.ar(gbfbus, (ambsinal1O*globallev) + (ambsinal1O*locallev));};
//
// espacAmbOutFunc = { |ambsinal, ambsinal1O, dec|
// 	Out.ar(this.globTBus, ambsinal1O);
// };





// // ------------------------------------------------------------
// // omni encoder
// // mono pink noise source
//
//
// // define encoder matrix
// ~encoder = FoaEncoderMatrix.newOmni
// ~encoder = FoaEncoderKernel.newSpread
// ~encoder = FoaEncoderKernel.newDiffuse
// // inspect
// ~encoder.kind
// ~encoder.numChannels
// ~encoder.dirChannels
//
// ~decoder = FoaDecoderMatrix.newPanto(8, k: 'dual')
//
//
//
// ~renderDecode = { arg in, decoder;
// 	var fr, s1r, s2r, br, bl, s2l, s1l, fl;
//
// 	#fr, s1r, s2r, br, bl, s2l, s1l, fl = FoaDecode.ar(in, ~decoder);
// 	[fl, fr, s1r, s2r, br, bl, s2l, s1l]
//
// }
//
// ~decoder.dirChannels * 180/pi
// //-> [ 22.5, 67.5, 112.5, 157.5, -157.5, -112.5, -67.5, -22.5 ]
//
// (
// {
// 	var sig;                            // audio signal
// 	var angle, azim, dist;              // angle and azimuth control
//
//
// 	// display encoder and decoder
// 	"Ambisonic encoding via % encoder".format(~encoder.kind).postln;
// 	"Ambisonic decoding via % decoder".format(~decoder.kind).postln;
//
// 	// angle ---> top           = push to plane wave
// 	//            bottom        = omni-directional
// 	angle = 1.5; // MouseY.kr(pi/2, 0).poll;
// 	dist = MouseY.kr(0.05, 10);
// 	// azimuth -> hard left     = back
// 	//            centre        = centre
// 	//            hard right    = back
// 	azim = MouseX.kr(pi, -pi).poll;
// 	(MouseX.kr(pi, -pi) * 180/pi).poll;
// 	(MouseX.kr(180, -180) * pi/180).poll;
//
// 	// ------------------------------------------------------------
// 	// test sig
// 	sig = Dust.ar(10);                         // mono pink noise
//
//
//
// 	// ------------------------------------------------------------
// 	// encode
// 	sig = FoaEncode.ar(sig, ~encoder);
//
// 	// ------------------------------------------------------------
// 	// transform
// 	sig = FoaTransform.ar(sig, 'push', angle, azim);
// 	sig = HPF.ar(sig, 20);
// 	sig = FoaTransform.ar(sig, 'proximity', dist);
//
//
// 	// ------------------------------------------------------------
// 	// decode (via ~renderDecode)
// 	~renderDecode.value(sig, ~decoder)
//
// }.scope;
// )
// // ------------------------------------------------------------
//
