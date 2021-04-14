package ro.tuc.tp.Model;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable {
    private final BlockingQueue<Task> tasks;
    private final AtomicInteger timpAsteptare;
    private final int nrCoada; //numarul fiecarei cozi
    private boolean run;

    public Server(int nrCoada, int nrMaxServers) {
        tasks = new ArrayBlockingQueue<>(nrMaxServers);
        this.timpAsteptare = new AtomicInteger();
        this.nrCoada = nrCoada;
    }

    public void addClient(Task clientNou) {
        tasks.add(clientNou);
        timpAsteptare.addAndGet(clientNou.getTimp_servire());
    }

    public void setRun(boolean run) {
        this.run = run;
    }

    public void run() {
        while (true) {
            if (!tasks.isEmpty()) {
                try {
                    int timp_servire;
                    Thread.sleep(1000);
                    timp_servire = tasks.peek().getTimp_servire(); //extrag timpul de servire al clientului din capul cozii
                    timpAsteptare.decrementAndGet(); //decrementez timpul de așteptare
                    //System.out.println(c);
                    timp_servire-- ;  //scad timpul de servire
                    tasks.peek().setTimp_servire(timp_servire); //setez timpul de servire nou decrementat
                    //System.out.println(tasks);
                    if (timp_servire == 0) {  //dacă timpul de servire este 0
                        tasks.take();         //extrag clientul din coadă
                    }
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }


    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public AtomicInteger getTimpAsteptare() {
        return timpAsteptare;
    }

    public int getNrCoada() {
        return nrCoada;
    }

    public String toString() {
        String res = "";
        if (getTimpAsteptare().intValue() == 0) {
            res = res + "closed";
        } else {
            res = res + tasks.toString() + "\n";
        }
        return res;
    }

}
