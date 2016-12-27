package alex9932.foxengine.gui;

import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.GL11;

public abstract class GuiScreen {
	private float width;
	private float height;
	private float middlex;
	private float middley;
	public static Gui gui = new Gui();
	public GuiScreen() {
        GL11.glClear(256);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, Display.getWidth(), Display.getHeight(), 0.0, 100.0, 300.0);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0, 0, -200);
        GL11.glPushMatrix(); 
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTranslatef(0, 0, 0);
        this.drawScreen();
	}
	
	public void updateScreen(){
        GL11.glClear(256);
        GL11.glMatrixMode(5889);
        GL11.glLoadIdentity();
        GL11.glOrtho(0.0, Display.getWidth(), Display.getHeight(), 0.0, 100.0, 300.0);
        GL11.glMatrixMode(5888);
        GL11.glLoadIdentity();
        GL11.glTranslatef(0, 0, -200);
        GL11.glPushMatrix(); 
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
        GL11.glTranslatef(0, 0, 0);
        this.drawScreen();
	}

	private void updateConsts() {
        width = Display.getDesktopDisplayMode().getWidth();
        height = Display.getDesktopDisplayMode().getHeight();
        middlex = width/2;
        middley = height/2;
	}
	
	public float getWidth() {
		updateConsts();
		return width;
	}
	
	public float getHeight() {
		updateConsts();
		return height;
	}
	public float getMiddlex() {
		updateConsts();
		return middlex;
	}
	public float getMiddley() {
		updateConsts();
		return middley;
	}
	
	public void sleep(long i) {
		try {
			Thread.sleep(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public abstract void drawScreen();
}