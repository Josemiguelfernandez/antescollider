TRecBufOnsetPerc_Gate : Tmodule2 {


	*def
	{
		^SynthDef(\recbuffonsetpercgate, { |in = 0, buf = 185, rec_dur = 2, threshes = 1, trigdel = 1, amp = 0, antirebond = 0, waittime = 1|

			var sig, env, onsets, chain, sr,  trig = 0, trig_silence, anti_dur, gate = 0;
			// = trigRate + WhiteNoise.kr(trigRateDev);
			// envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			// envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			// sr = SampleRate.ir;

			// localbuf = LocalBuf((rec_dur * sr).ceil, 1).clear;


			sig = In.ar(in, 1);

			/*			(
			play({
			a = Dust.ar(10); // the set trigger
			b = TDelay.ar(a, 1); // the reset trigger
			SetResetFF.ar(a,b) * BrownNoise.ar(0.2);

			}))*/

			// sig2 = SinOsc.ar(400, 0, 0, 0);
			// trig_silence = (1 - DetectSilence.ar(sig)); // off rec

			/*			chain = FFT(0, sig); // buffer creado al init (T.sc) con indice 0
			onsets = Onsets.kr(chain, threshes, \rcomplex);*/

			// onsets = PV_HainsworthFoote.ar(FFT(LocalBuf(2048), sig), 0, 0, 0.1, waittime);
			onsets = PV_HainsworthFoote.ar(FFT(LocalBuf(2048),sig), 1.0, 0.0, 0.2,waittime);
			// onsets.poll;
			/*			gate = 1 - TDelay.kr(onsets, trigdel);
			// gate.poll;

			trig = SetResetFF.kr(onsets, gate); // One shot del*/
			// trig.poll;
			// antirebond = 1;

			// gate = 1 - Trig1.kr(trig,1);

			// env = EnvGen.ar(Env.linen(0.0001, rec_dur, 0.01), trig, doneAction: 2); // variar el porte de los granos

			env = EnvGen.ar(Env.perc(0, rec_dur, 1, 0.5), onsets, doneAction: 0); // variar el porte de los granos



			sig = DelayC.ar(sig, 0.01, 0.004); //del rec input

			RecordBuf.ar(sig*env, buf, loop: 0, trigger: env); //, run: trig_silence


			// trig = TDelay.ir(trig, trigdel); // Dalay trig 0.001 for perc
			// anti_dur.poll;
			// trig.poll;

			/*~buffers[\granbuf_3_0].poll;
			Out.ar(0, env*0); // no sirve para nada*/



		}).load;
	}


	*metadata
	{
		var buffnum;
		buffnum = ~buffers[\granbuf_3_0].bufnum; //

		^(metadata: (
			synthdefname: "recbuffonsetpercgate",
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






