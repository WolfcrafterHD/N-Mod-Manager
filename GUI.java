/*
    Lupinus
    orig. 01.11.2026
    This Script Manages all Buttons etc. and their Layout. It is also the Listener for them.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class GUI implements ActionListener {
    /***Attributes***/
    public static GUI INSTANCE;

    //PanelMain
    private JButton switchButton;
    private JComboBox profileDropdownComboBox; public JComboBox GetProfileDropdownComboBox() {return profileDropdownComboBox;}
    private JTextField folderNameTextField;
    private JButton addButton;
    private JButton openModManagerFolderButton;
    private JButton vanillaButton;
    private JButton settingsButton;

    //PanelSettings
    private JTextField modManagerPathTextField; public JTextField GetModManagerPathTextField() {return  modManagerPathTextField;}
    private JTextField mcModPathTextField; public JTextField GetMcModPathTextField() {return mcModPathTextField;}
    private JTextField backgroundPathTextField; public JTextField GetBackgroundPathTextField() {return backgroundPathTextField;}
    private JButton backButton;

    /***Constructor***/
    GUI() {
        if (INSTANCE == null) {
            INSTANCE = this;
        }
    }

    /***Public***/
    public void Instantiate() {
        JPanel currentPanel = Display.INSTANCE.GetPanelMain();
        currentPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // --- ComboBox (Row 1, Column 1, Spans 2 Columns) ---
        profileDropdownComboBox = new JComboBox<FolderNamePathPair>();
        DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel<>();
        profileDropdownComboBox.setModel(defaultComboBoxModel1);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(profileDropdownComboBox, gbc);

        // --- Switch Button (Row 1, Column 3) ---
        switchButton = new JButton();
        switchButton.setText("Switch");
        switchButton.setToolTipText("Open the settings menu");
        gbc.gridx = 3;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(switchButton, gbc);

        // --- TextField (Row 2, Column 1, Spans 2 Columns) ---
        folderNameTextField = new JTextField();
        folderNameTextField.setPreferredSize(new Dimension(150, folderNameTextField.getPreferredSize().height));
        folderNameTextField.setToolTipText("Enter the name of a new profile");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(folderNameTextField, gbc);

        // --- Add Button (Row 2, Column 3) ---
        addButton = new JButton();
        addButton.setText("Add");
        addButton.setToolTipText("Creates a new folder with the name you entered");
        gbc.gridx = 3;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(addButton, gbc);

        // --- Open Mod Manager Folder Button (Row 3, Column 1, Spans 2 Columns) ---
        openModManagerFolderButton = new JButton();
        openModManagerFolderButton.setText("Open Mod Manager Folder");
        openModManagerFolderButton.setToolTipText("Opens the mod manager folder, what did you expect?");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(openModManagerFolderButton, gbc);

        // --- Vanilla Button (Row 3, Column 3) ---
        vanillaButton = new JButton();
        vanillaButton.setText("Vanilla");
        vanillaButton.setToolTipText("Removes all mods from the mc mod folder!");
        gbc.gridx = 3;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(vanillaButton, gbc);

        // --- Settings Button (Row 4, Column 1) ---
        settingsButton = new JButton();
        settingsButton.setText("Settings");
        settingsButton.setToolTipText("Opens the Settings Menu");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(settingsButton, gbc);

        // Top Vertical Spacer (Row 0, Column 1) - Pushes UI down
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        currentPanel.add(Box.createGlue(), gbc);

        // Bottom Vertical Spacer (Row 5, Column 1) - Pushes UI up
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        currentPanel.add(Box.createGlue(), gbc);

        // Left Horizontal Spacer (Row 2, Column 0) - Pushes UI right
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0; // Reset vertical weight
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(Box.createGlue(), gbc);

        // Right Horizontal Spacer (Row 2, Column 4) - Pushes UI left
        gbc.gridx = 4;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(Box.createGlue(), gbc);

        currentPanel = Display.INSTANCE.GetPanelSettings();
        currentPanel.setLayout(new GridBagLayout());
        gbc = new GridBagConstraints();

        // --- Mod Manager Path TextField (Row 1, Column 1) ---
        modManagerPathTextField = new JTextField();
        modManagerPathTextField.setText("");
        modManagerPathTextField.setToolTipText("Path to the folder with you mod profiles");
        modManagerPathTextField.setPreferredSize(new Dimension(150, modManagerPathTextField.getPreferredSize().height));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Corresponds to SIZEPOLICY_WANT_GROW
        gbc.weighty = 0.0;
        currentPanel.add(modManagerPathTextField, gbc);

        // --- Minecraft Mod Path TextField (Row 2, Column 1) ---
        mcModPathTextField = new JTextField();
        mcModPathTextField.setText("");
        mcModPathTextField.setToolTipText("Path to the MC mod folder");
        mcModPathTextField.setPreferredSize(new Dimension(150, mcModPathTextField.getPreferredSize().height));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        currentPanel.add(mcModPathTextField, gbc);

        // --- Background Path TextField (Row 3, Column 1) ---
        backgroundPathTextField = new JTextField();
        backgroundPathTextField.setText("");
        backgroundPathTextField.setToolTipText("Enter the path to a background image");
        backgroundPathTextField.setPreferredSize(new Dimension(150, backgroundPathTextField.getPreferredSize().height));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        currentPanel.add(backgroundPathTextField, gbc);

        // --- Back Button (Row 4, Column 1) ---
        backButton = new JButton();
        backButton.setText("Back");
        backButton.setToolTipText("Return to main menu");
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 0.0; // Buttons don't need to aggressively expand layout weights
        gbc.weighty = 0.0;
        currentPanel.add(backButton, gbc);

        // Top Vertical Spacer (Row 0, Column 1) - Pushes content down
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0; // Absorbs vertical layout space
        gbc.fill = GridBagConstraints.VERTICAL;
        currentPanel.add(Box.createGlue(), gbc);

        // Bottom Vertical Spacer (Row 5, Column 1) - Pushes content up
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.VERTICAL;
        currentPanel.add(Box.createGlue(), gbc);

        // Left Horizontal Spacer (Row 2, Column 0) - Centers content horizontally
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0; // Absorbs horizontal layout space
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(Box.createGlue(), gbc);

        // Right Horizontal Spacer (Row 2, Column 2) - Centers content horizontally
        gbc.gridx = 2;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        currentPanel.add(Box.createGlue(), gbc);

        //PanelMain
        switchButton.addActionListener(this);
        profileDropdownComboBox.addActionListener(this);
        folderNameTextField.addActionListener(this);
        addButton.addActionListener(this);
        openModManagerFolderButton.addActionListener(this);
        vanillaButton.addActionListener(this);
        settingsButton.addActionListener(this);

        //PanelSettings
        modManagerPathTextField.addActionListener(this);
        mcModPathTextField.addActionListener(this);
        backgroundPathTextField.addActionListener(this);
        backButton.addActionListener(this);
    }

    /***Overrides***/
    @Override
    public void actionPerformed(ActionEvent e) {
        //PanelMain
        if (e.getSource() == settingsButton) {
            Display.INSTANCE.SwitchPanel(Display.INSTANCE.GetFrameMain(), Display.INSTANCE.GetPanelSettings());
        }
        if (e.getSource() == openModManagerFolderButton) {
            Control.INSTANCE.OpenFolder(Control.INSTANCE.GetModProfilePath());
        }
        if (e.getSource() == vanillaButton) {
            Control.INSTANCE.ClearMcModFolder();
        }
        if (e.getSource() == addButton) {
            Control.INSTANCE.CreateFolder(folderNameTextField.getText());
            Control.INSTANCE.UpdateDropdown();
        }
        if (e.getSource() == switchButton) {
            Control.INSTANCE.SwitchModProfile();
        }

        //PanelSettings
        if (e.getSource() == backButton) {
            Display.INSTANCE.SwitchPanel(Display.INSTANCE.GetFrameMain(), Display.INSTANCE.GetPanelMain());
        }
        if (e.getSource() == backgroundPathTextField) {
            Control.INSTANCE.LoadImage(backgroundPathTextField.getText());
        }
        if (e.getSource() == mcModPathTextField) {
            Control.INSTANCE.SetMcModPath(new File(mcModPathTextField.getText()));
            Control.INSTANCE.FileSavingProtocol();
        }
        if (e.getSource() == modManagerPathTextField) {
            Control.INSTANCE.SetModProfilePath(new File(modManagerPathTextField.getText()));
            Control.INSTANCE.FileSavingProtocol();
        }
    }


}
