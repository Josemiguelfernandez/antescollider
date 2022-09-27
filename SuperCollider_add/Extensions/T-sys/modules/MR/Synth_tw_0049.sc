Synth_tw_0049 : Tmodule2 {

	*def
	{


	^SynthDef(\Synth_tw_0049, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, f1 = 9, f2 = 3, f3 = 9, f4 = 99, f5 = 33, f6 = 5e-4,  amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			// a=SinOsc;

			sig = Splay.ar(SinOsc.ar(f1,SinOsc.ar(midicps((Sweep.ar(0,(33..3))%128&(Sweep.ar(0,(3..9))%(LFSaw.ar(f2)*f3+f4)))+f5),0,pi)))/3;

			sig =  Mix.new(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Synth_tw_0049",
			type: \gen,
			main: \f1,
			sliders: [
				\f1 -> ControlSpec(0, 50, \lin, 0.001, 9, \fq),
				\f2 -> ControlSpec(0, 200, \lin, 0.01, 3, \fq),
				// \f3 -> ControlSpec(20, 20000, \exp, 0, 9, \Hz),
				\f2 -> ControlSpec(0, 200, \lin, 0.01, 9, \fq),

				\f4 -> ControlSpec(0, 2000, \lin, 0.001, 99, \fq),
				\f5 -> ControlSpec(0.001, 200, \lin, 0.001, 33, \fq),
				// \f6 -> ControlSpec(1e-4, 200, \lin, 0.001, 5e-4, \fq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


//--tweet0049
/*play{
	Splay.ar(SinOsc.ar(9,SinOsc.ar(midicps((Sweep.ar(0,(33..3))%128&(Sweep.ar(0,(3..9))%(LFSaw.ar(3)*9+99)))+33),0,pi)))/3}//#SuperCollider*/