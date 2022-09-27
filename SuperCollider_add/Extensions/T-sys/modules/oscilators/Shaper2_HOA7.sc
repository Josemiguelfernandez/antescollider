Shaper2_HOA7 : Tmodule2 {

	*def
	{
		^SynthDef(\Shaper2_HOA7, {|out = 0, buf=0, freq=250, atk=1, sus=0, rel=5, c0=1, c1=(-1), curvemax=1, amp=0, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1, doneAction = 2,  x = 0, y = 0, z = 0, globTBus, lpf_freq = 5|
			var sig, envgate, envpause, input, curve, env, position, distance, hoa_encoder;

			/*			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);*/
			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;
			distance = position.rho;

			env = EnvGen.ar(Env([0,1,1,0],[atk,sus,rel],[c0,0,c1]), doneAction:doneAction);


			curve = LFNoise1.kr(0.5!8).bipolar(curvemax);
			input = LFTri.ar({freq * Rand(-0.1, 0.1).midiratio} !8);
			input = input.lincurve(-1,1,-1,1,curve);
			sig = Shaper.ar(buf, input);
			sig = Mix.ar(sig);
			// sig = Splay.ar(sig); //spread 8 signals over stereo field
			sig = LeakDC.ar(sig); //remove DC bias
			sig = HPF.ar(sig, lpf_freq);

			// hoa_encoder = HOAEncoder.ar(5, sig*env, position.theta, position.phi, 0, 1,position.rho); // amp in dB
			hoa_encoder = AmbisonicEncoder.ar(7, sig*env, 0, distance, position.theta*(180/pi), position.phi*(180/pi));

			Out.ar(globTBus, hoa_encoder*amp.dbamp);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Shaper2_HOA7",
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

/*(
SynthDef(\Shaper2, {
arg buf=0, freq=250, amp= 0.2, out=0, atk=1, sus=0, rel=5, curvemax=1;
var sig, input, curve, env;
env = EnvGen.kr(Env.linen(atk, sus, rel, 1, [1,0,-1]), doneAction: 2);
curve = LFNoise1.kr(0.5!8).bipolar(curvemax);
input = LFTri.ar({freq * Rand(-0.1, 0.1).midiratio} !8);
input = input.lincurve(-1,1,-1,1,curve);
sig = Shaper.ar(buf, input);
sig = Splay.ar(sig) * amp*env;
sig = LeakDC.ar(sig);
Out.ar(out, sig)
}).load;
)*/