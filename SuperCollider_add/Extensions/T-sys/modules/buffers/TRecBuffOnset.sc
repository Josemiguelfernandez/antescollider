TRecBuffOnset : Tmodule2 {


	*def
	{
		^SynthDef(\TRecBuffOnset, { |in = 0, buf, rec_dur = 1, threshes = 1|

			var sig, env, localbuf, onsets, chain, sr, trig;
			// = trigRate + WhiteNoise.kr(trigRateDev);
			// envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			// envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			sr = SampleRate.ir;

			// localbuf = LocalBuf((rec_dur * sr).ceil, 1).clear;


			sig = SoundIn.ar(in, 1);

			chain = FFT(0, sig); // buffer creado al init (T.sc) con indice 0
			onsets = Onsets.kr(chain, threshes, \rcomplex);

			trig = SetResetFF.kr(onsets, 0); // One shot

			env = EnvGen.ar(Env.linen(0.01, rec_dur, 0.01), trig, doneAction: 2); // variar el porte de los granos

			RecordBuf.ar(sig*env, buf.clear, loop: 0, trigger: trig);

			/*pips = WhiteNoise.ar(EnvGen.kr(Env.perc(0.001, 0.1, 0.2), onsets));
			Out.ar(0, pips);*/


		}).load;
	}


	*metadata
	{
		^(metadata: (
			synthdefname: "TRecBuffOnset",
			type: \gen,
			main: \trigRate,
			sliders: [
				\buf -> ControlSpec(0, 1000, \lin,0, 1,\buf),
			],
			soundfileview: [
				\granenview -> "soundview1"
			]

		))

	}

}






