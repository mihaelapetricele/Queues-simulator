package ro.tuc.tp.Business;


import ro.tuc.tp.Model.Server;
import ro.tuc.tp.Model.Task;

import java.util.List;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        int minim = Integer.MAX_VALUE;
        int idCoada = 0;
        for (Server cm : servers) {
            if (cm.getTasks().size() < minim) {
                idCoada = cm.getNrCoada();
                minim = cm.getTasks().size();
            }
        }
        for (Server s : servers) {
            if (s.getNrCoada() == idCoada) {
                int sum=0;
                for(Task t: s.getTasks())
                {
                    sum += t.getTimp_servire();
                }
                task.setWaitingTime(sum);
                s.addClient(task); //adaugam clientul la coada cu cel mai mic numar de clienti procesati

            }
        }
    }
}
