package ro.tuc.tp.Logica;

import ro.tuc.tp.Model.Task;

import java.util.Comparator;

public class Sortare implements Comparator<Task>{

    @Override
    public int compare(Task c1, Task c2) {
        return c1.getTimp_sosire() - c2.getTimp_sosire();
    }
}
