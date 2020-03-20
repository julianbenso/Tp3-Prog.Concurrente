package Tp3;

public class Finalizador {

    private Generador generador;
    private Nucleo1 nucleo1;
    private Nucleo2 nucleo2;
    private Procesador1 procesador1;
    private Procesador2 procesador2;

    public Finalizador(Generador generador ,Nucleo1 nucleo1, Nucleo2 nucleo2, Procesador1 procesador1, Procesador2 procesador2){
        this.generador = generador;
        this.nucleo1 = nucleo1;
        this.nucleo2 = nucleo2;
        this.procesador1 = procesador1;
        this.procesador2 = procesador2;
    }

    public void desactivarObjetos(){
        generador.desactivar();
        nucleo1.desactivar();
        nucleo2.desactivar();
        procesador2.desactivar();
        procesador1.desactivar();
    }
}
