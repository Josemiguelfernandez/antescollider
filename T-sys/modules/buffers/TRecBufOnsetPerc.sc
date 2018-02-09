TRecBufOnsetPerc : Tmodule2 {


	*def
	{
		^SynthDef(\recbuffonsetperc, { |in = 0, buf = 185, rec_dur = 2, threshes = 1, trigdel = 0.001, amp = 0|

			var sig, env, onsets, chain, sr, trig, trig_silence;
			// = trigRate + WhiteNoise.kr(trigRateDev);
			// envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			// envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			// sr = SampleRate.ir;

			// localbuf = LocalBuf((rec_dur * sr).ceil, 1).clear;


			sig = In.ar(in, 1);

			// sig2 = SinOsc.ar(400, 0, 0, 0);
			// trig_silence = (1 - DetectSilence.ar(sig)); // off rec

			chain = FFT(0, sig); // buffer creado al init (T.sc) con indice 0
			onsets = Onsets.kr(chain, threshes, \rcomplex);

			trig = SetResetFF.kr(onsets, 0); // One shot

			// trig = TDelay.kr(trig, trigdel); // Dalay trig 0.001 for perc

			// env = EnvGen.ar(Env.linen(0.0001, rec_dur, 0.01), trig, doneAction: 2); // variar el porte de los granos

			env = EnvGen.ar(Env.perc(0, rec_dur, 1, 0.5), trig, doneAction: 2); // variar el porte de los granos



			sig = DelayC.ar(sig, 0.01, 0.004);

			RecordBuf.ar(sig*env, buf, loop: 0, trigger: env); //, run: trig_silence

			/*~buffers[\granbuf_3_0].poll;
			Out.ar(0, env*0); // no sirve para nada*/



		}).load;
	}


	*metadata
	{
		var buffnum;
		buffnum = ~buffers[\granbuf_3_0].bufnum; //

		^(metadata: (
			synthdefname: "recbuffonsetperc",
			type: \gen,
			main: \amp,
			sliders: [
				\amp -> ControlSpec(0.0001, 2, \exp, 0, 0.3, \amp), // db spec acts weird, so use self made one
				\buf -> ControlSpec(0, 2000, \lin,0, buffnum, \buf),
			],
			soundfileview: [
				\granenview -> "soundview1"
			]

		))

	}

}






