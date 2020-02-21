package Tp3;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.IntStream;

public class Rdp {
	private LectorMatrices lectorMatrices;
	private int[] marcaActual;
	private int[] marcaInicial;
    private int[][] Imenos;
    private int[][] Imas;
    private int[][] H; /*= new int[][]{
		
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
		{0,0,0,1,0,0,0,0,0,0,0,0,0,0,0},
	
		
		 };*/
		 private double[] B;//es la transpuesta de H x Q
		 private int[] Q;// 𝑄 un vector binario de dimensión 𝑛 × 1.qi = cero(𝑀(𝑝𝑖)))
		 private int[] E;//E es el vector  de sensibilizados
		 private int[] Eaux;
		 private OperadorDeMatrices operacion;
		 
		 private HashMap<Integer, TransicionTemporal> timedT;
		 
		 private int[] Z; //vector de temporizadas
		 
	public Rdp()
	{
		lectorMatrices = new LectorMatrices();
		
		this.operacion = new OperadorDeMatrices();
		try {
			this.H =lectorMatrices.leerMatrizI("src\\matrices\\H.txt");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
			
		System.out.println("H");
        for(int i = 0; i<16; i++)
		{
		    for(int j = 0; j<15; j++)
		    {
		        System.out.print(H[i][j]);
		    }
		    System.out.println();
		}
				
        this.H = operacion.traspuesta(H); //ya la guarda como traspuesta para agilizar los calculos
        
        
		/*Imas = new int[][]{
			
			{1,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,1,0,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
			{0,0,1,0,0,0,0,0,0,0,0,0,0,1,0},
			{0,0,0,0,1,0,1,0,0,0,0,0,0,0,1},
			{0,0,0,0,0,1,0,0,0,0,0,0,0,1,0},
			{0,0,0,1,0,0,0,0,0,0,0,0,0,1,0},
			{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
			{1,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
			{0,0,0,0,0,0,0,0,0,1,1,1,0,0,0},
			{1,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,1,0,0,0,0,0,0,0},
			{0,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
			 };*/
        try {
			Imas = lectorMatrices.leerMatrizI("src\\matrices\\Imas.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("Imas");
        for(int i = 0; i<16; i++)
		{
		    for(int j = 0; j<15; j++)
		    {
		        System.out.print(Imas[i][j]);
		    }
		    System.out.println();
		}

		/*Imenos = new int[][]{
					
					{0,1,0,0,0,0,0,0,0,0,0,0,0,0,0},
					{1,0,0,0,0,0,0,0,0,0,0,0,0,1,0},
					{0,0,1,0,0,0,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					{0,0,0,1,1,0,0,0,0,0,0,0,0,0,1},
					{0,0,0,0,1,1,1,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,1,0,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,1,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,1,0,0,0},
					{0,0,0,0,0,0,0,1,0,0,1,1,0,0,0},
					{0,0,0,0,0,0,0,0,1,1,1,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,1,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,1,0,0,0,0,0,0},
					{0,0,0,0,0,0,0,0,0,0,0,0,0,0,1},
					
					 };
		*/
        try {
			Imenos = lectorMatrices.leerMatrizI("src\\matrices\\Imenos.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Imenos");
        for(int i = 0; i<16; i++)
		{
		    for(int j = 0; j<15; j++) 
		    {
		        System.out.print(Imenos[i][j]);
		    }
		    System.out.println();
		}
        
			try {
				marcaInicial = lectorMatrices.leerVector("src\\matrices\\MarcaInicial.txt");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("\nMarca inicial");
			for(int i = 0; i < 16; i++) {
				System.out.print(marcaInicial[i]);
			}
			
			marcaActual=marcaInicial;
			crearVarEcuExtendida();
			
	
	
	
	}
	
	//CAMBIAR NOMBRE DE FUNCION, MAS FEO NO PUEDE SER 
	
	  private void crearVarEcuExtendida() { //crea  Q, Z, E, B y el Ex, todos los vectores para ver si esta sensibilizada
	       newQ();
	       newB();
	       newE();
		 
	   
	    }

	  private void newQ()
	  {
		  this.Q = new int[16];
		  //Es un vector binario de M. Donde halla 7 tocken de un plaza sera 1 en Q. y 0 si hay 0 tocken en M.
		
		  //  Q=operacion.and(marcaActual, marcaActual); Asi lo hizo pato con la funcion  de operadordematrices pero para no usarla cree el for de abajo fijense porque ya se me quemo la cabeza
		  for(int i=0;i<marcaActual.length;i++)
		  {
			  int aux;
			  if(marcaActual[i]!=0)
			  {
				  aux = 0;
			  }
			  else
			  {
				  aux = 1;
			  }
			  this.Q[i]=aux;//actualizo Q vector binario
		  }
	 
	  }
	  private void newB()
	  {
		  
		 // this.Q = operacion.and(marcaActual,marcaActual); // actualizo Q.
		  for(int i=0;i<marcaActual.length;i++)
		  {
			  int aux;
			  if(marcaActual[i]!=0)
			  {
				  aux = 0;
			  }
			  else
			  {
				  aux = 1;
			  }
			  this.Q[i]=aux;//actualizo Q vector binario
		  }
		
		  this.B = operacion.multiply(H,Q);  //B = not(H' * Q)
	    }
	 
	  
	 
	  private void newE()
	  {
		  this.E = new int[Imas[0].length];// haciendo Imas.length saca la cant de transiciones.
		  this.Eaux = new int[E.length];
	        for (int i = 0; i < Eaux.length ; i++) {
	            Eaux[i] = 0;//Inicializo en 0.
	        }
	        actualizarE();
	  }
	
	
	private void actualizarE()
	{
		for (int i = 0; i < E.length ; i++) {
            Eaux[i] = E[i];
           
            if(checkDisparo(i)==true) //TIENE QUE USARSE ALGO DISTINTO QUE DISPARO PARA CHQUEAR
            {
            	this.E[i]=1;
            }
            else {
            	this.E[i]=0;
            }
            
		}
	}
	
	private boolean checkDisparo(int i) { 
		int mPrueba[] = operacion.sumar(marcaActual, operacion.multiplyEscalar((int[])operacion.multiply(Imenos, crearSigma(i)), -1));
		return false;
	}
	
	private int[] crearSigma(int t) { //Se crea el vector disparo a partir de la transicion ingresada
		int sigma[] = new int[Imas[0].length];
		for (int i = 0; i < sigma.length; i++) {
			sigma[i] = 0;
		}
		sigma[t] = 1;
		return sigma;
	}

	public void agregarTemporal(int ID, TransicionTemporal t){ //agrega transiciones temporales al conjunto
		if(timedT.containsKey(ID)) {
			System.out.println("Ya existe la transicion " + ID + " en el conjunto");
		}
		else {
			timedT.put(ID, t);
		}
	}
	
	private void actualizarTemporales() {
		//HAY QUE TERMINAR LO DE LA EXTENDIDA ANTES DE HACER ESTE
	}
	
	
		public boolean disparo(int i)//Esta funcion sirve para ver si esta sensibilizada
		{
			double Iv[] = operacion.multiply(Imenos, generarVectorDisparo(i));//asdaca al principio use la del tp2 que hace lo mismo pero mete a un vector v como parametro y despues se me hace quilombo con la funcion actualizar E
			int mPrueba[] = new int[marcaInicial.length];
			
			for(int i1 = 0; i1 < marcaInicial.length; i1++)
			{
				mPrueba[i1] = (int) (marcaActual[i1] + Iv[i1]);
				 if(mPrueba[i1] == 0) return false;
			}
		        return true;//si la la plaza tiene el token pone true, puede disparar la transicion.
		
		}
		
		 private int[] generarVectorDisparo(int transicion){  //A partir del nro de transicion (ID) se genera un vector de disparo con todos ceros menos la transicion a disparar
		        int[] vectorDisparo = new int[Imas[0].length];
		        for(int i=0; i<Imas[0].length; i++){
		            vectorDisparo[i]=0;         //reseteo el vector con todos ceros
		        }
		        vectorDisparo[transicion]=1;        //tiene 1 solo en la transicion que se desea disparar
		        return vectorDisparo;
		    }
		 
		 
		 
		 //HASTA ACA TENGO  E,Q,B QUE SON NECESARIAS PARA REALIZAR LA FUNCION DISPARO PERO YA ME PERDI CON LAS TEMPORIZADAS Y LAS INVARIANTES
	
	public void getM()
	{
		for(int i = 0; i < marcaActual.length; i++)
		{
			System.out.println(marcaActual[i]);
		}
	}


public int getCantTransiciones(){
    return Imas[0].length;
}
}
