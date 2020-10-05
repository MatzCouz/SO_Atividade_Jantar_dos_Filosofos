package monitor;

public class Fork {

	private StatusFork statusFork;
	
	public Fork() {
		this.statusFork = StatusFork.F;
	}

	public void setStatusFork(StatusFork statusGarfo) {
		this.statusFork = statusGarfo;
	}
	
	public StatusFork getStatusFork() {
		return this.statusFork;
	}
	
}