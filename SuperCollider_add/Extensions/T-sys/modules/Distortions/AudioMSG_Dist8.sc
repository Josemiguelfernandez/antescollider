AudioMSG_Dist8 : Tmodule2 {

	*def
	{


		^SynthDef(\AudioMSG_Dist8, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, index = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, lpf, hpf;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8); // + (In.ar(inbus, 1) * ingain);

			sig = AudioMSG.ar(sig, index);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "AudioMSG_Dist8",
			type: \fx,
			main: \index,
			sliders: [
				\index -> ControlSpec(0, 2pi, \lin, 0.001, 0, \index),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}



// {AudioMSG.ar(SinOsc.ar(440, 0, 0.3), MouseX.kr(0, 2pi))}.play(s);
