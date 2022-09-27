Fluid_OnsetReplay : Tmodule2 {

	*def
	{


		^SynthDef(\Fluid_OnsetReplay, {|out = 0, in = 0, xFade = 1, trig = 0, amp = 0,  id = 10, matrix_ramp = 0.01, gate = 1, free = 1, metric = 9, threshold = 0.15, minSliceLength = 45, filterSize = 9, frameDelta = 0, windowSize = 128, hopSize = 64, fftSize = -1, maxFFTSize = -1|
			var sig;

			sig = In.ar(in);
			sig = FluidOnsetSlice.ar(sig, metric, threshold, minSliceLength, filterSize, frameDelta, windowSize, hopSize, fftSize, maxFFTSize);
			// onsets.poll;
			SendReply.ar(sig, '/onsetdetect', 1, id);
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_OnsetReplay",
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



