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

import to.avax.avalanche.apis.platformvm.dto.ValidatorsDTO;
import javax.swing.*;

/*
columns, from avax wallet
* node id, validator stake, available, delegator count, end time, fee %, selectbutton
* */

public class ValidatorsTable extends JTable {
    ValidatorsDTO validators;
    ValidatorsTableModel vtModel;
    public ValidatorsTable(ValidatorsDTO validators) {
        super(new ValidatorsTableModel(validators));

        this.validators = validators;
        vtModel = (ValidatorsTableModel)getModel();
        setAutoCreateRowSorter(true);

        getTableHeader().setDefaultRenderer(new ValidatorsTableHeaderRenderer());

        ValidatorCellRenderer vRenderer = new ValidatorCellRenderer();

        for (int i=0; i < vtModel.getColumnCount(); i++) {
            getColumnModel().getColumn(i).setCellRenderer(vRenderer);
        }
    }

}
