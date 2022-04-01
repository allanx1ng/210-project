package ui.buttons.guibuttons;

import ui.buttons.Button;
import ui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DepositUsd extends Button {
    private String label = "Deposit USD";

    //EFFECTS: Constructor for button
    public DepositUsd(Gui gui, JComponent parent) {
        super(gui, parent);
    }

    //EFFECTS: returns the string label
    @Override
    public String getLabel() {
        return label;
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Deposit USD");
        //button = customizeButton(button);
    }


    //EFFECTS: listener for event
    @Override
    protected void addListener(JComponent parent) {
        button.addActionListener(new DepositUsd.ButtonClickHandler());
    }


    public class ButtonClickHandler implements ActionListener {

        // EFFECTS: sets active tool to the shape tool
        //          called by the framework when the tool is clicked
        @Override
        public void actionPerformed(ActionEvent e) {
            gui.performAction(label);
        }
    }
}
