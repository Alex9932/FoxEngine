package alex9932.foxengine.entity;

import org.lwjgl.opengl.GL11;

import alex9932.foxengine.Main;
import alex9932.foxengine.utils.Model;
import alex9932.foxengine.utils.World3DHelper;

public class Entity {
	public float size = Main.size;
	public float x = 0, y = 0, z = 0;
	public float w = size/2, h = size*2 - (size/2), d = size/2;
	public double dx = 0, dy = 0, dz = 0;
	//public float angleX = 0, angleY = 0;
	public boolean onGround = false;
	private double speed = 0.002;
	private double rspeed = 0.8;
	public boolean ignoreALL = false;
	public float gravity = 0.000005f;
	public Model model;
	
	public Entity(float x, float y, float z, Model model) {

		this.x = x;
		this.y = y;
		this.z = z;
		this.model = model;
		
	}
	
	public void update(float time) {
		
		if(ignoreALL){
			x+=dx*time;
			y+=dy*time;
			z+=dz*time;
			dx=dz=0;
		}else{
			if (!onGround){
				dy -= gravity*time;
			}
			onGround=false;
			x+=dx*time;
			y+=dy*time;
			z+=dz*time;
			dx=dz=0;

			if(y < 0){
				y = 0;
				onGround = true;
			}
			if(x < -999){
				x = -999;
			}
			if(x > 999){
				x = 999;
			}
			if(z < -999){
				z = -999;
			}
			if(z > 999){
				z = 999;
			}
		}
	}

	public void render() {
		//GL11.glRotatef(angleX, 0, 10, 0);
		World3DHelper.renderModel(model, 20, x, y, z);
	}
}