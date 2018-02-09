GrainDelay {
	*ar {
		| in
		, maxdelaytime = 0.2
		, delaytime = 0.2
		, feedback = 0
		, feedbackFunc = nil
		, trigger = 0
		, dur = 1
		, rate = 1
		, jitter = 0
		, interp = 2
		, pan = 0
		, envbufnum = -1 |
		  
		var sr = SampleRate.ir;
		
		var pos		
		  = if (delaytime.isFloat) {
			var delayTimeRate = maxdelaytime.reciprocal;
			var posInc = (maxdelaytime * (1 + (jitter * delayTimeRate)) * sr).reciprocal;
			var resetPos = 1 - (delaytime*delayTimeRate);
			Phasor.ar(Impulse.ar(delayTimeRate), posInc, 0, 1, resetPos);
		} {
			delaytime.value(maxdelaytime);
		};
		
		var inputs = in.asArray;
		var numChannels = inputs.size;
		var buffers = inputs.collect { LocalBuf((maxdelaytime * sr).ceil, 1).clear };

		var z = GrainBuf.ar(numChannels, trigger, dur, buffers, rate, pos, interp, pan, envbufnum).sum.asArray;

		var fbFunc = feedbackFunc ? { |x| x };
		var fbSig = LeakDC.ar(fbFunc.value(z)).asArray;
		
		inputs.do { |x,i|
			RecordBuf.ar(x + (fbSig[i]*feedback), buffers[i]);
		};

		^z
	}
}
