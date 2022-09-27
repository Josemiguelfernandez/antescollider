AudioInAux2 : Tmodule2  {

	*def
	{
		^SynthDef(\AudioInAux2, {|in = 0, out = 0, in_ramp = 0.01, out_ramp = 0.01, amp = 0, updateFreq = 10, peakLag = 0, index = -1, gatee = 1|
			var sig;


			sig = In.ar(in, 8);

			sig = sig*EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gatee, doneAction:2);
			SendPeakRMS.kr(sig, updateFreq, peakLag, '/meterAuxIn', index);
			Out.ar(out, sig * amp.dbamp.lag); //salida directa a un bus
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "audioAuxIn",
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