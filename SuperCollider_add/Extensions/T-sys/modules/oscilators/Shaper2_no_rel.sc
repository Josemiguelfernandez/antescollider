Shaper2_no_rel : Tmodule2 {

	*def
	{
		^SynthDef(\Shaper2_no_rel, {|out = 0, buf=0, freq=250, atk=1, sus=0, rel=5, curvemax=1, amp=0, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, input, curve, env;

			envgate = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(in_ramp, 1.0, out_ramp, \welch ), gate, doneAction:1);
			// env = EnvGen.kr(Env.linen(atk, sus, rel, 1, [1,0,-1]), doneAction: 2);

			curve = LFNoise1.kr(0.5!8).bipolar(curvemax);
			input = LFTri.ar({freq * Rand(-0.1, 0.1).midiratio} !8);
			input = input.lincurve(-1,1,-1,1,curve);
			sig = Shaper.ar(buf, input);
			// sig = Mix.ar(sig);
			sig = Splay.ar(sig); //spread 8 signals over stereo field
			sig = LeakDC.ar(sig); //remove DC bias
			// sig = Balance2.ar(sig[0], sig[1], pan, amp); //L/R balance (pan)
			// sig = sig * env;
			sig = sig * envgate * envpause;
			Out.ar(out, sig*amp.dbamp.lag);
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Shaper2_no_rel",
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