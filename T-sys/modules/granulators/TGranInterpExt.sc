TGranInterpExt : Tmodule2 {

	*def
	{


		^SynthDef(\granInterpExt, { |in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, envbuf = -1, dur = 0.25, t_trig = 0, durDev = 0, envbuf0 = 43, envbuf1 = 44, ifac = 0, pan = 0, panDev = 0, amp=0, ampDev = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var impulso, trigRate2, sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			// trigRate2 = Impulse.ar(trigRate + WhiteNoise.kr(trigRateDev));
			dur = dur + WhiteNoise.kr(durDev).abs;
			// pan = (pan + WhiteNoise.kr(panDev)); //ver clip
			amp = amp + WhiteNoise.kr(ampDev);
			// sig = GrainIn.ar(8, trigRate2, dur, sig, pan, envbuf);
			sig = InGrainI.ar(t_trig, dur, sig, envbuf0, envbuf1, ifac);
			sig = sig * envgate * envpause * amp.dbamp.lag(1);

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar

		}).load;


	}

	*metadata
	{
		^(metadata: (
			synthdefname: "granInterpExt",
			type: \fx,
			main: \trigRate,
			sliders: [
				// \trigRate -> ControlSpec(0.1, 500, \lin, 0.1, 20, \trigRate),
				// \trigRateDev -> ControlSpec(0, 1000.0, \lin,0, 0.001,\trigRateDev),
				\dur -> ControlSpec(0.001, 1.0,\lin,0.001,0.25,\dur),
				\durDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\durDev),
				\ifac -> ControlSpec(0, 1, \lin, 0, 0.001, \ifac),
				// \panDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\panDev),
				\ampDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\ampDev),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
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














