package ui.Buttons;

import ui.Gui;

import javax.swing.*;

public class ExitButton extends Button{
    public ExitButton(Gui gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    public String getLabel() {
        return "ExitButton";
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(getLabel());
        button = customizeButton(button);
    }
}
