TCombC2 : Tmodule2 {

	*def
	{
		^SynthDef(\tCombC2, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, att = 0.001, rel = 1, amp = 0, imp = 1, dectime = 0.3, deltime = 0.1, gentime = 3, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			sig = CombC.ar(Decay2.ar(Impulse.ar(imp), att, dectime, sig), 0.2, deltime, gentime);

			sig = sig * envgate * envpause * amp.dbamp.lag;
			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tCombC2",
			type: \fx,
			main: \deltime,
			sliders: [
				\deltime -> ControlSpec(0.001, 0.5,\lin,0.001,0.015,\deltime),
				\dectime -> ControlSpec(0.001, 0.5,\lin,0.001,0.8,\dectime),
				\imp -> ControlSpec(0, 100.0, \lin, 0.01, 0.25, \imp),
				\gentime -> ControlSpec(0.1, 30, \exp, 0.1, 5.2, \gentime),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}