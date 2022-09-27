TDoppler : Tmodule2 {

	*def
	{


	^SynthDef(\TDoppler, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, speed = 1, dist = 10, amp = 0, panoffset = 0, dir = 1, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, x, y, distance,pitchRatio, amplitude, sound, azimuth, panValue;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			y = LFSaw.kr(speed, 0, 100);
			distance = hypot(dist, y);
			amplitude = 40 / distance.squared;
			azimuth = atan2(y, dist); // azimuth in radians
			panValue = ((azimuth / 0.5pi) * dir).linlin(-1, 1, 0, 2);

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = PanAz.ar(8, DelayL.ar(sig, 110/344, distance/344), panValue + panoffset, amplitude);
			sig = sig * envgate * envpause;

			// XOut.ar(out, cross, sig * amp.dbamp.lag;); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "TDoppler",
			type: \fx,
			main: \speed,
			sliders: [
				\speed -> ControlSpec(0.01, 4,\lin,0.001,1,\speed),
				\dist -> ControlSpec(0.01, 50,\lin,0.001,10,\dist),
				\panoffset -> ControlSpec(0, 2,\lin,0.001, 0,\panoffset),
				\dirt -> ControlSpec(-1, 1,\lin,1, 1,\dir),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}


