Bass_click_ext : Tmodule2 {

	*def
	{


		^SynthDef(\Bass_click_ext, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freq = 52.8, attack = 0.002, release = 0.1, t_trig = 0, dur = 0.2, gain = 0.5, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, impulse;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;
			impulse = Impulsar.ar(t_trig);
			// sig = BPF.ar(Impulse.ar(freq),freq_filter,rq, gain);

			// sig =	Limiter.ar(MidEQ.ar( SinOsc.ar([52.8, 740], 0, [2, 0.05]).mean.tanh * EnvGen.ar(Env([0, 0.5, 0.3, 0], [0, dur, 0.1]), (impulse).abs),15000, 0.7, 8));
			sig =	Limiter.ar(MidEQ.ar( SinOsc.ar([1, 14]*freq, 0, [2, 0.05]).mean.tanh * EnvGen.ar(Env([0, 0.5, 0.3, 0], [attack, dur, release]), (impulse).abs),15000, 0.7, 8));

			// sig = Mix.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Bass_click_ext",
			type: \gen,
			main: \freq,
			sliders: [
				\freq-> ControlSpec(20, 20000, \exp, 0.001, 52.8, \decay),
				\dur -> ControlSpec(0.0001, 2, \lin, 0.001, 0.05, \dur),
				\attack -> ControlSpec(0.0001, 2, \lin, 0.001, 0.002, \attack),
				\release -> ControlSpec(0.0001, 2, \lin, 0.001, 0.1, \release),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			buttons: [
				//\env: [\env, 10]//{ \envs[\envitems.item].plot(minval: 0, maxval: 1)}]
				/*\env -> [\menu, \env, { ~envs[\envitems.item].plot(minval: 0, maxval: 1)}],*/
				\trig -> [\t_trig]
			],
		))

	}

}

/*a = Synth(\Bass_click_ext, [\t_trig, 1]);
a.set(\t_trig, 1, \dur, 0.2, \attack, 0.001, \release, 0.05)
a.set(\t_trig, 1, \dur, 0.2, \attack, 0.01, \release, 0.5, \note, 100)*/
/*//--tweet0007
r{loop{x={BPF.ar(Pluck.ar(Crackle.ar([1.9,1.8]),Impulse.ar(5.rand+1),0.05,0.05.linrand),1200.rand)}.play(s,0,9);wait(9);x.release(69)}}.play*/





