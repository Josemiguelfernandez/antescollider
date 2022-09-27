TSVF : Tmodule2 {

	*def
	{


	^SynthDef(\tSVF, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, sin_freq = 0.1, sin_mul = 1550, sin_add = 1800, reson = 0.1, low=0.25, band=0.0, high=0.0, notch=0.0, peak=0.0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = SVF.ar(sig, SinOsc.kr(SinOsc.kr(sin_freq), 1.5pi, sin_mul, sin_add), reson, low, band, high, notch, peak)*amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tSVF",
			type: \fx,
			main: \sin_freq,
			sliders: [
				\sin_freq -> ControlSpec(0.0, 15.0, \lin, 0.001, 0.1, \sin_freq),
				\sin_mul -> ControlSpec(0, 5000, \lin, 0, 1550, \sin_mul),
				\sin_add -> ControlSpec(0, 5000, \lin, 0.1, 1800, \sin_add),
				\reson -> ControlSpec(0, 1, \lin, 0.001, 0.1, \reson),
				\low -> ControlSpec(0, 1, \lin, 0.001, 0.25, \low),
				\band -> ControlSpec(0, 1, \lin, 0.001, 0.0, \band),
				\high -> ControlSpec(0, 1, \lin, 0.001, 0.0, \high),
				\notch -> ControlSpec(0, 1, \lin, 0.001, 0.0, \notch),
				\peak -> ControlSpec(0, 1, \lin, 0.001, 0.0, \peak),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}









