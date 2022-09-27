TBufGrainI : Tmodule2 {


	*def
	{
		^SynthDef(\bufGrainI, { |out = 0, outbus = -2, trigRate = 1, trigRateDev = 0, buf, rate = 1.0, rateDev = 0, startPos= 0, centerDev = 0, dur = 0.25, durDev = 0, pan = 0, panDev = 0, amp=0, ampDev = 0, envbuf0, envbuf1, ifac = 0, interpol = 2, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, impulso, posDevia, pos, trigRate2, envgate, envpause;
			// = trigRate + WhiteNoise.kr(trigRateDev);
			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);


			trigRate2 = Impulse.ar(trigRate + WhiteNoise.kr(trigRateDev));
			posDevia = WhiteNoise.kr(centerDev);
			// pos = startPos * BufSamples.kr(buf);
			startPos = startPos + WhiteNoise.kr(centerDev);
			rate = rate + WhiteNoise.kr(rateDev);
			dur = dur + WhiteNoise.kr(durDev).abs;
			pan = pan + WhiteNoise.kr(panDev); //ver clip
			amp = amp + WhiteNoise.kr(ampDev);
			sig = BufGrainI.ar(trigRate2, dur, buf, rate, startPos, envbuf0, envbuf1, ifac);
			//sig = sig * EnvGen.kr(Env([0, 1, 0], [1, 1], \sin, 1), gate, doneAction: 2);
			sig = sig * envgate * envpause;

			Out.ar(out, sig*amp.dbamp.lag);
			// Out.ar(outbus, sig*amp.lag(1))
		}).load;


	}



	*metadata
	{
	 ^(metadata: (
			synthdefname: "bufGrainI",
			type: \gen,
			main: \trigRate,
			sliders: [
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



/*	SynthDef(\buf_grain_test, {arg gate = 1, sndbuf, amp = 1, envbuf1, envbuf2;
	Out.ar(0,
		BufGrainI.ar(Impulse.kr(10), 0.2, sndbuf, MouseX.kr(0.5, 8), PinkNoise.kr.range(0, 1),
			envbuf1, envbuf2, MouseY.kr(0, 1), 2,
			EnvGen.kr(
				Env([0, 1, 0], [1, 1], \sin, 1),
				gate,
				levelScale: amp,
				doneAction: 2)
			)
		)
	}).load;

s.sendMsg(\b_allocRead, x = s.bufferAllocator.alloc(1), Platform.resourceDir +/+ "sounds/a11wlk01.wav";);
y = Env([0, 1, 0], [1, 1], \sin).asSignal(1024);
y.plot
z = Env([0, 1, 0], [1, 1], [10, -10]).asSignal(1024);
z.plot
s.sendMsg(\b_alloc, b = s.bufferAllocator.alloc(1), 1024, 1, [\b_setn, b, 0, 1024] ++ y)
s.sendMsg(\b_alloc, c = s.bufferAllocator.alloc(1), 1024, 1, [\b_setn, c, 0, 1024] ++ z)
s.sendMsg(\s_new, \buf_grain_test, a=s.nextNodeID, 0, 1, \amp, 0.2, \sndbuf, x, \envbuf1, b, \envbuf2, c);
s.sendMsg(\n_set, a, \gate, 0);
s.sendMsg(\b_free, b);
s.sendMsg(\b_free, c);
s.sendMsg(\b_free, x);

p = Platform.resourceDir +/+ "sounds/a11wlk01.wav";*/


