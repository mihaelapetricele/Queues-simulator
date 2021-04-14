package ro.tuc.tp;

import ro.tuc.tp.GUI.SimulationFrame;
import ro.tuc.tp.GUI.ViewController;


public class App 
{
    public static void main( String[] args )
    {
        SimulationFrame view = new SimulationFrame();
        ViewController viewController = new ViewController(view);
    }
}
