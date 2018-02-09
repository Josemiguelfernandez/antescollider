//----------audiorate smooother ----------------

K2A1 {
	/*@ converts controlrate to audio rate and smoothes the input signal (with audiorate)

	@*/
	*ar {|in, lag = 1|
		var out;
		out = Lag.ar(K2A.ar(in), lag);
		^out;
	}
}

K2A2 {
	/*@ converts controlrate to audio rate and smoothes the input signal (with audiorate)

	@*/
	*ar {|in, lag = 1|
		var out;
		out = Lag2.ar(K2A.ar(in), lag);
		^out;
	}
}

K2AUd {
	/*@ converts controlrate to audio rate and smoothes the input signal (with audiorate)

	@*/
	*ar {|in, up = 1, down = 1|
		var out;
		out = LagUD.ar(K2A.ar(in), up, down);
		^out;
	}
}

K2ARamp {
	/*@ converts controlrate to audio rate and smoothes the input signal (with audiorate)

	@*/
	*ar {|in, lag = 1|
		var out;
		out = Ramp.ar(K2A.ar(in),lag.min(1).max(0));
		^out;
	}
}

