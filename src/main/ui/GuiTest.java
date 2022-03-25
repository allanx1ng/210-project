package ui;

import model.Coin;
import model.Portfolio;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.buttons.Button;
import ui.buttons.guibuttons.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


// certain elements in the code were taken from the given projects this term and official java sources
public class GuiTest extends JPanel {

    private ImageIcon img;
    private JLabel imgLabel;

    private BuyBtc buyBtc;
    private BuyEth buyEth;
    private SellBtc sellBtc;
    private SellEth sellEth;
    private DepositUsd depositUSD;
    private WithdrawUsd withdrawUsd;
    private SetPortfolioDisplayCoin setPortfolioDisplayCoin;
    private SetPortfolioDisplayUsd setPortfolioDisplayUsd;
    private Load load;
    private ExitProgram exit;

    private boolean alreadyLoaded = false;

    private JTextField buyBtcField;
    private JTextField sellBtcField;
    private JTextField buyEthField;
    private JTextField sellEthField;
    private JTextField depositUsdField;
    private JTextField withdrawUsdField;

    private JLabel buyBtcLabel;
    private JLabel sellBtcLabel;
    private JLabel buyEthLabel;
    private JLabel sellEthLabel;
    private JLabel depositUsdLabel;
    private JLabel withdrawUsdLabel;

    private JLabel portfolioValue;

    private JFrame frame;
    private JPanel panel;

    private List<JComponent> itemList;

    private static Portfolio portfolio;
    private static Coin btc;
    private static Coin eth;
    private static Coin usd;

    private static final String JSON_STORE = "./data/datastore.json";
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: initiates the different coins that the user can buy
    private void setCoins() {

        portfolio = new Portfolio();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        btc = new Coin("Bitcoin", 60000, 0);
        eth = new Coin("Ethereum", 4000, 0);
        usd = new Coin("USD", 1, 0);

    }

    //EFFECTS: loads data from JSON file
    private void readData() {
        if (!alreadyLoaded) {
            try {
                if (jsonReader.findCoin("Bitcoin")) {
                    btc.addCoin(jsonReader.getAmount("Bitcoin"));
                    portfolio.addCoinToList(btc);
                    System.out.println("BTC ADDED");
                }

                if (jsonReader.findCoin("Ethereum")) {
                    eth.addCoin(jsonReader.getAmount("Ethereum"));
                    portfolio.addCoinToList(eth);
                    System.out.println("ETH ADDED");
                }

                if (jsonReader.findCoin("USD")) {
                    usd.addCoin(jsonReader.getAmount("USD"));
                    portfolio.addCoinToList(usd);
                    System.out.println("USD ADDED");
                }

            } catch (IOException e) {
                System.out.println("Couldnt find coin");
            }

            alreadyLoaded = true;
            updatePortfolioCoin();
        }
    }


    // EFFECTS main method
    public static void main(String[] args) {
        new GuiTest();
    }

    //EFFECTS: constructor for the class
    public GuiTest() {

        setCoins();
        initializeGraphics();

    }

