public class Main 
{
    public static void main(String[] args) {
        Cola cola=new Cola();

        Productor productor=new Productor(cola, 1);
        Consumidor consumidor=new Consumidor(cola, 1);

        productor.start();
        consumidor.start();
    }
}
