AmbisonicEncoderOrder7In5 : MultiOutUGen
{
  *ar { | in1, in2, in3, in4, in5, gain_0(0.0), radius_0(1.0), azimuth_0(0.0), elevation_0(0.0), gain_1(0.0), radius_1(1.0), azimuth_1(0.0), elevation_1(0.0), gain_2(0.0), radius_2(1.0), azimuth_2(0.0), elevation_2(0.0), gain_3(0.0), radius_3(1.0), azimuth_3(0.0), elevation_3(0.0), gain_4(0.0), radius_4(1.0), azimuth_4(0.0), elevation_4(0.0) |
      ^this.multiNew('audio', in1, in2, in3, in4, in5, gain_0, radius_0, azimuth_0, elevation_0, gain_1, radius_1, azimuth_1, elevation_1, gain_2, radius_2, azimuth_2, elevation_2, gain_3, radius_3, azimuth_3, elevation_3, gain_4, radius_4, azimuth_4, elevation_4)
  }

  *kr { | in1, in2, in3, in4, in5, gain_0(0.0), radius_0(1.0), azimuth_0(0.0), elevation_0(0.0), gain_1(0.0), radius_1(1.0), azimuth_1(0.0), elevation_1(0.0), gain_2(0.0), radius_2(1.0), azimuth_2(0.0), elevation_2(0.0), gain_3(0.0), radius_3(1.0), azimuth_3(0.0), elevation_3(0.0), gain_4(0.0), radius_4(1.0), azimuth_4(0.0), elevation_4(0.0) |
      ^this.multiNew('control', in1, in2, in3, in4, in5, gain_0, radius_0, azimuth_0, elevation_0, gain_1, radius_1, azimuth_1, elevation_1, gain_2, radius_2, azimuth_2, elevation_2, gain_3, radius_3, azimuth_3, elevation_3, gain_4, radius_4, azimuth_4, elevation_4)
  } 

  checkInputs {
    if (rate == 'audio', {
      5.do({|i|
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
      ^this.initOutputs(64, rate)
  }

  name { ^"AmbisonicEncoderOrder7In5" }


  info { ^"Generated with Faust" }
}

