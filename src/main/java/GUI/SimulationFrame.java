package GUI;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class SimulationFrame extends JFrame {

    private final JTextField NumberClientsTextField;
    private final JTextField NumberQueuesTField;
    private final JTextField SimulationIntervalTField;
    private final JTextField MinArrivalTField;
    private final JTextField MaxArrivalTField;
    private final JTextField MinServiceTField;
    private final JTextField MaxServiceTField;
    private final JTextField MaxClientsideServerTFField;
    private final JButton btnNewButton;
    private final JComboBox<String> comboBox;
    private final JButton btnValidateInputData;
    private final JButton btnStop;
    private final JLabel TimeJLabel_1;
    private final JTextArea textArea_waitingClients;
    private final JTextArea textArea_queueEvolution;
    private final JLabel averageWaitingTimeLabel;
    private final JLabel averageServiceTimeLabel;
    private final JLabel PeakHourLabel;

    public SimulationFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 885, 631);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Simulation running", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
        panel.setBounds(21, 27, 389, 557);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel queueEvolutionJLabel = new JLabel("Queue Evolution:");
        queueEvolutionJLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        queueEvolutionJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        queueEvolutionJLabel.setBounds(0, 203, 123, 26);
        panel.add(queueEvolutionJLabel);

        JLabel simulationResults = new JLabel("Simulation Results");
        simulationResults.setHorizontalAlignment(SwingConstants.CENTER);
        simulationResults.setFont(new Font("Thoma", Font.PLAIN, 20));
        simulationResults.setBounds(94, 36, 187, 32);
        panel.add(simulationResults);

        JSeparator separator = new JSeparator();
        separator.setBounds(23, 66, 344, 2);
        panel.add(separator);

        JLabel TimeJLabel = new JLabel("Time:");
        TimeJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TimeJLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        TimeJLabel.setBounds(-16, 88, 98, 26);
        panel.add(TimeJLabel);

        JLabel waitingClientsLabel = new JLabel("Waiting Clients:");
        waitingClientsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        waitingClientsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        waitingClientsLabel.setBounds(0, 122, 108, 26);
        panel.add(waitingClientsLabel);

        JLabel AverageWaitingTimeJLabel = new JLabel("Average Waiting Time:");
        AverageWaitingTimeJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AverageWaitingTimeJLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        AverageWaitingTimeJLabel.setBounds(0, 485, 156, 26);
        panel.add(AverageWaitingTimeJLabel);

        JLabel AverageServiceTimeJLabel = new JLabel("Average Service Time:");
        AverageServiceTimeJLabel.setHorizontalAlignment(SwingConstants.CENTER);
        AverageServiceTimeJLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        AverageServiceTimeJLabel.setBounds(0, 510, 156, 26);
        panel.add(AverageServiceTimeJLabel);

        JLabel peakHourLabel = new JLabel("Peak Hour:");
        peakHourLabel.setHorizontalAlignment(SwingConstants.CENTER);
        peakHourLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        peakHourLabel.setBounds(197, 485, 123, 26);
        panel.add(peakHourLabel);


        textArea_queueEvolution = new JTextArea();
        textArea_queueEvolution.setWrapStyleWord(true);
        textArea_queueEvolution.setLineWrap(true);
        textArea_queueEvolution.setEditable(false);
        textArea_queueEvolution.setFont(new Font("Monospaced", Font.PLAIN, 15));
        JScrollPane scrollPane_1 = new JScrollPane(textArea_queueEvolution);
        scrollPane_1.setBounds(10, 226, 369, 249);
        panel.add(scrollPane_1);


        textArea_waitingClients = new JTextArea();
        textArea_waitingClients.setWrapStyleWord(true);
        textArea_waitingClients.setLineWrap(true);
        textArea_waitingClients.setEditable(false);
        textArea_waitingClients.setFont(new Font("Monospaced", Font.PLAIN, 15));
        JScrollPane scrollPane = new JScrollPane(textArea_waitingClients);
        scrollPane.setBounds(10, 146, 369, 58);
        panel.add(scrollPane);

        TimeJLabel_1 = new JLabel("");
        TimeJLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        TimeJLabel_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        TimeJLabel_1.setBounds(58, 88, 34, 26);
        panel.add(TimeJLabel_1);

        averageWaitingTimeLabel = new JLabel("");
        averageWaitingTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        averageWaitingTimeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        averageWaitingTimeLabel.setBounds(153, 485, 34, 26);
        panel.add(averageWaitingTimeLabel);

        averageServiceTimeLabel = new JLabel("");
        averageServiceTimeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        averageServiceTimeLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        averageServiceTimeLabel.setBounds(153, 510, 34, 26);
        panel.add(averageServiceTimeLabel);

        PeakHourLabel = new JLabel("");
        PeakHourLabel.setHorizontalAlignment(SwingConstants.CENTER);
        PeakHourLabel.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        PeakHourLabel.setBounds(298, 485, 34, 26);
        panel.add(PeakHourLabel);

        JPanel panel_1 = new JPanel();
        panel_1.setBorder(new TitledBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Data Input", TitledBorder.LEFT, TitledBorder.TOP, null, new Color(0, 0, 0)), "", TitledBorder.LEFT, TitledBorder.TOP, null, Color.BLACK));
        panel_1.setBounds(440, 27, 421, 557);
        contentPane.add(panel_1);
        panel_1.setLayout(null);

        JLabel numberOfClients = new JLabel("Number of Clients:");
        numberOfClients.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfClients.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        numberOfClients.setBounds(60, 104, 156, 25);
        panel_1.add(numberOfClients);

        JLabel numberOfQueues = new JLabel("Number of queues:");
        numberOfQueues.setHorizontalAlignment(SwingConstants.CENTER);
        numberOfQueues.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        numberOfQueues.setBounds(60, 139, 156, 25);
        panel_1.add(numberOfQueues);

        JLabel simulationInterval = new JLabel("Simulation Interval:");
        simulationInterval.setHorizontalAlignment(SwingConstants.CENTER);
        simulationInterval.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        simulationInterval.setBounds(60, 174, 156, 25);
        panel_1.add(simulationInterval);

        JLabel minArrivalTime = new JLabel("Minimum arrival time:");
        minArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
        minArrivalTime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        minArrivalTime.setBounds(49, 209, 156, 25);
        panel_1.add(minArrivalTime);

        JLabel maxArrivalTime = new JLabel("Maximum arrival time:");
        maxArrivalTime.setHorizontalAlignment(SwingConstants.CENTER);
        maxArrivalTime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        maxArrivalTime.setBounds(49, 244, 156, 25);
        panel_1.add(maxArrivalTime);

        JLabel minServiceTime = new JLabel("Minimum service time:");
        minServiceTime.setHorizontalAlignment(SwingConstants.CENTER);
        minServiceTime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        minServiceTime.setBounds(49, 279, 156, 25);
        panel_1.add(minServiceTime);

        JLabel maxServiceTime = new JLabel("Maximum service time:");
        maxServiceTime.setHorizontalAlignment(SwingConstants.CENTER);
        maxServiceTime.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        maxServiceTime.setBounds(49, 314, 156, 25);
        panel_1.add(maxServiceTime);

        JLabel insertData = new JLabel("Insert the data here");
        insertData.setHorizontalAlignment(SwingConstants.CENTER);
        insertData.setFont(new Font("Thoma", Font.PLAIN, 20));
        insertData.setBounds(134, 34, 187, 32);
        panel_1.add(insertData);

        JLabel strategyText = new JLabel("Strategy for selecting a queue for a client:");
        strategyText.setHorizontalAlignment(SwingConstants.CENTER);
        strategyText.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        strategyText.setBounds(10, 407, 263, 25);
        panel_1.add(strategyText);

        NumberClientsTextField = new JTextField();
        NumberClientsTextField.setHorizontalAlignment(SwingConstants.CENTER);
        NumberClientsTextField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        NumberClientsTextField.setBounds(226, 106, 119, 19);
        panel_1.add(NumberClientsTextField);
        NumberClientsTextField.setColumns(10);

        NumberQueuesTField = new JTextField();
        NumberQueuesTField.setHorizontalAlignment(SwingConstants.CENTER);
        NumberQueuesTField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        NumberQueuesTField.setColumns(10);
        NumberQueuesTField.setBounds(226, 141, 119, 19);
        panel_1.add(NumberQueuesTField);

        SimulationIntervalTField = new JTextField();
        SimulationIntervalTField.setHorizontalAlignment(SwingConstants.CENTER);
        SimulationIntervalTField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        SimulationIntervalTField.setColumns(10);
        SimulationIntervalTField.setBounds(226, 176, 119, 19);
        panel_1.add(SimulationIntervalTField);

        MinArrivalTField = new JTextField();
        MinArrivalTField.setHorizontalAlignment(SwingConstants.CENTER);
        MinArrivalTField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        MinArrivalTField.setColumns(10);
        MinArrivalTField.setBounds(226, 213, 119, 19);
        panel_1.add(MinArrivalTField);

        MaxArrivalTField = new JTextField();
        MaxArrivalTField.setHorizontalAlignment(SwingConstants.CENTER);
        MaxArrivalTField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        MaxArrivalTField.setColumns(10);
        MaxArrivalTField.setBounds(226, 248, 119, 19);
        panel_1.add(MaxArrivalTField);

        MinServiceTField = new JTextField();
        MinServiceTField.setHorizontalAlignment(SwingConstants.CENTER);
        MinServiceTField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        MinServiceTField.setColumns(10);
        MinServiceTField.setBounds(226, 283, 119, 19);
        panel_1.add(MinServiceTField);

        MaxServiceTField = new JTextField();
        MaxServiceTField.setHorizontalAlignment(SwingConstants.CENTER);
        MaxServiceTField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        MaxServiceTField.setColumns(10);
        MaxServiceTField.setBounds(226, 318, 119, 19);
        panel_1.add(MaxServiceTField);

        comboBox = new JComboBox<>();
        comboBox.setBackground(new Color(153, 204, 204));
        comboBox.setModel(new DefaultComboBoxModel<>(new String[]{"TimeStrategy", "ShortestQueueStrategy"}));
        comboBox.setBounds(267, 409, 144, 23);
        panel_1.add(comboBox);

        btnNewButton = new JButton("Start the Simulation");
        btnNewButton.setEnabled(false);
        btnNewButton.setBackground(new Color(204, 204, 204));
        btnNewButton.setBounds(225, 468, 156, 38);
        panel_1.add(btnNewButton);

        JLabel maxClientsideServerTime_1 = new JLabel("Max clients/server:");
        maxClientsideServerTime_1.setHorizontalAlignment(SwingConstants.CENTER);
        maxClientsideServerTime_1.setFont(new Font("Times New Roman", Font.PLAIN, 15));
        maxClientsideServerTime_1.setBounds(60, 349, 156, 25);
        panel_1.add(maxClientsideServerTime_1);

        MaxClientsideServerTFField = new JTextField();
        MaxClientsideServerTFField.setHorizontalAlignment(SwingConstants.CENTER);
        MaxClientsideServerTFField.setFont(new Font("Times New Roman", Font.BOLD, 15));
        MaxClientsideServerTFField.setColumns(10);
        MaxClientsideServerTFField.setBounds(226, 353, 119, 19);
        panel_1.add(MaxClientsideServerTFField);

        btnValidateInputData = new JButton("Validate Input Data");
        btnValidateInputData.setBackground(new Color(204, 204, 204));
        btnValidateInputData.setBounds(35, 468, 156, 38);
        panel_1.add(btnValidateInputData);

        btnStop = new JButton("Stop");
        btnStop.setBackground(new Color(204, 204, 204));
        btnStop.setBounds(310, 516, 71, 31);
        btnStop.setEnabled(false);
        panel_1.add(btnStop);

        this.setVisible(true);
    }

    public String getClients() {
        return NumberClientsTextField.getText();
    }

    public String getQueues() {
        return NumberQueuesTField.getText();
    }

    public String getSimulationInterval() {
        return SimulationIntervalTField.getText();
    }

    public String getMinServiceTime() {
        return MinServiceTField.getText();
    }

    public String getMaxServiceTime() {
        return MaxServiceTField.getText();
    }

    public String getMinArrivalTime() {
        return MinArrivalTField.getText();
    }

    public String getMaxArrivalTime() {
        return MaxArrivalTField.getText();
    }

    public String getMaxClientsPerServer() {
        return MaxClientsideServerTFField.getText();
    }

    public void StartListener(ActionListener start) {
        btnNewButton.addActionListener(start);
    }

    public void ValidateDataListener(ActionListener validate) {
        btnValidateInputData.addActionListener(validate);
    }

    public void StopListener(ActionListener stop) {
        btnStop.addActionListener(stop);
    }

    public void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

    public void setStartVisible(boolean value) {
        btnNewButton.setEnabled(value);
    }

    public void setStopVisible(boolean value) {
        btnStop.setEnabled(value);
    }

    public void setValidateVisible(boolean value) {
        btnValidateInputData.setEnabled(value);
    }

    public void setTime(String time) {
        TimeJLabel_1.setText(time);
    }

    public void setWaitingQueue(String queue) {
        textArea_waitingClients.setText(queue);
    }

    public void setQueueEvolution(String queue) {
        textArea_queueEvolution.setText(queue);
    }

    public void setAverageWaitingTimeJLabel(String avg) {
        averageWaitingTimeLabel.setText(avg);
    }

    public void setAverageServiceTimeJLabel(String avg) {
        averageServiceTimeLabel.setText(avg);
    }

    public void setPeakHourLabel(String peak) {
        PeakHourLabel.setText(peak);
    }

    public Object getComboBox() {
        return comboBox.getSelectedItem();
    }
}
