TFriction : Tmodule2 {

	*def
	{


		^SynthDef(\tFriction, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, friction = 0.5, spring = 0.414, damp = 0.313, mass = 0.1, beltmass = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = Friction.ar(sig, friction, damp, mass, beltmass)*amp.dbamp.lag;
			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tFriction",
			type: \fx,
			main: \spring,
			sliders: [
				\friction -> ControlSpec(0.00001, 0.03, \lin, 0.001, 0.5, \friction),
				\spring -> ControlSpec(0, 1, \lin, 0.001, 0.414, \spring),
				\damp -> ControlSpec(0, 3.5, \lin, 0.001, 0.313, \damp),
				\mass -> ControlSpec(0.2, 10, \lin, 0.001, 0.1, \mass),
				\beltmass -> ControlSpec(0.14, 1, \lin, 0.001, 1, \beltmass),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


