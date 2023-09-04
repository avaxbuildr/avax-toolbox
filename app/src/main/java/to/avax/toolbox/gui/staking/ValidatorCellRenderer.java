package to.avax.toolbox.gui.staking;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ValidatorCellRenderer extends DefaultTableCellRenderer {
    public ValidatorCellRenderer() {
        setHorizontalAlignment( JLabel.CENTER );
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (column == 1) {
            double uptime = (Double)value;
            if (uptime < 80) {
                c.setBackground(Color.RED);
            }
            if (uptime > 90) {
                c.setBackground(Color.GREEN);
            }
            if ( (uptime >= 80) && (uptime <= 90) ) {
                c.setBackground(Color.YELLOW);
            }
            if (uptime > 99) {
                JComponent jc = (JComponent) c;
                jc.setBorder(BorderFactory.createCompoundBorder());
                Font f = jc.getFont();
                jc.setFont(f.deriveFont(Font.BOLD));
                return jc;
            }
        } else {
            c.setBackground(table.getBackground());
        }



        return c;
    }


}
