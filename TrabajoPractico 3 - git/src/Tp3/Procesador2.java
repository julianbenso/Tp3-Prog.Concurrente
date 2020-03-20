package Tp3;

import java.util.ArrayList;

public class Procesador2 implements Runnable {
    private Monitor monitor;
    private ArrayList<Integer> conjunto1;
    private ArrayList<Integer> conjunto2;
    private ArrayList<Integer> conjunto3;
    public boolean activo;

    public Procesador2(Monitor m){
        this.monitor = m;
        conjunto1 = new ArrayList<>();
        conjunto1.add(13);
        conjunto2 = new ArrayList<>();
        conjunto2.add(14);
        conjunto3 = new ArrayList<>();
        conjunto2.add(11);
        conjunto3.add(12);
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
            monitor.disparo(conjunto3);
        }
    }
}
