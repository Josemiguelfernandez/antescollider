

var server, saxIn, n, antes, acc, server_num, dbspec;

/*var osc, fade_val1, fade_val2, fade_val3, fade_val4, fade_val5, fade_val6, fade_val7, fade_val8;
var lemur_port = 9889; //9876;*/
server_num = 0;

server = Server.default;
// saxIn = 8; // bus input saxophone babyface 8

saxIn = 17; //17; //9; //22;

/////ATENCION!!!!!! n localhost 8888 para resto de piezas, a causa de Riot
n = NetAddr("localhost", 8888); // adress to send from sclang to Max
antes = NetAddr("localhost", 1111); // adress to send from sclang to antescofo

dbspec = ControlSpec(0.ampdb, 2.ampdb, \db, 0, 0, \dB);

~synth = Dictionary.new;
~synth.values.collect(_.set(\free, 0));

~multisynth = Dictionary.new;
~bus_multisynth = Dictionary.new;
~dico_buses = Dictionary.new;
~simul = Dictionary.new;

~aux = Dictionary.new;
~aux.values.collect(_.set(\free, 0));

~aux_connections = Dictionary.new;
~aux_connections.values.collect(_.set(\free, 0));

~group = Dictionary.new;
~group_bus = Dictionary.new;
~group_out = Dictionary.new;
// ~group_synth = Dictionary.new;
~groups_synth = Dictionary.new;

~fader1 = Dictionary.new;
~fader2 = Dictionary.new;
~fader3 = Dictionary.new;
~fader4 = Dictionary.new;
~fader5 = Dictionary.new;
~fader6 = Dictionary.new;
~fader7 = Dictionary.new;
~fader8 = Dictionary.new;
~fader9 = Dictionary.new;
~fader10 = Dictionary.new;
~fader11 = Dictionary.new;
~fader12 = Dictionary.new;
~fader13 = Dictionary.new;
~fader14 = Dictionary.new;

~fader_reg = Dictionary.new;

~fadelev = Dictionary[\fader1 -> \fade1, \fader2 -> \fade2, \fader3 -> \fade3, \fader4 -> \fade4, \fader5 -> \fade5, \fader6 -> \fade6, \fader7 -> \fade7, \fader8 -> \fade8, \fader9 -> \fade9, \fader10 -> \fade10, \fader11 -> \fade11, \fader12 -> \fade12, \fader13 -> \fade13, \fader14 -> \fade14];

~fader_reset = Dictionary[\fader1 -> \f1, \fader2 -> \f2, \fader3 -> \f3, \fader4 -> \f4, \fader5 -> \f5, \fader6 -> \f6, \fader7 -> \f7, \fader8 -> \f8, \fader9 -> \f9, \fader10 -> \f10, \fader11 -> \f11, \fader12 -> \f12, \fader13 -> \f13, \fader14 -> \f14];



// ~fader1.values.collect(_.set(\amp, -120)) //control

// OSCdef(\TouchOSC, {|msg|
// 	{server.volume = msg[1].linlin(0, 1, -60, 6)}.defer;
// }, '/1/fader1', nil, 6666).fix;





OSCFunc({ |msg|
	msg.postln;
	// antes.sendMsg(\sconset, "onset"); // compatibilidad con anteriores
	antes.sendMsg(\scrange, msg[2]) // puede mandar lo que sea en funcion del id declarado al iniciar la fundef FftOnsetReplay
}, '/rangedetect').fix;

OSCFunc({ |msg|
	msg.postln;
	// antes.sendMsg(\sconset, "onset"); // compatibilidad con anteriores
	antes.sendMsg(\sconset, msg[2]) // puede mandar lo que sea en funcion del id declarado al iniciar la fundef FftOnsetReplay
}, '/onsetdetect').fix;

OSCFunc({ |msg|
	msg.postln;
	"toto".postln;
	// antes.sendMsg(\sconset, "onset"); // compatibilidad con anteriores
	antes.sendMsg(\sconset2, msg[2]) // puede mandar lo que sea en funcion del id declarado al iniciar la fundef FftOnsetReplay
}, '/onsetdetect2').fix;


acc = NetAddr("localhost", 3500); // adress to send from sclang to Max
OSCFunc({ |msg|
	// msg.postln;
	acc.sendMsg(\mi_list, *msg.drop(3))
}, '/mi-list').fix;



OSCdef(\Faders, {|msg|
	msg.postln;
	// ~synth[msg[1]].outsynth.postln;
	// ~faders.add(msg[1]-> TWgui
	currentEnvironment.at(msg[2]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
	~fader_reg.add(msg[1]-> msg[2]); // registran en que fader se encuentra la synth
	// currentEnvironment.at(msg[2]++"lev").add(msg[1]-> ~synth[msg[1]].outsynth);

}, '/fader', nil, 7002).fix;

// ~fader1
/*~f1.put(0, 0)*/
~f1 = Array.fill(15, 0); // nombre se synth par fade ici 15
~f2 = Array.fill(15, 0);
~f3 = Array.fill(15, 0);
~f4 = Array.fill(15, 0);

~f5 = Array.fill(15, 0);
~f6 = Array.fill(15, 0);
~f7 = Array.fill(15, 0);
~f8 = Array.fill(15, 0);

~f9 = Array.fill(30, 0);
~f10 = Array.fill(30, 0);
~f11 = Array.fill(30, 0);
~f12 = Array.fill(15, 0);

~f13 = Array.fill(30, 0);
~f14 = Array.fill(15, 0);

///////////////MIDI CONTROLLER

/*MIDIIn.connectAll;
MIDIFunc.cc({arg ...args;
// args.postln;
case
{args[1] == 0} {{~slider1.valueAction_(args[0].linlin(0, 127, 0.0, 1.0))}.defer}
{args[1] == 1} {{~slider2.valueAction_(args[0].linlin(0, 127, 0.0, 1.0))}.defer}
{args[1] == 2} {{~slider3.valueAction_(args[0].linlin(0, 127, 0.0, 1.0))}.defer}
{args[1] == 3} {{~slider4.valueAction_(args[0].linlin(0, 127, 0.0, 1.0))}.defer}
{args[1] == 4} {{~slider5.valueAction_(args[0].linlin(0, 127, 0.0, 1.0))}.defer}
{args[1] == 5} {{~slider6.valueAction_(args[0].linlin(0, 127, 0.0, 1.0))}.defer}
{args[1] == 6} {{~slider7.valueAction_(args[0].linlin(0, 127, 0.0, 1.0))}.defer}
{args[1] == 8} {{~slider8.valueAction_(args[0].linlin(0, 127, 0.0, 1.0))}.defer}

// args.postln
}, (0..8)); // match cc 1-10*/

///////////////MIDI CONTROLLER

// ~fader1.values.collect(
// \quit.isString

OSCdef(\debugging, {|msg|
	var nom;
	msg.postln;

}, '/debug', nil, 7002).fix;


OSCdef(\servers, {|msg|
	var nom;
	msg.postln;

	// nom = ("server"++server_num).asSymbol;

	if (~scservers.includesKey(msg[1])) {("this server exist "++msg[1]).postln} {
		// Synth(\play_buf2, [\buf, ~wave_buff[msg[1]], \out, msg[2], \amp, msg[3]]) // buf chnl amp

		~scservers.add(msg[1] -> Server.new(msg[1], NetAddr("127.0.0.1", 57110 + server_num + 1)));
		~scservers[msg[1]].options.inDevice = ~serverInDevice;
		~scservers[msg[1]].options.outDevice = ~serverOutDevice;
		~scservers[msg[1]].options.numAudioBusChannels = 20000;
		~scservers[msg[1]].options.maxSynthDefs = 2048;

		~scservers[msg[1]].options.numInputBusChannels = ~servers_in; // setup in startup file
		~scservers[msg[1]].options.numOutputBusChannels = ~servers_out;

		~scservers[msg[1]].options.sampleRate = ~servers_sr; //48000; //48000; //44100; //; //
		~scservers[msg[1]].options.maxNodes = 16384;
		//~scservers[msg[1]].internal.options.numOutputBusChannels = 8;
		//~scservers[msg[1]].internal.options.numInputBusChannels = 8;
		~scservers[msg[1]].options.memSize = 8192 * 128;  //so lots of memory for delay lines in Comb UGens etc
		// ~scservers[msg[1]].internal.options.sampleRate = 44100; //48000; //44100; //
		~scservers[msg[1]].options.numWireBufs = 256;
		~scservers[msg[1]].options.blockSize = 64;
		~scservers[msg[1]].latency=0.025; //low latency is helpful for optimal performance for some machine listening processes
		~scservers[msg[1]].waitForBoot({
			msg[1].postln;
			InitServer_buffer.new(~scservers[msg[1]]); //envia a la clase InitServer_buffer para cargar los buffers en cada server
		});
		~scservers_groups.add(msg[1] -> List[]);

		server_num = server_num + 1;

		/*			~group.add(msg[1].asSymbol -> Group.new);
		~group[msg[1]].postln;
		"toto".postln;
		~group_bus.add(msg[1]-> Bus.audio(server, 8));
		// ~group_bus[msg[1]].postln;
		// synth_group {|group, bus, in_ramp, chnl, amp|
		// "tototototototototototot".postln;
		// msg[2].posln;
		~group_out.add(msg[1]-> AudioOut8.synth_group(~group[msg[1]], ~group_bus[msg[1]], 0, msg[2], 0));*/
		n.sendMsg(\server, msg[1])
	};

	case
	{msg[2] == \quit} {~scservers[msg[1]].quit;
		~scservers.removeAt(msg[1])
	}
	{msg[2] == \plot} {{~scservers[msg[1]].plotTree}.defer}
	{msg[2] == \levels} {{MiServerMeter(~scservers[msg[1]])}.defer}
	{msg[2] == \vol} {{~scservers[msg[1]].volume.gui}.defer}
	{msg[2] == \query} {{~scservers[msg[1]].queryAllNodes(true)}.defer}
	{msg[2] == \freeAll} {{~scservers[msg[1]].freeAll}.defer}

	// {msg[2] == \amp} {~group_out[msg[1]].set(\amp, msg[3])}
	/*	{msg[2] == \pause} {fork {
	~group_out[msg[1]].set(\amp, -120); //fade out
	0.01.wait;
	~group[msg[1]].run(false) // pause all group
	}}
	{msg[2] == \resume} {fork {
	~group_out[msg[1]].set(\amp, 0); //fade In
	0.01.wait;
	~group[msg[1]].run(true) // resume group
	}}*/
	// {msg[2].isNumber}
	// {

	//}
}, '/servers', nil, 7002).fix;

// ~scservers[\server2].makeGui

// ServerTreeGui.makeWindow(~scservers[\server2])
/*MiServerMeter(~scservers[\toto1])
MiServerMeter(~scservers[\toto2])
MiServerMeter(~scservers[\toto3])
MiServerMeter(~scservers[\toto4])
//
~scservers[\toto1].volume.gui
~scservers[\toto2].volume.gui
~scservers[\toto3].volume.gui
~scservers[\toto4].volume.gui*/

OSCdef(\Actives_Server, {|msg|
	var servers;
	/*msg.postln;*/
	servers = ~scservers.order; //.keys.asArray
	if (~scservers.keys.notNil){
		n.sendMsg(\active_server, *servers)
	}{
		n.sendMsg(\active_server, "no server")
	}
}, '/actives_server', nil, 7002).fix;


OSCdef(\Group_server, {|msg|
	var bus_server;
	//msg.postln;
	if (~scservers_groups[msg[1]].asArray.notNil){
		bus_server = ~scservers_groups[msg[1]].asArray;
		n.sendMsg(\server_group, *bus_server)
	}{
		n.sendMsg(\server_group, "no server")
	}
}, '/group_server', nil, 7002).fix;


OSCdef(\Synth_group, {|msg|
	var bus_server;
	//msg.postln;
	if (~groups_synth[msg[1]].asArray.notNil){
		bus_server = ~groups_synth[msg[1]].asArray;
		n.sendMsg(\synth_group, *bus_server)
	}{
		n.sendMsg(\synth_group, "no group")
	}
}, '/group_synth', nil, 7002).fix;



OSCdef(\Free_Servers, {|msg|
	msg.postln;
	if (~scservers.size != 0) {
		"quit server"++~scservers[msg[1]].postln;
		~scservers[msg[1]].quit;
		~scservers.removeAt(msg[1]);
	} //set(\free, 0)}
	// ~scservers[msg[1]].postln;
	/*	~scservers[msg[1]].value.class.postln;
	if(~scservers[msg[1]].value.class == \Server) {
	"quit server"++~scservers[msg[1]].postln;
	~scservers[msg[1]].quit;} //set(\free, 0)}
	{"server".postln}*/
	{"no hay server!!".postln};

}, '/freeservers', nil, 7002).fix;

// ~scservers[\toto1].value.class

OSCdef(\Clear_servers, {|msg|
	msg.postln;
	if (~scservers.size != 0) {
		~scservers.values.collect({|serv|
			"quit server"++serv.postln;
			serv.quit;
			// ~group.removeAt()
		});
		{fork {
			0.01.wait;
		}};
		~scservers = Dictionary.new;
	} {"no hay servers!!".postln};
}, '/free_all_servers', nil, 7002).fix;



OSCdef(\Mix_group, {|msg|
	// msg.postln;
	case
	{msg[2] == \off} {~group[msg[1]].set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \amp} {~group_out[msg[1]].set(\amp, msg[3])}
	{msg[2] == \pause} {fork {
		~group_out[msg[1]].set(\amp, -120); //fade out
		0.01.wait;
		~group[msg[1]].run(false) // pause all group
	}}
	{msg[2] == \resume} {fork {
		~group_out[msg[1]].set(\amp, 0); //fade In
		0.01.wait;
		~group[msg[1]].run(true) // resume group
	}}
	{msg[2].isNumber}
	{
		if (~group.includesKey(msg[1])) {"this group exist".postln} {
			// Synth(\play_buf2, [\buf, ~wave_buff[msg[1]], \out, msg[2], \amp, msg[3]]) // buf chnl amp
			// msg[3].postln;
			if(msg[3].notNil) {
				~group.add(msg[1].asSymbol -> Group.new(~scservers[msg[3]])); //specific server
				~group_bus.add(msg[1]-> Bus.audio(~scservers[msg[3]], 8));
				// ~scservers_groups.add(~scservers[msg[3]].asSymbol, msg[1]);
				~scservers_groups[msg[3]].add(msg[1]); //para visualizar servers group desde Max
				~groups_synth.add(msg[1] -> List[]);
				/*				"__________GROUPA".postln;
				msg[1].postln*/
			}{
				~group.add(msg[1].asSymbol -> Group.new); // crea group en default server
				~group_bus.add(msg[1]-> Bus.audio(server, 8));
				~scservers_groups[\local].add(msg[1]); //para visualizar servers group desde Max
				~groups_synth.add(msg[1] -> List[]);
				// "__________GROUPB".postln;
				// msg[1].postln

			};
			// ~group[msg[1]].postln;
			// "toto".postln;

			// ~group_bus[msg[1]].postln;
			// synth_group {|group, bus, in_ramp, chnl, amp|
			// "tototototototototototot".postln;
			// msg[2].posln;
			~group_out.add(msg[1]-> AudioOut8.synth_group(~group[msg[1]], ~group_bus[msg[1]], 0, msg[2], 0));
			n.sendMsg(\active_group, msg[1])
		}
	}
}, '/mix_group', nil, 7002).fix;


OSCdef(\Mix_group16, {|msg|
	// msg.postln;
	case
	{msg[2] == \off} {~group[msg[1]].set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \amp} {~group_out[msg[1]].set(\amp, msg[3])}
	{msg[2] == \pause} {fork {
		~group_out[msg[1]].set(\amp, -120); //fade out
		0.01.wait;
		~group[msg[1]].run(false) // pause all group
	}}
	{msg[2] == \resume} {fork {
		~group_out[msg[1]].set(\amp, 0); //fade In
		0.01.wait;
		~group[msg[1]].run(true) // resume group
	}}
	{msg[2].isNumber}
	{
		if (~group.includesKey(msg[1])) {"this group exist".postln} {
			// Synth(\play_buf2, [\buf, ~wave_buff[msg[1]], \out, msg[2], \amp, msg[3]]) // buf chnl amp
			msg[3].postln;
			if(msg[3].notNil) {
				~group.add(msg[1].asSymbol -> Group.new(~scservers[msg[3]])); //specific server
				~group_bus.add(msg[1]-> Bus.audio(~scservers[msg[3]], 16));
				~scservers_groups[msg[3]].add(msg[1]); //para visualizar servers group desde Max
				~groups_synth.add(msg[1] -> List[]);
			}{
				~group.add(msg[1].asSymbol -> Group.new); // crea group en default server
				~group_bus.add(msg[1]-> Bus.audio(server, 16));
				~scservers_groups[\local].add(msg[1]); //para visualizar servers group desde Max
				~groups_synth.add(msg[1] -> List[]);
			};
			// ~group[msg[1]].postln;
			// "toto".postln;

			// ~group_bus[msg[1]].postln;
			// synth_group {|group, bus, in_ramp, chnl, amp|
			// "tototototototototototot".postln;
			// msg[2].posln;
			~group_out.add(msg[1]-> AudioOut16.synth_group(~group[msg[1]], ~group_bus[msg[1]], 0, msg[2], 0));
			n.sendMsg(\active_group, msg[1])
		}
	}
}, '/mix_group16', nil, 7002).fix;


OSCdef(\Mix_groupKubus, {|msg|
	// msg.postln;
	case
	{msg[2] == \off} {~group[msg[1]].set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \amp} {~group_out[msg[1]].set(\amp, msg[3])}
	{msg[2] == \pause} {fork {
		~group_out[msg[1]].set(\amp, -120); //fade out
		0.01.wait;
		~group[msg[1]].run(false) // pause all group
	}}
	{msg[2] == \resume} {fork {
		~group_out[msg[1]].set(\amp, 0); //fade In
		0.01.wait;
		~group[msg[1]].run(true) // resume group
	}}
	{msg[2].isNumber}
	{
		if (~group.includesKey(msg[1])) {"this group exist".postln} {
			// Synth(\play_buf2, [\buf, ~wave_buff[msg[1]], \out, msg[2], \amp, msg[3]]) // buf chnl amp
			msg[3].postln;
			if(msg[3].notNil) {
				~group.add(msg[1].asSymbol -> Group.new(~scservers[msg[3]])); //specific server
				~group_bus.add(msg[1]-> Bus.audio(~scservers[msg[3]], 43));
				~scservers_groups[msg[3]].add(msg[1]); //para visualizar servers group desde Max
				~groups_synth.add(msg[1] -> List[]);
			}{
				~group.add(msg[1].asSymbol -> Group.new); // crea group en default server
				~group_bus.add(msg[1]-> Bus.audio(server, 43));
				~scservers_groups[\local].add(msg[1]); //para visualizar servers group desde Max
				~groups_synth.add(msg[1] -> List[]);
			};
			// ~group[msg[1]].postln;
			// "toto".postln;

			// ~group_bus[msg[1]].postln;
			// synth_group {|group, bus, in_ramp, chnl, amp|
			// "tototototototototototot".postln;
			// msg[2].posln;
			~group_out.add(msg[1]-> AudioOutKubus.synth_group(~group[msg[1]], ~group_bus[msg[1]], 0, msg[2], 0));
			n.sendMsg(\active_group, msg[1])
		}
	}
}, '/mix_groupKubus', nil, 7002).fix;



/*~group[\server1].server
~group_out[\server1].server*/

OSCdef(\Mix_group_piano, {|msg|
	// msg.postln;
	// msg.drop(3).postln;
	case
	{msg[2] == \off} {~group[msg[1]].set(\out_ramp, msg[3], \gatee, 0);
		~group_out[msg[1]].set(\out_ramp, msg[3], \free, 0)
	}
	{msg[2] == \amp} {~group_out[msg[1]].set(\amp, msg[3])}
	{msg[2] == \set} {~group_out[msg[1]].set(*msg.drop(3))}
	{msg[2] == \pause} {fork {
		~group_out[msg[1]].set(\amp, -120); //fade out
		0.01.wait;
		~group[msg[1]].run(false) // pause all group
	}}
	{msg[2] == \resume} {fork {
		~group_out[msg[1]].set(\amp, 0); //fade In
		0.01.wait;
		~group[msg[1]].run(true) // resume group
	}}
	{msg[2].isNumber}
	{
		if (~group.includesKey(msg[1])) {"this group exist".postln} {
			// Synth(\play_buf2, [\buf, ~wave_buff[msg[1]], \out, msg[2], \amp, msg[3]]) // buf chnl amp
			~group.add(msg[1]-> Group.new);
			~group[msg[1]].postln;
			~group_bus.add(msg[1]-> Bus.audio(server, 8));
			// ~group_bus[msg[1]].postln;
			// synth_group {|group, bus, in_ramp, chnl, amp|
			// "tototototototototototot".postln;
			// msg[2].posln;
			~group_out.add(msg[1]-> TPan_8in_piano.synth_group(~group[msg[1]], ~group_bus[msg[1]], 0, msg[2], 0));
			n.sendMsg(\active_group, msg[1]);
			~group[msg[1]].onFree {
				"grupo_off".postln;
				~group[msg[1]].free;
				~group_out[msg[1]].free;
				~group.removeAt(msg[1]);
				~group_bus.removeAt(msg[1]);
				~group_out.removeAt(msg[1]);
			}
		}
	}
}, '/mix_group_piano', nil, 7002).fix;


// ~group[\test].free
// ~group_out[\test].set(\pos0, 1)
/*~audio_group.set(\amp, 0);
~synth[\test_synth3].outsynth.set(\amp, 0);
~synth[\test_synth2].outsynth.set(\amp, 0);
~synth[\test_synth1].outsynth.set(\amp, 0);
~synth[\test_synth4].outsynth.set(\amp, 0);


~group[\test].set(\matrix_ramp, 1, \gate, 0) // pause

~group[\test].run(true);
~group[\test].set(\matrix_ramp, 1, \gate, 1);
~synth[msg[1]].addsynth.values.collect(_.run(true))}
~synth[\test_synth4].addsynth.values.collect(_.run(true))*/


OSCdef(\Mix_group_kubus, {|msg|
	// msg.postln;
	// msg.drop(3).postln;

	case
	{msg[2] == \off} {~group[msg[1]].set(\out_ramp, msg[3], \gatee, 0);
		~group_out[msg[1]].set(\out_ramp, msg[3], \free, 0)
	}
	{msg[2] == \amp} {~group_out[msg[1]].set(\amp, msg[3])}
	{msg[2] == \set} {~group_out[msg[1]].set(*msg.drop(3))}
	{msg[2] == \pause} {fork {
		~group_out[msg[1]].set(\amp, -120); //fade out
		0.01.wait;
		~group[msg[1]].run(false) // pause all group
	}}
	{msg[2] == \resume} {fork {
		~group_out[msg[1]].set(\amp, 0); //fade In
		0.01.wait;
		~group[msg[1]].run(true) // resume group
	}}
	{msg[2].isNumber}
	{
		if (~group.includesKey(msg[1])) {"this group exist".postln} {
			// Synth(\play_buf2, [\buf, ~wave_buff[msg[1]], \out, msg[2], \amp, msg[3]]) // buf chnl amp
			~group.add(msg[1]-> Group.new);
			~group[msg[1]].postln;
			~group_bus.add(msg[1]-> Bus.audio(server, 8));
			// ~group_bus[msg[1]].postln;
			// synth_group {|group, bus, in_ramp, chnl, amp|
			// "tototototototototototot".postln;
			// msg[2].posln;
			~group_out.add(msg[1]-> TPan_1in_piano_kubus.synth_group_kubus(~group[msg[1]], ~group_bus[msg[1]], 0, msg[2], 0, msg[3])); //group, bus, in_ramp, gbus, amp, hp_offset (1, 2, 3)
			n.sendMsg(\active_group, msg[1]);
			~group[msg[1]].onFree {
				"grupo_off".postln;
				~group[msg[1]].free;
				~group_out[msg[1]].free;
				~group.removeAt(msg[1]);
				~group_bus.removeAt(msg[1]);
				~group_out.removeAt(msg[1]);
			}
		}
	}
}, '/mix_group_kubus', nil, 7002).fix;



OSCdef(\Free_groups, {|msg|
	msg.postln;
	if (~group.size != 0) {
		~group[msg[1]].postln;
		if(~group[msg[1]].value.class == \Group) {
			~group[msg[1]].free;} //set(\free, 0)}
		{
			~group[msg[1]].value.class.postln;
			~group[msg[1]].set(\out_ramp, msg[2], \gatee, 0);
		}
	} {"no hay synth!!".postln};

}, '/freegroup', nil, 7002).fix;

//////////////////////////////////////
// ver que cosa se free tiene que ser ~group_out!!
//~group[\test_circ_kubus].free
//////////////////////////////////////

OSCdef(\Actives_Groups, {|msg|
	var groups;
	/*msg.postln;*/
	groups = ~group.order; //keys.asArray;
	if (~group.keys.notNil){
		n.sendMsg(\active_group, *groups)
	}{
		n.sendMsg(\active_group, "no synth")
	}
}, '/actives_group', nil, 7002).fix;






OSCdef(\Clear_groups, {|msg|
	msg.postln;
	if (~group.size != 0) {
		~group.values.collect({|grup|
			grup.set(\out_ramp, 0.1, \gatee, 0);

			// ~group.removeAt()
		});
		{fork {
			0.01.wait;
			// ~group[msg[1]].free; // stop main group

		}};
		/*		~group_out.values.collect({|grupout|
		grupout.set(\out_ramp, 0.1, \free, 0);
		// ~group.removeAt()
		});*/

		~group_bus = Dictionary.new;
		~group_out = Dictionary.new;
		~group = Dictionary.new;
		~aux = Dictionary.new;
		~aux_connections = Dictionary.new;
		~groups_synth = Dictionary.new;
		~scservers_groups.keys.collect({|grp|
			~scservers_groups[grp] = List[];
			grp.postln
		});


	} {"no hay groups!!".postln};
}, '/free_all_group', nil, 7002).fix;



OSCdef(\Playsample, {|msg|

	Synth(\play_buf2, [\buf, ~wave_buff[msg[1]], \out, msg[2], \amp, msg[3]]) // buf chnl amp
}, '/playsample', nil, 7002).fix;


OSCdef(\PlaysampleBuf, {|msg|
	// msg.postln;
	Synth(\play_buf2, [\buf, ~buffers[msg[1].asSymbol], \out, msg[2], \amp, msg[3]]); // buf chnl amp
	// ~buffers[msg[1]].postln;
}, '/playsamplebuf', nil, 7002).fix;



OSCdef(\Playsample_solo, {|msg|
	// msg.postln;
	Synth(\play_buf2, [\buf, ~buffers[msg[1].asSymbol], \out, msg[2], \amp, msg[3], \rate, msg[4]]) // buf chnl amp
}, '/playsample_solo', nil, 7002).fix;


/*OSCdef(\Playsample_solo_G, {|msg| //envia a un track // buf chnl amp rate group
// msg.postln;
Synth(\play_buf2, [\buf, ~buffers[msg[1].asSymbol], \out, ~synth[msg[5].asSymbol].bus.index + msg[2], \amp, msg[3], \rate, msg[4]], ~synth[msg[5].asSymbol].group); // buf chnl amp
// ~synth[msg[5]].group.postln;
}, '/playsample_solo_G', nil, 7002).fix;*/

// con group
OSCdef(\Playsample_solo_G, {|msg| //envia a un track // buf chnl amp rate group
	// msg.postln;
	Synth(\play_buf2, [\buf, ~buffers[msg[1].asSymbol], \out, ~synth[msg[5].asSymbol].bus.index + msg[2], \amp, msg[3], \rate, msg[4]], ~group[msg[5].asSymbol]); // buf chnl amp
	// ~synth[msg[5]].group.postln;
}, '/playsample_solo_G', nil, 7002).fix;


OSCdef(\Playsample_solo_ste_G, {|msg|
	// msg.postln;
	Synth(\play_buf2_ste, [\buf, ~buffers[msg[1].asSymbol], \out, ~synth[msg[5].asSymbol].bus.index + msg[2], \amp, msg[3], \rate, msg[4]],~synth[msg[5].asSymbol].group) // buf chnl amp rate
}, '/playsample_solo_ste_G', nil, 7002).fix;




OSCdef(\Playsample_solo_G_dir_out, {|msg| //envia a un track // buf chnl amp rate group
	// msg.postln;
	Synth(\play_buf2, [\buf, ~buffers[msg[1].asSymbol], \out, msg[2], \amp, msg[3], \rate, msg[4]], ~group[msg[5].asSymbol]); // buf chnl amp
	// ~synth[msg[5]].group.postln;
}, '/playsample_solo_G_dir_out', nil, 7002).fix;


/*OSCdef(\Playsample_8ch_G, {|msg| //envia a un track // buf chnl amp rate group
msg.postln;
Synth(\play_buf8, [\buf, ~buffers[msg[1].asSymbol], \out, ~synth[msg[5].asSymbol].bus.index + msg[2], \amp, msg[3], \rate, msg[4]], ~group[msg[5].asSymbol]); // buf chnl amp
// ~synth[msg[5]].group.postln;
}, '/playsample_8ch_G', nil, 7002).fix;*/


// ~synth[\kick43]
OSCdef(\Playsample_8ch_G, {|msg|
	msg.postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3], \free, 0)} // fade out
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))} //control params, amp, rate...
	{msg[2] == \pause} {~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].run(true);
		~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 1);
		~synth[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2].isSymbolWS}
	{
		~synth.add(msg[1]-> Synth(\play_buf8, [\buf, ~buffers[msg[2].asSymbol], \out, ~synth[msg[6].asSymbol].bus.index + msg[3], \amp, msg[4], \rate, msg[5]],~synth[msg[6].asSymbol].group)) // buf chnl amp rate
	}
}, '/playsample_8ch_G', nil, 7002).fix;


