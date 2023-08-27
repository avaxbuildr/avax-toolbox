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

package to.avax.toolbox.airgapped.wallet;

import to.avax.avalanche.wallet.HdHelper;
import to.avax.avalanche.wallet.MnemonicWallet;

/*
* Generate a list of addresses from a mnemonic phrase
* */
public class WalletAddresses {
    private final static String MNEMONIC = "fetch affair risk reveal dolphin disagree message detail squeeze infant field clap budget vague sell raven awake what stomach blanket above naive wisdom quote";
    public static void main(String[] args) {
        var wallet = new MnemonicWallet(MNEMONIC);
        HdHelper hdh = wallet.getExternalHelper();

        for (int index=0; index<Integer.MAX_VALUE; index++) {
            String address = hdh.getAddressForIndex(index);
            System.out.println(index + ": " + address);
        }
    }
}
