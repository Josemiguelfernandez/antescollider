Fluid_MFCC : Tmodule2 {

	*def
	{


		^SynthDef(\Fluid_MFCC, {|in = 0, id = 10, numCoeffs = 13, numBands = 40, startCoeff = 0, minFreq = 20, maxFreq = 20000, windowSize = 1024, hopSize = -1, fftSize = -1, maxFFTSize = -1, trig_rate = 30, lagTimeU = 0.001, lagTimeD = 0.1|
			var sig, trig;

			sig = In.ar(in);
			trig = Impulse.kr(trig_rate);
			/*			sig = FluidMFCC.kr(sig, numCoeffs:numCoeffs, numBands:numBands, startCoeff:startCoeff, minFreq:minFreq, maxFreq:maxFreq, windowSize:windowSize, hopSize:hopSize, fftSize:fftSize, maxFFTSize:maxFFTSize, maxNumCoeffs);*/
			sig = FluidMFCC.kr(sig);
			sig = LagUD.kr(sig, lagTimeU, lagTimeD);
			SendReply.kr(trig, '/fluid_mfcc', sig, id);
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_MFCC",
			type: \fx,
			main: \thresh,
			sliders: [
				// \trig -> ControlSpec(0, 1, \lin, 1, 0, \trig),
				\thresh -> ControlSpec(0.0, 1.0, \lin, 0.01, 1, \thresh),
				\relaxtime -> ControlSpec(0.1, 5.0, \lin, 0.01, 0.5, \rlxtime),
				// \id -> ControlSpec(0, 1000, \lin, 1, 10, \rlxtime),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB),

			],

		))

	}

}

