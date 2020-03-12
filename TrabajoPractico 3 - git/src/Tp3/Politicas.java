package Tp3;

import java.util.ArrayList;
import java.util.HashMap;

public class Politicas {

    private HashMap<Integer, Integer> prioridades;
    private Rdp rdp;

    public Politicas(Rdp red){
        prioridades = new HashMap<>();
        this.rdp = red;
    }

    public void agregarPrioridad(int transicion, int prioridad){
        prioridades.put(transicion, prioridad);
    }

    public int elegirTransicion(ArrayList<Integer> transiciones){
        int eleccion = transiciones.get(0);
        for(Integer i : transiciones){
            if(prioridades.get(i) < prioridades.get(eleccion)){
                eleccion = i;
            }
        }
        if(eleccion == 1 || eleccion == 8){ //Decidimos a que buffer ira de forma aleatoria
            if(Math.random() < 0.5) return 1;
            else return 8;
        }
        if(eleccion == 2 || eleccion == 5){
            eleccionProcesador1(rdp.getMarcaActual());
        }
    }

    eleccionProcesador1(int[] marcaActual){
        if(marcaActual[])
    }

}
