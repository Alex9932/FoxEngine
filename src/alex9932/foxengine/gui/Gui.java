package alex9932.foxengine.gui;

import static org.lwjgl.opengl.GL11.GL_FOG;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;

import alex9932.foxengine.Main;
import alex9932.foxengine.Texture;

public class Gui {
	
	private UnicodeFont font;
	
	@SuppressWarnings("unchecked")
	public Gui() {
		try {
			font = new UnicodeFont("./res/fonts/font.ttf", 15, true, false);
			font.addAsciiGlyphs();
			font.addGlyphs(400, 600);
			font.getEffects().add(new ColorEffect(java.awt.Color.WHITE));
			font.loadGlyphs();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

	private void disableAll() {
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
        GL11.glEnable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL_FOG);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	}
	private void enableAll() {
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glEnable(GL_FOG);
		GL11.glEnable(GL11.GL_DEPTH_TEST);
	}
	
	public void drawString(String s, float x, float y){
		disableAll();
		//Main.println("Printing: " + s);
		font.drawString(x, y, s);
		enableAll();
	}
	
	public void drawString(String s, float x, float y, Color color){
		disableAll();
		//Main.println("Printing: " + s);
		font.drawString(x, y, s, color);
		enableAll();
	}
	
	public void drawTexture(Texture texture, float x, float y, float w, float h) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glEnable(GL11.GL_TEXTURE_2D);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, texture.getID());
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glTexCoord2f(0, 0);
		GL11.glVertex2f(x, y);
		GL11.glTexCoord2f(1, 0);
		GL11.glVertex2f(x + w, y);
		GL11.glTexCoord2f(1, 1);
		GL11.glVertex2f(x + w, y + h);
		GL11.glTexCoord2f(0, 1);
		GL11.glVertex2f(x, y + h);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glDisable(GL11.GL_BLEND);
	}
	
	public void drawQuad(float x, float y, float w, float h) {
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glBegin(GL11.GL_QUADS);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x + w, y);
		GL11.glVertex2f(x + w, y + h);
		GL11.glVertex2f(x, y + h);
		GL11.glEnd();
		GL11.glDisable(GL11.GL_BLEND);
	}

	public void drawLine(float x, float y, float x2, float y2) {
		GL11.glBegin(GL11.GL_LINES);
		GL11.glVertex2f(x, y);
		GL11.glVertex2f(x2, y2);
		GL11.glEnd();
	}

	public void drawPixel(float x, float y) {
		GL11.glBegin(GL11.GL_POINTS);
		GL11.glVertex2f(x, y);
		GL11.glEnd();
	}

	public void drawTriangles(Point[] points) {
		GL11.glBegin(GL11.GL_TRIANGLES);
		for (int i = 0; i < points.length; i++) {
			GL11.glVertex2f(points[i].x, points[i].y);
		}
		GL11.glEnd();
	}
	
	public void drawPolygon(Point[] points) {
		GL11.glBegin(GL11.GL_POLYGON);
		for (int i = 0; i < points.length; i++) {
			GL11.glVertex2f(points[i].x, points[i].y);
		}
		GL11.glEnd();
	}
	public class Point {
		public float x = 0, y = 0;
	}
}