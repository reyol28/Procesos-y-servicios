public class Deposito {
    private double litrosdeposito;
    private boolean ocupado = false;

    public synchronized double llenarDepositoCamion() throws InterruptedException {
        while (!ocupado || litrosdeposito <= 0) {
            wait(); 
        }
        double litrosCargados = Math.round(litrosdeposito * 100.0) / 100.0;//redondeo a 2 decimales
        litrosdeposito = 0;
        ocupado = false;
        System.out.println("Camión cargó: " + litrosCargados + " litros.");
        notifyAll();
        return litrosCargados;
    }

    public synchronized void prodLlenaDep(double litros) throws InterruptedException {
        while (ocupado)//mientras el deposito este ocupado espero
        {
            try {
                wait();
            } 
            catch (InterruptedException ie) {
                System.out.println("Error al esperar");
            }            
        }
        litrosdeposito += litros;
        litrosdeposito = Math.round(litrosdeposito*100.0)/100.0;
        ocupado = true;
        System.out.println("Productor llenó el depósito con =>" + litrosdeposito + " litros.");
        notifyAll();
    }
   
   }
   