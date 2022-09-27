TLoadSounds {

	var mi, men, win, drag, osc, index = 0, node, bufnumber, soundfiles, sf, startButton, startstop = 0, loop, spec, slid, slid2, slid_txt, slid_txt_vol, rate = 1, rev = 1, pos = 0, amp = 1, listsound;


	*new {
		^super.newCopyArgs().initTLoadSounds();
	}

	initTLoadSounds {


		win = Window("load sounds", Rect(1500, 500, 480, 680)).front;
		win.addFlowLayout;


		drag = DragBoth(win, Rect(10, 10, 340, 50)).align_(\center).background_(Color.rand);
		drag.object = "drag sound folder";

		startButton = Button(win, Rect(487,60,40,20))
		.states_([
			["Play", Color.black, Color.green(0.7)],
			["Stop", Color.white, Color.red(0.7)]
		])
		.onClose_({
		})
		.action_({|view|
			var args;
			startstop = view.value;
			if (view.value == 1) {
				node = Synth(\play_buf, [\bufnum, bufnumber, \loop, loop, \rate, rate, \rev, rev, \pos, pos, \amp, amp]);
			} {
				node.set(\gate, 0)}
		});


		Button(win, Rect(487,60,40,20))
		.states_([
			["Loop", Color.black, Color.green(0.7)],
			["Loop", Color.white, Color.red(0.7)]
		])
		.onClose_({
		})
		.action_({|view|
			var args;
			if (view.value == 1) {
				//node.set(\loop, 1);
				loop = 1;
			} {
				//node.set(\loop, 0);
				loop = 0}
		});



		mi = PopUpMenu(win,Rect(10, 10, 340, 21));

		mi.action = {|menu|
			var file, sfw;
			file = menu.item.asSymbol;
			[menu.item, ~buffers[file]].postln;
			bufnumber = ~buffers[file][0];
			sfw = ~buffers[file][1];
			sf.soundfile = sfw;
			sf.read(0, sfw.numFrames);
			sf.refresh;

			//soundfiles.postln;
			if ( startstop == 1)
			{node.set(\bufnum, ~buffers[file])};

		};

		// OSC reply
		osc = OSCFunc({ |msg| { startButton.value = 0}.defer}, '/buf_stop');


		Button(win, Rect(487,60,40,20))
		.states_([
			["clear"]
		])
		.onClose_({
		})
		.action_({
			listsound.clear;
			Buffer.freeAll;
			men = [];
			mi.items = [];
			index = 0;
			soundfiles = [];
			node.free;
			~buffers.keys.collect {|k| ~buffers.removeAt(k)};

		});

		NumberBox(win, Rect(405,60,40,20))
		.value_(0)
		.action_({arg numb;
			node.set(\out, numb.value)});


		spec = ControlSpec(0, 2, \linear, 0.001, 1);
		slid = Slider(win, Rect(20, 50, 150, 20))
		.focusColor_(Color.red(alpha:0.2))
		.background_(Color.rand)
		.value_(0.5)
		.action_({
			slid_txt.string_(spec.map(slid.value).asString);
			node.set(\rate, spec.map(slid.value));
			rate = spec.map(slid.value);
			// round the float so it will fit in the NumberBox
		});

		slid_txt = StaticText(win, Rect(20, 20, 50, 20)).align_(\center).background_(Color.rand).string_(1);

		Button(win, Rect(487,60,40,20))
		.states_([
			["norm", Color.black, Color.rand],
			["rever", Color.white, Color.rand]
		])
		.onClose_({
		})
		.action_({|view|
			var args;
			view.value.postln;
			if (view.value == 1) {
				rev = 1;
				pos = 0;
				node.set(\rev, 1)
			} {
				rev = -1;
				pos = 1;
				node.set(\rev, -1)}
		});

		slid2 = Slider(win, Rect(20, 50, 150, 20))
		.focusColor_(Color.red(alpha:0.2))
		.background_(Color.rand)
		.value_(0.5)
		.action_({
			slid_txt_vol.string_(spec.map(slid2.value).asString);
			node.set(\amp, spec.map(slid2.value));
			amp = spec.map(slid2.value);
			// round the float so it will fit in the NumberBox
		});

		slid_txt_vol = StaticText(win, Rect(20, 20, 50, 20)).align_(\center).background_(Color.rand).string_(1);


		sf = SoundFileView.new(win, Rect(20,20, 440, 60));
		listsound = ListView(win,Rect(10,10,150,500))
		.selectionMode_(\extended)
		.action_({|sbs|
			var sfw;
			[sbs.value, listsound.items[sbs.value]].postln; // .value returns the integer
			bufnumber = ~buffers[listsound.items[sbs.value]][0];
			sfw = ~buffers[listsound.items[sbs.value]][1];
			sf.soundfile = sfw;
			sf.read(0, sfw.numFrames);
			sf.refresh;
			//toto = y.selection.postln;

		})
		.beginDragAction = { arg listView;
			listView.items[ listView.value ].debug("begun dragging");

		};

		drag.receiveDragHandler = {|obj|
			var drags, myPathName, files,search;
			obj.object = View.currentDrag.value;
			//drags = obj.object.pathMatch;
			drags = obj.object.postln;
			search = drags +/+ "*";
			files = search.pathMatch;
			//soundfiles = SoundFile.collect(search);
			//.do {|f|
			//		soundfiles = soundfiles.add(f)};
			files.collect { |filepath|
				var files, f, buf, sf;
				buf = Buffer.readChannel(Server.default, filepath, channels: [0]);
				//i.postln;
				files = PathName.new(filepath);
				files = files.fileName;

				men = men.add(files.asString);
				//men.postln;
				mi.items = men;
				//listsound.items_(files.asSymbol);
				listsound.items= listsound.items.add(files.asSymbol);
				//f = SoundFile.openRead(filepath);
				//f.openRead(filepath);
				//soundfiles = soundfiles.add(SoundFile.openRead(filepath));
				sf = SoundFile.openRead(filepath);
				~buffers.put(files.asSymbol, [buf.bufnum, sf]);
				index = index +1;
				//soundfiles.postln;
			};
			//drags = PathName(obj.object);

		};


	}


}
