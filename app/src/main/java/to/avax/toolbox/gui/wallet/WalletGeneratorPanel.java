
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

import to.avax.avalanche.wallet.WalletFunctions;
import to.avax.toolbox.gui.AvaxtoPanel;
import to.avax.toolbox.gui.ToolboxFrame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.datatransfer.StringSelection;

public class WalletGeneratorPanel extends AvaxtoPanel {
    JLabel statusBar;
    public WalletGeneratorPanel(ToolboxFrame  tf) {
        super(tf);

        BorderLayout gbl = new BorderLayout();
        setLayout(gbl);

        JTextArea jtf = new JTextArea(4, 35);
        jtf.setLineWrap(true);
        jtf.setBorder(new EmptyBorder(3,3,3,3));
        add(jtf, BorderLayout.CENTER);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBorder(new EmptyBorder(3,3,3,3));
        ((FlowLayout)buttonsPanel.getLayout()).setAlignment(FlowLayout.LEFT);

        JButton generateButton = new JButton("Generate");
        JButton helperButton = new JButton("▶ Wallet Helper");
        JButton copyButton = new JButton("Copy Mnemonic");
        JButton clearButton = new JButton("Clear");

        helperButton.setEnabled(false);
        copyButton.setEnabled(false);
        clearButton.setEnabled(false);

        generateButton.setToolTipText("Generate a new Avalanche mnemonic phrase");
        helperButton.setToolTipText("Open the mnemonic phrase in AVAX Wallet Helper");
        copyButton.setToolTipText("Copy the mnemonic phrase to system clipboard. Make sure to clear ASAP.");
        clearButton.setToolTipText("Clear the mnemonic phrase from system clipboard and visible textbox. Cannot be undone.");


        generateButton.addActionListener(e -> {
            var mnm = WalletFunctions.generateMnemonic();
            if (mnm != null) {
                jtf.setText(String.join(" ", mnm));
                helperButton.setEnabled(true);
                copyButton.setEnabled(true);
                clearButton.setEnabled(true);
                statusBar.setText("Save mnemonic immediately. IT WILL NOT BE SHOWN AGAIN.");
            } else {
                throw new RuntimeException("Null mnemonic returned. Please notify developers about this error.");
            }
        });

        helperButton.addActionListener(e -> {
            AvaxtoWalletPanel awp = new AvaxtoWalletPanel(tf, jtf.getText());
            getMainFrame().getMainPane().add(awp, "Wallet");
            getMainFrame().getMainPane().setSelectedComponent(awp);
        });

        copyButton.addActionListener(e -> {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(jtf.getText()), null);
            statusBar.setText("Mnemonic copied. Save it immediately and clear clipboard.");
        });

        clearButton.addActionListener(e -> {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);
            jtf.setText("");
            helperButton.setEnabled(false);
            copyButton.setEnabled(false);
            clearButton.setEnabled(false);
            statusBar.setText("Clipboard and visible phrase cleared.");
        });

        buttonsPanel.add(generateButton);
        buttonsPanel.add(copyButton);
        buttonsPanel.add(helperButton);
        buttonsPanel.add(clearButton);

        add(buttonsPanel, BorderLayout.NORTH);

        statusBar = new JLabel("Generate a new 256 bit mnenomic phrase.");
        add(statusBar, BorderLayout.SOUTH);
        setVisible(true);
    }

}
