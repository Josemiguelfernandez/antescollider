{
	"patcher" : 	{
		"fileversion" : 1,
		"appversion" : 		{
			"major" : 8,
			"minor" : 5,
			"revision" : 3,
			"architecture" : "x64",
			"modernui" : 1
		}
,
		"classnamespace" : "box",
		"rect" : [ 162.0, 66.0, 1059.0, 1025.0 ],
		"bglocked" : 0,
		"openinpresentation" : 1,
		"default_fontsize" : 12.0,
		"default_fontface" : 0,
		"default_fontname" : "Arial",
		"gridonopen" : 1,
		"gridsize" : [ 15.0, 15.0 ],
		"gridsnaponopen" : 1,
		"objectsnaponopen" : 1,
		"statusbarvisible" : 2,
		"toolbarvisible" : 0,
		"lefttoolbarpinned" : 0,
		"toptoolbarpinned" : 0,
		"righttoolbarpinned" : 0,
		"bottomtoolbarpinned" : 0,
		"toolbars_unpinned_last_save" : 7,
		"tallnewobj" : 0,
		"boxanimatetime" : 200,
		"enablehscroll" : 1,
		"enablevscroll" : 1,
		"devicewidth" : 0.0,
		"description" : "",
		"digest" : "",
		"tags" : "",
		"style" : "",
		"subpatcher_template" : "",
		"assistshowspatchername" : 0,
		"boxes" : [ 			{
				"box" : 				{
					"id" : "obj-71",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4796.97869873046875, 452.68316650390625, 70.0, 22.0 ],
					"text" : "loadmess 0"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.329412, 0.478431, 0.0 ],
					"blinkcolor" : [ 1.0, 1.0, 0.4, 1.0 ],
					"id" : "obj-75",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4796.97869873046875, 410.93316650390625, 26.75, 26.75 ],
					"presentation" : 1,
					"presentation_rect" : [ 938.777777777777828, 861.516693115234375, 14.0, 14.538461685180664 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-69",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 25.0, 200.0, 24.0, 24.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 3.0, 189.0, 16.0, 16.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-67",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"patching_rect" : [ 848.0, -45.0, 29.5, 22.0 ],
					"text" : "!- 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-66",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 848.0, 0.0, 52.0, 22.0 ],
					"text" : "gate 1 0"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-64",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 855.0, 136.0, 52.0, 22.0 ],
					"text" : "gate 1 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-59",
					"maxclass" : "toggle",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1272.9583740234375, 149.0, 24.0, 24.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 966.277777777777828, 222.94757080078125, 45.06292724609375, 45.06292724609375 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-40",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1258.9583740234375, 209.0, 52.0, 22.0 ],
					"text" : "gate 1 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-39",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 1009.0, 188.5, 22.0, 22.0 ],
					"text" : "t b"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-77",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 858.340705023871578, 304.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 859.969187418620095, 279.764495849609375, 62.0, 20.0 ],
					"text" : "tempo GF"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-76",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 858.340705023871578, 324.5, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 909.340705023871578, 235.479034423828125, 52.0, 20.0 ],
					"text" : "from GF"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-56",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 1178.0, 157.0, 60.0, 22.0 ],
					"text" : "zl change"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.572549019607843, 0.631372549019608, 0.329411764705882, 1.0 ],
					"bgcolor2" : [ 0.572549019607843, 0.631372549019608, 0.329411764705882, 1.0 ],
					"bgfillcolor_angle" : 270.0,
					"bgfillcolor_autogradient" : 0.0,
					"bgfillcolor_color" : [ 0.384313725490196, 0.647058823529412, 0.435294117647059, 1.0 ],
					"bgfillcolor_color1" : [ 0.572549019607843, 0.631372549019608, 0.329411764705882, 1.0 ],
					"bgfillcolor_color2" : [ 0.505882352941176, 0.556862745098039, 0.227450980392157, 1.0 ],
					"bgfillcolor_proportion" : 0.5,
					"bgfillcolor_type" : "color",
					"fontsize" : 24.0,
					"gradient" : 1,
					"id" : "obj-53",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1036.45831298828125, 235.0, 132.0, 35.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 764.080298529730953, 229.014495849609375, 137.016883002387203, 35.0 ],
					"text" : "mes_2"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-51",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 1174.0, 112.0, 47.0, 22.0 ],
					"text" : "zl nth 3"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-50",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 984.7083740234375, 123.0, 178.0, 22.0 ],
					"text" : "1 Phrase-1 mes_2 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-47",
					"linecount" : 2,
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 930.0, 123.0, 50.0, 35.0 ],
					"text" : "49.365177"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-42",
					"maxclass" : "newobj",
					"numinlets" : 3,
					"numoutlets" : 3,
					"outlettype" : [ "", "", "" ],
					"patching_rect" : [ 949.7083740234375, 70.0, 120.0, 22.0 ],
					"text" : "route /tempo /marker"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-17",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 949.7083740234375, 34.0, 97.0, 22.0 ],
					"text" : "udpreceive 8891"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-8",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 693.0, 363.0, 53.0, 22.0 ],
					"text" : "r silence"
				}

			}
, 			{
				"box" : 				{
					"fontsize" : 9.0,
					"id" : "obj-37",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 596.5625, 816.993804931640625, 120.0, 17.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 650.260894775390625, 824.993804931640625, 32.0, 17.0 ],
					"text" : "reset "
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-29",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4442.97869873046875, 440.68316650390625, 70.0, 22.0 ],
					"text" : "loadmess 0"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.329412, 0.478431, 0.0 ],
					"blinkcolor" : [ 1.0, 1.0, 0.4, 1.0 ],
					"id" : "obj-28",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4442.97869873046875, 398.93316650390625, 26.75, 26.75 ],
					"presentation" : 1,
					"presentation_rect" : [ 997.340705023871578, 862.972036361694336, 14.0, 14.538461685180664 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-24",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 4541.47869873046875, 751.43316650390625, 143.0, 22.0 ],
					"text" : "s synth_bass-amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.329412, 0.478431, 0.0 ],
					"blinkcolor" : [ 1.0, 1.0, 0.4, 1.0 ],
					"id" : "obj-23",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4211.97869873046875, 396.29168701171875, 26.75, 26.75 ],
					"presentation" : 1,
					"presentation_rect" : [ 887.097181532118157, 861.516693115234375, 14.0, 14.538461685180664 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.329412, 0.478431, 0.0 ],
					"blinkcolor" : [ 1.0, 1.0, 0.4, 1.0 ],
					"id" : "obj-22",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3996.97869873046875, 396.29168701171875, 26.75, 26.75 ],
					"presentation" : 1,
					"presentation_rect" : [ 828.469187418620095, 861.516693115234375, 14.0, 14.538461685180664 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-21",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4211.97869873046875, 460.75, 70.0, 22.0 ],
					"text" : "loadmess 0"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-20",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4003.97869873046875, 460.75, 70.0, 22.0 ],
					"text" : "loadmess 0"
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-15",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 3847.0, 932.75, 89.0, 19.0 ],
					"text" : "s antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.329412, 0.478431, 0.0 ],
					"blinkcolor" : [ 1.0, 1.0, 0.4, 1.0 ],
					"id" : "obj-7",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3301.97869873046875, 340.29168701171875, 26.75, 26.75 ],
					"presentation" : 1,
					"presentation_rect" : [ 675.241256713867188, 825.993804931640625, 14.0, 14.538461685180664 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-6",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 38.882331000434078, 792.506195068359375, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 43.849846733940922, 801.0, 124.0, 20.0 ],
					"text" : "-----------------------------"
				}

			}
, 			{
				"box" : 				{
					"fontsize" : 9.0,
					"id" : "obj-1053",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ -2.671905517578125, 795.236964225769043, 120.0, 17.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 7.849846733940922, 822.5, 31.0, 17.0 ],
					"text" : "reset "
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.329412, 0.478431, 0.0 ],
					"blinkcolor" : [ 1.0, 1.0, 0.4, 1.0 ],
					"id" : "obj-1052",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2339.5, -43.75, 26.75, 26.75 ],
					"presentation" : 1,
					"presentation_rect" : [ 438.734405517578125, 825.993804931640625, 14.0, 14.538461685180664 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1049",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2848.5, 436.5, 125.0, 22.0 ],
					"text" : "s randbuf_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1048",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2603.0, 436.5, 113.0, 22.0 ],
					"text" : "s synth_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1047",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2435.9583740234375, 436.5, 128.0, 22.0 ],
					"text" : "s ribattuto_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1046",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2236.6590576171875, 429.0, 127.0, 22.0 ],
					"text" : "s warping_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1045",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2848.5, 106.75, 87.0, 22.0 ],
					"text" : "r randbuf_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1044",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2683.5, 106.75, 75.0, 22.0 ],
					"text" : "r synth_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1043",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2518.9583740234375, 114.75, 89.0, 22.0 ],
					"text" : "r ribattuto_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1042",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2358.5, 114.75, 88.0, 22.0 ],
					"text" : "r warping_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1041",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2159.6590576171875, 184.75, 111.0, 22.0 ],
					"text" : "s db12_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1040",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1987.6590576171875, 63.0, 73.0, 22.0 ],
					"text" : "r db12_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1039",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1854.4583740234375, 205.0, 86.0, 22.0 ],
					"text" : "r server6_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1038",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1619.0, 214.0, 86.0, 22.0 ],
					"text" : "r server4_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1037",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1930.0, 240.0, 86.0, 22.0 ],
					"text" : "r server6_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1036",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1684.0, 314.5, 86.0, 22.0 ],
					"text" : "r server4_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1035",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1320.0, 243.5, 86.0, 22.0 ],
					"text" : "r server3_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1034",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1787.9583740234375, -39.0, 86.0, 22.0 ],
					"text" : "r server2_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1033",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1385.0, -24.0, 86.0, 22.0 ],
					"text" : "r server1_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1032",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1973.0, 456.0, 125.0, 22.0 ],
					"text" : "s server6_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1031",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1664.5, 493.5, 125.0, 22.0 ],
					"text" : "s server4_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1030",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1467.9583740234375, 493.5, 125.0, 22.0 ],
					"text" : "s server3_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1029",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1776.9583740234375, 84.0, 125.0, 22.0 ],
					"text" : "s server2_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-494",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1338.4583740234375, 112.0, 125.0, 22.0 ],
					"text" : "s server1_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1028",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 368.550530327690922, 841.5, 58.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 351.26751708984375, 839.5, 38.0, 20.0 ],
					"text" : "notes"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1027",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 329.932861328125, 821.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 337.342529296875, 821.0, 61.0, 20.0 ],
					"text" : "Server3-6"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1026",
					"linecount" : 2,
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 724.5, 318.0, 98.0, 33.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 745.701307508680657, 184.5, 185.3958740234375, 20.0 ],
					"text" : "cacher avant la performance",
					"textcolor" : [ 1.0, 0.196078431372549, 0.196078431372549, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1025",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1502.0, 118.0, 169.0, 22.0 ],
					"text" : "prepend setvar $server1_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1024",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 937.597181532118157, 557.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 207.882331000434078, 801.0, 124.0, 20.0 ],
					"text" : "-----------------------------"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1022",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 937.597181532118157, 423.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 167.689337836371578, 801.0, 54.0, 20.0 ],
					"text" : "HOA"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1021",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 937.597181532118157, 535.21453857421875, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 277.882331000434078, 821.0, 54.0, 20.0 ],
					"text" : "Server6"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-995",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1854.4583740234375, 377.5, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-1015",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1794.4583740234375, 429.0, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 274.550530327690922, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1018",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1791.9583740234375, 249.0, 58.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 279.5, 839.5, 38.0, 20.0 ],
					"text" : "notes"
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-1019",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1795.9583740234375, 278.0, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 289.550530327690922, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.0, 1.0, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1020",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1794.4583740234375, 464.79168701171875, 169.0, 22.0 ],
					"text" : "prepend setvar $server6_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-640",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 937.597181532118157, 513.21453857421875, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 218.36993408203125, 821.0, 54.0, 20.0 ],
					"text" : "Server4"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-629",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1669.4583740234375, 377.5, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-633",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1609.4583740234375, 429.0, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 220.36993408203125, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-635",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1606.9583740234375, 249.0, 58.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 226.86993408203125, 839.5, 37.0, 20.0 ],
					"text" : "synth"
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-638",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1610.9583740234375, 278.0, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 233.36993408203125, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.0, 1.0, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-639",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1609.4583740234375, 464.79168701171875, 169.0, 22.0 ],
					"text" : "prepend setvar $server4_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-611",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 937.597181532118157, 491.21453857421875, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 159.189337836371578, 821.0, 54.0, 20.0 ],
					"text" : "Server3"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-564",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 937.597181532118157, 469.21453857421875, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 98.092156304253422, 821.0, 54.0, 20.0 ],
					"text" : "Server2"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-559",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 937.597181532118157, 446.5, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 43.849846733940922, 821.0, 54.0, 20.0 ],
					"text" : "Server1 "
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-558",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2508.4583740234375, 391.29168701171875, 99.0, 22.0 ],
					"text" : "prepend ribattuto"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-964",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2326.9583740234375, 387.29168701171875, 98.0, 22.0 ],
					"text" : "prepend warping"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1014",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1174.0, -94.0, 157.0, 22.0 ],
					"text" : "prepend setvar $samp_instr"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1013",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 855.0, 235.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_linecount" : 2,
					"presentation_rect" : [ 783.9583740234375, 116.0, 59.0, 33.0 ],
					"text" : "reload vezer"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1001",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1449.0, 648.26788330078125, 198.0, 22.0 ],
					"text" : "prepend setvar $refresh_vezer2nim"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1004",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"patching_rect" : [ 1449.0, 618.0, 22.0, 22.0 ],
					"text" : "t 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1012",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1447.0, 541.0, 64.0, 64.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 738.0, 105.5, 42.0, 42.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-983",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1240.9583740234375, -39.0, 165.0, 22.0 ],
					"text" : "prepend setvar $tempo_bach"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1011",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 625.375, 1286.0, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1009",
					"maxclass" : "textedit",
					"numinlets" : 1,
					"numoutlets" : 4,
					"outlettype" : [ "", "int", "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 440.75, 1276.5, 100.0, 50.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 386.158843994140625, 760.514495849609375, 330.449600219726562, 22.0 ],
					"text" : "bajo_mes418"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1006",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "int", "" ],
					"patching_rect" : [ 588.83331298828125, 1212.0, 63.0, 22.0 ],
					"text" : "unpack i s"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1010",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1320.0, -156.0, 38.0, 20.0 ],
					"text" : "vezer"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1008",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1255.9583740234375, -133.0, 71.0, 22.0 ],
					"text" : "fromsymbol"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1007",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1219.9583740234375, -160.0, 97.0, 22.0 ],
					"text" : "udpreceive 7401"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-994",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1314.9583740234375, 676.26788330078125, 172.0, 22.0 ],
					"text" : "prepend setvar $refresh_samp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-993",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"patching_rect" : [ 1314.9583740234375, 646.0, 22.0, 22.0 ],
					"text" : "t 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-991",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 855.0, 260.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 675.3245849609375, 120.0, 59.0, 20.0 ],
					"text" : "refresh sf"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-992",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1312.9583740234375, 569.0, 64.0, 64.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 630.3245849609375, 105.5, 42.0, 42.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 18.0,
					"id" : "obj-1005",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 219.0, 297.0, 180.0, 29.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 706.5625, 32.5, 159.0, 29.0 ],
					"text" : "notas_electronicas"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1002",
					"maxclass" : "number",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1305.0, -94.0, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-997",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1134.0, -61.0, 135.0, 22.0 ],
					"text" : "prepend setvar $electro"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-1000",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1083.0, 372.0, 131.0, 22.0 ],
					"text" : "prepend setvar $of_gui"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-999",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1113.0, 331.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_linecount" : 2,
					"presentation_rect" : [ 886.9583740234375, 115.5, 54.0, 33.0 ],
					"text" : "visual sources"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-998",
					"maxclass" : "toggle",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1083.0, 336.0, 24.0, 24.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 850.9583740234375, 115.5, 34.0, 34.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-990",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 573.6170654296875, 469.21453857421875, 88.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 567.0, 241.270706176757812, 37.0, 20.0 ],
					"text" : "notify",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.717647, 0.227451, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.0, 0.299738, 1.0, 1.0 ],
					"id" : "obj-989",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.898039, 1.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 125.0, 996.91668701171875, 39.5, 39.5 ],
					"presentation" : 1,
					"presentation_rect" : [ 532.25, 235.479034423828125, 31.583343505859375, 31.583343505859375 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-987",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 84.699996948242188, 1108.74993896484375, 141.0, 22.0 ],
					"text" : "prepend setvar $notify_5"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-988",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 88.75, 1331.0, 2794.0, 22.0 ],
					"text" : "selecteditems \"1642 play_env_trig_alto_mes_86_n group_hoa1 server1\" \"1597 play_env_trig_cb_mes78_4 group_hoa1 server1\" \"1725 play_env_trig_cb_mes86 group_hoa1 server1\" \"1660 play_env_trig_cb_mes_86_n group_hoa1 server1\" \"1559 play_env_trig_vlc_mes78 group_hoa1 server1\" \"1631 play_env_trig_vlc_mes78_4 group_hoa1 server1\" \"1648 play_env_trig_vlc_mes_86_n group_hoa1 server1\" \"1609 play_env_trig_vln_mes78_4 group_hoa1 server1\" \"1654 play_env_trig_vln_mes_86_n group_hoa1 server1\""
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-986",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 583.5, 209.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 582.5, 120.0, 44.0, 20.0 ],
					"text" : "stop sf"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-985",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"patching_rect" : [ 1174.0, 646.0, 22.0, 22.0 ],
					"text" : "t 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-966",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1140.0, 684.26788330078125, 159.0, 22.0 ],
					"text" : "prepend setvar $stop_sfplay"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-984",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1174.0, 571.0, 64.0, 64.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 537.5, 105.5, 42.0, 42.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-982",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 594.83331298828125, 1181.0, 71.0, 22.0 ],
					"text" : "fromsymbol"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-981",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 584.33331298828125, 1257.0, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-980",
					"maxclass" : "textedit",
					"numinlets" : 1,
					"numoutlets" : 4,
					"outlettype" : [ "", "int", "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 463.0, 1224.5, 100.0, 50.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 338.5, 760.514495849609375, 44.291656494140625, 22.0 ],
					"text" : "426"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-979",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1009.0, 807.4761962890625, 168.0, 22.0 ],
					"text" : "prepend setvar $reset_buffers"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-978",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 855.0, 282.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_linecount" : 2,
					"presentation_rect" : [ 994.520087348090328, 115.5, 70.0, 33.0 ],
					"text" : "reset buffers"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-977",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1051.0, 694.20831298828125, 64.0, 64.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 948.777777777777828, 105.5, 41.5, 41.5 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-972",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 962.0, -39.0, 22.0, 22.0 ],
					"text" : "t b"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 24.0,
					"format" : 6,
					"id" : "obj-975",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 767.0, 175.5, 90.0, 35.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 764.080298529730953, 272.264495849609375, 90.0, 35.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.968627, 1.0, 0.360784, 1.0 ],
					"bgfillcolor_autogradient" : 0.79,
					"bgfillcolor_color" : [ 0.2, 0.2, 0.2, 1 ],
					"bgfillcolor_color1" : [ 0.968627, 1.0, 0.360784, 1.0 ],
					"bgfillcolor_color2" : [ 0.685, 0.685, 0.685, 1.0 ],
					"bgfillcolor_type" : "gradient",
					"fontname" : "Arial",
					"fontsize" : 10.0,
					"gradient" : 0,
					"id" : "obj-976",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 721.5, 145.0, 52.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 764.080298529730953, 315.764495849609375, 52.0, 20.0 ],
					"text" : "tempo $1",
					"textcolor" : [ 0.0, 0.0, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-974",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 455.833343505859375, 241.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 490.234405517578125, 184.5, 61.0, 20.0 ],
					"text" : "RT tempo"
				}

			}
