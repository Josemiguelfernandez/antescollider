

TGUISlider {
	classvar <defaultWidth= 400, <defaultHeight= 18;	//bounds are hardcoded for this view

	var <>cv, <numb, <>name, <now, <>lista, <>getparams, <>controllers;
	//var
	var <>sliderlista, paramslista;

	*new {|parent, synth, cv, name, nom_fx_preset, leng, index, b, b_control, fxName, trackparams, knobs, mainslider, model|
		^super.new.init(parent, synth, cv, name, nom_fx_preset,leng, index, b, b_control, fxName, trackparams, knobs, mainslider, model);
	}
	init {|parent, synth, argCV, argName, nom_fx_preset,leng, index, b, b_control, fxName, trackparams, knobs, mainslider, model|

		paramslista = Array.fill(leng, 0);
		cv= argCV;
		// argCV.postln;
		name= argName;
		controllers = ();
		//index.postln;
		//leng.postln;
		//listaa = Array.fill(leng, 0)
		//interface_name = fxName;
		//postf("nombre %", interface_name);
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


		sliderlista = this.prMake(parent, synth, name, cv, nom_fx_preset,leng, index, b, b_control, fxName, trackparams, knobs, mainslider, model);

		// if (index == (leng - 1)) {
		// 	"TOTOTOTOTOTOTOTOTOTOTOTOTOTOTO".postln;
		//
		// 	/*		sliderlista.collect{|slider, i|
		// 	paramslista[i] = slider.value;
		// 	};
		// 	paramslista.postln;
		// 	//~slides_params.put(fxName, paramslista);
		// 	~slides_params.put(fxName, paramslista.copy);*/
		//
		// };


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
	prMake {|parent, synth, name, cv, nom_fx_preset,leng, index, b, b_control, fxName, trackparams, knobs, mainslider, model|
		var cmp, width1, height1, text, server, unit, win, winparent, paramslen, bounds;



		win = parent[0];
		winparent = parent[1];
		paramslen = leng - 1;
		//paramslen.postln;
		//postf("paramslen %", b);
		//name.postln;
		//b = Array.fill(leng,0);
		//b.postln;
		//index.postln;
		// cv.postln;
		//id.postln;
		//postf("leng %", leng);
		if(bounds.isNil, {
			bounds= Rect(800, 800, defaultWidth, defaultHeight);
		}, {
			bounds= bounds.asRect;
		});



		server = Server.default;
		//cmp.view.bounds.postln;
		cmp= CompositeView(win, bounds);
		cmp.decorator= FlowLayout(bounds, 4@4, 4@4);
		//width1= bounds.width*3.8;
		//height1= 14;
		text = StaticText(cmp, 50@14);
		text.string = name.asString;
		text.stringColor = Color.gray(0.75);
		//text.string = "tete";

		b[index] = SmoothSlider( cmp, 240@15 ).border_( 0.5 );
		b[index].borderColor_(Color.gray(0.1));

		b[index].baseWidth_(0.35);
		b[index].knobSize(0.5);
		b[index].knobColor_(  Gradient( Color.gray(0.9), Color.gray(0.1), \h ) );
		b[index].hiliteColor_( Gradient( Color.blue.alpha_(0.5), Color.web.purple.alpha_(0.25), \v ) );



		numb= SmoothNumberBox(cmp, 45@14);
		numb.font = Font.new("Helvetica", 10);
		numb.background_(Color.grey);
		numb.normalColor_(Color.grey(0.75));
		numb.typingColor_(Color.red);
		numb.stringColor_(Color.gray(0.75));
		//numb.value = cv.map(trackparams[index]).round(0.01);

		/*		if (~slides_params.includesKey(fxName) == false, {
		b[index].value = cv.unmap(cv.default) //Default from Specs
		}, {
		b[index].value = ~slides_params[fxName][index].value; //Ultiliza ultimo estado de gui sliders
		});*/
		b[index].value = cv.unmap(trackparams[index]);
		// acceder al max de Spec!!
		// knob[index].background_(Color.red.alpha_(0.5).blend( Color.black.alpha_(0.25), ((knob[index].value) - 1).abs));
		//knob[index].background_(Gradient (Color.red.alpha_(((knob[index].value) - 1).abs)), ( Color.black.alpha_(((knob[index].value) - 1).abs)),\h );
		b[index].background_( Gradient( Color.red.alpha_(b[index].value), Color.white.alpha_(b[index].value), \h ) );

		b[index].action_({|sl|
			numb.value = cv.map(sl.value).round(0.00001);


			// ~tsynths[fxName].set(name, cv.map(sl.value));

			synth.set(name, cv.map(sl.value));

			b[index].background_( Gradient( Color.red.alpha_(sl.value), Color.white.alpha_(b[index].value), \h ) );

			if (name == \amp) {knobs[0].value = sl.value};
			if (name == mainslider) {knobs[1].value = sl.value};

			//// MVC

			model.setParam(name, cv.map(sl.value)) // lee el diccionario de model

			//~slides_params.put(index,sl.value);

			//b[index] = sl.value;
			//listaa[i] = sl.value; //registra el estado actual de los sliders
			//listaa.postln;

			//[index, sl.value].postln;


			//^b;
		});

		//~slides_params.put(fxName,listaa.copy);


		numb.action_({|sl|
			b[index].valueAction = cv.unmap(sl.value);
		});


		///// MVC controler input, envia el valor del slider al model
		/*"slider".postln;
		b[index].postln;
		"slider".postln;

		"name".postln;
		name.postln;
		"name".postln;
		*/

		/*		update { |obj, what, val|
		if(what == \valA, {
		{
		numberbox.value_(val);
		slider.value_(val);
		level.value_(val);
		}.defer;
		});
		}*/
		b_control[index] = SimpleController(model).put(\param, { |obj, what, key, value|
			if(key == name) {
				b[index].valueAction = cv.unmap(value);
				numb.valueAction = value.round(0.00001);
			}
		});


		/*SimpleController(model).put(\param, { |obj, what, key, value|
		b[index].value = cv.unmap(value);
		numb.value = value.round(0.00001);
		});*/


		//knob[index].action_({|val| numb[index].value = val});

		unit = StaticText(cmp, 45@16);
		unit.string = cv.units.asString;
		unit.stringColor = Color.gray(0.75);


		numb.value = trackparams[index].round(0.00001); //Default from Specs

		cmp.decorator.nextLine;

		//knob[index][0].value = 0;
		//knob[index][0].value = 0.5;
		//b[index].postln;

		//listaa[index] =  b[index].value;
		//~slides_params = listaa.copy;

		/*			b.collect{|val, i|
		listaa[i] = val.value;
		listaa.postln
		};*/
		//index.postln;
		if( index == paramslen,{
			//"toto".postln;



			////////////////////////ModuloPresets.new(winparent, b, nom_fx_preset);



			getparams = b;
			controllers = b_control;


			/*track.listmodules.flatten(1).put(1, [1,2]);
			"aaaaaaaaaaaaaaaaaaaaaaaaaaaaa".postln;
			b.postln;
			track.listmodules.postln;
			*/

			//getparams = TParams.new(b);

			//^b;

			//b.postln;


			/*			getparams.collect{|val, i|
			getparams[i] = val.value;
			~slides_params.put(fxName,getparams.copy);
			}*/
		},{
			nil;
		});

		//^b;
	}

	/*	*addName {|synthnameout, intername|
	if (intername == interface_name)
	{ classsynthname = synthnameout;
	postf("igual", synthnameout)}
	}*/


	/////////////////
}

