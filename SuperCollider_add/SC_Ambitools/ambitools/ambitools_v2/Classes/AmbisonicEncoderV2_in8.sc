AmbisonicEncoderV2_in8 {
	*ar { |order, in, gain, coords, doppler|
		case   	{order == 1}
		            {var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7;
					#in1, in2, in3, in4, in5, in6, in7, in8 = in;
					#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7 = gain;
					#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7 = coords;
					^AmbisonicEncoderV2Order1In8.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler_0:doppler, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, doppler_1:doppler, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, doppler_2:doppler, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, doppler_3:doppler, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, doppler_4:doppler, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, doppler_5:doppler, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, doppler_6:doppler, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, doppler_7:doppler)}


		{order == 2}
		            {var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7;
					#in1, in2, in3, in4, in5, in6, in7, in8 = in;
					#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7 = gain;
					#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7 = coords;
					^AmbisonicEncoderV2Order2In8.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler_0:doppler, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, doppler_1:doppler, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, doppler_2:doppler, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, doppler_3:doppler, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, doppler_4:doppler, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, doppler_5:doppler, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, doppler_6:doppler, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, doppler_7:doppler)}


		{order == 3}
		            {var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7;
					#in1, in2, in3, in4, in5, in6, in7, in8 = in;
					#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7 = gain;
					#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7 = coords;
					^AmbisonicEncoderV2Order3In8.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler_0:doppler, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, doppler_1:doppler, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, doppler_2:doppler, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, doppler_3:doppler, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, doppler_4:doppler, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, doppler_5:doppler, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, doppler_6:doppler, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, doppler_7:doppler)}


		{order == 4}
		            {var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7;
					#in1, in2, in3, in4, in5, in6, in7, in8 = in;
					#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7 = gain;
					#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7 = coords;
					^AmbisonicEncoderV2Order4In8.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler_0:doppler, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, doppler_1:doppler, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, doppler_2:doppler, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, doppler_3:doppler, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, doppler_4:doppler, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, doppler_5:doppler, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, doppler_6:doppler, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, doppler_7:doppler)}


		{order == 5}
		            {var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7;
					#in1, in2, in3, in4, in5, in6, in7, in8 = in;
					#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7 = gain;
					#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7 = coords;
					^AmbisonicEncoderV2Order5In8.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler_0:doppler, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, doppler_1:doppler, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, doppler_2:doppler, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, doppler_3:doppler, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, doppler_4:doppler, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, doppler_5:doppler, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, doppler_6:doppler, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, doppler_7:doppler)}


		{order == 6}
		            {var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7;
					#in1, in2, in3, in4, in5, in6, in7, in8 = in;
					#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7 = gain;
					#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7 = coords;
					^AmbisonicEncoderV2Order6In8.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler_0:doppler, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, doppler_1:doppler, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, doppler_2:doppler, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, doppler_3:doppler, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, doppler_4:doppler, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, doppler_5:doppler, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, doppler_6:doppler, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, doppler_7:doppler)}


		{order == 7}
		            {var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7;
					#in1, in2, in3, in4, in5, in6, in7, in8 = in;
					#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7 = gain;
					#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7 = coords;
					^AmbisonicEncoderV2Order7In8.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7)}

		               				               				               		

	}
}

