package alex9932.foxengine.render;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

import alex9932.foxengine.Main;
import alex9932.foxengine.utils.BufferTools;
import alex9932.foxengine.utils.Light;
import alex9932.foxengine.utils.World3DHelper;

public class Renderer implements IRenderer {
	@Override
	public void render(long time) {
		long stime = System.nanoTime();
		glColor3f(1, 1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		//glTranslatef(0, 0, 0);
		//glRotatef(delta, 100, 0, 0);
		Main.player.update(time);
		Main.entity.update(time);
		
		glDisable(GL_LIGHTING);
		glDisable(GL_FOG);
		glBegin(GL_LINES);
		glColor3f(0, 1, 0);
		glVertex3f(-1000, 0, 0);
		glVertex3f(1000, 0, 0);
		glColor3f(0, 0, 1);
		glVertex3f(0, -1000, 0);
		glVertex3f(0, 1000, 0);
		glColor3f(1, 0, 0);
		glVertex3f(0, 0, -1000);
		glVertex3f(0, 0, 1000);
		glEnd();

		glColor3f(1, 1, 1);
		World3DHelper.drawBox(0, 0.07f, 0, 1000, Main.skybox);
		
		glEnable(GL_FOG);
		glEnable(GL_LIGHTING);

		if(Main.daytime > 0 && Main.timeup){
			Main.daytime += 0.001f;
		}
		if(Main.daytime < 1 && !Main.timeup){
			Main.daytime -= 0.001f;
		}
		if(Main.daytime >= 1f){
			Main.timeup = false;
			Main.daytime = 0.9f;
		}
		if(Main.daytime <= 0f){
			Main.timeup = true;
			Main.daytime = 0.001f;
		}
		glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(new float[]{0, 0, 0, 1}));
		Main.light = new Light(new Vector3f(0, 100, 0), 1, 100);
		Main.light.update(Main.shader.getID(), 1, 1, 100);
		glColor3f(1, 1, 1);
		

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, Main.terrain.getID());
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex3f(-1024, 0, -1024);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex3f(-1024, 0, 1024);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex3f(1024, 0, 1024);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex3f(1024, 0, -1024);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
		if(Main.drawmodels){
			//glDisable(GL_LIGHTING);
			glRotatef(Main.delta * 10, 0, 50, 0);
			World3DHelper.renderModel(Main.model, 30, Main.modelx, 15, Main.modelz);
			//glEnable(GL_LIGHTING);
		}
		Main.entity.render();
		Main.overlay.updateScreen();
		if(Main.isConsoleOpened){
			Main.gameconsole.updateScreen();
		}
		if((System.nanoTime() - stime)/1000000 < 100){
			System.out.println("Rendering time: " + (System.nanoTime() - stime)/1000000 + "ms  Fps: " + Main.fps);
		}else{
			System.err.println("Rendering time: " + (System.nanoTime() - stime)/1000000 + "ms  Fps: " + Main.fps + "\n");
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F1)){
			Main.screenShot();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F12)){
			try {
				if(Display.isFullscreen()){
					Main.println("[Display] Setting size: 1000x600 (Default)");
					Display.setDisplayMode(new DisplayMode(1000, 600));
				}else{
					Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
					Main.println("[Display] Setting size: " + Display.getWidth() + "x" + Display.getHeight() + " (Native)");
					Display.setFullscreen(true);
				}
			} catch (LWJGLException e) {
				e.printStackTrace();
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_L)){
			if(glIsEnabled(GL_LIGHTING)){
				glDisable(GL_LIGHTING);
			}else{
				glEnable(GL_LIGHTING);
			}
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)){
			Main.shutdown();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F2)){
			Main.showConsole();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F)){
			Main.shader.stop();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_V)){
			Main.shader.stop();
		}

		if(Keyboard.isKeyDown(Keyboard.KEY_UP)){
			Main.entity.dx = 0.01f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_DOWN)){
			Main.entity.dx = -0.01f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_LEFT)){
			Main.entity.dz = 0.01f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT)){
			Main.entity.dz = -0.01f;
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RSHIFT)){
			if(Main.entity.onGround)
				Main.entity.dy = 0.02f;
		}
		
		Mouse.setGrabbed(true);
	}

	@Override
	public void startup() {

	}
}