, 			{
				"box" : 				{
					"fontsize" : 18.0,
					"format" : 6,
					"id" : "obj-973",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 211.25, 404.0, 64.0, 29.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 422.5677490234375, 184.5, 64.0, 29.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-971",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 605.407913208007812, 949.0, 124.75, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 607.407913208007812, 723.59783935546875, 113.0, 20.0 ],
					"text" : "Reconstruction tree",
					"textcolor" : [ 0.996078431372549, 0.996078431372549, 0.996078431372549, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.913725, 0.913725, 0.0 ],
					"blinkcolor" : [ 1.0, 0.89, 0.09, 1.0 ],
					"id" : "obj-962",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.713726, 0.713726, 0.713726, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1803.9583740234375, 1024.0, 43.0, 43.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 577.5, 718.431167602539062, 30.333343505859375, 30.333343505859375 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-126",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1690.4583740234375, 1149.5, 178.0, 22.0 ],
					"text" : "prepend setvar $reconstruct_ob"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-970",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 1484.9583740234375, 1072.0, 48.0, 22.0 ],
					"text" : "del 300",
					"varname" : "3komp[2]"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-460",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 984.0, 1203.0, 71.0, 22.0 ],
					"text" : "fromsymbol"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-969",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 983.75, 1172.0, 47.0, 22.0 ],
					"text" : "zl iter 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-968",
					"linecount" : 2,
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 688.20831298828125, 1191.0, 122.0, 35.0 ],
					"text" : "\"102 track_hoa1\" \"106 track_hoa2\""
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-967",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 560.83331298828125, 1135.5, 121.0, 22.0 ],
					"text" : "route selecteditems"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-965",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 816.5, 1197.5, 142.0, 22.0 ],
					"text" : "\"426 bajo_mes418\""
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-961",
					"linecount" : 2,
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 472.0, 1064.0, 50.0, 35.0 ],
					"text" : "selecteditems"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-960",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4511.47869873046875, 467.68316650390625, 104.0, 22.0 ],
					"text" : "r synth_bass-amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-958",
					"maxclass" : "live.line",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 757.7576904296875, 618.0, 145.0, 5.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 36.349846733940922, 877.0, 395.316809760199703, 9.45831298828125 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-624",
					"maxclass" : "live.line",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 320.0, 240.0, 145.0, 5.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 414.0625, 877.0, 635.457587348090328, 14.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-951",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 68.5, 28.0, 51.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 380.46710205078125, 99.0, 51.0, 22.0 ],
					"text" : "suivi $1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-953",
					"maxclass" : "toggle",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 68.5, 3.0, 20.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 380.46710205078125, 74.0, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-956",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1024.0, -86.0, 94.0, 22.0 ],
					"text" : "prepend symbol"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-952",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1024.0, -156.0, 97.0, 22.0 ],
					"text" : "udpreceive 7400"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-950",
					"maxclass" : "newobj",
					"numinlets" : 5,
					"numoutlets" : 5,
					"outlettype" : [ "", "", "", "", "" ],
					"patching_rect" : [ 1024.0, -119.0, 220.0, 22.0 ],
					"text" : "route /event /electro /tempo /samp_instr"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-948",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 811.0, -145.0, 111.0, 22.0 ],
					"text" : "prepend /cue_label"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-947",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 811.0, -95.0, 158.0, 22.0 ],
					"text" : "udpsend 192.168.1.30 2323"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-930",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 501.33331298828125, 157.0, 54.0, 22.0 ],
					"text" : "deferlow"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-887",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 4778.97869873046875, 512.95831298828125, 160.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 948.777777777777828, 840.756195068359375, 44.0, 20.0 ],
					"text" : "synth2"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-880",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 4307.97869873046875, 736.95831298828125, 48.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 901.969187418620095, 840.756195068359375, 30.0, 20.0 ],
					"text" : "FX"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-873",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 4071.97869873046875, 747.95831298828125, 160.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 828.469187418620095, 840.756195068359375, 59.0, 20.0 ],
					"text" : "ProcHOA"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-866",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 3805.97869873046875, 736.95831298828125, 160.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 764.080298529730953, 840.756195068359375, 63.0, 20.0 ],
					"text" : "waveshap"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-859",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 3554.97869873046875, 736.95831298828125, 160.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 696.260894775390625, 840.756195068359375, 64.0, 20.0 ],
					"text" : "notasHOA"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-852",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 3303.97869873046875, 736.95831298828125, 160.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 650.260894775390625, 840.756195068359375, 43.25, 20.0 ],
					"text" : "notas"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-885",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 4922.47869873046875, 700.75, 90.0, 22.0 ],
					"text" : "s synth2_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-878",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 4176.47869873046875, 700.75, 100.0, 22.0 ],
					"text" : "s FX_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-871",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 3879.47869873046875, 700.75, 135.0, 22.0 ],
					"text" : "s ProcHOA_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-864",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 3635.47869873046875, 709.75, 155.0, 22.0 ],
					"text" : "s waveshaping_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-857",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 3383.47869873046875, 709.75, 140.0, 22.0 ],
					"text" : "s notasHOA_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-850",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 3114.47869873046875, 717.75, 114.0, 22.0 ],
					"text" : "s notas_amp_lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-889",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4826.43707275390625, 787.75, 91.0, 22.0 ],
					"text" : "prepend synth2"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-882",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4315.43707275390625, 787.75, 65.0, 22.0 ],
					"text" : "prepend fx"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-875",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4052.47869873046875, 787.75, 105.0, 22.0 ],
					"text" : "prepend procHOA"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-868",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3813.43707275390625, 787.75, 126.0, 22.0 ],
					"text" : "prepend waveshaping"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-861",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3562.43707275390625, 787.75, 111.0, 22.0 ],
					"text" : "prepend notasHOA"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-854",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3311.43707275390625, 787.75, 85.0, 22.0 ],
					"text" : "prepend notas"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-886",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4841.47869873046875, 504.25, 81.0, 22.0 ],
					"text" : "r synth2_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-879",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4315.47869873046875, 504.25, 61.0, 22.0 ],
					"text" : "r FX_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-872",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4064.47869873046875, 504.25, 97.0, 22.0 ],
					"text" : "r ProcHOA_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-865",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3813.47869873046875, 504.25, 116.0, 22.0 ],
					"text" : "r waveshaping_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-858",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3562.47869873046875, 504.25, 101.0, 22.0 ],
					"text" : "r notasHOA_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-851",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3311.47869873046875, 504.25, 75.0, 22.0 ],
					"text" : "r notas_amp"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-883",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4826.43707275390625, 694.75, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 942.527777777777828, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-884",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4830.97869873046875, 543.5, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 957.527777777777828, 861.516693115234375, 20.0, 126.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.847058823529412, 0.486274509803922, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-888",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4892.97869873046875, 633.95831298828125, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-876",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4300.43707275390625, 694.75, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 887.969187418620095, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-877",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4305.97869873046875, 543.5, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 901.969187418620095, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 1.0, 0.0, 0.070588235294118, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-881",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4366.97869873046875, 633.95831298828125, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-869",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4052.47869873046875, 700.75, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 834.969187418620095, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-870",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4053.97869873046875, 543.5, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 847.969187418620095, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 1.0, 0.0, 0.070588235294118, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-874",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4115.97869873046875, 633.95831298828125, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-862",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3798.43707275390625, 694.75, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 760.580298529730953, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-863",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3802.97869873046875, 543.5, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 774.830298529730953, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.956862745098039, 0.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-867",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3864.97869873046875, 633.95831298828125, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-855",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3547.43707275390625, 694.75, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 704.969187418620095, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-856",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3551.97869873046875, 543.5, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 718.260894775390625, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.956862745098039, 0.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-860",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3613.97869873046875, 633.95831298828125, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-848",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3297.43707275390625, 694.75, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 650.260894775390625, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-849",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3302.97869873046875, 543.5, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 662.302520751953125, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.956862745098039, 0.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-853",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3362.97869873046875, 633.95831298828125, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-799",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3297.43707275390625, 405.75, 70.0, 22.0 ],
					"text" : "loadmess 0"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-778",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3847.0, 894.275146484375, 188.0, 22.0 ],
					"text" : "prepend setvar $control_fromMax"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-657",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2326.9583740234375, 339.29168701171875, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 409.5625, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-658",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2338.5, 189.04168701171875, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 420.5625, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.0, 0.443137254901961, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-661",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2322.5, 154.5, 160.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 402.0625, 840.506195068359375, 57.0, 20.0 ],
					"text" : "warping"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-662",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2393.5, 278.5, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-663",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2539.5, 519.29168701171875, 188.0, 22.0 ],
					"text" : "prepend setvar $control_fromMax"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-644",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2093.6590576171875, 184.75, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 343.65045166015625, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-651",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2093.6590576171875, 33.5, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 356.19207763671875, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.0, 1.0, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-654",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2078.6590576171875, 4.0, 47.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 346.76751708984375, 799.0, 47.0, 20.0 ],
					"text" : "D&B12"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-655",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2159.6590576171875, 92.5, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-656",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2093.6590576171875, 223.0, 155.0, 22.0 ],
					"text" : "prepend setvar $db12_amp"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-650",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1690.4583740234375, 77.75, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 100.592156304253422, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-649",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1697.0, -72.5, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 115.592156304253422, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.0, 1.0, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-637",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1701.9583740234375, -101.25, 160.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 115.592156304253422, 839.5, 19.0, 20.0 ],
					"text" : "sf"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-643",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1757.0, 16.95831298828125, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-646",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1690.4583740234375, 112.0, 169.0, 22.0 ],
					"text" : "prepend setvar $server2_amp"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-641",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1491.4583740234375, 377.5, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-642",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1431.4583740234375, 429.0, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 163.46710205078125, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-631",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1556.0, 31.75, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-632",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1502.0, 72.0, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 36.349846733940922, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-464",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 4478.47869873046875, 518.3914794921875, 131.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 997.340705023871578, 839.5, 68.0, 20.0 ],
					"text" : "synth-bass"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-468",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4566.47869873046875, 625.3914794921875, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-472",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4509.97869873046875, 705.18316650390625, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 999.340705023871578, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-476",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 4511.47869873046875, 545.93316650390625, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 1012.340705023871578, 862.972036361694336, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.886274509803922, 1.0, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-610",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 4489.97869873046875, 792.0, 79.0, 22.0 ],
					"text" : "prepend bajo"
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-593",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1955.0, 323.75, 89.0, 19.0 ],
					"text" : "s antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 18.0,
					"id" : "obj-590",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 159.833389282226562, 172.0, 180.0, 29.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 218.0, 92.0, 151.0, 29.0 ],
					"text" : "mix_groups_HOA"
				}

			}
, 			{
				"box" : 				{
					"fontsize" : 9.0,
					"id" : "obj-589",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1224.9583740234375, 388.0, 120.0, 17.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 414.0625, 824.993804931640625, 32.0, 17.0 ],
					"text" : "reset "
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.329412, 0.478431, 0.0 ],
					"blinkcolor" : [ 1.0, 1.0, 0.4, 1.0 ],
					"id" : "obj-557",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.0, 0.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2122.0, -145.0, 26.75, 26.75 ],
					"presentation" : 1,
					"presentation_rect" : [ 32.849846733940922, 823.993804931640625, 14.0, 14.538461685180664 ]
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-587",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2539.5, 593.75, 89.0, 19.0 ],
					"text" : "s antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-560",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2498.1590576171875, 152.275146484375, 131.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 465.5625, 840.506195068359375, 52.0, 20.0 ],
					"text" : "ribattuto"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-561",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2592.4583740234375, 321.80029296875, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-562",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2508.4583740234375, 345.04168701171875, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 465.5625, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-563",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2509.9583740234375, 191.775146484375, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 478.5625, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.0, 0.443137254901961, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-528",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2358.5, 30.79168701171875, 70.0, 22.0 ],
					"text" : "loadmess 0"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-501",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 400.75, 58.0, 48.0, 22.0 ],
					"text" : "del 150"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-500",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "bang", "bang" ],
					"patching_rect" : [ 400.75, 21.0, 65.0, 22.0 ],
					"text" : "onebang 1"
				}

			}
