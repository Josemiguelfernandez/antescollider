AmbisonicEncoder_in3 {
	*ar { |order, in, gain, coords|
		case   	{order == 1}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2;
						#in1, in2, in3 = in;
						#gain_0, gain_1, gain_2 = gain;
						#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2 = coords;
						^AmbisonicEncoderOrder1In3.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2)}

		{order == 2}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2;
						#in1, in2, in3 = in;
						#gain_0, gain_1, gain_2 = gain;
						#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2 = coords;
						^AmbisonicEncoderOrder2In3.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2)}

		{order == 3}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2;
						#in1, in2, in3 = in;
						#gain_0, gain_1, gain_2 = gain;
						#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2 = coords;
						^AmbisonicEncoderOrder3In3.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2)}

		{order == 4}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2;
						#in1, in2, in3 = in;
						#gain_0, gain_1, gain_2 = gain;
						#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2 = coords;
						^AmbisonicEncoderOrder4In3.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2)}

		{order == 5}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2;
						#in1, in2, in3 = in;
						#gain_0, gain_1, gain_2 = gain;
						#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2 = coords;
						^AmbisonicEncoderOrder5In3.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2)}

		{order == 6}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2;
						#in1, in2, in3 = in;
						#gain_0, gain_1, gain_2 = gain;
						#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2 = coords;
						^AmbisonicEncoderOrder6In3.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2)}

		{order == 7}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2;
						#in1, in2, in3 = in;
						#gain_0, gain_1, gain_2 = gain;
						#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2 = coords;
						^AmbisonicEncoderOrder7In3.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2)}
		               				               				               		

	}
}

