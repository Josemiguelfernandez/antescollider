TSinOsc : Tmodule2 {
	*def
	{
	^SynthDef(\TSinOsc, {|out = 0, amp = -6, freq = 300, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause;
			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);
			sig = SinOsc.ar(freq.lag, 0,  amp.dbamp.lag);
			sig = sig * envgate * envpause;
			Out.ar(out, sig);
			}).load;
	}
	*metadata
	{
		^(metadata: (
			synthdefname: "TSinOsc",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 20000, \exp, 0, 300, \Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \dB)
			]
		))
	}
}



// ~part = {Synth(TMFxSin2.synthdefname)} ! 10


// ~part[1].set(\freq, 600)

/*Pbind(\instrument, TMFxSin2.synthdefname,
	\freq, Pseq( (1..30) * 200, inf),
	\sustain, Pn(Pseries(0.2, 0.01, 30), inf), // sustain cada vez mas largo en relacion al numero de armonico
	\in_ramp, 0.01,
	\out_ramp, 0.5,
	\amp, -30,
	\out, 0,
	\dur, 0.1).play;


Pbind(\instrument, TMFxSin2.synthdefname,
	\freq, Pseq( (1..30) * 220, inf),
	\sustain, Pn(Pseries(0.2, 0.01, 30), inf), // sustain cada vez mas largo en relacion al numero de armonico
	\in_ramp, 0.01,
	\out_ramp, 0.5,
	\amp, -30,
	\out, 1,
	\dur, 0.15).play;

Pbind(\instrument, TMFxSin2.synthdefname,
	\freq, Pseq( (1..30) * 230, inf),
	\sustain, Pn(Pseries(0.2, 0.01, 30), inf), // sustain cada vez mas largo en relacion al numero de armonico
	\in_ramp, 0.01,
	\out_ramp, 0.5,
	\amp, -30,
	\out, 1,
	\dur, 0.13).play;

Pbind(\instrument, TMFxSin2.synthdefname,
	\freq, Pseq( (1..30) * 235, inf),
	\sustain, Pn(Pseries(0.2, 0.01, 30), inf), // sustain cada vez mas largo en relacion al numero de armonico
	\in_ramp, 0.01,
	\out_ramp, 0.5,
	\amp, -30,
	\out, 0,
	\dur, 0.12).play;

Pbind(\instrument, TMFxSin2.synthdefname,
	\freq, Pseq( (1..30) * 270, inf),
	\sustain, Pn(Pseries(0.2, 0.01, 30), inf), // sustain cada vez mas largo en relacion al numero de armonico
	\in_ramp, 0.01,
	\out_ramp, 0.5,
	\amp, -30,
	\out, 1,
	\dur, 0.13).play;*/
