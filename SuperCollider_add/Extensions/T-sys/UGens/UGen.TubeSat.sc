TubeSat {
	*ar {|sig|
		sig = (0.5 * (sig + 1.41).pow(2)-1);
		^sig;
	}
	*kr {|sig|
		sig = (0.5 * (sig + 1.41).pow(2)-1);
		^sig;
	}
}

TubeSat4{
	*ar {|sig|
		sig = UpSample.ar(sig);
		sig = TubeSat.ar(sig.clip2(1.875));
		sig = DownSample.ar(sig);
		^sig;
	}
}

TubeSatL {
	/*@ band limited tube waveshaping @*/
	*ar {|sig, drv = 1, lmt = 3200|
		var hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		sig = sig * drv;
		sig = TubeSat.ar(sig);
		sig = sig * drv.reciprocal.sqrt;
		sig = sig + DelayN.ar(hgh, 4/SampleRate.ir, 4/SampleRate.ir);
		^sig;
	}
}

TubeSatL4 {
	/*@ band limited tube waveshaping @*/
	*ar {|sig, drv = 1, lmt = 3200|
		var hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		sig = sig * drv;
		
		sig = UpSample.ar(sig);
		sig = TubeSat.ar(sig.clip2(1.875));
		sig = DownSample.ar(sig);
		
		sig = sig * drv.reciprocal.sqrt;
		sig = sig + DelayN.ar(hgh, 4/SampleRate.ir, 4/SampleRate.ir);
		^sig;
	}
}

TubeSatExp {
	*ar {|sig|
		var sigExp;
		sigExp = sig.exp;
		sig = ((sigExp - (sig * (-1.2)).exp)/(sigExp + (sig * (-1)).exp));
		^sig;
	}
	*kr {|sig|
		var sigExp;
		sigExp = sig.exp;
		sig = ((sigExp - (sig * (-1.2)).exp)/(sigExp + (sig * (-1)).exp));
		^sig;
	}
} 

TubeSatExp4{
	*ar {|sig|
		sig = UpSample.ar(sig);
		sig = TubeSatExp.ar(sig.clip2(1.875));
		sig = DownSample.ar(sig);
		^sig;
	}
}

TubeSatExpL {
	*ar {|sig, drv = 1,lmt = 3200|
		var sigExp, hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		sig = sig * drv;
		sig = TubeSatExp.ar(sig);
		sig = sig * drv.reciprocal.sqrt;
		sig = sig + DelayN.ar(hgh,  4/SampleRate.ir, 4/SampleRate.ir);
		^sig;
	}
} 

TubeSatExpL4 {
	/*@ band limited tube waveshaping @*/
	*ar {|sig, drv = 0, lmt = 3200|
		var hgh;
		hgh = HPF.ar(sig, lmt);
		sig = LPF.ar(sig, lmt);
		drv = drv.dbamp;
		sig = sig * drv;
		
		sig = UpSample.ar(sig);
		sig = TubeSatExp.ar(sig.clip2(1.875));
		sig = DownSample.ar(sig);
		
		sig = sig * drv.reciprocal.sqrt;
		sig = sig + DelayN.ar(hgh, 4/SampleRate.ir, 4/SampleRate.ir);
		^sig;
	}
}