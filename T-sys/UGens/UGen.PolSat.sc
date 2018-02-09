PolSat {
	/*@ polynom waveshaping @*/
	*ar {|in, mul = 1, add = 0|	
		var out, x3 = in * in * in;
		in = in.clip2(1.875); //1.875

		out = ((x3 *(-0.18963)) + in) + (x3*(in.squared) * 0.0161817);
		out = out.softclip; //(1.875); //1.875
		^out.madd(mul, add);
	}
	*kr {|in, mul = 1, add = 0|	
		var out, x3 = in * in * in;
		out = ((x3 *(-0.18963)) + in) + (x3*(in.squared) * 0.0161817);
		^out.madd(mul, add);
	}
}




PolSat4{
	*ar {|sig,drv = 1, mul = 1, add = 0|
		sig = sig * drv;
		sig = UpSample.ar(sig);
		sig = PolSat.ar(sig);
		sig = DownSample.ar(sig);
		sig = sig * drv.reciprocal.sqrt;
		^sig.madd(mul, add);
	}
}

PolSatL{
	/*@ band limited polynom waveshaping @*/
	*ar {|sig, lmt = 3200, mul = 1, add = 0|
		var hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		sig = PolSat.ar(sig);
		sig = sig + DelayN.ar(hgh, 4/SampleRate.ir, 4/SampleRate.ir);
		^sig.madd(mul, add);
	}
}

PolSatL4{
	/*@ band limited polynom waveshaping @*/
	*ar {|sig, drv = 1, lmt = 3200, mul = 1, add = 0|
		var hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		sig = PolSat4.ar(sig, drv);
		sig = sig + DelayN.ar(hgh, 5/SampleRate.ir, 5/SampleRate.ir);
		^sig.madd(mul, add);
	}
}


PolSatLC{
	/*@ band limited polynom waveshaping @*/
	*ar {|sig, lmt = 3200, mul = 1, add = 0|
		var hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		sig = PolSat.ar(PolSat.ar(PolSat.ar(sig)));
		sig = sig + DelayN.ar(hgh,  4/SampleRate.ir, 4/SampleRate.ir);
		^sig.madd(mul, add);
	}
}

+ UGen {
	polsat {
		var val = this, x3 = val * val * val;
		val = ((x3 *(-0.18963)) + val) + (x3*(val * val) * 0.0161817);
		^val;
	}
}  

+ SimpleNumber {
	polsat {
		var val = this, x3 = val * val * val;
		val = ((x3 *(-0.18963)) + val) + (x3*(val * val) * 0.0161817);
		^val;
	}
}   

