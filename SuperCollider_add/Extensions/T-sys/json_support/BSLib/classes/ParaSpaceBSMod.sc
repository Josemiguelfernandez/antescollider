//This class is used to create the GUI for the control mixer and the preset mixer. Its original
//design is called ParaSpace and it's made by Thor Magnusson at ixi software. I have made some
// extensive modifications to this class in order to customize it to my needs, hence the added
//'BSMod' to the classname.
//I have tried to label all the changes I have made, but I must admit that bookkeeping of
//modifications went awry along the process. Here's a list over some added features:
//
//- parent / child relation between nodes
//- fixed buggy behaviour related to .relativeOrigin option
//- resize as option for rectangular nodes
//- resizing when egde of node are click-dragged. Resizing from sides and corners.
//- actions for resize mouse event and disconnect
//- possibility to have individual string label offset
//- possibility to make individual nodes "undeleteable"
//- set individual node size and shape.
//- possibility to receive drag an drop from other views
//- metadata for each node
//

//Disclaimer: This is an old class which hasn't been updated in many years, and isn't documented in any way. Use at own risk..
//Eirik Arthur Blekesaune


// (c) 2006, Thor Magnusson - www.ixi-software.net
// GNU licence - google it.


ParaSpaceBSMod {

	var <>paraNodes, connections;
	var chosennode, mouseTracker;
	var win, bounds;
	var downAction, upAction, trackAction, keyDownAction, rightDownAction, overAction, connAction;
	var resizeDownAction, resizeTrackAction, resizeUpAction, disconnAction;//bs mod: added
	var deleteNodeAction;
	var backgrDrawFunc;
	var background, fillcolor;
	var nodeCount, shape;
	var startSelPoint, endSelPoint, refPoint;
	var selNodes, outlinecolor, selectFillColor, selectStrokeColor;
	var keytracker, conFlag; // experimental
	var nodeSize, swapNode;
	var font, fontColor;
	var stringOffset; //bs mod: added
	var canReceiveDragHandler, receiveDragHandler;

	var refresh 			= true;	// false during 'reconstruct'
	var refreshDeferred	= false;
	var lazyRefreshFunc;

	*new { arg w, bounds;
		^super.new.initParaSpaceBSMod(w, bounds);
	}

	initParaSpaceBSMod { arg w, argbounds;
		var a, b, rect, relX, relY, pen, chosenEdge, tempRect;
		bounds = argbounds ? Rect(20, 20, 400, 200);
		bounds = Rect(bounds.left, bounds.top, bounds.width, bounds.height);
//bs mod:		bounds = Rect(bounds.left + 0.5, bounds.top + 0.5, bounds.width, bounds.height);

		if((win= w).isNil, {
			win = GUI.window.new("ParaSpaceBSMod",
				Rect(10, 250, bounds.left + bounds.width + 40, bounds.top + bounds.height+30));
			win.front;
		});

		// if w is not a scrollview but a scwindow
		if(w.isKindOf(Window), {win.acceptsMouseOver = false}); //bs change

		background = Color.white;
		fillcolor = Color.new255(103, 148, 103);
		outlinecolor = Color.red;
		selectFillColor = Color.green(alpha:0.2);
		selectStrokeColor = Color.black;
		paraNodes = List.new; // list of ParaNode objects
		connections = List.new; // list of arrays with connections eg. [2,3]
		nodeCount = 0;
		startSelPoint = 0@0;
		endSelPoint = 0@0;
		refPoint = 0@0;
		shape = "rect";
		conFlag = false;
		nodeSize = 8;
		font = Font("Arial", 9);
		fontColor = Color.black;
		pen	= GUI.pen;

		// tracking the delete button
		keytracker = UserView.new(win, Rect(-10, -10, 10, 10))
			.canFocus_(true)
			.keyDownAction_({ |me, key, modifiers, unicode | // not = keyDownAction_ (as sciss says)
				if(unicode == 127, {
					selNodes.do({arg box;
						paraNodes.copy.do({arg node, i;
							if(box === node and: node.deletable, {this.deleteNode(i)});
						})
					});
				});
				if(unicode == 99, {conFlag = true;}); // c is for connecting
				keyDownAction.value(key, modifiers, unicode);
				this.refresh;
			})
			.keyUpAction_({ |me, key, modifiers, unicode |
				if(unicode == 99, {conFlag = false;}); // c is for connecting

			});
		keytracker.setProperty(\focusColor, Color.clear);
		mouseTracker = GUI.userView.new(win, Rect(bounds.left, bounds.top, bounds.width, bounds.height))
			.canFocus_(false)
			.mouseDownAction_({|me, x, y, mod|
				chosennode = this.findNode(x, y);
				if( (mod & 0x00040000) != 0, {	// == 262401
					paraNodes.add(ParaNodeBSMod.new(x,y, fillcolor, bounds, nodeCount, nodeSize));
					nodeCount = nodeCount + 1;
					paraNodes.do({arg node; // deselect all nodes
					 		node.outlinecolor = Color.black;
							node.refloc = node.nodeloc;
					});
					startSelPoint = x-10@y-10;
					endSelPoint =   x-10@y-10;
				}, {
					if(chosennode !=nil, { // a node is selected
						chosenEdge = List.new;

						if(x < (chosennode.rect.left + 5), {chosenEdge.add(\left)});
						if(y < (chosennode.rect.top + 5), {chosenEdge.add(\top)});
						if(x > (chosennode.rect.right - 5), {chosenEdge.add(\right)});
						if(y > (chosennode.rect.bottom - 5), {chosenEdge.add(\bottom)});


						relX = chosennode.nodeloc.x - bounds.left - 0.5;
						relY = chosennode.nodeloc.y - bounds.top - 0.5;


						refPoint = x@y; // var used here for reference in trackfunc

						if(conFlag == true, { // if selected and "c" then connection is possible
							paraNodes.do({arg node, i;
								if(node === chosennode, {a = i;});
							});
							selNodes.do({arg selnode, j;
								paraNodes.do({arg node, i;
									if(node === selnode, {b = i;
										this.createConnection(a, b);
									});
								});
							});
						});
						downAction.value(chosennode);
					}, { // no node is selected
					 	paraNodes.do({arg node; // deselect all nodes
					 		node.outlinecolor = Color.black;
							node.refloc = node.nodeloc;
					 	});
						startSelPoint = x@y;
						endSelPoint = x@y;
						this.refresh;
					});
				});
			})
			.mouseMoveAction_({|me, x, y, mod|
				if(chosennode != nil, { // a node is selected

					if(chosennode.resizeable and: (chosenEdge.size > 0),
					{
						tempRect = Rect.newSides(
							(chosenEdge.matchItem(\left)).if({x},{chosennode.rect.left}),
							(chosenEdge.matchItem(\top)).if({y},{chosennode.rect.top}),
							(chosenEdge.matchItem(\right)).if({x},{chosennode.rect.right}),
							(chosenEdge.matchItem(\bottom)).if({y},{chosennode.rect.bottom})
						);

						relX = chosennode.nodeloc.x - bounds.left - 0.5;
						relY = chosennode.nodeloc.y - bounds.top - 0.5;

						chosennode.setRect_(tempRect);


						chosennode.childrenNodes.do({arg node;
								node.setLoc_(node.nodeloc);
						});

					},
					{
					relX = chosennode.nodeloc.x - bounds.left - 0.5;
					relY = chosennode.nodeloc.y - bounds.top - 0.5;
					chosennode.setLoc_(Point(
						chosennode.refloc.x + (x - refPoint.x),
						chosennode.refloc.y + (y - refPoint.y)
					));
					chosennode.childrenNodes.do({arg node;
								node.setLoc_(Point(
									node.refloc.x + (x - refPoint.x),
									node.refloc.y + (y - refPoint.y)
								));
					});
					block {|break|
						selNodes.do({arg node;
							if(node === chosennode,{ // if the mousedown box is one of selected
								break.value( // then move the whole thing ...
									selNodes.do({arg node; // move selected boxes
										node.setLoc_(Point(
											node.refloc.x + (x - refPoint.x),
											node.refloc.y + (y - refPoint.y)
										));
									});
								);
							});
						});
					};
					});
					trackAction.value(chosennode);
					this.refresh;
				}, { // no node is selected
					endSelPoint = x@y;
					this.refresh;
				});
			})
			.mouseOverAction_({arg me, x, y;
				chosennode = this.findNode(x, y);
				if(chosennode != nil, {
					relX = chosennode.nodeloc.x - bounds.left - 0.5;
					relY = chosennode.nodeloc.y - bounds.top - 0.5;
					overAction.value(chosennode);
				});
			})
			.mouseUpAction_({|me, x, y, mod|
				if(chosennode !=nil, { // a node is selected

						relX = chosennode.nodeloc.x - bounds.left - 0.5;
						relY = chosennode.nodeloc.y - bounds.top - 0.5;
						upAction.value(chosennode);
						paraNodes.do({arg node;
							node.refloc = node.nodeloc;
						});

					this.refresh;
				},{ // no node is selected
					// find which nodees are selected
					selNodes = List.new;
					paraNodes.do({arg node;
						if(Rect(	startSelPoint.x, // + rect
								startSelPoint.y,									endSelPoint.x - startSelPoint.x,
								endSelPoint.y - startSelPoint.y)
								.containsPoint(node.nodeloc), {
									node.outlinecolor = outlinecolor;
									selNodes.add(node);
						});
						if(Rect(	endSelPoint.x, // - rect
								endSelPoint.y,									startSelPoint.x - endSelPoint.x,
								startSelPoint.y - endSelPoint.y)
								.containsPoint(node.nodeloc), {
									node.outlinecolor = outlinecolor;
									selNodes.add(node);
						});
						if(Rect(	startSelPoint.x, // + X and - Y rect
								endSelPoint.y,									endSelPoint.x - startSelPoint.x,
								startSelPoint.y - endSelPoint.y)
								.containsPoint(node.nodeloc), {
									node.outlinecolor = outlinecolor;
									selNodes.add(node);
						});
						if(Rect(	endSelPoint.x, // - Y and + X rect
								startSelPoint.y,									startSelPoint.x - endSelPoint.x,
								endSelPoint.y - startSelPoint.y)
								.containsPoint(node.nodeloc), {
									node.outlinecolor = outlinecolor;
									selNodes.add(node);
						});
					});
					startSelPoint = 0@0;
					endSelPoint = 0@0;
					this.refresh;
				});
			})
			.drawFunc_({

				pen.width = 1;
				pen.color = background; // background color
				pen.fillRect(bounds); // background fill
				backgrDrawFunc.value; // background draw function

				// the lines
				pen.color = Color.black;
				connections.reverse.do({arg conn;
					pen.line(paraNodes[conn[0]].nodeloc+0.5, paraNodes[conn[1]].nodeloc+0.5);
				});
				pen.stroke;

				// the nodes or circles
				paraNodes.do({arg node;
					if(node.shape == "rect", { //bs_mod: if(shape == ""rect)
						pen.color = node.color;
						pen.fillRect(node.rect);
						pen.color = node.outlinecolor;
						pen.strokeRect(node.rect);
					}, {
						pen.color = node.color;
						pen.fillOval(node.rect);
						pen.color = node.outlinecolor;
						pen.strokeOval(node.rect);
					});
					if(GUI.current.id == \swing, {
					    	if( node.string.size.x > 0, {
						    	pen.fillColor = fontColor;
					    		pen.stringInRect( node.string,
					    			Rect(node.rect.left+node.size.x+5, node.rect.top-3, 80, 16));
					    	});
				    	},{//bs mod: added stringoffset from individual paranodes
					    	node.string.drawInRect(
					    		Rect(node.rect.left+node.size.x+node.stringOffset.x,
					    		node.rect.top+node.stringOffset.y, 80, 16),
				    								font, fontColor);
				    	});

				});
				pen.stroke;

				pen.color = selectFillColor;
				// the selection node
				pen.fillRect(Rect(	startSelPoint.x + 0.5,
									startSelPoint.y + 0.5,
									endSelPoint.x - startSelPoint.x,
									endSelPoint.y - startSelPoint.y
									));
				pen.color = selectStrokeColor;
				pen.strokeRect(Rect(	startSelPoint.x + 0.5,
									startSelPoint.y + 0.5,
									endSelPoint.x - startSelPoint.x,
									endSelPoint.y - startSelPoint.y
									));

				pen.color = Color.black;

				pen.strokeRect(bounds); // background frame
			});
			//.relativeOrigin_(w.isKindOf(Window).not); //bs mod: added,not working with Window

	keytracker.focus(true);
	}

	clearSpace {
		paraNodes = List.new;
		connections = List.new;
		nodeCount = 0;
		this.refresh;
	}

	createConnection {arg node1, node2, refresh=true;
		if((nodeCount < node1) || (nodeCount < node2), {
			"Can't connect - there aren't that many nodes".postln;
		}, {
			block {|break|
				connections.do({arg conn;
					if((conn == [node1, node2]) || (conn == [node2, node1]), {
						break.value;
					});
				});
				// if not broken out of the block, then add the connection
				connections.add([node1, node2]);
				paraNodes[node1].connectedNodes.add(paraNodes[node2]);
				paraNodes[node2].connectedNodes.add(paraNodes[node1]);
				connAction.value(paraNodes[node1], paraNodes[node2]);


				if(refresh == true, {this.refresh});
			}
		});
	}

	deleteConnection {arg node1, node2, refresh=true;
		connections.do({arg conn, i; if((conn == [node1, node2]) || (conn == [node2, node1]),
			 { connections.removeAt(i);
			 paraNodes[node1].connectedNodes.removeAt(
			 	paraNodes[node1].connectedNodes.detectIndex({|item|
			 		item == paraNodes[node2]
			 	});
			 );
			 paraNodes[node2].connectedNodes.removeAt(
			 	paraNodes[node2].connectedNodes.detectIndex({|item|
			 		item == paraNodes[node1]
			 	});
			 );
			 disconnAction.value(paraNodes[node1], paraNodes[node2])
			 })});
		if(refresh == true, {this.refresh});
	}

	deleteConnections { arg refresh=true; // delete all connections
		connections = List.new; // list of arrays with connections eg. [2,3]
		if(refresh == true, {this.refresh});
	}

	createNode {arg x, y, color, refresh=true;
		fillcolor = color ? fillcolor;
		paraNodes.add(ParaNodeBSMod.new(bounds.left+x+0.5, bounds.top+y+0.5, fillcolor, bounds, nodeCount, nodeSize));
		nodeCount = nodeCount + 1;
		if(refresh == true, {this.refresh});
	}

	createNode1 {arg argX, argY, color, refresh=true;
		var x, y;
		x = (argX * bounds.width).round(1);
		y = (argY * bounds.height).round(1);
		fillcolor = color ? fillcolor;
		paraNodes.add(ParaNodeBSMod.new(bounds.left+x+0.5, bounds.top+y+0.5, fillcolor, bounds, nodeCount, nodeSize));
		nodeCount = nodeCount + 1;
		if(refresh == true, {this.refresh});
	}

	deleteNode {arg nodenr, refresh=true; var del;
		del = 0;
		connections.copy.do({arg conn, i;
			if(conn.includes(nodenr), { connections.removeAt((i-del)); del=del+1;})
		});
		connections.do({arg conn, i;
			if(conn[0]>nodenr,{conn[0]=conn[0]-1});if(conn[1]>nodenr,{conn[1]= conn[1]-1});
		});
		if(paraNodes.size > 0, {
			deleteNodeAction.value(paraNodes[nodenr]);
			paraNodes.removeAt(nodenr);
		});
		if(refresh == true, {this.refresh});
	}

	setNodeLoc_ {arg index, argX, argY, refresh=true;
		var x, y;
		x = argX+bounds.left + 0.5;
		y = argY+bounds.top + 0.5;
		paraNodes[index].setLoc_(Point(x, y));
		if(refresh == true, {this.refresh});
	}

	setNodeLocAction_ {arg index, argX, argY, action, refresh=true;
		var x, y;
		x = argX+bounds.left + 0.5;
		y = argY+bounds.top + 0.5;
		paraNodes[index].setLoc_(Point(x, y));
		switch (action)
			{\down} 	{downAction.value(paraNodes[index])}
			{\up} 	{upAction.value(paraNodes[index])}
			{\track} 	{trackAction.value(paraNodes[index])};
		if(refresh == true, {this.refresh});
	}

	getNodeLoc {arg index;
		var x, y;
		x = paraNodes[index].nodeloc.x - bounds.left;
		y = paraNodes[index].nodeloc.y - bounds.top;
		^[x-0.5, y-0.5];
	}

	setNodeLoc1_ {arg index, argX, argY, refresh=true;
		var x, y;
		x = (argX * bounds.width).round(1);
		y = (argY * bounds.height).round(1);
		paraNodes[index].setLoc_(Point(x+bounds.left+0.5, y+bounds.top+0.5));
		if(refresh == true, {this.refresh});
	}

	setNodeLoc1Action_ {arg index, argX, argY, action, refresh=true;
		var x, y;
		x = (argX * bounds.width).round(1);
		y = (argY * bounds.height).round(1);
		paraNodes[index].setLoc_(Point(x+bounds.left+0.5, y+bounds.top+0.5));
		switch (action)
			{\down} 	{downAction.value(paraNodes[index])}
			{\up} 	{upAction.value(paraNodes[index])}
			{\track} 	{trackAction.value(paraNodes[index])};
		if(refresh == true, {this.refresh});
	}

	getNodeLoc1 {arg index;
		var x, y;
		x = (paraNodes[index].nodeloc.x - bounds.left) / bounds.width;
		y = (paraNodes[index].nodeloc.y - bounds.top) / bounds.height;
		^[x, y];
	}

	getNodeStates {
		var locs, color, size, string;
		locs = List.new; color = List.new; size = List.new; string = List.new;
		paraNodes.do({arg node;
			locs.add(node.nodeloc);
			color.add(node.color);
			size.add(node.size);
			string.add(node.string);
		});
		^[locs, connections, color, size, string];
	}

	setNodeStates_ {arg array; // array with [locs, connections, color, size, string]
		if(array[0].isNil == false, {
			paraNodes = List.new;
			array[0].do({arg loc;
				paraNodes.add(ParaNodeBSMod.new(loc.x, loc.y, fillcolor, bounds, nodeCount));
				nodeCount = nodeCount + 1;
				})
		});
		if(array[1].isNil == false, { connections = array[1];});
		if(array[2].isNil == false, { paraNodes.do({arg node, i; node.setColor_(array[2][i];)})});
		if(array[3].isNil == false, { paraNodes.do({arg node, i; node.setSize_(array[3][i];)})});
		if(array[4].isNil == false, { paraNodes.do({arg node, i; node.string = array[4][i];})});
		this.refresh;
	}

	setBackgrColor_ {arg color, refresh=true;
		background = color;
		if(refresh == true, {this.refresh});
	}

	setFillColor_ {arg color, refresh=true;
		fillcolor = color;
		paraNodes.do({arg node;
			node.setColor_(color);
		});
		if(refresh == true, {this.refresh});
	}

	setOutlineColor_ {arg color;
		outlinecolor = color;
		this.refresh;
	}

	setSelectFillColor_ {arg color, refresh=true;
		selectFillColor = color;
		if(refresh == true, {this.refresh});
	}

	setSelectStrokeColor_ {arg color, refresh=true;
		selectStrokeColor = color;
		if(refresh == true, {this.refresh});
	}

	setShape_ {arg argshape, refresh=true, spritenum;
		shape = argshape;
		if(refresh == true, {this.refresh});

	}

	setNodeShape_{ arg index, argshape, refresh=true; //bs mod: added method
		paraNodes[index].shape_(argshape);
		if(refresh == true, {this.refresh});
	}

	setNodeRect_{ arg index, argRect, refresh=true; //bs mod: added method
		paraNodes[index].rect_(argRect);
		if(refresh == true, {this.refresh});
	}

	getNodeRect{ arg index; //bs mod: added method
		^paraNodes[index].rect;
	}

	reconstruct { arg aFunc;
		refresh = false;
		aFunc.value( this );
		refresh = true;
		this.refresh;
	}

	refresh {
		if( refresh, { {mouseTracker.refresh}.defer; });
	}

	lazyRefresh {
		if( refreshDeferred.not, {
			AppClock.sched( 0.02, lazyRefreshFunc );
			refreshDeferred = true;
		});
	}

	setNodeSize_ {arg index, size, refresh=true;
		if(size == nil, {
			nodeSize = index;
			paraNodes.do({arg node; node.setSize_(nodeSize)});
		}, {
			paraNodes[index].setSize_(size);
		});
		if(refresh == true, {this.refresh});
	}

	getNodeSize {arg index;
		^paraNodes[index].size;
	}

	setNodeColor_ {arg index, color, refresh=true;
		paraNodes[index].setColor_(color);
		if(refresh == true, {this.refresh});
	}

	getNodeColor {arg index;
		^paraNodes[index].getColor;
	}

	setFont_ {arg f;
		font = f;
	}

	setFontColor_ {arg fc;
		fontColor = fc;
	}

	setNodeString_ {arg index, string;
		paraNodes[index].string = string;
		this.refresh;
	}

	getNodeString {arg index;
		^paraNodes[index].string;
	}

	setStringOffset{arg point; //bs mod: added method, can also be a functions
		stringOffset = point;
	}

	getStringOffset{ arg index; //bs mod: added method
		^stringOffset;
	}

	setNodeStringOffset_{ arg index, point, refresh=true; //bs mod: added method
		paraNodes[index].stringOffset_(point);
		this.refresh;
	}

	getNodeStringOffset{ arg index; //bs mod: added method
		^paraNodes[index].stringOffset.value(paraNodes[index]);
	}

	// PASSED FUNCTIONS OF MOUSE OR BACKGROUND
	nodeDownAction_ { arg func;
		downAction = func;
	}

	nodeUpAction_ { arg func;
		upAction = func;
	}

	nodeTrackAction_ { arg func;
		trackAction = func;
	}

	nodeOverAction_ { arg func;
		overAction = func;
		win.acceptsMouseOver = true;
	}

	connectAction_ {arg func;
		connAction = func;
	}

	disconnectAction_{ arg func;
		disconnAction = func;
	}

	deleteNodeAction_{ arg func;
		deleteNodeAction = func;
	}

	setMouseOverState_ {arg state;
		win.acceptsMouseOver = state;
	}

	keyDownAction_ {arg func;
		//mouseTracker.canFocus_(true); // in order to detect keys the view has to be focusable
		keyDownAction = func;
	}

	setBackgrDrawFunc_ { arg func;
		backgrDrawFunc = func;
		this.refresh;
	}

	setDragHandler_{ |aFunction|
		mouseTracker.receiveDragHandler_(aFunction);
	}

	setCanReceiveDragHandler_{ |aFunction|
		mouseTracker.canReceiveDragHandler_(aFunction);
	}




	// local function
	findNode {arg x, y;
		paraNodes.reverse.do({arg node;
			if(node.rect.containsPoint(Point.new(x,y)), {
				^node;
			});
		});
		^nil;
	}
}

