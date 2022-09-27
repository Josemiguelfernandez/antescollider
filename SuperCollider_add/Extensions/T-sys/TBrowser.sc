TBrowser {

	var myPath, myEntries, tab, inter_number, synth, <>win, <>frontrack;


	*new {|tracksdico|
		^super.newCopyArgs(tracksdico).initTBrowser(tracksdico);
	}

	initTBrowser {|tracksdico|

		tracksdico.postln;

		myPath = PathName.new(Platform.userAppSupportDir ++ "/Extensions/Mis-clases/T-sys/modules"); //("/Volumes/AuDiO-750Go/SC-system1/modules");
		myEntries = myPath.folders;
		win=Window.new("Synths Browser").setTopLeftBounds(Rect(0,0,385,1030));
		win.front.view.keyDownAction_({|view, char, modifiers, unicode, keycode| if (keycode==53){win.close}});
		win.background = Color.new255(120, 120, 120);
		win.alpha = 0.9;

		tab  = TabbedView2.newTall(win.view, Rect(5,5,375,1020))
		.tabPosition_(\left)
		.followEdges_(false)
		.tabWidth_(\auto);
		//myEntries.postln;

		myEntries.collect{|family|
			var foldernom, c, filesnom, boton;
			c=[Color.rand,Color.rand,Color.rand];
			foldernom = family.folderName;
			filesnom = family.files;
			//filesnom.fileName.postln;
			//filesnom.postln;
			tab.labelColors = c;

			tab.unfocusedColors = c.collect{|c| c.alpha_(0.4)};

			tab.backgrounds = c.collect{|c| c.alpha_(0.4)};
			tab.add(foldernom.asString).flow({|win|
				filesnom.collect{|modulos|
					var filenombre, fileclass, fontsize = 12;
					filenombre = modulos.fileNameWithoutExtension;
					fileclass = filenombre.asSymbol;
					boton = Button(win,Rect(500,500,86,24));
					if((filenombre.asString.size >= 13) && (filenombre.asString.size < 15)) {
						fontsize = 10;
					} {
						if((filenombre.asString.size >= 15) && (filenombre.asString.size < 20)) {
							fontsize = 8;
						} {if(filenombre.asString.size >= 20) { fontsize = 6}

						}
					};

					boton.font = Font("Helvetica-Bold", fontsize);
					// filenombre.asString.size.postln;
					/* .states_([[filenombre.asString]]).action_({TEffectModuleGUI(fileclass.asClass);
					fileclass.postln})*/
					boton.states_([[filenombre.asString, Color.gray(0.9), Color.gray(0.4)]]); //Color.new255(220, 220, 220)
					boton.mouseDownAction_({|view|
						var args;
						if (view.value == 0) {

							//~tracks[~tracks_front].addmodule(fileclass);
							if (frontrack.notNil) {
								tracksdico.detect({|x| x.key == frontrack}).value.addmodule(fileclass); //select track para inserir modulo con boton

								//tracksdico[frontrack].addmodule(fileclass);
							} {tracksdico.collect({|x| x.value})[0].addmodule(fileclass)} //si no hay select agrega al track en pos 0




						}

					});
					boton.beginDragAction_({ arg listView;
						fileclass;
					});
				}
			});
			/*var filename;
			filename = toto.files;*/
			//filename.folderName.postln
		};

	}
}