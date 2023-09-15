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
package to.avax.toolbox.gui.help;

import to.avax.toolbox.gui.AvaxtoPanel;
import to.avax.toolbox.gui.ToolboxFrame;
import to.avax.toolbox.utils.ToolboxUtils;
import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;

public class AboutPanel extends AvaxtoPanel {
    public AboutPanel(ToolboxFrame tf) {
        super(tf);
        var centerLabelHtml = ToolboxUtils.readResourceAsString("/about.html");
        JEditorPane jep = new JEditorPane();
        jep.setContentType("text/html");
        jep.setText(centerLabelHtml);
        jep.setEditable(false);
        jep.setOpaque(false);
        jep.addHyperlinkListener(hle -> {
            if (HyperlinkEvent.EventType.ACTIVATED.equals(hle.getEventType())) {
                try {
                    Desktop.getDesktop().browse(hle.getURL().toURI());
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }
        });

        add(jep, BorderLayout.CENTER);
        setVisible(true);
    }
}
