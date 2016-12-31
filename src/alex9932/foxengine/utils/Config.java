package alex9932.foxengine.utils;

import java.io.File;

import org.ini4j.Ini;

import alex9932.foxengine.Main;
import alex9932.foxengine.entity.Player;

public class Config {

	public static float fov;
	public static float renderDistance;
	public static float gravity;
	public static float x;
	public static float y;
	public static float z;
	public static float anglex;
	public static float angley;
	public static float sensetivity;
	public static String model;
	public static boolean flymode;
	public static boolean drawmodels;
	
	public static final String CONFIG = "./res/config/engine.cfg";
	
	public static void read() {
		try {
			Main.println("Reading cfg file...");
			Ini ini = new Ini(new File(CONFIG));
			Main.println("Reading cfg params...");
			fov = Float.parseFloat(ini.get("foxengine", "fov"));
			renderDistance = Float.parseFloat(ini.get("foxengine", "renderDistance"));
			model = ini.get("foxengine", "model");
			flymode = Boolean.getBoolean(ini.get("player", "flymode"));
			gravity = Float.parseFloat(ini.get("player", "gravity"));
			x = Float.parseFloat(ini.get("player", "x"));
			y = Float.parseFloat(ini.get("player", "y"));
			z = Float.parseFloat(ini.get("player", "z"));
			anglex = Float.parseFloat(ini.get("player", "anglex"));
			angley = Float.parseFloat(ini.get("player", "angley"));
			sensetivity = Float.parseFloat(ini.get("player", "sensetivity"));
			drawmodels = Boolean.getBoolean(ini.get("renderer", "drawmodels"));
			Main.println("Done!");
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public static void write() {
		String cfg = "";
		cfg = cfg + "[foxengine]\n";
		cfg = cfg + "fov = " + fov + "\n";
		cfg = cfg + "renderDistance = " + renderDistance + "\n";
		cfg = cfg + "model = " + model + "\n";
		cfg = cfg + "[player]\n";
		cfg = cfg + "flymode = " + flymode + "\n";
		cfg = cfg + "gravity = " + gravity + "\n";
		cfg = cfg + "x = " + x + "\n";
		cfg = cfg + "y = " + y + "\n";
		cfg = cfg + "z = " + z + "\n";
		cfg = cfg + "anglex = " + anglex + "\n";
		cfg = cfg + "angley = " + angley + "\n";
		cfg = cfg + "[renderer]\n";
		cfg = cfg + "drawmodels = " + drawmodels + "\n";
		new FileIO().writeFile(CONFIG, cfg);
		
	}
	
	public static void apply() {
		Main.println("Applying params...");
		Main.renderDistance = renderDistance;
		Main.MODEL_PATH = model;
		Main.fov = fov;
		Main.drawmodels = drawmodels;
		Main.println("Applying params to player...");
		Player.angleX = anglex;
		Player.angleY = angley;
		Player.x = x;
		Player.y = y;
		Player.z = z;
		Player.sensetivity = sensetivity;
		Player.gravity = gravity;
		//Player.ignoreALL = flymode;
		Main.println("Done!");
	}
}