import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
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

    public int elegirTransicion(LinkedList<Integer> transiciones){
        int eleccion = transiciones.getFirst();
        System.out.println("eleccion temporal: " + eleccion);
        for(Integer i : transiciones){
            System.out.println("comparamos con " + i);
            if(prioridades.get(i) < prioridades.get(eleccion)){
                eleccion = i;
                System.out.println("nueva eleccion " + i);

            }
        }
        if(eleccion == 1 || eleccion == 8){ //Decidimos a que buffer ira de forma aleatoria
            return equivalerBuffers(rdp.getMarcaActual());
        }
       /* else if(eleccion == 11 || eleccion==12 ){
            
            return DispararInhibidas(rdp.getMarcaActual());
         }*/
        else return eleccion;
    }

    private int equivalerBuffers(int marcaActual[]){
        if(marcaActual[2] > marcaActual[9]) return 8;
        else if(marcaActual[2] < marcaActual[9]) return 1;
        else return ((Math.random() < 0.5)? 8 : 1); // si estan iguales se eligira al azar
    }

    public void despertador(LinkedList<Condition> condiciones, int[] contadorCondiciones) {
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

    private int  DispararInhibidas(int marcaActual[])
    {
        if((marcaActual[10]== 0 && marcaActual[9]== 0) && marcaActual[11]==1 )
        {
            return 11;
        }
        else if(marcaActual[10]==1 || marcaActual[9]==1 && marcaActual[11]==1)
        {
            return 12    ;
        }
        return 0;
    }

}