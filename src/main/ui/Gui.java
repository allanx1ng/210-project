//package ui;
//
//import model.Coin;
//import model.Portfolio;
//import persistence.JsonReader;
//import persistence.JsonWriter;
//import ui.Buttons.*;
//import ui.Buttons.Button;
//import ui.Buttons.BuyMenu.BuyBtcButton;
//import ui.Buttons.BuyMenu.BuyEthButton;
//
//import javax.swing.*;
//import java.awt.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//public class Gui extends JFrame {
//
//    public static final int WIDTH = 800;
//    public static final int HEIGHT = 800;
//
//    private JPanel buttonArea;
//    private JPanel buyMenu;
//    private JPanel buyCoinMenu;
//
//    private JTextField textField;
//
//    private JFrame frame = new JFrame();
//    private JLayeredPane layeredPane;
//
//    private List<Button> buttons;
//
//    private static Portfolio portfolio;
//    private static Coin btc;
//    private static Coin eth;
//    private static Coin usd;
//
//    private static final String JSON_STORE = "./data/datastore.json";
//    private Scanner input;
//    private JsonWriter jsonWriter;
//    private JsonReader jsonReader;
//
//    // EFFECTS: initiates the different coins that the user can buy
//    private void setCoins() {
//
//        portfolio = new Portfolio();
//        jsonWriter = new JsonWriter(JSON_STORE);
//        jsonReader = new JsonReader(JSON_STORE);
//
//        btc = new Coin("Bitcoin", 60000, 0);
//        eth = new Coin("Ethereum", 4000, 0);
//        usd = new Coin("USD", 1, 0);
//
//       // readData();
//    }
//
//
//    public static void main(String[] args) {
//        new Gui();
//    }
//
//    public Gui() {
//
//        setCoins();
//        initializeGraphics();
//
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  draws the JFrame window where this DrawingEditor will operate, and populates the tools to be used
//    //           to manipulate this drawing
//    private void initializeGraphics() {
//
//        buttons = new ArrayList<Button>();
//
//        frame.setLayout(new GridLayout(0,1));
//        frame.setMinimumSize(new Dimension(WIDTH, HEIGHT));
//
//        layeredPane = new JLayeredPane();
//        layeredPane.setLayout(new GridLayout(0,1));
//
//        frame.add(layeredPane);
//
//        createButtons();
//        buyMenu();
//        buyCoinMenu();
//        System.out.println("lolxd");
//
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);
//        layeredPane.setVisible(true);
//    }
//
//    private void buyCoinMenu() {
//        buyCoinMenu = new JPanel();
//        JLabel buyCoinLabel = new JLabel("How much would you like to buy?");
//        buyCoinMenu.setBounds(0,0,0,0);
//        buyCoinMenu.setSize(new Dimension(0, 0));
//        layeredPane.add(buyCoinMenu);
//
//        layeredPane.add(buyCoinLabel);
//
//        Button buyBtcButton = new BuyBtcButton(this, buyCoinMenu);
//        buttons.add(buyBtcButton);
//
//        Button buyEthButton = new BuyEthButton(this, buyCoinMenu);
//        buttons.add(buyEthButton);
//
//        buyCoinMenu.setVisible(false);
//    }
//
//    private void buyMenu() {
//                //CreateBuyButtons
//        buyMenu = new JPanel();
//        buyMenu.setBounds(0,0,0,0);
//        buyMenu.setSize(new Dimension(0, 0));
//        layeredPane.add(buyMenu);
//
//        Button buyBtcButton = new BuyBtcButton(this, buyMenu);
//        buttons.add(buyBtcButton);
//
//        Button buyEthButton = new BuyEthButton(this, buyMenu);
//        buttons.add(buyEthButton);
//
//        buyMenu.setVisible(false);
//
//
//    }
//
//    // MODIFIES: this
//    // EFFECTS:  a helper method which declares and instantiates all tools
//    private void createButtons() {
//        buttonArea = new JPanel();
//        buttonArea.setBounds(0,0,0,0);
//        buttonArea.setSize(new Dimension(0, 0));
//        layeredPane.add(buttonArea);
//
//        Button buyButton = new BuyButton(this, buttonArea);
//        buttons.add(buyButton);
//
//        Button sellButton = new SellButton(this, buttonArea);
//        buttons.add(sellButton);
//
//        Button exitButton = new ExitButton(this, buttonArea);
//        buttons.add(exitButton);
//
//
//
//    }
//
//
//
//    // MODIFIES: this
//    // EFFECTS:  sets the given tool as the activeTool
//    public void setActiveMenu(String s) {
//        System.out.println("deez nuts" + s);
//        if (s == "Buy") {
//            buttonArea.setVisible(false);
//            buyMenu.setVisible(true);
//
//        } else if (s == "Sell") {
//            buttonArea.setVisible(false);
//        } else if (s == "Exit") {
//            System.out.println("exiting");
//        } else if (s == "BuyBTC") {
//            buttonArea.setVisible(true);
//            buyMenu.setVisible(false);
//        } else {
//            System.out.println("invalid");
//        }
//
//    }
//
//    private void clearButtons() {
//
//    }
//
//
////    // MODIFIES: this
////    // EFFECTS:  initializes a DrawingMouseListener to be used in the JFrame
////    private void initializeInteraction() {
////        DrawingMouseListener dml = new DrawingMouseListener();
////        addMouseListener(dml);
////        addMouseMotionListener(dml);
////    }
////
////
////    // EFFECTS: if activeTool != null, then mousePressedInDrawingArea is invoked on activeTool, depends on the
////    //          type of the tool which is currently activeTool
////    private void handleMousePressed(MouseEvent e)  {
////        if (activeTool != null)
////            activeTool.mousePressedInDrawingArea(e);
////        repaint();
////    }
////
////    private class DrawingMouseListener extends MouseAdapter {
////
////        // EFFECTS: Forward mouse pressed event to the active tool
////        public void mousePressed(MouseEvent e) {
////            handleMousePressed(translateEvent(e));
////        }
////
////        // EFFECTS: Forward mouse released event to the active tool
////        public void mouseReleased(MouseEvent e) {
////            handleMouseReleased(translateEvent(e));
////        }
////
////        // EFFECTS:Forward mouse clicked event to the active tool
////        public void mouseClicked(MouseEvent e) {
////            handleMouseClicked(translateEvent(e));
////        }
////
////        // EFFECTS:Forward mouse dragged event to the active tool
////        public void mouseDragged(MouseEvent e) {
////            handleMouseDragged(translateEvent(e));
////        }
////
////        // EFFECTS: translates the mouse event to current drawing's coordinate system
////        private MouseEvent translateEvent(MouseEvent e) {
////            return SwingUtilities.convertMouseEvent(e.getComponent(), e, currentDrawing);
////        }
////    }
//
//
//}
//
