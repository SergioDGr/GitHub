package actividades.ud4.actividad5;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFileChooser;

public class Servidor {
	
	static Integer PUERTO = 44441;
	static public EstructuraFicheros NF;
	static ServerSocket servidor;
	
	public static void main(String[] args) throws IOException {
		
		String directorio = "";
		JFileChooser f = new JFileChooser();
		f.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		f.setDialogTitle("Selecciona el directorio donde estan los ficheros");
		int returnVal = f.showDialog(f, "Seleccionar");
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File file = f.getSelectedFile();
			directorio = file.getAbsolutePath();
		}
		//Si no se selecciona nada, salir
		if(directorio.equals("")) {
			System.out.println("Debes seleccionar un directorio.");
			System.exit(1);
		}
		
		servidor = new ServerSocket(PUERTO);
		System.out.println("Servidor iniciado en el puerto " + PUERTO);
		while(true) {
			
			Socket cliente = servidor.accept();
			System.out.println("Bienvenido al cliente");
			NF = new EstructuraFicheros(directorio);
			HiloServidor hilo = new HiloServidor(cliente, NF);
			hilo.start();
		}
	}
	
}
