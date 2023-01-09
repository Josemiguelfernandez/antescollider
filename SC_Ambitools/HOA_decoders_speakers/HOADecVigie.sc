HOADecVigie{

classvar <>speakerPositions;
classvar <>sweeterPositions;
classvar <>speakerLabels;
classvar <>sweetSpot;

*initClass { 
speakerPositions = [
					[ 2.41984, -1.12839, 0.0 ],  //ch01
					[ 2.45, -0.0, 0.0 ],  //ch02
					[ 2.43013, 1.03153, 0.0 ],  //ch03
					[ 2.04883, 1.60072, 0.0 ],  //ch04
					[ 0.386916, 1.67592, 0.0 ],  //ch05
					[ -0.862582, 1.69291, 0.0 ],  //ch06
					[ -1.74937, 1.01, 0.0 ],  //ch07
					[ -1.78422, -0.989012, 0.0 ],  //ch08
					[ -0.858042, -1.684, 0.0 ],  //ch09
					[ 0.359687, -1.6922, 0.0 ],  //ch10
					[ 1.99172, -1.67125, 0.0 ],  //ch11
					[ 2.43478, -0.652397, 0.967593 ],  //ch12
					[ 2.45281, 0.657229, 0.974761 ],  //ch13
					[ 1.24122, 1.7084, 0.984701 ],  //ch14
					[ -0.300715, 1.70544, 0.959923 ],  //ch15
					[ -1.72743, 1.66816, 0.970231 ],  //ch16
					[ -1.80121, -0.0, 0.957722 ],  //ch17
					[ -1.7777, -1.60065, 0.966485 ],  //ch18
					[ -0.294752, -1.67162, 0.98 ],  //ch19
					[ 1.25994, -1.672, 0.976248 ],  //ch20
					[ 1.26952, -0.201073, 1.97926 ],  //ch21
					[ 0.359674, 0.988196, 1.9778 ],  //ch22
					[ -0.707399, 0.843046, 1.98539 ],  //ch23
					[ -0.696369, -1.03241, 1.99291 ],  //ch24
					[ 0.344708, -0.947078, 1.97803 ],  //ch25
					[ 0.0, -0.209057, 1.98904 ]  //ch26
					];

sweeterPositions = [
					[ 2.077, -1.107, -0.794 ],  //ch01
					[ 2.108, 0.021, -0.794 ],  //ch02
					[ 2.088, 1.053, -0.794 ],  //ch03
					[ 1.706, 1.622, -0.794 ],  //ch04
					[ 0.044, 1.697, -0.794 ],  //ch05
					[ -1.205, 1.714, -0.794 ],  //ch06
					[ -2.092, 1.031, -0.794 ],  //ch07
					[ -2.127, -0.968, -0.794 ],  //ch08
					[ -1.2, -1.663, -0.794 ],  //ch09
					[ 0.017, -1.671, -0.794 ],  //ch10
					[ 1.649, -1.65, -0.794 ],  //ch11
					[ 2.092, -0.631, 0.173 ],  //ch12
					[ 2.11, 0.679, 0.18 ],  //ch13
					[ 0.899, 1.73, 0.19 ],  //ch14
					[ -0.643, 1.727, 0.166 ],  //ch15
					[ -2.07, 1.69, 0.176 ],  //ch16
					[ -2.144, 0.021, 0.163 ],  //ch17
					[ -2.12, -1.579, 0.172 ],  //ch18
					[ -0.637, -1.65, 0.186 ],  //ch19
					[ 0.918, -1.651, 0.182 ],  //ch20
					[ 0.927, -0.18, 1.185 ],  //ch21
					[ 0.017, 1.01, 1.183 ],  //ch22
					[ -1.05, 0.864, 1.191 ],  //ch23
					[ -1.039, -1.011, 1.199 ],  //ch24
					[ 0.002, -0.926, 1.184 ],  //ch25
					[ -0.342, -0.188, 1.195 ]  //ch26
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
					"ch24",
					"ch25",
					"ch26"
					];

sweetSpot = [ 0.341, -0.022, 0.793 ];

}

*ar { |order, in, gain(-10.0), lf_hf(0.0), mute(0.0), xover(400.0)|
// declare variables for the b-format array in
// distribute the channels from the array over in1 ... inN
// return the Ugen with the b-format channels and with the args from the *ar method
case
{order == 1}
	{ var in1, in2, in3, in4; 
	#in1,in2, in3, in4 = in; 
	^Vigie1.ar(in1, in2, in3, in4, gain, lf_hf, mute, xover)}

{order == 2}
	{ var in1, in2, in3, in4, in5, in6, in7, in8, in9; 
	#in1, in2, in3, in4, in5, in6, in7, in8, in9 = in; 
	^Vigie2.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, gain, lf_hf, mute, xover)}

{order == 3}
	{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16; 
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16 = in; 
	^Vigie3.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, gain, lf_hf, mute, xover)}

{order == 4}
	{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25; 
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25 = in; 
	^Vigie4.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, gain, lf_hf, mute, xover)} 

{order == 5}
	{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36; 
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36 = in; 
	^Vigie5.ar(in1,in2, in3, in4,in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, gain, lf_hf, mute, xover)} 

{order == 7}
	{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64;
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64 = in;
	^Vigie7.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64, gain, lf_hf, mute, xover)}
	}
}