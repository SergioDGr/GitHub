package actividades.ud4.actividad5;

import java.io.Serializable;

public class EnviaFichero implements Serializable{
	
	byte[] contenidoFichero;
	String nombre;
	String directorio;
	
	public EnviaFichero(byte[] contenidoFichero, String nombre, String directorio) {
		this.contenidoFichero = contenidoFichero;
		this.nombre = nombre;
		this.directorio = directorio;
	}
	
	public byte[] getContenidoFichero() {
		return contenidoFichero;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public String getDirectorio() {
		return directorio;
	}
}
