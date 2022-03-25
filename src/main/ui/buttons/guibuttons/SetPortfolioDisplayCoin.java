package ui.buttons.guibuttons;

import ui.buttons.Button;
import ui.GuiTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SetPortfolioDisplayCoin extends Button {
    private String label = "SetPortfolioDisplayCoin";

    //EFFECTS: Constructor for button
    public SetPortfolioDisplayCoin(GuiTest gui, JComponent parent) {
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
        button = new JButton("SetPortfolioDisplayCoin");
        //button = customizeButton(button);
    }


    //EFFECTS: listener for event
    @Override
    protected void addListener(JComponent parent) {
        button.addActionListener(new SetPortfolioDisplayCoin.ButtonClickHandler());
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