ParaNodeBSMod {
	var <>fillrect, <>state, <>size, <rect, <>nodeloc, <>refloc, <>color, <>outlinecolor;
	var <>spritenum, <>temp, <>shape, <>resizeable = false;//bs mod: added shape resizeable
	var >bounds, <>type, <>metadata, <>deletable = true;//bs mod: bounds as setter and getter
	var <>string, >stringOffset, <parentNode, <>childrenNodes; //bs mod: added stringOffset
	var <connectedNodes;

	*new { arg x, y, color, bounds, spnum, size;
		^super.new.initGridNode(x, y, color, bounds, spnum, size);
	}

	initGridNode {arg argX, argY, argcolor, argbounds, spnum, argsize;
		spritenum = spnum;
		nodeloc =  Point(argX, argY);
		refloc = nodeloc;
		color = argcolor;
		outlinecolor = Color.black;
		size = (x: argsize ? 8, y: argsize ? 8);//bs mod: argsize ? 8
		bounds = argbounds;
		rect = Rect((argX-(size.x/2))+0.5, (argY-(size.y/2))+0.5, size.x, size.y);
		string = "";
		stringOffset = Point.new(1, 10);//bs mod, can also be a function
		temp = nil;
		childrenNodes = List.new;//bs mod: added childrenNodes as List
		metadata = ();
		connectedNodes = List.new;
	}

	setRect_{ arg argRect; //bs mod: added method
		//var tempBounds, point;
		//tempBounds = this.bounds;
//		size = rect.extent;
//		point = argRect.origin;
		size = rect.extent;
		rect = argRect.width_(argRect.width.max(10)).height_(argRect.height.max(10));
		nodeloc = rect.center;


	}

	setLoc_ {arg point;
		var tempBounds, tempSize;//bs mod
		tempBounds = this.bounds;
		size = rect.extent;
		nodeloc = point;

		// keep paranode inside the bounds
		if((point.x) > (tempBounds.left+tempBounds.width),
			{nodeloc.x = tempBounds.left+tempBounds.width - (size.x * 0.5)});
		if((point.x) < (tempBounds.left),
			{nodeloc.x = tempBounds.left + (size.x * 0.5)});
		if((point.y) > (tempBounds.top+tempBounds.height),
			{nodeloc.y = tempBounds.top+tempBounds.height - (size.y * 0.5)});
		if((point.y) < (tempBounds.top),
			{nodeloc.y = tempBounds.top + (size.y * 0.5)});
		rect = Rect((nodeloc.x-(size.x/2))+0.5, (nodeloc.y-(size.y/2))+0.5, size.x, size.y);


	}

	parentNode_{ arg argNode;//bs mod: added method
		parentNode = argNode;
		parentNode.childrenNodes.add(this);
		bounds = argNode.rect;
		this.setLoc_(nodeloc);//set loc to ensure it's placed inside parentnode
	}

	addChildrenNode{ arg argNode;
		argNode.parentNode_(this);
	}

	bounds{//bs mod: added new getter
		^if(parentNode.isNil, {bounds}, {parentNode.rect});
	}

	setState_ {arg argstate;
		state = argstate;
	}

	stringOffset{
		^stringOffset.value(this);
	}

	getState {
		^state;
	}

	setSize_ {arg argsize;
		size = argsize;
		rect = Rect((nodeloc.x-(size.x/2))+0.5, (nodeloc.y-(size.y/2))+0.5, size.x, size.y);
	}


	getSize {
		^size.x;
	}

	setColor_ {arg argcolor;
		color = argcolor;
	}

	getColor {
		^color;
	}
}