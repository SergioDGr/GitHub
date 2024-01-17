package actividades.ud4.actividad5;

import java.io.Serializable;

public class ObtieneFichero implements Serializable{
	
	byte[] contieneFichero;

	public ObtieneFichero(byte[] contieneFichero) {
		this.contieneFichero = contieneFichero;
	}
	
	public byte[] getContenidoFichero() {
		return contieneFichero;
	}
	
}
