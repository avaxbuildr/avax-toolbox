/*
 *
 * AVAX Toolbox - An Avalanche Toolbox
 * Copyright (C) 2023 AVAX Buildr @avaxbuildr
 *
 *
 * For more information, visit:
 * https://crypto.bi
 * https://avax.to
 * https://twitter.com/avaxbuildr
 *
 *
 **/
package to.avax.toolbox.gui.wallet;

import to.avax.avalanche.wallet.HdHelper;
import to.avax.avalanche.wallet.HdWalletCore;
import to.avax.avalanche.wallet.MnemonicWallet;
import to.avax.toolbox.gui.AvaxtoPanel;
import to.avax.toolbox.gui.ToolboxFrame;

import javax.swing.*;
import java.awt.*;

public class AvaxtoWalletPanel extends AvaxtoPanel {

    JLabel statusLabel;
    private String mnemonic;
    final private JPanel centralPanel;
    JPanel unloggedPanel;
    HdWalletCore wallet;
    JPanel xAddressesPanel;
    JPanel pAddressesPanel;

    JPanel xAddressesButtonPanel;
    JPanel pAddressesButtonPanel;
    JTextArea xAddressesTextArea;
    JTextArea pAddressesTextArea;

    private int currentXPage;
    private int currentPPage;

    private final static int DEFAULT_ADDRESS_COUNT = 20;
    AvaxtoWalletMainMenu jmb;

    public void listPAddresses() {
        listPAddresses(0, DEFAULT_ADDRESS_COUNT);
    }

    public void listPAddresses(int page, int addressCount) {
        int index = (page * addressCount);
        int lastIndex = index + addressCount;

        pAddressesTextArea.setText("");
        StringBuilder sb = new StringBuilder();
        HdHelper hdh = wallet.getPlatformHelper();
        for (; index<lastIndex; index++) {
            String address = hdh.getAddressForIndex(index);
            sb.append(address);
            sb.append("\n");
        }
        pAddressesTextArea.setText(sb.toString());
        centralPanel.removeAll();
        centralPanel.add(pAddressesPanel, BorderLayout.CENTER);
        centralPanel.revalidate();
    }

    public void listXAddresses() {
        listPAddresses(0, DEFAULT_ADDRESS_COUNT);
    }
    public void listXAddresses(int page, int addressCount) {
        int index = (page * addressCount);
        int lastIndex = index + addressCount;
        xAddressesTextArea.setText("");
        StringBuilder sb = new StringBuilder();
        HdHelper hdh = wallet.getExternalHelper();
        for (; index<lastIndex; index++) {
            String address = hdh.getAddressForIndex(index);
            sb.append(address);
            sb.append("\n");
        }
        xAddressesTextArea.setText(sb.toString());
        centralPanel.removeAll();
        centralPanel.add(xAddressesPanel, BorderLayout.CENTER);
        centralPanel.revalidate();
    }

