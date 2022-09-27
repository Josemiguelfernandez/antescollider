SynthBasic : Tmodule2 {


	*def
	{
	^SynthDef(\SynthBasic, {|out, outbus, outgain = 0, mul = 0, freq = 36, fc = 1000, rq = 0.25, d = 10, pos = 0, width = 2, orientation = 0.5, amp=0, sawwidth=0, matrix_ramp = 0.01, gate = 1, free = 1, lag = 0.2|
		var sig, envgate, envpause;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);


			sig = Mix.fill(4, {
				VarSaw.ar((freq + {d.rand2}), 0, sawwidth, 0.02)
			});
			sig = RLPF.ar(sig, fc, rq).softclip;
			sig = RLPF.ar(sig, fc, rq, mul).softclip;
			//x = Balance2.ar(x[0], x[1], bal);
			//x = Pan2.ar(x, -1);
			// sig = PanAz.ar(8, sig, pos.lag(lag), 1, width, orientation);
			sig = sig * envgate * envpause;
			Out.ar(out, sig * amp.dbamp); //salida directa a un bus
			//Out.ar(outbus, sig * outgain); //salida a un bus auxiliar

			}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "SynthBasic",
			type: \gen,
			main: \freq,
			sliders:  [
				\freq -> ControlSpec(20, 20000, \exp, 0, 300, \Hz),
				\fc -> ControlSpec(200, 5000, \exp,0.01,1000,\Hz),
				\rq -> ControlSpec(0.1, 0.7,\lin,0.001,0.25,\rq),
				\sawwidth -> ControlSpec(0, 1, \lin, 0, 0, \sawidth),
				\pos -> ControlSpec(0, 2, \lin, 0.001, 0, \pos),
				\width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB) // db spec acts weird, so use self made one
			]
		))

	}

}





/*
{|in, amount= 0.5, fc= 3500, center= 120, rq= 0.707| var k= 2*amount/(1-amount); var wet= ((1+k)*in)/(1+(k*in.abs)); wet= MidEQ.ar((LPF.ar(wet, fc*#[1, 1.1])*0.5), center, rq, 8)}

*/
//Synth("testOcil2"++uniqueID);
//Synth(\windowtest)

