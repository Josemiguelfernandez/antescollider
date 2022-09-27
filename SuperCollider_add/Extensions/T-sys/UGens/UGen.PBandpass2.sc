SVFBandpass {
	*ar {arg sig, eq = 0.5, res = 0;
		var hiC, loC;
		res = res * 0.75;
		hiC = (eq*(1-(res*0.5))*2).min(1.05).max(0) * 83 + 44;
		loC = (eq - (0.5 * (1-res)) * 2).min(1).max(0) * 87 + 30;
		sig = SVF.ar(sig, hiC.midicps,0, lowpass:1);
		sig = SVF.ar(sig, loC.midicps,0.5 * res, lowpass:0, highpass:1);
		^sig;
	}
}

PBandpass2{
	*ar {arg sig, eq = 0.5, res = 0;
		var hiC, loC;
		res = res * 0.75;
		hiC = (eq*(1-(res*0.5))*2).min(1.05).max(0) * 83 + 44;
		loC = (eq - (0.5 * (1-res)) * 2).min(1).max(0) * 87 + 30;
		sig = BLowPass.ar(sig, hiC.midicps);
		sig = BHiPass.ar(sig, loC.midicps,1 - (res*0.5));
		^sig;
	}
}
