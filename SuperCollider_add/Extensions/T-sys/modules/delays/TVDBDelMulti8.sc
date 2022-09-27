TVDBDelMulti8 : Tmodule2 { // based on vdb MaxMSP abstraction

	classvar n = 8; // numero de delays

	*def
	{


		SynthDef(\TVDBDelMulti8, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = -120, xFade = 1, wind_steep = 0.0005, buf_time = 10, lag_dels = 0, lag_amps = 0, amp = 0,  matrix_ramp = 0.01, gate = 1, free = 1|
			var envgate, envpause, cross, dels, amps, fbk, pos, src, phasor1, fenetre1, fenetre2, del_dyn1, del_dyn2, sig, input, del_ctr, buf, sr, fed, local;

			var taps1 = Array.fill(n);
			var taps2 = Array.fill(n);
			var tap = Array.fill(n);
			var tapPhase = Array.fill(n);


			dels = \dels.kr(Array.fill(n, 0));
			amps = \amps.kr(Array.fill(n, 1.0), lag_amps);
			fbk = \fbk.kr(Array.fill(n, 0));
			pos = \pos.kr(Array.fill(n, 0));


			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			fed = LeakDC.ar(LocalIn.ar(n));

			// sig = LeakDC.ar(sig);
			sr = SampleRate.ir;
			//buf = LocalBuf((buf_time * sr).ceil, n).clear;
			buf = Array.fill(n, {LocalBuf((buf_time * sr).ceil, 1).clear});

			input = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);

			/*sig = input + (LocalIn.ar(n) * fbk);*/


			n.do({|i|
				tapPhase[i] = DelTapWr.ar(buf[i], input + fed[i])
			});
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

			n.do({|i|
				tap[i] = taps1[i] + taps2[i];
			});
			sig = (tap * fbk);
			LocalOut.ar(sig);

			sig = tap * envgate * envpause * amp.dbamp.lag;

			// Out.ar(out, tap * amp.dbamp)

			XOut.ar(out, cross, tap * amp.dbamp); //sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain.dbamp);

		}).load;
	}

	// TVDBDelMulti8.def

	*metadata
	{
		^(metadata: (
			synthdefname: "TVDBDelMulti8",
			type: \fx,
			main: \amp,
			sliders: [
				\wind_steep -> ControlSpec(0.0001, 0.001, \lin, 0.0001, 0.0005, \wind),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			multisliders: [
				\dels -> [ControlSpec(0.0, 10.0, \lin, 0.001, 0, \dels), n],
				\amps -> [ControlSpec(-60, 10, \amp, 0.01, 0, \amps), n],
				\fbk -> [ControlSpec(0.0001, 1, \lin, 0.001, 0.0, \fbk), n],
				\pos -> [ControlSpec(0, 2, \lin, 0.001, 0, \pos), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\dels -> [\multisliders, \dels,{ rrand(0.0, 2.0) }, n],
				\amps -> [\multisliders, \amps,{ rrand(-10, 2) }, n],
				\fbk -> [\multisliders, \fbk, { rrand(0.0, 1.0) }, n],
				\pos -> [\multisliders, \pos, Array.series(n, 0)*(2/n)]
			]
		))

	}

}