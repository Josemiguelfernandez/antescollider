var dbspec, faderwin, server, lemur_port, fade_val1, fade_val2, fade_val3, fade_val4, fade_val5, fade_val6, fade_val7, fade_val8;

server = server = Server.default;
dbspec = ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB);

lemur_port = 9889; //9876;


///////////////LEMUR

OSCdef(\lemur_fade1, {|msg|
	{~slider1.valueAction_(msg[1])}.defer;
}, '/Fader3/x', nil, lemur_port).fix;

OSCdef(\lemur_fade2, {|msg|
	{~slider2.valueAction_(msg[1])}.defer;
}, '/Fader4/x', nil, lemur_port).fix;

OSCdef(\lemur_fade3, {|msg|
	{~slider3.valueAction_(msg[1])}.defer;
}, '/Fader5/x', nil, lemur_port).fix;

OSCdef(\lemur_fade4, {|msg|
	{~slider4.valueAction_(msg[1])}.defer;
}, '/Fader6/x', nil, lemur_port).fix;

OSCdef(\lemur_fade5, {|msg|
	{~slider5.valueAction_(msg[1])}.defer;
}, '/Fader7/x', nil, lemur_port).fix;

OSCdef(\lemur_fade6, {|msg|
	{~slider6.valueAction_(msg[1])}.defer;
}, '/Fader8/x', nil, lemur_port).fix;

OSCdef(\lemur_fade7, {|msg|
	{~slider7.valueAction_(msg[1])}.defer;
}, '/Fader9/x', nil, lemur_port).fix;

OSCdef(\lemur_fade8, {|msg|
	{~slider8.valueAction_(msg[1])}.defer;
}, '/Fader10/x', nil, lemur_port).fix;



OSCdef(\estremo, {|msg|
	var meter_val;
	// msg.postln;
	meter_val = (msg.drop(3).sum)/8; //adicion de las señales y divido por 8 channels
	// meter_val.postln;
	// ~synth[\estremo_init].bus.index
	~fader1.values.do({|val, i|
		if (msg[2] == val[0]){
			~f1[i] = meter_val;
			// ~f1.postln
	}});
	~fader2.values.do({|val, i|
		if (msg[2] == val[0]){
			~f2[i] = meter_val;
			// ~f2.postln
	}});
	~fader3.values.do({|val, i|
		if (msg[2] == val[0]){
			~f3[i] = meter_val;
			// ~f2.postln
	}});
	~fader4.values.do({|val, i|
		if (msg[2] == val[0]){
			~f4[i] = meter_val;
			// ~f2.postln
	}});
	~fader5.values.do({|val, i|
		if (msg[2] == val[0]){
			~f5[i] = meter_val;
			// ~f2.postln
	}});
	~fader6.values.do({|val, i|
		if (msg[2] == val[0]){
			~f6[i] = meter_val;
			// ~f2.postln
	}});
	~fader7.values.do({|val, i|
		if (msg[2] == val[0]){
			~f7[i] = meter_val;
			// ~f2.postln
	}});
	~fader8.values.do({|val, i|
		if (msg[2] == val[0]){
			~f8[i] = meter_val;
			// ~f2.postln
	}});
	fade_val1 = ((~f1.sum)/~f1.size); //adicion de las señales y divido por el numero de synths (15 ~f1...)
	fade_val2 = ((~f2.sum)/~f2.size);
	fade_val3 = ((~f3.sum)/~f3.size);
	fade_val4 = ((~f4.sum)/~f4.size);
	fade_val5 = ((~f5.sum)/~f5.size);
	fade_val6 = ((~f6.sum)/~f6.size);
	fade_val7 = ((~f7.sum)/~f7.size);
	fade_val8 = ((~f8.sum)/~f8.size);

	{
		~fade1.peakLevel = fade_val1.ampdb.linlin(-60, 0, 0, 1);
		~fade1.value = fade_val1.ampdb.linlin(-60, 0, 0, 1);
		~fade2.peakLevel = fade_val2.ampdb.linlin(-60, 0, 0, 1);
		~fade2.value = fade_val2.ampdb.linlin(-60, 0, 0, 1);
		~fade3.peakLevel = fade_val3.ampdb.linlin(-60, 0, 0, 1);
		~fade3.value = fade_val3.ampdb.linlin(-60, 0, 0, 1);
		~fade4.peakLevel = fade_val4.ampdb.linlin(-60, 0, 0, 1);
		~fade4.value = fade_val4.ampdb.linlin(-60, 0, 0, 1);
		~fade5.peakLevel = fade_val5.ampdb.linlin(-60, 0, 0, 1);
		~fade5.value = fade_val5.ampdb.linlin(-60, 0, 0, 1);
		~fade6.peakLevel = fade_val6.ampdb.linlin(-60, 0, 0, 1);
		~fade6.value = fade_val6.ampdb.linlin(-60, 0, 0, 1);
		~fade7.peakLevel = fade_val7.ampdb.linlin(-60, 0, 0, 1);
		~fade7.value = fade_val7.ampdb.linlin(-60, 0, 0, 1);
		~fade8.peakLevel = fade_val8.ampdb.linlin(-60, 0, 0, 1);
		~fade8.value = fade_val8.ampdb.linlin(-60, 0, 0, 1);
	}.defer;

}, '/meter', server.addr);

OSCdef(\lemur_fade1, {|msg|
	{~slider1.valueAction_(msg[1])}.defer;
}, '/Fader3/x', nil, 9999).fix;


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
faderwin.onClose_({OSCdef(\estremo).disable;});
