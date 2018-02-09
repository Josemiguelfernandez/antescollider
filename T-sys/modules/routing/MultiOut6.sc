MultiOut6 : Tmodule2  {

	*def
	{
		^SynthDef(\MultiOut6, {|in = 0, out = 0, offset = 0, in_ramp = 0.01, out_ramp = 0.01, amp = 0, out_array = #[1, 1, 1, 1, 1, 1], updateFreq = 10, peakLag = 0, index = -1, gatee = 1|
			var sig;


			sig = In.ar(in, 1);

			sig = sig*EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gatee, doneAction:2);
			SendPeakRMS.kr(sig, updateFreq, peakLag, '/meterAuxIn', index);
			Out.ar(out+offset, sig * amp.dbamp.lag * out_array); //salida directa a un bus
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "MultiOut6",
			type: \inAux,
			main: \amp,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			]
		))
	}

}


//AudioOut.metadata[\metadata][\sliders]

//Synth(\audioIn)