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
		
		//Definimos la conexión
		serverSocket = new ServerSocket(PUERTO);
		//Iniciamos el cliente
		socket = new Socket();
		
	}
	
	//Función para iniciar la conexión
	public void iniciarServer() throws IOException{
		
		System.out.println("Esperando 1er cliente");
			
			
		//Guardamos la petición que llegue al servidor en socket
		socket = serverSocket.accept();
		//El servidor se que da a la espera de recibir peticiones
			
		DataOutputStream mensajeClient = new DataOutputStream(socket.getOutputStream());
			
		//Enviamos mensaje al cliente para preguntar su nombre
		mensajeClient.writeUTF("Bienvenido, ¿cómo te llamas?");
			
		DataInputStream entrada = new DataInputStream(socket.getInputStream());
			
		//guardamos su nombre
		String nombre = entrada.readUTF();
			
		System.out.println("Encantado de verte, " + nombre);
			
		//nos vamos a la clase tarea
		Tarea tarea = new Tarea(socket);
		tarea.tareas();
		
		//Cerramos la conexión con el cliente
        socket.close();
			
		
	}
	
	//Función para finalizar la conexión
	public void finalizarServer() throws IOException {
        serverSocket.close();
        
    }

}
