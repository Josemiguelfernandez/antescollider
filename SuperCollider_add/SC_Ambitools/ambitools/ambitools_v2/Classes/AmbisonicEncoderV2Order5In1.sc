
AmbisonicEncoderV2Order5In1 : MultiOutUGen {
	*ar { | in1, gain_0(0.0), radius_0(1.0), azimuth_0(0.0), elevation_0(0.0), doppler(0) |
      ^this.multiNew('audio', in1, gain_0, radius_0, azimuth_0, elevation_0, doppler)
  }

	*kr { | in1, gain_0(0.0), radius_0(1.0), azimuth_0(0.0), elevation_0(0.0) , doppler(0)|
      ^this.multiNew('control', in1, gain_0, radius_0, azimuth_0, elevation_0, doppler)
  }


    name { ^"AmbisonicEncoderV2Order5In1" }

    info { ^"Generated with Faust" }


checkInputs {
    if (rate == 'audio', {
      1.do({|i|
        if (inputs.at(i).rate != 'audio', {
          ^(" input at index " + i + "(" + inputs.at(i) +
            ") is not audio rate");
        });
      });
    });
    ^this.checkValidInputs
  }




init { | ... theInputs |
      inputs = theInputs
      ^this.initOutputs(36, rate)
  }


}
