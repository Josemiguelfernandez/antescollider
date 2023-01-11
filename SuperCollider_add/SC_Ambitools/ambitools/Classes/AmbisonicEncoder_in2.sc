AmbisonicEncoder_in2 {
	*ar { |order, in, gain, coords|
		case   	{order == 1}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		^AmbisonicEncoderOrder1In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1)}
		{order == 2}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		^AmbisonicEncoderOrder2In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1)}
		{order == 3}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		^AmbisonicEncoderOrder3In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1)}
		{order == 4}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		^AmbisonicEncoderOrder4In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1)}
		{order == 5}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		^AmbisonicEncoderOrder5In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1)}
		{order == 6}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		^AmbisonicEncoderOrder6In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1)}
		{order == 7}
		               	{var in1, in2, gain_0, gain_1, radius0, radius1, az0, az1, elev0, elev1;
		               		#in1,in2 = in;
		               		#gain_0,gain_1 = gain;
		               		#radius0,az0,elev0,radius1,az1,elev1 = coords;
		               		^AmbisonicEncoderOrder7In2.ar(in1:in1, in2:in2, gain_0:gain_0, gain_1:gain_1, radius_0:radius0, radius_1:radius1, azimuth_0:az0.neg, azimuth_1:az1.neg, elevation_0:elev0, elevation_1:elev1)}

	}
}

