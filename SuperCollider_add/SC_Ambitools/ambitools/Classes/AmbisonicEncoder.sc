AmbisonicEncoder{
	*ar { |order, in, gain=0, radius=1,az=0, elev = 0|
		case   	{order == 1}
		               	{^AmbisonicEncoderOrder1In1.ar(in1:in, gain_0:gain, radius_0:radius, azimuth_0:az, elevation_0:elev)}
		      	{order == 2}
                		{^AmbisonicEncoderOrder2In1.ar(in1:in, gain_0:gain, radius_0:radius, azimuth_0:az, elevation_0:elev)}
              	{order == 3}
                		{^AmbisonicEncoderOrder3In1.ar(in1:in, gain_0:gain, radius_0:radius, azimuth_0:az, elevation_0:elev)}
              	{order == 4}
                		{^AmbisonicEncoderOrder4In1.ar(in1:in, gain_0:gain, radius_0:radius, azimuth_0:az, elevation_0:elev)}
              	{order == 5}
                		{^AmbisonicEncoderOrder5In1.ar(in1:in, gain_0:gain, radius_0:radius, azimuth_0:az, elevation_0:elev)}
				{order == 6}
                		{^AmbisonicEncoderOrder6In1.ar(in1:in, gain_0:gain, radius_0:radius, azimuth_0:az, elevation_0:elev)}
				{order == 7}
                		{^AmbisonicEncoderOrder7In1.ar(in1:in, gain_0:gain, radius_0:radius, azimuth_0:az, elevation_0:elev)}

	}
}
