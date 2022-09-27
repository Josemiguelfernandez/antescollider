HOA_Global_ConvRev_Dec3 : Tmodule2  {

	classvar n = 16; // number of convolutions

	*def
	{
		^SynthDef(\HOA_Global_ConvRev_Dec3, {|in, globTBus, gatee = 1, amp = 0, lag = 0.2, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1, in_ramp = 0.01, out_ramp = 0.01, fftsize = 2048, bufnum|
			var sig, envgate, envpause, cross, convsig, b2a, a2b;

			/*			b2a = FoaDecoderMatrix.newBtoA;
			a2b = FoaEncoderMatrix.newAtoB;*/

			sig = In.ar(in, 16);


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			// sig.poll;

			n.do({|i|

				sig[i] = PartConv.ar(sig[i], fftsize, bufnum+i)

			});


			Out.ar(globTBus, sig * amp.dbamp.lag(1));

		}).load;


	}
	// HOA_Global_ConvRev_Dec3.def
	*metadata
	{
		/*		var buffnum;
		buffnum = ~wave_buff[\vbap].bufnum; // VBAP Speaker conf.*/
		^(metadata: (
			synthdefname: "HOA_Global_ConvRev_Dec3",
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