, 			{
				"box" : 				{
					"fontsize" : 24.0,
					"id" : "obj-489",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 0,
					"patcher" : 					{
						"fileversion" : 1,
						"appversion" : 						{
							"major" : 8,
							"minor" : 5,
							"revision" : 3,
							"architecture" : "x64",
							"modernui" : 1
						}
,
						"classnamespace" : "box",
						"rect" : [ 34.0, 100.0, 1660.0, 983.0 ],
						"bglocked" : 0,
						"openinpresentation" : 0,
						"default_fontsize" : 12.0,
						"default_fontface" : 0,
						"default_fontname" : "Arial",
						"gridonopen" : 1,
						"gridsize" : [ 15.0, 15.0 ],
						"gridsnaponopen" : 1,
						"objectsnaponopen" : 1,
						"statusbarvisible" : 2,
						"toolbarvisible" : 1,
						"lefttoolbarpinned" : 0,
						"toptoolbarpinned" : 0,
						"righttoolbarpinned" : 0,
						"bottomtoolbarpinned" : 0,
						"toolbars_unpinned_last_save" : 0,
						"tallnewobj" : 0,
						"boxanimatetime" : 200,
						"enablehscroll" : 1,
						"enablevscroll" : 1,
						"devicewidth" : 0.0,
						"description" : "",
						"digest" : "",
						"tags" : "",
						"style" : "",
						"subpatcher_template" : "",
						"assistshowspatchername" : 0,
						"boxes" : [ 							{
								"box" : 								{
									"id" : "obj-885",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 2247.0416259765625, 342.5, 88.0, 22.0 ],
									"text" : "r synth2_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-886",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 1572.0, 215.0, 83.0, 22.0 ],
									"text" : "s synth2_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-24",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 2095.5, 342.5, 141.0, 22.0 ],
									"text" : "r synth_bass-amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-960",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 1460.0, 215.0, 106.0, 22.0 ],
									"text" : "s synth_bass-amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-878",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1972.0, 342.5, 98.0, 22.0 ],
									"text" : "r FX_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-871",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1832.0, 342.5, 133.0, 22.0 ],
									"text" : "r ProcHOA_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-879",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 1389.0, 215.0, 63.0, 22.0 ],
									"text" : "s FX_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-872",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 1285.0, 215.0, 99.0, 22.0 ],
									"text" : "s ProcHOA_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-864",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1670.0, 342.75, 153.0, 22.0 ],
									"text" : "r waveshaping_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-857",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1522.0, 342.75, 138.0, 22.0 ],
									"text" : "r notasHOA_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-850",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1400.0, 342.75, 112.0, 22.0 ],
									"text" : "r notas_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-865",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 1164.6590576171875, 215.0, 118.0, 22.0 ],
									"text" : "s waveshaping_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-858",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 1059.6590576171875, 215.0, 103.0, 22.0 ],
									"text" : "s notasHOA_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-851",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 978.6590576171875, 215.0, 77.0, 22.0 ],
									"text" : "s notas_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1049",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1148.0, 342.75, 125.0, 22.0 ],
									"text" : "r randbuf_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1048",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1278.0, 342.75, 113.0, 22.0 ],
									"text" : "r synth_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1047",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1018.0, 342.75, 128.0, 22.0 ],
									"text" : "r ribattuto_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1046",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 876.5, 343.0, 127.0, 22.0 ],
									"text" : "r warping_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1045",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 806.0, 215.0, 89.0, 22.0 ],
									"text" : "s randbuf_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1044",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 897.0, 215.0, 77.0, 22.0 ],
									"text" : "s synth_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1043",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 706.4583740234375, 215.0, 91.0, 22.0 ],
									"text" : "s ribattuto_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1042",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 607.666666666666629, 215.0, 90.0, 22.0 ],
									"text" : "s warping_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1041",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 754.6590576171875, 343.0, 109.0, 22.0 ],
									"text" : "r db12_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1040",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 525.666666666666629, 215.0, 75.0, 22.0 ],
									"text" : "s db12_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-3",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 65.0, 391.0, 100.0, 22.0 ],
									"text" : "prepend /Fader/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1032",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 626.5, 343.0, 125.0, 22.0 ],
									"text" : "r server6_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1031",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 484.5, 343.0, 125.0, 22.0 ],
									"text" : "r server4_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1030",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 346.5, 343.0, 125.0, 22.0 ],
									"text" : "r server3_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1029",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 205.5, 343.0, 125.0, 22.0 ],
									"text" : "r server2_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-494",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 65.0, 343.0, 125.0, 22.0 ],
									"text" : "r server1_amp_lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1039",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 433.666666666666686, 215.0, 88.000000000000057, 22.0 ],
									"text" : "s server6_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1038",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 341.666666666666686, 215.0, 88.0, 22.0 ],
									"text" : "s server4_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1035",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 249.666666666666686, 215.0, 88.0, 22.0 ],
									"text" : "s server3_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1034",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 157.666666666666686, 215.0, 88.0, 22.0 ],
									"text" : "s server2_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1033",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 65.0, 215.0, 88.0, 22.0 ],
									"text" : "s server1_amp"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-56",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1198.0, 551.0, 99.0, 22.0 ],
									"text" : "r lemur_interface"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-54",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1192.0, 614.0, 105.0, 22.0 ],
									"text" : "prepend /interface"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-48",
									"maxclass" : "message",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 214.0, 118.0, 266.0, 22.0 ],
									"text" : "/Fader17/z 0."
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-47",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 615.0, 595.0, 122.0, 22.0 ],
									"text" : "r to_lemur_udp_send"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-38",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 2247.0416259765625, 397.0, 113.0, 22.0 ],
									"text" : "prepend /Fader17/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-37",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 2095.5, 397.0, 113.0, 22.0 ],
									"text" : "prepend /Fader16/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-36",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1972.0, 397.0, 113.0, 22.0 ],
									"text" : "prepend /Fader15/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-34",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1832.0, 397.0, 113.0, 22.0 ],
									"text" : "prepend /Fader14/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-35",
									"maxclass" : "comment",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 1038.0, 589.0, 56.0, 20.0 ],
									"text" : "to Lemur"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-32",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1670.0, 397.0, 113.0, 22.0 ],
									"text" : "prepend /Fader13/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-30",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1522.0, 397.0, 113.0, 22.0 ],
									"text" : "prepend /Fader12/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-28",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1400.0, 397.0, 112.0, 22.0 ],
									"text" : "prepend /Fader11/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-26",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1278.0, 397.0, 113.0, 22.0 ],
									"text" : "prepend /Fader10/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-11",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1148.0, 401.0, 107.0, 22.0 ],
									"text" : "prepend /Fader9/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-5",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 1012.5, 401.0, 107.0, 22.0 ],
									"text" : "prepend /Fader8/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-2",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 876.5, 401.0, 107.0, 22.0 ],
									"text" : "prepend /Fader7/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-22",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 754.6590576171875, 401.0, 107.0, 22.0 ],
									"text" : "prepend /Fader6/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-17",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 346.5, 391.0, 107.0, 22.0 ],
									"text" : "prepend /Fader3/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-20",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 205.5, 391.0, 107.0, 22.0 ],
									"text" : "prepend /Fader2/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-16",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 626.5, 391.0, 107.0, 22.0 ],
									"text" : "prepend /Fader5/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-10",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 484.5, 391.0, 107.0, 22.0 ],
									"text" : "prepend /Fader4/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-9",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 870.0, 629.0, 165.0, 22.0 ],
									"text" : "udpsend 192.168.1.122 8000"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-4",
									"maxclass" : "newobj",
									"numinlets" : 22,
									"numoutlets" : 22,
									"outlettype" : [ "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "", "" ],
									"patching_rect" : [ 65.0, 159.0, 1958.000000000000227, 22.0 ],
									"text" : "route /Fader/x /Fader2/x /Fader3/x /Fader4/x /Fader5/x /Fader6/x /Fader7/x /Fader8/x /Fader9/x /Fader10/x /Fader11/x /Fader12/x /Fader13/x /Fader14/x /Fader15/x /Fader16/x /Fader17/x /Fader18/x /Fader19/x /Fader20/x /Fader21/x"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 65.0, 78.0, 97.0, 22.0 ],
									"text" : "udpreceive 8005"
								}

							}
 ],
						"lines" : [ 							{
								"patchline" : 								{
									"destination" : [ "obj-4", 0 ],
									"order" : 1,
									"source" : [ "obj-1", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-48", 1 ],
									"order" : 0,
									"source" : [ "obj-1", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-10", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-20", 0 ],
									"source" : [ "obj-1029", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-17", 0 ],
									"source" : [ "obj-1030", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-10", 0 ],
									"source" : [ "obj-1031", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-16", 0 ],
									"source" : [ "obj-1032", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-22", 0 ],
									"source" : [ "obj-1041", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-2", 0 ],
									"source" : [ "obj-1046", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-5", 0 ],
									"source" : [ "obj-1047", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-26", 0 ],
									"source" : [ "obj-1048", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-11", 0 ],
									"source" : [ "obj-1049", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-11", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-16", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-17", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-2", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-20", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-22", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-37", 0 ],
									"source" : [ "obj-24", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-26", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-28", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-3", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-30", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-32", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-34", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-36", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-37", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-38", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-1033", 0 ],
									"source" : [ "obj-4", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-1034", 0 ],
									"source" : [ "obj-4", 1 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-1035", 0 ],
									"source" : [ "obj-4", 2 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-1038", 0 ],
									"source" : [ "obj-4", 3 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-1039", 0 ],
									"source" : [ "obj-4", 4 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-1040", 0 ],
									"source" : [ "obj-4", 5 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-1042", 0 ],
									"source" : [ "obj-4", 6 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-1043", 0 ],
									"source" : [ "obj-4", 7 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-1044", 0 ],
									"source" : [ "obj-4", 9 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-1045", 0 ],
									"source" : [ "obj-4", 8 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-851", 0 ],
									"source" : [ "obj-4", 10 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-858", 0 ],
									"source" : [ "obj-4", 11 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-865", 0 ],
									"source" : [ "obj-4", 12 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-872", 0 ],
									"source" : [ "obj-4", 13 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-879", 0 ],
									"source" : [ "obj-4", 14 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-886", 0 ],
									"source" : [ "obj-4", 16 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-960", 0 ],
									"source" : [ "obj-4", 15 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-47", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-3", 0 ],
									"source" : [ "obj-494", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-5", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-54", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-54", 0 ],
									"source" : [ "obj-56", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-28", 0 ],
									"source" : [ "obj-850", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-30", 0 ],
									"source" : [ "obj-857", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-32", 0 ],
									"source" : [ "obj-864", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-34", 0 ],
									"source" : [ "obj-871", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-36", 0 ],
									"source" : [ "obj-878", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-38", 0 ],
									"source" : [ "obj-885", 0 ]
								}

							}
 ]
					}
,
					"patching_rect" : [ 1714.5, 611.5, 147.0, 35.0 ],
					"saved_object_attributes" : 					{
						"description" : "",
						"digest" : "",
						"globalpatchername" : "",
						"tags" : ""
					}
,
					"text" : "p lemur"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-484",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2828.0, 158.5, 131.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 521.5625, 840.506195068359375, 50.0, 20.0 ],
					"text" : "randbuf"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-483",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2663.0, 152.275146484375, 131.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 581.1041259765625, 840.756195068359375, 42.0, 20.0 ],
					"text" : "synth"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-481",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2752.5, 272.29168701171875, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-482",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2681.0, 340.29168701171875, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 577.6041259765625, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-480",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2895.97869873046875, 272.29168701171875, 72.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-479",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2847.0, 340.29168701171875, 50.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 521.5625, 991.20831298828125, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-474",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2848.5, 185.0, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 531.5625, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.0, 0.443137254901961, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-475",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2821.5, 398.29168701171875, 97.0, 22.0 ],
					"text" : "prepend randbuf"
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-471",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2684.5, 189.04168701171875, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 587.1041259765625, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.0, 0.443137254901961, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-466",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2681.0, 398.29168701171875, 85.0, 22.0 ],
					"text" : "prepend synth"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-462",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 31.0, 336.0, 47.0, 22.0 ],
					"text" : "zl nth 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-458",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 130.0, 346.0, 94.0, 22.0 ],
					"text" : "prepend symbol"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-457",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 31.0, 314.0, 68.0, 22.0 ],
					"text" : "route /label"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-453",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 31.0, 289.0, 97.0, 22.0 ],
					"text" : "udpreceive 6557"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-436",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2093.6590576171875, -39.0, 70.0, 22.0 ],
					"text" : "loadmess 0"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-441",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1502.0, 197.0, 70.0, 22.0 ],
					"text" : "loadmess 0"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-440",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1498.5, -134.04168701171875, 70.0, 22.0 ],
					"text" : "loadmess 0"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-439",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1467.9583740234375, -109.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 51.808220757378422, 839.5, 23.583251953125, 20.0 ],
					"text" : " fx"
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-438",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1503.5, -82.25, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 51.808220757378422, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.0, 1.0, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-434",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1428.9583740234375, 249.0, 58.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 167.689337836371578, 839.5, 37.0, 20.0 ],
					"text" : "synth"
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-432",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1641.9583740234375, 557.75, 89.0, 19.0 ],
					"text" : "s antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"contdata" : 1,
					"id" : "obj-428",
					"maxclass" : "multislider",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1432.9583740234375, 278.0, 20.0, 140.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 175.36993408203125, 861.516693115234375, 20.0, 125.0 ],
					"setminmax" : [ -70.0, 10.0 ],
					"setstyle" : 1,
					"slidercolor" : [ 0.0, 1.0, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-429",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1431.4583740234375, 464.79168701171875, 169.0, 22.0 ],
					"text" : "prepend setvar $server3_amp"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-427",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 599.0, 513.0, 124.75, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 607.407913208007812, 691.264495849609375, 91.833343505859375, 20.0 ],
					"text" : "Stop all Tracks",
					"textcolor" : [ 0.996078431372549, 0.996078431372549, 0.996078431372549, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-426",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "float" ],
					"patching_rect" : [ 1563.0, 1045.0, 32.0, 22.0 ],
					"text" : "t 1.5"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.913725, 0.913725, 0.0 ],
					"blinkcolor" : [ 1.0, 0.89, 0.09, 1.0 ],
					"id" : "obj-425",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.713726, 0.713726, 0.713726, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1565.0, 975.6666259765625, 43.0, 43.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 577.5, 686.097824096679688, 30.333343505859375, 30.333343505859375 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-424",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1563.0, 1101.1666259765625, 158.0, 22.0 ],
					"text" : "prepend setvar $free_tracks"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-423",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 175.75, -24.0, 527.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 509.777777777777828, 74.0, 526.0, 22.0 ],
					"text" : "score /Users/josephfernandez/Documents/AntesCollider-dev/ESPRO_2023_30_mesures.asco.txt"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-420",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 255.0, -61.0, 443.0, 22.0 ],
					"text" : "score /Users/josephfernandez/Documents/AntesCollider-dev/Ali-verbera1.asco.txt"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-343",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 0,
					"patcher" : 					{
						"fileversion" : 1,
						"appversion" : 						{
							"major" : 8,
							"minor" : 5,
							"revision" : 3,
							"architecture" : "x64",
							"modernui" : 1
						}
,
						"classnamespace" : "box",
						"rect" : [ 649.0, 100.0, 931.0, 692.0 ],
						"bglocked" : 0,
						"openinpresentation" : 0,
						"default_fontsize" : 12.0,
						"default_fontface" : 0,
						"default_fontname" : "Arial",
						"gridonopen" : 1,
						"gridsize" : [ 15.0, 15.0 ],
						"gridsnaponopen" : 1,
						"objectsnaponopen" : 1,
						"statusbarvisible" : 2,
						"toolbarvisible" : 1,
						"lefttoolbarpinned" : 0,
						"toptoolbarpinned" : 0,
						"righttoolbarpinned" : 0,
						"bottomtoolbarpinned" : 0,
						"toolbars_unpinned_last_save" : 0,
						"tallnewobj" : 0,
						"boxanimatetime" : 200,
						"enablehscroll" : 1,
						"enablevscroll" : 1,
						"devicewidth" : 0.0,
						"description" : "",
						"digest" : "",
						"tags" : "",
						"style" : "",
						"subpatcher_template" : "",
						"assistshowspatchername" : 0,
						"boxes" : [ 							{
								"box" : 								{
									"id" : "obj-20",
									"linecount" : 2,
									"maxclass" : "message",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 320.0, 476.0, 50.0, 35.0 ],
									"text" : "contacts : 0"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-18",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 530.0, 428.0, 129.0, 22.0 ],
									"text" : "prepend setvar Sensel"
								}

							}
, 							{
								"box" : 								{
									"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-19",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 530.0, 482.0, 117.0, 22.0 ],
									"text" : "s antescofo-mess"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-17",
									"maxclass" : "newobj",
									"numinlets" : 2,
									"numoutlets" : 2,
									"outlettype" : [ "", "" ],
									"patching_rect" : [ 376.0, 457.0, 55.0, 22.0 ],
									"text" : "zl slice 5"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-12",
									"linecount" : 2,
									"maxclass" : "message",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 54.0, 547.0, 732.0, 35.0 ],
									"text" : "{ x : 30.5 y : 104.8 force : 27.875 area : 42. ellipse : { orientation : 69.3125 major_axis : 10.546875 minor_axis : 10.1875 } deltas : { x : 0. y : 0. force : 0. area : 42. } bounding_box : { min_x : 20. min_y : 110. max_x : 25. max_y : 115. } peak : { x : 25. y : 115. force : 2. } }"
								}

							}
, 							{
								"box" : 								{
									"attr" : "poll",
									"id" : "obj-27",
									"maxclass" : "attrui",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"parameter_enable" : 0,
									"patching_rect" : [ 376.0, 307.0, 150.0, 22.0 ]
								}

							}
, 							{
								"box" : 								{
									"attr" : "sensitivity",
									"id" : "obj-35",
									"maxclass" : "attrui",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"parameter_enable" : 0,
									"patching_rect" : [ 543.0, 307.0, 150.0, 22.0 ]
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 13.0,
									"id" : "obj-85",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 376.0, 419.0, 85.0, 23.0 ],
									"text" : "dict.serialize"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-8",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 376.0, 375.0, 43.0, 22.0 ],
									"text" : "sensel"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-16",
									"maxclass" : "number",
									"numinlets" : 1,
									"numoutlets" : 2,
									"outlettype" : [ "", "bang" ],
									"parameter_enable" : 0,
									"patching_rect" : [ 89.0, 288.0, 50.0, 22.0 ]
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-15",
									"maxclass" : "number",
									"numinlets" : 1,
									"numoutlets" : 2,
									"outlettype" : [ "", "bang" ],
									"parameter_enable" : 0,
									"patching_rect" : [ 89.0, 288.0, 50.0, 22.0 ]
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-7",
									"maxclass" : "newobj",
									"numinlets" : 3,
									"numoutlets" : 3,
									"outlettype" : [ "", "", "" ],
									"patching_rect" : [ 88.0, 244.0, 69.0, 22.0 ],
									"text" : "route 21 22"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-13",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 88.0, 244.0, 100.0, 22.0 ]
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-5",
									"maxclass" : "newobj",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 409.0, 198.0, 57.0, 22.0 ],
									"text" : "pack 0 0."
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-4",
									"maxclass" : "newobj",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "float" ],
									"patching_rect" : [ 452.0, 164.0, 31.0, 22.0 ],
									"text" : "float"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-1",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 2,
									"outlettype" : [ "int", "int" ],
									"patching_rect" : [ 404.0, 130.0, 67.0, 22.0 ],
									"text" : "unpack 0 0"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-10",
									"maxclass" : "number",
									"numinlets" : 1,
									"numoutlets" : 2,
									"outlettype" : [ "", "bang" ],
									"parameter_enable" : 0,
									"patching_rect" : [ 122.0, 324.0, 50.0, 22.0 ]
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-9",
									"maxclass" : "number",
									"numinlets" : 1,
									"numoutlets" : 2,
									"outlettype" : [ "", "bang" ],
									"parameter_enable" : 0,
									"patching_rect" : [ 89.0, 288.0, 50.0, 22.0 ]
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-6",
									"maxclass" : "message",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 143.0, 161.0, 50.0, 22.0 ],
									"text" : "25 0"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-3",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 223.0, 253.0, 169.0, 22.0 ],
									"text" : "prepend setvar $SpaceMouse"
								}

							}
, 							{
								"box" : 								{
									"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-14",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 223.0, 307.0, 117.0, 22.0 ],
									"text" : "s antescofo-mess"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-64",
									"maxclass" : "message",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 326.0, 70.0, 136.0, 22.0 ],
									"text" : "\"SpaceMouse Wireless\""
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-63",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 2,
									"outlettype" : [ "list", "" ],
									"patching_rect" : [ 223.0, 114.0, 32.5, 22.0 ],
									"text" : "hi"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-62",
									"maxclass" : "message",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 274.0, 70.0, 46.0, 22.0 ],
									"text" : "poll 10"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-2",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 183.0, 40.0, 25.0, 22.0 ],
									"text" : "r hi"
								}

							}
 ],
						"lines" : [ 							{
								"patchline" : 								{
									"destination" : [ "obj-4", 0 ],
									"source" : [ "obj-1", 1 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-5", 0 ],
									"source" : [ "obj-1", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-12", 1 ],
									"order" : 0,
									"source" : [ "obj-17", 1 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-18", 0 ],
									"order" : 1,
									"source" : [ "obj-17", 1 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-20", 1 ],
									"source" : [ "obj-17", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-19", 0 ],
									"source" : [ "obj-18", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-63", 0 ],
									"source" : [ "obj-2", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-8", 0 ],
									"source" : [ "obj-27", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-14", 0 ],
									"source" : [ "obj-3", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-8", 0 ],
									"source" : [ "obj-35", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-5", 1 ],
									"source" : [ "obj-4", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-3", 0 ],
									"source" : [ "obj-5", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-63", 0 ],
									"source" : [ "obj-62", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-3", 0 ],
									"order" : 0,
									"source" : [ "obj-63", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-6", 1 ],
									"order" : 1,
									"source" : [ "obj-63", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-7", 0 ],
									"order" : 2,
									"source" : [ "obj-63", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-63", 0 ],
									"source" : [ "obj-64", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-10", 0 ],
									"source" : [ "obj-7", 1 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-7", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-85", 0 ],
									"source" : [ "obj-8", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-17", 0 ],
									"source" : [ "obj-85", 0 ]
								}

							}
 ],
						"styles" : [ 							{
								"name" : "tap",
								"default" : 								{
									"fontname" : [ "Lato Light" ]
								}
,
								"parentstyle" : "",
								"multi" : 0
							}
 ]
					}
,
					"patching_rect" : [ 463.0, 260.0, 100.0, 22.0 ],
					"saved_object_attributes" : 					{
						"description" : "",
						"digest" : "",
						"globalpatchername" : "",
						"tags" : ""
					}
,
					"text" : "p SpaceMouse"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-352",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 381.932861328125, 480.5, 91.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 385.6170654296875, 255.264495849609375, 91.5, 20.0 ],
					"text" : "gui",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-351",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 194.932861328125, 481.21453857421875, 91.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 199.932861328125, 254.764495849609375, 91.5, 20.0 ],
					"text" : "gui",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-349",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 541.0, 83.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 537.5, 51.5, 87.0, 20.0 ],
					"text" : "score"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-341",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 353.932861328125, 361.75, 124.75, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 607.407913208007812, 662.181182861328125, 97.833343505859375, 20.0 ],
					"text" : "Stop all Groups",
					"textcolor" : [ 0.996078431372549, 0.996078431372549, 0.996078431372549, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.913725, 0.913725, 0.0 ],
					"blinkcolor" : [ 1.0, 0.89, 0.09, 1.0 ],
					"id" : "obj-340",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.713726, 0.713726, 0.713726, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1399.9583740234375, 976.6666259765625, 43.0, 43.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 577.5, 657.014511108398438, 30.333343505859375, 30.333343505859375 ]
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-331",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1130.45831298828125, 31.75, 89.0, 19.0 ],
					"text" : "s antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-313",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1274.0, 1005.0, 50.0, 22.0 ],
					"text" : "server3"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-299",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 230.0, 157.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 327.145828247070312, 191.5, 67.0, 20.0 ],
					"text" : "Next Event"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-323",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 551.0, 107.0, 150.0, 20.0 ],
					"text" : "playfrom Label"
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-322",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 417.0, 372.0, 89.0, 19.0 ],
					"text" : "s antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"allowdrag" : 0,
					"fontsize" : 17.0,
					"id" : "obj-321",
					"items" : [ "SC_multiserver_INIT", ",", "create_load_envs_buff", ",", "create_gran_buff", ",", "create_fft_buff", ",", "obj_rec_buff", ",", "obj_rec_pvoc", ",", "waveshaping_buffs", ",", "vezer_xml2nims", ",", "load_samples", ",", "load_pvoc", ",", "mix_groups_HOA", ",", "notas_electronicas", ",", "mes_1", ",", "mes_2", ",", "mes_3", ",", "mes_4", ",", "mes_5", ",", "mes_6", ",", "mes_7", ",", "mes_8", ",", "mes_9", ",", "mes_10", ",", "mes_11", ",", "mes_12", ",", "mes_13", ",", "mes_14", ",", "mes_15", ",", "mes_16", ",", "mes_17", ",", "mes_18", ",", "mes_19", ",", "mes_20", ",", "mes_21", ",", "mes_22", ",", "mes_23", ",", "mes_24", ",", "mes_25", ",", "mes_26", ",", "mes_27", ",", "mes_28", ",", "mes_29", ",", "mes_30", ",", "mes_31", ",", "mes_32", ",", "mes_33", ",", "mes_34", ",", "mes_35", ",", "mes_36", ",", "mes_37", ",", "mes_38", ",", "mes_39", ",", "mes_40", ",", "mes_41", ",", "mes_42", ",", "mes_43", ",", "mes_44", ",", "mes_45", ",", "mes_46", ",", "mes_47", ",", "mes_48", ",", "mes_49", ",", "mes_50", ",", "mes_51", ",", "mes_52", ",", "mes_53", ",", "mes_54", ",", "mes_55", ",", "mes_56", ",", "mes_57", ",", "mes_58", ",", "mes_59", ",", "mes_60", ",", "mes_61", ",", "mes_62", ",", "mes_63", ",", "mes_64", ",", "mes_65", ",", "mes_66", ",", "mes_67", ",", "mes_68", ",", "mes_69", ",", "mes_70", ",", "mes_71", ",", "mes_72", ",", "mes_73", ",", "mes_74", ",", "mes_75", ",", "mes_76", ",", "mes_77", ",", "mes_78", ",", "mes_79", ",", "mes_80", ",", "mes_81", ",", "mes_82", ",", "mes_83", ",", "mes_84", ",", "mes_85", ",", "mes_86", ",", "mes_87", ",", "mes_88", ",", "mes_89", ",", "mes_90", ",", "mes_91", ",", "mes_92", ",", "mes_93", ",", "mes_94", ",", "mes_95", ",", "mes_96", ",", "mes_97", ",", "mes_98", ",", "mes_99", ",", "mes_100", ",", "mes_101", ",", "mes_102", ",", "mes_103", ",", "mes_104", ",", "mes_105", ",", "mes_106", ",", "mes_107", ",", "mes_108", ",", "mes_109", ",", "mes_110", ",", "mes_111", ",", "mes_112", ",", "mes_113", ",", "mes_114", ",", "mes_115", ",", "mes_116", ",", "mes_117", ",", "mes_118", ",", "mes_119", ",", "mes_120", ",", "mes_121", ",", "mes_122", ",", "mes_123", ",", "mes_124", ",", "mes_125", ",", "mes_126", ",", "mes_127", ",", "mes_128", ",", "mes_129", ",", "mes_130", ",", "mes_131", ",", "mes_132", ",", "mes_133", ",", "mes_134", ",", "mes_135", ",", "mes_136", ",", "mes_137", ",", "mes_138", ",", "mes_139", ",", "mes_140", ",", "mes_141", ",", "mes_142", ",", "mes_143", ",", "mes_144", ",", "mes_145", ",", "mes_146", ",", "mes_147", ",", "mes_148", ",", "mes_149", ",", "mes_150", ",", "mes_151", ",", "mes_152", ",", "mes_153", ",", "mes_154", ",", "mes_155", ",", "mes_156", ",", "mes_157", ",", "mes_158", ",", "mes_159", ",", "mes_160", ",", "mes_161", ",", "mes_162", ",", "mes_163", ",", "mes_164", ",", "mes_165", ",", "mes_166", ",", "mes_167", ",", "mes_168", ",", "mes_169", ",", "mes_170", ",", "mes_171", ",", "mes_172", ",", "mes_173", ",", "mes_174", ",", "mes_175", ",", "mes_176", ",", "mes_177", ",", "mes_178", ",", "mes_179", ",", "mes_180", ",", "mes_181", ",", "mes_182", ",", "mes_183", ",", "mes_184", ",", "mes_185", ",", "mes_186", ",", "mes_187", ",", "mes_188", ",", "mes_189", ",", "mes_190", ",", "mes_191", ",", "mes_192", ",", "mes_193", ",", "mes_194", ",", "mes_195", ",", "mes_196", ",", "mes_197", ",", "mes_198", ",", "mes_199", ",", "mes_200", ",", "mes_201", ",", "mes_202", ",", "mes_203", ",", "mes_204", ",", "mes_205", ",", "mes_206", ",", "mes_207", ",", "mes_208", ",", "mes_209", ",", "mes_210", ",", "mes_211", ",", "mes_212", ",", "mes_213", ",", "mes_214", ",", "mes_215", ",", "mes_216", ",", "mes_217", ",", "mes_218", ",", "mes_219", ",", "mes_220", ",", "mes_221", ",", "mes_222", ",", "mes_223", ",", "mes_224", ",", "mes_225", ",", "mes_226", ",", "mes_227", ",", "mes_228", ",", "mes_229", ",", "mes_230", ",", "mes_231", ",", "mes_232", ",", "mes_233", ",", "mes_234", ",", "mes_235", ",", "mes_236", ",", "mes_237", ",", "mes_238", ",", "mes_239", ",", "mes_240", ",", "mes_241", ",", "mes_242", ",", "mes_243", ",", "mes_244", ",", "mes_245", ",", "mes_246", ",", "mes_247", ",", "mes_248", ",", "mes_249", ",", "mes_250", ",", "mes_251", ",", "mes_252", ",", "mes_253", ",", "mes_254", ",", "mes_255", ",", "mes_256", ",", "mes_257", ",", "mes_258", ",", "mes_259", ",", "mes_260", ",", "mes_261", ",", "mes_262", ",", "mes_263", ",", "mes_264", ",", "mes_265", ",", "mes_266", ",", "mes_267", ",", "mes_268", ",", "mes_269", ",", "mes_270", ",", "mes_271", ",", "mes_272", ",", "mes_273", ",", "mes_274", ",", "mes_275", ",", "mes_276", ",", "mes_277", ",", "mes_278", ",", "mes_279", ",", "mes_280", ",", "mes_281", ",", "mes_282", ",", "mes_283", ",", "mes_284", ",", "mes_285", ",", "mes_286", ",", "mes_287", ",", "mes_288", ",", "mes_289", ",", "mes_290", ",", "mes_291", ",", "mes_292", ",", "mes_293", ",", "mes_294", ",", "mes_295", ",", "mes_296", ",", "mes_297", ",", "mes_298", ",", "mes_299", ",", "mes_300", ",", "mes_301", ",", "mes_302", ",", "mes_303", ",", "mes_304", ",", "mes_305", ",", "mes_306", ",", "mes_307", ",", "mes_308", ",", "mes_309", ",", "mes_310", ",", "mes_311", ",", "mes_312", ",", "mes_313", ",", "mes_314", ",", "mes_315", ",", "mes_316", ",", "mes_317", ",", "mes_318", ",", "mes_319", ",", "mes_320", ",", "mes_321", ",", "mes_322", ",", "mes_323", ",", "mes_324", ",", "mes_325", ",", "mes_326", ",", "mes_327", ",", "mes_328", ",", "mes_329", ",", "mes_330", ",", "mes_331", ",", "mes_332", ",", "mes_333", ",", "mes_334", ",", "mes_335", ",", "mes_336", ",", "mes_337", ",", "mes_338", ",", "mes_339", ",", "mes_340", ",", "mes_341", ",", "mes_342", ",", "mes_343", ",", "mes_344", ",", "mes_345", ",", "mes_346", ",", "mes_347", ",", "mes_348", ",", "mes_349", ",", "mes_350", ",", "mes_351", ",", "mes_352", ",", "mes_353", ",", "mes_354", ",", "mes_355", ",", "mes_356", ",", "mes_357", ",", "mes_358", ",", "mes_359", ",", "mes_360", ",", "mes_361", ",", "mes_362", ",", "mes_363", ",", "mes_364", ",", "mes_365", ",", "mes_366", ",", "mes_367", ",", "mes_368", ",", "mes_369", ",", "mes_370", ",", "mes_371", ",", "mes_372", ",", "mes_373", ",", "mes_374", ",", "mes_375", ",", "mes_376", ",", "mes_377", ",", "mes_378", ",", "mes_379", ",", "mes_380", ",", "mes_381", ",", "mes_382", ",", "mes_383", ",", "mes_384", ",", "mes_385", ",", "mes_386", ",", "mes_387", ",", "mes_388", ",", "mes_389", ",", "mes_390", ",", "mes_391", ",", "mes_392", ",", "mes_393", ",", "mes_394", ",", "mes_395", ",", "mes_396", ",", "mes_397", ",", "mes_398", ",", "mes_399", ",", "mes_400", ",", "mes_401", ",", "mes_402", ",", "mes_403", ",", "mes_404", ",", "mes_405", ",", "mes_406", ",", "mes_407", ",", "mes_408", ",", "mes_409", ",", "mes_410", ",", "mes_411", ",", "mes_412", ",", "mes_413", ",", "mes_414", ",", "mes_415", ",", "mes_416", ",", "mes_417", ",", "mes_418", ",", "mes_419", ",", "mes_420", ",", "mes_421", ",", "mes_422", ",", "mes_423", ",", "mes_424", ",", "mes_425", ",", "mes_426", ",", "mes_427", ",", "mes_428", ",", "mes_429", ",", "mes_430", ",", "mes_431", ",", "mes_432", ",", "mes_433", ",", "mes_434", ",", "mes_435", ",", "mes_436", ",", "mes_437", ",", "mes_438", ",", "mes_439", ",", "mes_440", ",", "mes_441", ",", "mes_442", ",", "mes_443", ",", "mes_444", ",", "mes_445", ",", "mes_446", ",", "mes_447", ",", "mes_448", ",", "mes_449", ",", "mes_450", ",", "mes_451", ",", "mes_452", ",", "mes_453", ",", "mes_454", ",", "mes_455", ",", "mes_456", ",", "mes_457", ",", "mes_458", ",", "mes_459", ",", "mes_460", ",", "mes_461", ",", "mes_462", ",", "mes_463", ",", "mes_464", ",", "mes_465", ",", "mes_466", ",", "mes_467", ",", "mes_468", ",", "mes_469", ",", "mes_470", ",", "mes_471", ",", "mes_472", ",", "mes_473", ",", "mes_474", ",", "mes_475", ",", "mes_476", ",", "mes_477", ",", "mes_478", ",", "mes_479", ",", "mes_480", ",", "mes_481", ",", "mes_482", ",", "mes_483", ",", "mes_484", ",", "mes_485", ",", "mes_486", ",", "mes_487", ",", "mes_488", ",", "mes_489", ",", "mes_490", ",", "mes_491", ",", "mes_492", ",", "mes_493", ",", "mes_494", ",", "mes_495", ",", "mes_496", ",", "mes_497", ",", "mes_498", ",", "mes_499", ",", "mes_500", ",", "mes_501", ",", "mes_502", ",", "mes_503", ",", "mes_504", ",", "mes_505", ",", "mes_506", ",", "mes_507", ",", "mes_508", ",", "mes_509", ",", "mes_510", ",", "mes_511", ",", "mes_512", ",", "mes_513", ",", "mes_514", ",", "mes_515", ",", "mes_516", ",", "mes_517", ",", "mes_518", ",", "mes_519", ",", "mes_520", ",", "mes_521", ",", "mes_522" ],
					"maxclass" : "umenu",
					"numinlets" : 1,
					"numoutlets" : 3,
					"outlettype" : [ "int", "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 235.249999999999943, 205.5, 210.0, 27.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-312",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 428.0, 297.0, 97.0, 22.0 ],
					"text" : "playfromlabel $1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-199",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 198.0, 502.1011962890625, 65.0, 22.0 ],
					"text" : "route clear"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-164",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1413.4583740234375, 1177.0, 183.0, 22.0 ],
					"text" : "prepend setvar $stop_groups_all"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-336",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 375.75, 958.02374267578125, 55.0, 22.0 ],
					"text" : "zl slice 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-335",
					"linecount" : 2,
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 284.5, 955.0, 50.0, 35.0 ],
					"text" : "addsynth_m6"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-329",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 767.0, 493.1011962890625, 65.0, 22.0 ],
					"text" : "route clear"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-330",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 767.0, 439.2261962890625, 102.0, 22.0 ],
					"text" : "r synth_tracks_all"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-328",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1045.75, 976.6666259765625, 193.0, 22.0 ],
					"text" : "prepend setvar $current_tracks_all"
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-326",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1221.25, 1261.5, 89.0, 19.0 ],
					"text" : "s antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-325",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 1196.4583740234375, 1197.5, 195.0, 22.0 ],
					"text" : "prepend setvar $free_select_group"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-319",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 983.75, 1252.5, 190.0, 22.0 ],
					"text" : "prepend setvar $free_select_track"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-316",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 353.932861328125, 816.0, 48.0, 22.0 ],
					"text" : "pipe 20"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-315",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 84.699996948242188, 835.5, 48.0, 22.0 ],
					"text" : "pipe 20"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-314",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 225.833389282226562, 792.0, 48.0, 22.0 ],
					"text" : "pipe 20"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-309",
					"linecount" : 3,
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 848.0, 822.0, 50.0, 49.0 ],
					"text" : "426 bajo_mes418"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-300",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 785.0, 673.2261962890625, 107.0, 22.0 ],
					"text" : "r synth_groups_all"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-298",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 292.0, 502.1011962890625, 65.0, 22.0 ],
					"text" : "route clear"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-297",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 292.0, 469.2261962890625, 107.0, 22.0 ],
					"text" : "r synth_groups_all"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-296",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 949.7083740234375, 765.125, 100.0, 22.0 ],
					"text" : "r synth_test_max"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-295",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 911.0, 972.0, 29.5, 22.0 ],
					"text" : "1"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.717647, 0.227451, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.0, 0.299738, 1.0, 1.0 ],
					"id" : "obj-170",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.898039, 1.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 897.25, 909.99993896484375, 39.5, 39.5 ],
					"presentation" : 1,
					"presentation_rect" : [ 178.932861328125, 235.479034423828125, 19.300048828125, 19.300048828125 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-152",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 721.5, 1017.6666259765625, 198.0, 22.0 ],
					"text" : "prepend setvar $current_groups_all"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-137",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "clear" ],
					"patching_rect" : [ 115.0, 521.0, 47.0, 22.0 ],
					"text" : "t l clear"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-294",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 714.95831298828125, 598.0, 35.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 667.1666259765625, 334.264495849609375, 35.0, 22.0 ],
					"text" : "clear"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 16.0,
					"id" : "obj-293",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 546.6170654296875, 557.0, 116.0, 24.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 572.3245849609375, 334.264495849609375, 68.0, 24.0 ],
					"text" : "Synths",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 16.0,
					"id" : "obj-271",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 368.0, 557.0, 116.0, 24.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 371.6170654296875, 334.264495849609375, 116.0, 24.0 ],
					"text" : "Tracks",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-269",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 595.0, 587.58331298828125, 96.0, 22.0 ],
					"text" : "prepend append"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-268",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 599.5, 552.5, 80.0, 22.0 ],
					"text" : "r synths_max"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-258",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 397.5, 845.0, 71.0, 22.0 ],
					"text" : "fromsymbol"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-260",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 338.0, 891.66668701171875, 47.0, 22.0 ],
					"text" : "zl nth 2"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-264",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 3,
					"outlettype" : [ "int", "", "clear" ],
					"patching_rect" : [ 398.949990844726585, 761.5, 57.0, 22.0 ],
					"text" : "t 0 l clear"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-209",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 505.75, 604.25, 35.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 496.0, 334.264495849609375, 35.0, 22.0 ],
					"text" : "clear"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-201",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 693.1666259765625, 825.91668701171875, 46.0, 22.0 ],
					"text" : "group1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-286",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 922.7083740234375, 715.20831298828125, 80.0, 22.0 ],
					"text" : "r synths_max"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.717647, 0.227451, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.0, 0.299738, 1.0, 1.0 ],
					"id" : "obj-285",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.898039, 1.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 785.0, 901.24993896484375, 39.5, 39.5 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-284",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 695.29168701171875, 965.6666259765625, 177.0, 22.0 ],
					"text" : "prepend setvar $current_synths"
				}

			}
, 			{
				"box" : 				{
					"fontface" : 0,
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-283",
					"items" : "<empty>",
					"margin" : 10,
					"maxclass" : "chooser",
					"numinlets" : 1,
					"numoutlets" : 6,
					"outlettype" : [ "", "", "", "", "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 553.1666259765625, 631.79168701171875, 154.3333740234375, 125.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 572.3245849609375, 366.264495849609375, 159.0, 288.03338623046875 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-282",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 397.5, 573.79168701171875, 77.0, 22.0 ],
					"text" : "r tracks_max"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-281",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 397.5, 613.58331298828125, 96.0, 22.0 ],
					"text" : "prepend append"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-273",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 266.75, 822.0, 71.0, 22.0 ],
					"text" : "fromsymbol"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-262",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 260.75, 891.66668701171875, 55.0, 22.0 ],
					"text" : "zl slice 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-257",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 616.5, 895.91668701171875, 46.0, 22.0 ],
					"text" : "group2"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-255",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 560.83331298828125, 895.91668701171875, 46.0, 22.0 ],
					"text" : "group1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-252",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 839.9583740234375, 715.20831298828125, 77.0, 22.0 ],
					"text" : "r tracks_max"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.717647, 0.227451, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.0, 0.299738, 1.0, 1.0 ],
					"id" : "obj-249",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.898039, 1.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 505.75, 887.0, 39.5, 39.5 ],
					"presentation" : 1,
					"presentation_rect" : [ 511.833343505859375, 662.181182861328125, 19.300048828125, 19.300048828125 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-243",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 488.0, 940.6666259765625, 175.0, 22.0 ],
					"text" : "prepend setvar $current_tracks"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-236",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 3,
					"outlettype" : [ "int", "", "clear" ],
					"patching_rect" : [ 225.833389282226562, 765.125, 57.0, 22.0 ],
					"text" : "t 0 l clear"
				}

			}
, 			{
				"box" : 				{
					"fontface" : 0,
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-230",
					"items" : [ 426, "bajo_mes418" ],
					"margin" : 10,
					"maxclass" : "chooser",
					"multiselect" : 1,
					"numinlets" : 1,
					"numoutlets" : 6,
					"outlettype" : [ "", "", "", "", "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 371.916656494140625, 649.5, 154.166671752929688, 100.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 365.6170654296875, 366.264495849609375, 201.0, 288.03338623046875 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-224",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 3,
					"outlettype" : [ "int", "", "clear" ],
					"patching_rect" : [ 84.700000000000003, 788.625, 61.0, 22.0 ],
					"text" : "t 0 s clear"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-215",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 109.0, 863.54168701171875, 50.0, 22.0 ],
					"text" : "server1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-198",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 684.33331298828125, 778.20831298828125, 49.0, 22.0 ],
					"presentation" : 1,
					"presentation_linecount" : 2,
					"presentation_rect" : [ 629.375, 553.472808837890625, 35.0, 35.0 ],
					"text" : "server2"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-196",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 573.33331298828125, 788.625, 56.0, 22.0 ],
					"presentation" : 1,
					"presentation_linecount" : 2,
					"presentation_rect" : [ 396.666656494140625, 535.764495849609375, 35.0, 35.0 ],
					"text" : "localhost"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-188",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 753.0, 796.74993896484375, 32.0, 22.0 ],
					"text" : "print"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-182",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 753.0, 715.20831298828125, 82.0, 22.0 ],
					"text" : "r groups_max"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.717647, 0.227451, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.0, 0.299738, 1.0, 1.0 ],
					"id" : "obj-119",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.898039, 1.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 494.33331298828125, 795.25, 39.5, 39.5 ],
					"presentation" : 1,
					"presentation_rect" : [ 402.25, 576.514495849609375, 19.300048828125, 19.300048828125 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-34",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 494.33331298828125, 856.4761962890625, 179.0, 22.0 ],
					"text" : "prepend setvar $current_groups"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-207",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 74.0, 522.0, 25.0, 22.0 ],
					"text" : "iter"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-206",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 38.0, 804.0, 32.0, 22.0 ],
					"text" : "print"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-205",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "bang", "clear" ],
					"patching_rect" : [ 211.25, 958.02374267578125, 51.0, 22.0 ],
					"text" : "t b clear"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-183",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 211.25, 1010.74993896484375, 182.0, 22.0 ],
					"text" : "prepend setvar $current_servers"
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-202",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 347.25, 1173.5, 89.0, 19.0 ],
					"text" : "s antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-181",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 44.0, 476.0, 85.0, 22.0 ],
					"text" : "r servers_max"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 16.0,
					"id" : "obj-172",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 187.800048828125, 434.0, 116.0, 24.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 188.0, 334.264495849609375, 116.0, 24.0 ],
					"text" : "Groups",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-16",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 198.0, 469.2261962890625, 82.0, 22.0 ],
					"text" : "r groups_max"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-27",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 198.0, 567.25, 35.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 279.5, 334.264495849609375, 35.0, 22.0 ],
					"text" : "clear"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-38",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 243.800048828125, 567.25, 96.0, 22.0 ],
					"text" : "prepend append"
				}

			}
, 			{
				"box" : 				{
					"fontface" : 0,
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-109",
					"items" : [ "<undef>", "group_hoa1", "server1", ",", "<undef>", "group_hoa2", "server2", ",", "<undef>", "group_hoa3", "server3", ",", "<undef>", "group_hoa3_DB_12", "server3", ",", "<undef>", "group_hoa4", "server4", ",", "<undef>", "group_hoa6", "server6", ",", "<undef>", "group_hoa6_DB_12", "server6" ],
					"margin" : 10,
					"maxclass" : "chooser",
					"numinlets" : 1,
					"numoutlets" : 6,
					"outlettype" : [ "", "", "", "", "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 197.800048828125, 649.5, 154.166671752929688, 100.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 184.46710205078125, 366.264495849609375, 159.0, 288.03338623046875 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-272",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3335.75, 1195.0672607421875, 86.0, 22.0 ],
					"text" : "group1"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-270",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3301.75, 1163.760498046875, 127.0, 22.0 ],
					"text" : "prepend /group_synth"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-154",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2705.14990234375, 1164.0, 51.0, 22.0 ],
					"text" : "server4"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-106",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2715.6171875, 1195.0672607421875, 132.0, 22.0 ],
					"text" : "prepend /group_server"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-292",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 2748.20068359375, 1046.75, 40.0, 22.0 ],
					"text" : "zl reg"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-291",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1935.5833740234375, 1012.75, 124.75, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 55.75, 730.514495849609375, 93.0, 20.0 ],
					"text" : "Free all Nodes",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.913725, 0.913725, 0.0 ],
					"blinkcolor" : [ 1.0, 0.89, 0.09, 1.0 ],
					"id" : "obj-290",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.713726, 0.713726, 0.713726, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2748.20068359375, 1016.0, 23.0, 23.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 35.0, 730.514495849609375, 20.5, 20.5 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-288",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2670.0, 1137.400146484375, 88.0, 22.0 ],
					"text" : "append freeAll"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-289",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2670.0, 1101.375, 105.0, 22.0 ],
					"text" : "prepend /servers2"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-278",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 2907.5, 1033.83642578125, 40.0, 22.0 ],
					"text" : "zl reg"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.164706, 0.792157, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.031373, 0.729412, 0.152941, 1.0 ],
					"id" : "obj-279",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.0, 1.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 2907.5, 997.75, 20.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 39.0, 314.264495849609375, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-274",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2785.3994140625, 1137.400146484375, 85.0, 22.0 ],
					"text" : "append levels"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-276",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2785.3994140625, 1101.375, 105.0, 22.0 ],
					"text" : "prepend /servers2"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-247",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2891.0, 1137.400146484375, 69.0, 22.0 ],
					"text" : "append vol"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-248",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2891.0, 1101.375, 105.0, 22.0 ],
					"text" : "prepend /servers2"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-246",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 3109.5, 1016.0, 40.0, 22.0 ],
					"text" : "zl reg"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-245",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1939.9583740234375, 987.25, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 60.75, 292.264495849609375, 99.0, 20.0 ],
					"text" : "Server vol Slider",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.164706, 0.792157, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.031373, 0.729412, 0.152941, 1.0 ],
					"id" : "obj-244",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.0, 1.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3109.5, 979.91357421875, 20.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 39.0, 292.264495849609375, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-266",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3119.5, 1137.400146484375, 84.0, 22.0 ],
					"text" : "append query"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-267",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3119.5, 1101.375, 105.0, 22.0 ],
					"text" : "prepend /servers2"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-265",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 3267.0, 1016.106689453125, 40.0, 22.0 ],
					"text" : "zl reg"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.164706, 0.792157, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.031373, 0.729412, 0.152941, 1.0 ],
					"id" : "obj-263",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 1.0, 0.023529, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3267.0, 966.106689453125, 20.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 39.0, 272.264495849609375, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-261",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1939.9583740234375, 942.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 60.75, 272.264495849609375, 78.0, 20.0 ],
					"text" : "Server query",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-259",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2999.623779296875, 1137.400146484375, 73.0, 22.0 ],
					"text" : "append plot"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-256",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 3012.5, 1261.0672607421875, 137.0, 22.0 ],
					"text" : "udpsend localhost 7002"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-254",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 3193.375, 1016.106689453125, 40.0, 22.0 ],
					"text" : "zl reg"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-253",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 2999.623779296875, 1101.375, 105.0, 22.0 ],
					"text" : "prepend /servers2"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-251",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1939.9583740234375, 919.0, 150.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 60.75, 253.764495849609375, 78.0, 20.0 ],
					"text" : "Server Tree",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.164706, 0.792157, 0.913725, 0.0 ],
					"blinkcolor" : [ 1.0, 0.023574, 0.0, 1.0 ],
					"id" : "obj-250",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.187838, 0.227773, 1.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 3191.375, 976.91357421875, 20.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 39.0, 253.764495849609375, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-123",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9027.5, 1108.0, 137.0, 22.0 ],
					"text" : "prepend /gui_group_lev"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.164706, 0.792157, 0.913725, 0.0 ],
					"blinkcolor" : [ 1.0, 0.023574, 0.0, 1.0 ],
					"id" : "obj-155",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.187838, 0.227773, 1.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 9029.5, 1032.0, 20.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 178.932861328125, 254.764495849609375, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-200",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 9027.5, 1078.0, 40.0, 22.0 ],
					"text" : "zl reg"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-122",
					"linecount" : 2,
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 9147.5, 789.25, 124.75, 33.0 ],
					"presentation" : 1,
					"presentation_linecount" : 2,
					"presentation_rect" : [ 69.800048828125, 691.264495849609375, 100.0, 33.0 ],
					"text" : "(Panic!!)\nStop all Servers ",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-105",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "bang", "bang" ],
					"patching_rect" : [ 9266.0, 691.5, 32.5, 22.0 ],
					"text" : "b"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-108",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 9222.25, 741.5, 50.0, 22.0 ],
					"text" : "del 200"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.913725, 0.913725, 0.0 ],
					"blinkcolor" : [ 1.0, 0.89, 0.09, 1.0 ],
					"id" : "obj-111",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.713726, 0.713726, 0.713726, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 9266.0, 629.5, 43.0, 43.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 23.567138671875, 684.014495849609375, 47.5, 47.5 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-114",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9304.0, 741.5, 147.0, 22.0 ],
					"text" : "prepend /free_all_servers"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-242",
					"linecount" : 2,
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2233.300048828125, 1037.75, 106.0, 33.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 55.75, 667.014495849609375, 109.0, 20.0 ],
					"text" : "Free Select Server",
					"textcolor" : [ 1.0, 0.236166998744011, 0.293467998504639, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-210",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 9191.75, 541.2078857421875, 57.0, 22.0 ],
					"text" : "del 1500"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.329412, 0.478431, 0.0 ],
					"blinkcolor" : [ 1.0, 1.0, 0.4, 1.0 ],
					"id" : "obj-211",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.713726, 0.109804, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 9199.75, 495.1827392578125, 26.75, 26.75 ],
					"presentation" : 1,
					"presentation_rect" : [ 38.0, 669.014495849609375, 14.0, 14.538461685180664 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-212",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9227.25, 573.1827392578125, 61.0, 22.0 ],
					"text" : "append 1"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-213",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 9241.75, 513.9432373046875, 40.0, 22.0 ],
					"text" : "zl reg"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-221",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9292.75, 661.25, 121.0, 22.0 ],
					"text" : "prepend /freeservers"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-118",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 3311.375, 1125.0, 86.0, 22.0 ],
					"text" : "server1"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-195",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1939.9583740234375, 898.5, 91.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 60.75, 235.479034423828125, 91.5, 20.0 ],
					"text" : "active Servers",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 16.0,
					"id" : "obj-190",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1939.9583740234375, 963.25, 116.0, 24.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 42.0, 333.764495849609375, 116.0, 24.0 ],
					"text" : "Servers",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.717647, 0.227451, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.0, 0.299738, 1.0, 1.0 ],
					"id" : "obj-169",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.898039, 1.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 211.25, 895.91668701171875, 39.5, 39.5 ],
					"presentation" : 1,
					"presentation_rect" : [ 39.0, 235.479034423828125, 19.300048828125, 19.300048828125 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-120",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "bang", "bang" ],
					"patching_rect" : [ 9338.0, 374.025146484375, 32.5, 22.0 ],
					"text" : "b"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-121",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 9242.0, 266.05029296875, 50.0, 22.0 ],
					"text" : "del 200"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-124",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9124.0, 375.0, 32.5, 22.0 ],
					"text" : "1"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-125",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 9154.0, 326.0, 124.75, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 233.0, 702.548065185546875, 91.0, 20.0 ],
					"text" : "Stop all Groups",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-168",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9094.0, 406.0, 138.0, 22.0 ],
					"text" : "prepend /free_all_group"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-239",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 1125.75, 1132.025146484375, 48.0, 22.0 ],
					"text" : "del 300",
					"varname" : "3komp[1]"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-130",
					"linecount" : 2,
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2234.15869140625, 1005.25, 106.0, 33.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 193.932861328125, 667.014495849609375, 109.0, 20.0 ],
					"text" : "Free Select Group",
					"textcolor" : [ 1.0, 0.236166998744011, 0.293467998504639, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.329412, 0.478431, 0.0 ],
					"blinkcolor" : [ 1.0, 1.0, 0.4, 1.0 ],
					"id" : "obj-237",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.713726, 0.109804, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1193.4583740234375, 1060.75, 26.75, 26.75 ],
					"presentation" : 1,
					"presentation_rect" : [ 178.0, 669.014495849609375, 14.0, 14.538461685180664 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-227",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9456.5, 775.26458740234375, 61.0, 22.0 ],
					"text" : "append 1"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-131",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 1193.4583740234375, 1132.025146484375, 40.0, 22.0 ],
					"text" : "zl reg"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-229",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9437.0, 824.26458740234375, 113.0, 22.0 ],
					"text" : "prepend /freegroup"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-219",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 9334.75, 432.48944091796875, 68.0, 22.0 ],
					"text" : "route bang"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-151",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9253.75, 282.025146484375, 136.0, 22.0 ],
					"text" : "prepend /actives_group"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-132",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 9403.25, 396.0, 91.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 199.932861328125, 235.479034423828125, 91.5, 20.0 ],
					"text" : "actives Groups",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.717647, 0.227451, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.0, 0.299738, 1.0, 1.0 ],
					"id" : "obj-133",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.898039, 1.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 9251.75, 218.275146484375, 39.5, 39.5 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-112",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9417.25, 530.51458740234375, 52.0, 22.0 ],
					"text" : "clear all"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-134",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"patching_rect" : [ 9339.75, 504.01458740234375, 96.5, 22.0 ],
					"text" : "t l b"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-135",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9339.75, 556.51458740234375, 84.0, 22.0 ],
					"text" : "prepend set 0"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-136",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "list" ],
					"patching_rect" : [ 9339.75, 530.51458740234375, 58.0, 22.0 ],
					"text" : "listfunnel"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 13.0,
					"id" : "obj-177",
					"maxclass" : "textedit",
					"numinlets" : 1,
					"numoutlets" : 4,
					"outlettype" : [ "", "int", "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 8950.0, 966.0, 161.0, 29.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 572.3245849609375, 610.264495849609375, 134.0, 25.0 ],
					"text" : "02_TVbapKubus"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-176",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 8950.0, 938.0, 74.0, 22.0 ],
					"text" : "prepend set"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-138",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 9044.0, 908.5, 57.0, 22.0 ],
					"text" : "zl slice 2"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-116",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 8708.5, 1022.0, 77.0, 22.0 ],
					"text" : "prepend /gui"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-110",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 8708.5, 1053.0, 137.0, 22.0 ],
					"text" : "udpsend localhost 7002"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.164706, 0.792157, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.646639, 0.821777, 0.854593, 1.0 ],
					"id" : "obj-139",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.187838, 0.227773, 1.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 8710.5, 949.0, 20.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 365.6170654296875, 255.264495849609375, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-140",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 8708.5, 992.0, 40.0, 22.0 ],
					"text" : "zl reg"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-277",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 9166.25, 525.0, 106.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 386.158843994140625, 667.014495849609375, 106.0, 20.0 ],
					"text" : "Stop Select Synth",
					"textcolor" : [ 1.0, 0.236166998744011, 0.293467998504639, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-275",
					"linecount" : 3,
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 9485.0, 205.0, 88.0, 47.0 ],
					"presentation" : 1,
					"presentation_linecount" : 2,
					"presentation_rect" : [ 407.0677490234375, 691.264495849609375, 95.0, 33.0 ],
					"text" : "(Panic!!)\nStop all Synths",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-235",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 887.75, 1132.025146484375, 48.0, 22.0 ],
					"text" : "del 300",
					"varname" : "3komp"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-234",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9412.75, 640.0, 61.0, 22.0 ],
					"text" : "append 1"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.329412, 0.478431, 0.0 ],
					"blinkcolor" : [ 1.0, 1.0, 0.4, 1.0 ],
					"id" : "obj-232",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.713726, 0.109804, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 905.75, 1072.0, 26.75, 26.75 ],
					"presentation" : 1,
					"presentation_rect" : [ 368.791656494140625, 669.014495849609375, 14.0, 14.538461685180664 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-225",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 983.75, 1132.025146484375, 40.0, 22.0 ],
					"text" : "zl reg"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-217",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9398.0, 456.275146484375, 108.0, 22.0 ],
					"text" : "prepend /freetrack"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-223",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9259.75, 431.0, 32.5, 22.0 ],
					"text" : "0"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-222",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9183.25, 431.0, 32.5, 22.0 ],
					"text" : "100"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"format" : 6,
					"id" : "obj-220",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 9236.75, 491.5, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"format" : 6,
					"id" : "obj-218",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 9166.25, 491.5, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-214",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 3,
					"outlettype" : [ "float", "int", "int" ],
					"patching_rect" : [ 9166.25, 465.03338623046875, 56.0, 19.0 ],
					"text" : "trough 100."
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-216",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 3,
					"outlettype" : [ "float", "int", "int" ],
					"patching_rect" : [ 9236.75, 465.03338623046875, 42.0, 19.0 ],
					"text" : "peak 0."
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-208",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9532.0, 391.275146484375, 52.0, 22.0 ],
					"text" : "clear all"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-203",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9336.75, 309.275146484375, 84.0, 22.0 ],
					"text" : "prepend set 0"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-204",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "list" ],
					"patching_rect" : [ 9336.75, 283.275146484375, 58.0, 22.0 ],
					"text" : "listfunnel"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-193",
					"linecount" : 18,
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9319.5, 195.775146484375, 25.0, 250.0 ],
					"text" : "group1 group1_8 group1_16 group1_Kubus"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-187",
					"linecount" : 10,
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 8708.5, 1157.0, 18.0, 143.0 ],
					"text" : "bpf_nim_107"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"hidden" : 1,
					"id" : "obj-171",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 8930.0, 1152.0, 57.0, 22.0 ],
					"text" : "zl slice 2"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-141",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"patching_rect" : [ 9621.0, 229.025146484375, 32.5, 22.0 ],
					"text" : "t l b"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-142",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9217.0, 283.275146484375, 52.0, 22.0 ],
					"text" : "clear all"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-143",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 9566.0, 185.025146484375, 68.0, 22.0 ],
					"text" : "route bang"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-144",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9618.0, 282.025146484375, 84.0, 22.0 ],
					"text" : "prepend set 0"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-158",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "list" ],
					"patching_rect" : [ 9618.0, 256.025146484375, 58.0, 22.0 ],
					"text" : "listfunnel"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-157",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1087.0, 901.0, 88.0, 20.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 385.6170654296875, 235.479034423828125, 88.0, 20.0 ],
					"text" : "actives Tracks",
					"textcolor" : [ 1.0, 1.0, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-150",
					"maxclass" : "newobj",
					"numinlets" : 7,
					"numoutlets" : 7,
					"outlettype" : [ "", "", "", "", "", "", "" ],
					"patching_rect" : [ 9567.0, 144.025146484375, 556.0, 22.0 ],
					"text" : "route /active_synth /synth /active_group /active_server /server_group /synth_group"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.717647, 0.227451, 0.913725, 0.0 ],
					"blinkcolor" : [ 0.803922, 0.898039, 0.909804, 1.0 ],
					"id" : "obj-153",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.898039, 1.0, 0.0, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1035.5, 879.75, 39.5, 39.5 ],
					"presentation" : 1,
					"presentation_rect" : [ 365.6170654296875, 235.479034423828125, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-115",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9621.0, 431.0, 125.0, 22.0 ],
					"text" : "prepend /synth_order"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 18.0,
					"id" : "obj-165",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 9566.0, 80.05029296875, 72.0, 27.0 ],
					"text" : "from sc"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 11.0,
					"id" : "obj-173",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9566.0, 109.525146484375, 92.0, 21.0 ],
					"text" : "udpreceive 8888"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-175",
					"maxclass" : "toggle",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 9266.0, 80.0, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-179",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9259.75, 118.0, 54.0, 22.0 ],
					"text" : "gate 1 1"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.913725, 0.913725, 0.913725, 0.0 ],
					"blinkcolor" : [ 1.0, 0.89, 0.09, 1.0 ],
					"id" : "obj-180",
					"maxclass" : "button",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"outlinecolor" : [ 0.713726, 0.713726, 0.713726, 1.0 ],
					"parameter_enable" : 0,
					"patching_rect" : [ 9412.75, 185.025146484375, 43.0, 43.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 362.19207763671875, 684.014495849609375, 47.5, 47.5 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-184",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9425.0, 273.0, 119.0, 22.0 ],
					"text" : "prepend /cleartracks"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-185",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 9437.0, 337.025146484375, 137.0, 22.0 ],
					"text" : "udpsend localhost 7002"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-113",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9445.0, 80.0, 99.0, 22.0 ],
					"text" : "udpreceive 5555"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-186",
					"maxclass" : "toggle",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 9352.0, 118.0, 20.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-189",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9372.0, 154.0, 54.0, 22.0 ],
					"text" : "gate 1 1"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-191",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 9387.0, 118.0, 64.0, 22.0 ],
					"text" : "route stop"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-192",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9387.0, 80.0, 51.0, 22.0 ],
					"text" : "r player"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-32",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 150.0, 598.0, 35.0, 22.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 125.0, 333.764495849609375, 35.0, 22.0 ],
					"text" : "clear"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-26",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 68.5, 562.0, 96.0, 22.0 ],
					"text" : "prepend append"
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-18",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 1083.0, 402.0, 89.0, 19.0 ],
					"text" : "s antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-25",
					"maxclass" : "comment",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 249.25, 382.0, 150.0, 20.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontface" : 0,
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-9",
					"items" : [ "server1", ",", "server2", ",", "server3", ",", "server4", ",", "server5", ",", "server6" ],
					"margin" : 10,
					"maxclass" : "chooser",
					"numinlets" : 1,
					"numoutlets" : 6,
					"outlettype" : [ "", "", "", "", "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 68.5, 649.5, 100.0, 100.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 42.0, 366.264495849609375, 109.0, 184.03338623046875 ]
				}

			}
