ATKGlobalFOA_Trans_Rev : Tmodule2  {

	*def
	{
		^SynthDef(\ATKGlobalFOA_Trans_Rev, {|in_rev, in, out, amp = 0, lag = 0.2, gate = 1, free = 1, freeroom = 0.5, freedamp = 0.5, freemul = 1, gatee = 1, in_ramp = 0.01, out_ramp = 0.01|
			var sig, sig_rev, convsig, b2a, a2b;

			b2a = FoaDecoderMatrix.newBtoA;
			a2b = FoaEncoderMatrix.newAtoB;


			sig_rev = In.ar(in_rev, 4);
			sig_rev = FoaDecode.ar(sig_rev, b2a); // convert b2a

			convsig = [
				FreeVerb.ar(sig_rev[0], mix: 1, room: freeroom, damp: freedamp, mul: freemul),
				FreeVerb.ar(sig_rev[1], mix: 1, room: freeroom, damp: freedamp, mul: freemul),
				FreeVerb.ar(sig_rev[2], mix: 1, room: freeroom, damp: freedamp, mul: freemul),
				FreeVerb.ar(sig_rev[3], mix: 1, room: freeroom, damp: freedamp, mul: freemul)
			];

			convsig = FoaEncode.ar(convsig, a2b);

			sig = In.ar(in, 4);

			sig = sig+convsig; // mix direct in + rev_in
			sig = sig**EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14);


			// Out.ar(0, FoaDecode.ar(convsig, ~decoder));
			Out.ar(out, FoaDecode.ar(sig*amp.dbamp.lag, ~decoder))
			// Out.ar(out, convsig) //FoaDecode.ar(convsig, ~decoder))
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		/*		var buffnum;
		buffnum = ~wave_buff[\vbap].bufnum; // VBAP Speaker conf.*/
		^(metadata: (
			synthdefname: "ATKGlobalFOA_Trans_Rev",
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

/*			sig = In.ar(globtbus, 4);


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);



			Out.ar(out, FoaDecode.ar(sig*amp.dbamp.lag, ~decoder))*/

// \inlev -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -12, \inlev),
// \mix -> ControlSpec(0, 1,\lin, 0.001,0.33, \mix),
/*\roomsize -> ControlSpec(0.0, 1.0,\lin, 0.001,0.15,\roomsize),
\damp -> ControlSpec(0, 1,\lin, 0.001,0.5,\damp),
\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)


SynthDef.new("revGlobalBFormatAmb",  { arg gbfbus;
	var convsig, sig = In.ar(gbfbus, 4);
	sig = FoaDecode.ar(sig, b2a);
	convsig = [
		FreeVerb.ar(sig[0], mix: 1, room: freeroom, damp: freedamp, mul: freemul),
		FreeVerb.ar(sig[1], mix: 1, room: freeroom, damp: freedamp, mul: freemul),
		FreeVerb.ar(sig[2], mix: 1, room: freeroom, damp: freedamp, mul: freemul),
		FreeVerb.ar(sig[3], mix: 1, room: freeroom, damp: freedamp, mul: freemul)
	];
	convsig = FoaEncode.ar(convsig, a2b);
	revGlobalAmbFunc.value(convsig, dec);
}).load;

revGlobalAmbFunc = { |ambsinal, dec|
	//				Out.ar( 0, FoaDecode.ar(ambsinal, dec));
	//SendTrig.kr(Impulse.kr(1), 301, this.globTBus); //debug
	Out.ar(this.globTBus, ambsinal);*/