declare name        "Ambisonic EncoderV2 order7 in1";
declare version     "1.1";
declare author      "Pierre Lecomte";
declare license     "CC-BY-NC-SA-4.0";
declare copyright   "(c) Pierre Lecomte";
declare options "[osc:on]";

// Changelog
// (2022-09) v1.1 
// - Doppler effect
// - Cartesian / Spherical coordinate choice at compilation
// - No more clicks when fast position change
// (2021-04-24)
// - Revert to azimuth-elevation spherical coordinate system $(\theta, \phi)$.

//###Encoder###
// This tool encodes $S$ sources as point sources in an Ambisonic sound scene up to a maximal degree $L$.
//
// ## Point source
// The $i$-th source, with $i \in \\{1, \cdots, S\\}$ carries a signal denoted $s(z)$ in the discrete domain. Encoded as a point source, its position is $(r_s, \theta_s, \phi_s)$ from origin and it emits a spherical wave.
// The near field filters $F_l(k_rs)$ are included to encode the radial distance $r_s$ information. (see [radial.lib]({% link docs/radial.md %}#near-field-filters)). 
// In the current implementation, these filters are stabilized with near field compensation filter $\frac{1}{F_l(1,z)}$ at radius $r_\text{spk}=1$ m (see [radial.lib]({% link docs/radial.md %}#stabilization-of-nf-filters-with-nfc-filters)).
// In addition, a delay $\frac{r_s}{c}$ due to the propagation time can be included. When the source moves, this produces a Doppler effect, which can be activated or not at runtime. 
// The resulting Ambisonic components are given by:
//
// $$\begin{equation}
// b_{l,m}(z) = s(z) z^{- \lfloor \frac{r_s}{c} \rfloor} \frac{F_l(r_s, z)}{F_l(1, z)}  Y_{l,m}(\theta_s, \phi_s)
// \label{eq:point_source}
// \end{equation}$$
//
// Note that to avoid exessing gain for small radius $r_s < 1$ m, the minimum $r_s$ radius is limited at $r_s = 0.75$ m.
// {:.info}
//
// ### Plane wave case
// If the source radius $r_s$ is set to $r_\text{spk} = 1$ m, the source is encoded as a plane wave with direction $(\theta_s, \phi_s)$. In fact the radial term in Eq. \eqref{eq:point_source} is equal to unity.
// The Ambisonic components become:
//
// $$\begin{equation}
// b_{l,m}(z) = s(z) Y_{l,m}(\theta_s, \phi_s)
// \end{equation}$$
//
// ## Compilation parameters
// - `L`: maximal Spherical Harmonics degree (i.e., Ambisonics order), $L > 0$,
// - `S`: number of source to encode, $S > 0$,
// - `coord` : Choice of coordinate system : `0` => Spherical, `1` => Cartesian,
// - `doppler` : Possibility of Doppler effect : `0` => No, `1` => Yes.
//
// ## Inputs / Outputs
// - Inputs: $S$
// - Outputs: $(L+1)^2$
//
// ## User Interface
// For the $i$-th source:
//
// |             Element                       |     OSC        | Min value | Max value |
// |:-----------------------------------------:|:--------------:|:---------:|:---------:|
// |          Gain (dB)                        |   `gain_i`     |    -20    |    20     |
// |           Doppler                         |   `doppler_i`  |      0    |     1     |
// |       Radius $r$) (m) (`coord = 0`)       |  `radius_i`    |    0.75   |    50     |
// | Azimuth $\theta$ ($^\circ$) (`coord = 0`) | `azimuth_i`    |    -180   |    180    |
// | Elevation $\phi$ ($^\circ$) (`coord = 0`) |  `elevation_i` |    -90    |    90     |
// |             $x$ (m) (`coord = 1`)         |       `x_i`    |    -50    |    50     |
// |             $y$ (m) (`coord = 1`)         |       `y_i`    |    -50    |    50     |
// |             $z$ (m) (`coord = 1`)         |       `z_i`    |    -50    |    50     |
//
//######

import("stdfaust.lib");
import("ylm.lib");
import("radial.lib");
import("grids.lib");

// COMPILATION PARAMETERS
L = 7; // maximal SH degree
S = 1; // number of inputs
coord = 0; // Choice of coordinate system : 0 => Spherical, 1 => Cartesian
doppler = 0; // Activate the possibility of Doppler effect : 0 => No, 1 => Yes

// DO NOT EDIT BELOW HERE
rspk = 1; // speaker radius (for near-field filters stabilization)
rmax = 50; // maximum radius
rmin = 0.75;

// User interface
g(i)	=	hslider("[%i+1][unit:dB][osc:/gain_%i -20 20][style:knob]Gain %2i",0,-20,20,0.1): ba.db2linear : si.smoo; // gain
d(i)    =       checkbox("[%i+5][osc:/doppler_%i 0 1]Doppler"); // Doppler effect

// User interface Cartesian
x(i)	=	vslider("[%i+2][unit:m][osc:/x_%i -%rmax %rmax]x %2i", 1, -rmax, rmax, 0.01); 
y(i)	=	vslider("[%i+3][unit:m][osc:/y_%i -%rmax %rmax]y %2i", 0, -rmax, rmax, 0.01);
z(i)	=	vslider("[%i+4][unit:m][osc:/z_%i -%rmax %rmax]z %2i", 0, -rmax, rmax, 0.01);

rtp(i) =  (x(i), y(i), z(i)) : cart2spher : (max(_, rmin), _, _); // ensures to never go below rmin.

r1(i) = rtp(i) : _, !, ! ;
t1(i) = rtp(i) : !, _, ! ;
p1(i) = rtp(i) : !, !, _ ;

// User interface Spherical
r0(i)	=	hslider("[%i+2][unit:m][osc:/radius_%i %rmin %rmax][style:knob]Radius %2i", 1, rmin, rmax, 0.01);// radius
t0(i)	=	hslider("[%i+3][unit:°][osc:/azimuth_%i -180 180][style:knob]Azimuth %2i", 0, -180, 180, 0.1)*ma.PI/180; // azimuth
p0(i)	=	hslider("[%i+4][unit:°][osc:/elevation_%i -90 90][style:knob]Elevation %2i", 0, -90, 90, 0.1)*ma.PI/180; // elevation

// Resulting Spherical coordinate system
r(i) = case{
        (0) => r0(i);
        (1) => r1(i);
        }(coord) : si.smoo;

t(i) = case{
        (0) => t0(i);
        (1) => t1(i);
        }(coord);  // no smoothing because of audible click when passing from -180° to 180°, handled with syvec function.

p(i) = case{
        (0) => p0(i);
        (1) => p1(i);
        }(coord) : si.smoo;

// Doppler delay or not
dd(i) = case{
        (0) => _;
        (1) => ddelay(sqrt(3) * rmax, r(i) * d(i));
        }(doppler);

source(i) =     hgroup("Source %2i",_*g(i) : dd(i) <:par(l, L+1, nf(l,r(i),rspk)<:par(i,2*l+1,_)) :> syvec((L+1)^2, t(i), p(i)));
// source(i) =     hgroup("Source %2i",_*g(i)*sqrt(r(i)) : dd(i) <:par(l, L+1, nf(l,r(i),rspk)<:par(i,2*l+1,_)) :> syvec((L+1)^2, t(i), p(i)));

process = par(i, S, source(i)) :> si.bus((L+1)^2);
