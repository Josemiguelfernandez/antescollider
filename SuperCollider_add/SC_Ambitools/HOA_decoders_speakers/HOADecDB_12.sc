HOADecDB_12{

	classvar <>speakerPositions;
	classvar <>sweeterPositions;
	classvar <>speakerLabels;
	classvar <>sweetSpot;

	*initClass {
		speakerPositions = [
			[ 3.14415, 7.789372, 0.0 ],  //ch01
			[ -2.942313, -7.782364, 0.0 ],  //ch02
			[ -11.966436, 3.371754, 0.0 ],  //ch03
			[ 11.96166, -3.54041, 0.0 ],  //ch04
			[ -2.868383, 7.832789, 0.0 ],  //ch05
			[ 3.286479, -7.775069, 0.0 ],  //ch06
			[ 8.026676, 7.72324, 0.0 ],  //ch07
			[ -8.076071, -7.809069, 0.0 ],  //ch08
			[ -12.003689, -3.663902, 0.0 ],  //ch09
			[ 11.94365, 3.73554, 0.0 ],  //ch10
			[ -8.031998, 7.75053, 0.0 ],  //ch11
			[ 8.240226, -7.750394, 0.0 ]  //ch12
		];

		sweeterPositions = [
			[ 3.084, 7.799, -0.01 ],  //ch01
			[ -3.002, -7.773, -0.01 ],  //ch02
			[ -12.026, 3.381, -0.01 ],  //ch03
			[ 11.902, -3.531, -0.01 ],  //ch04
			[ -2.928, 7.842, -0.01 ],  //ch05
			[ 3.226, -7.766, -0.01 ],  //ch06
			[ 7.967, 7.733, -0.01 ],  //ch07
			[ -8.136, -7.8, -0.01 ],  //ch08
			[ -12.064, -3.655, -0.01 ],  //ch09
			[ 11.884, 3.745, -0.01 ],  //ch10
			[ -8.092, 7.76, -0.01 ],  //ch11
			[ 8.18, -7.741, -0.01 ]  //ch12
		];

		speakerLabels = [
			"ch01",
			"ch02",
			"ch03",
			"ch04",
			"ch05",
			"ch06",
			"ch07",
			"ch08",
			"ch09",
			"ch10",
			"ch11",
			"ch12"
		];

		sweetSpot = [ 0.059, -0.01, 0.01 ];

	}

	*ar { |order, in, gain(-10.0), lf_hf(0.0), mute(0.0), xover(100.0)|
		// declare variables for the b-format array in
		// distribute the channels from the array over in1 ... inN
		// return the Ugen with the b-format channels and with the args from the *ar method
		case
		{order == 1}
		{ var in1, in2, in3, in4;
			#in1,in2, in3, in4 = in;
			^DB123.ar(in1, in2, in3, in4, gain, lf_hf, mute, xover)}

		{order == 2}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9 = in;
			^DB122.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, gain, lf_hf, mute, xover)}

		{order == 3}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16 = in;
			^DB123.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, gain, lf_hf, mute, xover)}
		{order == 4}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25 = in;
			^DB124.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, gain, lf_hf, mute, xover)}

		{order == 5}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36 = in;
			^DB125.ar(in1,in2, in3, in4,in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, gain, lf_hf, mute, xover)}

		{order == 6}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49  = in;
			^DB126.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, gain, lf_hf, mute, xover)}

		{order == 7}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64 = in;
			^DB127.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64, gain, lf_hf, mute, xover)}
	}

}