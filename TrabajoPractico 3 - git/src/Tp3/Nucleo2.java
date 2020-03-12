package Tp3;

import java.util.ArrayList;

public class Nucleo2 {

    private Monitor monitor;
    private ArrayList<Integer> conjunto1;
    private ArrayList<Integer> conjunto2;

    public Nucleo2(Monitor monitor){
        this.monitor = monitor;
        conjunto1 = new ArrayList<>();
        conjunto1.add(9);
        conjunto2 = new ArrayList<>();
        conjunto2.add(10);
    }

    @Override
    public void run() {
        while(true){
            monitor.disparo(conjunto1);
            monitor.disparo(conjunto2);
        }
    }
}
