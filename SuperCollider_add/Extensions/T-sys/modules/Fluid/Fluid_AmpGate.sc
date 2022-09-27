Fluid_AmpGate : Tmodule2 {

	*def
	{


		^SynthDef(\Fluid_AmpGate, {|in = 0,  id = 10, rampUp = 10, rampDown = 10, onThreshold = -90, offThreshold = -90, minSliceLength = 1, minSilenceLength = 1, minLengthAbove = 1, minLengthBelow = 1, lookBack = 0, lookAhead = 0, highPassFreq = 85|
			var sig;

			sig = In.ar(in);

			sig = FluidAmpGate.ar(sig, rampUp, rampDown, onThreshold, offThreshold, minSliceLength, minSilenceLength, minLengthAbove, minLengthBelow, lookBack, lookAhead, highPassFreq);
			// onsets.poll;
			SendReply.ar(sig, '/ampgatedetect', sig, id);
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_AmpGate",
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



