NTube1 : Tmodule2 {

	*def
	{



		^SynthDef(\nTube1, {|out = 0, outbus, outgain = -120, amp = -6, mod1 = 0, mod2=0.5, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1|
			var sig, sig1, sig2, sig3, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);

			sig = (NTube.ar(PinkNoise.ar,`[0.97,1.0,1.0,1.0,1.0,0.97],`[0.5, mod1 ,0.2,-0.4],`([0.01,0.02,0.01,0.005,0.05]*mod2))*0.1).dup;

			sig = Mix.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;
			Out.ar(out, sig);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "nTube1",
			type: \gen,
			main: \mod1,
			sliders: [
				\mod1 -> ControlSpec(-1.0,1.0,\lin,0.001,0,\mod1),
				\mod2 -> ControlSpec(0.001,1.0, \exp, 0, 0.5, \mod2),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \amp)
			]
		))
	}

}




// {(NTube.ar(PinkNoise.ar,`[0.97,1.0,1.0,1.0,1.0,0.97],`[0.5,MouseY.kr(-1.0,1.0),0.2,-0.4],`([0.01,0.02,0.01,0.005,0.05]*MouseX.kr(0.001,1.0,'exponential')))*0.1).dup}.play