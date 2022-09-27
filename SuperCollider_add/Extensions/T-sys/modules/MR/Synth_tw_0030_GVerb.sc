Synth_tw_0030_GVerb : Tmodule2 {

	*def
	{


	^SynthDef(\synth_tw_0030_GVerb, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, f1 = 1, f2 = 0.05, f3 = 0.2, f4 = 3, f5 = 5e-3, f6 = 5e-4,  amp = -20, roomsize = 80, revtime = 3, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			a=LFPar;

			sig = GVerb.ar(VarSaw.ar(a.ar(f1,0,5,a.ar([f2,f2-0.01],0,50,160).round(50)),0,a.ar(f3,0,0.5,a.ar(f4,0,0.2,0.5)))/8,roomsize,revtime); //#SuperCollider

			sig =  Mix.new(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "synth_tw_0030_GVerb",
			type: \gen,
			main: \f1,
			sliders: [
				\f1 -> ControlSpec(0, 20, \lin, 0.001, 1, \fq),
				\f2 -> ControlSpec(0, 20, \lin, 0.001, 0.05, \fq),
				\f3 -> ControlSpec(0, 20, \lin, 0.001, 0.2, \fq),
				\f4 -> ControlSpec(0, 20, \lin, 0.001, 3, \fq),
				\roomsize -> ControlSpec(0.1, 80,\lin, 0.1,80,\roomsize),
				\revtime -> ControlSpec(0.1, 100,\lin, 0.1,3,\revtime),
/*				\f5 -> ControlSpec(1e-3, 20, \lin, 0.001, 5e-3, \fq),
				\f6 -> ControlSpec(1e-4, 200, \lin, 0.001, 5e-4, \fq),*/
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


/*//--tweet0045
play{a=SinOsc;
	a.ar(a.ar(a.ar(0.11)),a.ar(a.ar(95*a.ar(0.01,0,1,1),0,a.ar(5e-3,0,50),100),a.ar([98,97]),pi+a.ar(5e-4))).tanh}//#SuperCollider*/