HyperSat {
	/*@ Hyperbolic Saturation @*/
	*ar {|in, th = 1, mul = 1, add = 0|
		var out;
		th  = th.max(0.000000000001);
		out = in / ((in * th.reciprocal).abs +1).madd(mul, add);
		^out;
	}	
	*kr {|in, th = 1, mul = 1, add = 0|
		var out;
		th  = th.max(0.000000000001);
		out = in / ((in * th.reciprocal).abs +1).madd(mul, add);
		^out;
	}	
	
}

HyperSat4{
	*ar {|sig, th = 1,mul = 1, add = 0|
		sig = UpSample.ar(sig);
		sig = HyperSat.ar(sig, th);
		sig = DownSample.ar(sig);
		^sig.madd(mul, add);
	}	
}

HyperSatL{
	/*@ band limited Hyperbolic waveshaping @*/
	*ar {|sig, th = 1, lmt = 3200, mul = 1, add = 0|
		var hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		sig = HyperSat.ar(sig);
		sig = sig + DelayL.ar(hgh, 4/SampleRate.ir, 4/SampleRate.ir);
		^sig;
	}	
}

HyperSatL4{
	/*@ band limited Hyperbolic waveshaping @*/
	*ar {|sig, th = 1, lmt = 3200, mul = 1, add = 0|
		var hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		sig = HyperSat4.ar(sig);
		sig = sig + DelayL.ar(hgh, 5/SampleRate.ir, 5/SampleRate.ir);
		^sig;
	}	
}



HyperSatLC{
	/*@ 3x band limited Hyperbolic waveshaping @*/
	*ar {|sig, th = 1, lmt = 3200, mul = 1, add = 0|
		var hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		sig = HyperSat.ar(HyperSat.ar(HyperSat.ar(sig)));
		sig = sig + DelayL.ar(hgh,  4/SampleRate.ir, 4/SampleRate.ir);
		^sig;
	}	
}

