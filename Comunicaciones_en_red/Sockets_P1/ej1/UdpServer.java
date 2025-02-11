import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpServer {

	public static void main(String[] args) throws IOException {

		DatagramSocket socket = new DatagramSocket(5000);
        byte[] buffer = new byte[1024];

        System.out.println("Servidor esperando datos...");

        // Recibe el número de Cliente 1
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String numeroStr = new String(packet.getData(), 0, packet.getLength());
        int numero = Integer.parseInt(numeroStr);

        // Dirección y puerto de Cliente 1
        InetAddress addressCliente1 = packet.getAddress();
        int portCliente1 = packet.getPort();

        System.out.println("Número recibido de Cliente 1: " + numero);

        // Enviar número a Cliente 2
        InetAddress addressCliente2 = InetAddress.getByName("localhost");
        int portCliente2 = 6001;
        DatagramPacket packetToClient2 = new DatagramPacket(numeroStr.getBytes(), numeroStr.length(), addressCliente2, portCliente2);
        socket.send(packetToClient2);
        System.out.println("Número enviado a Cliente 2");

        // Recibir resultado de Cliente 2
        packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String resultado = new String(packet.getData(), 0, packet.getLength());
        System.out.println("Factorial recibido de Cliente 2: " + resultado);

        // Enviar resultado a Cliente 1
        DatagramPacket packetToClient1 = new DatagramPacket(resultado.getBytes(), resultado.length(), addressCliente1, portCliente1);
        socket.send(packetToClient1);
        System.out.println("Resultado enviado a Cliente 1");

        socket.close();


    }
}
		