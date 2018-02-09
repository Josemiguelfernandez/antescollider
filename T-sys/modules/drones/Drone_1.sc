Drone_1 : Tmodule2 {

	*def
	{

	^SynthDef(\drone_1, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, f1 = 17, f2 = 217, f3 = 67, f4 = 55, f5 = 300, sin1 = 300, sin2 = 3257.7, sin3 = 1257.7, deltime = 1, decaytime = 10, deltime1 = 0.01, deltime2 = 1, decaytime2 = 20, revtime = 10, room1 = 1.7, room2 = 1.8, hpf = 55, amp = -20, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross;
			var time = 60*7;
			var hex = {|f| 1 - LFTri.ar(f)};
			// var line = {|s,e| Line.kr(s,e,time,1,0)};
			var hexes = hex.(f1,1647) * hex.(f2) * hex.(f3,17) * hex.([f4,f4+0.1]) * 0.05;
			var verb1 = hexes * SinOsc.ar(sin1) * SinOsc.ar(0.01);
			var verb2 = hexes * SinOsc.ar(SinOsc.ar(0.0334).range(100, 157.7)) * SinOsc.ar(0.008);
			var verb3 = hexes * SinOsc.ar(SinOsc.ar(0.0234).range(200, 257.7)) * SinOsc.ar(0.0043);
			var verb4 = (hexes * 20 * SinOsc.ar(sin2)).clip(-1,1) * SinOsc.ar(sin3) * SinOsc.ar(0.023) / 30;

			///////////module
			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;
			///////////module

			sig = verb1 + verb2 + verb3 + CombC.ar(verb4 * SinOsc.ar(f5), 10, deltime, decaytime, 1, verb4);
			// sig = sig * EnvGen.ar(Env.linen(0.01, time - 15, 13));
			sig = sig + DelayC.ar(GVerb.ar(sig, [room1, room2], revtime, drylevel: 0), 10, deltime1, 0.5) * 0.1;
			sig = sig + LPF.ar(CombC.ar(sig, 20, deltime2, decaytime2), LFTri.ar([0.0312, 0.0222]).range(50, 10000)) * 0.1;
			sig = Compander.ar(sig, sig, 0.5, 1, 1/20) * 8;
			sig = Compander.ar(sig, sig, 0.5, 1, 1/20) / 2.5;
			sig = HPF.ar(sig, hpf);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}


	*metadata
	{
		^(metadata: (
			synthdefname: "drone_1",
			type: \gen,
			main: \f1,
			sliders: [
				\f1 -> ControlSpec(1, 2000, \lin, 0.1, 17,\f1),
				\f2 -> ControlSpec(1.0, 2000, \lin, 0.1, 217,\f2),
				\f3 -> ControlSpec(1.0, 2000, \lin, 0.1, 67,\f3),
				\f4 -> ControlSpec(1.0, 2000, \lin, 0.1, 55,\f4),
				\f5 -> ControlSpec(1.0, 2000, \lin, 0.1, 300,\f5),
				\sin1 -> ControlSpec(20, 18000, \exp, 0.1, 300, \sin1),
				\sin2 -> ControlSpec(20, 18000, \exp, 0.1, 3257.7, \sin2),
				\sin3 -> ControlSpec(20, 18000, \exp, 0.1, 1257.7, \sin3),
				\deltime -> ControlSpec(0.0, 20, \lin, 0.001, 1,\deltime),
				\decaytime -> ControlSpec(0.0, 100, \lin, 0.001, 10,\decaytime),
				\deltime1 -> ControlSpec(0.0, 20, \lin, 0.001, 0.01,\deltime1),
				\deltime2 -> ControlSpec(0.0, 20, \lin, 0.001, 1,\deltime2),
				\decaytime2 -> ControlSpec(0.0, 100, \lin, 0.001, 20,\decaytime2),
				\revtime -> ControlSpec(0.0, 100, \lin, 0.001, 10,\revtime),
				\room1 -> ControlSpec(0.0, 100, \lin, 0.001, 10,\room1),
				\room2 -> ControlSpec(0.0, 100, \lin, 0.001, 10,\room2),
				\hpf -> ControlSpec(20, 18000, \exp, 0.1, 55, \hpf),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
		))

	}

}

/*(
{
	var time = 60*7;
	var hex = {|f| 1 - LFTri.ar(f)};
	var line = {|s,e| Line.kr(s,e,time,1,0)};
	var hexes = hex.(line.(17,1647)) * hex.(line.(217,17)) * hex.(67) * hex.([55,55.1]) * 0.05;
	var verb1 = hexes * SinOsc.ar(300) * SinOsc.ar(0.01);
	var verb2 = hexes * SinOsc.ar(SinOsc.ar(0.0334).range(100, 157.7)) * SinOsc.ar(0.008);
	var verb3 = hexes * SinOsc.ar(SinOsc.ar(0.0234).range(200, 257.7)) * SinOsc.ar(0.0043);
	var verb4 = (hexes * 20 * SinOsc.ar(3257.7)).clip(-1,1) * SinOsc.ar(1257.7) * SinOsc.ar(0.023) / 30;
	var combine = verb1 + verb2 + verb3 + CombC.ar(verb4 * SinOsc.ar(Line.ar(300, 10000, 60*7)), 10, 1, 10, 1, verb4);
	combine = combine * EnvGen.ar(Env.linen(0.01, time - 15, 13));
	combine = combine + DelayC.ar(GVerb.ar(combine, [1.7, 1.8], 10, drylevel: 0), 10, Line.kr(0.01, 1, time), 0.5) * 0.1;
	combine = combine + LPF.ar(CombC.ar(combine, 20, Line.kr(1, 0.01, time), 20), LFTri.ar([0.0312, 0.0222]).range(50, 10000)) * 0.1;
	combine = Compander.ar(combine, combine, 0.5, 1, 1/20) * 8;
	combine = Compander.ar(combine, combine, 0.5, 1, 1/20) / 2.5;
	HPF.ar(combine, 55);
}.play
)*/