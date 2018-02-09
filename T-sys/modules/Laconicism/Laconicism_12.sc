Laconicism_12 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_12, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, kFreq = 360, kBeat = 2, pFreq = 64, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, i;


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(i=Impulse.ar(_);SinOsc.ar(i.(kBeat).lagud(0,0.4)*kFreq,Integrator.ar(Integrator.ar(i.(pFreq).lag(LFNoise1.ar(2!2,2,2))*99,0.9),0.99).fold2(pi)));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_12",
			type: \gen,
			main: \kFreq,
			sliders: [
				\kFreq -> ControlSpec(10, 3600, \exp, 0, 360,\kFreq),
				\kBeat -> ControlSpec(0.2, 16, \exp, 0, 2,\kBeat),
				\pFreq -> ControlSpec(0.5, 256, \exp, 0, 64,\pFreq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

