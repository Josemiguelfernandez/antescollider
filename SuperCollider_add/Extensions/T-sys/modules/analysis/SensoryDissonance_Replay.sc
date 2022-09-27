SensoryDissonance_Replay : Tmodule2 {

	*def
	{


	^SynthDef(\SensoryDissonance_Replay, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, trig = 0, amp = 0, id = 10, matrix_ramp = 0.01, gate = 1, free = 1, impulse_freq = 20|
			var sig, sigin, envgate, envpause, cross, chain, fftsize, sensorydissonance;

			fftsize = 2048;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sigin = In.ar(in);

			chain = FFT(LocalBuf(fftsize), sigin);
			sensorydissonance = SensoryDissonance.kr(chain);


			SendReply.kr(Impulse.kr(impulse_freq), '/sensorydissonance', sensorydissonance, id);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "SensoryDissonance_Replay",
			type: \fx,
			main: \thresh,
			sliders: [
				// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
				// \smask -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.25, \smask),
				// \tmask -> ControlSpec(0.0, 1.0, \lin, 0.01, 1, \tmask),
				// \id -> ControlSpec(0, 1000, \lin, 1, 10, \rlxtime),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),

			],

		))

	}

}