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
package to.avax.toolbox.gui.common;

import javax.swing.*;

public class FileExitJMenuBar extends JMenuBar {

    JFrame mainFrame;
    JMenu fileMenu;
    public FileExitJMenuBar(JFrame mainFrame) {
        super();
        this.mainFrame = mainFrame;

        fileMenu = new JMenu("File");
        JMenuItem fileExitMenuItem = new JMenuItem("Exit");
        fileExitMenuItem.addActionListener(e -> {
            this.mainFrame.dispose();
            System.exit(0);
        });
        fileMenu.add(fileExitMenuItem);
        add(fileMenu);
    }

    public JMenu getFileMenu() {
        return fileMenu;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