// ~synth[\sample_group1].bus.index

OSCdef(\Playsample_solo_ste, {|msg|
	// msg.postln;
	Synth(\play_buf2_ste, [\buf, ~buffers[msg[1].asSymbol], \out, msg[2], \amp, msg[3], \rate, msg[4]]) // buf chnl amp
}, '/playsample_solo_ste', nil, 7002).fix;




OSCdef(\Playsample_mono_G, {|msg|
	msg.postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3], \free, 0)} // fade out
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))} //control params, amp, rate...
	{msg[2].isSymbolWS}
	{
		~synth.add(msg[1]-> Synth(\play_buf2_mono, [\buf, ~buffers[msg[2].asSymbol], \out, ~synth[msg[6].asSymbol].bus.index + msg[3], \amp, msg[4], \rate, msg[5]],~synth[msg[6].asSymbol].group)) // buf chnl amp rate
	}
}, '/playsample_mono_G', nil, 7002).fix;


// Synth(\play_buf2, [\buf,~buffers["007crotaleLa6.lin.aif".asSymbol], \out, 0, \amp, 6, \rate, 0.5])

OSCdef(\Playsample2, {|msg|
	var dur;
	msg.postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\free, 0)}
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))}
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1]-> Synth(\play_buf2, [\buf, ~buffers[msg[2].asSymbol], \out, msg[3], \amp, msg[4]])); // buf chnl amp
			~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
			dur = ~buffers[msg[2].asSymbol].duration;
			msg[1].postln;
			antes.sendMsg(\sfdur, dur);
			~buffers[msg[1].asSymbol].postln;
			dur.postln;
		}
	};
}, '/playsample2', nil, 7002).fix;



OSCdef(\Synth_solo, {|msg|
	// msg.postln;
	Synth(msg[1].asClass.synthdefname, [\out, msg[2], \matrix_ramp, msg[3], \amp, msg[4]] ++ msg.drop(5)) //chnl fade_in amp
}, '/synth_solo', nil, 7002).fix;



OSCdef(\Synth2, {|msg|
	msg.postln;
	TWguiEnv.new(msg[1], ~envs[msg[2]], msg[3], msg[4], msg[5])
}, '/Synth2', nil, 7002).fix;





OSCdef(\Synth3, {|msg|
	msg.postln;
	case
	{msg[2] == \off} {~synth[msg[1]].group.set(\gatee, 0)}
	{msg[2] == \set} {~synth[msg[1]].addsynth.set(msg[3], msg[4])}
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1]-> TWgui.new(msg[2], msg[3] , msg[4], msg[5], msg[6]));
			~synth[msg[1]].group.onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/Synth3', nil, 7002).fix;



OSCdef(\Synth4, {|msg|
	// msg.postln;

	if (msg[2] == \off) {~synth[msg[1]].set(\out_ramp, msg[3], \free, 0)}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, msg.drop(3)));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
		}
	}

}, '/Synth4', nil, 7002).fix;



OSCdef(\Synth5, {|msg|
	// msg.postln;
	// msg[1].class.postln;
	[msg.drop(3)].postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\free, 0)}
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))}
	{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4))} //param lista (listas)
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, msg.drop(3)));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/Synth5', nil, 7002).fix;

// *[rest++ [\buf, ~bufft[last.asSymbol]]].flat))

// *[msg[5], \buf, ~wave_buff[msg[6]]] ++ msg.drop(7)));




OSCdef(\Synth6, {|msg|

	var paramlist;
	// msg.drop(3).postln;
	paramlist = msg.drop(4);
	paramlist.postln;
	case
	{msg[2] == \off} {~synth[msg[1]].outsynth.set(*[\gatee, 0, \out_ramp, msg[3]])}
	{msg[2] == \set} {~synth[msg[1]].addsynth[msg[3]].set(*paramlist)}
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1]-> TWgui2.new(msg[2], msg[3], msg[4], msg[5]));
			~synth[msg[1]].group.onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/Synth6', nil, 7002).fix;

// a.addsynth[\TestSynth2_2].set(*b);
// outsynth.set(\out_ramp, 3, \gatee, 0)

OSCdef(\Clear_synth, {|msg|
	msg.postln;
	~synth.values.collect( _.set(\free, 0));
	~synth = Dictionary.new;
	~aux.values.collect( _.set(\free, 0));
	~aux = Dictionary.new;
}, '/clearSynth', nil, 7002).fix;

OSCdef(\Gui, {|msg| // crea gui a partir de patch concert Max
	msg.postln;
	{~synth[msg[1].asSymbol].gui(msg[1]);}.defer;

}, '/gui', nil, 7002).fix;


~antes_track = Dictionary.new;

