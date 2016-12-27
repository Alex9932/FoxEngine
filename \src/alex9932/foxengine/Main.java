package alex9932.foxengine;

import static org.lwjgl.opengl.GL11.*;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import javax.imageio.ImageIO;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.vector.Vector3f;

import alex9932.foxengine.command.Command;
import alex9932.foxengine.command.Commands;
import alex9932.foxengine.gui.Gui;
import alex9932.foxengine.gui.GuiGameOverlay;
import alex9932.foxengine.gui.GuiScreen;
import alex9932.foxengine.shaders.ImplShader;
import alex9932.foxengine.utils.BufferTools;
import alex9932.foxengine.utils.Light;
import alex9932.foxengine.utils.Model;
import alex9932.foxengine.utils.ObjLoader;
import alex9932.foxengine.utils.Timer;
import alex9932.foxengine.utils.World3DHelper;

/**
 * Author: Alex9932 
 * License: GPL
**/
public class Main {
	public static float fov = 90.f;
	public static float renderDistance = 5000.f;
	public static float delta = 0;
	public static float size = 10.0f;
	public static float delay = 0.2f;
	public static Console console;
	public static Texture cursor;
	public static Texture tex;
	public static Texture terrain;
	public static Player player;
	public static Texture blocktex;
	public static ImplShader shader;
	public static GuiScreen overlay;
	public static Model model;
	public static Texture[] skybox = new Texture[6];
	public static final String TEXTURES_PATH = "./res/textures/";
	public static int RESET;
	public static int fps;
	public static long time;
	public static int frames;
	public static float daytime = 0.01f;
	public static boolean timeup;
	public static float modelx = -0;
	public static float modelz = 0;
	public static boolean isFullscreen = true;
	public static Timer timer = new Timer();
	public static boolean isConsoleOpened;
	public static GuiConsole gameconsole;
	public static Command cmd;
	public static boolean drawmodels = true;
	private static Light light;
	private static Gui gui;
	private static int line = 1;

