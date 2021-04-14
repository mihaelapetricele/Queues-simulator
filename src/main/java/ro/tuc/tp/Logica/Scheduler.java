package ro.tuc.tp.Logica;


import ro.tuc.tp.Business.ConcreteStrategyQueue;
import ro.tuc.tp.Business.ConcreteStrategyTime;
import ro.tuc.tp.Business.Selection;
import ro.tuc.tp.Business.Strategy;
import ro.tuc.tp.Model.Server;
import ro.tuc.tp.Model.Task;

import java.util.ArrayList;
import java.util.List;

public class Scheduler{
    private final List<Server> servers = new ArrayList<>();
    private final int maxNrServers;
    private final int maxTasksPerServer;
    private Strategy strategie;

    public Scheduler(int maxNrServers, int maxTasksPerServer){
        this.maxNrServers = maxNrServers;
        this.maxTasksPerServer = maxTasksPerServer;
        for(int s = 0 ; s < maxNrServers; s++) {
            Server newServer = new Server(s,maxTasksPerServer);
            servers.add(newServer);
            Thread threadScheduler = new Thread(newServer);
            threadScheduler.start();
        }
    }

    public void stopServer()
    {
        for(Server serv : servers)
        {
            serv.setRun(false);
        }
    }

    public int maxTime()
    {
        int sum = 0;
        for(Server server: servers)
        {
            sum += server.getTasks().size();
        }
        return sum;
    }

    public void changeStrategy(Selection selectionPolicy)
    {
        if(selectionPolicy == Selection.SHORTEST_TIME)
        {
            strategie = new ConcreteStrategyTime();
        }
        else
        {
            if(selectionPolicy == Selection.SHORTEST_QUEUE)
            {
                strategie = new ConcreteStrategyQueue();
            }
        }
    }
    public  void dispatchTask(Task t){

        strategie.addTask(servers, t);
    }

    public boolean simulare() {
        for(Server server:servers)
        {
            if(!server.getTasks().isEmpty() && server.getTimpAsteptare().intValue() != 0)
            {
                return false;
            }
        }
        return true;
    }



    public String toString()
    {
        String afisare = "";
        for(Server s : servers)
        {
            int nrCoada = s.getNrCoada() + 1;
            afisare = afisare + "Queue " + nrCoada + ": " + s.toString() + "\n";
        }
        return afisare;
    }

}

