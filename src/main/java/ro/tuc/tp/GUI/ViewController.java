package ro.tuc.tp.GUI;


import ro.tuc.tp.Logica.SimulationManager;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class ViewController implements ActionListener {

    private final SimulationFrame frame;

    public ViewController(SimulationFrame simulationFrame) {
        frame = simulationFrame;
    }

    public void validate(int minTimpServire, int maxTimpServire, int minTimpSosire, int maxTimpSosire) throws ExceptionValidate {
        if (minTimpServire > maxTimpServire)
            throw new ExceptionValidate("Limitele pentru timpul de servire nu sunt valide!");
        if (minTimpSosire > maxTimpSosire) {
            throw new ExceptionValidate("Limitele pentru timpul de sosire nu sunt valide!");
        }

    }

    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == frame.getSubmit()) {
            try {
                int numberOfClients = parseInt(frame.getTextNrClienti().getText());
                int numberOfServers = parseInt(frame.getTextNrCozi().getText());
                int maxTimpServire = parseInt(frame.getTextMaxServire().getText());
                int minTimpServire = parseInt(frame.getTextMinServire().getText());
                int maxTimpSosire = parseInt(frame.getTextMaxSosire().getText());
                int minTimpSosire = parseInt(frame.getTextMinSosire().getText());
                int timeLimit = parseInt(frame.getTextTimpSimulare().getText());
                validate(minTimpServire, maxTimpServire, minTimpSosire, maxTimpSosire);

                SimulationManager sim = null;
                try {
                    sim = new SimulationManager(numberOfClients, numberOfServers, minTimpSosire, maxTimpSosire, minTimpServire, maxTimpServire, timeLimit);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                sim.setTextArea(frame.getTextafisare());
                Thread thread = new Thread(sim);
                frame.getTextafisare().setText("");
                sim.setTextArea(frame.getTextafisare());
                frame.getTextafisareresult().setText("");
                sim.setTextAreaSim(frame.getTextafisareresult());
                thread.start();
            } catch (ExceptionValidate exceptionValidate) {
                frame.showError(exceptionValidate.getMessage());
            }

        }

    }
}