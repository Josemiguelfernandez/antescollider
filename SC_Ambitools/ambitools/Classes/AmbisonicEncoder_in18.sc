AmbisonicEncoder_in18 {
	*ar { |order, in, gain, coords|
		case   	{order == 1}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9, in11, gain_10, radius10, az10, elev10, in12, gain_11, radius11, az11, elev11, in13, gain_12, radius12, az12, elev12, in14, gain_13, radius13, az13, elev13, in15, gain_14, radius14, az14, elev14, in16, gain_15, radius15, az15, elev15, in17, gain_16, radius16, az16, elev16, in18, gain_17, radius17, az17, elev17;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9, gain_10, gain_11, gain_12, gain_13, gain_14, gain_15, gain_16, gain_17 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9, radius10, az10, elev10, radius11, az11, elev11, radius12, az12, elev12, radius13, az13, elev13, radius14, az14, elev14, radius15, az15, elev15, radius16, az16, elev16, radius17, az17, elev17 = coords;
		^AmbisonicEncoderOrder1In18.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9, in11:in11, gain_10:gain_10, radius_10:radius10, azimuth_10:az10.neg, elevation_10:elev10, in12:in12, gain_11:gain_11, radius_11:radius11, azimuth_11:az11.neg, elevation_11:elev11, in13:in13, gain_12:gain_12, radius_12:radius12, azimuth_12:az12.neg, elevation_12:elev12, in14:in14, gain_13:gain_13, radius_13:radius13, azimuth_13:az13.neg, elevation_13:elev13, in15:in15, gain_14:gain_14, radius_14:radius14, azimuth_14:az14.neg, elevation_14:elev14, in16:in16, gain_15:gain_15, radius_15:radius15, azimuth_15:az15.neg, elevation_15:elev15, in17:in17, gain_16:gain_16, radius_16:radius16, azimuth_16:az16.neg, elevation_16:elev16, in18:in18, gain_17:gain_17, radius_17:radius17, azimuth_17:az17.neg, elevation_17:elev17)}


		{order == 2}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9, in11, gain_10, radius10, az10, elev10, in12, gain_11, radius11, az11, elev11, in13, gain_12, radius12, az12, elev12, in14, gain_13, radius13, az13, elev13, in15, gain_14, radius14, az14, elev14, in16, gain_15, radius15, az15, elev15, in17, gain_16, radius16, az16, elev16, in18, gain_17, radius17, az17, elev17;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9, gain_10, gain_11, gain_12, gain_13, gain_14, gain_15, gain_16, gain_17 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9, radius10, az10, elev10, radius11, az11, elev11, radius12, az12, elev12, radius13, az13, elev13, radius14, az14, elev14, radius15, az15, elev15, radius16, az16, elev16, radius17, az17, elev17 = coords;
		^AmbisonicEncoderOrder2In18.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9, in11:in11, gain_10:gain_10, radius_10:radius10, azimuth_10:az10.neg, elevation_10:elev10, in12:in12, gain_11:gain_11, radius_11:radius11, azimuth_11:az11.neg, elevation_11:elev11, in13:in13, gain_12:gain_12, radius_12:radius12, azimuth_12:az12.neg, elevation_12:elev12, in14:in14, gain_13:gain_13, radius_13:radius13, azimuth_13:az13.neg, elevation_13:elev13, in15:in15, gain_14:gain_14, radius_14:radius14, azimuth_14:az14.neg, elevation_14:elev14, in16:in16, gain_15:gain_15, radius_15:radius15, azimuth_15:az15.neg, elevation_15:elev15, in17:in17, gain_16:gain_16, radius_16:radius16, azimuth_16:az16.neg, elevation_16:elev16, in18:in18, gain_17:gain_17, radius_17:radius17, azimuth_17:az17.neg, elevation_17:elev17)}


		{order == 3}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9, in11, gain_10, radius10, az10, elev10, in12, gain_11, radius11, az11, elev11, in13, gain_12, radius12, az12, elev12, in14, gain_13, radius13, az13, elev13, in15, gain_14, radius14, az14, elev14, in16, gain_15, radius15, az15, elev15, in17, gain_16, radius16, az16, elev16, in18, gain_17, radius17, az17, elev17;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9, gain_10, gain_11, gain_12, gain_13, gain_14, gain_15, gain_16, gain_17 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9, radius10, az10, elev10, radius11, az11, elev11, radius12, az12, elev12, radius13, az13, elev13, radius14, az14, elev14, radius15, az15, elev15, radius16, az16, elev16, radius17, az17, elev17 = coords;
		^AmbisonicEncoderOrder3In18.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9, in11:in11, gain_10:gain_10, radius_10:radius10, azimuth_10:az10.neg, elevation_10:elev10, in12:in12, gain_11:gain_11, radius_11:radius11, azimuth_11:az11.neg, elevation_11:elev11, in13:in13, gain_12:gain_12, radius_12:radius12, azimuth_12:az12.neg, elevation_12:elev12, in14:in14, gain_13:gain_13, radius_13:radius13, azimuth_13:az13.neg, elevation_13:elev13, in15:in15, gain_14:gain_14, radius_14:radius14, azimuth_14:az14.neg, elevation_14:elev14, in16:in16, gain_15:gain_15, radius_15:radius15, azimuth_15:az15.neg, elevation_15:elev15, in17:in17, gain_16:gain_16, radius_16:radius16, azimuth_16:az16.neg, elevation_16:elev16, in18:in18, gain_17:gain_17, radius_17:radius17, azimuth_17:az17.neg, elevation_17:elev17)}


		{order == 4}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9, in11, gain_10, radius10, az10, elev10, in12, gain_11, radius11, az11, elev11, in13, gain_12, radius12, az12, elev12, in14, gain_13, radius13, az13, elev13, in15, gain_14, radius14, az14, elev14, in16, gain_15, radius15, az15, elev15, in17, gain_16, radius16, az16, elev16, in18, gain_17, radius17, az17, elev17;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9, gain_10, gain_11, gain_12, gain_13, gain_14, gain_15, gain_16, gain_17 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9, radius10, az10, elev10, radius11, az11, elev11, radius12, az12, elev12, radius13, az13, elev13, radius14, az14, elev14, radius15, az15, elev15, radius16, az16, elev16, radius17, az17, elev17 = coords;
		^AmbisonicEncoderOrder4In18.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9, in11:in11, gain_10:gain_10, radius_10:radius10, azimuth_10:az10.neg, elevation_10:elev10, in12:in12, gain_11:gain_11, radius_11:radius11, azimuth_11:az11.neg, elevation_11:elev11, in13:in13, gain_12:gain_12, radius_12:radius12, azimuth_12:az12.neg, elevation_12:elev12, in14:in14, gain_13:gain_13, radius_13:radius13, azimuth_13:az13.neg, elevation_13:elev13, in15:in15, gain_14:gain_14, radius_14:radius14, azimuth_14:az14.neg, elevation_14:elev14, in16:in16, gain_15:gain_15, radius_15:radius15, azimuth_15:az15.neg, elevation_15:elev15, in17:in17, gain_16:gain_16, radius_16:radius16, azimuth_16:az16.neg, elevation_16:elev16, in18:in18, gain_17:gain_17, radius_17:radius17, azimuth_17:az17.neg, elevation_17:elev17)}


		{order == 5}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9, in11, gain_10, radius10, az10, elev10, in12, gain_11, radius11, az11, elev11, in13, gain_12, radius12, az12, elev12, in14, gain_13, radius13, az13, elev13, in15, gain_14, radius14, az14, elev14, in16, gain_15, radius15, az15, elev15, in17, gain_16, radius16, az16, elev16, in18, gain_17, radius17, az17, elev17;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9, gain_10, gain_11, gain_12, gain_13, gain_14, gain_15, gain_16, gain_17 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9, radius10, az10, elev10, radius11, az11, elev11, radius12, az12, elev12, radius13, az13, elev13, radius14, az14, elev14, radius15, az15, elev15, radius16, az16, elev16, radius17, az17, elev17 = coords;
		^AmbisonicEncoderOrder5In18.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9, in11:in11, gain_10:gain_10, radius_10:radius10, azimuth_10:az10.neg, elevation_10:elev10, in12:in12, gain_11:gain_11, radius_11:radius11, azimuth_11:az11.neg, elevation_11:elev11, in13:in13, gain_12:gain_12, radius_12:radius12, azimuth_12:az12.neg, elevation_12:elev12, in14:in14, gain_13:gain_13, radius_13:radius13, azimuth_13:az13.neg, elevation_13:elev13, in15:in15, gain_14:gain_14, radius_14:radius14, azimuth_14:az14.neg, elevation_14:elev14, in16:in16, gain_15:gain_15, radius_15:radius15, azimuth_15:az15.neg, elevation_15:elev15, in17:in17, gain_16:gain_16, radius_16:radius16, azimuth_16:az16.neg, elevation_16:elev16, in18:in18, gain_17:gain_17, radius_17:radius17, azimuth_17:az17.neg, elevation_17:elev17)}


		{order == 6}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9, in11, gain_10, radius10, az10, elev10, in12, gain_11, radius11, az11, elev11, in13, gain_12, radius12, az12, elev12, in14, gain_13, radius13, az13, elev13, in15, gain_14, radius14, az14, elev14, in16, gain_15, radius15, az15, elev15, in17, gain_16, radius16, az16, elev16, in18, gain_17, radius17, az17, elev17;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9, gain_10, gain_11, gain_12, gain_13, gain_14, gain_15, gain_16, gain_17 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9, radius10, az10, elev10, radius11, az11, elev11, radius12, az12, elev12, radius13, az13, elev13, radius14, az14, elev14, radius15, az15, elev15, radius16, az16, elev16, radius17, az17, elev17 = coords;
		^AmbisonicEncoderOrder6In18.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9, in11:in11, gain_10:gain_10, radius_10:radius10, azimuth_10:az10.neg, elevation_10:elev10, in12:in12, gain_11:gain_11, radius_11:radius11, azimuth_11:az11.neg, elevation_11:elev11, in13:in13, gain_12:gain_12, radius_12:radius12, azimuth_12:az12.neg, elevation_12:elev12, in14:in14, gain_13:gain_13, radius_13:radius13, azimuth_13:az13.neg, elevation_13:elev13, in15:in15, gain_14:gain_14, radius_14:radius14, azimuth_14:az14.neg, elevation_14:elev14, in16:in16, gain_15:gain_15, radius_15:radius15, azimuth_15:az15.neg, elevation_15:elev15, in17:in17, gain_16:gain_16, radius_16:radius16, azimuth_16:az16.neg, elevation_16:elev16, in18:in18, gain_17:gain_17, radius_17:radius17, azimuth_17:az17.neg, elevation_17:elev17)}


		{order == 7}
		{var in1, gain_0, radius0, az0, elev0, in2, gain_1, radius1, az1, elev1, in3, gain_2, radius2, az2, elev2, in4, gain_3, radius3, az3, elev3, in5, gain_4, radius4, az4, elev4, in6, gain_5, radius5, az5, elev5, in7, gain_6, radius6, az6, elev6, in8, gain_7, radius7, az7, elev7, in9, gain_8, radius8, az8, elev8, in10, gain_9, radius9, az9, elev9, in11, gain_10, radius10, az10, elev10, in12, gain_11, radius11, az11, elev11, in13, gain_12, radius12, az12, elev12, in14, gain_13, radius13, az13, elev13, in15, gain_14, radius14, az14, elev14, in16, gain_15, radius15, az15, elev15, in17, gain_16, radius16, az16, elev16, in18, gain_17, radius17, az17, elev17;
		#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18 = in;
		#gain_0, gain_1, gain_2, gain_3, gain_4, gain_5, gain_6, gain_7, gain_8, gain_9, gain_10, gain_11, gain_12, gain_13, gain_14, gain_15, gain_16, gain_17 = gain;
		#radius0, az0, elev0, radius1, az1, elev1, radius2, az2, elev2, radius3, az3, elev3, radius4, az4, elev4, radius5, az5, elev5, radius6, az6, elev6, radius7, az7, elev7, radius8, az8, elev8, radius9, az9, elev9, radius10, az10, elev10, radius11, az11, elev11, radius12, az12, elev12, radius13, az13, elev13, radius14, az14, elev14, radius15, az15, elev15, radius16, az16, elev16, radius17, az17, elev17 = coords;
		^AmbisonicEncoderOrder7In18.ar(in1:in1, gain_0:gain_0, radius_0:radius0, azimuth_0:az0.neg, elevation_0:elev0, in2:in2, gain_1:gain_1, radius_1:radius1, azimuth_1:az1.neg, elevation_1:elev1, in3:in3, gain_2:gain_2, radius_2:radius2, azimuth_2:az2.neg, elevation_2:elev2, in4:in4, gain_3:gain_3, radius_3:radius3, azimuth_3:az3.neg, elevation_3:elev3, in5:in5, gain_4:gain_4, radius_4:radius4, azimuth_4:az4.neg, elevation_4:elev4, in6:in6, gain_5:gain_5, radius_5:radius5, azimuth_5:az5.neg, elevation_5:elev5, in7:in7, gain_6:gain_6, radius_6:radius6, azimuth_6:az6.neg, elevation_6:elev6, in8:in8, gain_7:gain_7, radius_7:radius7, azimuth_7:az7.neg, elevation_7:elev7, in9:in9, gain_8:gain_8, radius_8:radius8, azimuth_8:az8.neg, elevation_8:elev8, in10:in10, gain_9:gain_9, radius_9:radius9, azimuth_9:az9.neg, elevation_9:elev9, in11:in11, gain_10:gain_10, radius_10:radius10, azimuth_10:az10.neg, elevation_10:elev10, in12:in12, gain_11:gain_11, radius_11:radius11, azimuth_11:az11.neg, elevation_11:elev11, in13:in13, gain_12:gain_12, radius_12:radius12, azimuth_12:az12.neg, elevation_12:elev12, in14:in14, gain_13:gain_13, radius_13:radius13, azimuth_13:az13.neg, elevation_13:elev13, in15:in15, gain_14:gain_14, radius_14:radius14, azimuth_14:az14.neg, elevation_14:elev14, in16:in16, gain_15:gain_15, radius_15:radius15, azimuth_15:az15.neg, elevation_15:elev15, in17:in17, gain_16:gain_16, radius_16:radius16, azimuth_16:az16.neg, elevation_16:elev16, in18:in18, gain_17:gain_17, radius_17:radius17, azimuth_17:az17.neg, elevation_17:elev17)}

		               				               				               		

	}
}

