import java.io.*;
import java.net.*;

public class TcpServer {
  public static void main(String[] arg) throws IOException {
	
	int numeroPuerto = 6666;// Puerto
	ServerSocket servidor = new ServerSocket(numeroPuerto);
	Socket cliente1 = null;
    
	System.out.println("Esperando al cliente 1.....");
	cliente1 = servidor.accept();
    
	// input client1
	InputStream entrada1 = null;
	entrada1 = cliente1.getInputStream();
	DataInputStream flujoEntrada1 = new DataInputStream(entrada1);

	//Recibiendo datos del cliente ...
    String num=flujoEntrada1.readUTF();
	System.out.println("Recibiendo del CLIENTE 1: \n\t" + num);

    

    //abro el client 2
    Socket cliente2 = null;
    System.out.println("Esperando al cliente 2.....");
    cliente2=servidor.accept();

    //creo el output del client2
	OutputStream salida2 = null;
	salida2 = cliente2.getOutputStream();
	DataOutputStream flujoSalida2 = new DataOutputStream(salida2);

	//Enviando datos al cliente
	flujoSalida2.writeUTF(num);

    
    

    //input client2
    InputStream entrada2 = null;
	entrada2 = cliente2.getInputStream();
	DataInputStream flujoEntrada2 = new DataInputStream(entrada2);

    String resultado=flujoEntrada2.readUTF();
	System.out.println("Recibiendo del CLIENTE 2: \n\t" + resultado);
   


    //output client1
    OutputStream salida1 = null;
	salida1 = cliente1.getOutputStream();
	DataOutputStream flujoSalida1 = new DataOutputStream(salida1);

	//Enviando datos al cliente
	flujoSalida1.writeUTF(resultado);
    
    
    //finalmente cierro el server junto a los clientes y los input y output
    flujoEntrada1.close();
    entrada1.close();
    salida1.close();
    flujoSalida1.close();
    salida2.close();
    flujoSalida2.close();
    cliente1.close();
    cliente2.close();	
	servidor.close();
  }// main
}// fin
