package ej2;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Random;

public class UdpClient {
    public static void main(String[] args) throws Exception{

        InetAddress serverAddress = InetAddress.getByName("localhost");
        int puertoServidor = 5000;//puertoServidor del servidor

        DatagramSocket socket = new DatagramSocket(5001);
        Random random = new Random();
        
        

        
        for (int i = 0; i < 10; i++) 
        {

            int numero = random.nextInt(10) + 1; 
            //paso de int a String 
            String mensaje = String.valueOf(numero);
            //paso de String a byte
            byte[] buffer=new byte[1024];
            buffer = mensaje.getBytes();
            //envio al server
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, puertoServidor);
            
            socket.send(packet);
            
            System.out.println("Enviado: " + mensaje);
            
        }

        //recibo datos del server
        //suma
        byte[] bufferSum = new byte[1024];
        DatagramPacket packetSum = new DatagramPacket(bufferSum, bufferSum.length);
        socket.receive(packetSum);
        String strSuma = new String(packetSum.getData(), 0, packetSum.getLength());
        System.out.println("Suma recibida: " + strSuma);

        //mayor
        byte[] bufferMayor = new byte[1024];
        DatagramPacket packetMayor = new DatagramPacket(bufferMayor, bufferMayor.length);
        socket.receive(packetMayor);
        String strMayor = new String(packetMayor.getData(), 0, packetMayor.getLength());
        System.out.println("Número mayor recibido: " + strMayor);

        //resta
        byte[] bufferMenor = new byte[1024];
        DatagramPacket packetMenor = new DatagramPacket(bufferMenor, bufferMenor.length);
        socket.receive(packetMenor);
        String strMenor = new String(packetMenor.getData(), 0, packetMenor.getLength());
        System.out.println("Número menor recibido: " + strMenor);

        socket.close();
    }
}
