package Tp3;

import java.util.LinkedList;

public class Proc_Down2 extends Thread {
	
	private Monitor monitor;
    private LinkedList<Integer> conjunto1;
    private LinkedList<Integer> conjunto2;
    public static boolean activo;

    public Proc_Down2(Monitor m){
        this.monitor = m;
        conjunto1 = new LinkedList<>();
        conjunto1.add(11);
        conjunto2 = new LinkedList<>();
        conjunto2.add(12);
        activo = true;
    }

    public static void desactivar(){
        activo = false;
    }

    @Override
    public void run() {
        while(activo){
      //      monitor.disparo(conjunto1);
            monitor.disparo(conjunto2);
        }
    }

}
