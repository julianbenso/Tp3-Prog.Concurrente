package Tp3;

import java.io.IOException;

public class Main {
	
	private final static int ARRIVAL_RATE = 50;
	private final static int SERVICE_RATE_1 = 50;
	private final static int SERVICE_RATE_2 = 50;
	
	public static void main(String[] args) {
		
		Rdp rdp = new Rdp();
		Politicas politicas = new Politicas(rdp);
		Monitor monitor = new Monitor(rdp,politicas);
		Archivo archivo = new Archivo();
		rdp.setArchivo(archivo);
		
		//---------------------------------------------
		//Creacion de las trancisiones con tiempo
		//---------------------------------------------
		
		TransicionTemporal t0 = new TransicionTemporal(0,ARRIVAL_RATE);
		TransicionTemporal t7 = new TransicionTemporal(7,SERVICE_RATE_1);
		TransicionTemporal t10 = new TransicionTemporal(10,SERVICE_RATE_2);
		rdp.agregarTemporal(t0);
		rdp.agregarTemporal(t7);
		rdp.agregarTemporal(t10);
		
		politicas.agregarPrioridad(0, 5);
		politicas.agregarPrioridad(1, 3);
		politicas.agregarPrioridad(2, 0);
		politicas.agregarPrioridad(3, 0);
		politicas.agregarPrioridad(4, 0);
		politicas.agregarPrioridad(5, 0);
		politicas.agregarPrioridad(6, 0);
		politicas.agregarPrioridad(7, 0);
		politicas.agregarPrioridad(8, 3);
		politicas.agregarPrioridad(9, 0);
		politicas.agregarPrioridad(10, 0);
		politicas.agregarPrioridad(11, 0);
		politicas.agregarPrioridad(12, 0);
		politicas.agregarPrioridad(13, 0);
		politicas.agregarPrioridad(14, 0);


		
		Generador generador = new Generador(monitor);
		Nucleo1 nucleo1 = new Nucleo1(monitor);
		Nucleo2 nucleo2 = new Nucleo2(monitor);
		Procesador1 proc1 = new Procesador1(monitor);
		Procesador2 proc2 = new Procesador2(monitor);
		Proc_Down1 procD1 = new Proc_Down1(monitor);
		Proc_Down2 procD2 = new Proc_Down2(monitor);
		
		generador.start();
		nucleo1.start();
		nucleo2.start();
		proc1.start();
		proc2.start();
		procD1.start();
		procD2.start();
		
		Finalizador finalizador = new Finalizador(generador,nucleo1,nucleo2,proc1,proc2);
		monitor.setFinalizador(finalizador);
		
		try {
			generador.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.exit(0);

	}
	

}
