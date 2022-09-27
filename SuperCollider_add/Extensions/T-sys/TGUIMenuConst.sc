

TGUIMenuConst {
	//classvar <defaultWidth= 400, <defaultHeight= 110, <>params;	//bounds are hardcoded for this view
	var <>cv, <numb, <>name, <now, <>lista, <>getparams, <>menuitem;



	*new {|parent, synth, items, name, id, menuleng, index, b, fxName|
		^super.new.init(parent, synth, items, name, id, menuleng, index, b, fxName);
	}
	init {|parent, synth, items, name, id, menuleng, index, b, fxName|


		name = [index, name];
		"tototototototototototototototoototototototototoototototototototoo".postln;
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


/////////////////////cmp, nil, parambutton[x], x, nom_fx, sliderleng, index, bbutton, intername);


		this.prMake(parent, synth, name, items, id, menuleng, index, b, fxName);
	}


	prMake {|parent, synth, name, items, id,menuleng, index, b, fxName|
		var cmp, width1, height1, text, server, unit, win, winparent, paramslen, ind, nom;


		ind = name[0];
		nom = name[1];

		name.postln;
		win = parent[0];
		winparent = parent[1];
		cmp = parent[2];
		paramslen = menuleng - 1;

		b[index] = PopUpMenu(cmp,Rect(405,120,40,20))
		.items_(items[1])
		.stringColor_(Color.gray(0.75))
		.background_(Color.gray(0.4))
		.action_({|menu|

			synth.set(\envbuf, ~envs[menu.item])
			//var buf;
			/*if (~tsynths.includesKey(fxName) == true) {
			if (menu.item == -1, { ~tsynths[fxName].set(\envbuf, -1) }, //Default -1 Hann env
				{~tsynths[fxName].set(\envbuf, ~envs[menu.item]) })
			}*/
		});

		menuitem = b[index];

/*				var array = Array.fill(buttonfonction[2], buttonfonction[1]);
			//array.postln;
				if (buttonfonction[0] == \freqs) { //si es freqs ordena
					~tsynths[fxName].set(nom, array.sort);
					~multislides_obj[fxName][buttonfonction[0]].value = multisliderspecs[0].unmap(array.sort);
				} {
				~tsynths[fxName].set(nom, array);
				~multislides_obj[fxName][buttonfonction[0]].value = multisliderspecs[0].unmap(array);

				//multisliderspecs[0].unmap(array).postln;
			}{
				~tsynths[fxName].set(buttonfonction[0], buttonfonction[1]);
				~multislides_obj[fxName][buttonfonction[0]].value = multisliderspecs[0].unmap(buttonfonction[1]);

			};
			//buttonfonction.postln;

		});*/



		/*if( index == (menuleng -1)) {
			getparams = b;

	}{
		nil;
	};*/


	}

/////////////////

}
