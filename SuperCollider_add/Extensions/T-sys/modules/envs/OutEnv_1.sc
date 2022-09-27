OutEnv1 : Tmodule2  {


	*def
	{


	^SynthDef(\OutEnv1, {|in, out = 0, env, amp = 0, dur = 1, i_doneAction = 7|
			var sig;
			sig = In.ar(in, 1);

			sig = sig*PlayBuf.ar(1, env, 1/((SampleRate.ir/BufSamples.kr(env)) * dur), doneAction:i_doneAction); //BufRateScale.kr(env)SampleRate.ir * dur
			sig = sig * amp.dbamp.lag(1);

			Out.ar(out, sig); //salida directa a un bus

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "OutEnv1",
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