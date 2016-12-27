package alex9932.foxengine.gui;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import alex9932.foxengine.Main;
import alex9932.foxengine.Player;

public class GuiGameOverlay extends GuiScreen {
	@Override
	public void drawScreen() {
		GL11.glColor3f(1, 1, 1);
		GL11.glDisable(GL11.GL_FOG);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		gui.drawTexture(Main.cursor, getMiddlex() - 16, getMiddley() - 16, 32, 32);
		gui.drawLine(getMiddlex() - 15, getMiddley(), getMiddlex() + 15, getMiddley());
		gui.drawLine(getMiddlex(), getMiddley() - 15, getMiddlex(), getMiddley() + 15);
		GL11.glColor3f(1, 1, 1);
		gui.drawString("Fox engine version: 0.2 beta", 5, 5, Color.red);
		gui.drawString("QubeWorld v1.0.0", 5, 20, Color.red);
		gui.drawString("Game timer: " + Main.delta, 5, 35, Color.red);
		gui.drawString("Frames: " + Main.frames, 5, 50, Color.red);
		gui.drawString("Fps: " + Main.fps, 5, 65, Color.red);
		gui.drawString("Player at X: " + Player.x + " Y: " + Player.y + " Z: " + Player.z, 5, 80, Color.red);
		gui.drawString("Time: " + Main.daytime, 5, 95, Color.red);
	}
}