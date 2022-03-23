package ui;

import model.Coin;
import model.Portfolio;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.Buttons.Button;
import ui.Buttons.GuiTestButtons.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GuiTest extends JPanel implements ActionListener {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private JPanel buttonArea;
    private JPanel buyMenu;
    private JPanel buyCoinMenu;

    private BuyBtc buyBtc;
    private BuyEth buyEth;
    private SellBtc sellBtc;
    private SellEth sellEth;
    private DepositUsd depositUSD;
    private WithdrawUsd withdrawUsd;
    private SetPortfolioDisplayCoin setPortfolioDisplayCoin;
    private SetPortfolioDisplayUsd setPortfolioDisplayUsd;
    private ExitProgram exit;

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
    private List<Button> buttons;

    private static Portfolio portfolio;
    private static Coin btc;
    private static Coin eth;
    private static Coin usd;

    private static final String JSON_STORE = "./data/datastore.json";
    private Scanner input;
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

        // readData();
    }


    public static void main(String[] args) {
        new GuiTest();
    }

    public GuiTest() {

        setCoins();
        initializeGraphics();


    }

    private void initializeGraphics() {

        //Create and set up the window.
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));


        portfolioValue = new JLabel("BTC: " + btc.getAmountHeld() + " ETH: " + eth.getAmountHeld()
                + " USD: " + usd.getAmountHeld());

        createButtons();
        addToList();
        addToPanel();


        frame.add(panel, BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

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

        setPortfolioDisplayCoin = new SetPortfolioDisplayCoin(this,panel);

        setPortfolioDisplayUsd = new SetPortfolioDisplayUsd(this,panel);

        exit = new ExitProgram(this,panel);



    }

    private void addToPanel() {
        for (JComponent i : itemList) {
            panel.add(i);
        }
    }

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

    public void performAction(String s) {
        System.out.println("deez nuts");
        if (s.equals("Buy BTC")) {
            executeBuy("Bitcoin", btc, 1);

        } else {
            System.out.println("invalid");
        }
    }

    // REQUIRES: valid name of a coin, the coin itself, integer amount >=0
    // MODIFIES: portfolio, coin
    // EFFECTS: if the amount of USD is sufficient to buy amount*selected coin, amount* selected coin is added into
    // the user's portfolio and the equivalent value of USD is subtracted. If the user doesnt have enough USD,
    // they are told that there is insufficient USD for the transaction.
    public void executeBuy(String name, Coin c, int amount) {
        if (!portfolio.containsCoin(name)) {
            portfolio.addCoinToList(c);
        }
        portfolio.addCoins(name, amount);
        updatePortfolioCoin();
    }

    private void updatePortfolioCoin() {
        portfolioValue.setText("BTC: " + btc.getAmountHeld() + " ETH: " + eth.getAmountHeld()
                + " USD: " + usd.getAmountHeld());
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }


}
