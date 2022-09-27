#version 150

uniform mat4 modelViewProjectionMatrix;
in vec4 position;
in float pointSize;

void main()
{
    gl_Position = modelViewProjectionMatrix * position;
    gl_PointSize = pointSize;
}