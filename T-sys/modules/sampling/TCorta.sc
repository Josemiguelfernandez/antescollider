TCorta : Tmodule2 {


	*def
	{
		^SynthDef(\TCorta, { |out, in, ingain = 0, outbus, inbus, outgain = -120, t_trig= 0, xFade = 1, gran_dur = 0.5,  loop = 0, rate = 1, pos = 0,  trigDev = 0, trigAmp = 0, buf_time = 1, amp = 0, matrix_ramp = 0.01, gate = 0, free = 1|

			var sig, envgate, envpause, src, env, trigger, trig_time, env_trig, buf, sr, cross;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

			sr = SampleRate.ir;
			src = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			buf = LocalBuf((buf_time * sr).ceil, 1).clear;
			env = EnvGen.ar(Env.linen(0.01, gran_dur, 0.01), t_trig); // variar el porte de los granos
			RecordBuf.ar(src*env, buf, loop: 0, trigger: t_trig);
			trig_time = ((0.02+gran_dur).reciprocal + (LFBrownNoise1.kr(20, trigDev,mul:trigAmp)).abs).poll;
			trigger = Impulse.ar(trig_time);
			env_trig = EnvGen.ar(Env.linen(0.002, trig_time - 0.006, 0.002), trigger.abs);
			sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate, trigger, BufFrames.kr(buf) * pos, loop: loop);
			sig = sig * env_trig;
			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig*outgain.dbamp.lag)
		}).load;


	}


	*metadata
	{
		^(metadata: (
			synthdefname: "TCorta",
			type: \fx,
			main: \amp,
			sliders: [
				\gran_dur -> ControlSpec(0, 2, \lin, 0.001, 0.5, \gran_dur),
				\rate -> ControlSpec(-5, 5, \lin, 0.001, 1, \rate),
				\pos -> ControlSpec(0, 1, \lin, 0.001, 0, \pos),
				\trigDev -> ControlSpec(0, 100, \lin, 0.001, 0, \trigDev),
				\trigAmp -> ControlSpec(0, 20, \lin, 0.001, 0, \trigAmp),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			],

			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				\play -> [[["Play", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \gate, 1, \gate, 0],
				\trig -> [\t_trig]
			],


		))

	}

}




