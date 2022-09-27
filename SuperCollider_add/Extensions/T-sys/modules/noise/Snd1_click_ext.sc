Snd1_click_ext : Tmodule2 {

	*def
	{


		^SynthDef(\Snd1_click_ext, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, note = 34, t_trig = 0, dur = 0.4, gain = 0.5, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, impulse;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;
			impulse = Impulsar.ar(t_trig);
			// sig = BPF.ar(Impulse.ar(freq),freq_filter,rq, gain);
			sig = Limiter.ar(MidEQ.ar( LPF.ar(SinOsc.ar(note.midicps, 0, 0.5) + SinOsc.ar(90.midicps, 0, 0.6), 32.midicps * 2) + HPF.ar(LPF.ar(WhiteNoise.ar(0.008), 12000), 2400) * EnvGen.ar(Env([0, 1, 0.6, 0], [0.0001, dur, 0.01]), impulse),15000, 0.7, 8 )); // snd1

			// sig = Mix.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Snd1_click_ext",
			type: \gen,
			main: \freq,
			sliders: [
				\dur -> ControlSpec(20, 20000, \exp, 0, 958, \dur),
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

/*a = Synth(\Snd1_click_ext, [\t_trig, 1]);
a.set(\t_trig, 1, \dur, 0.1, \note, 40)*/
/*//--tweet0007
r{loop{x={BPF.ar(Pluck.ar(Crackle.ar([1.9,1.8]),Impulse.ar(5.rand+1),0.05,0.05.linrand),1200.rand)}.play(s,0,9);wait(9);x.release(69)}}.play*/





