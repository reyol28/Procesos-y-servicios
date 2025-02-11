package ej2;
import java.io.*;
import java.net.*;
import java.util.Random;
public class TcpClient{
    public static void main(String[] args) throws IOException{
        String direccionServidor = "192.168.114.115"; // Dirección del servidor
        int puerto = 6666; // Puerto del servidor

        Socket socket = new Socket(direccionServidor, puerto);
        DataOutputStream salida = new DataOutputStream(socket.getOutputStream());
        DataInputStream entrada = new DataInputStream(socket.getInputStream());

        // Generar un array aleatorio de 10 enteros entre 1 y 100
        Random random=new Random();
        int[] numeros=new int[10];
        for(int i=0;i<numeros.length;i++)
        {
            numeros[i]=random.nextInt(10)+1;
        }

        // Enviar la longitud del array
        salida.writeInt(numeros.length);

        // Enviar los números del array
        for (int num : numeros) {
            salida.writeInt(num);
        }

        salida.flush();

        // Recibir resultados del servidor
        int suma = entrada.readInt();
        int max = entrada.readInt();
        int min = entrada.readInt();

            
        System.out.println("Respuesta del servidor -> Suma: " + suma + ", Máximo: " + max + ", Mínimo: " + min);

        socket.close();
    }
}