HOA_Global_Lev50_Dec2 : Tmodule2  {

	*def
	{
		^SynthDef(\HOA_Global_Lev50_Dec2, {|in, out, gatee = 1, amp = 0, lag = 0.2, matrix_ramp = 0.05,  in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1,  speakers_radius = 1.07, hoa_buf_init|
			var sig, envgate, envpause, cross, convsig, b2a, a2b, hoa;

			sig = In.ar(in, 9);

			// sig = HOADecLebedev50.ar(2, sig, hrir_Filters:1);

			hoa = HOADecLebedev502.ar(
				sig[ 0], sig[ 1], sig[ 2], sig[ 3],
				sig[ 4], sig[ 5], sig[ 6], sig[ 7],
				sig[ 8],
				yes: 1,
				speakers_radius: speakers_radius
			);

			sig = hoa.collect{|item,i|
				i = i * 2 + hoa_buf_init;
				// i.postln;
				[
					// left channel
					Convolution2.ar( item, i, 0, 4096, 1),
					// right channel
					Convolution2.ar( item, i+1, 0, 4096, 1)
				]
			}.sum; // sum the [[L,R],[L,R],[L,R],...[L,R]] array

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			Out.ar(out, sig*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14)); //salida directa a un bus

		}).load;

	}

	*metadata
	{
		/*		var buffnum;
		buffnum = ~wave_buff[\vbap].bufnum; // VBAP Speaker conf.*/
		^(metadata: (
			synthdefname: "HOA_Global_Lev50_Dec2",
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
