// by Batuhan Bozkurt batuhan@batuhanbozkurt.com,  Volker Boehm

LPF1p
{
� � � �*ar
� � � �{|in, freq|

� � � � � � � �^OnePole.ar(in, exp(-2pi * (freq * SampleDur.ir)));
� � � �}
}

HPF1p
{
� � � �*ar
� � � �{|in, freq|

� � � � � � � �^(in - OnePole.ar(in, exp(-2pi * (freq * SampleDur.ir))));
� � � �}
}