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

package to.avax.toolbox.networked.apis.info;

import to.avax.avalanche.Avalanche;
import to.avax.avalanche.common.RequestResponseData;
import to.avax.avalanche.network.Constants;
import to.avax.avalanche.network.NetworkConfig;

import java.util.Map;

public class Peers {
    public static void main(String[] args) {
        NetworkConfig netConfig = Constants.getTestConfig();
        Avalanche avalanche = new Avalanche(netConfig);
        RequestResponseData rrd = avalanche.getInfoAPI().callMethod("info.getNodeVersion", Map.of());
        System.out.println(rrd);
    }
}
