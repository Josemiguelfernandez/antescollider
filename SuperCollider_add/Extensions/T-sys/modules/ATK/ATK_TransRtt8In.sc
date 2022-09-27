ATK_TransRtt8In : Tmodule2 {

	*def
	{
		^SynthDef(\aTK_TransRtt8In, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, angle = 1.5, dist = 3, azim = 0, rttFreq = 0.5, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, encoderMatrix, decoderMatrix, foa;
			var fr, s1r, s2r, br, bl, s2l, s1l, fl;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;


			sig = In.ar(in, 8);

			encoderMatrix = FoaEncoderMatrix.newPanto(8); //FoaEncoderMatrix.newAtoB; //FoaEncoderMatrix.newOmni; //FoaEncoderKernel.newSpread;
			foa = FoaEncode.ar(sig, encoderMatrix);

			foa = FoaRTT.ar(foa,
				        LFNoise2.kr(rttFreq, pi, add: pi),
				        LFNoise2.kr(rttFreq**(1/3), pi, add: pi),
				        LFNoise2.kr(rttFreq**(2/3), pi, add: pi)
			);

			foa = FoaTransform.ar(foa, 'push', angle, (azim+45) * (pi/180)); // +45 para que 0° sea L-R
			/*			foa = HPF.ar(foa, 20);
			foa = FoaTransform.ar(foa, 'proximity', dist);*/

			decoderMatrix = FoaDecoderMatrix.newPanto(8, k: 'dual');

			sig = FoaDecode.ar(foa, decoderMatrix);

			XOut.ar(out, cross, sig*amp.dbamp.lag); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "aTK_TransRtt8In",
			type: \fx,
			main: \azim,
			sliders: [
				\azim -> ControlSpec(0, 360,\lin, 1,0,\azim),
				\angle -> ControlSpec(0, 1.570,\lin,0.001, pi/2,\angle),
				\rttFreq -> ControlSpec(0.01, 30,\lin,0.001, 0.2,\rttFreq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}


