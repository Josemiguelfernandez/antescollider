TPan_8in_piano : Tmodule2  {


	*def
	{


		^SynthDef(\TPan_8in_piano, {|in, out = 0, outbus, outgain = -120, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, updateFreq = 10, pos0 = 0, pos1 = 0, pos2 = 0, pos3 = 0, pos4 = 0, pos5 = 0, pos6 = 0, pos7 = 0, width = 2, orientation = 0, ch_range = 1, peakLag = 0, index = -1, lag = 0, t1_amp = 0, t2_amp = 0, t3_amp = 0, t4_amp = 0, t5_amp = 0, t6_amp = 0, t7_amp = 0, ch8_amp = 0, matrix_ramp = 0, gate = 1, free = 1, xFade = 1|
			var sig, sig0, sig0_0, sig0_1, sig0_2, sig0_3, sig0_4, sig0_5, sig0_6, sig0_7, sig8, sig8_0, sig8_1, sig8_2, sig8_3, sig8_4, sig8_5, sig8_6, sig8_7, envgate, envpause, cross, panner0, panner1, panner2, panner3, panner4, panner5, panner6, panner7;

			sig = In.ar(in, 8); //PinkNoise.ar(0.2); // In.ar(in, 1);

			// sig = [PinkNoise.ar(0.2), SinOsc.ar(300, 0, 0.2), PinkNoise.ar(0.01), PinkNoise.ar(0.01), PinkNoise.ar(0.01), PinkNoise.ar(0.01), PinkNoise.ar(0.01), PinkNoise.ar(0.01)];

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			panner0 = PanAz.ar(9, sig[0], pos0.lag(lag), 1, width, orientation);
			panner1 = PanAz.ar(9, sig[1], pos1.lag(lag), 1, width, orientation);
			panner2 = PanAz.ar(9, sig[2], pos2.lag(lag), 1, width, orientation);
			panner3 = PanAz.ar(9, sig[3], pos3.lag(lag), 1, width, orientation);
			panner4 = PanAz.ar(9, sig[4], pos4.lag(lag), 1, width, orientation);
			panner5 = PanAz.ar(9, sig[5], pos5.lag(lag), 1, width, orientation);
			panner6 = PanAz.ar(9, sig[6], pos6.lag(lag), 1, width, orientation);
			panner7 = PanAz.ar(9, sig[7], pos7.lag(lag), 1, width, orientation);

			sig = sig * envgate * envpause;
			sig = sig * amp.dbamp.lag(1);


			sig0_0 = [panner0[0]*t1_amp.dbamp.lag(0.2), panner0[0]*t2_amp.dbamp.lag(0.2), panner0[0]*t3_amp.dbamp.lag(0.2), panner0[0]*t4_amp.dbamp.lag(0.2), panner0[0]*t5_amp.dbamp.lag(0.2), panner0[0]*t6_amp.dbamp.lag(0.2), panner0[0]*t7_amp.dbamp.lag(0.2)];

			sig0_1 = [panner1[0]*t1_amp.dbamp.lag(0.2), panner1[0]*t2_amp.dbamp.lag(0.2), panner1[0]*t3_amp.dbamp.lag(0.2), panner1[0]*t4_amp.dbamp.lag(0.2), panner1[0]*t5_amp.dbamp.lag(0.2), panner1[0]*t6_amp.dbamp.lag(0.2), panner1[0]*t7_amp.dbamp.lag(0.2)];

			sig0_2 = [panner2[0]*t1_amp.dbamp.lag(0.2), panner2[0]*t2_amp.dbamp.lag(0.2), panner2[0]*t3_amp.dbamp.lag(0.2), panner2[0]*t4_amp.dbamp.lag(0.2), panner2[0]*t5_amp.dbamp.lag(0.2), panner2[0]*t6_amp.dbamp.lag(0.2), panner2[0]*t7_amp.dbamp.lag(0.2)];

			sig0_3 = [panner3[0]*t1_amp.dbamp.lag(0.2), panner3[0]*t2_amp.dbamp.lag(0.2), panner3[0]*t3_amp.dbamp.lag(0.2), panner3[0]*t4_amp.dbamp.lag(0.2), panner3[0]*t5_amp.dbamp.lag(0.2), panner3[0]*t6_amp.dbamp.lag(0.2), panner3[0]*t7_amp.dbamp.lag(0.2)];

			sig0_4 = [panner4[0]*t1_amp.dbamp.lag(0.2), panner4[0]*t2_amp.dbamp.lag(0.2), panner4[0]*t3_amp.dbamp.lag(0.2), panner4[0]*t4_amp.dbamp.lag(0.2), panner4[0]*t5_amp.dbamp.lag(0.2), panner4[0]*t6_amp.dbamp.lag(0.2), panner4[0]*t7_amp.dbamp.lag(0.2)];

			sig0_5 = [panner5[0]*t1_amp.dbamp.lag(0.2), panner5[0]*t2_amp.dbamp.lag(0.2), panner5[0]*t3_amp.dbamp.lag(0.2), panner5[0]*t4_amp.dbamp.lag(0.2), panner5[0]*t5_amp.dbamp.lag(0.2), panner5[0]*t6_amp.dbamp.lag(0.2), panner5[0]*t7_amp.dbamp.lag(0.2)];

			sig0_6 = [panner6[0]*t1_amp.dbamp.lag(0.2), panner6[0]*t2_amp.dbamp.lag(0.2), panner6[0]*t3_amp.dbamp.lag(0.2), panner6[0]*t4_amp.dbamp.lag(0.2), panner6[0]*t5_amp.dbamp.lag(0.2), panner6[0]*t6_amp.dbamp.lag(0.2), panner6[0]*t7_amp.dbamp.lag(0.2)];

			sig0_7 = [panner7[0]*t1_amp.dbamp.lag(0.2), panner7[0]*t2_amp.dbamp.lag(0.2), panner7[0]*t3_amp.dbamp.lag(0.2), panner7[0]*t4_amp.dbamp.lag(0.2), panner7[0]*t5_amp.dbamp.lag(0.2), panner7[0]*t6_amp.dbamp.lag(0.2), panner7[0]*t7_amp.dbamp.lag(0.2)];

			sig0 = Mix.new([sig0_0, sig0_1, sig0_2, sig0_3, sig0_4, sig0_5, sig0_6, sig0_7]);

			sig8_0 = [panner0[1], panner0[2], panner0[3], panner0[4], panner0[5], panner0[6], panner0[7], panner0[8]];
			sig8_1 = [panner1[1], panner1[2], panner1[3], panner1[4], panner1[5], panner1[6], panner1[7], panner1[8]];
			sig8_2 = [panner2[1], panner2[2], panner2[3], panner2[4], panner2[5], panner2[6], panner2[7], panner2[8]];
			sig8_3 = [panner3[1], panner3[2], panner3[3], panner3[4], panner3[5], panner3[6], panner3[7], panner3[8]];
			sig8_4 = [panner4[1], panner4[2], panner4[3], panner4[4], panner4[5], panner4[6], panner4[7], panner4[8]];
			sig8_5 = [panner5[1], panner5[2], panner5[3], panner5[4], panner5[5], panner5[6], panner5[7], panner5[8]];
			sig8_6 = [panner6[1], panner6[2], panner6[3], panner6[4], panner6[5], panner6[6], panner6[7], panner6[8]];
			sig8_7 = [panner7[1], panner7[2], panner7[3], panner7[4], panner7[5], panner7[6], panner7[7], panner7[8]];

			sig8 = Mix.new([sig8_0, sig8_1, sig8_2, sig8_3, sig8_4, sig8_5, sig8_6, sig8_7]);

			XOut.ar(out, cross, sig0); //salida directa a un bus
			XOut.ar(out+(7*ch_range), cross, sig8*ch8_amp.dbamp.lag); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TPan_8in_piano",
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


/*a = Synth(\tpan_8in_piano)
a.set(\pos0, 1.77)
a.set(\pos1, 0.88)
a.set(\ch_range, 2)
a.set(\t1_amp, -20)
a.set(\t2_amp, 3)
a.set(\t3_amp, -6)
a.set(\t4_amp, 6)
a.set(\t5_amp, 5)
a.set(\t6_amp, -2)
a.set(\t7_amp, 10)
a.free*/
