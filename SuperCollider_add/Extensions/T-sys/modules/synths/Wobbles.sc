Wobbles : Tmodule2 {


	*def
	{
		^SynthDef(\Wobbles, {|out, outbus, outgain = 0, pan = -1, spread = 0, freq=200, amp=0, width=0, matrix_ramp = 0.001, gate = 1, free = 1|
			var sig, sig1, sig2, sig3, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);


			sig2 = LFSaw.ar(freq * 0.99 );
			sig3 = LFSaw.ar(freq * 1 );
			sig = sig2 + sig3;
			sig = (sig*50).tanh;
			// sig = sig * EnvGen.ar(\adsr.kr(Env.adsr(0.01,0.1,0.8,0.1)),gate,doneAction:doneAction);
			sig = Splay.ar(sig, spread, amp, pan);
			sig = sig * envgate * envpause;
			Out.ar(out, sig * amp.dbamp); //salida directa a un bus
			//Out.ar(outbus, sig * outgain); //salida a un bus auxiliar

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Wobbles",
			type: \gen,
			main: \note,
			sliders:  [
				\freq -> ControlSpec(20, 18000, \exp, 0.11, 41.2, \freq),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			]
		))

	}

}

/*(

SynthDef(\wobble, { arg out=0, amp=0.1, gate=1, pan=0, spread=0.8, freq=200, doneAction=2;
	var sig, sig1, sig2, sig3;
	sig1 = LFSaw.ar(freq * 1 + (0.04 * [1,-1]));
	sig2 = LFSaw.ar(freq * 0.99 );
	sig3 = LFSaw.ar(freq * 1 );
	sig = sig1 + sig2 + sig3;
	sig = (sig*50).tanh;
	sig = sig * EnvGen.ar(\adsr.kr(Env.adsr(0.01,0.1,0.8,0.1)),gate,doneAction:doneAction);
	sig = Splay.ar(sig, spread, amp, pan);
	Out.ar(out, sig);
}).add;

)*/

