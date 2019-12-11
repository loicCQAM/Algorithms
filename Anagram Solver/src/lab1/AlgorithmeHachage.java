package lab1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Algorithme avec table de hachage
 * @author StandardG
 *
 */
public class AlgorithmeHachage implements IAlgorithme {
	public double runAlgo(List<String> chaines1, List<String> chaines2) {
		// INITIALISATION DE VARIABLES D'ALGORITHMES
		Chronometre chrono = new Chronometre();
		
		
		
		// ALGORITHME DE HACHAGE
		System.out.println("----------   Algorithme de table de hachage   ----------");
		chrono.start();
		int totalAnagrammes = 0;
		for(String ch1 : chaines1) {
			int count = 0;
			for(String ch2 : chaines2) {
				if(EstUnAnagrammeHachage(ch1, ch2)) {
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
	 * Algorithme de hachage
	 * @param chaine1
	 * @param chaine2
	 * @return
	 */
	public boolean EstUnAnagrammeHachage(String chaine1, String chaine2) {
		if (chaine1.length() != chaine2.length())
			return false;
		HashMap<String, IntMutable> hachage = new HashMap<String, IntMutable>();
		// ajoute les lettres de la 1ere chaine
		for (char c : chaine1.toCharArray()) {
			String s = String.valueOf(c);
			IntMutable compte = hachage.get(s);
			if (compte == null)
				hachage.put(s, new IntMutable());
			else
				compte.incrementer();
		}
		// retire les lettres de la 2e chaine
		for (char c : chaine2.toCharArray()) {
			String s = String.valueOf(c);
			IntMutable compte = hachage.get(s);
			if (compte == null)
				return false;
			else
				compte.decrementer();
		}
		
		// verifie que tout est a 0
		boolean anagramme = true;
		for (IntMutable i : hachage.values()) {
			if (!(i.get() == 0)) {
				anagramme = false;
				break;
			}
				
		}
		
		return anagramme;
	}
}
