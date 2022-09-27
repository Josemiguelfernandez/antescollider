ParamEq : Tmodule2 {

	*def
	{
		^SynthDef(\ParamEq, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freqlow = 100, amplow = 0, rqlow = 1, freqpeak1 = 250, amppeak1 = 0, rqpeak1 = 1, freqpeak2 = 1000, amppeak2 = 0, rqpeak2 = 1, freqpeak3 = 3500, amppeak3 = 0, rqpeak3 = 1, freqhi = 6000, amphi = 0, rqhi = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.01); // xfade to avoid clicks
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 8); // + (In.ar(inbus, 1) * ingain);
			sig = BLowShelf.ar(sig, freqlow, rqlow, amplow);
			sig = BPeakEQ.ar(sig, freqpeak1, rqpeak1, amppeak1);
			sig = BPeakEQ.ar(sig, freqpeak2, rqpeak2, amppeak2);
			sig = BPeakEQ.ar(sig, freqpeak3, rqpeak3, amppeak3);
			sig = BHiShelf.ar(sig, freqhi, rqhi, amphi);
			sig = RemoveBadValues.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			// Out.ar(outbus, sig* outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "ParamEq",
			type: \fx,
			main: \freqlow,
			sliders: [
				\freqlow -> ControlSpec(20, 20000, \exp, 0, 100, \Hz),
				\amplow -> ControlSpec(0.000001.ampdb, 16.ampdb, \db, 0, 0, \dB),
				\rqlow -> ControlSpec(0.0001, 20.0, \lin, 0.001, 1,\rq),
				\freqpeak1 -> ControlSpec(20, 20000, \exp, 0, 250, \Hz),
				\amppeak1 -> ControlSpec(0.000001.ampdb, 16.ampdb, \db, 0, 0, \dB),
				\rqpeak1 -> ControlSpec(0.0001, 20.0, \lin, 0.001, 1,\rq),
				\freqpeak2 -> ControlSpec(20, 20000, \exp, 0, 1000, \Hz),
				\amppeak2 -> ControlSpec(0.000001.ampdb, 16.ampdb, \db, 0, 0, \dB),
				\rqpeak2 -> ControlSpec(0.0001, 20.0, \lin, 0.001, 1,\rq),
				\freqpeak3 -> ControlSpec(20, 20000, \exp, 0, 3500, \Hz),
				\amppeak3 -> ControlSpec(0.000001.ampdb, 16.ampdb, \db, 0, 0, \dB),
				\rqpeak3 -> ControlSpec(0.0001, 20.0, \lin, 0.001, 1,\rq),
				\freqhi -> ControlSpec(20, 20000, \exp, 0, 6000, \Hz),
				\amphi -> ControlSpec(0.000001.ampdb, 16.ampdb, \db, 0, 0, \dB),
				\rqhi -> ControlSpec(0.0001, 20.0, \lin, 0.001, 1,\rq),
				\amp -> ControlSpec(0.000001.ampdb, 16.ampdb, \db, 0, 0, \dB)

			],
		))
	}
}

// ParamEq.ampdefault
// ParamEq.main.default
/*[ [ 100, 0, 1 ], [ 250, 0, 1 ], [ 1000, 0, 1 ], [ 3500, 0, 1 ], [ 6000, 0, 1 ]

	init { |argFrdb|
		frdb = argFrdb ? [[100,0,1], [250,0,1], [1000,0,1], [3500,0,1], [6000,0,1]];

		synthdef = SynthDef("param_beq", { |out = 0, gate = 1, fadeTime = 0.05, doneAction = 2|
			var frdb, input, env;
			env = EnvGen.kr(Env.asr(fadeTime, 1, fadeTime), gate, doneAction: doneAction);
			input = In.ar(out, numChannels);
			input = BLowShelf.ar(input, *frdb[0][[0,2,1]].lag(0.1));
			input = BPeakEQ.ar(input, *frdb[1][[0,2,1]].lag(0.1));
			input = BPeakEQ.ar(input, *frdb[2][[0,2,1]].lag(0.1));
			input = BPeakEQ.ar(input, *frdb[3][[0,2,1]].lag(0.1));
			input = BHiShelf.ar(input, *frdb[4][[0,2,1]].lag(0.1));
			input = RemoveBadValues.ar(input);
			XOut.ar(out, env, input);
		}).load;
	}
	*/