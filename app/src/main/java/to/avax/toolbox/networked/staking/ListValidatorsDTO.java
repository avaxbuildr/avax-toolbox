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
package to.avax.toolbox.networked.staking;

import to.avax.avalanche.Avalanche;
import to.avax.avalanche.apis.platformvm.PlatformVMAPIHelpers;
import to.avax.avalanche.utils.Constants;

import static to.avax.avalanche.AvaNetwork.MainnetConfig;

public class ListValidatorsDTO {
    public static void main(String[] args) {
        var avalanche = new Avalanche(MainnetConfig);
        var pchain = avalanche.getPlatformVMAPI();
        var validators = pchain.getCurrentValidators(Constants.PblockchainID, null);
        var validatorsDTO = PlatformVMAPIHelpers.validatorsResponseToDTO(validators);
        System.out.println(validatorsDTO);
    }
}
