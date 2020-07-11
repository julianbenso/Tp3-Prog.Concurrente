package Tp3;

import java.util.LinkedList;

public class Procesador2 extends Thread {
    private Monitor monitor;
    private LinkedList<Integer> conjunto1;
    private LinkedList<Integer> conjunto2;
    private LinkedList<Integer> conjunto3;
    public boolean activo;

    public Procesador2(Monitor m){
        this.monitor = m;
        conjunto1 = new LinkedList<>();
        conjunto1.add(13);
        conjunto2 = new LinkedList<>();
        conjunto2.add(14);
        conjunto3 = new LinkedList<>();
        conjunto3.add(11);
        conjunto3.add(12);
        activo = true;
    }

    public void desactivar(){
        activo = false;
    }

    @Override
    public void run() {
        while(activo){
            System.out.println("Procesador 2 intenta disparar conjunto 1");
            monitor.disparo(conjunto1);
            monitor.disparo(conjunto2);
            monitor.disparo(conjunto3);
        }
    }
}
