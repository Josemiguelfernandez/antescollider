HOADecPierre{

classvar <>speakerPositions;
classvar <>sweeterPositions;
classvar <>speakerLabels;
classvar <>sweetSpot;

*initClass {
speakerPositions = [
					[ 1.78, -1.32, 0.0 ],  //ch01
					[ 0.8, -1.32, 0.0 ],  //ch02
					[ 0.0, -1.32, 0.0 ],  //ch03
					[ -0.8, -1.32, 0.0 ],  //ch04
					[ -1.78, -1.32, 0.0 ],  //ch05
					[ 1.78, 0.0, 0.0 ],  //ch06
					[ -1.78, -0.0, 0.0 ],  //ch07
					[ 1.0, 1.32, 0.0 ],  //ch08
					[ -0.0, 1.32, 0.0 ],  //ch09
					[ -1.0, 1.32, 0.0 ],  //ch10
					[ 1.78, -1.32, 1.2 ],  //ch11
					[ 0.0, -1.32, 1.2 ],  //ch12
					[ -1.78, -1.32, 1.2 ],  //ch13
					[ 1.78, 0.0, 1.2 ],  //ch14
					[ 0.0, 0.0, 1.2 ],  //ch15
					[ -1.78, -0.0, 1.2 ],  //ch16
					[ 1.0, 1.32, 1.2 ],  //ch17
					[ -0.0, 1.32, 1.2 ],  //ch18
					[ -1.0, 1.32, 1.2 ],  //ch19
					[ 1.78, -1.32, -1.05 ],  //ch20
					[ 0.0, -1.32, -1.05 ],  //ch21
					[ -1.78, -1.32, -1.05 ]  //ch22
					];

sweeterPositions = [
					[ 1.78, -1.02, -0.348 ],  //ch01
					[ 0.8, -1.02, -0.348 ],  //ch02
					[ 0.0, -1.02, -0.348 ],  //ch03
					[ -0.8, -1.02, -0.348 ],  //ch04
					[ -1.78, -1.02, -0.348 ],  //ch05
					[ 1.78, 0.3, -0.348 ],  //ch06
					[ -1.78, 0.3, -0.348 ],  //ch07
					[ 1.0, 1.62, -0.348 ],  //ch08
					[ -0.0, 1.62, -0.348 ],  //ch09
					[ -1.0, 1.62, -0.348 ],  //ch10
					[ 1.78, -1.02, 0.852 ],  //ch11
					[ 0.0, -1.02, 0.852 ],  //ch12
					[ -1.78, -1.02, 0.852 ],  //ch13
					[ 1.78, 0.3, 0.852 ],  //ch14
					[ 0.0, 0.3, 0.852 ],  //ch15
					[ -1.78, 0.3, 0.852 ],  //ch16
					[ 1.0, 1.62, 0.852 ],  //ch17
					[ -0.0, 1.62, 0.852 ],  //ch18
					[ -1.0, 1.62, 0.852 ],  //ch19
					[ 1.78, -1.02, -1.398 ],  //ch20
					[ 0.0, -1.02, -1.398 ],  //ch21
					[ -1.78, -1.02, -1.398 ]  //ch22
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
					"ch22"
					];

sweetSpot = [ 0.0, -0.301, 0.347 ];

}

*ar { |order, in, gain(-10.0), lf_hf(0.0), mute(0.0), xover(400.0)|
// declare variables for the b-format array in
// distribute the channels from the array over in1 ... inN
// return the Ugen with the b-format channels and with the args from the *ar method
case
{order == 1}
	{ var in1, in2, in3, in4;
	#in1,in2, in3, in4 = in;
	^FaustPierre1.ar(in1, in2, in3, in4, gain, lf_hf, mute, xover)}

{order == 2}
	{ var in1, in2, in3, in4, in5, in6, in7, in8, in9;
	#in1, in2, in3, in4, in5, in6, in7, in8, in9 = in;
	^FaustPierre2.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, gain, lf_hf, mute, xover)}

{order == 3}
	{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16;
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16 = in;
	^FaustPierre3.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, gain, lf_hf, mute, xover)}

{order == 4}
	{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25;
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25 = in;
	^FaustPierre4.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, gain, lf_hf, mute, xover)}

{order == 5}
	{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36;
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36 = in;
	^FaustPierre5.ar(in1,in2, in3, in4,in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, gain, lf_hf, mute, xover)}

{order == 7}
	{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64;
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64 = in;
	^Pierre7.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64, gain, lf_hf, mute, xover)}

 }



}