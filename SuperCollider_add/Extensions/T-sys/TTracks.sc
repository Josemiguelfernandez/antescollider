//Tsys José Miguel Fernández 2013


TTracks {
	classvar <>wwidth = 540, <>wheight = 0, widthpos = 1, <>fronttrack;
	var <win, scroll, inc, canvas, layout, i = 0, insertMod, makeEntry, view, viewindex, children, index, indexof, viewobj, container, <synthe, solo, outsynth, <>bus, <>group, levelmeter, slider, ampval, xfadeval, mainparam, mainparamdefault = 0.1, dbspec, textoutputlev, <>numoutputbus, <>listmodules, <>store_preset, menuitems, <>menu, <>tmute, <>tsolo, params, paramsmulti, <>specs, multispec, store, <modwin, ampknob, mainknob, xfadeknob, modgui, <>rand, others, buffer, <>ttrack, <>but_win_mod, <>slidparamsext, newauxbutt, auxView, auxInc, auxViewSroll, auxbus, auxguindexincr, auxbusguindex, fonction, fonction2;

	var <> presetpath;

	*new {|track, aux, grp, bs, otsyn|
		^super.newCopyArgs(track).initTTracks(track, aux, grp, bs, otsyn);
	}

	initTTracks {|track, aux, grp, bs, otsyn| // habia "aux" como arg antes...?
		var temp, osc;
		presetpath = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/Tracks/").standardizePath();

		container = List[];
		synthe = List[];
		solo = List[]; //para gestion de "solo" obligado a hacer una lista del estado "solo" de los modulos
		mainparam = List[]; //para gestion de main param en interface principal
		//mainparam = List[]; //para gestion de main param en interface principal
		listmodules = List[]; // lista para gestion de main param en interface principal
		store = List[];
		specs = List[];
		multispec = List[];
		modwin = List[];
		modgui = List[];
		index = List[];
		auxbus = List[];
		auxbusguindex = List[];

		ttrack = track;

		//store_preset = IdentityDictionary.new;
		menuitems = this.menucollect;

		win = Window.new(ttrack, Rect(0,0,310,450)).background_(Color.new255(120, 120, 120)).alpha_(0.9); // false
		win.view.deleteOnClose = false; // no destruye la ventana, para ser reusada si se cierra

		inc = 0;

		auxInc = 0;
		auxguindexincr = 0;
		//var name = "toto";
		///////////// sacar variable global??????????????
		/*~groups.add(ttrack.asSymbol -> [Group.new, Bus.audio(Server.default, 1)]);
		bus = ~groups[ttrack.asSymbol][1];*/
		if (grp.notNil) {
			group = grp; // si viene un track hecho a partir de Antescofo
		} {
			group = Group.new;
		};

		if (bs.notNil) {
			bus = bs; // si viene un track hecho a partir de Antescofo
		} {
			bus = Bus.audio(Server.default, 1);
		};


		if (otsyn.notNil) {
			outsynth = otsyn; // si viene un track hecho a partir de Antescofo
		} {
			outsynth = AudioOut.synth(group, bus, bus.index, 0.01, 0.01, 0, 1);

		};

		dbspec = ControlSpec(0.ampdb, 1.4126.ampdb, \db, 0, 0, \dB);

		// recupera parametro y ContronSpec de parametro principal


		canvas = View()
		.canReceiveDragHandler_({true})
		.receiveDragHandler_({
			if (View.currentDrag.isKindOf(Symbol)) {
				// add module
				this.addmodule(View.currentDrag);
			}
		});

		auxView = View();


		win.layout = VLayout(
			scroll = ScrollView().hasHorizontalScroller_(false).maxHeight_(320).maxWidth_(310),
			View().maxHeight_(50).fixedWidth_(308)
			.background_(Color.rand).layout_(
				VLayout(
					View().maxWidth_(310).layout_(
						HLayout(
							//Button().maxHeight_(20).maxWidth_(50).states_([["b-In"]]),
							VLayout(levelmeter = LevelIndicator().maxHeight_(14).fixedWidth_(187).numTicks_(37).numMajorTicks_(5).warning_(0.88).critical_(0.98).drawsPeak_(true),
								slider = Slider().maxHeight_(10).fixedWidth_(190).orientation_(\horizontal).thumbSize_(15).value_(dbspec.unmap(0)).action_({|val|
									outsynth.set(\amp, dbspec.map(val.value).postln);
									textoutputlev.string_(dbspec.map(val.value).round(0.1)); // .asStringWithFrac(1)
								}),
								nil,
								StaticText().maxHeight_(30).maxWidth_(145).font_(Font("Monaco", 10)).string_("Group ID: "++ group.asNodeID ++ "  Bus: "++ bus.index).align_(\center)

							).margins_([0, 0, 0, 0]).spacing_(0),

							Button().maxHeight_(10).fixedWidth_(10).states_([[""]]).action_({slider.valueAction_(dbspec.unmap(0).postln)}),
							textoutputlev = StaticText().maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right), //.align_(\bottom) .background_(Color.rand)
							StaticText().maxHeight_(45).fixedWidth_(15).string_("dB"),
							numoutputbus = NumberBox().maxHeight_(20).fixedWidth_(25).value_(0).action_({|val|
								outsynth.set(\out, val.value)}), // ~groups[ttrack.asSymbol][1]
							/*						Knob().maxHeight_(20).maxWidth_(25).mode_(\vert).value_(1).action_({|v|
							}),*/
							Button().maxHeight_(20).maxWidth_(20).states_([["M"],["M",Color.white, Color.red]])
							.action_({|val|
								if (val.value == 0) {
									group.run(true);
									group.set(\gate, 1);
									synthe.collect(_.run(true));
								}{
									// ~groups[ttrack.asSymbol][0].run(false);
									group.set(\gate, 0);

									{group.run(false)}.defer(0.5);
								}
							})
			).margins_([3, 5, 1, 2]).spacing_(1))).margins_([0.1, 0, 0, 0]).spacing_(0)),
			HLayout(
				menu = PopUpMenu().maxHeight_(20).maxWidth_(200).items_(menuitems).action_({|menu|
					var preset;
					this.modclear(ttrack);
					preset = Object.readArchive(presetpath ++ menu.item);
					preset.collect({|mod|
						/*var tempmod;
						mod.postln;
						tempmod = mod[0].postln;*/
						this.addmodule(mod[0], mod[1], mod[2], mod[3], mod[4]) //module, xfade, sliders, multislider, otros
					})

				}),

				Button().maxHeight_(20).maxWidth_(35).states_([
					["New", Color.new255(220, 220, 220),Color.gray(0.6)]
				])
				.onClose_({"New preset!".postln;
				})
				.action_({
					/*params.do({|va|
					va.value.postln;});
					this.modalGui(parentwin, params,fxName);*/
					this.modalGui(nil);

				}),


				Button().maxHeight_(20).maxWidth_(35).states_([
					["Del", Color.new255(220, 220, 220), Color.gray(0.6)]
				])
				.onClose_({
				})
				.action_({
					var alert, it;
					//menuin.postln;
					alert = SCAlert( "estas seguro???", ["cancel", "siiii"]);
					alert.actions = [ {"cancel".postln },
						{
							it = menu.item;
							("rm " ++ presetpath ++ it).unixCmd;

							menuitems.removeAt(menu.value);
							menuitems.postln;
							menu.items = menuitems;
							menu.valueAction = menu.value;
							/*store_preset.removeAt(menuin.item);
							menuin.removeItemAt(menu_index);
							store_preset.writeArchive("fxPresets/" ++ fxName);
							"Delete!"++menuin.item.postln*/

					}];
					//alert.onCloseIndex = 0;


				}),
				Button().maxHeight_(20).maxWidth_(35).states_([
					["Repl", Color.new255(220, 220, 220), Color.gray(0.6)]
				])
				.onClose_({
				})
				.action_({
					var alert, it;
					//menuin.postln;
					alert = SCAlert( "estas seguro???", ["cancel", "siiii"]);
					alert.actions = [ {"cancel".postln },
						{
							it = menu.item;
							this.modalGui(it);


					}];
					/*var lista = Array.fill(params.size);

					params.do({|va, i|
					lista[i] = va.value});
					lista.postln;
					store_preset.put(menuin.item,lista.copy);*/

				})
			).margins_([2, 1, 2, 1]).spacing_(2),

			HLayout(
				VLayout(

					Button().maxHeight_(20).maxWidth_(60).states_([["List"]]).action_({
						var slidparams, multislidparams, store2, store3, store4, spec1 = List[], specmulti1 = List[];

						container.postln;
						listmodules.postln;
						synthe.postln;
						solo.postln;
						mainparam.postln;
						specs.postln;

						store.postln;
						modwin.postln;
						canvas.children.postln;
						index.postln;
						//modwin[0].soundview_class.buf.postln;

						/*modwin.collect({|w, i|
						store3.add(w.objmultislider)//.postln;
						});*/


						slidparams = listmodules.collect(_[2]).deepCopy;
						multislidparams = listmodules.collect(_[3]).deepCopy;
						slidparams.postln;
						multislidparams.postln;
						"----------------------".postln;
						store2 = slidparams.collect(_.collect(_.value));
						store3 = modwin.collect(_.objmultislider);
						store4 = modwin.collect(_.soundview_class);

						store3.postln;
						specs.postln;
						multispec.postln;

						// specs.postln;
						specs.collect({|spval, i|
							spec1.add(spval.collect({|x| x.value}))
						});

						multispec.collect({|spval, i|
							specmulti1.add(spval.collect({|x| x.value}))
						});

						// spec1.postln;

						spec1.collect({|l1, i|
							var st, sp;
							st = store2[i];
							sp = l1;
							sp.collect({|spt, i|
								st[i] = spt.map(st[i].value);
							});
							store[i].put(2, st);
						});

						/*specmulti1.collect({|l1, i|
						var st;
						if (l1.notNil) {//si no hay multispec no registra nada
						st = store3[i];
						//	st.postln;
						l1.collect({|spt, i|
						st[i] = spt[0].map(st[i].value);
						//spt[0].postln; // recupera specs
						});
						store[i].insert(3, st)
						}
						});*/

						store3.collect({|l1, i|
							if (l1.size != 0) {
								store[i].put(3, l1)
							} {
								/*store[i]++nil;
								store[i].postln;
								store[i][0].postln;*/
								//store[i].insert(4, nil)
							}
						});

						store4.collect({|mod, i|
							if (mod.notNil) {
								//store[i].insert(4, \buf -> mod.soundview_class.buf);
								//store[i].insert(4, ["toto"]);
								"buffffffffffffffffeeeeeeeerrrrrrrrrrr".postln;
								mod.buf.postln;
								store[i].postln;
								store[i].put(4, \buf -> mod.buf.path);
								store[i].postln;
							}
						});

						specmulti1.postln;
						listmodules.postln;
						store.postcs;

					}), //clear parent
					Button().maxHeight_(20).maxWidth_(60).states_([["clear"]]).action_({

						this.modclear(ttrack);
					}),
					Button().maxHeight_(20).maxWidth_(60).states_([["freeAll"]]).action_({

						group.freeAll; // free all node
						outsynth = AudioOut.synth(group, bus, bus.index);
					}),
					Button().maxHeight_(20).maxWidth_(60).states_([["Load"]]).action_({
						var test;
						//if (synthe.size != 0) {this.modclear(ttrack)};
						this.modclear(ttrack);
						test = Object.readArchive(presetpath ++ "test2");
						test = test[\toto];
						test.collect({|mod|
							var tempmod;
							tempmod = mod[0].postln;
							this.addmodule(tempmod);

						});
						// test.postln;


					}),
					rand = Button().maxHeight_(20).maxWidth_(60).states_([["Rand"]]).action_({|val|
						var randind;

						randind = container.scramble;

						synthe = synthe[randind];
						mainparam = mainparam[randind];
						listmodules = listmodules[randind];
						specs = specs[randind];
						modwin= modwin[randind];
						store = store[randind];
						modgui = modgui[randind];
						index = index[randind];
						container = index.order.order;

						container.do({|cont, i|
							canvas.layout.insert(canvas.children[cont + 1], i + 1);

						});

						//reordena el orden de los nodos con un solo comando
						Server.default.reorder(synthe, group, \addToHead);

						container.postln;

					}),
					rand = Button().maxHeight_(20).maxWidth_(60).states_([["reorder"]]).action_({|val|
						var randind;

						/*randind = container.scramble;

						synthe = synthe[randind];
						mainparam = mainparam[randind];
						listmodules = listmodules[randind];
						specs = specs[randind];
						modwin= modwin[randind];
						store = store[randind];
						modgui = modgui[randind];
						index = index[randind];
						container = index.order.order;

						container.do({|cont, i|
						canvas.layout.insert(canvas.children[cont + 1], i + 1);

						});

						//reordena el orden de los nodos con un solo comando
						Server.default.reorder(synthe, ~groups[ttrack.asSymbol][0], \addToHead);

						container.postln;*/

					})
				),
				nil,
				VLayout(
					newauxbutt = [Button().maxHeight_(20).maxWidth_(60).states_([["New Aux"]]).action_({|val|
						this.addaux;

					}), align: \topLeft],

					auxViewSroll  = ScrollView().fixedWidth_(120).hasHorizontalScroller_(false)
				)


			)
		).margins_([0, 0, 0, 0]).spacing_(0);

		/*SmoothButton().maxHeight_(25).maxWidth_(25) // 25@25
		.label_( ['power', 'power'] )*/

		canvas.toFrontAction_({

			//~tracks_front = ttrack;
			fronttrack = ttrack;
			//ttrack.postln;

		}); // ventana activa


		canvas.layout = VLayout().margins_([0, 0, 0, 0]).spacing_(0);
		auxView.layout = VLayout().margins_([0, 0, 0, 0]).spacing_(0);

		// agrega un primer DragSink para insertar fx/synth al inicio
		this.insertinit(temp);



		// receive OSC para view-meter
		osc = OSCFunc({arg msg;
			if (msg[2] == bus.index) {
				{
					levelmeter.peakLevel = msg[3].ampdb.linlin(-60, 0, 0, 1);
					levelmeter.value = msg[4].ampdb.linlin(-60, 0, 0, 1);
				}.defer;
			}
		}, '/meter', Server.default.addr);
		win.onClose = {osc.free;};



		scroll.canvas = canvas;
		auxViewSroll.canvas = auxView;
		//scroll.front;
		//auxViewSroll.front;
		win.front;

		fonction = { |synth| // para gui desde synth externas llamado desde Antescofo
			var x = (), d = SynthDescLib.global[synth.defName.asSymbol];
			d.notNil.if { d.controls.do { |c| x.put(c.name, c.defaultValue) } };
			x
		};
		fonction2 = { |synth|
			var x = (), d = SynthDescLib.global[synth.defName.asSymbol];
			d.notNil.if { d.controls.do { |c|  synth.get(c.name,  { |y| x.put(c.name, y) }) } };
		};
	}

	///////////////////////////////// makeEntry

	makeEntry {|incr, name, background, synthtype| // Crea nuevo modulo
		//name.postln;
		//container.add(incr ->
		var temp, index1, index2, index3, index4;

		view = View().background_(background).layout_( //Color.grey(0.3)
			VLayout(
				View().layout_(
					HLayout(
						Button().maxHeight_(19).maxWidth_(35).states_([["In", Color.green(0.6), Color.gray(0.3)]]).mouseDownAction_({|val|
							var temporal, tmp2;
							index1 = index.indexOf(incr);
							// modwin[index1].parent.close;
							//modwin[index1].parent.postln;
							// modwin[index1] = nil;
							/*container[index1].value.postln;
							temporal = canvas.children[1];
							canvas.layout.insert(temporal, 3);
							tmp2 = canvas.children.removeAt(1);*/
							temporal = canvas.children[1];
							canvas.layout.insert(temporal, 3);
							canvas.children.postln;
						}),

						//////////////////////////////////////////////// Name button ////////////////////////////////////////////////////////////////////////////////////////////////////

						but_win_mod = Button().maxHeight_(19).fixedWidth_(100).states_([[name, Color.green(0.6), Color.gray(0.3)]]).action_({ //|val|
							// val.value.postln;
							var slidparams, multislidparams, spec1, specmultisld, gui;

							"tttttttttttttttttttttttttttttttttttttt".postln;

							index1 = index.indexOf(incr);



							spec1 = specs[index1].collect({|x| x.value});

							listmodules.postln;

							if (modwin[index1].isNil) { // si la ventana esta cerrada

								slidparams = listmodules[index1][2].deepCopy;


								/*multislidparams = paramsmulti;
								"----------------------".postln;*/
								"aaaaaaaaaaaaaaaaaaaaaaaaaaaaa".postln;
								slidparams = slidparams.collect(_.value);

								//multislidparams.postln;
								// multislidparams = multislidparams.collect(_.value).postln;
								//multislidparams = modwin[index1].objmultislider.postln;
								// "multislidparamsmultislidparamsmultislidparamsmultislidparamsmultislidparamsmultislidparamsmultislidparamsmultislidparamsmultislidparams".postln;
								/*listmodules.postln;
								multislidparams.postln;*/

								//spec1 = specs[index1];
								"bbbbbbbbbbbbbbbbbbbbbbbbbbb".postln;
								spec1.collect({|sp, i|
									slidparams[i] = sp.map(slidparams[i])
								});

								"ccccccccccccccccccccccccccc".postln;
								gui = TModuleGUI2(ttrack, name.asClass, synthe[index1], slidparams, multislidparams,  modgui[index1], others); //store[index1][2]

								// modwin.postln;

								modwin.put(index1, gui);

								params = modwin[index1].slid_param.getparams; //.collect(_.value);

								slidparamsext = listmodules.collect(_[2]);

								/*if (modwin[index1].multislid_param.notNil) {
								"------------Hay multisliders----------".postln;
								//modwin[index1].multislid_param.getparams.postln;
								// listmodules[index1].insert(3, modwin[index1].multislid_param.getparams);
								modwin[index1].objmultislider.postln;
								listmodules[index1].insert(3, modwin[index1].objmultislider);
								"------------Hay multisliders----------".postln;
								};*/

								listmodules[index1].put(2, params);

								/*slidparams.postln;
								store.postln;
								listmodules.postln;
								store.postln;
								*/
							} {
								modwin[index1].parent.front
							}

						}).beginDragAction_({
							//name.asString;
							index1 = index.indexOf(incr);
							index1;
							[name, index1];
						}).receiveDragHandler_({
							var modname, moddrag, mod_index;
							index1 = index.indexOf(incr);
							if (View.currentDrag.isKindOf(Array)) { // Drag desde canvas
								modname = View.currentDrag[0];
								mod_index = View.currentDrag[1];

								this.replacemodulecanvas(index1, modname, mod_index); // inset module method
							} { // Drag desde browser
								moddrag = View.currentDrag;
								//module = View.currentDrag.asClass;
								this.replacemodulebrowser(index1, moddrag);
							}
						}).keyDownAction_({|view, char, modifiers, unicode, keycode, key|
							//key.postln; // the actual number of the letter, even with a modifier pressed
							// modifiers.postln;
							if (key == 16777251) {"alt".postln}
						}) ,
						Button().maxHeight_(19).maxWidth_(35).states_([["Out", Color.green(0.6), Color.gray(0.3)]]).action_({
							var temporal;
							temporal = canvas.children[1];
							canvas.layout.insert(temporal, 4);
							canvas.children.postln;
							incr.postln}),
						nil,

						mainknob = Knob().maxHeight_(20).maxWidth_(25).color_([Color.gray(0.3),Color.red, Color.gray(0.2), Color.grey(0.7)]).mode_(\vert).value_(mainparamdefault).action_({|v|
							var mainparamname, mainparamspec, slideridx;
							index1 = index.indexOf(incr);

							mainparamname = mainparam[index1][0];
							mainparamspec = mainparam[index1][1];
							index1.postln;
							modwin[index1].postln;
							slideridx = specs[index1].collect({|x| x.key}).indexOf(mainparamname);

							if (modwin[index1].notNil) { // si la ventana se ha cerrado
								if (modwin[index1].parent.isNil)
								{modwin[index1] = nil}
							};

							if (modwin[index1].notNil) {

								listmodules[index1][2][slideridx].valueAction = v.value;
								// "toto".postln;
							} {
								synthe[index1].set(mainparamname.postln, mainparamspec.map(v.value).postln);
								listmodules[index1][2][slideridx] = v.value;
							};

							mainparamdefault = v.value;


							/*main.postln;
							main.keys.asArray.asSymbol[0].postln;
							main.values[0].postln;*/
							// listmodules[index1].put(4, mainparamdefault);

						}),
						ampknob = Knob().maxHeight_(20).maxWidth_(25).color_([Color.gray(0.3),Color.red, Color.gray(0.2), Color.grey(0.7)]).mode_(\vert).value_(ampval).action_({|v|
							var slideridx, mapspec;
							index1 = index.indexOf(incr);
							mapspec = specs[index1].detect({|x| x.key == \amp}).value;
							slideridx = specs[index1].collect({|x| x.key}).indexOf(\amp);

							if (modwin[index1].notNil) { // si la ventana se ha cerrado
								if (modwin[index1].parent.isNil)
								{modwin[index1] = nil}
							};

							if (modwin[index1].notNil) {

								listmodules[index1][2][slideridx].valueAction = v.value;
							} {

								synthe[index1].set(\amp, mapspec.map(v.value).postln);
								listmodules[index1][2][slideridx] = v.value;
							};
							ampval = v.value;

							/*slideridx.postln;
							listmodules[index1][2].postln;*/

						}),
						if (synthtype != \gen) {
							xfadeknob = Knob().maxHeight_(20).maxWidth_(25).color_([Color.gray(0.3),Color.red, Color.gray(0.2), Color.blue(1)]).mode_(\vert).value_(xfadeval).action_({|v|
								index1 = index.indexOf(incr);
								synthe[index1].set(\xFade, v.value.postln);
								xfadeval = v.value;
								listmodules[index1].put(1, xfadeval);

							})
						} {
							xfadeknob = nil;
							Knob().maxHeight_(20).maxWidth_(25).color_([Color.new255(120, 120, 120),Color.new255(120, 120, 120), Color.new255(120, 120, 120), Color.new255(120, 120, 120)]).enabled_(false)
							// View().maxHeight_(20).maxWidth_(25).background_(Color.red)
						},
						nil,
						tmute = Button().maxHeight_(15).maxWidth_(15).states_([["M", Color.gray(0.8), Color.gray(0.3)],["M",Color.white, Color.red]]).action_(
							{|val|
								index1 = index.indexOf(incr);
								if (val.value == 1) {
									// synthe[index1].run(false);
									synthe[index1].set(\gate, 0);
									if (modwin[index1].notNil) {
										modwin[index1].mute.value = 1; //interface1
									}
								} {
									synthe[index1].run(true);
									synthe[index1].set(\gate, 1);
									if (modwin[index1].notNil) {
										modwin[index1].mute.value = 0; //interface1
									}
								}
						}),
						// SOLO
						tsolo = Button().maxHeight_(15).maxWidth_(15).states_([["S", Color.gray(0.8), Color.gray(0.3)],["S",Color.black, Color.yellow]]).action_(
							{|val|
								index1 = index.indexOf(incr);
								if (val.value == 1) {
									solo.put(index1, 1);
									if (modwin[index1].notNil) {
										modwin[index1].solo.value = 1; //interface1
									};
									synthe.collect({|syn, i|

										if (solo[i] == 0) {
											// syn.run(false);
											syn.set(\gate, 0);
										} {
											// syn.run(true);
											syn.set(\gate, 1);
											syn.run(true);
										}
									})
								} {
									solo.put(index1, 0);
									if (modwin[index1].notNil) {
										modwin[index1].solo.value = 0; //interface1
									};
									if (solo.sum != 0) {
										// synthe[index1].run(false);
										synthe[index1].set(\gate, 0);
									} { // si no hay solo todos estan ON!!
										synthe.collect({|syn, i|
											synthe[i].set(\gate, 1);
											synthe[i].run(true);
										})
									}
								}
						}),
						nil,
						Button().maxHeight_(15).maxWidth_(15).states_([["-", Color.gray(0.8), Color.gray(0.3)]]).action_({
							var removecont;

							index1 = index.indexOf(incr);
							index1.postln;
							// synthe[index1].set(\gate, 0);
							synthe[index1].set(\free, 0);
							//viewobj[index1].remove;
							/*							"remove".postln;
							rem.postln;*/

							canvas.children[container[index1] + 1].remove;
							removecont = container.removeAt(index1);
							synthe.removeAt(index1);
							solo.removeAt(index1);
							mainparam.removeAt(index1);
							listmodules.removeAt(index1);
							specs.removeAt(index1);
							store.removeAt(index1);
							index.removeAt(index1);


							container = index.order.order;
							(index1 + 1).postln;

							/*container.do({|val, i| // reconstruye la lista sin orificios
							if (val > removecont) {container[i] = val-1}
							{container[i] = val};
							});*/

							container.postln;
							canvas.children.postln;

							//container[mod_index] + 1;





							modwin.collect({|w, i| // si la ventana ha sido cerrada desde la modwin
								if (w.notNil) {
									if (w.parent.isNil) // check si la ventana es cerrada y actualiza modwin con nil
									{modwin[i] = nil}
								}
							});

							if (modwin[index1].notNil) { // si hay una ventana abierta la cierra
								modwin[index1].parent.close;
							};
							modwin.removeAt(index1);


							if (container.size == 0) {
								inc = 0
							}{ //reinicializa contador
								//inc = inc - 1;
								inc.postln} // resta el incremento

				})).margins_([0, 3, 2, 1]).spacing_(0)),
				DragSink().maxHeight_(10).background_(Color.gray(0.7)).receiveDragHandler_({
					index1 = index.indexOf(incr);

					if (View.currentDrag.isKindOf(Array))  {
						var mod_index, modname;
						modname = View.currentDrag[0];
						mod_index = View.currentDrag[1];
						this.insertmodulecanvas(index1, modname, mod_index);

					} {
						var module, modname;
						module = View.currentDrag;
						modname = View.currentDrag;
						this.insertmodulebrowser(index1, module, modname);
					}


				})
			).margins_([0, 0, 0, 0]).spacing_(1)

		);

		^view;

	}

	///////////////////////////////// makeEntryIn ////////////////////////


	makeEntryIn {|incr, name, background, synthtype| // Crea nuevo modulo In

		var temp, index1, index2, index3, index4;

		view = View().background_(background).layout_( //Color.grey(0.3)
			VLayout(
				View().layout_(
					HLayout(
						/*	Button().maxHeight_(19).maxWidth_(35).states_([["In", Color.green(0.6), Color.gray(0.3)]]).mouseDownAction_({|val|
						var temporal, tmp2;
						index1 = index.indexOf(incr);
						temporal = canvas.children[1];
						canvas.layout.insert(temporal, 3);
						canvas.children.postln;
						}),*/

						//////////////////////////////////////////////// Name button ////////////////////////////////////////////////////////////////////////////////////////////////////

						Button().maxHeight_(19).fixedWidth_(100).states_([[name, Color.green(0.6), Color.gray(0.3)]]).action_({|val|
							// val.value.postln;
							var slidparams, spec1, gui;
							index1 = index.indexOf(incr);
							spec1 = specs[index1].collect({|x| x.value});

							modwin.collect({|w, i| // si la ventana ha sido cerrada desde la modwin
								if (w.notNil) {
									if (w.parent.isNil)
									{modwin[i] = nil}
								}
							});


							if (modwin[index1].isNil) {

								slidparams = listmodules[index1][2].deepCopy;
								"----------------------".postln;
								slidparams = slidparams.collect(_.value).postln;

								//spec1 = specs[index1];
								spec1.collect({|sp, i|
									slidparams[i] = sp.map(slidparams[i])
								});


								gui = TModuleGUI2(ttrack, name.asClass, synthe[index1], slidparams, nil, modgui[index1]); //store[index1][2]

								modwin.put(index1, gui);
								params = modwin[index1].slid_param.getparams; //.collect(_.value);
								listmodules[index1].put(2, params);

								/*slidparams.postln;
								store.postln;
								listmodules.postln;
								store.postln;*/

							} {
								modwin[index1].parent.front
							}
						}).beginDragAction_({
							//name.asString;
							index1 = index.indexOf(incr);
							index1;
							[name, index1];
						}).receiveDragHandler_({
							var modname, moddrag, mod_index;
							index1 = index.indexOf(incr);
							if (View.currentDrag.isKindOf(Array)) { // Drag desde canvas
								modname = View.currentDrag[0];
								mod_index = View.currentDrag[1];

								this.replacemodulecanvas(index1, modname, mod_index); // inset module method
							} { // Drag desde browser
								moddrag = View.currentDrag;
								//module = View.currentDrag.asClass;
								this.replacemodulebrowser(index1, moddrag);
							}
						}).keyDownAction_({|view, char, modifiers, unicode, keycode, key|
							//key.postln; // the actual number of the letter, even with a modifier pressed
							// modifiers.postln;
							if (key == 16777251) {"alt".postln}
						}) ,
						/*Button().maxHeight_(19).maxWidth_(35).states_([["Out", Color.green(0.6), Color.gray(0.3)]]).action_({
						var temporal;
						temporal = canvas.children[1];
						canvas.layout.insert(temporal, 4);
						canvas.children.postln;
						incr.postln}),*/
						nil,
						StaticText().maxHeight_(45).fixedWidth_(26).string_("adc").stringColor_(Color.gray(0.8)),
						NumberBox().maxHeight_(20).fixedWidth_(25).value_(0).action_({|val|
							index1 = index.indexOf(incr);
							synthe[index1].set(\input, val.value)}), // ADC a partir de 0 no de 1!!
						nil,

						/*	mainknob = Knob().maxHeight_(20).maxWidth_(25).color_([Color.gray(0.3),Color.red, Color.gray(0.2), Color.grey(0.7)]).mode_(\vert).value_(mainparamdefault).action_({|v|
						var mainparamname, mainparamspec, slideridx;
						index1 = index.indexOf(incr);

						mainparamname = mainparam[index1][0];
						mainparamspec = mainparam[index1][1];
						index1.postln;
						modwin[index1].postln;
						slideridx = specs[index1].collect({|x| x.key}).indexOf(mainparamname);

						if (modwin[index1].notNil) { // si la ventana se ha cerrado
						if (modwin[index1].parent.isNil)
						{modwin[index1] = nil}
						};

						if (modwin[index1].notNil) {

						listmodules[index1][2][slideridx].valueAction = v.value;
						// "toto".postln;
						} {
						synthe[index1].set(mainparamname.postln, mainparamspec.map(v.value).postln);
						listmodules[index1][2][slideridx] = v.value;
						};

						mainparamdefault = v.value;


						/*main.postln;
						main.keys.asArray.asSymbol[0].postln;
						main.values[0].postln;*/
						// listmodules[index1].put(4, mainparamdefault);

						}),*/
						ampknob = Knob().maxHeight_(20).maxWidth_(25).color_([Color.gray(0.3),Color.red, Color.gray(0.2), Color.grey(0.7)]).mode_(\vert).value_(ampval).action_({|v|
							var slideridx, mapspec;
							index1 = index.indexOf(incr);
							mapspec = specs[index1].detect({|x| x.key == \amp}).value;
							slideridx = specs[index1].collect({|x| x.key}).indexOf(\amp);

							if (modwin[index1].notNil) { // si la ventana se ha cerrado
								if (modwin[index1].parent.isNil)
								{modwin[index1] = nil}
							};

							if (modwin[index1].notNil) {

								listmodules[index1][2][slideridx].valueAction = v.value;
							} {

								synthe[index1].set(\amp, mapspec.map(v.value).postln);
								listmodules[index1][2][slideridx] = v.value;
							};
							ampval = v.value;

							/*slideridx.postln;
							listmodules[index1][2].postln;*/

						}),
						/*if (synthtype != \gen) {
						xfadeknob = Knob().maxHeight_(20).maxWidth_(25).color_([Color.gray(0.3),Color.red, Color.gray(0.2), Color.blue(1)]).mode_(\vert).value_(xfadeval).action_({|v|
						index1 = index.indexOf(incr);
						synthe[index1].set(\xFade, v.value.postln);
						xfadeval = v.value;
						listmodules[index1].put(1, xfadeval);

						})
						} {
						xfadeknob = nil;
						Knob().maxHeight_(20).maxWidth_(25).color_([Color.new255(120, 120, 120),Color.new255(120, 120, 120), Color.new255(120, 120, 120), Color.new255(120, 120, 120)]).enabled_(false)
						// View().maxHeight_(20).maxWidth_(25).background_(Color.red)
						},*/
						nil,
						tmute = Button().maxHeight_(15).maxWidth_(15).states_([["M", Color.gray(0.8), Color.gray(0.3)],["M",Color.white, Color.red]]).action_(
							{|val|
								index1 = index.indexOf(incr);
								if (val.value == 1) {
									// synthe[index1].run(false);
									synthe[index1].set(\gate, 0);
									if (modwin[index1].notNil) {
										modwin[index1].mute.value = 1; //interface1
									}
								} {
									synthe[index1].run(true);
									synthe[index1].set(\gate, 1);
									if (modwin[index1].notNil) {
										modwin[index1].mute.value = 0; //interface1
									}
								}
						}),
						// SOLO
						tsolo = Button().maxHeight_(15).maxWidth_(15).states_([["S", Color.gray(0.8), Color.gray(0.3)],["S",Color.black, Color.yellow]]).action_(
							{|val|
								index1 = index.indexOf(incr);
								if (val.value == 1) {
									solo.put(index1, 1);
									if (modwin[index1].notNil) {
										modwin[index1].solo.value = 1; //interface1
									};
									synthe.collect({|syn, i|

										if (solo[i] == 0) {
											// syn.run(false);
											syn.set(\gate, 0);
										} {
											// syn.run(true);
											syn.set(\gate, 1);
											syn.run(true);
										}
									})
								} {
									solo.put(index1, 0);
									if (modwin[index1].notNil) {
										modwin[index1].solo.value = 0; //interface1
									};
									if (solo.sum != 0) {
										// synthe[index1].run(false);
										synthe[index1].set(\gate, 0);
									} { // si no hay solo todos estan ON!!
										synthe.collect({|syn, i|
											synthe[i].set(\gate, 1);
											synthe[i].run(true);
										})
									}
								}
						}),
						nil,
						Button().maxHeight_(15).maxWidth_(15).states_([["-", Color.gray(0.8), Color.gray(0.3)]]).action_({
							var removecont;

							index1 = index.indexOf(incr);
							index1.postln;
							synthe[index1].set(\free, 0);
							[container[index1] + 1].remove;
							removecont = container.removeAt(index1);
							synthe.removeAt(index1);
							solo.removeAt(index1);
							mainparam.removeAt(index1);
							listmodules.removeAt(index1);
							specs.removeAt(index1);
							store.removeAt(index1);
							index.removeAt(index1);


							container = index.order.order;
							(index1 + 1).postln;

							container.postln;
							canvas.children.postln;

							modwin.collect({|w, i| // si la ventana ha sido cerrada desde la modwin
								if (w.notNil) {
									if (w.parent.isNil) // check si la ventana es cerrada y actualiza modwin con nil
									{modwin[i] = nil}
								}
							});

							if (modwin[index1].notNil) { // si hay una ventana abierta la cierra
								modwin[index1].parent.close;
							};
							modwin.removeAt(index1);


							if (container.size == 0) {
								inc = 0
							}{ //reinicializa contador
								//inc = inc - 1;
								inc.postln} // resta el incremento

				})).margins_([0, 3, 2, 1]).spacing_(0)),
				DragSink().maxHeight_(10).background_(Color.gray(0.7)).receiveDragHandler_({
					index1 = index.indexOf(incr);

					if (View.currentDrag.isKindOf(Array))  {
						var mod_index, modname;
						modname = View.currentDrag[0];
						mod_index = View.currentDrag[1];
						this.insertmodulecanvas(index1, modname, mod_index);

					} {
						var module, modname;
						module = View.currentDrag;
						modname = View.currentDrag;
						this.insertmodulebrowser(index1, module, modname);
					}


				})
			).margins_([0, 0, 0, 0]).spacing_(1)

		);

		^view;

	}


	insertinit {|temp|
		canvas.layout.insert(DragSink().maxHeight_(10).background_(Color.gray(0.7)).receiveDragHandler_({
			var addsynth, main, module, removelistmod, removespecs, removemodwin, removestore, removemodgui, removeindex, background, typesynth, sliderspec, temporal;
			solo.insert(0, 0);
			if (View.currentDrag.isKindOf(Array)) { //  isInteger
				var mod_index, modname, temp2, removesynth, removemainparam;
				modname = View.currentDrag[0];
				mod_index = View.currentDrag[1];

				temporal = container[mod_index] + 1;
				canvas.layout.insert(canvas.children[temporal], 1);

				synthe[mod_index].moveBefore(synthe[0]);

				removesynth = synthe.removeAt(mod_index);
				synthe.insert(0, removesynth);

				removemainparam = mainparam.removeAt(mod_index);
				mainparam.insert(0, removemainparam);

				removelistmod = listmodules.removeAt(mod_index);
				listmodules.insert(0, removelistmod);

				removespecs = specs.removeAt(mod_index);
				specs.insert(0, removespecs);

				removemodwin = modwin.removeAt(mod_index);
				modwin.insert(0, removemodwin);

				removestore = store.removeAt(mod_index);
				store.insert(0, removestore);

				removestore = store.removeAt(mod_index);
				store.insert(0, removestore);

				removeindex = index.removeAt(mod_index);
				index.insert(0, removeindex);

				removemodgui = modgui.removeAt(mod_index);
				modgui.insert(0, removemodgui);

				container = index.order.order;


			}

			{ // desde browser como addmodule
				module = View.currentDrag;
				addsynth = module.asClass.synthbefore(synthe[0], bus);
				main = module.asClass.main;
				mainparamdefault = main[1].unmap(main[1].default);
				background = module.asClass.background;
				params = module.asClass.paramslider.collect({|x| x.value.default});

				sliderspec = module.asClass.paramslider.collect({|x| x.value});
				typesynth = module.asClass.synthtype;

				sliderspec.collect({|sp, i|
					params[i] = sp.unmap(params[i])
				});

				temp = this.makeEntry(inc, module, background, typesynth);
				index.insert(0, inc);
				synthe.insert(0, addsynth);
				mainparam.insert(0, main);

				listmodules.insert(0, [module, xfadeval, params]);

				specs.insert(0, module.asClass.paramslider);
				modwin.insert(0, nil); //ventanas gui1
				modgui.insert(0, [ampknob, mainknob, xfadeknob, tsolo, tmute]);

				store.insert(0, [module, xfadeval, params]);

				canvas.layout.insert(temp, 1); //+2 offset +1 para ponerlo despues +1 por DragSink inicio
				canvas.layout.add(nil);

				container = index.order.order;

				inc = inc + 1;

			};

		}), 0);
		canvas.layout.add(nil);

	}




	addmodule {|module, xfade, par, multi, otros|
		var temp, addsynth, main, background, sliderspec, typesynth;
		// module.asClass.def;
		main = module.asClass.main;
		background = module.asClass.background;
		typesynth = module.asClass.synthtype;

		if (par.isNil) { // si el addmodule viene de un preset con parametros o no (desde browser)
			var ampspec;
			if (typesynth != 'conv') {
				addsynth = module.asClass.synthout(outsynth, bus);
			}{
				addsynth = module.asClass.synthoutconv(outsynth, bus);
				"CONVULUCION".postln;
			};
			params = module.asClass.paramslider.collect({|x| x.value.default});
			mainparamdefault = main[1].unmap(main[1].default); // envia para init gui
			ampspec = module.asClass.paramslider.detect({|x| x.key == \amp}).value;
			ampval = ampspec.unmap(module.asClass.ampdefault); // envia para init gui
		} {
			var temparams, tempmultiparams, amppar, ampparspec, mainpar, mainparspec;
			temparams = module.asClass.paramslider.collect({|x| x.key});
			tempmultiparams = module.asClass.parammultislider.collect({|x| x.key});
			if (otros.notNil) {
				if (otros.key == 'buf') {
					buffer = Buffer.read(Server.default, otros.value);
				};
				others = otros;
			};

			//Server.default.makeBundle(nil,{
			//addsynth = module.asClass.synthoutpar(outsynth, bus, [\xFade, xfade]++[temparams,par].flop.flat);
			//addsynth.set([tempmultiparams, multi].flop.flatten(1));


			/*addsynth = module.asClass.synthoutpar(outsynth, bus, [[temparams,par].flop.flat++[tempmultiparams, multi].flop.flatten(1), [\xFade, xfade], [\buf, buffer]].flatten(1));*/

			addsynth = module.asClass.synthoutpar(outsynth, [[temparams,par].flop.flat++[tempmultiparams, multi].flop.flatten(1), [\xFade, xfade], [\buf, buffer], [\in, bus, \out, bus]].flatten(1));




			//[[\xFade, xfade]++[temparams,par].flop.flat++[tempmultiparams, multi].flop.flatten(1)].flatten(1).postcs;
			/*[tempmultiparams, multi].flop.flatten(1).clump(2).collect({|ctrl|
			addsynth.set(ctrl);
			ctrl.postln;
			});*/
			//});
			ampparspec = module.asClass.paramslider.detect({|x| x.key == \amp}).value;
			mainparspec = module.asClass.main[1];
			amppar = par[module.asClass.paramslider.collect({|x| x.key}).indexOf(\amp)];
			mainpar = par[module.asClass.paramslider.collect({|x| x.key}).indexOf(module.asClass.main[0])];
			mainparamdefault = mainparspec.unmap(mainpar); // envia para init gui
			ampval = ampparspec.unmap(amppar); // envia para init gui

			params = par;
			paramsmulti = multi;

		};

		if (xfade.isNil) {
			xfadeval = 1;
		} {
			xfadeval = xfade; // envia para init gui
		};

		sliderspec = module.asClass.paramslider.collect({|x| x.value});

		sliderspec.collect({|sp, i|
			params[i] = sp.unmap(params[i])
		});

		if (typesynth == \in) {
			temp = this.makeEntryIn(inc, module, background, typesynth);
		}{
			temp = this.makeEntry(inc, module, background, typesynth);
		};


		index.insert(inc, inc);
		indexof = index.indexOf(inc);
		//"indexof".postln;
		synthe.insert(indexof, addsynth);
		mainparam.insert(indexof, main);
		modgui.insert(indexof, [ampknob, mainknob, xfadeknob, tsolo, tmute]); // elementos graficos de modulos en track para acceso del exterior
		// para preset
		listmodules.insert(indexof, [module, xfadeval, params, paramsmulti]);
		specs.insert(indexof, module.asClass.paramslider); //sliderspec .collect({|x| x.value});
		multispec.insert(indexof, module.asClass.parammultislider);
		modwin.insert(indexof, nil); //ventanas gui1
		store.insert(indexof, [module, xfadeval, params, paramsmulti, nil]); //nil otros pararmetros
		solo.insert(indexof, 0); //inicializa el estado del "solo"

		canvas.layout.insert(temp, indexof + 1); // + 1 a causa del DragSink del incio
		canvas.layout.add(nil);

		container = index.order.order;

		inc = inc + 1;

	}

	addmodfromsynth {|module, synth|

		var parados, parados2, temp, addsynth, main, background, sliderspec, sliderskey, typesynth, xfade, par, multi, otros;
		var temparams, tempmultiparams, amppar, ampparspec, mainpar, mainparspec, paralist = List[];
		// module.asClass.def;
		[module, synth].postln;
		// params = Array.new; //fill(module.asClass.paramslider.size,0);

		module.asClass.paramslider.collect({|slider|
			var slider_name;
			slider_name = slider.key;
			synth.get(slider_name, {|val| [slider_name, val].postln})

		});

		main = module.asClass.main;
		background = module.asClass.background;
		typesynth = module.asClass.synthtype;


		// "parte1".postln;
		temparams = module.asClass.paramslider.collect({|x| x.key});
		tempmultiparams = module.asClass.parammultislider.collect({|x| x.key});

		if (otros.notNil) {
			if (otros.key == 'buf') {
				buffer = Buffer.read(Server.default, otros.value);
			};
			others = otros;
			//};

			// addsynth = module.asClass.synthoutpar(outsynth, [[temparams,par].flop.flat++[tempmultiparams, multi].flop.flatten(1), [\xFade, xfade], [\buf, buffer], [\in, bus, \out, bus]].flatten(1));

			// "parte2".postln;
			ampparspec = module.asClass.paramslider.detect({|x| x.key == \amp}).value;
			mainparspec = module.asClass.main[1];
			amppar = par[module.asClass.paramslider.collect({|x| x.key}).indexOf(\amp)];
			mainpar = par[module.asClass.paramslider.collect({|x| x.key}).indexOf(module.asClass.main[0])];
			mainparamdefault = mainparspec.unmap(mainpar); // envia para init gui
			ampval = ampparspec.unmap(amppar); // envia para init gui

			params = par;
			paramsmulti = multi;

		};
		// "parte3".postln;
		if (xfade.isNil) {
			xfadeval = 1;
		} {
			xfadeval = xfade; // envia para init gui
		};
		// "parte3-2".postln;
		sliderspec = module.asClass.paramslider;//.collect({|x| x.value;x.postln});
		sliderskey = module.asClass.paramslider.collect({|x| x.key});
		// params = sliderskey;
		/*"parte3-22".postln;
		sliderskey.postln;
		params.postln;*/

		// Server.default.sync;

		sliderspec.collect({|sp, i| //recupera valores de las synth activas desde Antescofo
			var slider_name, slider_sp;
			slider_name = sp.key;
			slider_sp = sp.value;
			synth.get(slider_name, {|val|
				paralist.add(slider_sp.unmap(val));
				// slider_sp.unmap(val).postln;
				// paralist.postln;
				// i.postln;
				// slider_sp.unmap(val).postln;
				// val.postln;
				// sp.postln;
			});
		});

		// Server.default.sync;

		{0.1.wait;{ // para sincronizar la respuesta del servidor

		//AppClock.sched(0.001, { // create a Synth after 0.05 seconds

			paralist.postln;

			params = paralist.asArray;

			if (typesynth == \in) {
				temp = this.makeEntryIn(inc, module, background, typesynth);
			}{

				temp = this.makeEntry(inc, module, background, typesynth);
			};


			index.insert(inc, inc);
			indexof = index.indexOf(inc);
/*			"indexof".postln;
			xfadeval.postln;
			"params".postln;
			params.postln;
			"paramsmulti".postln;
			paramsmulti.postln;*/
			synthe.insert(indexof, synth);
			mainparam.insert(indexof, main);
			modgui.insert(indexof, [ampknob, mainknob, xfadeknob, tsolo, tmute]); // elementos graficos de modulos en track para acceso del exterior
			// para preset
			listmodules.insert(indexof, [module, xfadeval, params, paramsmulti]);
			specs.insert(indexof, module.asClass.paramslider); //sliderspec .collect({|x| x.value});
			multispec.insert(indexof, module.asClass.parammultislider);
			modwin.insert(indexof, nil); //ventanas gui1
			store.insert(indexof, [module, xfadeval, params, paramsmulti, nil]); //nil otros pararmetros
			solo.insert(indexof, 0); //inicializa el estado del "solo"


			canvas.layout.insert(temp, indexof + 1); // + 1 a causa del DragSink del incio
			canvas.layout.add(nil);

			container = index.order.order;

			inc = inc + 1;
		}.defer}.fork;


	}



	replacemodulecanvas {|index1, modname, mod_index|

		var background, moddrag, typesynth;

		moddrag = modname;
		background = modname.asClass.background;
		typesynth = modname.asClass.synthtype;
		typesynth.postln;


		if (index1 != mod_index) { // no permite de regenerar un drag sobre el mismo
			moddrag.postln;

			solo.removeAt(index1);

			synthe[index1].set(\free, 0);
			synthe[mod_index].moveAfter(synthe[index1]);

			synthe.removeAt(index1);
			mainparam.removeAt(index1);
			listmodules.removeAt(index1);
			specs.removeAt(index1);
			modwin.removeAt(index1);
			store.removeAt(index1);
			index.removeAt(index1);
			modgui.removeAt(index1);

			if (modwin[index1].notNil) { // si la ventana se ha cerrado
				if (modwin[index1].parent.isNil)
				{modwin[index1] = nil};
				modwin[index1].parent.close;
			};

			canvas.layout.insert(canvas.children[container[mod_index] + 1], index1 + 1);
			canvas.children[container[index1] + 1].remove;
			container = index.order.order;

			inc = inc + 1;


		}
	}




	replacemodulebrowser {|index1, moddrag| // Drag desde browser
		var module, addsynth, main, removesynth, background, temp2, typesynth;

		module = moddrag.asClass;
		main = module.asClass.main;
		background = module.asClass.background;
		typesynth = module.asClass.synthtype;


		synthe[index1].set(\free, 0); // stop sytnh
		addsynth = module.synthafter(synthe[index1], bus); // synth routing

		synthe.removeAt(index1);

		synthe.insert(index1, addsynth);

		mainparam.removeAt(index1);
		mainparamdefault = main[1].unmap(main[1].default);
		mainparam.insert(index1, main);

		listmodules.removeAt(index1);
		listmodules.insert(index1, [moddrag, xfadeval, params]);

		specs.removeAt(index1);
		specs.insert(index1, module.asClass.paramslider);

		if (modwin[index1].notNil) { // si la ventana se ha cerrado
			if (modwin[index1].parent.isNil)
			{modwin[index1] = nil};
			modwin[index1].parent.close;
		};

		modwin.removeAt(index1);
		modwin.insert(index1, nil);

		store.removeAt(index1);
		store.insert(index1, [module, xfadeval, params]);

		modgui.removeAt(index1);
		modgui.insert(index1, [ampknob, mainknob, xfadeknob, tsolo, tmute]);



		canvas.children[container[index1] + 1].remove;
		temp2 = this.makeEntry(inc, moddrag, background, typesynth);
		canvas.layout.insert(temp2, index1 + 1);
		canvas.layout.add(nil);



		// index1.postln;

		index.removeAt(index1);
		index.insert(index1, inc);

		container = index.order.order;

		inc = inc + 1;


	}



	insertmodulecanvas {|index3, modname, mod_index|
		var background, temp2, typesynth, removesynth, removemainparam, removelistmod, removespecs, removemodwin, removestore, removemodgui, removecontainer, removeindex;

		background = modname.asClass.background;
		typesynth = modname.asClass.synthtype;

		solo.insert(index3, 0);

		temp2 = container[mod_index] + 1;

		temp2.postln;

		synthe[mod_index].moveAfter(synthe[index3]); // synth routing

		removesynth = synthe.removeAt(mod_index);
		removemainparam = mainparam.removeAt(mod_index);
		removelistmod = listmodules.removeAt(mod_index);
		removespecs = specs.removeAt(mod_index);
		removemodwin = modwin.removeAt(mod_index);
		removestore = store.removeAt(mod_index);
		removemodgui = modgui.removeAt(mod_index);
		removeindex = index.removeAt(mod_index);

		if (mod_index < index3) { // condicion de movimiento drag de arriba hacia abajo

			synthe.insert(index3, removesynth);
			mainparam.insert(index3, removemainparam);
			listmodules.insert(index3, removelistmod);
			specs.insert(index3, removespecs);
			modwin.insert(index3, removemodwin);
			store.insert(index3, removestore);
			modgui.insert(index3, removemodgui);
			index.insert(index3, removeindex);

			canvas.layout.insert(canvas.children[temp2], index3 + 1);
		} {
			//removesynth.postln;
			synthe.insert(index3 + 1, removesynth);
			mainparam.insert(index3 + 1, removemainparam);
			listmodules.insert(index3 + 1, removelistmod);
			specs.insert(index3 + 1, removespecs);
			modwin.insert(index3 + 1, removemodwin);
			store.insert(index3 + 1, removestore);
			modgui.insert(index3 + 1, removemodgui);
			index.insert(index3 + 1, removeindex);

			canvas.layout.insert(canvas.children[temp2], index3 + 2);


		};

		container = index.order.order;

	}



	insertmodulebrowser {|index2, module, modname|

		var  addsynth, main, background, typesynth, temp, sliderspec;
		// "from_browser".postln;

		solo.insert(index2, 0);


		addsynth = module.asClass.synthafter(synthe[index2], bus);

		synthe.insert(index2 + 1, addsynth);

		main = module.asClass.main;
		mainparamdefault = main[1].unmap(main[1].default);
		mainparam.insert(index2 + 1, main);

		background = module.asClass.background;
		typesynth = module.asClass.synthtype;

		params = module.asClass.paramslider.collect({|x| x.value.default});

		sliderspec = module.asClass.paramslider.collect({|x| x.value});

		sliderspec.collect({|sp, i|
			params[i] = sp.unmap(params[i])
		});

		xfadeval = 1;

		temp = this.makeEntry(inc, modname, background, typesynth);

		index.insert(index2 + 1, inc);

		container = index.order.order;


		listmodules.insert(index2 + 1, [modname, xfadeval, params]);

		specs.insert(index2 + 1, module.asClass.paramslider);
		modwin.insert(index2 + 1, nil); //ventanas gui1

		store.insert(index2 + 1, [module, xfadeval, params]);
		modgui.insert(index2 + 1, [ampknob, mainknob, xfadeknob, tsolo, tmute]);



		canvas.layout.insert(temp, index2 + 2); //+2 offset +1 para ponerlo despues +1 por DragSink inicio
		canvas.layout.add(nil);
		inc = inc + 1;

	}

	modclear {|ttrack|

		synthe.collect(_.run(true)); // si hay un module en Mute lo activa para despues free
		group.set(\free, 0);
		canvas.removeAll;
		container = List[]; //clear list
		synthe = List[]; //clear list
		solo = List[]; //clear list
		mainparam = List[];
		listmodules = List[];
		specs = List[];
		store = List[];
		index = List[];
		specs = List[];
		multispec = List[];

		if (modwin.size != 0) {
			modwin.collect({|mod|
				if (mod.notNil) {
					if (mod.parent.notNil){
						mod.parent.view.deleteOnClose = true;
						mod.parent.close}
				}
			})
		};
		modwin = List[];
		modgui = List[];
		buffer.free;
		// bus.free;
		params = nil;
		paramsmulti = nil;
		others = nil;

		TModuleGUI2.wwidth = 540;
		TModuleGUI2.wheight = 0;
		TModuleGUI2.widthpos = 1;

		//bus.index.postln;
		inc = 0;
		this.insertinit;

	}

	modalGui { |presetreplace|
		/*var x, a, presetname;
		var lista = Array.fill(params.size,0);
		var listamulti = Array.fill(multi.size,0);
		var merge = Array.new(params.size+multi.size, 0);*/
		/* //NO FUNCIONA EN QT
		x = SCModalSheet.new(parentwin, 250@70);
		a = TextField(x, Rect(10, 20, 150, 20));
		a.background_(Color.grey);
		a.stringColor_(Color.white);
		a.string = "preset";
		a.action = {arg field; field.value.postln};
		*/
		var x, a, presetname, slidparams, store2, store3, store4, fadestore, spec1 = List[];

		fadestore = listmodules.collect(_[1]).deepCopy;

		slidparams = listmodules.collect(_[2]).deepCopy;
		slidparams.postln;

		store2 = slidparams.collect(_.collect(_.value)).postln;
		"----------------------".postln;
		"----------------------".postln;
		modwin.postln;
		modwin.collect(_.objmultislider).postln;
		"----------------------".postln;
		"----------------------".postln;

		modwin.collect({|mdw, i|
			if (mdw.objmultislider.notNil) {
				store3[i] = mdw.objmultislider;
			};
		});
		/*		if (modwin.objmultislider.notNil) {
		store3 = modwin.collect(_.objmultislider);
		};*/

		modwin.collect({|mdw, i|
			if (mdw.soundview_class.notNil) {
				store4[i] = mdw.soundview_class;
			};
		});
		/*		if (modwin.soundview_class.notNil) {
		store4 = modwin.collect(_.soundview_class); //buffers
		};*/

		specs.collect({|spval, i|
			spec1.add(spval.collect({|x| x.value}))
		});

		spec1.postln;
		spec1.collect({|l1, i|
			var st, sp;
			st = store2[i];
			sp = l1;
			/*st.postln;
			sp.postln;*/

			sp.collect({|spt, i|
				var t;
				/*spt.postln;
				st[i].postln;*/
				st[i] = spt.map(st[i].value);

			});
			//slidparams[i] = st;
			store[i].put(2, st);
			store[i].put(1, fadestore[i]);
		});

		store3.collect({|l1, i|
			if (l1.size != 0) {
				store[i].insert(3, l1)
			}
		});

		store4.collect({|mod, i|
			if (mod.notNil) {
				store[i].put(4, \buf -> mod.buf.path);
			}
		});


		store.postln;

		if (presetreplace.isNil) {

			x = Window.new("new preset", 250@70);
			a = TextField(x, Rect(10, 20, 150, 20));
			a.background_(Color.grey);
			a.stringColor_(Color.white);
			a.string = "preset";
			a.action = {|txt| presetname = txt.value.postln};
			presetname.postln;
			x.front;
			Button(x, Rect(170,20,25,25))
			.states_([
				["Ok", Color.black, Color.grey]
			])
			.onClose_({"New preset!".postln;
			})
			.action_({ x.close ;

				store.writeArchive(presetpath ++ a.string);
				menuitems.add(a.string);
				menuitems.postln;
				menu.items = menuitems;
				menu.value = menuitems.indexOfEqual(a.string);


			});
		} {
			store.writeArchive(presetpath ++ menu.item);
		}

	}

	menucollect {

		var myPath;
		myPath = PathName.new(presetpath);
		^myPath.files.collect(_.fileNameWithoutExtension);


	}

	addaux {|aux, auxindex|
		if (auxbus.detect({|x| x.key == aux.asSymbol}).isNil) {
			this.addauxindex(aux, auxindex, auxguindexincr);
			auxguindexincr.postln;
			auxguindexincr = auxguindexincr + 1;
		}

	}

	addauxindex {|aux, auxindex, auxguindex|
		var auxknob;

		auxknob = "auxknob"++auxguindex;
		auxView.layout.insert(
			View().background_(Color.rand).layout_(HLayout(auxknob = Knob().maxHeight_(20).maxWidth_(25).mode_(\vert).value_(dbspec.unmap(0)).action_({|v|
				auxbus[auxguindex].value.set(\amp, dbspec.map(v.value).postln);
				auxguindex.postln;
				auxbus[auxguindex];
				}).mouseDownAction_({|uvw, x, y,modifiers, buttonNumber, clickCount| //double-click
					if (clickCount == 2) {
						//tracksitem.modwin.parent.front
						//selitem.modwin[moduleitem].parent.front;
						auxknob.value = dbspec.unmap(0);
						//"Knob-doble-click".postln;
					};
					//if ((clickCount == 1) && (~interfaces.size == 1)) {"totototototooto".postln; ~tgraphsynthslist.value = nil};
				}), Button().maxHeight_(20).maxWidth_(48).states_([[aux.asString]]), Button().maxHeight_(15).maxWidth_(15).states_([["-"]]).action_({
					var auxtempindex;
					auxtempindex = auxbusguindex.indexOf(auxguindex);
					"_________________".postln;
					auxtempindex.postln;
					"_________________".postln;
					auxView.children.postln;
					auxbus.postln;
					auxguindex.postln;
					auxInc.postln;
					auxbusguindex.postln;
					auxbus[auxtempindex].value.set(\gatee, 0); //libera node
					auxbus.removeAt(auxtempindex);
					auxView.children[auxtempindex].remove;
					auxbusguindex.removeAt(auxtempindex);
					auxInc = auxInc - 1;


				})
		).margins_([0, 1, 0, 1]).spacing_(0)), auxInc); // "Aux"++(auxInc+1)
		auxView.layout.add(nil);



		auxbus.insert(auxguindex, aux.asSymbol -> AudioInAux.synthInAux(group, bus.index, auxindex, bus.index)); // evita crear mas aux del mismo index
		auxbusguindex.insert(auxguindex, auxguindex);
		/*auxindex.postln;
		bus.index.postln;*/
		//insynth = AudioInAux.synthInAux(group, bus.index, auxindex, bus.index);
		//auxbus.postln;
		auxbus.postln;
		auxInc = auxInc + 1;

	}



	removeaux {|aux|
		var removeauxtempidx;
		/*aux.class.postln;
		aux.postln;*/
		//auxbus.postln;
		removeauxtempidx = auxbus.collect(_.key).indexOf(aux);
		auxbus.detect({|x| x.key == aux}).value.set(\gatee, 0); //libera node
		/*auxbus.detect({|x| x.key == aux}).value.postln;
		"removiendo-aux".postln;
		removeauxtempidx.postln;*/
		auxbus.removeAt(removeauxtempidx);
		auxView.children[removeauxtempidx].remove;
		auxbusguindex.removeAt(removeauxtempidx);
		auxInc = auxInc - 1;

	}

}


