package Tp3;

import java.util.ArrayList;

public class Nucleo1 implements Runnable {

    private Monitor monitor;
    private ArrayList<Integer> conjunto1;
    private ArrayList<Integer> conjunto2;

    public Nucleo1(Monitor monitor){
        this.monitor = monitor;
        conjunto1 = new ArrayList<>();
        conjunto1.add(6);
        conjunto2 = new ArrayList<>();
        conjunto2.add(7);
    }

    @Override
    public void run() {

    }
}
