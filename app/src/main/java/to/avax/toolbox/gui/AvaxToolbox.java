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
package to.avax.toolbox.gui;

import to.avax.toolbox.gui.common.ToolboxUtils;
import to.avax.toolbox.gui.common.ToolboxStatusBar;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URISyntaxException;
import to.avax.toolbox.gui.main.ToolboxMainMenu;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class AvaxToolbox {

    ToolboxMainMenu mainMenu;
    ToolboxStatusBar statusBar;

    private ToolboxFrame mainFrame;
    private JTabbedPane mainPane;

    public static void main(String args[]) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AvaxToolbox mainApp = new AvaxToolbox();
                mainApp.initGUI();

            }
        });
    }

    public void initGUI() {

        mainPane = new JTabbedPane();

        mainFrame = new ToolboxFrame("AVAX.TO olbox", mainPane);

        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        mainMenu = new ToolboxMainMenu(mainFrame);
        mainFrame.setJMenuBar(mainMenu);

        JPanel homePanel = new JPanel();
        homePanel.setLayout(new BorderLayout());

        var centerLabelHtml = """
                    <center>
                        <span style="color: #ff0000; font-size: 15px; font-family: sans-serif; text-decoration: none;">
                        <a href="https://crypto.bi">crypto.bi</a> 
                        &nbsp; | &nbsp; 
                        <a href="https://avax.to">avax.to</a>
                        &nbsp; | &nbsp;
                        <a href="https://twitter.com/avaxto">@avaxto</a>
                        &nbsp; | &nbsp;
                        <a href="https://twitter.com/avaxbuildr">@avaxbuildr</a>
                        </span>
                    </center>
                """;

        ImageIcon icon;
        Image i = ToolboxUtils.getImage("avaxto.png");
        if (i != null) {
            icon = new ImageIcon(i);
        } else {
            icon = new ImageIcon();
        }

        JLabel centerLabel = new JLabel(icon);
        centerLabel.setVerticalTextPosition(JLabel.BOTTOM);
        centerLabel.setHorizontalTextPosition(JLabel.CENTER);
        centerLabel.setHorizontalAlignment(JLabel.CENTER);
        centerLabel.setVerticalAlignment(JLabel.CENTER);
        centerLabel.setBackground(Color.BLACK);

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BorderLayout());

        JEditorPane jep = new JEditorPane();
        jep.setContentType("text/html");
        jep.setText(centerLabelHtml);
        jep.setEditable(false);
        jep.setBackground(Color.BLACK);

        jep.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent hle) {
                if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                    try {
                        Desktop.getDesktop().browse(hle.getURL().toURI());
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } catch (URISyntaxException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

        centerPanel.add(jep, BorderLayout.SOUTH);
        centerPanel.add(centerLabel, BorderLayout.CENTER);

        homePanel.add(centerPanel, BorderLayout.CENTER);
        mainPane.add("Home", homePanel);

        mainFrame.add(mainPane, BorderLayout.CENTER);
        statusBar = new ToolboxStatusBar();
        mainFrame.add(statusBar, BorderLayout.SOUTH);
        mainFrame.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
                mainMenu.disposeValidatorPanel();
            }
        });
        // ---------------
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setExtendedState(mainFrame.getExtendedState()|JFrame.MAXIMIZED_BOTH);
        mainFrame.setVisible(true);

        KeyboardFocusManager keyManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyManager.addKeyEventDispatcher(new KeyEventDispatcher() {

            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyCode() == KeyEvent.VK_W && e.isControlDown()) {
                    getMainPane().remove(getMainPane().getSelectedComponent());
                }
                return false;
            }

        });
    }

    public JTabbedPane getMainPane() {
        return mainPane;
    }

    public JFrame getMainFrame() {
        return mainFrame;
    }
}
