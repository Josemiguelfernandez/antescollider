Rz_click_ext : Tmodule2 {

	*def
	{


		^SynthDef(\Rz_click_ext, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freq = 222, cutoff = 1500, attack = 0, bwr = 1, t_trig = 0, dur = 0.1, gain = 0.5, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, impulse;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;
			impulse = Impulsar.ar(t_trig);
			// sig = BPF.ar(Impulse.ar(freq),freq_filter,rq, gain);

			// sig =	Limiter.ar(MidEQ.ar( SinOsc.ar([52.8, 740], 0, [2, 0.05]).mean.tanh * EnvGen.ar(Env([0, 0.5, 0.3, 0], [0, dur, 0.1]), (impulse).abs),15000, 0.7, 8));
			// sig =	Limiter.ar(MidEQ.ar( SinOsc.ar([52.8, 740], 0, [2, 0.05]).mean.tanh * EnvGen.ar(Env([0, 0.5, 0.3, 0], [attack, dur, release]), (impulse).abs),15000, 0.7, 8));

			sig = Resonz.ar(Saw.ar(freq), cutoff, bwr) * EnvGen.kr(Env.perc(attack, dur), impulse);

			// sig = Mix.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Rz_click_ext",
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

/*a = Synth(\Rz_click_ext, [\freq, 3000, \t_trig, 1, \cutoff, 4000, \dur, 0.05]);
a.set(\t_trig, 1, \cutoff, exprand(1600,15000), \freq, exprand(150,10000), \dur, exprand(0.05,0.05))*/


	