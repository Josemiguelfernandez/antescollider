// by Batuhan Bozkurt batuhan@batuhanbozkurt.com,  Volker Boehm

LPF1p
{
Ê Ê Ê Ê*ar
Ê Ê Ê Ê{|in, freq|

Ê Ê Ê Ê Ê Ê Ê Ê^OnePole.ar(in, exp(-2pi * (freq * SampleDur.ir)));
Ê Ê Ê Ê}
}

HPF1p
{
Ê Ê Ê Ê*ar
Ê Ê Ê Ê{|in, freq|

Ê Ê Ê Ê Ê Ê Ê Ê^(in - OnePole.ar(in, exp(-2pi * (freq * SampleDur.ir))));
Ê Ê Ê Ê}
}