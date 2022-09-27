TPhasing : Tmodule2 {

	*def
	{
		^SynthDef(\tPhasing, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, modfreq = 1, ampmod = 0.01,lpfilt = 10000,  hpfilt = 50, decaytime = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = AllpassN.ar(sig, 0.03, SinOsc.kr(modfreq,0,ampmod,0.01), decaytime) * amp.dbamp.lag; //max delay of 20msec
			sig	= MidEQ.ar(sig, 3000, 2, -12); //correct earchannel-resonance
			sig	= MidEQ.ar(sig, 15000, 0.5, 3);
			sig	= LPF.ar(sig, lpfilt);
			sig	= HPF.ar(sig, hpfilt);
			sig	= Limiter.ar(sig, 0.9, 0.01);

			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tPhasing",
			type: \fx,
			main: \modfreq,
			sliders: [
				\modfreq -> ControlSpec(0.001, 100, \exp, 0.001, 1, \Hz),
				\ampmod -> ControlSpec(0.001, 0.015, \lin, 0.001, 0.01, \ampmod),
				\decaytime -> ControlSpec(0.1, 10, \lin, 0.01, 1, \decaytime),
				\lpfilt -> ControlSpec(30, 20000, \exp, 0.1, 10000, \lpfilt),
				\hpfilt -> ControlSpec(30, 20000, \exp, 0.1, 50, \hpfilt),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}