	public static void main(String[] args) {
		Thread t = new Thread(){
			public void run() {
				console = new Console();
			}
		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException e1) {
			
		}
		println("Starting up...");
		new ErrorMessage();
		try{
			
			println("Creating display...");
			if(!isFullscreen){
				println("[Display] Setting size: 1000x600 (Default)");
				Display.setDisplayMode(new DisplayMode(1000, 600));
			}else{
				Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
				println("[Display] Setting size: " + Display.getWidth() + "x" + Display.getHeight() + " (Native)");
				Display.setFullscreen(true);
			}
			Display.create();
			Display.setTitle("Fox engine");

			initGL2D();
			gui = new Gui();
			
			glColor3f(1, 1, 1);
			glBegin(GL_QUADS);
			glVertex2f(0, 0);
			glVertex2f(1000, 0);
			glVertex2f(1000, 600);
			glVertex2f(0, 600);
			glEnd();
			
			println("Setting default params...");
			Keyboard.create();
			Mouse.create();
			

			gui.drawString("[FoxEngine] Loading shaders...", 1, line * 15); line++;
			shader = new ImplShader();
			gui.drawString("[FoxEngine] Loading lighting...", 1, line * 15); line++;
			light = new Light(new Vector3f(0, 100, 0), 10, 100);
			gui.drawString("[FoxEngine] Loading commands...", 1, line * 15); line++;
			cmd = new Command();
			cmd.addCommand(Commands.drawModels);
			gui.drawString("[FoxEngine] Loading modles...", 1, line * 15); line++;
			model = ObjLoader.loadModel(new File("./res/bunny.obj"));

			println("Loading textures...");
			gui.drawString("[FoxEngine] Loading textures...", 1, line * 15); line++;
			gui.drawString("[FoxEngine] [Texture loader] Loading " + TEXTURES_PATH + "logo.png", 1, line * 15); line++;;
			tex = new Texture(TEXTURES_PATH + "logo.png");
			gui.drawString("[FoxEngine] [Texture loader] Loading " + TEXTURES_PATH + "diamond_block.png", 1, line * 15); line++;
			blocktex = new Texture(TEXTURES_PATH + "diamond_block.png");
			gui.drawString("[FoxEngine] [Texture loader] Loading " + TEXTURES_PATH + "reset.png", 1, line * 15); line++;
			RESET = new Texture(TEXTURES_PATH + "reset.png").getID();
			gui.drawString("[FoxEngine] [Texture loader] Loading " + TEXTURES_PATH + "terrain.png", 1, line * 15); line++;
			terrain = new Texture(TEXTURES_PATH + "terrain.png");
			gui.drawString("[FoxEngine] [Texture loader] Loading " + TEXTURES_PATH + "cursor.png", 1, line * 15); line++;
			cursor = new Texture(TEXTURES_PATH + "cursor.png");
			gui.drawString("[FoxEngine] [Texture loader] Loading " + TEXTURES_PATH + "skybox_<ID>.png", 1, line * 15); line++;
			for (int i = 0; i < skybox.length; i++) {
				skybox[i] = new Texture(TEXTURES_PATH + "skybox/skybox_" + i + ".png");
			}

			gui.drawString("[FoxEngine] Loading player", 1, line * 15); line++;
			println("Loading player...");
			overlay = new GuiGameOverlay();
			player = new Player();

			gui.drawString("[FoxEngine] Init lwjgl", 1, line * 15); line++;
			println("Initialization OpenGl...");
			initGL2D();
			shader.start();

			gui.drawString("[FoxEngine] Loading done!", 1, line * 15); line++;
			Thread.sleep(3000);
			println("Starting main game loop...");
			long starttime = System.nanoTime();
			while(!Display.isCloseRequested()){
				timer.start();
				glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
				glClearColor(0, 0.5f, 0.8f, 0.5f);
				Mouse.setCursorPosition(Display.getDisplayMode().getWidth() / 2, Display.getDisplayMode().getHeight() / 2);
				initGL3D();
				render(time);
				//Display.sync(60);
				delta += delay;
				
				Display.update();
				
				frames++;
				time = timer.getElapsedTimeAsMicroSeconds() / 100;
				if (System.nanoTime() - starttime >= 1000000000) {
					starttime = System.nanoTime();
					fps = frames;
					frames = 0;
				}
			}
			
		}catch(Exception e){
	        Mouse.setGrabbed(false);
		}
	}

