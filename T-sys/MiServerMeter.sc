MiServerMeter : ServerMeter {

	*new { |server, numIns, numOuts|

		var window, meterView;

		numIns = numIns ?? { server.options.numInputBusChannels };
		numOuts = numOuts ?? { server.options.numOutputBusChannels };

		window = Window.new(server.name ++ " levels (dBFS)",
			Rect(1330, 60, ServerMeterView.getWidth(numIns, numOuts),
				ServerMeterView.height),
			false).alwaysOnTop_(true).alpha_(0.85).background_(Color.gray(0.5)); // js I hacked this to move the server window

		meterView = ServerMeterView(server, window, 0@0, numIns, numOuts);
		meterView.view.keyDownAction_( { arg view, char, modifiers;
			if(modifiers & 16515072 == 0) {
				case
				{char === 27.asAscii } { window.close };
			};
		});

		window.front;

		^super.newCopyArgs(window, meterView)

	}
}

// MiServerMeter(s)

