package actividades.ud4.actividad5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.net.Socket;

public class HiloServidor extends Thread{
	
	private Socket socket;
	ObjectOutputStream outObjeto; //Stream de salida
	ObjectInputStream inObjeto; //steam de entrada
	EstructuraFicheros NF;
	
	public HiloServidor(Socket socket, EstructuraFicheros nF) throws IOException {
		this.socket = socket;
		NF = nF;
		inObjeto = new ObjectInputStream(socket.getInputStream());
		outObjeto = new ObjectOutputStream(socket.getOutputStream());
	}
	
	@Override
	public void run() {
		try {
			//Enviar al cliente el objeto EstructuraFicheros
			outObjeto.writeObject(NF);
			while(true) {
				
				System.out.println("EEEEEEnviado");
				Object peticion;
				
				try {
					peticion = inObjeto.readObject();
					// Comprobar que quiere el cliente
					if(peticion instanceof PideFichero) {
						//El Cliente pide un fichero al servidor
						PideFichero fichero = (PideFichero) peticion;
						enviaFichero(fichero);
					}
					if(peticion instanceof EnviaFichero) {
						//El Cliente encia un fichero al servidor
						EnviaFichero fic = (EnviaFichero) peticion;
						File d = new File(fic.getDirectorio());
						File f1 = new File(d, fic.getNombre());
						//Creacion del fichero en el directorio, con los bytes enviados en el objeto.
						FileOutputStream fos = new FileOutputStream(f1);
						fos.write(fic.getContenidoFichero());
						fos.close();
						// Creacion de la nueva estructura de directorios
						
						EstructuraFicheros n = new EstructuraFicheros(fic.getDirectorio());
						outObjeto.writeObject(n); //Se envia al cliente
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					inObjeto.close();
					outObjeto.close();
					socket.close();
					System.out.println("Cerrando cliente");
				}
			}
		} catch (IOException e) {
			
		}
	}
	
	private void enviaFichero(PideFichero fich) {
		try {
			File fichero = new File(fich.getNombreFichero());
			FileInputStream filein = null;
			filein = new FileInputStream(fichero);
			long bytes = fichero.length();
			byte[] buff = new byte[(int) bytes];
			int i,j = 0;
			try {
				while((i = filein.read()) != 1) {
					buff[j] = (byte) i;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				filein.close();
			}catch (IOException e) {
				e.printStackTrace();
			}
			Object ff = new ObtieneFichero(buff);
			try {
				outObjeto.writeObject(ff);
			}catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
