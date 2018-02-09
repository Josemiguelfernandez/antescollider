ATK_spread : Tmodule2 {

	*def
	{
		/*var decoder, encoder, cond, sndbuf;

		cond = Condition.new;

		Routine.run({

		// encoder = FoaEncoderKernel.newSpread;
		decoder = FoaDecoderMatrix.newPanto(8, k: 'dual');

		// sndbuf = Buffer.read(s, Atk.userSoundsDir ++ "/uhj/Palestrina-O_Bone.wav");

		Server.default.sync(cond);
		*/
		SynthDef(\atk_spread, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, angle = 1.5, dist = 3, azim = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1,buffer|
			var sig, envgate, envpause, cross, encoderMatrix, decoderMatrix, foa, src,encode, decoder;
			var fr, s1r, s2r, br, bl, s2l, s1l, fl;

			decoder = FoaDecoderMatrix.newPanto(8, k: 'dual'); // 8 chnls horizontal decoder
			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;


			// src = PlayBuf.ar(sndbuf.numChannels, buffer, BufRateScale.kr(buffer));
			// src = SinOsc.ar(200); // + (In.ar(inbus, 1) * ingain);
			sig = In.ar(in, 1);
			// encode using a UHJ encoder
			foa = FoaEncode.ar(sig, ~spread_encoder);
			// foa = FoaTransform.ar(foa, 'push', angleM, azimM);
			foa = FoaTransform.ar(foa, 'push', angle, (azim+45) * (pi/180)); // +45 para que 0° sea L-R

			//  decode using an HRTF decoder
			sig = FoaDecode.ar(foa, decoder);

			// sig = In.ar(in, 1); // + (In.ar(inbus, 1) * ingain);

			// encoderMatrix = FoaEncoderMatrix.newOmni; //FoaEncoderMatrix.newOmni; //FoaEncoderKernel.newSpread;
			// foa = FoaEncode.ar(sig, encoder);

			// foa = HPF.ar(foa, 20);
			// foa = FoaTransform.ar(foa, 'push', angle, azim);

			// decoderMatrix = FoaDecoderMatrix.newPanto(8, k: 'dual');

			// sig = FoaDecode.ar(foa, decoderMatrix);
			// sig = FoaDecode.ar(sig, decoder);

			// #fr, s1r, s2r, br, bl, s2l, s1l, fl = FoaDecode.ar(foa, decoder);
			// sig = [fl, fr, s1r, s2r, br, bl, s2l, s1l] * envgate * envpause * amp.dbamp.lag;

			/*			sig = FoaTransform.ar(sig, 'push', angle, azim * (pi/180));
			sig = HPF.ar(sig, 20);
			sig = FoaTransform.ar(sig, 'proximity', dist);*/

			/*			#fr, s1r, s2r, br, bl, s2l, s1l, fl = FoaDecode.ar(sig, FoaDecoderMatrix.newPanto(8, k: 'dual'));

			sig = [fl, fr, s1r, s2r, br, bl, s2l, s1l] * envgate * envpause * amp.dbamp.lag;*/

			XOut.ar(out, cross, sig*amp.dbamp.lag); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;
		/*
		Server.default.sync(cond);
		});*/
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "atk_spread",
			type: \fx,
			main: \azim,
			sliders: [
				\angle -> ControlSpec(0, 1.570,\lin,0.001, pi/2,\angle),
				\azim -> ControlSpec(0, 360,\lin, 1,0,\azim),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

// ~spread_encoder = FoaEncoderKernel.newSpread
// ~decoder = FoaDecoderMatrix.newPanto(8, k: 'dual')
// ~renderDecode = { arg in, decoder;
// 	var fr, s1r, s2r, br, bl, s2l, s1l, fl;
//
// 	#fr, s1r, s2r, br, bl, s2l, s1l, fl = FoaDecode.ar(in, ~decoder);
// 	[fl, fr, s1r, s2r, br, bl, s2l, s1l]
//
// }

// ATK_spread.def
//
// ~decoder = FoaDecoderMatrix.newPanto(8, k: 'dual')
// ~decoder.numChannels
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
//

