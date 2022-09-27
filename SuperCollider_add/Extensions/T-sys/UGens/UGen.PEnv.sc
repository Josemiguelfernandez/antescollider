PEnv {
	*ar{|sig, lag = 0.1, gate = 1|
		sig = sig * EnvGen.kr(Env.asr(lag, 1, lag), gate, doneAction:2);
		^sig;
	}
	
	*kr{|lag = 0.1, gate|
		^EnvGen.kr(Env.asr(lag, 1, lag), gate, doneAction:2);
	}
}