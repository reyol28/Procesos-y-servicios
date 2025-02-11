import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UdpClient2 {
    public static void main(String[] args) throws IOException{
        DatagramSocket socket = new DatagramSocket(6001);
        byte[] buffer = new byte[1024];

        System.out.println("Cliente 2 esperando número...");

        // Recibir número del servidor
        DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
        socket.receive(packet);
        String numeroStr = new String(packet.getData(), 0, packet.getLength());
        int numero = Integer.parseInt(numeroStr);
        System.out.println("Número recibido del servidor: " + numero);

        // Calcular factorial
        long factorial = calcularFactorial(numero);
        String resultado = Long.toString(factorial);
        System.out.println("Factorial calculado: " + resultado);

        // Enviar resultado al servidor
        InetAddress serverAddress = packet.getAddress();
        int serverPort = 5000;
        DatagramPacket packetToServer = new DatagramPacket(
                resultado.getBytes(), resultado.length(), serverAddress, serverPort);
        socket.send(packetToServer);
        System.out.println("Resultado enviado al servidor");

        socket.close();
    }
     
    

    public static long calcularFactorial(int n) {
        if (n == 0 || n == 1) return 1;
        long factorial = 1;
        for (int i = 2; i <= n; i++) {
            factorial *= i;
        }
        return factorial;
    }
}
