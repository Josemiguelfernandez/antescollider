AudioOut : Tmodule2  {


	*def
	{


	^SynthDef(\audioOut, {|in, out = 0, outbus, outgain = 0, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, updateFreq = 10, peakLag = 0, index = -1|
			var sig;
			sig = In.ar(in, 1);
			//outbus = in;
			sig = sig*EnvGen.kr(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14); //doneAction: 5 libera todos los nodes mas el grupo
			sig = sig * amp.dbamp.lag(1);
			SendPeakRMS.kr(sig, updateFreq, peakLag, '/meter', index);
			Out.ar(out, sig); //salida directa a un bus
			Out.ar(in, sig * outgain.dbamp.lag(1)); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "audioOut",
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