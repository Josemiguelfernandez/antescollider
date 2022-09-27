SynthBasic1_HOA_4 : Tmodule2 {


	*def
	{
	^SynthDef(\SynthBasic1_HOA_4, {| x = 0, y = 0, z = 0,  globTBus, mul = 0, freq = 36, fc = 1000, rq = 0.25, d = 10, width = 2, orientation = 0.5, amp=0, sawwidth=0, matrix_ramp = 0.01, gate = 1, free = 1, lag = 0.2|
		var sig, envgate, envpause, position, hoa_encoder;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;
			mul = Lag.kr(mul, 0.2);

			sig = Mix.fill(4, {
				VarSaw.ar((freq + {d.rand2}), 0, sawwidth, 0.02)
			});
			sig = RLPF.ar(sig, fc, rq).softclip;
			sig = RLPF.ar(sig, fc, rq, mul).softclip;

			sig = sig * envgate * envpause * amp.dbamp.lag;

			hoa_encoder = HOAEncoder.ar(4, sig, position.theta, position.phi, 0, 1,position.rho); // amp in dB
			Out.ar(globTBus, hoa_encoder);

			}).load;

	}
	*metadata
	{
		^(metadata: (
			synthdefname: "SynthBasic1_HOA_4",
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

