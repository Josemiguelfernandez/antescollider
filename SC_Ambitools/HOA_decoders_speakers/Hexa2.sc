Hexa2 : MultiOutUGen
{
  *ar { | in1, in2, in3, in4, in5, in6, in7, in8, in9, gain(0.0), lf_hf(0.0), mute(0.0), xover(400.0) |
      ^this.multiNew('audio', in1, in2, in3, in4, in5, in6, in7, in8, in9, gain, lf_hf, mute, xover)
  }

  *kr { | in1, in2, in3, in4, in5, in6, in7, in8, in9, gain(0.0), lf_hf(0.0), mute(0.0), xover(400.0) |
      ^this.multiNew('control', in1, in2, in3, in4, in5, in6, in7, in8, in9, gain, lf_hf, mute, xover)
  } 

  checkInputs {
    if (rate == 'audio', {
      9.do({|i|
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
      ^this.initOutputs(6, rate)
  }

  name { ^"Hexa2" }


  info { ^"Generated with Faust" }
}