OSCdef(\Gui_antes, {|msg| // crea gui a partir de patch concert Max
	var ttrack, tsynths, outsynth, server;

	msg.postln;
	tsynths = msg.drop(6);
	tsynths.postln;
	server = Server.named.at(msg[1]);
	outsynth = Synth.basicNew(\audioOut8, server, msg[4]);

	{
		ttrack = TTracks8.new(msg[2].asString, nil, msg[3].asInt.asGroup, msg[4].asBus, outsynth);
		~antes_track.add(msg[1]++msg[3] -> ttrack);
		tsynths.pairsDo({|a, b|
			ttrack.addmodfromantes(b, Synth.basicNew(b, server, a));
		});
	}.defer;

}, '/gui_antes', nil, 7002).fix;


OSCdef(\Gui_data, {|msg| // crea gui a partir de patch concert Max
	// msg.postln;
	// {~synth[msg[1].asSymbol].gui(msg[1]);}.defer;
	if(~antes_track[msg[1]++msg[2]].mvc_model_dico[msg[3]].notNil)
	{
		// "no es nil".postln;
		{ ~antes_track[msg[1]++msg[2]].mvc_model_dico[msg[3]].setParam(msg[4], msg[5]) }.defer;
	}

}, '/gui_data', nil, 7002).fix;

// ~antes_track[100].modwin.removeAt(0)

/*OSCdef(\queryTre, {|msg| // crea gui a partir de patch concert Max
if(msg[1]==0) {
msg.postln;
}
}, '/g_queryTree.reply', nil).fix;*/

// ~antes_track[100].mvc_model_dico[104].notNil

OSCdef(\Gui_group_lev, {|msg| // crea gui a partir de patch concert Max
	var textoutputlev;
	msg.postln;
	/*	{~group[msg[1].asSymbol];*/
	// .gui(msg[1]);
	{
		Window(msg[1].asString, Rect(128, 64, 50, 250)).background_(Color(0, 0.1, 0.1, 0.3)).layout_( GridLayout.columns(
			[StaticText().stringColor_(Color.white).string_(msg[1].asString), Slider().background_(Color.blue).knobColor_(Color.blue).thumbSize_(25).value_(dbspec.unmap(0)).action_({|sld|
				/*		~fader1.values.collect({|val|
				val[1].set(\amp, dbspec.map(sld.value))});*/
				~group_out[msg[1]].set(\amp, dbspec.map(sld.value));
				textoutputlev.string_(dbspec.map(sld.value).round(0.1));
			}), textoutputlev = StaticText().stringColor_(Color.white).maxHeight_(20).fixedWidth_(36).string_("0.0").align_(\right)],
		)).front;



	}.defer;

}, '/gui_group_lev', nil, 7002).fix;


// ~group_out[\group1].set()
/*OSCdef(\Arrtest, {|msg| // crea gui a partir de patch concert Max
msg.postln;
// {~synth[msg[1].asSymbol].gui(msg[1]);}.defer;

}, '/test', nil, 12000).fix;*/

// ~synth[\freeze_tbr1].gui

OSCdef(\Clear_tracks, {|msg|
	msg.postln;
	if (~synth.size != 0) {
		~synth.values.collect({|synth|
			if(synth.class.asSymbol == \Synth) {
				synth.set(\free, 0, \matrix_ramp, 1);
			} {
				synth.group.set(\gatee, 0);
			}
		});
		~synth = Dictionary.new;
		~fader_reg = Dictionary.new;

		~fader1 = Dictionary.new;
		~fader2 = Dictionary.new;
		~fader3 = Dictionary.new;
		~fader4 = Dictionary.new;
		~fader5 = Dictionary.new;
		~fader6 = Dictionary.new;
		~fader7 = Dictionary.new;
		~fader8 = Dictionary.new;
		~fader9 = Dictionary.new;
		~fader10 = Dictionary.new;
		~fader11 = Dictionary.new;
		// ~fader12 = Dictionary.new;

	} {"no hay synth!!".postln};
	if (~aux.size != 0) {
		~aux.values.collect({|synth|
			if(synth.class.asSymbol == \Synth) {
				synth.set(\free, 0);
			} {
				synth.group.set(\gatee, 0);
			}
		});
		~aux = Dictionary.new;
	} {"no hay aux!!".postln};
	if (~aux_connections.size != 0) {
		~aux_connections.values.collect({|synth|
			if(synth.class.asSymbol == \Synth) {
				// synth.set(\free, 0);
				synth.free;
			} {
				synth.group.set(\gatee, 0);
			}
		});
		~aux_connections = Dictionary.new;
	} {"no hay aux_connect!!".postln};

}, '/cleartracks', nil, 7002).fix;

/*~synth.size == []*/
OSCdef(\Clear_1track, {|msg|
	msg.postln;
	if (~synth.size != 0) {
		~synth[msg[1]].postln;
		if(~synth[msg[1]].value.class == \Synth) {
			~synth[msg[1]].free;} //set(\free, 0)}
		{
			~synth[msg[1]].value.class.postln;
			~synth[msg[1]].group.set(\out_ramp, msg[2], \gatee, 0);
		}
	} {"no hay synth!!".postln};

}, '/freetrack', nil, 7002).fix;

// ~synth[\addsynth_mes139].free




OSCdef(\TrackEnv, {|msg|
	// msg.postln;
	TrackConstEnv(msg[1], msg[2], msg[3], msg[4], *msg.drop(5)); //ch, env, dur, amp, args [Module args... #-> Module args... #-> Module args, etc...]
}, '/crea_trackEnv', nil, 7002).fix;

OSCdef(\TrackEnv2, {|msg| // a traves un group
	// msg.postln;
	TrackConstEnv2(~group_bus[msg[5]], msg[1], msg[2], msg[3], msg[4], ~group[msg[5].asSymbol], *msg.drop(6)); //ch, env, dur, amp, group, args [Module args... #-> Module args... #-> Module args, etc...]
}, '/crea_trackEnv2', nil, 7002).fix;

/*TrackConstEnv2(~group_bus[\Synth2], 5, "inv".asSymbol, 1, 6, "AudioInput".asSymbol, "input".asSymbol, 11, msg[4], *msg.drop(6));*/

// ~group_bus[\Synth2]

