Synth_tw_TDuty05 : Tmodule2 {

	*def
	{


		^SynthDef(\synth_tw_TDuty05, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, z = 10, f1 = 10, p1 = 99, rq = 0.01, gain = 6,  amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, y;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			y = LFTri.ar(z).abs/z;

			sig = RLPF.ar(TDuty.ar(y,0,y),f1*p1+y,rq,gain+y); //!2

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "synth_tw_TDuty05",
			type: \gen,
			main: \z,
			sliders: [
				\z -> ControlSpec(1, 100, \lin, 1, 10, \z),
				\f1 -> ControlSpec(1, 100, \lin, 1, 10, \z),
				\p1 -> ControlSpec(0, 200, \lin, 0.01, 99, \p1),
				\rq -> ControlSpec(0.0001, 1.0, \lin, 0.01, 1,\rq),
				\gain -> ControlSpec(1, 100, \lin, 0.01, 6, \gain),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


/*//--tweet0005
r{loop{
	z=60.rand+1;
	x={y=LFTri.ar(z).abs/z;
		RLPF.ar(TDuty.ar(y,0,y),z*99+y,0.01,6+y)!2}.play(s,0,z);
	wait(z/3);
	x.release}}.play//#SuperCollider*/