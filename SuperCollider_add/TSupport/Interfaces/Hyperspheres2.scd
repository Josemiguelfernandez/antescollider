var dbspec, faderwin, server, lemur_port, fade_val1, fade_val2, fade_val3, fade_val4, fade_val5, fade_val6, fade_val7, fade_val8, fade_val9, fade_val10, fade_val11, fade_val12, fade_val13, fade_val14, dBLow = -80, lemur_in, instruments;

server = server = Server.default;
dbspec = ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB);
lemur_port = 8005; //9889; //9889 9876;

// lemur_in = NetAddr("169.254.87.209", 8000); // adress to send from sclang to Lemur

// lemur_in = NetAddr("192.168.1.104", 8000); // adress to send from sclang to Lemur

lemur_in = NetAddr("192.168.1.122", 8000);

/*~toto = NetAddr("169.254.199.185", 8000);
~toto.sendMsg("/Range/x", 0, 0.6)*/

instruments = [\Add1, \Add2, \Add3, \Add4, \Synth1, \Synth2, \Synth3, \Synth4, \SynthPerc1, \SynthPerc2, \SynthPerc3];

/*~group_bus[\percussion]*/

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




OSCdef(\hyperspheres, {|msg|
	var meter_val, fade_val1_lin, fade_val2_lin, fade_val3_lin, fade_val4_lin, fade_val5_lin, fade_val6_lin, fade_val7_lin, fade_val8_lin, fade_val9_lin, fade_val10_lin, fade_val11_lin, fade_val13_lin, fade_val14_lin;
	// msg.postln;
	meter_val = msg[3]; // (msg.drop(3).sum); //  dividido /8; //adicion de las señales y divido por 8 channels
	// meter_val.postln;
	// ~synth[\estremo_init].bus.index

	// ~group_bus[instruments[0]].postln;
	if (msg[2] == ~group_bus[instruments[0]].index){
		fade_val1 = meter_val;
		// fade_val1.postln;
	};

	if (msg[2] == ~group_bus[instruments[1]].index){
		fade_val2 = meter_val;
		// fade_val2.postln;
	};

	if (msg[2] == ~group_bus[instruments[2]].index){
		fade_val3 = meter_val;
		// fade_val1.postln;
	};

	if (msg[2] == ~group_bus[instruments[3]].index){
		fade_val4 = meter_val;
		// fade_val1.postln;
	};

	if (msg[2] == ~group_bus[instruments[4]].index){
		fade_val5 = meter_val;
		// fade_val1.postln;
	};

	if (msg[2] == ~group_bus[instruments[5]].index){
		fade_val6 = meter_val;
		// fade_val2.postln;
	};

	if (msg[2] == ~group_bus[instruments[6]].index){
		fade_val7 = meter_val;
		// fade_val1.postln;
	};

	if (msg[2] == ~group_bus[instruments[7]].index){
		fade_val8 = meter_val;
		// fade_val1.postln;
	};

	if (msg[2] == ~group_bus[instruments[8]].index){
		fade_val9 = meter_val;
		// fade_val1.postln;
	};


	/*	~fader1.values.do({|val, i|
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
	// ~f2.postln*/
	// }});
	/*~fader6.values.do({|val, i|
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

	}});
	~fader9.values.do({|val, i|
	if (msg[2] == val[0]){
	~f9[i] = meter_val;
	// ~f2.postln
	}});
	~fader10.values.do({|val, i|
	if (msg[2] == val[0]){
	~f10[i] = meter_val;
	// ~f2.postln
	}});*/


	~fader11.values.do({|val, i|
		if (msg[2] == val[0]){
			~f11[i] = meter_val;
			// ~f2.postln
	}});
	~fader13.values.do({|val, i|
		if (msg[2] == val[0]){
			~f13[i] = meter_val;
			// ~f2.postln
	}});
	~fader14.values.do({|val, i|
		if (msg[2] == val[0]){
			~f14[i] = meter_val;
			// ~f2.postln
	}});
	/*
	fade_val1 = ((~f1.sum)/~fader1.size);  //~f1.sum; //((~f1.sum)/~f1.size); //adicion de las señales y divido por el numero de synths (15 ~f1...)
	fade_val2 = ((~f2.sum)/~fader2.size); //~f2.sum; //((~f2.sum)/~f2.size);
	fade_val3 = ((~f3.sum)/~fader3.size); //~f3.sum; //((~f3.sum)/~f3.size);
	fade_val4 = ((~f4.sum)/~fader4.size); //~f4.sum; //((~f4.sum)/~f4.size);
	fade_val5 = ((~f5.sum)/~fader5.size); //~f5.sum; //((~f5.sum)/~f5.size);

	fade_val6 = ((~f6.sum)/~fader6.size); //~f6.sum; //((~f6.sum)/~f6.size);
	fade_val7 = ((~f7.sum)/~fader7.size); //~f7.sum; //((~f7.sum)/~f7.size);
	fade_val8 = ((~f8.sum)/~fader8.size); //~f8.sum; //((~f8.sum)/~f8.size);
	fade_val9 = ((~f9.sum)/~fader9.size); //~f9.sum; //((~f5.sum)/~f5.size);
	*/
	fade_val10 = ((~f10.sum)/~fader10.size); //~f10.sum; //((~f6.sum)/~f6.size);
	fade_val11 = ((~f11.sum)/~fader11.size); //~f11.sum; //((~f7.sum)/~f7.size);
	// fade_val12 = ((~f8.sum)/~fader8.size); //~f12.sum; //((~f8.sum)/~f8.size);
	fade_val13 = ((~f13.sum)/~fader13.size);
	fade_val14 = ((~f14.sum)/~fader14.size);
	{
		fade_val1_lin = fade_val1.ampdb.linlin(dBLow, 0, 0, 1);
		fade_val2_lin = fade_val2.ampdb.linlin(dBLow, 0, 0, 1);
		fade_val3_lin = fade_val3.ampdb.linlin(dBLow, 0, 0, 1);
		fade_val4_lin = fade_val4.ampdb.linlin(dBLow, 0, 0, 1);
		fade_val5_lin = fade_val5.ampdb.linlin(dBLow, 0, 0, 1);

		fade_val6_lin = fade_val6.ampdb.linlin(dBLow, 0, 0, 1);
		fade_val7_lin = fade_val7.ampdb.linlin(dBLow, 0, 0, 1);
		fade_val8_lin = fade_val8.ampdb.linlin(dBLow, 0, 0, 1);
		fade_val9_lin = fade_val9.ampdb.linlin(dBLow, 0, 0, 1);
		fade_val10_lin = fade_val10.ampdb.linlin(dBLow, 0, 0, 1);
		fade_val11_lin = fade_val11.ampdb.linlin(dBLow, 0, 0, 1);

		fade_val13_lin = fade_val13.ampdb.linlin(dBLow, 0, 0, 1);
		fade_val14_lin = fade_val14.ampdb.linlin(dBLow, 0, 0, 1);

		// ~fade1.peakLevel = fade_val1.ampdb.linlin(-60, 0, 0, 1);
		~fade1.value = fade_val1_lin;
		lemur_in.sendMsg("/MultiSlider1/x", fade_val1_lin);

		// ~fade2.peakLevel = fade_val2.ampdb.linlin(-60, 0, 0, 1);
		~fade2.value = fade_val2_lin;
		lemur_in.sendMsg("/MultiSlider2/x", fade_val2_lin);

		// ~fade3.peakLevel = fade_val3.ampdb.linlin(-60, 0, 0, 1);
		~fade3.value = fade_val3_lin;
		lemur_in.sendMsg("/MultiSlider3/x", fade_val3_lin);

		// ~fade4.peakLevel = fade_val4.ampdb.linlin(-60, 0, 0, 1);
		~fade4.value = fade_val4_lin;
		lemur_in.sendMsg("/MultiSlider4/x", fade_val4_lin);

		// ~fade5.peakLevel = fade_val5.ampdb.linlin(-60, 0, 0, 1);
		~fade5.value = fade_val5_lin;
		lemur_in.sendMsg("/MultiSlider5/x", fade_val5_lin);

		// ~fade6.peakLevel = fade_val6.ampdb.linlin(-60, 0, 0, 1);
		~fade6.value = fade_val6_lin;
		lemur_in.sendMsg("/MultiSlider6/x", fade_val6_lin);

		// ~fade7.peakLevel = fade_val7.ampdb.linlin(-60, 0, 0, 1);
		~fade7.value = fade_val7_lin;
		lemur_in.sendMsg("/MultiSlider7/x", fade_val7_lin);

		// ~fade8.peakLevel = fade_val8.ampdb.linlin(-60, 0, 0, 1);
		~fade8.value = fade_val8_lin;
		lemur_in.sendMsg("/MultiSlider8/x", fade_val8_lin);

		// ~fade9.peakLevel = fade_val9.ampdb.linlin(-60, 0, 0, 1);
		~fade9.value = fade_val9_lin;
		lemur_in.sendMsg("/MultiSlider9/x", fade_val9_lin);

		// ~fade10.peakLevel = fade_val10.ampdb.linlin(-60, 0, 0, 1);
		~fade10.value = fade_val10_lin;
		lemur_in.sendMsg("/MultiSlider10/x", fade_val10_lin);

		// ~fade11.peakLevel = fade_val11.ampdb.linlin(-60, 0, 0, 1);
		~fade11.value = fade_val11_lin;
		lemur_in.sendMsg("/MultiSlider11/x", fade_val11_lin);

		~fade13.value = fade_val13_lin;
		lemur_in.sendMsg("/MultiSlider12/x", fade_val13_lin);

		~fade14.value = fade_val14_lin;
		lemur_in.sendMsg("/MultiSlider13/x", fade_val14_lin);
	}.defer;


}, '/meter', server.addr);

