
SigmoidSat {
	*ar {|sig, crv = 0|
		crv = crv.min(1).max(0) * (-3) - 2;
		sig = (2 / (1 + (crv * sig).exp)) -1;
		^sig;
	}
} 

SigmoidSat4 {
	*ar {|sig, drv = 1, crv = 0|
		sig = sig * drv;
		sig = UpSample.ar(sig);
		sig = SigmoidSat.ar(sig, crv);
		sig = DownSample.ar(sig);
		sig = sig * drv.reciprocal.sqrt;
		^sig;
	}
}

SigmoidSatL {
	*ar {|sig, drv = 1,crv = 0, lmt = 3200|
		var sigExp, hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		sig = sig * drv;
		sig = SigmoidSat.ar(sig, crv);
		sig = sig * drv.reciprocal.sqrt;
		sig = sig + DelayN.ar(hgh,  4/SampleRate.ir, 4/SampleRate.ir);
		^sig;
	}
}

SigmoidSatL4 {
	*ar{|sig, drv = 1, crv = 0, lmt = 3200|
		sig = UpSample.ar(sig);
		sig = SigmoidSatL.ar(sig, drv, crv, lmt);
		sig = DownSample.ar(sig);
	}
}
