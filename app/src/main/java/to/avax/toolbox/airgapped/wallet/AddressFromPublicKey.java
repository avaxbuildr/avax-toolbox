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

import to.avax.avalanche.apis.avm.AvmKeyPair;
import to.avax.avalanche.utils.Bintools;
import to.avax.avalanche.wallet.Types;

/**
 * Derive an address from an Avalanche public key
 * */
public class AddressFromPublicKey {

    // Example address X-avax1hdsvqfx8hwdgqhgregm765r9zzw4qgqt5e20wv

    static final int[] key = {
            2, 149, 241, 189, 171, 31, 150, 96,
            233, 24, 28, 177, 149, 59, 216, 223,
            193, 184, 108, 81, 195, 73, 53, 51,
            76, 99, 210, 241, 232, 215, 59, 57,
            246
    };

    public static void main(String[] args) {
        byte[] bKey = new byte[key.length];
        for (int i=0; i<key.length; i++) {
            bKey[i] = (byte) (key[i] & 0xff);
        }
        var addr = AvmKeyPair.addressFromPublicKey(bKey);
        String strAddr = Bintools.addressToString("avax", Types.ChainAlias.X, addr);
        System.out.println(strAddr);
    }

}
