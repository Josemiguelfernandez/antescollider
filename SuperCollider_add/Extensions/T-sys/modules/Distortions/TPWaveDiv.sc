TPWaveDiv : Tmodule2 {

	*def
	{
		^SynthDef(\twaveDiv, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1, lag = 0.2, div = 0,eq = 0.5, res = 0.01, jit = 0.1, jitF = 1, n = 0,
			lss = 0, am = 0|
			var sig, envgate, envpause, cross, omit, ns, sLg, aLag;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			omit = sig * Lag.ar(1-Stepper.ar(sig, 1, 0,(div*40+1).max(1)), div.squared * 0.1);
			sig  = SelectX.ar((div > 0).lag(lag), [sig, omit]);

			jit = jit.lag(lag);
			jitF  = {jitF * Rand(0.99, 1.01)}!1;
			ns  = TRand1.kr(0, 1, jitF,jitF.reciprocal);
			aLag = (1-am * 0.25) * (ns * jit +1);

			sLg = sig * Lag.ar(1-Stepper.ar(sig, 1, 0,((n * (ns*jit +1))*33+1).max(1)), aLag);
			sig = SelectX.ar(am, [sig, sLg]);
			sig = WaveLoss.ar(sig, lss* 33, 33, (jit + 1).round);
			sig = PolSat.ar(sig);
			sig = PBandpass2.ar(sig, eq.lag(lag), res);
			sig = LeakDC.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "twaveDiv",
			type: \fx,
			main: \div,
			sliders: [
				\lag -> ControlSpec(0.01, 1, \exp, 0.001, 1, \lag),
				\div -> ControlSpec(0, 1, \amp, 0.001, 0.01),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0),
				\eq  -> ControlSpec(0, 1, \lin, 0, 0.5),
				\lss -> ControlSpec(0, 1, \lin, 0, 0),
				\am  -> ControlSpec(0, 1, 'lin', 0, 0)
			],
		))
	}
}

