package lab1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * Algorithme de base (a battre)
 * @author StandardG
 *
 */
public class AlgorithmeBase implements IAlgorithme {

	public double runAlgo(List<String> chaines1, List<String> chaines2) {
		// INITIALISATION DE VARIABLES D'ALGORITHMES
		Chronometre chrono = new Chronometre();

		
		
		// ALGORITHME DE BASE
		System.out.println("----------   Algorithme de base   ----------");
		chrono.start();
		int totalAnagrammes = 0;
		for(String ch1 : chaines1) {
			int count = 0;
			for(String ch2 : chaines2) {
				if(EstUnAnagramme(ch1, ch2)) {
					count++;
					totalAnagrammes++;
				}
			}
			chrono.stop();
			System.out.println(ch1 + " a " + count + " anagramme(s)");
			chrono.start();
		}
		chrono.stop();
		
		
		
		NumberFormat f = new DecimalFormat("#0.000 000 000 000");
		System.out.println("Il y a un total de " + totalAnagrammes + " anagrammes.");
		System.out.println("Temps d'exécution: " + f.format(chrono.getTempsActualise()) + " secondes.");
		System.out.println();
		
		return chrono.getTempsActualise();
	}
	
	/**
	 * Algorithme de base
	 * @param chaine1
	 * @param chaine2
	 * @return
	 */
	public boolean EstUnAnagramme(String chaine1, String chaine2) {
		StringBuilder ch2 = new StringBuilder(chaine2);
		for(int i = 0; i < chaine1.length(); i++) {
			boolean trouve = false;
			int j = 0;
			while(!trouve && j < ch2.length()) {
				if (chaine1.charAt(i) == ch2.charAt(j)) {
					ch2.deleteCharAt(j);
					trouve = true;
				}
				j++;
			}
			if(!trouve) {
				return false;
			}
		}
		if(ch2.length() > 0) {
			return false;
		}
		
		return true;
	}
}
