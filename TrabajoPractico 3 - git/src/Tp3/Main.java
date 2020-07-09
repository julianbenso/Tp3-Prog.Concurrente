package Tp3;

import java.io.IOException;

public class Main {
	public static void main(String[] args) {
		
		Rdp rdp = new Rdp();
		Politicas politicas = new Politicas(rdp);
		Monitor monitor = new Monitor(rdp,politicas);
		
		//---------------------------------------------
		//Creacion de las trancisiones con tiempo
		//---------------------------------------------
		
		TransicionTemporal t0 = new TransicionTemporal(0);
		TransicionTemporal t7 = new TransicionTemporal(7);
		TransicionTemporal t10 = new TransicionTemporal(10);
		rdp.agregarTemporal(t0);
		rdp.agregarTemporal(t7);
		rdp.agregarTemporal(t10);
		
		for(int i = 0; i < 15; i++) {
			politicas.agregarPrioridad(i, 0); //ESTO ES AUXILIAR SOLO PARA PROBAR SI ANDA
		}
		
		Generador generador = new Generador(monitor);
		Nucleo1 nucleo1 = new Nucleo1(monitor);
		Nucleo2 nucleo2 = new Nucleo2(monitor);
		Procesador1 proc1 = new Procesador1(monitor);
		Procesador2 proc2 = new Procesador2(monitor);
		
		generador.run();
		nucleo1.run();
		nucleo2.run();
		proc1.run();
		proc2.run();
		
		while(true);
	}

}
