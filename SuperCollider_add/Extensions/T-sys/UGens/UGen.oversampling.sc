UpSample {
	*ar{|sig|
		var upsampled, coeff, t0, t_1, t1, t2;
		
						//now upsample;
				coeff = [ 
					[ -0.0703125, 0.8671875, 0.2265625, -0.0234375 ], 
					[ -0.0625, 0.5625, 0.5625, -0.0625 ],
					[ -0.0234375, 0.2265625, 0.8671875, -0.0703125 ]
				];
				t2 = sig;
				t1 = Delay1.ar(t2);
				t0 = Delay1.ar(t1);
				t_1 = Delay1.ar(t0);
				upsampled = Array.fill(coeff.size, {|i| (coeff[i]*[t_1,t0,t1,t2]).sum});
				upsampled = upsampled ++ t1;
		
		^upsampled;
	}
}

DownSample {
	*ar{|sig|
		var upsampled, coeff, t0, t_1, t1, t2, dl3;
		coeff = [ 
					[ -0.0703125, 0.8671875, 0.2265625, -0.0234375 ], 
					[ -0.0625, 0.5625, 0.5625, -0.0625 ],
					[ -0.0234375, 0.2265625, 0.8671875, -0.0703125 ]
				];
		upsampled = sig;
		dl3 = 3/SampleRate.ir(); 
		t_1	= Delay1.ar(upsampled[0]); //this is a new t_1!!
		t0 	= (coeff[0] * [upsampled[1], Delay1.ar(upsampled[1]), Delay2.ar(upsampled[1]), DelayN.ar(upsampled[1], 0.01, dl3)]).sum;
		t1 	= (coeff[1] * [upsampled[2], Delay1.ar(upsampled[2]), Delay2.ar(upsampled[2]), DelayN.ar(upsampled[2], 0.01, dl3)]).sum;
		t2	= (coeff[2] * [upsampled[3], Delay1.ar(upsampled[3]), Delay2.ar(upsampled[3]), DelayN.ar(upsampled[3], 0.01, dl3)]).sum;
		sig = (t_1+t0+t1+t2) * 0.1;
		^sig;
	}
}