PlayBuff : Tmodule2 {

	*def
	{
		^SynthDef(\PlayBuff, { |out = 0, outbus, bufnum, loop = 0, rate = 1, rev = 1, pos = 0, outgain = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			sig = PlayBuf.ar(1, bufnum, BufRateScale.kr(bufnum) * rate * rev, 0, BufFrames.kr(bufnum) * pos, loop: loop, doneAction: 1);


			SendReply.kr(Done.kr(sig),'/buf_stop', 0);
			sig = sig * envgate * envpause;

			Out.ar(out, sig);
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "PlayBuff",
			type: \gen,
			main: \freq,
			sliders: [
				\rate -> ControlSpec(0, 2, \linear, 0.001, 1, \rate),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \dB)
			]
		))
	}

}

