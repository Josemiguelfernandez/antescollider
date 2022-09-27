MIDIController {
	var <midiIn, <midiOut;//temp getter
	var <components;

	*new{arg inputDeviceName, inputPortName, outputDeviceName, outputPortName;
		^super.new.init(inputDeviceName, inputPortName, outputDeviceName, outputPortName);
	}

	init{arg inputDeviceName_, inputPortName_, outputDeviceName_, outputPortName_;
		midiIn = MIDIIn.findPort(inputDeviceName_, inputPortName_);
		midiOut = MIDIOut.newByName(outputDeviceName_, outputPortName_);
		components = ();
	}

	setMappings{arg mappingsDict;
		mappingsDict.keysValuesDo({arg key, mappings;
			mappings.do{arg mapping, i;
				var newComp;
				newComp = MIDIControllerComponent.create(
						midiIn, midiOut,
						mapping[\chan],
						mapping[\number],
						mapping[\msgType] ? \control
					);
				components.put((key ++ "." ++ (i + 1)).asSymbol, newComp);
			};
		});

	}

	refresh{
		components.do(_.refresh);
	}
}

MIDIControllerComponent {
	var <value;
	var responder;
	var syncFunction;
	var chan;
	var number;
	var <spec;
	var msgType;
	var midiIn, midiOut;
	var <>action;

	*create{arg midiIn, midiOut, chan, number, msgType;
		var newObj;
		if(msgType == \control14, {
			newObj = MIDIControllerComponent14BitCC.new(midiIn, midiOut, chan, number);
		}, {
			newObj = this.new(midiIn, midiOut, chan, number, msgType);
		});
		^newObj;
	}

	*new{arg midiIn, midiOut, chan, number, msgType = \control;
		^super.new.init(midiIn, midiOut, chan, number, msgType);
	}

	init{arg midiIn_, midiOut_, chan_, number_, msgType_;
		midiIn = midiIn_;
		midiOut = midiOut_;
		chan = chan_;
		number = number_;
		msgType = msgType_;
		value = 0;
		this.prSetupSpec;
		this.prSetupResponderAndSyncFunc;
	}

	prSetupSpec{
		spec = \midi.asSpec;
	}

	prSetupResponderAndSyncFunc{
		responder = MIDIFunc({arg val, num, chan, src;
			this.valueAction_(val);
			this.changed(this, \value);
		}, number, chan, msgType, midiIn.uid);
		syncFunction = {arg comp;
			//implement sending other msgtypes than control, ot switch statement
			fork {
				midiOut.control(chan, number, value);
			};
		};
	}

	valueAction_{arg val;
		value = val;
		this.doAction;
	}

	doAction{
		"action: %\n".postf([this.value, number, chan]);
		this.action.value(this);
	}

	update{arg theChanged, theChanger, what;
		if(theChanger !== this, {
			var newVal;
			if(what == \value, {
				newVal = theChanged.spec.unmap(theChanged.value);
				newVal = spec.map(newVal).asInteger;
				value = newVal;
				this.refresh;
				"%\n".postf(newVal);
			});
		});
	}

	refresh{
		syncFunction.value(this);
	}
}

MIDIControllerComponent14BitCC : MIDIControllerComponent {
	var hiByteResponder, loByteResponder;
	var loByte, hiByte;
	var waitingForLoByte = false;

	*new{arg midiIn, midiOut, chan, number;
		^super.new(midiIn, midiOut, chan, number, \control14);
	}

	prSetupSpec{
		spec = ControlSpec(0, 1023, step: 1, default: 512);
	}

	prSetupResponderAndSyncFunc{
		var calculateValue, loNumber;
		loNumber = number + 32;
		calculateValue = {arg lo, hi;
			var result;
			result = lo + (hi << 7);
			result;
		};
		hiByteResponder = MIDIFunc.cc({arg val, num, chan, src;
			hiByte = val;
			waitingForLoByte = true;
		}, number, chan, midiIn.uid);

		loByteResponder = MIDIFunc.cc({arg val, num, chan, src;
			loByte = val;
			if(waitingForLoByte, {
				var val;
				val = calculateValue.value(loByte, hiByte);
				this.valueAction_(val);
				this.changed(this, \value);
				waitingForLoByte = false;
				loByte = nil;
				hiByte = nil;
			});
		}, loNumber, chan, midiIn.uid);

		syncFunction = {arg comp;
			fork {
				midiOut.control(chan, number, value >> 7);
				midiOut.control(chan, loNumber, value % 128);
			};
		};
	}

	prCalculateValue{
		value = loByte + (hiByte << 7);
	}
}