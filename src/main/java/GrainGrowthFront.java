import javax.swing.*;
import java.awt.*;

public class GrainGrowthFront {
    private static GrainGrowthFront instance;

    JFrame frame = new JFrame("Grain Growth Generator");
    JPanel firstPanel = new JPanel();
    static JPanel showPanel = new JPanel();
    JLabel matrixSizeX = new JLabel("Matrix Size x: ");
    JSlider xSizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 800, 400);
    JLabel matrixSizeY = new JLabel("Matrix Size y: ");
    JSlider ySizeSlider = new JSlider(JSlider.HORIZONTAL, 0, 800, 400);
    JLabel numberOfGrains = new JLabel("Number of grains:");
    static JTextField numberOfGrainsText = new JTextField(6);
    JLabel type_of_boundaries = new JLabel("Boundaries type: ");
    JRadioButton periodic = new JRadioButton("periodic");
    JRadioButton absorbent = new JRadioButton("absorbent");
    JLabel type_of_inclusion = new JLabel("Type of inclusions:");
    String[] inclusionTypes = {"", "Square", "Circle"};
    JComboBox typeOfInclusionsComboBox = new JComboBox(inclusionTypes);
    JLabel inclusionSize = new JLabel("Inclusions size:");
    JTextField inclusionSizeText = new JTextField();
    JLabel inclusionNumber = new JLabel("Number of Inclusions:");
    JTextField inclusionsNumberText = new JTextField();
    JLabel addInclusionAt = new JLabel("Add inclusions:");
    String[] inclusionInsertTime = {"Prior Simulation", "Post simulation"};
    JComboBox timeOfInclusionsInsertComboBox = new JComboBox(inclusionInsertTime);
    JButton clearAll = new JButton("Clear All");
    JButton startSimulation = new JButton("Start Simulation");
    JButton stopSimulation = new JButton("Stop Simulation");
    JMenuBar menuBar = new JMenuBar();
    JMenu menuFile = new JMenu("File");
    JMenu importFile = new JMenu("Import File");
    JMenu exportFile = new JMenu("Export File");
    JMenuItem txtFile = new JMenuItem(".txt");
    JMenuItem pngFile = new JMenuItem(".bmp");
    JLabel boundariesLabel = new JLabel("Grains boundaries parameters:");
    JLabel borderThicknessLabel = new JLabel("Border thickness:");
    JSlider borderThicknessSlider = new JSlider(JSlider.HORIZONTAL, 0, 15, 5);
    JButton addBordersButton = new JButton("Add Borders");
    JButton clearSpaceButton = new JButton("Clear Space");


    public void setFrameLayout() {
        GridBagLayout gridBagLayout = new GridBagLayout();
        GridBagConstraints gridBagConstraints = new GridBagConstraints();
        //typeOfInclusionsComboBox.setSelectedIndex(0);
        //timeOfInclusionsInsertComboBox.setSelectedIndex(0);

        exportFile.add(txtFile);
        exportFile.add(pngFile);
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
        firstPanel.add(matrixSizeX, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        xSizeSlider.setMajorTickSpacing(200);
        xSizeSlider.setPaintLabels(true);
        firstPanel.add(xSizeSlider, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(matrixSizeY, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        ySizeSlider.setMajorTickSpacing(200);
        ySizeSlider.setPaintLabels(true);
        firstPanel.add(ySizeSlider, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(numberOfGrains, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(numberOfGrainsText, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(type_of_boundaries, gridBagConstraints);

        ButtonGroup radioButtonsGroup = new ButtonGroup();
        radioButtonsGroup.add(periodic);
        radioButtonsGroup.add(absorbent);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(periodic, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new Insets(10, 150, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(absorbent, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(type_of_inclusion, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 4;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(typeOfInclusionsComboBox, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(inclusionSize, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 5;
        gridBagConstraints.insets = new Insets(10, 10, 0, 20);
        gridBagConstraints.weightx = 1;
        firstPanel.add(inclusionSizeText, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(inclusionNumber, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 6;
        gridBagConstraints.insets = new Insets(10, 10, 0, 20);
        gridBagConstraints.weightx = 1;
        firstPanel.add(inclusionsNumberText, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(addInclusionAt, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 7;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(timeOfInclusionsInsertComboBox, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 1;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new Insets(10, 0, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(stopSimulation, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 8;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(startSimulation, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 9;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(boundariesLabel, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(borderThicknessLabel, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 10;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        borderThicknessSlider.setMajorTickSpacing(5);
        borderThicknessSlider.setPaintLabels(true);
        firstPanel.add(borderThicknessSlider, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(addBordersButton, gridBagConstraints);

        gridBagConstraints.fill = GridBagConstraints.HORIZONTAL;
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 11;
        gridBagConstraints.insets = new Insets(10, 10, 0, 0);
        gridBagConstraints.weightx = 1;
        firstPanel.add(clearSpaceButton, gridBagConstraints);

        firstPanel.setBackground(Color.white);
        firstPanel.setBorder(BorderFactory.createEtchedBorder());
        showPanel.setBackground(Color.white);

        frame.add(firstPanel, BorderLayout.WEST);
        frame.add(showPanel, BorderLayout.CENTER);
        frame.getContentPane();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);
        frame.setResizable(true);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addListeners();
    }

    public void addListeners() {
        startSimulation.addActionListener(new ButtonListeners.StartSimulation());
        txtFile.addActionListener(new ButtonListeners.SaveToTxt());
        pngFile.addActionListener(new ButtonListeners.SaveToBmp());
        typeOfInclusionsComboBox.addActionListener(new ButtonListeners.DisableInclusions());
        clearSpaceButton.addActionListener(new ButtonListeners.ClearSpace());
        addBordersButton.addActionListener(new ButtonListeners.AddBorders());

    }

    public static GrainGrowthFront getInstance() {
        if (instance == null) instance = new GrainGrowthFront();
        return instance;
    }

    public JButton getClearAll() {
        return clearAll;
    }

    public JButton getStartSimulation() {
        return startSimulation;
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

    public JComboBox getTimeOfInclusionsInsertComboBox() {
        return timeOfInclusionsInsertComboBox;
    }

    public JTextField getInclusionsNumberText() {
        return inclusionsNumberText;
    }

    public JPanel getShowPanel() {
        return showPanel;
    }

    public JSlider getxSizeSlider() {
        return xSizeSlider;
    }

    public JSlider getySizeSlider() {
        return ySizeSlider;
    }

    public JSlider getBorderThicknessSlider() {
        return borderThicknessSlider;
    } public JButton getAddBordersButton() {
        return addBordersButton;
    }
    public void setBorderThicknessSlider(JSlider borderThicknessSlider) {
        this.borderThicknessSlider = borderThicknessSlider;
    }
}
