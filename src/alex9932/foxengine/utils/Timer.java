package alex9932.foxengine.utils;

public class Timer {
	private long startTime;

	public void start(){
		startTime = System.nanoTime();
	}

	public long getElapsedTime(){
		return System.nanoTime() - startTime;
	}
	public long getElapsedTimeAsMicroSeconds(){
		return (System.nanoTime() - startTime) / 1000;
	}
	public long getElapsedTimeAsMiliSeconds(){
		return (System.nanoTime() - startTime) / 1000000;
	}
	public long getElapsedTimeAsSeconds(){
		return (System.nanoTime() - startTime) / 1000000000;
	}
}