package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private final int PUERTO = 9876;
	private ServerSocket serverSocket;	
	private Socket socket;
	
	//Definimos el constructor
	public Server() throws IOException {
		
		//Definimos la conexi�n
		serverSocket = new ServerSocket(PUERTO);
		//Iniciamos el cliente
		socket = new Socket();
		
	}
	
	//Funci�n para iniciar la conexi�n
	public void iniciarServer() throws IOException{
		
		System.out.println("Esperando 1er cliente");
			
			
		//Guardamos la petici�n que llegue al servidor en socket
		socket = serverSocket.accept();
		//El servidor se que da a la espera de recibir peticiones
			
		DataOutputStream mensajeClient = new DataOutputStream(socket.getOutputStream());
			
		//Enviamos mensaje al cliente para preguntar su nombre
		mensajeClient.writeUTF("Bienvenido, �c�mo te llamas?");
			
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
			
		//guardamos su nombre
		String nombre = entrada.readUTF();
			
		System.out.println("Encantado de verte, " + nombre);
			
		//nos vamos a la clase tarea
		Tarea tarea = new Tarea(socket);
		tarea.tareas();
		
		//Cerramos la conexi�n con el cliente
        socket.close();
			
		
	}
	
	//Funci�n para finalizar la conexi�n
	public void finalizarServer() throws IOException {
        serverSocket.close();
        
    }

}
