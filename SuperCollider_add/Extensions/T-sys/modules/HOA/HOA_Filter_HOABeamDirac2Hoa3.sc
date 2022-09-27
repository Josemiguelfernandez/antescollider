HOA_Filter_HOABeamDirac2Hoa3 : Tmodule2  {

	*def
	{
		^SynthDef(\HOA_Filter_HOABeamDirac2Hoa3, {|in, out, gatee = 1, amp = 0, lag = 0.2, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1, buf = 172, spr = 0, freeroom = 0.5, freedamp = 0.5, freemul = 1, az = 0, ele = 0|
			var sig, envgate, envpause, cross, convsig, b2a, a2b;


			sig = In.ar(in, 16);

			sig = HOABeamDirac2Hoa.ar(3, sig, Lag.kr(-1*az, 0.1), Lag.kr(ele, 0.1));

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			Out.ar(out, sig) //FoaDecode.ar(convsig, ~decoder))
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		/*		var buffnum;
		buffnum = ~wave_buff[\vbap].bufnum; // VBAP Speaker conf.*/
		^(metadata: (
			synthdefname: "HOA_Filter_HOABeamDirac2Hoa3",
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

