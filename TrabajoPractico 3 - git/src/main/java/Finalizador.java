import matrices.ArchivoTransiciones;

import java.io.IOException;
import java.util.LinkedList;

public class Finalizador {

    Monitor monitor;
    private int tareasEj1;
    private int tareasEj2;
    private final int TAREAS_TOTALES = 5;
    private boolean fin;
    private boolean fin2;
    private LinkedList<Integer> transiciones;
    private ArchivoTransiciones file;
    private int cuenta;

    public Finalizador(Monitor m){
        monitor = m;
        fin = false;
        transiciones = new LinkedList<Integer>();
        file = new ArchivoTransiciones("out/transitions.txt");
    }

    public void desactivarObjetos(){
        Generador.desactivar();
//        Nucleo1.desactivar();
  //      Nucleo2.desactivar();
       // Procesador1.desactivar();
       // Procesador2.desactivar();
    //    Proc_Down1.desactivar();
      //  Proc_Down2.desactivar();
        monitor.lastSignal();
        fin = true;
    }

    public void checkEstadoObjetos(int transicion){
        transiciones.add(transicion);
        if(transicion == 7) {
            tareasEj1++;
            System.out.println("tareas ejecutadas 1: " + tareasEj1);
        }
        if(transicion == 10) {
            tareasEj2++;
            System.out.println("tareas ejecutadas 2: " + tareasEj2);
        }
        if(tareasEj1 + tareasEj2 >= TAREAS_TOTALES){
            //METODO PARA DESBLOQUEAR TODO
            desactivarObjetos();
            System.out.println("Se han ejecutado " + tareasEj1 + " tareas en el procesador 1 y " + tareasEj2 + " en el procesador 2");
        }
        if(transicion == 2 || transicion == 11){
            lastTransiciones();
        }
    }

    private void lastTransiciones(){
        cuenta++;
        if(cuenta == 2){
            Nucleo1.desactivar();
            Nucleo2.desactivar();
            Procesador1.desactivar();
            Procesador2.desactivar();
            Proc_Down1.desactivar();
            Proc_Down2.desactivar();
            monitor.lastSignal();
            fin2 = true;
        }
    }

    public void printTransiciones(){
        for(int i : transiciones){
            System.out.print("T" + i + ",");
            try {
                file.escribirArchivo("T" + i + ",");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return;
    }

    public boolean finPrograma() {
        return fin;
    }

    public boolean finPrograma2() {
        return fin2;
    }
}