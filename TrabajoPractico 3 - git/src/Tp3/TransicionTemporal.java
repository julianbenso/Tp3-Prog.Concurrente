package Tp3;

import java.util.Calendar;

public class TransicionTemporal {
	
	private int limInf;
	private long limSup;
	private int ID;
	private long inicioSensibilizado;
	
	public TransicionTemporal(int id) {
		this.ID = id;
		limInf = 100;
		limSup = 999999999;
	}
	
	public int getLimInf() {
		return limInf;
	}
	
	public long getLimSup() {
		return limSup;
	}
	
	public boolean isHabilitada() { //retorna truen si esta dentro del intervalo de tiempo
		if(inicioSensibilizado == 0) return false;
		long diferencia = calcularDif();
		if(diferencia < limSup && diferencia > limInf) return true;
		return false;
	}
	
	private long calcularDif() { //diferencia entre el tiempo actual y en el que se sensibilizo la transicion
		return Calendar.getInstance().getTimeInMillis() - inicioSensibilizado;
	}
	
	public int getID() {
		return ID;
	}

	public void setInicioSensibilizado(){
		inicioSensibilizado = Calendar.getInstance().getTimeInMillis();
	}
}
