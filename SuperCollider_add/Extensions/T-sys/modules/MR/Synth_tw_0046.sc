Synth_tw_0046 : Tmodule2 {

	*def
	{


	^SynthDef(\synth_tw_0046, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, f1 = 50, c1 = 97, f2 = 8000, f3 = 0.01, f4 = 0.01, f5 = 5e-3, f6 = 5e-4,  amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b, c, l;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			a=LFTri;

			sig = GVerb.ar(Mix(Limiter.ar(BRF.ar(a.ar(f1,1e-4),a.ar(a.ar([1.01,1.0111])*a.ar(f2)*1e3+4e3,55),a.ar(f3)*3))))/9;

			sig =  Mix.new(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "synth_tw_0046",
			type: \gen,
			main: \f1,
			sliders: [
				\f1 -> ControlSpec(0, 200, \lin, 0.001, 50, \fq),
				// \c1 -> ControlSpec(0, 200, \lin, 0.01, 97, \fq),
				\f2 -> ControlSpec(0, 10000, \lin, 0.001, 8000, \fq),
				\f3 -> ControlSpec(0.001, 20, \lin, 0.001, 0.01, \fq),
				// \f6 -> ControlSpec(1e-4, 200, \lin, 0.001, 5e-4, \fq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


//--tweet0046
/*play{
	a=LFTri;
	GVerb.ar(Mix(Limiter.ar(BRF.ar(a.ar(50,1e-4),a.ar(a.ar([1.01,1.0111])*a.ar(8e3)*1e3+4e3,55),a.ar(0.01)*3))))/9}//#SuperCollider*/