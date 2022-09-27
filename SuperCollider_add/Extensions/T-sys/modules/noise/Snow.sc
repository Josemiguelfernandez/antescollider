Snow : Tmodule2 {

	*def
	{
		^SynthDef(\Snow, {|out, amp=0, speed=1, density = 1, tone = 1, blur = 1, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, size, trig, bpfreq, bpfrq, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			size = 20;
			trig = Impulse.ar({ rrand(1/1,15.0) } ! size * density);
			sig = { WhiteNoise.ar } ! size;
			sig = sig.collect({ arg sig, x;
				bpfreq = exprand(50.0,16000) * tone;
				bpfrq = 0.1 * SinOsc.ar(( x+1 ) * 1/153 * speed).exprange(0.1,4) * blur;
				sig = BPF.ar(sig,  bpfreq, bpfrq) * 34;
				sig = BPF.ar(sig,  bpfreq, bpfrq) * 4;
				sig = sig * EnvGen.ar(Env([0,1,0],[0.1,0.8]), trig[x]);
				//sig = sig.fold2( LFNoise0.kr(1/23 * ( x+1 ) * speed).range(0.01,8) ) * 4;
				sig = sig * SinOsc.ar(1/700 * ( x+1 ) * speed).range(0.01,1);
			});

			sig = Mix.new(sig); // mix channels

			sig = 0.25 * sig * envgate * envpause;
			Out.ar(out, sig*amp.dbamp.lag(1));
			// sig = Pan2.ar(sig, ( { rrand(-1.0,1) } ! size * SinOsc.ar(1/132 * (1..size) * speed).range(0,1) ), amp).mean;
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Snow",
			type: \gen,
			main: \speed,
			sliders:  [
				// \freq -> ControlSpec(20, 15000, \exp,0.01,200,\Hz),
				\speed -> ControlSpec(0.05, 20,\lin,0.001,1,\speed),
				\density -> ControlSpec(0.05, 20,\lin,0.001,1,\density),
				\tone -> ControlSpec(0.05, 20,\lin,0.001,1,\tone),
				\blur ->ControlSpec(0.05, 20,\lin,0.001,1,\blur),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			]
		))

	}

}

