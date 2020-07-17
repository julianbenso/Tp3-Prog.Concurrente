

import java.util.LinkedList;

public class Nucleo1 extends Thread {

    private Monitor monitor;
    private LinkedList<Integer> conjunto1;
    private LinkedList<Integer> conjunto2;
    private static boolean activo;

    public Nucleo1(Monitor monitor){
        this.monitor = monitor;
        conjunto1 = new LinkedList<>();
        conjunto1.add(6);
        conjunto2 = new LinkedList<>();
        conjunto2.add(7);
        activo = true;
    }

    public static void desactivar(){
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