, 			{
				"box" : 				{
					"format" : 6,
					"id" : "obj-33",
					"maxclass" : "flonum",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 1277.0, 482.0, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-5",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"patching_rect" : [ 421.25, 434.0, 24.0, 22.0 ],
					"text" : "t 1"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 18.0,
					"id" : "obj-4",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 288.25, 404.0, 125.0, 29.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 604.407913208007812, 180.0, 140.0, 29.0 ],
					"text" : "free all groups"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-3",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 421.25, 476.0, 165.0, 22.0 ],
					"text" : "prepend setvar $free_groups"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.443137254901961, 0.643137254901961, 0.392156862745098, 1.0 ],
					"bgcolor2" : [ 0.419607843137255, 0.662745098039216, 0.43921568627451, 1.0 ],
					"bgfillcolor_angle" : 270.0,
					"bgfillcolor_autogradient" : 0.0,
					"bgfillcolor_color" : [ 0.2, 0.2, 0.2, 1.0 ],
					"bgfillcolor_color1" : [ 0.443137254901961, 0.643137254901961, 0.392156862745098, 1.0 ],
					"bgfillcolor_color2" : [ 0.419607843137255, 0.662745098039216, 0.43921568627451, 1.0 ],
					"bgfillcolor_proportion" : 0.5,
					"bgfillcolor_type" : "gradient",
					"fontname" : "Arial",
					"fontsize" : 30.0,
					"gradient" : 1,
					"id" : "obj-65",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 11.5, 374.0, 186.0, 42.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 30.0, 132.0, 452.5, 42.0 ],
					"text" : "mes_366"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-63",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 595.0, 480.0, 151.0, 22.0 ],
					"text" : "prepend setvar $likelihood"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-62",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 465.33331298828125, 423.0, 164.0, 22.0 ],
					"text" : "prepend setvar $from_nodes"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 11.0,
					"id" : "obj-61",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 465.33331298828125, 390.0, 78.0, 21.0 ],
					"text" : "r loglikelihood"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-60",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 595.0, 452.0, 68.0, 22.0 ],
					"text" : "r likelihood"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 18.0,
					"id" : "obj-48",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 241.0, 21.0, 44.0, 29.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 340.5, 16.5, 56.0, 29.0 ],
					"text" : "play"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-49",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 241.0, -101.0, 515.0, 22.0 ],
					"text" : "score /Users/fernandez/Documents/Projet-Audiovisuel-Raphael/Compo/univers2_3.asco.txt"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-2",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "bang" ],
					"patching_rect" : [ 525.33331298828125, 0.0, 100.0, 22.0 ],
					"text" : "loadbang"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-156",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 8972.0, 52.025146484375, 53.0, 22.0 ],
					"text" : "/notify 0"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-30",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 8835.0, 52.025146484375, 129.0, 22.0 ],
					"text" : "maxqueuesize 16384"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-107",
					"maxclass" : "number",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "", "bang" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 8899.0, 258.025146484375, 50.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-74",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 8822.0, 337.025146484375, 29.5, 22.0 ],
					"text" : "0"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-92",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9134.5, 253.025146484375, 29.5, 22.0 ],
					"text" : "set"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-36",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9068.0, 445.025146484375, 29.5, 22.0 ],
					"text" : "set"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-73",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9027.5, 562.025146484375, 284.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-72",
					"maxclass" : "newobj",
					"numinlets" : 3,
					"numoutlets" : 3,
					"outlettype" : [ "", "", "" ],
					"patching_rect" : [ 9063.0, 392.025146484375, 118.0, 22.0 ],
					"text" : "route 0 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-70",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 9120.5, 302.025146484375, 262.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-58",
					"maxclass" : "live.meter~",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "float", "int" ],
					"patching_rect" : [ 8906.0, 344.025146484375, 5.0, 100.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-57",
					"maxclass" : "live.meter~",
					"numinlets" : 1,
					"numoutlets" : 2,
					"outlettype" : [ "float", "int" ],
					"patching_rect" : [ 8880.0, 344.025146484375, 5.0, 100.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-55",
					"maxclass" : "newobj",
					"numinlets" : 3,
					"numoutlets" : 3,
					"outlettype" : [ "", "", "" ],
					"patching_rect" : [ 8873.0, 308.025146484375, 71.0, 22.0 ],
					"text" : "route 86 94"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-54",
					"maxclass" : "toggle",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "int" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 8846.5, 144.025146484375, 16.0, 16.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-52",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 8869.0, 144.025146484375, 34.0, 22.0 ],
					"text" : "gate"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-45",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 8934.0, 519.525146484375, 263.0, 22.0 ]
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-41",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "", "" ],
					"patching_rect" : [ 8835.0, 267.025146484375, 57.0, 22.0 ],
					"text" : "zl slice 1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-35",
					"maxclass" : "newobj",
					"numinlets" : 4,
					"numoutlets" : 4,
					"outlettype" : [ "", "", "", "" ],
					"patching_rect" : [ 8835.0, 218.025146484375, 247.0, 22.0 ],
					"text" : "route /meter /status.reply /g_queryTree.reply"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-31",
					"linecount" : 3,
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 8948.0, 282.025146484375, 115.0, 49.0 ],
					"text" : "1 0 0 9 1687 0.03806 0.16095 0. 0."
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-11",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 8869.0, 170.025146484375, 149.0, 22.0 ],
					"text" : "print \"received messages\""
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-10",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 8835.0, 95.025146484375, 106.0, 22.0 ],
					"text" : "udpreceive 33333"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 14.0,
					"id" : "obj-1",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 255.0, 145.0, 89.0, 24.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 186.46710205078125, 56.5, 89.0, 24.0 ],
					"text" : "reloadscore"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 14.0,
					"id" : "obj-238",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 903.5, 201.0, 71.0, 24.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 281.5, 56.5, 71.0, 24.0 ],
					"text" : "nextlabel"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 14.0,
					"id" : "obj-228",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 257.5, 107.0, 41.0, 24.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 163.46710205078125, 96.0, 47.0, 24.0 ],
					"text" : "killall"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-226",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 618.5, 262.0, 89.0, 22.0 ],
					"text" : "killall_and_osc"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-149",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 2,
					"outlettype" : [ "bang", "" ],
					"patching_rect" : [ 332.5, 28.0, 43.0, 22.0 ],
					"text" : "sel 32"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-148",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 4,
					"outlettype" : [ "int", "int", "int", "int" ],
					"patching_rect" : [ 329.5, 0.0, 50.5, 22.0 ],
					"text" : "key"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-128",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patcher" : 					{
						"fileversion" : 1,
						"appversion" : 						{
							"major" : 8,
							"minor" : 5,
							"revision" : 3,
							"architecture" : "x64",
							"modernui" : 1
						}
,
						"classnamespace" : "box",
						"rect" : [ 40.0, 610.0, 300.0, 265.0 ],
						"bglocked" : 0,
						"openinpresentation" : 0,
						"default_fontsize" : 12.0,
						"default_fontface" : 0,
						"default_fontname" : "Arial",
						"gridonopen" : 1,
						"gridsize" : [ 15.0, 15.0 ],
						"gridsnaponopen" : 1,
						"objectsnaponopen" : 1,
						"statusbarvisible" : 2,
						"toolbarvisible" : 1,
						"lefttoolbarpinned" : 0,
						"toptoolbarpinned" : 0,
						"righttoolbarpinned" : 0,
						"bottomtoolbarpinned" : 0,
						"toolbars_unpinned_last_save" : 0,
						"tallnewobj" : 0,
						"boxanimatetime" : 200,
						"enablehscroll" : 1,
						"enablevscroll" : 1,
						"devicewidth" : 0.0,
						"description" : "",
						"digest" : "",
						"tags" : "",
						"style" : "",
						"subpatcher_template" : "",
						"assistshowspatchername" : 0,
						"boxes" : [ 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-10",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 154.0, 143.0, 69.0, 22.0 ],
									"text" : "s ante_pos"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 10.0,
									"id" : "obj-1",
									"maxclass" : "message",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 183.0, 92.0, 55.0, 20.0 ],
									"text" : "6"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 10.0,
									"id" : "obj-2",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 184.0, 67.0, 66.0, 20.0 ],
									"text" : "prepend set"
								}

							}
