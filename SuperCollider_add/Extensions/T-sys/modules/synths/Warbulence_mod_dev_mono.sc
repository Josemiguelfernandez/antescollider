Warbulence_mod_dev_mono : Tmodule2 {
	classvar num = 10;
	*def
	{
		^SynthDef(\Warbulence_mod_dev_mono, {|out = 0, freq=200, amp=0, pan=0, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1, exp_line = 10, decaytime = 8|
			var sig, envgate, envpause, bufpos, env, devs, n;

			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);
			// env = EnvGen.kr(Env.adsr(releaseTime:0.5, decayTime:0.1), gate);

			/*	f = rrand(60, 96).midicps;
			a = min(1, (500/f));
			a.postln;*/
			// r = XLine.kr(10,1.85, 25.6);
			n = 12;

			devs = \devs.kr(Array.fill(num, { n.asFloat.rand + 1 }));

			sig = Mix.arFill(num, { arg i;
				// g = n.asFloat.rand + 1;
				// g.postln;
				// Pan2.ar(
				// FSinOsc.ar(freq*devs[i], 0, SinOsc.kr( exp_line * rrand(0.9,1.1),2pi.rand,0.08,-0.04).max(0)) * (2/devs[i]),
				FSinOsc.ar(freq*devs[i], 0, SinOsc.kr( exp_line * rrand(0.9,1.1),2pi.rand,0.08,-0.04).max(0)) * (2/devs[i])
				// )
			}) ;

			sig = Mix.fill(15, { CombN.ar(sig, 0.3, rrand(0.1,0.3), decaytime) }) * 0.3; //  [rrand(0.1,0.3),rrand(0.1,0.3)],

			sig = sig * envgate * envpause;
			Out.ar(out, sig*amp.dbamp.lag);
		}).load;
	}
	// Warbulence_mod_dev_mono.def
	*metadata
	{
		^(metadata: (
			synthdefname: "Warbulence_mod_dev_mono",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 20000, \exp, 0, 200, \Hz),
				\exp_line-> ControlSpec(0, 100, \lin, 0.01, 10, \exp_line),
				\decaytime-> ControlSpec(0, 50, \lin, 0.01, 8, \decaytime),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \dB)
			],
			multisliders: [
				\devs -> [ControlSpec(0, 13, \lin, 0.01, 0, \devs),  num],
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\devs -> [\multisliders, \devs, {
					var n = 12, res; //en midi quart de ton
					 res = n.asFloat.rand + 1;
					res.postln;
				}, num],
			]
		))
	}

}


