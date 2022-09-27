Noisy : Tmodule2 {

	*def
	{


		^SynthDef(\Noisy, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, freq = 400, fr1 = 5, fr2 = 10, fr3 = 1, amp = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var sig, envgate, envpause, cross, fc, fm, i, x, a;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);


			fc = freq + LFNoise2.ar([1,2]).range(100,200);
			fm = LFNoise0.ar(fr1).range(100,200);
			i = LFNoise1.ar(fr2).range(1,20);
			x = SinOsc.ar( fc + ( SinOsc.ar(fm) * i * fm ) ) * 0.5;
			a = RLPF.ar(x, SinOsc.ar( LFNoise1.ar(fr3).range(0.1,1) ).range(1000,2000), LFNoise1.ar(1).range(0.5,10));
			sig = SinOsc.ar(0.2, a * LFNoise1.ar(0.1).range(1.0,4.0) * 4pi)*0.5;

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(out, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Noisy",
			type: \gen,
			main: \freq,
			sliders: [
				\freq -> ControlSpec(20, 5000, 'exponential', 10, 400, \Hz),
				\fr1 -> ControlSpec(0, 20, \lin, 0, 5, \f1),
				\fr2 -> ControlSpec(0, 20, \lin, 0, 10, \f2),
				\fr3 -> ControlSpec(0, 20, \lin, 0, 1, \f3),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
		))

	}

}

// Noise
/*Synth(\noisy)



(
{
var fc = 400 + LFNoise2.ar([1,2]).range(100,200);
var fm = LFNoise0.ar(5).range(100,200);
i = LFNoise1.ar(10).range(1,20);
x = SinOsc.ar( fc + ( SinOsc.ar(fm) * i * fm ) ) * 0.5;
a = RLPF.ar(x, SinOsc.ar( LFNoise1.ar(1).range(0.1,1) ).range(1000,2000), LFNoise1.ar(1).range(0.5,10) );
SinOsc.ar(0.2, a * LFNoise1.ar(0.1).range(1.0,4.0) * 4pi)*0.5
}.play
)


w = ServerRecordWindow( s );*/