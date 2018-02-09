Drone_2 : Tmodule2 {

	*def
	{

	^SynthDef(\drone_2, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freq = 233, modfreq = 0.1, pos = 0, damp = 0.5, room = 0.5, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, sig2, envgate, envpause, cross;


			///////////module
			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;
			///////////module

			sig = Array.fill(3,{|i| VarSaw.ar(freq.lag*(i+1.0001),mul:0.05/(i+1))}).sum;
			sig2 = Ringz.ar(WhiteNoise.ar(0.0003),TRand.ar(freq.lag,(freq.lag.cpsmidi+1).midicps,Impulse.ar(10)));


			sig = Pan2.ar(sig+sig2*(0.8+SinOsc.kr(modfreq,0,0.2)), pos.lag);
			sig = Limiter.ar(FreeVerb.ar(LPF.ar(sig,10000),mix:0.33, room:room, damp:damp),0.7);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "drone_2",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 18000, \exp, 0.1, 300, \freq),
				\modfreq -> ControlSpec(0.0, 20, \lin, 0.001, 0.1,\modfreq),
				\damp -> ControlSpec(0.0, 1.0, \lin, 0.001, 0.5,\damp),
				\room -> ControlSpec(0.0, 1.0, \lin, 0.001, 0.5,\room),
				\pos -> ControlSpec(-1.0, 1.0, \lin, 0.01, 0.0,\pos),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}

/*SynthDef(\voice,{arg out=0,n=0,p=0,d=10,r=10;
var sig=Array.fill(3,{|i| VarSaw.ar(n.midicps*(i+1.0001),mul:0.05/(i+1))}).sum;
var sig2=Ringz.ar(WhiteNoise.ar(0.0003),TRand.ar(n.midicps,(n+1).midicps,Impulse.ar(10)));
	// var env=EnvGen.kr(Env.linen(d,1,r),gate:1,doneAction:2);
Out.ar(out,Pan2.ar((sig+sig2)*env*(0.8+SinOsc.kr(0.1,0,0.2)),p));
}).add;

Ndef(\rev,{
Out.ar(0,Limiter.ar(FreeVerb.ar(LPF.ar(In.ar([0,1]),10000),mix:0.33),0.7));
};
);*/
