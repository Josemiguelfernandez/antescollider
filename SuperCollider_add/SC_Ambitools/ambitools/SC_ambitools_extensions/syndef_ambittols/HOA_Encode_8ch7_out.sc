HOA_Encode_8ch7_out : Tmodule2 {

	*def
	{
		^SynthDef(\HOA_Encode_8ch7_out, {|in, out = 0, amp = 0, gatee = 1, in_ramp = 0.01, out_ramp = 0.01, xoffset = 0, yoffset = 0, zoffset = 0,  dopon = 0, dopamnt = 0, globTBus|
			var sig;
			var gain, coords, n=8;

			gain = \gain.kr(([0.0] ! n).flat);
			coords = \coords.ar(([1.0, 0.0, 0.0] ! n).flat, 0.1);

			sig = In.ar(in, n);
			sig = AmbisonicEncoder_in8.ar(7, sig, gain, coords);

			sig = sig*EnvGen.ar(Env.asr(in_ramp, 1, out_ramp, \welch ), gatee, doneAction: 14);

			Out.ar(globTBus, sig*amp.dbamp.lag);

		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "HOA_Encode_8ch7_out",
			type: \fx,
			main: \x,
			sliders: [
				\x -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\x),
				\y -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\y),
				\z -> ControlSpec(-1.0, 1.0,\lin,0.001, 0,\z),
				\dopon -> ControlSpec(0, 1,\lin,1, 0,\dopon),
				\dopamnt -> ControlSpec(0.0, 1.0,\lin,0.001, 0,\dopamnt),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],

		))
	}
}

/*			sig = In.ar(in, 1);
			/*sig = sig * amp.dbamp.lag(1);*/

			// sig.poll;
			// sig = LPF.ar(sig, (dis) * 18000 + 2000); // attenuate high freq with distance
			// Doppler
			// rd = (1 - dis) * 340;
			rd = distance * 340;
			rd = Lag.kr(rd, 1.0);
			// dopplershift= DelayC.ar(sig, 0.2, rd/1640.0 * dopon * dopamnt);
			sig = Select.ar(dopon, [sig, DelayC.ar(sig, 0.2, rd/1640.0 * dopamnt)]);*/


