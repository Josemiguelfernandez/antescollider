Laconicism_15 : Tmodule2 {

	*def
	{


	^SynthDef(\Laconicism_15, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freqMul = 1, numHarm = 1, ampRFreq = 16, ampExp = 9, fbMul = 100, dist = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, n, v;

			n=LFDNoise0.kr(_);
			v=Blip.ar([2e4,2e4-9]*freqMul,numHarm,n.(ampRFreq)*0.5+0.5**ampExp);
			42.do{v=LeakDC.ar(AllpassC.ar(v,1,n.(5)*5e-2+(5e-2+1e-3),fbMul))};

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;


			sig = Limiter.ar(tanh(v*99*dist));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Laconicism_15",
			type: \gen,
			main: \freqMul,
			sliders: [
				\freqMul -> ControlSpec(0.01, 1, -3, 0, 1,\freqMul),
				\numHarm -> ControlSpec(1, 10, \lin, 0, 1,\numHarm),
				\ampRFreq -> ControlSpec(0.5, 32, \lin, 0, 16,\ampRFreq),
				\ampExp -> ControlSpec(1, 32, \exp, 0, 9,\ampExp),
				\fbMul -> ControlSpec(0.1, 200, \lin, 0, 100,\fbMul),
				\dist -> ControlSpec(0.1, 1, \exp, 0, 1,\dist),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


