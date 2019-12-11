package lab1;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestWordLength {
	
	private static final int NOMBRE_EXECUTION = 1;
	
	Random random = new Random();
	
//	public TestWordLength(){
//		testCase(length);
//	}
	
//	public void testCase(long length){
//		String word1 = generateWord(length);
//		String word2 = shuffle(word1);
//		double tempsTotal = 0;
//		double anagrammeBase;
//		double anagrammeTri;
//		double anagrammeHachage;
//		double anagrammePrimes;
//		NumberFormat f = new DecimalFormat("#0.000000000000");
//		
//		List<String> chaines1 = new ArrayList<String>();
//		List<String> chaines2 = new ArrayList<String>();
//		
//		chaines1.add(word1);
//		chaines2.add(word2);
//		
//		AlgorithmeBase algoBase = new AlgorithmeBase(); 
//		AlgorithmeTri algoTri = new AlgorithmeTri(); 
//		AlgorithmeHachage algoHachage = new AlgorithmeHachage(); 
//		AlgorithmePrimes algoPrimes = new AlgorithmePrimes(); 
//		
//		tempsTotal = 0;
//		for (int i=0; i<NOMBRE_EXECUTION; i++) {
//			tempsTotal += algoBase.runAlgo(chaines1, chaines2);
//		}
//		System.out.println("Algorithme de base");
//		System.out.println("Temps moyen: " + f.format(tempsTotal/NOMBRE_EXECUTION));
//		System.out.println("");
//		
//		tempsTotal = 0;
//		for (int i=0; i<NOMBRE_EXECUTION; i++) {
//			tempsTotal += algoTri.runAlgo(chaines1, chaines2);
//		}
//		System.out.println("Algorithme de tri");
//		System.out.println("Temps moyen: " + f.format(tempsTotal/NOMBRE_EXECUTION));
//		System.out.println("");
//		
//		tempsTotal = 0;
//		for (int i=0; i<NOMBRE_EXECUTION; i++) {
//			tempsTotal += algoHachage.runAlgo(chaines1, chaines2);
//		}
//		System.out.println("Algorithme de hachage");
//		System.out.println("Temps moyen: " + f.format(tempsTotal/NOMBRE_EXECUTION));
//		System.out.println("");
//		
//		tempsTotal = 0;
//		for (int i=0; i<NOMBRE_EXECUTION; i++) {
//			tempsTotal += algoPrimes.runAlgo(chaines1, chaines2);
//		}
//		System.out.println("Algorithme de nombres premiers");
//		System.out.println("Temps moyen: " + f.format(tempsTotal/NOMBRE_EXECUTION));
//		System.out.println("");
//	}
	
	public String generateWord(long length){
		String alphabet = "0123456789abcdefghijklmnopqrstuvwxyz";
		String word = "";
		
		
	    for (int i = 0; i < length; i++) {
			int num = random.nextInt(alphabet.length());
			char car = alphabet.charAt(num);
			word += car;
	    }
	    
	    return word;
	}

	
	/*
	 * Methode created by cherouvim on Stackoverflow
	 * https://stackoverflow.com/questions/3316674/how-to-shuffle-characters-in-a-string
	 */
	public String shuffle(String text) {
	    char[] characters = text.toCharArray();
	    for (int i = 0; i < characters.length; i++) {
	        int randomIndex = (int)(Math.random() * characters.length);
	        char temp = characters[i];
	        characters[i] = characters[randomIndex];
	        characters[randomIndex] = temp;
	    }
	    return new String(characters);
	}
	
	public String quickShuffle(String text) {
		Random rnd = new Random();
		char[] chars = text.toCharArray();
		int length = text.length();
		if (length > 10)
			length = 10;

		int indice;
		for (int i = 0; i < length; i++) {
			indice = rnd.nextInt(text.length() - 1);
			char temp = chars[indice];
	        chars[i] = chars[indice+1];
	        chars[indice+1] = temp;
		}
		return new String(chars);
	}
}
