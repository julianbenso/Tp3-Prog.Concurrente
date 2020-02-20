package Tp3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class LectorMatrices {
	
	private BufferedReader lector = null;

	public LectorMatrices() {
	}
	
	public int[][] leerMatrizI(String path) throws IOException {
		File file = new File(path); 
		  
		lector = new BufferedReader(new FileReader(file));
		
		int[][] m = new int[16][15]; 
		
		int aux = 2;
		for(int i = -1, j = 0;(aux = lector.read()) != -1; ) {
			if(aux == 'P') {
				lector.readLine();
				i++;
			}
			else {
				if(aux == '0' || aux == '1') {
					m[i][j] = aux - 48;
					j++;
					if(j == 15) j = 0;
				}
			}
		}
		lector.close();
		
		return m;
	}
	
	public int[] leerVector(String path) throws IOException {
		File file = new File(path);
		
		lector = new BufferedReader(new FileReader(file));
		
		int[] v = new int[16];
		int aux = 2;
		for(int i = 0; (aux = lector.read()) != -1;) {
			if(aux == '0' || aux == '1') {
				v[i] = aux-48;
				i++;
			}
		}
		
		lector.close();
		return v;
	}
}