	private static void render(float time) {
		long stime = System.nanoTime();
		glColor3f(1, 1, 1);
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		//glTranslatef(0, 0, 0);
		//glRotatef(delta, 100, 0, 0);
		player.update(time);

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
		glEnable(GL_FOG);
		glEnable(GL_LIGHTING);

		if(daytime > 0 && timeup){
			daytime += 0.001f;
		}
		if(daytime < 1 && !timeup){
			daytime -= 0.001f;
		}
		if(daytime >= 1f){
			timeup = false;
			daytime = 0.9f;
		}
		if(daytime <= 0f){
			timeup = true;
			daytime = 0.001f;
		}
		glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(new float[]{0, 0, 0, 1}));
		light = new Light(new Vector3f(0, 100, 0), 1, 100);
		light.update(shader.getID(), 1, 1, 100);
		glColor3f(1, 1, 1);
		

        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, terrain.getID());
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
		if(drawmodels){
			//glDisable(GL_LIGHTING);
			glRotatef(delta * 10, 0, 50, 0);
			World3DHelper.renderModel(model, 30, modelx, 15, modelz);
			//glEnable(GL_LIGHTING);
		}
		overlay.updateScreen();
		if(isConsoleOpened){
			gameconsole.updateScreen();
		}
		if((System.nanoTime() - stime)/1000000 < 100){
			System.out.println("Rendering time: " + (System.nanoTime() - stime)/1000000 + "ms  Fps: " + fps);
		}else{
			System.err.println("Rendering time: " + (System.nanoTime() - stime)/1000000 + "ms  Fps: " + fps + "\n");
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F1)){
			screenShot();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F12)){
			try {
				if(Display.isFullscreen()){
					println("[Display] Setting size: 1000x600 (Default)");
					Display.setDisplayMode(new DisplayMode(1000, 600));
				}else{
					Display.setDisplayModeAndFullscreen(Display.getDesktopDisplayMode());
					println("[Display] Setting size: " + Display.getWidth() + "x" + Display.getHeight() + " (Native)");
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
			showConsole();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F)){
			shader.stop();
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_V)){
			shader.stop();
		}
		
		Mouse.setGrabbed(true);
		
	}
	
	private static void showConsole() {
		isConsoleOpened = true;
		gameconsole = new GuiConsole();
		gameconsole.updateScreen();
	}
	
	private static void initGL2D() {
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, Display.getWidth(), 0, Display.getHeight(), 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glViewport(0, 0, Display.getWidth(), Display.getHeight());
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}
	
	private static void initGL3D() {
		setUpLighting();
		glDepthMask(true);
		glClearDepth(1.f);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		GLU.gluPerspective(fov, 1.f, 1.f, renderDistance);
		glEnable(GL_FOG);
		FloatBuffer fogColor = BufferUtils.createFloatBuffer(4);
		fogColor.put(0.5f).put(0.5f).put(0.5f).put(1.0f).flip();
		int fogMode = GL_EXP;
		glFogi(GL_FOG_MODE, fogMode);
		glFog(GL_FOG_COLOR, fogColor);
		glFogf(GL_FOG_DENSITY, 0.002f);
		glHint(GL_FOG_HINT, GL_DONT_CARE);
		glFogf(GL_FOG_START, 800.0f);
		glFogf(GL_FOG_END, 1000.0f);
		glEnable(GL_TEXTURE_2D);
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
		glBindTexture(GL_TEXTURE_2D, RESET);
	}

	private static void setUpLighting() {
		glShadeModel(GL_SMOOTH);
		glEnable(GL_DEPTH_TEST);
		glEnable(GL_LIGHTING);
		glEnable(GL_LIGHT0);
		glLightModel(GL_LIGHT_MODEL_AMBIENT, BufferTools.asFlippedFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1f}));
		glLight(GL_LIGHT0, GL_POSITION, BufferTools.asFlippedFloatBuffer(new float[]{0, 0, 0, 1}));
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		glColor3f(1, 1, 1);
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT, GL_DIFFUSE);
		glColor3f(1, 1, 1);
	}

	public static void println(String str) {
		console.append("[FoxEngine] " + str + "\n");
		System.out.println("[FoxEngine] " + str);
	}
	
	public static void shutdown() {
		println("Shutting down...");
		System.exit(0);
	}
	
	private static void screenShot(){
		int[] pixels = new int[Display.getWidth() * Display.getHeight()];
		int bindex;
		ByteBuffer fb = ByteBuffer.allocateDirect(Display.getWidth() * Display.getHeight() * 3);
		glReadPixels(0, 0, Display.getWidth(), Display.getHeight(), GL_RGB, GL_UNSIGNED_BYTE, fb);
		BufferedImage imageIn = new BufferedImage(Display.getWidth(), Display.getHeight(),BufferedImage.TYPE_INT_RGB);
		for (int i=0; i < pixels.length; i++) {
			bindex = i * 3;
			pixels[i] =
				((fb.get(bindex) << 16))  +
				((fb.get(bindex+1) << 8))  +
				((fb.get(bindex+2) << 0));
		}
		imageIn.setRGB(0, 0, Display.getWidth(), Display.getHeight(), pixels, 0 , Display.getWidth());
		AffineTransform at =  AffineTransform.getScaleInstance(1, -1);
		at.translate(0, -imageIn.getHeight(null));
		AffineTransformOp opRotated = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
		BufferedImage imageOut = opRotated.filter(imageIn, null);
		try {
			new File("./screenshots").mkdir();
			File file = new File("./screenshots/Screenshot" + delta + ".png");
			ImageIO.write(imageOut, "PNG", file);
		}catch (Exception e) {
			System.out.println("ScreenShot() exception: " +e);
		}
	}
}