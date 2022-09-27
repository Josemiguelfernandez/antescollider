JSONSerializer {
	classvar <>indentationLevel, <maxColums;

	*initClass {
		indentationLevel = 0;
		maxColums = 60;
	}

	*writeToFile{arg obj, file;
		var done = false;
		if(obj.isString and: {done.not}, {
			file << obj.asCompileString.("\n", JSON.nl).replace("\t", JSON.tab);
			done = true;
		});
		if(obj.class === Symbol and: {done.not}, {
			JSONSerializer.writeToFile(obj.asString, file);
			done = true;
		});

		if(obj.isKindOf(Dictionary) and: {done.not}, {
			JSONSerializer.indentationLevel = JSONSerializer.indentationLevel + 1;
			file << "{" << Char.nl << Char.tab.dup(JSONSerializer.indentationLevel).join;
			obj.keysValuesDo({ arg key, value, i;
				file << (key.asString.asCompileString ++ "\t: ");
				JSONSerializer.writeToFile(value, file);
				if((i+1) != obj.size, {
					file << "," << Char.nl << Char.tab.dup(JSONSerializer.indentationLevel).join;
				}, {
					JSONSerializer.indentationLevel = JSONSerializer.indentationLevel - 1;
					file << Char.nl << Char.tab.dup(JSONSerializer.indentationLevel).join << "}";
				});
			});
			done = true;
		});


		if(obj.isKindOf(Association) and: {done.not}, {
			"association".postln;
			JSONSerializer.indentationLevel = JSONSerializer.indentationLevel + 1;
			// file << "{" << Char.nl << Char.tab.dup(JSONSerializer.indentationLevel).join;
			obj.do({|argu, i|
				file << (argu.key.asString.asCompileString ++ "\t: ");
				JSONSerializer.writeToFile(argu.value, file);
				/*				if((i+1) != obj.size, {
				file << "," << Char.nl << Char.tab.dup(JSONSerializer.indentationLevel).join;
				}, {
				JSONSerializer.indentationLevel = JSONSerializer.indentationLevel - 1;
				file << Char.nl << Char.tab.dup(JSONSerializer.indentationLevel).join << "}";
				});*/
			});
			done = true;
		});
		if(obj.isKindOf(List) and: {done.not}, {
			"Lista_____________".postln;
			obj.postln;
			obj.class.postln;
			JSONSerializer.indentationLevel = JSONSerializer.indentationLevel + 1;
			file << "{" << Char.nl << Char.tab.dup(JSONSerializer.indentationLevel).join;
			obj.do({|argu, i|
				file << (argu.key.asString.asCompileString ++ "\t: ");
				JSONSerializer.writeToFile(argu.value, file);
				if((i+1) != obj.size, {
					file << "," << Char.nl << Char.tab.dup(JSONSerializer.indentationLevel).join;
				}, {
					JSONSerializer.indentationLevel = JSONSerializer.indentationLevel - 1;
					file << Char.nl << Char.tab.dup(JSONSerializer.indentationLevel).join << "}";
				});
			});
			done = true;
		});

		if(obj.isNil and: {done.not}, {
			file << "null";
			done = true;
		});
		if(obj === true and: {done.not}, {
			file << "true";
			done = true;
		});
		if(obj === false and: {done.not}, {
			file << "false";
			done = true;
		});
		if(obj.isNumber and: {done.not}, {
			if(obj.isNaN, {
				file << "NaN";
				done = true;
			});
			if(obj === inf, {
				file << "Infinity";
				done = true;
			});
			if(obj === (-inf), {
				file << "-Infinity";
				done = true;
			});
			file << obj.asString;
			done = true;
		});
		if(obj.isKindOf(SequenceableCollection) and: {done.not}, {
			file << "[";
			if(JSONSerializer.getObjectStringLength(obj) > 60, {
				file << Char.space(JSONSerializer.indentationLevel);
			});
			obj.collect({ arg sub, i;
				JSONSerializer.writeToFile(sub, file);
				if((i+1) != obj.size, {file << ", "});
				if(JSONSerializer.getObjectStringLength(obj) > 60, {
					file << Char.space(JSONSerializer.indentationLevel);
				});
			});
			file << "]";
			done = true;
		});
	}

	*getObjectStringLength{arg obj;
		^JSON.stringify(obj).size;
	}
}