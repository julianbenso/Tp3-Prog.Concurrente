package Tp3;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Monitor {

    private final ReentrantLock mutex;
    private Condition condiciones[];
    private Rdp rdp;

    public Monitor(Rdp redPetri){
        this.rdp = redPetri;
        mutex = new ReentrantLock();
        creacionDeCondiciones();
    }

    private void creacionDeCondiciones(){ //Creamos tantas condiciones como transiciones tenemos
        for(int i = 0; i < rdp.getCantidadTransiciones(); i++){
            condiciones[i] = mutex.newCondition();
        }
    }

    public void disparar(int transicion){
        try {
            mutex.lock();

            while (!rdp.testSensibilisado(transicion)) {
                try {
                    condiciones[transicion].await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (rdp.isTemp(transicion)) {
                //METODO PARA DISPARAR TEMPORIZADAS
            } else {
                //METODO PARA DISPARAR NORMALES DE LA CLASE RDP
            }

            //METODO PARA, EN RELACIONA NUESTRAS POLITICAS, HACERLE RELEASE A UN HILO DORMIDO
            signalSalida();

        }
        finally{
            mutex.unlock();
        }
    }

    private void signalSalida(){ //NECESITO ARMAR LAS POLITICAS PARA ESTE METODO

    }
}
