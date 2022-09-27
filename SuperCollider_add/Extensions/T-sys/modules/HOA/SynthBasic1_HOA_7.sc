SynthBasic1_HOA_7 : Tmodule2 {


	*def
	{
		^SynthDef(\SynthBasic1_HOA_7, {| x = 0, y = 0, z = 0,  globTBus, mul = 0, freq = 36, fc = 1000, rq = 0.25, d = 10, width = 2, orientation = 0.5, amp=0, sawwidth=0, matrix_ramp = 0.01, gate = 1, free = 1, lag = 0.2, compensation = 1, lo_freq = 150, lo_freq_gain = -60, min_sphere = 0.8|
			var sig, envgate, envpause, position, hoa_encoder, distance, dis2;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			position = Cartesian(y, x.neg, z);
			position = position.asSpherical;
			mul = Lag.kr(mul, 0.2);
			distance = position.rho;

			sig = Mix.fill(4, {
				VarSaw.ar((freq + {d.rand2}), 0, sawwidth, 0.02)
			});
			sig = RLPF.ar(sig, fc, rq).softclip;
			sig = RLPF.ar(sig, fc, rq, mul).softclip;

			distance = Select.kr(distance < 0.001, [distance, 0.001]);
			dis2 = distance;
			dis2 = dis2.linlin(0.0, min_sphere, lo_freq_gain, 0.0);
			// sig = Select.ar(compensation, [sig, BLowShelf.ar(sig, lo_freq, 1.0, dis2.lag(0.1))]);
			// dis2.poll;
			sig = sig * envgate * envpause * amp.dbamp.lag;
			// position.theta.poll;
			// hoa_encoder = HOAEncoder.ar(5, sig, position.theta, position.phi, 0, 1,distance); // amp in dB
			hoa_encoder = AmbisonicEncoder.ar(7, sig, 0, distance, position.theta*(180/pi), position.phi*(180/pi));

			Out.ar(globTBus, hoa_encoder);

		}).load;

	}
	*metadata
	{
		^(metadata: (
			synthdefname: "SynthBasic1_HOA_7",
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

