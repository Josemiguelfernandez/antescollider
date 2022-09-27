Sample_play_seg2 : Tmodule2 {


	*def
	{
		^SynthDef(\Sample_play_seg2, { |out, buf_idx, buf_src = -1, index, rate = 1.0, pos = 0, loop = 0, amp = 0, t_trig = 0, matrix_ramp = 0.01, gate = 1, free = 1, dur_secs = 0, id = 0, seg_dur = 1|

			var sig, env, startsamp, stopsamp, phs, dursecs;

			 startsamp = Index.kr(buf_idx,index);
			 stopsamp = Index.kr(buf_idx,index+1)*seg_dur;
/*			startsamp.poll;
			stopsamp.poll;*/
			 phs = Phasor.ar(0,BufRateScale.ir(buf_src),startsamp,stopsamp);
			 sig = BufRd.ar(1,buf_src, phs);
			 dursecs = (stopsamp - startsamp) / BufSampleRate.ir(buf_src);
			 env = EnvGen.kr(Env([0,1,1,0],[0.01,dursecs-0.02,0.01]),doneAction:2);
			SendReply.kr(Done.kr(env), '/seg_end', 1, id);
			sig * env;

			sig = sig * env * amp.dbamp.lag;

			Out.ar(out, sig);
			// Out.ar(outbus, sig*outgain.dbamp.lag)
		}).load;


	}


	*metadata
	{
		^(metadata: (
			synthdefname: "Sample_play_seg2",
			type: \gen,
			main: \rate,
			sliders: [
				\pos -> ControlSpec(0, 1, \lin, 0.001, 0, \pos),
				\rate -> ControlSpec(0.0, 5.0,\lin, 0.001,1.0,\rate),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			],
			soundfileview: [
				\playbufview -> "soundview1"
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				\play -> [[["Play", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \t_trig, 1, \t_trig, 0],
				\loop -> [[["Loop", Color.black, Color.green(0.7)], ["Loop", Color.white, Color.red(0.7)]], \loop, 1, \loop, 0],
				\rev -> [[["norm", Color.black, Color.rand], ["rever", Color.white, Color.rand]], \rev, -1, \rev, 1],
			],


		))

	}

}


/*	arg index;
{
var startsamp = Index.kr(~indices,index);
var stopsamp = Index.kr(~indices,index+1);
var phs = Phasor.ar(0,BufRateScale.ir(~src),startsamp,stopsamp);
var sig = BufRd.ar(1,~src,phs);
var dursecs = (stopsamp - startsamp) / BufSampleRate.ir(~src);
var env = EnvGen.kr(Env([0,1,1,0],[0.03,dursecs-0.06,0.03]),doneAction:2);
sig.dup * env;
}.play;*/
