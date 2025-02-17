package ej3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class TcpClient {
    public static void main(String[] arg) throws IOException,
            ClassNotFoundException {
    String Host = "localhost";
    int Puerto = 6000;//puerto remoto	
        
    System.out.println("PROGRAMA CLIENTE INICIADO....");
    Socket cliente = new Socket(Host, Puerto);	
                

    Scanner sc=new Scanner(System.in);
    System.out.println("INTRODUCE LOS DATOS DE LA FACTURA:");
    System.out.println("Número:");
    int num=sc.nextInt();
    
    SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy");
    Date date=null;
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
    double importe=sc.nextDouble();
    System.out.println("Tipo IVA:\n1. IGC\n2. ESP\n3. EUR");
    int eleccion=sc.nextInt();
    TipoIVA tipoIVA=null;
    switch(eleccion)
    {
        case 1:
            tipoIVA=TipoIVA.IGC;
            break;
        case 2:
            tipoIVA=TipoIVA.ESP;
            break;
        case 3:
            tipoIVA=TipoIVA.EUR;
            break;
        default:
            tipoIVA=TipoIVA.IGC;
            System.out.println("Opción no válida, asignado IGC automáticamente.");
            break;
    }
    
    Factura factura=new Factura(num, date, importe,tipoIVA);
    System.out.println("Factura: "+factura);
    ObjectOutputStream envioObjeto = new ObjectOutputStream(cliente.getOutputStream());
    
    envioObjeto.writeObject(factura);


    //Flujo de entrada para objetos
    ObjectInputStream facturaRecibida = new ObjectInputStream(cliente.getInputStream());
    //Se recibe un objeto
    Factura dato = (Factura) facturaRecibida.readObject();//recibo objeto
    System.out.println("Datos de la factura:\nNúmero:"+dato.getNumero()+"\nFecha:"+dato.getFecha()+
    "\nImporte sin IVA:"+dato.getImporte()+"\nTipo IVA:"+dato.getTipoIVA()+"\nImporte total:"+dato.getImporteTotal());
    
	
                         
		
    // CERRAR STREAMS Y SOCKETS
    sc.close();
    envioObjeto.close();
    facturaRecibida.close();
    cliente.close();		
  }

}

