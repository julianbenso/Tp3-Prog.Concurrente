import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Archivo {

    private FileWriter archivo = null;
    private PrintWriter escritor = null;

    public Archivo() {

        try
        {
            archivo=new FileWriter("C:\\Users\\augus\\Desktop\\logar.txt"); //Se crea un txt para escribir
            escritor=new PrintWriter(archivo);//Se crea un escritor

        } catch(Exception e) {     //Si existe un problema cae aqui

            System.out.println("Error al escribir: "+ e.getMessage());
        }
    }

    public void escribirArchivo(String datos) throws IOException {
        try {
            escritor.print(datos); //Escribe los datos
            escritor.flush(); //El escritor cada vez que se cierra el txt se escribe sobre el mismo
            //System.out.println(datos);
        }catch(Exception e){
            System.out.println("Error al escribir: "+ e.getMessage());
        }
    }

}