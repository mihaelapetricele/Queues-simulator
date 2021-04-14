package ro.tuc.tp.GUI;


import javax.swing.*;
import java.awt.*;

public class SimulationFrame extends JFrame {
    private final JTextField textNrCozi = new JTextField(3);
    private final JTextField textMinSosire = new JTextField(3);
    private final JTextField textMaxSosire= new JTextField(3);
    private final JTextField textMinServire = new JTextField(3);
    private final JTextField textNrClienti = new JTextField(3);
    private final JTextField textMaxServire = new JTextField(3);
    private final JTextField textTimpSimulare = new JTextField(3);

    private final JTextArea textafisare = new JTextArea(15,60);
    private final JTextArea textafisareresult = new JTextArea(8,20);

    private final JButton submit = new JButton("SUBMIT");

    private final JLabel numarClienti = new JLabel("Introduceti numarul clientilor:");
    private final JLabel numarCozi = new JLabel("Introduceti numarul de cozi:");
    private final JLabel timpMaxSimulare = new JLabel("Introduceti timpul simularii:");
    private final JLabel intervalSosire = new JLabel("Introduceti intervalul timpului de sosire:       Min ");
    private final JLabel intervalSosireMax = new JLabel("   Max ");
    private final JLabel intervalServire = new JLabel("Introduceti intervalul timpului de servire:      Min ");
    private final JLabel intervalServireMax = new JLabel("   Max ");
    private final JLabel afisare = new JLabel("REZULTATE");
    private final JLabel afisareresult = new JLabel("REZULTATE SIMULARE");

    public static final Color purple = new Color(230, 230, 250);

    ViewController viewController = new ViewController(this);

    public SimulationFrame(){

        JFrame frame = new JFrame("Simulare Cozi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,670);
        JPanel panel = new JPanel();
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();

        panel.add(numarClienti);
        panel.add(textNrClienti);

        panel.add(numarCozi);
        panel.add(textNrCozi);
        panel.setMaximumSize(new Dimension(900,30));
        panel.setBackground(purple);

        panel1.add(intervalSosire);
        panel1.add(textMinSosire);
        panel1.add(intervalSosireMax);
        panel1.add(textMaxSosire);
        panel1.setBackground(purple);
        panel1.setMaximumSize(new Dimension(900,30));

        panel2.add(intervalServire);
        panel2.add(textMinServire);
        panel2.add(intervalServireMax);
        panel2.add(textMaxServire);
        panel2.setBackground(purple);
        panel2.setMaximumSize(new Dimension(900,30));

        panel3.add(timpMaxSimulare);
        panel3.add(textTimpSimulare);
        panel3.setBackground(purple);
        panel3.setMaximumSize(new Dimension(900,30));

        panel4.add(submit);
        submit.addActionListener(viewController);
        panel4.setMaximumSize(new Dimension(900,60));
        panel4.setBackground(purple);

        panel5.add(afisare);
        panel5.setBackground(purple);
        panel5.setMaximumSize(new Dimension(900,30));
        panel6.add(textafisare);
        panel6.setMaximumSize(new Dimension(900,300));
        panel6.setBackground(purple);
        textafisare.setEditable(false);

        panel7.add(afisareresult);
        panel7.setBackground(purple);
        panel7.setMaximumSize(new Dimension(900,30));
        panel8.add(textafisareresult);
        panel8.setBackground(purple);
        panel8.setMaximumSize(new Dimension(900,200));
        textafisareresult.setEditable(false);

        JPanel simulareCozi = new JPanel();
        simulareCozi.add(panel);
        simulareCozi.add(panel1);
        simulareCozi.add(panel2);
        simulareCozi.add(panel3);
        simulareCozi.add(panel4);
        simulareCozi.add(panel5);
        simulareCozi.add(panel6);
        simulareCozi.add(panel7);
        simulareCozi.add(panel8);
        simulareCozi.setLayout(new BoxLayout(simulareCozi, BoxLayout.Y_AXIS));
        frame.setContentPane(simulareCozi);
        frame.setVisible(true);

    }

    public JTextField getTextNrCozi() {
        return textNrCozi;
    }

    public JTextField getTextMinSosire() {
        return textMinSosire;
    }

    public JTextField getTextMaxSosire() {
        return textMaxSosire;
    }

    public JTextField getTextMinServire() {
        return textMinServire;
    }

    public JTextField getTextMaxServire() {
        return textMaxServire;
    }

    public JTextField getTextTimpSimulare() {
        return textTimpSimulare;
    }

    public JTextArea getTextafisare() {
        return textafisare;
    }

    public JButton getSubmit() {
        return submit;
    }

    public JTextField getTextNrClienti() {
        return textNrClienti;
    }

    public JTextArea getTextafisareresult() {
        return textafisareresult;
    }

    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

}
