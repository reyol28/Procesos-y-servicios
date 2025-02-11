

public class Productor extends Thread 
{
    private Deposito deposito;

    public Productor(Deposito deposito) 
    {
        this.deposito = deposito;
    }

    public void run() 
    {
        try {
            for (int i = 0; i < 15; i++) 
            {//llena 15 veces
                double litros = Math.round(Math.random() * 1000 * 100.0) / 100.0;//en vez de crear un método para generar un random, es más cómodo usar Math.random()
                deposito.prodLlenaDep(litros);
                Thread.sleep((int)(Math.random()*2000));//si quisiera simular un tiempo más realista podría generar un random dentro del sleep para que no sea siempre igual el número de milisegundos que para
            }
            
        } 
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
   