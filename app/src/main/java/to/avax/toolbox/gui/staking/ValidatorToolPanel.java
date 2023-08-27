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

import to.avax.avalanche.Avalanche;
import to.avax.avalanche.apis.platformvm.PlatformVMAPI;
import to.avax.avalanche.apis.platformvm.PlatformVMAPIHelpers;
import to.avax.avalanche.apis.platformvm.dto.ValidatorsDTO;
import to.avax.avalanche.utils.Constants;
import to.avax.toolbox.gui.AvaxtoPanel;
import to.avax.toolbox.gui.ToolboxFrame;
import to.avax.toolbox.gui.common.ToolboxStatusBar;
import javax.swing.*;
import java.awt.*;

import static to.avax.avalanche.wallet.store.modules.network.NetworkConstants.MainnetConfig;

public class ValidatorToolPanel extends AvaxtoPanel {
    static ToolboxStatusBar statusBar;
    Avalanche avalanche;
    ValidatorsDTO validators;
    PlatformVMAPI pChain;
    ValidatorsTable table;

    public ValidatorToolPanel(ToolboxFrame tf) {

        super(tf);

        avalanche = new Avalanche(MainnetConfig);
        pChain = avalanche.getPlatformVMAPI();

        statusBar = new ToolboxStatusBar();
        add(statusBar, BorderLayout.SOUTH);

        ValidatorToolFilters vtf = new ValidatorToolFilters(this);
        add(vtf, BorderLayout.NORTH);

        new Thread(() -> {
            statusBar.setText("Loading validators...");
            var valRR = pChain.getCurrentValidators(Constants.PblockchainID, null);
            this.validators = PlatformVMAPIHelpers.validatorsResponseToDTO(valRR);
            System.out.println(validators);
            statusBar.setText("" + validators.getValidators().size() + " validators loaded.");
            table = new ValidatorsTable(validators);
            JScrollPane jsp = new JScrollPane(table);
            add(jsp, BorderLayout.CENTER);

        }).start();

        setVisible(true);
    }

    public ValidatorsTable getTable() {
        return table;
    }

}
