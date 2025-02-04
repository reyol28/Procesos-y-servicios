import java.io.*;
import java.net.*;

public class TcpClient2  {
  public static void main(String[] args) throws Exception {
	String Host = "localhost";//si hay que conectarse a otro pc, se cambia a ,a ip de ese ordenador
	
	int Puerto = 6666;//puerto remoto	
	
	System.out.println("PROGRAMA CLIENTE 2 INICIADO....");
	Socket Cliente = new Socket(Host, Puerto);

    // Creación flujo de entrada desde el servidor
	DataInputStream flujoEntrada = new  DataInputStream(Cliente.getInputStream()); 


    String numero=flujoEntrada.readUTF();
	System.out.println("Recibiendo del SERVIDOR: \n\t" + numero);
    
    
    int num=Integer.parseInt(numero);
    //calculo el factorial
    int numFactorial= factorial(num);

	// Creación flujo de salida hacia el servidor
	DataOutputStream flujoSalida = new DataOutputStream(Cliente.getOutputStream());
	flujoSalida.writeUTF(""+numFactorial);

	

	// CERRAR STREAMS Y SOCKETS	
	flujoEntrada.close();	
	flujoSalida.close();	
	Cliente.close();	
  }
  public static int factorial(int n) {
    if (n < 0) {
        throw new IllegalArgumentException("El número debe ser positivo o cero.");
    }
    int resultado = 1;
    for (int i = 2; i <= n; i++) {
        resultado *= i;
    }
    return resultado;
}
}