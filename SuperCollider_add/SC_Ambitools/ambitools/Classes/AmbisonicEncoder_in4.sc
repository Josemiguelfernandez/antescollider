AmbisonicEncoder_in4 {
	*ar { |order, in, gain, coords|
		case   	{order == 1}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3;
							#in1, in2, in3, in4 = in;
							#gain_0, gain_1, gain_2, gain_3 = gain;
							#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3 = coords;
							^AmbisonicEncoderOrder1In4.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3)}

		{order == 2}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3;
							#in1, in2, in3, in4 = in;
							#gain_0, gain_1, gain_2, gain_3 = gain;
							#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3 = coords;
							^AmbisonicEncoderOrder2In4.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3)}

		{order == 3}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3;
							#in1, in2, in3, in4 = in;
							#gain_0, gain_1, gain_2, gain_3 = gain;
							#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3 = coords;
							^AmbisonicEncoderOrder3In4.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3)}

		{order == 4}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3;
							#in1, in2, in3, in4 = in;
							#gain_0, gain_1, gain_2, gain_3 = gain;
							#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3 = coords;
							^AmbisonicEncoderOrder4In4.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3)}

		{order == 5}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3;
							#in1, in2, in3, in4 = in;
							#gain_0, gain_1, gain_2, gain_3 = gain;
							#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3 = coords;
							^AmbisonicEncoderOrder5In4.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3)}

		{order == 6}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3;
							#in1, in2, in3, in4 = in;
							#gain_0, gain_1, gain_2, gain_3 = gain;
							#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3 = coords;
							^AmbisonicEncoderOrder6In4.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3)}

		{order == 7}
		               	{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3;
							#in1, in2, in3, in4 = in;
							#gain_0, gain_1, gain_2, gain_3 = gain;
							#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3 = coords;
							^AmbisonicEncoderOrder7In4.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3)}
		               				               				               		

	}
}

