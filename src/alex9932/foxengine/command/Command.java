package alex9932.foxengine.command;

import java.util.ArrayList;

import alex9932.foxengine.Main;

public class Command {
	public ArrayList<ICommand> commands = new ArrayList<ICommand>();

	public void addCommand(ICommand command) {
		this.commands.add(command);
	}
	
	public void removeCommand(ICommand command) {
		this.commands.remove(command);
	}
	
	public void run(String command){
		String[] cmd = command.split(" ");
		for (int i = 0; i < commands.size(); i++) {
			Main.println("Command " + command);
			Main.println("Command " + cmd[i]);
			Main.println("Command chek:" + commands.get(i).name());
			if(commands.get(i).name().equals(cmd[0])){
				String[] args = new String[cmd.length - 1];
				for (int j = 1; j < cmd.length; j++) {
					args[j - 1] = cmd[j];
					Main.println("Args: " + args[j - 1]);
				}
				commands.get(i).execute(args);
			}
		}
	}
}