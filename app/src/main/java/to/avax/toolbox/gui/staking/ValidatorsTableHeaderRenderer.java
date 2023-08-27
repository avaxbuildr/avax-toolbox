package to.avax.toolbox.gui.staking;

import to.avax.toolbox.gui.common.ToolboxGUI;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ValidatorsTableHeaderRenderer extends JLabel implements TableCellRenderer {

    public ValidatorsTableHeaderRenderer() {
        setBackground(ToolboxGUI.AVALANCHE_COLOR);
        setForeground(Color.BLACK);
        setHorizontalTextPosition(JLabel.CENTER);
        setHorizontalAlignment(JLabel.CENTER);
    }
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText(value.toString());
        return this;
    }
}
