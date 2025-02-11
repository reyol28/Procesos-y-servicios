package ej2;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;

public class UdpServer {
    public static void main(String[] args) throws IOException 
    {
        //creo el socket
        DatagramSocket socket = new DatagramSocket(5000);
        

        System.out.println("Servidor esperando datos...");

        while(true)
        {
            byte[] buffer = new byte[1024];
            DatagramPacket entrada=new DatagramPacket(buffer,buffer.length);
            socket.receive(entrada);
            String strLongitud=new String(entrada.getData(),0,entrada.getLength());
            
            int longitud=Integer.parseInt(strLongitud);
            int[] numeros = new int[longitud];
            int suma=0, numMayor=0, numMenor=11;
        
            //recibo los números del array
            for (int i = 0; i < longitud; i++) 
            {
                //para que funcione esto tengo que estar reiniciando el buffer porque sino hay errores
                byte[] buffer2 = new byte[1024];
                DatagramPacket entrada2=new DatagramPacket(buffer2,buffer2.length);
                
                socket.receive(entrada2);
                String strNumero=new String(entrada2.getData(),0,entrada2.getLength());
                numeros[i] = Integer.parseInt(strNumero);
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

            System.out.println("Desde server(suma,numMayor y numMenor): "+suma+" "+numMayor+" "+numMenor);//para probar en el server si los datos acaban bien
            //salto dos líneas para diferenciar lo que se recibe de cada cliente
            System.out.println("\n");


            //obtengo el address y el puerto del primer datagram packet que hago (podria hacerlo tambien con el segundo porque el address y el port es el mismo)
            InetAddress addressCliente = entrada.getAddress(); 
            int puertoCliente = entrada.getPort(); 

            //suma
            String strSuma = String.valueOf(suma);
            byte[] bufferSum = strSuma.getBytes();
            DatagramPacket packetSum = new DatagramPacket(bufferSum, bufferSum.length, addressCliente, puertoCliente);
            socket.send(packetSum);

            //mayor
            String strMayor = String.valueOf(numMayor);
            byte[] bufferMayor = strMayor.getBytes();
            DatagramPacket packetMayor = new DatagramPacket(bufferMayor, bufferMayor.length, addressCliente, puertoCliente);
            socket.send(packetMayor);

            //menor
            String strMenor = String.valueOf(numMenor);
            byte[] bufferMenor = strMenor.getBytes();
            DatagramPacket packetMenor = new DatagramPacket(bufferMenor, bufferMenor.length, addressCliente, puertoCliente);
            socket.send(packetMenor);
            
        }
    }
    
}
