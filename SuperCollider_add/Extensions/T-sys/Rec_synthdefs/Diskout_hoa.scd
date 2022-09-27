

// this will record ambisonic order 3 without decoder to the disk

SynthDef("Diskout_hoa_1", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 4);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_hoa_2", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 9);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_hoa_3", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 16);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_hoa_4", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 25);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_hoa_5", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 36);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;