OSCdef(\Track, {|msg|
	/*var modlist;
	modlist = msg.drop(1);*/
	// msg.postln;
	// msg.drop(4).postln;*/
	case
	{msg[2] == \off} {~synth[msg[1]].group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].group.run(true);
		~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~synth[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} {~synth[msg[1]].addsynth[msg[3]].set(*msg.drop(4))}
	{msg[2] == \buf} {~synth[msg[1]].addsynth[msg[3]].set(\buf, ~buffers[msg[3]])}
	{msg[2] == \add} {~synth[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \insert} {~synth[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~synth[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~synth[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \track} {~synth[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1]-> TrackConst(msg[2], msg[3], msg[4], *msg.drop(5))); //ch, fadeIn, amp, args
			~synth[msg[1]].group.onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/crea_track', nil, 7002).fix;

// ~synth[\track1].addsynth.values ["02_TMFxShift2".asSymbol]

// synthe[mod_index].moveAfter(synthe[index3]); // synth routing

////envia synth_order
// ~buffers["grattage_unghie023.aif".asSymbol]
// ~buffers.collect(_.postln)
/*~synth[\Mesure229].addsynth.set(\pos, 1.25)
~synth[\synth].outsynth.set(\amp, -120)

[ 0, 0.25, 0.5, 0.75, 1, 1.25, 1.5, 1.75 ]*/

// ~synth[\addsynth_mes153].addsynth["00_TAddic_20_8".asSymbol].setn(\freqs ,[200, 300, 400, 500, 600, 700, 1122.0307617188, 1290.1633300781, 1568.0185546875, 1806.7377929688, 1989.4948730469, 2072.4255371094, 2602.7551269531, 2782.2658691406, 2881.4304199219, 3012.1325683594, 3275.5361328125, 3436.4228515625, 3607.79296875, 3693.3349609375])

// ~synth[\test_Ingran6].addsynth["01_TGranInterpExt".asSymbol].set(\t_trig, 1)
OSCdef(\Track8, {|msg|
	var fad, fad_pos, test, lista, group_arg, group_pos, server_arg, server_pos, preset, preset0, preset1, temparams, tempmultiparams, paramsmerge, presetlist, lista0, lista1, mixvalues, paramList, multiList, interpol_len, interpolresult, json_path, json_file, path_def, path, fxName;
	//msg.postln;
	/*	msg.postln;
	msg.drop(4).postln;*/
	case
	{msg[2] == \off} {~synth[msg[1]].outsynth.set(\out_ramp, msg[3], \gatee, 0)}// ??group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].group.run(true);
		~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~synth[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} { ~synth[msg[1]].addsynth[msg[3]].set(*msg.drop(4));
		/*if(~synth[msg[1]].track.notNil) { // si se ha abierto la ventana del modulo desde el exterior entonces envia valores a GUI
		{~synth[msg[1]].track.mvc_model[0].setParam(*msg.drop(4))}.defer; //visulalizacion EXPERIMENTAL, no optimizado!!!!!
		}*/
	}
	{msg[2] == \setn} {lista = msg.drop(4); ~synth[msg[1]].addsynth[msg[3]].set(lista[0], lista.drop(1))}
	{msg[2] == \setpreset} { // seria mas elegante de hacerlo dentro de TrackConst8 o no??
		// msg[5].postln;
		// preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++(msg[3].asString.drop(3)));
		// preset.postln;
		// msg[4].postln;
		// path_def.postln;
		fxName = msg[3].asString.drop(3).asSymbol;
		path = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/").standardizePath();
		path_def = path++fxName++".json";

		json_path = File(path_def.standardizePath,"r");
		json_file = json_path.readAllString;
		json_path.close;

		preset = json_file.jsonToDict; //convert json to dictionary

		preset = preset[fxName][msg[4]]; //reucpera preset_name contenido

		// preset.postln;

		/*		preset = preset[msg[4]];
		msg[3].asString.drop(3).postln;
		("/TSupport/fxPresets/"++msg[3].asString.drop(3)).postln;*/
		if(preset.notNil){
			/*			temparams = (msg[3].asString.drop(3)).asSymbol.asClass.paramslider.collect({|x| x.key});
			tempmultiparams = (msg[3].asString.drop(3)).asSymbol.asClass.parammultislider.collect({|x| x.key});
			paramsmerge = temparams++tempmultiparams;
			presetlist = [paramsmerge, preset].flop.flatten(1); // merge de nombres y valores de los presets*/
			presetlist = preset.asKeyValuePairs;
			~synth[msg[1]].addsynth[msg[3]].set(*presetlist);


		}
		{("este preset track8 no existe "++msg[4]).postln}
	}
	{msg[2] == \setpresetinterpol} {
		preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++(msg[3].asString.drop(3)));
		// preset.postln;
		preset0 = preset[msg[4]];
		preset1 = preset[msg[5]];
		/*		preset0.postln;
		preset1.postln;*/
		temparams = (msg[3].asString.drop(3)).asSymbol.asClass.paramslider.collect({|x| x.key});
		tempmultiparams = (msg[3].asString.drop(3)).asSymbol.asClass.parammultislider.collect({|x| x.key});
		paramsmerge = temparams++tempmultiparams;
		interpolresult = ((preset1*msg[6])+(preset0*(1-msg[6])));
		// interpolresult.postln;
		presetlist = [paramsmerge, interpolresult].flop.flatten(1);
		~synth[msg[1]].addsynth[msg[3]].set(*presetlist);
	}
	/*	{msg[2] == \interpolval} {
	msg[4].postln;
	preset0.postln;
	preset1.postln;
	interpolresult = ((preset1*msg[4])+(preset0*(1-msg[4])));
	interpolresult.postln;
	presetlist = [paramsmerge, interpolresult].flop.flatten(1);
	~synth[msg[1]].addsynth[msg[3]].set(*presetlist);
	}*/
	{msg[2] == \buf} {~synth[msg[1]].addsynth[msg[3]].set(\buf, ~buffers[msg[4]])}
	{msg[2] == \add} {~synth[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \addmulti} {~synth[msg[1]].addmulti(msg.drop(3))}
	{msg[2] == \insert} {~synth[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~synth[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~synth[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \pos} {~synth[msg[1]].outsynth.set(\pos, msg[3])}
	{msg[2] == \track} {~synth[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~synth.includesKey(msg[1])) {("exist "++msg[1]).postln}
		{
			// test fader and mixing_group
			if (msg.indicesOfEqual('#fader').notNil) {
				// "fader_presente".postln;
				fad_pos = msg.indicesOfEqual('#fader');
				// fad_pos.class.postln;
				// fad = msg.drop(*fad_pos);
				fad = [msg.removeAt(*fad_pos), msg.removeAt(*fad_pos)];
				// fad_pos.postln;
				// msg.postln;
				// fad = msg.removeAt(*fad_pos);
				// msg.removeAt(1).postln;
				/*fad.postln;
				msg.postln;*/
				// msg = msg.keep(*fad_pos);
				/*		msg = msg.takeAt(fad_pos);
				msg.postln;*/
			};

			if (msg.indicesOfEqual('#group').notNil) {
				// "gr_presente".postln;
				group_pos = msg.indicesOfEqual('#group');
				// fad_pos.class.postln;
				group_arg = [msg.removeAt(*group_pos), msg.removeAt(*group_pos)];
				// fad.postln;
				// msg = msg.keep(*group_pos);
				/*group_arg.postln;
				~group[group_arg[1]].postln;*/
				currentEnvironment.put((group_arg[1]++"group").asSymbol, []); //crea array
				// msg.postln;
				~synth.add(msg[1]-> TrackConst82(~group_bus[group_arg[1]], msg[2], msg[3], msg[4], ~group[group_arg[1]], *msg.drop(5))); //bus, ch, fadeIn, amp, group, args
				~groups_synth[group_arg[1]].add(msg[1]);

				~group[group_arg[1]].onFree {
					"grupo_off".postln;
					~group_out[group_arg[1]].free;
					~group[group_arg[1]].free;
					~group.removeAt(group_arg[1]);
					~group_out.removeAt(group_arg[1]);
					~group_bus.removeAt(group_arg[1]);
					~groups_synth.removeAt(group_arg[1]);
				}
			}{
				if (msg.indicesOfEqual('#server').notNil) {
					//poner TrackConst8 con server
					server_pos = msg.indicesOfEqual('#server');
					server_arg = [msg.removeAt(*server_pos), msg.removeAt(*server_pos)];
					server_arg.postln;
					~synth.add(msg[1]-> TrackConst83(msg[2], msg[3], msg[4], ~scservers[server_arg[1]], *msg.drop(5))); //ch, fadeIn, amp, server, args
				}{
					~synth.add(msg[1]-> TrackConst8(msg[2], msg[3], msg[4], *msg.drop(5))); //ch, fadeIn, amp, args
				}
			};



			~synth[msg[1]].group.onFree {
				if(~fader_reg[msg[1]].notNil)
				{
					// var to_levels;

					currentEnvironment.at(~fader_reset[~fader_reg[msg[1]]]).fill(0); // reset array de valores vumeters en ~f1, ~f2, etc... encontrar mejor solucion!!!!

					// msg[1].postln;
					// to_levels = ~fadelev[~fader_reg[msg[1]]]; //registra variable local antes de kill
					/*~fadelev[msg[1]].postln;
					~fader_reg[msg[1]].postln;*/
					~synth.removeAt(msg[1]);

					currentEnvironment.at(~fader_reg[msg[1]]).removeAt(msg[1]); // remove synth from fader_num
					currentEnvironment.at(fad[1]).removeAt(msg[1]); // remove assign sinth a fader

					~fader_reg.removeAt(msg[1]); // remove synth from fader_reg
					/*					"toto".postln;
					fad[1].postln;
					~fader_reset[~fader_reg[msg[1]]].postln;
					"toto".postln;*/

					/*					{0.1.wait;
					{
					/*							currentEnvironment.at(to_levels).peakLevel = 0;
					currentEnvironment.at(to_levels).value = 0;*/


					("off_synth_level_indicator "++ to_levels).postln;
					}.defer}.fork;*/
					~groups_synth[group_arg[1]].removeAt(~groups_synth[group_arg[1]].indexOf(msg[1]));

				}{
					// ~fader_reg[msg[1]].postln;
					~synth.removeAt(msg[1]);
					~groups_synth[group_arg[1]].removeAt(~groups_synth[group_arg[1]].indexOf(msg[1]));

				}

			};
			// msg[1].postln;
			/// crea fader
			if (fad.notNil){
				currentEnvironment.at(fad[1]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
				~fader_reg.add(msg[1]-> fad[1]);
				// ("fade assign "++ fad[1]).postln;
			};
			/*			if (group_arg.notNil){
			// ~group_synth.addpair()
			currentEnvironment.at((group_arg[1]++"group").asSymbol).add(~synth[msg[1]]);
			"nombre_environment".postln;
			(group_arg[1]++"group").asSymbol.postln;
			(group_arg[1]++"group").asSymbol.class.postln;
			msg[1].postln;
			currentEnvironment.at((group_arg[1]++"group").asSymbol).postln;
			"nombre_environment".postln;
			// ~fader_reg.add(msg[1]-> fad[1]);
			// ("fade assign "++ fad[1]).postln;
			}*/

			/*			fad[1].postln;
			msg[1].postln;*/
		}
	};
	/*	if (fad.notNil){
	currentEnvironment.at(msg[2]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
	~fader_reg.add(msg[1]-> msg[2]); // registran en que fader se encuentra la synth
	}*/


}, '/crea_track8', nil, 7002).fix;


OSCdef(\Track16, {|msg|
	var fad, fad_pos, test, lista, group_arg, group_pos, server_arg, server_pos, preset, preset0, preset1, temparams, tempmultiparams, paramsmerge, presetlist, lista0, lista1, mixvalues, paramList, multiList, interpol_len, interpolresult, json_path, json_file, path_def, path, fxName;
	/*	msg.postln;
	msg.flat.postln;
	msg[5].class.postln;*/
	/*	msg.postln;
	msg.drop(4).postln;*/
	case
	{msg[2] == \off} {~synth[msg[1]].outsynth.set(\out_ramp, msg[3], \gatee, 0)}// ??group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].group.run(true);
		~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~synth[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} { ~synth[msg[1]].addsynth[msg[3]].set(*msg.drop(4))}
	{msg[2] == \setn} {lista = msg.drop(4); ~synth[msg[1]].addsynth[msg[3]].set(lista[0], lista.drop(1))}
	{msg[2] == \setpreset} { // seria mas elegante de hacerlo dentro de TrackConst8 o no??

		fxName = msg[3].asString.drop(3).asSymbol;
		path = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/").standardizePath();
		path_def = path++fxName++".json";

		json_path = File(path_def.standardizePath,"r");
		json_file = json_path.readAllString;
		json_path.close;

		preset = json_file.jsonToDict; //convert json to dictionary

		preset = preset[fxName][msg[4]]; //reucpera preset_name contenido

		if(preset.notNil){

			presetlist = preset.asKeyValuePairs;
			~synth[msg[1]].addsynth[msg[3]].set(*presetlist);

		} {("este preset no existe "++msg[4]).postln}
	}
	{msg[2] == \setpresetinterpol} {
		preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++(msg[3].asString.drop(3)));
		// preset.postln;
		preset0 = preset[msg[4]];
		preset1 = preset[msg[5]];
		/*		preset0.postln;
		preset1.postln;*/
		temparams = (msg[3].asString.drop(3)).asSymbol.asClass.paramslider.collect({|x| x.key});
		tempmultiparams = (msg[3].asString.drop(3)).asSymbol.asClass.parammultislider.collect({|x| x.key});
		paramsmerge = temparams++tempmultiparams;
		interpolresult = ((preset1*msg[6])+(preset0*(1-msg[6])));
		// interpolresult.postln;
		presetlist = [paramsmerge, interpolresult].flop.flatten(1);
		~synth[msg[1]].addsynth[msg[3]].set(*presetlist);
	}
	/*	{msg[2] == \interpolval} {
	msg[4].postln;
	preset0.postln;
	preset1.postln;
	interpolresult = ((preset1*msg[4])+(preset0*(1-msg[4])));
	interpolresult.postln;
	presetlist = [paramsmerge, interpolresult].flop.flatten(1);
	~synth[msg[1]].addsynth[msg[3]].set(*presetlist);
	}*/
	{msg[2] == \buf} {~synth[msg[1]].addsynth[msg[3]].set(\buf, ~buffers[msg[4]])}
	{msg[2] == \add} {~synth[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \addmulti} {~synth[msg[1]].addmulti(msg.drop(3))}
	{msg[2] == \insert} {~synth[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~synth[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~synth[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \pos} {~synth[msg[1]].outsynth.set(\pos, msg[3])}
	{msg[2] == \track} {~synth[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~synth.includesKey(msg[1])) {("exist "++msg[1]).postln}
		{
			// test fader and mixing_group
			if (msg.indicesOfEqual('#fader').notNil) {
				// "fader_presente".postln;
				fad_pos = msg.indicesOfEqual('#fader');
				// fad_pos.class.postln;
				// fad = msg.drop(*fad_pos);
				fad = [msg.removeAt(*fad_pos), msg.removeAt(*fad_pos)];
				// fad_pos.postln;
				// msg.postln;
				// fad = msg.removeAt(*fad_pos);
				// msg.removeAt(1).postln;
				/*fad.postln;
				msg.postln;*/
				// msg = msg.keep(*fad_pos);
				/*		msg = msg.takeAt(fad_pos);
				msg.postln;*/
			};

			if (msg.indicesOfEqual('#group').notNil) {
				// "gr_presente".postln;
				group_pos = msg.indicesOfEqual('#group');
				// fad_pos.class.postln;
				group_arg = [msg.removeAt(*group_pos), msg.removeAt(*group_pos)];
				// fad.postln;
				// msg = msg.keep(*group_pos);
				/*group_arg.postln;
				~group[group_arg[1]].postln;*/
				currentEnvironment.put((group_arg[1]++"group").asSymbol, []); //crea array
				// msg.postln;
				~synth.add(msg[1]-> TrackConst16_2(~group_bus[group_arg[1]], msg[2], msg[3], msg[4], ~group[group_arg[1]], *msg.drop(5))); //bus, ch, fadeIn, amp, group, args
				~groups_synth[group_arg[1]].add(msg[1]);

				~group[group_arg[1]].onFree {
					"grupo_off".postln;
					~group_out[group_arg[1]].free;
					~group[group_arg[1]].free;
					~group.removeAt(group_arg[1]);
					~group_out.removeAt(group_arg[1]);
					~group_bus.removeAt(group_arg[1]);
					~groups_synth.removeAt(group_arg[1]);



				}
			}{
				if (msg.indicesOfEqual('#server').notNil) {
					//poner TrackConst8 con server
					server_pos = msg.indicesOfEqual('#server');
					server_arg = [msg.removeAt(*server_pos), msg.removeAt(*server_pos)];
					server_arg.postln;
					~synth.add(msg[1]-> TrackConst16_3(msg[2], msg[3], msg[4], ~scservers[server_arg[1]], *msg.drop(5))); //ch, fadeIn, amp, server, args
				}{
					~synth.add(msg[1]-> TrackConst16(msg[2], msg[3], msg[4], *msg.drop(5))); //ch, fadeIn, amp, args
				}
			};



			~synth[msg[1]].group.onFree {
				if(~fader_reg[msg[1]].notNil)
				{
					// var to_levels;

					currentEnvironment.at(~fader_reset[~fader_reg[msg[1]]]).fill(0); // reset array de valores vumeters en ~f1, ~f2, etc... encontrar mejor solucion!!!!

					// msg[1].postln;
					// to_levels = ~fadelev[~fader_reg[msg[1]]]; //registra variable local antes de kill
					/*~fadelev[msg[1]].postln;
					~fader_reg[msg[1]].postln;*/
					~synth.removeAt(msg[1]);

					currentEnvironment.at(~fader_reg[msg[1]]).removeAt(msg[1]); // remove synth from fader_num
					currentEnvironment.at(fad[1]).removeAt(msg[1]); // remove assign sinth a fader

					~fader_reg.removeAt(msg[1]); // remove synth from fader_reg
					~groups_synth[group_arg[1]].removeAt(~groups_synth[group_arg[1]].indexOf(msg[1]));
					/*					"toto".postln;
					fad[1].postln;
					~fader_reset[~fader_reg[msg[1]]].postln;
					"toto".postln;*/

					/*					{0.1.wait;
					{
					/*							currentEnvironment.at(to_levels).peakLevel = 0;
					currentEnvironment.at(to_levels).value = 0;*/


					("off_synth_level_indicator "++ to_levels).postln;
					}.defer}.fork;*/
				}{
					// ~fader_reg[msg[1]].postln;
					~synth.removeAt(msg[1]);
					~groups_synth[group_arg[1]].removeAt(~groups_synth[group_arg[1]].indexOf(msg[1]));
				}

			};
			// msg[1].postln;
			/// crea fader
			if (fad.notNil){
				currentEnvironment.at(fad[1]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
				~fader_reg.add(msg[1]-> fad[1]);
				// ("fade assign "++ fad[1]).postln;
			};
			/*			if (group_arg.notNil){
			// ~group_synth.addpair()
			currentEnvironment.at((group_arg[1]++"group").asSymbol).add(~synth[msg[1]]);
			"nombre_environment".postln;
			(group_arg[1]++"group").asSymbol.postln;
			(group_arg[1]++"group").asSymbol.class.postln;
			msg[1].postln;
			currentEnvironment.at((group_arg[1]++"group").asSymbol).postln;
			"nombre_environment".postln;
			// ~fader_reg.add(msg[1]-> fad[1]);
			// ("fade assign "++ fad[1]).postln;
			}*/

			/*			fad[1].postln;
			msg[1].postln;*/
		}
	};
	/*	if (fad.notNil){
	currentEnvironment.at(msg[2]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
	~fader_reg.add(msg[1]-> msg[2]); // registran en que fader se encuentra la synth
	}*/


}, '/crea_track16', nil, 7002).fix;

OSCdef(\Trackkubus, {|msg|
	var fad, fad_pos, test, lista, group_arg, group_pos, server_arg, server_pos, preset, preset0, preset1, temparams, tempmultiparams, paramsmerge, presetlist, lista0, lista1, mixvalues, paramList, multiList, interpol_len, interpolresult, json_path, json_file, path_def, path, fxName;
	/*	msg.postln;
	msg.flat.postln;
	msg[5].class.postln;*/
	/*	msg.postln;
	msg.drop(4).postln;*/
	case
	{msg[2] == \off} {~synth[msg[1]].outsynth.set(\out_ramp, msg[3], \gatee, 0)}// ??group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].group.run(true);
		~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~synth[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} { ~synth[msg[1]].addsynth[msg[3]].set(*msg.drop(4))}
	{msg[2] == \setn} {lista = msg.drop(4); ~synth[msg[1]].addsynth[msg[3]].set(lista[0], lista.drop(1))}
	{msg[2] == \setpreset} { // seria mas elegante de hacerlo dentro de TrackConst8 o no??

		fxName = msg[3].asString.drop(3).asSymbol;
		path = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/").standardizePath();
		path_def = path++fxName++".json";

		json_path = File(path_def.standardizePath,"r");
		json_file = json_path.readAllString;
		json_path.close;

		preset = json_file.jsonToDict; //convert json to dictionary

		preset = preset[fxName][msg[4]]; //reucpera preset_name contenido

		if(preset.notNil){

			presetlist = preset.asKeyValuePairs;
			~synth[msg[1]].addsynth[msg[3]].set(*presetlist);

		} {("este preset no existe "++msg[4]).postln}
	}
	{msg[2] == \setpresetinterpol} {
		preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++(msg[3].asString.drop(3)));
		// preset.postln;
		preset0 = preset[msg[4]];
		preset1 = preset[msg[5]];
		/*		preset0.postln;
		preset1.postln;*/
		temparams = (msg[3].asString.drop(3)).asSymbol.asClass.paramslider.collect({|x| x.key});
		tempmultiparams = (msg[3].asString.drop(3)).asSymbol.asClass.parammultislider.collect({|x| x.key});
		paramsmerge = temparams++tempmultiparams;
		interpolresult = ((preset1*msg[6])+(preset0*(1-msg[6])));
		// interpolresult.postln;
		presetlist = [paramsmerge, interpolresult].flop.flatten(1);
		~synth[msg[1]].addsynth[msg[3]].set(*presetlist);
	}
	/*	{msg[2] == \interpolval} {
	msg[4].postln;
	preset0.postln;
	preset1.postln;
	interpolresult = ((preset1*msg[4])+(preset0*(1-msg[4])));
	interpolresult.postln;
	presetlist = [paramsmerge, interpolresult].flop.flatten(1);
	~synth[msg[1]].addsynth[msg[3]].set(*presetlist);
	}*/
	{msg[2] == \buf} {~synth[msg[1]].addsynth[msg[3]].set(\buf, ~buffers[msg[4]])}
	{msg[2] == \add} {~synth[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \addmulti} {~synth[msg[1]].addmulti(msg.drop(3))}
	{msg[2] == \insert} {~synth[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~synth[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~synth[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \pos} {~synth[msg[1]].outsynth.set(\pos, msg[3])}
	{msg[2] == \track} {~synth[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~synth.includesKey(msg[1])) {("exist "++msg[1]).postln}
		{
			// test fader and mixing_group
			if (msg.indicesOfEqual('#fader').notNil) {
				// "fader_presente".postln;
				fad_pos = msg.indicesOfEqual('#fader');
				// fad_pos.class.postln;
				// fad = msg.drop(*fad_pos);
				fad = [msg.removeAt(*fad_pos), msg.removeAt(*fad_pos)];
				// fad_pos.postln;
				// msg.postln;
				// fad = msg.removeAt(*fad_pos);
				// msg.removeAt(1).postln;
				/*fad.postln;
				msg.postln;*/
				// msg = msg.keep(*fad_pos);
				/*		msg = msg.takeAt(fad_pos);
				msg.postln;*/
			};

			if (msg.indicesOfEqual('#group').notNil) {
				// "gr_presente".postln;
				group_pos = msg.indicesOfEqual('#group');
				// fad_pos.class.postln;
				group_arg = [msg.removeAt(*group_pos), msg.removeAt(*group_pos)];
				// fad.postln;
				// msg = msg.keep(*group_pos);
				/*group_arg.postln;
				~group[group_arg[1]].postln;*/
				currentEnvironment.put((group_arg[1]++"group").asSymbol, []); //crea array
				// msg.postln;
				~synth.add(msg[1]-> TrackConstKubus(~group_bus[group_arg[1]], msg[2], msg[3], msg[4], ~group[group_arg[1]], *msg.drop(5))); //bus, ch, fadeIn, amp, group, args

				~groups_synth[group_arg[1]].add(msg[1]);

				~group[group_arg[1]].onFree {
					"grupo_off".postln;
					~group_out[group_arg[1]].free;
					~group[group_arg[1]].free;
					~group.removeAt(group_arg[1]);
					~group_out.removeAt(group_arg[1]);
					~group_bus.removeAt(group_arg[1]);
					~groups_synth.removeAt(group_arg[1]);



				}
			}{
				if (msg.indicesOfEqual('#server').notNil) {
					//poner TrackConst8 con server
					server_pos = msg.indicesOfEqual('#server');
					server_arg = [msg.removeAt(*server_pos), msg.removeAt(*server_pos)];
					server_arg.postln;
					~synth.add(msg[1]-> TrackConst16_3(msg[2], msg[3], msg[4], ~scservers[server_arg[1]], *msg.drop(5))); //ch, fadeIn, amp, server, args
				}{
					~synth.add(msg[1]-> TrackConst16(msg[2], msg[3], msg[4], *msg.drop(5))); //ch, fadeIn, amp, args
				}
			};



			~synth[msg[1]].group.onFree {
				if(~fader_reg[msg[1]].notNil)
				{
					// var to_levels;

					currentEnvironment.at(~fader_reset[~fader_reg[msg[1]]]).fill(0); // reset array de valores vumeters en ~f1, ~f2, etc... encontrar mejor solucion!!!!

					// msg[1].postln;
					// to_levels = ~fadelev[~fader_reg[msg[1]]]; //registra variable local antes de kill
					/*~fadelev[msg[1]].postln;
					~fader_reg[msg[1]].postln;*/
					~synth.removeAt(msg[1]);

					currentEnvironment.at(~fader_reg[msg[1]]).removeAt(msg[1]); // remove synth from fader_num
					currentEnvironment.at(fad[1]).removeAt(msg[1]); // remove assign sinth a fader

					~fader_reg.removeAt(msg[1]); // remove synth from fader_reg
					~groups_synth[group_arg[1]].removeAt(~groups_synth[group_arg[1]].indexOf(msg[1]));
					/*					"toto".postln;
					fad[1].postln;
					~fader_reset[~fader_reg[msg[1]]].postln;
					"toto".postln;*/

					/*					{0.1.wait;
					{
					/*							currentEnvironment.at(to_levels).peakLevel = 0;
					currentEnvironment.at(to_levels).value = 0;*/


					("off_synth_level_indicator "++ to_levels).postln;
					}.defer}.fork;*/
				}{
					// ~fader_reg[msg[1]].postln;
					~synth.removeAt(msg[1]);
					~groups_synth[group_arg[1]].removeAt(~groups_synth[group_arg[1]].indexOf(msg[1]));
				}

			};
			// msg[1].postln;
			/// crea fader
			if (fad.notNil){
				currentEnvironment.at(fad[1]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
				~fader_reg.add(msg[1]-> fad[1]);
				// ("fade assign "++ fad[1]).postln;
			};
			/*			if (group_arg.notNil){
			// ~group_synth.addpair()
			currentEnvironment.at((group_arg[1]++"group").asSymbol).add(~synth[msg[1]]);
			"nombre_environment".postln;
			(group_arg[1]++"group").asSymbol.postln;
			(group_arg[1]++"group").asSymbol.class.postln;
			msg[1].postln;
			currentEnvironment.at((group_arg[1]++"group").asSymbol).postln;
			"nombre_environment".postln;
			// ~fader_reg.add(msg[1]-> fad[1]);
			// ("fade assign "++ fad[1]).postln;
			}*/

			/*			fad[1].postln;
			msg[1].postln;*/
		}
	};
	/*	if (fad.notNil){
	currentEnvironment.at(msg[2]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
	~fader_reg.add(msg[1]-> msg[2]); // registran en que fader se encuentra la synth
	}*/


}, '/crea_track_kubus', nil, 7002).fix;



OSCdef(\Multisynth, {|msg|
	var fad, fad_pos, test, lista, group_arg, group_pos, server_arg, server_pos, preset, preset0, preset1, temparams, tempmultiparams, paramsmerge, presetlist, lista0, lista1, mixvalues, paramList, multiList, interpol_len, interpolresult, json_path, json_file, path_def, path, fxName, temp_msg;
	/*	msg.postln;
	msg.flat.postln;
	msg[5].class.postln;*/
	/*	msg.postln;
	msg.drop(4).postln;*/
	case
	{msg[2] == \off} {
		~multisynth[msg[1]].collect({|synth| synth.outsynth.set(\out_ramp, msg[3], \gatee, 0)})
	}// ??group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~multisynth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~multisynth[msg[1]].group.run(true);
		~multisynth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~multisynth[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} {
		msg.postln;
		msg.drop(4).postln;
		msg.drop(5).postln;
		temp_msg = msg.drop(5).unfoldOSCArrays.flat; // unfoldOSCArrays part of OpenObject Quarks to create Array from OSC
		// temp_msg.unfoldOSCArrays.flat.postln;
		temp_msg.postln;
		// [*temp_msg].postln;
		// msg[5][2].postln;
		~multisynth[msg[1]].collect({|synth, i| synth.addsynth[msg[3]].set(msg[4], temp_msg[i])})
		// ~multisynth[msg[1]].addsynth[msg[3]].set(*msg.drop(4))
	}
	{msg[2] == \simuloff} {~simul[msg[3]].free}
	{msg[2] == \map} {

		if(msg[3] == \amp)
		{
			// ~dico_buses.add(msg[1]+msg[3] -> List[]);
			~bus_multisynth.add(msg[1]++msg[3] -> Bus.audio(~multisynth[msg[1]][0].group.server, ~multisynth[msg[1]].size));
			~simul.add(msg[4] -> Synth(\multi_lfo, [\bus, ~bus_multisynth[msg[1]++msg[3]], \start, msg[5], \end,  msg[6]], ~multisynth[msg[1]][0].group.server)); // Synth de test aqui debe instanciarse el programa FAUST receive_audio_data

			~multisynth[msg[1]].collect({|synth, i|
				synth.outsynth.map(\amp, ~bus_multisynth[msg[1]++msg[3]].subBus(i, 1));
			}) // mapea los buses a los argumentos "amp" de las outsynth

			/*~dico_buses[msg[1]+msg[3]].add(synth.outsynth.map(\amp, ~bus_multisynth[msg[1]++msg[3]].subBus(i, 1)))})*/

			/*			~multisynth[msg[1]].size.do({|i|
			~dico_buses.put(i, ~dico_synth[i].map(\amp, ~bus.subBus(i, 1)));
			});*/
		}{

			~bus_multisynth.add(msg[1]++msg[3]++msg[4] -> Bus.audio(~multisynth[msg[1]][0].group.server, ~multisynth[msg[1]].size));
			~simul.add(msg[5] -> Synth(\multi_lfo, [\bus, ~bus_multisynth[msg[1]++msg[3]++msg[4]], \start, msg[6], \end,  msg[7]], ~multisynth[msg[1]][0].group.server));

			/*			msg[1].postln;
			msg[4].postln;
			msg[3].postln;
			~bus_multisynth[msg[1]++msg[3]++msg[3]].postln;*/
			// Synth de test aqui debe instanciarse el programa FAUST receive_audio_data
			~multisynth[msg[1]].collect({|synth, i| synth.addsynth[msg[3].asSymbol].map(msg[4], ~bus_multisynth[msg[1]++msg[3]++msg[4]].subBus(i, 1));
			});
			/*			~multisynth[msg[1]].size.do({|synth, i|
			synth.addsynth[msg[3]].map(msg[4], ~bus_multisynth[msg[1]++msg[3]++msg[3]].subBus(i, 1));
			msg[4].postln;
			});*/
		}
	}
	{msg[2] == \setn} {lista = msg.drop(4); ~multisynth[msg[1]].addsynth[msg[3]].set(lista[0], lista.drop(1))}
	{msg[2] == \setpreset} { // seria mas elegante de hacerlo dentro de TrackConst8 o no??

		fxName = msg[3].asString.drop(3).asSymbol;
		path = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/").standardizePath();
		path_def = path++fxName++".json";

		json_path = File(path_def.standardizePath,"r");
		json_file = json_path.readAllString;
		json_path.close;

		preset = json_file.jsonToDict; //convert json to dictionary

		preset = preset[fxName][msg[4]]; //reucpera preset_name contenido

		if(preset.notNil){

			presetlist = preset.asKeyValuePairs;
			~multisynth[msg[1]].addsynth[msg[3]].set(*presetlist);

		} {("este preset no existe "++msg[4]).postln}
	}
	{msg[2] == \buf} {~multisynth[msg[1]].addsynth[msg[3]].set(\buf, ~buffers[msg[4]])}
	{msg[2] == \add} {~multisynth[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \addmulti} {~multisynth[msg[1]].addmulti(msg.drop(3))}
	{msg[2] == \insert} {~multisynth[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~multisynth[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~multisynth[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \pos} {~multisynth[msg[1]].outsynth.set(\pos, msg[3])}
	{msg[2] == \track} {~multisynth[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~multisynth.includesKey(msg[1])) {("exist "++msg[1]).postln}
		{
			// test fader and mixing_group
			if (msg.indicesOfEqual('#fader').notNil) {
				// "fader_presente".postln;
				fad_pos = msg.indicesOfEqual('#fader');
				// fad_pos.class.postln;
				// fad = msg.drop(*fad_pos);
				fad = [msg.removeAt(*fad_pos), msg.removeAt(*fad_pos)];

			};

			if (msg.indicesOfEqual('#group').notNil) {
				// "gr_presente".postln;
				group_pos = msg.indicesOfEqual('#group');
				// fad_pos.class.postln;
				group_arg = [msg.removeAt(*group_pos), msg.removeAt(*group_pos)];


				currentEnvironment.put((group_arg[1]++"group").asSymbol, []); //crea array
				~multisynth.add(msg[1]-> List[]);

				/*				"msg".postcln;
				msg.postln;
				~group_bus[group_arg[1]].postln;
				~group[group_arg[1]].postln;
				"msg".postcln;*/

				msg[4].do({|i|
					// i.postln;
					// ~multisynth[msg[1]].add(i);
					~multisynth[msg[1]].add(TrackConstKubus(~group_bus[group_arg[1]], 0, msg[2], msg[3], ~group[group_arg[1]], *msg.drop(5)));
				});

				// ~multisynth.add(msg[1]-> TrackConstKubus(~group_bus[group_arg[1]], msg[2], msg[3], msg[4], ~group[group_arg[1]], *msg.drop(5))); //bus, ch, fadeIn, amp, group, args
				~groups_synth[group_arg[1]].add(msg[1]);

				~group[group_arg[1]].onFree {
					"grupo_off".postln;
					~group_out[group_arg[1]].free;
					~group[group_arg[1]].free;
					~group.removeAt(group_arg[1]);
					~group_out.removeAt(group_arg[1]);
					~group_bus.removeAt(group_arg[1]);
					~groups_synth.removeAt(group_arg[1]);
				}
			}{
				if (msg.indicesOfEqual('#server').notNil) {
					//poner TrackConst8 con server
					server_pos = msg.indicesOfEqual('#server');
					server_arg = [msg.removeAt(*server_pos), msg.removeAt(*server_pos)];
					server_arg.postln;
					~multisynth.add(msg[1]-> TrackConst16_3(msg[2], msg[3], msg[4], ~scservers[server_arg[1]], *msg.drop(5))); //ch, fadeIn, amp, server, args
				}{
					~multisynth.add(msg[1]-> TrackConst16(msg[2], msg[3], msg[4], *msg.drop(5))); //ch, fadeIn, amp, args
				}
			};

			~multisynth[msg[1]][0].group.onFree {
				if(~fader_reg[msg[1]].notNil)
				{
					// var to_levels;

					currentEnvironment.at(~fader_reset[~fader_reg[msg[1]]]).fill(0); // reset array de valores vumeters en ~f1, ~f2, etc... encontrar mejor solucion!!!!


					~multisynth.removeAt(msg[1]);

					currentEnvironment.at(~fader_reg[msg[1]]).removeAt(msg[1]); // remove synth from fader_num
					currentEnvironment.at(fad[1]).removeAt(msg[1]); // remove assign sinth a fader

					~fader_reg.removeAt(msg[1]); // remove synth from fader_reg
					~groups_synth[group_arg[1]].removeAt(~groups_synth[group_arg[1]].indexOf(msg[1]));

				}{
					// ~fader_reg[msg[1]].postln;
					~multisynth.removeAt(msg[1]);
					~groups_synth[group_arg[1]].removeAt(~groups_synth[group_arg[1]].indexOf(msg[1]));
				}

			};

			if (fad.notNil){
				currentEnvironment.at(fad[1]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
				~fader_reg.add(msg[1]-> fad[1]);
				// ("fade assign "++ fad[1]).postln;
			};

		}
	};


}, '/multisynth', nil, 7002).fix;


/*~group_bus[\server1].server.class

~group[\server1].server*/
// ~group[\test].run(true)
// ~group_out[\test].set(\amp, 0)
//
// ~group[\test].set(\gate, 1)
//
// currentEnvironment.put(\test, [])
//
// ~testgroup .add("toto")
//
// currentEnvironment.at(\test).add("tata")
//
// ~testgroup[0].addsynth
// ~group[\test].free
//
// fork {
// 	~group_out[\test].set(\amp, -120);
// 	0.01.wait;
// 	~group[\test].run(false)
// };
//
// fork {
// 	~group_out[\test].set(\amp, 0);
// 	0.01.wait;
// 	~group[\test].run(true)
// };

// ~group[\test].set[\gatee, 0]

// currentEnvironment.at(\test)

// ~synth[\freeze_vla1].outsynth

// currentEnvironment.at(\fade1)
/*~fadelev[\fader6]

~fade1.peakLevel = 0;
~fade1.value = 0;*/

// currentEnvironment.at(\test)

// ~synth[\synth1].outsynth.set(\pos0, 0.66)

OSCdef(\Track8_piano, {|msg|
	var fad, fad_pos, test, lista, group_arg, group_pos;

	// msg.postln;
	case
	{msg[2] == \off} {~synth[msg[1]].outsynth.set(\out_ramp, msg[3], \gatee, 0)}// ??group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].group.run(true);
		~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~synth[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} {~synth[msg[1]].addsynth[msg[3]].set(*msg.drop(4))}
	{msg[2] == \setn} {lista = msg.drop(4); ~synth[msg[1]].addsynth[msg[3]].set(lista[0], lista.drop(1))}
	{msg[2] == \buf} {~synth[msg[1]].addsynth[msg[3]].set(\buf, ~buffers[msg[3]])}
	{msg[2] == \add} {~synth[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \addmulti} {~synth[msg[1]].addmulti(msg.drop(3))}
	{msg[2] == \insert} {~synth[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~synth[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~synth[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \pos} {~synth[msg[1]].outsynth.set(\pos, msg[3])}
	{msg[2] == \pan} {~synth[msg[1]].outsynth.set(msg[3], msg[4])}
	// ~synth[\toto].outsynth.set(\pos0, 0.44)
	{msg[2] == \track} {~synth[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~synth.includesKey(msg[1])) {("exist"++msg[1]).postln}
		{
			// test fader and mixing_group
			if (msg.indicesOfEqual('#fader').notNil) {
				// "fader_presente".postln;
				fad_pos = msg.indicesOfEqual('#fader');
				// fad_pos.class.postln;
				// fad = msg.drop(*fad_pos);
				fad = [msg.removeAt(*fad_pos), msg.removeAt(*fad_pos)];

			};

			if (msg.indicesOfEqual('#group').notNil) {
				// "gr_presente".postln;
				group_pos = msg.indicesOfEqual('#group');
				// fad_pos.class.postln;
				group_arg = [msg.removeAt(*group_pos), msg.removeAt(*group_pos)];
				// fad.postln;
				// msg = msg.keep(*group_pos);
				/*group_arg.postln;
				~group[group_arg[1]].postln;*/
				currentEnvironment.put((group_arg[1]++"group").asSymbol, []); //crea array
				// msg.postln;
				~synth.add(msg[1]-> TrackConst8_in_piano(~group_bus[group_arg[1]], msg[2], msg[3], msg[4], ~group[group_arg[1]], *msg.drop(5))); //bus, ch, fadeIn, amp, group, args

				~group[group_arg[1]].onFree {
					"grupo_off".postln;
					~group[group_arg[1]].free;
					~group.removeAt(group_arg[1]);

				}
			}{
				~synth.add(msg[1]-> TrackConst8_in_piano2(msg[2], msg[3], msg[4], *msg.drop(5))); //ch, fadeIn, amp, args

			};



			~synth[msg[1]].group.onFree {
				if(~fader_reg[msg[1]].notNil)
				{
					// var to_levels;

					currentEnvironment.at(~fader_reset[~fader_reg[msg[1]]]).fill(0); // reset array de valores vumeters en ~f1, ~f2, etc... encontrar mejor solucion!!!!


					~synth.removeAt(msg[1]);

					currentEnvironment.at(~fader_reg[msg[1]]).removeAt(msg[1]); // remove synth from fader_num
					currentEnvironment.at(fad[1]).removeAt(msg[1]); // remove assign sinth a fader

					~fader_reg.removeAt(msg[1]); // remove synth from fader_reg

				}{
					// ~fader_reg[msg[1]].postln;
					~synth.removeAt(msg[1]);
				}

			};
			// msg[1].postln;
			/// crea fader
			if (fad.notNil){
				currentEnvironment.at(fad[1]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
				~fader_reg.add(msg[1]-> fad[1]);
				// ("fade assign "++ fad[1]).postln;
			};

		}
	};



}, '/crea_track8_piano', nil, 7002).fix;




OSCdef(\Aux, {|msg|
	var fad, fad_pos, group_arg, group_pos, preset, presetlist, json_path, json_file, path_def, path, fxName;
	/*	"auxxxxxxxxxxxx".postln;
	msg.postln;*/
	/*var modlist;
	modlist = msg.drop(1);*/
	// msg.postln;
	//msg.drop(3).postln;
	if (msg.indicesOfEqual('#fader').notNil) {
		// "fader_presente".postln;
		fad_pos = msg.indicesOfEqual('#fader');
		// fad_pos.class.postln;
		fad = msg.drop(*fad_pos);
		// fad.postln;
		msg = msg.keep(*fad_pos);
		// msg.postln;
	};

	case
	{msg[2] == \off} {~aux[msg[1]].outsynth.set(\out_ramp, msg[3], \gatee, 0)}// ??group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~aux[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~aux[msg[1]].group.run(true);
		~aux[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~aux[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} {~aux[msg[1]].addsynth[msg[3]].set(*msg.drop(4))}
	{msg[2] == \setn} {~aux[msg[1]].addsynth[msg[3]].set(msg.drop(4))}
	{msg[2] == \setpreset} { // seria mas elegante de hacerlo dentro de TrackConst8 o no??
		// msg[5].postln;
		// preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++(msg[3].asString.drop(3)));
		// preset.postln;
		// msg[4].postln;
		// path_def.postln;
		fxName = msg[3].asString.drop(3).asSymbol;
		path = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/").standardizePath();
		path_def = path++fxName++".json";

		json_path = File(path_def.standardizePath,"r");
		json_file = json_path.readAllString;
		json_path.close;

		preset = json_file.jsonToDict; //convert json to dictionary

		preset = preset[fxName][msg[4]]; //reucpera preset_name contenido

		// preset.postln;

		/*		preset = preset[msg[4]];
		msg[3].asString.drop(3).postln;
		("/TSupport/fxPresets/"++msg[3].asString.drop(3)).postln;*/
		if(preset.notNil){
			/*			temparams = (msg[3].asString.drop(3)).asSymbol.asClass.paramslider.collect({|x| x.key});
			tempmultiparams = (msg[3].asString.drop(3)).asSymbol.asClass.parammultislider.collect({|x| x.key});
			paramsmerge = temparams++tempmultiparams;
			presetlist = [paramsmerge, preset].flop.flatten(1); // merge de nombres y valores de los presets*/
			presetlist = preset.asKeyValuePairs;
			~synth[msg[1]].addsynth[msg[3]].set(*presetlist);


		}
		{("este preset aux no existe "++msg[4]).postln}
	}
	{msg[2] == \buf} {~aux[msg[1]].addsynth[msg[3]].set(\buf, ~buffers[msg[3]])}
	{msg[2] == \add} {~aux[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \addmulti} {~aux[msg[1]].addmulti(msg.drop(3))}
	{msg[2] == \insert} {~aux[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~aux[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~aux[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \pos} {~aux[msg[1]].outsynth.set(\pos, msg[3])}
	{msg[2] == \track} {~aux[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~aux.includesKey(msg[1])) {("aux exist "++msg[1]).postln}
		{


			if (msg.indicesOfEqual('#group').notNil) {

				group_pos = msg.indicesOfEqual('#group');
				// fad_pos.class.postln;
				group_arg = [msg.removeAt(*group_pos), msg.removeAt(*group_pos)];

				// fad.postln;
				// msg = msg.keep(*group_pos);
				// "tototototo".postln;

				~group_out[group_arg[1]].postln;

				currentEnvironment.put((group_arg[1]++"group").asSymbol, []); //crea array
				// msg.postln;
				~aux.add(msg[1]-> AuxConst82(~group_bus[group_arg[1]], msg[2], msg[3], msg[4], ~group[group_arg[1]], *msg.drop(5))); //bus, ch, fadeIn, amp, group, args
				// "gr_presente3".postln;
				~groups_synth[group_arg[1]].add(msg[1]);

				// "gr_presente4".postln;

				~group[group_arg[1]].onFree {
					"grupo_off".postln;
					~group_out[group_arg[1]].free;
					~group[group_arg[1]].free;
					~group.removeAt(group_arg[1]);
					~group_out.removeAt(group_arg[1]);
					~group_bus.removeAt(group_arg[1]);
					~aux_connections[msg[1]].free;
					~groups_synth.removeAt(group_arg[1]);

				}
			}{

				~aux.add(msg[1]-> AuxConst8(msg[2], msg[3], msg[4], *msg.drop(5))); //ch, fadeIn, amp, args
			};

			~aux[msg[1]].group.onFree {
				if(~fader_reg[msg[1]].notNil)
				{

					if(~aux_connections[msg[1]].isRunning){
						~aux_connections[msg[1]].free;
					};

					~aux.removeAt(msg[1]);

					currentEnvironment.at(~fader_reset[~fader_reg[msg[1]]]).fill(0); // reset array de valores vumeters en ~f1, ~f2, etc... encontrar mejor solucion!!!!

					currentEnvironment.at(~fader_reg[msg[1]]).removeAt(msg[1]); // remove synth from fader_num
					currentEnvironment.at(fad[1]).removeAt(msg[1]); // remove assign sinth a fader

					~fader_reg.removeAt(msg[1]); // remove synth from fader_reg
					~aux_connections.removeAt(msg[1]);

					"OFF-Aux".postln;
					// currentEnvironment.at(~fader_reg[msg[1]]).removeAt(msg[1]); // remove synth from fader_num
					// ~fader_reg.removeAt(msg[1]); // remove synth from fader_reg
					// {0.1.wait;
					// 	{
					// 		~aux_connections.removeAt(msg[1]);
					// 		currentEnvironment.at(to_levels).peakLevel = 0;
					// 		currentEnvironment.at(to_levels).value = 0;
					// }.defer}.fork;

				}{
					// ~fader_reg[msg[1]].postln;
					if(~aux_connections[msg[1]].isRunning){
						~aux_connections[msg[1]].free;
					};
					~aux.removeAt(msg[1]);
					~aux_connections.removeAt(msg[1]);
				}

			};
			// msg[1].postln;
			/// crea fader
			/*currentEnvironment.at(fad[1]).add(msg[1]-> [~aux[msg[1]].bus.index,~aux[msg[1]].outsynth]);
			~fader_reg.add(msg[1]-> fad[1]);*/

			if (fad.notNil){
				currentEnvironment.at(fad[1]).add(msg[1]-> [~aux[msg[1]].bus.index,~aux[msg[1]].outsynth]);
				~fader_reg.add(msg[1]-> fad[1]);
				// ("fade assign "++ fad[1]).postln;
			};
			// "gr_presente5".postln;
		}
	};
	/*	currentEnvironment.at(msg[2]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
	~fader_reg.add(msg[1]-> msg[2]); // registran en que fader se encuentra la synth*/


}, '/crea_aux', nil, 7002).fix;


OSCdef(\Aux16, {|msg|
	var fad, fad_pos, group_arg, group_pos, preset, presetlist, json_path, json_file, path_def, path, fxName;
	/*	"auxxxxxxxxxxxx".postln;
	msg.postln;*/
	/*var modlist;
	modlist = msg.drop(1);*/
	// msg.postln;
	//msg.drop(3).postln;
	if (msg.indicesOfEqual('#fader').notNil) {
		// "fader_presente".postln;
		fad_pos = msg.indicesOfEqual('#fader');
		// fad_pos.class.postln;
		fad = msg.drop(*fad_pos);
		// fad.postln;
		msg = msg.keep(*fad_pos);
		// msg.postln;
	};

	case
	{msg[2] == \off} {~aux[msg[1]].outsynth.set(\out_ramp, msg[3], \gatee, 0)}// ??group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~aux[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~aux[msg[1]].group.run(true);
		~aux[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~aux[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} {~aux[msg[1]].addsynth[msg[3]].set(*msg.drop(4))}
	{msg[2] == \setn} {~aux[msg[1]].addsynth[msg[3]].set(msg.drop(4))}
	{msg[2] == \setpreset} { // seria mas elegante de hacerlo dentro de TrackConst8 o no??
		// msg[5].postln;
		// preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++(msg[3].asString.drop(3)));
		// preset.postln;
		// msg[4].postln;
		// path_def.postln;
		fxName = msg[3].asString.drop(3).asSymbol;
		path = (Platform.userAppSupportDir ++ "/TSupport/fxPresets/").standardizePath();
		path_def = path++fxName++".json";

		json_path = File(path_def.standardizePath,"r");
		json_file = json_path.readAllString;
		json_path.close;

		preset = json_file.jsonToDict; //convert json to dictionary

		preset = preset[fxName][msg[4]]; //reucpera preset_name contenido

		// preset.postln;

		/*		preset = preset[msg[4]];
		msg[3].asString.drop(3).postln;
		("/TSupport/fxPresets/"++msg[3].asString.drop(3)).postln;*/
		if(preset.notNil){
			/*			temparams = (msg[3].asString.drop(3)).asSymbol.asClass.paramslider.collect({|x| x.key});
			tempmultiparams = (msg[3].asString.drop(3)).asSymbol.asClass.parammultislider.collect({|x| x.key});
			paramsmerge = temparams++tempmultiparams;
			presetlist = [paramsmerge, preset].flop.flatten(1); // merge de nombres y valores de los presets*/
			presetlist = preset.asKeyValuePairs;
			~synth[msg[1]].addsynth[msg[3]].set(*presetlist);


		}
		{("este preset aux16 no existe "++msg[4]).postln}
	}
	{msg[2] == \buf} {~aux[msg[1]].addsynth[msg[3]].set(\buf, ~buffers[msg[3]])}
	{msg[2] == \add} {~aux[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \addmulti} {~aux[msg[1]].addmulti(msg.drop(3))}
	{msg[2] == \insert} {~aux[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~aux[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~aux[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \pos} {~aux[msg[1]].outsynth.set(\pos, msg[3])}
	{msg[2] == \track} {~aux[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~aux.includesKey(msg[1])) {("exist "++msg[1]).postln}
		{


			if (msg.indicesOfEqual('#group').notNil) {

				group_pos = msg.indicesOfEqual('#group');
				// fad_pos.class.postln;
				group_arg = [msg.removeAt(*group_pos), msg.removeAt(*group_pos)];

				// fad.postln;
				// msg = msg.keep(*group_pos);
				// "tototototo".postln;

				// ~group_out[group_arg[1]].postln;

				currentEnvironment.put((group_arg[1]++"group").asSymbol, []); //crea array
				// msg.postln;
				~aux.add(msg[1]-> AuxConst16_2(~group_bus[group_arg[1]], msg[2], msg[3], msg[4], ~group_out[group_arg[1]], *msg.drop(5))); //bus, ch, fadeIn, amp, group, args
				// "gr_presente3".postln;
				~groups_synth[group_arg[1]].add(msg[1]);

				// "gr_presente4".postln;

				~group[group_arg[1]].onFree {
					"grupo_off".postln;
					~group_out[group_arg[1]].free;
					~group[group_arg[1]].free;
					~group.removeAt(group_arg[1]);
					~group_out.removeAt(group_arg[1]);
					~group_bus.removeAt(group_arg[1]);
					~aux_connections[msg[1]].free;
					~groups_synth.removeAt(group_arg[1]);

				}
			}{
				/////////////actualizar a 16 !!! ! ! ! ! ! !
				~aux.add(msg[1]-> AuxConst8(msg[2], msg[3], msg[4], *msg.drop(5))); //ch, fadeIn, amp, args
			};

			~aux[msg[1]].group.onFree {
				if(~fader_reg[msg[1]].notNil)
				{

					if(~aux_connections[msg[1]].isRunning){
						~aux_connections[msg[1]].free;
					};

					~aux.removeAt(msg[1]);

					currentEnvironment.at(~fader_reset[~fader_reg[msg[1]]]).fill(0); // reset array de valores vumeters en ~f1, ~f2, etc... encontrar mejor solucion!!!!

					currentEnvironment.at(~fader_reg[msg[1]]).removeAt(msg[1]); // remove synth from fader_num
					currentEnvironment.at(fad[1]).removeAt(msg[1]); // remove assign sinth a fader

					~fader_reg.removeAt(msg[1]); // remove synth from fader_reg
					~aux_connections.removeAt(msg[1]);

					"OFF-Aux".postln;
					// currentEnvironment.at(~fader_reg[msg[1]]).removeAt(msg[1]); // remove synth from fader_num
					// ~fader_reg.removeAt(msg[1]); // remove synth from fader_reg
					// {0.1.wait;
					// 	{
					// 		~aux_connections.removeAt(msg[1]);
					// 		currentEnvironment.at(to_levels).peakLevel = 0;
					// 		currentEnvironment.at(to_levels).value = 0;
					// }.defer}.fork;

				}{
					// ~fader_reg[msg[1]].postln;
					if(~aux_connections[msg[1]].isRunning){
						~aux_connections[msg[1]].free;
					};
					~aux.removeAt(msg[1]);
					~aux_connections.removeAt(msg[1]);
				}

			};
			// msg[1].postln;
			/// crea fader
			/*currentEnvironment.at(fad[1]).add(msg[1]-> [~aux[msg[1]].bus.index,~aux[msg[1]].outsynth]);
			~fader_reg.add(msg[1]-> fad[1]);*/

			if (fad.notNil){
				currentEnvironment.at(fad[1]).add(msg[1]-> [~aux[msg[1]].bus.index,~aux[msg[1]].outsynth]);
				~fader_reg.add(msg[1]-> fad[1]);
				// ("fade assign "++ fad[1]).postln;
			};
			// "gr_presente5".postln;
		}
	};
	/*	currentEnvironment.at(msg[2]).add(msg[1]-> [~synth[msg[1]].bus.index,~synth[msg[1]].outsynth]);
	~fader_reg.add(msg[1]-> msg[2]); // registran en que fader se encuentra la synth*/


}, '/crea_aux16', nil, 7002).fix;

/*~aux[\aux_1].bus*/

OSCdef(\Connect_Aux, {|msg| //connect_aux synth aux ramp_in
	var group, bus, auxindex;
	group = ~synth[msg[1]].group;
	bus = ~synth[msg[1]].bus;
	auxindex = ~aux[msg[2]].bus.index;
	// msg.postln;
	if(~aux.size != 0){
		~aux[msg[2]].bus;
	}{"primero instanciar un aux!!".posln};
	if(~synth.size != 0){
		/*	~synth[msg[1]].bus.postln;
		~synth[msg[1]].group.postln;*/
	}{"no hay synth!!".posln};
	if (~aux_connections.includesKey(msg[2]++msg[1])) {("aux connection exist "++msg[2]++msg[1]).postln}
	{
		~aux_connections.add(msg[2]++msg[1] -> AudioInAux.synthInAux(group, bus.index, auxindex, bus.index, msg[3]));
	}

}, '/connect_aux', nil, 7002).fix;



OSCdef(\Aux_in_lev, {|msg| //controla el nivel de salida hacia

	~aux_connections[msg[1]].set(\amp, msg[2])

}, '/aux_in_lev', nil, 7002).fix;


OSCdef(\Track_noEnv, {|msg|
	/*var modlist;
	modlist = msg.drop(1);*/
	msg.postln;
	//msg.drop(3).postln;
	case
	{msg[2] == \off} {~synth[msg[1]].group.set(\gatee, 0)}
	{msg[2] == \set} {~synth[msg[1]].addsynth[msg[3]].set(*msg.drop(4))}
	{msg[2] == \add} {~synth[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \insert} {~synth[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~synth[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~synth[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \track_amp} {~synth[msg[1]].group.set(\amp, msg[3])} // amp group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1]-> TrackConst(msg[2], 0.01, msg[3], *msg.drop(4))); //ch, fadeIn, amp, args
			~synth[msg[1]].group.onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/crea_track_nenv', nil, 7002).fix;




// ~synth[\grantestin].addsynth["01_TGran_ext_in".asSymbol].set(\buf, ~buffers[\tata], \t_trig, 1, \envbuf, 41, \dur, 1, \rate, 1)



OSCdef(\Synth_order, {|msg|
	if (~synth[msg[1]].notNil){
		n.sendMsg(\synth, msg[1], *(~synth[msg[1]].addsynth.order))
	}{
		n.sendMsg(\synth, "error no synth")
	}
}, '/synth_order', nil, 7002).fix;


OSCdef(\Actives_Synth, {|msg|
	var synths;
	/*msg.postln;*/
	synths = ~synth.order; //keys.asArray;
	if (~synth.keys.notNil){
		n.sendMsg(\active_synth, *synths)
	}{
		n.sendMsg(\active_synth, "no synth")
	}
}, '/actives_synth', nil, 7002).fix;





OSCdef(\wavetable, {|msg|
	//[msg[1], msg[2], msg[3], msg[4], [msg[5], \buf, ~wave_buff[msg[6]].bufnum]].postln;
	// msg.postln;
	TrackConstEnv(msg[1], msg[2], msg[3], msg[4], *[msg[5], \buf, ~wave_buff[msg[6]]] ++ msg.drop(7)); //ch, env, dur, amp, module, buf, args

}, '/wavetable', nil, 7002).fix;


OSCdef(\Wavetable_noEnv, {|msg|
	/*var modlist;
	modlist = msg.drop(1);*/
	// msg.postln;
	//msg.drop(3).postln;
	case
	{msg[2] == \off} {~synth[msg[1]].group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].group.run(true);
		~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~synth[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} {~synth[msg[1]].addsynth[msg[3]].set(*msg.drop(4))}
	{msg[2] == \add} {~synth[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \insert} {~synth[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~synth[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~synth[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \pos} {~synth[msg[1]].outsynth.set(\pos, msg[3])}
	{msg[2] == \track} {~synth[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{

			~synth.add(msg[1]-> TrackConst(msg[2], msg[3], msg[4], *[msg[5], \buf, ~wave_buff[msg[6]]] ++ msg.drop(7))); //ch, fadeIn, amp, args
			~synth[msg[1]].group.onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/wavetable_noenv', nil, 7002).fix;




OSCdef(\AddSynth, {|msg|
	var lista, freqs, amps, pos;
	// msg.postln;
	lista = msg.drop(4);
	pos = Array.fill(20, 0);

	freqs = lista.keep(20);
	amps = lista.keep(-20);
	/*freqs.postln;
	amps.postln;*/
	// [msg[3], msg.drop(4)].postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3]); ~synth[msg[1]].set(\free, 0)}
	{msg[2] == \pause} {~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].run(true);
		~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 1)}
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))}
	{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4)); msg.drop(4)} //param lista (listas)
	{msg[2] == \preset} { // addSynth track_name preset TAddic_20_8 preset_name fadein, channel, amp
		var preset, temparams, group, bus, outsynth, fadein, channel, amp;
		// msg.postln;
		group = Group.new;
		bus = Bus.audio(Server.default, 1);
		// fadein = msg[3];
		channel = msg[6];
		amp = msg[7];

		preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++msg[3].asString);
		preset.postln;
		temparams = msg[3].asClass.paramslider.collect({|x| x.key});
		temparams.postln;
		preset = preset[msg[4].asSymbol];
		preset.postln;

		outsynth = AudioOut.synth2(group, bus, bus.index, fadein, channel, amp);
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1] -> msg[3].asClass.synthoutpar(outsynth, [[temparams,preset].flop.flat, [\matrix_ramp, msg[5], \in, bus, \out, bus]].flatten(1)));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
		}
	} //param lista (listas)
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, [\matrix_ramp, msg[3], \freqs, freqs, \amps, amps, \pos, pos]));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1]);};
		}
	};

}, '/addSynth', nil, 7002).fix;


OSCdef(\Impulses, {|msg|
	var lista, freqs, amps, pos;
	// msg.postln;
	lista = msg.drop(4);
	pos = Array.fill(4, 0);

	freqs = lista.keep(4);
	amps = lista.keep(-4);
	// freqs.postln;
	// msg[3].postln;
	// [msg[3], msg.drop(4)].postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3]); ~synth[msg[1]].set(\free, 0)}
	{msg[2] == \pause} {~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].run(true);
		~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 1)}
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))}
	{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4))} //param lista (listas)
	{msg[2] == \preset} { // addSynth track_name preset TAddic_20_8 preset_name fadein, channel, amp
		var preset, temparams, group, bus, outsynth, fadein, channel, amp;
		msg.postln;
		group = Group.new;
		bus = Bus.audio(Server.default, 1);
		// fadein = msg[3];
		channel = msg[6];
		amp = msg[7];

		preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++msg[3].asString);
		preset.postln;
		temparams = msg[3].asClass.paramslider.collect({|x| x.key});
		temparams.postln;
		preset = preset[msg[4].asSymbol];
		preset.postln;

		outsynth = AudioOut.synth2(group, bus, bus.index, fadein, channel, amp);
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1] -> msg[3].asClass.synthoutpar(outsynth, [[temparams,preset].flop.flat, [\matrix_ramp, msg[5], \in, bus, \out, bus]].flatten(1)));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
		}
	} //param lista (listas)
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			// "toto".postln;
			~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, [\matrix_ramp, msg[3], \freqs, freqs]));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1]);};
		}
	};

}, '/impulses', nil, 7002).fix;


// addSynth_gen $track TAddic_XX nombre_partiels $fade_in

OSCdef(\AddSynth_gen, {|msg|
	var preset, temparams, tempmultiparams, group, bus, outsynth, fadein, channel, amp, params;
	// msg.postln;
	/*
	amps.postln;*/
	// [msg[3], msg.drop(4)].postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3]); ~synth[msg[1]].set(\free, 0)}
	{msg[2] == \pause} {~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].run(true);
		~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 1)}
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))}
	{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4))} //param lista (listas)
	{msg[2] == \amp} {~synth[msg[1]++"out".asSymbol].set(\amp, msg[3])} // control amp de AudioOut8
	{msg[2] == \preset} { // addSynth track_name preset TAddic_20_8 preset_name fadein, amp

		group = Group.new;
		bus = Bus.audio(Server.default, 8);
		// fadein = msg[3];
		channel = 0;
		amp = msg[6];

		preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++msg[3].asString);
		// preset.postln;
		temparams = msg[3].asClass.paramslider.collect({|x| x.key});

		tempmultiparams = msg[3].asClass.parammultislider.collect({|x| x.key});
		// tempmultiparams.postln;
		temparams = temparams++tempmultiparams;
		// temparams.postln;
		preset = preset[msg[4].asSymbol];
		// preset.postln;
		params = [temparams,preset].flop.flatten(1);
		// params.postln;
		outsynth = AudioOut8.synth2(group, bus, bus.index, fadein, channel, amp);
		~synth.add(msg[1]++"out" -> outsynth); // registra outsynth en diccionario
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1] -> msg[3].asClass.synthoutpar(outsynth, [params, [\matrix_ramp, msg[5], \in, bus, \out, bus]].flatten(1)));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1]);~synth.removeAt(msg[1]++"out");group.free;};
		}
	} //param lista (listas)
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			var lista, freqs, amps, pos, num_partiel;
			// msg.postln;
			lista = msg.drop(5);


			num_partiel = msg[3];

			pos = Array.fill(num_partiel, 0);

			freqs = lista.keep(num_partiel);
			amps = lista.keep(num_partiel.neg); // negative
			~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, [\matrix_ramp, msg[3], \freqs, freqs, \amps, amps, \pos, pos]));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/addSynth_gen', nil, 7002).fix;


