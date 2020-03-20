package Tp3;

import java.util.ArrayList;

public class Nucleo2 implements Runnable {

    private Monitor monitor;
    private ArrayList<Integer> conjunto1;
    private ArrayList<Integer> conjunto2;
    public boolean activo;

    public Nucleo2(Monitor monitor){
        this.monitor = monitor;
        conjunto1 = new ArrayList<>();
        conjunto1.add(9);
        conjunto2 = new ArrayList<>();
        conjunto2.add(10);
        activo = true;
    }

    public void desactivar(){
        activo = false;
    }

    @Override
    public void run() {
        while(activo){
            monitor.disparo(conjunto1);
            monitor.disparo(conjunto2);
        }
    }
}
