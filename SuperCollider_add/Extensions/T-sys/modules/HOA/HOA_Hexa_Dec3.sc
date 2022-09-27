HOA_Hexa_Dec3 : Tmodule2  {

	*def
	{
		^SynthDef(\HOA_Hexa_Dec3, {|in, out, gatee = 1, amp = 0, lag = 0.2, matrix_ramp = 0.05,  in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1, xFade = 1, buf = 172, azi = 0, ele = 1, spr = 0, freeroom = 0.5, freedamp = 0.5, freemul = 1|
			var sig, envgate, envpause, cross, convsig, b2a, a2b;

/*			b2a = FoaDecoderMatrix.newBtoA;
			a2b = FoaEncoderMatrix.newAtoB;*/

			sig = In.ar(in, 16);
			// sig = FoaDecode.ar(sig, b2a);

			// sig = HOADecLebedev26.ar(3, sig, hrir_Filters:1);

			sig = HOADecHexa.ar(3, sig, 0);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

		// cross = xFade * envgate * envpause;
			Out.ar(out, sig*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14)); //salida directa a un bus

/*			convsig = [
				FreeVerb.ar(sig[0], mix: 1, room: freeroom, damp: freedamp, mul: freemul),
				FreeVerb.ar(sig[1], mix: 1, room: freeroom, damp: freedamp, mul: freemul),
				FreeVerb.ar(sig[2], mix: 1, room: freeroom, damp: freedamp, mul: freemul),
				FreeVerb.ar(sig[3], mix: 1, room: freeroom, damp: freedamp, mul: freemul)
			];

			convsig = FoaEncode.ar(convsig, a2b);*/

/*			convsig = convsig * envgate * envpause;
			convsig = convsig * amp.dbamp.lag(1);
			XOut.ar(out, cross, convsig); //salida directa a un bus*/

			// Out.ar(0, FoaDecode.ar(convsig, ~decoder));

			// Out.ar(out, sig) //FoaDecode.ar(convsig, ~decoder))
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		/*		var buffnum;
		buffnum = ~wave_buff[\vbap].bufnum; // VBAP Speaker conf.*/
		^(metadata: (
			synthdefname: "HOA_Hexa_Dec3",
			type: \fx,
			main: \azi,
			sliders:[
				\freeroom -> ControlSpec(0.0, 1.0,\lin, 0.001,0.5,\freeroom),
				\freedamp -> ControlSpec(0, 1,\lin, 0.001,0.5,\freedamp),
				\freemul -> ControlSpec(0, 1,\lin, 0.001, 1.0,\freemul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			]
		))

	}
}

