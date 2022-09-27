TCortaOnsetEnv : Tmodule2 {


	*def
	{
		^SynthDef(\TCortaOnsetEnv, { |out, in, ingain = 1, outbus, inbus, outgain = -120, t_trig= 0, xFade = 1, gran_dur = 0.5,  loop = 0, rate = 1, pos = 0,  trigDev = 0, trigAmp = 0, thresh = 0.5, buf_time = 1, trig_time = 0.5, t60 = 1, damp = 0, size = 3, earlyDiff = 0.707, modDepth = 0.1, modFreq = 2, low = 1, mid = 1, high = 1, lowcut = 500, highcut = 2000, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, src, env, trigger, env_trig, buf, sr, cross, onsets, chain, srconset, line, envgen, line2, revTime, trig, line3, sigamp;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

			sr = SampleRate.ir;
			src = In.ar(in, 1);
			srconset = In.ar(in, 1) * ingain;
			buf = LocalBuf((buf_time * sr).ceil, 1).clear;

			chain = FFT(0, srconset);
			onsets = Onsets.kr(chain, thresh, \rcomplex);

			trig = SetResetFF.kr(onsets, 0); // One shot

			// trig = TDelay.kr(trig, 0.01); //delay trigger para no tomar ataque del sonido

			env = EnvGen.ar(Env.linen(0.01, gran_dur, 0.01), trig); // variar el porte de los granos
			RecordBuf.ar(src*env, buf, loop: 0, trigger: trig);
			//trig_time = ((0.02+gran_dur).reciprocal + (LFBrownNoise1.kr(20, trigDev,mul:trigAmp)).abs).poll;

			line = Env([10, 20], [5]);
			envgen = EnvGen.ar(line, onsets); //control playbuf trigger rate

			line2 = Env([-1, -1, 1], [5, 5]);
			revTime = EnvGen.ar(line2, onsets); //control rev

			line3 = Env([1, 1, 0], [8, 20]);
			sigamp = EnvGen.ar(line3, onsets); //control rev

			trigger = Impulse.ar(envgen);


			//env_trig = EnvGen.ar(Env.linen(0.002, trig_time - 0.006, 0.002), trigger.abs);


			sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate, trigger, BufFrames.kr(buf) * pos, loop: loop)*sigamp;
			// sig = sig * env_trig;

			// sig = FreeVerb.ar(sig, 0.5, envgen2, 0.5);


			sig = XFade2.ar(sig, JPverb.ar(sig, 60, damp, size, earlyDiff, modDepth, modFreq, low, mid, high, lowcut, highcut), revTime);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig*outgain.dbamp.lag)
		}).load;
	}


	*metadata
	{
		^(metadata: (
			synthdefname: "TCortaOnsetEnv",
			type: \fx,
			main: \amp,
			sliders: [
				\gran_dur -> ControlSpec(0, 2, \lin, 0.001, 0.5, \gran_dur),
				\trig_time -> ControlSpec(0, 30, \lin, 0.001, 0.5, \trig_time),
				\rate -> ControlSpec(-5, 5, \lin, 0.001, 1, \rate),
				\pos -> ControlSpec(0, 1, \lin, 0.001, 0, \pos),
				\thresh -> ControlSpec(0, 1, \lin, 0.001, 0.5, \thresh),
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




