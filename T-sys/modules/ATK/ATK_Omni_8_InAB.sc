ATK_Omni_8_InAB : Tmodule2 {

	*def
	{
		^SynthDef(\atk_omni_8_InAB, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, angle = 1.5, dist = 3, azim = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, encoderMatrix, decoderMatrix, foa;
			var fr, s1r, s2r, br, bl, s2l, s1l, fl;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;


			sig = In.ar(in, 8);

			encoderMatrix = FoaEncoderMatrix.newAtoB; //FoaEncoderMatrix.newOmni; //FoaEncoderKernel.newSpread;
			foa = FoaEncode.ar(sig, encoderMatrix);
			foa = FoaTransform.ar(foa, 'push', angle, (azim+45) * (pi/180)); // +45 para que 0° sea L-R
			foa = HPF.ar(foa, 20);
			foa = FoaTransform.ar(foa, 'proximity', dist);

			decoderMatrix = FoaDecoderMatrix.newPanto(8, k: 'dual');

			sig = FoaDecode.ar(foa, decoderMatrix);

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "atk_omni_8_InAB",
			type: \fx,
			main: \azim,
			sliders: [
				\dist -> ControlSpec(0.05, 10,\lin,0.001, 3,\dist),
				\azim -> ControlSpec(0, 360,\lin, 1,0,\azim),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}


