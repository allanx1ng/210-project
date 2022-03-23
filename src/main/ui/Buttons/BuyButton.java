//package ui.Buttons;
//
//import ui.Gui;
//
//import javax.swing.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//
//public class BuyButton extends Button {
//
//    private String label = "Buy";
//
//    public BuyButton(Gui gui, JComponent parent) {
//        super(gui, parent);
//    }
//
//    @Override
//    public String getLabel() {
//        return label;
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  creates new button and adds to parent
//    @Override
//    protected void createButton(JComponent parent) {
//        button = new JButton("Buy");
//       //button = customizeButton(button);
//    }
//
//
//    @Override
//    protected void addListener(JComponent parent) {
//        button.addActionListener(new ButtonClickHandler());
//    }
//
//
//    public class ButtonClickHandler implements ActionListener {
//
//        // EFFECTS: sets active tool to the shape tool
//        //          called by the framework when the tool is clicked
//        @Override
//        public void actionPerformed(ActionEvent e) {
//            gui.setActiveMenu("Buy");
//        }
//    }
//
//
//}
