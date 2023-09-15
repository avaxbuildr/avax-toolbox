/*
 *
 * AVAX Toolbox - An Avalanche Toolbox
 * Copyright (C) 2023 @REKTBuildr
 *
 *
 * For more information, visit:
 * https://crypto.bi
 *
 *
 **/
package to.avax.toolbox.gui.main;

import javax.swing.*;

import to.avax.toolbox.gui.ToolboxFrame;
import to.avax.toolbox.gui.common.FileExitJMenuBar;
import to.avax.toolbox.gui.help.AboutPanel;
import to.avax.toolbox.gui.staking.ValidatorToolPanel;
import to.avax.toolbox.gui.wallet.AvaxtoWalletPanel;
import to.avax.toolbox.gui.wallet.WalletGeneratorPanel;

import java.awt.*;
import java.net.URI;

public class ToolboxMainMenu extends FileExitJMenuBar {

    final private ToolboxFrame mainFrame;
    private ValidatorToolPanel validatorToolPanel;

    public void disposeValidatorPanel() {
        // if a validator window was created, dispose of it
        if (validatorToolPanel != null) {
            mainFrame.getMainPane().remove(validatorToolPanel);
        }
    }

    public ToolboxMainMenu(ToolboxFrame mainFrame) {
        super(mainFrame);
        this.mainFrame = mainFrame;

        JMenu walletMenu = new JMenu("Wallet");
        JMenu stakingMenu = new JMenu("Staking");
        JMenu helpMenu = new JMenu("Help");

        JMenuItem walletHelperMenuItem = new JMenuItem("Helper");
        walletHelperMenuItem.addActionListener(e -> {
            AvaxtoWalletPanel awp = new AvaxtoWalletPanel(mainFrame);
            mainFrame.getMainPane().add(awp, "Wallet Helper");
            mainFrame.getMainPane().setSelectedComponent(awp);

        });
        walletMenu.add(walletHelperMenuItem);

        JMenuItem walletGeneratorMenuItem = new JMenuItem("Generator");
        walletGeneratorMenuItem.addActionListener(e -> {
            WalletGeneratorPanel wgp = new WalletGeneratorPanel(mainFrame);
            mainFrame.getMainPane().add(wgp, "Wallet Generator");
            mainFrame.getMainPane().setSelectedComponent(wgp);
        });
        walletMenu.add(walletGeneratorMenuItem);

        JMenuItem validatorToolMenuItem = new JMenuItem("Validators");
        validatorToolMenuItem.addActionListener(e -> {
            // Validator loading is a heavy operation
            // create only a singleton validator tool panel
            if (validatorToolPanel == null) {
                validatorToolPanel = new ValidatorToolPanel(mainFrame);
            }

            validatorToolPanel.setVisible(true);
            mainFrame.getMainPane().add(validatorToolPanel, "Validatooors");
            mainFrame.getMainPane().setSelectedComponent(validatorToolPanel);
        });
        stakingMenu.add(validatorToolMenuItem);


        JMenuItem docAboutMenuItem = new JMenuItem("Documentation");
        docAboutMenuItem.addActionListener(e -> {
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(new URI("https://crypto.bi/category/avax-toolbox/"));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Please visit https://crypto.bi/avax-toolbox for documentation", "AVAX Toolbox Documentation", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        helpMenu.add(docAboutMenuItem);

        JMenuItem helpAboutMenuItem = new JMenuItem("About");
        helpAboutMenuItem.addActionListener(e -> {
            AboutPanel ap = new AboutPanel(mainFrame);
            mainFrame.getMainPane().add(ap,"About AVAX Toolbox");
            mainFrame.getMainPane().setSelectedComponent(ap);
        });
        helpMenu.add(helpAboutMenuItem);

        this.add(walletMenu);
        this.add(stakingMenu);
        this.add(helpMenu);

    }
}
