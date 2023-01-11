AmbisonicEncoderV2_in2 {
	*ar { |order, in, gain, coords, doppler|
		case   	{order == 1}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1, dopp0, dopp1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		#dopp0, dopp1 = doppler;
		               		^AmbisonicEncoderV2Order1In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1, doppler_0:dopp0, doppler_1:dopp1)}
		{order == 2}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1, dopp0, dopp1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		#dopp0, dopp1 = doppler;
		               		^AmbisonicEncoderV2Order2In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1, doppler_0:dopp0, doppler_1:dopp1)}
		{order == 3}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1, dopp0, dopp1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		#dopp0, dopp1 = doppler;
		               		^AmbisonicEncoderV2Order3In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1, doppler_0:dopp0, doppler_1:dopp1)}
		{order == 4}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1, dopp0, dopp1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		#dopp0, dopp1 = doppler;
		               		^AmbisonicEncoderV2Order4In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1, doppler_0:dopp0, doppler_1:dopp1)}
		{order == 5}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1, dopp0, dopp1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		#dopp0, dopp1 = doppler;
		               		^AmbisonicEncoderV2Order5In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1, doppler_0:dopp0, doppler_1:dopp1)}
		{order == 6}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1, dopp0, dopp1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		#dopp0, dopp1 = doppler;
		               		^AmbisonicEncoderV2Order6In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1, doppler_0:dopp0, doppler_1:dopp1)}
		{order == 7}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1, dopp0, dopp1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		#dopp0, dopp1 = doppler;
		               		^AmbisonicEncoderV2Order7In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1, doppler_0:dopp0, doppler_1:dopp1)}

	}
}

