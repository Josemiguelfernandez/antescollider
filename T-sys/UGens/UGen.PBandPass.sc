PBandPass {
	*ar {|sig, eq = 0.5, eqB = 1, res = 0|
		var lo, hi;
		res = 1 - res;
		eq  = eq  * 110 + 25;
		lo  = (eq - (60 * (eqB-0.1))).min(135).max(20);
		hi  = (eq + (80 * (eqB.squared))).min(135).max(20);
		sig = BLowPass.ar(sig, hi.midicps, res);
		sig = BHiPass4.ar(sig, lo.midicps, res);
		^sig;
	}
}
