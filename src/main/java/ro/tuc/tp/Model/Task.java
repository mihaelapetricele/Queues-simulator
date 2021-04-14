package ro.tuc.tp.Model;


public class Task {
    private int ID;
    private int timp_servire;
    private int timp_sosire;
    private int waitingTime= 0;

    public Task(int ID, int timp_sosire, int timp_servire){
        this.ID = ID;
        this.timp_sosire = timp_sosire;
        this.timp_servire = timp_servire;

    }


    public int getTimp_servire() {
        return timp_servire;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public int getTimp_sosire() {
        return timp_sosire;
    }

    public void setTimp_servire(int timp_servire) {
        this.timp_servire = timp_servire;
    }

    @Override
    public String toString() {
        return "(" + ID + ", " + timp_sosire + ", " + timp_servire + ") ";
    }
}
