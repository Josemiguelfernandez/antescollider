FftBinDemand : Tmodule2 {

	*def
	{


	^SynthDef(\fftBinDemand, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, trig = 0, amp = 0, oscilfreq = 50, oscilIn = 200, oscilEnd = 800, speed = 0.05, binfac = 1, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, fftsize, inA, chain, chainA, inB, chainB;
			fftsize = 1024;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			inA = Saw.ar(SinOsc.kr(oscilfreq).range(200, 800));
			inB = sig * 0.7;

			chainA = FFT(LocalBuf(fftsize), inA);
			chainB = FFT(LocalBuf(fftsize), inB);

			chain = PV_CopyPhase(chainB, chainA);
			chain = PV_BinShift(chain, Duty.kr(Drand([0.5, 0.2, 0.4, 0.3, 1]*speed, inf), 0, Dseq([1.5, 0.6, 1.05, 1.07, 0.84, 1.33, 0.67]*binfac, inf)));

			sig = IFFT(chain);


			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "fftBinDemand",
			type: \fx,
			main: \amp,
			sliders: [
				\speed -> ControlSpec(0.01, 4,\lin,0.001, 0.05,\speed),
				\binfac -> ControlSpec(0.01, 20,\lin,0.001, 1,\binfac),
				\oscilfreq -> ControlSpec(1, 200,\lin,1, 50,\oscilfreq),
				\oscilIn -> ControlSpec(1, 1000,\lin,1, 200,\oscilIn),
				\oscilEnd -> ControlSpec(1, 5000,\lin,1, 800,\oscilEnd),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			]
		))

	}

}
