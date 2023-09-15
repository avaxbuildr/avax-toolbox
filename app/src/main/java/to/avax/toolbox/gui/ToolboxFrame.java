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
import java.awt.*;

public class ToolboxFrame extends JFrame {
    JTabbedPane mainPane;

    public ToolboxFrame(String title, JTabbedPane mainPane) throws HeadlessException {
        super(title);
        this.mainPane = mainPane;
    }

    public JTabbedPane getMainPane() {
        return mainPane;
    }
}
