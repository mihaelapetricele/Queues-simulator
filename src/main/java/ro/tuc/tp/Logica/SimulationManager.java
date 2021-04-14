package ro.tuc.tp.Logica;


import ro.tuc.tp.Business.Selection;
import ro.tuc.tp.Model.Task;

import javax.swing.*;
import java.io.*;
import java.util.*;
import java.util.List;


public class SimulationManager implements Runnable {
    //data read from UI

    public int timeLimit;//maximul processing time = read from UI
    public int maxTimpServire;
    public int minTimpServire;
    public int minTimpSosire;
    public int maxTimpSosire;
    public int numberOfServers;
    public int numberOfClients;
    public Selection selectionPolicy = Selection.SHORTEST_TIME;

    private JTextArea textArea;
    private JTextArea textAreaSim;
    private JButton stopSim;
    private final Scheduler scheduler;  //entitatea responsabila cu menagerierea cozii si distribuirea clientilor
    private List<Task> generatedTasks;   //clientii care cumpara din magazin
    private File myFile = new File("rezultatSimulare.txt");
    private FileWriter myWriter = new FileWriter("rezultatSimulare.txt");

    public SimulationManager(int numberOfClients, int numberOfServers, int minTimpSosire, int maxTimpSosire, int minTimpServire, int maxTimpServire, int timeLimit) throws IOException {
        this.numberOfClients = numberOfClients;
        this.numberOfServers = numberOfServers;
        this.minTimpSosire = minTimpSosire;
        this.maxTimpSosire = maxTimpSosire;
        this.minTimpServire = minTimpServire;
        this.maxTimpServire = maxTimpServire;
        this.timeLimit = timeLimit;

        if (myFile.createNewFile()) {
            System.out.println("Fiserul s-a creat: " + myFile.getName());
        } else {
            System.out.println("Fisierul exista deja");
        }

        textArea = new JTextArea(15, 60);
        textArea = new JTextArea(8, 20);
        stopSim = new JButton();
        scheduler = new Scheduler(numberOfServers, numberOfClients);
        scheduler.changeStrategy(selectionPolicy);
        generateNRandmonClients(numberOfClients);

    }

    public int randomTimpServire() {
        int random;
        random = (int) (minTimpServire + (Math.random() * (maxTimpServire - minTimpServire)));
        return random;
    }

    public int randomTimpSosire() {
        int random;
        random = (int) (minTimpSosire + (Math.random() * (maxTimpSosire - minTimpSosire)));
        return random;
    }

    public void generateNRandmonClients(int n) {
        generatedTasks = new ArrayList<>();
        int i = 1;
        while (i <= n) {
            Task clientNou = new Task(i, randomTimpSosire(), randomTimpServire());
            generatedTasks.add(clientNou);
            i++;
        }
        Collections.sort(generatedTasks, new Sortare());
    }

    public void run() {
        try {
            double avgTime = 0, avgServTime = 0;
            int currentTime = 0, nr = 0, peakSecond = 0, peakTime = 0; // tSimulation
            List<Task> toRemove = new ArrayList<>();
            String rezultat, rezAvgTime, rezAvgServTime, rezPeakSecond;
            while (currentTime < timeLimit) {
                for (Task task : generatedTasks) {
                    if (currentTime == task.getTimp_sosire()) {
                        scheduler.dispatchTask(task); //trimit task-ul la coada
                        toRemove.add(task); //adaug task-ul intr-o alta lista ca sa il elimin la final
                        avgTime += task.getWaitingTime();
                        avgServTime += task.getTimp_servire();
                        nr++;
                        if(peakTime< scheduler.maxTime())
                        {
                            peakTime = scheduler.maxTime();
                            peakSecond = currentTime;
                        }
                    }
                }
                for (Task objRemove : toRemove) {
                    generatedTasks.remove(objRemove); //elimin task-urile din coada cu clientii in asteptare
                }
                rezultat = rezultat(currentTime);
                textArea.setText(rezultat); myWriter.write(rezultat);
                currentTime++;
                if(generatedTasks.isEmpty() && scheduler.simulare())
                {
                    break;
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            scheduler.stopServer();
            rezAvgTime =  "Average waiting time: " + avgTime / nr + "\n";
            rezAvgServTime= "Average service time: " + avgServTime / nr + "\n";
            rezPeakSecond = "Peak second: " + peakSecond + "\n";
            textAreaSim.append(rezAvgTime); textAreaSim.append(rezAvgServTime); textAreaSim.append(rezPeakSecond);
            myWriter.write(rezAvgTime); myWriter.write(rezAvgServTime);myWriter.write(rezPeakSecond);myWriter.close();
        } catch (IOException e) { System.out.println("A aparut o eroare!"); }
    }

    //metoda String pentru a afisa log-ul de evenimente
    public String rezultat(int curentTime) {
        String result = "Time: " + curentTime + "\n";
        result = result + "Waiting clients: ";
        for (Task i : generatedTasks) {
            result = result + i.toString();
        }
        result = result + "\n" + scheduler.toString() + "\n";
        return result;
    }

    public void setTextArea(JTextArea textArea) {
        this.textArea = textArea;
    }

    public void setTextAreaSim(JTextArea textAreaSim) {
        this.textAreaSim = textAreaSim;
    }
}
