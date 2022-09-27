Synth_tw_Moog06 : Tmodule2 {

	*def
	{


		^SynthDef(\Synth_tw_Moog06, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, lfo = 0.21, lfomul = 600, lfoadd = 990, roomsize = 9, revtime = 9, damping = 1, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = GVerb.ar(MoogFF.ar(ClipNoise.ar*0.4,LFPar.kr(lfo,0,lfomul,lfoadd)),roomsize,revtime,damping);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Synth_tw_Moog06",
			type: \gen,
			main: \lfo,
			sliders: [
				\lfo -> ControlSpec(0, 200, \lin, 0.001, 0.21, \lfo),
				\lfomul -> ControlSpec(1, 10000, \lin, 1, 600, \lfomul),
				\lfoadd -> ControlSpec(0, 10000, \lin, 0.01, 99, \lfoadd),
				\roomsize -> ControlSpec(0.1, 300,\lin, 0.1,9,\roomsize),
				\revtime -> ControlSpec(0.1, 300,\lin, 0.1,9,\revtime),
				\damping -> ControlSpec(0, 1,\lin, 0.001,1,\damping),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


/*r{loop{x={

	GVerb.ar(MoogFF.ar(ClipNoise.ar*0.4,LFPar.kr({0.3.rand}!2,0,600,990)),9,9,1)

}.play(s,0,19);

	3.wait;x.release}}.play//#SuperCollider*/