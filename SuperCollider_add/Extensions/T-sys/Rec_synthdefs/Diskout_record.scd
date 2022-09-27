

// this will record channels to the disk

SynthDef("Diskout_1", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 1);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_2", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 2);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_3", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 3);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_4", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 4);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_5", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 5);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_6", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 6);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_7", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 7);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_8", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 8);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_9", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 9);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_10", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 10);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_11", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 11);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_12", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 12);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_13", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 13);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_14", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 14);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_15", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 15);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_16", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 16);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_17", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 17);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_18", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 18);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_19", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 19);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_20", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 20);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_21", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 21);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_22", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 22);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_23", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 23);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_24", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 24);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_25", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 25);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_26", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 26);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_27", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 27);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_28", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 28);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_29", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 29);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_30", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 30);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_31", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 31);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_32", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 32);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_33", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 33);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_34", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 34);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_35", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 35);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_36", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 36);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_37", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 37);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_38", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 38);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_39", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 39);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_40", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 40);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_41", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 41);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_42", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 42);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_43", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 43);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_44", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 44);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_45", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 45);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_46", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 46);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_47", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 47);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_48", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 48);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_49", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 49);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

SynthDef("Diskout_50", { |in, bufnum, in_ramp = 0.01, out_ramp = 0.01,  free = 1|
	var sig;

	sig =  In.ar(in, 50);
	sig = sig*EnvGen.ar(Env.asr(in_ramp, 1.0, out_ramp, \welch ), free, doneAction:2);

	DiskOut.ar(bufnum, sig);
}).load;

