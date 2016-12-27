package alex9932.foxengine;

import static org.lwjgl.opengl.GL11.*;

public class GuiHelper {
	public static void drawBox(float size) {
		glBegin(GL_QUADS);
		//front
		glTexCoord2f(0, 1); glVertex3f(-size, -size, -size);
		glTexCoord2f(1, 1); glVertex3f(size, -size, -size);
		glTexCoord2f(1, 0); glVertex3f(size, size, -size);
		glTexCoord2f(0, 0); glVertex3f(-size, size, -size);
	
		//back
		glTexCoord2f(0, 1); glVertex3f(size, -size, size);
		glTexCoord2f(1, 1); glVertex3f(-size, -size, size);
		glTexCoord2f(1, 0); glVertex3f(-size, size, size);
		glTexCoord2f(0, 0); glVertex3f( size, size, size);

		//left
		glTexCoord2f(0, 1); glVertex3f(-size, -size, size);
		glTexCoord2f(1, 1); glVertex3f(-size, -size, -size);
		glTexCoord2f(1, 0); glVertex3f(-size, size, -size);
		glTexCoord2f(0, 0); glVertex3f(-size, size, size);

		//right
		glTexCoord2f(0, 1); glVertex3f(size, -size, -size);
		glTexCoord2f(1, 1); glVertex3f(size, -size, size);
		glTexCoord2f(1, 0); glVertex3f(size, size, size);
		glTexCoord2f(0, 0); glVertex3f(size, size, -size);

		//bottom
		glTexCoord2f(0, 1); glVertex3f(-size, -size, size);
		glTexCoord2f(1, 1); glVertex3f(size, -size, size);
		glTexCoord2f(1, 0); glVertex3f(size, -size, -size);
		glTexCoord2f(0, 0); glVertex3f(-size, -size, -size);

		//top  		
		glTexCoord2f(0, 1); glVertex3f(-size, size, -size);
		glTexCoord2f(1, 1); glVertex3f(size, size, -size);
		glTexCoord2f(1, 0); glVertex3f(size, size, size);
		glTexCoord2f(0, 0); glVertex3f(-size, size, size);

		glEnd();
	}
	public static void drawBox(float size, Texture tex) {
		glBindTexture(GL_TEXTURE_2D, tex.getID());
		drawBox(size);
	}
	public static void drawBox(float size, Texture tex[]) {
		glBindTexture(GL_TEXTURE_2D, tex[0].getID());
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1); glVertex3f(-size, -size, -size);
		glTexCoord2f(1, 1); glVertex3f(size, -size, -size);
		glTexCoord2f(1, 0); glVertex3f(size, size, -size);
		glTexCoord2f(0, 0); glVertex3f(-size, size, -size);

		glEnd();
		glBindTexture(GL_TEXTURE_2D, tex[1].getID());
		glBegin(GL_QUADS);
		//back
		glTexCoord2f(0, 1); glVertex3f(size, -size, size);
		glTexCoord2f(1, 1); glVertex3f(-size, -size, size);
		glTexCoord2f(1, 0); glVertex3f(-size, size, size);
		glTexCoord2f(0, 0); glVertex3f( size, size, size);

		glEnd();
		glBindTexture(GL_TEXTURE_2D, tex[2].getID());
		glBegin(GL_QUADS);
		//left
		glTexCoord2f(0, 1); glVertex3f(-size, -size, size);
		glTexCoord2f(1, 1); glVertex3f(-size, -size, -size);
		glTexCoord2f(1, 0); glVertex3f(-size, size, -size);
		glTexCoord2f(0, 0); glVertex3f(-size, size, size);

		glEnd();
		glBindTexture(GL_TEXTURE_2D, tex[3].getID());
		glBegin(GL_QUADS);
		//right
		glTexCoord2f(0, 1); glVertex3f(size, -size, -size);
		glTexCoord2f(1, 1); glVertex3f(size, -size, size);
		glTexCoord2f(1, 0); glVertex3f(size, size, size);
		glTexCoord2f(0, 0); glVertex3f(size, size, -size);

		glEnd();
		glBindTexture(GL_TEXTURE_2D, tex[4].getID());
		glBegin(GL_QUADS);
		//bottom
		glTexCoord2f(0, 1); glVertex3f(-size, -size, size);
		glTexCoord2f(1, 1); glVertex3f(size, -size, size);
		glTexCoord2f(1, 0); glVertex3f(size, -size, -size);
		glTexCoord2f(0, 0); glVertex3f(-size, -size, -size);

		glEnd();
		glBindTexture(GL_TEXTURE_2D, tex[5].getID());
		glBegin(GL_QUADS);
		//top  		
		glTexCoord2f(0, 1); glVertex3f(-size, size, -size);
		glTexCoord2f(1, 1); glVertex3f(size, size, -size);
		glTexCoord2f(1, 0); glVertex3f(size, size, size);
		glTexCoord2f(0, 0); glVertex3f(-size, size, size);

		glEnd();
	}
}