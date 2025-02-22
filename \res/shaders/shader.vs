#version 330 core

in vec3 position;
out vec3 color;

void main(void){
	gl_Position = vec4(position.xyz, 1.0);
	color = vec3(position.x + 1.5, position.y + 0.5, position.z + 2.5);
}