package alex9932.foxengine.utils;

import static org.lwjgl.opengl.GL20.*;

import org.lwjgl.util.vector.Vector3f;

public class Light {
	
	private Vector3f position;
	private float ambient;
	private float range;
	
	public Light(Vector3f position, float ambient, float range) {
		this.position = position;
		this.ambient = ambient;
		this.range = range;
	}
	
	public void update(int shaderProgram, int lightPositionID, int ambientID, int lightRangeID) {
		glUseProgram(shaderProgram);
		
		glUniform3f(lightPositionID, position.x, position.y, position.z);
		glUniform1f(ambientID, ambient);
		glUniform1f(lightRangeID, range);

		glUseProgram(0);
	}
	
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	public float getAmbient() {
		return ambient;
	}
	public void setAmbient(float ambient) {
		this.ambient = ambient;
	}
	public float getRange() {
		return range;
	}
	public void setRange(float range) {
		this.range = range;
	}
}