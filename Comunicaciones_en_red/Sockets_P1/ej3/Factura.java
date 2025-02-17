package ej3;

import java.io.Serializable;
import java.util.Date;

public class Factura implements Serializable{
    public int numero;
    public Date fecha;
    public double importe;
    public TipoIVA tipoIVA;
    public double importeTotal;
    

    public Factura(int numero,Date fecha, double importe, TipoIVA tipoIVA)
    {
        this.numero=numero;
        this.fecha=fecha;
        this.importe=importe;
        this.tipoIVA=tipoIVA;
        this.importeTotal=0;
    }
    public double getImporteTotal() {
        return importeTotal;
    }
    public void setImporteTotal(double importeTotal) {
        this.importeTotal = importeTotal;
    }
    public Date getFecha() {
        return fecha;
    }
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    public double getImporte() {
        return importe;
    }
    public void setImporte(double importe) {
        this.importe = importe;
    }
    public int getNumero() {
        return numero;
    }
    public void setNumero(int numero) {
        this.numero = numero;
    }
    public TipoIVA getTipoIVA() {
        return tipoIVA;
    }
    public void setTipoIVA(TipoIVA tipoIVA) {
        this.tipoIVA = tipoIVA;
    }

    public double calcularIVA(TipoIVA tipoIVA)
    {
        double iva=0;
        if(tipoIVA.equals(TipoIVA.IGC))
        {
            iva=importe*0.07;
        }
        else if(tipoIVA.equals(TipoIVA.ESP))
        {
            iva=importe*0.21;
        }
        else if(tipoIVA.equals(TipoIVA.EUR))
        {
            iva=importe*0.15;
        }
        return iva;
    }
    public double calcularImporteTotal(double iva)
    {
        double importeTotal=importe+iva;
        return importeTotal;
    }
    


    @Override
    
    public String toString() {
        // TODO Auto-generated method stub
        return super.toString();
    }
}
