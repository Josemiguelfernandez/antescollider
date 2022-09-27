Pan_to_2 : Tmodule2  {


	*def
	{


	^SynthDef(\Pan_to_2, {|in, out = 0, out2,  gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, updateFreq = 10  peakLag = 0, lag = 0,  pan2_pos = -1, matrix_ramp = 0, gate = 1|
			var sig, sig1, sig2, envgate, envpause, cross, split;

			sig = In.ar(in, 2);

			envgate = EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14);

			sig1 = Pan2.ar(sig[0], pan2_pos);
			sig2 = Pan2.ar(sig[1], pan2_pos);

			sig1 = sig1 * envgate * amp.dbamp.lag(0.2);
			sig2 = sig2 * envgate * amp.dbamp.lag(0.2);

			Out.ar(out, [sig1[0], sig2[0]]); //salida 1
			Out.ar(out2, [sig2[1], sig2[1]]); //salidas 2

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Pan_to_2",
			type: \fx,
			main: \pos,
			sliders:[
				\pan2_pos -> ControlSpec(-1, 1, \lin, 0.001, -1, \pan2_pos),
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

