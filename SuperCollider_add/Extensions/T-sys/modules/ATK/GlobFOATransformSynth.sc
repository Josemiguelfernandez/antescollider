GlobFOATransformSynth : Tmodule2  {

	*def
	{
		^SynthDef(\GlobFOATransformSynth, {|in, out = 0, gatee = 1, amp = 0, lag = 0.2, matrix_ramp = 0.05, gate = 1, free = 1, globtbus|
			var sig, envgate, envpause;


			sig = In.ar(globtbus, 4);


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);



			Out.ar(out, FoaDecode.ar(sig*amp.dbamp.lag, ~decoder))
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		/*		var buffnum;
		buffnum = ~wave_buff[\vbap].bufnum; // VBAP Speaker conf.*/
		^(metadata: (
			synthdefname: "GlobFOATransformSynth",
			type: \fx,
			main: \amp,
			sliders:[
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			]
		))

	}
}

/*SynthDef(\globFOATransformSynth,  { |globtbus=0, heading=0, roll=0, pitch=0|
	var sig = In.ar(globtbus, 4);
	Out.ar(0, FoaDecode.ar(sig, ~decoder));
}).load;*/