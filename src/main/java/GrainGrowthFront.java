import javax.swing.*;
import java.awt.*;

public class GrainGrowthFront {
    JFrame frame = new JFrame("Grain Growth Generator");
    JPanel firstPanel = new JPanel();
    static  JPanel showPanel = new JPanel();
    JLabel matrixSize = new JLabel("Matrix Size:");
    JLabel xSize = new JLabel("x:");
    JTextField xText = new JTextField(6);
    JLabel ySize = new JLabel("y:");
    JTextField yText = new JTextField(6);
    JLabel numberOfGrains = new JLabel("Number of grains:");
    JTextField numberOfGrainsText = new JTextField(6);
    JLabel type_of_boundaries = new JLabel("Type of boundaries:");
    JRadioButton periodic = new JRadioButton("periodic");
    JRadioButton absorbent = new JRadioButton("absorbent");
    JLabel type_of_inclusion = new JLabel("Type of inclusions:");
    String[] inclusionTypes = {"None", "Square", "Circle"};
    JComboBox typeOfInclusionsComboBox = new JComboBox(inclusionTypes);
    JLabel inclusionSize = new JLabel("Inclusions size:");
    JTextField inclusionSizeText = new JTextField();
    JLabel addInclusionAt = new JLabel("Add inclusions:");
    String[] inclusionInsertType = {"Prior Simulation", " Post simulation"};
    JComboBox typeOfInclusionsInsertComboBox = new JComboBox(inclusionInsertType);
    JButton clearAll = new JButton("Clear All");
    JButton startSimulation = new JButton("Start Simulation");
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("File");
    JMenuItem importFile = new JMenuItem("Import File");
    JMenuItem exportFile = new JMenuItem("Export File");

    public void setFrameLayout() {
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

       /* ColorGenerator colorGeneratorCanvas = new ColorGenerator();
        colorGeneratorCanvas.setSize(1000, 1000);
        showPanel.add(colorGeneratorCanvas);
*/
        frame.add(firstPanel, BorderLayout.PAGE_START);
        frame.add(showPanel, BorderLayout.CENTER);
        frame.getContentPane();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 1000);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addListeners();
    }

    public void addListeners() {
        startSimulation.addActionListener(new ButtonListeners.StartSimulation());
        clearAll.addActionListener(new ButtonListeners.ClearAll());
    }

    public JButton getClearAll() {
        return clearAll;
    }

    public JButton getStartSimulation() {
        return startSimulation;
    }

    public JTextField getxText() {
        return xText;
    }

    public JTextField getyText() {
        return yText;
    }

    public JTextField getNumberOfGrainsText() {
        return numberOfGrainsText;
    }

    public JRadioButton getPeriodic() {
        return periodic;
    }

    public JRadioButton getAbsorbent() {
        return absorbent;
    }

    public JComboBox getTypeOfInclusionsComboBox() {
        return typeOfInclusionsComboBox;
    }

    public JTextField getInclusionSizeText() {
        return inclusionSizeText;
    }

    public JPanel getShowPanel() { return showPanel; }
}
