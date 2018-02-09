TShuffler : Tmodule2 { // based on vdb MaxMSP abstraction

	*def
	{


		^SynthDef(\tshuffler, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = -120, xFade = 1, del= 0, wind_steep = 0.0005, buf_time = 10, fbk = 0, env = 43, dur = 1, prob = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|
			var sig, envgate, envpause, cross, tapPhase, tap1, tap2, phasor1, fenetre1, fenetre2, del_dyn1, del_dyn2, del_ctr, buf, sr, trig;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = sig + (LocalIn.ar(1) * fbk);
			sr = SampleRate.ir;
			buf = LocalBuf((buf_time * sr).ceil, 1).clear;
			trig = CoinGate.kr(prob, Impulse.kr(dur.reciprocal)); // cps to dur
			tapPhase = DelTapWr.ar(buf, sig);
			phasor1 = Phasor.ar(0, wind_steep, 0, 2pi, 0);
			del_ctr = K2A.ar(del);
			fenetre1 = sin(phasor1*0.5);
			fenetre2 = sin(phasor1+pi*0.5).abs;
			del_dyn1 = Latch.ar(del_ctr, fenetre1-0.0001);
			del_dyn2 = Latch.ar(del_ctr, fenetre2-0.0001);
			tap1 = DelTapRd.ar(buf, tapPhase, del_dyn1, 2) * fenetre1;
			tap2 = DelTapRd.ar(buf, tapPhase, del_dyn2, 2) * fenetre2;
			sig =  (tap1+tap2);
			sig = LeakDC.ar(sig, 0.995);
			LocalOut.ar(sig);
			// PlayBuf.ar(1, env, 1/((SampleRate.ir/BufSamples.kr(env)) * dur), trig, loop: 1).poll;
			sig = sig*PlayBuf.ar(1, env, 1/((SampleRate.ir/BufSamples.kr(env)) * dur), trig, loop: 0);

			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
		}).load;

	}


	*metadata
	{
		^(metadata: (
			synthdefname: "tshuffler",
			type: \fx,
			main: \del,
			sliders: [
				\del -> ControlSpec(0.0, 10.0, \lin, 0.001, 0, \del),
				\wind_steep -> ControlSpec(0.0001, 0.001, \lin, 0.0001, 0.0005, \wind),
				\fbk -> ControlSpec(0.0, 1, \lin, 0.001, 0, \fbk),
				\env -> ControlSpec(1, 100, \lin, 1, 0, \env),
				\dur -> ControlSpec(0.001, 10, \lin, 0.001, 1, \dur),
				\prob -> ControlSpec(0, 1, \lin, 0.001, 1, \prob),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}













	