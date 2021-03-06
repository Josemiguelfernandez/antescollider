TBufGrainMod : Tmodule2 {


	*def
	{
		^SynthDef(\bufGrainMod, { |out = 0, outbus = -2, trigRate = 1, dur = 0.25, amp=0, matrix_ramp = 0.01, freq = 1, sndbuf = 185, envbuf = 48, dur_add = 0.001, rate = 1, iphase = 0, saw_freq = 0.5, dur_offset = 0, gate = 1, free = 1|

			var sig, trig, posDevia, pos, trigRate2, envgate, envpause, pan;
			// = trigRate + WhiteNoise.kr(trigRateDev);
			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			dur = ((1/freq)-dur_add)+dur_offset;

			trig = Impulse.kr(freq);

			pan = LFSaw.kr(saw_freq, iphase);

			sig = GrainBuf.ar(8, trig, dur, sndbuf, rate, 0, 2, pan, envbuf); //LFNoise2.kr(0.1).range(0, 1)


			//sig = sig * EnvGen.kr(Env([0, 1, 0], [1, 1], \sin, 1), gate, doneAction: 2);
			sig = sig * envgate * envpause;

			Out.ar(out, sig*amp.dbamp.lag);
			// Out.ar(outbus, sig*amp.lag(1))
		}).load;


	}

	/*x = Synth(\bufGrainSimple, [\sndbuf, ~buffers[\granbuf_3_0], \envbuf, 48])
	x.set(\freq, 3)*/

	*metadata
	{
		var buffnum;
		buffnum = ~buffers[\granbuf_3_0].bufnum; //

		^(metadata: (
			synthdefname: "bufGrainMod",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(0, 100, \lin, 0.1, 1, \freq),
				\sndbuf -> ControlSpec(0, 1000, \lin, 1, buffnum, \sndbuf),
				\envbuf -> ControlSpec(41, 48, \lin, 1, 48, \envbuf),
				\dur -> ControlSpec(0.0, 3.0,\lin,0.001,0.01,\dur),
				\dur_add -> ControlSpec(-1.0, 1.0,\lin,0.0001,0.001,\dur_add),
				\dur_offset -> ControlSpec(-1.0, 1.0,\lin,0.01,0.0,\dur_offset),
				\saw_freq -> ControlSpec(0, 40.0,\lin,0.01, 0.5,\saw_freq),
				\iphase -> ControlSpec(0.0, 2.0,\lin,0.01, 0,\iphase),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB), // db spec acts weird, so use self made one
			],
			/*		soundfileview: [
			\granenview -> "soundview1"
			],*/
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


