TGranBuf_onset : Tmodule2 {


	*def
	{
		^SynthDef(\granEnv_8_onset, { |in = 0, out = 0, inbus, ingain = 0, outbus = -2, trigRate = 1, trigRateDev = 0, buf = 1, envbuf = -1, rate = 1.0, xFade = 1, thresh = 0.5, rateDev = 0, startPos= 0, centerDev = 0, dur = 0.25, durDev = 0, pan = 0, panDev = 0, amp=0, ampDev = 0, interpol = 2, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, impulso, posDevia, pos, trigRate2, envgate, envpause, chain, onsets, granout, cross;
			// = trigRate + WhiteNoise.kr(trigRateDev);
			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);


			cross = xFade * envgate * envpause;

			trigRate2 = Impulse.ar(trigRate + WhiteNoise.kr(trigRateDev));
			posDevia = WhiteNoise.kr(centerDev);
			// pos = startPos * BufSamples.kr(buf);
			rate = rate + WhiteNoise.kr(rateDev);
			dur = dur + WhiteNoise.kr(durDev).abs;
			pan = pan + WhiteNoise.kr(panDev); //ver clip
			amp = amp + WhiteNoise.kr(ampDev);

			sig = In.ar(in, 1);// + (In.ar(inbus, 1) * ingain);
			chain = FFT(0, sig);
			onsets = Onsets.kr(chain, thresh, \rcomplex);

			RecordBuf.ar(sig, buf.clear, loop: 0, trigger: onsets);

			granout = GrainBuf.ar(8, trigRate2, dur, buf, rate, startPos, interpol, pan, envbuf);

			granout = granout * envgate * envpause;

			// Out.ar(out, granout*amp.dbamp.lag);
			XOut.ar(out, cross, granout*amp.dbamp.lag); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar

		}).load;


	}


	*metadata
	{
		^(metadata: (
			synthdefname: "granEnv_8_onset",
			type: \fx,
			main: \trigRate,
			sliders: [
				\thresh -> ControlSpec(0, 1, \lin, 0.001, 0.5, \thresh),
				\trigRate -> ControlSpec(0.1, 500, \lin, 0.1, 20, \trigRate),
				\trigRateDev -> ControlSpec(0, 1000.0, \lin,0, 0.001,\trigRateDev),
				\startPos -> ControlSpec(0, 1, \lin,0.001, 0.1,\startPos),
				\centerDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\centerDev),
				\dur -> ControlSpec(0.001, 1.0,\lin,0.001,0.001,\dur),
				\durDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\durDev),
				\rate -> ControlSpec(-10.0, 10.0,\lin, 0.001,1.0,\rate),
				\rateDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\rateDev),
				\pan -> ControlSpec(-1, 1, \lin, 0, 0, \pan),
				\panDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\panDev),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB), // db spec acts weird, so use self made one
				\ampDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\ampDev),
				\interpol -> ControlSpec(1, 4, \lin, 1, 4, \interpol),
			],
			soundfileview: [
				\granenview -> "soundview1"
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				\env -> [\menu, \env, { ~envs[\envitems.item].plot(minval: 0, maxval: 1)}]
			],
			menu: [
				\envitems -> [\envbuf, [-1,0,1, 2, 3, 4, 5, 6, 7]],

			]

		))

	}

}

