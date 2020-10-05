package monitor;

import javafx.util.Pair;
import monitor.Fork;
import monitor.Forks;

public class Forks {

	private Fork[] forks;
	
	public Forks(int qtd) {
		this.forks = new Fork[qtd];
		for (int i = 0; i < this.forks.length; i++) {
			this.forks[i] = new Fork();
		}
	}
	
	
	synchronized public void getForks(int idFilosofo, int qtdPhilo) throws InterruptedException {
		Pair<Integer, Integer> indexGarfos = this.getForkRightLeft(idFilosofo, qtdPhilo);

		
		Fork forkRight = this.forks[indexGarfos.getValue()]; 
		Fork forkLeft = this.forks[indexGarfos.getKey()]; 

		if(forkRight.getStatusFork() == StatusFork.B) {
			wait();
		}

		if(forkLeft.getStatusFork() == StatusFork.B) {
			wait();
		}
		
		forkRight.setStatusFork(StatusFork.B);
		forkLeft.setStatusFork(StatusFork.B);
		
	}
	
	synchronized public void liberarGarfos(int idFilosofo, int qtdFilosofos) {
		Pair<Integer, Integer> indexGarfos = this.getForkRightLeft(idFilosofo, qtdFilosofos);
		
		this.forks[indexGarfos.getValue()].setStatusFork(StatusFork.F);
		this.forks[indexGarfos.getKey()].setStatusFork(StatusFork.F);
		
		notify();
	}
	
	private Pair<Integer, Integer> getForkRightLeft(int idPhilo, int qtdPhilo) {
		int indexForkRight, indexForkLeft = -1;
		
		if(idPhilo == 1) {
			indexForkRight = qtdPhilo-1;
		}
		else {
			indexForkRight = idPhilo-2;
		}
		indexForkLeft = idPhilo-1;
		
		return new Pair<Integer, Integer>(indexForkLeft, indexForkRight);
	}
}