    //MODIFIES: THIS
    //EFFECTS: constructs jframe and initialize graphics
    private void initializeGraphics() {

        //Create and set up the window.
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));


        portfolioValue = new JLabel("BTC: " + btc.getAmountHeld() + " ETH: " + eth.getAmountHeld()
                + " USD: " + usd.getAmountHeld());

        Image image = null;
        
        try {
            image = ImageIO.read(new File("./data/BTC.png"));
            image = image.getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        img = new ImageIcon(image);
       // img = new ImageIcon("./data/BTC.png");

        imgLabel = new JLabel(img);
        imgLabel.setSize(100,100);
        panel.add(imgLabel);
        createButtons();
        addToList();
        addToPanel();


        frame.add(panel, BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    //EFFECTS: constructs the buttons and labels
    private void createButtons() {

        buyBtcLabel = new JLabel("Enter amount of BTC to buy");
        buyBtcField = new JTextField();
        buyBtc = new BuyBtc(this, panel);

        buyEthLabel = new JLabel("Enter amount of ETH to buy");
        buyEthField = new JTextField();
        buyEth = new BuyEth(this,panel);

        sellBtcLabel = new JLabel("Enter amount of BTC to sell");
        sellBtcField = new JTextField();
        sellBtc = new SellBtc(this,panel);

        sellEthLabel = new JLabel("Enter amount of ETH to sell");
        sellEthField = new JTextField();
        sellEth = new SellEth(this,panel);

        depositUsdLabel = new JLabel("Enter amount of USD to deposit");
        depositUsdField = new JTextField();
        depositUSD = new DepositUsd(this,panel);

        withdrawUsdLabel = new JLabel("Enter amount of USD to withdraw");
        withdrawUsdField = new JTextField();
        withdrawUsd = new WithdrawUsd(this,panel);

        load = new Load(this,panel);

      //  setPortfolioDisplayCoin = new SetPortfolioDisplayCoin(this,panel);

      //  setPortfolioDisplayUsd = new SetPortfolioDisplayUsd(this,panel);

        exit = new ExitProgram(this,panel);



    }

    // MODIFIES: this
    // EFFECTS: adds labels and textboxes to panel
    private void addToPanel() {
        for (JComponent i : itemList) {
            panel.add(i);
        }
    }

    //MODIFIES: THIS
    //EFFECTS: adds labels and textboxes to a list
    private void addToList() {

        itemList = new ArrayList<>();

        itemList.add(portfolioValue);
        itemList.add(buyBtcLabel);
        itemList.add(buyBtcField);
        //itemList.add(buyBtc);
        itemList.add(buyEthLabel);
        itemList.add(buyEthField);
        //itemList.add(buyEth);
        itemList.add(sellBtcLabel);
        itemList.add(sellBtcField);
        //itemList.add(sellBtc);
        itemList.add(sellEthLabel);
        itemList.add(sellEthField);
        //itemList.add(sellEth);

        itemList.add(depositUsdLabel);
        itemList.add(depositUsdField);
        //itemList.add(depositUSD);
        itemList.add(withdrawUsdLabel);
        itemList.add(withdrawUsdField);
        //itemList.add(withdrawUsd);
        //itemList.add(setPortfolioCoin);
        //itemList.add(getSetPortfolioUsd);
        //itemList.add(exit);


    }

    //EFFECTS: performs actions based on which button is pressed
    public void performAction(String s) {
        if (s.equals("Buy BTC")) {
            buy("BTC");

        } else if (s.equals("Buy ETH")) {
            buy("ETH");

        } else if (s.equals("Sell BTC")) {
            sell("BTC");

        } else if (s.equals("Sell ETH")) {
            sell("ETH");

        } else if (s.equals("Load")) {
            readData();

        } else if (s.equals("Save and Exit")) {
            initiateExit();

        }  else if (s.equals("Withdraw USD")) {
            executeWithdraw();

        }  else if (s.equals("Deposit USD")) {
            addMoney();

        } else {
            System.out.println("invalid");
        }
    }

    // EFFECTS: buys the coin with name
    public void buy(String name) {
        try {
            if (name.equals("BTC")) {
                int a = Integer.parseInt(buyBtcField.getText());
                executeBuy("Bitcoin", btc, a);
            } else if (name.equals("ETH")) {
                int a = Integer.parseInt(buyEthField.getText());
                executeBuy("Ethereum", eth, a);
            } else {
                System.out.println("Please enter a valid input");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid input");
        }
    }

    // EFFECTS: Sells the coin with name
    public void sell(String name) {
        try {
            if (name.equals("BTC")) {
                int a = Integer.parseInt(sellBtcField.getText());
                executeSell("Bitcoin", btc, a);
            } else if (name.equals("ETH")) {
                int a = Integer.parseInt(sellEthField.getText());
                executeSell("Ethereum", eth, a);
            } else {
                System.out.println("Please enter a valid input");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid input");
        }
    }

    // EFFECTS: exits the program
    private void initiateExit() {
        try {
            jsonWriter.open();
            jsonWriter.write(portfolio);
            jsonWriter.close();
            System.out.println("Saved portfolio to " + JSON_STORE);
            System.exit(0);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
        System.exit(1);
    }

    // REQUIRES: valid name of a coin, the coin itself, integer amount >=0
    // MODIFIES: portfolio, coin
    // EFFECTS: if the amount of USD is sufficient to buy amount*selected coin, amount* selected coin is added into
    // the user's portfolio and the equivalent value of USD is subtracted. If the user doesnt have enough USD,
    // they are told that there is insufficient USD for the transaction.
    public void executeBuy(String name, Coin c, int amount) {
        if (amount >= 0) {

            if (portfolio.getAmountOfCoinHeld("USD") >= amount * c.getPrice()) {
                if (!portfolio.containsCoin(name)) {
                    portfolio.addCoinToList(c);
                }
                portfolio.addCoins(name, amount);
                portfolio.addCoins("USD",- amount * c.getPrice());
            } else {
                System.out.println("Not enough money");
            }
        } else {
            System.out.println("Please enter a valid input");
        }
        updatePortfolioCoin();
    }

    //EFFECTS: updates the textbox to display how many items are in portfolio
    private void updatePortfolioCoin() {
        portfolioValue.setText("BTC: " + btc.getAmountHeld() + " ETH: " + eth.getAmountHeld()
                + " USD: " + usd.getAmountHeld());
    }

    // REQUIRES: valid coin name, the coin itself and integer amount >=0
    // MODIFIES: portfolio, coin
    // EFFECTS: subtracts amount*coin from portfolio and adds the equivalent value of USD to portfolio
    // - if the new balance of selected coin is 0, it is removed from portfolio
    private void executeSell(String name, Coin c, int amount) {
        if (amount >= 0) {

            if (portfolio.getAmountOfCoinHeld(name) >= amount) {
                portfolio.addCoins(name, - amount);
                portfolio.addCoins("USD", amount * c.getPrice());

                if (portfolio.getAmountOfCoinHeld(name) == 0) {
                    portfolio.getCoinList().remove(c);
                }
            } else {
                System.out.println("Not enough money");
            }
        } else {
            System.out.println("Please enter a valid input");
        }
        updatePortfolioCoin();
    }

    // REQUIRES: the valid name of a coin and the actual coin
    // MODIFIES: portfolio, coin
    // EFFECTS: Withdraws the amount of the selected coin; if the new balance in user's portfolio of select
    // coin is 0 then the coin is removed from portfolio
    private void executeWithdraw() {

        try {
            int a = Integer.parseInt(withdrawUsdField.getText());
            if (a >= 0) {
                if (portfolio.getAmountOfCoinHeld("USD") >= a) {
                    portfolio.addCoins("USD", - a);

                    if (portfolio.getAmountOfCoinHeld("USD") == 0) {
                        portfolio.getCoinList().remove(usd);
                    }
                }

            } else {
                System.out.println("Please enter a valid input");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a positive integer");
        }
        updatePortfolioCoin();
    }

    // MODIFIES: portfolio, usd
    // EFFECTS: takes user inputs from text box to see how much USD they want to deposit into their wallet
    // - checks to see if USD is already a part of user's portfolio, if not, then it is added
    public void addMoney() {

        try {
            int a = Integer.parseInt(depositUsdField.getText());
            if (a >= 0) {
                if (!portfolio.containsCoin("USD")) {
                    portfolio.addCoinToList(usd);
                }
                portfolio.addCoins("USD", a);
            } else {
                System.out.println("Please enter a valid input");
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a positive integer");
        }
        updatePortfolioCoin();
    }



}
