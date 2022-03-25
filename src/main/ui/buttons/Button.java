package ui.buttons;

//import ui.Gui;
import ui.GuiTest;

import javax.swing.*;

public abstract class Button {

    protected JButton button;
    protected GuiTest gui;

    //MODIFIES: this
    //EFFECTS: constructs a button with parent in GUI
    public Button(GuiTest gui, JComponent parent) {
        this.gui = gui;
        createButton(parent);
        addToParent(parent);
        addListener(parent);
    }

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: creates button
    protected abstract void createButton(JComponent parent);


    //EFFECTS: Returns the string for the label.
    public abstract String getLabel();

    // MODIFIES: this
    // EFFECTS:  customizes the button
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

    // EFFECTS: adds a listener for this tool
    protected abstract void addListener(JComponent parent);




}
