package Tp3;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		
		Rdp rdp = new Rdp();
		Politicas politicas = new Politicas(rdp);
		Monitor monitor = new Monitor(rdp,politicas);
		//---------------------------------------------
		
		
		
		
	}

}
