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

import to.avax.avalanche.wallet.WalletFunctions;

/**
 * Sample app generates a new mnemonic wallet
 * */
public class GenerateWallet {
    public static void main(String[] args) {
        var mnm = WalletFunctions.generateMnemonic();
        System.out.println(String.join(" ", mnm));
    }
}
