package alex9932.foxengine.utils;

import static org.lwjgl.opengl.GL11.*;

import org.lwjgl.util.vector.Vector3f;

import alex9932.foxengine.Texture;

public class World3DHelper {
	public static void renderModel(Model model, float modelSize, float modelx, float modely, float modelz) {

		glPolygonMode(GL_FRONT_AND_BACK, GL_LINES);
		glTranslatef(modelx, modely, modelz);
		glScalef(modelSize, modelSize, modelSize);
		glColor3f(1, 1, 1);
		glBegin(GL_TRIANGLES);
        for (Model.Face face : model.getFaces()) {
            Vector3f n1 = model.getNormals().get(face.getNormalIndices()[0] - 1);
            glNormal3f(n1.x, n1.y, n1.z);
            Vector3f v1 = model.getVertices().get(face.getVertexIndices()[0] - 1);
            glVertex3f(v1.x, v1.y, v1.z);
            Vector3f n2 = model.getNormals().get(face.getNormalIndices()[1] - 1);
            glNormal3f(n2.x, n2.y, n2.z);
            Vector3f v2 = model.getVertices().get(face.getVertexIndices()[1] - 1);
            glVertex3f(v2.x, v2.y, v2.z);
            Vector3f n3 = model.getNormals().get(face.getNormalIndices()[2] - 1);
            glNormal3f(n3.x, n3.y, n3.z);
            Vector3f v3 = model.getVertices().get(face.getVertexIndices()[2] - 1);
            glVertex3f(v3.x, v3.y, v3.z);
        }
        glEnd();
		glTranslatef(-modelx, -modely, -modelz);
	}
	public static void renderTexturedModel(Model model, Texture texture, float modelSize, float modelx, float modely, float modelz) {

		glPolygonMode(GL_FRONT_AND_BACK, GL_LINES);
		glEnable(GL_TEXTURE_2D);
		glBindTexture(GL_TEXTURE_2D, texture.getID());
		glTranslatef(modelx, modely, modelz);
		glScalef(modelSize, modelSize, modelSize);
		glColor3f(1, 1, 1);
		glBegin(GL_TRIANGLES);
		int i = 0;
        for (Model.Face face : model.getFaces()) {
        	glTexCoord2f(model.getTextureCoordinates().get(i).x, model.getTextureCoordinates().get(i).y);
            Vector3f n1 = model.getNormals().get(face.getNormalIndices()[0] - 1);
            glNormal3f(n1.x, n1.y, n1.z);
            Vector3f v1 = model.getVertices().get(face.getVertexIndices()[0] - 1);
            glVertex3f(v1.x, v1.y, v1.z);
        	glTexCoord2f(model.getTextureCoordinates().get(i).x, model.getTextureCoordinates().get(i).y);
            Vector3f n2 = model.getNormals().get(face.getNormalIndices()[1] - 1);
            glNormal3f(n2.x, n2.y, n2.z);
            Vector3f v2 = model.getVertices().get(face.getVertexIndices()[1] - 1);
            glVertex3f(v2.x, v2.y, v2.z);
        	glTexCoord2f(model.getTextureCoordinates().get(i).x, model.getTextureCoordinates().get(i).y);
            Vector3f n3 = model.getNormals().get(face.getNormalIndices()[2] - 1);
            glNormal3f(n3.x, n3.y, n3.z);
            Vector3f v3 = model.getVertices().get(face.getVertexIndices()[2] - 1);
            glVertex3f(v3.x, v3.y, v3.z);
            i++;
        }
        glEnd();
		glTranslatef(-modelx, -modely, -modelz);
		glDisable(GL_TEXTURE_2D);
	}
	
	public static void drawBox(float x, float y, float z, float size, Texture texture[]) {
		glTranslatef(x * size, y * size, z * size);
		
		//front
		glBindTexture(GL_TEXTURE_2D, texture[0].getID());
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
		glVertex3f(-size, -size, -size);
		glTexCoord2f(1, 1);
		glVertex3f(size, -size, -size);
		glTexCoord2f(1, 0);
		glVertex3f(size, size, -size);
		glTexCoord2f(0, 0);
		glVertex3f( -size, size, -size);
		glEnd();
		//back
		glBindTexture(GL_TEXTURE_2D, texture[1].getID());
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
		glVertex3f(size, -size, size);
		glTexCoord2f(1, 1);
		glVertex3f(-size, -size, size);
		glTexCoord2f(1, 0);
		glVertex3f(-size, size, size);
		glTexCoord2f(0, 0);
		glVertex3f(size, size, size);
		glEnd();
		//left
		glBindTexture(GL_TEXTURE_2D, texture[2].getID());
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
		glVertex3f(-size, -size, size);
		glTexCoord2f(1, 1);
		glVertex3f(-size, -size, -size);
		glTexCoord2f(1, 0);
		glVertex3f(-size, size, -size);
		glTexCoord2f(0, 0);
		glVertex3f(-size, size, size);
		glEnd();
		//right
		glBindTexture(GL_TEXTURE_2D, texture[3].getID());
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
		glVertex3f(size, -size, -size);
		glTexCoord2f(1, 1);
		glVertex3f(size, -size, size);
		glTexCoord2f(1, 0);
		glVertex3f(size, size, size);
		glTexCoord2f(0, 0);
		glVertex3f(size, size, -size);
		glEnd();
		//bottom
		glBindTexture(GL_TEXTURE_2D, texture[4].getID());
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
		glVertex3f(-size, -size, size);
		glTexCoord2f(1, 1);
		glVertex3f(size, -size, size);
		glTexCoord2f(1, 0);
		glVertex3f(size, -size, -size);
		glTexCoord2f(0, 0);
		glVertex3f(-size, -size, -size);
		glEnd();
		//top
		glBindTexture(GL_TEXTURE_2D, texture[5].getID());
		glBegin(GL_QUADS);
		glTexCoord2f(0, 1);
		glVertex3f(-size, size, -size);
		glTexCoord2f(1, 1);
		glVertex3f(size, size,-size);
		glTexCoord2f(1, 0);
		glVertex3f(size, size, size);
		glTexCoord2f(0, 0);
		glVertex3f( -size, size, size);
		glEnd();
		
		glTranslatef(-(x * size), -(y * size), -(z * size));
	}
}