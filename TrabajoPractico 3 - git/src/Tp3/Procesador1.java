package Tp3;

import java.util.ArrayList;

public class Procesador1 implements Runnable {

    private Monitor monitor;
    private ArrayList<Integer> conjunto1;
    private ArrayList<Integer> conjunto2;
    private ArrayList<Integer> conjunto3;
    public boolean activo;

    public Procesador1(Monitor m){
        this.monitor = m;
        conjunto1 = new ArrayList<>();
        conjunto1.add(3);
        conjunto2 = new ArrayList<>();
        conjunto2.add(4);
        conjunto3 = new ArrayList<>();
        conjunto2.add(5);
        conjunto3.add(2);
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
