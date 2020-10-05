package monitor;

public class Philosopher implements Runnable{

	private int id;
	private StatusFilosofo statusPhilosopher;
	private int totalFilo;
	private Forks fork;
	
	public Philosopher(int id, int qtd, Forks fork) {
		
		this.id = id;
		this.totalFilo = qtd;
		this.fork = fork;
		new Thread((Runnable) this, " Philosopher").start();
		
	}
	
	public void setStatusFilosofo(StatusFilosofo statusFilosofo) {
		
		this.statusPhilosopher = statusFilosofo;
		
	}
	
	public StatusFilosofo getStatusFilosofo() {
		
		return this.statusPhilosopher;
		
	}
	
	public int getId() {
		
		return this.id;
		
	}
	
	@Override
	public String toString() {
		return  "\nID: " + this.id + " status: " + this.statusPhilosopher;
	}
	
	private void eat() {
		try {
			
			this.fork.getForks(this.id, this.totalFilo);
			this.setStatusFilosofo(StatusFilosofo.EAT);
			
			
			System.out.println(this.toString());
			Thread.sleep(this.getRandomNumber(2000, 3000));
			
			
			this.stop();
			
			
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void hungry() {
		try {
			this.setStatusFilosofo(StatusFilosofo.HUN);
			System.out.println(this.toString());
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private void stop() {
		this.fork.liberarGarfos(this.id, this.totalFilo);
	}
	
	private void tkink() {
		try {
			this.setStatusFilosofo(StatusFilosofo.THINK);
			System.out.println(this.toString());
			Thread.sleep(this.getRandomNumber(2000, 5000));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private int getRandomNumber(int min, int max) {
		return (int) (Math.random() * (max - min) + min);
	}	
	
	@Override
	public void run() {
		
		while (true) {
			
			this.hungry();
			this.eat();
			this.tkink();
			
		}	
		
	}

}