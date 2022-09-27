Synth_tw_0045 : Tmodule2 {

	*def
	{


		^SynthDef(\Synth_tw_0045, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, f1 = 0.11, f2 = 95, f3 = 97, f4 = 0.01, f5 = 5e-3, f6 = 5e-4,  amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			a=SinOsc;

			sig = a.ar(a.ar(a.ar(f1)),a.ar(a.ar(f2*a.ar(f4,0,1,1),0,a.ar(f5,0,50),100),a.ar([f3+1,f3]),pi+a.ar(f6))).tanh;

			// sig =  Mix.new(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Synth_tw_0045",
			type: \gen,
			main: \f1,
			sliders: [
				\f1 -> ControlSpec(0, 20, \lin, 0.001, 0.11, \fq),
				\f2 -> ControlSpec(0, 200, \lin, 0.01, 95, \fq),
				\f3 -> ControlSpec(20, 20000, \exp, 0, 97, \Hz),
				\f4 -> ControlSpec(0, 20, \lin, 0.001, 0.01, \fq),
				\f5 -> ControlSpec(1e-3, 20, \lin, 0.001, 5e-3, \fq),
				\f6 -> ControlSpec(1e-4, 200, \lin, 0.001, 5e-4, \fq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


/*//--tweet0045
play{a=SinOsc;
	a.ar(a.ar(a.ar(0.11)),a.ar(a.ar(95*a.ar(0.01,0,1,1),0,a.ar(5e-3,0,50),100),a.ar([98,97]),pi+a.ar(5e-4))).tanh}//#SuperCollider*/