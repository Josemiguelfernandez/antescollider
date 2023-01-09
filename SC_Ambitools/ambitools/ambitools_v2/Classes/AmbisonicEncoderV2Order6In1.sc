
AmbisonicEncoderV2Order6In1 : MultiOutUGen {

    *ar{|in0,gain__0(0), radius__0(1), azimuth__0(0), elevation__0(0), doppler(0)|
      ^this.multiNew('audio', in0,gain__0, radius__0, azimuth__0, elevation__0, doppler)
    }

    *kr{|in0,gain__0(0), radius__0(1), azimuth__0(0), elevation__0(0), doppler(0)|
      ^this.multiNew('control', in0,gain__0, radius__0, azimuth__0, elevation__0, doppler)
    }

    name { ^"AmbisonicEncoderV2Order6In1" }

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
      ^this.initOutputs(49, rate)
  }

    
}
