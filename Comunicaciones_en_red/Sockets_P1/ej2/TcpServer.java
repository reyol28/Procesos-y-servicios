package ej2;
import java.io.*;
import java.net.*;
import java.util.Arrays;

public class TcpServer {
  public static void main(String[] arg) throws IOException {
	//creo el serversocket
	int numeroPuerto = 6666;
	ServerSocket servidor = new ServerSocket(numeroPuerto);
    while(true)
    {
        Socket cliente = servidor.accept();
        System.out.println("Cliente conectado desde "+cliente.getInetAddress());

        DataInputStream entrada = new DataInputStream(cliente.getInputStream());
        DataOutputStream salida = new DataOutputStream(cliente.getOutputStream());

        int longitud = entrada.readInt();
        int[] numeros = new int[longitud];
        int suma=0, numMayor=0, numMenor=11;//pongo el numero menor 0 para que se sustituya por el siguiente menor sabiendo que todos los números que reciba son menores que 11 (lo mismo para numMayor)
        
        //recibo los números del array
        for (int i = 0; i < longitud; i++) {
            numeros[i] = entrada.readInt();
            suma+=numeros[i];
            if(numeros[i]>numMayor)
            {
                numMayor=numeros[i];
            }
            else if(numeros[i]<numMenor)
            {
                numMenor=numeros[i];
            }
        }
        
        System.out.println("Array recibido: " + Arrays.toString(numeros));

        salida.writeInt(suma);
        salida.writeInt(numMayor);
        salida.writeInt(numMenor);
        
        salida.flush();
    }
	
    
	
  }// main
}// fin