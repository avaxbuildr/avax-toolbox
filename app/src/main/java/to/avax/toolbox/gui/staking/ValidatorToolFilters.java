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
package to.avax.toolbox.gui.staking;

import javax.swing.*;
import java.awt.*;

public class ValidatorToolFilters extends JPanel {

    ValidatorToolPanel validatorsFrame;
    ValidatorsTable table;
    public ValidatorToolFilters(ValidatorToolPanel jf){
        validatorsFrame = jf;
        table = jf.getTable();
        ((FlowLayout)getLayout()).setAlignment(FlowLayout.LEFT);

        JLabel mutf = new JLabel("Min. % Uptime: ");
        add(mutf);
        JTextField minUptimeTextField = new JTextField("90");
        add(minUptimeTextField);

        JLabel mutf3 = new JLabel("Min. Avail $AVAX: ");
        add(mutf3);
        JTextField minAvaxAvailTextField = new JTextField("1000");
        add(minAvaxAvailTextField);

        JButton jb3 = new JButton("Apply Filters");

        jb3.addActionListener(e -> {

        });

        add(jb3);

    }
}
