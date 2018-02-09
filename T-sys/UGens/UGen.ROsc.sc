/* based on rachmiel's xi-s oscilators*/ 
ROsc {
	*ar {
		arg p = 40, md1 = -70, md2 = -50, ed = 0.5, fb = 1, p2J = 24, p3J = 25;
		var sig, lcl, osc1, osc2, osc3;
		md1   = md1 + LFNoise1.kr(0.1, p2J);
		md2   = md2 + LFNoise1.kr(0.1 * 0.8, p3J);
		lcl  = LocalIn.ar(1);
		osc1 = SinOsc.ar(p.midicps, 0, lcl + ed);
		osc2 = SinOsc.ar(md1.midicps, 0, osc1);
		osc3 = SinOsc.ar(md2.midicps, 0, osc2);
		LocalOut.ar(osc3);
		//sig = [osc1 + osc2, osc1 + osc3];
		sig = osc1;
		^sig;
	}
}