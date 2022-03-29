package BusinessLogic;

import GUI.SimulationFrame;
import Model.ClientTask;
import Model.Server;

import java.awt.event.ActionEvent;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

public class SimulationManager implements Runnable {
    public static int timeLimit = 60;
    public int maxProcessingTime;
    public int minProcessingTime;
    public int maxArrivalTime;
    public int minArrivalTime;
    public int numberOfServers;
    public int numberOfClients;
    public int maxTasksServer;
    public boolean getOut = false;
    public SelectionPolicy selectionPolicy;

    private static double averageWaitingTime = 0.0;
    private static double averageServiceTime = 0.0;
    private double peakHour = 0.0;
    private int timePeakHour = 0;
    private final Scheduler scheduler;
    private final SimulationFrame frame;
    private final LinkedList<ClientTask> generatedTasks = new LinkedList<>();
    private FileWriter myWriter;

    public SimulationManager(SimulationFrame frames) {
        frame = frames;
        numberOfClients = Integer.parseInt(frame.getClients());
        numberOfServers = Integer.parseInt(frame.getQueues());
        System.out.println(numberOfServers);
        timeLimit = Integer.parseInt(frame.getSimulationInterval());
        maxArrivalTime = Integer.parseInt(frame.getMaxArrivalTime());
        minArrivalTime = Integer.parseInt(frame.getMinArrivalTime());
        maxProcessingTime = Integer.parseInt(frame.getMaxServiceTime());
        minProcessingTime = Integer.parseInt(frame.getMinServiceTime());
        maxTasksServer = Integer.parseInt(frame.getMaxClientsPerServer());
        scheduler = new Scheduler(numberOfServers, maxTasksServer);
        stopQueues();
        String value = frame.getComboBox().toString();
        if (value.equals("TimeStrategy")) {
            selectionPolicy = SelectionPolicy.SHORTEST_TIME;
        } else
            selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
        scheduler.changeStrategy(selectionPolicy);
        generateNRandomClientTasks();
        try {
            myWriter = new FileWriter("log.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopQueues() {
        frame.StopListener(
                (ActionEvent e) -> {
                    getOut = true;
                    frame.setValidateVisible(true);
                    frame.setStopVisible(false);
                    averageWaitingTime = 0.0;
                    averageServiceTime = 0.0;
                    peakHour = 0.0;
                    timePeakHour = 0;
                    frame.setAverageWaitingTimeJLabel("--");
                    frame.setAverageServiceTimeJLabel("--");
                    frame.setPeakHourLabel("--");
                }
        );
    }

    static class ByArrivalTime implements Comparator<ClientTask> {
        @Override
        public int compare(ClientTask o1, ClientTask o2) {
            return o1.getArrivalTime() - o2.getArrivalTime();
        }
    }

    private void generateNRandomClientTasks() {
        int index = 0;
        Random rand = new Random();

        while (index != numberOfClients) {
            int atime = rand.nextInt(maxArrivalTime - minArrivalTime + 1) + minArrivalTime;
            int time = rand.nextInt(maxProcessingTime - minProcessingTime + 1) + minProcessingTime;
            generatedTasks.add(new ClientTask(index + 1, atime, time));
            index++;
        }
        Comparator<ClientTask> byATime = new ByArrivalTime();
        generatedTasks.sort(byATime);
        for (int i = 0; i < numberOfClients; i++) {
            System.out.println(generatedTasks.get(i));
        }
    }

    public static void addAverageWaitingTime(int number) {
        averageWaitingTime += number;
    }

    public static void addAverageServiceTime(int number) {
        averageServiceTime += number;
    }

    public void peakHour(LinkedList<Server> serverList, int time) {
        int waitingTime = 0;
        for (Server queue : serverList) {
            waitingTime += queue.getWaitingPeriod().intValue();
        }
        if (waitingTime / (double) numberOfServers - peakHour > 1e-3) {
            peakHour = waitingTime / (double) numberOfServers;
            timePeakHour = time;
        }
    }

    public void printResult(LinkedList<Server> serverList, int time) {
        try {
            myWriter.write("Time " + time + "\n");
            myWriter.write("Waiting clients: ");
            StringBuilder waitingQueue = new StringBuilder();
            for (ClientTask remainedTasks : generatedTasks) {
                myWriter.write(remainedTasks + ";");
                waitingQueue.append(remainedTasks).append("; ");
            }
            frame.setWaitingQueue(waitingQueue.toString());
            StringBuilder queues = new StringBuilder();
            myWriter.write("\n");
            for (Server queue : serverList) {
                myWriter.write("Queue " + queue.getTheId() + ": ");
                queues.append("Queue ").append(queue.getTheId()).append(": ");
                ClientTask[] clients = queue.getTasks();
                if (clients.length == 0) {
                    myWriter.write("closed");
                    queues.append("closed");
                }
                for (ClientTask task : clients) {
                    myWriter.write(task + "; ");
                    queues.append(task).append("; ");
                }
                myWriter.write("\n");
                queues.append("\n");
            }
            frame.setQueueEvolution(queues.toString());
            myWriter.write("--------------------------------------------------------------------------\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean verifyEndOfSimulation(LinkedList<Server> serverList) {
        if (generatedTasks.size() != 0)
            return false;
        else
            for (Server queue : serverList) {
                if (queue.getTaskSize() != 0)
                    return false;
            }
        return true;
    }

    @Override
    public void run() {
        int currentTime = 0;
        LinkedList<Server> serverList = scheduler.getServers();
        while (!verifyEndOfSimulation(serverList)) {
            frame.setTime(currentTime + "");
            if (getOut)
                break;
            ClientTask client = generatedTasks.peekFirst();
            while (client != null && client.getArrivalTime() <= currentTime) {
                if (scheduler.dispatchTask(client)) {
                    addAverageServiceTime(client.getServiceTime());
                    generatedTasks.removeFirst();
                } else
                    break;
                client = generatedTasks.peekFirst();
            }
            peakHour(serverList, currentTime);
            printResult(serverList, currentTime);
            currentTime++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println("Problem with main thread sleeping");
            }
        }
        printResult(serverList, currentTime);
        frame.setAverageWaitingTimeJLabel(Math.round(averageWaitingTime / numberOfClients * 100.0) / 100.0 + "");
        frame.setAverageServiceTimeJLabel(Math.round(averageServiceTime / numberOfClients * 100.0) / 100.0 + "");
        frame.setPeakHourLabel(timePeakHour + "");
        for (Server queue : serverList)
            queue.stop();
        try {
            myWriter.write("Average waiting time: " + Math.round(averageWaitingTime / numberOfClients * 100.0) / 100.0 + "\nAverage service time: " + Math.round(averageServiceTime / numberOfClients * 100.0) / 100.0 + "\nPeak Hour at the time: " + timePeakHour);
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SimulationFrame frame = new SimulationFrame();
        frame.ValidateDataListener(
                (ActionEvent e) -> {
                    String output = null;
                    int ok = 1;
                    int check, min, max;
                    try {
                        output = frame.getClients();
                        check = Integer.parseInt(output);
                        output = frame.getQueues();
                        check = Integer.parseInt(output);
                        output = frame.getSimulationInterval();
                        check = Integer.parseInt(output);
                        output = frame.getMaxArrivalTime();
                        max = Integer.parseInt(output);
                        output = frame.getMinArrivalTime();
                        min = Integer.parseInt(output);
                        if (min > max)
                            throw new NumberFormatException();
                        output = frame.getMaxServiceTime();
                        max = Integer.parseInt(output);
                        output = frame.getMinServiceTime();
                        min = Integer.parseInt(output);
                        if (min > max)
                            throw new NumberFormatException();
                        output = frame.getMaxClientsPerServer();
                        check = Integer.parseInt(output);
                    } catch (NumberFormatException a) {
                        ok = 0;
                        frame.showError("Invalid data for \"" + output + "\". Try again!");
                    }
                    if (ok == 1) {
                        frame.setStartVisible(true);
                        frame.setValidateVisible(false);
                    }
                }
        );
        frame.StartListener(
                (ActionEvent e) -> {
                    frame.setStartVisible(false);
                    frame.setStopVisible(true);
                    SimulationManager gen = new SimulationManager(frame);
                    Thread t = new Thread(gen);
                    t.start();
                }
        );
    }
}