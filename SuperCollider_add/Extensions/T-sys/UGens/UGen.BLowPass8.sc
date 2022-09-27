BLowPass8 {
	*ar{|sig, freq = 1000, rq = 1, mul = 1, add = 0|
		freq = freq.min(15000);
		sig = BLowPass4.ar(sig, freq, rq);
		sig = BLowPass4.ar(sig, freq * 1.3333, mul, add);
		^sig;
	}
}