package alex9932.foxengine;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

public class Texture {
	private int width;
	private int height;
	private int id;
	private String filename;
	private BufferedImage image;
	private ByteBuffer buffer;

	public Texture(String filename) {
		this.filename = filename;
		load();
	}

	public Texture(BufferedImage image, int x, int y, int w, int h) {
		load(image, x, y, w, h);
	}
	
	public Texture(String filename, int x, int y, int w, int h) {
		this.filename = filename;
		load(x, y, w, h);
	}

	public void load(){
		System.out.print("[Texture Loader] Loading texture...");
		try {
			image = ImageIO.read(new File(filename));
			int[] pixels = new int[image.getWidth() * image.getHeight()];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
			buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4); //4 for RGBA, 3 for RGB
			for(int y = 0; y < image.getHeight(); y++){
				for(int x = 0; x < image.getWidth(); x++){
					int pixel = pixels[y * image.getWidth() + x];
					buffer.put((byte) ((pixel >> 16) & 0xFF));
					buffer.put((byte) ((pixel >> 8) & 0xFF));
					buffer.put((byte) (pixel & 0xFF));
					buffer.put((byte) ((pixel >> 24) & 0xFF));
				}
			}
			buffer.flip();
			createTexture();
		} catch (Exception e) {
			System.err.println("  FAILED!");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!\n" + e);
			System.exit(1);
		}
	}
	
	public void load(BufferedImage img, int X, int Y, int W, int H){
		System.out.print("[Texture Loader] Loading texture...");
		try {
			image = new BufferedImage(W, H, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics g = image.getGraphics();
			
			g.drawImage(img, 0, 0, W, H, X, Y, X + W, Y + H, null);
			
			int[] pixels = new int[image.getWidth() * image.getHeight()];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
			buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4); //4 for RGBA, 3 for RGB
			for(int y = 0; y < image.getHeight(); y++){
				for(int x = 0; x < image.getWidth(); x++){
					int pixel = pixels[y * image.getWidth() + x];
					buffer.put((byte) ((pixel >> 16) & 0xFF));
					buffer.put((byte) ((pixel >> 8) & 0xFF));
					buffer.put((byte) (pixel & 0xFF));
					buffer.put((byte) ((pixel >> 24) & 0xFF));
				}
			}
			buffer.flip();
			createTexture();
		} catch (Exception e) {
			System.err.println("  FAILED!");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!\n" + e);
			System.exit(1);
		}
	}
	
	public void load(int X, int Y, int W, int H){
		System.out.print("[Texture Loader] Loading texture...");
		try {

			BufferedImage img = ImageIO.read(new File(filename));
			image = new BufferedImage(W, H, BufferedImage.TYPE_4BYTE_ABGR);
			Graphics g = image.getGraphics();
			
			g.drawImage(img, 0, 0, W, H, X, Y, X + W, Y + H, null);
			
			int[] pixels = new int[image.getWidth() * image.getHeight()];
			image.getRGB(0, 0, image.getWidth(), image.getHeight(), pixels, 0, image.getWidth());
			buffer = BufferUtils.createByteBuffer(image.getWidth() * image.getHeight() * 4); //4 for RGBA, 3 for RGB
			for(int y = 0; y < image.getHeight(); y++){
				for(int x = 0; x < image.getWidth(); x++){
					int pixel = pixels[y * image.getWidth() + x];
					buffer.put((byte) ((pixel >> 16) & 0xFF));
					buffer.put((byte) ((pixel >> 8) & 0xFF));
					buffer.put((byte) (pixel & 0xFF));
					buffer.put((byte) ((pixel >> 24) & 0xFF));
				}
			}
			buffer.flip();
			createTexture();
		} catch (Exception e) {
			System.err.println("  FAILED!");
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error!\n" + e);
			System.exit(1);
		}
	}
	
	public void createTexture(){	
		id = GL11.glGenTextures();
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, id);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL12.GL_CLAMP_TO_EDGE);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_NEAREST);
		GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_NEAREST);
		GL11.glTexImage2D(GL11.GL_TEXTURE_2D, 0, GL11.GL_RGBA8, image.getWidth(), image.getHeight(), 0, GL11.GL_RGBA, GL11.GL_UNSIGNED_BYTE, buffer);
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, 0);
		System.out.println("  OK!");
	}

	public int getID(){
		return id;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
}