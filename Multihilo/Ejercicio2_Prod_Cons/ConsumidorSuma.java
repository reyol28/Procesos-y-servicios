//Clase consumidor: recoge número de la cola con método "get"
public class ConsumidorSuma extends Thread {
    private Cola cola;
    private int n;
    private int resultado=0;

    //Constructor recibe la cola y un id para el hilo consumidor
    public ConsumidorSuma(Cola c, int n) {
        this.cola = c;
        this.n = n;
    }

    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            System.out.println("suma:");
            valor = cola.get(); //recoge el n�mero
            
            resultado=resultado+valor;
            try {
                sleep(100);
            } catch (InterruptedException e) { }	
        }
        System.out.println("Suma: "+getSuma());
    }
    public int getSuma()
    {
        return resultado;
    }
}