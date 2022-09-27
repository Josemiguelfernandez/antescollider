Fluid_TransientSlice : Tmodule2 {

	*def
	{


		^SynthDef(\Fluid_TransientSlice, {|in = 0,  id = 10, order = 20, blockSize = 256, padSize = 128, skew = 0.0, threshFwd = 2.0, threshBack = 1.1, windowSize = 14, clumpLength = 25, minSliceLength = 1000|
			var sig;

			sig = In.ar(in);

			sig = FluidTransientSlice.ar(sig, order, blockSize, padSize, skew, threshFwd, threshBack, windowSize, clumpLength, minSliceLength);

			// onsets.poll;
			SendReply.ar(sig, '/onsetdetect', 1, id);
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_TransientSlice",
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



