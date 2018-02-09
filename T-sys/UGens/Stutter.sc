Stutter {
    *ar { |in, reset, length, rate = 1.0, maxdelay = 10|
        var phase, fragment, del;
        phase = Sweep.ar(reset);
        fragment = { |ph| (ph - Delay1.ar(ph)) < 0 + Impulse.ar(0) }.value(phase / length % 1);
        del = Latch.ar(phase, fragment) + ((length - Sweep.ar(fragment)) * (rate - 1));
        ^DelayC.ar(in, maxdelay, del);
    }
}