Fluid_SpectralShape : Tmodule2 {

	*def
	{


		^SynthDef(\Fluid_SpectralShape, {|in = 0, id = 10, minFreq = 0, maxFreq = -1, rolloffPercent = 95, unit = 0, power = 0, windowSize = 1024, hopSize = -1, fftSize = -1, maxFFTSize = -1, trig_rate = 30, lagTimeU = 0.001, lagTimeD = 0.1|
			var sig, trig;

			sig = In.ar(in);
			trig = Impulse.kr(trig_rate);
			/*			sig = FluidSpectralShape.kr(sig, minFreq:minFreq, maxFreq:maxFreq, rolloffPercent:rolloffPercent, unit:unit, power:power, windowSize:windowSize, hopSize:hopSize, fftSize:fftSize, maxFFTSize:maxFFTSize);*/
			sig = FluidSpectralShape.kr(sig);
			sig = LagUD.kr(sig, lagTimeU, lagTimeD);
			// onsets.poll;
			SendReply.kr(trig, '/fluid_spectralshape', sig, id);
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_SpectralShape",
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


