TEnv : Tmodule2  {


	*def
	{


		^SynthDef(\tEnv, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, matrix_ramp = 0.01, i_doneAction = 3, xFade = 1, env = 41, amp = 0, dur = 1, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			sig = In.ar(in, 8);
			sig = sig * PlayBuf.ar(1, env, 1/((SampleRate.ir/BufSamples.kr(env)) * dur), doneAction:i_doneAction); //BufRateScale.kr(env)SampleRate.ir * dur
			cross = xFade * envgate * envpause;

			// sig = sig * amp.dbamp.lag(1);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// DetectSilence.ar(sig, doneAction: i_doneAction);

			XOut.ar(out, cross, sig); //salida directa a un bus

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tEnv",
			type: \fx,
			main: \amp,
			sliders:[
				\amp -> ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB"),
				\dur -> ControlSpec(0.001, 5.0, \lin, 0.001, 1, \dur)
			]
		))

	}
}

