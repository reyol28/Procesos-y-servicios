import java.util.Random;

public class Productor extends Thread
{
    public Cola cola;
    public int n;
    public Productor(Cola cola, int n)
    {
        this.cola=cola;
        this.n=n;
    }

    public void run()
    {
        int numero=0;
        for (int i = 0; i < 10; i++) {
            numero=generarRandom();
            cola.put(numero);
            
            try {
                sleep(100);
            } catch (InterruptedException e) { }	
        }
    }


    public int generarRandom()
    {
        Random random=new Random();
        int numAleatorio=(random.nextInt(100)+1);
        return numAleatorio;
    }
}
