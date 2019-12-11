package lab1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.List;

/**
 * Algorithme de tri
 * @author StandardG
 *
 */
public class AlgorithmeTri implements IAlgorithme {
	public double runAlgo(List<String> chaines1, List<String> chaines2) {
		// INITIALISATION DE VARIABLES D'ALGORITHMES
		Chronometre chrono = new Chronometre();
		

		
		// ALGORITHME TRI+COMPARAISON
		System.out.println("----------   Algorithme de tri   ----------");
		chrono.start();
		int totalAnagrammes = 0;
		for(String ch1 : chaines1) {
			int count = 0;
			for(String ch2 : chaines2) {
				if(EstUnAnagrammeComparaison(ch1, ch2)) {
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
	 * Algorithme de tri+comparaison
	 * @param chaine1
	 * @param chaine2
	 * @return
	 */
	public boolean EstUnAnagrammeComparaison(String chaine1, String chaine2) {
		if (chaine1.length() != chaine2.length())
			return false;
		return Arrays.equals(TriMot(chaine1), TriMot(chaine2));
	}
	public static char[] TriMot(String chaine) {
		char[] mot = chaine.toCharArray();
		Arrays.sort(mot);
		return mot;
	}
}
