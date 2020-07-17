

import java.util.Calendar;

public class TransicionTemporal {

	private int limInf;
	private long limSup;
	private int ID;
	private long inicioSensibilizado;

	public TransicionTemporal(int id) {
		this.ID = id;
		limInf = 50;
		limSup = 999999999;
	}

	public TransicionTemporal(int id, int limInf) {
		this.ID = id;
		this.limInf = limInf;
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

	public int estadoVentana(){
		if(inicioSensibilizado == 0) return 0; // no ha sido resensibilisada
		long diferencia = calcularDif();
		if(diferencia <= limSup && diferencia >= limInf) return 1; //esta adentro de la ventana de tiempo
		else if(diferencia < limInf){ //esta antes de la ventana de tiempo
			return 2;
		}
		else return 3; //ya paso la ventana de tiempo
	}

	public long getTiempoSleep(){
		return limInf - calcularDif();
	}
}