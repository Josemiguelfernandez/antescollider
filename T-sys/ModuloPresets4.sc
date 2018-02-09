ModuloPresets4 {
	var <>params, <>win, <>menu, <>menuin,<>menu_index, <>store_preset, <>path, json_file, <>store_preset_js, dico_preset;



	*new {|parentwin, params, multi, multibuf, fxName|
		^super.new.initModuloPresets(parentwin, params, multi, multibuf, fxName);
	}

	initModuloPresets {|parentwin, params, multi, multibuf, fxName|
		//var para;
		//params = param;
		//para + 1;
		//params.postln;
		win =  parentwin;
		store_preset = IdentityDictionary.new;
		store_preset_js = List[];
		dico_preset = ();
		// store_preset_js.put(fxName, ());
		path = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/").standardizePath();
		path.postln;

		this.botones(parentwin, params, multi, multibuf, fxName);
		this.presetDef(params, multi, multibuf, fxName);
		this.updateMenu(parentwin, params, multi, multibuf, fxName);
		//this.interpol(fxName);
		//^params;
		//ModuloParams.new(params, nil);
		menu.value=3;

	}

	botones {|parentwin, params, multi, multibuf, fxName|
		var menu;
		var lista_js = ();

		Button(parentwin, Rect(405,37,40,20))
		.states_([
			["New", Color.gray(0.75), Color.gray(0.4)]
		])
		.onClose_({"New preset!".postln;
		})
		.action_({
			params.do({|va|
				va.value.postln});
			//multi.postln;
			//fxName.postln;
			multi.do({|va, i|
				va.value.postln});

			multibuf.do({|va, i|
				va.value.postln});

			this.modalGui(parentwin, params, multi, multibuf, fxName);

		});

		//menu  = EZPopUpMenu.new(parentwin,Rect(405, 12, 80, 20));
		//menu.items = ["preset0","preset1","preset2","preset3"];
		// b.postln;

		Button(parentwin, Rect(446,37,40,20))
		.states_([
			["Del", Color.gray(0.75), Color.gray(0.4)]
		])
		.onClose_({
		})
		.action_({
			var a;

			a = SCAlert( "seguro???", ["cancel", "siiii"]);

			a.actions = [{"cancel".postln }, {
				("Delete! "++menuin.item).postln;

				dico_preset[fxName].removeAt(menuin.item);
				menuin.removeItemAt(menu_index); // remove
				json_file = File(path ++ fxName ++ ".json", "w+"); //escribe dicoToJson
				JSONSerializer.writeToFile(dico_preset, json_file);
				json_file.close;

			}];
			// a.onCloseIndex = 0;

		});
		Button(parentwin, Rect(487,37,40,20))
		.states_([
			["Repl", Color.gray(0.75), Color.gray(0.4)]
		])
		.onClose_({
		})
		.action_({

			fxName.asClass.paramslider.collect({|kspec, a|
				lista_js.add(kspec.key -> kspec.value.map(params[a].value));
			});

			fxName.asClass.parammultislider.collect({|spec, a|
				lista_js.add(spec.key -> spec.value[0].map(multi[spec.key].value));
			});

			dico_preset[fxName].add(menuin.item -> lista_js);

			json_file = File(path ++ fxName ++ ".json", "w+"); //escribe dicoToJson
			JSONSerializer.writeToFile(dico_preset, json_file);
			json_file.close;

		});

		Button(parentwin, Rect(487,12,40,20))
		.states_([
			["interp", Color.gray(0.75), Color.gray(0.4)]
		])
		.onClose_({
		})
		.action_({
			//menuin.value=0;
			this.interpol(fxName, params, multi, multibuf);

		});
		Button(parentwin, Rect(527,12,10,20))
		.states_([
			["r", Color.gray(0.75), Color.gray(0.4)]
		])
		.onClose_({
		})
		.action_({
			//menuin.value=0;
			this.presetDef(params, multi, multibuf, fxName);
			this.updateMenu(parentwin, params, multi, multibuf, fxName);

		});

	}

	/*
	NumberBox(parent, Rect(405,65,40,20));
	//~numberCh.value = 0;
	.action_({arg numb;
	~adict1.set(\out, numb.value)};

	*/

	presetDef {|params, multi, multibuf, fxName|
		var json_path, path_def;
		var lista_js = ();
		// var lista = Array.fill(params.size,0);
		// var listamulti = Array.fill(multi.size,0);
		// var merge = Array.new(params.size+multi.size, 0);
		// merge = lista++listamulti;
		//merge.postcs;
		/*		(path ++ fxName).postln;
		test = Object.readArchive(path ++ fxName);
		test.postln;*/
		path_def = path++fxName++".json";
		//
		/*		"path___________".postln;
		File.exists(path_def).postln;
		// json_path.exists.postln;
		"path___________".postln;*/
		// dico_preset.postcs;
		// json_file.postln;
		// json_file.jsonToDict;
		[fxName, "fxName"].postln;
		if ( File.exists(path_def) == false,
			{"no hay preset se ha creado un default en /fxName".postln;

				// TAddic_15_8.parammultislider.collect({|fr| var a; a = fr.value.postln});

				fxName.asClass.paramslider.collect({|kspec, a|
					var def;
					def = kspec.value;

					lista_js.add(kspec.key -> def.default);

				});

				fxName.asClass.parammultislider.collect({|spec, a|

					lista_js.add(spec.key -> spec.value[0].map(Array.fill(spec.value[1], {1.0.rand}))); // crea rand list
					// 1.0.rand!spec.value[1].postln;
				});

				lista_js.postcs;
				dico_preset.add(fxName -> ());
				dico_preset[fxName].add(\default -> lista_js);
				dico_preset.postcs;

				json_file = File(path ++ fxName ++ ".json", "w+"); //escribe dicoToJson
				JSONSerializer.writeToFile(dico_preset, json_file);
				json_file.close;

				(path ++ fxName).postln;
				this.updateMenu},
			{

				// path_def.postln;
				json_path = File(path_def.standardizePath,"r");
				json_file = json_path.readAllString;
				json_path.close;
				dico_preset = json_file.jsonToDict; //convert json to dictionary
			}
		);
	}


	updateMenu {|parentwin, params, multi, multibuf, fxName|
		menu.remove;
		menu  = EZPopUpMenu.new(parentwin,Rect(405, 12, 80, 20));
		menu.setColors(Color.green(0.4),Color.gray(0.75), Color.gray(0.4), Color.gray(0.75), Color.gray(0.75));
		menuin = menu;



		dico_preset[fxName].order.collect({|i| 			//loop en contenido de las keys del diccionario
			menu.addItem(i,{ |a|	//agrega intems al menu

				// menuin = menu;
				menu_index = menu.value;

				fxName.asClass.paramslider.collect({|kspec, a|
					{params[a].valueAction = kspec.value.unmap(dico_preset[fxName][i][kspec.key])}.defer; //envia params directamente a slider desde dico_json
				});

				fxName.asClass.parammultislider.value.collect({|spec, a|
					{multi[spec.key].valueAction = spec.value[0].unmap(dico_preset[fxName][i][spec.key])}.defer;
				});

				fxName.asClass.parammultisliderbuffer.collect({|spec, i|

					/*					multislider[i].postln;
					multi[spec.key].value.postln;

					spec.key.postln;
					spec.class.postln;
					spec.postln;*/

					{multibuf[spec.key].valueAction = spec.value[0].unmap(dico_preset[fxName][i][spec.key])}.defer;

					// listamulti[i] = spec.value[0].map(multi.values[i].value);

				});


				// {slider.do({|i,a| params[a].valueAction_(i) })}.defer; //envia los valores a los sliders
				// {multislider.do({|i,a| multifromdico[a].valueAction = i})}.defer; //envia los valores a los multisliders
				//multislider.do({|i,a| i.postcs;a.postcs })
			}
		)});
		menu.value=0;

	}

	modalGui { |parentwin, params, multi, multibuf, fxName|
		var x, a;
		var lista_js = ();

		// var merge_js = Array.new(params.size+multi.size, 0);
		/* //NO FUNCIONA EN QT
		x = SCModalSheet.new(parentwin, 250@70);
		a = TextField(x, Rect(10, 20, 150, 20));
		a.background_(Color.grey);
		a.stringColor_(Color.white);
		a.string = "preset";
		a.action = {arg field; field.value.postln};
		*/

		x = Window.new("new preset"++fxName, 250@70);
		a = TextField(x, Rect(10, 20, 150, 20));
		a.background_(Color.grey);
		a.stringColor_(Color.white);
		a.string = "preset";
		a.action = {arg field; field.value.postln};
		x.front;
		Button(x, Rect(170,20,25,25))
		.states_([
			["Ok", Color.black, Color.grey]
		])
		.onClose_({"New preset!".postln;
		})
		.action_({ x.close ;

			// TAddic_15_8.paramslider.collect(_.value);

			this.presetDef(params, multi, multibuf, fxName); //refresh menu
			this.updateMenu(parentwin, params, multi, multibuf, fxName);

			// dico_preset[fxName].add(value.asSymbol

			fxName.asClass.paramslider.collect({|kspec, a|

				lista_js.add(kspec.key -> kspec.value.map(params[a].value));

			});

			fxName.asClass.parammultislider.collect({|spec, a|

				lista_js.add(spec.key -> spec.value[0].map(multi[spec.key].value));
			});

			dico_preset[fxName].add(a.value.asSymbol -> lista_js);

			json_file = File(path ++ fxName ++ ".json", "w+"); //escribe dicoToJson
			JSONSerializer.writeToFile(dico_preset, json_file);
			json_file.close;


			this.updateMenu(parentwin, params, multi, multibuf, fxName);

		});

	}

	interpol {|fxName, params, multi, multibuf|
		var w, t,m1, m2, m3, m4, items_list;
		var preset_lh, preset_rh, preset_lb, preset_rb;

		w = Window(fxName++"-Interpol", Rect(100,Window.screenBounds.height-397, 350 ,370));
		w.alpha = 0.8;
		t = Slider2D(w, Rect(25, 25, 300, 300))
		.y_(rrand(0,1.0))
		.x_(rrand(0,1.0))
		.background_( Color.rand )
		.knobColor_(Color.blue)
		.action_({|v|
			var val1,val2,val3,val4,sum, x, y;
			x = v.x;
			y = v.y;
			val1 = preset_rh*x*y;
			val2 = preset_lh*(1.0-x)*y;
			val3 = preset_rb*x*(1.0-y);
			val4 = preset_lb*(1.0-x)*(1.0-y);
			sum = val1+val2+val3+val4;

			fxName.asClass.paramslider.collect({|kspec, a|
				{params[a].valueAction = sum[a]}.defer; //envia params directamente a slider desde dico_json
			});
			fxName.asClass.parammultislider.collect({|spec, a|
				{multi[spec.key].valueAction = sum.copyRange(params.size, params.size+multi.size)[a]}.defer;
				/*multi.postln;
				sum.copyRange(params.size, params.size+multi.size)[a].postln;*/
			});

			/*fxName.asClass.parammultisliderbuffer.collect({|spec, i|
			{multibuf[spec.key].valueAction = spec.value[0].unmap(dico_preset[fxName][i][spec.key])}.defer;
			});*/
		});


		items_list = menuin.items.collect(_.key);

		m1 = EZPopUpMenu.new(w,Rect(25, 4, 80, 20));
		menuin.items.do({|i|
			m1.addItem(i.key,{
				preset_lh = this.recupera_val_prst(fxName, params, multi, multibuf, i.key);
			}
		)});
		m1.valueAction_((items_list.size).rand);

		m2 = EZPopUpMenu.new(w,Rect(245, 4, 80, 20));
		menuin.items.do({|i|
			m2.addItem(i.key,{
				preset_rh = this.recupera_val_prst(fxName, params, multi, multibuf, i.key);
			}
		)});
		m2.valueAction_((items_list.size).rand);

		m3 = EZPopUpMenu.new(w,Rect(25, 330, 80, 20));
		menuin.items.do({|i|
			m3.addItem(i.key,{
				preset_lb = this.recupera_val_prst(fxName, params, multi, multibuf, i.key);
			}
		)});
		m3.valueAction_((items_list.size).rand);

		m4 = EZPopUpMenu.new(w,Rect(245, 330, 80, 20));
		menuin.items.do({|i|
			m4.addItem(i.key,{
				preset_rb = this.recupera_val_prst(fxName, params, multi, multibuf, i.key);
			}
		)});
		m4.valueAction_((items_list.size).rand);

		w.front;
		CmdPeriod.doOnce({w.close});
	}

	recupera_val_prst {|fxName, params, multi, multibuf, prst|
		var lista_params = Array.fill(params.size);
		var listamulti = Array.fill(multi.size,0);
		var listamultibuf = Array.fill(multibuf.size,0);

		fxName.asClass.paramslider.collect({|kspec, a|
			lista_params[a] = kspec.value.unmap(dico_preset[fxName][prst][kspec.key]);
			// {params[a].valueAction = kspec.value.unmap(dico_preset[fxName][prst][kspec.key])}.defer; //envia params directamente a slider desde dico_json
		});

		fxName.asClass.parammultislider.collect({|spec, a|
			listamulti[a] = spec.value[0].unmap(dico_preset[fxName][prst][spec.key]);
			// {multi[spec.key].valueAction = spec.value[0].unmap(dico_preset[fxName][i][spec.key])}.defer;
		});
		^lista_params++listamulti;
	}
}
