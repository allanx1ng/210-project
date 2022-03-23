package ui.Buttons.GuiTestButtons;

import ui.Buttons.Button;
import ui.GuiTest;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WithdrawUsd extends Button {
    private String label = "Withdraw USD";

    public WithdrawUsd(GuiTest gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    public String getLabel() {
        return label;
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton("Withdraw USD");
        //button = customizeButton(button);
    }


    @Override
    protected void addListener(JComponent parent) {
        button.addActionListener(new WithdrawUsd.ButtonClickHandler());
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
