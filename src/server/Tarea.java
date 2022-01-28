package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class Tarea{
	

	private Socket socket;
	private String descripcion;
	private String estado;
	ArrayList<String> descripcionArray = new ArrayList<String>();
	ArrayList<String> estadoArray = new ArrayList<String>();
	
	//Traemos los datos del socket de la clase Server
	public Tarea(Socket socket) {
		this.socket = socket;
	}
	
	
	public void tareas() {
		
		try {
			
			
			DataOutputStream mensajeClient = new DataOutputStream(socket.getOutputStream());
			//enviamos el mensaje de cuantas tareas tenemos que realizar
			mensajeClient.writeUTF("�Cu�ntas tareas has de realizar?");
			
			
			
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			//leemos el numero de tareas que queremos hacer
			String tarea = entrada.readUTF();
			int tareas = Integer.parseInt(tarea);
			
			
			System.out.println("Se han recibido " + tareas + " tareas");
			
			//bucle para recorrer todas las tareas
			for (int i=0;i<tareas;i++) {
				
				mensajeClient.writeUTF("Introducci�n de la tarea: " + (i+1));
				mensajeClient.writeUTF("Introduce la descripci�n: ");
				//leemos la descripci�n y la almacenamos
				descripcion = entrada.readUTF();
				System.out.println("Descripci�n recibida: " + descripcion);
				mensajeClient.writeUTF("Introduce el estado: ");
				//leemos el estado y lo almacenamos
				estado = entrada.readUTF();
				System.out.println("Estado recibido: " + estado);
				//a�adimos la descripci�n y el estado a un arraylist para luego m�s tarde mostrarlos
				descripcionArray.add(descripcion);
				estadoArray.add(estado);
				
			}
			
			System.out.println("Listado de tareas");
			mensajeClient.writeUTF("Listado de tareas");
			
			//bucle for para enviar todas las tareas y estados almacenados en el arraylist anterior
			for (int f=0;f<tareas;f++) {
				mensajeClient.writeUTF("Tarea: " + descripcionArray.get(f) + ", con estado " + estadoArray.get(f));
			
				
			}
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
