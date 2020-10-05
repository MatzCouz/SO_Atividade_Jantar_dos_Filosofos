package monitor;

public class Main {
	
	public static void main(String[] args) {
		
		final int qtdFilosofos = 10;
		
		Forks g = new Forks(qtdFilosofos);

		for (int i = 1; i <= qtdFilosofos; i++) {
			new Philosopher(i, qtdFilosofos, g);
		}
		
	}
}