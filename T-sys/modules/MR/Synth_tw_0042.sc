Synth_tw_0042 : Tmodule2 {

	*def
	{


	^SynthDef(\synth_tw_0042, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, f1 = 5, c1 = 97, f2 = 98, f3 = 97, f4 = 0.01, f5 = 5e-3, f6 = 5e-4,  amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, a, b, c, l;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			c=[c1,c1+2];

			l=3**9;

			a=LocalBuf(l,2).clear;

			sig = BufWr.ar(Saw.ar(c/f1),a,BPF.ar(VarSaw.ar(c),f2,0.1)*l);
			sig = PlayBuf.ar(2,a,1/4,1,0,1);

			sig =  Mix.new(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "synth_tw_0042",
			type: \gen,
			main: \f1,
			sliders: [
				\f1 -> ControlSpec(0, 20, \lin, 0.001, 5, \fq),
				\c1 -> ControlSpec(0, 200, \lin, 0.01, 97, \fq),
				\f2 -> ControlSpec(0, 200, \lin, 0.001, 98, \fq),
/*				\f3 -> ControlSpec(1e-3, 20, \lin, 0.001, 5e-3, \fq),
				\f6 -> ControlSpec(1e-4, 200, \lin, 0.001, 5e-4, \fq),*/
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


/*//--tweet0042


play{c=[97,99];l=3**9;a=LocalBuf(l,2).clear;BufWr.ar(Saw.ar(c/5),a,BPF.ar(VarSaw.ar(c),98,0.1)*l);PlayBuf.ar(2,a,1/4,1,0,1)}//#SuperCollider
*/
