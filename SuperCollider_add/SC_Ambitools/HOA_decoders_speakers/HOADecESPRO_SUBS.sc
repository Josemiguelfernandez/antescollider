HOADecESPRO_SUBS{

	classvar <>speakerPositions;
	classvar <>sweeterPositions;
	classvar <>speakerLabels;
	classvar <>sweetSpot;

	*initClass {
		speakerPositions = [
			[ -12.0, 7.8, 0.0 ],  //ch01
			[ -4.0, 7.8, 0.0 ],  //ch02
			[ 4.0, 7.8, 0.0 ],  //ch03
			[ 12.0, 7.8, 0.0 ],  //ch04
			[ 12.0, -7.8, 0.0 ],  //ch05
			[ 4.0, -7.8, 0.0 ],  //ch06
			[ -4.0, -7.8, 0.0 ],  //ch07
			[ -12.0, -7.8, 0.0 ]  //ch08
		];

		sweeterPositions = [
			[ -12.0, 7.8, 0.0 ],  //ch01
			[ -4.0, 7.8, 0.0 ],  //ch02
			[ 4.0, 7.8, 0.0 ],  //ch03
			[ 12.0, 7.8, 0.0 ],  //ch04
			[ 12.0, -7.8, 0.0 ],  //ch05
			[ 4.0, -7.8, 0.0 ],  //ch06
			[ -4.0, -7.8, 0.0 ],  //ch07
			[ -12.0, -7.8, 0.0 ]  //ch08
		];

		speakerLabels = [
			"ch01",
			"ch02",
			"ch03",
			"ch04",
			"ch05",
			"ch06",
			"ch07",
			"ch08"
		];

		sweetSpot = [ 0.0, -0.001, 0.0 ];

	}

	*ar { |order, in, gain(-10.0), lf_hf(0.0), mute(0.0), xover(10.0)|
		// declare variables for the b-format array in
		// distribute the channels from the array over in1 ... inN
		// return the Ugen with the b-format channels and with the args from the *ar method
		case
		{order == 1}
		{ var in1, in2, in3, in4;
			#in1,in2, in3, in4 = in;
			^ESPROSUBS1.ar(in1, in2, in3, in4, gain, lf_hf, mute, xover)}

		{order == 2}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9 = in;
			^ESPROSUBS2.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, gain, lf_hf, mute, xover)}

		{order == 3}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16 = in;
			^ESPROSUBS3.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, gain, lf_hf, mute, xover)}

		{order == 4}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25 = in;
			^ESPROSUBS4.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, gain, lf_hf, mute, xover)}

		{order == 5}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36 = in;
			^ESPROSUBS5.ar(in1,in2, in3, in4,in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, gain, lf_hf, mute, xover)}

		{order == 6}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49  = in;
			^ESPROSUBS6.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, gain, lf_hf, mute, xover)}

		{order == 7}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64 = in;
			^ESPROSUBS7.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64, gain, lf_hf, mute, xover)}
	}

}
