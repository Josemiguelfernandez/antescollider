HOADecHexa{

	classvar <>speakerPositions;
	classvar <>sweeterPositions;
	classvar <>speakerLabels;
	classvar <>sweetSpot;

	*initClass {
speakerPositions = [
					[ 0.866025, 0.5, 0.0 ],  //ch1
					[ 0.866025, -0.5, 0.0 ],  //ch2
					[ 0.0, -1.0, 0.0 ],  //ch3
					[ -0.866025, -0.5, 0.0 ],  //ch4
					[ -0.866025, 0.5, 0.0 ],  //ch5
					[ 0.0, 1.0, 0.0 ]  //ch6
					];

sweeterPositions = [
					[ 0.866, 0.5, 0.01 ],  //ch1
					[ 0.866, -0.5, 0.01 ],  //ch2
					[ 0.0, -1.0, 0.01 ],  //ch3
					[ -0.867, -0.5, 0.01 ],  //ch4
					[ -0.867, 0.5, 0.01 ],  //ch5
					[ 0.0, 1.0, 0.01 ]  //ch6
					];

		speakerLabels = [
			"ch1",
			"ch2",
			"ch3",
			"ch4",
			"ch5",
			"ch6"
		];

		sweetSpot = [ 0.0, 0.0, -0.01 ];

	}

	*ar { |order, in, gain(-10.0), lf_hf(0.0), mute(0.0), xover(400.0)|
		// declare variables for the b-format array in
		// distribute the channels from the array over in1 ... inN
		// return the Ugen with the b-format channels and with the args from the *ar method
		case
		{order == 1}
		{ var in1, in2, in3, in4;
			#in1,in2, in3, in4 = in;
			^Hexa1.ar(in1, in2, in3, in4, gain, lf_hf, mute, xover)}

		{order == 2}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9 = in;
			^Hexa2.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, gain, lf_hf, mute, xover)}

		{order == 3}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16 = in;
			^Hexa3.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, gain, lf_hf, mute, xover)}

		{order == 4}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25 = in;
			^Hexa4.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, gain, lf_hf, mute, xover)}

		{order == 5}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36 = in;
			^Hexa5.ar(in1,in2, in3, in4,in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, gain, lf_hf, mute, xover)}

		{order == 6}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49  = in;
			^Hexa6.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, gain, lf_hf, mute, xover)}

		{order == 7}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64 = in;
			^Hexa7.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64, gain, lf_hf, mute, xover)}
	}


}