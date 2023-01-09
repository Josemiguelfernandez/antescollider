AmbisonicEncoder_in1{
	*ar { |order, in, gain, coords|
		case   	{order == 1}
		               	{var radius0, az0, elev0;
		               		#radius0,az0,elev0 = coords;
		               		^AmbisonicEncoderOrder1In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0)}
		{order == 2}
		               	{var radius0, az0, elev0;
		               		#radius0,az0,elev0 = coords;
		               		^AmbisonicEncoderOrder2In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0)}
		{order == 3}
		               	{var radius0, az0, elev0;
		               		#radius0,az0,elev0 = coords;
		               		^AmbisonicEncoderOrder3In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0)}
		{order == 4}
		               	{var radius0, az0, elev0;
		               		#radius0,az0,elev0 = coords;
		               		^AmbisonicEncoderOrder4In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0)}
		{order == 5}
		               	{var radius0, az0, elev0;
		               		#radius0,az0,elev0 = coords;
		               		^AmbisonicEncoderOrder5In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0)}
		{order == 6}
		               	{var radius0, az0, elev0;
		               		#radius0,az0,elev0 = coords;
		               		^AmbisonicEncoderOrder6In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0)}
		{order == 7}
		               	{var radius0, az0, elev0;
		               		#radius0,az0,elev0 = coords;
		               		^AmbisonicEncoderOrder7In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0)}

	}
}
