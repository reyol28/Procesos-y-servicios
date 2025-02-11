import java.io.*;
import java.net.*;
import java.util.Scanner;

public class TcpClient1  {
  public static void main(String[] args) throws Exception {
	String Host = "localhost";//si hay que conectarse a otro pc, se cambia a ,a ip de ese ordenador
	
	int Puerto = 6666;//puerto remoto	
	
	System.out.println("PROGRAMA CLIENTE 1 INICIADO....");
	Socket cliente = new Socket(Host, Puerto);
	Scanner sc=new Scanner(System.in);
	System.out.println("Introduce un número para calcular el factorial:");
	String numEnviar=sc.next();

	// Creación flujo de salida hacia el servidor 
	DataOutputStream flujoSalida = new DataOutputStream(cliente.getOutputStream());
	flujoSalida.writeUTF(numEnviar);




	//creacion flujo entrada del servidor al cliente
	DataInputStream flujoEntrada = new  DataInputStream(cliente.getInputStream()); 

	
	String resultado = flujoEntrada.readUTF(); // Leer la respuesta del servidor
	System.out.println("Resultado del factorial de " + numEnviar + ": " + resultado);
		


	sc.close();
	flujoSalida.close();	
	flujoEntrada.close();

	cliente.close();	
  }
} 