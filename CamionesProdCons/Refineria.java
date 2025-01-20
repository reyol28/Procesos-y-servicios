public class Refineria {//main
    public static void main(String[] args) {
        Deposito deposito=new Deposito();
        Productor productor=new Productor(deposito);
        Camion camion1=new Camion(deposito, 1);
        Camion camion2=new Camion(deposito, 2);
        Camion camion3=new Camion(deposito, 3);

        productor.start();
        camion1.start();
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.println("Error espera");
        }
        camion2.start();
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            System.out.println("Error espera");
        }
        camion3.start();


        try{
            productor.join();
            camion1.join();
            camion2.join();
            camion3.join();
        }
        catch(InterruptedException ie)
        {
            Thread.currentThread().interrupt();
        }
    }
}
