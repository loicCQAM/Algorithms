package lab1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GenerateurFichiersTXT {
	static Random rnd = new Random();
	
	public static void genererFichierTXT(String nomFichier, int nbMots, int longueurMaxMot, List<String> anagrammes) {
		try{
			
			BufferedWriter wr = new BufferedWriter(new FileWriter(new File(nomFichier)));
		    TestWordLength generateur = new TestWordLength();
		    for (int i = 0; i < nbMots; i++) {
		    	String mot;
		    	if (anagrammes != null && Math.random() < 0.7) {
		    		mot = generateur.quickShuffle(anagrammes.get(rnd.nextInt(anagrammes.size())));
		    		wr.append(mot+"\n");
		    	} else {
		    		mot = generateur.generateWord(rnd.nextInt(longueurMaxMot));
		    		wr.append(mot+"\n");
		    	}
		    
		    }
		    wr.flush();
		    wr.close();
		    
		} catch (IOException e) {
		   e.printStackTrace();
		}
	}
}
