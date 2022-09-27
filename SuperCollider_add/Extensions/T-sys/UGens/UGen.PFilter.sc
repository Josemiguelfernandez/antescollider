P2Shelf {
	/*@ one knob hi/loshelf combination with boost at 35 Hz @*/
	*ar {|sig, eq = 0.5, lfM = 4, hfM = 8|
		var miA, hiA, loA;
		miA = (1-eq) * 12 - 6;
		hiA = SelectX.kr(eq * 2, [-36,0,3]);
		loA = SelectX.kr(eq * 2, [12,0,-6]);
			
		sig = BPeakEQ.ar(sig, 35, 2, miA);
		sig = BLowShelf.ar(sig, 500 * (eq.squared * lfM+1), 1, loA);
		sig = BHiShelf.ar(sig, 200 * (eq.squared*hfM+1), 1, hiA);
		^sig;
	}
}