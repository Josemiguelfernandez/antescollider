//Tsys



TModuleGUI2 {
	classvar <>wwidth = 540, <>wheight = 0, <>widthpos = 1;
	var <tmodule, <>parent, position, butt, buttval = -1,
	<views, <mirror, win, <>id, menu, <>nom_fx, <>sliderleng, <>multisliderleng, <>multisliderbufleng, <>buttonleng, <>soundfileviewleng, <>index, <>bslider, <>bmultislider, bslider_control, <>objmultislider, <>objmulti, <>bbutton, <>bsoundfileview, <>synthdefname, synth_number, >synthname, inter_number, >intername, <>slid_param, slid_list, paramslider, parammultislider, parammultisliderbuffer, parambutton, sliderlengconts, sliderparammap, multisliderparammap, multisliderbufparammap, bmultisliderbuf, objmultisliderbuf, objmultibuf,  synthtype, sliders, multisliders, multislidersbuf, soundfileview, soundfileview_buf, menu, menuleng, bmenu, menu_param, tmodule, <>mute, <>solo, xfade, mainslider, <>windopen, multislid_param, multislidbuf_param, multislid_obj, multislidbuf_obj, menu_param, menu_obj, <>soundview_class, soundfileviewconv, <>soundviewconv_class, <>soundview_buff, <>soundviewconv_buff, buffer, <>controllers, <>win_status;

	*new {|ttrack, module, synth, slidparams, multislidparams, knobs, model, others|
		^super.newCopyArgs(ttrack, module, synth, slidparams, multislidparams, knobs).initModuleGUI(ttrack, module, synth, slidparams, multislidparams, knobs, model, others);
	}

	initModuleGUI {|ttrack, module, synth, slidparams, multislidparams, knobs, model, others|
		var cmp, classes, params, labels;
		//ide.postln;
		//mirror= argMirror;
		tmodule = module.asClass;
		"------------".postln;
		//track.postln;

		//track.tsolo.value.postln;

		// TAddic_20_8.parammultislider;
		// SpecDel.parammultisliderbuffer

		win_status = 0;

		classes= (
			\slider: TGUISlider,
			\multislider: TGUIMulti,
			\multisliderbuf: TGUIMultibuf,
			\buttons: TGUIButton,
			\soundfileview: TGUISoundViewConst,
			\soundfileviewconv: TGUISoundViewConv,
			\menu: TGUIMenuConst
		);

		multislid_param = Dictionary.new;
		multislidbuf_param = Dictionary.new;
		multislid_obj = Dictionary.new;
		multislidbuf_obj = Dictionary.new;
		windopen = 1;
		mainslider = tmodule.main[0];
		paramslider = tmodule.paramslider;
		paramslider.postln;

		parammultislider = tmodule.parammultislider;
		parammultislider.postln;
		parammultisliderbuffer = tmodule.parammultisliderbuffer;
		parambutton = tmodule.parambutton;
		parambutton.postln;
		synthdefname = tmodule.defname;
		soundfileview = tmodule.soundfileview;
		soundfileviewconv = tmodule.soundfileviewconv;
		menu = tmodule.menu;

		/*"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;
		others.postln;
		"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;*/

		if (others.notNil) { // si existe el parametro others
			case
			{others.key == 'buf'} {
				"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;
				buffer = others.value;
				buffer.postln;
			}
		};

		// synthdefname.postln;
		sliderleng = paramslider.size;
		// sliderleng.postln;
		multisliderleng = parammultislider.size ? 1;
		multisliderbufleng = parammultisliderbuffer.size ? 1;

		// multisliderleng.postln;
		buttonleng = parambutton.size;
		// buttonleng.postln;
		soundfileviewleng = soundfileview.size;
		menuleng = menu.size;
		synthtype = tmodule.synthtype;

		nom_fx = tmodule.name;
		//postf("nom_fx %", nom_fx);

		/*nom_fx.postln;
		nom_fx.postln;
		nom_fx.postln;*/


		inter_number = ~interfaces.size;
		//~tracks[~tracks_front]

		//~tracks[\track_1].key;

		intername = ttrack.asString ++ "_" ++ nom_fx; //prefix nombre track a los modulos
		intername.postln;




		//postf("multisliderleng %", multisliderleng);

		if (sliderleng <= 4) {
			if ((synthtype == \fx)||(synthtype == \an)) {sliderlengconts = 3.7} //agranda la ventana cuando sliders -4 y fx in
			{sliderlengconts = 3.7}}
		{sliderlengconts = sliderleng}; //agranda la ventana cuando los sliders son menos de 4

		if ((synthtype == \fx)||(synthtype == \an)) {sliderlengconts + 10};

		cmp= this.prContainer((sliderlengconts * 24) + (multisliderleng * 100) + (multisliderbufleng * 100) + (soundfileviewleng * 80), intername, knobs, synth); //track // + multisliderleng * 2.2, intername);
		//cmp.postln;

		//postf("sliderleng %", sliderleng);

		bslider = Array.fill(sliderleng,0);
		bslider_control = Array.fill(sliderleng,0);
		slid_list = Array.fill(sliderleng,0);
		sliderparammap = Array.fill(sliderleng,0);

		multisliderparammap = Array.fill(multisliderleng,0);
		bmultislider = Array.fill(multisliderleng,0);
		objmultislider = List.newClear(multisliderleng); // List[[0],[0],[0]]; //Array.fill(multisliderleng,0);
		objmulti = List.newClear(multisliderleng);

		multisliderbufparammap = Array.fill(multisliderbufleng,0);
		bmultisliderbuf = Array.fill(multisliderbufleng,0);
		objmultisliderbuf = List.newClear(multisliderbufleng); // List[[0],[0],[0]]; //Array.fill(multisliderleng,0)
		objmultibuf = List.newClear(multisliderbufleng);

		bbutton = Array.fill(buttonleng,0);
		bmenu = Array.fill(menuleng,0);
		bsoundfileview = Array.fill(soundfileviewleng,0);


		// creacion de sliders ////
		if (paramslider != nil){

			views= paramslider.collect({|x| [x.key, x.value]}).collect{|x, i|

				"x_test_antes".postln;
				x.postln;


				index = i;

				slid_param = classes[\slider].new(cmp, synth, x[1], x[0], nom_fx, sliderleng, index, bslider, bslider_control, intername, slidparams, knobs, mainslider, model);


			};
			//~slides_params.put(intername, slid_param.getparams);
		};



		// creacion de multisliders ////
		if (parammultislider != nil){
			// views= parammultislider.order.asArray.collect{|x, i|
			views= parammultislider.collect({|x| [x.key, x.value]}).collect{|x, i|

				//x.postln;
				//parammultislider[x].postln; //spec + size de slider
				parammultislider.postln;
				index = i;
				//multislid_param.add(x[0],

				//x[0].postln;
				multislid_param.put(x[0], classes[\multislider].new(cmp, synth, x[1], x[0], nom_fx, multisliderleng, index, bmultislider, intername, objmultislider, multislidparams));
				multislid_obj.put(x[0], multislid_param[x[0]].multislid) //recupera los multislider objets


			};
			// ~multislides_obj.put(intername, multislid_param.getparams);


		} {
			objmultislider = nil;
		};



		// creacion de multisliders ////
		if (parammultisliderbuffer != nil){
			// views= parammultislider.order.asArray.collect{|x, i|
			views= parammultisliderbuffer.collect({|x| [x.key, x.value]}).collect{|x, i|

				// "multisliderbufmultisliderbufmultisliderbuf".postln;
				// x.postln;
				//parammultislider[x].postln; //spec + size de slider
				// parammultisliderbuffer.postln;
				index = i;
				//multislid_param.add(x[0],

				x[1].postln;
				multislidbuf_param.put(x[0], classes[\multisliderbuf].new(cmp, synth, x[1], x[0], nom_fx, multisliderbufleng, index, bmultisliderbuf, intername, objmultisliderbuf, multislidparams));
				multislidbuf_obj.put(x[0], multislidbuf_param[x[0]].multislid) //recupera los multislider objets


			};
			// ~multislides_obj.put(intername, multislid_param.getparams);


		} {
			objmultislider = nil;
		};


		/////////////////////////////////////////////////////Arreglar///////////////////////////////////////////////////////////////////////////////

		// creacion de menus ////
		if (menu != nil){
			//"TUTUTUTUTUTUTUTUTUTUTU".postln;
			views= menu.collect{|x, i|
				//x.postln;

				index = i;
				x.postln;

				// menu_param =
				menu_param = classes[\menu].new(cmp, synth, x.value, x.key, nom_fx, menuleng, index, bmenu, intername); //parammultislider[x] recupera specs de multisliderq
				//module.cvs[x.value].postln;

			};
			// ~menu_obj.put(intername, menu_param.getparams);
			menu_obj = menu_param.menuitem;
		};


		// menu_obj.postln;



		// creacion de buttons ////
		if (parambutton != nil){
			"buttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbuttonbutton0000000000".postln;

			views= parambutton.collect({|x, i|
				//x = [x.key, x.value];

				index = i;
				// parambutton[x].postln;*/
				x.key.postln;
				x.postln;
				if (x.value[0] == \multisliders)
				{
					/*"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".postln;
					parammultislider.postln;
					x.value[1].postln;
					parammultislider.detect({|mtsld| mtsld.key == x.value[1]}).value.postln;*/
					classes[\buttons].new(cmp, parammultislider.detect({|mtsld| mtsld.key == x.value[1]}).value[0], x.value, x.key, nom_fx, buttonleng, index, bbutton, intername, multislid_obj)
				} {
					if (x.value[0] == \multislidersbuf)
					{
						"larlarlarlarlarlarlarlarlarlarlarlar".postln;
						parammultisliderbuffer.postln;
						x.value[1].postln;
						parammultisliderbuffer.detect({|mtsld| mtsld.key == x.value[1]}).value.postln;
						classes[\buttons].new(cmp, parammultisliderbuffer.detect({|mtsld| mtsld.key == x.value[1]}).value[0], x.value, x.key, nom_fx, buttonleng, index, bbutton, intername, multislidbuf_obj)
					} {
						classes[\buttons].new(cmp, nil, x.value, x.key, nom_fx, buttonleng, index, bbutton, intername, nil, menu_obj, synth)
					};
				};



			})
		};



		// creacion de soundfileview ////
		if (soundfileview != nil){
			views= soundfileview.collect{|x, i|
				//x.postln;
				index = i;
				x.postln;
				soundview_class = classes[\soundfileview].new(cmp, synth, soundfileview, x.key, nom_fx, soundfileviewleng, index, bbutton, intername, buffer); //parammultislider[x] recupera specs de multisliderq

				soundview_buff = soundview_class.buf;
			};

		} {
			soundview_class = nil;
		};




		// creacion de soundfileview para convolution reverb ////
		if (soundfileviewconv != nil){
			"CONVULUCION VIEW".postln;
			views= soundfileviewconv.collect{|x, i|
				//x.postln;
				index = i;
				x.postln;
				soundviewconv_class = classes[\soundfileviewconv].new(cmp, synth, soundfileview, x.key, nom_fx, soundfileviewleng, index, bbutton, intername, buffer); //parammultislider[x] recupera specs de multisliderq

				soundviewconv_buff = soundviewconv_class.buf;
			};

		} {
			soundviewconv_class = nil;
		};
		/*"ppppppppppppppppppppppppppppppppp".postln;
		soundview_buff.postln;
		"ppppppppppppppppppppppppppppppppp".postln;*/

		// "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbtotototototototo".postln;
		// slid_param.getparams.postln;
		// multislid_param.postln;
		// multislid_obj.postln;
		// "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbtotototototototo".postln;

		sliders = slid_param.getparams;
		multisliders = multislid_obj;
		multislidersbuf = multislidbuf_obj;
		controllers = slid_param.controllers; // interoga el objeto TGUISlider para recuperara los SimpleControllers MVC para eliminarlos con close

		ModuloPresets4.new(parent, sliders, multisliders, multislidersbuf, nom_fx);
		"controllers".postln;
		controllers.postln;
		"controllers".postln;

	}
	close {
		if(win.notNil and:{win.isClosed.not}, {

			win.close});
	}

	//--private
	prContainer {|size, nom_fx, knobs, synth| //track desde parent
		var cmp, cpmparent, width, height, win, gap= 4@4, margin= 4@4, merge, drag, dragin, screen, composite;
		width = Window.screenBounds.width;
		height = Window.screenBounds.height;


		wheight = wheight + size;
		//wwidth.postln;

		if (wheight >= height) {
			//widthpos.postln;
			wheight = 0 + size; //resetea position height
			wwidth = wwidth + 540;  //540 es el ancho de los modulos

			//wwidth.postln;
			//wheight.postln;
			widthpos = widthpos + 1;

		};
		//parent= Window(nom_fx, Rect(position.x, position.y, 540, size));
		parent= Window(nom_fx, Rect(width - wwidth, height - wheight - 45, 540, size), border:true); //height - wheight - 20

/*		parent.view.onClose_({ controllers.postln;
			"cerrando_ventana".postln;
		});

		parent.view.isClosed({
			"1".postln;
		});

		parent.view.notClosed({
			"0".postln;
		});*/



		parent.view.deleteOnClose = false; // no destruye la ventana, para ser reusada si se cierra


		// parent.view.notClosed_({ controllers.postln;
		// 	"abriendo_ventana".postln;
		// });

		// parent.keyDownAction_({||doc, char| char.postln; synth.set(\t_trig, 1)}); // press "t" to trigger
		/*		parent.view.keyDownAction = {|view, char, modifiers, unicode, keycode|
		[view, char, modifiers, unicode, keycode].postln
		};*/
		case {synthtype == \gen} { parent.background = Color.new255(120, 120, 120)}
		{synthtype == \fx} { parent.background = Color.new255(180, 120, 120)}
		{synthtype == \an} { parent.background = Color.new255(120, 180, 120)};


		//parent.border = false;
		parent.alpha = 0.85;
		win= parent;

		parent.toFrontAction_({ //controllers.postln;
			"visible1".postln;
			parent.view.visible.postln;
			win_status = parent.view.visible;
			"visible1".postln;
		});

		parent.endFrontAction_({ //controllers.postln;
			"visible2".postln;
			parent.view.visible.postln;
			win_status = parent.view.visible;
			"visible2".postln;
		});

		parent.onClose = { //cuando cierra ventana haz-->
			"ventana1".postln;
		//parent = nil;
		windopen = 0;
		// ~windgraph.removeAt(intername);
		};


		CmdPeriod.doOnce({if(win.isClosed.not, {win.close})});



		//ModuloPresets3.new(parent, sliders ,nil,  nom_fx);

		composite = CompositeView(parent, Rect(400, 60, 150, 200)); //crea una region a la derecha para start, in/out, botones,etc
		composite.decorator = FlowLayout(composite.bounds);

		mute = Button(composite).maxHeight_(20).maxWidth_(20).mouseDownAction_("toto".postln).states_([["M", Color.gray(0.8), Color.gray(0.3)],["M",Color.white, Color.red]]).action_(
		//this.postln;
		"this".postln;
		{|val|
		//index1 = index.indexOf(incr);
		if (val.value == 1) {
		// synthe[index1].run(false);
		//synthe[index1].set(\gate, 0);
		//track.tsolo.valueAction_(1);
		knobs[4].valueAction = 1;
		// "toto".postln;
		} {
		/*synthe[index1].run(true);
		synthe[index1].set(\gate, 1);*/
		//track.tsolo.valueAction_(0);
		// this.class.postln;
		//this.subclasses.postln;
		// this.metaclass.postln;
		knobs[4].valueAction = 0;
		}
		});
		// SOLO
		solo = Button(composite).maxHeight_(20).maxWidth_(20).states_([["S", Color.gray(0.8), Color.gray(0.3)],["S",Color.black, Color.yellow]]).action_(
		{|val|
		if (val.value == 1) {
		knobs[3].valueAction = 1;
		}{
		knobs[3].valueAction = 0;
		}
		});

		Button(composite).maxHeight_(20).maxWidth_(100).states_([["rand", Color.gray(0.8), Color.gray(0.3)]]).action_(
		{|val|
		// val.value.postln;
		if (val.value == 0) {

		/*paramslider.postln;
		TAddic_15_8.paramslider[1].key*/
		sliders.postln;
		sliders.collect(({|sld, i|
		if(tmodule.paramslider[i].key != \amp){ // no random in amp!!
		sld.valueAction_(1.0.rand);
		}
		}))

		}
		});

		if (synthtype != \gen) {
		// "tototototoototototototoo".postln;
		xfade = Knob(composite).maxHeight_(20).maxWidth_(25).color_([Color.gray(0.3),Color.red, Color.gray(0.2), Color.grey(0.7)]).mode_(\vert).value_(knobs[2].value).action_({|v|
		/*index1 =index.indexOf(incr);
		synthe[inde1].set(\xFade, v.value.postln);
		xfadeval = vvalue;
		listmodules[idex1].put(1, xfadeval);*/
		knobs[2].valueAction = v.value;
		});
		};

		////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

		//butt = Button(composite, Rect(487,60,40,20))
		/*		butt = Button(composite, Rect(0,0,40,20))
		.states_([
		["Start", Color.black, Color.green(0.7)],
		["Stop", Color.white, Color.red(0.7)]
		])
		.onClose_({
		})
		.action_({|view|
		var args;


		if (view.value == 1) {
		if (view.value != buttval) {
		var tempval, slid_temp, slid_temp2, in, outbus, soundview, temparam;
		~tsynths[intername].get(\gate, {|val| tempval = val}); //verifica si la Synth esta on/off
		in = [\in, ~touts[intername].index];
		outbus = [\outbus, ~touts[intername].index];
		soundview = [\buf, ~soundfileview_buf[intername]];

		if (~slides_params.includesKey(intername) == true) {
		paramslider.keys.asArray.collect{|x, i|
		x.postln;

		i.postln;
		sliderparammap[i] = [x, paramslider[x].map(~slides_params[intername][i].value).round(0.01)]; //envia params a GUI
		};
		//sliderparammap = sliderparammap.flatten(1);
		sliderparammap.postln;
		};

		if (~multislides_params.includesKey(intername) == true) {
		"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".postln;
		"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".postln;
		parammultislider.keys.postln;
		parammultislider.order.postln;
		"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".postln;
		"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".postln;
		parammultislider.order.asArray.collect{|x, i|
		var temp;

		x.postln;
		i.postln;
		parammultislider[x][0].postln;
		temp = [x, [parammultislider[x][0].map(~multislides_params[intername][i])].flatten(1)]; //.flatten(1).flatten(1)
		//multisliderparammap[i] = [x, parammultislider[x][0].map(~multislides_params[intername])]; //envia params a GUI
		multisliderparammap[i] = temp; //
		multisliderparammap[i].postln;
		};
		//multisliderparammap = multisliderparammap.flatten(1);
		};



		"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".postln;
		multisliderparammap.flatten(1).postcs;
		sliderparammap.postln;
		"xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx".postln;

		if (tempval != 1) { //si esta apagado

		/*						if (~multislides_params.includesKey(intername) == false) { //si no hay parametros en GUI
		"titititi".postln;*/
		if (~slides_params.includesKey(intername) == false) {
		"tatatatata".postln;
		if ((synthtype == \fx)||(synthtype == \an)) {
		~tsynths.put(intername, Synth(synthdefname , [\gate, 1] ++ in))
		} {
		"tutututu".postln;
		~tsynths.put(intername, Synth(synthdefname , [\gate, 1] ++ outbus ++ soundview))}

		} /*{

		if (~slides_params.includesKey(intername) == false)  { //si no hay parametros en GUI
		if ((synthtype == \fx)||(synthtype == \an)) {
		~tsynths.put(intername, Synth(synthdefname , [\gate, 1] ++ in))
		} {~tsynths.put(intername, Synth(synthdefname , [\gate, 1] ++ outbus ++ soundview))}

		} */{ //si hay parametros hechos en GUI entonces:
		/*							slid_param.getparams.collect{|val, i|
		slid_list[i] = val.value;
		~slides_params.put(nom_fx, slid_list.copy); //pone parametros en variable global
		};*/

		//--------------------------------------
		/*							paramslider.keys.asArray.collect{|x, i|
		sliderparammap[i] = paramslider[x].map(~slides_params[intername][i].value).round(0.01); //envia params a GUI
		};



		if ((synthtype == \fx)||(synthtype == \an)) {
		slid_temp = [[paramslider.keys.asArray, sliderparammap].flop, [\gate, 1] ++ in].flat //formatea mensaje para Synth
		} {
		if (multisliderleng != 0) {
		parammultislider.keys.asArray.collect{|x, i|
		multisliderparammap[i] = parammultislider[x][0].map(~multislides_params[intername][i]); //envia params a GUI
		};
		slid_temp = [[\gate, 1] ++ outbus,[paramslider.keys.asArray, sliderparammap].flop, [[parammultislider.keys.asArray, multisliderparammap].flop]].flatten(3)} {
		"totototo".postln;
		slid_temp = [[paramslider.keys.asArray, sliderparammap].flop, [\gate, 1] ++ outbus ++ soundview].flat
		}


		};
		slid_temp.postln;
		~tsynths.put(intername, Synth(synthdefname, slid_temp));*/

		//--------------------------------------
		temparam = [[\gate, 1] ++ multisliderparammap.flatten(1) ++ sliderparammap.flatten(1) ++ soundview ++ outbus].flatten(1);
		temparam.postcs;
		~tsynths.put(intername, Synth(synthdefname, temparam));

		/*if (~slides_params.includesKey(intername) == false) {~tsynths.put(intername, Synth(synthdefname, slid_temp))}
		{
		~tsynths.put(intername, Synth(synthdefname, slid_temp))};*/ //crea nueva sytnh con parametros de la GUI


		}
		};

		~tsynthslist.items = ~tsynths.keys.asArray
		} {}; // si esta on poner "start" ->ON
		//};
		//~tsynths[intername].set(\gate, 1);
		/*
		~tsynthslist.items = ~tsynths.keys.asArray*/


		} {
		~tsynths[intername].set(\gate, 0);
		~tsynths.removeAt(intername);
		~tsynthslist.items = ~tsynths.keys.asArray;



		};
		buttval = view.value;
		});*/

		/*		if (~tsynths[intername].get(\gate) == 1) {butt.value = 1};

		if (~tsynths[\g_TTestSynth_1].get(\gate) == 0) {"toto".postln};

		butt.value = 1;*/

		// ////////////////////~tsynths[intername].get(\gate, {|val| if (val == 1) {{butt.value = 1}.defer}}); //si la Synth esta encendida (cuando habre la GUI) poner botton Start

		//~windgraph.put(intername, [parent, butt]); //, slid_param.getparams




		cmp= CompositeView(parent, 400@800); // es este el que bug!!

		cmp.decorator= FlowLayout(cmp.bounds, margin, gap);
		merge = [cmp, win, composite];

		parent.front.view.keyDownAction = {|view, char, modifiers, unicode, keycode| if (keycode==53){win.close};
		// keycode.postln;
		if(keycode == 17) { synth.set(\t_trig, 1)}}; // press "t" to trigger}.postln};
		^merge;

		}

		}
		