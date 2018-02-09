OnsetAttackSlope : Tmodule2 {

	*def
	{


		^SynthDef(\onsetAttackSlope, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, windowsize = 1024, peakpicksize = 20, leak = 0.999, energythreshold = 0.01, sumthreshold = 20, mingap = 30, numslopesaveraged = 10, id = 1000, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, onsets;


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in);

			// chain = FFT(LocalBuf(fftsize), sigin);
			onsets = AttackSlope.kr(sig, windowsize, peakpicksize, leak, energythreshold, sumthreshold, mingap, numslopesaveraged).poll;

			onsets = K2A.ar(onsets[0]);
			onsets = A2K.kr(onsets);

			SendReply.kr(onsets, '/onsetdetect', 1, id);

			/*			sig = PinkNoise.ar(Decay.kr(onsets, 0.2));
			Out.ar(0, sig);*/

			/*			sig = WhiteNoise.ar(EnvGen.kr(Env.perc(0.001, 0.1, 0.2), onsets));

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar*/
	}).load;

}

*metadata
{
	^(metadata: (
		synthdefname: "onsetAttackSlope",
		type: \fx,
		main: \energythreshold,
		sliders: [
			// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
			\energythreshold -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.01, \energythreshold),
			\sumthreshold -> ControlSpec(1, 100, \lin, 1, 20, \sumthreshold),
			\peakpicksize -> ControlSpec(1, 50, \lin, 1, 20, \peakpicksize),
			\mingap -> ControlSpec(1, 50, \lin, 1, 30, \mingap),
			\leak -> ControlSpec(0.0, 1.0, \lin, 0.01, 0.999, \leak),
			\numslopesaveraged -> ControlSpec(1, 50, \lin, 1, 10, \numslopesaveraged),

			\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -120, \dB)
		],

	))

}

}

