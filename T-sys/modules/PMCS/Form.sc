Form : Tmodule2 {

	*def
	{
		^SynthDef(\Form, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, freq = 500, bpf = 1000, mul = 0, pan = 0.0, aamp = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, exciter, spec, n, p;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			mul = Lag.kr(mul, 0.2);
			sig = Pan2.ar(Formant.ar(freq, Lag.kr(bpf, 0.5), freq*(mul+1), mul) , pan);
			sig = Line.kr(0, 1, 2)*sig;
			sig = sig.softclip * aamp * SinOsc.ar(Rand(1.0, 6), 0, 0.2, 0.8);

			Out.ar(out, sig* amp.dbamp.lag);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Form",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 10000, \exp,0.01,500,\Hz),
				\bpf -> ControlSpec(20, 10000, \exp,0.01,1000,\Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}