TPFshift : Tmodule2 {

	*def
	{
		^SynthDef(\TPFshift, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, shft = 0.1, fb = 0.1, del = 0.01, hpp = 15, lpp = 130, lag = 10, lfo = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig 	= sig + (LocalIn.ar(1) * fb);
			shft	= shft * shft * shft.sign;
			// shft	= [shft, shft*sprd];
			shft	= Lag.kr(shft, lag);
			//shft 	= shft + LFNoise1.kr(lfop.midicps, lfo);
			sig 	= FreqShift.ar(sig, shft);
			sig 	= DelayL.ar(sig, 1, K2A1.ar(del, lag));
			sig 	= HPF.ar(sig, Lag.kr(hpp.midicps, lag));
			sig 	= BLowPass.ar(sig, Lag.kr(lpp.midicps, lag));
			LocalOut.ar(sig);
			sig = sig * envgate * envpause * amp.dbamp.lag;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TPFshift",
			type: \fx,
			main: \shft,
			sliders: [
				\shft -> ControlSpec(-100, 100, \lin, 0.1, 0.1, \Hz),
				\lfo -> ControlSpec(0.1, 100, \exp, 0.1, 1, \lfo), //random change of shift-frequency. in hz
				\fb -> ControlSpec(0.001, 1, \exp, 0.001, 0.1, \fb),
				\hpp -> ControlSpec(15,125, \exp, 0.1, 15, \hpp),
				\lpp -> ControlSpec(15,135, \exp, 0.1, 130, \lpp),
				\lag -> ControlSpec(0.1, 30, \exp, 0.1, 10, \lag),
				\del -> ControlSpec(0.01, 1, \exp, 0.01, 0.01, \lag),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

                