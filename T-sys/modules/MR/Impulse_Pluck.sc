Impulse_Pluck : Tmodule2 {

	*def
	{


	^SynthDef(\impulse_Pluck, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freq = 958, freq_imp = 3, del = 0.05, gain = 2, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			// sig = BPF.ar(Impulse.ar(freq),freq_filter,rq, gain);
			sig = BPF.ar(Pluck.ar(Crackle.ar([1.9,1.8]),Impulse.ar(freq_imp),2,del),freq);
			sig = Mix.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "impulse_Pluck",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 20000, \exp, 0, 958, \Hz),
				\freq_imp -> ControlSpec(0, 100, \lin, 0.01, 3, \freq_imp),
				\del -> ControlSpec(0.0001, 2, \lin, 0.001, 0.05,\del),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}


/*//--tweet0007
r{loop{x={BPF.ar(Pluck.ar(Crackle.ar([1.9,1.8]),Impulse.ar(5.rand+1),0.05,0.05.linrand),1200.rand)}.play(s,0,9);wait(9);x.release(69)}}.play*/





