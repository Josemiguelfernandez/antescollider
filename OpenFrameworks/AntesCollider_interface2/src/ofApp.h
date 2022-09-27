#pragma once

#pragma once

#include "ofMain.h"
#include "ofxOsc.h"
#include "ofxOscParameterSync.h"
#include "ofxGui.h"
#include "ofxAssimpModelLoader.h"
#include "ofxSpatialHash.h"
#include <math.h>

// listen on port 12345
#define PORT 12345

// send host (aka ip address)
#define HOST "localhost"
#define HOST2 "169.254.157.190"

/// send port
#define SEND_PORT 23456
#define SEND_PORT2 5555

class ofApp : public ofBaseApp{
    
public:
    
    typedef glm::vec2 Vec2;
    typedef glm::vec3 Vec3;
    
    //    drawGui() = default;
    ofApp();
    void setup();
    void update();
    void draw();
    
    void setupGui();
    void drawGui(ofEventArgs & args);
    void init_gui();
    
    void drawFboTest(); // draws some test graphics into the two fbos

    
    void keyPressed(int key);
    void keyReleased(int key);
    void mouseMoved(int x, int y );
    void mouseDragged(int x, int y, int button);
    void mousePressed(int x, int y, int button);
    void mouseReleased(int x, int y, int button);
    void mouseEntered(int x, int y);
    void mouseExited(int x, int y);
    void windowResized(int w, int h);
    void dragEvent(ofDragInfo dragInfo);
    void gotMessage(ofMessage msg);
    
    //            void drawInteractionArea();
    
    float LinearToDecibel(float amp);
    
    //    vector<ofxPanel> gui;
    //
    
    ofxOscReceiver receiver;
    ofxOscSender sender;
    ofxOscSender sender2; // Raph
    
    ofParameterGroup parameters;
    
    ofParameterGroup general_state;
    ofParameterGroup planet_3D_params;
    ofParameterGroup ambi_test;
    ofParameterGroup rotate_hoa;
    ofParameterGroup filter_hoa;
    ofParameterGroup PM_solveur;
    ofParameterGroup gen_trans;
    ofParameterGroup concat_trans;
    ofParameterGroup concat_trans2;
    
    ofParameter<bool> head_status;
    ofParameter<bool> plane_status;
    ofParameter<bool> grid_status;
    ofParameter<bool> circle_status;
    ofParameter<bool> speakers;
    ofParameter<bool> g_sphere;
    ofParameter<bool> trajectories;
    ofParameter<bool> grains;
    ofParameter<bool> tmolecules;
    ofParameter<bool> list3D;
    ofParameter<bool> concat3D;
    ofParameter<bool> test_3D;
    ofParameter<bool> hoa_filter;
    ofParameter<string> screenSize;
    
    ofxPanel gui;
    
    ofxOscParameterSync sync;
    ofParameter<int> mass_num;
    ofParameter<float> force_x;
    ofParameter<float> force_y;
    ofParameter<float> force_z;
    ofParameter<float> links_length;
    ofParameter<float> links_rigidity;
    ofParameter<float> links_visc;
    ofParameter<float> links_vel;
    ofParameter<float> mass;
    ofParameter<float> posX;
    ofParameter<float> posY;
    ofParameter<float> posZ;
    ofParameter<float> pos_fac;
    ofParameter<float> baseFreq;
    ofParameter<float> disto_freqs;
    ofParameter<bool> reset;
    
    ofxPanel gui_params;
    
    //current state of the rotation
    ofQuaternion RotPhi, RotTheta, RotPsi, Rotation;
    
    float head_pos_x;
    float head_pos_y;
    float head_pos_z;
    float head_pos_angle;
    
    ofParameter<float> _x;
    ofParameter<float> _y;
    ofParameter<float> _z;
    ofParameter<float> theta;
    ofParameter<float> _r;
    
    ofParameterGroup parameters_test;
    
    //    test_hoa;
    ofSpherePrimitive test_sphere;
    
    ofxOscParameterSync sync_rotate;
    ofParameter<float> rot_x;
    ofParameter<float> rot_y;
    ofParameter<float> rot_z;
    
    
    ofParameter<float> filter_az;
    ofParameter<float> filter_ele;
    ofParameter<float> filter_ele2;
    
    // Solveur
    ofParameter<float> D;
    ofParameter<float> k;
    ofParameter<int> mass_pos;
    ofParameter<float> f1;
    ofParameter<float> freq;
    ofParameter<int> list_radius;
    ofParameter<int> volume;
    
    // trans xyz
    ofParameter<float> trans_x;
    ofParameter<float> trans_y;
    ofParameter<float> trans_z;
    
    // concat xyz
    ofParameter<float> concat_x;
    ofParameter<float> concat_y;
    ofParameter<float> concat_z;
    ofParameter<float> concat_radius;
    ofParameter<bool> concat_rand;
    ofParameter<bool> concat_clear;
    ofParameter<float> concat_speed;
    ofParameter<float> concat_ambitus;
    
