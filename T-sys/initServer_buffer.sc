InitServer_buffer {

	*new {|server|
		^super.newCopyArgs(server).initServer_buffer(server);
	}

	initServer_buffer {|server|


		/////////////INIT///////////////////////
		var vbap_speakers, vbap_speakersQuad, vbap_speakers3D, vbap_speakers_buf, vbap_speakers3D_buf, vbap_speakersKubus, dbspec, faderwin, f1, f2, f3, f4, f5, f6, f7, f8, a1, a2, a3, a4, a5, a6, a7, a8, g1, g2, g3, noise_buf, re2_2_buf, oscillaz, a8buf, cond;

		// server = Server.default;

		("Init-starting.... " ++ server).postln;


		/*~bufferserver  = IdentityDictionary.new;
		~envserver  = IdentityDictionary.new;
		~bufferserver.put(\bufserver1, IdentityDictionary.new);
		~envserver.put(\envserver1, Array.fill(11));*/

		Buffer.alloc(server, 512); // for Onset detection buffer 0

		40.do({|val| // crea 20 buffers y los pone en un diccionario hay que estar seguro que son los 20 primeros buffers!!!!!
			Buffer.alloc(server, server.sampleRate*0.5, 1)

		});



		/*		~scservers[\server1]
		~bufferserver[\bufserver1]*/
		// creacion de envolventes buffer 41 a 48 REVISAR!!!

		// ~envs = Array.fill(11); // number of envs
		[Env([0, 1, 0], [0.5, 0.5], [8, -8]), Env([0, 1, 0], [0.5, 0.5], [-4, 4]), Env([0, 1, 0], [0.1, 0.9], [8, -8]), Env([0, 1, 0], [0.9, 0.1], [8, 2]), Env([0,1,0.9,0], [0.1,0.5, 1].normalizeSum,[-5,0,-5]), Env([0,1,0.5,0.9, 0], [0.5,0.2, 0.2, 0.5].normalizeSum,[4,4,2, -4]), Env([0,0.7,0.3,1, 0], [0.5,0.2, 0.2, 0.2].normalizeSum,[4,-4,2, -4]), Env([0,1,1,0], [0.005,0.99, 0.005].normalizeSum,[4,0, -4]), Env([0,1,1,0], [0.005,0.99, 0.1].normalizeSum,[4,0, 4]), Env([0,1,1,0], [0.05,0.99, 0.05].normalizeSum)].do({|e i|
			Buffer.sendCollection(server, e.discretize, 1);
		});

		noise_buf = Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/buffers/noise_env.aif"); // env from noise soundfile
		("noise_buf_num = "++noise_buf.bufnum).postln;


		80.do({|val| // crea 40 buffers para fft freeze 2048
			if (val< 40){
				Buffer.alloc(server,2048);
			} {
				Buffer.alloc(server, 1024)
			}
		});

		// Buffer.cachedBufferAt(s, 53);
		// ~bufft[\bufft58].plot

		10.do({|val| // crea 10 buffers de 2 segundos a partir de 132

			Buffer.alloc(server, server.sampleRate*2, 1);
		});


		// ~bufft.postItems
		// ~buffers.postItems


		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/buffers-synth/Multi17-4.aif", 0, 32768); //cambiado para hyperspheres para ver para Dispertion el uso de power of 2
		Buffer.read(server,Platform.userAppSupportDir ++ "/TSupport/buffers-synth/granbuf1.aif", 0, 32768);
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/buffers-synth/slap-la2-Mono.aif", 0, 65536);
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/buffers-synth/slap-do3.aif", 0, 65536);
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/buffers-synth/slap-lab3.aif", 0, 65536);
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/buffers-synth/Multi111.aif", 0, 65536);
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/buffers-synth/re2-1.aif", 0, 65536);
		re2_2_buf = Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/buffers-synth/re2-2.aif", 0, 65536);

		("re2_2_buf "++re2_2_buf.bufnum).postln;
		// Synth(\play_buf2, [\buf, ~buffers[\re2_2]])
		// ~buffers[\re2_2]
		// ~buffers[\granbuf_24]

		/*40.do({|val| // crea 40 buffers para fft freeze 1024
		~bufft.put((\bufft++val).asSymbol, Buffer.alloc(server, 1024))});*/

		// buffers para wavetable synthesis


		// SoundFile
		f1 = SoundFile.openRead(Platform.userAppSupportDir ++ "/TSupport/buffers-synth/Multi17-4.aif");
		f2 = SoundFile.openRead(Platform.userAppSupportDir ++ "/TSupport/buffers-synth/granbuf1.aif");
		f3 = SoundFile.openRead(Platform.userAppSupportDir ++ "/TSupport/buffers-synth/slap-la2-Mono.aif");
		f4 = SoundFile.openRead(Platform.userAppSupportDir ++ "/TSupport/buffers-synth/slap-do3.aif");
		f5 = SoundFile.openRead(Platform.userAppSupportDir ++ "/TSupport/buffers-synth/slap-lab3.aif");
		f6 = SoundFile.openRead(Platform.userAppSupportDir ++ "/TSupport/buffers-synth/Multi111.aif");
		f7 = SoundFile.openRead(Platform.userAppSupportDir ++ "/TSupport/buffers-synth/re2-1.aif");
		f8 = SoundFile.openRead(Platform.userAppSupportDir ++ "/TSupport/buffers-synth/re2-2.aif");
		// f.path; // ok

		// An array to load the data
		a1 = FloatArray.newClear(f1.numFrames);
		a2 = FloatArray.newClear(f2.numFrames);
		a3 = FloatArray.newClear(f3.numFrames);
		a4 = FloatArray.newClear(f4.numFrames);
		a5 = FloatArray.newClear(f5.numFrames);
		a6 = FloatArray.newClear(f6.numFrames);
		a7 = FloatArray.newClear(f7.numFrames);
		a8 = FloatArray.newClear(f8.numFrames);

		f1.readData(a1);
		f2.readData(a2);
		f3.readData(a3);
		f4.readData(a4);
		f5.readData(a5);
		f6.readData(a6);
		f7.readData(a7);
		f8.readData(a8);

		f1.close; // close the file
		f2.close; // close the file
		f3.close; // close the file
		f4.close; // close the file
		f5.close; // close the file
		f6.close; // close the file
		f7.close; // close the file
		f8.close; // close the file
		// a.size; // 169 in my file

		// resamp the table to have a pow of 2 (bigger to avoid aliassing)
		// if u read many diff samples choose a bigger pow of 2
		a1 = a1.resamp1(1024);
		a2 = a2.resamp1(1024);
		a3 = a3.resamp1(1024);
		a4 = a4.resamp1(1024);
		a5 = a5.resamp1(1024);
		a6 = a6.resamp1(1024);
		a7 = a7.resamp1(1024);
		a8 = a8.resamp1(1024);

		// Conver it to a Signal
		a1 = a1.as(Signal);
		a2 = a2.as(Signal);
		a3 = a3.as(Signal);
		a4 = a4.as(Signal);
		a5 = a5.as(Signal);
		a6 = a6.as(Signal);
		a7 = a7.as(Signal);
		a8 = a8.as(Signal);
		// a.size; // 256 ok

		// Convert it to a Wavetable
		a1 = a1.asWavetable;
		a2 = a2.asWavetable;
		a3 = a3.asWavetable;
		a4 = a4.asWavetable;
		a5 = a5.asWavetable;
		a6 = a6.asWavetable;
		a7 = a7.asWavetable;
		a8 = a8.asWavetable;
		// a.size; // 512 ok, (wavetable format is signal.size * 2

		// Server side
		/*s.boot;
		b = Buffer.loadCollection(s, a);
		x = b.play(loop: true); // ok sounds
		x.free;

		x = { LPF.ar(Osc.ar(b, MouseX.kr(440, 880)), SampleRate.ir/2-1000) }.play;
		s.freqscope*/

		/*x.free;
		s.quit;*/

		Buffer.loadCollection(server, a1); //cambiado para hyperspheres para ver para Dispertion el uso de power of 2
		Buffer.loadCollection(server, a2);
		Buffer.loadCollection(server, a3);
		Buffer.loadCollection(server, a4);
		Buffer.loadCollection(server, a5);
		Buffer.loadCollection(server, a6);
		Buffer.loadCollection(server, a7);
		a8buf = Buffer.loadCollection(server, a8);
		("a8 "++a8buf.bufnum).postln;



		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/Multi23-larg-BUENO.aif");
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/ecrase-derriere-chevalet.aif");
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/sonido17.aif");
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/cherokee.aif");
		// ~buffers.put(\octa5, Buffer.read(server, "/Users/jose/Documents/Lara/Octaedrite/sounds/double-bass-gettato1.aif"));
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/traspPM-perc.wav");

		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/prova1gran.wav");
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/ecrase-derriere-chevalet1.aif");

		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/PM-perc-render0-M13.wav");
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/glis_036-trasp.wav");
		Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/tramaAcuta+metalAudio13.wav");

		oscillaz = Buffer.read(server, Platform.userAppSupportDir ++ "/TSupport/octa_sounds/oscillaz.wav");

		("oscillaz "++oscillaz.bufnum).postln;






		// ~buffers[\octa4].plot;
		// ~wave_buff[\vbap].plot

		///// Write Buffers ======== ~buffers[\granbuf1].write("/Volumes/AuDiO-750Go/Composicion-Claude-2014/buffers-synth/granbuf1.aif", headerFormat: "aiff", sampleFormat: "int24", numFrames: -1, startFrame: 0, leaveOpen: false);
		// Buffer.freeAll
		/*~buffers[\granbuf6]
		~buffers.keys.asArray[0].class*/

		//////////Init buffer Impulse/Response Reverberation

		~fftsize=2048; // also 4096 works on my machine; 1024 too often and amortisation too pushed, 8192 more high load FFT

		cond = Condition.new;

		{
			var ir, irbuffer, bufsize, irspectrum;

			Routine.run({
				g1 = Buffer.alloc(server, 9, 1);
				g2 = Buffer.alloc(server, 9, 1);
				g3 = Buffer.alloc(server, 9, 1);

				g1.setn(0,Array.rand(9,0.1,0.9));
				g2.setn(0,Array.rand(9,0.1,0.9));
				g3.setn(0,Array.rand(9,0.1,0.9));

				server.sync(cond);
				// // MONO ONLY
				("g3 "++g3.bufnum).postln;
				// irbuffer = Buffer.read(s, Platform.resourceDir +/+ "sounds/8.7s_Big_Gothic_Church.L.aif");


				// synthesise the honourable 'Dan Stowell' impulse response

				ir = ([1] ++0.dup(100) ++ ((1, 0.99998 .. 0).collect{|f| f =
					f.squared.squared; f = if(f.coin){0}{f.squared}; f =
					if(0.5.coin){0-f}{f} } * 0.1)).normalizeSum;


				irbuffer = Buffer.loadCollection(server, ir);

				server.sync(cond);

				bufsize= PartConv.calcBufSize(~fftsize, irbuffer);

				// ~numpartitions= PartConv.calcNumPartitions(~fftsize, irbuffer);

				irspectrum = Buffer.alloc(server, bufsize, 1);

				irspectrum.preparePartConv(irbuffer, ~fftsize);

				("irspectrum "++irspectrum.bufnum).postln;

				server.sync(cond);

				irbuffer.free; // don't need time domain data anymore, just needed spectral version
				server.sync(cond);
				vbap_speakers = VBAPSpeakerArray.new(2, [-22.5, 22.5, 67.5, 112.5, 157.5, -157.5, -112.5, -67.5]); // 8 channel ring
				// b = a.loadToBuffer;
				server.sync(cond);
				vbap_speakers_buf = vbap_speakers.loadToBuffer(server);
				("vbap_speakers "++vbap_speakers_buf.bufnum).postln;
				server.sync(cond);

				vbap_speakers3D = VBAPSpeakerArray.new(3, [[-22.5, 0], [22.5, 0], [67.5, 0], [112.5, 0], [157.5, 0], [-157.5, 0], [-112.5, 0], [-67.5, 0], [-22.5, 14.97], [22.5, 14.97], [90, 14.97], [157.5, 14.97], [-157.5, 14.97], [-90, 14.97], [0, 14.97], [180, 14.97]]);
				server.sync(cond);

				// toma mucho tiempo de creacion oof hasta la proxima Karlsruhe

				/*vbap_speakersKubus = VBAPSpeakerArray.new(3, [[180, 0], [-146.575195, 0], [-124.992012, 0], [-106.699242, 0], [-73.300751, 0], [-55.007980, 0], [-33.424812, 0], [0, 0], [33.424812, 0], [55.007980, 0], [73.300751, 0], [106.699242, 0], [124.992012, 0], [146.575195, 0], [-165.256439, 32.465874], [-137.121094, 27.628639], [-127.568588, 31.371115], [-89.999992, 37.568592], [-52.431408, 31.371115], [-42.878902, 27.628639], [-14.968815, 32.590466], [14.968815, 32.590466], [42.878902, 27.628639], [52.431408, 31.371115], [89.999992, 37.568592], [127.568588, 31.371115], [137.121094, 27.628639], [165.256439, 32.465874], [-149.036240, 52.136356], [-116.565048, 53.300774], [-63.434952, 53.300774], [-30.963758, 52.136356], [30.963758, 52.136356], [63.434952, 53.300774], [116.565048, 53.300774], [149.036240, 52.136356], [-135.000000, 71.594818], [-89.999992, 70.559975], [-45.000000, 71.594818], [45.000000, 71.594818], [89.999992, 70.559975], [135.000000, 71.594818], [0, 89.999992]]);*/

				vbap_speakersKubus = VBAPSpeakerArray.new(2, [0, 45, 90, 135, 180, -135, -90, -45]); // reemplazo


				server.sync(cond);


				~wave_buff.put(\vbap3d, vbap_speakers3D.loadToBuffer(server));
				("vbap_speakers "++~wave_buff[\vbap3d].bufnum).postln;
				server.sync(cond);
				~wave_buff.put(\vbapkubus, Buffer.loadCollection(server, vbap_speakersKubus.getSetsAndMatrices));
				("vbap_speakers "++~wave_buff[\vbapkubus].bufnum).postln;

				///Vbap Init
				("buffer_vbap2D = "++~wave_buff[\vbap].bufnum).postln;
				("buffer_vbap3D = "++~wave_buff[\vbap3d].bufnum).postln;
				("buffer_vbap_kubus = "++~wave_buff[\vbapkubus].bufnum).postln;
				server.sync(cond);

				vbap_speakersQuad = VBAPSpeakerArray.new(2, [-45, 45, 135, -135]); // 8 channel ring

				~wave_buff.put(\vbapQuad, vbap_speakersQuad.loadToBuffer(server));
				("vbap_speakers_Quad "++~wave_buff[\vbapQuad].bufnum).postln;


				// 1.wait;
				/////ATK Init
				server.sync(cond);

				~spread_encoder = FoaEncoderKernel.newSpread(6, 2048, server);
				~diffuse_encoder = FoaEncoderKernel.newDiffuse(3, 2048, server);
				server.sync;

				server.sync(cond);


				30.do({|val| // crea 10 buffers de 2 segundos a partir de 132

					~buffers.put((\granbuf_3_++val).asSymbol, Buffer.alloc(server, server.sampleRate*3, 1));
				});

				//buffers percusion
				"buffers percusion".postln;
				~buffers[\granbuf_3_0].bufnum.postln;
				~buffers[\granbuf_3_29].bufnum.postln;

				/////////////carga files desde folder "/sounds/" en SuperCollider

				~files = (Platform.userAppSupportDir ++ "/sounds/").standardizePath();
				~files2 = PathName.new(~files);
				~soundfolder = ~files2.folders;


				~soundfolder.collect({ |folders|
					folders.files.collect({ |filepath|
						var file_name;
						file_name = filepath.fileName;
						// file_name.postln;
						Buffer.read(server, filepath.fullPath);
					})

				});


			});
		}.fork;


		// ("vbap3d "++vbap_speakers3D_buf.bufnum).postln;




		///////////////////////faders

		("Init-end.... " ++ server).postln;
		/////////////INIT///////////////////////
	}
}
