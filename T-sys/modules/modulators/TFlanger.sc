TFlanger : Tmodule2 {

	*def
	{
		^SynthDef(\tflanger, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, flangefreq = 0.1, ampmod = 0.005,lpfilt = 10000,  hpfilt = 50, fdback = 0.1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig= sig+ LocalIn.ar(1); //add some feedback
			sig = DelayN.ar(sig,0.02,SinOsc.kr(flangefreq,0,ampmod,0.005)); //max delay of 20msec
			sig	= MidEQ.ar(sig, 3000, 2, -12); //correct earchannel-resonance
			sig	= MidEQ.ar(sig, 15000, 0.5, 3);
			sig	= LPF.ar(sig, lpfilt);
			sig	= HPF.ar(sig, hpfilt);
			sig	= Limiter.ar(sig, 0.9, 0.01);
			LocalOut.ar(fdback*sig);
			sig = sig * envgate * envpause;
			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "tflanger",
			type: \fx,
			main: \flangefreq,
			sliders: [
				\flangefreq -> ControlSpec(0.001, 10, \exp, 0.001, 0.1, \Hz),
				\ampmod -> ControlSpec(0.001, 0.015, \lin, 0.001, 0.005, \ampmod),
				\fdback -> ControlSpec(0, 1, \lin, 0.01, 0.1, \fdback),
				\lpfilt -> ControlSpec(30, 20000, \exp, 0.1, 10000, \lpfilt),
				\hpfilt -> ControlSpec(30, 20000, \exp, 0.1, 50, \hpfilt),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))
	}
}



