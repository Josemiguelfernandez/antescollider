AmbisonicEncoder_in10 {
	*ar { |order, in, gain, coords|
		case   	{order == 1}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9 = coords;
		^AmbisonicEncoderOrder1In10.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9)}


		{order == 2}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9 = coords;
		^AmbisonicEncoderOrder2In10.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9)}


		{order == 3}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9 = coords;
		^AmbisonicEncoderOrder3In10.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9)}


		{order == 4}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9 = coords;
		^AmbisonicEncoderOrder4In10.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9)}


		{order == 5}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9 = coords;
		^AmbisonicEncoderOrder5In10.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9)}


		{order == 6}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9 = coords;
		^AmbisonicEncoderOrder6In10.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9)}


		{order == 7}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9 = coords;
		^AmbisonicEncoderOrder7In10.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9)}

		               				               				               		

	}
}

