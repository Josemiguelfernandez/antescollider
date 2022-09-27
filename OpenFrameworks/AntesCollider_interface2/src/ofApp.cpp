#include "ofApp.h"

ofApp::ofApp():
hash(points),
mode(MODE_RADIUS),
radius(DEFAULT_RADIUS),
nearestN(DEFAULT_NEAREST_N)
{
}

//--------------------------------------------------------------
void ofApp::setup(){
    
    
    //    #ifdef TARGET_OPENGLES
    //        shader.load("shadersES2/shader");
    //    #else
    //        if(ofIsGLProgrammableRenderer()){
    //            shader.load("shadersGL3/shader");
    //        }else{
    //            shader.load("shadersGL2/shader");
    //        }
    //    #endif
    //    ofDisableArbTex();
    //    shader.load("shadersGL3/shader");
    
//    ofDisableArbTex();
    ofSetFrameRate(60); // run at 60 fps
    ofBackground(0);
    gui_params.setup(parameters);
    gui_params.setName("parameters");
    gui_params.minimizeAll();
    gui_params.setPosition(10, 288);
    
    
    grain_color.push_back(ofColor(255, 0, 0));
    grain_color.push_back(ofColor(1, 255, 0));
    grain_color.push_back(ofColor(20, 60, 255));
    grain_color.push_back(ofColor(252, 125, 171));
    grain_color.push_back(ofColor(254, 50, 230));
    grain_color.push_back(ofColor(43, 163, 179));
    
    
    //    model.loadModel("kemar_head_5000.dae", 2);
    model.load("male_head.obj", 2);
    //    model.loadModel("speaker2.3ds", 2);
    
    
    
    
    //    model.load("kemar_head_5000.ply");
    
    //model.setRotation(0, 180, 1, 0, 0);
    //model.setScale(0.9, 0.9, 0.9);
    
    receiver.setup(PORT);
    
    // open an outgoing connection to HOST:PORT
    sender.setup(HOST, SEND_PORT);
    
    sender2.setup(HOST2, SEND_PORT2);
    
        ofSetVerticalSync(true);
    
    // this uses depth information for occlusion
    // rather than always drawing things on top of each other
//        ofEnableDepthTest();
    
    // ofBox uses texture coordinates from 0-1, so you can load whatever
    // sized images you want and still use them to texture your box
    // but we have to explicitly normalize our tex coords here
//    ofEnableNormalizedTexCoords();
    
    // loads the OF logo from disk
    //    ofLogo.load("of.png");
    
    // draw the ofBox outlines with some weight
    //    ofSetLineWidth(10);
    
    //    osc_list.assign(osc_size,0.0f);
    //    osc_grains.assign(osc_size,0.0f);
    //osc_grains2.assign(6*4,0.0f); //polyphonie 4 de 5 elements
    //    times.assign(osc_size,0.0f);
    //    polylines.resize(50);
    
    //    grains.resize(50);
    
    osc_list.assign(osc_size,0.0f);
    
    //    osc_traj.resize(4*traj_polyphony);
    //    osc_traj.resize(traj_polyphony*2);
    //    polyspheres.resize(traj_polyphony);
    
    osc_traj.resize(3*100);
    polyspheres.resize(1*100);
    
    
    
    molecules.resize(4*molecules_polyphony);
    sphere_molecules.resize(molecules_polyphony);
    
    speakers_size = 24;
    speakers_sphere.resize(speakers_size);
    osc_speakers.assign(osc_size,0.0f);
    meter_out_levels.assign(speakers_size,0.0f);
    
    hmin = 20;
    hmax = 120;
    dBmin = -70;
    dBmax = 6;
    
    
    osc_grains1.resize(grains_polyphony*6); //polyphonie * 7 elements
    osc_grains2.resize(grains_polyphony*6); //polyphonie * 7 elements
    osc_grains3.resize(grains_polyphony*6); //polyphonie * 7 elements
    osc_grains4.resize(grains_polyphony*6); //polyphonie * 7 elements
    osc_grains5.resize(grains_polyphony*6); //polyphonie * 7 elements
    osc_grains6.resize(grains_polyphony*6); //polyphonie * 7 elements
    
    
    
    
    sphere_grains1.resize(100); //grains_polyphony
    sphere_grains2.resize(grains_polyphony);
    sphere_grains3.resize(grains_polyphony);
    sphere_grains4.resize(grains_polyphony);
    sphere_grains5.resize(grains_polyphony);
    sphere_grains6.resize(grains_polyphony);
    
    // x, y, z, distance(size)
    knn_search_list.resize(4);
    knn_search_list2.resize(4);
    
    //times.assign(times_size,0);
    
    
    // we set up a plane on which the car will move
    plane.set(10000, 10000);
    roadMaterial.setDiffuseColor(ofFloatColor::gray);
    roadMaterial.setShininess(0.01);
    //    plane.rotate(270, 1, 0 , 0);
    plane.move(0, 0, -20);
    //ofSetSmoothLighting(true);
    //light.setDiffuseColor( ofFloatColor(.5, .5, 0.5) );
    //light.setSpecularColor( ofFloatColor(1.f, 1.f, 1.f));
    
    //light2.setDiffuseColor( ofFloatColor( 238.f/255.f, 57.f/255.f, 135.f/255.f ));
    //light2.setSpecularColor(ofFloatColor(.8f, .8f, .9f));
    
    //light3.setDiffuseColor( ofFloatColor(19.f/255.f,94.f/255.f,77.f/255.f) );
    //light3.setSpecularColor( ofFloatColor(18.f/255.f,150.f/255.f,135.f/255.f) );
    
    //    light.setPointLight();
    light.setPosition(200,10,200);
    light2.setPosition(-200,-10,200);
    light3.setPosition(0,-200,-200);
    light4.setPosition(0, 200,-200);
    
    //model.setRotation(0, 180, 1, 0, 0);
    //model.setScale(0.9, 0.9, 0.9);
    
    //    model.setRotation(0, 141, 2.39, 3.3, 3.75);
    model.setRotation(0, 90, 1, 0, 0);
    model.setScale(0.1, 0.1, 0.1);
    model.setPosition(0, 0, -7);
    //model.setPosition(ofGetWidth()*2/6, (float)ofGetHeight() * 0.75 , 0);
    model.disableColors();
    
    startTime = ofGetElapsedTimeMillis();  // get the start time
    //    bTimerReached = false;
    
    //    cone.set( 0, height*2.2 );
    cone.setPosition(0, 150, 0);
    cone.setHeight(300);
    cone.setRadius(20);
    cone.setResolution(20, 1, 1);
    //    cone.setCapColor(ofColor(20, 60, 255));
    
    icoSphere.setResolution(0);
    test_sphere.setResolution(10);
    
    // shininess is a value between 0 - 128, 128 being the most shiny //
    material.setShininess( 120 );
    // the light highlight of the material //
    material.setSpecularColor(ofColor(255, 255, 255, 255));
    
    cam.disableOrtho();
    cam.setRelativeYAxis(true);
    cam.setOrientation(ofVec3f(62, 0, 0));
    cam.setGlobalPosition(ofVec3f(1, -617, 341));
    cam.setDistance(705);
    
    
    firefly = Vec3(ofGetWidth() / 2, ofGetHeight() / 2, 0);
    firefly2 = Vec3(ofGetWidth() / 2, ofGetHeight() / 2, 0);
    
 
//    #ifdef TARGET_OPENGLES
//        rgbaFboFloat.allocate(400, 400, GL_RGBA ); // with alpha, 8 bits red, 8 bits green, 8 bits blue, 8 bits alpha, from 0 to 255 in 256 steps
//        ofLogWarning("ofApp") << "GL_RGBA32F_ARB is not available for OPENGLES.  Using RGBA.";
//    #else
//        rgbaFboFloat.allocate(400, 400, GL_RGBA32F_ARB); // with alpha, 32 bits red, 32 bits green, 32 bits blue, 32 bits alpha, from 0 to 1 in 'infinite' steps
//    #endif
       // trails
    /* allocate and clear fbo */
//    rgbaFboFloat.allocate(ofGetWidth(), ofGetHeight(), GL_RGBA32F_ARB);
//    rgbaFboFloat.begin();
//    ofClear(255,255,255, 0);
//    rgbaFboFloat.end();
    
    fbo.allocate(1024, 768);
    fbo.begin();
    ofClear(255, 255, 255);
    fbo.end();
    
//    rgbaFboFloat.begin();
//    ofClear(255,255,255, 0);
//    rgbaFboFloat.end();
    
}