/*OSCdef(\SubSynth_gen, {|msg|
var preset, temparams, tempmultiparams, group, bus, outsynth, fadein, channel, amp, params;
// msg.postln;
/*
amps.postln;*/
// [msg[3], msg.drop(4)].postln;
case
{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3]); ~synth[msg[1]].set(\free, 0)}
{msg[2] == \pause} {~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 0)}
{msg[2] == \resume} {~synth[msg[1]].run(true);
~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 1)}
{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))}
{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4))} //param lista (listas)
{msg[2] == \amp} {~synth[msg[1]++"out".asSymbol].set(\amp, msg[3])} // control amp de AudioOut8
{msg[2] == \preset} { // addSynth track_name preset TAddic_20_8 preset_name fadein, amp

group = Group.new;
bus = Bus.audio(Server.default, 8);
// fadein = msg[3];
channel = 0;
amp = msg[6];

preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++msg[3].asString);
// preset.postln;
temparams = msg[3].asClass.paramslider.collect({|x| x.key});

tempmultiparams = msg[3].asClass.parammultislider.collect({|x| x.key});
// tempmultiparams.postln;
temparams = temparams++tempmultiparams;
// temparams.postln;
preset = preset[msg[4].asSymbol];
// preset.postln;
params = [temparams,preset].flop.flatten(1);
// params.postln;
outsynth = AudioOut8.synth2(group, bus, bus.index, fadein, channel, amp);
~synth.add(msg[1]++"out" -> outsynth); // registra outsynth en diccionario
if (~synth.includesKey(msg[1])) {"exist".postln}
{
~synth.add(msg[1] -> msg[3].asClass.synthoutpar(outsynth, [params, [\matrix_ramp, msg[5], \in, bus, \out, bus]].flatten(1)));
~synth[msg[1]].onFree {~synth.removeAt(msg[1]);~synth.removeAt(msg[1]++"out");group.free;};
}
} //param lista (listas)
{msg[2].isSymbolWS}
{
if (~synth.includesKey(msg[1])) {"exist".postln}
{
var lista, freqs, amps, pos, num_partiel;
// msg.postln;
lista = msg.drop(5);

num_partiel = msg[3];

pos = Array.fill(num_partiel, 0);

freqs = lista.keep(num_partiel);
amps = lista.keep(num_partiel.neg); // negative
~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, [\matrix_ramp, msg[3], \freqs, freqs, \amps, amps, \pos, pos]));
~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
}
};

}, '/subsynth_gen', nil, 7002).fix;*/




