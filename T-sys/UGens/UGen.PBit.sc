PBit {
	/* bit reduction, truncated version */
	*ar{|sig, bit = 16|
		sig = sig.trunc(2.pow(bit.min(24).max(1)-1).reciprocal);
		^sig;
	}
}

PBitR {
	/* bit reduction, rounded version */
	*ar{|sig, bit = 16|
		sig = sig.round(2.pow(bit.min(24).max(1)-1).reciprocal);
		^sig;
	}
}