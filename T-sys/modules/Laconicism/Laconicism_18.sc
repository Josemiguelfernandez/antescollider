Laconicism_18 : Tmodule2 {

	*def
	{


	^SynthDef(\laconicism_18, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, lagMul = 1, fbck = 9, cFMul = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, n, v;

			n=LFDNoise0.kr(*_);
			v=Blip.ar([60,61],5,n.(4)**8);

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			12.do{v=LeakDC.ar(CombC.ar(v,1,n.([1*cFMul,0.05,0.06]).lag(5e3 * lagMul),fbck))};

			sig = Limiter.ar(v,0.9,1);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "laconicism_18",
			type: \gen,
			main: \lagMul,
			sliders: [
				\lagMul -> ControlSpec(0, 2, \lin, 0, 1,\lagMul),
				\fbck -> ControlSpec(0.01, 100, \exp, 0, 9,\rel),
				\cfMul -> ControlSpec(0.1, 100, \exp, 0, 1,\cfMul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

