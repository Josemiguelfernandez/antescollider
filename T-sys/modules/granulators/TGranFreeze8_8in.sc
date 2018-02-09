TGranFreeze8_8in : Tmodule2 {

	*def
	{


		^SynthDef(\granFreeze8_8in, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = -120, xFade = 1, buf = 1, t_trig = 1, amp = 0, trate =  60, rate = 1, rateDev = 0, tratedev = 0, pan = 0, panDev = 0, dur = 0.1, durDev = 0.01, matrix_ramp = 0.01, startPos = 0.1, gate = 1, free = 1|
			var sig, envgate, envpause, cross, clk, pos; //dur,

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8) + (In.ar(inbus, 8) * ingain);

			sig = Mix.new(sig);

			// dur = 12 / trate;
			clk = Impulse.kr(trate + WhiteNoise.kr(tratedev));
			dur = dur + TRand.kr(0, durDev, clk);
			// pos = (startPos / BufSamples.kr(buf)) + TRand.kr(0, 0.01, clk);
			pos = 0.1 + TRand.kr(0, 0.01, clk);
			pan = pan + WhiteNoise.kr(panDev); //ver clip
			rate = rate + WhiteNoise.kr(rateDev);
			// sig = SoundIn.ar(9);
			RecordBuf.ar(sig, buf.clear, loop: 0, trigger: t_trig);
			sig = TGrains.ar(8, clk, buf, rate, pos, dur, pan, 0.4);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			//Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "granFreeze8_8in",
			type: \fx,
			main: \amp,
			sliders: [
				\t_trig -> ControlSpec(0, 1,\lin,1,0,\t_trig),
				\buf -> ControlSpec(1, 20, \lin, 1, 0, \buf),
				\trate -> ControlSpec(0, 60,\lin,0.1,60,\trate),
				\tratedev -> ControlSpec(0, 1000.0, \lin,0, 0.001,\tratedev),
				\rate -> ControlSpec(0.01, 5,\lin,0.01,1,\rate),
				\rateDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\rateDev),
/*				\startPos -> ControlSpec(0, 1, \lin,0.001, 0.1,\startPos),
				\centerDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\centerDev),*/
				\dur -> ControlSpec(0.001, 1.0,\lin,0.001,0.001,\dur),
				\durDev -> ControlSpec(0, 1.0, \lin,0, 0.001,\durDev),
				\pan -> ControlSpec(-1, 1, \lin, 0.001, 0, \pan),
				\panDev -> ControlSpec(0, 1.0, \lin,0.001, 0,\panDev),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\trig -> [\t_trig]
			]
		))

	}

}

// 1.bufnum