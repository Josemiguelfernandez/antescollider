Klanky_t : Tmodule2 {

	classvar n = 20; //numero de componentes
	// : TEffectModule
	*def
	{

		^SynthDef(\Klanky_t, {|in = 0, inbus, ingain = 0, out = -1, outbus = -2, outgain = -120, transp = 1.0, amp = -12, d = 0, shift = 0, distor = 0, lfnoise_amp = 0.001, lfnoise_ring = 0.001, lag = 1, gate = 1, free = 1, matrix_ramp = 0.01, width = 2, dust_freq = 10, amp_fac = 1, orientation = 0, lag_spat = 0.2, xFade = 1, t_trig = 0, attack = 0.05, decay = 2|
			var freqs, amps, rings, sig, pos, conca, len, lista, distarray, envgate, envpause, exc;

			envgate = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), free, doneAction:2);
			envpause = EnvGen.kr(Env.asr(matrix_ramp, 1.0, matrix_ramp, \welch ), gate, doneAction:1);

			exc = PinkNoise.ar(amp) * EnvGen.ar(Env.perc(0.005, 0.04), t_trig);
			distarray = Array.series(n, 1);
			freqs = \freqs.kr(Array.fill(n, { [(40 + 75.rand) * (0.50.midiratio),(40 + 75.rand)].choose.midicps}));
			amps = \amps.kr(Array.fill(n, { 0.2 + 0.7.rand }));
			rings = \rings.kr(Array.fill(n, { 0.05 + 0.2.rand }));
			// pos = \pos.kr((Array.series(20, 0)*(2/20)));

			// cross = xFade * envgate * envpause;


			sig = DynKlank.ar(`[freqs, amps, rings], exc, transp, decayscale: decay) - Klank.ar(`[freqs, amps, rings], exc, transp, decayscale: attack);

			sig = sig * envgate * envpause* 0.25;


			// XOut.ar(out, cross, sig*amp.dbamp.lag;); //salida directa a un bus
			// Out.ar(outbus, sig * outgain.dbamp.lag); //salida a un bus auxiliar
			Out.ar(out, sig*amp.dbamp.lag(1));
		}).load;

	}


	// Klanky_t.def
	// Synth(\klanky)

	*metadata
	{
		^(metadata: (
			synthdefname: "Klanky_t",
			type: \gen,
			main: \transp,
			sliders: [
				\transp -> ControlSpec(0.001, 5, \lin, 0.001, 1, \transp),
				\attack -> ControlSpec(0, 0.5, \lin, 0.001, 0.05, \attack),
				\decay -> ControlSpec(0.01, 20, \lin, 0.01, 2, \decay),
				\amp -> ControlSpec(0.ampdb, 2.ampdb, \db, 0, -12, \dB),
				// \d -> ControlSpec(0, 200, \lin, 0.1, 0, \desv),
				// \shift -> ControlSpec(-1200, 1200, \lin, 0, 0, \shift),
				// \distor -> ControlSpec(-2, 2, \lin, 0.001, 0, \distor),
				// \lag -> ControlSpec(0.001, 10, \lin, 0.001, 1, \lag),
				// \width -> ControlSpec(0, 8, \lin, 0.001, 2, \width),
				// \lag_spat -> ControlSpec(0.001, 10, \lin, 0.001, 0.2, \lag_spat),
				// \dust_freq -> ControlSpec(0, 100, \lin, 0.1, 10, \dust_freq)


			],
			multisliders: [
				\freqs -> [ControlSpec(20, 10000, \exp, 0.01, 20, \freqs), n],
				\amps -> [ControlSpec(0, 2, \amp, 0.01, 0.02), n],
				\rings -> [ControlSpec(0, 0.25, \lin, 0.001, 0.1, \rings), n],
				// \pos -> [ControlSpec(0.0001, 2, \lin, 0, 0.3, \vol), n]
			],
			\buttons: [ //[<\que controla>, <\parametro>, <action (funcion, listas, elementos,etc)>]
				\freqs -> [\multisliders, \freqs, {
					var freq_min = 40, freq_max = 75; //en midi quart de ton
					[(freq_min + freq_max.rand) * (0.50.midiratio),(freq_min + freq_max.rand)].choose.midicps}, n],
				\amps -> [\multisliders, \amps,{ rrand(0.0, 2.0) }, n],
				\rings -> [\multisliders, \rings,{ rrand(0.0, 0.25) }, n],
				// \pos -> [\multisliders, \pos, { rrand(0.0, 2.0) }, n],
				// \pos_rt -> [\multisliders, \pos, Array.series(n, 0)*(2/n)],
				\fq_sr -> [\multisliders, \freqs, Array.series(n, 1).linlin(1, n, 20, 10000)],
				\trig -> [\t_trig]
			]
		))

	}

}




/*SynthDef("klanky", { arg inBus = 0, outBus = 0, freqs, fxGate, ringtimes, amps = #[0,1,1,0], durs = #[5,50,5];
var sig, env, fxEnvdurctl, fxEnvampctl, fxEnv, kfreqctl, kfreqarray, kampctl,
kamparray, kringctl, kringarray, knum = 111;

//effect fade envelope
fxEnv = Env.new(amps, durs, \linear);
env = EnvGen.ar(fxEnv, gate: fxGate, doneAction: 2, levelScale: 0.5);

//klank controls
kfreqarray = Array.geom(knum, 300,1.05);
kamparray = Array.fill(knum, 1/knum);
kringarray = Array.fill(knum, 1);
kfreqctl = Control.names([\kfreqarray]).kr(kfreqarray);    //removed the .asArray
kampctl = Control.names([\kamparray]).kr(kamparray); //removed the .asArray
kringctl = Control.names([\kringarray]).kr(kringarray);    //removed the .asArray


//		sig = In.ar(inBus,2);
sig = Dust2.ar([20,20]);
//removed extra [] arround array vars since they are already arrays
sig = DynKlank.ar(`[kfreqctl,kampctl,kringctl], sig);
XOut.ar(outBus, env, sig);
}).send(s);*/