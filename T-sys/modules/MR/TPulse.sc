TPulse : Tmodule2 {

	*def
	{


	^SynthDef(\tPulse, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freq = 50, width = 0.5, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

			sig = Pulse.ar(freq + [0,1], width, 0.1);

			sig = Mix.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tPulse",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 20000, \exp, 0, 958, \Hz),
				\width -> ControlSpec(0, 1, \lin, 0.001, 0.5, \width),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}







