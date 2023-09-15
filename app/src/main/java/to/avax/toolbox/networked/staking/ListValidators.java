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
import to.avax.avalanche.utils.Constants;

import static to.avax.avalanche.AvaNetwork.MainnetConfig;

/*
* List current validators
* */
public class ListValidators {
    public static void main(String[] args) {
        var avalanche = new Avalanche(MainnetConfig);
        var pchain = avalanche.getPlatformVMAPI();
        var validators = pchain.getCurrentValidators(Constants.PblockchainID, null);
        System.out.println(validators.getData().toPrettyString());
    }
}
