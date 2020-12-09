import javax.swing.*;
import java.awt.*;

public class GrainGrowthFront {
    public static void main(String[] args) {
        setFrameLayout();
    }
    static JFrame frame = new JFrame("Grain Growth Generator");
    static JPanel container = new JPanel();
    static JPanel firstPanel = new JPanel();
    static JPanel showPanel = new JPanel();
    static JLabel matrixSize = new JLabel("Matrix Size:");
    static JLabel xSize = new JLabel("x:");
    static JTextField xText = new JTextField(6);
    static JLabel ySize = new JLabel("y:");
    static JTextField yText = new JTextField(6);
    static JLabel numberOfGrains = new JLabel("Number of grains:");
    static JTextField numberOfGrainsText = new JTextField(6);
    static JLabel type_of_boundaries = new JLabel("Type of boundaries:");
    static JRadioButton periodic = new JRadioButton("periodic");
    static JRadioButton absorbent = new JRadioButton("absorbent");

    static JLabel type_of_inclusion = new JLabel("Type of inclusions:");
    static String[] inclusionTypes = {"None", "Square", "Circle"};
    static JComboBox typeOfInclusionsComboBox = new JComboBox(inclusionTypes);
    static JLabel inclusionSize = new JLabel("Inclusions size:");
    static JTextField inclusionSizeText = new JTextField();
    static JLabel addInclusionAt = new JLabel("Add inclusions:");
    static String[] inclusionInsertType = {"Prior Simulation", " Post simulation"};
    static JComboBox typeOfInclusionsInsertComboBox = new JComboBox(inclusionInsertType);
    static JButton clearAll = new JButton("Clear All");
    static JButton startSimulation = new JButton("Start Simulation");

    static JMenuBar menuBar = new JMenuBar();
    static JMenu menuFile = new JMenu("File");
    static JMenuItem importFile = new JMenuItem("Import File");
    static JMenuItem exportFile = new JMenuItem("Export File");

    public GrainGrowthFront() {

    }

    public static void setFrameLayout() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        menuFile.add(importFile);
        menuFile.add(exportFile);
        menuBar.add(menuFile);
        firstPanel.setLayout(gridBagLayout);
        frame.setJMenuBar(menuBar);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(matrixSize, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(xSize, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(xText, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(ySize, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(yText, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(numberOfGrains, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(numberOfGrainsText, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(type_of_boundaries, gridBagConstraints);

        ButtonGroup radioButtonsGroup = new ButtonGroup();
        radioButtonsGroup.add(periodic);
        radioButtonsGroup.add(absorbent);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(periodic, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 9;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(absorbent, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(type_of_inclusion, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(typeOfInclusionsComboBox, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(inclusionSize, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 5;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 0, 20);
        gridBagConstraints.weightx = 1;
        firstPanel.add(inclusionSizeText, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 6;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(addInclusionAt, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 7;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(typeOfInclusionsInsertComboBox, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 8;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(startSimulation, gridBagConstraints);



        firstPanel.setBackground(Color.white);
        showPanel.setBackground(Color.red);

        frame.add(firstPanel, BorderLayout.PAGE_START);
        frame.add(showPanel,BorderLayout.CENTER);
        frame.getContentPane();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static JButton getClearAll() {
        return clearAll;
    }

    public static JButton getStartSimulation() {
        return startSimulation;
    }

    public static JTextField getxText() {
        return xText;
    }

    public static JTextField getyText() {
        return yText;
    }

    public static JTextField getNumberOfGrainsText() {
        return numberOfGrainsText;
    }

    public static JRadioButton getPeriodic() {
        return periodic;
    }

    public static JRadioButton getAbsorbent() {
        return absorbent;
    }

    public static JComboBox getTypeOfInclusionsComboBox() {
        return typeOfInclusionsComboBox;
    }

    public static JTextField getInclusionSizeText() {
        return inclusionSizeText;
    }


}
