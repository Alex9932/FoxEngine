package alex9932.foxengine.command;

public interface ICommand {
	public void execute(String[] args);
	public String name();
}