//--------------------------------------------------------------
void ofApp::setupGui(){
    
    ofBackground(255);
    
    gui.setup("Views");
    //    gui_params.setup(parameters);
    //    gui.setName("Views");
    gui.add(head_status.set("head_status", true));
    gui.add(plane_status.set("plane_status", false));
    gui.add(grid_status.set("grid_status", true));
    gui.add(circle_status.set("circle_status", true));
    gui.add(speakers.set("speakers", false));
    gui.add(g_sphere.set("g_sphere", false));
    gui.add(trajectories.set("trajectories", false));
    gui.add(grains.set("grains", false));
    gui.add(tmolecules.set("tmolecules", false));
    gui.add(list3D.set("list3D", false));
    gui.add(concat3D.set("concat3D", true));
    gui.add(test_3D.set("test_3D", false));
    gui.add(hoa_filter.set("hoa_filter", false));
    //    gui.add(general_state);
    //    gui.add(general_state);
    planet_3D_params.setName("3D_plane_forces");
    planet_3D_params.add(mass_num.set("mass_num",0,0,64));
    planet_3D_params.add(force_x.set("force_x",0,0,100));
    planet_3D_params.add(force_y.set("force_y",0,0,100));
    planet_3D_params.add(force_z.set("force_z",0,0,100));
    planet_3D_params.add(links_length.set("links_length",0,-10,10));
    planet_3D_params.add(links_rigidity.set("links_rigidity",1,-10,10));
    planet_3D_params.add(links_vel.set("links_vel",0,0,10));
    planet_3D_params.add(mass.set("mass",10,0,1000));
    planet_3D_params.add(posX.set("posX",0,-10,10));
    planet_3D_params.add(posY.set("posY",0,-10,10));
    planet_3D_params.add(posZ.set("posZ",0,-10,10));
    planet_3D_params.add(pos_fac.set("pos_fac",1,0,5));
    planet_3D_params.add(baseFreq.set("baseFreq",210,4,1000));
    planet_3D_params.add(disto_freqs.set("disto_freqs",1,0.001,10));
    planet_3D_params.add(reset.set("reset", false));
    parameters.add(planet_3D_params);
    
    
    
    /// ambi_test
    ambi_test.setName("ambi_test");
    ambi_test.add(_x.set("ambiX",0,-1.5,1.5));
    ambi_test.add(_y.set("ambiY",0,-1.5,1.5));
    ambi_test.add(_z.set("ambiZ",0,-1.5,1.5));
    ambi_test.add(theta.set("theta",0, -90, 90));
    ambi_test.add(_r.set("radio",1, 0 ,2));
    parameters.add(ambi_test);
    
    
    rotate_hoa.setName("ambi_rotate");
    rotate_hoa.add(rot_x.set("pitch",0,-180,180));
    rotate_hoa.add(rot_y.set("roll",0,-180,180));
    rotate_hoa.add(rot_z.set("yaw",0,-180,180));
    parameters.add(rotate_hoa);
    
    filter_hoa.setName("hoa_filter");
    filter_hoa.add(filter_az.set("filter_az",0,-180,180));
    filter_hoa.add(filter_ele.set("filter_ele",0,-90,90));
    filter_hoa.add(filter_ele2.set("filter_ele2",0,-90,90));
    parameters.add(filter_hoa);
    
    PM_solveur.setName("PM_solveur");
    PM_solveur.add(D.set("damping",0, -0.1,0.1));
    PM_solveur.add(k.set("k",0, -10,100));
    PM_solveur.add(mass_pos.set("mass_pos",72, 0,144));
    PM_solveur.add(f1.set("force",2,0,30));
    PM_solveur.add(freq.set("freq",30,3,2000));
    PM_solveur.add(list_radius.set("radius",5,1,40));
    PM_solveur.add(volume.set("volume",0,-75,6));
    parameters.add(PM_solveur);
    
    gen_trans.setName("trans_xyz");
    gen_trans.add(trans_x.set("g_x",0,-100,100));
    gen_trans.add(trans_y.set("g_y",0,-100,100));
    gen_trans.add(trans_z.set("g_z",0,-100,100));
    parameters.add(gen_trans);
    
    
    concat_trans.setName("concat_xyz");
    concat_trans.add(concat_x.set("c_x",0,-3*fact,3*fact));
    concat_trans.add(concat_y.set("c_y",0,-3*fact,3*fact));
    concat_trans.add(concat_z.set("c_z",0,-3*fact,3*fact));
    concat_trans.add(concat_radius.set("radius",radius,1,1000));
    concat_trans.add(concat_rand.set("rand", false));
    concat_trans.add(concat_clear.set("clear", false));
    concat_trans.add(concat_speed.set("speed",1,0,20));
    concat_trans.add(concat_ambitus.set("ambitus",1,1,1000));
    parameters.add(concat_trans);
    
    concat_trans2.setName("concat_xyz2");
    concat_trans2.add(concat_x2.set("c_x",0,-3*fact,3*fact));
    concat_trans2.add(concat_y2.set("c_y",0,-3*fact,3*fact));
    concat_trans2.add(concat_z2.set("c_z",0,-3*fact,3*fact));
    concat_trans2.add(concat_radius2.set("radius",radius,1,1000));
    concat_trans2.add(concat_rand2.set("rand", false));
    concat_trans2.add(concat_clear.set("clear", false));
    concat_trans2.add(concat_speed2.set("speed",1,0,20));
    concat_trans2.add(concat_ambitus2.set("ambitus",1,1,1000));
    parameters.add(concat_trans2);
    
    
    gui_params.setup(parameters);
    
    sync.setup((ofParameterGroup&)gui_params.getParameter(),6665,"localhost",6666);
    
    // OSC parameters send/receive
    //    sync.setup((ofParameterGroup&)gui_params.getParameter(),6665,"localhost",6666);
    //    sync.setup((ofParameterGroup&)parameters.getParameter(),6665,"localhost",6667);
    
    
}

