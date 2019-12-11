package lab1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class main {

	private static final int LONGUEUR_MOT_MAX = 2000000000;
	
	// MAIN DE GÉNÉRATION DE FICHIERS À TESTER
	public static void main(String[] args) {
		GenerateurFichiersTXT gen = new GenerateurFichiersTXT();
		String fichierMots = args[0];
		String fichierDict = args[1];
		String nbDeMotsDansMots = args[2];
		String nbDeMotsDansDict = args[3];
		
		
		gen.genererFichierTXT(fichierMots, Integer.parseInt(nbDeMotsDansMots), LONGUEUR_MOT_MAX, null);
		File words = new File(fichierMots);
		try {
			List<String> anagrammes = extraireChaines(words);
			gen.genererFichierTXT(fichierDict, Integer.parseInt(nbDeMotsDansDict), LONGUEUR_MOT_MAX, anagrammes);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// MAIN DE TEST D'ALGORITHMES
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//		
//		File words = new File(args[0]);
//		File dict = new File(args[1]);
////		TestWordLength testCase = new TestWordLength(2000);
//		
//		// extraction des mots
//		try {
//			List<String> chaines1 = extraireChaines(words);
//			List<String> chaines2 = extraireChaines(dict);
//			double tempsTotal = 0;
//			IAlgorithme algo;
//			NumberFormat f = new DecimalFormat("#0.000 000 000 000");
//
//
//			new AlgorithmeHachage().runAlgo(chaines1, chaines2);
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
	
	public static List<String> extraireChaines(File fichier) throws IOException{
		FileReader fr = new FileReader(fichier);
		BufferedReader brw = new BufferedReader(fr);
		String chaine;
		List<String> chaines = new ArrayList<String>();
		while ((chaine = brw.readLine()) != null && chaine.length() != 0) 
		{ 
			// Enlever les espaces de la chaine
			chaine = chaine.replaceAll("\\s+","");
			chaines.add(chaine);
		}
		return chaines;
	}
	
//	System.out.println("Exécution de l'algorithme de base * "+NOMBRE_EXECUTION);
//	algo = new AlgorithmeBase();
//	tempsTotal = 0;
//	for (int i=0; i<NOMBRE_EXECUTION; i++) {
//		tempsTotal += algo.runAlgo(chaines1, chaines2);
//	}
//	System.out.println("Temps moyen: " + f.format(tempsTotal/NOMBRE_EXECUTION));
}
