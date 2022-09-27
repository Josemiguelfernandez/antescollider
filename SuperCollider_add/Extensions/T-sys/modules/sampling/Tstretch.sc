Tstretch : Tmodule2 {

	*def
	{


		^SynthDef(\Tstretch, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, stretch=1,q = 6, lvl=0.2, amp = 0, winsize = 0.1, buf, startPos = 0, dur = 3000, loop = 0, ipol=2, loopRel=0, matrix_ramp = 0.01, gate = 0, free = 1|
			var sig, envgate, envpause, cross, freq,band,hilb,hilb1,re_im,re_im1,a,b,c,d,x,y,frq,am,divisor;

			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);
			cross = xFade * envgate * envpause;

			sig = LoopBuf.ar(1, buf, BufRateScale.kr(buf) * stretch.reciprocal, gate+loopRel, BufFrames.kr(buf) * startPos, startPos, startPos+dur, ipol);

			/*sig = In.ar(in, 1) + (In.ar(inbus, 1) * ingain);
			sig = PitchShift.ar(sig, winsize, stretch.reciprocal)*amp.dbamp.lag;*/

			sig = Mix.fill(59, { |i|
				freq  = 20 * (2**(i*2/12));
				band = BPF.ar(sig,freq,q.reciprocal);  // bandfilter

				re_im = Hilbert.ar(band);  // = [real,imag]
				re_im1 = Delay1.ar(re_im); //   = 1 sample-delay of [real,imag]

				// prepare terms
				a = re_im[0]; b = re_im[1]; c = re_im1[0]; d = re_im1[1];

				// complex division.
				divisor = (c*c) + (d*d);
				x = (a*c) + (b*d)  / divisor;
				y = (b*c) - (a * d) / divisor;

				// get instaneous frequency in hz
				frq = atan2(y,x) / pi * SampleRate.ir * 0.5 * stretch;

				// get instaneous amplitude in hz.
				am = sqrt((c*c) + (d*d));

				// modulate sine function with the instaneous frequency and amplitude
				SinOsc.ar(frq, mul: am);

			}) ! 2 * lvl;
			sig = sig * envgate * envpause * amp.dbamp.lag;

			XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig * outgain); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "Tstretch",
			type: \gen,
			main: \stretch,
			sliders: [
				\stretch -> ControlSpec(0.001, 20,\lin,0.001,1,\stretch),
				\q -> ControlSpec(0.1, 20,\lin,0.1,6,\q),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB)
			],
			soundfileview: [
				\playbufview -> "soundview1"
			],
			buttons: [
				\play -> [[["Play", Color.black, Color.green(0.7)], ["Stop", Color.white, Color.red(0.7)]], \gate, 1, \gate, 0]
				]
		))

	}

}
