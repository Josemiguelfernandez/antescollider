ATK_diffuse : Tmodule2 {

	*def
	{

		SynthDef(\atk_diffuse, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, angle = 1.5, dist = 3, azim = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1,buffer|
			var sig, envgate, envpause, cross, encoderMatrix, decoderMatrix, foa, src,encode, decoder;
			var fr, s1r, s2r, br, bl, s2l, s1l, fl;

			decoder = FoaDecoderMatrix.newPanto(8, k: 'dual'); // 8 chnls horizontal decoder

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);

			// encoder
			foa = FoaEncode.ar(sig, ~diffuse_encoder);
			// foa = FoaTransform.ar(foa, 'push', angleM, azimM);
			foa = FoaTransform.ar(foa, 'push', angle, (azim+45) * (pi/180)); // +45 para que 0° sea L-R

			//  decode using an HRTF decoder
			sig = FoaDecode.ar(foa, decoder);

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
			synthdefname: "atk_diffuse",
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