//--------------------------------------------------------------
void ofApp::update(){
    
    myTimer = ofGetElapsedTimeMillis();
    
    float time = ofGetElapsedTimef() * concat_speed;
    float time2 = ofGetElapsedTimef() * concat_speed2;
    
    //    firefly.x = ofNoise(time +   0)*fact;
    //    firefly.y = ofNoise(time + 100)*fact;
    //    firefly.z = ofNoise(time + 500)*fact;
    
    
    
    //    firefly.x = ofMap(ofNoise(time +   0), 0, 1, -500, ofGetWidth() + 500);
    //    firefly.y = ofMap(ofNoise(time + 100), 0, 1, -500, ofGetHeight() + 500);
    //    firefly.z = ofMap(ofNoise(time + 500), 0, 1, -1000, 1000);
    
    
    sync.update();
    
    // check for waiting messages
    while(receiver.hasWaitingMessages()){
        // get the next message
        ofxOscMessage m;
        receiver.getNextMessage(m);
        
        
        if(m.getAddress() == "/speakers_pos"){
            osc_size = m.getNumArgs();
            
            if(speakers_sphere.size() != osc_size){ // resize le vecteur
                osc_speakers.resize(osc_size);
            }
            
            for( int i=0; i<osc_size; i++ ){
                osc_speakers[i] = m.getArgAsFloat(i);
                cout << "osc_size " << m.getArgAsFloat(i) << "\n";
            }
        }
        
        if(m.getAddress() == "/master_meter_out"){
            speakers_size = m.getNumArgs();
            
            if(meter_out_levels.size() != speakers_size){ // resize le vecteur
                meter_out_levels.resize(speakers_size);
            }
            
            for( int i=0; i<speakers_size; i++ ){
                meter_out_levels[i] = m.getArgAsFloat(i);
                cout << "meter_out_levels " << i << " " << " " << speakers_size << " " << m.getArgAsFloat(i) << "\n";
            }
        }
        
        if(m.getAddress() == "/of_list3D"){
            osc_size = m.getNumArgs();
            
            //            cout << "osc_size " << osc_size << "\n";
            
            if(osc_list.size() != osc_size){ // resize le vecteur
                osc_list.resize(osc_size);
            }
            
            for( int i=0; i<osc_size; i++ ){
                osc_list[i] = m.getArgAsFloat(i);
            }
        }
        
        if(m.getAddress() == "/knn_search_list"){
            //            osc_size = m.getNumArgs();
            
            //         cout << "osc_size " << m.getNumArgs() << "\n";
            for( int i=0; i<m.getNumArgs(); i++ ){
                knn_search_list[i] = m.getArgAsFloat(i);
            }
        }

        if(m.getAddress() == "/knn_search_list2"){
            //            osc_size = m.getNumArgs();
            
            //         cout << "osc_size " << m.getNumArgs() << "\n";
            for( int i=0; i<m.getNumArgs(); i++ ){
                knn_search_list2[i] = m.getArgAsFloat(i);
            }
        }
        
        if(m.getAddress() == "/of_concat_list_num"){
            
            of_concat_list_counter = 0;
            of_concat_index = 0;
            sphere_index = 0;
            of_concat_list_num = m.getArgAsInt(0);
            of_concat_list_buff_size = m.getArgAsInt(1);
            
            
            cout << "of_concat_list_num " << of_concat_list_num << "\n";
            cout << "of_concat_list_buff_size " << of_concat_list_buff_size << "\n";
            cout << "of_concat_index " << of_concat_index << "\n";
            
//            concat_size = of_concat_list_num * of_concat_list_buff_size; // buffer size
            num_concat = of_concat_list_num/5; // 5 number of dercritption elements
            
            cout << "num_concat " << num_concat << "\n";
            
            concat_list_gate = true;
            mesh.clear();
            sphere_concat.clear();
            sphere_concat.resize(num_concat);
            concat_list.resize(of_concat_list_num);
            mesh.setMode(OF_PRIMITIVE_POINTS);
            
            
        }
        
        if(m.getAddress() == "/of_concat3D"){
            
            //            if(of_concat_index<(of_concat_list_num*1000/5)-3)
            //            {
            //                cout << "of_concat_list_counter00 " << of_concat_list_counter << "\n";
            //                for(int i=of_concat_list_counter*1000; i<(of_concat_list_counter+1)*1000; i += 5)
            if(concat_list_gate)
            {
                for(int i=0; i<of_concat_list_buff_size; i += 5)
                {
                    //                    cout << "of_concat_index " << of_concat_index << "\n";
                    ////
                    //                    cout << "of_concat_list_counter " << of_concat_list_counter << "\n";
                    //                    cout << "index " << i << "\n";
                    //                    cout << "float1 " << m.getArgAsFloat(i) << "\n";
                    //                    cout << "float2 " << m.getArgAsFloat(i+1) << "\n";
                    //                    cout << "float3 " << m.getArgAsFloat(i+2) << "\n";
                    //                    cout << "float4 " << m.getArgAsFloat(i+3) << "\n";
                    //                    cout << "float5 " << m.getArgAsFloat(i+4) << "\n";
                    
                    
                    Vec3 point(m.getArgAsFloat(i), m.getArgAsFloat(i+1), m.getArgAsFloat(i+2));

                    
                    float psize = m.getArgAsFloat(i+3);
                    float pcolor = m.getArgAsFloat(i+4);
                    
//                    int sphere_index_add = i%5+1;
                    
                    //                Vec3 point(ofRandomWidth(), ofRandomHeight(), ofRandom(-500, 500));
                    points.push_back(point);
                    colors.push_back(pcolor);
                    
                    //                glPointSize(15);
                    
                    
                    pointSize.push_back(psize);
                    
                    /////:
    //                ofVec3f pos(mesh.getVertex(i));
                    
                    //                ofColor color;
                    //                color.setHue(222);
                    
    //                ofColor c = ofColor::fromHsb( 0, 255, 255 ); // bright red
    //                c.setHue(colors[i]); // now bright cyan
    //                c.setBrightness(128);
    //                //                ofSetColor(color);
    //                ofSetColor(c);
                    
                    sphere_concat[sphere_index].setPosition(point);
                    sphere_concat[sphere_index].setRadius(psize);
                    
//                    sphere_concat[i].draw();
                    
                    ////
                    
                    mesh.addVertex(point);
                    
//                    of_concat_index += 5;
                    sphere_index += 1;
    //                cout << "of_concat_list_counter2 " << (of_concat_list_num*1000/5) << "\n";
//                    cout << "of_concat_index2 " << of_concat_index << "\n";
//                    cout << "sphere_index " << sphere_index << "\n";
    //                cout << "of_concat_list_num " << of_concat_list_num << "\n";
    //                cout << "of_concat_list_buff_size " << of_concat_list_buff_size << "\n";
                    

                    if(sphere_index == num_concat)
                    {
                        cout << "end " << "\n";
    //                    cout << "of_concat_index3 " << of_concat_index << "\n";
                        hash.buildIndex();
                        concat_list_gate = false;
                        break; // stop if iteration
                    }
                    
                }
            }
//            of_concat_list_counter +=1;
            //            cout << "of_concat_list_counter3 " << (of_concat_list_num*1000/5)-2 << "\n";
            //            cout << "of_concat_index3 " << of_concat_index << "\n";
            //            }
            
        }
        
        //        if(m.getAddress() == "/of_concat3D"){
        //            concat_size = m.getNumArgs();
        //
        //            if(m.getArgTypeName(0) == "s")
        //            {
        ////            concat_type = m.getArgTypeName(0);
        //               if(m.getArgAsString(0) == "clear")
        //               {
        ////                       cout << "clear " << concat_type << "\n";
        //                   mesh.clear();
        //                   sphere_concat.clear();
        //                   points.clear();
        //                   colors.clear();
        //                   pointSize.clear();
        //               }
        //            }
        //            else
        //            {
        //            int num_concat = concat_size/5;
        //            //            cout << "osc_size " << osc_size << "\n";
        //
        //            mesh.clear();
        //            sphere_concat.clear();
        //
        //            if(concat_list.size() != concat_size){ // resize le vecteur
        //                concat_list.resize(concat_size);
        //            }
        //
        //
        //            sphere_concat.resize(num_concat);
        //
        //            mesh.setMode(OF_PRIMITIVE_POINTS);
        //
        //            //            shader.begin();
        //
        //            for( int i=0; i<concat_size; i += 5)
        //            {
        //                    //                concat_list[i] = m.getArgAsFloat(i);
        //
        //                    Vec3 point(m.getArgAsFloat(i), m.getArgAsFloat(i+1), m.getArgAsFloat(i+2));
        //                    float psize = m.getArgAsFloat(i+3);
        //                    float pcolor = m.getArgAsFloat(i+4);
        //
        //                    //                Vec3 point(ofRandomWidth(), ofRandomHeight(), ofRandom(-500, 500));
        //                    points.push_back(point);
        //                    colors.push_back(pcolor);
        //
        //                    //                glPointSize(15);
        //                    mesh.addVertex(point);
        //
        //                    pointSize.push_back(psize);
        //                    //                int pointAttLoc = shader.getAttributeLocation("pointSize");
        //
        //                    // Draw all of the points.
        //                    //                mesh.getVbo().setAttributeData(shader.getAttributeLocation("pointSize"), &pointSize[0], 1, pointSize.size(), GL_DYNAMIC_DRAW);
        //
        //
        //                    //                mesh.getVbo().setAttributeData(shader.getAttributeLocation("point_size"), &pointSize[0], 1, pointSize.size(), GL_DRAW_STATIC, sizeof(float));
        //
        //                    //                mesh.addVertex(ofSpherePrimitive(5, 10));
        //                    //                ofSphere
        //            }
        //                //            shader.end();
        //            hash.buildIndex();
        //        }
        //    }
        
        
        if(m.getAddress() == "/of_traj"){
            //            for( int i=0; i<osc_size; i++ ){
            
            traj_size = osc_traj.size();
            traj_num = m.getNumArgs();
            
            if(traj_size != traj_num){
                polyspheres.resize(traj_num/3);
                osc_traj.resize(traj_num);
                //                                cout << "traj_num " << traj_num << "\n";
                //                                cout << "polyspheres.size " << polyspheres.size() << "\n";
                //                                cout << "osc_traj.size " << osc_traj.size() << "\n";
            }
            //            else // resize le vecteur si change size
            //            {
            //
            //            }
            
            
            for( int i=0; i<traj_num; i++ ){
                osc_traj[i] = m.getArgAsFloat(i);
            }
            
            //
        }
        //
        
        if(m.getAddress() == "/of_molecules"){
            molecules_polyphony = m.getNumArgs();
            
            if(molecules.size() != molecules_polyphony){ // resize le vecteur
                molecules.resize(molecules_polyphony);
            }
            
            
            for( int i=0; i< molecules.size(); i++ ){
                molecules[i] = m.getArgAsFloat(i);
            }
            //            for( int i=0; i<osc_size; i++ ){
            //            int molecules_index = m.getArgAsInt(0);
            //
            //                        cout << "molecules_index " << molecules_index << "\n";
            //                        cout << "x " << m.getArgAsFloat(1) << "\n";
            //                        cout << "y " << m.getArgAsFloat(2) << "\n";
            //                        cout << "z " << m.getArgAsFloat(3) << "\n";
            //
            //            //            osc_traj[index*4] = traj_index;
            //            molecules[molecules_index*4] = molecules_index;
            //            molecules[molecules_index*4+1] = m.getArgAsFloat(1);
            //            molecules[molecules_index*4+2] = m.getArgAsFloat(2);
            //            molecules[molecules_index*4+3] = m.getArgAsFloat(3);
            //
        }
        
        
        //        if(m.getAddress() == "/of_grains"){
        //            //            for( int i=0; i<osc_size; i++ ){
        //            for(int i = 0; i < m.getNumArgs(); i++){
        //                osc_grains[i] = m.getArgAsFloat(i);
        //                //                    cout << "listening for osc messages on port " << m.getArgAsFloat(i) << "\n";
        //
        //            }
        //            //                osc_list[i] = m.getArgAsFloat(i);
        //
        //            //            }
        //            //             cout << "max index " << osc_list[index*4+1] << "\n";
        //        }
        
        if(m.getAddress() == "/of_grains1"){
            
            //            cout << "startTime " << startTime << "\n";
            //            cout << "myTimer " << myTimer << "\n";
            //            cout << "new_time " << myTimer-startTime << "\n";
            
            osc_grains1[grain_index1*6] = m.getArgAsInt(0);
            osc_grains1[grain_index1*6+1] = m.getArgAsFloat(1);
            osc_grains1[grain_index1*6+2] = m.getArgAsFloat(2);
            osc_grains1[grain_index1*6+3] = m.getArgAsFloat(3);
            //            osc_grains1[grain_index1*6+4] = m.getArgAsInt(4);
            osc_grains1[grain_index1*6+4] = myTimer; // start time
            osc_grains1[grain_index1*6+5] = 1; // run timer
            
            grain_index1 = (grain_index1+1) % grains_polyphony;
            
        }
        
        if(m.getAddress() == "/of_grains2"){
            
            osc_grains2[grain_index2*6] = m.getArgAsInt(0);
            osc_grains2[grain_index2*6+1] = m.getArgAsFloat(1);
            osc_grains2[grain_index2*6+2] = m.getArgAsFloat(2);
            osc_grains2[grain_index2*6+3] = m.getArgAsFloat(3);
            //            osc_grains2[grain_index2*6+4] = m.getArgAsInt(4);
            osc_grains2[grain_index2*6+4] = myTimer; // start time
            osc_grains2[grain_index2*6+5] = 1; // run timer
            
            grain_index2 = (grain_index2+1) % grains_polyphony;
        }
        
        if(m.getAddress() == "/of_grains3"){
            
            osc_grains3[grain_index3*6] = m.getArgAsInt(0);
            osc_grains3[grain_index3*6+1] = m.getArgAsFloat(1);
            osc_grains3[grain_index3*6+2] = m.getArgAsFloat(2);
            osc_grains3[grain_index3*6+3] = m.getArgAsFloat(3);
            //            osc_grains3[grain_index3*6+4] = m.getArgAsInt(4);
            osc_grains3[grain_index3*6+4] = myTimer; // start time
            osc_grains3[grain_index3*6+5] = 1; // run timer
            
            grain_index3 = (grain_index3+1) % grains_polyphony;
        }
        
        if(m.getAddress() == "/of_grains4"){
            
            osc_grains4[grain_index4*6] = m.getArgAsInt(0);
            osc_grains4[grain_index4*6+1] = m.getArgAsFloat(1);
            osc_grains4[grain_index4*6+2] = m.getArgAsFloat(2);
            osc_grains4[grain_index4*6+3] = m.getArgAsFloat(3);
            //            osc_grains4[grain_index4*6+4] = m.getArgAsInt(4);
            osc_grains4[grain_index4*6+4] = myTimer; // start time
            osc_grains4[grain_index4*6+5] = 1; // run timer
            
            grain_index4 = (grain_index4+1) % grains_polyphony;
        }
        
        if(m.getAddress() == "/of_grains5"){
            
            osc_grains5[grain_index5*6] = m.getArgAsInt(0);
            osc_grains5[grain_index5*6+1] = m.getArgAsFloat(1);
            osc_grains5[grain_index5*6+2] = m.getArgAsFloat(2);
            osc_grains5[grain_index5*6+3] = m.getArgAsFloat(3);
            //           osc_grains5[grain_index5*6+4] = m.getArgAsInt(4);
            osc_grains5[grain_index5*6+4] = myTimer; // start time
            osc_grains5[grain_index5*6+5] = 1; // run timer
            
            grain_index5 = (grain_index5+1) % grains_polyphony;
            
        }
        
        if(m.getAddress() == "/of_grains6"){
            
            osc_grains6[grain_index6*6] = m.getArgAsInt(0);
            osc_grains6[grain_index6*6+1] = m.getArgAsFloat(1);
            osc_grains6[grain_index6*6+2] = m.getArgAsFloat(2);
            osc_grains6[grain_index6*6+3] = m.getArgAsFloat(3);
            //              osc_grains6[grain_index6*6+4] = m.getArgAsInt(4);
            osc_grains6[grain_index6*6+4] = myTimer; // start time
            osc_grains6[grain_index6*6+5] = 1; // run timer
            
            grain_index6 = (grain_index6+1) % grains_polyphony;
            
        }
        
        if(m.getAddress() == "/head_pos"){
            
            //            head_pos_angle = m.getArgAsFloat(0);
            head_pos_x = m.getArgAsFloat(0);
            head_pos_y = m.getArgAsFloat(1);
            head_pos_z = m.getArgAsFloat(2);
            
            //
            //            cout << "head_pos_x " << head_pos_x << "\n";
            //            cout << "head_pos_y " << head_pos_y << "\n";
            //            cout << "head_pos_z " << head_pos_z << "\n";
            
            
            
        }
    }
    
    if(concat3D)
    {
        if(concat_clear)
        {
            mesh.clear();
            sphere_concat.clear();
            points.resize(0);
            colors.resize(0);
            pointSize.resize(0);
            mesh.clearVertices();
            hash.buildIndex();
        }
        if(concat_rand)
        {
            float rand_concat_x = ofMap(ofNoise(time +   0), 0, 1, -fact-concat_ambitus, fact+concat_ambitus);
            float rand_concat_y = ofMap(ofNoise(time + 100), 0, 1, -fact-concat_ambitus, fact+concat_ambitus);
            float rand_concat_z = ofMap(ofNoise(time + 500), 0, 1, 0, fact-100);
            
            firefly.x = rand_concat_x;
            firefly.y = rand_concat_y;
            firefly.z = rand_concat_z;
            concat_x = rand_concat_x;
            concat_y = rand_concat_y;
            concat_z = rand_concat_z;
        }
        else
        {
            firefly.x = concat_x;
            firefly.y = concat_y;
            firefly.z = concat_z;
        }
        
        if (MODE_RADIUS == mode)
        {
            // An estimate of the number of points we are expecting to find.
            
            // Estimate the volume of our seach envelope as a cube.
            // A cube already overestimates a spherical search space.
            float approxCubicSearchBoxSize = (concat_radius * 2 * concat_radius * 2 * concat_radius * 2);
            
            // Calculate the volume of our total search space as a cube.
            float approxCubicSearchSpaceSize = (ofGetWidth() * ofGetHeight() * 2 * 500);
            
            // Determine the percentage of the total search space we expect to capture.
            float approxPercentageOfTotalPixels = approxCubicSearchBoxSize / approxCubicSearchSpaceSize;
            
            // Assuming an uniform distribution of points in our search space,
            // get a percentage of them.
            std::size_t approximateNumPointsToFind = (concat_size/5) * approxPercentageOfTotalPixels;
            
            searchResults.resize(approximateNumPointsToFind);
            
            hash.findPointsWithinRadius(firefly, concat_radius, searchResults);
            
        }
        else
        {
            searchResults.resize(nearestN);
            
            // NOTE: this method signature is slower that the alternative method
            // signature as this method signature requires extra copies.  This is
            // done to simplify the example and use the same search results data
            // structure.
            hash.findNClosestPoints(firefly, nearestN, searchResults);
        }

        if(concat_rand2)
        {
            float rand_concat_x2 = ofMap(ofNoise(time2 + 100), 0, 1, -fact-concat_ambitus2, fact+concat_ambitus2);
            float rand_concat_y2 = ofMap(ofNoise(time2 + 300), 0, 1, -fact-concat_ambitus2, fact+concat_ambitus2);
            float rand_concat_z2 = ofMap(ofNoise(time2 + 900), 0, 1, 0, fact-100);
            
            firefly2.x = rand_concat_x2;
            firefly2.y = rand_concat_y2;
            firefly2.z = rand_concat_z2;
            concat_x2 = rand_concat_x2;
            concat_y2 = rand_concat_y2;
            concat_z2 = rand_concat_z2;
        }
        else
        {
            firefly2.x = concat_x2;
            firefly2.y = concat_y2;
            firefly2.z = concat_z2;
        }
        
        if (MODE_RADIUS == mode)
        {
            // An estimate of the number of points we are expecting to find.
            
            // Estimate the volume of our seach envelope as a cube.
            // A cube already overestimates a spherical search space.
            float approxCubicSearchBoxSize = (concat_radius2 * 2 * concat_radius2 * 2 * concat_radius2 * 2);
            
            // Calculate the volume of our total search space as a cube.
            float approxCubicSearchSpaceSize = (ofGetWidth() * ofGetHeight() * 2 * 500);
            
            // Determine the percentage of the total search space we expect to capture.
            float approxPercentageOfTotalPixels = approxCubicSearchBoxSize / approxCubicSearchSpaceSize;
            
            // Assuming an uniform distribution of points in our search space,
            // get a percentage of them.
            std::size_t approximateNumPointsToFind = (concat_size/5) * approxPercentageOfTotalPixels;
            
            searchResults2.resize(approximateNumPointsToFind);
            
            hash.findPointsWithinRadius(firefly2, concat_radius2, searchResults2);
            
        }
        else
        {
            searchResults2.resize(nearestN);
            
            // NOTE: this method signature is slower that the alternative method
            // signature as this method signature requires extra copies.  This is
            // done to simplify the example and use the same search results data
            // structure.
            hash.findNClosestPoints(firefly2, nearestN, searchResults2);
        }
    }
    

    
}

