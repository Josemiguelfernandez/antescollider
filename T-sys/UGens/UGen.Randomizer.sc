
//---------randomizer---------------

TRand0 {
	/*@ 
		random values with automatic triggering
	@*/
	*kr{|lo = 0, hi = 1, rate = 1, mul = 1, add = 0|
		var out;
		out = TRand.kr(lo, hi, Dust.kr(rate)).madd(mul, add);
		^out;
	}
}


TRand1 {
	/*@ 
		random values with automatic triggering and smoothing
	@*/
	*kr{|lo = 0, hi = 1, rate = 1, lag = 1, mul = 1, add = 0|
		var out;
		out = Lag.kr(TRand0.kr(lo, hi, rate, mul, add), lag);
		^out;
	}
}

TBrownRand0 {
	/*@ 
		random values with automatic triggering and smoothing
	@*/
	*kr{|lo = 0, hi = 1, rate = 1, reg = 1, mul = 1, add = 0|
		var out, tr;
		tr  = Geiger0.kr(rate, reg);
		out = TBrownRand.kr(lo, hi, trig:tr, mul:mul, add:add);
		^out;
	}
}


TBrownRand1 {
	/*@ 
		random values with automatic triggering and smoothing
	@*/
	*kr{|lo = 0, hi = 1, rate = 1, reg = 0.25, lag = 1, mul = 1, add = 0|
		var out;
		out = TBrownRand0.kr(lo, hi, rate, reg, mul, add).lag(lag);
		^out;
	}
}

Geiger0 {
	/*@
	random value generator [0..1],  generates values at random intervals or regularly
	@*/
	
	*kr {|f = 1, rnd = 1, mul = 1, add = 0|
		var tr;
		tr = Impulse.kr(f);
		tr = CoinGate.kr(rnd.max(0.01), tr);
		tr = TRand.kr(0, 1, tr).madd(mul, add);
		^tr;
	}
}

Geiger1 {
	/*@
	lagged, random value generator [0..1],  generates values at random intervals or regularly
	@*/
	
	*kr {|f = 1, rnd = 1, lag = 1,mul = 1, add = 0|
		^Geiger0.kr(f, rnd, mul, add).lag(lag);
	}
}