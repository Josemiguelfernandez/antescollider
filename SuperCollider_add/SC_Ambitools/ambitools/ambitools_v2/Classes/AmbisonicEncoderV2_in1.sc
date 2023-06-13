AmbisonicEncoderV2_in1{
	*ar { |order, in, gain, coords, doppler|
		case   	{order == 1}
		               	{var radius0, az0, elev0, dopp; //, x0, y0, z0;
		               		#radius0, az0, elev0 = coords;
		               		// #x0, y0, z0 = coords_car;
		               		^AmbisonicEncoderV2Order1In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler:doppler)}
		{order == 2}
		               	{var radius0, az0, elev0, dopp; //, x0, y0, z0;
		               		#radius0, az0, elev0 = coords;
		               		// #x0, y0, z0 = coords_car;
		               		^AmbisonicEncoderV2Order2In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler:doppler)}
		{order == 3}
		               	{var radius0, az0, elev0, dopp; //, x0, y0, z0;
		               		#radius0, az0, elev0 = coords;
		               		// #x0, y0, z0 = coords_car;
		               		^AmbisonicEncoderV2Order3In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0)} //, doppler:doppler)}
		{order == 4}
		               	{var radius0, az0, elev0, dopp; //, x0, y0, z0;
		               		#radius0, az0, elev0 = coords;
		               		// #x0, y0, z0 = coords_car;
		               		^AmbisonicEncoderV2Order4In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler:doppler)}
		{order == 5}
		               	{var radius0, az0, elev0, dopp; //, x0, y0, z0;
		               		#radius0, az0, elev0 = coords;
		               		// #x0, y0, z0 = coords_car;
		               		^AmbisonicEncoderV2Order5In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler:doppler)}
		{order == 6}
		               	{var radius0, az0, elev0, dopp; //, x0, y0, z0;
		               		#radius0, az0, elev0 = coords;
		               		// #x0, y0, z0 = coords_car;
		               		^AmbisonicEncoderV2Order6In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, doppler:doppler)}
		{order == 7}
		               	{var radius0, az0, elev0, dopp; //, x0, y0, z0;
		               		#radius0, az0, elev0 = coords;
		               		// #x0, y0, z0 = coords_car;
		               		^AmbisonicEncoderV2Order7In1.ar(in1:in, gain_0:gain, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0)}
		//, doppler:doppler

	}
}

// x_0(1.0), y_0(0.0), z_0