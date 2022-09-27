VST_1 : Tmodule2  {

	*def
	{
		^SynthDef(\VST_1, {|in, out, gatee = 1, amp = 0, matrix_ramp = 0.05,  in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1, bypass = 0, xFade = 1, p1, idx, p2|
			var sig, envgate, envpause, cross, convsig, b2a, a2b;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1);

			// sig = VSTPlugin.ar(In.ar(in, 1), 1, params: [1, p1, idx, p2]);
			sig = VSTPlugin.ar(sig, 1, bypass);

			sig = sig * envgate * envpause;

			XOut.ar(out, cross, sig);
		}).load;

	}

	*metadata
	{
		/*		var buffnum;
		buffnum = ~wave_buff[\vbap].bufnum; // VBAP Speaker conf.*/
		^(metadata: (
			synthdefname: "VST_1",
			type: \fx,
			main: \freeroom,
			sliders:[
				\freeroom -> ControlSpec(0.0, 1.0,\lin, 0.001,0.5,\freeroom),
				\freedamp -> ControlSpec(0, 1,\lin, 0.001,0.5,\freedamp),
				\freemul -> ControlSpec(0, 1,\lin, 0.001, 1.0,\freemul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			]
		))

	}
}

