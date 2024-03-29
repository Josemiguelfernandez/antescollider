HOADecESPRO{

classvar <>speakerPositions;
classvar <>sweeterPositions;
classvar <>speakerLabels;
classvar <>sweetSpot;

*initClass {
speakerPositions = [
					[ -4.837538, 11.98531, 1.083555 ],  //ch01
					[ -2.53504, 11.949188, 1.083551 ],  //ch02
					[ 0.004872, 12.035028, 1.052929 ],  //ch03
					[ 2.544717, 11.949184, 1.083551 ],  //ch04
					[ 4.847216, 11.985294, 1.083553 ],  //ch05
					[ 7.742575, 9.221455, 1.249182 ],  //ch06
					[ 7.797584, 6.538892, 0.883554 ],  //ch07
					[ 7.767122, 3.955071, 0.883555 ],  //ch08
					[ 7.790725, 1.233163, 0.883553 ],  //ch09
					[ 7.772947, -1.369727, 0.883553 ],  //ch10
					[ 7.779188, -3.9613, 0.883554 ],  //ch11
					[ 7.812486, -6.551271, 0.883551 ],  //ch12
					[ 7.748472, -9.228378, 1.054236 ],  //ch13
					[ 4.85766, -12.011055, 1.083554 ],  //ch14
					[ 2.341351, -12.020557, 1.083551 ],  //ch15
					[ 0.004816, -12.0, 1.083558 ],  //ch16
					[ -2.331773, -12.020577, 1.083555 ],  //ch17
					[ -4.847919, -12.011094, 1.083557 ],  //ch18
					[ -7.738702, -9.228515, 1.053695 ],  //ch19
					[ -7.802678, -6.551404, 0.883551 ],  //ch20
					[ -7.769559, -3.96118, 0.883555 ],  //ch21
					[ -7.763263, -1.369711, 0.883554 ],  //ch22
					[ -7.781037, 1.233161, 0.883555 ],  //ch23
					[ -7.757418, 3.955075, 0.883554 ],  //ch24
					[ -7.787888, 6.538892, 0.883553 ],  //ch25
					[ -7.724157, 9.211069, 1.051708 ],  //ch26
					[ -4.837523, 11.985271, 3.323554 ],  //ch27
					[ 0.004846, 11.97, 3.323574 ],  //ch28
					[ 4.847202, 11.98526, 3.323545 ],  //ch29
					[ 7.726291, 7.2, 3.32356 ],  //ch30
					[ 7.766874, 1.93529, 3.323551 ],  //ch31
					[ 7.803126, -4.502414, 3.323546 ],  //ch32
					[ 7.707677, -9.859346, 3.323561 ],  //ch33
					[ 4.852248, -11.997673, 3.398822 ],  //ch34
					[ 0.004816, -12.0, 3.323578 ],  //ch35
					[ -4.847897, -12.011038, 3.323539 ],  //ch36
					[ -7.698133, -9.859189, 3.323557 ],  //ch37
					[ -7.793501, -4.502301, 3.323555 ],  //ch38
					[ -7.757175, 1.935289, 3.323554 ],  //ch39
					[ -7.716577, 7.2, 3.323564 ],  //ch40
					[ -2.535041, 11.949192, 5.763562 ],  //ch41
					[ 2.544716, 11.94918, 5.763541 ],  //ch42
					[ 7.863849, 9.705051, 5.763562 ],  //ch43
					[ 7.79, 4.494818, 5.763541 ],  //ch44
					[ 7.773754, -1.937061, 5.763562 ],  //ch45
					[ 7.741205, -7.214243, 5.763584 ],  //ch46
					[ 2.341346, -12.020532, 5.763543 ],  //ch47
					[ -2.331774, -12.02058, 5.763567 ],  //ch48
					[ -7.731504, -7.214288, 5.76359 ],  //ch49
					[ -7.764091, -1.936957, 5.763562 ],  //ch50
					[ -7.780451, 4.494834, 5.763576 ],  //ch51
					[ -7.854174, 9.705088, 5.763568 ],  //ch52
					[ -4.943107, 9.710903, 8.234879 ],  //ch53
					[ 0.004846, 9.772496, 8.198559 ],  //ch54
					[ 4.964826, 9.73, 8.198535 ],  //ch55
					[ 4.864838, 4.53, 8.198561 ],  //ch56
					[ 4.884841, 0.0, 8.198592 ],  //ch57
					[ 4.884787, -4.55, 8.198529 ],  //ch58
					[ 5.174878, -9.84, 8.198554 ],  //ch59
					[ 0.004789, -9.8, 8.198501 ],  //ch60
					[ -4.795136, -9.48, 8.198559 ],  //ch61
					[ -4.875189, -4.55, 8.198586 ],  //ch62
					[ -4.875186, -0.0, 8.198555 ],  //ch63
					[ -4.85514, 4.53, 8.198521 ],  //ch64
					[ -2.455139, 7.14, 8.198506 ],  //ch65
					[ 2.464853, 7.14, 8.198556 ],  //ch66
					[ 2.434852, 1.900003, 8.198563 ],  //ch67
					[ 2.444847, -1.909987, 8.198549 ],  //ch68
					[ 2.474901, -7.16, 8.198603 ],  //ch69
					[ -2.465117, -7.16, 8.198546 ],  //ch70
					[ -2.435175, -1.90997, 8.198577 ],  //ch71
					[ -2.395154, 1.900003, 8.198572 ],  //ch72
					[ 0.004846, 4.51, 8.198545 ],  //ch73
					[ 0.00488, -4.51, 8.198527 ],  //ch74
					[ 0.00485, 0.0, 8.198548 ],  //ch75
					[ -4.846402, 12.007272, -1.399924 ],  //ch76
					[ 0.004846, 12.034346, -1.238426 ],  //ch77
					[ 4.856099, 12.007259, -1.399922 ],  //ch78
					[ 7.787908, 7.257845, -1.30711 ],  //ch79
					[ 7.934295, 1.977036, -1.149189 ],  //ch80
					[ 7.928953, -4.564692, -1.285812 ],  //ch81
					[ 7.720531, -9.875788, -1.539161 ],  //ch82
					[ 4.867186, -12.034609, -1.405129 ],  //ch83
					[ 0.004842, -12.063867, -1.243634 ],  //ch84
					[ -4.857426, -12.034646, -1.405129 ],  //ch85
					[ -7.711004, -9.875673, -1.53843 ],  //ch86
					[ -7.9, -4.56401, -1.282278 ],  //ch87
					[ -7.924716, 1.977059, -1.147883 ],  //ch88
					[ -7.778269, 7.257847, -1.306244 ]  //ch89
					];

sweeterPositions = [
					[ -4.848, 12.115, -2.417 ],  //ch01
					[ -2.545, 12.079, -2.417 ],  //ch02
					[ -0.005, 12.164, -2.448 ],  //ch03
					[ 2.534, 12.079, -2.417 ],  //ch04
					[ 4.837, 12.115, -2.417 ],  //ch05
					[ 7.732, 9.351, -2.252 ],  //ch06
					[ 7.787, 6.668, -2.617 ],  //ch07
					[ 7.757, 4.084, -2.617 ],  //ch08
					[ 7.781, 1.363, -2.617 ],  //ch09
					[ 7.763, -1.24, -2.617 ],  //ch10
					[ 7.769, -3.832, -2.617 ],  //ch11
					[ 7.802, -6.422, -2.617 ],  //ch12
					[ 7.738, -9.099, -2.447 ],  //ch13
					[ 4.847, -11.882, -2.417 ],  //ch14
					[ 2.331, -11.891, -2.417 ],  //ch15
					[ -0.005, -11.871, -2.417 ],  //ch16
					[ -2.342, -11.891, -2.417 ],  //ch17
					[ -4.858, -11.882, -2.417 ],  //ch18
					[ -7.749, -9.099, -2.447 ],  //ch19
					[ -7.813, -6.422, -2.617 ],  //ch20
					[ -7.78, -3.832, -2.617 ],  //ch21
					[ -7.773, -1.24, -2.617 ],  //ch22
					[ -7.791, 1.363, -2.617 ],  //ch23
					[ -7.768, 4.084, -2.617 ],  //ch24
					[ -7.798, 6.668, -2.617 ],  //ch25
					[ -7.734, 9.34, -2.449 ],  //ch26
					[ -4.848, 12.115, -0.177 ],  //ch27
					[ -0.005, 12.099, -0.177 ],  //ch28
					[ 4.837, 12.115, -0.177 ],  //ch29
					[ 7.716, 7.329, -0.177 ],  //ch30
					[ 7.757, 2.065, -0.177 ],  //ch31
					[ 7.793, -4.373, -0.177 ],  //ch32
					[ 7.697, -9.73, -0.177 ],  //ch33
					[ 4.842, -11.868, -0.102 ],  //ch34
					[ -0.005, -11.871, -0.177 ],  //ch35
					[ -4.858, -11.882, -0.177 ],  //ch36
					[ -7.708, -9.73, -0.177 ],  //ch37
					[ -7.804, -4.373, -0.177 ],  //ch38
					[ -7.767, 2.065, -0.177 ],  //ch39
					[ -7.727, 7.329, -0.177 ],  //ch40
					[ -2.545, 12.079, 2.263 ],  //ch41
					[ 2.534, 12.079, 2.263 ],  //ch42
					[ 7.854, 9.834, 2.263 ],  //ch43
					[ 7.78, 4.624, 2.263 ],  //ch44
					[ 7.764, -1.808, 2.263 ],  //ch45
					[ 7.731, -7.085, 2.263 ],  //ch46
					[ 2.331, -11.891, 2.263 ],  //ch47
					[ -2.342, -11.891, 2.263 ],  //ch48
					[ -7.742, -7.085, 2.263 ],  //ch49
					[ -7.774, -1.808, 2.263 ],  //ch50
					[ -7.791, 4.624, 2.263 ],  //ch51
					[ -7.864, 9.834, 2.263 ],  //ch52
					[ -4.953, 9.84, 4.734 ],  //ch53
					[ -0.005, 9.902, 4.698 ],  //ch54
					[ 4.955, 9.859, 4.698 ],  //ch55
					[ 4.855, 4.659, 4.698 ],  //ch56
					[ 4.875, 0.129, 4.698 ],  //ch57
					[ 4.875, -4.421, 4.698 ],  //ch58
					[ 5.165, -9.711, 4.698 ],  //ch59
					[ -0.005, -9.671, 4.698 ],  //ch60
					[ -4.805, -9.351, 4.698 ],  //ch61
					[ -4.885, -4.421, 4.698 ],  //ch62
					[ -4.885, 0.129, 4.698 ],  //ch63
					[ -4.865, 4.659, 4.698 ],  //ch64
					[ -2.465, 7.269, 4.698 ],  //ch65
					[ 2.455, 7.269, 4.698 ],  //ch66
					[ 2.425, 2.029, 4.698 ],  //ch67
					[ 2.435, -1.781, 4.698 ],  //ch68
					[ 2.465, -7.031, 4.698 ],  //ch69
					[ -2.475, -7.031, 4.698 ],  //ch70
					[ -2.445, -1.781, 4.698 ],  //ch71
					[ -2.405, 2.029, 4.698 ],  //ch72
					[ -0.005, 4.639, 4.698 ],  //ch73
					[ -0.005, -4.381, 4.698 ],  //ch74
					[ -0.005, 0.129, 4.698 ],  //ch75
					[ -4.857, 12.137, -4.901 ],  //ch76
					[ -0.005, 12.164, -4.739 ],  //ch77
					[ 4.846, 12.137, -4.901 ],  //ch78
					[ 7.778, 7.387, -4.808 ],  //ch79
					[ 7.924, 2.106, -4.65 ],  //ch80
					[ 7.919, -4.435, -4.787 ],  //ch81
					[ 7.71, -9.746, -5.04 ],  //ch82
					[ 4.857, -11.905, -4.906 ],  //ch83
					[ -0.005, -11.934, -4.745 ],  //ch84
					[ -4.868, -11.905, -4.906 ],  //ch85
					[ -7.721, -9.746, -5.039 ],  //ch86
					[ -7.91, -4.435, -4.783 ],  //ch87
					[ -7.935, 2.106, -4.649 ],  //ch88
					[ -7.788, 7.387, -4.807 ]  //ch89
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
					"ch26",
					"ch27",
					"ch28",
					"ch29",
					"ch30",
					"ch31",
					"ch32",
					"ch33",
					"ch34",
					"ch35",
					"ch36",
					"ch37",
					"ch38",
					"ch39",
					"ch40",
					"ch41",
					"ch42",
					"ch43",
					"ch44",
					"ch45",
					"ch46",
					"ch47",
					"ch48",
					"ch49",
					"ch50",
					"ch51",
					"ch52",
					"ch53",
					"ch54",
					"ch55",
					"ch56",
					"ch57",
					"ch58",
					"ch59",
					"ch60",
					"ch61",
					"ch62",
					"ch63",
					"ch64",
					"ch65",
					"ch66",
					"ch67",
					"ch68",
					"ch69",
					"ch70",
					"ch71",
					"ch72",
					"ch73",
					"ch74",
					"ch75",
					"ch76",
					"ch77",
					"ch78",
					"ch79",
					"ch80",
					"ch81",
					"ch82",
					"ch83",
					"ch84",
					"ch85",
					"ch86",
					"ch87",
					"ch88",
					"ch89"
					];

sweetSpot = [ 0.009, -0.13, 3.5 ];

}

*ar { |order, in, gain(-10.0), lf_hf(0.0), mute(0.0), xover(400.0)|
// declare variables for the b-format array in
// distribute the channels from the array over in1 ... inN
// return the Ugen with the b-format channels and with the args from the *ar method
case
{order == 1}
	{ var in1, in2, in3, in4;
	#in1,in2, in3, in4 = in;
	^ESPRO1.ar(in1, in2, in3, in4, gain, lf_hf, mute, xover)}

{order == 2}
	{ var in1, in2, in3, in4, in5, in6, in7, in8, in9;
	#in1, in2, in3, in4, in5, in6, in7, in8, in9 = in;
	^ESPRO2.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, gain, lf_hf, mute, xover)}

{order == 3}
	{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16;
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16 = in;
	^ESPRO3.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, gain, lf_hf, mute, xover)}

{order == 4}
	{ var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25;
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25 = in;
	^ESPRO4.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, gain, lf_hf, mute, xover)}

{order == 5}
	{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36;
	#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36 = in;
	^ESPRO5.ar(in1,in2, in3, in4,in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, gain, lf_hf, mute, xover)}

	{order == 6}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49  = in;
			^ESPRO6.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, gain, lf_hf, mute, xover)}

		{order == 7}
		{var in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64;
			#in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64 = in;
			^ESPRO7.ar(in1, in2, in3, in4, in5, in6, in7, in8, in9, in10, in11, in12, in13, in14, in15, in16, in17, in18, in19, in20, in21, in22, in23, in24, in25, in26, in27, in28, in29, in30, in31, in32, in33, in34, in35, in36, in37, in38, in39, in40, in41, in42, in43, in44, in45, in46, in47, in48, in49, in50, in51, in52, in53, in54, in55, in56, in57, in58, in59, in60, in61, in62, in63, in64, gain, lf_hf, mute, xover)}
 }



}