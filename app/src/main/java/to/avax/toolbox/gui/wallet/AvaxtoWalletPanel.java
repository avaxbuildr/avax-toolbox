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
    private JPanel centralPanel;
    JPanel unloggedPanel;
    HdWalletCore wallet;
    JPanel xAddressesPanel;
    JPanel pAddressesPanel;
    JTextArea xAddressesTextArea;
    JTextArea pAddressesTextArea;
    int addressCount = 20;

    public void listPAddresses() {
        pAddressesTextArea.setText("");
        StringBuilder sb = new StringBuilder();
        HdHelper hdh = wallet.getPlatformHelper();
        for (int index=0; index<addressCount; index++) {
            String address = hdh.getAddressForIndex(index);
            sb.append(address + "\n");
        }
        pAddressesTextArea.setText(sb.toString());
        centralPanel.removeAll();
        centralPanel.add(pAddressesPanel);
        centralPanel.revalidate();
    }

    public void listXAddresses() {
        xAddressesTextArea.setText("");
        StringBuilder sb = new StringBuilder();
        HdHelper hdh = wallet.getExternalHelper();
        for (int index=0; index<addressCount; index++) {
            String address = hdh.getAddressForIndex(index);
            sb.append(address + "\n");
        }
        xAddressesTextArea.setText(sb.toString());
        centralPanel.removeAll();
        centralPanel.add(xAddressesPanel);
        centralPanel.revalidate();
    }

    // has a mnemonic or privkey and can be activated
    private void activateWalletPane(){
        centralPanel.removeAll();
        JLabel l = new JLabel("Wallet ready.");
        centralPanel.add(l);
        statusLabel.setText("Wallet initialized");
    }
    public AvaxtoWalletPanel(ToolboxFrame tf) {
        super(tf);

        centralPanel = new JPanel();

        AvaxtoWalletMainMenu jmb = new AvaxtoWalletMainMenu(this);
        add(jmb, BorderLayout.NORTH);

        unloggedPanel = new JPanel();
        JLabel jpjl = new JLabel("Enter mnemonic");
        JTextArea jpjta = new JTextArea(3, 30);
        JButton jpbut = new JButton("Go");

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

        unloggedPanel.add(jpjl);
        unloggedPanel.add(jpjta);
        unloggedPanel.add(jpbut);

        xAddressesTextArea = new JTextArea();
        pAddressesTextArea = new JTextArea();
        JScrollPane jspx = new JScrollPane(xAddressesTextArea);
        JScrollPane jspp = new JScrollPane(pAddressesTextArea);
        xAddressesPanel = new JPanel();
        pAddressesPanel = new JPanel();

        xAddressesPanel.add(jspx);
        pAddressesPanel.add(jspp);

        centralPanel.add(unloggedPanel);
        add(centralPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Wallet login.");
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);

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
