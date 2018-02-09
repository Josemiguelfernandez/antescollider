AudioOutEnv : Tmodule2  {


	*def
	{


	^SynthDef(\audioOutEnv, {|in, out = 0, outbus, outgain = -120, gatee = 1, env = -1, amp = 0, dur = 1|
			var sig;
			sig = In.ar(in, 1);
			//outbus = in;
			sig = sig*PlayBuf.ar(1, env, 1/((SampleRate.ir/BufSamples.kr(env)) * dur), doneAction:14); //BufRateScale.kr(env)SampleRate.ir * dur
			sig = sig * amp.dbamp.lag(1);
			// SendPeakRMS.kr(sig, updateFreq, peakLag, '/meter', index);
			Out.ar(out, sig); //salida directa a un bus
			Out.ar(in, sig * outgain.dbamp.lag(1)); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "audioOutEnv",
			type: \out,
			sliders:[
				\amp -> ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB"),
				\outgain -> ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB")
			]
		))

	}
}


//AudioOut.metadata[\metadata][\sliders]

/*SynthDef(\distort, { arg in= 12, out=0, pregain=40, amp=0.2;
	var src= In.ar(in, 2);
	Out.ar(out, (src * pregain).distort * amp);
}, #[\ir, \ir, 0.1, 0.1, 0]).add;*/