TBufRdEnvTrig : Tmodule2 {


	*def
	{
		^SynthDef(\TBufRdEnvTrig, { |out = 0, buf, loop = 1, rate=1, envbuf, env_dur, impulse_rate = 1, pos=0|
			var sig, env, trig, phasor_env, phasor_play, phasor, sr, framesInBuffer;
			sr = SampleRate.ir;
			// impulse_rate = MouseX.kr(0.1, 200, 1);
			trig = Impulse.ar(impulse_rate);
			 framesInBuffer = BufFrames.kr(buf);
			// phasor = Phasor.ar(trig, impulse_rate/sr, 0, 1);
			// phasor = Phasor.ar(trig, BufRateScale.kr(buf), 0, BufFrames.kr(buf));
			phasor = Phasor.ar(trig, impulse_rate/sr, 0, 1);
			env = BufRd.ar(1, envbuf, phasor*BufFrames.kr(envbuf));
			// phasor_env = Phasor.ar(trig, BufRateScale.kr(envbuf), 0, BufFrames.kr(envbuf));
			phasor_play = Phasor.ar(trig, BufRateScale.kr(buf)*rate, 0, BufFrames.kr(buf), framesInBuffer*pos);
			// env = BufRd.ar(1, envbuf, (phasor_play/BufFrames.kr(envbuf))*sr*BufFrames.kr(envbuf));
			// sig =  BufRd.ar(1, buf, BufFrames.kr(buf)*phasor, interpolation:4);
			// sig =  BufRd.ar(1, buf, phasor*BufFrames.kr(buf)/BufRateScale.kr(b));
			sig =  BufRd.ar(1, buf, phasor_play, interpolation:4);
			Out.ar(out, sig*env);
/*			phasor_env = Phasor.ar(trig, ((SampleRate.ir/BufFrames.kr(envbuf)).reciprocal*env_dur.reciprocal)*rate, 0.0, BufFrames.kr(envbuf);

			env = BufRd.ar(1, envbuf, phasor*
			env = PlayBuf.ar(1, envbuf, ((SampleRate.ir/BufFrames.kr(envbuf)).reciprocal*env_dur.reciprocal)*rate, trig, loop: loop); //BufRateScale.kr(envbuf) *
			sig = PlayBuf.ar(1, buf, BufRateScale.kr(buf) * rate, trig, 0, loop: loop);

			Out.ar(out, sig*env);*/
			// Out.ar(out, sig);
		}).load;
	}
/*	~envs[5].plot
	~envs[7].plot
	~a11 = Buffer.read(s, "/Users/josephfernandez/sounds-off/bateria-perc/Cymbal.aif")
	~a11 = Buffer.read(s, "/Users/josephfernandez/Library/Application Support/SuperCollider/recordings/test_010101.aif")
	~a11 = Buffer.read(s, Platform.resourceDir +/+ "sounds/a11wlk01.wav");

	a =Synth(\TBufRdEnvTrig, [out: 0, impulse_rate:1, envbuf: ~envs[2], \buf, ~a11])

	Synth(\pluck, [out: 30, freq: 100.midicps, pan: rrand(-1.0, 1.0)], ~drumGroup);
a.set(\envbuf, ~envs[2], \buf, 	~a11)
a.set(\envbuf, ~envs[7], \buf, 	~a11)
a.set(\impulse_rate, 20)
	a.set(\rate, 1)
	a.free
   x = Phasor.ar(trig, BufRateScale.kr(b.bufnum), 0, framesInBuffer,
        [0, MouseY.kr(0, framesInBuffer)]);
	{ BufRd.ar(1, b.bufnum, Phasor.ar(0, BufRateScale.kr(b.bufnum), 0, BufFrames.kr(b.bufnum))) }.play;*/

	*metadata
	{
		^(metadata: (
			synthdefname: "TBufRdEnvTrig",
			type: \gen,
			main: \trigRate,
			sliders: [
				\amp -> ControlSpec(0.0001, 2, \exp, 0, 0.3, \amp), // db spec acts weird, so use self made one
				\ampDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\ampDev),
				\interpol -> ControlSpec(1, 4, \lin, 1, 4, \interpol),
			],
			soundfileview: [
				\granenview -> "soundview1"
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				\env -> [\menu, \env, { ~envs[\envitems.item].plot(minval: 0, maxval: 1)}],
				\trig -> [\t_trig]
			],
			menu: [
				\envitems -> [\envbuf, [-1,0,1, 2, 3, 4, 5, 6, 7]],

			]

		))

	}

}

// TGran_ext_in.def
// env = EnvGen.ar(Env.sine, HPZ1.ar(phasor).neg, timeScale: BufDur.ir(buf)).poll;


// 2.reciprocal
