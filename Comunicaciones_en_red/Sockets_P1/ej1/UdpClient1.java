import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import java.util.Scanner;

public class UdpClient1 {
    public static void main(String[] args) throws IOException { 
        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName("localhost");
        int serverPort = 5000;

        // Leer número desde la consola
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese un número para calcular su factorial: ");
        int numero = scanner.nextInt();
        scanner.close();

        // Enviar número al servidor
        String mensaje = Integer.toString(numero);
        DatagramPacket packet = new DatagramPacket(
                mensaje.getBytes(), mensaje.length(), serverAddress, serverPort);
        socket.send(packet);
        System.out.println("Número enviado al servidor");

        // Recibir resultado del servidor
        byte[] buffer = new byte[1024];
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String resultado = new String(packet.getData(), 0, packet.getLength());

        // Mostrar resultado
        System.out.println("Factorial recibido del servidor: " + resultado);

        socket.close();
    }
}
