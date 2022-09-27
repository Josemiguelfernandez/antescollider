TDissonator : Tmodule2 {

	*def
	{


		^SynthDef(\dissonator, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, amp = 0, mix = 1, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, mod;
			var filterfreqs = Array.geom(23, 60, 2**(1/3));
			var rq = ((2**(1/3)) - 1) / (2**(1/6));
			var width = 2**(1/6);
			var fmin = filterfreqs / width;
			var fmax = filterfreqs * width;
			var g = { |f| 2.27 * (f**0.477) };


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(out);
			sig = BPF.ar(sig, filterfreqs, rq);

			mod = SinOsc.ar(0.5 * g.value((0.2*fmin) + (0.8*fmax)));
			mod = (1 - mix) + (mix * mod);

			sig = sig * mod;
			sig = Mix(sig);

			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "dissonator",
			type: \fx,
			main: \mix,
			sliders: [
				\mix -> ControlSpec(0.01, 1,\lin,0.001,1,\mix),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}



