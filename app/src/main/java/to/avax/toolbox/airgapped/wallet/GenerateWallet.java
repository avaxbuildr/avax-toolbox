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

import to.avax.avalanche.wallet.WalletFunctions;

/**
 * Sample app generates a new mnemonic wallet
 * */
public class GenerateWallet {
    public static void main(String[] args) {
        var mnm = WalletFunctions.generateMnemonic();
        if (mnm != null) {
            System.out.println(String.join(" ", mnm));
            return;
        }
        System.err.println("ERROR generating wallet. This might be a bug. Report at https://crypto.bi/forum/");
    }
}
