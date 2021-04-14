package ro.tuc.tp.Business;


import ro.tuc.tp.Model.Server;
import ro.tuc.tp.Model.Task;

import java.util.List;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addTask(List<Server> servers, Task task) {
        int timpMinim = Integer.MAX_VALUE;
        int idCoada = 0;
        for(Server cm: servers)
        {
            if(cm.getTimpAsteptare().intValue() < timpMinim)
            {
                idCoada = cm.getNrCoada();
                timpMinim = cm.getTimpAsteptare().intValue();
            }
        }
        for(Server s : servers) {
            if(s.getNrCoada() == idCoada) {
                int sum=0;
                for(Task t: s.getTasks())
                {
                    sum += t.getTimp_servire();
                }
                task.setWaitingTime(sum);
                s.addClient(task); //aduagam clientul la coada cu timpul de asteptare cel mai mic
            }
        }

    }
}
