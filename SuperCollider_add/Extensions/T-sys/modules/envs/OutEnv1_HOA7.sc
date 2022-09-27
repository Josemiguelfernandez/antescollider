OutEnv1_HOA7 : Tmodule2  {


	*def
	{


	^SynthDef(\OutEnv1_HOA7, {|in, globTBus, env, amp = 0, dur = 1, i_doneAction = 14|
			// var sig, position, distance;
			var sig, gain, coords, n=1, radius, az, ele;

			sig = In.ar(in, 1);

			gain = \gain.kr(([0.0] ! n).flat);
			coords = \coords.kr(([1.0, 0.0, 0.0] ! n).flat);
/*			radius = coords[0];
			az = coords[1];
			ele = coords[2];*/
/*			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;
			distance = position.rho;
			distance = Select.kr(distance < 0.001, [distance, 0.001]);*/


			sig = sig*PlayBuf.ar(1, env, 1/((SampleRate.ir/BufSamples.kr(env)) * dur), doneAction:i_doneAction)*amp.dbamp.lag(1); //BufRateScale.kr(env)SampleRate.ir * dur
			// sig = AmbisonicEncoder.ar(7, sig, gain, radius, az, ele);
			sig = AmbisonicEncoder_in1.ar(7, sig, gain, coords);
			// sig = AmbisonicEncoder.ar(7, sig, 0, distance, position.theta*(180/pi), position.phi*(180/pi));

			Out.ar(globTBus, sig); //salida directa a un bus

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "OutEnv1_HOA7",
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
