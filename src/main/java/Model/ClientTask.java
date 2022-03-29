package Model;

public class ClientTask {
    private final int ID;
    private final int arrivalTime;
    private int serviceTime;

    public ClientTask(int id, int atime, int time) {
        ID = id;
        arrivalTime = atime;
        serviceTime = time;
    }

    public synchronized int getServiceTime() {
        return serviceTime;
    }

    public void decrementServiceTime() {
        serviceTime -= 1;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public String toString() {
        return "(" + ID + "," + arrivalTime + "," + serviceTime + ")";
    }

}