////--------------------------------------------------------------
//void ofApp::drawFboTest(){
//    //we clear the fbo if c is pressed.
//    //this completely clears the buffer so you won't see any trails
//    if( ofGetKeyPressed('c') ){
//        ofClear(255,255,255, 0);
//    }
//
//    //some different alpha values for fading the fbo
//    //the lower the number, the longer the trails will take to fade away.
//    fadeAmnt = 40;
//    if(ofGetKeyPressed('1')){
//        fadeAmnt = 1;
//    }else if(ofGetKeyPressed('2')){
//        fadeAmnt = 5;
//    }else if(ofGetKeyPressed('3')){
//        fadeAmnt = 15;
//    }
//
//    //1 - Fade Fbo
//
//    //this is where we fade the fbo
//    //by drawing a rectangle the size of the fbo with a small alpha value, we can slowly fade the current contents of the fbo.
//    ofFill();
//    ofSetColor(255,255,255, fadeAmnt);
//    ofDrawRectangle(0,0,400,400);
//
//    //2 - Draw graphics
//
//    ofNoFill();
//    ofSetColor(255,255,255);
//
//    //we draw a cube in the center of the fbo and rotate it based on time
//    ofPushMatrix();
//    ofTranslate(200,200,0);
//    ofRotateDeg(ofGetElapsedTimef()*30, 1,0,0.5);
//    ofDrawBox(0,0,0,100);
//    ofPopMatrix();
//
//    //also draw based on our mouse position
//    ofFill();
//    ofDrawCircle(ofGetMouseX() % 410, ofGetMouseY(), 8);
//
//    //we move a line across the screen based on the time
//    //the %400 makes the number stay in the 0-400 range.
////    int shiftX   = (ofGetElapsedTimeMillis() / 8 ) % 400;
////
////    ofDrawRectangle(shiftX, rgbaFbo.getHeight()-30, 3, 30);
//
//}
//--------------------------------------------------------------
void ofApp::draw(){
    
    
//    rgbaFboFloat.begin();
//    glClear(GL_DEPTH_BUFFER_BIT);
//    ofSetColor(100,100,100,30);
//
    light.enable();
    light2.enable();
    light3.enable();
    light4.enable();
    
    //    ofBackground(0, 0, 0);
    
    float cloudSize = ofGetWidth() / 4;
    float spacing = 10;
    
    
    
    
    cam.begin();
    ofEnableDepthTest();
    
    if(plane_status)
    {
        roadMaterial.begin();
        plane.draw();
        roadMaterial.end();
    }
    
    if(grid_status)
    {
        ofDrawGrid(50, 10, false, false, false, true);
        //    ofDrawRotationAxes(500, 100);
        //    ofDrawGridPlane(50, 10);
        ofDrawAxis(200);
    }
    
    ofTranslate(0, 0, 50); // translate all graphics
    
    if(circle_status)
    {
        ofNoFill();
        ofSetCircleResolution(100);
        
        ofDrawCircle(0, 0,300);
    }
    
    
    
    //
    if(head_status)
    {
        ofPushMatrix();
        //        model.setRotation(0, slider_x, 0, 0 , 0);
        //        model.setRotation(1, slider_y, 0, 0 , 0);
        //        model.setRotation(2, slider_z, 0, 0 , 0);
        //        model.setRotation(0, 180, slider_x, slider_y, slider_z);
        //        model.setRotation(0, head_pos_angle+90, head_pos_x, head_pos_y, head_pos_z);
        //        ofRotateX(head_pos_x);
        //        ofRotateY(head_pos_y);
        
        //        ofRotate(head_pos_z);
        //        ofRotate(head_pos_x, head_pos_y, head_pos_z);
        ofTranslate(0, 0, 10);
        ofSetColor(135, 107, 225);
        model.drawFaces();
        ofPopMatrix();
    }
    
    // translate osc data from antescofo
    ofPushMatrix();
    ofTranslate(trans_x*fact, trans_y*fact, trans_z*fact);
    
    if(list3D)
    {
        
        for(int i = 0; i < osc_list.size(); i += 3) {
            ofPushMatrix();
            
            ofVec3f pos(osc_list[i]*fact, osc_list[i+1]*fact, osc_list[i+2]*fact);
            
            
            //        float boxSize = maxBoxSize * ofNoise(pos.x, pos.y, pos.z);
            
            //        pos *= cloudSize;
            ofTranslate(pos);
            
            sphere.setPosition(0, 0, 0);
            sphere.setRadius( list_radius ); // 5 antes
            
            if(i==0 || i == osc_list.size()-1){
                ofSetColor(120, 200, 100);
            }else{
                ofSetColor(255);
            };
            //        sphere.setPosition(ofGetWidth()*-1.2, ofGetHeight()*-0.1, 0);
            sphere.draw();
            
            ofPopMatrix();
        }
    }
    
    //    if(concat3D)
    //    {
    //
    //        for(int i = 0; i < concat_list.size(); i += 8) {
    //            ofPushMatrix();
    //
    //            ofVec3f pos(concat_list[i]*fact, concat_list[i+1]*fact, concat_list[i+2]*fact);
    //
    //
    //            //        float boxSize = maxBoxSize * ofNoise(pos.x, pos.y, pos.z);
    //
    //            //        pos *= cloudSize;
    //            ofTranslate(pos);
    //
    //            sphere.setPosition(0, 0, 0);
    //            sphere.setRadius( concat_list[i+3] ); // 5 antes
    //            //            cout << "osc_size " << concat_list[i+1] << "\n";
    //            ofSetColor(concat_list[i+4], concat_list[i+5], concat_list[i+6]);
    //            //            ofSetColor(120, 200, 100);
    //            //            if(i==0 || i == osc_list.size()-1){
    //            //                ofSetColor(120, 200, 100);
    //            //            }else{
    //            //                ofSetColor(concat_list[i+4], concat_list[i+5], concat_list[i+6]);
    //            //            };
    //            //        sphere.setPosition(ofGetWidth()*-1.2, ofGetHeight()*-0.1, 0);
    //            sphere.draw();
    //
    //            ofPopMatrix();
    //        }
    //    }
    if(concat3D)
    {
        ofPushMatrix();
        ofFill();
            for(int i = 0; i < mesh.getNumVertices(); ++i) {
                
                ofColor c = ofColor::fromHsb( 0, 255, 255 ); // bright red
                c.setHue(colors[i]); // now bright cyan
                c.setBrightness(128);
    //            //                ofSetColor(color);
                ofSetColor(c);
                
                sphere_concat[i].draw();
            }
        
        
//                    mesh.draw();
        
        //            shader.end();
        //            ofDisablePointSprites();
        
//        ofFill();
        
        //        for (int i = 0; i < knn_search_list.size(); ++i)
        //        {
        //            ofVec3f pos(knn_search_list[i], knn_search_list[i+1], knn_search_list[i+2]);
        
        // found position trigger segment
        ofVec3f pos(knn_search_list[0], knn_search_list[1], knn_search_list[2]);
        ofVec3f pos2(knn_search_list2[0], knn_search_list2[1], knn_search_list2[2]);
        
        ofSetColor(255,255,0); //, knn_search_list[3] * 255
        //            ofSetColor(0,191,255, knn_search_list[3] * 255);
        ofDrawSphere(pos, 10);
        
        ofSetColor(255, 0, 0); //, knn_search_list[3] * 255
        //            ofSetColor(0,191,255, knn_search_list[3] * 255);
        ofDrawSphere(pos2, 10);
        //        }
        
        
//        ofSetColor(255, 255, 0, 80);
        
        ofxOscMessage m_out;
        ofxOscMessage m_out2;
        m_out.setAddress("/knn_index");
        m_out2.setAddress("/knn_index2");
        
        for (std::size_t i = 0; i < searchResults.size(); ++i)
        {
            float normalizedDistance = ofMap(searchResults[i].second, concat_radius * concat_radius, 0, 0, 1, true);
            
            int search = searchResults[i].first;
            //        int search2 = searchResults[i].second;
            
            ofSetColor(255, 0, 0, normalizedDistance * 255);
            
            //        search = searchResults[i].first;
            //        search2 =searchResults[i].second;
            
            
            ofDrawSphere(points[search], pointSize[search]+3);
            
            m_out.addIntArg(search);
            //        m.addIntArg(search2);
            m_out.addFloatArg(normalizedDistance);
            sender.sendMessage(m_out, false);
            
            //        if(normalizedDistance > 0.5)
            //        {
            //            m_out.addIntArg(search);
            //            //        m.addIntArg(search2);
            //            m_out.addFloatArg(normalizedDistance);
            //            sender.sendMessage(m_out, false);
            //        }
            //        if(search != search_old)
            //        {
            //            cout << "search " << search << "\n";
            //            m.addIntArg(search);
            //            sender.sendMessage(m, false);
            //            search_old = search;
            //        }
            
            
            if (MODE_NEAREST_N == mode)
            {
                ofSetColor(255, 127);
                ofDrawBitmapString(ofToString(i), points[searchResults[i].first]);
            }
        }
        
        if (MODE_RADIUS == mode)
        {
            //                ofxOscMessage m_firefly;
            //                m_firefly.setAddress("/pos");
            //                m_firefly.addFloatArg(firefly.x);
            //                m_firefly.addFloatArg(firefly.y);
            //                m_firefly.addFloatArg(firefly.z);
            //                sender2.sendMessage(m_firefly, false);
            
            ofNoFill();
            ofSetColor(255, 0, 0, 50);
            ofDrawSphere(firefly, concat_radius);
        }
        for (std::size_t i = 0; i < searchResults2.size(); ++i)
        {
            float normalizedDistance = ofMap(searchResults2[i].second, concat_radius2 * concat_radius2, 0, 0, 1, true);
            
            int search2 = searchResults2[i].first;
            //        int search2 = searchResults[i].second;
            
            ofSetColor(255, 255, 0, normalizedDistance * 255);
            
            //        search = searchResults[i].first;
            //        search2 =searchResults[i].second;
            
            
            ofDrawSphere(points[search2], pointSize[search2]+3);
            
            m_out2.addIntArg(search2);
            //        m.addIntArg(search2);
            m_out2.addFloatArg(normalizedDistance);
            sender.sendMessage(m_out2, false);
            
            //        if(normalizedDistance > 0.5)
            //        {
            //            m_out.addIntArg(search);
            //            //        m.addIntArg(search2);
            //            m_out.addFloatArg(normalizedDistance);
            //            sender.sendMessage(m_out, false);
            //        }
            //        if(search != search_old)
            //        {
            //            cout << "search " << search << "\n";
            //            m.addIntArg(search);
            //            sender.sendMessage(m, false);
            //            search_old = search;
            //        }
            
            
            if (MODE_NEAREST_N == mode)
            {
                ofSetColor(255, 127);
                ofDrawBitmapString(ofToString(i), points[searchResults2[i].first]);
            }
        }
        
        if (MODE_RADIUS == mode)
        {
            //                ofxOscMessage m_firefly;
            //                m_firefly.setAddress("/pos");
            //                m_firefly.addFloatArg(firefly.x);
            //                m_firefly.addFloatArg(firefly.y);
            //                m_firefly.addFloatArg(firefly.z);
            //                sender2.sendMessage(m_firefly, false);
            
            ofNoFill();
            ofSetColor(255, 255, 0, 50);
            ofDrawSphere(firefly2, concat_radius2);
        }
        ofPopMatrix();
    }
    //    ofPopMatrix();
   

  
    if(trajectories)
    {
//        ofEnableAlphaBlending();
//        ofPushMatrix();
//        rgbaFboFloat.begin();
//        ofFill();
//        ofDrawRectangle(0,0,ofGetWidth(), ofGetHeight());
//        ofSetColor(255,255,255, 40);
        
//        glClear(GL_DEPTH_BUFFER_BIT);
//        ofSetColor(100,100,100,30);
        for(int i = 0; i < osc_traj.size(); i += 3) {
            //            ofPushMatrix();
            //            ofVec3f pos(osc_traj[i+1]*fact, osc_traj[i+2]*fact, osc_traj[i+3]*fact);
            ofVec3f pos(osc_traj[i]*fact, osc_traj[i+1]*fact, osc_traj[i+2]*fact);
            int index = i/3;
            ofSetColor(255);
            
            //            cout << "num " << i << "\n";
            //            cout << "index " << index << "\n";
            //            cout << "size " << osc_traj.size() << "\n";
            //            cout << "x " << osc_traj[i]*fact << "\n";
            //            cout << "y " << osc_traj[i+1]*fact << "\n";
            //            cout << "z " << osc_traj[i+2]*fact << "\n";
            
            polyspheres[index].setPosition(pos);
            polyspheres[index].setRadius(3);
            polyspheres[index].draw();
            
            //            ofPopMatrix();
        }


//        glClear(GL_DEPTH_BUFFER_BIT);
//        ofSetColor(255,255,255, 40);
//                  rgbaFboFloat.end();
//          rgbaFboFloat.draw(0, 0);
//        ofPopMatrix();
        
    }
    
    if(tmolecules)
    {
//        rgbaFboFloat.begin();
//        ofEnableAlphaBlending();
        // molecules
//        ofPushMatrix();
        for(int i = 0; i < molecules.size(); i += 3) {
            ofVec3f pos(molecules[i]*fact, molecules[i+1]*fact, molecules[i+2]*fact);
            
            int index = i/3;
            
            ofSetColor(120, 120, 120);
            
            sphere_molecules[index].setPosition(pos);
            sphere_molecules[index].setRadius(2);
            sphere_molecules[index].draw();
        }
//                          rgbaFboFloat.end();
//                    ofSetColor( 255, 255, 255 );
//                  rgbaFboFloat.draw(0, 0);
//        ofPopMatrix();
    }
    //    material.begin();
    //    ofFill();
    
    //        if (mode == 3) {
    //            float angle = (ofGetElapsedTimef() * 1.4);
    //            ofVec3f faceNormal;
    //            for (size_t i = 0; i < triangles.size(); i++) {
    //                float frc = ofSignedNoise(angle* (float)i * .1, angle*.05) * 4;
    //                faceNormal = triangles[i].getFaceNormal();
    //                for (int j = 0; j < 3; j++) {
    //                    triangles[i].setVertex(j, triangles[i].getVertex(j) + faceNormal * frc);
    //                }
    //            }
    //            icoSphere.getMesh().setFromTriangles(triangles);
    //        }
    
    
    
    if(grains)
    {
        for(int i = 0; i < osc_grains1.size(); i += 6) {
            
            if(osc_grains1[i] > 0 && (osc_grains1[i+5] == 1))
            {
                float timer = myTimer - osc_grains1[i+4]; // start timer gran_dur
                //                cout << "timer " << timer << "\n";
                
                //            ofSetColor(grain_color[osc_grains1[i+4]]);
                ofSetColor(grain_color[0]);
                
                ofVec3f pos_g1 (osc_grains1[i+1]*fact, osc_grains1[i+2]*fact, osc_grains1[i+3]*fact);
                
                sphere_grains1[grain_index1].setPosition(pos_g1);
                sphere_grains1[grain_index1].setRadius(1.7);
                sphere_grains1[grain_index1].draw();
                
                if(timer >= osc_grains1[i]) { // gran_dur off
                    osc_grains1[i+5] = 0;
                }
            }
        }
        
        for(int i = 0; i < osc_grains2.size(); i += 6) {
            
            if(osc_grains2[i] > 0 && (osc_grains2[i+5] == 1))
            {
                float timer = myTimer - osc_grains2[i+4]; // start timer gran_dur
                //cout << "timer " << timer << "\n";
                
                //            ofSetColor(grain_color[osc_grains2[i+4]]);
                ofSetColor(grain_color[1]);
                
                ofVec3f pos_g2 (osc_grains2[i+1]*fact, osc_grains2[i+2]*fact, osc_grains2[i+3]*fact);
                
                sphere_grains2[grain_index2].setPosition(pos_g2);
                sphere_grains2[grain_index2].setRadius(1.7);
                sphere_grains2[grain_index2].draw();
                
                if(timer >= osc_grains2[i]) { // gran_dur off
                    osc_grains2[i+5] = 0;
                }
            }
        }
        
        for(int i = 0; i < osc_grains3.size(); i += 6) {
            
            if(osc_grains3[i] > 0 && (osc_grains3[i+5] == 1))
            {
                float timer = myTimer - osc_grains3[i+4]; // start timer gran_dur
                //cout << "timer " << timer << "\n";
                
                //            ofSetColor(grain_color[osc_grains3[i+4]]);
                ofSetColor(grain_color[2]);
                
                ofVec3f pos_g3 (osc_grains3[i+1]*fact, osc_grains3[i+2]*fact, osc_grains3[i+3]*fact);
                
                sphere_grains3[grain_index3].setPosition(pos_g3);
                sphere_grains3[grain_index3].setRadius(1.7);
                sphere_grains3[grain_index3].draw();
                
                if(timer >= osc_grains3[i]) { // gran_dur off
                    osc_grains3[i+5] = 0;
                }
            }
        }
        
        for(int i = 0; i < osc_grains4.size(); i += 6) {
            
            if(osc_grains4[i] > 0 && (osc_grains4[i+5] == 1))
            {
                float timer = myTimer - osc_grains4[i+4]; // start timer gran_dur
                //cout << "timer " << timer << "\n";
                
                //            ofSetColor(grain_color[osc_grains4[i+4]]);
                ofSetColor(grain_color[3]);
                
                ofVec3f pos_g2 (osc_grains4[i+1]*fact, osc_grains4[i+2]*fact, osc_grains4[i+3]*fact);
                
                sphere_grains4[grain_index4].setPosition(pos_g2);
                sphere_grains4[grain_index4].setRadius(1.7);
                sphere_grains4[grain_index4].draw();
                
                if(timer >= osc_grains4[i]) { // gran_dur off
                    osc_grains4[i+5] = 0;
                }
            }
        }
        
        for(int i = 0; i < osc_grains5.size(); i += 6) {
            
            if(osc_grains5[i] > 0 && (osc_grains5[i+5] == 1))
            {
                float timer = myTimer - osc_grains5[i+4]; // start timer gran_dur
                //cout << "timer " << timer << "\n";
                
                //            ofSetColor(grain_color[osc_grains5[i+4]]);
                ofSetColor(grain_color[4]);
                
                ofVec3f pos_g2 (osc_grains5[i+1]*fact, osc_grains5[i+2]*fact, osc_grains5[i+3]*fact);
                
                sphere_grains5[grain_index5].setPosition(pos_g2);
                sphere_grains5[grain_index5].setRadius(1.7);
                sphere_grains5[grain_index5].draw();
                
                if(timer >= osc_grains5[i]) { // gran_dur off
                    osc_grains5[i+5] = 0;
                }
            }
        }
        
        for(int i = 0; i < osc_grains6.size(); i += 6) {
            
            if(osc_grains6[i] > 0 && (osc_grains6[i+5] == 1))
            {
                float timer = myTimer - osc_grains6[i+4]; // start timer gran_dur
                //cout << "timer " << timer << "\n";
                
                //            ofSetColor(grain_color[osc_grains6[i+4]]);
                ofSetColor(grain_color[5]);
                
                ofVec3f pos_g2 (osc_grains6[i+1]*fact, osc_grains6[i+2]*fact, osc_grains6[i+3]*fact);
                
                sphere_grains6[grain_index6].setPosition(pos_g2);
                sphere_grains6[grain_index6].setRadius(1.7);
                sphere_grains6[grain_index6].draw();
                
                if(timer >= osc_grains6[i]) { // gran_dur off
                    osc_grains6[i+5] = 0;
                }
            }
        }
    }
    ofPopMatrix();
    
    if(speakers)
    {
        // speakers
        for(int i = 0; i < osc_speakers.size(); i += 3) {
            ofVec3f pos(osc_speakers[i]*fact, osc_speakers[i+1]*fact, osc_speakers[i+2]*fact);
            float level;
            if(i == 0)
            {
                level = LinearToDecibel(meter_out_levels[0]);
            }
            else{
                level = LinearToDecibel(meter_out_levels[i/3]);
            }
            //            material.begin();
            ofFill();
            //            ofSetColor(255, 0, 0);
            //            ofSetColor(1*i+10, 1*i+10, 1*i+10);
            
            ofSetColor((level*hmax*-1 + dBmax*hmax + (level*hmin) - (dBmin*hmin))/(dBmax - dBmin),100,100);
            
            //            ofSetColor(meter_out_levels[(i+1)]*50, 120, 120);
            speakers_sphere[osc_speakers[i]].setResolution(0);
            speakers_sphere[osc_speakers[i]].setPosition(pos);
            speakers_sphere[osc_speakers[i]].setRadius(20);
            //            speakers_sphere[osc_speakers[i]].setCo
            speakers_sphere[osc_speakers[i]].draw();
            
            //            material.end();
            ofNoFill();
            //            ofSetColor(1*i+10, 1*i+10, 1*i+10);
            ofSetColor(120, 120, 120);
            speakers_sphere[osc_speakers[i]].setScale(1.01f);
            speakers_sphere[osc_speakers[i]].drawWireframe();
            speakers_sphere[osc_speakers[i]].setScale(1.f);
            
        }
    }
    
    if(hoa_filter)
    {
        ofPushMatrix();
        ofRotateDeg(filter_ele, 1, 0, 0);
        ofRotateDeg(filter_az, 0, 0, 1);
        ofRotateDeg(filter_ele2, 0, 1, 0);
        //            cone.setCapColor(ofColor(20, 60, 255));
        
        ofSetColor(172, 32, 232, 150);
        //        cone.rotate(filter_ele, 1, 0, 0);
        //        cone.rotate(filter_az, 0, 1, 0);
        //        cone.rotate(filter_ele2, 0, 0, 1);
        //        ofRotateDeg((cos(ofGetElapsedTimef() * 6) + 1)*.5 * 360, 1, 0, 0);
        //        ofRotateDeg((cos(ofGetElapsedTimef() * 6) + 1)*.5 * 360, 0, 0, 1);
        cone.draw();
        ofPopMatrix();
        
    }
    
    //    cout << "myTimer " << myTimer << "\n";
    ofSetColor(255, 20, 10);
    //        test_sphere.setPosition(_x*fact, _y*fact, _z*fact);
    //        test_sphere.setRadius( 5 );
    //    test_sphere.draw();
    
    
    
    //    material.begin();
    ofFill();
    
    //        if (mode == 3) {
    //            float angle = (ofGetElapsedTimef() * 1.4);
    //            ofVec3f faceNormal;
    //            for (size_t i = 0; i < triangles.size(); i++) {
    //                float frc = ofSignedNoise(angle* (float)i * .1, angle*.05) * 4;
    //                faceNormal = triangles[i].getFaceNormal();
    //                for (int j = 0; j < 3; j++) {
    //                    triangles[i].setVertex(j, triangles[i].getVertex(j) + faceNormal * frc);
    //                }
    //            }
    //            icoSphere.getMesh().setFromTriangles(triangles);
    //        }
    
    //     material.end();
    
    if(test_3D)
    {
        
        icoSphere.setPosition(_x*fact, _y*fact, _z*fact);
        
        icoSphere.draw();
        
        
        
        ofSetColor(0, 0, 0);
        icoSphere.setScale(1.01f);
        icoSphere.drawWireframe();
        icoSphere.setScale(1.f);
    }
    
    //    icoSphere.setResolution(1);
    //    icoSphere.setPosition(_x*fact, _y*fact, _z*fact);
    //    icoSphere.rotateDeg(20, 1.0, 0.0, 0.0);
    //    icoSphere.rotateDeg(20, 0, 1.0, 0.0);
    //    icoSphere.draw();
    
    if(g_sphere)
    {
        ofSetColor(120, 200, 150, 100);
        //        global_sphere.setPosition(0, 0, 0);
        global_sphere.setRadius( 300 );
        global_sphere.draw();
    }
    
//        rgbaFboFloat.end();
    

    ofSetColor(255,255,255);
    //    light.draw();
    //    light2.draw();
    //    light3.draw();
    //    light4.draw();
    
    ofDisableDepthTest();
    
    cam.end();
    ofDisableLighting();
    //    light.disable();
    //    light2.disable();
    //    light3.disable();


    
    
    
    ofSetColor(135, 107, 225);
    string info  = "FPS         : "+ofToString(ofGetFrameRate(),0)+"\n";
    //            info += "RelativeYAxis: "+ofToString(cam.getRelativeYAxis(), 0)+"\n";
    //            info += "TranslationKey: "+ofToString(cam.getTranslationKey(), 0)+"\n";
    info += "Position    : "+ofToString(cam.getPosition(), 0)+"\n";
    //    info += "GPosition: "+ofToString(cam.getGlobalPosition(), 0)+"\n";
    info += "Distance    : "+ofToString(cam.getDistance(), 0)+"\n";
    info += "Orientation : "+ofToString(cam.getOrientationEulerDeg(), 0)+"\n";
    
    //    info += "\nPress ' ' to get a new random end time\n";
    
    ofDrawBitmapString(info, 1050, 20);
    //    drawInteractionArea();
    

    
    
}

