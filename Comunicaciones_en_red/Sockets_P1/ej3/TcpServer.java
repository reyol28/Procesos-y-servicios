package ej3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
    public static void main(String[] arg) throws IOException,
						ClassNotFoundException {
   int numeroPuerto = 6000;// Puerto
   ServerSocket servidor =  new ServerSocket(numeroPuerto);
   System.out.println("Esperando al cliente.....");   
   Socket cliente = servidor.accept();
	
   

   // Se obtiene un stream para leer objetos
   ObjectInputStream inObjeto = new ObjectInputStream(cliente.getInputStream());
   System.out.println("Servidor recibe objeto");
   Factura dato = (Factura) inObjeto.readObject();
   System.out.println("Calculando...");
    //calculo el importe total
    double importeTotal=dato.calcularImporteTotal(dato.calcularIVA(dato.getTipoIVA()));
    dato.setImporteTotal(importeTotal);
                            


   // Se prepara un flujo de salida para objetos 		
   ObjectOutputStream outObjeto = new ObjectOutputStream(cliente.getOutputStream()); 	
   // Se prepara un objeto y se envia 
   
   outObjeto.writeObject(dato); //enviando objeto
   System.out.println("Servidor envía objeto de vuelta");  
		
   // CERRAR STREAMS Y SOCKETS
   outObjeto.close();
   inObjeto.close();
   cliente.close();
   servidor.close();
  }
}
