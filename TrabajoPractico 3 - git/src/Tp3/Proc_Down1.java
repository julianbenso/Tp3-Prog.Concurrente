package Tp3;

import java.util.LinkedList;

public class Proc_Down1 extends Thread {
	
	private Monitor monitor;
    private LinkedList<Integer> conjunto1;
    private LinkedList<Integer> conjunto2;
    public boolean activo;

    public Proc_Down1(Monitor m){
        this.monitor = m;
        conjunto1 = new LinkedList<>();
        conjunto1.add(5);
        conjunto2 = new LinkedList<>();
        conjunto2.add(2);
        activo = true;
    }

    public void desactivar(){
        activo = false;
    }

    @Override
    public void run() {
        while(activo){
            System.out.println("Procesador down 1 intenta disparar conjunto 1");
            monitor.disparo(conjunto1);
            monitor.disparo(conjunto2);
        }
    }

}
