public class Camion extends Thread {
    public int numCamion;
    public Deposito deposito;

    
    public Camion (Deposito dep, int n)
    {
        this.deposito=dep;
        this.numCamion=n;
    }
    public void run() {
        try {
            double sumaCargas=0;
            for (int i = 0; i < 5; i++) 
            {//5 cargas
                double cargado = deposito.llenarDepositoCamion();
                System.out.println("Camión " + numCamion + " carga " + (i + 1) + ": " + cargado + " litros.");
                sumaCargas+=cargado;
                Thread.sleep((int) (Math.random() * 2000));
            }
            System.out.println("Volumen total recogido: "+sumaCargas+" - - -Operación carga finalizada!!");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
   
   
