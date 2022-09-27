FM_fbk : Tmodule2 {


	*def
	{
		^SynthDef(\fm_fbk, {|out, outbus, outgain = 0, freq = 440, fb = 0, modindex = 0, amp=0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, sr, phas;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			sr = SampleRate.ir;

			fb = OnePole.ar( (LocalIn.ar(1) * fb), 0.91);
			phas =  Phasor.ar(0, freq/sr) + fb;
			sig = SinOsc.ar(0, phas*2pi, 0.5);
			LocalOut.ar(sig);
			sig = sig * envgate * envpause;
			OffsetOut.ar(out, sig * amp.dbamp); //salida directa a un bus
			//Out.ar(outbus, sig * outgain); //salida a un bus auxiliar

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "fm_fbk",
			type: \gen,
			main: \freq,
			sliders:  [
				\freq -> ControlSpec(20, 5000, 'exponential', 10, 440, \Hz),
				\fb -> ControlSpec(0.0, 1.5, 'linear', 0.01, 0.0,\fb),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB) // db spec acts weird, so use self made one
			]
		))

	}

}





/*SynthDef("fm_fb", {arg b_freq = 440;
	 var chain, phas, sr, freq, fb;
	 	freq = MouseY.kr(50, 1000, 'exponential');
	 	sr = SampleRate.ir;
	 	fb = OnePole.ar( (LocalIn.ar(1) * fb), 0.91);
	 	phas =  Phasor.ar(0, freq/sr) + fb;
	 	sig = SinOsc.ar(0, phas*2pi, 0.5);
	 	LocalOut.ar(sig);
	 	OffsetOut.ar(0, sig);
}).play(s);*/