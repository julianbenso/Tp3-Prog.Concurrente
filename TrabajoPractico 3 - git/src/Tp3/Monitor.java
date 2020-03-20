package Tp3;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Monitor {

    private final ReentrantLock mutex;
    private Condition condiciones[];
    private int[] contadorCondiciones;
    private Rdp rdp;
    private Politicas politicas;
    private int tareasEjecutadas;

    public Monitor(Rdp redPetri, Politicas pol){
        this.rdp = redPetri;
        mutex = new ReentrantLock();
        creacionDeCondiciones();
        politicas = pol;
        tareasEjecutadas = 0;
    }

    private void creacionDeCondiciones(){ //Creamos tantas condiciones como transiciones tenemos
        contadorCondiciones = new int[rdp.getCantidadTransiciones()];
        for(int i = 0; i < rdp.getCantidadTransiciones(); i++){
            condiciones[i] = mutex.newCondition();
            contadorCondiciones[i] = 0;
        }
        return;
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
                    contadorCondiciones[eleccion] += 1;
                    condiciones[eleccion].await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            if (rdp.isTemp(eleccion)) {
               disparoTemporizado(eleccion);// METODO PARA DISPARAR TEMPORIZADAS
            } else {
                rdp.disparar(eleccion);
                checkEstadoObjetos();
            }

            signalSalida();             //METODO PARA, EN RELACIONA NUESTRAS POLITICAS, HACERLE RELEASE A UN HILO DORMIDO

        }
        finally{
            mutex.unlock();
        }
    }

    private void disparoTemporizado(int transicion){
        TransicionTemporal temporal = rdp.getTransicionTemporal(transicion);
        if(temporal == null) throw new NullPointerException("La transicion temporal solicitada no existe");
        switch(temporal.estadoVentana()){
            case 1:
                    rdp.disparar(transicion);
                    checkEstadoObjetos();
                    break;
            case 2:
                    mutex.unlock();
                    signalSalida();
                    try {
                        Thread.sleep(temporal.getTiempoSleep()); //duerme el tiempo necesario para que pueda dispararse dentro de la ventana
                        mutex.lock();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    disparoTemporizado(transicion); //Llamamos recursivamente para comprobar que se cumpla la condicion
                    break;
            default: throw new RuntimeException("Disparo no debido");
        }
    }

    private void signalSalida(){ //NECESITO ARMAR LAS POLITICAS PARA ESTE METODO
        ArrayList<Integer> conjuntoPosible = new ArrayList<>();
        for(int transicion = 0; transicion < contadorCondiciones.length; transicion++){
            if(transicion > 0 && rdp.testSensibilisado(transicion)){
                conjuntoPosible.add(transicion);
            }
        }
        if(!(conjuntoPosible.isEmpty())){
            int eleccion = politicas.elegirTransicion(conjuntoPosible);
            contadorCondiciones[eleccion] -= 1;
            condiciones[eleccion].signal();
        }
        return;
    }

    private void checkEstadoObjetos(){
        tareasEjecutadas++;
        if(tareasEjecutadas >= 1000){
            //METODO PARA DESBLOQUEAR TODO

        }
    }
}
