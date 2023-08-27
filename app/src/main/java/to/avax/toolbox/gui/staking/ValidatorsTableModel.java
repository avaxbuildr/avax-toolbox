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

import lombok.Data;
import lombok.val;
import to.avax.avalanche.apis.platformvm.dto.ValidatorDTO;
import to.avax.avalanche.apis.platformvm.dto.ValidatorsDTO;
import to.avax.avalanche.utils.Constants;
import to.avax.avalanche.utils.NumberUtils;

import javax.swing.table.AbstractTableModel;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.TimeZone;

@Data
public class ValidatorsTableModel extends AbstractTableModel {
    ValidatorsDTO validators;

    final String[] columns = {
            "Node ID ▼▲",
            "Uptime ▼▲",
            "Start Time ▼▲",
            "End Time ▼▲",
            "Fee % ▼▲",
            "# Deleg. ▼▲",
            "Own AVAX ▼▲",
            "Ext AVAX ▼▲",
            "Free AVAX ▼▲"
    };

    final Class<?>[] columnTypes = {
            String.class,
            Double.class,
            LocalDateTime.class,
            LocalDateTime.class,
            Double.class,
            Long.class,
            BigInteger.class,
            BigInteger.class,
            BigInteger.class
    };

    public ValidatorsTableModel(ValidatorsDTO validators) {
        super();
        this.validators = validators;
    }

    @Override
    public int getRowCount() {
        return validators.getValidators().size();
    }

    @Override
    public int getColumnCount() {
        return columns.length;
    }
    @Override
    public String getColumnName(int col) {
        return columns[col];
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ValidatorDTO row = validators.getValidators().get(rowIndex);
        switch(columnIndex) {
            case 0 -> {
                return row.getNodeID();
            }
            case 1 -> {
                return row.getUptime();
            }
            case 2 -> {
                long uTimestamp = row.getStartTime() * 1000;
                LocalDateTime localDateTime =  LocalDateTime.ofInstant(Instant.ofEpochMilli(uTimestamp),
                                TimeZone.getDefault().toZoneId());
                return localDateTime;
            }
            case 3 -> {
                long uTimestamp = row.getEndTime() * 1000;
                LocalDateTime localDateTime =  LocalDateTime.ofInstant(Instant.ofEpochMilli(uTimestamp),
                        TimeZone.getDefault().toZoneId());
                return localDateTime;
            }
            case 4 -> {
                return row.getDelegationFee();
            }
            case 5 -> {
                return row.getDelegatorCount();
            }
            case 6 -> {
                return NumberUtils.bigToLocaleString(new BigDecimal( NumberUtils.bnToAvaxX(row.getWeight())), 2);
            }
            case 7 -> {
                return NumberUtils.bigToLocaleString(new BigDecimal( NumberUtils.bnToAvaxX(row.getDelegatorWeight())), 2);
            }
            case 8 -> {
                BigInteger freeAmount = new BigInteger("3000000").multiply(Constants.ONEAVAX);
                BigDecimal bd = new BigDecimal(freeAmount.subtract(row.getWeight().add(row.getDelegatorWeight())));
                return  NumberUtils.bigToLocaleString(bd.divide(new BigDecimal(Constants.ONEAVAX)), 2);
            }
            default -> {
                return "N/A";
            }
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex < columnTypes.length)
            return columnTypes[columnIndex];
        return super.getColumnClass(columnIndex);
    }
}
