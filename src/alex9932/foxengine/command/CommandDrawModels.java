package alex9932.foxengine.command;

import alex9932.foxengine.Main;

public class CommandDrawModels implements ICommand {
	@Override
	public void execute(String[] args) {
		if(args.length < 1) return;
		Main.drawmodels = Boolean.getBoolean(args[0]);
	}

	@Override
	public String name() {
		return "drawmodels";
	}
}
