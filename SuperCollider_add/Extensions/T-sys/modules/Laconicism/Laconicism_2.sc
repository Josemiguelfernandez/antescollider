Laconicism_2 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_2, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, pFreq = 90, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, x;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			LocalOut.ar(x = LFNoise1.ar(0.5*LocalIn.ar(1)+0.1,0.5,0.5));

			sig = Limiter.ar(PitchShift.ar(PitchShift.ar(Pulse.ar([pFreq,pFreq*1.0001111],x),10,x*4,x),10,4-(x*4),1-x));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_2",
			type: \gen,
			main: \pFreq,
			sliders: [
				\pFreq -> ControlSpec(1, 15000, \lin, 10, 90,\pFreq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

