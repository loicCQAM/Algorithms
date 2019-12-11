package lab1;

public class Chronometre {
	private long tempsDebut;
	private long tempsTotal = 0;
	private boolean enCours = false;
	
	public void start() {
		enCours = true;
		tempsDebut = System.nanoTime();
	}
	public double stop() {
		double temps = getTempsActualise();
		enCours = false;
		return temps;
	}
	public void reset() {
		tempsTotal = 0;
	}
	public double getTempsActualise() {
		long tempsActuel = System.nanoTime();
		double tempsActualise;
		if (enCours) {
			tempsActualise = (tempsActuel - tempsDebut);
			tempsTotal += tempsActualise;
		}
		return tempsTotal/1000000000.0;
	}
}
