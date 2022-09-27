ATK_Proximity8In : Tmodule2 {

	*def
	{
		^SynthDef(\aTK_Proximity8In, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, angle = 1.5, dist = 0.5, azim = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, encoderMatrix, decoderMatrix, foa;
			var fr, s1r, s2r, br, bl, s2l, s1l, fl;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			decoderMatrix = FoaDecoderMatrix.newPanto(8, k: 'dual');

			sig = In.ar(in, 8);

			encoderMatrix = [
				FoaEncoderMatrix.newDirection,
				FoaEncoderMatrix.newDirection(pi/4),
				FoaEncoderMatrix.newDirection(pi/2),
				FoaEncoderMatrix.newDirection(3*pi/4),
				FoaEncoderMatrix.newDirection(7*pi/8),
				FoaEncoderMatrix.newDirection(pi),
				FoaEncoderMatrix.newDirection(pi.neg/2),
				FoaEncoderMatrix.newDirection(pi.neg/4)
			];

			sig = Mix.fill(8, {|i|
				FoaEncode.ar(sig.at(i), encoderMatrix.at(i))
			});

			// ------------------------------------------------------------
			// transform
			sig = FoaTransform.ar(sig, 'nfc', dist.lag); //


			sig = FoaDecode.ar(sig, decoderMatrix);

			XOut.ar(out, cross, sig*amp.dbamp.lag); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "aTK_Proximity8In",
			type: \fx,
			main: \dist,
			sliders: [
				\dist -> ControlSpec(0.05, 2,\lin,0.001, 0.5,\dist),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}


