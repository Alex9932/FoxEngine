package alex9932.foxengine;

import static java.lang.Math.PI;
import static java.lang.Math.cos;
import static java.lang.Math.sin;
import static java.lang.Math.tan;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.glu.GLU;

public class Player {
	public static float size = Main.size;
	public static float x = 0, y = 0, z = 0;
	public static float w = size/2, h = size*2 - (size/2), d = size/2;
	public static double dx = 0, dy = 0, dz = 0;
	public static float angleX = 0, angleY = 0;
	public static boolean onGround = false;
	private double speed = 0.002;
	private double rspeed = 0.8;
	private float sensetivity = 2f;
	private boolean ignoreALL = true;
	private float gravity = 0.000005f;
	
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
		}

		angleX -= Mouse.getDX() / sensetivity;
		angleY += Mouse.getDY() / sensetivity;


		if(angleY >= 90){
			angleY = 90;
		}
		if(angleY <= -90){
			angleY = -90;
		}
		
		//System.out.println("[Player] Checking keyboard...");
		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			angleY += rspeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			angleY -= rspeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			angleX -= rspeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			angleX += rspeed;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT)){
			if(ignoreALL){
				y += -0.02f * time;
			}
			speed = 0.001;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LCONTROL)){
			speed = 0.02;
		}
		
		
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			if(ignoreALL){
				y += 0.02f * time;
			}else{
				if (onGround) {
					onGround = false;
					dy = 0.013;
				}
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_W)){
			dx =- sin(angleX/180*PI) * speed; 
			dz =- cos(angleX/180*PI) * speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_S)){
			dx = sin(angleX/180*PI) * speed;
			dz = cos(angleX/180*PI) * speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_D)){
			dx = sin((angleX+90)/180*PI) * speed;	
			dz = cos((angleX+90)/180*PI) * speed;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A)){
			dx = sin((angleX-90)/180*PI) * speed;	
			dz = cos((angleX-90)/180*PI) * speed;
		}
		speed = 0.01;
		//System.out.println("Player:  X: " + x + " Y: " + y + " Z: " + z + "   anglex: " + angleX + " angley: " + angleY);
		GLU.gluLookAt(x, y + size*2 - (size/2), z, (float)(x-sin(angleX/180*PI)), (float)(y+tan(angleY/180*PI)) + size*2 - (size/2), (float)(z-cos(angleX/180*PI)), 0f, 1f, 0f);
	}
}