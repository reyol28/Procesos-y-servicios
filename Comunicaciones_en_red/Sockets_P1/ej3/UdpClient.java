package ej3;
import java.io.*;
import java.net.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class UdpClient {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String host = "localhost";
        int puerto = 6000;
        //creo el socket
        DatagramSocket socket = new DatagramSocket();
        
        System.out.println("PROGRAMA CLIENTE INICIADO....");
        Scanner sc = new Scanner(System.in);
        //se piden los datos del objeto factura
        System.out.println("INTRODUCE LOS DATOS DE LA FACTURA:");
        System.out.println("Número:");
        int num = sc.nextInt();
        
        SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        while (date == null) {
            System.out.println("Fecha:");
            String fecha = sc.next();
            try {
                date = formato.parse(fecha);
            } catch (ParseException e) {
                System.out.println("Dato fecha no válido. Inténtelo nuevamente.");
            }
        }
        
        System.out.println("Importe:");
        double importe = sc.nextDouble();
        System.out.println("Tipo IVA:\n1. IGC\n2. ESP\n3. EUR");
        int eleccion = sc.nextInt();
        TipoIVA tipoIVA = null;
        switch (eleccion) {
            case 1:
                tipoIVA = TipoIVA.IGC;
                break;
            case 2:
                tipoIVA = TipoIVA.ESP;
                break;
            case 3:
                tipoIVA = TipoIVA.EUR;
                break;
            default:
                tipoIVA = TipoIVA.IGC;
                System.out.println("Opción no válida, asignado IGC automáticamente.");
                break;
        }
        
        Factura factura = new Factura(num, date, importe, tipoIVA);
        System.out.println("Factura: " + factura);
        
        //serializo el objeto para enviarlo como bytearray
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream envioObjeto = new ObjectOutputStream(baos);
        envioObjeto.writeObject(factura);
        byte[] datosEnviados = baos.toByteArray();
        
        InetAddress direccionServidor = InetAddress.getByName(host);
        DatagramPacket paqueteSalida = new DatagramPacket(datosEnviados, datosEnviados.length, direccionServidor, puerto);
        socket.send(paqueteSalida);
        
        
        byte[] buffer = new byte[1024];
        DatagramPacket paqueteEntrada = new DatagramPacket(buffer, buffer.length);
        socket.receive(paqueteEntrada);
        //deserializo el objeto recibido (el mismo con los cálculos hechos)
        ByteArrayInputStream bais = new ByteArrayInputStream(paqueteEntrada.getData());
        ObjectInputStream facturaRecibida = new ObjectInputStream(bais);
        Factura dato = (Factura) facturaRecibida.readObject();
        
        System.out.println("Datos de la factura:\nNúmero:" + dato.getNumero() + "\nFecha:" + dato.getFecha() +
                "\nImporte sin IVA:" + dato.getImporte() + "\nTipo IVA:" + dato.getTipoIVA() + "\nImporte total:" + dato.getImporteTotal());
        
        //cierro socket e inputs y outputs
        sc.close();
        envioObjeto.close();
        facturaRecibida.close();
        socket.close();
    }
}
