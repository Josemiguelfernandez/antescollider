///////////////////////
// Model.sc
Model {
	var <valA;

	*new { | valA |
		^super.new.init(valA);
	}

	init { | valA |
		this.valA = valA;
	}

	valA_ { |new_val|
		valA = new_val;
		this.changed(\valA, new_val);
	}

}

//Views.sc
Views {

	var <>model;
	var <>window, <>slider, <>level, <>numberbox;

	*new { |model|
		^super.newCopyArgs(model).init(model);
	}

	init { |mdl|
		model = mdl;
		model.addDependant(this);
		this.makeGui;
	}

	makeGui {

		window = Window("MyWindow", Rect(100,100,288,80));

		numberbox = NumberBox(window,Rect(20,20,44,20))
		.value_(model.valA)
		.action_({ |view| model.valA_(view.value) });

		slider = Slider(window, Rect(68,20,200,20))
		.value_(model.valA)
		.action_({ |view| model.valA_(view.value) });

		level = LevelIndicator(window, Rect(272,20,5,20))
		.warning_(0.8)
		.critical_(0.95);

		window.front;
		window.onClose_({ model.removeDependant(this)});
	}


	update { |obj, what, val|
		if(what == \valA, {
			{
				numberbox.value_(val);
				slider.value_(val);
				level.value_(val);
			}.defer;
		});
	}
}

/*//main.scd after re-compiling classes etc....
~model = Model(1);
~view = Views(~model);

//after moving the slider and numberbox,  checking valA shows they work but aren't updating each other
~model.valA*/