/*OSCdef(\lemur_fade1, {|msg|
{~slider1.valueAction_(msg[1])}.defer;
}, '/Fader3/x', nil, 1122).fix;*/


faderwin =Window("Levels", Rect(128, 64, 560, 360)).background_(Color(0, 0.1, 0.1, 0.3)).layout_( GridLayout.columns(
	[StaticText().stringColor_(Color.white).string_("Add_1"),~slider1 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		// ~fader1.values.collect({|val|
		// val[1].set(\amp, dbspec.map(sld.value))});
		~group_out[instruments[0]].set(\amp, dbspec.map(sld.value));
		// dbspec.map(sld.value).postln;
		// });

		~textoutputlev1.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev1 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade1 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Add_2"),~slider2 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		// ~fader2.values.collect({|val|
		// val[1].set(\amp, dbspec.map(sld.value))});
		~group_out[instruments[1]].set(\amp, dbspec.map(sld.value));
		// });
		~textoutputlev2.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev2 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade2 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Add_3"),~slider3 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		// ~fader3.values.collect({|val|
		// val[1].set(\amp, dbspec.map(sld.value))});
		~group_out[instruments[2]].set(\amp, dbspec.map(sld.value));
		// });
		~textoutputlev3.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev3 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade3 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Add_4"),~slider4 = Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		// ~fader4.values.collect({|val|
		// val[1].set(\amp, dbspec.map(sld.value))});
		~group_out[instruments[3]].set(\amp, dbspec.map(sld.value));
		// });
		~textoutputlev4.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev4 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade4 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Synth1"),~slider5 = Slider().background_(Color.green).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		// ~fader5.values.collect({|val|
		// val[1].set(\amp, dbspec.map(sld.value))});
		~group_out[instruments[4]].set(\amp, dbspec.map(sld.value));
		// });
		~textoutputlev5.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev5 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade5 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Synth2"),~slider6 = Slider().background_(Color.green).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		// ~fader6.values.collect({|val|
		// val[1].set(\amp, dbspec.map(sld.value))
		~group_out[instruments[5]].set(\amp, dbspec.map(sld.value));

		// });
		~textoutputlev6.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev6 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade6 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Synth3"),~slider7 = Slider().background_(Color.green).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		// ~fader7.values.collect({|val|
		// val[1].set(\amp, dbspec.map(sld.value))
		~group_out[instruments[6]].set(\amp, dbspec.map(sld.value));

		// });
		~textoutputlev7.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev7 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade7 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Synth4"),~slider8 = Slider().background_(Color.green).knobColor_(Color.green).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		// ~fader8.values.collect({|val|
		// val[1].set(\amp, dbspec.map(sld.value))
		~group_out[instruments[7]].set(\amp, dbspec.map(sld.value));

		// });
		~textoutputlev8.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev8 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade8 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("SynthPerc1"),~slider9 = Slider().background_(Color.red).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		// ~fader9.values.collect({|val|
		// val[1].set(\amp, dbspec.map(sld.value))
		~group_out[instruments[8]].set(\amp, dbspec.map(sld.value));

		// });
		~textoutputlev9.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev9 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade9 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("SynthPerc2"),~slider10 = Slider().background_(Color.red).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~fader10.values.collect({|val|
			val[1].set(\amp, dbspec.map(sld.value))});
		~textoutputlev10.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev10 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade10 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("SynthPerc3"),~slider11 = Slider().background_(Color.red).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~fader11.values.collect({|val|
			val[1].set(\amp, dbspec.map(sld.value))});
		~textoutputlev11.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev11 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade11 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("Master"),~slider12 = Slider().background_(Color.gray).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		/*									~fader8.values.collect({|val|
		val[1].set(\amp, dbspec.map(sld.value))});*/
		~scservers.values.collect({|val|
			val.volume = dbspec.map(sld.value);
		});
		// server.volume = dbspec.map(sld.value);
		~textoutputlev12.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev12 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade12 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).maxWidth_(30)],

	[StaticText().stringColor_(Color.white).string_("Samples"),~slider13 = Slider().background_(Color.red).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~fader13.values.collect({|val|
			val[1].set(\amp, dbspec.map(sld.value))});
		~textoutputlev13.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev13 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade13 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)],


	[StaticText().stringColor_(Color.white).string_("SFiles"),~slider14 = Slider().background_(Color.red).knobColor_(Color.red).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
		~fader14.values.collect({|val|
			val[1].set(\amp, dbspec.map(sld.value))});
		~textoutputlev14.string_(dbspec.map(sld.value).round(0.1));
	}), ~textoutputlev14 = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
	[nil, ~fade14 = LevelIndicator().background_(Color(0, 0.1, 0.1, 0.3)).drawsPeak_(true).maxWidth_(30)]


)).front;
faderwin.onClose_({OSCdef(\hyperspheres).disable;});

