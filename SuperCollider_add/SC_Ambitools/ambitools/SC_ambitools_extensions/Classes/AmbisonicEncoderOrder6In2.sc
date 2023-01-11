AmbisonicEncoderOrder6In2 : MultiOutUGen
{
  *ar { | in1, in2, gain_0(0.0), radius_0(1.0), azimuth_0(0.0), elevation_0(0.0), gain_1(0.0), radius_1(1.0), azimuth_1(0.0), elevation_1(0.0) |
      ^this.multiNew('audio', in1, in2, gain_0, radius_0, azimuth_0, elevation_0, gain_1, radius_1, azimuth_1, elevation_1)
  }

  *kr { | in1, in2, gain_0(0.0), radius_0(1.0), azimuth_0(0.0), elevation_0(0.0), gain_1(0.0), radius_1(1.0), azimuth_1(0.0), elevation_1(0.0) |
      ^this.multiNew('control', in1, in2, gain_0, radius_0, azimuth_0, elevation_0, gain_1, radius_1, azimuth_1, elevation_1)
  } 

  checkInputs {
    if (rate == 'audio', {
      2.do({|i|
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
      ^this.initOutputs(49, rate)
  }

  name { ^"AmbisonicEncoderOrder6In2" }


  info { ^"Generated with Faust" }
}

