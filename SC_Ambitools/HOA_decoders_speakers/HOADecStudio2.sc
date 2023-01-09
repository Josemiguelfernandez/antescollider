HOADecStudio2{

classvar <>speakerPositions;
classvar <>sweeterPositions;
classvar <>speakerLabels;
classvar <>sweetSpot;

*initClass { 
speakerPositions = [
					[ 0.0, 2.63992, -0.230963 ],  //ch01
					[ -1.69691, 2.02229, -0.230963 ],  //ch02
					[ -2.59981, 0.458417, -0.230963 ],  //ch03
					[ -2.28623, -1.31996, -0.230963 ],  //ch04
					[ -0.902904, -2.48071, -0.230963 ],  //ch05
					[ 0.902904, -2.48071, -0.230963 ],  //ch06
					[ 2.28623, -1.31996, -0.230963 ],  //ch07
					[ 2.59981, 0.458417, -0.230963 ],  //ch08
					[ 1.69691, 2.02229, -0.230963 ],  //ch09
					[ -0.814625, 2.23816, 1.16168 ],  //ch10
					[ -2.0627, 1.1909, 1.16168 ],  //ch11
					[ -2.34562, -0.413596, 1.16168 ],  //ch12
					[ -1.53099, -1.82457, 1.16168 ],  //ch13
					[ 0.0, -2.3818, 1.16168 ],  //ch14
					[ 1.53099, -1.82457, 1.16168 ],  //ch15
					[ 2.34562, -0.413596, 1.16168 ],  //ch16
					[ 2.0627, 1.1909, 1.16168 ],  //ch17
					[ 0.814625, 2.23816, 1.16168 ],  //ch18
					[ 0.0, 1.44329, 2.22248 ],  //ch19
					[ -1.37265, 0.446002, 2.22248 ],  //ch20
					[ -0.848347, -1.16765, 2.22248 ],  //ch21
					[ 0.848347, -1.16765, 2.22248 ],  //ch22
					[ 1.37265, 0.446002, 2.22248 ],  //ch23
					[ 0.0, 0.0, 2.65 ]  //ch24
					];

sweeterPositions = [
					[ 0.0, 2.639, -1.154 ],  //ch01
					[ -1.697, 2.022, -1.154 ],  //ch02
					[ -2.6, 0.458, -1.154 ],  //ch03
					[ -2.287, -1.32, -1.154 ],  //ch04
					[ -0.903, -2.481, -1.154 ],  //ch05
					[ 0.902, -2.481, -1.154 ],  //ch06
					[ 2.286, -1.32, -1.154 ],  //ch07
					[ 2.599, 0.458, -1.154 ],  //ch08
					[ 1.696, 2.022, -1.154 ],  //ch09
					[ -0.815, 2.238, 0.239 ],  //ch10
					[ -2.063, 1.19, 0.239 ],  //ch11
					[ -2.346, -0.414, 0.239 ],  //ch12
					[ -1.531, -1.825, 0.239 ],  //ch13
					[ 0.0, -2.382, 0.239 ],  //ch14
					[ 1.53, -1.825, 0.239 ],  //ch15
					[ 2.345, -0.414, 0.239 ],  //ch16
					[ 2.062, 1.19, 0.239 ],  //ch17
					[ 0.814, 2.238, 0.239 ],  //ch18
					[ 0.0, 1.443, 1.3 ],  //ch19
					[ -1.373, 0.446, 1.3 ],  //ch20
					[ -0.849, -1.168, 1.3 ],  //ch21
					[ 0.848, -1.168, 1.3 ],  //ch22
					[ 1.372, 0.446, 1.3 ],  //ch23
					[ 0.0, 0.0, 1.727 ]  //ch24
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
					"ch12",
					"ch13",
					"ch14",
					"ch15",
					"ch16",
					"ch17",
					"ch18",
					"ch19",
					"ch20",
					"ch21",
					"ch22",
					"ch23",
					"ch24"
					];

sweetSpot = [ -0.001, -0.001, 0.922 ];

}

*ar { |order, in, gain(-10.0), lf_hf(0.0), mute(0.0), xover(400.0)|
// declare variables for the b-format array in
// distribute the channels from the array over in1 ... inN
// return the Ugen with the b-format channels and with the args from the *ar method
		case
		{order == 1}
		{ var in1, in2, in3, in4;
			#in1,in2, in3, in4 = in;
			^Studio21.ar(in1, in2, in3, in4, gain, lf_hf, mute, xover)}

		{order == 2}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9 = in;
			^Studio22.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, gain, lf_hf, mute, xover)}

		{order == 3}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16 = in;
			^Studio23.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, gain, lf_hf, mute, xover)}

		{order == 4}
		{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25 = in;
			^Studio24.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, gain, lf_hf, mute, xover)}

		{order == 5}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36 = in;
			^Studio25.ar(in1,in2, in3, in4,in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, gain, lf_hf, mute, xover)}

		{order == 6}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49  = in;
			^Studio26.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, gain, lf_hf, mute, xover)}

		{order == 7}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64 = in;
			^Studio27.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64, gain, lf_hf, mute, xover)}
	}


}