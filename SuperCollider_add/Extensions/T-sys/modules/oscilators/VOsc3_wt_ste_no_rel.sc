VOsc3_wt_ste_no_rel : Tmodule2 {

	*def
	{
		^SynthDef(\VOsc3_wt_ste_no_rel, {|out = 0, buf, numBufs=8, num_buf_offset=8, buf_pos_offset=0, freq=200, detune=0.2, amp=0, pan=0, lfo_pos_freq=0.5, atk=0.01, sus=1, rel=0.01, c0=1, c1=(-1), matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, bufpos, env, detuneSig;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			// env = EnvGen.ar(Env([0,1,1,0],[atk,sus,rel],[c0,0,c1]), doneAction:2);

			//array of eight Oscs with uniquely detune frequencies
			//and unique initial phase offsets
			// detuneSig = LFNoise1.kr(0.2!8).bipolar(detune).midiratio;
			// bufpos = Clip.kr((buf + LFNoise1.kr(lfo_pos_freq).range(0, numBufs-1)*buf_pos_fact)+buf_pos_offset, buf, buf+numBufs-1);
			bufpos = Clip.kr(buf + buf_pos_offset + LFNoise1.kr(lfo_pos_freq).range(0, num_buf_offset), buf, buf+numBufs-1);
			// bufpos.poll;
			sig = VOsc3.ar(bufpos, freq+[0,1],freq+[0.37,1.1],freq+[0.43, -0.29]);
			// sig = Mix.ar(sig);
			sig = Splay.ar(sig); //spread 8 signals over stereo field
			sig = LeakDC.ar(sig); //remove DC bias
			// sig = Balance2.ar(sig[0], sig[1], pan, amp); //L/R balance (pan)
			// sig = sig * env;
			sig = sig * envgate * envpause;
			Out.ar(out, sig*amp.dbamp.lag);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "VOsc3_wt_ste_no_rel",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 20000, \exp, 0, 300, \Hz),
				\buf -> ControlSpec(0, 100, \lin, 1, 0, \buf),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \dB)
			],
			soundfileview: [
				\waveview -> "waveview1"
			],
		))
	}

}
