package Ejercicio3;

public class GenerarHilo implements Runnable {
    Thread t;

    public GenerarHilo() {
        t = new Thread(this, "Thread");
        System.out.println("Child thread: " + t);
        t.start();
    }

    @Override
    public void run() {
        try {
            System.out.println("Child Thread");
            Thread.sleep(50);
        } catch (InterruptedException e) {
            System.out.println("The child thread is interrupted.");
        }
        System.out.println("Exiting the child thread");
    }
}

