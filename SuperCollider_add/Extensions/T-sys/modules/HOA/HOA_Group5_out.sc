HOA_Group5_out : Tmodule2  {


	*def
	{


	^SynthDef(\HOA_Group5_out, {|in, out = 0, outbus, outgain = -120, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, amp = 0, updateFreq = 10, pos = 0, width = 2, orientation = 0.5, peakLag = 2, index = -1, globTBus|
			var sig;
			sig = In.ar(in, 25);
			//outbus = in;
			// sig = sig*; //doneAction: 5 libera todos los nodes mas el grupo
			sig = sig * amp.dbamp.lag(1);
			// SendPeakRMS.kr(Mix(sig), updateFreq, peakLag, '/meter', index);
			//sig = PanAz.ar(8, sig, pos.lag(0.2), 1, width, orientation);
			Out.ar(globTBus, sig*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14)); //salida directa a un bus
			//Out.ar(in, sig * outgain.dbamp.lag(1)); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Group5_out",
			type: \out,
			sliders:[
				// \pos -> ControlSpec(0, 2, \lin, 0.001, 0, \pos),
				// \width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\amp -> ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB"),
				// \outgain -> ControlSpec(0.ampdb, 1.ampdb, \db, units: " dB")
			]
		))

	}
}


//AudioOut.metadata[\metadata][\sliders]

/*SynthDef(\distort, { arg in= 12, out=0, pregain=40, amp=0.2;
	var src= In.ar(in, 2);
	Out.ar(out, (src * pregain).distort * amp);
}, #[\ir, \ir, 0.1, 0.1, 0]).add;*/


/*
Array.series(8, 0)*(2/8)

[ 0, 0.25, 0.5, 0.75, 1, 1.25, 1.5, 1.75 ]
*/

