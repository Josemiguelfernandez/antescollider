Fluid_Chroma : Tmodule2 {

	*def
	{


		^SynthDef(\Fluid_Chroma, {|in = 0, id = 10, numChroma = 24, ref = 440, normalize = 0, minFreq = 0, maxFreq = -1, windowSize = 1024, hopSize = -1, fftSize = -1, maxFFTSize = -1, maxNumChroma, trig_rate = 30, lagTimeU = 0.001, lagTimeD = 0.1|
			var sig, trig;

			sig = In.ar(in);
			trig = Impulse.kr(trig_rate);
			/*			sig = FluidChroma.kr(sig, numChroma:numChroma, ref:ref, normalize:normalize, minFreq:minFreq, maxFreq:maxFreq, windowSize:windowSize, hopSize:hopSize, fftSize:fftSize, maxFFTSize:maxFFTSize);*/
			sig = FluidChroma.kr(sig, numChroma: 24, ref:442, normalize:1, minFreq:200, maxFreq: 7000, maxNumChroma:24, windowSize: 4096);

			sig = LagUD.kr(sig, lagTimeU, lagTimeD);
			// sig.poll;
			SendReply.kr(trig, '/fluid_chroma', sig, id);
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_Chroma",
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



