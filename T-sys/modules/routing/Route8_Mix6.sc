Route8_Mix6 : Tmodule2  {


	*def
	{


		^SynthDef(\Route8_Mix6, {|in, out = 0, outbus, outgain = -120, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, updateFreq = 10, lag = 0.2, pos0 = 0, pos1 = 0.25, pos2 = 0.5, pos3 = 0.75, pos4 = 1, pos5 = 1.25, pos6 = 1.5, pos7 = 1.75, width = 2, orientation = 0, matrix_ramp = 0, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross, panner0, panner1, panner2, panner3, panner4, panner5, panner6, panner7, sum;

			sig = In.ar(in, 8);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = Mix.new(sig);
			sig = sig ! 6;

			sig = sig * envgate * envpause;
			sig = sig * amp.dbamp.lag(1);

			XOut.ar(out, cross, sig); //salida directa a un bus
			// XOut.ar(out+(7*ch_range), cross, sig8*ch8_amp.dbamp.lag); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Route8_Mix6",
			type: \fx,
			main: \pos,
			sliders:[
				\pos0 -> ControlSpec(0, 2, \lin, 0.001, 0.0, \pos0),
				\pos1 -> ControlSpec(0, 2, \lin, 0.001, 0.25, \pos1),
				\pos2 -> ControlSpec(0, 2, \lin, 0.001, 0.5, \pos2),
				\pos3 -> ControlSpec(0, 2, \lin, 0.001, 0.75, \pos3),
				\pos4 -> ControlSpec(0, 2, \lin, 0.001, 1.0, \pos4),
				\pos5 -> ControlSpec(0, 2, \lin, 0.001, 1.25, \pos5),
				\pos6 -> ControlSpec(0, 2, \lin, 0.001, 1.5, \pos6),
				\pos7 -> ControlSpec(0, 2, \lin, 0.001, 1.75, \pos7),
				\width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\lag -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag),
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
