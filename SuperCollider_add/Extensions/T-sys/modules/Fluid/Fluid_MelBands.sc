Fluid_MelBands : Tmodule2 {

	*def
	{


		^SynthDef(\Fluid_MelBands, {|in = 0, id = 10, numBands = 40, minFreq = 20, maxFreq = 20000, normalize = 1, scale = 0, windowSize = 1024, trig_rate = 30, lagTimeU = 0.001, lagTimeD = 0.1|
			var sig, trig;

			sig = In.ar(in);
			trig = Impulse.kr(trig_rate);
			/*			sig = FluidMelBands.kr(sig, numBands: numBands, minFreq: minFreq, maxFreq: maxFreq, normalize: normalize, scale: scale, windowSize: windowSize);*/
			sig = FluidMelBands.kr(sig);
			sig = LagUD.kr(sig, lagTimeU, lagTimeD);
			SendReply.kr(trig, '/fluid_melbands', sig, id);
		}).load;
	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Fluid_MelBands",
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


