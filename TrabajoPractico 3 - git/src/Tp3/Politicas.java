package Tp3;

import java.util.ArrayList;
import java.util.HashMap;

public class Politicas {

    private HashMap<Integer, Integer> prioridades;

    public Politicas(Rdp red){
        prioridades = new HashMap<>();
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
    }

}
