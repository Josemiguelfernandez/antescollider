TestSynth  {


	*def
	{
	^SynthDef(\TestSynth, {|out = 0, outbus, outgain = 0, note = 36, fc = 1000, rq = 0.25, bal = 0, amp=0.4, width=0, gate = 1|
		var x;
		x = Mix.fill(4, {
				VarSaw.ar((note + {0.1.rand2}.dup).midicps, 0, width, 0.02)
			});
			x = RLPF.ar(x, fc, rq).softclip;
			x = RLPF.ar(x, fc, rq, amp).softclip;
			//x = Balance2.ar(x[0], x[1], bal);
			//x = Pan2.ar(x, -1);
			x = x * EnvGen.kr(Env.cutoff, gate, 5, doneAction: 2);
			Out.ar(out, x[0]); //salida directa a un bus
			Out.ar(outbus, x * outgain); //salida a un bus auxiliar

			}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TestSynth",
			type: \gen,
			sliders:  (
				\note: ControlSpec(24, 60, \lin, 1, 36, \note),
				\fc: ControlSpec(200, 5000, \exp,0.01,1000,\Hz),
				\rq: ControlSpec(0.1, 0.7,\lin,0.001,0.2,\rq),
				\bal: ControlSpec(-1, 1, \lin, 0, 0, \pan),
				\amp: ControlSpec(0.0001, 2, \exp, 0, 0.3, \vol), // db spec acts weird, so use self made one
				\width: ControlSpec(0, 1, \lin, 0, 0.3, \width)
			)
		))

	}

}





/*
{|in, amount= 0.5, fc= 3500, center= 120, rq= 0.707| var k= 2*amount/(1-amount); var wet= ((1+k)*in)/(1+(k*in.abs)); wet= MidEQ.ar((LPF.ar(wet, fc*#[1, 1.1])*0.5), center, rq, 8)}

*/
//Synth("testOcil2"++uniqueID);
//Synth(\windowtest)

