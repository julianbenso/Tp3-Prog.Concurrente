import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class Monitor {

    private final ReentrantLock mutex;
    private List<Condition> condiciones;
    private int[] contadorCondiciones;
    private Rdp rdp;
    private Politicas politicas;
    private Finalizador finalizador;

    public Monitor(Rdp redPetri, Politicas pol){
        this.rdp = redPetri;
        mutex = new ReentrantLock();
        creacionDeCondiciones();
//        corregirCondiciones();
        politicas = pol;
    }

    private void creacionDeCondiciones(){ //Creamos tantas condiciones como transiciones tenemos
        contadorCondiciones = new int[rdp.getCantidadTransiciones()];
        condiciones = new LinkedList<Condition>();
        for(int i = 0; i < rdp.getCantidadTransiciones(); i++){
            condiciones.add(mutex.newCondition());
            contadorCondiciones[i] = 0;
        }
        return;
    }

    private void corregirCondiciones() {
        condiciones.set(4,condiciones.get(3));
        condiciones.set(2,condiciones.get(3));
        condiciones.set(13,condiciones.get(11));
        condiciones.set(14,condiciones.get(11));
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

    public void disparo(LinkedList<Integer> transiciones){
        //   try{
        mutex.lock();
        int eleccion;

        if(transiciones.size() == 1) eleccion = transiciones.get(0);
        else{
            eleccion = politicas.elegirTransicion(transiciones); //Si se encuentra conflicto, se decide con las politicas la transicion a disparar
        }

        while (!rdp.testSensibilisado(eleccion)) {
            try {
                contadorCondiciones[eleccion] += 1;
                System.out.println("toca esperar en transicion" + eleccion);
                //        mutex.unlock();
                condiciones.get(eleccion).await();
                mutex.lock();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (rdp.isTemp(eleccion)) {
            disparoTemporizado(eleccion);// METODO PARA DISPARAR TEMPORIZADAS
        } else {
            rdp.disparar(eleccion);
            finalizador.checkEstadoObjetos(eleccion);
        }


        // }
        // finally{
        signalSalida();             //METODO PARA, EN RELACIONA NUESTRAS POLITICAS, HACERLE RELEASE A UN HILO DORMIDO
        mutex.unlock();
        //}
    }

    private void disparoTemporizado(int transicion){
        TransicionTemporal temporal = rdp.getTransicionTemporal(transicion);
        if(temporal == null) throw new NullPointerException("La transicion temporal solicitada no existe");
        switch(temporal.estadoVentana()){
            case 1:
                rdp.disparar(transicion);
                finalizador.checkEstadoObjetos(transicion);
                break;
            case 2:
                mutex.unlock();
                signalSalida();
                try {
                    Thread.sleep(temporal.getTiempoSleep()); //duerme el tiempo necesario para que pueda dispararse dentro de la ventana
                    mutex.lock(); //PRIMER DIFERENCIA, EL NO LIBERA POST SLEEP
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                disparoTemporizado(transicion); //Llamamos recursivamente para comprobar que se cumpla la condicion
                break;
            default: throw new RuntimeException("Disparo no debido en transicion " + transicion +"  con un tiempo de ventana de: " + rdp.getTransicionTemporal(transicion).getTiempoSleep());
        }
        return;
    }

    private void signalSalida(){ //NECESITO ARMAR LAS POLITICAS PARA ESTE METODO
        LinkedList<Integer> conjuntoPosible = new LinkedList<>();
        for(int transicion = 0; transicion < contadorCondiciones.length; transicion++){
            if(contadorCondiciones[transicion] > 0 && rdp.testSensibilisado(transicion)){
                System.out.println("Agregada la transicion " + transicion + " al conjunto posible");
                conjuntoPosible.add(transicion);
            }
        }
        if(!(conjuntoPosible.isEmpty())){
            for(int i : conjuntoPosible) { //Despertamos a todas las que esten sensibilizadas
                condiciones.get(i).signal();
                contadorCondiciones[i] -= 1;
            }
      /*      int eleccion = politicas.elegirTransicion(conjuntoPosible);
        	System.out.println("Se eligio para liberar a la trancision numero " + eleccion);
            contadorCondiciones[eleccion] -= 1;
            condiciones.get(eleccion).signal();*/
        }
        return;
    }

    public void lastSignal() { //lo utiliza el finalizador para despertar todos los hilos y que finalize el programa
        for(Condition c : condiciones) {
            c.signal();
        }
        return;
    }

    public void setFinalizador(Finalizador f) {
        finalizador = f;
    }
}