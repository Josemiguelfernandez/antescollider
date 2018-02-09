TDFM1 : Tmodule2 {

	*def
	{
		^SynthDef(\tDFM1, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freq = 1000, res = 0.1, inputgain = 1, type = 0, noiselevel = 0.0003, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = DFM1.ar(sig, freq, res, inputgain, type, noiselevel)* amp.dbamp.lag;
			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tDFM1",
			type: \fx,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 15000, \exp, 0.1, 1000, \Hz),
				\res -> ControlSpec(0.001, 2, \lin, 0.001, 0.1, \res),
				\noiselevel -> ControlSpec(0.0001, 0.01, \lin, 0.0001, 0.0003, \noiselevel),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			buttons: [
				\type -> [[["Lowp", Color.black, Color.green(0.7)], ["Highp", Color.white, Color.red(0.7)]], \type, 1, \type, 0],
			],
		))
	}
}

