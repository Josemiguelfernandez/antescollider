Pluck_zither2 : Tmodule2 {

	*def
	{


		^SynthDef(\pluck_zither2, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, x = 0, y = 0, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, pitch, triggerSpacing, panSpacing;



			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			// pitch = [ 50, 53.86, 57.02, 59.69, 62, 64.04, 65.86, 67.51, 69.02, 71.69, 72.88, 74 ];
			pitch = \freqs.kr(Array.fill(12, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));

			triggerSpacing = 0.5 / (pitch.size - 1);
			panSpacing = 1.5 / (pitch.size - 1);
			sig = Mix.arFill(pitch.size, { arg i;
				var trigger, pluck, period, string;
				// place trigger points from 0.25 to 0.75
				trigger = HPZ1.kr(x > (0.25 + (i * triggerSpacing))).abs;
				pluck = PinkNoise.ar(Decay.kr(trigger, 0.05));
				period = (pitch.at(i)+y).midicps.reciprocal;
				string = CombL.ar(pluck, period, period, 8);
				Pan2.ar(string, i * panSpacing - 0.75);
				});
				sig = LPF.ar(sig, 12000);
				sig = LeakDC.ar(sig);
				sig = sig * envgate * envpause * amp.dbamp.lag;

				// XOut.ar(out, cross, sig); //salida directa a un bus
				Out.ar(outbus, sig); //salida a un bus auxiliar
			}).load;

			}

			*metadata
			{
				^(metadata: (
					synthdefname: "pluck_zither2",
					type: \gen,
					main: \x,
					sliders: [
						\x -> ControlSpec(0, 1, \lin, 0.001, 0,\x),
						\y -> ControlSpec(0, 1, \lin, 0.001, 0,\y),
						\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
					],
				))

			}

		}




