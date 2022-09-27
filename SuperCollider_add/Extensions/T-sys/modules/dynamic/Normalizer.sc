Norm : Tmodule2 {

	*def
	{
		^SynthDef(\Norm, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, modfreq = 1, ampmod = 1, coef = 0.995, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1, lev = 1, dur=0.01|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1); // + (In.ar(inbus, 1) * ingain);

			sig	= Normalizer.ar(sig, lev, dur);

			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig* amp.dbamp.lag ); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Norm",
			type: \fx,
			main: \amp,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

