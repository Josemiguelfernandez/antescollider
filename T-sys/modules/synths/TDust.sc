

TDust : Tmodule2  {

	*def
	{


		// var updateFreq = 10, numRMSSamps, server;
		// server = Server.default;
		// numRMSSamps = server.sampleRate/updateFreq;

	^SynthDef(\TDust, {|bus, out = 0, amp = 0, freq = 3, matrix_ramp = 0.05, gate = 1|
			var sig, imp;
			imp = Impulse.kr(10);
			sig = Dust.ar(freq, amp.dbamp);
			//SendReply.kr(imp, '/levelis', [RunningSum.ar(sig.squared, numRMSSamps), Peak.ar(sig, Delay1.ar(imp)).lag(0, 3)].flop.flat, 0);
			sig = sig*EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate);
	//mix.collect { |bus, i|
			Out.ar(out, sig)
			}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TDust",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(0, 200, \lin, 0.1, 3, \Hz),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			]
		))



	}

}