OSCdef(\AddSynth8, {|msg|
	var lista, freqs, amps, pos, num;
	//msg.postln;
	num = 8; // number of partials
	lista = msg.drop(4);
	pos = Array.fill(num, 0);

	freqs = lista.keep(num);
	amps = lista.keep(num * -1);
	/*freqs.postln;
	amps.postln;*/
	// [msg[3], msg.drop(4)].postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3]); ~synth[msg[1]].set(\free, 0)}
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))}
	{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4))} //param lista (listas)
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, [\matrix_ramp, msg[3], \freqs, freqs, \amps, amps, \pos, pos]));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/addSynth8', nil, 7002).fix;



OSCdef(\AddSynth11, {|msg|
	var lista, freqs, amps, pos, num;
	// msg.postln;
	num = 11; // number of partials
	lista = msg.drop(4);
	pos = Array.fill(num, 0);

	freqs = lista.keep(num);
	amps = lista.keep(num * -1);
	/*freqs.postln;
	amps.postln;*/
	// [msg[3], msg.drop(4)].postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3]); ~synth[msg[1]].set(\free, 0)}
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))}
	{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4))} //param lista (listas)
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, [\matrix_ramp, msg[3], \freqs, freqs, \amps, amps, \pos, pos]));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/addSynth11', nil, 7002).fix;



