package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	private final String HOST = "localhost";
	private final int PUERTO = 9876;
	private Socket socket;
	Scanner sc = new Scanner(System.in);
	
	public Client() throws IOException {
		socket = new Socket("localhost", 9876);
		
	}
	
	//Funci�n para iniciar el cliente
	public void iniciarClient() throws IOException {
		//Iniciamos la entrada de datos
		DataInputStream entradaServer = new DataInputStream(socket.getInputStream());
		System.out.println("Recibiendo mensaje del servidor");
		//leemos la pregunta del nombre
		System.out.println(entradaServer.readUTF());
		
		DataOutputStream salidaServer = new DataOutputStream(socket.getOutputStream());
		
		//almacenamos y enviamos el nombre
		String nombre = sc.nextLine();
		salidaServer.writeUTF(nombre);
		System.out.println("Enviando mensaje a servidor: {" + nombre + "}");
		
		System.out.println("Recibiendo mensaje del servidor");
		//recibimos mensaje cuantas tareas queremos hacer
		System.out.println(entradaServer.readUTF());
		
		//enviamos el n�mero de tareas que queremos hacer
		String tareas = sc.nextLine();
		int tarea = Integer.parseInt(tareas);
		salidaServer.writeUTF(tareas);
		System.out.println("Enviando mensaje a servidor: {" + tareas + "}");
		System.out.println("Leyendo mensaje del servidor");
		
		for(int i=0;i<tarea;i++) {
			
			//leemos introducci�n de la tarea i
			System.out.println(entradaServer.readUTF());
			//leemos introduce la descripci�n
			System.out.println("Leyendo mensaje del servidor");
			System.out.println(entradaServer.readUTF());
			//almacenamos la descripci�n y la enviamos al servidor
			String descripcion = sc.nextLine();
			salidaServer.writeUTF(descripcion);
			System.out.println("Enviando mensaje a servidor: {" + descripcion + "}");
			System.out.println("Leyendo mensaje del servidor");
			
			//leemos introduce el estado
			System.out.println(entradaServer.readUTF());
			//almacenamos el estado y lo enviamos al servidor
			String estado = sc.nextLine();
			salidaServer.writeUTF(estado);
			System.out.println("Enviando mensaje a servidor: {" + estado + "}");
			System.out.println("Leyendo mensaje del servidor");
		}
		
		//leemos la lista de tareas
		System.out.println(entradaServer.readUTF());
		//recibimos los datos del servidor sobre todas las tareas
		for (int f=0;f<tarea;f++) {
			System.out.println("Leyendo mensaje del servidor");
			System.out.println(entradaServer.readUTF());
		
		}
		
		System.out.println("Cerrando cliente");
		
		
		salidaServer.close();
		entradaServer.close();
		
	}
	
	//Funci�n para finalizar el cliente
	public void finalizarClient() throws IOException {
        socket.close();
        
    }

}
