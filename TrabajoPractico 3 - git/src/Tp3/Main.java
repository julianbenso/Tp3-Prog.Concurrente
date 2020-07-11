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
		
		TransicionTemporal t0 = new TransicionTemporal(0,50);
		TransicionTemporal t7 = new TransicionTemporal(7,50);
		TransicionTemporal t10 = new TransicionTemporal(10,50);
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
	//	procD1.start();
	//	procD2.start();
		
		Finalizador finalizador = new Finalizador(generador,nucleo1,nucleo2,proc1,proc2);
		monitor.setFinalizador(finalizador);
		
		while(true);
	}

}
