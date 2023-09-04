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

import javax.swing.*;

public class AvaxtoWalletMainMenu extends JMenuBar {

    AvaxtoWalletPanel walletPanel;
    public AvaxtoWalletMainMenu(AvaxtoWalletPanel fj) {
        this.walletPanel = fj;
        JMenu mainMenu = new JMenu("Wallet");
        JMenuItem jmiXAddresses = new JMenuItem("X Addresses");
        JMenuItem jmiPAddresses = new JMenuItem("P Addresses");
        JMenuItem jmiClose = new JMenuItem("Close");

        jmiXAddresses.addActionListener(x -> {
            walletPanel.listXAddresses();
        });

        jmiPAddresses.addActionListener(x -> {
            walletPanel.listPAddresses();
        });

        jmiClose.addActionListener(x -> {
            walletPanel.getMainFrame().getMainPane().remove(this.walletPanel);
        });

        mainMenu.add(jmiXAddresses);
        mainMenu.add(jmiPAddresses);
        mainMenu.add(jmiClose);
        this.add(mainMenu);
    }


}
