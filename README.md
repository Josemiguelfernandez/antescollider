# antescollider
Antescofo programming to control SC server
## step by step installation

1.- Download SuperCollider

https://supercollider.github.io/downloads

2.- Put SuperCollider application (SuperCollider.app) into Applications folder (MacOs). Open SuperCollider and quit, this will create the folder SuperCollider in /Users/username/Library/Application\ Support/

3.- Download SC3plugins and Flucoma library for your OS and architecture (intel/arm) and put into Extensions: /Users/username/Library/Application\ Support/SuperCollider/Extensions

- SC3plugins
    - https://supercollider.github.io/sc3-plugins/
(For M1 you need to compile from sources)
- Flucoma
    - https://github.com/flucoma/flucoma-sc/releases (For M1 use nightly build release)

-vb Ugens
    - https://github.com/v7b1/vb_UGens/releases

4.- Run the install.sh script, it will put the content of SuperCollider_add folder into SuperCollider folder /Users/username/Library/Application\ Support/SuperCollider/ :

* The content of 'TSupport': inside /Users/username/Library/Application\ Support/SuperCollider/ 
* The content 'synthdefs': inside /Users/username/Library/Application\ Support/SuperCollider/synthdefs
* The content of 'SC_Ambitools' : inside /Users/username/Library/Application\ Support/SuperCollider/Extensions
* The content of 'Extensions': inside /Users/username/Library/Application\ Support/SuperCollider/Extensions
* Create a folder named 'Sounds' inside /Users/username/Library/Application\ Support/SuperCollider/

5.- Open 'Demo-Antecollider+traj-lib.asco.txt' score in Sublime Text (or Atom) and edit:

Line 15: The name of audio card you will use
From line 19 to 22 you can instantiate different scsynth servers, in this exemple 4 scsynth in parallel.

6.- Save the 'Demo-Antecollider+traj-lib.asco.txt' score 

7.- Open AntesCollider_Max-interface Max patch

8.- "Load score" (score) Max message 

9.- Select 'Demo-Antecollider+traj-lib.asco.txt'

10.- Press "Start" (green message) in Max patch

11.- Press space bar to advance Antescofo events

- First event 'start_SC_multiserver' launch scsynth servers in the Terminal (you can see if your audio card is correctly initialised)
- Second event 'create_load_envs_buff' generate envelopes in antescofo language and put into SuperCollider server buffers
- Third event 'load_samples' will load samples into SuperCollider servers buffers if there are sound files inside 'sounds' folder in /Users/username/Library/Application\ Support/SuperCollider/sounds/ You need to create 'sounds' folder the first time.
- Fourth event 'mix_groups' creates mix groups for mono or multichannel audio treatment chain
- Fifth event 'mix_groups_HOA' creates High Order Ambisonic mix groups. You can define the order and decoder to be use (binaural or speakers)
- Sixth event 'demo_mix_group' create audio synth chain

### VST plugin

download vstplugin from 
https://git.iem.at/pd/vstplugin/-/releases
and add it in
/Users/username/Library/Application\ Support/SuperCollider/Extensions

### Sublime Text

You can use alt+p in Sublime Text to evaluate portions of text (live coding). To do this you need to download the Package Antescofo from Sublime

### Sclang 

To work with SuperCollider language interface you need to install quarks:

wslib, PopUpTreeMenu, TabbedView2_QT, json

And add this line into startup.scd file (/Users/username/Library/Application\ Support/SuperCollider/startup.scd

{0.1.wait;{T.new;}.defer}.fork;
 
