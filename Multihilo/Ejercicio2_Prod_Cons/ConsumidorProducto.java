public class ConsumidorProducto extends Thread {
    private Cola cola;
    private int n;
    private int resultado=1;

    //Constructor recibe la cola y un id para el hilo consumidor
    public ConsumidorProducto(Cola c, int n) {
        this.cola = c;
        this.n = n;
    }

    public void run() {
        int valor = 0;
        for (int i = 0; i < 5; i++) {
            System.out.println("prod:");
            valor = cola.get(); //recoge el n�mero
            
            resultado=resultado*valor;
            try {
                sleep(100);
            } catch (InterruptedException e) { }	
        }
        System.out.println("Producto:"+ getProducto());
    }
    public int getProducto()
    {
        return resultado;
    }
}