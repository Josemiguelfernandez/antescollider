NTube2 : Tmodule2 {

	*def
	{



		^SynthDef(\nTube2, {|out = 0, outbus, outgain = -120, amp = -6, mod1 = 0, mod2=0.5, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1|
			var sig, sig1, sig2, sig3, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);

			sig = (NTube.ar(Impulse.ar(mod1)*MouseY.kr(0.0,1.0),`(Array.rand(11,0.95,0.99)),`(Array.series(9,0.8,-0.1)),`(Array.rand(10,0.01,0.05)) )*0.025).dup;

			sig = Mix.ar(sig);

			sig = sig * envgate * envpause * amp.dbamp.lag;
			Out.ar(out, sig);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "nTube2",
			type: \gen,
			main: \mod1,
			sliders: [
				\mod1 -> ControlSpec(16,1600,\lin,0.001,100,\mod1),
				\mod2 -> ControlSpec(0.0,1.0,\lin,0.001,0.5,\mod2),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \amp)
			]
		))
	}

}




