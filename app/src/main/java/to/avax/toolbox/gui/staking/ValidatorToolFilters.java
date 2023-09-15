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
package to.avax.toolbox.gui.staking;

import javax.swing.*;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.math.BigInteger;

public class ValidatorToolFilters extends JPanel {

    ValidatorToolPanel validatorsFrame;
    JTextField minUptimeTextField;
    JTextField minAvaxAvailTextField;

    private final static int UPTIME_COL = 1;
    private final static int AVAILABLE_COL = 8;

    public ValidatorToolFilters(ValidatorToolPanel jf){
        validatorsFrame = jf;
        ((FlowLayout)getLayout()).setAlignment(FlowLayout.LEFT);

        JLabel mutf = new JLabel("Min. % Uptime: ");
        add(mutf);
        minUptimeTextField = new JTextField("90", 3);

        add(minUptimeTextField);

        JLabel mutf3 = new JLabel("Min. Avail $AVAX: ");
        add(mutf3);
        minAvaxAvailTextField = new JTextField("1000", 7);
        add(minAvaxAvailTextField);

        JButton jb3 = new JButton("Apply Filters");

        jb3.addActionListener(e -> {
            final RowFilter<ValidatorsTableModel, Integer> filter = new RowFilter<>() {
                @Override
                public boolean include(RowFilter.Entry<? extends ValidatorsTableModel, ? extends Integer> entry) {
                    double minUptime = Double.parseDouble(minUptimeTextField.getText());
                    BigInteger minAvail = new BigInteger(minAvaxAvailTextField.getText());
                    int modelRow = entry.getIdentifier();
                    double rowUptime = (Double)entry.getModel().getValueAt(modelRow, UPTIME_COL);
                    BigInteger rowAvail = (BigInteger)entry.getModel().getValueAt(modelRow, AVAILABLE_COL);
                    return (rowAvail.compareTo(minAvail) >= 0) && (rowUptime >= minUptime);
                }
            };

            final TableRowSorter<ValidatorsTableModel> sorter = new TableRowSorter<>((ValidatorsTableModel)validatorsFrame.getTable().getModel());

            sorter.setRowFilter(filter);
            validatorsFrame.getTable().setRowSorter(sorter);
        });

        add(jb3);

    }
}
