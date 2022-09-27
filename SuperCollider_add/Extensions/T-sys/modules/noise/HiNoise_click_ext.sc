HiNoise_click_ext : Tmodule2 {

	*def
	{


		^SynthDef(\HiNoise_click_ext, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, t_trig = 0, decay_time = 1, gain = 0.5, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, impulse;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;
			impulse = Impulsar.ar(t_trig);
			// sig = BPF.ar(Impulse.ar(freq),freq_filter,rq, gain);
			sig = Limiter.ar(MidEQ.ar(BPF.ar(WhiteNoise.ar(Decay2.ar(impulse * LFNoise1.ar(8, 0.5, 0.5), 0.02, 0.1 * decay_time) * 0.05), TRand.ar(12000, 15000, impulse), 0.9, gain),15000, 0.7, 8)); //hiNoise
			// sig = Mix.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HiNoise_click_ext",
			type: \gen,
			main: \freq,
			sliders: [
				\decay_time -> ControlSpec(20, 20000, \exp, 0, 958, \decay),
				\gain -> ControlSpec(0.0001, 2, \lin, 0.001, 0.05,\gain),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				/*\env -> [\menu, \env, { ~envs[\envitems.item].plot(minval: 0, maxval: 1)}],*/
				\trig -> [\t_trig]
			],
		))

	}

}

/*a = Synth(\HiNoise_click_ext, [\t_trig, 1]);
a.set(\t_trig, 1, \decay_time, 1, \gain, 10)*/
/*//--tweet0007
r{loop{x={BPF.ar(Pluck.ar(Crackle.ar([1.9,1.8]),Impulse.ar(5.rand+1),0.05,0.05.linrand),1200.rand)}.play(s,0,9);wait(9);x.release(69)}}.play*/





