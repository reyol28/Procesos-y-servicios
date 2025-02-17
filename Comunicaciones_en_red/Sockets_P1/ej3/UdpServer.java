package ej3;
import java.io.*;
import java.net.*;

public class UdpServer {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        int puerto = 6000; 
        //se crea el soclet
        DatagramSocket socket = new DatagramSocket(puerto);
        System.out.println("Esperando al cliente...");

        //espera a recibir el datagram packet
        byte[] buffer = new byte[1024];
        DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
        socket.receive(paqueteEntrada);
        
        //se deserializa el objeto recibido
        ByteArrayInputStream bais = new ByteArrayInputStream(paqueteEntrada.getData());
        ObjectInputStream inObjeto = new ObjectInputStream(bais);
        Factura dato = (Factura) inObjeto.readObject();
        System.out.println("Servidor recibe objeto");
        
        //calculos
        double importeTotal = dato.calcularImporteTotal(dato.calcularIVA(dato.getTipoIVA()));
        dato.setImporteTotal(importeTotal);
        System.out.println("Calculando...");

        //serializo el objeto de nuevo para mandarlo
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream outObjeto = new ObjectOutputStream(baos);
        outObjeto.writeObject(dato);
        byte[] datosEnviados = baos.toByteArray();
        
        InetAddress direccionCliente = paqueteEntrada.getAddress();
        int puertoCliente = paqueteEntrada.getPort();
        DatagramPacket paqueteSalida = new DatagramPacket(datosEnviados, datosEnviados.length, direccionCliente, puertoCliente);
        socket.send(paqueteSalida);
        System.out.println("Servidor envía objeto de vuelta");

        //cierro socket y outputs e inputs
        inObjeto.close();
        outObjeto.close();
        socket.close();
    }
}