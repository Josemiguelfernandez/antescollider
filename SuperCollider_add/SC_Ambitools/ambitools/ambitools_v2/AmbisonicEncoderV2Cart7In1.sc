AmbisonicEncoderV2Cart7In1 : MultiOutUGen
{
  *ar { | in1, gain_0(0.0), x_0(1.0), y_0(0.0), z_0(0.0) |
      ^this.multiNew('audio', in1, gain_0, x_0, y_0, z_0)
  }

  *kr { | in1, gain_0(0.0), x_0(1.0), y_0(0.0), z_0(0.0) |
      ^this.multiNew('control', in1, gain_0, x_0, y_0, z_0)
  } 

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
      ^this.initOutputs(64, rate)
  }

  name { ^"AmbisonicEncoderV2Cart7In1" }


  info { ^"Generated with Faust" }
}

