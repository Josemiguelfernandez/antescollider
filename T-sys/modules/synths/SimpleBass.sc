SimpleBass : Tmodule2 {


	*def
	{
	^SynthDef(\SimpleBass, {|out, outbus, outgain = 0, note = 36, fc = 1000, rq = 0.25, bal = 0, amp=0, width=0, matrix_ramp = 0.01, gate = 1, free = 1|
		var sig, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);


			sig = Mix.fill(4, {
				VarSaw.ar((note + {0.1.rand2}).midicps, 0, width, 0.02)
			});
			sig = RLPF.ar(sig, fc, rq).softclip;
			sig = RLPF.ar(sig, fc, rq, amp.dbamp).softclip;
			//x = Balance2.ar(x[0], x[1], bal);
			//x = Pan2.ar(x, -1);
			sig = sig * envgate * envpause;
			Out.ar(out, sig * amp.dbamp); //salida directa a un bus
			//Out.ar(outbus, sig * outgain); //salida a un bus auxiliar

			}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "SimpleBass",
			type: \gen,
			main: \note,
			sliders:  [
				\note -> ControlSpec(24, 60, \lin, 1, 36, \note),
				\fc -> ControlSpec(200, 5000, \exp,0.01,1000,\Hz),
				\rq -> ControlSpec(0.1, 0.7,\lin,0.001,0.25,\rq),
				\bal -> ControlSpec(-1, 1, \lin, 0, 0, \pan),
				\width -> ControlSpec(0, 1, \lin, 0, 0, \width),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB) // db spec acts weird, so use self made one
			]
		))

	}

}
