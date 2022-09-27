TAdCVerb_order3 : Tmodule2 {

	classvar n = 16; // numero de inputs

	*def
	{



		^SynthDef(\TAdCVerb_order3, {|in = 0, inbus, ingain = 0, out = 0, outbus, amp = 0, outgain = -120, revTime = 10, hfDamping = 0.1, predelay = 0.02, numCombs = 8, numAllpasses = 4, inFilter = 0.6, leakCoeff = 0.995, combScale = 1, apScale = 1, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = In.ar(in, 16); // + (In.ar(inbus, 8) * ingain);

			sig = AdCVerb.ar(sig, revTime: revTime.lag(0.5),
				hfDamping: hfDamping.lag(0.5),
				nOuts: 2,         // fixed number of decorrelated output channels
				predelay: predelay.lag(0.5),
				numCombs:     8,     // fixed number - increase for more density
				numAllpasses: 4,     // fixed number - increase for more diffuseness
				inFilter: inFilter,
				leakCoeff: leakCoeff,
				combScale: combScale.lag(0.5), // lagged to
				apScale: apScale.lag(0.5))*amp.dbamp.lag;


			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}
	// TAdCVerb8.def
	*metadata
	{
		^(metadata: (
			synthdefname: "TAdCVerb_order3",
			type: \fx,
			main: \revTime,
			sliders: [
				\revTime -> ControlSpec(0.01, 100,\exp, 0.01,1,\revTime),
				\hfDamping -> ControlSpec(0, 1,\lin, 0.001,0.1,\hfDamping),
				\predelay -> ControlSpec(0.0, 0.2,\amp, 0.001,0.02,\predelay),
				\inFilter -> ControlSpec(-0.99, 0.99,\lin, 0.001,0.6,\inFilter),
				\leakCoeff -> ControlSpec(0.8, 1.0,\lin, 0.001,0.995,\leakCoeff),
				\combScale -> ControlSpec(0.1, 2,\exp, 0.01,1,\combScale),
				\apScale -> ControlSpec(0.1, 2,\exp, 0.01,1,\apScale),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


