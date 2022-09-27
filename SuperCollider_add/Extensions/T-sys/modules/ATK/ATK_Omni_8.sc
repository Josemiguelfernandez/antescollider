ATK_Omni_8 : Tmodule2 {

	*def
	{
		^SynthDef(\atk_omni_8, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, angle = 1.5, dist = 3, azim = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, encoderMatrix, decoderMatrix, foa;
			var fr, s1r, s2r, br, bl, s2l, s1l, fl;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;


			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			encoderMatrix = FoaEncoderMatrix.newOmni; //FoaEncoderMatrix.newOmni; //FoaEncoderKernel.newSpread;
			foa = FoaEncode.ar(sig, encoderMatrix);
			foa = FoaTransform.ar(foa, 'push', angle, (azim+45) * (pi/180)); // +45 para que 0° sea L-R
			foa = HPF.ar(foa, 20);
			foa = FoaTransform.ar(foa, 'proximity', dist);

			decoderMatrix = FoaDecoderMatrix.newPanto(8, k: 'dual');

			sig = FoaDecode.ar(foa, decoderMatrix);

/*			sig = FoaTransform.ar(sig, 'push', angle, azim * (pi/180));
			sig = HPF.ar(sig, 20);
			sig = FoaTransform.ar(sig, 'proximity', dist);*/

/*			#fr, s1r, s2r, br, bl, s2l, s1l, fl = FoaDecode.ar(sig, FoaDecoderMatrix.newPanto(8, k: 'dual'));

			sig = [fl, fr, s1r, s2r, br, bl, s2l, s1l] * envgate * envpause * amp.dbamp.lag;*/

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "atk_omni_8",
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
