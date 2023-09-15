/*
 *
 * AVAX Toolbox - An Avalanche Toolbox
 * Copyright (C) 2023 @REKTBuildr
 *
 * For more information, visit:
 * https://crypto.bi
 *
 **/
package to.avax.toolbox.gui.wallet;

import to.avax.avalanche.wallet.HdWalletCore;
import to.avax.avalanche.wallet.MnemonicPhrase;
import to.avax.avalanche.wallet.MnemonicWallet;
import to.avax.toolbox.gui.AvaxtoPanel;
import to.avax.toolbox.gui.ToolboxFrame;
import to.avax.toolbox.gui.wallet.addresses.AvaxtoWalletAddressPanel;

import javax.swing.*;
import java.awt.*;

public class AvaxtoWalletPanel extends AvaxtoPanel {

    JLabel statusLabel;
    private MnemonicPhrase mnemonic;
    final private JPanel centralPanel;
    JPanel unloggedPanel;
    HdWalletCore wallet;
    AvaxtoWalletAddressPanel xAddressesPanel;
    AvaxtoWalletAddressPanel pAddressesPanel;

    private final static int DEFAULT_ADDRESS_COUNT = 20;
    AvaxtoWalletMainMenu jmb;

    public void listPAddresses() {
        pAddressesPanel.listAddresses();
        centralPanel.removeAll();
        centralPanel.add(pAddressesPanel, BorderLayout.CENTER);
        centralPanel.revalidate();
    }

    public void listXAddresses() {
        xAddressesPanel.listAddresses();
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

    private void initFromMnemonic(String mnm) {
        this.mnemonic = new MnemonicPhrase(mnm);
        this.wallet = new MnemonicWallet(this.mnemonic.getValue());

        xAddressesPanel = new AvaxtoWalletAddressPanel(this, DEFAULT_ADDRESS_COUNT, wallet.getExternalHelper());
        pAddressesPanel = new AvaxtoWalletAddressPanel(this, DEFAULT_ADDRESS_COUNT, wallet.getPlatformHelper());
    }

    public AvaxtoWalletPanel(ToolboxFrame tf) {
        super(tf);
        setLayout(new BorderLayout());

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

            unloggedPanel.setVisible(false);

            initFromMnemonic(jpjta.getText());
            activateWalletPane();
        });

        unloggedInternalPanel.add(jpjl);
        unloggedInternalPanel.add(jpjta);
        unloggedInternalPanel.add(jpbut);

        unloggedPanel.add(unloggedInternalPanel, BorderLayout.LINE_START);

        centralPanel.add(unloggedPanel, BorderLayout.CENTER);
        add(centralPanel, BorderLayout.CENTER);

        statusLabel = new JLabel("Wallet login.");
        add(statusLabel, BorderLayout.SOUTH);

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

        initFromMnemonic(mnemonic);
        activateWalletPane();
    }
}
