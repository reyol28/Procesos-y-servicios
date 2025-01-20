//Clase consumidor: recoge número de la cola con método "get"
public class Consumidor extends Thread {
    private Cola cola;
    private int n;
    private int resultado=0;

    //Constructor recibe la cola y un id para el hilo consumidor
    public Consumidor(Cola c, int n) {
        this.cola = c;
        this.n = n;
    }

    public void run() {
        int valor = 0;
        for (int i = 0; i < 10; i++) {
            valor = cola.get(); //recoge el n�mero
            resultado=resultado+valor;
            try {
                sleep(100);
            } catch (InterruptedException e) { }	
        }
        System.out.println("Suma: "+resultado);
    }
    public int getSuma()
    {
        return resultado;
    }
}
