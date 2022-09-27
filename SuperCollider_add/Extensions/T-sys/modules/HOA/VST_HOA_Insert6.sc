VST_HOA_Insert6 : Tmodule2  {

	*def
	{
		^SynthDef(\VST_HOA_Insert6, {|in, out, globTBus, gatee = 1, amp = 0, lag = 0.2, matrix_ramp = 0.05, gate = 1, free = 1,  bypass = 0, xFade = 1|
			var sig, envgate, envpause, cross;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 49);

			sig = VSTPlugin.ar(sig, 49, bypass); // load IEM VST plugins

			sig = sig * amp.dbamp.lag(1);
			XOut.ar(globTBus, cross, sig);
		}).load;

	}

	*metadata
	{
		/*		var buffnum;
		buffnum = ~wave_buff[\vbap].bufnum; // VBAP Speaker conf.*/
		^(metadata: (
			synthdefname: "VST_HOA_Insert6",
			type: \fx,
			main: \azi,
			sliders:[
				\freeroom -> ControlSpec(0.0, 1.0,\lin, 0.001,0.5,\freeroom),
				\freedamp -> ControlSpec(0, 1,\lin, 0.001,0.5,\freedamp),
				\freemul -> ControlSpec(0, 1,\lin, 0.001, 1.0,\freemul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			]
		))

	}
}

