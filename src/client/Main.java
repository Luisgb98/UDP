package client;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		
		//Creamos objeto de cliente
		Client cli = new Client();
		System.out.println("Iniciando cliente . . .");
		
		//Iniciamos la conexi�n
		cli.iniciarClient();
		
		//finalizamos la conexi�n
		cli.finalizarClient();

	}

}
