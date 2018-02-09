Bajo : Tmodule2 {

	*def
	{



		^SynthDef(\Bajo, {|out = 0, outbus, outgain = -120, amp = -6, freq = 41.2, lfnoisefreq = 1, lag = 5, d = 0, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1|
			var trig, sig, sweep, swr, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);

			trig = Impulse.kr(1);
			swr = Demand.kr(trig, 0, Drand([20, 1, 2, 3, 4, 6], inf));
			sweep = LFTri.ar(1000);

			sig = LFSaw.ar(freq + LFDNoise3.kr(lfnoisefreq)!8 * d).sum; //.sum

			sig = Normalizer.ar(sig);
			sig = sig + BPF.ar(sig, 1500, 2);
			sig = sig + GVerb.ar(sig, 9, 0.1, 0.7, mul: 0.1);
			sig = (sig * 2).clip2;

			sig = RLPF.ar(sig + SinOsc.ar(freq).tanh, freq*4, 0.4);

			sig = FreeVerb.ar(((sig*0.5)).tanh, 0.6, 0.9);

			sig = sig * envgate * envpause * amp.dbamp.lag;
			Out.ar(out, sig);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Bajo",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 18000, \exp, 0.11, 41.2, \freq),
				\d -> ControlSpec(0, 10, \lin, 0.01, 0, \desv),
				\lfnoisefreq -> ControlSpec(0, 20, \lin, 0.001, 0.001, \lfreq),
				\lag -> ControlSpec(0.001, 15, \lin, 0.001, 5, \lag),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \amp)
			]
		))
	}

}