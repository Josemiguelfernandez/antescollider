Lpf : Tmodule2 {

	*def
	{



		^SynthDef(\Lpf, {|in, inbus, ingain = 0, out = 0, outbus, amp = 0, freq = 1000, matrix_ramp = 0.05, gate = 1, free = 1, xFade = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8); // + (In.ar(inbus, 2) * ingain);

			sig = LPF.ar(sig, (freq+LFNoise2.kr(0.1, 200)).max(30));

			XOut.ar(out, cross, sig * amp.dbamp.lag); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Lpf",
			type: \fx,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 10000, \exp,0.01,500,\Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