//--------------------------------------------------------------
void ofApp::drawGui(ofEventArgs & args){
    ofBackground(104, 111, 140);
    gui.draw();
    gui_params.draw();
}

//--------------------------------------------------------------
void ofApp::keyPressed(int key){
    
}

//--------------------------------------------------------------
void ofApp::keyReleased(int key){
    
}

//--------------------------------------------------------------
void ofApp::mouseMoved(int x, int y ){
    
}

//--------------------------------------------------------------
void ofApp::mouseDragged(int x, int y, int button){
    
}

//--------------------------------------------------------------
void ofApp::mousePressed(int x, int y, int button){
    
}

//--------------------------------------------------------------
void ofApp::mouseReleased(int x, int y, int button){
    
}

//--------------------------------------------------------------
void ofApp::mouseEntered(int x, int y){
    
}

//--------------------------------------------------------------
void ofApp::mouseExited(int x, int y){
    
}

//--------------------------------------------------------------
void ofApp::windowResized(int w, int h){
    
}

//--------------------------------------------------------------
void ofApp::gotMessage(ofMessage msg){
    
}

//--------------------------------------------------------------
void ofApp::dragEvent(ofDragInfo dragInfo){
    
}

float ofApp::LinearToDecibel(float amp)
{
    float db;
    
    if (amp != 0.0f)
        db = 20.0f * log10(amp);
    else
        db = -120.0f;  // effectively minus infinity
    
    return db;
}

//--------------------------------------------------------------
//void ofApp::drawInteractionArea(){
//    ofRectangle vp = ofGetCurrentViewport();
//    float r = std::min<float>(vp.width, vp.height) * 0.5f;
//    float x = vp.width * 0.5f;
//    float y = vp.height * 0.5f;
//
//    ofPushStyle();
//    ofSetLineWidth(3);
//    ofSetColor(255, 255, 0);
//    ofNoFill();
//    glDepthMask(false);
//    ofDrawCircle(x, y, r);
//    glDepthMask(true);
//    ofPopStyle();
//}
