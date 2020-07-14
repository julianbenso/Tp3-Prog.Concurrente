package Tp3;

public class Finalizador {
	
	Monitor monitor;
	private int tareasEj1;
	private int tareasEj2;
	private final int TAREAS_TOTALES = 1000;
	private boolean fin;

    public Finalizador(Monitor m){
        monitor = m;
        fin = false;
    }

    public void desactivarObjetos(){
    	Generador.desactivar();
    	Nucleo1.desactivar();
    	Nucleo2.desactivar();
    	Procesador1.desactivar();
    	Procesador2.desactivar();
    	Proc_Down1.desactivar();
    	Proc_Down2.desactivar();
    	monitor.lastSignal();
    	fin = true;
    }
    
    public void checkEstadoObjetos(int transicion){
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
    }
    
    public boolean finPrograma() {
    	return fin;
    }
}
