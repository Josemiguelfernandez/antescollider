

Tmodule2  {
	var metadata, <>synthdefname, varsynth, all, <>paramslider, <>parammultislider, <>parammultisliderbuffer, <>parambutton, <>synthdefname, <>soundfileview, <>menu, <>synthtype;

	/*	prepareForPlay {|server|
	if(group.isNil, {
	internalGroup= Group.after(server.defaultGroup);
	CmdPeriod.doOnce({internalGroup.free});
	group= internalGroup;
	});
	synth= Synth.controls(this.def.name, args, group, defaultAddAction);
	}*/
	/*
	free {
	RedAbstractModule.all.remove(this);
	synth.free;
	internalGroup.free;
	}
	gui {|parent, position|
	^RedEffectModuleGUI(this, parent, position);
	}*/

	var prDef;
	*new {|out= 0, group, defaultAddAction= \addToTail|
		^super.new.initTmodule2(out, group, defaultAddAction);
	}
	*initTmodule2 {
		//all= [];
	}

	*defname {

		^this.metadata[\metadata][\synthdefname].asArray;

	}

	*toto {

		^this;

	}

	*gui {

		TModuleGUI(TModule(this));

	}

	// AudioOut
	*synth {|group, bus, index, in_ramp, out_ramp, chnl, amp|
		//this.synth2(this.metadata[\metadata][\synthdefname], nil);

		^Synth(this.metadata[\metadata][\synthdefname], [\in, bus, \out, 0, \index, index, \in_ramp, in_ramp, \out_ramp, out_ramp, \out, chnl, \amp, amp], group, '\addToTail');

	}


	*synth2 {|group, bus, index, in_ramp, chnl, amp|
		//this.synth2(this.metadata[\metadata][\synthdefname], nil);

		^Synth(this.metadata[\metadata][\synthdefname], [\in, bus.index, \out, 0, \index, index, \in_ramp, in_ramp, \out, chnl, \amp, amp], group, '\addToTail');

	}
	*synth_group {|group, bus, in_ramp, gbus, amp|
		//this.synth2(this.metadata[\metadata][\synthdefname], nil);

		^Synth(this.metadata[\metadata][\synthdefname], [\in, bus, \index, bus.index, \in_ramp, in_ramp, \out, gbus, \amp, amp], group, '\addToTail');

	}

	*synth_group_kubus {|group, bus, in_ramp, gbus, amp, hp_offset|
		//this.synth2(this.metadata[\metadata][\synthdefname], nil);

		^Synth(this.metadata[\metadata][\synthdefname], [\in, bus, \index, bus.index, \in_ramp, in_ramp, \out, gbus, \amp, amp, \ch_range, hp_offset], group, '\addToTail');

	}
	// AudioOut
	*synthenv {|group, bus, index, env, chnl, amp, dur| // ERROR 2 out...
		//this.synth2(this.metadata[\metadata][\synthdefname], nil);

		^Synth(this.metadata[\metadata][\synthdefname], [\in, bus, \out, 0, \index, index, \env, env, \out, chnl, \amp, amp, \dur, dur], group, '\addToTail');

	}

	*synthenv2 {|group, bus, env, gbus, amp, dur|
		//this.synth2(this.metadata[\metadata][\synthdefname], nil);

		^Synth(this.metadata[\metadata][\synthdefname], [\in, bus, \out, gbus, \env, env, \amp, amp, \dur, dur], group, '\addToTail');

	}

	// AudioInAux
	*synthInAux {|group, busin, busaout, index, in_ramp|
		//this.synth2(this.metadata[\metadata][\synthdefname], nil);

		^Synth(this.metadata[\metadata][\synthdefname], [\in, busin, \out, busaout, \index, index, \in_ramp, in_ramp], group, '\addToTail').register; //register para ver si la conexion no fue interrrumpida antes

	}

	// Modulos
	*synthout {|outsynth, bus|


		^Synth(this.metadata[\metadata][\synthdefname], [\in, bus, \out, bus], outsynth, '\addBefore');

	}

	*synthoutconv {|outsynth, bus| // expecifico para init buffer convrev


		^Synth(this.metadata[\metadata][\synthdefname], [\in, bus, \out, bus, \convbuf, ~irspectrum], outsynth, '\addBefore');

	}

	*synthoutpar {|outsynth, params| //, bus
		/*"el bus es:".postln;
		bus.postln;
		([\in, bus, \out, bus] ++ params).postln;*/
		/*^Synth(this.metadata[\metadata][\synthdefname], [\in, bus, \out, bus] ++ params, outsynth, '\addBefore');*/
		^Synth(this.metadata[\metadata][\synthdefname], params, outsynth, '\addBefore');

	}

	*synthafter {|synthbefore, bus|


		^Synth(this.metadata[\metadata][\synthdefname], [\in, bus, \out, bus], synthbefore, '\addAfter');

	}

	*synthafternw{|synthbefore, bus, params|


		^Synth(this.metadata[\metadata][\synthdefname], params, synthbefore, '\addAfter');

	}

	*synthbefore {|synthafter, bus|


		^Synth(this.metadata[\metadata][\synthdefname], [\in, bus, \out, bus], synthafter, '\addBefore');

	}

	synth2 {|synthdef, group|

		varsynth = Synth(synthdef, nil, group, '\addToTail');
	}

	*main {
		var mainparam, result;

		/*		mainparam = this.metadata[\metadata][\main];
		^[mainparam, this.metadata[\metadata][sliders][mainparam]]:*/
		mainparam = this.metadata[\metadata][\main];
		result = [mainparam, this.metadata[\metadata][\sliders].detect({|x| x.key == mainparam}).value];
		// ^mainparam;
		^result;
	}

	*ampdefault {

		^this.metadata[\metadata][\sliders].detect({|x| x.key == \amp}).value.default

	}

	*background {
		var background, type = this.metadata[\metadata][\type];
		case {type == \gen} {background = Color.new255(120, 120, 120)}
		{type == \fx} {background = Color.new255(180, 120, 120)}
		{type == \an} {background = Color.new255(120, 180, 120)}
		{type == \in} {background = Color.new255(86, 100, 120)};
		^background;

	}

	*paramslider {
		// ^paramslider = this.metadata[\metadata][\sliders];
		^this.metadata[\metadata][\sliders];
	}

	*parammultislider {
		// ^parammultislider = this.metadata[\metadata][\multisliders]; //.keys.asArray;
		^this.metadata[\metadata][\multisliders]; //.keys.asArray;
	}

	*parammultisliderbuffer { // crea buffer a partir de multislider
		// ^parammultislider = this.metadata[\metadata][\multisliders]; //.keys.asArray;
		^this.metadata[\metadata][\multisliderbuf]; //.keys.asArray;
	}


	*parambutton {
		// ^parambutton = this.metadata[\metadata][\buttons]; //.keys.asArray;
		^this.metadata[\metadata][\buttons]; //.keys.asArray;
	}

	*synthdefname {
		// ^synthdefname = this.metadata[\metadata][\synthdefname].asArray; //.keys.asArray;
		^this.metadata[\metadata][\synthdefname].asArray; //.keys.asArray;
	}

	*soundfileview {
		// ^soundfileview = this.metadata[\metadata][\soundfileview];
		^this.metadata[\metadata][\soundfileview];
	}

	*soundfileviewconv {
		// ^soundfileview = this.metadata[\metadata][\soundfileview];
		^this.metadata[\metadata][\soundfileviewconv];
	}

	*menu {
		// ^menu = this.metadata[\metadata][\menu];
		^this.metadata[\metadata][\menu];
	}

	*synthtype {
		// ^synthtype = this.metadata[\metadata][\type];
		^this.metadata[\metadata][\type];
	}



	*def {^this.subclassResponsibility(thisMethod)}
}


