Chromagram_Replay : Tmodule2 {

	*def
	{


	^SynthDef(\Chromagram_Replay, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0,  fftsize = 2048, n = 12, tuningbase =  32.703195662575, octaves = 8, integrationflag = 0, coeff = 0.9, octaveratio = 2, perframenormalize = 0, xFade = 1, trig = 0, amp = 0, id = 10, matrix_ramp = 0.01, gate = 1, free = 1, impulse_freq = 20|

			var  sigin, envgate, envpause, cross, chain, chromagram = [];

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			cross = xFade * envgate * envpause;

			sigin = In.ar(in);

			chain = FFT(LocalBuf(fftsize), sigin);

			chromagram = Chromagram.kr(chain, fftsize, n); //, tuningbase, octaves, integrationflag, coeff, octaveratio, perframenormalize);

			SendReply.kr(Impulse.kr(impulse_freq), '/chromagram', chromagram, id);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Chromagram_Replay",
			type: \fx,
			main: \thresh,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),

			],

		))

	}
}


