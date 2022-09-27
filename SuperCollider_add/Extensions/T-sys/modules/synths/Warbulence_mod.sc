Warbulence_mod : Tmodule2 {

	*def
	{
		^SynthDef(\Warbulence_mod, {|out = 0, freq=200, amp=0, pan=0, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1, exp_line = 10|
			var sig, envgate, envpause, bufpos, env, f, r, n, a, g;

			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);
			// env = EnvGen.kr(Env.adsr(releaseTime:0.5, decayTime:0.1), gate);

			/*	f = rrand(60, 96).midicps;
			a = min(1, (500/f));
			a.postln;*/
			// r = XLine.kr(10,1.85, 25.6);
			n = 12;
			sig = Mix.arFill(20, { arg i;
				g = n.asFloat.rand + 1;
				// g.postln;
				Pan2.ar(
					FSinOsc.ar(freq*g, 0, SinOsc.kr( exp_line * rrand(0.9,1.1),2pi.rand,0.08,-0.04).max(0)) * (2/g),
					1.0.rand2
				)
			}) ;

			sig = Mix.fill(30, { CombN.ar(sig, 0.3, [rrand(0.1,0.3),rrand(0.1,0.3)], 8) }) * 0.3;

			sig = sig * envgate * envpause;
			Out.ar(out, sig*amp.dbamp.lag);
		}).load;
	}
	*metadata
	{
		^(metadata: (
			synthdefname: "Warbulence_mod",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 20000, \exp, 0, 200, \Hz),
				\exp_line-> ControlSpec(0, 100, \exp, 0.001, 10, \exp_line),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \dB)
			],
		))
	}

}

