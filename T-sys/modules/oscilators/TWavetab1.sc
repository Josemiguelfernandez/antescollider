TWavetab1 : Tmodule2 {

	*def
	{



		^SynthDef(\TWavetab1, {|out = 0, outbus, outgain = -120, amp = 0, freq = 300, buf, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);

			sig = Osc.ar(buf, freq.lag, 0,  amp.dbamp.lag);
			sig = sig * envgate * envpause;

			Out.ar(out, sig);
			Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TWavetab1",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 20000, \exp, 0, 300, \Hz),
				\buf -> ControlSpec(0, 100, \lin, 1, 0, \buf),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \dB)
			],
			soundfileview: [
				\waveview -> "waveview1"
			],
		))
	}

}