, 							{
								"box" : 								{
									"id" : "obj-3",
									"maxclass" : "toggle",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "int" ],
									"parameter_enable" : 0,
									"patching_rect" : [ 150.0, 33.0, 15.0, 15.0 ]
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 10.0,
									"id" : "obj-4",
									"maxclass" : "newobj",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 151.0, 63.0, 29.0, 20.0 ],
									"text" : "gate"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-5",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 74.0, 114.0, 103.0, 19.0 ],
									"text" : "send antescofo_calib"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-6",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 28.0, 178.0, 107.0, 19.0 ],
									"text" : "send antescofo-labels"
								}

							}
, 							{
								"box" : 								{
									"comment" : "",
									"id" : "obj-7",
									"index" : 1,
									"maxclass" : "outlet",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 120.0, 152.0, 15.0, 15.0 ]
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-8",
									"maxclass" : "newobj",
									"numinlets" : 3,
									"numoutlets" : 3,
									"outlettype" : [ "", "", "" ],
									"patching_rect" : [ 28.0, 71.0, 103.0, 19.0 ],
									"text" : "route cue calibration"
								}

							}
, 							{
								"box" : 								{
									"comment" : "",
									"id" : "obj-9",
									"index" : 1,
									"maxclass" : "inlet",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 28.0, 38.0, 15.0, 15.0 ]
								}

							}
 ],
						"lines" : [ 							{
								"patchline" : 								{
									"destination" : [ "obj-1", 0 ],
									"source" : [ "obj-2", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-4", 0 ],
									"source" : [ "obj-3", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-2", 0 ],
									"source" : [ "obj-4", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-10", 0 ],
									"order" : 0,
									"source" : [ "obj-8", 2 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-4", 1 ],
									"order" : 0,
									"source" : [ "obj-8", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-5", 0 ],
									"source" : [ "obj-8", 1 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-6", 0 ],
									"order" : 1,
									"source" : [ "obj-8", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-7", 0 ],
									"order" : 1,
									"source" : [ "obj-8", 2 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-8", 0 ],
									"source" : [ "obj-9", 0 ]
								}

							}
 ]
					}
,
					"patching_rect" : [ 85.0625, 191.0, 55.0, 19.0 ],
					"saved_object_attributes" : 					{
						"description" : "",
						"digest" : "",
						"globalpatchername" : "",
						"tags" : ""
					}
,
					"text" : "p dispatch"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-127",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 26.0, -46.0, 135.0, 19.0 ],
					"text" : "r antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 12.0,
					"id" : "obj-178",
					"maxclass" : "newobj",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patcher" : 					{
						"fileversion" : 1,
						"appversion" : 						{
							"major" : 8,
							"minor" : 5,
							"revision" : 3,
							"architecture" : "x64",
							"modernui" : 1
						}
,
						"classnamespace" : "box",
						"rect" : [ 758.0, 251.0, 653.0, 656.0 ],
						"bglocked" : 0,
						"openinpresentation" : 0,
						"default_fontsize" : 12.0,
						"default_fontface" : 0,
						"default_fontname" : "Arial",
						"gridonopen" : 1,
						"gridsize" : [ 15.0, 15.0 ],
						"gridsnaponopen" : 1,
						"objectsnaponopen" : 1,
						"statusbarvisible" : 2,
						"toolbarvisible" : 1,
						"lefttoolbarpinned" : 0,
						"toptoolbarpinned" : 0,
						"righttoolbarpinned" : 0,
						"bottomtoolbarpinned" : 0,
						"toolbars_unpinned_last_save" : 0,
						"tallnewobj" : 0,
						"boxanimatetime" : 200,
						"enablehscroll" : 1,
						"enablevscroll" : 1,
						"devicewidth" : 0.0,
						"description" : "",
						"digest" : "",
						"tags" : "",
						"style" : "",
						"subpatcher_template" : "",
						"assistshowspatchername" : 0,
						"boxes" : [ 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-46",
									"maxclass" : "newobj",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "int" ],
									"patching_rect" : [ 173.949950999999999, 458.75, 32.5, 22.0 ],
									"text" : "+ 1"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-45",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 173.949951171875, 536.0, 74.0, 22.0 ],
									"text" : "prepend set"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-44",
									"maxclass" : "newobj",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "int" ],
									"patching_rect" : [ 382.628539999999987, 261.035706000000005, 32.5, 22.0 ],
									"text" : "+ 1"
								}

							}
, 							{
								"box" : 								{
									"allowdrag" : 0,
									"fontsize" : 18.0,
									"id" : "obj-42",
									"items" : [ "SC_multiserver_INIT", ",", "create_load_envs_buff", ",", "create_gran_buff", ",", "create_fft_buff", ",", "obj_rec_buff", ",", "obj_rec_pvoc", ",", "waveshaping_buffs", ",", "vezer_xml2nims", ",", "load_samples", ",", "load_pvoc", ",", "mix_groups_HOA", ",", "notas_electronicas", ",", "mes_1", ",", "mes_2", ",", "mes_3", ",", "mes_4", ",", "mes_5", ",", "mes_6", ",", "mes_7", ",", "mes_8", ",", "mes_9", ",", "mes_10", ",", "mes_11", ",", "mes_12", ",", "mes_13", ",", "mes_14", ",", "mes_15", ",", "mes_16", ",", "mes_17", ",", "mes_18", ",", "mes_19", ",", "mes_20", ",", "mes_21", ",", "mes_22", ",", "mes_23", ",", "mes_24", ",", "mes_25", ",", "mes_26", ",", "mes_27", ",", "mes_28", ",", "mes_29", ",", "mes_30", ",", "mes_31", ",", "mes_32", ",", "mes_33", ",", "mes_34", ",", "mes_35", ",", "mes_36", ",", "mes_37", ",", "mes_38", ",", "mes_39", ",", "mes_40", ",", "mes_41", ",", "mes_42", ",", "mes_43", ",", "mes_44", ",", "mes_45", ",", "mes_46", ",", "mes_47", ",", "mes_48", ",", "mes_49", ",", "mes_50", ",", "mes_51", ",", "mes_52", ",", "mes_53", ",", "mes_54", ",", "mes_55", ",", "mes_56", ",", "mes_57", ",", "mes_58", ",", "mes_59", ",", "mes_60", ",", "mes_61", ",", "mes_62", ",", "mes_63", ",", "mes_64", ",", "mes_65", ",", "mes_66", ",", "mes_67", ",", "mes_68", ",", "mes_69", ",", "mes_70", ",", "mes_71", ",", "mes_72", ",", "mes_73", ",", "mes_74", ",", "mes_75", ",", "mes_76", ",", "mes_77", ",", "mes_78", ",", "mes_79", ",", "mes_80", ",", "mes_81", ",", "mes_82", ",", "mes_83", ",", "mes_84", ",", "mes_85", ",", "mes_86", ",", "mes_87", ",", "mes_88", ",", "mes_89", ",", "mes_90", ",", "mes_91", ",", "mes_92", ",", "mes_93", ",", "mes_94", ",", "mes_95", ",", "mes_96", ",", "mes_97", ",", "mes_98", ",", "mes_99", ",", "mes_100", ",", "mes_101", ",", "mes_102", ",", "mes_103", ",", "mes_104", ",", "mes_105", ",", "mes_106", ",", "mes_107", ",", "mes_108", ",", "mes_109", ",", "mes_110", ",", "mes_111", ",", "mes_112", ",", "mes_113", ",", "mes_114", ",", "mes_115", ",", "mes_116", ",", "mes_117", ",", "mes_118", ",", "mes_119", ",", "mes_120", ",", "mes_121", ",", "mes_122", ",", "mes_123", ",", "mes_124", ",", "mes_125", ",", "mes_126", ",", "mes_127", ",", "mes_128", ",", "mes_129", ",", "mes_130", ",", "mes_131", ",", "mes_132", ",", "mes_133", ",", "mes_134", ",", "mes_135", ",", "mes_136", ",", "mes_137", ",", "mes_138", ",", "mes_139", ",", "mes_140", ",", "mes_141", ",", "mes_142", ",", "mes_143", ",", "mes_144", ",", "mes_145", ",", "mes_146", ",", "mes_147", ",", "mes_148", ",", "mes_149", ",", "mes_150", ",", "mes_151", ",", "mes_152", ",", "mes_153", ",", "mes_154", ",", "mes_155", ",", "mes_156", ",", "mes_157", ",", "mes_158", ",", "mes_159", ",", "mes_160", ",", "mes_161", ",", "mes_162", ",", "mes_163", ",", "mes_164", ",", "mes_165", ",", "mes_166", ",", "mes_167", ",", "mes_168", ",", "mes_169", ",", "mes_170", ",", "mes_171", ",", "mes_172", ",", "mes_173", ",", "mes_174", ",", "mes_175", ",", "mes_176", ",", "mes_177", ",", "mes_178", ",", "mes_179", ",", "mes_180", ",", "mes_181", ",", "mes_182", ",", "mes_183", ",", "mes_184", ",", "mes_185", ",", "mes_186", ",", "mes_187", ",", "mes_188", ",", "mes_189", ",", "mes_190", ",", "mes_191", ",", "mes_192", ",", "mes_193", ",", "mes_194", ",", "mes_195", ",", "mes_196", ",", "mes_197", ",", "mes_198", ",", "mes_199", ",", "mes_200", ",", "mes_201", ",", "mes_202", ",", "mes_203", ",", "mes_204", ",", "mes_205", ",", "mes_206", ",", "mes_207", ",", "mes_208", ",", "mes_209", ",", "mes_210", ",", "mes_211", ",", "mes_212", ",", "mes_213", ",", "mes_214", ",", "mes_215", ",", "mes_216", ",", "mes_217", ",", "mes_218", ",", "mes_219", ",", "mes_220", ",", "mes_221", ",", "mes_222", ",", "mes_223", ",", "mes_224", ",", "mes_225", ",", "mes_226", ",", "mes_227", ",", "mes_228", ",", "mes_229", ",", "mes_230", ",", "mes_231", ",", "mes_232", ",", "mes_233", ",", "mes_234", ",", "mes_235", ",", "mes_236", ",", "mes_237", ",", "mes_238", ",", "mes_239", ",", "mes_240", ",", "mes_241", ",", "mes_242", ",", "mes_243", ",", "mes_244", ",", "mes_245", ",", "mes_246", ",", "mes_247", ",", "mes_248", ",", "mes_249", ",", "mes_250", ",", "mes_251", ",", "mes_252", ",", "mes_253", ",", "mes_254", ",", "mes_255", ",", "mes_256", ",", "mes_257", ",", "mes_258", ",", "mes_259", ",", "mes_260", ",", "mes_261", ",", "mes_262", ",", "mes_263", ",", "mes_264", ",", "mes_265", ",", "mes_266", ",", "mes_267", ",", "mes_268", ",", "mes_269", ",", "mes_270", ",", "mes_271", ",", "mes_272", ",", "mes_273", ",", "mes_274", ",", "mes_275", ",", "mes_276", ",", "mes_277", ",", "mes_278", ",", "mes_279", ",", "mes_280", ",", "mes_281", ",", "mes_282", ",", "mes_283", ",", "mes_284", ",", "mes_285", ",", "mes_286", ",", "mes_287", ",", "mes_288", ",", "mes_289", ",", "mes_290", ",", "mes_291", ",", "mes_292", ",", "mes_293", ",", "mes_294", ",", "mes_295", ",", "mes_296", ",", "mes_297", ",", "mes_298", ",", "mes_299", ",", "mes_300", ",", "mes_301", ",", "mes_302", ",", "mes_303", ",", "mes_304", ",", "mes_305", ",", "mes_306", ",", "mes_307", ",", "mes_308", ",", "mes_309", ",", "mes_310", ",", "mes_311", ",", "mes_312", ",", "mes_313", ",", "mes_314", ",", "mes_315", ",", "mes_316", ",", "mes_317", ",", "mes_318", ",", "mes_319", ",", "mes_320", ",", "mes_321", ",", "mes_322", ",", "mes_323", ",", "mes_324", ",", "mes_325", ",", "mes_326", ",", "mes_327", ",", "mes_328", ",", "mes_329", ",", "mes_330", ",", "mes_331", ",", "mes_332", ",", "mes_333", ",", "mes_334", ",", "mes_335", ",", "mes_336", ",", "mes_337", ",", "mes_338", ",", "mes_339", ",", "mes_340", ",", "mes_341", ",", "mes_342", ",", "mes_343", ",", "mes_344", ",", "mes_345", ",", "mes_346", ",", "mes_347", ",", "mes_348", ",", "mes_349", ",", "mes_350", ",", "mes_351", ",", "mes_352", ",", "mes_353", ",", "mes_354", ",", "mes_355", ",", "mes_356", ",", "mes_357", ",", "mes_358", ",", "mes_359", ",", "mes_360", ",", "mes_361", ",", "mes_362", ",", "mes_363", ",", "mes_364", ",", "mes_365", ",", "mes_366", ",", "mes_367", ",", "mes_368", ",", "mes_369", ",", "mes_370", ",", "mes_371", ",", "mes_372", ",", "mes_373", ",", "mes_374", ",", "mes_375", ",", "mes_376", ",", "mes_377", ",", "mes_378", ",", "mes_379", ",", "mes_380", ",", "mes_381", ",", "mes_382", ",", "mes_383", ",", "mes_384", ",", "mes_385", ",", "mes_386", ",", "mes_387", ",", "mes_388", ",", "mes_389", ",", "mes_390", ",", "mes_391", ",", "mes_392", ",", "mes_393", ",", "mes_394", ",", "mes_395", ",", "mes_396", ",", "mes_397", ",", "mes_398", ",", "mes_399", ",", "mes_400", ",", "mes_401", ",", "mes_402", ",", "mes_403", ",", "mes_404", ",", "mes_405", ",", "mes_406", ",", "mes_407", ",", "mes_408", ",", "mes_409", ",", "mes_410", ",", "mes_411", ",", "mes_412", ",", "mes_413", ",", "mes_414", ",", "mes_415", ",", "mes_416", ",", "mes_417", ",", "mes_418", ",", "mes_419", ",", "mes_420", ",", "mes_421", ",", "mes_422", ",", "mes_423", ",", "mes_424", ",", "mes_425", ",", "mes_426", ",", "mes_427", ",", "mes_428", ",", "mes_429", ",", "mes_430", ",", "mes_431", ",", "mes_432", ",", "mes_433", ",", "mes_434", ",", "mes_435", ",", "mes_436", ",", "mes_437", ",", "mes_438", ",", "mes_439", ",", "mes_440", ",", "mes_441", ",", "mes_442", ",", "mes_443", ",", "mes_444", ",", "mes_445", ",", "mes_446", ",", "mes_447", ",", "mes_448", ",", "mes_449", ",", "mes_450", ",", "mes_451", ",", "mes_452", ",", "mes_453", ",", "mes_454", ",", "mes_455", ",", "mes_456", ",", "mes_457", ",", "mes_458", ",", "mes_459", ",", "mes_460", ",", "mes_461", ",", "mes_462", ",", "mes_463", ",", "mes_464", ",", "mes_465", ",", "mes_466", ",", "mes_467", ",", "mes_468", ",", "mes_469", ",", "mes_470", ",", "mes_471", ",", "mes_472", ",", "mes_473", ",", "mes_474", ",", "mes_475", ",", "mes_476", ",", "mes_477", ",", "mes_478", ",", "mes_479", ",", "mes_480", ",", "mes_481", ",", "mes_482", ",", "mes_483", ",", "mes_484", ",", "mes_485", ",", "mes_486", ",", "mes_487", ",", "mes_488", ",", "mes_489", ",", "mes_490", ",", "mes_491", ",", "mes_492", ",", "mes_493", ",", "mes_494", ",", "mes_495", ",", "mes_496", ",", "mes_497", ",", "mes_498", ",", "mes_499", ",", "mes_500", ",", "mes_501", ",", "mes_502", ",", "mes_503", ",", "mes_504", ",", "mes_505", ",", "mes_506", ",", "mes_507", ",", "mes_508", ",", "mes_509", ",", "mes_510", ",", "mes_511", ",", "mes_512", ",", "mes_513", ",", "mes_514", ",", "mes_515", ",", "mes_516", ",", "mes_517", ",", "mes_518", ",", "mes_519", ",", "mes_520", ",", "mes_521", ",", "mes_522" ],
									"maxclass" : "umenu",
									"numinlets" : 1,
									"numoutlets" : 3,
									"outlettype" : [ "int", "", "" ],
									"parameter_enable" : 0,
									"patching_rect" : [ 382.628539999999987, 221.785706000000005, 193.699950999999999, 29.0 ]
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 12.0,
									"id" : "obj-12",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 195.5, 100.0, 96.0, 22.0 ],
									"text" : "prepend symbol"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-52",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "float" ],
									"patcher" : 									{
										"fileversion" : 1,
										"appversion" : 										{
											"major" : 8,
											"minor" : 5,
											"revision" : 3,
											"architecture" : "x64",
											"modernui" : 1
										}
,
										"classnamespace" : "box",
										"rect" : [ 1049.0, 112.0, 600.0, 426.0 ],
										"bglocked" : 0,
										"openinpresentation" : 0,
										"default_fontsize" : 12.0,
										"default_fontface" : 0,
										"default_fontname" : "Arial",
										"gridonopen" : 1,
										"gridsize" : [ 15.0, 15.0 ],
										"gridsnaponopen" : 1,
										"objectsnaponopen" : 1,
										"statusbarvisible" : 2,
										"toolbarvisible" : 1,
										"lefttoolbarpinned" : 0,
										"toptoolbarpinned" : 0,
										"righttoolbarpinned" : 0,
										"bottomtoolbarpinned" : 0,
										"toolbars_unpinned_last_save" : 0,
										"tallnewobj" : 0,
										"boxanimatetime" : 200,
										"enablehscroll" : 1,
										"enablevscroll" : 1,
										"devicewidth" : 0.0,
										"description" : "",
										"digest" : "",
										"tags" : "",
										"style" : "",
										"subpatcher_template" : "",
										"assistshowspatchername" : 0,
										"boxes" : [ 											{
												"box" : 												{
													"id" : "obj-10",
													"maxclass" : "number",
													"numinlets" : 1,
													"numoutlets" : 2,
													"outlettype" : [ "", "bang" ],
													"parameter_enable" : 0,
													"patching_rect" : [ 313.0, 143.0, 50.0, 22.0 ]
												}

											}
, 											{
												"box" : 												{
													"fontname" : "Arial",
													"fontsize" : 9.0,
													"id" : "obj-1",
													"maxclass" : "newobj",
													"numinlets" : 0,
													"numoutlets" : 1,
													"outlettype" : [ "" ],
													"patching_rect" : [ 58.0, 130.0, 78.0, 19.0 ],
													"text" : "r avance-index"
												}

											}
, 											{
												"box" : 												{
													"fontname" : "Arial",
													"fontsize" : 9.0,
													"id" : "obj-2",
													"maxclass" : "message",
													"numinlets" : 2,
													"numoutlets" : 1,
													"outlettype" : [ "" ],
													"patching_rect" : [ 208.0, 169.0, 24.0, 19.0 ],
													"text" : "dec"
												}

											}
, 											{
												"box" : 												{
													"fontname" : "Arial",
													"fontsize" : 9.0,
													"id" : "obj-3",
													"maxclass" : "message",
													"numinlets" : 2,
													"numoutlets" : 1,
													"outlettype" : [ "" ],
													"patching_rect" : [ 235.0, 169.0, 22.0, 19.0 ],
													"text" : "inc"
												}

											}
, 											{
												"box" : 												{
													"id" : "obj-4",
													"maxclass" : "incdec",
													"numinlets" : 1,
													"numoutlets" : 1,
													"outlettype" : [ "float" ],
													"parameter_enable" : 0,
													"patching_rect" : [ 138.0, 212.0, 15.0, 15.0 ]
												}

											}
, 											{
												"box" : 												{
													"fontname" : "Arial",
													"fontsize" : 9.0,
													"id" : "obj-5",
													"maxclass" : "newobj",
													"numinlets" : 3,
													"numoutlets" : 3,
													"outlettype" : [ "bang", "bang", "" ],
													"patching_rect" : [ 208.0, 143.0, 65.0, 19.0 ],
													"text" : "sel 28 29"
												}

											}
, 											{
												"box" : 												{
													"fontname" : "Arial",
													"fontsize" : 9.0,
													"id" : "obj-6",
													"maxclass" : "newobj",
													"numinlets" : 0,
													"numoutlets" : 4,
													"outlettype" : [ "int", "int", "int", "int" ],
													"patching_rect" : [ 208.0, 91.0, 40.0, 19.0 ],
													"text" : "key"
												}

											}
, 											{
												"box" : 												{
													"comment" : "",
													"id" : "obj-7",
													"index" : 1,
													"maxclass" : "outlet",
													"numinlets" : 1,
													"numoutlets" : 0,
													"patching_rect" : [ 138.0, 299.0, 41.0, 41.0 ]
												}

											}
, 											{
												"box" : 												{
													"comment" : "",
													"id" : "obj-8",
													"index" : 1,
													"maxclass" : "inlet",
													"numinlets" : 0,
													"numoutlets" : 1,
													"outlettype" : [ "int" ],
													"patching_rect" : [ 138.0, 76.0, 15.0, 15.0 ]
												}

											}
 ],
										"lines" : [ 											{
												"patchline" : 												{
													"destination" : [ "obj-4", 0 ],
													"source" : [ "obj-1", 0 ]
												}

											}
, 											{
												"patchline" : 												{
													"destination" : [ "obj-4", 0 ],
													"source" : [ "obj-2", 0 ]
												}

											}
, 											{
												"patchline" : 												{
													"destination" : [ "obj-4", 0 ],
													"source" : [ "obj-3", 0 ]
												}

											}
, 											{
												"patchline" : 												{
													"destination" : [ "obj-7", 0 ],
													"midpoints" : [ 147.0, 298.0 ],
													"source" : [ "obj-4", 0 ]
												}

											}
, 											{
												"patchline" : 												{
													"destination" : [ "obj-2", 0 ],
													"source" : [ "obj-5", 0 ]
												}

											}
, 											{
												"patchline" : 												{
													"destination" : [ "obj-3", 0 ],
													"source" : [ "obj-5", 1 ]
												}

											}
, 											{
												"patchline" : 												{
													"destination" : [ "obj-10", 0 ],
													"order" : 0,
													"source" : [ "obj-6", 0 ]
												}

											}
, 											{
												"patchline" : 												{
													"destination" : [ "obj-5", 0 ],
													"order" : 1,
													"source" : [ "obj-6", 0 ]
												}

											}
, 											{
												"patchline" : 												{
													"destination" : [ "obj-4", 0 ],
													"source" : [ "obj-8", 0 ]
												}

											}
 ]
									}
,
									"patching_rect" : [ 173.949950999999999, 374.75, 49.0, 19.0 ],
									"saved_object_attributes" : 									{
										"description" : "",
										"digest" : "",
										"globalpatchername" : "",
										"tags" : ""
									}
,
									"text" : "p avance"
								}

							}
, 							{
								"box" : 								{
									"allowdrag" : 0,
									"fontsize" : 18.0,
									"id" : "obj-20",
									"items" : [ "SC_multiserver_INIT", ",", "create_load_envs_buff", ",", "create_gran_buff", ",", "create_fft_buff", ",", "obj_rec_buff", ",", "obj_rec_pvoc", ",", "waveshaping_buffs", ",", "vezer_xml2nims", ",", "load_samples", ",", "load_pvoc", ",", "mix_groups_HOA", ",", "notas_electronicas", ",", "mes_1", ",", "mes_2", ",", "mes_3", ",", "mes_4", ",", "mes_5", ",", "mes_6", ",", "mes_7", ",", "mes_8", ",", "mes_9", ",", "mes_10", ",", "mes_11", ",", "mes_12", ",", "mes_13", ",", "mes_14", ",", "mes_15", ",", "mes_16", ",", "mes_17", ",", "mes_18", ",", "mes_19", ",", "mes_20", ",", "mes_21", ",", "mes_22", ",", "mes_23", ",", "mes_24", ",", "mes_25", ",", "mes_26", ",", "mes_27", ",", "mes_28", ",", "mes_29", ",", "mes_30", ",", "mes_31", ",", "mes_32", ",", "mes_33", ",", "mes_34", ",", "mes_35", ",", "mes_36", ",", "mes_37", ",", "mes_38", ",", "mes_39", ",", "mes_40", ",", "mes_41", ",", "mes_42", ",", "mes_43", ",", "mes_44", ",", "mes_45", ",", "mes_46", ",", "mes_47", ",", "mes_48", ",", "mes_49", ",", "mes_50", ",", "mes_51", ",", "mes_52", ",", "mes_53", ",", "mes_54", ",", "mes_55", ",", "mes_56", ",", "mes_57", ",", "mes_58", ",", "mes_59", ",", "mes_60", ",", "mes_61", ",", "mes_62", ",", "mes_63", ",", "mes_64", ",", "mes_65", ",", "mes_66", ",", "mes_67", ",", "mes_68", ",", "mes_69", ",", "mes_70", ",", "mes_71", ",", "mes_72", ",", "mes_73", ",", "mes_74", ",", "mes_75", ",", "mes_76", ",", "mes_77", ",", "mes_78", ",", "mes_79", ",", "mes_80", ",", "mes_81", ",", "mes_82", ",", "mes_83", ",", "mes_84", ",", "mes_85", ",", "mes_86", ",", "mes_87", ",", "mes_88", ",", "mes_89", ",", "mes_90", ",", "mes_91", ",", "mes_92", ",", "mes_93", ",", "mes_94", ",", "mes_95", ",", "mes_96", ",", "mes_97", ",", "mes_98", ",", "mes_99", ",", "mes_100", ",", "mes_101", ",", "mes_102", ",", "mes_103", ",", "mes_104", ",", "mes_105", ",", "mes_106", ",", "mes_107", ",", "mes_108", ",", "mes_109", ",", "mes_110", ",", "mes_111", ",", "mes_112", ",", "mes_113", ",", "mes_114", ",", "mes_115", ",", "mes_116", ",", "mes_117", ",", "mes_118", ",", "mes_119", ",", "mes_120", ",", "mes_121", ",", "mes_122", ",", "mes_123", ",", "mes_124", ",", "mes_125", ",", "mes_126", ",", "mes_127", ",", "mes_128", ",", "mes_129", ",", "mes_130", ",", "mes_131", ",", "mes_132", ",", "mes_133", ",", "mes_134", ",", "mes_135", ",", "mes_136", ",", "mes_137", ",", "mes_138", ",", "mes_139", ",", "mes_140", ",", "mes_141", ",", "mes_142", ",", "mes_143", ",", "mes_144", ",", "mes_145", ",", "mes_146", ",", "mes_147", ",", "mes_148", ",", "mes_149", ",", "mes_150", ",", "mes_151", ",", "mes_152", ",", "mes_153", ",", "mes_154", ",", "mes_155", ",", "mes_156", ",", "mes_157", ",", "mes_158", ",", "mes_159", ",", "mes_160", ",", "mes_161", ",", "mes_162", ",", "mes_163", ",", "mes_164", ",", "mes_165", ",", "mes_166", ",", "mes_167", ",", "mes_168", ",", "mes_169", ",", "mes_170", ",", "mes_171", ",", "mes_172", ",", "mes_173", ",", "mes_174", ",", "mes_175", ",", "mes_176", ",", "mes_177", ",", "mes_178", ",", "mes_179", ",", "mes_180", ",", "mes_181", ",", "mes_182", ",", "mes_183", ",", "mes_184", ",", "mes_185", ",", "mes_186", ",", "mes_187", ",", "mes_188", ",", "mes_189", ",", "mes_190", ",", "mes_191", ",", "mes_192", ",", "mes_193", ",", "mes_194", ",", "mes_195", ",", "mes_196", ",", "mes_197", ",", "mes_198", ",", "mes_199", ",", "mes_200", ",", "mes_201", ",", "mes_202", ",", "mes_203", ",", "mes_204", ",", "mes_205", ",", "mes_206", ",", "mes_207", ",", "mes_208", ",", "mes_209", ",", "mes_210", ",", "mes_211", ",", "mes_212", ",", "mes_213", ",", "mes_214", ",", "mes_215", ",", "mes_216", ",", "mes_217", ",", "mes_218", ",", "mes_219", ",", "mes_220", ",", "mes_221", ",", "mes_222", ",", "mes_223", ",", "mes_224", ",", "mes_225", ",", "mes_226", ",", "mes_227", ",", "mes_228", ",", "mes_229", ",", "mes_230", ",", "mes_231", ",", "mes_232", ",", "mes_233", ",", "mes_234", ",", "mes_235", ",", "mes_236", ",", "mes_237", ",", "mes_238", ",", "mes_239", ",", "mes_240", ",", "mes_241", ",", "mes_242", ",", "mes_243", ",", "mes_244", ",", "mes_245", ",", "mes_246", ",", "mes_247", ",", "mes_248", ",", "mes_249", ",", "mes_250", ",", "mes_251", ",", "mes_252", ",", "mes_253", ",", "mes_254", ",", "mes_255", ",", "mes_256", ",", "mes_257", ",", "mes_258", ",", "mes_259", ",", "mes_260", ",", "mes_261", ",", "mes_262", ",", "mes_263", ",", "mes_264", ",", "mes_265", ",", "mes_266", ",", "mes_267", ",", "mes_268", ",", "mes_269", ",", "mes_270", ",", "mes_271", ",", "mes_272", ",", "mes_273", ",", "mes_274", ",", "mes_275", ",", "mes_276", ",", "mes_277", ",", "mes_278", ",", "mes_279", ",", "mes_280", ",", "mes_281", ",", "mes_282", ",", "mes_283", ",", "mes_284", ",", "mes_285", ",", "mes_286", ",", "mes_287", ",", "mes_288", ",", "mes_289", ",", "mes_290", ",", "mes_291", ",", "mes_292", ",", "mes_293", ",", "mes_294", ",", "mes_295", ",", "mes_296", ",", "mes_297", ",", "mes_298", ",", "mes_299", ",", "mes_300", ",", "mes_301", ",", "mes_302", ",", "mes_303", ",", "mes_304", ",", "mes_305", ",", "mes_306", ",", "mes_307", ",", "mes_308", ",", "mes_309", ",", "mes_310", ",", "mes_311", ",", "mes_312", ",", "mes_313", ",", "mes_314", ",", "mes_315", ",", "mes_316", ",", "mes_317", ",", "mes_318", ",", "mes_319", ",", "mes_320", ",", "mes_321", ",", "mes_322", ",", "mes_323", ",", "mes_324", ",", "mes_325", ",", "mes_326", ",", "mes_327", ",", "mes_328", ",", "mes_329", ",", "mes_330", ",", "mes_331", ",", "mes_332", ",", "mes_333", ",", "mes_334", ",", "mes_335", ",", "mes_336", ",", "mes_337", ",", "mes_338", ",", "mes_339", ",", "mes_340", ",", "mes_341", ",", "mes_342", ",", "mes_343", ",", "mes_344", ",", "mes_345", ",", "mes_346", ",", "mes_347", ",", "mes_348", ",", "mes_349", ",", "mes_350", ",", "mes_351", ",", "mes_352", ",", "mes_353", ",", "mes_354", ",", "mes_355", ",", "mes_356", ",", "mes_357", ",", "mes_358", ",", "mes_359", ",", "mes_360", ",", "mes_361", ",", "mes_362", ",", "mes_363", ",", "mes_364", ",", "mes_365", ",", "mes_366", ",", "mes_367", ",", "mes_368", ",", "mes_369", ",", "mes_370", ",", "mes_371", ",", "mes_372", ",", "mes_373", ",", "mes_374", ",", "mes_375", ",", "mes_376", ",", "mes_377", ",", "mes_378", ",", "mes_379", ",", "mes_380", ",", "mes_381", ",", "mes_382", ",", "mes_383", ",", "mes_384", ",", "mes_385", ",", "mes_386", ",", "mes_387", ",", "mes_388", ",", "mes_389", ",", "mes_390", ",", "mes_391", ",", "mes_392", ",", "mes_393", ",", "mes_394", ",", "mes_395", ",", "mes_396", ",", "mes_397", ",", "mes_398", ",", "mes_399", ",", "mes_400", ",", "mes_401", ",", "mes_402", ",", "mes_403", ",", "mes_404", ",", "mes_405", ",", "mes_406", ",", "mes_407", ",", "mes_408", ",", "mes_409", ",", "mes_410", ",", "mes_411", ",", "mes_412", ",", "mes_413", ",", "mes_414", ",", "mes_415", ",", "mes_416", ",", "mes_417", ",", "mes_418", ",", "mes_419", ",", "mes_420", ",", "mes_421", ",", "mes_422", ",", "mes_423", ",", "mes_424", ",", "mes_425", ",", "mes_426", ",", "mes_427", ",", "mes_428", ",", "mes_429", ",", "mes_430", ",", "mes_431", ",", "mes_432", ",", "mes_433", ",", "mes_434", ",", "mes_435", ",", "mes_436", ",", "mes_437", ",", "mes_438", ",", "mes_439", ",", "mes_440", ",", "mes_441", ",", "mes_442", ",", "mes_443", ",", "mes_444", ",", "mes_445", ",", "mes_446", ",", "mes_447", ",", "mes_448", ",", "mes_449", ",", "mes_450", ",", "mes_451", ",", "mes_452", ",", "mes_453", ",", "mes_454", ",", "mes_455", ",", "mes_456", ",", "mes_457", ",", "mes_458", ",", "mes_459", ",", "mes_460", ",", "mes_461", ",", "mes_462", ",", "mes_463", ",", "mes_464", ",", "mes_465", ",", "mes_466", ",", "mes_467", ",", "mes_468", ",", "mes_469", ",", "mes_470", ",", "mes_471", ",", "mes_472", ",", "mes_473", ",", "mes_474", ",", "mes_475", ",", "mes_476", ",", "mes_477", ",", "mes_478", ",", "mes_479", ",", "mes_480", ",", "mes_481", ",", "mes_482", ",", "mes_483", ",", "mes_484", ",", "mes_485", ",", "mes_486", ",", "mes_487", ",", "mes_488", ",", "mes_489", ",", "mes_490", ",", "mes_491", ",", "mes_492", ",", "mes_493", ",", "mes_494", ",", "mes_495", ",", "mes_496", ",", "mes_497", ",", "mes_498", ",", "mes_499", ",", "mes_500", ",", "mes_501", ",", "mes_502", ",", "mes_503", ",", "mes_504", ",", "mes_505", ",", "mes_506", ",", "mes_507", ",", "mes_508", ",", "mes_509", ",", "mes_510", ",", "mes_511", ",", "mes_512", ",", "mes_513", ",", "mes_514", ",", "mes_515", ",", "mes_516", ",", "mes_517", ",", "mes_518", ",", "mes_519", ",", "mes_520", ",", "mes_521", ",", "mes_522" ],
									"maxclass" : "umenu",
									"numinlets" : 1,
									"numoutlets" : 3,
									"outlettype" : [ "int", "", "" ],
									"parameter_enable" : 0,
									"patching_rect" : [ 105.650024000000002, 412.5, 193.699950999999999, 29.0 ]
								}

							}
, 							{
								"box" : 								{
									"comment" : "",
									"id" : "obj-47",
									"index" : 1,
									"maxclass" : "inlet",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 105.650024000000002, 40.0, 25.0, 25.0 ]
								}

							}
, 							{
								"box" : 								{
									"comment" : "",
									"id" : "obj-49",
									"index" : 2,
									"maxclass" : "inlet",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "symbol" ],
									"patching_rect" : [ 195.5, 40.0, 25.0, 25.0 ]
								}

							}
, 							{
								"box" : 								{
									"comment" : "",
									"id" : "obj-51",
									"index" : 1,
									"maxclass" : "outlet",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 173.949951171875, 587.5, 25.0, 25.0 ]
								}

							}
 ],
						"lines" : [ 							{
								"patchline" : 								{
									"destination" : [ "obj-20", 0 ],
									"order" : 1,
									"source" : [ "obj-12", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-42", 0 ],
									"order" : 0,
									"source" : [ "obj-12", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-52", 0 ],
									"midpoints" : [ 115.150024000000002, 439.5, 115.150024000000002, 448.5, 51.650024000000002, 448.5, 51.650024000000002, 332.5, 183.449950999999999, 332.5 ],
									"source" : [ "obj-20", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-44", 0 ],
									"source" : [ "obj-42", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-45", 0 ],
									"source" : [ "obj-44", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-51", 0 ],
									"source" : [ "obj-45", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-45", 0 ],
									"source" : [ "obj-46", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-20", 0 ],
									"order" : 1,
									"source" : [ "obj-47", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-42", 0 ],
									"order" : 0,
									"source" : [ "obj-47", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-12", 0 ],
									"source" : [ "obj-49", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-20", 0 ],
									"order" : 1,
									"source" : [ "obj-52", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-46", 0 ],
									"order" : 0,
									"source" : [ "obj-52", 0 ]
								}

							}
 ]
					}
,
					"patching_rect" : [ 368.0, 201.0, 125.0, 22.0 ],
					"saved_object_attributes" : 					{
						"description" : "",
						"digest" : "",
						"globalpatchername" : "",
						"tags" : ""
					}
,
					"text" : "p next-event-bidouille"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-12",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patcher" : 					{
						"fileversion" : 1,
						"appversion" : 						{
							"major" : 8,
							"minor" : 5,
							"revision" : 3,
							"architecture" : "x64",
							"modernui" : 1
						}
,
						"classnamespace" : "box",
						"rect" : [ 150.0, 509.0, 370.0, 224.0 ],
						"bglocked" : 0,
						"openinpresentation" : 0,
						"default_fontsize" : 12.0,
						"default_fontface" : 0,
						"default_fontname" : "Arial",
						"gridonopen" : 1,
						"gridsize" : [ 15.0, 15.0 ],
						"gridsnaponopen" : 1,
						"objectsnaponopen" : 1,
						"statusbarvisible" : 2,
						"toolbarvisible" : 1,
						"lefttoolbarpinned" : 0,
						"toptoolbarpinned" : 0,
						"righttoolbarpinned" : 0,
						"bottomtoolbarpinned" : 0,
						"toolbars_unpinned_last_save" : 0,
						"tallnewobj" : 0,
						"boxanimatetime" : 200,
						"enablehscroll" : 1,
						"enablevscroll" : 1,
						"devicewidth" : 0.0,
						"description" : "",
						"digest" : "",
						"tags" : "",
						"style" : "",
						"subpatcher_template" : "",
						"assistshowspatchername" : 0,
						"boxes" : [ 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-9",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 2,
									"outlettype" : [ "bang", "bang" ],
									"patching_rect" : [ 128.0, 58.0, 32.5, 19.0 ],
									"text" : "t b b"
								}

							}
, 							{
								"box" : 								{
									"comment" : "",
									"id" : "obj-8",
									"index" : 1,
									"maxclass" : "inlet",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "bang" ],
									"patching_rect" : [ 128.0, 25.0, 25.0, 25.0 ]
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-7",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 129.0, 158.0, 81.0, 19.0 ],
									"text" : "s antescofo-mess"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-6",
									"maxclass" : "message",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 128.0, 125.0, 42.0, 19.0 ],
									"text" : "getcues"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-1",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 209.0, 47.0, 62.0, 19.0 ],
									"text" : "r labelmenu"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-2",
									"maxclass" : "newobj",
									"numinlets" : 1,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 7.0, 73.0, 78.0, 19.0 ],
									"text" : "prepend append"
								}

							}
, 							{
								"box" : 								{
									"comment" : "",
									"id" : "obj-3",
									"index" : 1,
									"maxclass" : "outlet",
									"numinlets" : 1,
									"numoutlets" : 0,
									"patching_rect" : [ 7.5, 158.0, 15.0, 15.0 ]
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-4",
									"maxclass" : "message",
									"numinlets" : 2,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 152.5, 84.0, 33.0, 19.0 ],
									"text" : "clear"
								}

							}
, 							{
								"box" : 								{
									"fontname" : "Arial",
									"fontsize" : 9.0,
									"id" : "obj-5",
									"maxclass" : "newobj",
									"numinlets" : 0,
									"numoutlets" : 1,
									"outlettype" : [ "" ],
									"patching_rect" : [ 7.0, 21.0, 92.0, 19.0 ],
									"text" : "r antescofo-labels"
								}

							}
 ],
						"lines" : [ 							{
								"patchline" : 								{
									"destination" : [ "obj-4", 0 ],
									"source" : [ "obj-1", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-3", 0 ],
									"source" : [ "obj-2", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-3", 0 ],
									"source" : [ "obj-4", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-2", 0 ],
									"source" : [ "obj-5", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-7", 0 ],
									"source" : [ "obj-6", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-9", 0 ],
									"source" : [ "obj-8", 0 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-4", 0 ],
									"source" : [ "obj-9", 1 ]
								}

							}
, 							{
								"patchline" : 								{
									"destination" : [ "obj-6", 0 ],
									"source" : [ "obj-9", 0 ]
								}

							}
 ]
					}
,
					"patching_rect" : [ 444.5, 107.0, 64.0, 19.0 ],
					"saved_object_attributes" : 					{
						"description" : "",
						"digest" : "",
						"globalpatchername" : "",
						"tags" : ""
					}
,
					"text" : "p LabelMenu"
				}

			}
, 			{
				"box" : 				{
					"allowdrag" : 0,
					"fontsize" : 17.0,
					"id" : "obj-13",
					"items" : [ "SC_multiserver_INIT", ",", "create_load_envs_buff", ",", "create_gran_buff", ",", "create_fft_buff", ",", "obj_rec_buff", ",", "obj_rec_pvoc", ",", "waveshaping_buffs", ",", "vezer_xml2nims", ",", "load_samples", ",", "load_pvoc", ",", "mix_groups_HOA", ",", "notas_electronicas", ",", "mes_1", ",", "mes_2", ",", "mes_3", ",", "mes_4", ",", "mes_5", ",", "mes_6", ",", "mes_7", ",", "mes_8", ",", "mes_9", ",", "mes_10", ",", "mes_11", ",", "mes_12", ",", "mes_13", ",", "mes_14", ",", "mes_15", ",", "mes_16", ",", "mes_17", ",", "mes_18", ",", "mes_19", ",", "mes_20", ",", "mes_21", ",", "mes_22", ",", "mes_23", ",", "mes_24", ",", "mes_25", ",", "mes_26", ",", "mes_27", ",", "mes_28", ",", "mes_29", ",", "mes_30", ",", "mes_31", ",", "mes_32", ",", "mes_33", ",", "mes_34", ",", "mes_35", ",", "mes_36", ",", "mes_37", ",", "mes_38", ",", "mes_39", ",", "mes_40", ",", "mes_41", ",", "mes_42", ",", "mes_43", ",", "mes_44", ",", "mes_45", ",", "mes_46", ",", "mes_47", ",", "mes_48", ",", "mes_49", ",", "mes_50", ",", "mes_51", ",", "mes_52", ",", "mes_53", ",", "mes_54", ",", "mes_55", ",", "mes_56", ",", "mes_57", ",", "mes_58", ",", "mes_59", ",", "mes_60", ",", "mes_61", ",", "mes_62", ",", "mes_63", ",", "mes_64", ",", "mes_65", ",", "mes_66", ",", "mes_67", ",", "mes_68", ",", "mes_69", ",", "mes_70", ",", "mes_71", ",", "mes_72", ",", "mes_73", ",", "mes_74", ",", "mes_75", ",", "mes_76", ",", "mes_77", ",", "mes_78", ",", "mes_79", ",", "mes_80", ",", "mes_81", ",", "mes_82", ",", "mes_83", ",", "mes_84", ",", "mes_85", ",", "mes_86", ",", "mes_87", ",", "mes_88", ",", "mes_89", ",", "mes_90", ",", "mes_91", ",", "mes_92", ",", "mes_93", ",", "mes_94", ",", "mes_95", ",", "mes_96", ",", "mes_97", ",", "mes_98", ",", "mes_99", ",", "mes_100", ",", "mes_101", ",", "mes_102", ",", "mes_103", ",", "mes_104", ",", "mes_105", ",", "mes_106", ",", "mes_107", ",", "mes_108", ",", "mes_109", ",", "mes_110", ",", "mes_111", ",", "mes_112", ",", "mes_113", ",", "mes_114", ",", "mes_115", ",", "mes_116", ",", "mes_117", ",", "mes_118", ",", "mes_119", ",", "mes_120", ",", "mes_121", ",", "mes_122", ",", "mes_123", ",", "mes_124", ",", "mes_125", ",", "mes_126", ",", "mes_127", ",", "mes_128", ",", "mes_129", ",", "mes_130", ",", "mes_131", ",", "mes_132", ",", "mes_133", ",", "mes_134", ",", "mes_135", ",", "mes_136", ",", "mes_137", ",", "mes_138", ",", "mes_139", ",", "mes_140", ",", "mes_141", ",", "mes_142", ",", "mes_143", ",", "mes_144", ",", "mes_145", ",", "mes_146", ",", "mes_147", ",", "mes_148", ",", "mes_149", ",", "mes_150", ",", "mes_151", ",", "mes_152", ",", "mes_153", ",", "mes_154", ",", "mes_155", ",", "mes_156", ",", "mes_157", ",", "mes_158", ",", "mes_159", ",", "mes_160", ",", "mes_161", ",", "mes_162", ",", "mes_163", ",", "mes_164", ",", "mes_165", ",", "mes_166", ",", "mes_167", ",", "mes_168", ",", "mes_169", ",", "mes_170", ",", "mes_171", ",", "mes_172", ",", "mes_173", ",", "mes_174", ",", "mes_175", ",", "mes_176", ",", "mes_177", ",", "mes_178", ",", "mes_179", ",", "mes_180", ",", "mes_181", ",", "mes_182", ",", "mes_183", ",", "mes_184", ",", "mes_185", ",", "mes_186", ",", "mes_187", ",", "mes_188", ",", "mes_189", ",", "mes_190", ",", "mes_191", ",", "mes_192", ",", "mes_193", ",", "mes_194", ",", "mes_195", ",", "mes_196", ",", "mes_197", ",", "mes_198", ",", "mes_199", ",", "mes_200", ",", "mes_201", ",", "mes_202", ",", "mes_203", ",", "mes_204", ",", "mes_205", ",", "mes_206", ",", "mes_207", ",", "mes_208", ",", "mes_209", ",", "mes_210", ",", "mes_211", ",", "mes_212", ",", "mes_213", ",", "mes_214", ",", "mes_215", ",", "mes_216", ",", "mes_217", ",", "mes_218", ",", "mes_219", ",", "mes_220", ",", "mes_221", ",", "mes_222", ",", "mes_223", ",", "mes_224", ",", "mes_225", ",", "mes_226", ",", "mes_227", ",", "mes_228", ",", "mes_229", ",", "mes_230", ",", "mes_231", ",", "mes_232", ",", "mes_233", ",", "mes_234", ",", "mes_235", ",", "mes_236", ",", "mes_237", ",", "mes_238", ",", "mes_239", ",", "mes_240", ",", "mes_241", ",", "mes_242", ",", "mes_243", ",", "mes_244", ",", "mes_245", ",", "mes_246", ",", "mes_247", ",", "mes_248", ",", "mes_249", ",", "mes_250", ",", "mes_251", ",", "mes_252", ",", "mes_253", ",", "mes_254", ",", "mes_255", ",", "mes_256", ",", "mes_257", ",", "mes_258", ",", "mes_259", ",", "mes_260", ",", "mes_261", ",", "mes_262", ",", "mes_263", ",", "mes_264", ",", "mes_265", ",", "mes_266", ",", "mes_267", ",", "mes_268", ",", "mes_269", ",", "mes_270", ",", "mes_271", ",", "mes_272", ",", "mes_273", ",", "mes_274", ",", "mes_275", ",", "mes_276", ",", "mes_277", ",", "mes_278", ",", "mes_279", ",", "mes_280", ",", "mes_281", ",", "mes_282", ",", "mes_283", ",", "mes_284", ",", "mes_285", ",", "mes_286", ",", "mes_287", ",", "mes_288", ",", "mes_289", ",", "mes_290", ",", "mes_291", ",", "mes_292", ",", "mes_293", ",", "mes_294", ",", "mes_295", ",", "mes_296", ",", "mes_297", ",", "mes_298", ",", "mes_299", ",", "mes_300", ",", "mes_301", ",", "mes_302", ",", "mes_303", ",", "mes_304", ",", "mes_305", ",", "mes_306", ",", "mes_307", ",", "mes_308", ",", "mes_309", ",", "mes_310", ",", "mes_311", ",", "mes_312", ",", "mes_313", ",", "mes_314", ",", "mes_315", ",", "mes_316", ",", "mes_317", ",", "mes_318", ",", "mes_319", ",", "mes_320", ",", "mes_321", ",", "mes_322", ",", "mes_323", ",", "mes_324", ",", "mes_325", ",", "mes_326", ",", "mes_327", ",", "mes_328", ",", "mes_329", ",", "mes_330", ",", "mes_331", ",", "mes_332", ",", "mes_333", ",", "mes_334", ",", "mes_335", ",", "mes_336", ",", "mes_337", ",", "mes_338", ",", "mes_339", ",", "mes_340", ",", "mes_341", ",", "mes_342", ",", "mes_343", ",", "mes_344", ",", "mes_345", ",", "mes_346", ",", "mes_347", ",", "mes_348", ",", "mes_349", ",", "mes_350", ",", "mes_351", ",", "mes_352", ",", "mes_353", ",", "mes_354", ",", "mes_355", ",", "mes_356", ",", "mes_357", ",", "mes_358", ",", "mes_359", ",", "mes_360", ",", "mes_361", ",", "mes_362", ",", "mes_363", ",", "mes_364", ",", "mes_365", ",", "mes_366", ",", "mes_367", ",", "mes_368", ",", "mes_369", ",", "mes_370", ",", "mes_371", ",", "mes_372", ",", "mes_373", ",", "mes_374", ",", "mes_375", ",", "mes_376", ",", "mes_377", ",", "mes_378", ",", "mes_379", ",", "mes_380", ",", "mes_381", ",", "mes_382", ",", "mes_383", ",", "mes_384", ",", "mes_385", ",", "mes_386", ",", "mes_387", ",", "mes_388", ",", "mes_389", ",", "mes_390", ",", "mes_391", ",", "mes_392", ",", "mes_393", ",", "mes_394", ",", "mes_395", ",", "mes_396", ",", "mes_397", ",", "mes_398", ",", "mes_399", ",", "mes_400", ",", "mes_401", ",", "mes_402", ",", "mes_403", ",", "mes_404", ",", "mes_405", ",", "mes_406", ",", "mes_407", ",", "mes_408", ",", "mes_409", ",", "mes_410", ",", "mes_411", ",", "mes_412", ",", "mes_413", ",", "mes_414", ",", "mes_415", ",", "mes_416", ",", "mes_417", ",", "mes_418", ",", "mes_419", ",", "mes_420", ",", "mes_421", ",", "mes_422", ",", "mes_423", ",", "mes_424", ",", "mes_425", ",", "mes_426", ",", "mes_427", ",", "mes_428", ",", "mes_429", ",", "mes_430", ",", "mes_431", ",", "mes_432", ",", "mes_433", ",", "mes_434", ",", "mes_435", ",", "mes_436", ",", "mes_437", ",", "mes_438", ",", "mes_439", ",", "mes_440", ",", "mes_441", ",", "mes_442", ",", "mes_443", ",", "mes_444", ",", "mes_445", ",", "mes_446", ",", "mes_447", ",", "mes_448", ",", "mes_449", ",", "mes_450", ",", "mes_451", ",", "mes_452", ",", "mes_453", ",", "mes_454", ",", "mes_455", ",", "mes_456", ",", "mes_457", ",", "mes_458", ",", "mes_459", ",", "mes_460", ",", "mes_461", ",", "mes_462", ",", "mes_463", ",", "mes_464", ",", "mes_465", ",", "mes_466", ",", "mes_467", ",", "mes_468", ",", "mes_469", ",", "mes_470", ",", "mes_471", ",", "mes_472", ",", "mes_473", ",", "mes_474", ",", "mes_475", ",", "mes_476", ",", "mes_477", ",", "mes_478", ",", "mes_479", ",", "mes_480", ",", "mes_481", ",", "mes_482", ",", "mes_483", ",", "mes_484", ",", "mes_485", ",", "mes_486", ",", "mes_487", ",", "mes_488", ",", "mes_489", ",", "mes_490", ",", "mes_491", ",", "mes_492", ",", "mes_493", ",", "mes_494", ",", "mes_495", ",", "mes_496", ",", "mes_497", ",", "mes_498", ",", "mes_499", ",", "mes_500", ",", "mes_501", ",", "mes_502", ",", "mes_503", ",", "mes_504", ",", "mes_505", ",", "mes_506", ",", "mes_507", ",", "mes_508", ",", "mes_509", ",", "mes_510", ",", "mes_511", ",", "mes_512", ",", "mes_513", ",", "mes_514", ",", "mes_515", ",", "mes_516", ",", "mes_517", ",", "mes_518", ",", "mes_519", ",", "mes_520", ",", "mes_521", ",", "mes_522" ],
					"maxclass" : "umenu",
					"numinlets" : 1,
					"numoutlets" : 3,
					"outlettype" : [ "int", "", "" ],
					"parameter_enable" : 0,
					"patching_rect" : [ 11.5, 255.0, 210.0, 27.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 30.0, 189.0, 297.95062255859375, 27.0 ]
				}

			}
, 			{
				"box" : 				{
					"color" : [ 1.0, 0.890196, 0.090196, 1.0 ],
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-14",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 436.0, 543.0, 89.0, 19.0 ],
					"text" : "s antescofo-mess"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 9.0,
					"id" : "obj-19",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 142.0625, 221.5, 58.0, 19.0 ],
					"text" : "visitlabel $1"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-167",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 501.33331298828125, 191.0, 34.0, 22.0 ],
					"text" : "print"
				}

			}
, 			{
				"box" : 				{
					"id" : "obj-166",
					"maxclass" : "newobj",
					"numinlets" : 0,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 501.33331298828125, 123.0, 41.0, 22.0 ],
					"text" : "r print"
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.867, 0.867, 0.867, 1.0 ],
					"bgfillcolor_angle" : 270.0,
					"bgfillcolor_autogradient" : 0.79,
					"bgfillcolor_color" : [ 0.290196, 0.309804, 0.301961, 1.0 ],
					"bgfillcolor_color1" : [ 0.867, 0.867, 0.867, 1.0 ],
					"bgfillcolor_color2" : [ 0.685, 0.685, 0.685, 1.0 ],
					"bgfillcolor_proportion" : 0.39,
					"bgfillcolor_type" : "gradient",
					"fontname" : "Arial",
					"fontsize" : 19.0,
					"gradient" : 0,
					"id" : "obj-240",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 512.5416259765625, 34.0, 147.0, 30.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 198.46710205078125, 14.5, 129.4835205078125, 30.0 ],
					"text" : "ascograph on",
					"textcolor" : [ 0.250979989767075, 0.50196099281311, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.867, 0.867, 0.867, 1.0 ],
					"bgfillcolor_angle" : 270.0,
					"bgfillcolor_autogradient" : 0.79,
					"bgfillcolor_color" : [ 0.290196, 0.309804, 0.301961, 1.0 ],
					"bgfillcolor_color1" : [ 0.867, 0.867, 0.867, 1.0 ],
					"bgfillcolor_color2" : [ 0.685, 0.685, 0.685, 1.0 ],
					"bgfillcolor_proportion" : 0.39,
					"bgfillcolor_type" : "gradient",
					"fontname" : "Arial",
					"fontsize" : 20.0,
					"gradient" : 0,
					"id" : "obj-159",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 150.0, 67.0, 43.0, 31.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 127.76708984375, 14.5, 62.0, 31.0 ],
					"text" : "info",
					"textcolor" : [ 0.18783800303936, 0.227772995829582, 1.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.867, 0.867, 0.867, 1.0 ],
					"bgfillcolor_angle" : 270.0,
					"bgfillcolor_autogradient" : 0.79,
					"bgfillcolor_color" : [ 0.290196, 0.309804, 0.301961, 1.0 ],
					"bgfillcolor_color1" : [ 0.867, 0.867, 0.867, 1.0 ],
					"bgfillcolor_color2" : [ 0.685, 0.685, 0.685, 1.0 ],
					"bgfillcolor_proportion" : 0.39,
					"bgfillcolor_type" : "gradient",
					"fontname" : "Arial",
					"fontsize" : 20.0,
					"gradient" : 0,
					"id" : "obj-160",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 197.800049000000001, 67.0, 55.0, 31.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 30.0, 53.0, 62.0, 31.0 ],
					"text" : "stop",
					"textcolor" : [ 1.0, 0.023574000224471, 0.0, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"bgcolor" : [ 0.867, 0.867, 0.867, 1.0 ],
					"bgfillcolor_angle" : 270.0,
					"bgfillcolor_autogradient" : 0.79,
					"bgfillcolor_color" : [ 0.290196, 0.309804, 0.301961, 1.0 ],
					"bgfillcolor_color1" : [ 0.867, 0.867, 0.867, 1.0 ],
					"bgfillcolor_color2" : [ 0.685, 0.685, 0.685, 1.0 ],
					"bgfillcolor_proportion" : 0.39,
					"bgfillcolor_type" : "gradient",
					"fontname" : "Arial",
					"fontsize" : 33.0,
					"gradient" : 0,
					"id" : "obj-161",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 44.0, 58.0, 87.0, 45.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 30.0, 5.5, 87.0, 45.0 ],
					"text" : "start",
					"textcolor" : [ 0.200000002980232, 0.800000011920929, 0.047058999538422, 1.0 ]
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial",
					"fontsize" : 18.0,
					"id" : "obj-162",
					"maxclass" : "message",
					"numinlets" : 2,
					"numoutlets" : 1,
					"outlettype" : [ "" ],
					"patching_rect" : [ 257.5, 67.0, 56.0, 29.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 119.0, 53.0, 56.0, 29.0 ],
					"text" : "score"
				}

			}
, 			{
				"box" : 				{
					"fontname" : "Arial Black",
					"fontsize" : 14.0,
					"id" : "obj-163",
					"maxclass" : "newobj",
					"numinlets" : 1,
					"numoutlets" : 4,
					"outlettype" : [ "", "float", "symbol", "bang" ],
					"patching_rect" : [ 101.833389282226562, 112.0, 296.0, 28.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 30.0, 96.0, 94.0, 28.0 ],
					"saved_object_attributes" : 					{
						"IncomingOscPort" : 5678,
						"Warning" : 1,
						"ascograph_height" : 768,
						"ascograph_width" : 1024,
						"ascographconf" : [ "localhost", 6789 ],
						"ascographpanel" : 1,
						"ascographpos" : [ 100, 100 ],
						"incomingosc" : 0
					}
,
					"text" : "antescofo~"
				}

			}
, 			{
				"box" : 				{
					"angle" : 0.0,
					"background" : 1,
					"bgcolor" : [ 0.27443, 0.365877, 0.439216, 1.0 ],
					"border" : 1,
					"bordercolor" : [ 0.623529, 0.623529, 0.623529, 1.0 ],
					"id" : "obj-194",
					"maxclass" : "panel",
					"mode" : 0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2331.73193359375, 1046.75, 128.0, 128.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 356.19207763671875, 229.014495849609375, 393.80792236328125, 526.5 ],
					"proportion" : 0.39,
					"shadow" : -2
				}

			}
, 			{
				"box" : 				{
					"angle" : 0.0,
					"background" : 1,
					"bgcolor" : [ 0.27443, 0.365877, 0.439216, 1.0 ],
					"border" : 1,
					"bordercolor" : [ 0.623529, 0.623529, 0.623529, 1.0 ],
					"id" : "obj-197",
					"maxclass" : "panel",
					"mode" : 0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2199.73193359375, 1046.75, 128.0, 128.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 169.26708984375, 229.014495849609375, 191.0164794921875, 526.5 ],
					"proportion" : 0.39,
					"shadow" : -2
				}

			}
