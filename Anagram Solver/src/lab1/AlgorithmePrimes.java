package lab1;

import java.util.HashMap;
import java.util.List;

public class AlgorithmePrimes implements IAlgorithme {
	
	HashMap<Character, Integer> values = new HashMap<Character, Integer>();
	
	public double runAlgo(List<String> chaines1, List<String> chaines2) {
		// INITIALISATION DE VARIABLES D'ALGORITHMES
		int totalAnagrammes = 0;
		double tempsTotal = 0;
		long temps02 = 0;
		long temps01 = 0;
		
		values.put('a', 2);		values.put('b', 3); 	values.put('c', 5);
		values.put('d', 7); 	values.put('e', 11); 	values.put('f', 13);
		values.put('g', 17); 	values.put('h', 19); 	values.put('i', 23);
		values.put('j', 29); 	values.put('k', 31); 	values.put('l', 37);
		values.put('m', 41); 	values.put('n', 43); 	values.put('o', 47);
		values.put('p', 53); 	values.put('q', 59); 	values.put('r', 61);
		values.put('s', 67); 	values.put('t', 71); 	values.put('u', 73);
		values.put('v', 79); 	values.put('w', 83); 	values.put('x', 89);
		values.put('y', 97); 	values.put('z', 101);	values.put('1', 103);
		values.put('2', 107);	values.put('3', 109); 	values.put('4', 113);
		values.put('5', 127);	values.put('6', 131); 	values.put('7', 137);
		values.put('8', 139); 	values.put('9', 149); 	values.put('0', 151);
		
		// ALGORITHME DE HACHAGE
		System.out.println("Exécution algorithme de table de hachage-----------------------");
		temps01 = System.nanoTime();
		
		for(String ch1 : chaines1) {
			int count = 0;
			for(String ch2 : chaines2) {
				if(EstUnAnagrammePrimes(ch1, ch2, values)) {
					count++;
					totalAnagrammes++;
				}
			}
			System.out.println(ch1 + " a " + count + " anagramme(s)");
		}
		temps02 = System.nanoTime();
		tempsTotal = (temps02 - temps01)/1000000000.0;
		
		System.out.println("Il y a un total de " + totalAnagrammes + " anagrammes.");
		System.out.println("Temps d'exécution: " + tempsTotal + " secondes.");
		System.out.println();
//		System.out.println();

		return tempsTotal;
	}
	
	
	/**
	 * On associe haque lettre de l'alphabet à un nombre premier.
	 * On effectue le produit des lettres des deux mots.
	 * Si les deux produits sont équivalent, on a un anagramme !
	 *
	 * @ordre  n
	 * @param  chaine1	Premier mot
	 * @param  chaine2	Second mot
	 * @return      	Est-ce que les mots sont des anagrammes
	 */
	public boolean EstUnAnagrammePrimes(String chaine1, String chaine2, HashMap<Character, Integer> values){
		if(chaine1.length() != chaine2.length())
			return false;
		
		long value1 = 1;
		long value2 = 1;
		char[] ch1 = chaine1.toCharArray();
		char[] ch2 = chaine2.toCharArray();
		
		for(int i = 0; i < ch1.length; i++){
			value1 = value1 * values.get(ch1[i]);
			value2 = value2 * values.get(ch2[i]);
		}
		
		return value1 == value2;
	}
}
