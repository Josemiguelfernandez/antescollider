

TGUIMultibuf {
	classvar <defaultWidth= 600, <defaultHeight= 95, <>params;	//bounds are hardcoded for this view
	var <>cv, <numb, <now, <>lista, <>getparams, <>multislid; //, <>multiparams;


	*new {|parent, synth, multispec, name, id, leng, index, b, fxName, objmultislider, multislidparams|

		^super.new.init(parent, synth, multispec, name, id,leng, index, b, fxName, objmultislider, multislidparams);

	}
	init {|parent, synth, multispec, name, id,leng, index, b, fxName, objmultislider, multislidparams|
		//slidsize= argCV;
		//argCV.postln;
		//dic = Dictionary.new;
		//name= argName;
		//multiparams = List[[0],[0],[0]];
		lista = Array.fill(multispec[1], 0);
		// dic = Array.fill(leng);
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

	//--private
	prMake {|parent, synth, multispec, name, id,leng, index, b, fxName, objmultislider, multislidparams|
		var cmp, width1, height1, text, server, unit, win, winparent, paramslen, ind, nom, bounds;


		if(multislidparams.notNil) {
			objmultislider = multislidparams
		};

		win = parent[0];
		winparent = parent[1];
		paramslen = leng - 1;

		if(bounds.isNil, {
			bounds= Rect(200, 200, defaultWidth, defaultHeight);
		}, {
			bounds= bounds.asRect;
		});

		"size : ".postln;
		multispec[1].postln;
		multispec[1].class.postln;

		server = Server.default;

		cmp= CompositeView(win, bounds);
		cmp.decorator= FlowLayout(bounds, 5@4, 5@4);


		b[index] = MultiSliderView(cmp, 343@90);//380@90); //default thumbWidth is 13 ancho-alto
		b[index].indexIsHorizontal_(true);
		b[index].size_(multispec[1]);
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

		if (multispec[3] == \mid_buf){

			b[index].action = {|q|
				var result;
				// lista.put(q.index*2, q.value);
				// q.index.postln;
/*				q.value.postln;
				lista.postln;*/
				result = [q.value, lista].flop.flat;
				// result.postln;
				// q.value.size.postln;
				// lista.size.postln;

				server.cachedBufferAt(multispec[2]).sendCollection(result); //envia array al buffer -> multispec[2] (numero buffer) en este caso result es fft butfer

				objmultislider.put(index, multispec[0].map(q.value));


			};

		}{
			b[index].action = {|q|

				// synth.set(name, multispec[0].map(q.value));
				// ~bufft[\bufft40].sendCollection(slider.value*2);

				server.cachedBufferAt(multispec[2]).sendCollection(q.value); //envia array al buffer -> multispec[2] (numero buffer)

				objmultislider.put(index, multispec[0].map(q.value));


			};
		};
		multislid = b[index];

		text = StaticText(cmp, 40@16);
		text.string = name.asString;
		text.stringColor = Color.gray(0.75);



	}

}

