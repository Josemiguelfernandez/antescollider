TStresonLfo : Tmodule2 {

	*def
	{


	^SynthDef(\tStresonlfo, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, reson = 0.9, lfofreq = 0.1, freqmin = 280, freqmax = 377, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = Streson.ar(sig * 0.9, LinExp.kr(LFCub.kr(lfofreq, 0.5*pi), -1, 1, freqmin, freqmax).reciprocal, reson, 0.3)*amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tStresonlfo",
			type: \fx,
			main: \lfofreq,
			sliders: [
				\lfofreq -> ControlSpec(0.0, 5.0, \lin, 0.001, 0.1, \lfofreq),
				\freqmin -> ControlSpec(0, 5000, \lin, 0, 280, \freqmin),
				\freqmax -> ControlSpec(0, 5000, \lin, 0.1, 377, \freqmax),
				\reson -> ControlSpec(0, 1, \lin, 0.001, 0.1, \reson),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}



