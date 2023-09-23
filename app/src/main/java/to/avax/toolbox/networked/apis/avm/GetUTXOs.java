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

package to.avax.toolbox.networked.apis.avm;

import to.avax.avalanche.Avalanche;
import to.avax.avalanche.apis.avm.AVMAPI;
import to.avax.avalanche.common.RequestResponseData;
import to.avax.avalanche.network.Constants;
import to.avax.avalanche.network.NetworkConfig;

import java.util.List;
import java.util.Map;

public class GetUTXOs {
    public static void main(String[] args) {
        NetworkConfig netConfig = Constants.getTestConfig();
        Avalanche avalanche = new Avalanche(netConfig);
        AVMAPI api = avalanche.getAvmAPI();
        List<String> addresses = List.of("X-fuji1ulxld0t2kfeq4qvvrjasndnu2hgx9han4uq6yg");
        RequestResponseData rrd = api.callMethod("avm.getUTXOs", Map.of("addresses", addresses));
        System.out.println(rrd.getData().toPrettyString());
    }
}
