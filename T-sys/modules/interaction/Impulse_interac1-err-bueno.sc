Impulse_interac1__ : Tmodule2 {

	*def
	{


		^SynthDef(\impulse_interac1, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, x = 0, y = 0, amp = 0,  matrix_ramp = 0.01, freq = 10, gate = 1, free = 1|

			var sig, envgate, envpause, cross, pitch, exc, pluse;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			pluse = LFPulse.kr(freq,0,0.1,0.002);
			exc = LPZ1.ar(GrayNoise.ar([amp,amp]));
			sig = Klank.ar(`[FloatArray.fill(4, { rrand(80.0,400.0) }),
				nil,
				FloatArray[1, 1, 1, 1]], exc).abs;
			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "impulse_interac1",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(0, 200, \lin, 1, 10,\x),
				\x -> ControlSpec(0, 1, \lin, 0.001, 0,\x),
				\y -> ControlSpec(0, 1, \lin, 0.001, 0,\y),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}








/*(
{
	var exc, pluse;
	pluse = LFPulse.kr(8,0,0.1,0.002);
	exc = LPZ1.ar(GrayNoise.ar([amp,amp]));
	Klank.ar(`[FloatArray.fill(4, { rrand(80.0,400.0) }),
		nil,
		FloatArray[1, 1, 1, 1]], exc).abs;
}.play;
)*/