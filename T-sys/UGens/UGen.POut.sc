POut {
	*ar {
		arg sig, amp = 1, out = 0,tbus = 100, gate = 1, lag = 1;
		sig = sig * EnvGen.kr(Env.asr(lag, 1, lag), gate, doneAction:2);
		Out.ar(tbus, sig);
		Out.ar(out, sig * amp.lag(1));
		^sig;
	}
}

POutA {
	*ar {
		arg sig, amp = 1, out = 0,tbus = 100,aux1 = 66, wet = 0, 
		gate = 1, lag = 1;
		sig = sig * EnvGen.kr(Env.asr(lag, 1, lag), gate, doneAction:2);
		Out.ar(tbus, sig);
		amp = amp.lag(1);
		Out.ar(aux1, sig * wet.lag(1) * amp);
		Out.ar(out, sig * (1-wet) * amp);
		^sig;
	}
}

