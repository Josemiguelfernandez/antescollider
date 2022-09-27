Fluid_NoveltySlice : Tmodule2 {

	*def
	{


		^SynthDef(\Fluid_NoveltySlice, {|in = 0,  id = 10, algorithm = 0, kernelSize = 3, threshold = 0.8, filterSize = 1, minSliceLength = 2, windowSize = 1024, hopSize = -1, fftSize = -1|
			var sig;

			sig = In.ar(in);

			sig = FluidNoveltySlice.ar(sig, algorithm, kernelSize, threshold, filterSize, minSliceLength, windowSize, hopSize, fftSize, -1, -1, -1);
			// onsets.poll;
			SendReply.ar(sig, '/onsetdetect', 1, id);
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_NoveltySlice",
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