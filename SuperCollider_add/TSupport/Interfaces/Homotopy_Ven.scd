var dbspec, faderwin, server, server1, server2, server3, lemur_port, meter_val1, meter_val2, meter_val3, meter_val4, meter_val5, meter_val6, meter_val7, meter_val8, meter_val9, meter_val10, meter_val11, meter_val12, meter_val13, meter_val14, dBLow = -80, lemur_in, level1, level2, level3, level4, level5, level6, level7, level8, level9, level10, level11, level12, level13, level14;

server = Server.default;
server1 = ~scservers[\server1];
server2 = ~scservers[\server2];
server3 = ~scservers[\server3];

dbspec = ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB);
lemur_port = 8005; //9876; OSC 0 en Lemur

// lemur_in = NetAddr("192.168.1.120", 8000); // adress to send from sclang to Lemur

lemur_in = NetAddr("192.168.1.122", 8000);

// lemur_in = NetAddr("169.254.131.120", 8000); // adress to send from sclang to Lemur
// lemur_in = NetAddr("127.0.0.1", 8000); // adress to send from sclang to Lemur

/*~toto = NetAddr("169.254.199.185", 8000);
~toto.sendMsg("/Range/x", 0, 0.6)*/

lemur_in.postln;

///////////////LEMUR

OSCdef(\lemur_fade1, {|msg|
	{~slider1.valueAction_(msg[1])}.defer;
}, '/Fader1/x', nil, lemur_port).fix;

OSCdef(\lemur_fade2, {|msg|
	{~slider2.valueAction_(msg[1])}.defer;
}, '/Fader2/x', nil, lemur_port).fix;

OSCdef(\lemur_fade3, {|msg|
	{~slider3.valueAction_(msg[1])}.defer;
}, '/Fader3/x', nil, lemur_port).fix;

OSCdef(\lemur_fade4, {|msg|
	{~slider4.valueAction_(msg[1])}.defer;
}, '/Fader4/x', nil, lemur_port).fix;

OSCdef(\lemur_fade5, {|msg|
	{~slider5.valueAction_(msg[1])}.defer;
}, '/Fader5/x', nil, lemur_port).fix;

OSCdef(\lemur_fade6, {|msg|
	{~slider6.valueAction_(msg[1])}.defer;
}, '/Fader6/x', nil, lemur_port).fix;

OSCdef(\lemur_fade7, {|msg|
	{~slider7.valueAction_(msg[1])}.defer;
}, '/Fader7/x', nil, lemur_port).fix;

OSCdef(\lemur_fade8, {|msg|
	{~slider8.valueAction_(msg[1])}.defer;
}, '/Fader8/x', nil, lemur_port).fix;

OSCdef(\lemur_fade9, {|msg|
	{~slider9.valueAction_(msg[1])}.defer;
}, '/Fader9/x', nil, lemur_port).fix;

OSCdef(\lemur_fade10, {|msg|
	{~slider10.valueAction_(msg[1])}.defer;
}, '/Fader10/x', nil, lemur_port).fix;

OSCdef(\lemur_fade11, {|msg|
	{~slider11.valueAction_(msg[1])}.defer;
}, '/Fader11/x', nil, lemur_port).fix;

OSCdef(\lemur_fade12, {|msg|
	{~slider12.valueAction_(msg[1])}.defer;
}, '/Fader12/x', nil, lemur_port).fix;


OSCdef(\lemur_fade13, {|msg|
	{~slider13.valueAction_(msg[1])}.defer;
}, '/Fader13/x', nil, lemur_port).fix;

OSCdef(\lemur_fade14, {|msg|
	{~slider14.valueAction_(msg[1])}.defer;
}, '/Fader14/x', nil, lemur_port).fix;



