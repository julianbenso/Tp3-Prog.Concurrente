package Tp3;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Monitor {

    private final ReentrantLock mutex;
    private Condition condiciones[];
    private Rdp rdp;
    private Politicas politicas;

    public Monitor(Rdp redPetri, Politicas pol){
        this.rdp = redPetri;
        mutex = new ReentrantLock();
        creacionDeCondiciones();
        politicas = pol;
    }

    private void creacionDeCondiciones(){ //Creamos tantas condiciones como transiciones tenemos
        for(int i = 0; i < rdp.getCantidadTransiciones(); i++){
            condiciones[i] = mutex.newCondition();
        }
    }

  /*  public void disparar(int transicion){
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
    }*/

    public void disparo(ArrayList<Integer> transiciones){
        try{
            mutex.lock();
            int eleccion;

            if(transiciones.size() == 1) eleccion = transiciones.get(0);
            else{
                eleccion = politicas.elegirTransicion(transiciones); //Si se encuentra conflicto, se decide con las politicas la transicion a disparar
            }

            while (!rdp.testSensibilisado(eleccion)) {
                try {
                    condiciones[eleccion].await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (rdp.isTemp(eleccion)) {
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
