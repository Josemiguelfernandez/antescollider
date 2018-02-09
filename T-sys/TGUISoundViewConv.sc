

TGUISoundViewConv {
	classvar <defaultWidth= 600, <defaultHeight= 95, <>params;	//bounds are hardcoded for this view
	var <>cv, <numb, <now, <>lista, <>getparams, <>dic, f, <>buf, openSFView, fileDur, fftsize, bufsize, irspectrum, server;


	*new {|parent, synth, multislidparam, name, id, leng, index, b, fxName, bufferpath|

		^super.new.init(parent, synth, multislidparam, name, id,leng, index, b, fxName, bufferpath);

	}
	init {|parent, synth, multislidparam, name, id,leng, index, b, fxName, bufferpath|
		var bufpath;

		fftsize = 2048;
		lista = Array.fill(leng, 0);
		name = [index, name];
		// server = Server.default;

		/*if (bufferpath.notNil) {
			bufpath = bufferpath;
			"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;
			"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;
			"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;
		}{
			buf = Buffer.read(Server.default, Platform.resourceDir +/+ "sounds/a11wlk01.wav"); //defaul
			bufpath = buf.path;
			//prepate buffer para convolucion
			{
				server.sync;
				bufsize= PartConv.calcBufSize(fftsize, buf);
				irspectrum = Buffer.alloc(server, bufsize, 1);
				irspectrum.preparePartConv(buf, fftsize);
				server.sync;
			}.fork;
			synth.set(\convbuf, irspectrum);
			// synth.set(\buf, buf);
		};*/

		/////////////////////


		this.prMake(parent, synth, name, multislidparam, id,leng, index, b, fxName);

		this.sfview(bufpath);


	}


	//--private
	prMake {|parent, synth, name, multislidparam, id,leng, index, b, fxName|
		var cmp, width1, height1, text, server, unit, win, winparent, paramslen,dic, ind, nom, bufsamples, slids, bounds;


		//ind = name[0];
		//nom = name[1];
		//cv.postln;
		//multislidparam[0] = Specs
		//multislidparam[1] = multislider size

		/*[name, index].postln;*/
		win = parent[0];
		winparent = parent[1];
		//paramslen = leng - 1;

		if(bounds.isNil, {
			bounds= Rect(200, 200, defaultWidth, defaultHeight);
			}, {
				bounds= bounds.asRect;
		});



		//server = Server.default;

		cmp= CompositeView(win, bounds);
		cmp.decorator = FlowLayout(bounds, 5@4, 5@4);


		openSFView = SoundFileView.new(cmp, Rect(0, 0, 345, 80))
		.waveColors_([Color.gray(0.75)])
		.background_(Color.gray(0.4))
		.setSelectionColor(0, Color.black)
		.canReceiveDragHandler_({true})
		.receiveDragHandler_({
			var f, bu;
			~buffers[View.currentDrag].postln;
			bu = ~buffers[View.currentDrag][0];
			f = ~buffers[View.currentDrag][1];
			openSFView.soundfile = f;
			openSFView.read(0, f.numFrames);
			buf = Server.default.cachedBufferAt(bu);
			//~soundfileview_buf.put(fxName, buf);
			synth.set(\convbuf, buf);
			//if (~tsynths.includesKey(fxName) == true) {~tsynths[fxName].set(\buf, buf)};
		}); //posts in swing but not in cocoa..


		//a.beginDragAction= { "started dragging".postln; "item dragged"; };

		Button(cmp, Rect(0,0,40,20))
		.states_([


			["Open", Color.gray(0.75), Color.gray(0.4)]
		])
		.mouseDownAction_({|view|
			var args;
			//view.value.postln;
			if (view.value == 0) {
				// open mono sound file

				Dialog.openPanel({ arg paths;

					forkIfNeeded {
						var ir, irBuffer; //, bufSize = List[];
						// var numChannels = SoundFile.use(path, {|f| f.numChannels });
						server = server ? Server.default;
						server.sync;
						// mono buffer only
						/*irBuffer = numChannels.collect{|i|
							Buffer.readChannel(server, path, channels: i)
						};*/
						irBuffer = Buffer.read(server, paths);
						server.sync;
						// get the size
						/*irBuffer.do{|buf|
							bufSize.add(PartConv.calcBufSize(fftSize, buf));
						};*/
						bufsize= PartConv.calcBufSize(fftsize, irBuffer);
						server.sync;
/*						irSpectrum = numChannels.collect{|i| Buffer.alloc(server, bufSize[i], 1) };
						server.sync;*/
						irspectrum = Buffer.alloc(server, bufsize, 1);
						server.sync;
						/*irBuffer.do{|buf, i|
							irSpectrum[i].preparePartConv(buf, fftSize);
						};*/
						irspectrum.preparePartConv(irBuffer, fftsize);
						server.sync;
						synth.set(\convbuf, irspectrum);
						irspectrum.postln;
						// don't need time domain data anymore, just needed spectral version
						// irBuffer.do{|buf| buf.free };
						irBuffer.free;

					};

					this.sfview(paths);
					//prepate buffer para convolucion
/*					{

						buf = Buffer.read(server, paths);
						// server.sync;
						bufsize= PartConv.calcBufSize(fftsize, buf);
						irspectrum = Buffer.alloc(server, bufsize, 1);
						irspectrum.preparePartConv(buf, fftsize);
						server.sync;
						"envia IR buffer".postln;
						irspectrum.postln;
						synth.set(\convbuf, irspectrum);
						buf.free;
						this.sfview(paths);

					}.fork(AppClock);*/





					},{
						"cancelled".postln;
				});
				buf.postln;

			}
		});

		"titititi".postln;
		//slids = ~slides_params[fxName].postln;


		// mouseMoveAction
		/*openSFView.mouseMoveAction = {
		slids[0].valueAction = openSFView.selections[openSFView.currentSelection][0] / bufsamples; // / fileDur
		slids[2].valueAction = openSFView.selections[openSFView.currentSelection][1] / bufsamples; // / fileDur

		};*/



	}

	sfview {|paths|
		f = SoundFile.new;
		f.openRead(paths);
		fileDur = f.duration.postln;
		openSFView.soundfile = f;
		openSFView.read(0, f.numFrames);

	}

}

