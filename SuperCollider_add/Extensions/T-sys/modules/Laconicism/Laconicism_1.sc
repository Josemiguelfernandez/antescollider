Laconicism_1 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_1, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, speed = 5, round = 10, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, k;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = Limiter.ar(Splay.ar(Integrator.ar(LFDNoise0.ar(speed!3,k=Duty.ar(32/speed,0,Dseq([0.05,Drand([0.04,0.08],1)],inf))).round(k/round)).sin.sqrt.tanh,0.3));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_1",
			type: \gen,
			main: \speed,
			sliders: [
				\speed -> ControlSpec(0.1, 10000, \exp, 0, 5,\speed),
				\round -> ControlSpec(1, 10, \lin, 0, 10,\round),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}