OSCdef(\SustractSynth, {|msg| // track_name, preset_name, fadein, channel, amp  sustractsynth toto poesia-paisaje1 1 0 0
	// msg.postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3]); ~synth[msg[1]].set(\free, 0)}

	// {msg[2] == \off} {~synth[msg[1]].group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].run(true);
		~synth[msg[1]].set(\matrix_ramp, msg[3], \gate, 1)}
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))}
	{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4))} //param lista (listas)
	{msg[2] == \track} {~synth[msg[1]].group.set(*msg.drop(3))} // control global group
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			var preset, temparams_s, temparams_m, temparams, group, bus, outsynth, fadein, channel, amp;
			msg.postln;
			group = Group.new;
			bus = Bus.audio(Server.default, 1);
			// fadein = msg[3];
			channel = msg[4];
			amp = msg[5];

			preset = Object.readArchive(Platform.userAppSupportDir ++ "/TSupport/fxPresets/"++"TSustract");
			temparams_s = \TSustract.asClass.paramslider.collect({|x| x.key});
			temparams_m = \TSustract.asClass.parammultislider.collect({|x| x.key});
			temparams = temparams_s++temparams_m;
			temparams.postln;
			preset = preset[msg[2].asSymbol];
			[temparams,preset].flop.flatten(1).postln;

			outsynth = AudioOut.synth2(group, bus, bus.index, fadein, channel, amp);
			~synth.add(msg[1] -> \TSustract.asClass.synthoutpar(outsynth, [[temparams,preset].flop.flatten(1), [\matrix_ramp, msg[3], \in, bus, \out, bus]].flatten(1)));
			~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/sustractsynth', nil, 7002).fix;

/*~synth[\aurora1].set(\gate, 0)*/

OSCdef(\directSynthEnv, {|msg|

	//[msg[1] = synth, msg[2] = amp, msg[3] =dur, msg[4], [msg[5], \buf, ~wave_buff[msg[6]].bufnum]].postln;
	var listampfreq, synth, listparams, freqs, amps, pos;
	msg.postln;
	listampfreq = msg.keep(-40);
	listparams = msg.drop(2).drop(-40);
	listparams.postln;
	synth = msg[1];
	freqs = listampfreq.keep(20);
	amps = listampfreq.keep(-20);
	Synth(synth, listparams ++ [\freqs, freqs, \amps, amps]);

}, '/directSynthEnv', nil, 7002).fix;


OSCdef(\SigmundTrack, {|msg|
	var lista, freqs, amps, pos;
	//msg.postln;

	/*	lista = msg.drop(4);
	pos = Array.fill(20, 0);

	freqs = lista.keep(20);
	amps = lista.keep(-20);
	/*freqs.postln;
	amps.postln;*/
	// [msg[3], msg.drop(4)].postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3]); ~synth[msg[1]].set(\free, 0)}
	{msg[2] == \set} {~synth[msg[1]].set(*msg.drop(3))}
	{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4))} //param lista (listas)
	{msg[2].isSymbolWS}
	{
	if (~synth.includesKey(msg[1])) {"exist".postln}
	{
	~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, [\matrix_ramp, msg[3], \freqs, freqs, \amps, amps, \pos, pos]));
	~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
	}
	};*/

}, '/sigmundTrack', nil, 7002).fix;


/*
a = [1, 2, 3, 4, 5]
b = a.drop(1).drop(-2);
a.first
a.drop(1)
*/

OSCdef(\Plot, {|msg|
	var plt;
	msg.postcs;
	plt = msg.drop(1);
	{ plt.asArray.plot}.defer;

}, '/plot', nil, 7002).fix;



OSCdef(\Test, {|msg|
	msg.postcs;
}, '/test', nil, 7002).fix;


OSCdef(\Pitch, {|msg|
	// msg.postcs;
	~testsy.set(\pitch, msg[1]);
	~testsy2.set(\pitch, msg[1]+5);
}, '/pitch', nil, 7002).fix;

OSCdef(\Amp, {|msg|
	// msg.postcs;
	~testsy.set(\amp, msg[1]);
	~testsy2.set(\amp, msg[1]);
}, '/amp', nil, 7002).fix;

OSCdef(\Pitch).disable;
OSCdef(\Amp).disable;
OSCdef(\Noisiness).disable;

/*
~testsy = Synth(\pitchFollow2)
~testsy2 = Synth(\pitchFollow2)
~testsy.set(\amp, 0.7)
~testsy.set(\pitch, 30)

~testsy.free
*/
/*OSCdef(\Pitch, {|msg|
//msg.postcs;

}, '/pitch', nil, 7002).fix;*/

OSCdef(\Noisiness, {|msg|
	msg.postcs;

}, '/noisiness', nil, 7002).fix;

OSCdef(\BufWavetab, {|msg|
	var wave, size, harmtab;
	//msg.postcs;
	harmtab = msg.drop(2);
	harmtab.postcs;
	size = 512;
	wave = Signal.sineFill(512, harmtab).asWavetable;
	//wave = Signal([0.5, 0.2, 0.1, 0.44, 0.33]).asWavetable;
	//~wave_buff.put(msg[1], Buffer.alloc(s, size * 2));
	~wave_buff[msg[1]].loadCollection(wave);

}, '/bufWavetab', nil, 7002).fix;


OSCdef(\Trig_gran_seq, {|msg|
	// msg.postln;
	// msg[1].class.postln;
	// [msg.drop(3)].postln;
	// [\t_trig, 1, msg.drop(3)].flat.postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \set} {~synth[msg[1]].set(*[\t_trig, 1, msg.drop(3)].flat)}
	// {msg[2] == \set} {~synth[msg[1]].set(*[[\buf, ~buffers[msg[3]], \t_trig, 1] ++ msg.drop(4)].flat)}
	{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4))} //param lista (listas)
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			/*			msg[3].postln;
			~buffers[msg[3]].postln;*/
			if (~buffers[msg[3]].notNil) { //test de seguridad si buffer no exite NO TOCA!!
				// ~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, msg.drop(4)));
				~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, *[[\buf, ~buffers[msg[3]]] ++ msg.drop(4)]));
				~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
				/*	"buffer existe".postln;*/
			}{
				/*"buffer NO existe".postln;*/
			}
		}
	};

}, '/trig_gran_seq', nil, 7002).fix;

// if (~buffers[\mi3_mes1].notNil) {"toto".postln}{"titi".postln}

// ~synth[\play_gran_seq_mes161].set(\t_trig, 1, \buf, 174);
// ~synth[\trackgran].set(\free, 0);


OSCdef(\GranTrigger, {|msg|
	// msg.postln;

	~synth[msg[1]].addsynth["00_TGran_ext_in".asSymbol].set(\buf, ~buffers[msg[2].asSymbol], \t_trig, 1, *msg.drop(3)); //track, buffer_name, params
	//gran_trig $track 01_TGran_ext_in t_trig 1 buf $buf envbuf @choose(@range($envs2)) dur @dur2sec($del) rate $rate

}, '/gran_trig', nil, 7002).fix;


OSCdef(\GranTrigger2, {|msg|
	// msg.postln;

	~synth[msg[1]].addsynth["00_TGran_ext_in3".asSymbol].set(\buf, ~buffers[msg[2].asSymbol], \t_trig, 1, *msg.drop(3)); //track, buffer_name, params
	//gran_trig $track 01_TGran_ext_in t_trig 1 buf $buf envbuf @choose(@range($envs2)) dur @dur2sec($del) rate $rate

}, '/gran_trig2', nil, 7002).fix;


OSCdef(\Trig_env_interp, {|msg|
	// msg.postln;
	// msg[1].class.postln;
	// [msg.drop(3)].postln;
	// [\t_trig, 1, msg.drop(3)].flat.postln;
	case
	{msg[2] == \off} {~synth[msg[1]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \set} {~synth[msg[1]].set(*[msg.drop(3)].flat)}
	// {msg[2] == \set} {~synth[msg[1]].set(*[[\buf, ~buffers[msg[3]], \t_trig, 1] ++ msg.drop(4)].flat)}
	{msg[2] == \setn} {~synth[msg[1]].set(msg[3], msg.drop(4))} //param lista (listas)
	{msg[2].isSymbolWS}
	{
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			/*			msg[3].postln;
			~buffers[msg[3]].postln;*/
			if (~buffers[msg[2]].notNil) { //test de seguridad si buffer no exite NO TOCA!!
				// ~synth.add(msg[1]-> Synth(\bufGrainI, *[[\buf, ~buffers[msg[2]]] ++ msg.drop(3)]));
				~synth.add(msg[1]-> Synth(\granInterp, *[[\buf, ~buffers[msg[2]]] ++ msg.drop(3)]));
				~synth[msg[1]].onFree {~synth.removeAt(msg[1])};
				/*	"buffer existe".postln;*/
			}{
				/*"buffer NO existe".postln;*/
			}
		}
	};

}, '/trig_env_interp', nil, 7002).fix;



