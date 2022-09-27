TCombC : Tmodule2 {

	*def
	{
		^SynthDef(\tCombC, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, maxdelaytime = 0.2, delaytime = 0.2, decaytime = 1, lag = 0.5, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			sig = CombC.ar(sig, maxdelaytime, delaytime.lag(lag), decaytime.lag(lag));

			sig = sig * envgate * envpause * amp.dbamp.lag;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tCombC",
			type: \fx,
			main: \delaytime,
			sliders: [
				\maxdelaytime -> ControlSpec(0.001, 0.5,\lin,0.001,0.2,\maxdeltime),
				\delaytime -> ControlSpec(0.001, 0.5,\lin,0.001,0.2,\deltime),
				\decaytime -> ControlSpec(-10, 20, \lin, 0.01, 1, \decay),
				\lag -> ControlSpec(0.1, 30, \exp, 0.1, 0.5, \lag),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}





	