, 			{
				"box" : 				{
					"angle" : 0.0,
					"background" : 1,
					"bgcolor" : [ 0.27443, 0.365877, 0.439216, 1.0 ],
					"border" : 1,
					"bordercolor" : [ 0.623529, 0.623529, 0.623529, 1.0 ],
					"id" : "obj-280",
					"maxclass" : "panel",
					"mode" : 0,
					"numinlets" : 1,
					"numoutlets" : 0,
					"patching_rect" : [ 2476.3056640625, 1046.75, 128.0, 128.0 ],
					"presentation" : 1,
					"presentation_rect" : [ 23.567138671875, 229.014495849609375, 146.23291015625, 526.5 ],
					"proportion" : 0.39,
					"shadow" : -2
				}

			}
 ],
		"lines" : [ 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-1", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-35", 0 ],
					"order" : 1,
					"source" : [ "obj-10", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-52", 1 ],
					"order" : 0,
					"source" : [ "obj-10", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-18", 0 ],
					"source" : [ "obj-1000", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-202", 0 ],
					"source" : [ "obj-1001", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-997", 0 ],
					"source" : [ "obj-1002", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1001", 0 ],
					"source" : [ "obj-1004", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-458", 0 ],
					"source" : [ "obj-1005", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1011", 0 ],
					"source" : [ "obj-1006", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-981", 0 ],
					"source" : [ "obj-1006", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1008", 0 ],
					"source" : [ "obj-1007", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-950", 0 ],
					"source" : [ "obj-1008", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1009", 0 ],
					"source" : [ "obj-1011", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1004", 0 ],
					"source" : [ "obj-1012", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-331", 0 ],
					"source" : [ "obj-1014", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1020", 0 ],
					"order" : 2,
					"source" : [ "obj-1015", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1032", 0 ],
					"order" : 0,
					"source" : [ "obj-1015", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-995", 0 ],
					"order" : 1,
					"source" : [ "obj-1015", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1015", 0 ],
					"source" : [ "obj-1019", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-432", 0 ],
					"source" : [ "obj-1020", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-593", 0 ],
					"source" : [ "obj-1025", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-438", 0 ],
					"source" : [ "obj-1033", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-649", 0 ],
					"source" : [ "obj-1034", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-428", 0 ],
					"source" : [ "obj-1035", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-638", 0 ],
					"source" : [ "obj-1036", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1019", 0 ],
					"source" : [ "obj-1037", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-638", 0 ],
					"source" : [ "obj-1038", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1019", 0 ],
					"source" : [ "obj-1039", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-651", 0 ],
					"source" : [ "obj-1040", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-658", 0 ],
					"source" : [ "obj-1042", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-563", 0 ],
					"source" : [ "obj-1043", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-471", 0 ],
					"source" : [ "obj-1044", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-474", 0 ],
					"source" : [ "obj-1045", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-108", 0 ],
					"source" : [ "obj-105", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-114", 0 ],
					"source" : [ "obj-105", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-528", 0 ],
					"source" : [ "obj-1052", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-256", 0 ],
					"source" : [ "obj-106", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-55", 1 ],
					"source" : [ "obj-107", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-236", 0 ],
					"source" : [ "obj-109", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-105", 0 ],
					"source" : [ "obj-111", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-179", 1 ],
					"order" : 1,
					"source" : [ "obj-113", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-191", 0 ],
					"order" : 0,
					"source" : [ "obj-113", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-185", 0 ],
					"source" : [ "obj-114", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-185", 0 ],
					"source" : [ "obj-115", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-110", 0 ],
					"source" : [ "obj-116", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-34", 0 ],
					"source" : [ "obj-119", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-13", 0 ],
					"order" : 2,
					"source" : [ "obj-12", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-178", 0 ],
					"order" : 0,
					"source" : [ "obj-12", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-321", 0 ],
					"order" : 1,
					"source" : [ "obj-12", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-121", 0 ],
					"source" : [ "obj-120", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-168", 0 ],
					"source" : [ "obj-120", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-133", 0 ],
					"source" : [ "obj-121", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-110", 0 ],
					"source" : [ "obj-123", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-168", 0 ],
					"source" : [ "obj-124", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-326", 0 ],
					"source" : [ "obj-126", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-127", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-19", 0 ],
					"source" : [ "obj-13", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-325", 0 ],
					"source" : [ "obj-131", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-151", 0 ],
					"source" : [ "obj-133", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-112", 0 ],
					"source" : [ "obj-134", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-136", 0 ],
					"source" : [ "obj-134", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-135", 0 ],
					"source" : [ "obj-136", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-109", 0 ],
					"order" : 2,
					"source" : [ "obj-137", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-207", 0 ],
					"source" : [ "obj-137", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-209", 0 ],
					"order" : 1,
					"source" : [ "obj-137", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-294", 0 ],
					"order" : 0,
					"source" : [ "obj-137", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-9", 0 ],
					"order" : 3,
					"source" : [ "obj-137", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-176", 0 ],
					"source" : [ "obj-138", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-140", 0 ],
					"source" : [ "obj-139", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-116", 0 ],
					"source" : [ "obj-140", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-142", 0 ],
					"source" : [ "obj-141", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-158", 0 ],
					"source" : [ "obj-141", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-141", 0 ],
					"source" : [ "obj-143", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-142", 0 ],
					"order" : 1,
					"source" : [ "obj-143", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-208", 0 ],
					"order" : 0,
					"source" : [ "obj-143", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-149", 0 ],
					"source" : [ "obj-148", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-500", 0 ],
					"source" : [ "obj-149", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-143", 0 ],
					"source" : [ "obj-150", 5 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-143", 0 ],
					"source" : [ "obj-150", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-193", 1 ],
					"order" : 1,
					"source" : [ "obj-150", 4 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-219", 0 ],
					"order" : 0,
					"source" : [ "obj-150", 4 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-219", 0 ],
					"source" : [ "obj-150", 2 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-185", 0 ],
					"source" : [ "obj-151", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-202", 0 ],
					"source" : [ "obj-152", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-328", 0 ],
					"source" : [ "obj-153", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-106", 0 ],
					"source" : [ "obj-154", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-200", 0 ],
					"source" : [ "obj-155", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-10", 0 ],
					"source" : [ "obj-156", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-144", 0 ],
					"source" : [ "obj-158", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-159", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-199", 0 ],
					"source" : [ "obj-16", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-160", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-161", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"midpoints" : [ 267.0, 109.0, 111.333389282226562, 109.0 ],
					"source" : [ "obj-162", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-12", 0 ],
					"source" : [ "obj-163", 3 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-128", 0 ],
					"source" : [ "obj-163", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-178", 1 ],
					"order" : 1,
					"source" : [ "obj-163", 2 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-65", 1 ],
					"order" : 2,
					"source" : [ "obj-163", 2 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-948", 0 ],
					"order" : 0,
					"source" : [ "obj-163", 2 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-973", 0 ],
					"source" : [ "obj-163", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-326", 0 ],
					"source" : [ "obj-164", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-930", 0 ],
					"source" : [ "obj-166", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-185", 0 ],
					"source" : [ "obj-168", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-205", 0 ],
					"source" : [ "obj-169", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-42", 0 ],
					"source" : [ "obj-17", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-152", 0 ],
					"source" : [ "obj-170", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-115", 0 ],
					"hidden" : 1,
					"midpoints" : [ 8977.5, 506.0, 8968.99981706250037, 495.0, 8958.99981706250037, 440.0, 9630.5, 451.0 ],
					"order" : 0,
					"source" : [ "obj-171", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-140", 1 ],
					"order" : 1,
					"source" : [ "obj-171", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-187", 1 ],
					"hidden" : 1,
					"order" : 2,
					"source" : [ "obj-171", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-150", 0 ],
					"source" : [ "obj-173", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-179", 0 ],
					"source" : [ "obj-175", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-177", 0 ],
					"source" : [ "obj-176", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-13", 0 ],
					"order" : 1,
					"source" : [ "obj-178", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-321", 0 ],
					"order" : 0,
					"source" : [ "obj-178", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-137", 0 ],
					"order" : 0,
					"source" : [ "obj-181", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-206", 0 ],
					"order" : 1,
					"source" : [ "obj-181", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-188", 0 ],
					"source" : [ "obj-182", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-202", 0 ],
					"source" : [ "obj-183", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-185", 0 ],
					"source" : [ "obj-184", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-189", 0 ],
					"source" : [ "obj-186", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-180", 0 ],
					"source" : [ "obj-189", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"hidden" : 1,
					"source" : [ "obj-19", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-189", 1 ],
					"source" : [ "obj-191", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-191", 0 ],
					"source" : [ "obj-192", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-34", 0 ],
					"source" : [ "obj-196", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-34", 0 ],
					"source" : [ "obj-198", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-27", 0 ],
					"source" : [ "obj-199", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-38", 0 ],
					"source" : [ "obj-199", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-240", 0 ],
					"source" : [ "obj-2", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-870", 0 ],
					"source" : [ "obj-20", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-123", 0 ],
					"source" : [ "obj-200", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-34", 0 ],
					"source" : [ "obj-201", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-203", 0 ],
					"source" : [ "obj-204", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-183", 0 ],
					"source" : [ "obj-205", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-9", 0 ],
					"source" : [ "obj-205", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-26", 0 ],
					"source" : [ "obj-207", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-230", 0 ],
					"source" : [ "obj-209", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-877", 0 ],
					"source" : [ "obj-21", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-210", 0 ],
					"order" : 1,
					"source" : [ "obj-211", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-213", 0 ],
					"order" : 0,
					"source" : [ "obj-211", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-221", 0 ],
					"hidden" : 1,
					"source" : [ "obj-212", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-212", 0 ],
					"hidden" : 1,
					"source" : [ "obj-213", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-218", 0 ],
					"source" : [ "obj-214", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-220", 0 ],
					"source" : [ "obj-216", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-185", 0 ],
					"hidden" : 1,
					"source" : [ "obj-217", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-112", 0 ],
					"source" : [ "obj-219", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-134", 0 ],
					"source" : [ "obj-219", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-20", 0 ],
					"source" : [ "obj-22", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-185", 0 ],
					"source" : [ "obj-221", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-214", 1 ],
					"source" : [ "obj-222", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-216", 1 ],
					"source" : [ "obj-223", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-109", 0 ],
					"source" : [ "obj-224", 2 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-315", 0 ],
					"source" : [ "obj-224", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-34", 0 ],
					"source" : [ "obj-224", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-965", 1 ],
					"order" : 1,
					"source" : [ "obj-225", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-969", 0 ],
					"order" : 0,
					"source" : [ "obj-225", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-226", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-229", 0 ],
					"hidden" : 1,
					"source" : [ "obj-227", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-228", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-185", 0 ],
					"source" : [ "obj-229", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-21", 0 ],
					"source" : [ "obj-23", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-264", 0 ],
					"order" : 1,
					"source" : [ "obj-230", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-967", 0 ],
					"order" : 0,
					"source" : [ "obj-230", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-225", 0 ],
					"hidden" : 1,
					"order" : 0,
					"source" : [ "obj-232", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-235", 0 ],
					"order" : 1,
					"source" : [ "obj-232", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-217", 0 ],
					"hidden" : 1,
					"source" : [ "obj-234", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-109", 0 ],
					"order" : 1,
					"source" : [ "obj-235", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-294", 0 ],
					"order" : 0,
					"source" : [ "obj-235", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-230", 0 ],
					"source" : [ "obj-236", 2 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-273", 0 ],
					"source" : [ "obj-236", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-314", 0 ],
					"source" : [ "obj-236", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-131", 0 ],
					"order" : 0,
					"source" : [ "obj-237", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-239", 0 ],
					"order" : 1,
					"source" : [ "obj-237", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-238", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-209", 0 ],
					"order" : 1,
					"source" : [ "obj-239", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-294", 0 ],
					"order" : 0,
					"source" : [ "obj-239", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-9", 0 ],
					"order" : 2,
					"source" : [ "obj-239", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-240", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-202", 0 ],
					"source" : [ "obj-243", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-246", 0 ],
					"source" : [ "obj-244", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-248", 0 ],
					"source" : [ "obj-246", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-256", 0 ],
					"source" : [ "obj-247", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-247", 0 ],
					"source" : [ "obj-248", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-243", 0 ],
					"source" : [ "obj-249", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-254", 0 ],
					"source" : [ "obj-250", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-188", 0 ],
					"order" : 1,
					"source" : [ "obj-252", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-309", 1 ],
					"order" : 0,
					"source" : [ "obj-252", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-259", 0 ],
					"source" : [ "obj-253", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-253", 0 ],
					"source" : [ "obj-254", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-243", 0 ],
					"source" : [ "obj-255", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-243", 0 ],
					"source" : [ "obj-257", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-260", 0 ],
					"order" : 2,
					"source" : [ "obj-258", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-336", 0 ],
					"order" : 1,
					"source" : [ "obj-258", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-961", 1 ],
					"order" : 0,
					"source" : [ "obj-258", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-256", 0 ],
					"source" : [ "obj-259", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-9", 0 ],
					"source" : [ "obj-26", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-335", 1 ],
					"source" : [ "obj-260", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-243", 0 ],
					"source" : [ "obj-262", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-265", 0 ],
					"source" : [ "obj-263", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-258", 0 ],
					"source" : [ "obj-264", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-283", 0 ],
					"source" : [ "obj-264", 2 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-316", 0 ],
					"source" : [ "obj-264", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-267", 0 ],
					"source" : [ "obj-265", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-256", 0 ],
					"source" : [ "obj-266", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-266", 0 ],
					"source" : [ "obj-267", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-269", 0 ],
					"source" : [ "obj-268", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-283", 0 ],
					"source" : [ "obj-269", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-109", 0 ],
					"source" : [ "obj-27", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-256", 0 ],
					"source" : [ "obj-270", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-270", 0 ],
					"source" : [ "obj-272", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-131", 1 ],
					"order" : 0,
					"source" : [ "obj-273", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-262", 0 ],
					"order" : 1,
					"source" : [ "obj-273", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-256", 0 ],
					"source" : [ "obj-274", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-274", 0 ],
					"source" : [ "obj-276", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-276", 0 ],
					"source" : [ "obj-278", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-278", 0 ],
					"source" : [ "obj-279", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-29", 0 ],
					"source" : [ "obj-28", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-230", 0 ],
					"source" : [ "obj-281", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-281", 0 ],
					"source" : [ "obj-282", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-202", 0 ],
					"source" : [ "obj-284", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-284", 0 ],
					"source" : [ "obj-285", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-188", 0 ],
					"source" : [ "obj-286", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-256", 0 ],
					"source" : [ "obj-288", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-288", 0 ],
					"source" : [ "obj-289", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-476", 0 ],
					"source" : [ "obj-29", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-292", 0 ],
					"source" : [ "obj-290", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-289", 0 ],
					"source" : [ "obj-292", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-283", 0 ],
					"source" : [ "obj-294", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-152", 0 ],
					"source" : [ "obj-295", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-188", 0 ],
					"source" : [ "obj-296", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-298", 0 ],
					"source" : [ "obj-297", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-209", 0 ],
					"order" : 1,
					"source" : [ "obj-298", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-27", 0 ],
					"order" : 2,
					"source" : [ "obj-298", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-294", 0 ],
					"order" : 0,
					"source" : [ "obj-298", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-38", 0 ],
					"source" : [ "obj-298", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-14", 0 ],
					"source" : [ "obj-3", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-10", 0 ],
					"source" : [ "obj-30", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-188", 0 ],
					"source" : [ "obj-300", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-322", 0 ],
					"source" : [ "obj-312", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-230", 0 ],
					"source" : [ "obj-314", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-109", 0 ],
					"source" : [ "obj-315", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-283", 0 ],
					"source" : [ "obj-316", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-326", 0 ],
					"source" : [ "obj-319", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-9", 0 ],
					"source" : [ "obj-32", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-312", 0 ],
					"source" : [ "obj-321", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-326", 0 ],
					"source" : [ "obj-325", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-326", 0 ],
					"source" : [ "obj-328", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-209", 0 ],
					"source" : [ "obj-329", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-281", 0 ],
					"source" : [ "obj-329", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-329", 0 ],
					"source" : [ "obj-330", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-284", 0 ],
					"source" : [ "obj-336", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-202", 0 ],
					"source" : [ "obj-34", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-164", 0 ],
					"source" : [ "obj-340", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-31", 1 ],
					"source" : [ "obj-35", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-41", 0 ],
					"source" : [ "obj-35", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-70", 1 ],
					"source" : [ "obj-35", 3 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-72", 0 ],
					"source" : [ "obj-35", 2 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-45", 0 ],
					"order" : 1,
					"source" : [ "obj-36", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-73", 0 ],
					"order" : 0,
					"source" : [ "obj-36", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-109", 0 ],
					"source" : [ "obj-38", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-238", 0 ],
					"source" : [ "obj-39", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-5", 0 ],
					"source" : [ "obj-4", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-39", 0 ],
					"source" : [ "obj-40", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-55", 0 ],
					"source" : [ "obj-41", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-47", 1 ],
					"order" : 0,
					"source" : [ "obj-42", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-50", 1 ],
					"order" : 1,
					"source" : [ "obj-42", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-51", 0 ],
					"order" : 0,
					"source" : [ "obj-42", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-64", 1 ],
					"order" : 1,
					"source" : [ "obj-42", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-420", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-423", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-326", 0 ],
					"source" : [ "obj-424", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-426", 0 ],
					"source" : [ "obj-425", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-424", 0 ],
					"source" : [ "obj-426", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-642", 0 ],
					"source" : [ "obj-428", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-432", 0 ],
					"source" : [ "obj-429", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-651", 0 ],
					"source" : [ "obj-436", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-632", 0 ],
					"source" : [ "obj-438", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-438", 0 ],
					"order" : 1,
					"source" : [ "obj-440", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-649", 0 ],
					"order" : 0,
					"source" : [ "obj-440", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1019", 0 ],
					"order" : 0,
					"source" : [ "obj-441", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-428", 0 ],
					"order" : 2,
					"source" : [ "obj-441", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-638", 0 ],
					"order" : 1,
					"source" : [ "obj-441", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-457", 0 ],
					"source" : [ "obj-453", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-462", 0 ],
					"source" : [ "obj-457", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-13", 0 ],
					"source" : [ "obj-458", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-319", 0 ],
					"source" : [ "obj-460", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-458", 0 ],
					"source" : [ "obj-462", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-663", 0 ],
					"source" : [ "obj-466", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-476", 0 ],
					"source" : [ "obj-468", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-482", 0 ],
					"source" : [ "obj-471", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-24", 0 ],
					"order" : 1,
					"source" : [ "obj-472", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-468", 0 ],
					"order" : 0,
					"source" : [ "obj-472", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-610", 0 ],
					"order" : 2,
					"source" : [ "obj-472", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-479", 0 ],
					"source" : [ "obj-474", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-663", 0 ],
					"source" : [ "obj-475", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-472", 0 ],
					"source" : [ "obj-476", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1049", 0 ],
					"order" : 1,
					"source" : [ "obj-479", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-475", 0 ],
					"order" : 2,
					"source" : [ "obj-479", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-480", 0 ],
					"order" : 0,
					"source" : [ "obj-479", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-48", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-474", 0 ],
					"source" : [ "obj-480", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-471", 0 ],
					"source" : [ "obj-481", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1048", 0 ],
					"order" : 2,
					"source" : [ "obj-482", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-466", 0 ],
					"order" : 1,
					"source" : [ "obj-482", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-481", 0 ],
					"order" : 0,
					"source" : [ "obj-482", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-49", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-3", 0 ],
					"source" : [ "obj-5", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-238", 0 ],
					"order" : 0,
					"source" : [ "obj-500", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-501", 0 ],
					"order" : 1,
					"source" : [ "obj-500", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-500", 1 ],
					"source" : [ "obj-501", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-56", 0 ],
					"source" : [ "obj-51", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-11", 0 ],
					"source" : [ "obj-52", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-471", 0 ],
					"order" : 1,
					"source" : [ "obj-528", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-474", 0 ],
					"order" : 0,
					"source" : [ "obj-528", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-563", 0 ],
					"order" : 2,
					"source" : [ "obj-528", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-658", 0 ],
					"order" : 3,
					"source" : [ "obj-528", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-52", 0 ],
					"source" : [ "obj-54", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-57", 0 ],
					"source" : [ "obj-55", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-58", 0 ],
					"source" : [ "obj-55", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-436", 0 ],
					"order" : 0,
					"source" : [ "obj-557", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-440", 0 ],
					"order" : 2,
					"source" : [ "obj-557", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-441", 0 ],
					"order" : 1,
					"source" : [ "obj-557", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-663", 0 ],
					"source" : [ "obj-558", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-40", 1 ],
					"order" : 0,
					"source" : [ "obj-56", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-53", 1 ],
					"order" : 1,
					"source" : [ "obj-56", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-563", 0 ],
					"source" : [ "obj-561", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1047", 0 ],
					"order" : 2,
					"source" : [ "obj-562", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-558", 0 ],
					"order" : 1,
					"source" : [ "obj-562", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-561", 0 ],
					"order" : 0,
					"source" : [ "obj-562", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-562", 0 ],
					"source" : [ "obj-563", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-40", 0 ],
					"order" : 0,
					"source" : [ "obj-59", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-64", 0 ],
					"order" : 1,
					"source" : [ "obj-59", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-67", 0 ],
					"order" : 2,
					"source" : [ "obj-59", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-458", 0 ],
					"source" : [ "obj-590", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-63", 0 ],
					"source" : [ "obj-60", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-62", 0 ],
					"source" : [ "obj-61", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-778", 0 ],
					"source" : [ "obj-610", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-14", 0 ],
					"source" : [ "obj-62", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-638", 0 ],
					"source" : [ "obj-629", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-14", 0 ],
					"source" : [ "obj-63", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-438", 0 ],
					"source" : [ "obj-631", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1025", 0 ],
					"order" : 1,
					"source" : [ "obj-632", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-494", 0 ],
					"order" : 2,
					"source" : [ "obj-632", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-631", 0 ],
					"order" : 0,
					"source" : [ "obj-632", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1031", 0 ],
					"order" : 1,
					"source" : [ "obj-633", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-629", 0 ],
					"order" : 0,
					"source" : [ "obj-633", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-639", 0 ],
					"order" : 2,
					"source" : [ "obj-633", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-633", 0 ],
					"source" : [ "obj-638", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-432", 0 ],
					"source" : [ "obj-639", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-975", 0 ],
					"source" : [ "obj-64", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-428", 0 ],
					"source" : [ "obj-641", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1030", 0 ],
					"order" : 1,
					"source" : [ "obj-642", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-429", 0 ],
					"order" : 2,
					"source" : [ "obj-642", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-641", 0 ],
					"order" : 0,
					"source" : [ "obj-642", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-649", 0 ],
					"source" : [ "obj-643", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1041", 0 ],
					"order" : 0,
					"source" : [ "obj-644", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-655", 0 ],
					"order" : 1,
					"source" : [ "obj-644", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-656", 0 ],
					"order" : 2,
					"source" : [ "obj-644", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-593", 0 ],
					"source" : [ "obj-646", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-650", 0 ],
					"source" : [ "obj-649", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1029", 0 ],
					"order" : 0,
					"source" : [ "obj-650", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-643", 0 ],
					"order" : 1,
					"source" : [ "obj-650", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-646", 0 ],
					"order" : 2,
					"source" : [ "obj-650", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-644", 0 ],
					"source" : [ "obj-651", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-651", 0 ],
					"source" : [ "obj-655", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-593", 0 ],
					"source" : [ "obj-656", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1046", 0 ],
					"order" : 2,
					"source" : [ "obj-657", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-662", 0 ],
					"order" : 0,
					"source" : [ "obj-657", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-964", 0 ],
					"order" : 1,
					"source" : [ "obj-657", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-657", 0 ],
					"source" : [ "obj-658", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-238", 0 ],
					"source" : [ "obj-66", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-658", 0 ],
					"source" : [ "obj-662", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-587", 0 ],
					"source" : [ "obj-663", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-66", 0 ],
					"source" : [ "obj-67", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-13", 0 ],
					"source" : [ "obj-69", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-799", 0 ],
					"source" : [ "obj-7", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-884", 0 ],
					"source" : [ "obj-71", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-45", 1 ],
					"source" : [ "obj-72", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-73", 1 ],
					"source" : [ "obj-72", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-57", 0 ],
					"order" : 1,
					"source" : [ "obj-74", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-58", 0 ],
					"order" : 0,
					"source" : [ "obj-74", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-71", 0 ],
					"source" : [ "obj-75", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-15", 0 ],
					"source" : [ "obj-778", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-849", 0 ],
					"order" : 3,
					"source" : [ "obj-799", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-856", 0 ],
					"order" : 2,
					"source" : [ "obj-799", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-863", 0 ],
					"order" : 1,
					"source" : [ "obj-799", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-884", 0 ],
					"order" : 0,
					"source" : [ "obj-799", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-850", 0 ],
					"order" : 2,
					"source" : [ "obj-848", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-853", 0 ],
					"order" : 0,
					"source" : [ "obj-848", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-854", 0 ],
					"order" : 1,
					"source" : [ "obj-848", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-848", 0 ],
					"source" : [ "obj-849", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-849", 0 ],
					"source" : [ "obj-851", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-849", 0 ],
					"source" : [ "obj-853", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-778", 0 ],
					"source" : [ "obj-854", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-857", 0 ],
					"order" : 2,
					"source" : [ "obj-855", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-860", 0 ],
					"order" : 0,
					"source" : [ "obj-855", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-861", 0 ],
					"order" : 1,
					"source" : [ "obj-855", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-855", 0 ],
					"source" : [ "obj-856", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-856", 0 ],
					"source" : [ "obj-858", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-856", 0 ],
					"source" : [ "obj-860", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-778", 0 ],
					"source" : [ "obj-861", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-864", 0 ],
					"order" : 2,
					"source" : [ "obj-862", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-867", 0 ],
					"order" : 0,
					"source" : [ "obj-862", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-868", 0 ],
					"order" : 1,
					"source" : [ "obj-862", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-862", 0 ],
					"source" : [ "obj-863", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-863", 0 ],
					"source" : [ "obj-865", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-863", 0 ],
					"source" : [ "obj-867", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-778", 0 ],
					"source" : [ "obj-868", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-871", 0 ],
					"order" : 2,
					"source" : [ "obj-869", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-874", 0 ],
					"order" : 0,
					"source" : [ "obj-869", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-875", 0 ],
					"order" : 1,
					"source" : [ "obj-869", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-869", 0 ],
					"source" : [ "obj-870", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-870", 0 ],
					"source" : [ "obj-872", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-870", 0 ],
					"source" : [ "obj-874", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-778", 0 ],
					"source" : [ "obj-875", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-881", 0 ],
					"order" : 0,
					"source" : [ "obj-876", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-882", 0 ],
					"order" : 1,
					"source" : [ "obj-876", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-876", 0 ],
					"order" : 0,
					"source" : [ "obj-877", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-878", 0 ],
					"order" : 1,
					"source" : [ "obj-877", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-877", 0 ],
					"source" : [ "obj-879", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-877", 0 ],
					"source" : [ "obj-881", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-778", 0 ],
					"source" : [ "obj-882", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-885", 0 ],
					"order" : 0,
					"source" : [ "obj-883", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-888", 0 ],
					"order" : 1,
					"source" : [ "obj-883", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-889", 0 ],
					"order" : 2,
					"source" : [ "obj-883", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-883", 0 ],
					"source" : [ "obj-884", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-884", 0 ],
					"source" : [ "obj-886", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-884", 0 ],
					"source" : [ "obj-888", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-778", 0 ],
					"source" : [ "obj-889", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-215", 1 ],
					"order" : 5,
					"source" : [ "obj-9", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-224", 0 ],
					"order" : 6,
					"source" : [ "obj-9", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-246", 1 ],
					"order" : 2,
					"source" : [ "obj-9", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-254", 1 ],
					"order" : 1,
					"source" : [ "obj-9", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-265", 1 ],
					"order" : 0,
					"source" : [ "obj-9", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-278", 1 ],
					"order" : 3,
					"source" : [ "obj-9", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-292", 1 ],
					"order" : 4,
					"source" : [ "obj-9", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-70", 0 ],
					"source" : [ "obj-92", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-167", 0 ],
					"source" : [ "obj-930", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-947", 0 ],
					"source" : [ "obj-948", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1014", 0 ],
					"source" : [ "obj-950", 3 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-956", 0 ],
					"source" : [ "obj-950", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-975", 0 ],
					"order" : 1,
					"source" : [ "obj-950", 2 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-983", 0 ],
					"order" : 0,
					"source" : [ "obj-950", 2 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-997", 0 ],
					"source" : [ "obj-950", 1 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-951", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-950", 0 ],
					"source" : [ "obj-952", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-951", 0 ],
					"source" : [ "obj-953", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-972", 0 ],
					"source" : [ "obj-956", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-476", 0 ],
					"source" : [ "obj-960", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-126", 0 ],
					"source" : [ "obj-962", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-663", 0 ],
					"source" : [ "obj-964", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-202", 0 ],
					"source" : [ "obj-966", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-225", 1 ],
					"order" : 0,
					"source" : [ "obj-967", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-982", 0 ],
					"order" : 1,
					"source" : [ "obj-967", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-460", 0 ],
					"source" : [ "obj-969", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-66", 1 ],
					"source" : [ "obj-972", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-976", 0 ],
					"source" : [ "obj-975", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-976", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-979", 0 ],
					"source" : [ "obj-977", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-202", 0 ],
					"source" : [ "obj-979", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-980", 0 ],
					"source" : [ "obj-981", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1006", 0 ],
					"source" : [ "obj-982", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-163", 0 ],
					"source" : [ "obj-983", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-985", 0 ],
					"source" : [ "obj-984", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-966", 0 ],
					"source" : [ "obj-985", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-202", 0 ],
					"source" : [ "obj-987", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-987", 0 ],
					"source" : [ "obj-989", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-993", 0 ],
					"source" : [ "obj-992", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-994", 0 ],
					"source" : [ "obj-993", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-202", 0 ],
					"source" : [ "obj-994", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1019", 0 ],
					"source" : [ "obj-995", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-331", 0 ],
					"source" : [ "obj-997", 0 ]
				}

			}
, 			{
				"patchline" : 				{
					"destination" : [ "obj-1000", 0 ],
					"source" : [ "obj-998", 0 ]
				}

			}
 ],
		"dependency_cache" : [ 			{
				"name" : "antescofo~.mxo",
				"type" : "iLaX"
			}
 ],
		"autosave" : 0
	}

}
