TPitchMulti5Disp : Tmodule2 {

	*def
	{
		^SynthDef(\TPitchMulti5Disp, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, trans = 1, windsize = 0.2, pitchDispersion = 0, timeDispersion = 0 fb = 0.1, del = 0.01, hpp = 15, lpp = 130, lag = 10, lfo = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, n;
			n = 5;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);


			sig = Mix.fill(n*3, { |i|
				PitchShift.ar(sig, windsize,trans.lag(lag), pitchDispersion.lag(lag), timeDispersion.lag(lag)); //freqs.wrapAt(i) *
				});

			sig = (sig * 0.25) * envgate * envpause * amp.dbamp.lag;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TPitchMulti5Disp",
			type: \fx,
			main: \trans,
			sliders: [
				\trans -> ControlSpec(0.01, 4,\lin,0.001,1,\trans),
				\pitchDispersion -> ControlSpec(0, 200,\lin,0.1,0,\pDisp),
				\timeDispersion -> ControlSpec(0, 10, \lin, 0.01, 0, \tDisp),
				\lag -> ControlSpec(0.1, 30, \exp, 0.1, 10, \lag),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}





	