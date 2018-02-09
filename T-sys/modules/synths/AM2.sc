AM2 : Tmodule2  {

	*def
	{


	^SynthDef(\AM2, {|in, out = 0, outbus, outgain = -120, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, freq = 440, modfreq = 5, matrix_ramp = 0, gate = 1, free = 1, xFade = 1|

			var sig, envgate, envpause, carrier, modulator, env;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			// cross = xFade * envgate * envpause;
			modulator = modulator = LFSaw.kr(modfreq).range(0, 1); // provides amplitude modulation for wobble
			sig = LFSaw.ar(freq: freq, mul: modulator); // provides triangle wave for an interesting harmonic


			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "AM2",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 10000, \exp, 0, 440,\freq),
				\modfreq -> ControlSpec(0, 50, \lin, 0, 5,\modfreq),
/*				\decay -> ControlSpec(0.01, 10, \lin, 0, 3,\decay),
				\ratio -> ControlSpec(0.1, 4, \exp, 0, 2,\ratio),*/
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


//
// (
// SynthDef("AM2", { arg freq = 440, modfreq = 12, amp = 0.5, attack = 0.01, dur = 1, pos = 0;
// 	var carrier, modulator, env;
// 	modulator = LFSaw.kr(modfreq).range(0, 1);
// 	carrier = LFSaw.ar(freq: freq, mul: modulator);
// 	env = Env.perc(attackTime: attack, releaseTime: dur - attack, level: amp).kr(2);
// 	carrier = carrier * env;
// 	Out.ar(0, Pan2.ar(carrier, pos))
// }).add;
// )

/*(
SynthDef("Triangular AM", { arg freq = 440, modfreq = 5, amp = 0.5, attack = 0.01, release = 0.1, pos = 0, gate = 1;
	var carrier, modulator, env;
	modulator = SinOsc.kr(modfreq).range(0, 1); // provides amplitude modulation for wobble
	carrier = LFTri.ar(freq: freq, mul: modulator); // provides triangle wave for an interesting harmonic
	env = Env.asr(
		attackTime: attack,
		sustainLevel: amp,
		releaseTime: release).kr(doneAction: 2, gate: gate);
	carrier = carrier * env;
	Out.ar(0, Pan2.ar(carrier, pos))
}).add;
)*/