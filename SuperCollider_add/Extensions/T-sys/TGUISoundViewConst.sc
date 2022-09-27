

TGUISoundViewConst {
	classvar <defaultWidth= 600, <defaultHeight= 95, <>params;	//bounds are hardcoded for this view
	var <>cv, <numb, <now, <>lista, <>getparams, <>dic, f, <>buf, openSFView, fileDur, bufsamples;


	*new {|parent, synth, multislidparam, name, id, leng, index, b, fxName, bufferpath|

		^super.new.init(parent, synth, multislidparam, name, id,leng, index, b, fxName, bufferpath);

	}
	init {|parent, synth, multislidparam, name, id,leng, index, b, fxName, bufferpath|
		var bufpath;
		//slidsize= argCV;
		//argCV.postln;
		//dic = Dictionary.new;
		//name= argName;
		lista = Array.fill(leng, 0);
		name = [index, name];
		/*name.postln;

		parent.postln;
		//postf("parent %", parent);
		//bounds.postln;
		//postf("bounds %", bounds);
		multislidparam.postln;
		//postf("argCV %", argCV);
		name.postln;
		//postf("argName %", argName);
		id.postln;
		//postf("id %", id);
		leng.postln;
		//postf("leng %", leng);
		index.postln;
		//postf("index %", index);
		b.postln;
		//postf("b %", b);
		fxName.postln;
		//postf("fxName %", fxName);*/
		/*"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;
		"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;
		bufferpath.postln;
		"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;*/
		//buf.free;
		if (bufferpath.notNil) {
			bufpath = bufferpath;
			"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;
			"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;
			"ecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecoleecole".postln;
		}{
			buf = Buffer.read(Server.default, Platform.resourceDir +/+ "sounds/a11wlk01.wav"); //defaul
			bufpath = buf.path;
			bufsamples = buf.numFrames.postln;
			synth.set(\buf, buf);
		};

		/////////////////////


		this.prMake(parent, synth, name, multislidparam, id,leng, index, b, fxName);

		this.sfview(bufpath);


	}


	//--private
	prMake {|parent, synth, name, multislidparam, id,leng, index, b, fxName|
		var cmp, width1, height1, text, server, unit, win, winparent, paramslen,dic, ind, nom, slids, bounds;


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
		.gridColor_(Color.gray(0.82))
		.setSelectionColor(0, Color.black)
		.canReceiveDragHandler_({true})
		.receiveDragHandler_({
			var f, bu;
			~buffers[View.currentDrag].postln;
			bu = ~buffers[View.currentDrag][0];
			f = ~buffers[View.currentDrag][1];
			bufsamples = f.numFrames.postln;
			openSFView.soundfile = f;
			openSFView.read(0, f.numFrames);
			buf = Server.default.cachedBufferAt(bu);
			//~soundfileview_buf.put(fxName, buf);
			synth.set(\buf, buf);
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
					/*f = SoundFile.new;
					f.openRead(paths);
					fileDur = f.duration.postln;
					openSFView.soundfile = f;
					openSFView.read(0, f.numFrames);*/
					//s.sendMsg(\b_allocRead, 10, p);
					buf.free; // libera el file de ram cuando llamado del button
					this.sfview(paths);
					buf = Buffer.read(Server.default, paths);
					//b.postln;
					bufsamples = f.numFrames.postln;
					//~buffe = buf;
					//~soundfileview_buf.put(fxName, buf);
					synth.set(\buf, buf);
					//if (~tsynths.includesKey(fxName) == true) {~tsynths[fxName].set(\buf, buf)};
					},{
						"cancelled".postln;
				});
				buf.postln;

			}
		});

		"titititi".postln;
		//slids = ~slides_params[fxName].postln;


		// mouseMoveAction
		openSFView.mouseMoveAction = {
			var sel;
			sel = openSFView.selections[openSFView.currentSelection];
			// slids[0].valueAction = openSFView.selections[openSFView.currentSelection][0] / bufsamples; // / fileDur
			// slids[2].valueAction = openSFView.selections[openSFView.currentSelection][1] / bufsamples; // / fileDur
			// [sel[0],sel[0]+sel[1]].postln;
			[sel[0],sel[1]].postln;
			synth.set(\startPos, sel[0], \dur, sel[1]);


		};



	}

	sfview {|paths|
		f = SoundFile.new;
		f.openRead(paths);
		fileDur = f.duration.postln;
		openSFView.soundfile = f;
		openSFView.read(0, f.numFrames);

	}

}

