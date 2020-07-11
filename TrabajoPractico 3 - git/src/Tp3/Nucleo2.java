package Tp3;

import java.util.LinkedList;

public class Nucleo2 extends Thread {

    private Monitor monitor;
    private LinkedList<Integer> conjunto1;
    private LinkedList<Integer> conjunto2;
    public boolean activo;

    public Nucleo2(Monitor monitor){
        this.monitor = monitor;
        conjunto1 = new LinkedList<>();
        conjunto1.add(9);
        conjunto2 = new LinkedList<>();
        conjunto2.add(10);
        activo = true;
    }

    public void desactivar(){
        activo = false;
    }

    @Override
    public void run() {
        while(activo){
            System.out.println("Nucleo 2 intenta disparar conjunto 1");

            monitor.disparo(conjunto1);
            monitor.disparo(conjunto2);
        }
    }
}
