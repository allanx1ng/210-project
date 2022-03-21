package ui.Buttons;

import ui.Gui;

import javax.swing.*;

public class SellButton extends Button{

    public SellButton(Gui gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    public String getLabel() {
        return "SellButton";
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(getLabel());
        button = customizeButton(button);
    }
}
