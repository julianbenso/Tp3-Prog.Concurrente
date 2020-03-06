package Tp3;

import java.util.ArrayList;

public class Generador implements Runnable {

    private Monitor monitor;
    private ArrayList<Integer> conjunto1;
    private ArrayList<Integer> conjunto2;

    public Generador(Monitor monitor){
        this.monitor = monitor;
        conjunto1 = new ArrayList<>();
        conjunto1.add(0); //Primer transicion a disparar por el generador
        conjunto2 = new ArrayList<>();
        conjunto2.add(1); //Transicion que agrega una tarea al buffer del Nucleo 1
        conjunto2.add(8); //Transicion que agrega una tarea al buffer del Nucleo 2
    }

    @Override
    public void run() {
        while(true){        //CAMBIAR ESTO O BUSCAR LA FORMA DE QUE ALGUNA VEZ FINALIZE
            monitor.disparo(conjunto1); //Primer transicion a disparar por el generador
            monitor.disparo(conjunto2);
        }
    }
}
