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
package to.avax.toolbox.gui.help;

import to.avax.toolbox.gui.AvaxtoPanel;
import to.avax.toolbox.gui.ToolboxFrame;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class AboutPanel extends AvaxtoPanel {
    public AboutPanel(ToolboxFrame tf) {
        super(tf);
        var centerLabelHtml = """
                    <center>
                        <span style="color: #ff0000; font-size: 15px; font-family: sans-serif; text-decoration: none;">
                            AVAX Toolbox<br/>
                            Written by <a href="https://twitter.com/avaxbuildr">@avaxbuildr</a><br/>
                            <br>
                            Sponsored by:<br>
                            <a href="https://crypto.bi">crypto.bi</a> 
                            &nbsp; | &nbsp; 
                            <a href="https://avax.to">avax.to</a>
                            &nbsp; | &nbsp;
                            <a href="https://twitter.com/avaxto">@avaxto</a>

                        </span>
                    </center>
                """;
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

        add(jep, BorderLayout.CENTER);
        setVisible(true);
    }
}
