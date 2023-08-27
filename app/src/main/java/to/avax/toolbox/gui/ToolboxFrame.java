package to.avax.toolbox.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.beans.PropertyChangeListener;

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
