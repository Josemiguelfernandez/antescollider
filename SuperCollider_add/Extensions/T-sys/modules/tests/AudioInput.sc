AudioInput : Tmodule2  {

	*def
	{
		^SynthDef(\AudioInput, {|input = 0, out = 0, outbus = 5, outgain = -120, matrix_ramp = 0.01, amp = 0, inlev = 0, updateFreq = 10, peakLag = 0, index = -1, gate = 1, free = 1|
			var sig, envgate, envpause;
			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			sig = SoundIn.ar(input); //  * inlev.dbamp.lag(0.2)

			sig = sig * envgate * envpause * amp.dbamp.lag(1);
			//SendPeakRMS.kr(sig, updateFreq, peakLag, '/meterIn', index);
			Out.ar(out, sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag(1)); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "AudioInput",
			type: \in,
			main: \amp,
			sliders: [
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			]
		))
	}

}


//AudioOut.metadata[\metadata][\sliders]

//Synth(\audioIn)