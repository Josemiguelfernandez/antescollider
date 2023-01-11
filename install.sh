#!/bin/csh -f

set Lib =  "/Users/"`whoami`"/Library/Application Support/SuperCollider"

open -a SuperCollider

echo cp -r SuperCollider_add/TSupport/ "$Lib"
cp -r SuperCollider_add/TSupport/ "$Lib"
cp -r SuperCollider_add/Synthdefs/ "$Lib/Synthdefs"
cp -r SuperCollider_add/Extensions/ "$Lib/Extensions"
cp -r SuperCollider_add/Ambitools/ "$Lib/Extensions"
mkdir "$Lib/sounds"