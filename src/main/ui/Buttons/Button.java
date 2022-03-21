package ui.Buttons;

import ui.Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public abstract class Button {

    protected JButton button;
    protected Gui gui;

    public Button(Gui gui, JComponent parent) {
        this.gui = gui;
        createButton(parent);
        addToParent(parent);
       // addListener();
    }

    // MODIFIES: parent
    // EFFECTS:  adds the given button to the parent component
    public void addToParent(JComponent parent) {
        parent.add(button);
    }

    // EFFECTS: creates button to activate tool
    protected abstract void createButton(JComponent parent);

    // EFFECTS: default behaviour does nothing
    public void mouseClickedInDrawingArea(MouseEvent e) {}

    //EFFECTS: Returns the string for the label.
    protected abstract String getLabel();

    // MODIFIES: this
    // EFFECTS:  customizes the button used for this tool
    protected JButton customizeButton(JButton button) {
        button.setBorderPainted(true);
        button.setFocusPainted(true);
        button.setContentAreaFilled(true);
        return button;
    }

//    // EFFECTS: adds a listener for this tool
//    protected void addListener() {
//        button.addActionListener(new ButtonClickHandler());
//    }


//    private class ButtonClickHandler implements ActionListener {
//
//        // EFFECTS: sets active tool to the shape tool
//        //          called by the framework when the tool is clicked
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            gui.performAction(this);
//        }
//    }




}
