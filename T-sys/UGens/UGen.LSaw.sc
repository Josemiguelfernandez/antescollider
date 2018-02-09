// Victor Lazzarini Analog Sawtooth
LSaw {
	*ar {|freq = 100, m = 0, dst = 0.001, amp = 0.25|
		var square, cos, out;
		dst = dst.min(1).max(0.0001) * 5000;
		m   = m.min(1).max(0);
		square = ((SinOsc.ar(freq)) * dst).tanh;
  	 	cos = ((SinOsc.ar(freq, pi/2)) * m) + 1;
    	^out = square * cos * amp;
	}
}