Osc_wt_HOA3 : Tmodule2 {

	*def
	{
		^SynthDef(\Osc_wt_HOA3, {|out = 0, buf, freq=200, detune=0.2, amp=0, pan=0, atk=0, sus=1, rel=0, c0=1, c1=(-1), in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1, doneAction = 2,  x = 0, y = 0, z = 0, globTBus, lpf_freq = 100|
			var sig, envgate, envpause, env, detuneCtrl, position, hoa_encoder;

			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;

/*			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);*/
			env = EnvGen.ar(Env([0,1,1,0],[atk,sus,rel],[c0,0,c1]), doneAction:doneAction);

			//array of eight Oscs with uniquely detune frequencies
			//and unique initial phase offsets
			detuneCtrl = LFNoise1.kr(0.1!8).bipolar(detune).midiratio;
			sig = Osc.ar(buf, freq * detuneCtrl, {Rand(0,2pi)}!8);
			sig = Mix.ar(sig);
			// sig = Splay.ar(sig); //spread 8 signals over stereo field
			sig = LeakDC.ar(sig); //remove DC bias
			sig = HPF.ar(sig, lpf_freq);
			// sig = Balance2.ar(sig[0], sig[1], pan, amp); //L/R balance (pan)
			// sig = sig * env;
			// sig = sig * envgate * envpause  * env;

			hoa_encoder = HOAEncoder.ar(3, sig*env, position.theta, position.phi, 0, 1,position.rho); // amp in dB

			Out.ar(globTBus, hoa_encoder*amp.dbamp);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Osc_wt_HOA3",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 20000, \exp, 0, 300, \Hz),
				\buf -> ControlSpec(0, 100, \lin, 1, 0, \buf),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -6, \dB)
			],
			soundfileview: [
				\waveview -> "waveview1"
			],
		))
	}

}

