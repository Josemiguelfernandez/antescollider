T {

	var wind, <>tracks, trackscount, tracksview, tracksitem, trackindex, moduleview, moduleitem, paramsview, selectrack, selitem, parentview, buses, busview, busesobj, renametext, renamewin, hview, auxview, brwwind, sndwwind, contextSlider, slidwin, selparam, param_numb, interfaces, interfaces_names;
	var aux, auxcount, auxcview, auxcitem, auxbuses, auxviewlist, auxmoduleview, auxreceiveview, selauxitem, selectaux, auxmoduleitem, auxinput, selectauxkey, guindex;
	var auxreceiveitem, server, plotwin, plotcomposite, mastereq, eq_on_off, file_device_pathI, deviceI, file_deviceI, file_device_pathO, deviceO, file_deviceO;

	var dbspec, faderwin;
	var dlI, dlrI, dlO, dlrO;


	*new {
		^super.newCopyArgs().initT();
	}

	initT {

		server = Server.default;
		interfaces = (Platform.userAppSupportDir ++ "/TSupport/Interfaces/*").pathMatch;
		interfaces_names = interfaces.collect({|name|
			name.basename.drop(-4); //remove extension .scd
		});

		///////////////////////////////////// menu devices

		file_device_pathI = (Platform.userAppSupportDir ++ "/TSupport/pref_audio_In_device.txt");
		file_device_pathO = (Platform.userAppSupportDir ++ "/TSupport/pref_audio_Out_device.txt");

		// file_device = File(Platform.userAppSupportDir ++ "/TSupport/pref_audio_device.txt", "w");

		if( File.exists(file_device_pathI))
		{ File.use(file_device_pathI, "r", { |file|
			deviceI = file.readAllString;
			Server.local.options.inDevice = deviceI;
			~serverInDevice = deviceI;
			deviceI.postln;
		});
		}{file_deviceI = File.new(file_device_pathI, "w+");
			file_deviceI.write("Built-in Microph");
			file_deviceI.close;
		};

		dlI = ServerOptions.inDevices.collect(_.asSymbol); //converto to symbol
		/*if(dlI.indexOf(deviceI.asSymbol).notNil) {
		dlrI = dlI.indexOf(deviceI.asSymbol);
		dlI.removeAt(dlrI);

		}*/

		// dlI = dlI.collect(_.asString);

		///////////////

		if( File.exists(file_device_pathO))
		{ File.use(file_device_pathO, "r", { |file|
			deviceO = file.readAllString;
			Server.local.options.outDevice = deviceO;
			~serverOutDevice = deviceO;
			deviceO.postln;
		});
		}{file_deviceO = File.new(file_device_pathO, "w+");
			file_deviceO.write("Built-in Output");
			file_deviceO.close;
		};

		dlO = ServerOptions.outDevices.collect(_.asSymbol); //converto to symbol
		/*if(dlO.indexOf(deviceO.asSymbol).notNil) {
		dlrO = dlO.indexOf(deviceO.asSymbol);
		dlO.removeAt(dlrO);

		}*/

		// dlO = dlO.collect(_.asString);


		// dl.postln;
		/*		dl = ServerOptions.devices.collect(_.asSArray); //converto to array
		dl.postln;*/
		///////////////////////////////////// menu devices


		// tracks = Dictionary.new;
		tracks = List[];
		trackindex = List[];
		buses = List[];
		busesobj = List[];


		aux = List[];
		auxbuses = List[];
		auxinput = Dictionary.new;

		eq_on_off = 'off';

		trackscount = 0;
		auxcount = 0;
		guindex = 0;

		wind =Window("T v.0", Rect(385, 550, 300, 500))
		.background_(Color.new255(120, 120, 120))
		.alpha_(0.9)
		.layout_( VLayout(
			View()   //.maxHeight_(50).fixedWidth_(308)
			.background_(Color.rand).layout_(
				VLayout(
					HLayout(
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["Browser", Color.black, Color.gray], ["Browser", Color.black, Color.green]]).action_({|val|
							if(val.value == 1) {
								brwwind = TBrowser.new(tracks)
							}{
								brwwind.win.close;
							}
						}),
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["SoundsFiles", Color.black, Color.red]]).action_({|val|
							// val.value.postln;
							if(val.value == 0) {
								sndwwind = TLoadSounds.new()
						}}),
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["Ctrl Levels", Color.black, Color.gray(0.6)]]).action_({
							MiServerMeter(server);}),
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["INIT", Color.black, Color.gray(0.6)]]).action_({
							(Platform.userAppSupportDir ++ "/TSupport/TInit.scd").load;
							/////////////INIT///////////////////////
							/*var vbap_speakers;
							"Init-starting....".postln;
							~tsynths = IdentityDictionary.new;
							~interfaces = IdentityDictionary.new;
							~windgraph = IdentityDictionary.new;
							~slides_params = IdentityDictionary.new;
							~multislides_params = IdentityDictionary.new;
							~multislides_obj = IdentityDictionary.new;
							~menu_obj = IdentityDictionary.new;
							~soundfileview_buf = IdentityDictionary.new;
							~tmodules = IdentityDictionary.new;
							~touts = Dictionary[\out0 -> 0, \out1 -> 1, \out2 -> 2, \out3 -> 3, \out4 -> 4, \out5 -> 5, \out6 -> 6, \out7 -> 7, \out8 -> 8];
							~connections = Dictionary.new;
							~buffers  = IdentityDictionary.new;
							~bufft  = IdentityDictionary.new;
							~wave_buff = IdentityDictionary.new;

							~buffers.put(\onset_buf, Buffer.alloc(server, 512)); // for Onset detection buffer 0

							40.do({|val| // crea 20 buffers y los pone en un diccionario hay que estar seguro que son los 20 primeros buffers!!!!!
							~buffers.put((\granbuf++val).asSymbol, Buffer.alloc(server, server.sampleRate*0.5, 1))

							});


							// creacion de envolventes buffer 41 a 48 REVISAR!!!

							~envs = Array.fill(11); // number of envs
							[Env([0, 1, 0], [0.5, 0.5], [8, -8]), Env([0, 1, 0], [0.5, 0.5], [-4, 4]), Env([0, 1, 0], [0.1, 0.9], [8, -8]), Env([0, 1, 0], [0.9, 0.1], [8, 2]), Env([0,1,0.9,0], [0.1,0.5, 1].normalizeSum,[-5,0,-5]), Env([0,1,0.5,0.9, 0], [0.5,0.2, 0.2, 0.5].normalizeSum,[4,4,2, -4]), Env([0,0.7,0.3,1, 0], [0.5,0.2, 0.2, 0.2].normalizeSum,[4,-4,2, -4]), Env([0,1,1,0], [0.005,0.99, 0.005].normalizeSum,[4,0, -4]), Env([0,1,1,0], [0.005,0.99, 0.1].normalizeSum,[4,0, 4]), Env([0,1,1,0], [0.05,0.99, 0.05].normalizeSum)].do({|e i|
							~envs[i] = Buffer.sendCollection(server, e.discretize, 1);
							});
							~envs[10] = Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/buffers/noise_env.aif"); // env from noise soundfile



							80.do({|val| // crea 40 buffers para fft freeze 2048
							if (val< 40){
							~bufft.put((\bufft++val).asSymbol, Buffer.alloc(server,2048));
							} {
							~bufft.put((\bufft++val).asSymbol, Buffer.alloc(server, 1024))
							}
							});

							// ~bufft[\bufft58].plot
							10.do({|val| // crea 10 buffers de 2 segundos a partir de 132
							~buffers.put((\granbuf_2++val).asSymbol, Buffer.alloc(server, server.sampleRate*2, 1))

							});


							// ~bufft.postItems
							// ~buffers[\granbuf_24]

							/*40.do({|val| // crea 40 buffers para fft freeze 1024
							~bufft.put((\bufft++val).asSymbol, Buffer.alloc(server, 1024))});*/

							// buffers para wavetable synthesis

							~wave_buff.put(\multi1, Buffer.read(server, "/Users/jose/Documents/Composicion-Claude-2014/buffers-synth/Multi17-4.aif"));
							~wave_buff.put(\freeze1, Buffer.read(server,"/Users/jose/Documents/Composicion-Claude-2014/buffers-synth/granbuf1.aif"));
							~wave_buff.put(\slap_la2, Buffer.read(server, "/Users/jose/Documents/Composicion-Claude-2014/buffers-synth/slap-la2-Mono.aif"));
							~wave_buff.put(\slap_do3, Buffer.read(server, "/Users/jose/Documents/Composicion-Claude-2014/buffers-synth/slap-do3.aif"));
							~wave_buff.put(\slap_la3, Buffer.read(server, "/Users/jose/Documents/Composicion-Claude-2014/buffers-synth/slap-lab3.aif"));
							~wave_buff.put(\multi111, Buffer.read(server, "/Users/jose/Documents/Composicion-Claude-2014/buffers-synth/Multi111.aif"));

							~wave_buff.put(\re2_1, Buffer.read(server, "/Users/jose/Documents/Composicion-Claude-2014/buffers-synth/re2-1.aif"));
							~wave_buff.put(\re2_2, Buffer.read(server, "/Users/jose/Documents/Composicion-Claude-2014/buffers-synth/re2-2.aif"));

							vbap_speakers = VBAPSpeakerArray.new(2, [0, 45, 90, 135, 180, -135, -90, -45]); // 8 channel ring
							// b = a.loadToBuffer;
							~wave_buff.put(\vbap, vbap_speakers.loadToBuffer);

							~buffers.put(\octa1, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/Multi23-larg-BUENO.aif"));
							~buffers.put(\octa2, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/ecrase-derriere-chevalet.aif"));
							~buffers.put(\octa3, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/sonido17.aif"));
							~buffers.put(\octa4, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/cherokee.aif"));
							// ~buffers.put(\octa5, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/double-bass-gettato1.aif"));
							~buffers.put(\octa5, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/traspPM-perc.wav"));

							~buffers.put(\octa6, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/prova1gran.wav"));
							~buffers.put(\octa7, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/ecrase-derriere-chevalet1.aif"));

							~buffers.put(\octa8, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/PM-perc-render0-M13.wav"));
							~buffers.put(\octa9, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/glis_036-trasp.wav"));
							~buffers.put(\octa10, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/tramaAcuta+metalAudio13.wav"));

							~buffers.put(\octa11, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/oscillaz.wav"));



							~files = (Platform.userAppSupportDir ++ "/sounds/").standardizePath();
							~files2 = PathName.new(~files);
							~soundfolder = ~files2.folders;

							~soundfolder.collect({ |folders|
							folders.files.collect({ |filepath|
							var file_name;
							file_name = filepath.fileName;
							file_name.postln;
							~buffers.put(file_name.asSymbol, Buffer.read(Server.default, filepath.fullPath));
							})

							});

							// ~buffers["double-bass-gettato1.aif".asSymbol]
							// ~files2.files.collect({ |filepath|
							// 	var file_name;
							// 	file_name = filepath.fileName;
							// 	filepath.fullPath.postln;
							//
							// 	~buffers.put(file_name.asSymbol, Buffer.read(Server.default, filepath.fullPath));
							// });

							///gravity_synth //GravityGrid

							~buffers.put(\gravity1, Buffer.alloc(server, 9, 1));
							~buffers.put(\gravity2, Buffer.alloc(server, 9, 1));
							~buffers.put(\gravity3, Buffer.alloc(server, 9, 1));

							~buffers[\gravity1].setn(0,Array.rand(9,0.1,0.9));
							~buffers[\gravity2].setn(0,Array.rand(9,0.1,0.9));
							~buffers[\gravity2].setn(0,Array.rand(9,0.1,0.9));



							// ~buffers[\octa4].plot;
							// ~wave_buff[\vbap].plot

							///// Write Buffers ======== ~buffers[\granbuf1].write("/Volumes/AuDiO-750Go/Composicion-Claude-2014/buffers-synth/granbuf1.aif", headerFormat: "aiff", sampleFormat: "int24", numFrames: -1, startFrame: 0, leaveOpen: false);
							// Buffer.freeAll
							/*~buffers[\granbuf6]
							~buffers.keys.asArray[0].class*/

							~fftsize=2048; // also 4096 works on my machine; 1024 too often and amortisation too pushed, 8192 more high load FFT


							{
							var ir, irbuffer, bufsize;

							// // MONO ONLY

							// irbuffer = Buffer.read(s, Platform.resourceDir +/+ "sounds/8.7s_Big_Gothic_Church.L.aif");


							// synthesise the honourable 'Dan Stowell' impulse response

							ir = ([1] ++0.dup(100) ++ ((1, 0.99998 .. 0).collect{|f| f =
							f.squared.squared; f = if(f.coin){0}{f.squared}; f =
							if(0.5.coin){0-f}{f} } * 0.1)).normalizeSum;


							irbuffer = Buffer.loadCollection(server, ir);

							server.sync;

							bufsize= PartConv.calcBufSize(~fftsize, irbuffer);

							// ~numpartitions= PartConv.calcNumPartitions(~fftsize, irbuffer);

							~irspectrum= Buffer.alloc(server, bufsize, 1);

							~irspectrum.preparePartConv(irbuffer, ~fftsize);

							server.sync;

							irbuffer.free; // don't need time domain data anymore, just needed spectral version
							}.fork;

							///////////////////////faders



							dbspec = ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB);
							faderwin =Window("Levels", Rect(128, 64, 560, 360)).layout_( GridLayout.columns(
							[StaticText().string_("Synths1"),~slider1 = Slider().value_(dbspec.unmap(0)).action_({|sld|
							~fader1.values.collect({|val|
							val[1].set(\amp, dbspec.map(sld.value))});
							~textoutputlev1.string_(dbspec.map(sld.value).round(0.1));
							}), ~textoutputlev1 = StaticText().maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
							[nil, ~fade1 = LevelIndicator().drawsPeak_(true).maxWidth_(30)],

							[StaticText().string_("Synths2"),~slider2 = Slider().value_(dbspec.unmap(0)).action_({|sld|
							~fader2.values.collect({|val|
							val[1].set(\amp, dbspec.map(sld.value))});
							~textoutputlev2.string_(dbspec.map(sld.value).round(0.1));
							}), ~textoutputlev2 = StaticText().maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
							[nil, ~fade2 = LevelIndicator().drawsPeak_(true).maxWidth_(30)],

							[StaticText().string_("fl"),~slider3 = Slider().value_(dbspec.unmap(0)).action_({|sld|
							~fader3.values.collect({|val|
							val[1].set(\amp, dbspec.map(sld.value))});
							~textoutputlev3.string_(dbspec.map(sld.value).round(0.1));
							}), ~textoutputlev3 = StaticText().maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
							[nil, ~fade3 = LevelIndicator().drawsPeak_(true).maxWidth_(30)],

							[StaticText().string_("sax"),~slider4 = Slider().value_(dbspec.unmap(0)).action_({|sld|
							~fader4.values.collect({|val|
							val[1].set(\amp, dbspec.map(sld.value))});
							~textoutputlev4.string_(dbspec.map(sld.value).round(0.1));
							}), ~textoutputlev4 = StaticText().maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
							[nil, ~fade4 = LevelIndicator().drawsPeak_(true).maxWidth_(30)],

							[StaticText().string_("trb"),~slider5 = Slider().value_(dbspec.unmap(0)).action_({|sld|
							~fader5.values.collect({|val|
							val[1].set(\amp, dbspec.map(sld.value))});
							~textoutputlev5.string_(dbspec.map(sld.value).round(0.1));
							}), ~textoutputlev5 = StaticText().maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
							[nil, ~fade5 = LevelIndicator().drawsPeak_(true).maxWidth_(30)],

							[StaticText().string_("vla"),~slider6 = Slider().value_(dbspec.unmap(0)).action_({|sld|
							~fader6.values.collect({|val|
							val[1].set(\amp, dbspec.map(sld.value))});
							~textoutputlev6.string_(dbspec.map(sld.value).round(0.1));
							}), ~textoutputlev6 = StaticText().maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
							[nil, ~fade6 = LevelIndicator().drawsPeak_(true).maxWidth_(30)],

							[StaticText().string_("cb"),~slider7 = Slider().value_(dbspec.unmap(0)).action_({|sld|
							~fader7.values.collect({|val|
							val[1].set(\amp, dbspec.map(sld.value))});
							~textoutputlev7.string_(dbspec.map(sld.value).round(0.1));
							}), ~textoutputlev7 = StaticText().maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
							[nil, ~fade7 = LevelIndicator().drawsPeak_(true).maxWidth_(30)],

							[StaticText().string_("Master"),~slider8 = Slider().value_(dbspec.unmap(0)).action_({|sld|
							/*									~fader8.values.collect({|val|
							val[1].set(\amp, dbspec.map(sld.value))});*/
							server.volume = dbspec.map(sld.value);
							~textoutputlev8.string_(dbspec.map(sld.value).round(0.1));
							}), ~textoutputlev8 = StaticText().maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
							[nil, ~fade8 = LevelIndicator().maxWidth_(30)]
							)).front;


							///////////////////////faders

							"Init-end....".postln;
							/////////////INIT///////////////////////
							}),*/
						}),
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["Boot", Color.black, Color.gray], ["Boot", Color.black, Color.green]]).action_({|val|
							if(val.value == 1) {
								server.boot;
								server.waitForBoot({
									~scservers.add(\local -> server);
									~scservers_groups.add(\local -> List[]);
								})
							}{
								server.quit;
							}
						}),

						nil
					),
					HLayout(

						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["Envs", Color.black, Color.red]]).action_({
							plotwin = Window("plot panel", Rect(20, 30, 520, 250));
							plotcomposite = CompositeView(plotwin, Rect(10, 35, 490, 200)).background_(Color.rand(0.7)).resize_(5);

							Plotter("plot", parent: plotcomposite).value_([0, 1, 2, 3, 4].scramble * 100);
							plotwin.front;
						}),
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["Tree", Color.black, Color.gray(0.6)]]).action_({
							server.plotTree
						}),
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["Master-lev", Color.black, Color.gray(0.6)]]).action_({
							server.volume.gui
						}),
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["OSC-receiver", Color.black, Color.gray(0.6)]]).action_({
							(Platform.userAppSupportDir ++ "/TSupport/OSC_Receiver.scd").load; // load OSC_Receiver.scd file
							// ("/Users/jose/Documents/Projet_Recherche/OSC_Receiver.scd").load;
						}),
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["Aux", Color.black, Color.gray], ["Aux", Color.black, Color.green]]).action_({|val|
							//val.value.postln;
							hview.children.postln;
							if(val.value == 1) {
								if (hview.children[8].isNil){ // 7 es la posicion vacia a partir de la cual se pueden generar mas childrens AQUI HABIA ERROR!!!
									// "dibuja_auxiliares".postln;
									wind.setInnerExtent(700, 500);
									hview.layout.add(
										auxview = View()   //.maxHeight_(50).fixedWidth_(308)
										.background_(Color.rand).layout_(
											HLayout(
												VLayout(
													Button().maxHeight_(22).fixedWidth_(50).font_(Font("Helvetica", 11)).states_([["New", Color.black, Color.green]]).action_({
														aux.insert(auxcount, (\aux_++(auxcount+1)).asSymbol -> TAux.new(\aux_++(auxcount+1)));

														auxviewlist.items = aux.collect({|x| x.key});


														auxcount = auxcount + 1;

													}),
													Button().maxHeight_(22).fixedWidth_(50).font_(Font("Helvetica", 11)).states_([["Rename", Color.black, Color.red]]).action_({|val|
														aux.postln;
														auxbuses.postln;

													})
													,
													Button().maxHeight_(22).fixedWidth_(50).font_(Font("Helvetica", 11)).states_([["Delete", Color.black, Color.gray(0.6)]]).action_({
														// tracksitem.postln;

														auxcitem.collect({|ax, i|


															ax.win.view.deleteOnClose = true;
															ax.win.close;
															ax.modclear;
															ax.group.free;
															ax.bus.free;
															//auxinput.postln;
															//auxinput.removeAt(auxviewlist.items[selectaux][i]);
														});


														"tototototo".postln;
														selectauxkey.postln;
														auxinput[selectauxkey].postln;
														auxinput[selectauxkey].collect({|tr|
															auxviewlist.items[selectaux].collect({|au| //remove Tracks from aux
																if (auxinput[au].notNil) {
																	if (auxinput[au].includes(tr) == true) {

																		tracks.detect({|x| x.key == tr.asSymbol}).value.removeaux(au);

																	}
																}
															});
														});
														/*auxinput[selectauxkey].collect({|tr|
														tracks.detect({|x| x.key == tr.asSymbol}).value.removeaux(selectauxkey);

														});*/
														auxinput.removeAt(selectauxkey);
														auxreceiveview.items = [];
														selauxitem.postln;
														auxcitem.postln;
														selectaux.postln;
														"tototototo".postln;

														selectaux.collect({|idx, i| // remove from an index
															aux.removeAt(idx-i);

															/*busview.children[idx-i].remove;
															busview.layout.add(nil, 1);*/
														});


														aux.postln;

														if (aux.size != 0) {
															auxviewlist.items = aux.collect(_.key);
															auxmoduleview.items = [];
															auxreceiveview.items = [];


														}{
															auxviewlist.items = [];
															auxmoduleview.items = [];
															// paramsview.items = [];

															auxcitem = 0;

															auxcount = 0;

														};


														//auxreceiveview.items = union.asArray;

													}),
													nil
												).spacing_(2),
												nil,
												auxreceiveview = ListView().background_(Color.grey)
												.canReceiveDragHandler_({ View.currentDrag.isArray})
												.receiveDragHandler_({|drag|
													var union;
													/*View.currentDrag.value.postln;*/
													/*"totototototo".postln;*/
													if (auxviewlist.items.size != 0) {

														// auxreceiveview.items = View.currentDrag.value;
														union = View.currentDrag.asSet | auxinput[selectauxkey.asSymbol].asArray.asSet; // interseccion
														// selectaux.postln;
														// auxviewlist.items[selectaux].postln;
														// selectauxkey.asSymbol.postln;
														// selauxitem.postln;
														// auxcitem.postln;
														// View.currentDrag.postln;
														//union.asArray.postln;
														//union.asArray.postln;
														union.asArray.collect({|tr|

															/*tr.postln;
															auxcitem.postln;
															auxviewlist.items[selectaux].postln;*/

															auxcitem.collect({|au, i|
																tracks.detect({|x| x.key == tr.asSymbol}).value.addaux(auxviewlist.items[selectaux][i], au.bus.index); //   addaux(tr.asString)
																auxinput.put(auxviewlist.items[selectaux][i].asSymbol, union.copy);
															});

														});

														// selauxitem.bus.postln;
														// auxinput.put(selectauxkey.asSymbol, union);
														auxreceiveview.items = union.asArray;
														//tracks.postln;

													}
												})
												.selectionMode_(\extended)
												.selectionAction_({|sbs|

													//auxreceiveitem = auxreceiveview.selection.postln;

												})
												.keyDownAction_({|view, char, modifiers, unicode, keycode, key|
													var auxreceivetempdelete;
													case
													{unicode == 8} {"delete".postln;
														auxreceiveview.selection.postln;
														//auxreceiveview.selection.last.postln;
														auxreceivetempdelete = auxreceiveview.items[auxreceiveview.selection].postln;

														auxinput.postln;
														selectauxkey.postln;


														auxinput[selectauxkey].postln;

														// selectauxkey.postln;

														auxreceivetempdelete.collect({|tr|
															// selectauxkey.postln;
															"---------------------".postln;
															tr.postln;
															auxviewlist.items[selectaux].postln;

															//auxinput.postln;
															auxviewlist.items[selectaux].collect({|au| //remove Tracks from aux
																//"AAAAAAAAAAAAAAAAAAAAAAAAAAAA".postln;
																//auxinput[au].postln;
																/*au.postln;
																au.class.postln;
																auxinput.postln;
																auxinput.keys.postln;
																auxinput.keys.collect(_.class.postln);
																auxinput[\aux_1].postln;
																auxinput[au.asSymbol].postln;*/
																if (auxinput[au].notNil) {
																	if (auxinput[au].includes(tr) == true) {

																		tracks.detect({|x| x.key == tr.asSymbol}).value.removeaux(au);
																		//auxinput[au].remove(tr);
																		/*auxinput[au].postln;
																		au.postln;
																		auxinput.postln;
																		tr.postln;*/
																	}
																}
															});


															"---------------------".postln;
														});


														auxreceivetempdelete.collect({|tr|
															auxviewlist.items[selectaux].collect({|au|
																if (auxinput[au].notNil) {
																	if (auxinput[au].includes(tr) == true) {
																		"$$$$$$$$$$$$$$$$$$$$$$$".postln;
																		auxinput.postln;
																		auxreceivetempdelete.postln;
																		auxviewlist.items[selectaux].postln;
																		auxinput[au].postln;
																		auxinput[au].remove(tr);
																		//auxinput[\aux_1].remove(\track_1);
																		auxinput.postln;
																		au.postln;
																		tr.postln;


																		"$$$$$$$$$$$$$$$$$$$$$$$".postln;
																	}
																}
															})
														});
														auxreceiveview.items = auxinput[selectauxkey].asArray; //limpia list

													};
													//char.postln;
													unicode.postln;
												})
												.mouseDownAction_({|uvw, x, y,modifiers, buttonNumber, clickCount| //double-click
													if (clickCount == 2) {


													};

												})


												,


												auxviewlist = ListView().background_(Color.grey)
												.selectionAction_({|sbs|

													if (auxcitem != 0) {
														auxcitem = aux.collect({|x| x.value})[auxviewlist.selection];
														if( auxcitem.size != 0) {
															selauxitem = auxcitem.last;

															/*	tracksitem.postln;
															tracksitem.size.postln;
															sbs.postln;
															sbs.value.postln;*/
															selauxitem.win.front;


														}
														//moduleview.items = tracksitem[sbs].listmodules.collect(_.[0]);
														/*	tracksitem.postln;
														tracksitem[sbs].postln;*/

													}{
														auxcitem = [];
													};


													selauxitem.win.front; //selecciona track window
													selectaux = auxviewlist.selection.postln;
													//auxcitem.postln;
													"---------------------".postln;
													selectauxkey = aux.collect(_.key)[selectaux.last].postln;
													auxinput[selectauxkey.asSymbol].postln;
													auxreceiveview.items = auxinput[selectauxkey.asSymbol].asArray;
													auxinput.postln;
													// auxviewlist.items[selectaux].postln;
													"---------------------".postln;

												})
												.canReceiveDragHandler_({ View.currentDrag.isString })
												.beginDragAction_({|listView|

												})
												.selectionMode_(\extended)
												.keyDownAction_({|view, char, modifiers, unicode, keycode, key|
													char.postln})
												.mouseDownAction_({|uvw, x, y,modifiers, buttonNumber, clickCount| //double-click
													if (clickCount == 2) {


													};

												}),



												auxmoduleview = ListView().background_(Color.black).selectionMode_(\extended)
												.selectionAction_({|sbs|

													// tracksitem = tracks[tracksview.items[sbs.value]];
													auxmoduleitem = sbs.value;

													auxmoduleitem.postln;

													//paramsview.items = selitem.specs[moduleitem].collect(_.key).postln;


												})
												/*.canReceiveDragHandler_({ View.currentDrag.isString })
												.beginDragAction_({|listView|
												//listView.items[ listView.value ].debug("begun dragging");

												//[\g_02_TTestSynth, \t_01_TMFxRev ]
												//[0, 1, 2, 3];
												})*/

												.keyDownAction_({|view, char, modifiers, unicode, keycode, key|
													// unicode.postln;
													if (unicode == 119) {
														// selitem.modwin[moduleitem].postln;//but_win_mod.valueAction_(1);
														selauxitem.but_win_mod.valueAction_(1);
														char.postln
													}
												})
												.mouseDownAction_({|uvw, x, y,modifiers, buttonNumber, clickCount| //double-click
													if (clickCount == 2) {
														//tracksitem.modwin.parent.front
														selauxitem.modwin[auxmoduleitem].parent.front;

													};
													//if ((clickCount == 1) && (~interfaces.size == 1)) {"totototototooto".postln; ~tgraphsynthslist.value = nil};
												}),
											).spacing_(0).margins_([5, 0, 5, 0])
									));

									auxview.postln;
									hview.children.postln;
								}{
									auxview.visible_(true);
								}
							}{
								auxview.visible_(false);
								/*hview.children.postln;
								hview.children[6].visible_(false);
								hview.children[7].visible_(false);
								hview.children[8].visible_(false);*/
								{0.005.wait;{wind.setInnerExtent(300, 500)}.defer}.fork;
								/*//hview.refresh;

								wind*/
								/*wind.setInnerExtent(300, 500);
								wind.setInnerExtent(300, 500);
								wind.setInnerExtent(300, 500);*/
							}
						}),
						nil
					),

					HLayout(
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["Rec", Color.black, Color.green]]).action_({
							ServerRecordWindow( server ); // from weslib
						}),
						PopUpMenu().maxHeight_(22).maxWidth_(70).items_(interfaces_names.addFirst("interfaces")).font_(Font("Helvetica", 11)).background_(Color.gray(0.6)).action_({|menu|
							// menu.item.postln;
							(Platform.userAppSupportDir ++ "/TSupport/Interfaces/"++menu.item++".scd").load;
						}),
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["MasterEQ", Color.black, Color.gray], ["MasterEQ", Color.black, Color.green]]).action_({|val|
							val.value.postln;
							eq_on_off.postln;
							if(val.value==1 && eq_on_off == 'off') {
								mastereq = MasterEQ.new(8);
								mastereq.window.userCanClose_(false);
								eq_on_off = 'on'
							};
							if(val.value==1 && eq_on_off == 'on') {
								mastereq.window.visible_(true);
							};
							if(val.value==0){
								mastereq.window.visible_(false);
								// eq_on_off = 'on'
							};

							/*if(mastereq.window.visible != false){
							mastereq = MasterEQ.new(8)
							}{mastereq.window.visible_(true);}
							}{mastereq.window.visible_(false);}*/
						}),
						nil,
						nil,
						Button().maxHeight_(22).fixedWidth_(70).font_(Font("Helvetica", 11)).states_([["Errors", Color.black, Color.gray], ["Errors", Color.black, Color.green]]).action_({|val|
							// val.value.postln;
							// eq_on_off.postln;
							if(val.value==1) {
								{thisThread.exceptionHandler = { 0 }}.defer;
							}
							{
								{thisThread.exceptionHandler = nil}.defer;
							}
						}),
						nil

						/// no envia errores a pos window


					),
					HLayout(
						StaticText().string_("In"),
						PopUpMenu().maxHeight_(20).maxWidth_(160).items_(dlI.addFirst(deviceI)).font_(Font("Helvetica", 11)).background_(Color.gray(0.6)).action_({|menu|
							server.options.inDevice = menu.item;
							~serverInDevice = menu.item;
							file_deviceI = File.new(file_device_pathI, "w+");
							file_deviceI.write(menu.item.asString);
							file_deviceI.close;
							// server.reboot;

						}),
						nil,
						StaticText().string_("Out"),
						PopUpMenu().maxHeight_(20).maxWidth_(160).items_(dlO.addFirst(deviceO)).font_(Font("Helvetica", 11)).background_(Color.gray(0.6)).action_({|menu|
							server.options.outDevice = menu.item;
							~serverOutDevice = menu.item;
							file_deviceO = File.new(file_device_pathO, "w+");
							file_deviceO.write(menu.item.asString);
							file_deviceO.close;
							// server.reboot;

						}),
						nil
					)


			).spacing_(5)),
			hview = View()   //.maxHeight_(50).fixedWidth_(308)
			.background_(Color.rand).layout_(
				HLayout(
					HLayout(
						VLayout(
							Button().maxHeight_(22).fixedWidth_(50).font_(Font("Helvetica", 11)).states_([["New", Color.black, Color.green]]).action_({
								tracks.insert(trackscount, (\track_++(trackscount+1)).asSymbol -> TTracks.new(\track_++(trackscount+1), aux));
								trackindex.insert(trackscount, trackscount);

								buses.insert(trackscount, busview.layout.insert(NumberBox().action_({|val|
									var trackscountint;
									trackscountint = trackscount;
									trackscountint.postln;
									//busview.layout.insert(NumberBox().action_({|val|
									val.value.postln;
									tracks[trackscount-1].value.postln;
									tracks[trackscount-1].value.numoutputbus.valueAction = busview.children[trackscount-1].value;
									busview.children.postln;
									trackscount.postln;
									trackscount.postln;
									tracks.postln;
									tracks[trackscount].postln; //textoutputlev.valueAction_(val.value);

								}).fixedHeight_(15).font_(Font("Helvetica", 11)), trackscount));
								busview.layout.add(nil);

								tracks.postln;
								tracksview.items = tracks.collect({|x| x.key});
								buses.postln;

								trackscount = trackscount + 1;
							}),
							Button().maxHeight_(22).fixedWidth_(50).font_(Font("Helvetica", 11)).states_([["New8ch", Color.black, Color.green]]).action_({ // 8 channels
								tracks.insert(trackscount, (\track_++(trackscount+1)).asSymbol -> TTracks8.new(\track_++(trackscount+1), aux));
								trackindex.insert(trackscount, trackscount);

								buses.insert(trackscount, busview.layout.insert(NumberBox().action_({|val|
									var trackscountint;
									trackscountint = trackscount;
									trackscountint.postln;
									//busview.layout.insert(NumberBox().action_({|val|
									val.value.postln;
									tracks[trackscount-1].value.postln;
									tracks[trackscount-1].value.numoutputbus.valueAction = busview.children[trackscount-1].value;
									busview.children.postln;
									trackscount.postln;
									trackscount.postln;
									tracks.postln;
									tracks[trackscount].postln; //textoutputlev.valueAction_(val.value);

								}).fixedHeight_(15).font_(Font("Helvetica", 11)), trackscount));
								busview.layout.add(nil);

								tracks.postln;
								tracksview.items = tracks.collect({|x| x.key});
								buses.postln;

								trackscount = trackscount + 1;
							}),
							Button().maxHeight_(22).fixedWidth_(50).font_(Font("Helvetica", 11)).states_([["New16ch", Color.black, Color.green]]).action_({ // 8 channels
								tracks.insert(trackscount, (\track_++(trackscount+1)).asSymbol -> TTracks16.new(\track_++(trackscount+1), aux));
								trackindex.insert(trackscount, trackscount);

								buses.insert(trackscount, busview.layout.insert(NumberBox().action_({|val|
									var trackscountint;
									trackscountint = trackscount;
									trackscountint.postln;
									//busview.layout.insert(NumberBox().action_({|val|
									val.value.postln;
									tracks[trackscount-1].value.postln;
									tracks[trackscount-1].value.numoutputbus.valueAction = busview.children[trackscount-1].value;
									busview.children.postln;
									trackscount.postln;
									trackscount.postln;
									tracks.postln;
									tracks[trackscount].postln; //textoutputlev.valueAction_(val.value);

								}).fixedHeight_(15).font_(Font("Helvetica", 11)), trackscount));
								busview.layout.add(nil);

								tracks.postln;
								tracksview.items = tracks.collect({|x| x.key});
								buses.postln;

								trackscount = trackscount + 1;
							}),
							Button().maxHeight_(22).fixedWidth_(50).font_(Font("Helvetica", 11)).states_([["NewKubus", Color.black, Color.green]]).action_({ // 8 channels
								tracks.insert(trackscount, (\track_++(trackscount+1)).asSymbol -> TTracksKubus.new(\track_++(trackscount+1), aux));
								trackindex.insert(trackscount, trackscount);

								buses.insert(trackscount, busview.layout.insert(NumberBox().action_({|val|
									var trackscountint;
									trackscountint = trackscount;
									trackscountint.postln;
									//busview.layout.insert(NumberBox().action_({|val|
									val.value.postln;
									tracks[trackscount-1].value.postln;
									tracks[trackscount-1].value.numoutputbus.valueAction = busview.children[trackscount-1].value;
									busview.children.postln;
									trackscount.postln;
									trackscount.postln;
									tracks.postln;
									tracks[trackscount].postln; //textoutputlev.valueAction_(val.value);

								}).fixedHeight_(15).font_(Font("Helvetica", 11)), trackscount));
								busview.layout.add(nil);

								tracks.postln;
								tracksview.items = tracks.collect({|x| x.key});
								buses.postln;

								trackscount = trackscount + 1;
							}),
							Button().maxHeight_(22).fixedWidth_(50).font_(Font("Helvetica", 11)).states_([["Rename", Color.black, Color.red]]).action_({|val|
								var track, trackname;
								track = tracks.collect(_.value)[selectrack][0];
								trackname = tracks.collect(_.key)[selectrack][0];
								trackname.postln;
								val.value.postln;
								tracks[selectrack].value.postln;
								if (val.value == 0) {

									renamewin = Window.new(border:false).front;
									renamewin.bounds = Rect(wind.bounds.left + 10, wind.bounds.top + 373 ,145,22);
									renamewin.alpha_(0.8);

									renametext = TextField(renamewin)
									.background_(Color.white)
									.string_(trackname.asString)
									.action_({|txt|
										tracks.put(selectrack, txt.value.asSymbol -> track);
										track.win.name_(txt.value);
										track.ttrack = txt.value;
										tracksview.items = tracks.collect({|x| x.key});
										renamewin.close;
										// renametext.remove
									})
								}


							})
							,
							Button().maxHeight_(22).fixedWidth_(50).font_(Font("Helvetica", 11)).states_([["Delete", Color.black, Color.gray(0.6)]]).action_({

								// tracksitem.postln;

								tracksitem.collect({|trk|

									if(trk.eq.notNil){
										trk.eq.gui.window.close; //cierra eq si abierto
									};

									trk.win.view.deleteOnClose = true;
									trk.win.close;
									trk.modclear;
									trk.group.free;
									trk.bus.free;

								});



								selectrack.postln;

								selectrack.collect({|idx, i| // remove from an index
									tracks.removeAt(idx-i);
									// buses[idx-i].remove;
									busview.children[idx-i].remove;
									busview.layout.add(nil, 1);
								});


								tracks.postln;
								//trackindex.removeAt(selectrack);
								/*tracksitem.postln;
								tracks.postln;
								"totototo".postln;
								tracks.size.postln;
								tracksview.postln;*/
								if (tracks.size != 0) {
									tracksview.items = tracks.collect({|x| x.key});
									moduleview.items = [];
									//tracksitem.postln;
									//tracksitem = tracks.collect({|x| x.value})[sbs.value];

								}{
									tracksview.items = [];
									moduleview.items = [];
									paramsview.items = [];
									//tracksitem.items = [];
									tracksitem = 0;
									//moduleview = 0;
									trackscount = 0;

								};



							}), nil
						).spacing_(2),
						HLayout(
							busview = View().fixedWidth_(20).background_(Color.rand),
							tracksview = ListView() /////////////////////////////////////Tracks Synth/////////////////////////////////
							.fixedWidth_(100)
							.items_(tracks.asArray)
							.background_(Color.black(0.6))
							.stringColor_(Color.green(1))
							.alpha_(1)
							.hiliteColor_(Color.red(0.6))
							.selectedStringColor_(Color.green(1))
							.action_({|sbs|


								/*tracksitem = tracks.collect({|x| x.value})[sbs.value];
								moduleview.items = tracksitem.listmodules.collect(_.[0]).postln;
								tracksitem.postln;*/


							})
							.defaultKeyDownAction({|char, modifiers, unicode, keycode, key|
								char.postln;
								modifiers.postln;
								unicode.postln

							})
							.selectionAction_({|sbs|


								// tracksitem = tracks[tracksview.items[sbs.value]];
								// ES aqui el problema!!!!!
								/*"tototototototototototototottootototototo".postln;
								tracks.postln;
								moduleview.postln;
								tracksitem.postln;
								tracksitem.isNil.postln;*/
								/*"rrrrrrrrrrrrrrr".postln;
								sbs.postln;
								"rrrrrrrrrrrrrrr".postln;*/
								//tracksitem.postln;
								if (tracksitem != 0) {
									tracksitem = tracks.collect({|x| x.value})[tracksview.selection];
									if( tracksitem.size != 0) {
										selitem = tracksitem.last;

										/*	tracksitem.postln;
										tracksitem.size.postln;
										sbs.postln;
										sbs.value.postln;*/
										selitem.win.front;


									}
									//moduleview.items = tracksitem[sbs].listmodules.collect(_.[0]);
									/*	tracksitem.postln;
									tracksitem[sbs].postln;*/

								}{
									tracksitem = [];
								};


								selitem.win.front; //selecciona track window
								//tracksitem[tracksview.items[sbs.value].flat].postln;

								if (moduleview != 0) {
									moduleview.items = selitem.listmodules.collect(_.[0]);
								}{
									moduleview = [];
								};


								selectrack = tracksview.selection.postln;
								selitem.postln;
								tracksview.items[sbs.value].postln;

								if (brwwind.notNil) { // si Browser ha sido creado
									brwwind.frontrack = tracksview.items[sbs.value]; //envia track seleccionado a Browser
								};



							})
							.canReceiveDragHandler_({ View.currentDrag.isString })
							.beginDragAction_({|listView|
								//listView.items[ listView.value ].debug("begun dragging");
								//~tgraph;
								//[\g_02_TTestSynth, \t_01_TMFxRev ]
								//[0, 1, 2, 3];
								listView.value.postln;
								/*selectrack = tracksview.selection.postln;
								selitem.postln;*/
								tracksview.items[selectrack].postln;
							})
							.selectionMode_(\extended)
							.keyDownAction_({|view, char, modifiers, unicode, keycode, key|
								char.postln})
							.mouseDownAction_({|uvw, x, y,modifiers, buttonNumber, clickCount| //double-click
								if (clickCount == 2) {
									/*tracksitem.win.front;*/

								};
								//if ((clickCount == 1) && (~interfaces.size == 1)) {"totototototooto".postln; ~tgraphsynthslist.value = nil};
							}),

							moduleview = ListView() /////////////////////////////////////Tracks FXs /////////////////////////////////
							.fixedWidth_(100)
							.items_()
							.background_(Color.black)
							.stringColor_(Color.new255(238, 154, 0))               //Color.blue(1))
							.hiliteColor_(Color.blue(0.5))
							.selectedStringColor_(Color.new255(139, 90, 0))     //Color.blue(1))
							.action_({ arg sbs;

								// tracksitem = tracks[tracksview.items[sbs.value]];
								//tracksitem = tracks.collect({|x| x.value})[sbs.value];
								//tracksitem.postln;

								//tracksitem.modwin[sbs.value].postln;


							})
							.selectionAction_({|sbs|

								// tracksitem = tracks[tracksview.items[sbs.value]];
								moduleitem = sbs.value;

								"+++++++++++++++++++++++++".postln;
								moduleitem.postln;
								if (moduleitem.notNil) {
									paramsview.items = selitem.specs[moduleitem].collect(_.key).postln;
								}{
									paramsview.items = [];
								};
								"+++++++++++++++++++++++++".postln;


							})
							.canReceiveDragHandler_({ View.currentDrag.isString })
							.beginDragAction_({|listView|
								//listView.items[ listView.value ].debug("begun dragging");

								//[\g_02_TTestSynth, \t_01_TMFxRev ]
								//[0, 1, 2, 3];
							})
							.selectionMode_(\extended)
							.keyDownAction_({|view, char, modifiers, unicode, keycode, key|
								// unicode.postln;
								if (unicode == 119) {
									// selitem.modwin[moduleitem].postln;//but_win_mod.valueAction_(1);
									selitem.but_win_mod.valueAction_(1);
									char.postln
								}
							})
							.mouseDownAction_({|uvw, x, y,modifiers, buttonNumber, clickCount| //double-click
								if (clickCount == 2) {
									//tracksitem.modwin.parent.front
									selitem.modwin[moduleitem].parent.front;

								};
								//if ((clickCount == 1) && (~interfaces.size == 1)) {"totototototooto".postln; ~tgraphsynthslist.value = nil};
							}),
							paramsview = ListView() // reflejo de parametros de cada modulo
							.fixedWidth_(100)
							.items_(tracks.asArray)
							.background_(Color.black(0.6))
							.stringColor_(Color.new255(255, 0, 0))      //Color.green(1))
							.alpha_(1)
							.hiliteColor_(Color.yellow(0.6))
							.selectedStringColor_(Color.new255(139, 0, 0))          //  Color.green(1))
							.selectionAction_({|sbs|

								selparam = sbs.value.postln


							})
							.mouseDownAction_({ |view, x, y, mod, mouseBut, cCount|

								[view, x, y, mod, mouseBut, cCount].postln;
								if(mouseBut == 1, {
									slidwin = Window.new(border:false).front;
									slidwin.bounds = Rect(wind.bounds.left + 292, wind.bounds.top - y + 370 ,180,12);
									slidwin.alpha_(0.65);
									slidwin.layout_(
										HLayout(
											contextSlider = Slider().fixedHeight_(12)
											.value_(selitem.modwin[moduleitem].slid_param.getparams[selparam].value)
											.orientation_(\horizontal)
											.background_(Color.red)
											.knobColor_(Color.blue)
											.action_({ contextSlider.value.postln;

												param_numb.value = selitem.specs[moduleitem].collect(_.value)[selparam].map(contextSlider.value).postln;
												selitem.modwin[moduleitem].slid_param.getparams[selparam].valueAction = contextSlider.value; // no se vecesita mapear spec

											})
											.thumbSize_(10)
											.mouseLeaveAction_{
												// contextSlider.remove;
												slidwin.close;
											}
											.mouseDownAction_({ |cm, x, y, mod, mBut, cCount|
												//[cm.value, cm.items[cm.value]].postln;//returns not actual mouse click but last one BUG
											}), param_numb = NumberBox().fixedHeight_(12).fixedWidth_(30).font_(Font("Helvetica", 8))
											.background_(Color.black)
											.normalColor_(Color.white)
											.value_(selitem.specs[moduleitem].collect(_.value)[selparam].map(selitem.modwin[moduleitem].slid_param.getparams[selparam].value))
										).margins_([0, 0, 0, 0]).spacing_(0)
									)
								});
							})
						).spacing_(0),nil,

					),
					/*~wind1 = ListView() //Buses
					//.items_(~interfaces.keys.asArray)
					.fixedWidth_(100)
					.background_(Color.gray(0.6))
					.hiliteColor_(Color.green(alpha:0.6))
					.action_({ arg sbs;
					//[sbs.value, v.items[sbs.value]] // .value returns the integer
					})
					.canReceiveDragHandler_({ View.currentDrag.isString })
					.beginDragAction = { arg listView;
					//listView.items[ listView.value ].debug("begun dragging");
					},
					~wind2 = ListView() /////////////////////////////////////Tracks OTROS /////////////////////////////////
					.fixedWidth_(100)
					.items_()
					.background_(Color.gray(0.6))
					.hiliteColor_(Color.green(alpha:0.6))
					.action_({ arg sbs;

					// tracksitem = tracks[tracksview.items[sbs.value]];
					//tracksitem = tracks.collect({|x| x.value})[sbs.value];
					//tracksitem.postln;


					})
					.canReceiveDragHandler_({ View.currentDrag.isString })
					.beginDragAction_({ arg listView;
					//listView.items[ listView.value ].debug("begun dragging");
					~tgraph;
					//[\g_02_TTestSynth, \t_01_TMFxRev ]
					//[0, 1, 2, 3];
					})
					.selectionMode_(\extended)
					.keyDownAction_({|view, char, modifiers, unicode, keycode, key|
					char.postln})
					.mouseDownAction_({|uvw, x, y,modifiers, buttonNumber, clickCount| //double-click
					if (clickCount == 2) {
					//tracksitem.win.front;

					};
					//if ((clickCount == 1) && (~interfaces.size == 1)) {"totototototooto".postln; ~tgraphsynthslist.value = nil};
					}),
					~wind3 = ListView() /////////////////////////////////////Tracks OTROS /////////////////////////////////
					.fixedWidth_(100)
					.items_()
					.background_(Color.gray(0.6))
					.hiliteColor_(Color.green(alpha:0.6))
					.action_({ arg sbs;

					// tracksitem = tracks[tracksview.items[sbs.value]];
					//tracksitem = tracks.collect({|x| x.value})[sbs.value];
					//tracksitem.postln;


					})
					.canReceiveDragHandler_({ View.currentDrag.isString })
					.beginDragAction_({ arg listView;
					//listView.items[ listView.value ].debug("begun dragging");
					~tgraph;
					//[\g_02_TTestSynth, \t_01_TMFxRev ]
					//[0, 1, 2, 3];
					})
					.selectionMode_(\extended)
					.keyDownAction_({|view, char, modifiers, unicode, keycode, key|
					char.postln})
					.mouseDownAction_({|uvw, x, y,modifiers, buttonNumber, clickCount| //double-click
					if (clickCount == 2) {
					//tracksitem.win.front;

					};
					//if ((clickCount == 1) && (~interfaces.size == 1)) {"totototototooto".postln; ~tgraphsynthslist.value = nil};
					})
					*/
			), nil),



		).margins_([0, 0, 0, 0])).front;

		busview.layout = VLayout().margins_([0, 2, 0, 0]).spacing_(0);
	}

}

/*~wind1.visible_(false);
~wind2.visible_(false);
~wind3.visible_(false);

~vert.layout.insert(NumberBox().fixedHeight_(15).font_(Font("Helvetica", 11)), 0);
~vert.layout.add(nil, 1);
~vert.layout.add(nil);
~vert.removeAll


a = NumberBox()
a.font*/