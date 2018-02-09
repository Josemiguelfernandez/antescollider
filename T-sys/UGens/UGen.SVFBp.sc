SVFBp {
	*ar{
		arg in, eq = 0.5, res = 0.1;
		var sig, lp;
		lp = res.min(0).abs;
		sig = SVF.ar(in, (eq * 100 + 30).midicps, res, lowpass:lp, bandpass:1, highpass:lp);
		^sig;
	}
}
