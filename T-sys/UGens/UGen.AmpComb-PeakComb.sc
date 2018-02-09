AmpComb {
	/*@ comb filtered amplitude follower, (rms based) @*/
	*ar {|sig, sm = 0.01, dec = 0.1, pow = 1, att = 0.1|
		var env;
		env = AmplitudeMod.ar(sig, att, dec);
		env = env.max(0.0001).pow(pow);//.sqrt;
		env = CombL.ar(env, 0.1, sm, dec);
		^env;
	}
	
	*kr {|sig, sm = 0.01, dec = 0.1, pow = 1, att = 0.1|
		var env;
		env = AmplitudeMod.kr(sig, att, dec);
		env = env.max(0.0001).pow(pow);//.sqrt;
		env = CombL.kr(env, 0.1, sm, dec);
		^env;
	}
}

AmpAllpass {
	/*@ comb filtered amplitude follower, (rms based) @*/
	*ar {|sig, sm = 0.01, dec = 0.1, pow = 1, att = 0.1|
		var env;
		env = AmplitudeMod.ar(sig, att, dec);
		env = env.max(0.0001).pow(pow);//.sqrt;
		env = AllpassL.ar(env, 0.1, sm, dec);
		^env;
	}
	
	*kr {|sig, sm = 0.01, dec = 0.1, pow = 1, att = 0.1|
		var env;
		env = AmplitudeMod.kr(sig, att, dec);
		env = env.max(0.0001).pow(pow);//.sqrt;
		env = AllpassL.kr(env, 0.1, sm, dec);
		^env;
	}
}

PeakComb {
	*ar {|sig, sm = 0.01, dec = 0.1, pow = 1|
		var env;
		env = PeakFollower.ar(sig, 0.0001);
		env = env.max(0.001).pow(pow);
		env = CombL.ar(env, 0.1, sm, dec);
		env = LPF.ar(env, 12000 * (1-sm).max(0.1));
		^env;
	}
	
	*kr {|sig, sm = 0.01, dec = 0.1, pow = 1|
		var env;
		env = PeakFollower.kr(sig, 0.0001);
		env = env.max(0.001).pow(pow);
		env = CombL.kr(env, 0.1, sm, dec);
		env = LPF.kr(env, 12000 * (1-sm).max(0.1));
		^env;
	}
}