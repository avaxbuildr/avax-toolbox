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

package to.avax.toolbox.airgapped.wallet;

import to.avax.avalanche.network.Network;
import to.avax.avalanche.wallet.HdHelper;
import to.avax.avalanche.wallet.MnemonicWallet;

/*
 * Generate a list of addresses from a mnemonic phrase
 * */
public class TestAddresses {

    private final static int ADDRESS_COUNT = 20;
    private final static String MNEMONIC = "fetch affair risk reveal dolphin disagree message detail squeeze infant field clap budget vague sell raven awake what stomach blanket above naive wisdom quote";
    public static void main(String[] args) {

        String mnemonic = MNEMONIC;
        if (args.length > 0) {
            mnemonic = args[0];
        }

        System.out.println(mnemonic);

        var wallet = new MnemonicWallet(MNEMONIC);
        HdHelper hdh = wallet.getExternalHelper();

        for (int index=0; index<ADDRESS_COUNT; index++) {
            String address = hdh.getAddressForIndex(index, Network.getFuji().getNetworkID());
            System.out.println(index + ": " + address);
        }
    }
}
