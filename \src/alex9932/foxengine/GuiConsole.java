package alex9932.foxengine;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

import alex9932.foxengine.gui.GuiScreen;

public class GuiConsole extends GuiScreen {
	public String command = "";
	public String[] texts = new String[68];
	public int pos = 1;

	public GuiConsole() {
		for (int i = 0; i < texts.length; i++) {
			texts[i] = "";
		}
	}
	
	@Override
	public void drawScreen() {
		try{
		GL11.glColor3f(1, 1, 1);
		GL11.glDisable(GL11.GL_FOG);
		GL11.glDisable(GL11.GL_LIGHTING);
		GL11.glDisable(GL11.GL_DEPTH_TEST);
		for (int i = 0; i < getHeight(); i++) {
			gui.drawLine(0, i, getWidth(), i);
		}
		for (int i = 0; i < texts.length; i++) {
			gui.drawString(texts[i], 5, 5 + (i * 15), Color.black);
		}
		texts[0] = "Game console";
		gui.drawString("> " + command, 5, getHeight() - 15, Color.black);
		//System.out.println(command);
		if(Keyboard.isKeyDown(Keyboard.KEY_A)){
			command = command + "a";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_B)){
			command = command + "b";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_C)){
			command = command + "c";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_D)){
			command = command + "d";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_E)){
			command = command + "e";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F)){
			command = command + "f";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_G)){
			command = command + "g";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_H)){
			command = command + "h";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_I)){
			command = command + "i";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_J)){
			command = command + "j";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_K)){
			command = command + "k";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_L)){
			command = command + "l";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_M)){
			command = command + "m";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_N)){
			command = command + "n";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_O)){
			command = command + "o";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_P)){
			command = command + "p";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Q)){
			command = command + "q";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_R)){
			command = command + "r";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_S)){
			command = command + "s";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_T)){
			command = command + "t";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_U)){
			command = command + "u";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_V)){
			command = command + "v";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_W)){
			command = command + "w";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_X)){
			command = command + "x";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Y)){
			command = command + "y";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_Z)){
			command = command + "z";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_1)){
			command = command + "1";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_2)){
			command = command + "2";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_3)){
			command = command + "3";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_4)){
			command = command + "4";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_5)){
			command = command + "5";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_6)){
			command = command + "6";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_7)){
			command = command + "7";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_8)){
			command = command + "8";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_9)){
			command = command + "9";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_0)){
			command = command + "0";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
			command = command + " ";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_RETURN)){
			texts[pos] = "Command " + command + " executed!";
			Main.cmd.run(command);
			pos++;
			if(pos > 68){
				pos = 68;
				for (int i = 1; i < texts.length; i++) {
					texts[i - 1] = texts[i];
				}
			}
			
			command = "";
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_BACK)){
			try{
				command = command.substring(0, command.length() - 1);
			}catch(Exception e){
			
			}
			sleep(70);
		}
		if(Keyboard.isKeyDown(Keyboard.KEY_F3)){
			Main.isConsoleOpened = false;
		}
		}catch(Exception e){
			e.printStackTrace();
			if(!(e instanceof NullPointerException)){
				System.exit(1);
			}
		}
	}
}