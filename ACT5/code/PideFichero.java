package actividades.ud4.actividad5;

import java.io.Serializable;

public class PideFichero implements Serializable{
	
	private static final long serialVersionUID = 1L;
	String nombreFichero;

	public PideFichero(String nombreFichero) {
		this.nombreFichero = nombreFichero;
	}
	
	public String getNombreFichero() {
		return nombreFichero;
	}
	
}
