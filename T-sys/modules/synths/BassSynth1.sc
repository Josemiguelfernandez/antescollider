BassSynth1 : Tmodule2 {

	*def
	{



		^SynthDef(\BassSynth1, {|out = 0, outbus, outgain = -120, amp = -6, pan = -1, spread = 0, freq=200, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1|
			var sig, sig1, sig2, sig3, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);

			freq = SinOsc.ar(freq*\fmfreq.kr(1).lag(0.3)) * \fmrange.kr(0.5).lag(0.3) * LFNoise1.kr(1/7).range(0.9,1.1) + 1 * freq;
			sig = SinOsc.ar(freq.lag(0.1) * [1,8,2,4,1.002]);
			sig1 = LFPulse.ar(freq * [1,4,1.001,2 * LFNoise1.kr(1/10).range(0.999,1.001),1/2], mul:1.00);
			sig = sig + sig1;
			sig = sig.fold2(SinOsc.kr(1/13).range(0.9,1));
			sig = sig.wrap2(SinOsc.kr(1/14).range(0.9,1));
			sig = RLPF.ar(sig, \lpf.kr(500).lag(0.4) * LFNoise1.kr(1/9).range(0.9,1.1), \rq.kr(0.5));
			sig = HPF.ar(sig, \hpf.kr(40));
			sig = sig * EnvGen.ar(\iadsr.kr(Env.adsr(0.01,0.1,0.8,0.1)),\igate.kr(1),doneAction:0);
			sig = Splay.ar(sig, spread, 1, pan);
			sig = sig * envgate * envpause * amp.dbamp.lag;
			Out.ar(out, sig);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "BassSynth1",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 20000, \exp, 0, 300, \freq),
				\fmrange -> ControlSpec(0.01, 10,\lin,0.001,0.5,\fmrange),
				\fmfreq -> ControlSpec(0.01, 10,\lin,0.001,1,\fmfreq),
				\lpf -> ControlSpec(20, 20000, \exp, 0, 500, \lpf),
				\hpf -> ControlSpec(20, 20000, \exp, 0, 40, \hpf),
				\rq -> ControlSpec(0.001, 1,\lin,0.001,0.5,\rq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \amp)
			]
		))
	}

}




