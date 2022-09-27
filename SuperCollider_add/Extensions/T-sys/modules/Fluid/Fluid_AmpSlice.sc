Fluid_AmpSlice : Tmodule2 {

	*def
	{


		^SynthDef(\Fluid_AmpSlice, {|in = 0,  id = 10, fastRampUp = 1, fastRampDown = 1, slowRampUp = 100, slowRampDown = 100, onThreshold = -144, offThreshold = -144, floor = -144, minSliceLength = 2, highPassFreq = 85|
			var sig;

			sig = In.ar(in);

			sig = FluidAmpSlice.ar(sig, fastRampUp, fastRampDown, slowRampUp, slowRampDown, onThreshold, offThreshold, floor, minSliceLength, highPassFreq);

			// onsets.poll;
			SendReply.ar(sig, '/onsetdetect', 1, id);
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_AmpSlice",
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



