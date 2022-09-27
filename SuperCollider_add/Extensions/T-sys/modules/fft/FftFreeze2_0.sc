FftFreeze2_0 : Tmodule2 {

	*def
	{


	^SynthDef(\fftFreeze2, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = -120, xFade = 1, trig = 0, amp = 0, winsize = 0.1, buffer, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, chain, fftsize, onsets;
			/*fftsize = 2048;
			buffer= LocalBuf(2**11).clear;*/

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			chain = FFT(buffer, sig);

			// onsets = Trig1.kr(trig, 2048/48000 * 0.5 + 0.002);

			chain = PV_Freeze(chain, DelayN.kr(trig, 0.05, 0.05)); //DelayN.kr(abs(onsets - 1), 0.05, 0.05));

			// chain = PV_Freeze(chain, trig);
			sig = IFFT(chain);


			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "fftFreeze2",
			type: \fx,
			main: \amp,
			sliders: [
				// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),
				\buffer -> ControlSpec(1, 100, \lin, 1, 0, \buffer)
			],
			buttons: [ \play -> [[["Freeze", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \trig, 1, \trig, 0]
			]
		))

	}

}

/*a = Synth(\fftMagFreeze, [\in, 60, \t_trig, 1], 1000, 'addToTail')

a.free*/