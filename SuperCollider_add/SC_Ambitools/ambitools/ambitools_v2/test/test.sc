FaustTest : UGen
{
  *ar { | delay(0.0) |
      ^this.multiNew('audio', delay)
  }

  *kr { | delay(0.0) |
      ^this.multiNew('control', delay)
  } 

  name { ^"FaustTest" }


  info { ^"Generated with Faust" }
}