OSCdef(\FftFreezeTrigger, {|msg|
	var last, rest;
	// msg.postln;
	last = msg.last;
	rest = msg.drop(5).drop(-1);

	// [rest++ [\buf, ~bufft[last.asSymbol]]].postln;
	// modlist = msg.drop(1);
	// msg.postln;
	/*	last.class.postln;
	rest.postln;
	last.asSymbol.postln;
	~bufft[last].postln;
	[msg[4], [rest ++ [\buf, ~bufft[last.asSymbol]]]].postln;*/
	//msg.drop(3).postln;
	// [msg[2], msg[3], msg[4], *[rest, \buf, ~bufft[last.asSymbol]]].postln;
	case
	{msg[2] == \off} {~synth[msg[1]].group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].group.run(true);
		~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~synth[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} {~synth[msg[1]].addsynth[msg[3]].set(*msg.drop(4))}
	{msg[2] == \buf} {~synth[msg[1]].addsynth[msg[3]].set(\buf, ~buffers[msg[3]])}
	{msg[2] == \add} {~synth[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \insert} {~synth[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~synth[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~synth[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \pos} {~synth[msg[1]].outsynth.set(\pos, msg[3])} // outsynth es AudioOut8
	{msg[2] == \track} {~synth[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~synth.includesKey(rest[1])) {"exist".postln}
		{
			// TrackConst8(msg[2], msg[3], msg[4], *msg.drop(5)));
			// *[msg[5], \buf, ~wave_buff[msg[6]]] ++ msg.drop(7)));
			~synth.add(msg[1]-> TrackConst8(msg[2], msg[3], msg[4], *[rest++ [\buf, ~bufft[last.asSymbol]]].flat)); //ch, fadeIn, amp, fftbuffer args
			~synth[msg[1]].group.onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/fftfreezetrig', nil, 7002).fix;

OSCdef(\FftFreezeTrigger_multi, {|msg|
	var buff, rest, rest2;
	// msg.postln;

	case
	{msg[2] == \off} {~synth[msg[1]].group.set(\out_ramp, msg[3], \gatee, 0)}
	{msg[2] == \pause} {~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 0)}
	{msg[2] == \resume} {~synth[msg[1]].group.run(true);
		~synth[msg[1]].group.set(\matrix_ramp, msg[3], \gate, 1);
		~synth[msg[1]].addsynth.values.collect(_.run(true))}
	{msg[2] == \set} {~synth[msg[1]].addsynth[msg[3]].set(*msg.drop(4))}
	{msg[2] == \buf} {~synth[msg[1]].addsynth[msg[3]].set(\buf, ~buffers[msg[3]])}
	{msg[2] == \add} {~synth[msg[1]].addmodule(msg.drop(3))}
	{msg[2] == \insert} {~synth[msg[1]].insertafter(msg[3], msg[4], *msg.drop(5))}
	{msg[2] == \kill} {~synth[msg[1]].addsynth[msg[3]].set(\matrix_ramp, msg[3],\free, 0)}
	{msg[2] == \amp} {~synth[msg[1]].outsynth.set(\amp, msg[3])}
	{msg[2] == \pos} {~synth[msg[1]].outsynth.set(\pos, msg[3])} // outsynth es AudioOut8
	{msg[2] == \track} {~synth[msg[1]].group.set(*msg.drop(3))} // control global group

	{msg[2].isNumber}
	{
		// "tototoototootototototo".postln;
		//[msg[2], msg[3], msg[4], msg.drop(5)].postln;
		if (~synth.includesKey(msg[1])) {"exist".postln}
		{
			// "tototototo".postln;
			rest = msg.drop(5);
			// rest.postln;
			buff = rest[5];
			buff = ~bufft[buff.asSymbol];
			// rest.insert(5, \buff);
			rest.put(5, buff.bufnum);
			rest2 = rest.insert(5, \buf);
			// rest2.postln;
			~synth.add(msg[1]-> TrackConst8(msg[2], msg[3], msg[4], *rest2)); //ch, fadeIn, amp, fftbuffer args
			~synth[msg[1]].group.onFree {~synth.removeAt(msg[1])};
		}
	};

}, '/fftfreezetrigmulti', nil, 7002).fix;


// ~bufft["bufft0".asSymbol]

// ~synth[\freeze_fondu].outsynth

/*~wave_buff[\cb_m41].plot*/

OSCdef(\RecBuffer, {|msg|
	msg.postcs;

	case
	{msg[1] == \new} {

		if (~synth.includesKey(msg[1])) {"exist, use replace".postln}

		{
			{
				~buffers.put(msg[2].asSymbol, Buffer.alloc(server, server.sampleRate*msg[3], 1)); //crea un buffer mono y lo pone el buf en el dico ~buffers
				server.sync; // importante sino SC server crash!!
				Synth(\recbuff, [\in, saxIn, \rec_dur, msg[3], \buf, ~buffers[msg[2].asSymbol].bufnum])}.fork;
		} //lanza el rec

	}

	{msg[1] == \free} {
		~buffers[msg[2].asSymbol].free;
		~buffers.removeAt(msg[2].asSymbol)}
	{msg[1] == \replace} {
		~buffers[msg[2].asSymbol].free;
		~buffers.removeAt(msg[2].asSymbol);
		{
			~buffers.put(msg[2].asSymbol, Buffer.alloc(server, server.sampleRate*msg[3], 1));
			server.sync; // importante sino SC server crash!!

			{Synth(\recbuff, [\in, saxIn, \buf, ~buffers[msg[2].asSymbol].bufnum])}.defer(0.011); // defer para dejar tiempo para vaciar re-instanciar y volver a lanzar el rec
		}.fork;
	}
}, '/recbuf', nil, 7002).fix;

OSCdef(\RecBufferInst, {|msg|
	/*	msg.postcs;
	"prueba".postln;*/
	case
	{msg[1] == \new} {

		if (~synth.includesKey(msg[1])) {"exist, use replace".postln}

		{
			{
				~buffers.put(msg[2].asSymbol, Buffer.alloc(server, server.sampleRate*msg[3], 1)); //crea un buffer mono y lo pone el buf en el dico ~buffers
				server.sync; // importante sino SC server crash!!
				Synth(\recbuff, [\in, msg[4], \rec_dur, msg[3], \buf, ~buffers[msg[2].asSymbol].bufnum]);
				/*				~toto = ~buffers[msg[2].asSymbol].bufnum;
				~toto.postln;*/
			}.fork;

		} //lanza el rec

	}

	{msg[1] == \free} {
		~buffers[msg[2].asSymbol].free;
		~buffers.removeAt(msg[2].asSymbol)}
	{msg[1] == \clear} {
		~buffers[msg[2].asSymbol].zero}
	{msg[1] == \replace} {
		~buffers[msg[2].asSymbol].free;
		~buffers.removeAt(msg[2].asSymbol);
		{
			~buffers.put(msg[2].asSymbol, Buffer.alloc(server, server.sampleRate*msg[3], 1));
			server.sync; // importante sino SC server crash!!

			{Synth(\recbuff, [\in, msg[4], \buf, ~buffers[msg[2].asSymbol].bufnum])}.defer(0.011); // defer para dejar tiempo para vaciar re-instanciar y volver a lanzar el rec
		}.fork;
	}
}, '/recbufinst', nil, 7002).fix;

// ~buffers[\granbuf_3_0]

// ~buffers[\mes119_sax].plot

/*a = Buffer.alloc(Server.default, Server.default.sampleRate*1, 1);

a.plot

Synth(\recbuff, [\in, 24, \rec_dur, 1, \buf, a.bufnum])*/


OSCdef(\RecBufferAux, {|msg|
	var bus;
	msg.postcs;
	bus = ~synth[msg[4]].bus.index;
	"prueba".postln;
	bus.postln;
	case
	{msg[1] == \new} {

		if (~synth.includesKey(msg[1])) {"exist, use replace".postln}

		{
			{
				~buffers.put(msg[2].asSymbol, Buffer.alloc(server, server.sampleRate*msg[3], 1)); //crea un buffer mono y lo pone el buf en el dico ~buffers
				server.sync; // importante sino SC server crash!!
				Synth(\recbuff, [\in, bus, \rec_dur, msg[3], \buf, ~buffers[msg[2].asSymbol].bufnum]);
				/*				~toto = ~buffers[msg[2].asSymbol].bufnum;
				~toto.postln;*/
			}.fork;

		} //lanza el rec

	}

	{msg[1] == \free} {
		~buffers[msg[2].asSymbol].free;
		~buffers.removeAt(msg[2].asSymbol)}
	{msg[1] == \replace} {
		~buffers[msg[2].asSymbol].free;
		~buffers.removeAt(msg[2].asSymbol);
		{
			~buffers.put(msg[2].asSymbol, Buffer.alloc(server, server.sampleRate*msg[3], 1));
			server.sync; // importante sino SC server crash!!

			{Synth(\recbuff, [\in, bus, \buf, ~buffers[msg[2].asSymbol].bufnum], addAction: 'addToTail')}.defer(0.011); // defer para dejar tiempo para vaciar re-instanciar y volver a lanzar el rec
		}.fork;
	}
}, '/recbufaux', nil, 7002).fix;

// ~buffers[\mes119_sax].plot


OSCdef(\RecBufferOnset, {|msg|
	// msg.postcs;
	{
		~buffers.put(msg[1].asSymbol, Buffer.alloc(server, server.sampleRate*msg[2], 1)); //crea un buffer mono y lo pone el buf en el dico ~buffers
		server.sync; // importante sino SC server crash!!
		Synth(\recbuffonset, [\in, saxIn, \rec_dur, msg[2], \buf, ~buffers[msg[1].asSymbol].bufnum]) //lanza la synth y espera el onset
		/*{~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, *[[\buf, ~buffers[msg[3]]] ++ msg.drop(4)]));
		~synth[msg[1]].onFree {~synth.removeAt(msg[1])}.defer(0.011);*/
	}.fork;

}, '/recbufonset', nil, 7002).fix;


OSCdef(\RecBufferOnsetInst, {|msg|
	// msg.postcs;
	{
		~buffers.put(msg[1].asSymbol, Buffer.alloc(server, server.sampleRate*msg[2], 1)); //crea un buffer mono y lo pone el buf en el dico ~buffers
		server.sync; // importante sino SC server crash!!
		Synth(\recbuffonset, [\in, msg[3], \rec_dur, msg[2], \buf, ~buffers[msg[1].asSymbol].bufnum]) //lanza la synth y espera el onset
		/*{~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, *[[\buf, ~buffers[msg[3]]] ++ msg.drop(4)]));
		~synth[msg[1]].onFree {~synth.removeAt(msg[1])}.defer(0.011);*/
	}.fork;

}, '/recbufonsetinst', nil, 7002).fix;

// ~buffers[\cb_test].plot
/*~buffers[\granbuf_3_0]
Buffer.as*/

OSCdef(\Clearbuffer, {|msg|
	// msg.postcs;
	{
		// ~buffers.put(msg[1].asSymbol, Buffer.alloc(server, server.sampleRate*msg[2], 1)); //crea un buffer mono y lo pone el buf en el dico ~buffers
		// server.sync; // importante sino SC server crash!!
		// Synth(\recbuffonset, [\in, msg[3], \rec_dur, msg[2], \buf, ~buffers[msg[1].asSymbol].bufnum]) //lanza la synth y espera el onset
		/*{~synth.add(msg[1]-> Synth(msg[2].asClass.synthdefname, *[[\buf, ~buffers[msg[3]]] ++ msg.drop(4)]));
		~synth[msg[1]].onFree {~synth.removeAt(msg[1])}.defer(0.011);*/
		// ~buffers[\]

	}.fork;

}, '/clearbuffer', nil, 7002).fix;


// ~group[\SynthPerc1].server

("sax-input"++saxIn).postln;

"OSC-receiver-loaded".postln;

/*

{
SynthDef(.....).add;
s.sync;
Synth(\newSDef, ...);
}.fork;

//////multi freeze

~buffers.put(\toto, Buffer.alloc(s, s.sampleRate*1, 1))

a = Synth(\recbuff, [\in, 8, \buf, ~buffers[\toto].bufnum])
a.free

~buffers.removeAt(\toto)

~buffers[\toto].plot
~buffers[\tata].plot

OSCdef(\tab, {|msg|
msg.postln;

}, '/tab', nil, 7676).fix;


(
var buffersFreeze;
buffersFreeze = {Buffer.alloc(s, s.sampleRate*1)}.dup(10); //genera un array de 10 buffers

OSCdef(\multiFreeze, {|msg|
var index;
index = msg[3].asInteger;
if(msg[2]== 2, {
// msg.postln;
// Synth.new(\onsetGranMulti, [\trig, 1, \buf, buffersFreeze[index-1]]);
Synth.new(\onsetGranMultiRall, [\trig, 1, \buf, buffersFreeze[index-1], \sustain, 5]);
})
}, '/tr', s.addr); // recibe osc trigger desde scsynth



)


SynthDef(\onsetFft1, {|out = 0, in = 9, thresh = 0.5, num_buf = 10|
var input, chain, onsets, counter, fftsize;
fftsize = 512;
input= SoundIn.ar(in); //input signal
chain = FFT(LocalBuf(fftsize, 1), input);
onsets = Onsets.kr(chain, thresh); // Onser detector, probar otros!
counter = PulseCount.ar(K2A.ar(onsets))%num_buf+1;
SendTrig.kr(onsets, 2, counter)
}).load;


SynthDef(\onsetGranMulti, {|out = 0, thresh = 0.5, trate =  50, trig = 0, buf| //poner aqui la interaccion-captores
var dur, clk, pos, pan, in, chain, onsets, env, grain;
dur = 12 / trate;
clk = Impulse.kr(trate);
pos = TRand.kr(0, 0.01, clk);
pan = WhiteNoise.kr(0.6);
in = SoundIn.ar(9);
RecordBuf.ar(in, buf, loop: 0, trigger: trig);
env = EnvGen.kr(Env.linen(0.01, 2, 4, 0.6, 'sine'), trig, doneAction: 2);
grain = TGrains.ar(2, clk, buf.clear, 1, pos, dur, pan, 0.1);
Out.ar(out, grain*env)
}).load;

x = Synth.new(\onsetFft1);
x.free;

SynthDef(\onsetGranMultiRall, {|out = 0, thresh = 0.5, trate =  50, trig = 0, buf, trigRateDev = 50, rateLo = 0.95, rateHi = 1.03, sustain = 4| //poner aqui la interaccion-captores
var dur, clk, pos, pan, in, chain, onsets, env, grain, line, rate, desv;

line = XLine.kr(120, 2, sustain + 4);
clk = Impulse.kr(line+ WhiteNoise.kr(trigRateDev));
pos = TRand.kr(0, 0.01, clk);
rate = TRand.kr(rateLo, rateHi, clk);
dur = 12 / trate + TRand.kr(0, 0.1, clk);
pan = WhiteNoise.kr(0.6);
in = SoundIn.ar(9);
RecordBuf.ar(in, buf, loop: 0, trigger: trig);
env = EnvGen.kr(Env.linen(0.01, sustain, 2, 0.8, 'sine'), trig, doneAction: 2);
grain = TGrains.ar(2, clk, buf, rate, pos, dur, pan, 0.1);
Out.ar(out, grain*env)
}).load;

OSCdef.all

~synth[\track3].addmodule([\TestSynth2, '#preset', \preset2])
~synth[\track3].addmodule([\TMFxShift2, \modfreq, 50, \amp, 0])
~synth[\track3].addmodule([\TMFxShift2, '#preset', \preset1])
~synth[\track3].addsynth[\TMFxShift2_2].set(\modfreq, 100)

~synth[\track3].addsynth.keys.asArray.sort
~synth[\track3].addsynth.order
~synth[\track3].addsynth.sort
~synth[\track3].addsynth.asKeyValuePairs;
~synth[\track3].addsynth.gui

~synth[\track3].outsynth.set(\amp, 0.2)

~synth[\track2]



a = '[Synth1, 1, [freq, 400, amp, 0] -> Synth2, 1, [freq, 800, amp, -10]]'
b = ("#" ++ a.asArray.asCompileString).interpret
b[0].class
c = b.asCompileString.interpret
c.class

[1, 3, 2, 0]

[2, 3, 0, 1]

[2, 1, 0, 3] original
[1, 2, 0, 3] original

a

x = "1,2,3,4,5,6,7".split($,).collect(_.asInteger);
a = ("#" ++ [4,7,6].asCompileString).interpret
a.class

out = 0, outbus, outgain = 0, amp = -6, freq = 300, in_ramp = 0.01, out_ramp = 0.01, gate = 1, free = 1|

a = Synth(TMFxSin2.synthdefname, [\in_ramp, 2])
a.set(\out_ramp, 0.1,\free, 0)
a

b = [\track1, \TMFxSin2, \freq, 500, \amp, -10]

b.drop(2);

b
a.set(b.drop(2).flat)



~synth.values.collect(_.set(\free, 0));
~synth = Dictionary.new;
~synth.size


~synth.includesKey(\toto)

///generar dictinary!!
~synth = Dictionary.new;

~synth.add(\track1->TWgui.new("sin112", 1 , 1, 0, 1))

~synth[\track1].addsynth.set(\freq, 400)

~synth[\track1].group.set(\gatee, 0)

~synth[\track1].group.onFree {~synth.removeAt(\track1)};


case
(

OSCdef(\OSCil, {|msg|

if (msg[2] == \off) {~synth[msg[1]].group.set(\gatee, 0)}
{
if (~synth.includesKey(msg[1])) {"exist".postln}
{
~synth.add(msg[1]-> TWgui.new(msg[2], msg[3] , msg[4], msg[5], msg[6]));
~synth[msg[1]].group.onFree {~synth.removeAt(msg[1])};
}
}

}, '/Synth3', nil, 7002);

)




[\in, 0,\out, 1]


b = TWgui.new("sin112", 1 , 1, 0, 1)
b.synth

TWguiEnv.new("test-par2", ~envs[2], 0, 0.2, 1.5)

a = TWguiEnv.new("shift-test-fade2", ~envs[2], 0, 0.2, 30.5)
a.group

1.class

TWguiEnv.new(preset, env, chnl, amp, dur)


a = Synth(TMFxSin2.synthdefname)

a.set(\gate, 0)

a.nodeID


(

OSCdef(\Menu, {|msg|
var temp;
temp = Synth(TMFxSin2.synthdefname);


}, '/Synth3', nil, 7002);

)

[1,2,3].as


~envs[2].plot


(
var i, x, z;
z = [\synth, \set, \otros];
i = z.choose.postln;
x = case
{ i == \synth } { "toto"}
{ i == \set } { "titi"}
{i.isSymbolWS}{"tutu"};

x.postln;
)


///////////////////////////
s.plotTree;
a = TrackConst(2, 5, 0.5, \TestSynth2, \note, 40, \fc, 500, '#->', \TestSynth2, \note, 35, \fc, 300, '#->', \TMFxShift2, \modfreq, 50, \amp, 0)
a.gui
a.addsynth
a = TrackConst(2, 5, 0.5, \TestSynth2, \preset, \preset1, \to, \TestSynth2, \note, 45, \fc, 300, \to, \TMFxShift2, \modfreq, 50, \amp, 0)
a = TrackConst(1, 5, 0.5, \TestSynth2, '#preset', \preset2)

a.gui
a.addmodule([\TMFxShift2, \modfreq, 50, \amp, 0])
a.addmodule([\TestSynth2, '#preset', \preset2])
a.addsynth[\TMFxShift2_8].set(\modfreq, 10)
a.addsynth[\TMFxShift2].set(\free, 0)
a.addsynth[\TestSynth2_2].set(\free, 0)
a.insertafter(\TestSynth2, \TestSynth2_2, \note, 30, \fc, 500)
a.addsynth.gui
a.group.set(\out_ramp, 3, \gatee, 0)



a = TrackConstEnv(2, 22, 1, 0, \TestSynth2, \note, 40, \fc, 500, '#->', \TestSynth2, \note, 35, \fc, 300, '#->', \TMFxShift2, \modfreq, 50, \amp, 1)

*/
