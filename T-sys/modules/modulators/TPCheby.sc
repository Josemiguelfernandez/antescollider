TPCheby : Tmodule2 {
	classvar <info			= "7th order dynamic cheby shaper";
	classvar n = 8;

	*def {
		var bands = 8;

		^SynthDef("pcheby", {|in = 0, inbus, ingain = 0, out = 0, outbus,outgain = 0,
			amp = 1, lag = 0.1, tbus = 60, drv = 0, eq = 0.5, xFade = 1,
			matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, partvol, envgate, envpause, cross;
			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			//sig = SinOsc.ar(400);
			drv = drv.dbamp.lag(lag);
			sig = Limiter.ar(sig * drv);
			partvol = Control.names(\partvol).kr(Array.fill(bands, {0}));
			sig = ChebyShaper.ar(sig, partvol.lag(lag));
			sig = P2Shelf.ar(sig, eq.lag(lag));
			sig = LeakDC.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;


	}

	*metadata
	{
		^(metadata: (
			synthdefname: "pcheby",
			type: \fx,
			main: \eq,
			sliders: [

				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\drv -> ControlSpec(0.dbamp, 24.dbamp, \db, 0, 1, \dB),
				\eq -> ControlSpec(0, 1, \lin, 0, 0.5),
				\lag -> ControlSpec(0.01, 1, \exp, default:0.1)

			],
			multisliders: [
				\partvol -> [ControlSpec(0, 1, \amp, 0, 1), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\amps -> [\multisliders, \partvol,{ 0}, n],
			]
		)
		)
	}
}


//TPCheby.def