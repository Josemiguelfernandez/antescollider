Rolling : Tmodule2 {

	*def
	{
		^SynthDef(\Rolling, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, t_gate = 0.0, mul = 1.0, freq = 5000, freqMul = 1.0,  pan = 0.0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, exciter, spec, n, p;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			n = 5;
			p = 8;
			exciter = EnvGen.ar(Env([1, 0.3],[0.1]), t_gate) * PinkNoise.ar(0.001)*LFNoise2.kr(5, 0.2, 1);
			spec = {
				`[
					Array.fill(p, { 80 + 1000.0.linrand} ),
					nil,
					Array.fill(p, { 0.2 + 6.0.rand } )
				]
			}!2;

			sig = Pan2.ar(LPF.ar(Mix.ar( Klank.ar(spec, exciter)), Lag.kr(freq * freqMul, 0.2)), pan) * mul ;

			Out.ar(out, sig* amp.dbamp.lag);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Rolling",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 10000, \exp,0.01,5000,\Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

