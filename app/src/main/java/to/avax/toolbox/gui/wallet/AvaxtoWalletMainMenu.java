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

    AvaxtoWalletPanel mainFrame;
    public AvaxtoWalletMainMenu(AvaxtoWalletPanel fj) {
        this.mainFrame = fj;
        JMenu mainMenu = new JMenu("Wallet");
        JMenuItem jmiXAddresses = new JMenuItem("X Addresses");
        JMenuItem jmiPAddresses = new JMenuItem("P Addresses");
        JMenuItem jmiClose = new JMenuItem("Close");

        jmiXAddresses.addActionListener(x -> {
            mainFrame.listXAddresses();
        });

        jmiPAddresses.addActionListener(x -> {
            mainFrame.listPAddresses();
        });

        jmiClose.addActionListener(x -> {
            mainFrame.getMainFrame().remove(fj);
        });

        mainMenu.add(jmiXAddresses);
        mainMenu.add(jmiPAddresses);
        mainMenu.add(jmiClose);
        this.add(mainMenu);
    }


}
