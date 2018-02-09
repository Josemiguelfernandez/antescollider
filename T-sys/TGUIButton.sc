

TGUIButton {
	//classvar <defaultWidth= 400, <defaultHeight= 110, <>params;	//bounds are hardcoded for this view
	var <>cv, <numb, <>name, <now, <>lista, <>getparams, <>buttonlista, boton;



	*new {|parent, multisliderspecs, buttonfonction, name, id, buttonleng, index, b, fxName, multislidico, menuobjet, synth|
		^super.new.init(parent, multisliderspecs, buttonfonction, name, id, buttonleng, index, b, fxName, multislidico, menuobjet, synth);
	}
	init {|parent, multisliderspecs, buttonfonction, name, id, buttonleng, index, b, fxName, multislidico, menuobjet, synth|


		name = [index, name];
		// name.postln;
		// //lista = Array.fill(leng, 0);
		// parent.postln;
		// //postf("parent %", parent);
		// multisliderspecs.postln;
		// //postf("bounds %", bounds);
		// buttonfonction.postln;
		// //postf("buttonAction %", buttonfonction);
		// name.postln;
		// //postf("argName %", name);
		// id.postln;
		// //postf("id %", id);
		// buttonleng.postln;
		// //postf("leng %", buttonleng);
		// index.postln;
		// //postf("index %", index);
		// b.postln;
		// //postf("b %", b);
		// fxName.postln;
		// //postf("fxName %", fxName);

		//"bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb".postln;
		/////////////////////cmp, nil, parambutton[x], x, nom_fx, sliderleng, index, bbutton, intername);


		buttonlista = this.prMake(parent, multisliderspecs, name, buttonfonction, id,buttonleng, index, b, fxName, multislidico, menuobjet, synth);
	}


	prMake {|parent, multisliderspecs, name, buttonfonction, id,buttonleng, index, b, fxName, multislidico, menuobjet, synth|
		var cmp, width1, height1, text, server, unit, win, winparent, paramslen, ind, nom;


		ind = name[0];
		nom = name[1];
		// "buttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbutton".postln;
		name.postln;
		parent.postln;
		synth.postln;
		win = parent[0];
		winparent = parent[1];
		cmp = parent[2];
		paramslen = buttonleng - 1;

		if (buttonfonction[0] == \osc) {
			boton = Button(cmp, Rect(0,0,40,20))
			.states_([
				[nom.asString, Color.gray(0.75), Color.gray(0.4)],
				[nom.asString, Color.green, Color.red(0.7)]
			]);
		}{
			boton = Button(cmp, Rect(0,0,40,20))
			.states_([
				[nom.asString, Color.gray(0.75), Color.gray(0.4)]
			]);
		};



		case
		{buttonfonction[0] == \multisliders} {
			boton.action_({|view|
				var multiobjet;
				multiobjet = multislidico[buttonfonction[1]];


				// multiobjet = multislidico[buttonfonction[1]];

				/*				"-------------------------------------------------".postln;
				buttonfonction.postln;
				multisliderspecs.postln;
				"-------------------------------------------------".postln;*/
				if (buttonfonction[2].isFunction) {
					var multisparams = Array.fill(buttonfonction[3], buttonfonction[2]);

					if (buttonfonction[1] == \freqs) { //si es freqs ordena
						multiobjet.postln;
						multisparams.postln;
						multisliderspecs.postln;
						//multiobjet.getparams.postln;
						multiobjet.valueAction =  multisliderspecs.unmap(multisparams.sort);
						//array.postln;
						//multisliderspecs[0].postln;

						//"-------------------------------------------------".postln;
						//~tsynths[fxName].set(nom, array.sort);
						//~multislides_obj[fxName][buttonfonction[1]].valueAction = multisliderspecs[0].unmap(array.sort);
					} {
						multiobjet.valueAction =  multisliderspecs.unmap(multisparams);
						// multiobjet.valueAction = multisliderspecs[0].unmap(array);

					}
				}{
					//~tsynths[fxName].set(buttonfonction[1], buttonfonction[2]);
					multiobjet.valueAction = multisliderspecs.unmap(buttonfonction[2]);

				};
				//buttonfonction.postln;

			});
		}
		{buttonfonction[0] == \multislidersbuf} {
			boton.action_({|view|
				var multiobjet;
				multiobjet = multislidico[buttonfonction[1]];


				// multiobjet = multislidico[buttonfonction[1]];

/*				"-------------------------------------------------tetetet".postln;
				buttonfonction.postln;
				multisliderspecs.postln;
				"-------------------------------------------------tetetete".postln;*/
				if (buttonfonction[2].isFunction) {
					var multisparams = Array.fill(buttonfonction[3], buttonfonction[2]);

					if (buttonfonction[1] == \freqs) { //si es freqs ordena
						multiobjet.postln;
						multisparams.postln;
						multisliderspecs.postln;
						//multiobjet.getparams.postln;
						multiobjet.valueAction =  multisliderspecs.unmap(multisparams.sort);
						//array.postln;
						//multisliderspecs[0].postln;

						//"-------------------------------------------------".postln;
						//~tsynths[fxName].set(nom, array.sort);
						//~multislides_obj[fxName][buttonfonction[1]].valueAction = multisliderspecs[0].unmap(array.sort);
					} {
						multiobjet.valueAction =  multisliderspecs.unmap(multisparams);
						// multiobjet.valueAction = multisliderspecs[0].unmap(array);
						multisliderspecs.unmap(multisparams).postln;

					}
				}{
					//~tsynths[fxName].set(buttonfonction[1], buttonfonction[2]);
					multiobjet.valueAction = multisliderspecs.unmap(buttonfonction[2]);

				};
				//buttonfonction.postln;

			});
		}
		{buttonfonction[0] == \menu} {
			// menuobjet.item.postln;
			boton.action_({
				if (menuobjet.item != -1) {
					~envs[menuobjet.item].plot(minval: 0, maxval: 1)
				}
			});

		}
		{buttonfonction[0] == \t_trig} {
			boton.action_({
				// "t_triggggg".postln; synth.postln;
				synth.set(\t_trig, 1)
			});
		}
		{buttonfonction[0].isArray} {boton.states = buttonfonction[0];
			boton.action_({|val|
				if (val.value == 1) {
					synth.run(true); //activa synth si ha estado puesta en pause
					synth.set(buttonfonction[1],buttonfonction[2]);
					/*synth.postln;
					buttonfonction[1].postln;
					buttonfonction[2].postln;*/
				}{
					synth.set(buttonfonction[3],buttonfonction[4]);
				};
			});
		}
		{buttonfonction[0] == \osc} {
			boton.action_({|val|
				var multiobjet;
				// multiobjet = multislidico[buttonfonction[1]];
				// val.value.postln;
				multisliderspecs.postln;

				if (val.value == 1) {
					//"t_triggggg".postln; synth.postln; synth.set(\t_trig, 1)
					// multiobjet.postln;
					"osc = on".postln;

					OSCdef(\Osc_1, {|msg|
						var val, freq_val, amp_val;
						msg.postln;
						val = msg;
						val.removeAt(0);

						freq_val = val.keep(buttonfonction[1]);
						amp_val = val.keep((buttonfonction[1]) * -1);

						synth.setn(\freqs, freq_val);

						synth.setn(\amps, amp_val);

						/*{~multifreq.value = freq_val.linlin(20, 5000, 0, 1);
						~multiamp.value = amp_val*2}.defer;*/

					}, '/add', nil, 4444).fix;

				}{
					"osc = off".postln;
					OSCdef(\Osc_1).disable;
				};

			});
		}
		// });



	}


}
