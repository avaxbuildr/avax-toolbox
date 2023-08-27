package to.avax.toolbox.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

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
