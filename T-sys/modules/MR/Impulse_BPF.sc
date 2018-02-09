Impulse_BPF : Tmodule2 {

	*def
	{


	^SynthDef(\impulse_BPF, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freq = 14.5, freq_filter = 2791, rq = 0.195, gain = 2, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = BPF.ar(Impulse.ar(freq),freq_filter,rq, gain);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "impulse_BPF",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(0, 100, \lin, 0.01, 14.5, \freq),
				\freq_filter -> ControlSpec(20, 20000, \exp, 0, 2791, \Hz),
				\rq -> ControlSpec(0.0001, 20.0, \lin, 0.001, 1,\rq),
				\gain -> ControlSpec(1, 10, \lin, 0.01, 2, \gain),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


// r{99.do{x={Pan2.ar(BPF.ar(Impulse.ar(25.linrand+0.5),9999.linrand,0.3.linrand,5),1.0.rand2)}.play;3.wait;x.release(9)}}.play//#SuperCollider





