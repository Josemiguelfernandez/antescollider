

TGUIMulti {
	classvar <defaultWidth= 600, <defaultHeight= 95, <>params;	//bounds are hardcoded for this view
	var <>cv, <numb, <now, <>lista, <>getparams, <>dic, <>multislid; //, <>multiparams;


	*new {|parent, synth, multispec, name, id, leng, index, b, fxName, objmultislider, multislidparams|

		^super.new.init(parent, synth, multispec, name, id,leng, index, b, fxName, objmultislider, multislidparams);

	}
	init {|parent, synth, multispec, name, id,leng, index, b, fxName, objmultislider, multislidparams|
		//slidsize= argCV;
		//argCV.postln;
		//dic = Dictionary.new;
		//name= argName;
		//multiparams = List[[0],[0],[0]];
		lista = Array.fill(leng, 0);
		dic = Array.fill(leng);
		"______________________".postln;
		parent.postln;
		multispec.postln;
		//name = [index, name];
		name.postln;
		id.postln;
		leng.postln;
		index.postln;
		b.postln;
		fxName.postln;
		objmultislider.postln;
		multislidparams.postln;
		//objmulti.postln;

		"______________________".postln;


/*		//parent.postln;
		postf("parent %", parent);
		//bounds.postln;
		postf("bounds %", bounds);
		//argCV.postln;
		postf("argCV %", argCV);
		//argName.postln;
		postf("argName %", argName);
		//id.postln;
		postf("id %", id);
		//leng.postln;
		postf("leng %", leng);
		//index.postln;
		postf("index %", index);
		//b.postln;
		postf("b %", b);
		//fxName.postln;
		postf("fxName %", fxName);*/


/////////////////////


		this.prMake(parent, synth, multispec, name, id,leng, index, b, fxName, objmultislider, multislidparams);


		//ModuloPresets.new(lista);
		//lista."This is a comment.".postln;
		//postf("lista %", lista);
	}

/*
	value {^cv.input}
	value_ {|val| cv.input= val}
	save {now= knob[index].value}
	interp {|val, target| cv.input= now.blend(target, val)}
 */
	//--private
	prMake {|parent, synth, multispec, name, id,leng, index, b, fxName, objmultislider, multislidparams|
		var cmp, width1, height1, text, server, unit, win, winparent, paramslen, ind, nom, bounds;


		if(multislidparams.notNil) {
			objmultislider = multislidparams
		};
		/*ind = name[0];
		nom = name[1];

		nom.postln;
		nom.postln;
		nom.postln;
		nom.postln;
		nom.postln;*/

		//cv.postln;
		//multispec[0] = Specs
		//multispec[1] = multislider size

		/*[name, index].postln;*/
		win = parent[0];
		winparent = parent[1];
		paramslen = leng - 1;

		if(bounds.isNil, {
			bounds= Rect(200, 200, defaultWidth, defaultHeight);
		}, {
			bounds= bounds.asRect;
		});

/*		bounds.postln;
		bounds.postln;
		bounds.postln;
		bounds.postln;*/

		server = Server.default;

		cmp= CompositeView(win, bounds);
		cmp.decorator= FlowLayout(bounds, 5@4, 5@4);


		//text.string = "tete";

		//cmp.decorator.nextLine;

		//b[index] = MultiSliderView(cmp,Rect(30,185,32*13+80,100)); //default thumbWidth is 13
		b[index] = MultiSliderView(cmp, 343@90);//380@90); //default thumbWidth is 13 ancho-alto
		b[index].indexIsHorizontal_(true);
		b[index].showIndex_(true);
		b[index].isFilled_ (true);
		b[index].background = Color.gray(0.4); //Color.new255(190, 190, 190);
		b[index].fillColor = Color.gray(0.5); //Color.rand;
		b[index].strokeColor = Color.rand;
		b[index].elasticMode = 1;
		b[index].indexThumbSize = 340/multispec[1] - 3;
		if(multislidparams.isNil) {
			b[index].value = Array.fill(multispec[1], 1.0.rand);
		} {
			b[index].value = multispec[0].unmap(multislidparams[index])
		};

		b[index].action = {|q|
			//~adict1.setn(\freqs, q.value.linlin(0, 1, 20, 10000));
			//server.sendMsg(\n_setn, id, name, cv.map(q.value)); //id
			//q.value.postln;
			//lista = b.collect(_.value);
			//lista = q.value;
			//lista.postln;
			// ~tsynths[fxName].set(nom, multispec[0].map(q.value));
			synth.set(name, multispec[0].map(q.value));
			objmultislider.put(index, multispec[0].map(q.value));
			//objmultislider.postcs;
			// index.postln;
			//multispec[0].map(q.value).postln;

			// ~multislides_params.put(fxName, lista.copy);

		};
		multislid = b[index];
		//objmulti.put(index,[name, b[index]]);
		//b[index] = [nom, b[ind]];
		//dic = b.copy;
		//objmultislider = b.copy;
		//
		// objmultislider[index] = [name, b[index]];
		//
		//
		//
		text = StaticText(cmp, 40@16);
		text.string = name.asString;
		text.stringColor = Color.gray(0.75);
		//
		// //dic.add(name -> b[index]);
		//
		// //knob[index].action_({|val| numb[index].value = val});
		//
		// //unit = StaticText(cmp, 30@16);
		// //unit.string = cv.units.asString;
		//
		//
		// //numb.value = cv.unmap(cv.default).round(0.01); //Default from Specs
		//
		// cmp.decorator.nextLine;
		//
		// //knob[index][0].value = 0;
		// //knob[index][0].value = 0.5;
		// //b[index].postln;
		//
		/*if (index == paramslen) {
				//getparams = b;

			};*/
		//"toto".postln;
			////////////////////////ModuloPresets.new(winparent, b, nom_fx_preset);


		//getparams = Dictionary.newFrom(objmulti.flat);

		// 	//"toto".postln;
		// 	//ModuloPresets.new(winparent, b, fxName);
		// 	//dic;
		// 	//getparams = dic;
		// 	objmultislider.postln;

		// 	getparams = Dictionary.newFrom(objmultislider.flat);
		// 	/*getparams = Dictionary.newFrom(dic.flat);*/
		//
		// 	//getparams = b.collect(_.value);
		// 	},{
		// 		nil;
		// });


	}
	/*
		resetSliders {
			var index;
		knob[index].value = 0;

	}
	*/

/////////////////
}

 