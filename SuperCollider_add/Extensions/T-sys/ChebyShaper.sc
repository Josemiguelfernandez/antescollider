ChebyShaper {
	/* author: tim blechmann */
	classvar polynomials;

	*initClass {
		polynomials = [
			[0, 1],
			[-1, 0, 2],
			[0, -3, 0, 4],
			[1, 0, -8, 0, 8],
			[0, 5, 0, -20, 0, 16],
			[-1, 0, 18, 0, -48, 0, 32],
			[0, -7, 0, 56, 0, -112, 0, 64],
			[1, 0, -32, 0, 160, 0, -256, 0, 128]
		];
	}

	*ar {|sig, factors|
		var degree = factors.size;
		var coefficients = {0.0} ! (degree+1);
		var result;
		var coeff;

		degree.do {|deg|
			var poly = polynomials[deg];
			poly.size.do {|i|
				coefficients[i] = coefficients[i] + (poly[i] * factors[deg]);
			}
		};

		result = sig.madd(coefficients.pop, coefficients.pop);

		coefficients.reverse.do {|c|
			result = result.madd(sig, c)
		};

		^result
	}
}