    // has a mnemonic or privkey and can be activated
    private void activateWalletPane(){
        centralPanel.removeAll();
        centralPanel.add(new JPanel());
        statusLabel.setText("Wallet ready");
    }
    public AvaxtoWalletPanel(ToolboxFrame tf) {
        super(tf);
        setLayout(new BorderLayout());

        currentPPage = 0;
        currentXPage = 0;

        centralPanel = new JPanel();
        centralPanel.setLayout(new BorderLayout());

        jmb = new AvaxtoWalletMainMenu(this);
        add(jmb, BorderLayout.NORTH);

        JPanel unloggedInternalPanel = new JPanel();
        unloggedPanel = new JPanel();
        unloggedPanel.setLayout(new BorderLayout());

        JLabel jpjl = new JLabel("Enter mnemonic");
        JTextArea jpjta = new JTextArea(3, 60);
        JButton jpbut = new JButton("Go >>");

        jpbut.addActionListener( e -> {

            if (!MnemonicWallet.validateMnemonic(jpjta.getText())) {
                JOptionPane.showMessageDialog(this, "Invalid mnemonic phrase. Unable to open wallet.", "Wallet Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            this.mnemonic = jpjta.getText();
            this.wallet = new MnemonicWallet(this.mnemonic);

            unloggedPanel.setVisible(false);
            activateWalletPane();
        });

        unloggedInternalPanel.add(jpjl);
        unloggedInternalPanel.add(jpjta);
        unloggedInternalPanel.add(jpbut);

        unloggedPanel.add(unloggedInternalPanel, BorderLayout.LINE_START);

        xAddressesTextArea = new JTextArea();
        pAddressesTextArea = new JTextArea();

        JScrollPane jspx = new JScrollPane(xAddressesTextArea);
        JScrollPane jspp = new JScrollPane(pAddressesTextArea);

        xAddressesPanel = new JPanel();
        pAddressesPanel = new JPanel();

        xAddressesButtonPanel = new JPanel();
        pAddressesButtonPanel = new JPanel();

        ((FlowLayout)xAddressesButtonPanel.getLayout()).setAlignment(FlowLayout.LEFT);
        ((FlowLayout)pAddressesButtonPanel.getLayout()).setAlignment(FlowLayout.LEFT);

        JButton xAddrNextButton = new JButton(">>");
        JButton xAddrPrevButton = new JButton("<<");

        JButton pAddrNextButton = new JButton(">>");
        JButton pAddrPrevButton = new JButton("<<");

        JLabel xAddrPageLabel = new JLabel("1");
        JLabel pAddrPageLabel = new JLabel("1");

        xAddressesButtonPanel.add(xAddrPrevButton);
        xAddressesButtonPanel.add(xAddrPageLabel);
        xAddressesButtonPanel.add(xAddrNextButton);

        pAddressesButtonPanel.add(pAddrPrevButton);
        pAddressesButtonPanel.add(pAddrPageLabel);
        pAddressesButtonPanel.add(pAddrNextButton);

        xAddressesPanel.setLayout(new BorderLayout());
        pAddressesPanel.setLayout(new BorderLayout());

        xAddressesPanel.add(xAddressesButtonPanel, BorderLayout.NORTH);
        pAddressesPanel.add(pAddressesButtonPanel, BorderLayout.NORTH);

        xAddressesPanel.add(jspx, BorderLayout.CENTER);
        pAddressesPanel.add(jspp, BorderLayout.CENTER);

        centralPanel.add(unloggedPanel, BorderLayout.CENTER);
        add(centralPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Wallet login.");
        add(statusLabel, BorderLayout.SOUTH);

        // ---------- Button Handlers ------------------

        xAddrNextButton.addActionListener( e -> {
            currentXPage++;
            listXAddresses(currentXPage, DEFAULT_ADDRESS_COUNT);
            xAddrPageLabel.setText(String.valueOf(currentXPage + 1));
        });

        xAddrPrevButton.addActionListener( e -> {
            if (currentXPage > 0) {
                currentXPage--;
            }
            listXAddresses(currentPPage, DEFAULT_ADDRESS_COUNT);
            xAddrPageLabel.setText(String.valueOf(currentXPage + 1));

        });

        pAddrNextButton.addActionListener( e -> {
            currentPPage++;
            listPAddresses(currentPPage, DEFAULT_ADDRESS_COUNT);
            pAddrPageLabel.setText(String.valueOf(currentPPage + 1));

        });

        pAddrPrevButton.addActionListener( e -> {
            if (currentPPage > 0) {
                currentPPage--;
            }
            listPAddresses(currentPPage, DEFAULT_ADDRESS_COUNT);
            pAddrPageLabel.setText(String.valueOf(currentPPage + 1));

        });


    }

    /**
     * Construct a new MnemonicWallet helper
     * */
    public AvaxtoWalletPanel(ToolboxFrame tf, String mnemonic) {
        this(tf);

        if (!MnemonicWallet.validateMnemonic(mnemonic)) {
            JOptionPane.showMessageDialog(this, "Invalid mnemonic phrase. Unable to open wallet.", "Wallet Error", JOptionPane.ERROR_MESSAGE);
            tf.getMainPane().remove(this);
            return;
        }

        this.mnemonic = mnemonic;
        this.wallet = new MnemonicWallet(this.mnemonic);

        activateWalletPane();
    }
}
