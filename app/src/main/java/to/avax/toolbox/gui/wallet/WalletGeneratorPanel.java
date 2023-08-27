
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

        JButton jb = new JButton("Generate");
        JButton jb2 = new JButton("▶ Wallet Helper");
        JButton jb3 = new JButton("Copy Mnemonic");
        JButton jb4 = new JButton("Clear");

        jb2.setEnabled(false);
        jb3.setEnabled(false);
        jb4.setEnabled(false);

        jb.setToolTipText("Generate a new Avalanche mnemonic phrase");
        jb2.setToolTipText("Open the mnemonic phrase in AVAX Wallet Helper");
        jb3.setToolTipText("Copy the mnemonic phrase to system clipboard. Make sure to clear ASAP.");
        jb4.setToolTipText("Clear the mnemonic phrase from system clipboard and visible textbox. Cannot be undone.");


        jb.addActionListener(e -> {
            var mnm = WalletFunctions.generateMnemonic();
            jtf.setText(String.join(" ", mnm));
            jb2.setEnabled(true);
            jb3.setEnabled(true);
            jb4.setEnabled(true);
            statusBar.setText("Save mnemonic immediately. IT WILL NOT BE SHOWN AGAIN.");
        });

        jb2.addActionListener(e -> {
           new AvaxtoWalletPanel(tf, jtf.getText());
        });

        jb3.addActionListener(e -> {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(jtf.getText()), null);
            statusBar.setText("Mnemonic copied. Save it immediately and clear clipboard.");
        });

        jb4.addActionListener(e -> {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(""), null);
            jtf.setText("");
            jb2.setEnabled(false);
            jb3.setEnabled(false);
            jb4.setEnabled(false);
            statusBar.setText("Clipboard and visible phrase cleared.");
        });

        buttonsPanel.add(jb);
        buttonsPanel.add(jb3);
        buttonsPanel.add(jb2);
        buttonsPanel.add(jb4);

        add(buttonsPanel, BorderLayout.NORTH);

        statusBar = new JLabel("Generate a new 256 bit mnenomic phrase.");
        add(statusBar, BorderLayout.SOUTH);
        setVisible(true);
    }

}
