package Tp3;

import java.util.Arrays;
import java.util.stream.IntStream;

public class OperadorDeMatrices {

	 public OperadorDeMatrices() {
	    }
	 
public int[][] traspuesta(int[][] mat) {
	        if (mat == null) throw new NullPointerException("matriz nula!");
	        int[][] traspuesta = new int[mat[0].length][mat.length];
	        for (int i = 0; i < mat.length; i++) {
	            for (int j = 0; j < mat[0].length; j++) {
	                traspuesta[j][i] = mat[i][j];
	            }
	        }
	        return traspuesta;
	    }

public int[][] traspuesta(int[] vect) { //dim(vect) = n -> dim(r) = nx1
    if (vect == null) throw new NullPointerException("vector nulo!");
    int[][] traspuesta = new int[vect.length][1];
    for (int i = 0; i < vect.length; i++) {
        traspuesta[i][0] = vect[i];
    }
    return traspuesta;
}

public int[][] and(int[][] a, int[][] b) {
    if (a == null || b == null) throw new NullPointerException("matriz nula!");
    else if (a.length != b.length || a[0].length != b[0].length)
        throw new IndexOutOfBoundsException("diferente formato operacion imposible!");

    int[][] resultado = new int[a.length][a.length];
    for (int i = 0; i < a.length; i++) {
        resultado[i] = and(a[i], b[i]);
    }
    return resultado;
}

public int[] sumar(int[] a, int[] b){
    if(a == null || b == null)throw new NullPointerException("vector nulo!");
    if (a.length != b.length) throw new IndexOutOfBoundsException("diferente formato operacion imposible!");
    int[] resultado = new int[a.length];
    for(int i=0; i < a.length; i++){
        resultado[i] = a[i] + b[i];
    }
    return resultado;
}

public int[] restar(int[]a, int[]b){
	     if(a == null || b == null) throw new NullPointerException("vector nulo");
	     if(a.length != b.length) throw new IndexOutOfBoundsException("formato incompatible para la operacion");
	     int r[] = new int[a.length];
	     for(int i = 0; i < r.length; i++){
	         r[i] = a[i] - b[i];
         }
	     return r;
}

public int[] multiplyEscalar(int[] a, int e){
    if(a == null)throw new NullPointerException("vector nulo!");
    int[] resultado = new int[a.length];
    for(int i=0; i < a.length; i++){
        resultado[i] = a[i] * e;
    }
    return resultado;
}

public int[] and(int[] a, int[] b) {
    if (a == null || b == null) throw new NullPointerException("vector nulo!");
    else if (a.length != b.length) throw new IndexOutOfBoundsException("diferente formato operacion imposible!");

    int[] resultado = new int[a.length];
    for (int i = 0; i < a.length; i++) {
        resultado[i] = (int) a[i] & b[i];
    }
    return resultado;
}

public double[] multiply(int[][] matrix, int[] vector) {
    return Arrays.stream(matrix)
                 .mapToDouble(row -> 
                    IntStream.range(0, row.length)
                             .mapToDouble(col -> row[col] * vector[col])
                             .sum()
                 ).toArray();
}


public double[] complementar(double[] a){
    if(a == null)throw new NullPointerException("vector nulo!");
    double[] complemento = new double [a.length];
    for(int i=0; i < a.length; i++){
        if(a[i]==0){
            complemento[i]=1;
        }else complemento[i]=0;
    }
    return complemento;
}

public int[] arrayDoubleAInt(double [] d){
	     int i[] = new int[d.length];
	     for(int j = 0; j < d.length; j++){
	         i[j] = (int) d[j];
         }
	     return i;
}


public int[] not(double[] d) {
    if(d == null)throw new NullPointerException("vector nulo!");
    int[] aux = new int[d.length];
    for(int i = 0; i < d.length; i++) {
    	if(d[i] != 0) aux[i] = 0;
    	else aux[i] = 1;
    }
    return aux;
}

}
