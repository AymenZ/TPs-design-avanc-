package main;

import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {
    private Settings settings;
    private Caretaker caretaker;
    
    private JTextField volumeField;
    private JTextField brightnessField;
    private JCheckBox darkModeCheckbox;
    private JLabel statusLabel;
    private DefaultListModel<SettingsMemento> listModel;
    private JList<SettingsMemento> snapshotList;
    
    public GUI() {
        settings = new Settings(50, 50, false);
        caretaker = new Caretaker();
        listModel = new DefaultListModel<>();
        
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Settings - Memento Pattern Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null);
        
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Left panel for settings
        JPanel settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.setBorder(BorderFactory.createTitledBorder("Current Settings"));
        
        // Volume Control
        JPanel volumePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel volumeLabel = new JLabel("Volume (0-100):");
        volumeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        volumeField = new JTextField(String.valueOf(settings.getVolume()), 10);
        volumeField.setFont(new Font("Arial", Font.PLAIN, 14));
        volumePanel.add(volumeLabel);
        volumePanel.add(volumeField);
        
        // Brightness Control
        JPanel brightnessPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JLabel brightnessLabel = new JLabel("Brightness (0-100):");
        brightnessLabel.setFont(new Font("Arial", Font.BOLD, 14));
        brightnessField = new JTextField(String.valueOf(settings.getBrightness()), 10);
        brightnessField.setFont(new Font("Arial", Font.PLAIN, 14));
        brightnessPanel.add(brightnessLabel);
        brightnessPanel.add(brightnessField);
        
        // Dark Mode Control
        JPanel darkModePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        darkModeCheckbox = new JCheckBox("Dark Mode", settings.isDarkMode());
        darkModeCheckbox.setFont(new Font("Arial", Font.BOLD, 14));
        darkModePanel.add(darkModeCheckbox);
        
        // Save button
        JButton saveButton = new JButton("Save Snapshot");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(76, 175, 80));
        saveButton.setForeground(Color.WHITE);
        saveButton.setFocusPainted(false);
        saveButton.addActionListener(e -> saveState());
        
        // Status Label
        statusLabel = new JLabel("Ready");
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setForeground(Color.GRAY);
        
        // Add to settings panel
        settingsPanel.add(volumePanel);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        settingsPanel.add(brightnessPanel);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        settingsPanel.add(darkModePanel);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 15)));
        settingsPanel.add(saveButton);
        settingsPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        settingsPanel.add(statusLabel);
        
        // Right panel for snapshots
        JPanel snapshotPanel = new JPanel(new BorderLayout(5, 5));
        snapshotPanel.setBorder(BorderFactory.createTitledBorder("Saved Snapshots"));
        
        snapshotList = new JList<>(listModel);
        snapshotList.setFont(new Font("Monospaced", Font.PLAIN, 11));
        JScrollPane scrollPane = new JScrollPane(snapshotList);
        scrollPane.setPreferredSize(new Dimension(350, 300));
        
        JPanel snapshotButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton restoreButton = new JButton("Restore Selected");
        restoreButton.setFont(new Font("Arial", Font.BOLD, 12));
        restoreButton.setBackground(new Color(33, 150, 243));
        restoreButton.setForeground(Color.WHITE);
        restoreButton.setFocusPainted(false);
        restoreButton.addActionListener(e -> restoreState());
        
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteButton.setBackground(new Color(244, 67, 54));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.addActionListener(e -> deleteSnapshot());
        
        snapshotButtonPanel.add(restoreButton);
        snapshotButtonPanel.add(deleteButton);
        
        snapshotPanel.add(scrollPane, BorderLayout.CENTER);
        snapshotPanel.add(snapshotButtonPanel, BorderLayout.SOUTH);
        
        // Add panels to main panel
        mainPanel.add(settingsPanel, BorderLayout.WEST);
        mainPanel.add(snapshotPanel, BorderLayout.CENTER);
        
        add(mainPanel);
    }
    
    private void saveState() {
        try {
            int volume = Integer.parseInt(volumeField.getText());
            int brightness = Integer.parseInt(brightnessField.getText());
            
            if (volume < 0 || volume > 100) {
                updateStatus("Error: Volume must be between 0 and 100");
                return;
            }
            if (brightness < 0 || brightness > 100) {
                updateStatus("Error: Brightness must be between 0 and 100");
                return;
            }
            
            settings.setVolume(volume);
            settings.setBrightness(brightness);
            settings.setDarkMode(darkModeCheckbox.isSelected());
            
            caretaker.saveState(settings);
            updateSnapshotList();
            updateStatus("Snapshot saved! (Volume: " + settings.getVolume() + 
                        ", Brightness: " + settings.getBrightness() + 
                        ", Dark Mode: " + settings.isDarkMode() + ")");
        } catch (NumberFormatException e) {
            updateStatus("Error: Please enter valid numbers for volume and brightness");
        }
    }
    
    private void restoreState() {
        SettingsMemento selected = snapshotList.getSelectedValue();
        if (selected == null) {
            updateStatus("Error: Please select a snapshot to restore");
            return;
        }
        
        caretaker.restoreState(settings, selected.getId());
        updateUI();
        updateStatus("Snapshot #" + selected.getId() + " restored! (Volume: " + settings.getVolume() + 
                    ", Brightness: " + settings.getBrightness() + 
                    ", Dark Mode: " + settings.isDarkMode() + ")");
    }
    
    private void deleteSnapshot() {
        SettingsMemento selected = snapshotList.getSelectedValue();
        if (selected == null) {
            updateStatus("Error: Please select a snapshot to delete");
            return;
        }
        
        caretaker.deleteMemento(selected.getId());
        updateSnapshotList();
        updateStatus("Snapshot #" + selected.getId() + " deleted");
    }
    
    private void updateSnapshotList() {
        listModel.clear();
        for (SettingsMemento memento : caretaker.getAllMementos()) {
            listModel.addElement(memento);
        }
    }
    
    private void updateUI() {
        volumeField.setText(String.valueOf(settings.getVolume()));
        brightnessField.setText(String.valueOf(settings.getBrightness()));
        darkModeCheckbox.setSelected(settings.isDarkMode());
    }
    
    private void updateStatus(String message) {
        statusLabel.setText(message);
    }
}
