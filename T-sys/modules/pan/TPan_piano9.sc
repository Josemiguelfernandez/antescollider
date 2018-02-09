TPan_piano9 : Tmodule2  {


	*def
	{


	^SynthDef(\TPan_piano9, {|in, out = 0, outbus, outgain = -120, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, updateFreq = 10, pos = 0, width = 2, orientation = 0, ch_range = 1, peakLag = 0, index = -1, lag = 0.2, t1_amp = 0, t2_amp = 0, t3_amp = 0, t4_amp = 0, t5_amp = 0, t6_amp = 0, t7_amp = 0, ch8_amp = 0, matrix_ramp = 0, gate = 1, free = 1, xFade = 1|
			var sig, sig0, sig8, envgate, envpause, cross;

			sig = In.ar(in, 1); //PinkNoise.ar(0.2); // In.ar(in, 1);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = PanAz.ar(9, sig, pos.lag(lag), 1, width, orientation);

			sig = sig * envgate * envpause;
			sig = sig * amp.dbamp.lag(1);
			sig0 = [sig[0]*t1_amp.dbamp.lag(0.2), sig[0]*t2_amp.dbamp.lag(0.2), sig[0]*t3_amp.dbamp.lag(0.2), sig[0]*t4_amp.dbamp.lag(0.2), sig[0]*t5_amp.dbamp.lag(0.2), sig[0]*t6_amp.dbamp.lag(0.2), sig[0]*t7_amp.dbamp.lag(0.2)];
			sig8 = [sig[1], sig[2], sig[3], sig[4], sig[5], sig[6], sig[7], sig[8]];

			XOut.ar(out, cross, sig0); //salida directa a un bus
			XOut.ar(out+(7*ch_range), cross, sig8*ch8_amp.dbamp.lag); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TPan_piano9",
			type: \fx,
			main: \pos,
			sliders:[
				\pos -> ControlSpec(0, 2, \lin, 0.001, 0, \pos),
				\width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag),
				\t1_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\t2_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\t3_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\t4_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\t5_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\t6_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\t7_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\t8_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\ch8_amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			]
		))

	}
}


/*a = Synth(\tpan9_piano)
a.set(\pos, 0.2)
a.set(\ch_range, 2)
a.set(\t1_amp, -20)
a.set(\t2_amp, 3)
a.set(\t3_amp, -6)
a.set(\t4_amp, 6)
a.set(\t5_amp, 5)
a.set(\t6_amp, -2)
a.set(\t7_amp, 10)*/

