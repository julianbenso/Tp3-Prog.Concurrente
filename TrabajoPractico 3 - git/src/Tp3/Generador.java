package Tp3;

import java.util.LinkedList;

public class Generador extends Thread {

    private Monitor monitor;
    private LinkedList<Integer> conjunto1;
    private LinkedList<Integer> conjunto2;
    public boolean activo;

    public Generador(Monitor monitor){
        this.monitor = monitor;
        conjunto1 = new LinkedList<>();
        conjunto1.add(0); //Primer transicion a disparar por el generador
        conjunto2 = new LinkedList<>();
        conjunto2.add(1); //Transicion que agrega una tarea al buffer del Nucleo 1
        conjunto2.add(8); //Transicion que agrega una tarea al buffer del Nucleo 2
        activo = true;
    }

    public void desactivar(){
        activo = false;
    }

    @Override
    public void run() {
        while(activo){        //CAMBIAR ESTO O BUSCAR LA FORMA DE QUE ALGUNA VEZ FINALIZE
            monitor.disparo(conjunto1); //Primer transicion a disparar por el generador
            monitor.disparo(conjunto2);
            try {
				sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
    }
}
