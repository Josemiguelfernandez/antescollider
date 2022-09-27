+ Dictionary {
    //converts all string values from string to either Integer, Float or Boolean
	//this is practical when importing YAML and JSON files with parseYAML
    changeScalarValuesToDataTypes {
        var parseYAMLValue, changeValue, tempdict;
        parseYAMLValue= {arg str;
            var result = str;
			case
			{"^-?[0-9]+(?:\.[0-9]+)?$".matchRegexp(str)}//if number
                {
                    if(str.asFloat == str.asInteger,
                        {
                            result = str.asInteger;
                        }, {
                            result = str.asFloat;
                        }
                    );
			}
			{"^0[xX][0-9a-fA-F]+$".matchRegexp(str)} {result = str.interpret; } //hex notation
			{"^true$|^Y$|^Yes$|^ON$".matchRegexp(str)} { result = true; }// yaml1.2 /json compatible booleans
			{"^false$|^n$|^FALSE$|^No$|^off$".matchRegexp(str)} { result = false; };
            result;
        };
        changeValue = {|key,val|
            var result = val;
            if(val.isKindOf(Collection), {
                if(val.isKindOf(String), {
                    result = parseYAMLValue.value(val);
                    }, {
                        if(val.isKindOf(Dictionary), {
                            result = val.keysValuesChange(changeValue);
                            }, {
                                result = val.collect({|item|
                                    changeValue.value(nil, item);
                                });
                        })
                })
            });
            result;
        };
		^this.deepCopy.keysValuesChange(changeValue);
    }
}