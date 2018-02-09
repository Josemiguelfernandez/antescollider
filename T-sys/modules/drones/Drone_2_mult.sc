Drone_2_mult : Tmodule2 {

	classvar n = 12; //numero de componentes

	*def
	{

		^SynthDef(\drone_2_mult, {|in = 0, inbus, ingain = 0, out = 0, outbus, outgain = 0, xFade = 1, freq = 233, modfreq = 0.1, damp = 0.5, room = 0.5, amp = -20, transp = 1.0, lag_spat = 0.2, width = 2, orientation = 0, matrix_ramp = 0.01, gate = 1, free = 1|

			var freqs, amps, pos, sig, sig2, envgate, envpause, cross;


			///////////module
			envgate = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.ar(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);
			//cross = Line.ar(0, free, 0.1);¨
			cross = xFade * envgate * envpause;
			///////////module

			freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.fill(n, { 0.02 + 0.07.rand }));
			pos = \pos.kr((Array.series(n, 0)*(2/20)));

			sig = Mix.fill(n, { |i|
				var ampc;
				ampc = AmpCompA.kr(freqs[i]);
				PanAz.ar(8,
					Limiter.ar(FreeVerb.ar(LPF.ar(((Array.fill(3,{|inc| VarSaw.ar(freqs[i].lag*transp.lag*(inc+1.0001),mul:0.05/(inc+1))}).sum +
						Ringz.ar(WhiteNoise.ar(0.0003),TRand.ar(freqs[i].lag*transp.lag, ((freqs[i].lag*transp.lag).cpsmidi+1).midicps,Impulse.ar(10)))) * (0.8+SinOsc.kr(modfreq,0,0.2)) * amps[i].lag),10000),mix:0.33, room:room, damp:damp),0.7), Lag.kr(pos[i], lag_spat), 1, width, orientation) * ampc;
			});

			sig = sig * envgate * envpause * amp.dbamp.lag;

			// XOut.ar(out, cross, sig); //salida directa a un bus
			Out.ar(outbus, sig); //salida a un bus auxiliar
		}).load;

	}

	*metadata
	{
		^(metadata: (
			synthdefname: "drone_2_mult",
			type: \gen,
			main: \transp,
			sliders: [
				\transp -> ControlSpec(0.001, 5, \lin, 0.001, 1, \transp),
				\modfreq -> ControlSpec(0.0, 20, \lin, 0.001, 0.1,\modfreq),
				\damp -> ControlSpec(0.0, 1.0, \lin, 0.001, 0.5,\damp),
				\room -> ControlSpec(0.0, 1.0, \lin, 0.001, 0.5,\room),
				\lag_spat -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag_spat),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -20, \dB)
			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
				\pos -> [ControlSpec(0.0001, 2, \lin, 0, 0.3, \vol), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\pos -> [\multisliders, \pos, { rrand(0.0, 2.0) }, n],
				\pos_rt -> [\multisliders, \pos, Array.series(n, 0)*(2/n)],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)]
			]
		))

	}

}

// Drone_2_mult.def

/*freqs = \freqs.kr(Array.fill(20, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
amps = \amps.kr(Array.fill(20, { 0.02 + 0.07.rand }));
pos = \pos.kr((Array.series(20, 0)*(2/20)));
/*freqs.poll;
amps.poll;*/
sig = Mix.fill(20*3, { |i|
var ampc;
ampc = AmpCompA.kr(freqs.wrapAt(i));

PanAz.ar(8, SinOsc.ar(Lag.kr((freqs.wrapAt(i)*transp) + (Rand(-2.0, 2.0) * d  + shift) * (distarray.wrapAt(i) ** distor), (0.5 + 2.0.rand)*lag) , 0, Lag.kr(amps.wrapAt(i) , ((0.5 + 2.0.rand)*lag)) * SinOsc.ar(ampmod, 0, 0.5, 1)), Lag.kr(pos.wrapAt(i), lag_spat), 1, width, orientation) * ampc;
});*/