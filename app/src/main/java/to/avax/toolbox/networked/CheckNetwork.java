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

package to.avax.toolbox.networked;

import static to.avax.avalanche.AvaNetwork.MainnetConfig;
import static to.avax.avalanche.AvaNetwork.TestnetConfig;

public class CheckNetwork {
    public static void main(String[] args) {

        boolean testMainnet = MainnetConfig.testConnection();
        System.out.println("Mainnet Check OK? " + testMainnet);

        boolean testTestnet = TestnetConfig.testConnection();
        System.out.println("Testnet Check OK? " + testTestnet);
    }

}

