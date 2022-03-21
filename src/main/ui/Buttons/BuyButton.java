package ui.Buttons;

import ui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyButton extends Button{

    public BuyButton(Gui gui, JComponent parent) {
        super(gui, parent);
    }

    @Override
    public String getLabel() {
        return "BuyButton";
    }

    // MODIFIES: this
    // EFFECTS:  creates new button and adds to parent
    @Override
    protected void createButton(JComponent parent) {
        button = new JButton(getLabel());
        button = customizeButton(button);
    }


//    // MODIFIES: this
//    // EFFECTS:  constructs a new listener object which is added to the JButton
//    @Override
//    protected void addListener() {
//        button.addActionListener(new DeleteToolClickHandler());
//    }


//    private class DeleteToolClickHandler implements ActionListener {
//
//        // EFFECTS: sets active tool to the delete tool
//        //          called by the framework when the tool is clicked
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            gui.setActiveTool(BuyButton.this);
//        }
//    }
}
