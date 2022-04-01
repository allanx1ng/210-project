package ui.buttons.guibuttons;

import ui.buttons.Button;
import ui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Load extends Button {
    private String label = "Load";

    //EFFECTS: Constructor for button
    public Load(Gui gui, JComponent parent) {
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
        button = new JButton("Load");
        //button = customizeButton(button);
    }


    //EFFECTS: listener for event
    @Override
    protected void addListener(JComponent parent) {
        button.addActionListener(new Load.ButtonClickHandler());
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