OSCdef(\levels_server1, {|msg|
	var meter_val, meter_val1_lin, meter_val2_lin, meter_val3_lin, meter_val4_lin;
	// msg.postln;
	meter_val = msg[3]; // (msg.drop(3).sum); //  dividido /8; //adicion de las señales y divido por 8 channels
	if(msg[2] == ~group_bus[\Add1].index){
		meter_val1 = meter_val;
		// meter_val1.postln;
	};

	if(msg[2] == ~group_bus[\Add2].index){
		meter_val2 = meter_val;
		// meter_val1.postln;
	};

	if(msg[2] == ~group_bus[\Add3].index){
		meter_val3 = meter_val;
		// meter_val1.postln;
	};

	if(msg[2] == ~group_bus[\Add4].index){
		meter_val4 = meter_val;
		// meter_val1.postln;
	};
	{
		meter_val1_lin = meter_val1.ampdb.linlin(dBLow, 0, 0, 1);
		meter_val2_lin = meter_val2.ampdb.linlin(dBLow, 0, 0, 1);
		meter_val3_lin = meter_val3.ampdb.linlin(dBLow, 0, 0, 1);
		meter_val4_lin = meter_val4.ampdb.linlin(dBLow, 0, 0, 1);

		// level1.peakLevel = fade_val1.ampdb.linlin(-60, 0, 0, 1);
		level1.value = meter_val1_lin;
		lemur_in.sendMsg("/MultiSlider1/x", meter_val1_lin);

		// level2.peakLevel = meter_val2.ampdb.linlin(-60, 0, 0, 1);
		level2.value = meter_val2_lin;
		lemur_in.sendMsg("/MultiSlider2/x", meter_val2_lin);

		// level3.peakLevel = meter_val3.ampdb.linlin(-60, 0, 0, 1);
		level3.value = meter_val3_lin;
		lemur_in.sendMsg("/MultiSlider3/x", meter_val3_lin);
		// meter_val3_lin.postln;

		// level4.peakLevel = meter_val4.ampdb.linlin(-60, 0, 0, 1);
		level4.value = meter_val4_lin;
		lemur_in.sendMsg("/MultiSlider4/x", meter_val4_lin);
	}.defer;


}, '/meter', server1.addr);

OSCdef(\levels_server2, {|msg|
	var meter_val, meter_val5_lin, meter_val6_lin, meter_val7_lin, meter_val8_lin;
	// msg.postln;
	meter_val = msg[3];

	if(msg[2] == ~group_bus[\Synth1].index){
		meter_val5 = meter_val;
		// meter_val1.postln;
	};

	if(msg[2] == ~group_bus[\Synth2].index){
		meter_val6 = meter_val;
		// meter_val1.postln;
	};

	if(msg[2] == ~group_bus[\Synth3].index){
		meter_val7 = meter_val;
		// meter_val1.postln;
	};

	if(msg[2] == ~group_bus[\Synth4].index){
		meter_val8 = meter_val;
		// meter_val1.postln;
	};
	{
		meter_val5_lin = meter_val5.ampdb.linlin(dBLow, 0, 0, 1);
		meter_val6_lin = meter_val6.ampdb.linlin(dBLow, 0, 0, 1);
		meter_val7_lin = meter_val7.ampdb.linlin(dBLow, 0, 0, 1);
		meter_val8_lin = meter_val8.ampdb.linlin(dBLow, 0, 0, 1);

		// level5.peakLevel = meter_val5.ampdb.linlin(-60, 0, 0, 1);
		level5.value = meter_val5_lin;
		lemur_in.sendMsg("/MultiSlider5/x", meter_val5_lin);

		// level6.peakLevel = meter_val6.ampdb.linlin(-60, 0, 0, 1);
		level6.value = meter_val6_lin;
		lemur_in.sendMsg("/MultiSlider6/x", meter_val6_lin);

		// level7.peakLevel = meter_val7.ampdb.linlin(-60, 0, 0, 1);
		level7.value = meter_val7_lin;
		lemur_in.sendMsg("/MultiSlider7/x", meter_val7_lin);

		// level8.peakLevel = meter_val8.ampdb.linlin(-60, 0, 0, 1);
		level8.value = meter_val8_lin;
		lemur_in.sendMsg("/MultiSlider8/x", meter_val8_lin);
	}.defer;

}, '/meter', server2.addr);


OSCdef(\levels_server3, {|msg|
	var meter_val,meter_val9_lin, meter_val10_lin, meter_val11_lin;
	// msg.postln;
	meter_val = msg[3];

	if(msg[2] == ~group_bus[\SynthPerc1].index){
		meter_val9 = meter_val;
		// meter_val1.postln;
	};

	if(msg[2] == ~group_bus[\SynthPerc2].index){
		meter_val10 = meter_val;
		// meter_val1.postln;
	};

	if(msg[2] == ~group_bus[\SynthPerc3].index){
		meter_val11 = meter_val;
		// meter_val1.postln;
	};


	{
		meter_val9_lin = meter_val9.ampdb.linlin(dBLow, 0, 0, 1);
		meter_val10_lin = meter_val10.ampdb.linlin(dBLow, 0, 0, 1);
		meter_val11_lin = meter_val11.ampdb.linlin(dBLow, 0, 0, 1);

		// level9.peakLevel = meter_val9.ampdb.linlin(-60, 0, 0, 1);
		level9.value = meter_val9_lin;
		lemur_in.sendMsg("/MultiSlider9/x", meter_val9_lin);

		// level10.peakLevel = meter_val10.ampdb.linlin(-60, 0, 0, 1);
		level10.value = meter_val10_lin;
		lemur_in.sendMsg("/MultiSlider10/x", meter_val10_lin);

		// level11.peakLevel = meter_val11.ampdb.linlin(-60, 0, 0, 1);
		level11.value = meter_val11_lin;
		lemur_in.sendMsg("/MultiSlider11/x", meter_val11_lin);
	}.defer;


}, '/meter', server3.addr);


