HOA_Global_Rot_xyz_ord3 : Tmodule2  {

	*def
	{
		^SynthDef(\HOA_Global_Rot_xyz_ord3, {|in, out, gatee = 1, amp = 0, lag = 0.2, matrix_ramp = 0.05,  in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1,  pitch = 0, roll = 0, yaw = 0|
			var sig, envgate, envpause;

			sig = In.ar(in, 16);

			sig = HOARotator3.ar( // return the Ugen with the b-format channels
				sig[ 0], sig[ 1], sig[ 2], sig[ 3],
				sig[ 4], sig[ 5], sig[ 6], sig[ 7],
				sig[ 8], sig[ 9], sig[10], sig[11],
				sig[12], sig[13], sig[14], sig[15],
				pitch, roll, yaw); // and with the args from the *ar method

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			Out.ar(out, sig*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14)); //salida directa a un bus
		}).load;

	}

	*metadata
	{
		/*		var buffnum;
		buffnum = ~wave_buff[\vbap].bufnum; // VBAP Speaker conf.*/
		^(metadata: (
			synthdefname: "HOA_Global_Rot_xyz_ord3",
			type: \fx,
			main: \azi,
			sliders:[
				\freeroom -> ControlSpec(0.0, 1.0,\lin, 0.001,0.5,\freeroom),
				\freedamp -> ControlSpec(0, 1,\lin, 0.001,0.5,\freedamp),
				\freemul -> ControlSpec(0, 1,\lin, 0.001, 1.0,\freemul),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)

			]
		))

	}
}
