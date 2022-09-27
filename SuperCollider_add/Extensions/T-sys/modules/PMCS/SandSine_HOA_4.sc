SandSine_HOA_4 : Tmodule2 {

	*def
	{
		^SynthDef(\SandSine_HOA_4, {|x = 0, y = 0, z = 0, globTBus,  gatee = 1.0, freq = 500, bpf = 1000, mul = 0, pan = 0.0, aamp = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, position, hoa_encoder, env;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;

			env = EnvGen.kr(Env.perc(0.001, 0.2, aamp, -10), gatee);
			sig =  BPF.ar(BPF.ar(WhiteNoise.ar(100), freq, 0.2), bpf, 0.2);
			sig = Line.kr(0, 1, 2) * sig*4 * env;
			sig = sig * mul *0.5;
			sig = sig* amp.dbamp.lag;

			hoa_encoder = HOAEncoder.ar(4, sig, position.theta, position.phi, 0, 1,position.rho); // amp in dB

			Out.ar(globTBus, hoa_encoder);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "SandSine_HOA_4",
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
