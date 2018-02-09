TPan_1in_piano_kubus : Tmodule2  {


	*def
	{


	^SynthDef(\TPan_1in_piano_kubus, {|in, out = 0, outbus, outgain = -120, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, updateFreq = 10, pos = 0, width = 2, orientation = 0, ch_range = 1, peakLag = 0, index = -1, lag = 0, t1_amp = 0, t2_amp = 0, t3_amp = 0, t4_amp = 0, t5_amp = 0, t6_amp = 0, t7_amp = 0, ch8_amp = 0, pan2_pos = -1, matrix_ramp = 0, gate = 1, free = 1, xFade = 1|
			var sig, sig0, sig8, envgate, envpause, cross, split;

			sig = In.ar(in, 1); //PinkNoise.ar(0.2); // In.ar(in, 1);

			// sig = PinkNoise.ar(0.2);

			// envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			// envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			envgate = EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14);

			// cross = xFade * envgate * envpause;

			sig = Pan2.ar(sig, pan2_pos);

			sig = sig * envgate * amp.dbamp.lag(1);

			sig0 = [sig[0]*t1_amp.dbamp.lag(0.2), sig[0]*t2_amp.dbamp.lag(0.2), sig[0]*t3_amp.dbamp.lag(0.2), sig[0]*t4_amp.dbamp.lag(0.2), sig[0]*t5_amp.dbamp.lag(0.2), sig[0]*t6_amp.dbamp.lag(0.2), sig[0]*t7_amp.dbamp.lag(0.2)];

			sig8 = PanAz.ar(8, sig[1], pos.lag(lag), 1, width, orientation);

			// sig8 = [sig[1], sig[2], sig[3], sig[4], sig[5], sig[6], sig[7], sig[8]];

			Out.ar(out, sig0); //salida a transductores
			Out.ar(out+7+(8*(ch_range-1)), sig8*ch8_amp.dbamp.lag); //salidas a pan 8 chnls
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TPan_1in_piano_kubus",
			type: \fx,
			main: \pos,
			sliders:[
				\pan2_pos -> ControlSpec(-1, 1, \lin, 0.001, -1, \pan2_pos),
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


/*a = Synth(\tPan_1in_piano_kubus)
a.set(\pan2_pos, 1)

b = Synth(\tPan_1in_piano_kubus)

b.set(\pan2_pos, 1)
b.set(\ch_range, 1)

b.set(\pos, 0.1)

a.set(\pos, 0.2)
a.set(\ch_range, 2)
a.set(\t1_amp, -20)
a.set(\t2_amp, 3)
a.set(\t3_amp, -6)
a.set(\t4_amp, 6)
a.set(\t5_amp, 5)
a.set(\t6_amp, -2)
a.set(\t7_amp, 10)*/

