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
package to.avax.toolbox.gui;

import javax.swing.*;

public class AvaxtoPanel extends JPanel {

    ToolboxFrame mainFrame;

    private AvaxtoPanel() {}

    public AvaxtoPanel(ToolboxFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public ToolboxFrame getMainFrame() {
        return mainFrame;
    }
}
