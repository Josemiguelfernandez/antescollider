Bform : Tmodule2 {

	*def
	{
		^SynthDef(\Bform, {|out = 0, buf, numBufs=2, freq=200, formfreq = 70, bwfreq = 200, amp=0, pan=0, rout=0, atk=0.01, sus=1, rel=0.01, c0=1, c1=(-1), in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, bufpos, env;

			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);
			env = EnvGen.kr(Env.adsr(releaseTime:0.5, decayTime:0.1), gate);


			sig = Formant.ar(freq * ((-1..1)/500 + 1), formfreq * env.range(0.01,2), bwfreq);
			// sig = Mix.ar(sig);
			sig = Splay.ar(sig); //spread 8 signals over stereo field
			// sig = LeakDC.ar(sig); //remove DC bias
			// sig = Balance2.ar(sig[0], sig[1], pan, amp); //L/R balance (pan)
			// sig = sig * env;
			sig = sig * envgate * envpause;
			Out.ar(out, sig*amp.dbamp.lag);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Bform",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 20000, \exp, 0, 200, \Hz),
				\formfreq -> ControlSpec(20, 20000, \exp, 0, 70, \formfreq),
				\fbwfreq -> ControlSpec(20, 20000, \exp, 0, 200, \fbwfreq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \dB)
			],
		))
	}

}

