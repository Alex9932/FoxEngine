function main(args) {
	system.println("Hello, JS!");

	file.writeFile("./logs/player.log", "===Logger has started===");

	/**while(true){
		file.appendFile("./logs/player.log", "Player: x:" + player.x + "  y:" + player.y + "  z:" + player.z + "   anglex:" + player.anglex + "  angley:" + player.angley + "\n");
	}**/

	system.println("Done!");
}