OSCdef(\levels_server_local, {|msg|
	var meter_val, meter_val13_lin, meter_val14_lin;
	// msg.postln;

	meter_val = msg[3];

	if(msg[2] == ~group_bus[\Samples].index){
		meter_val13 = meter_val;
		// meter_val1.postln;
	};

	if(msg[2] == ~group_bus[\SFiles].index){
		meter_val14 = meter_val;
		// meter_val1.postln;
	};
	{
		meter_val13_lin = meter_val13.ampdb.linlin(dBLow, 0, 0, 1);
		meter_val14_lin = meter_val14.ampdb.linlin(dBLow, 0, 0, 1);


		level13.value = meter_val13_lin;
		lemur_in.sendMsg("/MultiSlider12/x", meter_val13_lin);

		level14.value = meter_val14_lin;
		lemur_in.sendMsg("/MultiSlider13/x", meter_val14_lin);

		// level12.peakLevel = meter_val12.ampdb.linlin(-60, 0, 0, 1);
		// level12.value = meter_val12.ampdb.linlin(-60, 0, 0, 1);
	}.defer;

}, '/meter', server.addr);

// enable in case of close window/disable
OSCdef(\levels_server_local).enable;
OSCdef(\levels_server1).enable;
OSCdef(\levels_server2).enable;
OSCdef(\levels_server3).enable;

/*OSCdef(\lemur_fade1, {|msg|
{~slider1.valueAction_(msg[1])}.defer;
}, '/Fader3/x', nil, 1122).fix;*/


faderwin =Window("Levels", Rect(128, 64, 560, 360)).background_(Color(0, 0.1, 0.1, 0.3)).layout_( GridLayout.columns(
	[StaticText().stringColor_(Color.white).string_("Add_1"),~slider1 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\Add1].set(\amp, dbspec.map(sld.value));
		~textoutputlev1.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev1 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level1 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Add_2"),~slider2 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\Add2].set(\amp, dbspec.map(sld.value));
		~textoutputlev2.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev2 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level2 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Add_3"),~slider3 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\Add3].set(\amp, dbspec.map(sld.value));
		~textoutputlev3.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev3 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level3 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Add_4"),~slider4 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\Add4].set(\amp, dbspec.map(sld.value));
		~textoutputlev4.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev4 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level4 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Synth1"),~slider5 = Slider().background_(Color.green).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\Synth1].set(\amp, dbspec.map(sld.value));
		~textoutputlev5.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev5 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level5 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Synth2"),~slider6 = Slider().background_(Color.green).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\Synth2].set(\amp, dbspec.map(sld.value));
		~textoutputlev6.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev6 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level6 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Synth3"),~slider7 = Slider().background_(Color.green).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\Synth3].set(\amp, dbspec.map(sld.value));
		~textoutputlev7.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev7 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level7 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Synth4"),~slider8 = Slider().background_(Color.green).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\Synth4].set(\amp, dbspec.map(sld.value));
		~textoutputlev8.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev8 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level8 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("SynthPerc1"),~slider9 = Slider().background_(Color.red).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\SynthPerc1].set(\amp, dbspec.map(sld.value));
		~textoutputlev9.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev9 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level9 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("SynthPerc2"),~slider10 = Slider().background_(Color.red).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\SynthPerc2].set(\amp, dbspec.map(sld.value));
		~textoutputlev10.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev10 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level10 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("SynthPerc3"),~slider11 = Slider().background_(Color.red).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\SynthPerc3].set(\amp, dbspec.map(sld.value));
		~textoutputlev11.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev11 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level11 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("Master"),~slider12 = Slider().background_(Color.gray).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		/*									levelr8.values.collect({|val|
		val[1].set(\amp, dbspec.map(sld.value))});*/
		server.volume = dbspec.map(sld.value);
		server1.volume = dbspec.map(sld.value);
		server2.volume = dbspec.map(sld.value);
		server3.volume = dbspec.map(sld.value);
		~textoutputlev12.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev12 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level12 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Samples"),~slider13 = Slider().background_(Color.red).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\Samples].set(\amp, dbspec.map(sld.value));
		~textoutputlev13.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev13 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level13 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("SFiles"),~slider14 = Slider().background_(Color.red).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~group_out[\SFiles].set(\amp, dbspec.map(sld.value));
		~textoutputlev14.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev14 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, level14 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)]


)).front;
faderwin.onClose_({
	OSCdef(\levels_server_local).disable;
	OSCdef(\levels_server1).disable;
	OSCdef(\levels_server2).disable;
	OSCdef(\levels_server3).disable;
});

