TShuffler8 : Tmodule2 { // based on vdb MaxMSP abstraction

	classvar n = 8; // numero de delays

	*def
	{


		SynthDef(\tshuffler8, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = -120, xFade = 1, wind_steep = 0.0005, buf_time = 10, lag_dels = 0, lag_amps = 0, env = 43, width = 2, amp = 0,  matrix_ramp = 0.01, gate = 1, free = 1|
			var envgate, envpause, cross, dels, amps, fbk, dur, prob, pos, src, phasor1, fenetre1, fenetre2, del_dyn1, del_dyn2, sig, input, del_ctr, buf, sr, fed, local;

			var taps1 = Array.fill(n);
			var taps2 = Array.fill(n);
			var tap = Array.fill(n);
			var tapPhase = Array.fill(n);
			// var prob = Array.fill(n);
			var trig = Array.fill(n);
			var pbuf = Array.fill(n);

			dels = \dels.kr(Array.fill(n, 0));
			amps = \amps.kr(Array.fill(n, 1.0), lag_amps);
			fbk = \fbk.kr(Array.fill(n, 0));
			dur = \dur.kr(Array.fill(n, 0.1));
			prob = \prob.kr(Array.fill(n, 0));
			pos = \pos.kr(Array.series(n, 0)*(2/n));


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			fed = LeakDC.ar(LocalIn.ar(n));

			// sig = LeakDC.ar(sig);
			sr = SampleRate.ir;
			//buf = LocalBuf((buf_time * sr).ceil, n).clear;
			buf = Array.fill(n, {LocalBuf((buf_time * sr).ceil, 1).clear});

			n.do({|i|
				trig[i] = CoinGate.kr(prob[i], Impulse.kr(dur[i].reciprocal)); // cps to dur
			});

			// trig = CoinGate.kr(prob, Impulse.kr(dur.reciprocal)); // cps to dur

			input = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			n.do({|i|
				tapPhase[i] = DelTapWr.ar(buf[i], input + fed[i])
			});
			n.do({|i|
				pbuf[i] = PlayBuf.ar(1, env, 1/((SampleRate.ir/BufSamples.kr(env)) * dur[i]), trig[i])
			});
			// pbuf.poll;



			/*trigtest = CoinGate.kr(prob[0], Impulse.kr(dur[0].reciprocal));
			ptest = PlayBuf.ar(1, env, 1/((SampleRate.ir/BufSamples.kr(env)) * dur[0]), trigtest);

			ptest.poll;
			trigtest.poll;
			dur[0].reciprocal.poll;
			prob[0].poll;*/
			/*			dur[0].poll;
			trig.poll;
			prob[0].poll;*/
			//tapPhase = DelTapWr.ar(buf, src + fed);
			phasor1 = Phasor.ar(0, wind_steep, 0, 2pi, 0);
			//fed.postln;
			del_ctr = K2A.ar(dels); //Lag.ar(K2A.ar(dels), lag_dels);
			fenetre1 = sin(phasor1*0.5);
			fenetre2 = sin(phasor1+pi*0.5).abs;
			del_dyn1 = Latch.ar(del_ctr, fenetre1-0.0001);
			del_dyn2 = Latch.ar(del_ctr, fenetre2-0.0001);

			n.do({|i|
				taps1[i] = DelTapRd.ar(buf[i], tapPhase[i], del_dyn1[i], 2, amps[i].dbamp) * fenetre1;
			});
			n.do({|i|
				taps2[i] = DelTapRd.ar(buf[i], tapPhase[i], del_dyn2[i], 2, amps[i].dbamp) * fenetre2;
			});

			// taps1.poll;

			/*			sig = Mix.fill(n, {|i|
			PanAz.ar(8, ((taps1[i] + taps2[i]) * pbuf[i]), pos[i], width: width)
			});*/

			/*n.do({|i|
				tap[i] = PanAz.ar(8, (taps1[i] + taps2[i]) * pbuf[i], pos[i], width: width)
			});*/

			n.do({|i|
				tap[i] = (taps1[i] + taps2[i]) * pbuf[i];
			});

			/*pbuf.poll;
			env.poll;*/
			// trig.poll;

			sig = (tap * fbk);
			LocalOut.ar(sig);

			sig = tap * envgate * envpause * amp.dbamp.lag;


			// Out.ar(out, tap * amp.dbamp)

			XOut.ar(out, cross, sig); //tap * amp.dbamp); //sig); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp);

		}).load;
	}

	// TShuffler8.def

	*metadata
	{
		^(metadata: (
			synthdefname: "tshuffler8",
			type: \fx,
			main: \amp,
			sliders: [
				\wind_steep -> ControlSpec(0.0001, 0.001, \lin, 0.0001, 0.0005, \wind),
				\env -> ControlSpec(1, 100, \lin, 1, 0, \env),
				\width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			multisliders: [
				\dels -> [ControlSpec(0.0, 10.0, \lin, 0.001, 0, \dels), n],
				\amps -> [ControlSpec(-60, 10, \amp, 0.01, 0, \amps), n],
				\fbk -> [ControlSpec(0.0001, 1, \lin, 0.001, 0.0, \fbk), n],
				\dur -> [ControlSpec(0.001, 5, \lin, 0.001, 1, \dur), n],
				\prob -> [ControlSpec(0, 1, \lin, 0.001, 1, \prob), n],
				// \pos -> [ControlSpec(0, 2, \lin, 0.001, 0, \pos), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\dels -> [\multisliders, \dels,{ rrand(0.0, 2.0) }, n],
				\amps -> [\multisliders, \amps,{ rrand(-10, 2) }, n],
				\fbk -> [\multisliders, \fbk, { rrand(0.0, 1.0) }, n],
				\dur -> [\multisliders, \dur, { rrand(0.0, 1.0) }, n],
				\prob -> [\multisliders, \prob, { rrand(0.0, 1.0) }, n],
				// \pos -> [\multisliders, \pos, Array.series(n, 0)*(2/n)],
				\env -> [\menu, \env, { ~envs[\envitems.item].plot(minval: 0, maxval: 1)}]
			],
			menu: [
				\envitems -> [\envbuf, [-1,0,1, 2, 3, 4, 5, 6, 7]],

			]
		))

	}

}