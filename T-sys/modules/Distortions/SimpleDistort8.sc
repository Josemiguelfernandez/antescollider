SimpleDistort8 : Tmodule2 {

	*def
	{


		^SynthDef(\simpledistort8, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, lowPassCutoff = 7000, highPassCutoff = 100, postDistCutoff = 18000, distAmt = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, lpf, hpf;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8) + (In.ar(inbus, 8) * ingain);

			lpf = LPF.ar(sig, lowPassCutoff);
			hpf = HPF.ar(lpf, highPassCutoff);
			sig = (hpf * distAmt).distort;
			sig = LPF.ar(sig, postDistCutoff);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "simpledistort8",
			type: \fx,
			main: \distAmt,
			sliders: [
				\distAmt -> ControlSpec(0, 5000, \lin, 0, 1, \distAmt),
				\lowPassCutoff -> ControlSpec(20, 20000, \exp, 0, 7000, \Hz, \lowPassCutoff),
				\highPassCutoff -> ControlSpec(20, 20000, \exp, 0, 100, \Hz, \highPassCutoff),
				\postDistCutoff -> ControlSpec(20, 20000, \exp, 0, 18000, \Hz, \postDistCutoff),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}



