package lab1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AlgorithmeTri2 implements IAlgorithme {
	public double runAlgo(List<String> chaines1, List<String> chaines2) {
		// INITIALISATION DE VARIABLES D'ALGORITHMES
		Chronometre chrono = new Chronometre();
		
		
		
		// ALGORITHME TRI+COMPARAISON
		System.out.println("----------   Algorithme de tri global   ----------");
		chrono.start();
		int totalAnagrammes = 0;
		List<char[]> chaines1Tri = new ArrayList<char[]>();
		List<char[]> chaines2Tri = new ArrayList<char[]>();
		int indiceChaines1 = 0;
		for (String ch1 : chaines1) {
			chaines1Tri.add(TriMot(ch1));
		}
		for (String ch2 : chaines2) {
			chaines2Tri.add(TriMot(ch2));
		}
		for(char[] ch1 : chaines1Tri) {
			int count = 0;
			for(char[] ch2 : chaines2Tri) {
				if(Arrays.equals(ch1, ch2)) {
					count++;
					totalAnagrammes++;
				}
			}
			chrono.stop();
			System.out.println(chaines1.get(indiceChaines1) + " a " + count + " anagramme(s)");
			chrono.start();
			indiceChaines1++;
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
//	public boolean EstUnAnagrammeComparaison(String chaine1, String chaine2) {
//		if (chaine1.length() != chaine2.length())
//			return false;
//		return TriMot(chaine1).equals(TriMot(chaine2));
//	}
	public static char[] TriMot(String chaine) {
		char[] mot = chaine.toCharArray();
		Arrays.sort(mot);
		return mot;
	}
}
