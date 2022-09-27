MotorMix {
	var buttonLSB, faderLSB, mmmixID;
	var buttonMSBState, faderMSBState;
	var responders;
	var <>faderAction;
	var <>buttonAction;
	var <>encoderAction;
	var <>rotarySwitchAction;
	var <>rotaryButtonAction;
	var midiOutput, midiInput;
	var currentEnvir;

	classvar <buttonLEDs;
	classvar <stripLEDs;
	classvar <lcdHeader;
	classvar <buttonNames;

	*new {arg argMidiDevice, argMidiName;
		^super.new.init(argMidiDevice, argMidiName);
	}

	*initClass {
		buttonNames = [\touch, \lhs, \rhs, \select, \multi, \mute,
			\solo, \burn, \burn_sw, \multi_sw];
		buttonLEDs = (\lhs: 8, \rhs: 9, \burn: 10, \multi: 11);
		stripLEDs = (\select: 1, \mute: 2, \solo: 3, \multi_sw: 4, \burn_sw: 5);
		lcdHeader = Int8Array[240, 0, 1, 15, 0, 17, 0, 16];
		}

	init{ arg argMidiDevice, argMidiName;
		midiOutput = MIDIOut.newByName(argMidiDevice, argMidiName);
		mmmixID = MIDIClient.sources.detect({|item|
			(item.device == argMidiDevice) and: {item.device == argMidiName}}).uid;


		responders = IdentityDictionary[
			\buttonLSB -> MIDIFunc.cc({|val, num, chan, id|
							buttonLSB = val;
							buttonMSBState = nil;
							buttonMSBState = case
									{val.bitAnd(2r11111000) == 0} {\strip}
									{val == 8} {\lhs}
									{val == 9} {\rhs}
									{val == 10} {\burn}
									{val == 11} {\multi};
							if(buttonMSBState.notNil)
							{
								responders.at(buttonMSBState).enable;
								responders.at(\buttonLSB).disable;
							};
							}, srcID: mmmixID, ccNum: 15, chan: nil, argTemplate: (0..15)),
			\strip -> MIDIFunc.cc({|val, num, chan, id|
							var stripnum, value, button, type;
							stripnum = buttonLSB;
							value = val.bitAnd(2r01000000) >> 6;
							button = val.bitAnd(2r00000111);
							type = [\touch, \select, \mute, \solo, \multi_sw,
									\burn_sw].at(button);
                // [type, stripnum, value, id].postln;
							buttonAction.value(type, stripnum + 1, value);
							responders.at(\strip).disable;
							responders.at(\buttonLSB).enable;
							}, srcID: mmmixID, ccNum: nil, chan: 0).disable,
			\lhs -> MIDIFunc.cc({|val, num, chan, id|
							var buttonnum, value;
							buttonnum = val.bitAnd(2r00000111);
							value = val.bitAnd(2r01000000) >> 6;
                // [\lhs, buttonnum, value, id].postln;
							buttonAction.value(\lhs, buttonnum + 1, value);
							responders.at(\lhs).disable;
							responders.at(\buttonLSB).enable;
							}, srcID: mmmixID, ccNum: nil, chan: 0).disable,
			\rhs -> MIDIFunc.cc({|val, num, chan, id|
							var buttonnum, value;
							buttonnum = val.bitAnd(2r00000111);
							value = val.bitAnd(2r01000000) >> 6;
                // [\rhs, buttonnum, value, id].postln;
							buttonAction.value(\rhs, buttonnum + 1, value);
							responders.at(\rhs).disable;
							responders.at(\buttonLSB).enable;
							}, srcID: mmmixID, ccNum: nil, chan: 0).disable,
			\burn -> MIDIFunc.cc({|val, num, chan, id|
							var buttonnum, value;
							buttonnum = val.bitAnd(2r00000111);
							value = val.bitAnd(2r01000000) >> 6;
                // [\burn, buttonnum, value, id].postln;
							buttonAction.value(\burn, buttonnum + 1, value);
							responders.at(\burn).disable;
							responders.at(\buttonLSB).enable;
							}, srcID: mmmixID, ccNum: nil, chan: 0).disable,
			\multi -> MIDIFunc.cc({|val, num, chan, id|
							var buttonnum, value;
							buttonnum = val.bitAnd(2r00000111);
							value = val.bitAnd(2r01000000) >> 6;
                // [\multi, buttonnum, value, id].postln;
							buttonAction.value(\multi, buttonnum + 1, value);
							responders.at(\multi).disable;
							responders.at(\buttonLSB).enable;
							}, srcID: mmmixID, ccNum: nil, chan: 0).disable,
			\rotaryEncoder ->  MIDIFunc.cc({ |val, num, chan, id|
							var encodernum;
							encodernum = num.bitAnd(2r00000111);
							encoderAction.value(encodernum + 1, val);
                // [\encoder, encodernum, val, id].postln;
							}, srcID: mmmixID, ccNum: (64..71), chan: 0),
			\rotarySwitch -> MIDIFunc.cc({|val, num, chan, id|
                // [\rotSwitch, val, id].postln;
							rotarySwitchAction.value(val + 1, num, id);
							}, srcID: mmmixID, ccNum: 72, chan: 0, argTemplate: [1,65]),
			\rotaryButton -> MIDIFunc.cc({|val, num, chan, id|
                // [\rotButton, val, id].postln;
							rotaryButtonAction.value(val + 1, id);
							}, srcID: mmmixID, ccNum: 73, chan: 0, argTemplate: [0,1]),
			\faderLSB -> MIDIFunc.cc({|val, num, chan, id|
							faderLSB = nil;
							faderLSB = val;
							responders.at(\faderLSB).disable;
							responders.at(\faderMSB).enable;
							}, srcID: mmmixID, ccNum: Array.series(8, 0), chan: 0),
			\faderMSB -> MIDIFunc.cc({|val, num, chan, id|
							var value, fadernum;
							if(faderLSB.notNil)
							{
								fadernum = num.bitAnd(2r00000111);
								value = (faderLSB << 2) + (val >> 5);
								faderAction.value(fadernum + 1, value);
                    // [\fader, fadernum, value, id].postln;
							};

							responders.at(\faderLSB).enable;
							responders.at(\faderMSB).disable;
							}, srcID: mmmixID, ccNum: Array.series(8, 32)).disable;

		];

	}

	remove {
		responders.collect({|item|
			item.free;
		});
	}

	free{
		this.remove;
		}


	setLEDState {arg type, index, value;
		var command;
		type = type.asSymbol;
        value = value.booleanValue.asInteger;
		index = index - 1;
		command = case
			{this.class.buttonLEDs.keys.includes(type)}
				{
					this.buildButtonLEDCommand(buttonLEDs.at(type), index, value)
				}
			{this.class.stripLEDs.keys.includes(type)}
				{
					this.buildStripLEDCommand(stripLEDs.at(type), index, value)
				};
		this.send(command);
	}

	buildButtonLEDCommand {arg typeindex, index, value;
		var result;
		result = Int8Array[16rB0, 0x0C,
					typeindex, 0x2C,
					(value << 6).bitOr(index.bitAnd(2r00000111))
				];
		^result;
	}

	buildStripLEDCommand {arg typeindex, index, value;
		var result;
		result = Int8Array[16rB0, 0x0C,
					index, 0x2C,
					(value << 6).bitOr(typeindex.bitAnd(2r00000111))
				];
		^result;
	}

	setMotorPosition{arg num, value;
		var positionLSB, positionMSB, command;
		value = value.asInteger.clip(0, 511).bitAnd(2r111111111);//change to try or if
		num = (num - 1).bitAnd(2r00000111);//change to try or if
		positionLSB = (value >> 2).bitAnd(2r01100000);
		positionMSB = (value >> 2).bitAnd(2r01111111);
		command = Int8Array[16rB0, num, positionMSB, 16rB0, num + 32, positionLSB];
		this.send(command);
		}

	setLCDString { arg argstring, startSegment = 0;
		var result, command;
		result = [240, 0, 1, 15, 0, 17, 0, 16] ++ startSegment ++ argstring.ascii ++ 247;
		result = result.flatten;
		command = result.as(Int8Array);
		this.send(command);
	}

	clearLCD{arg area = \upper;
		var startseg, numsegments;
		switch(area,
			\upper, {startseg = 0; numsegments = 40},
			\lower, {startseg = 40; numsegments = 40},
			\both, {startseg = 0; numsegments = 80});
		if(((startseg.notNil) or: (numsegments.notNil)),
			{this.setLCDString((Char.space ! numsegments), startseg)},
			{"Invalid arguments".error});
	}

	setEncoderDisplay{ arg channel, value, type = 0;
		var result;
		value = value.asInteger.clip(0, 127);
		result = Int8Array[240, 0, 1, 15, 0, 17, 0, 17, type, channel - 1, value, 247];
		this.send(result);
	}

	setSegmentDisplay { arg argstring, dotA = false, dotB = false;
		var result, outputstring;
		var msbHiNibble, msbLoNibble, lsbHiNibble, lsbLoNibble;

		"This is arg string stize: %\n".postf(argstring.size);
		switch(argstring.size,
			0, {outputstring = "  "},
			1, {outputstring = " " ++ argstring; },
			{outputstring = argstring;}
		);
		outputstring = outputstring.toUpper;
		"This is arg string stize after save: %\n".postf(outputstring.size);
		outputstring = outputstring.copyRange(0, 2).ascii;//keep only two chars
		msbHiNibble = (outputstring[0] >> 4).bitOr(dotA.asInteger << 6);
		msbLoNibble = (outputstring[0].bitAnd(0x0F));
		lsbHiNibble = (outputstring[1] >> 4).bitOr(dotB.asInteger << 6);
		lsbLoNibble = (outputstring[1].bitAnd(0x0F));
		result = Int8Array[240, 0, 1, 15, 0, 17, 0, 18,
						msbHiNibble,
						msbLoNibble,
						lsbHiNibble,
						lsbLoNibble,
						247];
		this.send(result);

	}

	send{arg command;
		//["sending:", command].postln;
		midiOutput.sysex(command);
	}

	pushEnvir{arg argEnvir;
		this.popEnvir;
		currentEnvir = argEnvir;
		this.faderAction_(this.class.prGetFaderAction(currentEnvir));
		this.encoderAction_(this.class.prGetEncoderAction(currentEnvir));
		this.buttonAction_(this.class.prGetButtonAction(currentEnvir));
		currentEnvir.addDependant(this);
		currentEnvir.refresh;
	}

	popEnvir{
		this.faderAction_(nil);
		this.encoderAction_(nil);
		this.buttonAction_(nil);
		currentEnvir.removeDependant(this);
	}

	update{arg theChanged, what, args;
		var type, channel, value;
		[theChanged, what, args].postln;
		#type, channel = what.asString.split($.);
		channel = channel !? {channel.asInteger};
		value = args;
		if(theChanged === currentEnvir, {
			switch(type.asSymbol,
				\fader, {
					this.setMotorPosition(channel, value * 512);
				},
				\encoder, {
					this.setEncoderDisplay(channel, value * 128);
				},
				\solo, {this.setLEDState(type, channel, value);},
				\lhs, {this.setLEDState(type, channel, value);},
				\rhs, {this.setLEDState(type, channel, value);},
				\select, {this.setLEDState(type, channel, value);},
				\multi, {this.setLEDState(type, channel, value);},
				\mute, {this.setLEDState(type, channel, value);},
				\burn, {this.setLEDState(type, channel, value);},
				\burn_sw, {this.setLEDState(type, channel, value);},
				\multi_sw, {this.setLEDState(type, channel, value);},
				\lcdString, {this.clearLCD; this.setLCDString(args);},
				\segmentString, {this.setSegmentDisplay(args);}
			);
		})
	}


	*prGetFaderAction{arg envir;
		^{|num, value|
			var key;
			key = ("fader." ++ num).asSymbol;
			envir.put(key, value / 512.0);
		}
	}

	*prGetButtonAction{arg envir;
		^{|name, num, value|
			var key;
			key = (name ++ "." ++ num).asSymbol;
			if(value == 1, {
				var currentValue = envir.at(key);
				value = (currentValue + 1) % 2;
				envir.put(key, value);
			});
		};
	}

	*prGetEncoderAction{arg envir;
		^{|num, value|
			var key, currentValue, delta, min, max;
			var deltaMin = 0.01, deltaMax = 0.1;
			key = ("encoder." ++ num).asSymbol;
			currentValue = envir.at(key);
			delta = if(value > 64,
				{value.linlin(64, 127, deltaMin, deltaMax)},
				{value.linlin(0, 63, deltaMin.neg, deltaMax.neg)}
			);
			value = [0.0, 1.0].asSpec.map(currentValue + delta);
			envir.put(key, value);
		};
	}
}