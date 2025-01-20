public class Main 
{
    public static void main(String[] args) {
        Cola cola=new Cola();

        Productor productor=new Productor(cola, 1);
        ConsumidorSuma consumidor=new ConsumidorSuma(cola, 1);
        ConsumidorProducto consumidor2=new ConsumidorProducto(cola, 1);

        productor.start();
        consumidor.start();
        consumidor2.start();

       
    }
}