Bowed_string : Tmodule2 {

	*def
	{


		^SynthDef(\bowed_string, {|out, xFade = 1, decay = 1, y = 0, freq = 43, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, exc;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			// cross = xFade * envgate * envpause;

			exc = BrownNoise.ar([0.007,0.007]);
			sig = (DynKlank.ar(`[
					Array.series(12, freq, freq),
					Array.geom(12,1,rrand(0.7,0.9)),
					Array.fill(12, {rrand(1.0,3.0)})
				], exc) * 0.1).softclip;


			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "bowed_string",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 1000, \lin, 0.001, 0,\freq),
				// \y -> ControlSpec(0, 1, \lin, 0.001, 0,\y),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}



// Synth(\bowed_string)



/*(
{
var root, scale;
			// bowed string
		var trig, p, s, exc, x, freq;
		//root = rrand(3,6);
		root = 5;
		scale = #[0,2,4,5,7,9,11];
			freq = (scale.choose + #[24,36,48,60,72,84].choose + root).midicps;
	freq.postln;
			exc = BrownNoise.ar([0.007,0.007]);
			s = (Klank.ar(`[
					Array.series(12, freq, freq),
					Array.geom(12,1,rrand(0.7,0.9)),
					Array.fill(12, {rrand(1.0,3.0)})
				], exc) * 0.1).softclip;
}.play;
)*/