    // concat xyz 2
    ofParameter<float> concat_x2;
    ofParameter<float> concat_y2;
    ofParameter<float> concat_z2;
    ofParameter<float> concat_radius2;
    ofParameter<bool> concat_rand2;
    ofParameter<bool> concat_clear2;
    ofParameter<float> concat_speed2;
    ofParameter<float> concat_ambitus2;
    
    
    int fact = 300; // facteur graphic size
    
    vector<float> osc_list;
    long osc_size = 50;
    
    vector<float> concat_list;
    long concat_size = 50;
    
    vector<float> molecules;
    long molecules_polyphony = 300;
    
    
    
    vector<float> osc_traj;
    long traj_num = 0;
    long traj_size;
    //    int traj_index = 0;
    long traj_polyphony = 0;
    
    
    //    int last_index = 0;
    //    int max_index;
    //    int times_size = 4;
    
    int grains_polyphony = 50;
    
    vector<float> osc_grains1;
    vector<float> osc_grains2;
    vector<float> osc_grains3;
    vector<float> osc_grains4;
    vector<float> osc_grains5;
    vector<float> osc_grains6;
    
    int grain_index1 = 0;
    int grain_index2 = 0;
    int grain_index3 = 0;
    int grain_index4 = 0;
    int grain_index5 = 0;
    int grain_index6 = 0;
    
    
    float startTime; // store when we start time timer
    float myTimer;
    vector <float> times;
    
    ofPolyline polyline;
    //    ofSpherePrimitive sphere;
    //    ofImage ofLogo; // the OF logo
    ofLight light; // creates a light and enables lighting
    ofLight light2;
    ofLight light3;
    ofLight light4;
    
    ofEasyCam cam; // add mouse controls for camera movement
    
    //    vector <ofPolyline> polylines;
    
    vector <ofSpherePrimitive> polyspheres;
    
    vector <ofSpherePrimitive> sphere_grains1;
    vector <ofSpherePrimitive> sphere_grains2;
    vector <ofSpherePrimitive> sphere_grains3;
    vector <ofSpherePrimitive> sphere_grains4;
    vector <ofSpherePrimitive> sphere_grains5;
    vector <ofSpherePrimitive> sphere_grains6;
    
    vector <ofSpherePrimitive> sphere_molecules;
    
    vector <ofColor> grain_color;
    
    ofSpherePrimitive sphere;
    ofSpherePrimitive global_sphere;
    ofPlanePrimitive plane;
    ofMaterial roadMaterial;
    //    ofMesh mesh;
    
    ofxAssimpModelLoader model;
    
    
    
    ofConePrimitive cone;
    
    
    //// para trails
//    ofFbo rgbaFbo; // with alpha
//    int fadeAmnt;
    
    
    //Speakers
    
    long speakers_size;
    
    ofIcoSpherePrimitive icoSphere;
    ofMaterial material;
    
    vector <ofIcoSpherePrimitive> speakers_sphere;
    vector <float> osc_speakers;
    
    vector<float> meter_out_levels;
    
    float hmin; // = 20;
    float hmax; // = 120;
    float dBmin; //=-70;
    float dBmax; // = 6;
    
    
    //// KNN
    
    /// \brief A collection of default values.
    enum
    {
        NUM_POINTS = 10000,
        DEFAULT_RADIUS = 50, // vector units (pixels)
        DEFAULT_NEAREST_N = 200
    };
    
    
    /// \brief Our point collection.
    ///
    /// These points MUST be initialized BEFORE initing the hash.
    std::vector<Vec3> points;
    
    /// \brief The spatial hash specialized for ofVec3f.
    ofx::KDTree<Vec3> hash;
    
    /// \brief The search results specialized for ofVec3f.
    ofx::KDTree<Vec3>::SearchResults searchResults;
    ofx::KDTree<Vec3>::SearchResults searchResults2;
    
    /// \brief A little firefly that moves around the 3D space.
    Vec3 firefly;
    Vec3 firefly2;
    
    ofMesh mesh;
    vector<float> pointSize;
    vector<float> colors;
    
    string concat_type;
    int of_concat_list_num;
    int of_concat_index;
    int of_concat_list_counter;
    int of_concat_list_buff_size;
    bool concat_list_gate = true;
    int sphere_index;
    int num_concat;
    
    //    vector<float> pointSize;
    ofShader shader;
    
    vector<ofSpherePrimitive> sphere_concat;
    
    /// \brief The search modes in this example.
    enum Modes
    {
        MODE_RADIUS,
        MODE_NEAREST_N
    };
    
    vector<float> knn_search_list;
    vector<float> knn_search_list2;
    
    /// \brief The current search mode.
    int mode = 0;
    
    /// \brief Radius used for radius search.
    int radius = 0;
    
    /// \brief Number of nearest neighbors to use for Nearest Nieghbor search.
    int nearestN = 0;
    
    //    int search = 0;
    float search2 = 0.0;
    int search_old = 0;
    
    
    
    /// \brief A mesh to make it easier to draw lots of points.
    //    ofMesh mesh;
    
    
    //32 bits red, 32 bits green, 32 bits blue, from 0 to 1 in 'infinite' steps
    ofFbo rgbaFboFloat; // with alpha
    ofFbo fbo;
    int fadeAmnt;
};
